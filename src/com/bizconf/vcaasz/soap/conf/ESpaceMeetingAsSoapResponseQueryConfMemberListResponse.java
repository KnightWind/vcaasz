/**
 * ESpaceMeetingAsSoapResponseQueryConfMemberListResponse.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Nov 19, 2006 (02:31:34 GMT+00:00) WSDL2Java emitter.
 */

package com.bizconf.vcaasz.soap.conf;

public class ESpaceMeetingAsSoapResponseQueryConfMemberListResponse  implements java.io.Serializable {
    private com.bizconf.vcaasz.soap.conf.ESpaceMeetingAsSoapScheduledUser[] users;

    private int totalCount;

    public ESpaceMeetingAsSoapResponseQueryConfMemberListResponse() {
    }

    public ESpaceMeetingAsSoapResponseQueryConfMemberListResponse(
           com.bizconf.vcaasz.soap.conf.ESpaceMeetingAsSoapScheduledUser[] users,
           int totalCount) {
           this.users = users;
           this.totalCount = totalCount;
    }


    /**
     * Gets the users value for this ESpaceMeetingAsSoapResponseQueryConfMemberListResponse.
     * 
     * @return users
     */
    public com.bizconf.vcaasz.soap.conf.ESpaceMeetingAsSoapScheduledUser[] getUsers() {
        return users;
    }


    /**
     * Sets the users value for this ESpaceMeetingAsSoapResponseQueryConfMemberListResponse.
     * 
     * @param users
     */
    public void setUsers(com.bizconf.vcaasz.soap.conf.ESpaceMeetingAsSoapScheduledUser[] users) {
        this.users = users;
    }


    /**
     * Gets the totalCount value for this ESpaceMeetingAsSoapResponseQueryConfMemberListResponse.
     * 
     * @return totalCount
     */
    public int getTotalCount() {
        return totalCount;
    }


    /**
     * Sets the totalCount value for this ESpaceMeetingAsSoapResponseQueryConfMemberListResponse.
     * 
     * @param totalCount
     */
    public void setTotalCount(int totalCount) {
        this.totalCount = totalCount;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof ESpaceMeetingAsSoapResponseQueryConfMemberListResponse)) return false;
        ESpaceMeetingAsSoapResponseQueryConfMemberListResponse other = (ESpaceMeetingAsSoapResponseQueryConfMemberListResponse) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = true && 
            ((this.users==null && other.getUsers()==null) || 
             (this.users!=null &&
              java.util.Arrays.equals(this.users, other.getUsers()))) &&
            this.totalCount == other.getTotalCount();
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
        _hashCode += getTotalCount();
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(ESpaceMeetingAsSoapResponseQueryConfMemberListResponse.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("eSpaceMeeting", "eSpaceMeeting.as.soap.response.QueryConfMemberListResponse"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("users");
        elemField.setXmlName(new javax.xml.namespace.QName("", "users"));
        elemField.setXmlType(new javax.xml.namespace.QName("eSpaceMeeting", "eSpaceMeeting.as.soap.ScheduledUser"));
        elemField.setNillable(false);
        elemField.setItemQName(new javax.xml.namespace.QName("", "item"));
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("totalCount");
        elemField.setXmlName(new javax.xml.namespace.QName("", "totalCount"));
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
