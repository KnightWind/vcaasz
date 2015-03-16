/**
 * ESpaceMeetingAsSoapResponseQueryConfInfoByPinResponse.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Nov 19, 2006 (02:31:34 GMT+00:00) WSDL2Java emitter.
 */

package com.bizconf.vcaasz.soap.conf;

public class ESpaceMeetingAsSoapResponseQueryConfInfoByPinResponse  implements java.io.Serializable {
    private com.bizconf.vcaasz.soap.conf.ESpaceMeetingAsSoapConfInfo confInfo;

    private com.bizconf.vcaasz.soap.conf.ESpaceMeetingAsSoapUserInfo userInfo;

    public ESpaceMeetingAsSoapResponseQueryConfInfoByPinResponse() {
    }

    public ESpaceMeetingAsSoapResponseQueryConfInfoByPinResponse(
           com.bizconf.vcaasz.soap.conf.ESpaceMeetingAsSoapConfInfo confInfo,
           com.bizconf.vcaasz.soap.conf.ESpaceMeetingAsSoapUserInfo userInfo) {
           this.confInfo = confInfo;
           this.userInfo = userInfo;
    }


    /**
     * Gets the confInfo value for this ESpaceMeetingAsSoapResponseQueryConfInfoByPinResponse.
     * 
     * @return confInfo
     */
    public com.bizconf.vcaasz.soap.conf.ESpaceMeetingAsSoapConfInfo getConfInfo() {
        return confInfo;
    }


    /**
     * Sets the confInfo value for this ESpaceMeetingAsSoapResponseQueryConfInfoByPinResponse.
     * 
     * @param confInfo
     */
    public void setConfInfo(com.bizconf.vcaasz.soap.conf.ESpaceMeetingAsSoapConfInfo confInfo) {
        this.confInfo = confInfo;
    }


    /**
     * Gets the userInfo value for this ESpaceMeetingAsSoapResponseQueryConfInfoByPinResponse.
     * 
     * @return userInfo
     */
    public com.bizconf.vcaasz.soap.conf.ESpaceMeetingAsSoapUserInfo getUserInfo() {
        return userInfo;
    }


    /**
     * Sets the userInfo value for this ESpaceMeetingAsSoapResponseQueryConfInfoByPinResponse.
     * 
     * @param userInfo
     */
    public void setUserInfo(com.bizconf.vcaasz.soap.conf.ESpaceMeetingAsSoapUserInfo userInfo) {
        this.userInfo = userInfo;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof ESpaceMeetingAsSoapResponseQueryConfInfoByPinResponse)) return false;
        ESpaceMeetingAsSoapResponseQueryConfInfoByPinResponse other = (ESpaceMeetingAsSoapResponseQueryConfInfoByPinResponse) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = true && 
            ((this.confInfo==null && other.getConfInfo()==null) || 
             (this.confInfo!=null &&
              this.confInfo.equals(other.getConfInfo()))) &&
            ((this.userInfo==null && other.getUserInfo()==null) || 
             (this.userInfo!=null &&
              this.userInfo.equals(other.getUserInfo())));
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
        if (getConfInfo() != null) {
            _hashCode += getConfInfo().hashCode();
        }
        if (getUserInfo() != null) {
            _hashCode += getUserInfo().hashCode();
        }
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(ESpaceMeetingAsSoapResponseQueryConfInfoByPinResponse.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("eSpaceMeeting", "eSpaceMeeting.as.soap.response.QueryConfInfoByPinResponse"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("confInfo");
        elemField.setXmlName(new javax.xml.namespace.QName("", "confInfo"));
        elemField.setXmlType(new javax.xml.namespace.QName("eSpaceMeeting", "eSpaceMeeting.as.soap.ConfInfo"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("userInfo");
        elemField.setXmlName(new javax.xml.namespace.QName("", "userInfo"));
        elemField.setXmlType(new javax.xml.namespace.QName("eSpaceMeeting", "eSpaceMeeting.as.soap.UserInfo"));
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
