/**
 * ESpaceMeetingAsSoapResponseQueryEnterpriseResponse.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Nov 19, 2006 (02:31:34 GMT+00:00) WSDL2Java emitter.
 */

package com.bizconf.vcaasz.soap.conf;

public class ESpaceMeetingAsSoapResponseQueryEnterpriseResponse  implements java.io.Serializable {
    private com.bizconf.vcaasz.soap.conf.ESpaceMeetingAsEnterprise enterprise;

    public ESpaceMeetingAsSoapResponseQueryEnterpriseResponse() {
    }

    public ESpaceMeetingAsSoapResponseQueryEnterpriseResponse(
           com.bizconf.vcaasz.soap.conf.ESpaceMeetingAsEnterprise enterprise) {
           this.enterprise = enterprise;
    }


    /**
     * Gets the enterprise value for this ESpaceMeetingAsSoapResponseQueryEnterpriseResponse.
     * 
     * @return enterprise
     */
    public com.bizconf.vcaasz.soap.conf.ESpaceMeetingAsEnterprise getEnterprise() {
        return enterprise;
    }


    /**
     * Sets the enterprise value for this ESpaceMeetingAsSoapResponseQueryEnterpriseResponse.
     * 
     * @param enterprise
     */
    public void setEnterprise(com.bizconf.vcaasz.soap.conf.ESpaceMeetingAsEnterprise enterprise) {
        this.enterprise = enterprise;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof ESpaceMeetingAsSoapResponseQueryEnterpriseResponse)) return false;
        ESpaceMeetingAsSoapResponseQueryEnterpriseResponse other = (ESpaceMeetingAsSoapResponseQueryEnterpriseResponse) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = true && 
            ((this.enterprise==null && other.getEnterprise()==null) || 
             (this.enterprise!=null &&
              this.enterprise.equals(other.getEnterprise())));
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
        if (getEnterprise() != null) {
            _hashCode += getEnterprise().hashCode();
        }
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(ESpaceMeetingAsSoapResponseQueryEnterpriseResponse.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("eSpaceMeeting", "eSpaceMeeting.as.soap.response.QueryEnterpriseResponse"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("enterprise");
        elemField.setXmlName(new javax.xml.namespace.QName("", "enterprise"));
        elemField.setXmlType(new javax.xml.namespace.QName("eSpaceMeeting", "eSpaceMeeting.as.Enterprise"));
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
