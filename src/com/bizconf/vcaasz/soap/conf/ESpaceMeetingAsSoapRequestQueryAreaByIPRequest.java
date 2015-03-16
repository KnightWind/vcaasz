/**
 * ESpaceMeetingAsSoapRequestQueryAreaByIPRequest.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Nov 19, 2006 (02:31:34 GMT+00:00) WSDL2Java emitter.
 */

package com.bizconf.vcaasz.soap.conf;

public class ESpaceMeetingAsSoapRequestQueryAreaByIPRequest  implements java.io.Serializable {
    private com.bizconf.vcaasz.soap.conf.ESpaceMeetingAsSoapToken token;

    private java.lang.String enterpriseId;

    private java.lang.String ip;

    public ESpaceMeetingAsSoapRequestQueryAreaByIPRequest() {
    }

    public ESpaceMeetingAsSoapRequestQueryAreaByIPRequest(
           com.bizconf.vcaasz.soap.conf.ESpaceMeetingAsSoapToken token,
           java.lang.String enterpriseId,
           java.lang.String ip) {
           this.token = token;
           this.enterpriseId = enterpriseId;
           this.ip = ip;
    }


    /**
     * Gets the token value for this ESpaceMeetingAsSoapRequestQueryAreaByIPRequest.
     * 
     * @return token
     */
    public com.bizconf.vcaasz.soap.conf.ESpaceMeetingAsSoapToken getToken() {
        return token;
    }


    /**
     * Sets the token value for this ESpaceMeetingAsSoapRequestQueryAreaByIPRequest.
     * 
     * @param token
     */
    public void setToken(com.bizconf.vcaasz.soap.conf.ESpaceMeetingAsSoapToken token) {
        this.token = token;
    }


    /**
     * Gets the enterpriseId value for this ESpaceMeetingAsSoapRequestQueryAreaByIPRequest.
     * 
     * @return enterpriseId
     */
    public java.lang.String getEnterpriseId() {
        return enterpriseId;
    }


    /**
     * Sets the enterpriseId value for this ESpaceMeetingAsSoapRequestQueryAreaByIPRequest.
     * 
     * @param enterpriseId
     */
    public void setEnterpriseId(java.lang.String enterpriseId) {
        this.enterpriseId = enterpriseId;
    }


    /**
     * Gets the ip value for this ESpaceMeetingAsSoapRequestQueryAreaByIPRequest.
     * 
     * @return ip
     */
    public java.lang.String getIp() {
        return ip;
    }


    /**
     * Sets the ip value for this ESpaceMeetingAsSoapRequestQueryAreaByIPRequest.
     * 
     * @param ip
     */
    public void setIp(java.lang.String ip) {
        this.ip = ip;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof ESpaceMeetingAsSoapRequestQueryAreaByIPRequest)) return false;
        ESpaceMeetingAsSoapRequestQueryAreaByIPRequest other = (ESpaceMeetingAsSoapRequestQueryAreaByIPRequest) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = true && 
            ((this.token==null && other.getToken()==null) || 
             (this.token!=null &&
              this.token.equals(other.getToken()))) &&
            ((this.enterpriseId==null && other.getEnterpriseId()==null) || 
             (this.enterpriseId!=null &&
              this.enterpriseId.equals(other.getEnterpriseId()))) &&
            ((this.ip==null && other.getIp()==null) || 
             (this.ip!=null &&
              this.ip.equals(other.getIp())));
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
        if (getToken() != null) {
            _hashCode += getToken().hashCode();
        }
        if (getEnterpriseId() != null) {
            _hashCode += getEnterpriseId().hashCode();
        }
        if (getIp() != null) {
            _hashCode += getIp().hashCode();
        }
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(ESpaceMeetingAsSoapRequestQueryAreaByIPRequest.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("eSpaceMeeting", "eSpaceMeeting.as.soap.request.QueryAreaByIPRequest"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("token");
        elemField.setXmlName(new javax.xml.namespace.QName("", "token"));
        elemField.setXmlType(new javax.xml.namespace.QName("eSpaceMeeting", "eSpaceMeeting.as.soap.Token"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("enterpriseId");
        elemField.setXmlName(new javax.xml.namespace.QName("", "enterpriseId"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("ip");
        elemField.setXmlName(new javax.xml.namespace.QName("", "ip"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(true);
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
