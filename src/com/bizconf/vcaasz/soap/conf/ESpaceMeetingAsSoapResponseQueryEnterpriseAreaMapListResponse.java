/**
 * ESpaceMeetingAsSoapResponseQueryEnterpriseAreaMapListResponse.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Nov 19, 2006 (02:31:34 GMT+00:00) WSDL2Java emitter.
 */

package com.bizconf.vcaasz.soap.conf;

public class ESpaceMeetingAsSoapResponseQueryEnterpriseAreaMapListResponse  implements java.io.Serializable {
    private com.bizconf.vcaasz.soap.conf.ESpaceMeetingAsEnterpriseAreaMap[] enterpriseAreaMapList;

    public ESpaceMeetingAsSoapResponseQueryEnterpriseAreaMapListResponse() {
    }

    public ESpaceMeetingAsSoapResponseQueryEnterpriseAreaMapListResponse(
           com.bizconf.vcaasz.soap.conf.ESpaceMeetingAsEnterpriseAreaMap[] enterpriseAreaMapList) {
           this.enterpriseAreaMapList = enterpriseAreaMapList;
    }


    /**
     * Gets the enterpriseAreaMapList value for this ESpaceMeetingAsSoapResponseQueryEnterpriseAreaMapListResponse.
     * 
     * @return enterpriseAreaMapList
     */
    public com.bizconf.vcaasz.soap.conf.ESpaceMeetingAsEnterpriseAreaMap[] getEnterpriseAreaMapList() {
        return enterpriseAreaMapList;
    }


    /**
     * Sets the enterpriseAreaMapList value for this ESpaceMeetingAsSoapResponseQueryEnterpriseAreaMapListResponse.
     * 
     * @param enterpriseAreaMapList
     */
    public void setEnterpriseAreaMapList(com.bizconf.vcaasz.soap.conf.ESpaceMeetingAsEnterpriseAreaMap[] enterpriseAreaMapList) {
        this.enterpriseAreaMapList = enterpriseAreaMapList;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof ESpaceMeetingAsSoapResponseQueryEnterpriseAreaMapListResponse)) return false;
        ESpaceMeetingAsSoapResponseQueryEnterpriseAreaMapListResponse other = (ESpaceMeetingAsSoapResponseQueryEnterpriseAreaMapListResponse) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = true && 
            ((this.enterpriseAreaMapList==null && other.getEnterpriseAreaMapList()==null) || 
             (this.enterpriseAreaMapList!=null &&
              java.util.Arrays.equals(this.enterpriseAreaMapList, other.getEnterpriseAreaMapList())));
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
        if (getEnterpriseAreaMapList() != null) {
            for (int i=0;
                 i<java.lang.reflect.Array.getLength(getEnterpriseAreaMapList());
                 i++) {
                java.lang.Object obj = java.lang.reflect.Array.get(getEnterpriseAreaMapList(), i);
                if (obj != null &&
                    !obj.getClass().isArray()) {
                    _hashCode += obj.hashCode();
                }
            }
        }
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(ESpaceMeetingAsSoapResponseQueryEnterpriseAreaMapListResponse.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("eSpaceMeeting", "eSpaceMeeting.as.soap.response.QueryEnterpriseAreaMapListResponse"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("enterpriseAreaMapList");
        elemField.setXmlName(new javax.xml.namespace.QName("", "enterpriseAreaMapList"));
        elemField.setXmlType(new javax.xml.namespace.QName("eSpaceMeeting", "eSpaceMeeting.as.EnterpriseAreaMap"));
        elemField.setNillable(false);
        elemField.setItemQName(new javax.xml.namespace.QName("", "item"));
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
