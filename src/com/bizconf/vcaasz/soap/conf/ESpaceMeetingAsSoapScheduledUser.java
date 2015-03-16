/**
 * ESpaceMeetingAsSoapScheduledUser.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Nov 19, 2006 (02:31:34 GMT+00:00) WSDL2Java emitter.
 */

package com.bizconf.vcaasz.soap.conf;

public class ESpaceMeetingAsSoapScheduledUser  implements java.io.Serializable {
    private java.lang.String userId;

    private java.lang.String name;

    private java.lang.String enterpriseId;

    private java.lang.String uri;

    private java.lang.String allowCallNum;

    private java.lang.String pin;

    private int termType;

    private int role;

    private com.bizconf.vcaasz.soap.conf.ESpaceMeetingAsStringKV[] notifyTypes;

    public ESpaceMeetingAsSoapScheduledUser() {
    }

    public ESpaceMeetingAsSoapScheduledUser(
           java.lang.String userId,
           java.lang.String name,
           java.lang.String enterpriseId,
           java.lang.String uri,
           java.lang.String allowCallNum,
           java.lang.String pin,
           int termType,
           int role,
           com.bizconf.vcaasz.soap.conf.ESpaceMeetingAsStringKV[] notifyTypes) {
           this.userId = userId;
           this.name = name;
           this.enterpriseId = enterpriseId;
           this.uri = uri;
           this.allowCallNum = allowCallNum;
           this.pin = pin;
           this.termType = termType;
           this.role = role;
           this.notifyTypes = notifyTypes;
    }


    /**
     * Gets the userId value for this ESpaceMeetingAsSoapScheduledUser.
     * 
     * @return userId
     */
    public java.lang.String getUserId() {
        return userId;
    }


    /**
     * Sets the userId value for this ESpaceMeetingAsSoapScheduledUser.
     * 
     * @param userId
     */
    public void setUserId(java.lang.String userId) {
        this.userId = userId;
    }


    /**
     * Gets the name value for this ESpaceMeetingAsSoapScheduledUser.
     * 
     * @return name
     */
    public java.lang.String getName() {
        return name;
    }


    /**
     * Sets the name value for this ESpaceMeetingAsSoapScheduledUser.
     * 
     * @param name
     */
    public void setName(java.lang.String name) {
        this.name = name;
    }


    /**
     * Gets the enterpriseId value for this ESpaceMeetingAsSoapScheduledUser.
     * 
     * @return enterpriseId
     */
    public java.lang.String getEnterpriseId() {
        return enterpriseId;
    }


    /**
     * Sets the enterpriseId value for this ESpaceMeetingAsSoapScheduledUser.
     * 
     * @param enterpriseId
     */
    public void setEnterpriseId(java.lang.String enterpriseId) {
        this.enterpriseId = enterpriseId;
    }


    /**
     * Gets the uri value for this ESpaceMeetingAsSoapScheduledUser.
     * 
     * @return uri
     */
    public java.lang.String getUri() {
        return uri;
    }


    /**
     * Sets the uri value for this ESpaceMeetingAsSoapScheduledUser.
     * 
     * @param uri
     */
    public void setUri(java.lang.String uri) {
        this.uri = uri;
    }


    /**
     * Gets the allowCallNum value for this ESpaceMeetingAsSoapScheduledUser.
     * 
     * @return allowCallNum
     */
    public java.lang.String getAllowCallNum() {
        return allowCallNum;
    }


    /**
     * Sets the allowCallNum value for this ESpaceMeetingAsSoapScheduledUser.
     * 
     * @param allowCallNum
     */
    public void setAllowCallNum(java.lang.String allowCallNum) {
        this.allowCallNum = allowCallNum;
    }


    /**
     * Gets the pin value for this ESpaceMeetingAsSoapScheduledUser.
     * 
     * @return pin
     */
    public java.lang.String getPin() {
        return pin;
    }


    /**
     * Sets the pin value for this ESpaceMeetingAsSoapScheduledUser.
     * 
     * @param pin
     */
    public void setPin(java.lang.String pin) {
        this.pin = pin;
    }


    /**
     * Gets the termType value for this ESpaceMeetingAsSoapScheduledUser.
     * 
     * @return termType
     */
    public int getTermType() {
        return termType;
    }


    /**
     * Sets the termType value for this ESpaceMeetingAsSoapScheduledUser.
     * 
     * @param termType
     */
    public void setTermType(int termType) {
        this.termType = termType;
    }


