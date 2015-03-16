/**
 * ESpaceMeetingAsSoapResponseQueryMRSListResponse.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Nov 19, 2006 (02:31:34 GMT+00:00) WSDL2Java emitter.
 */

package com.bizconf.vcaasz.soap.conf;

public class ESpaceMeetingAsSoapResponseQueryMRSListResponse  implements java.io.Serializable {
    private com.bizconf.vcaasz.soap.conf.ESpaceMeetingAsAreaMRS[] mrsList;

    public ESpaceMeetingAsSoapResponseQueryMRSListResponse() {
    }

    public ESpaceMeetingAsSoapResponseQueryMRSListResponse(
           com.bizconf.vcaasz.soap.conf.ESpaceMeetingAsAreaMRS[] mrsList) {
           this.mrsList = mrsList;
    }


    /**
     * Gets the mrsList value for this ESpaceMeetingAsSoapResponseQueryMRSListResponse.
     * 
     * @return mrsList
     */
    public com.bizconf.vcaasz.soap.conf.ESpaceMeetingAsAreaMRS[] getMrsList() {
        return mrsList;
    }


    /**
     * Sets the mrsList value for this ESpaceMeetingAsSoapResponseQueryMRSListResponse.
     * 
     * @param mrsList
     */
    public void setMrsList(com.bizconf.vcaasz.soap.conf.ESpaceMeetingAsAreaMRS[] mrsList) {
        this.mrsList = mrsList;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof ESpaceMeetingAsSoapResponseQueryMRSListResponse)) return false;
        ESpaceMeetingAsSoapResponseQueryMRSListResponse other = (ESpaceMeetingAsSoapResponseQueryMRSListResponse) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = true && 
            ((this.mrsList==null && other.getMrsList()==null) || 
             (this.mrsList!=null &&
              java.util.Arrays.equals(this.mrsList, other.getMrsList())));
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
        if (getMrsList() != null) {
            for (int i=0;
                 i<java.lang.reflect.Array.getLength(getMrsList());
                 i++) {
                java.lang.Object obj = java.lang.reflect.Array.get(getMrsList(), i);
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
        new org.apache.axis.description.TypeDesc(ESpaceMeetingAsSoapResponseQueryMRSListResponse.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("eSpaceMeeting", "eSpaceMeeting.as.soap.response.QueryMRSListResponse"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("mrsList");
        elemField.setXmlName(new javax.xml.namespace.QName("", "mrsList"));
        elemField.setXmlType(new javax.xml.namespace.QName("eSpaceMeeting", "eSpaceMeeting.as.AreaMRS"));
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
