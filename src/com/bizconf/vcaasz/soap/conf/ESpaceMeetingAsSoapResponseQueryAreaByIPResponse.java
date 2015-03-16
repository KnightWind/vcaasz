/**
 * ESpaceMeetingAsSoapResponseQueryAreaByIPResponse.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Nov 19, 2006 (02:31:34 GMT+00:00) WSDL2Java emitter.
 */

package com.bizconf.vcaasz.soap.conf;

public class ESpaceMeetingAsSoapResponseQueryAreaByIPResponse  implements java.io.Serializable {
    private com.bizconf.vcaasz.soap.conf.ESpaceMeetingAsArea area;

    public ESpaceMeetingAsSoapResponseQueryAreaByIPResponse() {
    }

    public ESpaceMeetingAsSoapResponseQueryAreaByIPResponse(
           com.bizconf.vcaasz.soap.conf.ESpaceMeetingAsArea area) {
           this.area = area;
    }


    /**
     * Gets the area value for this ESpaceMeetingAsSoapResponseQueryAreaByIPResponse.
     * 
     * @return area
     */
    public com.bizconf.vcaasz.soap.conf.ESpaceMeetingAsArea getArea() {
        return area;
    }


    /**
     * Sets the area value for this ESpaceMeetingAsSoapResponseQueryAreaByIPResponse.
     * 
     * @param area
     */
    public void setArea(com.bizconf.vcaasz.soap.conf.ESpaceMeetingAsArea area) {
        this.area = area;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof ESpaceMeetingAsSoapResponseQueryAreaByIPResponse)) return false;
        ESpaceMeetingAsSoapResponseQueryAreaByIPResponse other = (ESpaceMeetingAsSoapResponseQueryAreaByIPResponse) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = true && 
            ((this.area==null && other.getArea()==null) || 
             (this.area!=null &&
              this.area.equals(other.getArea())));
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
        if (getArea() != null) {
            _hashCode += getArea().hashCode();
        }
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(ESpaceMeetingAsSoapResponseQueryAreaByIPResponse.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("eSpaceMeeting", "eSpaceMeeting.as.soap.response.QueryAreaByIPResponse"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("area");
        elemField.setXmlName(new javax.xml.namespace.QName("", "area"));
        elemField.setXmlType(new javax.xml.namespace.QName("eSpaceMeeting", "eSpaceMeeting.as.Area"));
        elemField.setNillable(false);
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
