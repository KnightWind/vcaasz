/**
 * ESpaceMeetingAsAreaMRS.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Nov 19, 2006 (02:31:34 GMT+00:00) WSDL2Java emitter.
 */

package com.bizconf.vcaasz.soap.conf;

public class ESpaceMeetingAsAreaMRS  implements java.io.Serializable {
    private java.lang.String mrsId;

    private java.lang.String areaId;

    private java.lang.String name;

    private java.lang.String mrsIp;

    private java.lang.String mrsPort;

    private int mrsType;

    private int isDefault;

    public ESpaceMeetingAsAreaMRS() {
    }

    public ESpaceMeetingAsAreaMRS(
           java.lang.String mrsId,
           java.lang.String areaId,
           java.lang.String name,
           java.lang.String mrsIp,
           java.lang.String mrsPort,
           int mrsType,
           int isDefault) {
           this.mrsId = mrsId;
           this.areaId = areaId;
           this.name = name;
           this.mrsIp = mrsIp;
           this.mrsPort = mrsPort;
           this.mrsType = mrsType;
           this.isDefault = isDefault;
    }


    /**
     * Gets the mrsId value for this ESpaceMeetingAsAreaMRS.
     * 
     * @return mrsId
     */
    public java.lang.String getMrsId() {
        return mrsId;
    }


    /**
     * Sets the mrsId value for this ESpaceMeetingAsAreaMRS.
     * 
     * @param mrsId
     */
    public void setMrsId(java.lang.String mrsId) {
        this.mrsId = mrsId;
    }


    /**
     * Gets the areaId value for this ESpaceMeetingAsAreaMRS.
     * 
     * @return areaId
     */
    public java.lang.String getAreaId() {
        return areaId;
    }


    /**
     * Sets the areaId value for this ESpaceMeetingAsAreaMRS.
     * 
     * @param areaId
     */
    public void setAreaId(java.lang.String areaId) {
        this.areaId = areaId;
    }


    /**
     * Gets the name value for this ESpaceMeetingAsAreaMRS.
     * 
     * @return name
     */
    public java.lang.String getName() {
        return name;
    }


    /**
     * Sets the name value for this ESpaceMeetingAsAreaMRS.
     * 
     * @param name
     */
    public void setName(java.lang.String name) {
        this.name = name;
    }


    /**
     * Gets the mrsIp value for this ESpaceMeetingAsAreaMRS.
     * 
     * @return mrsIp
     */
    public java.lang.String getMrsIp() {
        return mrsIp;
    }


    /**
     * Sets the mrsIp value for this ESpaceMeetingAsAreaMRS.
     * 
     * @param mrsIp
     */
    public void setMrsIp(java.lang.String mrsIp) {
        this.mrsIp = mrsIp;
    }


    /**
     * Gets the mrsPort value for this ESpaceMeetingAsAreaMRS.
     * 
     * @return mrsPort
     */
    public java.lang.String getMrsPort() {
        return mrsPort;
    }


    /**
     * Sets the mrsPort value for this ESpaceMeetingAsAreaMRS.
     * 
     * @param mrsPort
     */
    public void setMrsPort(java.lang.String mrsPort) {
        this.mrsPort = mrsPort;
    }


    /**
     * Gets the mrsType value for this ESpaceMeetingAsAreaMRS.
     * 
     * @return mrsType
     */
    public int getMrsType() {
        return mrsType;
    }


    /**
     * Sets the mrsType value for this ESpaceMeetingAsAreaMRS.
     * 
     * @param mrsType
     */
    public void setMrsType(int mrsType) {
        this.mrsType = mrsType;
    }


    /**
     * Gets the isDefault value for this ESpaceMeetingAsAreaMRS.
     * 
     * @return isDefault
     */
    public int getIsDefault() {
        return isDefault;
    }


    /**
     * Sets the isDefault value for this ESpaceMeetingAsAreaMRS.
     * 
     * @param isDefault
     */
    public void setIsDefault(int isDefault) {
        this.isDefault = isDefault;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof ESpaceMeetingAsAreaMRS)) return false;
        ESpaceMeetingAsAreaMRS other = (ESpaceMeetingAsAreaMRS) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = true && 
            ((this.mrsId==null && other.getMrsId()==null) || 
             (this.mrsId!=null &&
              this.mrsId.equals(other.getMrsId()))) &&
            ((this.areaId==null && other.getAreaId()==null) || 
             (this.areaId!=null &&
              this.areaId.equals(other.getAreaId()))) &&
            ((this.name==null && other.getName()==null) || 
             (this.name!=null &&
              this.name.equals(other.getName()))) &&
            ((this.mrsIp==null && other.getMrsIp()==null) || 
             (this.mrsIp!=null &&
              this.mrsIp.equals(other.getMrsIp()))) &&
            ((this.mrsPort==null && other.getMrsPort()==null) || 
             (this.mrsPort!=null &&
              this.mrsPort.equals(other.getMrsPort()))) &&
            this.mrsType == other.getMrsType() &&
            this.isDefault == other.getIsDefault();
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
        if (getMrsId() != null) {
            _hashCode += getMrsId().hashCode();
        }
        if (getAreaId() != null) {
            _hashCode += getAreaId().hashCode();
        }
        if (getName() != null) {
            _hashCode += getName().hashCode();
        }
        if (getMrsIp() != null) {
            _hashCode += getMrsIp().hashCode();
        }
        if (getMrsPort() != null) {
            _hashCode += getMrsPort().hashCode();
        }
        _hashCode += getMrsType();
        _hashCode += getIsDefault();
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(ESpaceMeetingAsAreaMRS.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("eSpaceMeeting", "eSpaceMeeting.as.AreaMRS"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("mrsId");
        elemField.setXmlName(new javax.xml.namespace.QName("", "mrsId"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("areaId");
        elemField.setXmlName(new javax.xml.namespace.QName("", "areaId"));
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
        elemField.setFieldName("mrsIp");
        elemField.setXmlName(new javax.xml.namespace.QName("", "mrsIp"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("mrsPort");
        elemField.setXmlName(new javax.xml.namespace.QName("", "mrsPort"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("mrsType");
        elemField.setXmlName(new javax.xml.namespace.QName("", "mrsType"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "int"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("isDefault");
        elemField.setXmlName(new javax.xml.namespace.QName("", "isDefault"));
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
