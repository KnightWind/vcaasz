/**
 * ESpaceMeetingAsSoapResponseQueryCycleConfInfoResponse.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Nov 19, 2006 (02:31:34 GMT+00:00) WSDL2Java emitter.
 */

package com.bizconf.vcaasz.soap.conf;

public class ESpaceMeetingAsSoapResponseQueryCycleConfInfoResponse  implements java.io.Serializable {
    private com.bizconf.vcaasz.soap.conf.ESpaceMeetingAsSoapCycleConfInfo cycleConfInfo;

    public ESpaceMeetingAsSoapResponseQueryCycleConfInfoResponse() {
    }

    public ESpaceMeetingAsSoapResponseQueryCycleConfInfoResponse(
           com.bizconf.vcaasz.soap.conf.ESpaceMeetingAsSoapCycleConfInfo cycleConfInfo) {
           this.cycleConfInfo = cycleConfInfo;
    }


    /**
     * Gets the cycleConfInfo value for this ESpaceMeetingAsSoapResponseQueryCycleConfInfoResponse.
     * 
     * @return cycleConfInfo
     */
    public com.bizconf.vcaasz.soap.conf.ESpaceMeetingAsSoapCycleConfInfo getCycleConfInfo() {
        return cycleConfInfo;
    }


    /**
     * Sets the cycleConfInfo value for this ESpaceMeetingAsSoapResponseQueryCycleConfInfoResponse.
     * 
     * @param cycleConfInfo
     */
    public void setCycleConfInfo(com.bizconf.vcaasz.soap.conf.ESpaceMeetingAsSoapCycleConfInfo cycleConfInfo) {
        this.cycleConfInfo = cycleConfInfo;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof ESpaceMeetingAsSoapResponseQueryCycleConfInfoResponse)) return false;
        ESpaceMeetingAsSoapResponseQueryCycleConfInfoResponse other = (ESpaceMeetingAsSoapResponseQueryCycleConfInfoResponse) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = true && 
            ((this.cycleConfInfo==null && other.getCycleConfInfo()==null) || 
             (this.cycleConfInfo!=null &&
              this.cycleConfInfo.equals(other.getCycleConfInfo())));
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
        if (getCycleConfInfo() != null) {
            _hashCode += getCycleConfInfo().hashCode();
        }
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(ESpaceMeetingAsSoapResponseQueryCycleConfInfoResponse.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("eSpaceMeeting", "eSpaceMeeting.as.soap.response.QueryCycleConfInfoResponse"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("cycleConfInfo");
        elemField.setXmlName(new javax.xml.namespace.QName("", "cycleConfInfo"));
        elemField.setXmlType(new javax.xml.namespace.QName("eSpaceMeeting", "eSpaceMeeting.as.soap.CycleConfInfo"));
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
