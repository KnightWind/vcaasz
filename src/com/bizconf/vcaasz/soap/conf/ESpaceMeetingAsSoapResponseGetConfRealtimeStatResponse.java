/**
 * ESpaceMeetingAsSoapResponseGetConfRealtimeStatResponse.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Nov 19, 2006 (02:31:34 GMT+00:00) WSDL2Java emitter.
 */

package com.bizconf.vcaasz.soap.conf;

public class ESpaceMeetingAsSoapResponseGetConfRealtimeStatResponse  implements java.io.Serializable {
    private com.bizconf.vcaasz.soap.conf.ESpaceMeetingAsStringKV[] statData;

    public ESpaceMeetingAsSoapResponseGetConfRealtimeStatResponse() {
    }

    public ESpaceMeetingAsSoapResponseGetConfRealtimeStatResponse(
           com.bizconf.vcaasz.soap.conf.ESpaceMeetingAsStringKV[] statData) {
           this.statData = statData;
    }


    /**
     * Gets the statData value for this ESpaceMeetingAsSoapResponseGetConfRealtimeStatResponse.
     * 
     * @return statData
     */
    public com.bizconf.vcaasz.soap.conf.ESpaceMeetingAsStringKV[] getStatData() {
        return statData;
    }


    /**
     * Sets the statData value for this ESpaceMeetingAsSoapResponseGetConfRealtimeStatResponse.
     * 
     * @param statData
     */
    public void setStatData(com.bizconf.vcaasz.soap.conf.ESpaceMeetingAsStringKV[] statData) {
        this.statData = statData;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof ESpaceMeetingAsSoapResponseGetConfRealtimeStatResponse)) return false;
        ESpaceMeetingAsSoapResponseGetConfRealtimeStatResponse other = (ESpaceMeetingAsSoapResponseGetConfRealtimeStatResponse) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = true && 
            ((this.statData==null && other.getStatData()==null) || 
             (this.statData!=null &&
              java.util.Arrays.equals(this.statData, other.getStatData())));
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
        if (getStatData() != null) {
            for (int i=0;
                 i<java.lang.reflect.Array.getLength(getStatData());
                 i++) {
                java.lang.Object obj = java.lang.reflect.Array.get(getStatData(), i);
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
        new org.apache.axis.description.TypeDesc(ESpaceMeetingAsSoapResponseGetConfRealtimeStatResponse.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("eSpaceMeeting", "eSpaceMeeting.as.soap.response.GetConfRealtimeStatResponse"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("statData");
        elemField.setXmlName(new javax.xml.namespace.QName("", "statData"));
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
