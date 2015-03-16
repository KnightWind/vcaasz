/**
 * ESpaceMeetingAsSoapResponseNotifyTerminalTypeResponse.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Nov 19, 2006 (02:31:34 GMT+00:00) WSDL2Java emitter.
 */

package com.bizconf.vcaasz.soap.conf;

public class ESpaceMeetingAsSoapResponseNotifyTerminalTypeResponse  implements java.io.Serializable {
    private com.bizconf.vcaasz.soap.conf.ESpaceMeetingAsSoapDataConfInfo dataConfInfo;

    public ESpaceMeetingAsSoapResponseNotifyTerminalTypeResponse() {
    }

    public ESpaceMeetingAsSoapResponseNotifyTerminalTypeResponse(
           com.bizconf.vcaasz.soap.conf.ESpaceMeetingAsSoapDataConfInfo dataConfInfo) {
           this.dataConfInfo = dataConfInfo;
    }


    /**
     * Gets the dataConfInfo value for this ESpaceMeetingAsSoapResponseNotifyTerminalTypeResponse.
     * 
     * @return dataConfInfo
     */
    public com.bizconf.vcaasz.soap.conf.ESpaceMeetingAsSoapDataConfInfo getDataConfInfo() {
        return dataConfInfo;
    }


    /**
     * Sets the dataConfInfo value for this ESpaceMeetingAsSoapResponseNotifyTerminalTypeResponse.
     * 
     * @param dataConfInfo
     */
    public void setDataConfInfo(com.bizconf.vcaasz.soap.conf.ESpaceMeetingAsSoapDataConfInfo dataConfInfo) {
        this.dataConfInfo = dataConfInfo;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof ESpaceMeetingAsSoapResponseNotifyTerminalTypeResponse)) return false;
        ESpaceMeetingAsSoapResponseNotifyTerminalTypeResponse other = (ESpaceMeetingAsSoapResponseNotifyTerminalTypeResponse) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = true && 
            ((this.dataConfInfo==null && other.getDataConfInfo()==null) || 
             (this.dataConfInfo!=null &&
              this.dataConfInfo.equals(other.getDataConfInfo())));
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
        if (getDataConfInfo() != null) {
            _hashCode += getDataConfInfo().hashCode();
        }
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(ESpaceMeetingAsSoapResponseNotifyTerminalTypeResponse.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("eSpaceMeeting", "eSpaceMeeting.as.soap.response.NotifyTerminalTypeResponse"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("dataConfInfo");
        elemField.setXmlName(new javax.xml.namespace.QName("", "dataConfInfo"));
        elemField.setXmlType(new javax.xml.namespace.QName("eSpaceMeeting", "eSpaceMeeting.as.soap.DataConfInfo"));
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
