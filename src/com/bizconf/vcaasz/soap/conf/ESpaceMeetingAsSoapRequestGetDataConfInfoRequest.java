/**
 * ESpaceMeetingAsSoapRequestGetDataConfInfoRequest.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Nov 19, 2006 (02:31:34 GMT+00:00) WSDL2Java emitter.
 */

package com.bizconf.vcaasz.soap.conf;

public class ESpaceMeetingAsSoapRequestGetDataConfInfoRequest  implements java.io.Serializable {
    private com.bizconf.vcaasz.soap.conf.ESpaceMeetingAsSoapToken token;

    private com.bizconf.vcaasz.soap.conf.ESpaceMeetingAsSoapRequester requester;

    private java.lang.String confId;

    private java.lang.String userUri;

    private int termType;

    private java.lang.String termIp;

    private java.lang.String areaId;

    public ESpaceMeetingAsSoapRequestGetDataConfInfoRequest() {
    }

    public ESpaceMeetingAsSoapRequestGetDataConfInfoRequest(
           com.bizconf.vcaasz.soap.conf.ESpaceMeetingAsSoapToken token,
           com.bizconf.vcaasz.soap.conf.ESpaceMeetingAsSoapRequester requester,
           java.lang.String confId,
           java.lang.String userUri,
           int termType,
           java.lang.String termIp,
           java.lang.String areaId) {
           this.token = token;
           this.requester = requester;
           this.confId = confId;
           this.userUri = userUri;
           this.termType = termType;
           this.termIp = termIp;
           this.areaId = areaId;
    }


    /**
     * Gets the token value for this ESpaceMeetingAsSoapRequestGetDataConfInfoRequest.
     * 
     * @return token
     */
    public com.bizconf.vcaasz.soap.conf.ESpaceMeetingAsSoapToken getToken() {
        return token;
    }


    /**
     * Sets the token value for this ESpaceMeetingAsSoapRequestGetDataConfInfoRequest.
     * 
     * @param token
     */
    public void setToken(com.bizconf.vcaasz.soap.conf.ESpaceMeetingAsSoapToken token) {
        this.token = token;
    }


    /**
     * Gets the requester value for this ESpaceMeetingAsSoapRequestGetDataConfInfoRequest.
     * 
     * @return requester
     */
    public com.bizconf.vcaasz.soap.conf.ESpaceMeetingAsSoapRequester getRequester() {
        return requester;
    }


    /**
     * Sets the requester value for this ESpaceMeetingAsSoapRequestGetDataConfInfoRequest.
     * 
     * @param requester
     */
    public void setRequester(com.bizconf.vcaasz.soap.conf.ESpaceMeetingAsSoapRequester requester) {
        this.requester = requester;
    }


    /**
     * Gets the confId value for this ESpaceMeetingAsSoapRequestGetDataConfInfoRequest.
     * 
     * @return confId
     */
    public java.lang.String getConfId() {
        return confId;
    }


    /**
     * Sets the confId value for this ESpaceMeetingAsSoapRequestGetDataConfInfoRequest.
     * 
     * @param confId
     */
    public void setConfId(java.lang.String confId) {
        this.confId = confId;
    }


    /**
     * Gets the userUri value for this ESpaceMeetingAsSoapRequestGetDataConfInfoRequest.
     * 
     * @return userUri
     */
    public java.lang.String getUserUri() {
        return userUri;
    }


    /**
     * Sets the userUri value for this ESpaceMeetingAsSoapRequestGetDataConfInfoRequest.
     * 
     * @param userUri
     */
    public void setUserUri(java.lang.String userUri) {
        this.userUri = userUri;
    }


    /**
     * Gets the termType value for this ESpaceMeetingAsSoapRequestGetDataConfInfoRequest.
     * 
     * @return termType
     */
    public int getTermType() {
        return termType;
    }


    /**
     * Sets the termType value for this ESpaceMeetingAsSoapRequestGetDataConfInfoRequest.
     * 
     * @param termType
     */
    public void setTermType(int termType) {
        this.termType = termType;
    }


    /**
     * Gets the termIp value for this ESpaceMeetingAsSoapRequestGetDataConfInfoRequest.
     * 
     * @return termIp
     */
    public java.lang.String getTermIp() {
        return termIp;
    }


    /**
     * Sets the termIp value for this ESpaceMeetingAsSoapRequestGetDataConfInfoRequest.
     * 
     * @param termIp
     */
    public void setTermIp(java.lang.String termIp) {
        this.termIp = termIp;
    }


    /**
     * Gets the areaId value for this ESpaceMeetingAsSoapRequestGetDataConfInfoRequest.
     * 
     * @return areaId
     */
    public java.lang.String getAreaId() {
        return areaId;
    }


    /**
     * Sets the areaId value for this ESpaceMeetingAsSoapRequestGetDataConfInfoRequest.
     * 
     * @param areaId
     */
    public void setAreaId(java.lang.String areaId) {
        this.areaId = areaId;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof ESpaceMeetingAsSoapRequestGetDataConfInfoRequest)) return false;
        ESpaceMeetingAsSoapRequestGetDataConfInfoRequest other = (ESpaceMeetingAsSoapRequestGetDataConfInfoRequest) obj;
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
            ((this.confId==null && other.getConfId()==null) || 
             (this.confId!=null &&
              this.confId.equals(other.getConfId()))) &&
            ((this.userUri==null && other.getUserUri()==null) || 
             (this.userUri!=null &&
              this.userUri.equals(other.getUserUri()))) &&
            this.termType == other.getTermType() &&
            ((this.termIp==null && other.getTermIp()==null) || 
             (this.termIp!=null &&
              this.termIp.equals(other.getTermIp()))) &&
            ((this.areaId==null && other.getAreaId()==null) || 
             (this.areaId!=null &&
              this.areaId.equals(other.getAreaId())));
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
        if (getConfId() != null) {
            _hashCode += getConfId().hashCode();
        }
        if (getUserUri() != null) {
            _hashCode += getUserUri().hashCode();
        }
        _hashCode += getTermType();
        if (getTermIp() != null) {
            _hashCode += getTermIp().hashCode();
        }
        if (getAreaId() != null) {
            _hashCode += getAreaId().hashCode();
        }
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(ESpaceMeetingAsSoapRequestGetDataConfInfoRequest.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("eSpaceMeeting", "eSpaceMeeting.as.soap.request.GetDataConfInfoRequest"));
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
        elemField.setFieldName("confId");
        elemField.setXmlName(new javax.xml.namespace.QName("", "confId"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("userUri");
        elemField.setXmlName(new javax.xml.namespace.QName("", "userUri"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("termType");
        elemField.setXmlName(new javax.xml.namespace.QName("", "termType"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "int"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("termIp");
        elemField.setXmlName(new javax.xml.namespace.QName("", "termIp"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("areaId");
        elemField.setXmlName(new javax.xml.namespace.QName("", "areaId"));
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
