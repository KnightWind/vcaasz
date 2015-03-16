/**
 * ESpaceMeetingAsSoapResponseQueryEnterpriseListResponse.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Nov 19, 2006 (02:31:34 GMT+00:00) WSDL2Java emitter.
 */

package com.bizconf.vcaasz.soap.conf;

public class ESpaceMeetingAsSoapResponseQueryEnterpriseListResponse  implements java.io.Serializable {
    private com.bizconf.vcaasz.soap.conf.ESpaceMeetingAsEnterprise[] enterpriseList;

    public ESpaceMeetingAsSoapResponseQueryEnterpriseListResponse() {
    }

    public ESpaceMeetingAsSoapResponseQueryEnterpriseListResponse(
           com.bizconf.vcaasz.soap.conf.ESpaceMeetingAsEnterprise[] enterpriseList) {
           this.enterpriseList = enterpriseList;
    }


    /**
     * Gets the enterpriseList value for this ESpaceMeetingAsSoapResponseQueryEnterpriseListResponse.
     * 
     * @return enterpriseList
     */
    public com.bizconf.vcaasz.soap.conf.ESpaceMeetingAsEnterprise[] getEnterpriseList() {
        return enterpriseList;
    }


    /**
     * Sets the enterpriseList value for this ESpaceMeetingAsSoapResponseQueryEnterpriseListResponse.
     * 
     * @param enterpriseList
     */
    public void setEnterpriseList(com.bizconf.vcaasz.soap.conf.ESpaceMeetingAsEnterprise[] enterpriseList) {
        this.enterpriseList = enterpriseList;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof ESpaceMeetingAsSoapResponseQueryEnterpriseListResponse)) return false;
        ESpaceMeetingAsSoapResponseQueryEnterpriseListResponse other = (ESpaceMeetingAsSoapResponseQueryEnterpriseListResponse) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = true && 
            ((this.enterpriseList==null && other.getEnterpriseList()==null) || 
             (this.enterpriseList!=null &&
              java.util.Arrays.equals(this.enterpriseList, other.getEnterpriseList())));
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
        if (getEnterpriseList() != null) {
            for (int i=0;
                 i<java.lang.reflect.Array.getLength(getEnterpriseList());
                 i++) {
                java.lang.Object obj = java.lang.reflect.Array.get(getEnterpriseList(), i);
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
        new org.apache.axis.description.TypeDesc(ESpaceMeetingAsSoapResponseQueryEnterpriseListResponse.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("eSpaceMeeting", "eSpaceMeeting.as.soap.response.QueryEnterpriseListResponse"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("enterpriseList");
        elemField.setXmlName(new javax.xml.namespace.QName("", "enterpriseList"));
        elemField.setXmlType(new javax.xml.namespace.QName("eSpaceMeeting", "eSpaceMeeting.as.Enterprise"));
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
