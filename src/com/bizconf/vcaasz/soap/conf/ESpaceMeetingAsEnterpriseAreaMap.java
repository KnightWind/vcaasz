/**
 * ESpaceMeetingAsEnterpriseAreaMap.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Nov 19, 2006 (02:31:34 GMT+00:00) WSDL2Java emitter.
 */

package com.bizconf.vcaasz.soap.conf;

public class ESpaceMeetingAsEnterpriseAreaMap  implements java.io.Serializable {
    private java.lang.String areaId;

    private java.lang.String enterpriseId;

    private int audioPort;

    private int videoPort;

    private int dataPort;

    private java.lang.String parentAreaId;

    private int isDefault;

    public ESpaceMeetingAsEnterpriseAreaMap() {
    }

    public ESpaceMeetingAsEnterpriseAreaMap(
           java.lang.String areaId,
           java.lang.String enterpriseId,
           int audioPort,
           int videoPort,
           int dataPort,
           java.lang.String parentAreaId,
           int isDefault) {
           this.areaId = areaId;
           this.enterpriseId = enterpriseId;
           this.audioPort = audioPort;
           this.videoPort = videoPort;
           this.dataPort = dataPort;
           this.parentAreaId = parentAreaId;
           this.isDefault = isDefault;
    }


    /**
     * Gets the areaId value for this ESpaceMeetingAsEnterpriseAreaMap.
     * 
     * @return areaId
     */
    public java.lang.String getAreaId() {
        return areaId;
    }


    /**
     * Sets the areaId value for this ESpaceMeetingAsEnterpriseAreaMap.
     * 
     * @param areaId
     */
    public void setAreaId(java.lang.String areaId) {
        this.areaId = areaId;
    }


    /**
     * Gets the enterpriseId value for this ESpaceMeetingAsEnterpriseAreaMap.
     * 
     * @return enterpriseId
     */
    public java.lang.String getEnterpriseId() {
        return enterpriseId;
    }


    /**
     * Sets the enterpriseId value for this ESpaceMeetingAsEnterpriseAreaMap.
     * 
     * @param enterpriseId
     */
    public void setEnterpriseId(java.lang.String enterpriseId) {
        this.enterpriseId = enterpriseId;
    }


    /**
     * Gets the audioPort value for this ESpaceMeetingAsEnterpriseAreaMap.
     * 
     * @return audioPort
     */
    public int getAudioPort() {
        return audioPort;
    }


    /**
     * Sets the audioPort value for this ESpaceMeetingAsEnterpriseAreaMap.
     * 
     * @param audioPort
     */
    public void setAudioPort(int audioPort) {
        this.audioPort = audioPort;
    }


    /**
     * Gets the videoPort value for this ESpaceMeetingAsEnterpriseAreaMap.
     * 
     * @return videoPort
     */
    public int getVideoPort() {
        return videoPort;
    }


    /**
     * Sets the videoPort value for this ESpaceMeetingAsEnterpriseAreaMap.
     * 
     * @param videoPort
     */
    public void setVideoPort(int videoPort) {
        this.videoPort = videoPort;
    }


    /**
     * Gets the dataPort value for this ESpaceMeetingAsEnterpriseAreaMap.
     * 
     * @return dataPort
     */
    public int getDataPort() {
        return dataPort;
    }


    /**
     * Sets the dataPort value for this ESpaceMeetingAsEnterpriseAreaMap.
     * 
     * @param dataPort
     */
    public void setDataPort(int dataPort) {
        this.dataPort = dataPort;
    }


    /**
     * Gets the parentAreaId value for this ESpaceMeetingAsEnterpriseAreaMap.
     * 
     * @return parentAreaId
     */
    public java.lang.String getParentAreaId() {
        return parentAreaId;
    }


    /**
     * Sets the parentAreaId value for this ESpaceMeetingAsEnterpriseAreaMap.
     * 
     * @param parentAreaId
     */
    public void setParentAreaId(java.lang.String parentAreaId) {
        this.parentAreaId = parentAreaId;
    }


    /**
     * Gets the isDefault value for this ESpaceMeetingAsEnterpriseAreaMap.
     * 
     * @return isDefault
     */
    public int getIsDefault() {
        return isDefault;
    }


    /**
     * Sets the isDefault value for this ESpaceMeetingAsEnterpriseAreaMap.
     * 
     * @param isDefault
     */
    public void setIsDefault(int isDefault) {
        this.isDefault = isDefault;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof ESpaceMeetingAsEnterpriseAreaMap)) return false;
        ESpaceMeetingAsEnterpriseAreaMap other = (ESpaceMeetingAsEnterpriseAreaMap) obj;
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
            ((this.enterpriseId==null && other.getEnterpriseId()==null) || 
             (this.enterpriseId!=null &&
              this.enterpriseId.equals(other.getEnterpriseId()))) &&
            this.audioPort == other.getAudioPort() &&
            this.videoPort == other.getVideoPort() &&
            this.dataPort == other.getDataPort() &&
            ((this.parentAreaId==null && other.getParentAreaId()==null) || 
             (this.parentAreaId!=null &&
              this.parentAreaId.equals(other.getParentAreaId()))) &&
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
        if (getEnterpriseId() != null) {
            _hashCode += getEnterpriseId().hashCode();
        }
        _hashCode += getAudioPort();
        _hashCode += getVideoPort();
        _hashCode += getDataPort();
        if (getParentAreaId() != null) {
            _hashCode += getParentAreaId().hashCode();
        }
        _hashCode += getIsDefault();
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(ESpaceMeetingAsEnterpriseAreaMap.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("eSpaceMeeting", "eSpaceMeeting.as.EnterpriseAreaMap"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("areaId");
        elemField.setXmlName(new javax.xml.namespace.QName("", "areaId"));
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
        elemField.setFieldName("parentAreaId");
        elemField.setXmlName(new javax.xml.namespace.QName("", "parentAreaId"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(true);
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
