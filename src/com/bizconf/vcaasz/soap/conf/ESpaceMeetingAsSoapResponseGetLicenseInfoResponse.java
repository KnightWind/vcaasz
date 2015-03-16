/**
 * ESpaceMeetingAsSoapResponseGetLicenseInfoResponse.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Nov 19, 2006 (02:31:34 GMT+00:00) WSDL2Java emitter.
 */

package com.bizconf.vcaasz.soap.conf;

public class ESpaceMeetingAsSoapResponseGetLicenseInfoResponse  implements java.io.Serializable {
    private java.lang.String productName;

    private java.lang.String version;

    private java.lang.String status;

    private java.lang.String updateTime;

    private int alarmThresholdValue;

    private int remainDays;

    public ESpaceMeetingAsSoapResponseGetLicenseInfoResponse() {
    }

    public ESpaceMeetingAsSoapResponseGetLicenseInfoResponse(
           java.lang.String productName,
           java.lang.String version,
           java.lang.String status,
           java.lang.String updateTime,
           int alarmThresholdValue,
           int remainDays) {
           this.productName = productName;
           this.version = version;
           this.status = status;
           this.updateTime = updateTime;
           this.alarmThresholdValue = alarmThresholdValue;
           this.remainDays = remainDays;
    }


    /**
     * Gets the productName value for this ESpaceMeetingAsSoapResponseGetLicenseInfoResponse.
     * 
     * @return productName
     */
    public java.lang.String getProductName() {
        return productName;
    }


    /**
     * Sets the productName value for this ESpaceMeetingAsSoapResponseGetLicenseInfoResponse.
     * 
     * @param productName
     */
    public void setProductName(java.lang.String productName) {
        this.productName = productName;
    }


    /**
     * Gets the version value for this ESpaceMeetingAsSoapResponseGetLicenseInfoResponse.
     * 
     * @return version
     */
    public java.lang.String getVersion() {
        return version;
    }


    /**
     * Sets the version value for this ESpaceMeetingAsSoapResponseGetLicenseInfoResponse.
     * 
     * @param version
     */
    public void setVersion(java.lang.String version) {
        this.version = version;
    }


    /**
     * Gets the status value for this ESpaceMeetingAsSoapResponseGetLicenseInfoResponse.
     * 
     * @return status
     */
    public java.lang.String getStatus() {
        return status;
    }


    /**
     * Sets the status value for this ESpaceMeetingAsSoapResponseGetLicenseInfoResponse.
     * 
     * @param status
     */
    public void setStatus(java.lang.String status) {
        this.status = status;
    }


    /**
     * Gets the updateTime value for this ESpaceMeetingAsSoapResponseGetLicenseInfoResponse.
     * 
     * @return updateTime
     */
    public java.lang.String getUpdateTime() {
        return updateTime;
    }


    /**
     * Sets the updateTime value for this ESpaceMeetingAsSoapResponseGetLicenseInfoResponse.
     * 
     * @param updateTime
     */
    public void setUpdateTime(java.lang.String updateTime) {
        this.updateTime = updateTime;
    }


    /**
     * Gets the alarmThresholdValue value for this ESpaceMeetingAsSoapResponseGetLicenseInfoResponse.
     * 
     * @return alarmThresholdValue
     */
    public int getAlarmThresholdValue() {
        return alarmThresholdValue;
    }


    /**
     * Sets the alarmThresholdValue value for this ESpaceMeetingAsSoapResponseGetLicenseInfoResponse.
     * 
     * @param alarmThresholdValue
     */
    public void setAlarmThresholdValue(int alarmThresholdValue) {
        this.alarmThresholdValue = alarmThresholdValue;
    }


    /**
     * Gets the remainDays value for this ESpaceMeetingAsSoapResponseGetLicenseInfoResponse.
     * 
     * @return remainDays
     */
    public int getRemainDays() {
        return remainDays;
    }


    /**
     * Sets the remainDays value for this ESpaceMeetingAsSoapResponseGetLicenseInfoResponse.
     * 
     * @param remainDays
     */
    public void setRemainDays(int remainDays) {
        this.remainDays = remainDays;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof ESpaceMeetingAsSoapResponseGetLicenseInfoResponse)) return false;
        ESpaceMeetingAsSoapResponseGetLicenseInfoResponse other = (ESpaceMeetingAsSoapResponseGetLicenseInfoResponse) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = true && 
            ((this.productName==null && other.getProductName()==null) || 
             (this.productName!=null &&
              this.productName.equals(other.getProductName()))) &&
            ((this.version==null && other.getVersion()==null) || 
             (this.version!=null &&
              this.version.equals(other.getVersion()))) &&
            ((this.status==null && other.getStatus()==null) || 
             (this.status!=null &&
              this.status.equals(other.getStatus()))) &&
            ((this.updateTime==null && other.getUpdateTime()==null) || 
             (this.updateTime!=null &&
              this.updateTime.equals(other.getUpdateTime()))) &&
            this.alarmThresholdValue == other.getAlarmThresholdValue() &&
            this.remainDays == other.getRemainDays();
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
        if (getProductName() != null) {
            _hashCode += getProductName().hashCode();
        }
        if (getVersion() != null) {
            _hashCode += getVersion().hashCode();
        }
        if (getStatus() != null) {
            _hashCode += getStatus().hashCode();
        }
        if (getUpdateTime() != null) {
            _hashCode += getUpdateTime().hashCode();
        }
        _hashCode += getAlarmThresholdValue();
        _hashCode += getRemainDays();
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(ESpaceMeetingAsSoapResponseGetLicenseInfoResponse.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("eSpaceMeeting", "eSpaceMeeting.as.soap.response.GetLicenseInfoResponse"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("productName");
        elemField.setXmlName(new javax.xml.namespace.QName("", "productName"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("version");
        elemField.setXmlName(new javax.xml.namespace.QName("", "version"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("status");
        elemField.setXmlName(new javax.xml.namespace.QName("", "status"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("updateTime");
        elemField.setXmlName(new javax.xml.namespace.QName("", "updateTime"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("alarmThresholdValue");
        elemField.setXmlName(new javax.xml.namespace.QName("", "alarmThresholdValue"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "int"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("remainDays");
        elemField.setXmlName(new javax.xml.namespace.QName("", "remainDays"));
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
