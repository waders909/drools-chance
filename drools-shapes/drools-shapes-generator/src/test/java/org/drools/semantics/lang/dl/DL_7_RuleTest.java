/*
 * Copyright 2013 JBoss Inc
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *       http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package org.drools.semantics.lang.dl;

import org.drools.ClassObjectFilter;
import org.drools.KnowledgeBase;
import org.drools.KnowledgeBaseFactory;
import org.drools.builder.KnowledgeBuilder;
import org.drools.builder.KnowledgeBuilderFactory;
import org.drools.builder.ResourceType;
import org.drools.event.rule.ObjectInsertedEvent;
import org.drools.event.rule.ObjectRetractedEvent;
import org.drools.event.rule.ObjectUpdatedEvent;
import org.drools.event.rule.WorkingMemoryEventListener;
import org.drools.factmodel.traits.Entity;
import org.drools.io.Resource;
import org.drools.io.ResourceFactory;
import org.drools.io.impl.ByteArrayResource;
import org.drools.lang.DrlDumper;
import org.drools.lang.api.DescrFactory;
import org.drools.lang.api.PackageDescrBuilder;
import org.drools.runtime.StatefulKnowledgeSession;
import org.drools.semantics.builder.DLFactory;
import org.drools.semantics.builder.DLFactoryBuilder;
import org.drools.semantics.builder.model.OntoModel;
import org.drools.semantics.builder.reasoner.DLogicTransformer;
import org.drools.semantics.builder.reasoner.RecognitionRuleBuilder;
import org.junit.Test;
import org.semanticweb.owlapi.model.IRI;
import org.semanticweb.owlapi.model.OWLClassExpression;
import org.semanticweb.owlapi.model.OWLDataFactory;
import org.semanticweb.owlapi.model.OWLObjectIntersectionOf;
import org.semanticweb.owlapi.model.OWLObjectUnionOf;
import org.semanticweb.owlapi.model.OWLOntology;

import java.util.List;
import java.util.Map;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;


@SuppressWarnings("restriction")
public class DL_7_RuleTest {


    protected DLFactory factory = DLFactoryBuilder.newDLFactoryInstance();

    @Test
    public void testPizzaOntologyRecognition() {
        Resource res = ResourceFactory.newClassPathResource( "ontologies/pizza.owl" );

        OWLOntology pizza = factory.parseOntology( res );

        OntoModel pizzaModel = factory.buildModel( "pizza",
                res,
                OntoModel.Mode.NONE,
                DLFactory.defaultAxiomGenerators );

        String drl = new RecognitionRuleBuilder().createDRL( pizza, pizzaModel );

        System.err.println( "***********************************************************************" );
        System.err.println( drl );
        System.err.println( "***********************************************************************" );
    }



    @Test
    public void testExampleDNFRecognition() {
        Resource res = ResourceFactory.newClassPathResource( "ontologies/testDNF.owl" );

        OWLOntology onto = factory.parseOntology( res );

        OntoModel ontoModel = factory.buildModel( "test",
                res,
                OntoModel.Mode.NONE,
                DLFactory.defaultAxiomGenerators );

        String drl = new RecognitionRuleBuilder().createDRL( onto, ontoModel );

        KnowledgeBuilder kBuilder = KnowledgeBuilderFactory.newKnowledgeBuilder();
        kBuilder.add( new ByteArrayResource( drl.getBytes() ), ResourceType.DRL );


        String drl2 = "package t.x \n" +
                "import org.drools.factmodel.traits.Entity;\n" +
                "import org.drools.factmodel.traits.Thing;\n" +
                "" +
                "rule Init when \n" +
                "then \n" +
                "   Entity e = new Entity();\n" +
                "   insert( e ); \n" +
                "   don( e, W.class ); \n" +
                "end \n" +
                "" +
                "" +
                "rule Final when \n" +
                "  $s : String() \n" +
                "  $x : W() \n" +
                "then \n" +
                "   retract( $s );\n " +
                "   shed( $x, W.class ); \n " +
                "end \n";

        kBuilder.add( new ByteArrayResource( drl2.getBytes() ), ResourceType.DRL );
        if ( kBuilder.hasErrors() ) {
            fail( kBuilder.getErrors().toString() );
        }
        KnowledgeBase kbase = KnowledgeBaseFactory.newKnowledgeBase();
        kbase.addKnowledgePackages( kBuilder.getKnowledgePackages() );
        StatefulKnowledgeSession kSession = kbase.newStatefulKnowledgeSession();
        kSession.fireAllRules();

        for ( Object o : kSession.getObjects() ) {
            System.err.println( o );
        }

        System.err.println( "----------" );
        kSession.insert( "go" );
        kSession.fireAllRules();

        for ( Object o : kSession.getObjects() ) {
            System.err.println( o );
        }

    }



    @Test
    public void testDRLDumper() {
        PackageDescrBuilder packBuilder = DescrFactory.newPackage().name( "org.test" )
                .newRule().name( "org.test" )
                .lhs().and().or()
                        .and().pattern().type( "Integer" ).end().pattern().type( "Long" ).end().end()
                        .and().pattern().type( "String" ).end().end()
                .end().end().end()
                .rhs("")
                .end()
        .end();
        String drl = new DrlDumper().dump( packBuilder.getDescr() );
        System.out.println(drl);

        KnowledgeBuilder kb = KnowledgeBuilderFactory.newKnowledgeBuilder();
        kb.add( new ByteArrayResource( drl.getBytes() ), ResourceType.DRL );
        System.out.println( kb.getErrors() );
        assertFalse( kb.hasErrors() );
    }




    @Test
    public void testExampleDNFQuantifiers() {

        String owl = "" +
                "<?xml version=\"1.0\"?>\n" +
                "<rdf:RDF xmlns=\"http://t/x#\"\n" +
                "     xml:base=\"http://t/x\"\n" +
                "     xmlns:rdfs=\"http://www.w3.org/2000/01/rdf-schema#\"\n" +
                "     xmlns:owl=\"http://www.w3.org/2002/07/owl#\"\n" +
                "     xmlns:xsd=\"http://www.w3.org/2001/XMLSchema#\"\n" +
                "     xmlns:rdf=\"http://www.w3.org/1999/02/22-rdf-syntax-ns#\">\n" +
                "    <owl:Ontology rdf:about=\"http://t/x\"/>\n" +
                "    <owl:ObjectProperty rdf:about=\"http://t/x#objProp\"/>\n" +
                "    <owl:Class rdf:about=\"http://t/x#D\"/>\n" +
                "    <owl:Class rdf:about=\"http://t/x#E\">\n" +
                "        <owl:equivalentClass>\n" +
                "            <owl:Class>\n" +
                "                <owl:intersectionOf rdf:parseType=\"Collection\">\n" +
                "                    <rdf:Description rdf:about=\"http://t/x#D\"/>\n" +
                "                    <owl:Restriction>\n" +
                "                        <owl:onProperty rdf:resource=\"http://t/x#objProp\"/>\n" +
                "                        <owl:someValuesFrom rdf:resource=\"http://t/x#F\"/>\n" +
                "                    </owl:Restriction>\n" +
                "                </owl:intersectionOf>\n" +
                "            </owl:Class>\n" +
                "        </owl:equivalentClass>\n" +
                "    </owl:Class>\n" +
                "    <owl:Class rdf:about=\"http://t/x#F\"/>\n" +
                "</rdf:RDF>\n" +
                "\n";

        String drl2 = "package t.x; \n" +
                "import org.drools.factmodel.traits.Entity; \n" +
                "" +
                "declare Entity end\n" +
                "" +
                "rule Init \n" +
                "when \n" +
                "then \n" +
                "   Entity e1 = new Entity( \"X\" ); \n" +
                "   insert( e1 );\n" +
                "   Entity e2 = new Entity( \"Y\"); \n" +
                "   insert( e2 );\n" +
                " " +
                "   D d1 = don( e1, D.class, true ); \n" +
                "   F f2 = don( e2, F.class, true ); \n" +
                " " +
                "   modify ( d1 ) { \n" +
                "      getObjProp().add( f2.getCore() );" +
                "   } \n" +
                "   modify ( f2.getCore() ) {} \n " +
                "end \n" +
                "" +
                "" +
                "rule Shed \n" +
                "when \n" +
                "   $s : String( this == \"go\") \n" +
                "   $x : E( $objs : objProp ) \n" +
                "   $y : F( $z : core memberOf $objs ) \n" +
                "then \n" +
                "   retract( $s ); \n" +
                "   System.out.println( \"SUCCESS : E has been recognized \" );\n" +
                "   shed( $y, F.class );" +
                "end \n" +
                "" +
                "" +
                "rule Shed_2 \n" +
                "when \n" +
                "   $s : String( this == \"go2\") \n" +
                "   $x : E( $objs : objProp ) \n" +
                "   $y : F( $z : core memberOf $objs ) \n" +
                "then \n" +
                "   retract( $s ); \n" +
                "   System.out.println( \"SUCCESS : E has been recognized \" );\n" +
                "   modify ( $x ) {\n" +
                "       getObjProp().remove( $z ); \n" +
                "   }\n" +
                "   modify ( $y ) {} " +
                "end \n";


        Resource res = ResourceFactory.newByteArrayResource( owl.getBytes() );

        OWLOntology onto = factory.parseOntology( res );

        OntoModel ontoModel = factory.buildModel( "test",
                res,
                OntoModel.Mode.NONE,
                DLFactory.defaultAxiomGenerators );

        String drl = new RecognitionRuleBuilder().createDRL( onto, ontoModel );

        KnowledgeBuilder kBuilder = KnowledgeBuilderFactory.newKnowledgeBuilder();
        kBuilder.add( new ByteArrayResource( drl.getBytes() ), ResourceType.DRL );

        kBuilder.add( new ByteArrayResource( drl2.getBytes() ), ResourceType.DRL );
        if ( kBuilder.hasErrors() ) {
            fail( kBuilder.getErrors().toString() );
        }
        KnowledgeBase kbase = KnowledgeBaseFactory.newKnowledgeBase();
        kbase.addKnowledgePackages( kBuilder.getKnowledgePackages() );


        StatefulKnowledgeSession kSession = kbase.newStatefulKnowledgeSession();
        kSession.fireAllRules();

        for ( Object o : kSession.getObjects( new ClassObjectFilter( Entity.class ) ) ) {
            Entity e = (Entity) o;
            if ( e.getId().equals( "X" ) ) {
                assertTrue( e.hasTrait( "t.x.D" ) );
                assertTrue( e.hasTrait( "t.x.E" ) );
                assertFalse( e.hasTrait( "t.x.F" ) );
                assertEquals( 1, ( (List) e._getDynamicProperties().get( "objProp" ) ).size() );
            } else if ( e.getId().equals(  "Y" ) ) {
                assertTrue( e.hasTrait( "t.x.F" ) );
                assertFalse( e.hasTrait( "t.x.D" ) );
                assertFalse( e.hasTrait( "t.x.E" ) );
            } else {
                fail( "Unrecognized entity in WM" );
            }
        }


        kSession.insert( "go" );
        kSession.fireAllRules();

        for ( Object o : kSession.getObjects( new ClassObjectFilter( Entity.class ) ) ) {
            Entity e = (Entity) o;
            if ( e.getId().equals( "X" ) ) {
                assertTrue( e.hasTrait( "t.x.D" ) );
                assertFalse( e.hasTrait( "t.x.E" ) );
                assertFalse( e.hasTrait( "t.x.F" ) );
                assertEquals( 1, ( (List) e._getDynamicProperties().get( "objProp" ) ).size() );
            } else if ( e.getId().equals(  "Y" ) ) {
                assertFalse( e.hasTrait( "t.x.F" ) );
                assertFalse( e.hasTrait( "t.x.D" ) );
                assertFalse( e.hasTrait( "t.x.E" ) );
            } else {
                fail( "Unrecognized entity in WM" );
            }

        }


        kSession = kbase.newStatefulKnowledgeSession();
        kSession.fireAllRules();

        kSession.insert( "go2" );
        kSession.fireAllRules();

        for ( Object o : kSession.getObjects( new ClassObjectFilter( Entity.class ) ) ) {
            Entity e = (Entity) o;
            if ( e.getId().equals( "X" ) ) {
                assertTrue( e.hasTrait( "t.x.D" ) );
                assertFalse( e.hasTrait( "t.x.E" ) );
                assertFalse( e.hasTrait( "t.x.F" ) );
                assertEquals( 0, ( (List) e._getDynamicProperties().get( "objProp" ) ).size() );
            } else if ( e.getId().equals(  "Y" ) ) {
                assertTrue( e.hasTrait( "t.x.F" ) );
                assertFalse( e.hasTrait( "t.x.D" ) );
                assertFalse( e.hasTrait( "t.x.E" ) );
            } else {
                fail( "Unrecognized entity in WM" );
            }

        }



    }


    @Test
    public void testDNFConversion() {
        Resource res = ResourceFactory.newClassPathResource( "ontologies/testDNF.owl" );

        OWLOntology onto = factory.parseOntology( res );
        OWLDataFactory fac = onto.getOWLOntologyManager().getOWLDataFactory();

        Map<OWLClassExpression, OWLClassExpression> dnf = new DLogicTransformer( onto ).getDefinitions();

        String ns = "http://t/x#";
        OWLClassExpression a = fac.getOWLClass( IRI.create( ns + "A" ) );
        OWLClassExpression b = fac.getOWLClass( IRI.create( ns + "B" ) );
        OWLClassExpression c = fac.getOWLClass( IRI.create( ns + "C" ) );
        OWLClassExpression d = fac.getOWLClass( IRI.create( ns + "D" ) );
        OWLClassExpression e = fac.getOWLClass( IRI.create( ns + "E" ) );
        OWLClassExpression f = fac.getOWLClass( IRI.create( ns + "F" ) );

        assertNotNull( a );
        assertNotNull( b );
        assertNotNull( c );
        assertNotNull( d );
        assertNotNull( e );
        assertNotNull( f );
        assertTrue( dnf.containsKey( a ) );
        assertTrue( dnf.containsKey( b ) );
        assertTrue( dnf.containsKey( c ) );
        assertTrue( dnf.containsKey( d ) );
        assertTrue( dnf.containsKey( e ) );
        assertTrue( dnf.containsKey( f ) );

        checkPlainNormal( dnf.get( a ), 6, 3 );
        checkPlainNormal( dnf.get( b ), 1, 4 );
        checkPlainNormal( dnf.get( c ), 1, 4 );
        checkPlainNormal( dnf.get( d ), 4, 1 );
        checkNormal(dnf.get(e), 2, 3);
        checkNormal(dnf.get(f), 2, 2);

    }



    private void checkPlainNormal( OWLClassExpression x, int ands, int args ) {
        checkNormal( x, ands, args, true );
    }

    private void checkNormal( OWLClassExpression x, int ands, int args ) {
        checkNormal( x, ands, args, false );
    }

    private void checkNormal( OWLClassExpression x, int ands, int args, boolean allNamed ) {
        assertTrue( x instanceof OWLObjectUnionOf );
        OWLObjectUnionOf xnorm = (OWLObjectUnionOf) x;
        assertEquals( ands, xnorm.getOperands().size() );
        for ( OWLClassExpression expr : xnorm.getOperands() ) {
            assertTrue( expr instanceof OWLObjectIntersectionOf );
            OWLObjectIntersectionOf xinner = (OWLObjectIntersectionOf) expr;
            assertEquals( args, xinner.getOperands().size() );
            if ( allNamed ) {
                for( OWLClassExpression arg : xinner.getOperands() ) {
                    assertFalse( arg.isAnonymous() );
                }
            }
        }
    }




}