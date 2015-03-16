/**
 * ESpaceMeetingAsEnterprise.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Nov 19, 2006 (02:31:34 GMT+00:00) WSDL2Java emitter.
 */

package com.bizconf.vcaasz.soap.conf;

public class ESpaceMeetingAsEnterprise  implements java.io.Serializable {
    private java.lang.String enterpriseId;

    private java.lang.String name;

    private java.lang.String passwd;

    private int status;

    private java.lang.String feature;

    private int audioPort;

    private int videoPort;

    private int dataPort;

    private int foreverAudioPort;

    private int foreverVideoPort;

    private int foreverDataPort;

    private int isSupportTP;

    private int needDefaultArea;

    public ESpaceMeetingAsEnterprise() {
    }

    public ESpaceMeetingAsEnterprise(
           java.lang.String enterpriseId,
           java.lang.String name,
           java.lang.String passwd,
           int status,
           java.lang.String feature,
           int audioPort,
           int videoPort,
           int dataPort,
           int foreverAudioPort,
           int foreverVideoPort,
           int foreverDataPort,
           int isSupportTP,
           int needDefaultArea) {
           this.enterpriseId = enterpriseId;
           this.name = name;
           this.passwd = passwd;
           this.status = status;
           this.feature = feature;
           this.audioPort = audioPort;
           this.videoPort = videoPort;
           this.dataPort = dataPort;
           this.foreverAudioPort = foreverAudioPort;
           this.foreverVideoPort = foreverVideoPort;
           this.foreverDataPort = foreverDataPort;
           this.isSupportTP = isSupportTP;
           this.needDefaultArea = needDefaultArea;
    }


    /**
     * Gets the enterpriseId value for this ESpaceMeetingAsEnterprise.
     * 
     * @return enterpriseId
     */
    public java.lang.String getEnterpriseId() {
        return enterpriseId;
    }


    /**
     * Sets the enterpriseId value for this ESpaceMeetingAsEnterprise.
     * 
     * @param enterpriseId
     */
    public void setEnterpriseId(java.lang.String enterpriseId) {
        this.enterpriseId = enterpriseId;
    }


    /**
     * Gets the name value for this ESpaceMeetingAsEnterprise.
     * 
     * @return name
     */
    public java.lang.String getName() {
        return name;
    }


    /**
     * Sets the name value for this ESpaceMeetingAsEnterprise.
     * 
     * @param name
     */
    public void setName(java.lang.String name) {
        this.name = name;
    }


    /**
     * Gets the passwd value for this ESpaceMeetingAsEnterprise.
     * 
     * @return passwd
     */
    public java.lang.String getPasswd() {
        return passwd;
    }


    /**
     * Sets the passwd value for this ESpaceMeetingAsEnterprise.
     * 
     * @param passwd
     */
    public void setPasswd(java.lang.String passwd) {
        this.passwd = passwd;
    }


    /**
     * Gets the status value for this ESpaceMeetingAsEnterprise.
     * 
     * @return status
     */
    public int getStatus() {
        return status;
    }


    /**
     * Sets the status value for this ESpaceMeetingAsEnterprise.
     * 
     * @param status
     */
    public void setStatus(int status) {
        this.status = status;
    }


    /**
     * Gets the feature value for this ESpaceMeetingAsEnterprise.
     * 
     * @return feature
     */
    public java.lang.String getFeature() {
        return feature;
    }


    /**
     * Sets the feature value for this ESpaceMeetingAsEnterprise.
     * 
     * @param feature
     */
    public void setFeature(java.lang.String feature) {
        this.feature = feature;
    }


    /**
     * Gets the audioPort value for this ESpaceMeetingAsEnterprise.
     * 
     * @return audioPort
     */
    public int getAudioPort() {
        return audioPort;
    }


    /**
     * Sets the audioPort value for this ESpaceMeetingAsEnterprise.
     * 
     * @param audioPort
     */
    public void setAudioPort(int audioPort) {
        this.audioPort = audioPort;
    }


    /**
     * Gets the videoPort value for this ESpaceMeetingAsEnterprise.
     * 
     * @return videoPort
     */
    public int getVideoPort() {
        return videoPort;
    }


    /**
     * Sets the videoPort value for this ESpaceMeetingAsEnterprise.
     * 
     * @param videoPort
     */
    public void setVideoPort(int videoPort) {
        this.videoPort = videoPort;
    }


    /**
     * Gets the dataPort value for this ESpaceMeetingAsEnterprise.
     * 
     * @return dataPort
     */
    public int getDataPort() {
        return dataPort;
    }


    /**
     * Sets the dataPort value for this ESpaceMeetingAsEnterprise.
     * 
     * @param dataPort
     */
    public void setDataPort(int dataPort) {
        this.dataPort = dataPort;
    }


    /**
     * Gets the foreverAudioPort value for this ESpaceMeetingAsEnterprise.
     * 
     * @return foreverAudioPort
     */
    public int getForeverAudioPort() {
        return foreverAudioPort;
    }


    /**
     * Sets the foreverAudioPort value for this ESpaceMeetingAsEnterprise.
     * 
     * @param foreverAudioPort
     */
    public void setForeverAudioPort(int foreverAudioPort) {
        this.foreverAudioPort = foreverAudioPort;
    }


