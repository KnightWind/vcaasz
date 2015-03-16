/**
 * ESpaceMeetingAsSoapLicenseRes.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Nov 19, 2006 (02:31:34 GMT+00:00) WSDL2Java emitter.
 */

package com.bizconf.vcaasz.soap.conf;

public class ESpaceMeetingAsSoapLicenseRes  implements java.io.Serializable {
    private int audioPort;

    private int videoPort;

    private int dataPort;

    private boolean isSupportTP;

    public ESpaceMeetingAsSoapLicenseRes() {
    }

    public ESpaceMeetingAsSoapLicenseRes(
           int audioPort,
           int videoPort,
           int dataPort,
           boolean isSupportTP) {
           this.audioPort = audioPort;
           this.videoPort = videoPort;
           this.dataPort = dataPort;
           this.isSupportTP = isSupportTP;
    }


    /**
     * Gets the audioPort value for this ESpaceMeetingAsSoapLicenseRes.
     * 
     * @return audioPort
     */
    public int getAudioPort() {
        return audioPort;
    }


    /**
     * Sets the audioPort value for this ESpaceMeetingAsSoapLicenseRes.
     * 
     * @param audioPort
     */
    public void setAudioPort(int audioPort) {
        this.audioPort = audioPort;
    }


    /**
     * Gets the videoPort value for this ESpaceMeetingAsSoapLicenseRes.
     * 
     * @return videoPort
     */
    public int getVideoPort() {
        return videoPort;
    }


    /**
     * Sets the videoPort value for this ESpaceMeetingAsSoapLicenseRes.
     * 
     * @param videoPort
     */
    public void setVideoPort(int videoPort) {
        this.videoPort = videoPort;
    }


    /**
     * Gets the dataPort value for this ESpaceMeetingAsSoapLicenseRes.
     * 
     * @return dataPort
     */
    public int getDataPort() {
        return dataPort;
    }


    /**
     * Sets the dataPort value for this ESpaceMeetingAsSoapLicenseRes.
     * 
     * @param dataPort
     */
    public void setDataPort(int dataPort) {
        this.dataPort = dataPort;
    }


    /**
     * Gets the isSupportTP value for this ESpaceMeetingAsSoapLicenseRes.
     * 
     * @return isSupportTP
     */
    public boolean isIsSupportTP() {
        return isSupportTP;
    }


    /**
     * Sets the isSupportTP value for this ESpaceMeetingAsSoapLicenseRes.
     * 
     * @param isSupportTP
     */
    public void setIsSupportTP(boolean isSupportTP) {
        this.isSupportTP = isSupportTP;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof ESpaceMeetingAsSoapLicenseRes)) return false;
        ESpaceMeetingAsSoapLicenseRes other = (ESpaceMeetingAsSoapLicenseRes) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = true && 
            this.audioPort == other.getAudioPort() &&
            this.videoPort == other.getVideoPort() &&
            this.dataPort == other.getDataPort() &&
            this.isSupportTP == other.isIsSupportTP();
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
        _hashCode += getAudioPort();
        _hashCode += getVideoPort();
        _hashCode += getDataPort();
        _hashCode += (isIsSupportTP() ? Boolean.TRUE : Boolean.FALSE).hashCode();
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(ESpaceMeetingAsSoapLicenseRes.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("eSpaceMeeting", "eSpaceMeeting.as.soap.LicenseRes"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
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
        elemField.setFieldName("isSupportTP");
        elemField.setXmlName(new javax.xml.namespace.QName("", "isSupportTP"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "boolean"));
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
