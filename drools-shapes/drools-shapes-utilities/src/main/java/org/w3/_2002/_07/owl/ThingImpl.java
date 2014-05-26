//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, v2.2.4 
// See <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2012.08.21 at 09:13:49 PM MST 
//


package org.w3._2002._07.owl;

import com.clarkparsia.empire.EmpireGenerated;
import com.clarkparsia.empire.annotation.Namespaces;
import com.clarkparsia.empire.annotation.RdfsClass;
import com.sun.xml.bind.CycleRecoverable;
import org.drools.core.factmodel.traits.LogicalTypeInconsistencyException;
import org.drools.core.factmodel.traits.TraitFieldTMS;
import org.drools.core.factmodel.traits.TraitFieldTMSImpl;
import org.drools.core.factmodel.traits.TraitType;
import org.drools.core.factmodel.traits.TraitTypeMap;
import org.drools.core.factmodel.traits.Traitable;
import org.drools.core.factmodel.traits.TraitableBean;
import org.drools.semantics.NamedEntity;
import org.drools.semantics.UIdAble;
import org.jvnet.jaxb2_commons.lang.CopyStrategy;
import org.jvnet.jaxb2_commons.lang.CopyTo;
import org.jvnet.jaxb2_commons.lang.Equals;
import org.jvnet.jaxb2_commons.lang.HashCode;
import org.jvnet.jaxb2_commons.lang.JAXBCopyStrategy;
import org.jvnet.jaxb2_commons.lang.JAXBMergeStrategy;
import org.jvnet.jaxb2_commons.lang.MergeFrom;
import org.jvnet.jaxb2_commons.lang.MergeStrategy;
import org.jvnet.jaxb2_commons.locator.ObjectLocator;
import org.jvnet.jaxb2_commons.locator.util.LocatorUtils;
import org.openrdf.model.Graph;
import thewebsemantic.Namespace;
import thewebsemantic.RdfType;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.Table;
import javax.persistence.Transient;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlID;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;
import javax.xml.bind.annotation.XmlType;
import java.io.Serializable;
import java.util.BitSet;
import java.util.Collection;
import java.util.Collections;
import java.util.Map;


/**
 * <p>Java class for Thing complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="Thing">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="dyEntryType" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="dyReference" type="{http://www.w3.org/2001/XMLSchema}boolean"/>
 *         &lt;element name="dyEntryId" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "Thing", propOrder = {
//    "dyEntryType",
    "dyReference",
    "dyEntryId"
})
@RdfsClass("tns:Thing")
@Namespaces({
    "tns",
    "http://www.w3.org/2002/07/owl"
})
@Namespace( "http://www.w3.org/2002/07/owl" )
@RdfType( "http://www.w3.org/2002/07/owl/Thing" )
@XmlRootElement(name = "ThingImpl")
@Entity(name = "ThingImpl")
@Table(name = "THINGIMPL")
@Inheritance(strategy = InheritanceType.JOINED)
@Traitable
public class ThingImpl
    extends UIdAble
        implements NamedEntity, Serializable, Cloneable, CycleRecoverable, CopyTo, Equals, HashCode, MergeFrom, Thing, EmpireGenerated, TraitableBean<ThingImpl, ThingImpl>, TraitType {

//    protected String dyEntryType;
    @XmlAttribute( required = false, name = "idref" )
    protected String dyReference;
    @XmlID
    @XmlAttribute( required = true, name = "id" )
    protected String dyEntryId;

    /**
     * Default no-arg constructor
     * 
     */
    public ThingImpl() {
        super();
    }