    /**
     * Gets the role value for this ESpaceMeetingAsSoapScheduledUser.
     * 
     * @return role
     */
    public int getRole() {
        return role;
    }


    /**
     * Sets the role value for this ESpaceMeetingAsSoapScheduledUser.
     * 
     * @param role
     */
    public void setRole(int role) {
        this.role = role;
    }


    /**
     * Gets the notifyTypes value for this ESpaceMeetingAsSoapScheduledUser.
     * 
     * @return notifyTypes
     */
    public com.bizconf.vcaasz.soap.conf.ESpaceMeetingAsStringKV[] getNotifyTypes() {
        return notifyTypes;
    }


    /**
     * Sets the notifyTypes value for this ESpaceMeetingAsSoapScheduledUser.
     * 
     * @param notifyTypes
     */
    public void setNotifyTypes(com.bizconf.vcaasz.soap.conf.ESpaceMeetingAsStringKV[] notifyTypes) {
        this.notifyTypes = notifyTypes;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof ESpaceMeetingAsSoapScheduledUser)) return false;
        ESpaceMeetingAsSoapScheduledUser other = (ESpaceMeetingAsSoapScheduledUser) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = true && 
            ((this.userId==null && other.getUserId()==null) || 
             (this.userId!=null &&
              this.userId.equals(other.getUserId()))) &&
            ((this.name==null && other.getName()==null) || 
             (this.name!=null &&
              this.name.equals(other.getName()))) &&
            ((this.enterpriseId==null && other.getEnterpriseId()==null) || 
             (this.enterpriseId!=null &&
              this.enterpriseId.equals(other.getEnterpriseId()))) &&
            ((this.uri==null && other.getUri()==null) || 
             (this.uri!=null &&
              this.uri.equals(other.getUri()))) &&
            ((this.allowCallNum==null && other.getAllowCallNum()==null) || 
             (this.allowCallNum!=null &&
              this.allowCallNum.equals(other.getAllowCallNum()))) &&
            ((this.pin==null && other.getPin()==null) || 
             (this.pin!=null &&
              this.pin.equals(other.getPin()))) &&
            this.termType == other.getTermType() &&
            this.role == other.getRole() &&
            ((this.notifyTypes==null && other.getNotifyTypes()==null) || 
             (this.notifyTypes!=null &&
              java.util.Arrays.equals(this.notifyTypes, other.getNotifyTypes())));
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
        if (getUserId() != null) {
            _hashCode += getUserId().hashCode();
        }
        if (getName() != null) {
            _hashCode += getName().hashCode();
        }
        if (getEnterpriseId() != null) {
            _hashCode += getEnterpriseId().hashCode();
        }
        if (getUri() != null) {
            _hashCode += getUri().hashCode();
        }
        if (getAllowCallNum() != null) {
            _hashCode += getAllowCallNum().hashCode();
        }
        if (getPin() != null) {
            _hashCode += getPin().hashCode();
        }
        _hashCode += getTermType();
        _hashCode += getRole();
        if (getNotifyTypes() != null) {
            for (int i=0;
                 i<java.lang.reflect.Array.getLength(getNotifyTypes());
                 i++) {
                java.lang.Object obj = java.lang.reflect.Array.get(getNotifyTypes(), i);
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
        new org.apache.axis.description.TypeDesc(ESpaceMeetingAsSoapScheduledUser.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("eSpaceMeeting", "eSpaceMeeting.as.soap.ScheduledUser"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("userId");
        elemField.setXmlName(new javax.xml.namespace.QName("", "userId"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("name");
        elemField.setXmlName(new javax.xml.namespace.QName("", "name"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("enterpriseId");
        elemField.setXmlName(new javax.xml.namespace.QName("", "enterpriseId"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("uri");
        elemField.setXmlName(new javax.xml.namespace.QName("", "uri"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("allowCallNum");
        elemField.setXmlName(new javax.xml.namespace.QName("", "allowCallNum"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("pin");
        elemField.setXmlName(new javax.xml.namespace.QName("", "pin"));
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
        elemField.setFieldName("role");
        elemField.setXmlName(new javax.xml.namespace.QName("", "role"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "int"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("notifyTypes");
        elemField.setXmlName(new javax.xml.namespace.QName("", "notifyTypes"));
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
