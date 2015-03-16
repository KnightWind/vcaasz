/**
 * ESpaceMeetingAsSoapResponseGetSystemParamResponse.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Nov 19, 2006 (02:31:34 GMT+00:00) WSDL2Java emitter.
 */

package com.bizconf.vcaasz.soap.conf;

public class ESpaceMeetingAsSoapResponseGetSystemParamResponse  implements java.io.Serializable {
    private com.bizconf.vcaasz.soap.conf.ESpaceMeetingAsStringKV[] params;

    public ESpaceMeetingAsSoapResponseGetSystemParamResponse() {
    }

    public ESpaceMeetingAsSoapResponseGetSystemParamResponse(
           com.bizconf.vcaasz.soap.conf.ESpaceMeetingAsStringKV[] params) {
           this.params = params;
    }


    /**
     * Gets the params value for this ESpaceMeetingAsSoapResponseGetSystemParamResponse.
     * 
     * @return params
     */
    public com.bizconf.vcaasz.soap.conf.ESpaceMeetingAsStringKV[] getParams() {
        return params;
    }


    /**
     * Sets the params value for this ESpaceMeetingAsSoapResponseGetSystemParamResponse.
     * 
     * @param params
     */
    public void setParams(com.bizconf.vcaasz.soap.conf.ESpaceMeetingAsStringKV[] params) {
        this.params = params;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof ESpaceMeetingAsSoapResponseGetSystemParamResponse)) return false;
        ESpaceMeetingAsSoapResponseGetSystemParamResponse other = (ESpaceMeetingAsSoapResponseGetSystemParamResponse) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = true && 
            ((this.params==null && other.getParams()==null) || 
             (this.params!=null &&
              java.util.Arrays.equals(this.params, other.getParams())));
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
        if (getParams() != null) {
            for (int i=0;
                 i<java.lang.reflect.Array.getLength(getParams());
                 i++) {
                java.lang.Object obj = java.lang.reflect.Array.get(getParams(), i);
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
        new org.apache.axis.description.TypeDesc(ESpaceMeetingAsSoapResponseGetSystemParamResponse.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("eSpaceMeeting", "eSpaceMeeting.as.soap.response.GetSystemParamResponse"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("params");
        elemField.setXmlName(new javax.xml.namespace.QName("", "params"));
        elemField.setXmlType(new javax.xml.namespace.QName("eSpaceMeeting", "eSpaceMeeting.as.StringKV"));
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