//    /**
//     * Gets the value of the dyEntryType property.
//     *
//     * @return
//     *     possible object is
//     *     {@link String }
//     *
//     */
//    @Basic
//    @Column(name = "DYENTRYTYPE", length = 255)
//    public String getDyEntryType() {
//        return dyEntryType;
//    }

    /**
     * Sets the value of the dyEntryType property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
//    public void setDyEntryType(String value) {
//        this.dyEntryType = value;
//    }

    /**
     * Gets the value of the dyReference property.
     * 
     */
    @Basic
    @Column(name = "DYREFERENCE")
    public String getDyReference() {
        return dyReference;
    }

    /**
     * Sets the value of the dyReference property.
     * 
     */
    public void setDyReference(String value) {
        this.dyReference = value;
    }

    /**
     * Gets the value of the dyEntryId property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    @Id
    @thewebsemantic.Id
    @Column(name = "DYENTRYID")
    public String getDyEntryId() {
        return dyEntryId;
    }

    /**
     * Sets the value of the dyEntryId property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setDyEntryId(String value) {
        this.dyEntryId = value;
    }

    public Object clone() {
        return copyTo(createNewInstance());
    }

    public Object copyTo(Object target) {
        final CopyStrategy strategy = JAXBCopyStrategy.INSTANCE;
        return copyTo(null, target, strategy);
    }

    public Object copyTo(ObjectLocator locator, Object target, CopyStrategy strategy) {
        final Object draftCopy = ((target == null)?createNewInstance():target);
        if (draftCopy instanceof ThingImpl) {
            final ThingImpl copy = ((ThingImpl) draftCopy);
//            if (this.dyEntryType!= null) {
//                String sourceDyEntryType;
//                sourceDyEntryType = this.getDyEntryType();
//                String copyDyEntryType = ((String) strategy.copy(LocatorUtils.property(locator, "dyEntryType", sourceDyEntryType), sourceDyEntryType));
//                copy.setDyEntryType(copyDyEntryType);
//            } else {
//                copy.dyEntryType = null;
//            }
            if ( this.dyReference != null ) {
                String sourceDyReference;
                sourceDyReference = this.getDyReference();
                String copyDyReference = ((String) strategy.copy(LocatorUtils.property(locator, "dyReference", sourceDyReference), sourceDyReference) );
                copy.setDyReference( copyDyReference );
            } else {
                copy.dyReference = null;
            }

            if (this.dyEntryId!= null) {
                String sourceDyEntryId;
                sourceDyEntryId = this.getDyEntryId();
                String copyDyEntryId = ((String) strategy.copy(LocatorUtils.property(locator, "dyEntryId", sourceDyEntryId), sourceDyEntryId));
                copy.setDyEntryId(copyDyEntryId);
            } else {
                copy.dyEntryId = null;
            }
        }
        return draftCopy;
    }

    public java.lang.Object createNewInstance() {
        return new ThingImpl();
    }

    public void mergeFrom(Object left, Object right) {
        final MergeStrategy strategy = JAXBMergeStrategy.INSTANCE;
        mergeFrom(null, null, left, right, strategy);
    }

    public void mergeFrom(ObjectLocator leftLocator, ObjectLocator rightLocator, Object left, Object right, MergeStrategy strategy) {
        if (right instanceof ThingImpl) {
            final ThingImpl target = this;
            final ThingImpl leftObject = ((ThingImpl) left);
            final ThingImpl rightObject = ((ThingImpl) right);
            {
                String lhsDyReference;
                lhsDyReference = leftObject.getDyReference();
                String rhsDyReference;
                rhsDyReference = rightObject.getDyReference();
                String mergedDyReference = ((String) strategy.merge(LocatorUtils.property(leftLocator, "dyReference", lhsDyReference), LocatorUtils.property(rightLocator, "dyReference", rhsDyReference), lhsDyReference, rhsDyReference));
                target.setDyReference(mergedDyReference);
            }
            {
                String lhsDyEntryId;
                lhsDyEntryId = leftObject.getDyEntryId();
                String rhsDyEntryId;
                rhsDyEntryId = rightObject.getDyEntryId();
                String mergedDyEntryId = ((String) strategy.merge(LocatorUtils.property(leftLocator, "dyEntryId", lhsDyEntryId), LocatorUtils.property(rightLocator, "dyEntryId", rhsDyEntryId), lhsDyEntryId, rhsDyEntryId));
                target.setDyEntryId(mergedDyEntryId);
            }
        }
    }


    public ThingImpl withDyReference(String value) {
        setDyReference(value);
        return this;
    }

    public ThingImpl withDyEntryId(String value) {
        setDyEntryId(value);
        return this;
    }
    





    public boolean equals(Object object) {
        return super.equals( object );
    }



    public boolean equals(org.jvnet.jaxb2_commons.locator.ObjectLocator thisLocator, org.jvnet.jaxb2_commons.locator.ObjectLocator thatLocator, Object object, org.jvnet.jaxb2_commons.lang.EqualsStrategy strategy) {
        if ( this == object ) {
            return true;
        }
        return object instanceof ThingImpl;
    }



    public int hashCode() {
        return super.hashCode();
    }

     public int hashCode(org.jvnet.jaxb2_commons.locator.ObjectLocator locator,  org.jvnet.jaxb2_commons.lang.HashCodeStrategy strategy) {
        return 31;
     }


    public Object onCycleDetected( com.sun.xml.bind.CycleRecoverable.Context context ) {

           ThingImpl x = new ThingImpl();
               x.setDyEntryId( this.getDyEntryId() );
//               x.setDyEntryType( "Thing" );
           return x;
    }


    @javax.persistence.Transient
    public String getSemanticTypeName() {
        return "Thing";
    }

    @javax.xml.bind.annotation.XmlTransient
    protected static java.util.List<String> ThingPropertyNames = java.util.Arrays.asList(  );

    @javax.persistence.Transient
    public java.util.List<String> getPropertyNames() {
        return ThingPropertyNames;
    }


    @javax.persistence.Transient
    public java.util.List get( String name ) {
        return get( name, true );
    }

    @javax.persistence.Transient
    public java.util.List get( String name, boolean inferred ) {
        int index = ThingPropertyNames.indexOf( name );
        
        switch ( index ) {
            
            default : return java.util.Collections.emptyList();
        }
    }


    @XmlTransient
    private Graph allTriples;

    @XmlTransient
    private Graph instanceTriples;


    @javax.persistence.Transient
    public Graph getAllTriples() {
        return allTriples;
    }

    public void setAllTriples(Graph allTriples) {
        this.allTriples = allTriples;
    }

    @javax.persistence.Transient
    public Graph getInstanceTriples() {
        return instanceTriples;
    }

    public void setInstanceTriples(Graph instanceTriples) {
        this.instanceTriples = instanceTriples;
    }

    @javax.persistence.Transient
    public Class getInterfaceClass() {
        return Thing.class;
    }


    // In compliance with the Traiting Thing interface,
    @Transient
    public Map<String, Object> getFields() {
        throw new UnsupportedOperationException( ThingImpl.class.getName() + " should support a field accessor, TODO" );
    }

    @Transient
    public org.drools.semantics.Thing getCore() {
        return this;
    }

    @Transient
    public boolean isTop() {
        return false;
    }

    @Transient
    public String get__IndividualName() {
        return dyEntryId;
    }

    public void set__IndividualName( String name ) {
        if ( getDyEntryId() == null ) {
            setDyEntryId( name );
        }
    }

    @Transient
    public String getFullName() {
        return Thing.class.getName();
    }


    @XmlTransient
    private TraitFieldTMS __$$field_Tms$$;
    @XmlTransient
    private Map<String,Object> __$$dynamic_properties_map$$;
    @XmlTransient
    private Map<String, org.drools.core.factmodel.traits.Thing<ThingImpl>> __$$dynamic_traits_map$$;


    @Transient
    public Map<String, Object> _getDynamicProperties() {
        return  __$$dynamic_properties_map$$;
    }

    public void _setDynamicProperties(Map map) {
        __$$dynamic_properties_map$$ = map;
    }

    public void _setTraitMap(Map map) {
        __$$dynamic_traits_map$$ = map;
    }

    @Transient
    public Map<String, org.drools.core.factmodel.traits.Thing<ThingImpl>> _getTraitMap() {
        return __$$dynamic_traits_map$$;
    }

    public void addTrait(String type, org.drools.core.factmodel.traits.Thing proxy) throws LogicalTypeInconsistencyException {
        ((TraitTypeMap) _getTraitMap()).putSafe(type, proxy);
    }

    @Transient
    public org.drools.core.factmodel.traits.Thing getTrait(String type) {
        return _getTraitMap().get( type );
    }


    public boolean hasTrait( String type ) {
        return  __$$dynamic_traits_map$$ != null && __$$dynamic_traits_map$$.containsKey( type );
    }

    public boolean hasTraits() {
        return __$$dynamic_traits_map$$ != null && ! __$$dynamic_traits_map$$.isEmpty();
    }

    public Collection<org.drools.core.factmodel.traits.Thing<ThingImpl>> removeTrait( String type ) {
        if (  __$$dynamic_traits_map$$ != null ) {
            return ((TraitTypeMap)_getTraitMap()).removeCascade(type);
        } else {
            return null;
        }
    }

    public Collection<org.drools.core.factmodel.traits.Thing<ThingImpl>> removeTrait( BitSet typeCode ) {
        if (  __$$dynamic_traits_map$$ != null ) {
            return ((TraitTypeMap)_getTraitMap()).removeCascade( typeCode );
        } else {
            return null;
        }
    }

    @Transient
    public Collection<String> getTraits() {
        if (  __$$dynamic_traits_map$$ != null ) {
            return _getTraitMap().keySet();
        } else {
            return Collections.emptySet();
        }
    }

    @Transient
    public Collection<org.drools.core.factmodel.traits.Thing> getMostSpecificTraits() {
        if ( __$$dynamic_traits_map$$ == null ) {
            return Collections.EMPTY_LIST;
        }
        return ((TraitTypeMap) __$$dynamic_traits_map$$).getMostSpecificTraits();
    }

    @Transient
    public BitSet getCurrentTypeCode() {
        if ( __$$dynamic_traits_map$$ == null ) {
            return null;
        }
        return ((TraitTypeMap) __$$dynamic_traits_map$$).getCurrentTypeCode();
    }

    public void _setBottomTypeCode( BitSet bottomTypeCode ) {
        ((TraitTypeMap) __$$dynamic_traits_map$$).setBottomCode( bottomTypeCode );
    }

    @Transient
    public TraitFieldTMS _getFieldTMS() {
        if ( __$$field_Tms$$ == null ) {
            __$$field_Tms$$ = new TraitFieldTMSImpl();
        }
        return __$$field_Tms$$;
    }

    @Transient
    public BitSet getTypeCode() {
        throw new UnsupportedOperationException( "Shapes generated classes can't predict the type code at compile time" );
    }

    @Transient
    public boolean isVirtual() {
        return false;
    }

    @Transient
    public String getTraitName() {
        return getFullName();
    }
}