    /**
     * Gets the foreverVideoPort value for this ESpaceMeetingAsEnterprise.
     * 
     * @return foreverVideoPort
     */
    public int getForeverVideoPort() {
        return foreverVideoPort;
    }


    /**
     * Sets the foreverVideoPort value for this ESpaceMeetingAsEnterprise.
     * 
     * @param foreverVideoPort
     */
    public void setForeverVideoPort(int foreverVideoPort) {
        this.foreverVideoPort = foreverVideoPort;
    }


    /**
     * Gets the foreverDataPort value for this ESpaceMeetingAsEnterprise.
     * 
     * @return foreverDataPort
     */
    public int getForeverDataPort() {
        return foreverDataPort;
    }


    /**
     * Sets the foreverDataPort value for this ESpaceMeetingAsEnterprise.
     * 
     * @param foreverDataPort
     */
    public void setForeverDataPort(int foreverDataPort) {
        this.foreverDataPort = foreverDataPort;
    }


    /**
     * Gets the isSupportTP value for this ESpaceMeetingAsEnterprise.
     * 
     * @return isSupportTP
     */
    public int getIsSupportTP() {
        return isSupportTP;
    }


    /**
     * Sets the isSupportTP value for this ESpaceMeetingAsEnterprise.
     * 
     * @param isSupportTP
     */
    public void setIsSupportTP(int isSupportTP) {
        this.isSupportTP = isSupportTP;
    }


    /**
     * Gets the needDefaultArea value for this ESpaceMeetingAsEnterprise.
     * 
     * @return needDefaultArea
     */
    public int getNeedDefaultArea() {
        return needDefaultArea;
    }


    /**
     * Sets the needDefaultArea value for this ESpaceMeetingAsEnterprise.
     * 
     * @param needDefaultArea
     */
    public void setNeedDefaultArea(int needDefaultArea) {
        this.needDefaultArea = needDefaultArea;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof ESpaceMeetingAsEnterprise)) return false;
        ESpaceMeetingAsEnterprise other = (ESpaceMeetingAsEnterprise) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = true && 
            ((this.enterpriseId==null && other.getEnterpriseId()==null) || 
             (this.enterpriseId!=null &&
              this.enterpriseId.equals(other.getEnterpriseId()))) &&
            ((this.name==null && other.getName()==null) || 
             (this.name!=null &&
              this.name.equals(other.getName()))) &&
            ((this.passwd==null && other.getPasswd()==null) || 
             (this.passwd!=null &&
              this.passwd.equals(other.getPasswd()))) &&
            this.status == other.getStatus() &&
            ((this.feature==null && other.getFeature()==null) || 
             (this.feature!=null &&
              this.feature.equals(other.getFeature()))) &&
            this.audioPort == other.getAudioPort() &&
            this.videoPort == other.getVideoPort() &&
            this.dataPort == other.getDataPort() &&
            this.foreverAudioPort == other.getForeverAudioPort() &&
            this.foreverVideoPort == other.getForeverVideoPort() &&
            this.foreverDataPort == other.getForeverDataPort() &&
            this.isSupportTP == other.getIsSupportTP() &&
            this.needDefaultArea == other.getNeedDefaultArea();
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
        if (getEnterpriseId() != null) {
            _hashCode += getEnterpriseId().hashCode();
        }
        if (getName() != null) {
            _hashCode += getName().hashCode();
        }
        if (getPasswd() != null) {
            _hashCode += getPasswd().hashCode();
        }
        _hashCode += getStatus();
        if (getFeature() != null) {
            _hashCode += getFeature().hashCode();
        }
        _hashCode += getAudioPort();
        _hashCode += getVideoPort();
        _hashCode += getDataPort();
        _hashCode += getForeverAudioPort();
        _hashCode += getForeverVideoPort();
        _hashCode += getForeverDataPort();
        _hashCode += getIsSupportTP();
        _hashCode += getNeedDefaultArea();
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(ESpaceMeetingAsEnterprise.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("eSpaceMeeting", "eSpaceMeeting.as.Enterprise"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("enterpriseId");
        elemField.setXmlName(new javax.xml.namespace.QName("", "enterpriseId"));
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
        elemField.setFieldName("passwd");
        elemField.setXmlName(new javax.xml.namespace.QName("", "passwd"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("status");
        elemField.setXmlName(new javax.xml.namespace.QName("", "status"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "int"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("feature");
        elemField.setXmlName(new javax.xml.namespace.QName("", "feature"));
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
        elemField.setFieldName("foreverAudioPort");
        elemField.setXmlName(new javax.xml.namespace.QName("", "foreverAudioPort"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "int"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("foreverVideoPort");
        elemField.setXmlName(new javax.xml.namespace.QName("", "foreverVideoPort"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "int"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("foreverDataPort");
        elemField.setXmlName(new javax.xml.namespace.QName("", "foreverDataPort"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "int"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("isSupportTP");
        elemField.setXmlName(new javax.xml.namespace.QName("", "isSupportTP"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "int"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("needDefaultArea");
        elemField.setXmlName(new javax.xml.namespace.QName("", "needDefaultArea"));
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
