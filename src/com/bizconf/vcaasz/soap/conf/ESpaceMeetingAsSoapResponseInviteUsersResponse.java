/**
 * ESpaceMeetingAsSoapResponseInviteUsersResponse.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Nov 19, 2006 (02:31:34 GMT+00:00) WSDL2Java emitter.
 */

package com.bizconf.vcaasz.soap.conf;

public class ESpaceMeetingAsSoapResponseInviteUsersResponse  implements java.io.Serializable {
    private java.lang.String[] userIds;

    public ESpaceMeetingAsSoapResponseInviteUsersResponse() {
    }

    public ESpaceMeetingAsSoapResponseInviteUsersResponse(
           java.lang.String[] userIds) {
           this.userIds = userIds;
    }


    /**
     * Gets the userIds value for this ESpaceMeetingAsSoapResponseInviteUsersResponse.
     * 
     * @return userIds
     */
    public java.lang.String[] getUserIds() {
        return userIds;
    }


    /**
     * Sets the userIds value for this ESpaceMeetingAsSoapResponseInviteUsersResponse.
     * 
     * @param userIds
     */
    public void setUserIds(java.lang.String[] userIds) {
        this.userIds = userIds;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof ESpaceMeetingAsSoapResponseInviteUsersResponse)) return false;
        ESpaceMeetingAsSoapResponseInviteUsersResponse other = (ESpaceMeetingAsSoapResponseInviteUsersResponse) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = true && 
            ((this.userIds==null && other.getUserIds()==null) || 
             (this.userIds!=null &&
              java.util.Arrays.equals(this.userIds, other.getUserIds())));
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
        if (getUserIds() != null) {
            for (int i=0;
                 i<java.lang.reflect.Array.getLength(getUserIds());
                 i++) {
                java.lang.Object obj = java.lang.reflect.Array.get(getUserIds(), i);
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
        new org.apache.axis.description.TypeDesc(ESpaceMeetingAsSoapResponseInviteUsersResponse.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("eSpaceMeeting", "eSpaceMeeting.as.soap.response.InviteUsersResponse"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("userIds");
        elemField.setXmlName(new javax.xml.namespace.QName("", "userIds"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
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
