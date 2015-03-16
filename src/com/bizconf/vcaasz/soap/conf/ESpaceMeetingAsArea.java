/**
 * ESpaceMeetingAsArea.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Nov 19, 2006 (02:31:34 GMT+00:00) WSDL2Java emitter.
 */

package com.bizconf.vcaasz.soap.conf;

public class ESpaceMeetingAsArea  implements java.io.Serializable {
    private java.lang.String areaId;

    private java.lang.String name;

    private int audioPort;

    private int videoPort;

    private int dataPort;

    private int isDefault;

    public ESpaceMeetingAsArea() {
    }

    public ESpaceMeetingAsArea(
           java.lang.String areaId,
           java.lang.String name,
           int audioPort,
           int videoPort,
           int dataPort,
           int isDefault) {
           this.areaId = areaId;
           this.name = name;
           this.audioPort = audioPort;
           this.videoPort = videoPort;
           this.dataPort = dataPort;
           this.isDefault = isDefault;
    }


    /**
     * Gets the areaId value for this ESpaceMeetingAsArea.
     * 
     * @return areaId
     */
    public java.lang.String getAreaId() {
        return areaId;
    }


    /**
     * Sets the areaId value for this ESpaceMeetingAsArea.
     * 
     * @param areaId
     */
    public void setAreaId(java.lang.String areaId) {
        this.areaId = areaId;
    }


    /**
     * Gets the name value for this ESpaceMeetingAsArea.
     * 
     * @return name
     */
    public java.lang.String getName() {
        return name;
    }


    /**
     * Sets the name value for this ESpaceMeetingAsArea.
     * 
     * @param name
     */
    public void setName(java.lang.String name) {
        this.name = name;
    }


    /**
     * Gets the audioPort value for this ESpaceMeetingAsArea.
     * 
     * @return audioPort
     */
    public int getAudioPort() {
        return audioPort;
    }


    /**
     * Sets the audioPort value for this ESpaceMeetingAsArea.
     * 
     * @param audioPort
     */
    public void setAudioPort(int audioPort) {
        this.audioPort = audioPort;
    }


    /**
     * Gets the videoPort value for this ESpaceMeetingAsArea.
     * 
     * @return videoPort
     */
    public int getVideoPort() {
        return videoPort;
    }


    /**
     * Sets the videoPort value for this ESpaceMeetingAsArea.
     * 
     * @param videoPort
     */
    public void setVideoPort(int videoPort) {
        this.videoPort = videoPort;
    }


    /**
     * Gets the dataPort value for this ESpaceMeetingAsArea.
     * 
     * @return dataPort
     */
    public int getDataPort() {
        return dataPort;
    }


    /**
     * Sets the dataPort value for this ESpaceMeetingAsArea.
     * 
     * @param dataPort
     */
    public void setDataPort(int dataPort) {
        this.dataPort = dataPort;
    }


    /**
     * Gets the isDefault value for this ESpaceMeetingAsArea.
     * 
     * @return isDefault
     */
    public int getIsDefault() {
        return isDefault;
    }


    /**
     * Sets the isDefault value for this ESpaceMeetingAsArea.
     * 
     * @param isDefault
     */
    public void setIsDefault(int isDefault) {
        this.isDefault = isDefault;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof ESpaceMeetingAsArea)) return false;
        ESpaceMeetingAsArea other = (ESpaceMeetingAsArea) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = true && 
            ((this.areaId==null && other.getAreaId()==null) || 
             (this.areaId!=null &&
              this.areaId.equals(other.getAreaId()))) &&
            ((this.name==null && other.getName()==null) || 
             (this.name!=null &&
              this.name.equals(other.getName()))) &&
            this.audioPort == other.getAudioPort() &&
            this.videoPort == other.getVideoPort() &&
            this.dataPort == other.getDataPort() &&
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
        if (getAreaId() != null) {
            _hashCode += getAreaId().hashCode();
        }
        if (getName() != null) {
            _hashCode += getName().hashCode();
        }
        _hashCode += getAudioPort();
        _hashCode += getVideoPort();
        _hashCode += getDataPort();
        _hashCode += getIsDefault();
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(ESpaceMeetingAsArea.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("eSpaceMeeting", "eSpaceMeeting.as.Area"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
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
        elemField.setFieldName("audioPort");
        elemField.setXmlName(new javax.xml.namespace.QName("", "audioPort"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "int"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("videoPort");
        elemField.setXmlName(new javax.xml.namespace.QName("", "videoPort"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "int"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("dataPort");
        elemField.setXmlName(new javax.xml.namespace.QName("", "dataPort"));
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
