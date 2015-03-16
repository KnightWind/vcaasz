/**
 * ESpaceMeetingAsStringKV.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Nov 19, 2006 (02:31:34 GMT+00:00) WSDL2Java emitter.
 */

package com.bizconf.vcaasz.soap.conf;

public class ESpaceMeetingAsStringKV  implements java.io.Serializable {
    private java.lang.String k;

    private java.lang.String v;

    public ESpaceMeetingAsStringKV() {
    }

    public ESpaceMeetingAsStringKV(
           java.lang.String k,
           java.lang.String v) {
           this.k = k;
           this.v = v;
    }


    /**
     * Gets the k value for this ESpaceMeetingAsStringKV.
     * 
     * @return k
     */
    public java.lang.String getK() {
        return k;
    }


    /**
     * Sets the k value for this ESpaceMeetingAsStringKV.
     * 
     * @param k
     */
    public void setK(java.lang.String k) {
        this.k = k;
    }


    /**
     * Gets the v value for this ESpaceMeetingAsStringKV.
     * 
     * @return v
     */
    public java.lang.String getV() {
        return v;
    }


    /**
     * Sets the v value for this ESpaceMeetingAsStringKV.
     * 
     * @param v
     */
    public void setV(java.lang.String v) {
        this.v = v;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof ESpaceMeetingAsStringKV)) return false;
        ESpaceMeetingAsStringKV other = (ESpaceMeetingAsStringKV) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = true && 
            ((this.k==null && other.getK()==null) || 
             (this.k!=null &&
              this.k.equals(other.getK()))) &&
            ((this.v==null && other.getV()==null) || 
             (this.v!=null &&
              this.v.equals(other.getV())));
        __equalsCalc = null;
        return _equals;
    }

    private boolean __hashCodeCalc = false;
    public synchronized int hashCode() {
        if (__hashCodeCalc) {
            return 0;
        }
        __hashCodeCalc = true;
        int _hashCode = 1;
        if (getK() != null) {
            _hashCode += getK().hashCode();
        }
        if (getV() != null) {
            _hashCode += getV().hashCode();
        }
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(ESpaceMeetingAsStringKV.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("eSpaceMeeting", "eSpaceMeeting.as.StringKV"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("k");
        elemField.setXmlName(new javax.xml.namespace.QName("", "k"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("v");
        elemField.setXmlName(new javax.xml.namespace.QName("", "v"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
    }

    /**
     * Return type metadata object
     */
    public static org.apache.axis.description.TypeDesc getTypeDesc() {
        return typeDesc;
    }

    /**
     * Get Custom Serializer
     */
    public static org.apache.axis.encoding.Serializer getSerializer(
           java.lang.String mechType, 
           java.lang.Class _javaType,  
           javax.xml.namespace.QName _xmlType) {
        return 
          new  org.apache.axis.encoding.ser.BeanSerializer(
            _javaType, _xmlType, typeDesc);
    }

    /**
     * Get Custom Deserializer
     */
    public static org.apache.axis.encoding.Deserializer getDeserializer(
           java.lang.String mechType, 
           java.lang.Class _javaType,  
           javax.xml.namespace.QName _xmlType) {
        return 
          new  org.apache.axis.encoding.ser.BeanDeserializer(
            _javaType, _xmlType, typeDesc);
    }

}
