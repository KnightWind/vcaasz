/**
 * ESpaceMeetingAsSoapRequestUpdateConfMemberListRequest.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Nov 19, 2006 (02:31:34 GMT+00:00) WSDL2Java emitter.
 */

package com.bizconf.vcaasz.soap.conf;

public class ESpaceMeetingAsSoapRequestUpdateConfMemberListRequest  implements java.io.Serializable {
    private com.bizconf.vcaasz.soap.conf.ESpaceMeetingAsSoapToken token;

    private java.lang.String confId;

    private int updateType;

    private com.bizconf.vcaasz.soap.conf.ESpaceMeetingAsSoapScheduledUser[] users;

    public ESpaceMeetingAsSoapRequestUpdateConfMemberListRequest() {
    }

    public ESpaceMeetingAsSoapRequestUpdateConfMemberListRequest(
           com.bizconf.vcaasz.soap.conf.ESpaceMeetingAsSoapToken token,
           java.lang.String confId,
           int updateType,
           com.bizconf.vcaasz.soap.conf.ESpaceMeetingAsSoapScheduledUser[] users) {
           this.token = token;
           this.confId = confId;
           this.updateType = updateType;
           this.users = users;
    }


    /**
     * Gets the token value for this ESpaceMeetingAsSoapRequestUpdateConfMemberListRequest.
     * 
     * @return token
     */
    public com.bizconf.vcaasz.soap.conf.ESpaceMeetingAsSoapToken getToken() {
        return token;
    }


    /**
     * Sets the token value for this ESpaceMeetingAsSoapRequestUpdateConfMemberListRequest.
     * 
     * @param token
     */
    public void setToken(com.bizconf.vcaasz.soap.conf.ESpaceMeetingAsSoapToken token) {
        this.token = token;
    }


    /**
     * Gets the confId value for this ESpaceMeetingAsSoapRequestUpdateConfMemberListRequest.
     * 
     * @return confId
     */
    public java.lang.String getConfId() {
        return confId;
    }


    /**
     * Sets the confId value for this ESpaceMeetingAsSoapRequestUpdateConfMemberListRequest.
     * 
     * @param confId
     */
    public void setConfId(java.lang.String confId) {
        this.confId = confId;
    }


    /**
     * Gets the updateType value for this ESpaceMeetingAsSoapRequestUpdateConfMemberListRequest.
     * 
     * @return updateType
     */
    public int getUpdateType() {
        return updateType;
    }


    /**
     * Sets the updateType value for this ESpaceMeetingAsSoapRequestUpdateConfMemberListRequest.
     * 
     * @param updateType
     */
    public void setUpdateType(int updateType) {
        this.updateType = updateType;
    }


    /**
     * Gets the users value for this ESpaceMeetingAsSoapRequestUpdateConfMemberListRequest.
     * 
     * @return users
     */
    public com.bizconf.vcaasz.soap.conf.ESpaceMeetingAsSoapScheduledUser[] getUsers() {
        return users;
    }


    /**
     * Sets the users value for this ESpaceMeetingAsSoapRequestUpdateConfMemberListRequest.
     * 
     * @param users
     */
    public void setUsers(com.bizconf.vcaasz.soap.conf.ESpaceMeetingAsSoapScheduledUser[] users) {
        this.users = users;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof ESpaceMeetingAsSoapRequestUpdateConfMemberListRequest)) return false;
        ESpaceMeetingAsSoapRequestUpdateConfMemberListRequest other = (ESpaceMeetingAsSoapRequestUpdateConfMemberListRequest) obj;
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
            ((this.confId==null && other.getConfId()==null) || 
             (this.confId!=null &&
              this.confId.equals(other.getConfId()))) &&
            this.updateType == other.getUpdateType() &&
            ((this.users==null && other.getUsers()==null) || 
             (this.users!=null &&
              java.util.Arrays.equals(this.users, other.getUsers())));
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
        if (getConfId() != null) {
            _hashCode += getConfId().hashCode();
        }
        _hashCode += getUpdateType();
        if (getUsers() != null) {
            for (int i=0;
                 i<java.lang.reflect.Array.getLength(getUsers());
                 i++) {
                java.lang.Object obj = java.lang.reflect.Array.get(getUsers(), i);
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
        new org.apache.axis.description.TypeDesc(ESpaceMeetingAsSoapRequestUpdateConfMemberListRequest.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("eSpaceMeeting", "eSpaceMeeting.as.soap.request.UpdateConfMemberListRequest"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("token");
        elemField.setXmlName(new javax.xml.namespace.QName("", "token"));
        elemField.setXmlType(new javax.xml.namespace.QName("eSpaceMeeting", "eSpaceMeeting.as.soap.Token"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("confId");
        elemField.setXmlName(new javax.xml.namespace.QName("", "confId"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("updateType");
        elemField.setXmlName(new javax.xml.namespace.QName("", "updateType"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "int"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("users");
        elemField.setXmlName(new javax.xml.namespace.QName("", "users"));
        elemField.setXmlType(new javax.xml.namespace.QName("eSpaceMeeting", "eSpaceMeeting.as.soap.ScheduledUser"));
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
