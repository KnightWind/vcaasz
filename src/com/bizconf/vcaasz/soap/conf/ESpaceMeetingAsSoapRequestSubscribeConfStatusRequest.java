/**
 * ESpaceMeetingAsSoapRequestSubscribeConfStatusRequest.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Nov 19, 2006 (02:31:34 GMT+00:00) WSDL2Java emitter.
 */

package com.bizconf.vcaasz.soap.conf;

public class ESpaceMeetingAsSoapRequestSubscribeConfStatusRequest  implements java.io.Serializable {
    private com.bizconf.vcaasz.soap.conf.ESpaceMeetingAsSoapToken token;

    private com.bizconf.vcaasz.soap.conf.ESpaceMeetingAsSoapRequester requester;

    private java.lang.String subscriberName;

    private java.lang.String confId;

    private int subscribeType;

    private int notifyType;

    public ESpaceMeetingAsSoapRequestSubscribeConfStatusRequest() {
    }

    public ESpaceMeetingAsSoapRequestSubscribeConfStatusRequest(
           com.bizconf.vcaasz.soap.conf.ESpaceMeetingAsSoapToken token,
           com.bizconf.vcaasz.soap.conf.ESpaceMeetingAsSoapRequester requester,
           java.lang.String subscriberName,
           java.lang.String confId,
           int subscribeType,
           int notifyType) {
           this.token = token;
           this.requester = requester;
           this.subscriberName = subscriberName;
           this.confId = confId;
           this.subscribeType = subscribeType;
           this.notifyType = notifyType;
    }


    /**
     * Gets the token value for this ESpaceMeetingAsSoapRequestSubscribeConfStatusRequest.
     * 
     * @return token
     */
    public com.bizconf.vcaasz.soap.conf.ESpaceMeetingAsSoapToken getToken() {
        return token;
    }


    /**
     * Sets the token value for this ESpaceMeetingAsSoapRequestSubscribeConfStatusRequest.
     * 
     * @param token
     */
    public void setToken(com.bizconf.vcaasz.soap.conf.ESpaceMeetingAsSoapToken token) {
        this.token = token;
    }


    /**
     * Gets the requester value for this ESpaceMeetingAsSoapRequestSubscribeConfStatusRequest.
     * 
     * @return requester
     */
    public com.bizconf.vcaasz.soap.conf.ESpaceMeetingAsSoapRequester getRequester() {
        return requester;
    }


    /**
     * Sets the requester value for this ESpaceMeetingAsSoapRequestSubscribeConfStatusRequest.
     * 
     * @param requester
     */
    public void setRequester(com.bizconf.vcaasz.soap.conf.ESpaceMeetingAsSoapRequester requester) {
        this.requester = requester;
    }


    /**
     * Gets the subscriberName value for this ESpaceMeetingAsSoapRequestSubscribeConfStatusRequest.
     * 
     * @return subscriberName
     */
    public java.lang.String getSubscriberName() {
        return subscriberName;
    }


    /**
     * Sets the subscriberName value for this ESpaceMeetingAsSoapRequestSubscribeConfStatusRequest.
     * 
     * @param subscriberName
     */
    public void setSubscriberName(java.lang.String subscriberName) {
        this.subscriberName = subscriberName;
    }


    /**
     * Gets the confId value for this ESpaceMeetingAsSoapRequestSubscribeConfStatusRequest.
     * 
     * @return confId
     */
    public java.lang.String getConfId() {
        return confId;
    }


    /**
     * Sets the confId value for this ESpaceMeetingAsSoapRequestSubscribeConfStatusRequest.
     * 
     * @param confId
     */
    public void setConfId(java.lang.String confId) {
        this.confId = confId;
    }


    /**
     * Gets the subscribeType value for this ESpaceMeetingAsSoapRequestSubscribeConfStatusRequest.
     * 
     * @return subscribeType
     */
    public int getSubscribeType() {
        return subscribeType;
    }


    /**
     * Sets the subscribeType value for this ESpaceMeetingAsSoapRequestSubscribeConfStatusRequest.
     * 
     * @param subscribeType
     */
    public void setSubscribeType(int subscribeType) {
        this.subscribeType = subscribeType;
    }


    /**
     * Gets the notifyType value for this ESpaceMeetingAsSoapRequestSubscribeConfStatusRequest.
     * 
     * @return notifyType
     */
    public int getNotifyType() {
        return notifyType;
    }


    /**
     * Sets the notifyType value for this ESpaceMeetingAsSoapRequestSubscribeConfStatusRequest.
     * 
     * @param notifyType
     */
    public void setNotifyType(int notifyType) {
        this.notifyType = notifyType;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof ESpaceMeetingAsSoapRequestSubscribeConfStatusRequest)) return false;
        ESpaceMeetingAsSoapRequestSubscribeConfStatusRequest other = (ESpaceMeetingAsSoapRequestSubscribeConfStatusRequest) obj;
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
            ((this.requester==null && other.getRequester()==null) || 
             (this.requester!=null &&
              this.requester.equals(other.getRequester()))) &&
            ((this.subscriberName==null && other.getSubscriberName()==null) || 
             (this.subscriberName!=null &&
              this.subscriberName.equals(other.getSubscriberName()))) &&
            ((this.confId==null && other.getConfId()==null) || 
             (this.confId!=null &&
              this.confId.equals(other.getConfId()))) &&
            this.subscribeType == other.getSubscribeType() &&
            this.notifyType == other.getNotifyType();
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
        if (getRequester() != null) {
            _hashCode += getRequester().hashCode();
        }
        if (getSubscriberName() != null) {
            _hashCode += getSubscriberName().hashCode();
        }
        if (getConfId() != null) {
            _hashCode += getConfId().hashCode();
        }
        _hashCode += getSubscribeType();
        _hashCode += getNotifyType();
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(ESpaceMeetingAsSoapRequestSubscribeConfStatusRequest.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("eSpaceMeeting", "eSpaceMeeting.as.soap.request.SubscribeConfStatusRequest"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("token");
        elemField.setXmlName(new javax.xml.namespace.QName("", "token"));
        elemField.setXmlType(new javax.xml.namespace.QName("eSpaceMeeting", "eSpaceMeeting.as.soap.Token"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("requester");
        elemField.setXmlName(new javax.xml.namespace.QName("", "requester"));
        elemField.setXmlType(new javax.xml.namespace.QName("eSpaceMeeting", "eSpaceMeeting.as.soap.Requester"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("subscriberName");
        elemField.setXmlName(new javax.xml.namespace.QName("", "subscriberName"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("confId");
        elemField.setXmlName(new javax.xml.namespace.QName("", "confId"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("subscribeType");
        elemField.setXmlName(new javax.xml.namespace.QName("", "subscribeType"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "int"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("notifyType");
        elemField.setXmlName(new javax.xml.namespace.QName("", "notifyType"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "int"));
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
