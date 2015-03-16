/**
 * ESpaceMeetingAsSoapResponseGetDataConfInfoResponse.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Nov 19, 2006 (02:31:34 GMT+00:00) WSDL2Java emitter.
 */

package com.bizconf.vcaasz.soap.conf;

public class ESpaceMeetingAsSoapResponseGetDataConfInfoResponse  implements java.io.Serializable {
    private java.lang.String confId;

    private int confType;

    private java.lang.String mediaBits;

    private java.lang.String serverIP;

    private java.lang.String authKey;

    private long timestamp;

    private java.lang.String clusterId;

    private java.lang.String hostkey;

    public ESpaceMeetingAsSoapResponseGetDataConfInfoResponse() {
    }

    public ESpaceMeetingAsSoapResponseGetDataConfInfoResponse(
           java.lang.String confId,
           int confType,
           java.lang.String mediaBits,
           java.lang.String serverIP,
           java.lang.String authKey,
           long timestamp,
           java.lang.String clusterId,
           java.lang.String hostkey) {
           this.confId = confId;
           this.confType = confType;
           this.mediaBits = mediaBits;
           this.serverIP = serverIP;
           this.authKey = authKey;
           this.timestamp = timestamp;
           this.clusterId = clusterId;
           this.hostkey = hostkey;
    }


    /**
     * Gets the confId value for this ESpaceMeetingAsSoapResponseGetDataConfInfoResponse.
     * 
     * @return confId
     */
    public java.lang.String getConfId() {
        return confId;
    }


    /**
     * Sets the confId value for this ESpaceMeetingAsSoapResponseGetDataConfInfoResponse.
     * 
     * @param confId
     */
    public void setConfId(java.lang.String confId) {
        this.confId = confId;
    }


    /**
     * Gets the confType value for this ESpaceMeetingAsSoapResponseGetDataConfInfoResponse.
     * 
     * @return confType
     */
    public int getConfType() {
        return confType;
    }


    /**
     * Sets the confType value for this ESpaceMeetingAsSoapResponseGetDataConfInfoResponse.
     * 
     * @param confType
     */
    public void setConfType(int confType) {
        this.confType = confType;
    }


    /**
     * Gets the mediaBits value for this ESpaceMeetingAsSoapResponseGetDataConfInfoResponse.
     * 
     * @return mediaBits
     */
    public java.lang.String getMediaBits() {
        return mediaBits;
    }


    /**
     * Sets the mediaBits value for this ESpaceMeetingAsSoapResponseGetDataConfInfoResponse.
     * 
     * @param mediaBits
     */
    public void setMediaBits(java.lang.String mediaBits) {
        this.mediaBits = mediaBits;
    }


    /**
     * Gets the serverIP value for this ESpaceMeetingAsSoapResponseGetDataConfInfoResponse.
     * 
     * @return serverIP
     */
    public java.lang.String getServerIP() {
        return serverIP;
    }


    /**
     * Sets the serverIP value for this ESpaceMeetingAsSoapResponseGetDataConfInfoResponse.
     * 
     * @param serverIP
     */
    public void setServerIP(java.lang.String serverIP) {
        this.serverIP = serverIP;
    }


    /**
     * Gets the authKey value for this ESpaceMeetingAsSoapResponseGetDataConfInfoResponse.
     * 
     * @return authKey
     */
    public java.lang.String getAuthKey() {
        return authKey;
    }


    /**
     * Sets the authKey value for this ESpaceMeetingAsSoapResponseGetDataConfInfoResponse.
     * 
     * @param authKey
     */
    public void setAuthKey(java.lang.String authKey) {
        this.authKey = authKey;
    }


    /**
     * Gets the timestamp value for this ESpaceMeetingAsSoapResponseGetDataConfInfoResponse.
     * 
     * @return timestamp
     */
    public long getTimestamp() {
        return timestamp;
    }


    /**
     * Sets the timestamp value for this ESpaceMeetingAsSoapResponseGetDataConfInfoResponse.
     * 
     * @param timestamp
     */
    public void setTimestamp(long timestamp) {
        this.timestamp = timestamp;
    }


    /**
     * Gets the clusterId value for this ESpaceMeetingAsSoapResponseGetDataConfInfoResponse.
     * 
     * @return clusterId
     */
    public java.lang.String getClusterId() {
        return clusterId;
    }


    /**
     * Sets the clusterId value for this ESpaceMeetingAsSoapResponseGetDataConfInfoResponse.
     * 
     * @param clusterId
     */
    public void setClusterId(java.lang.String clusterId) {
        this.clusterId = clusterId;
    }


    /**
     * Gets the hostkey value for this ESpaceMeetingAsSoapResponseGetDataConfInfoResponse.
     * 
     * @return hostkey
     */
    public java.lang.String getHostkey() {
        return hostkey;
    }


    /**
     * Sets the hostkey value for this ESpaceMeetingAsSoapResponseGetDataConfInfoResponse.
     * 
     * @param hostkey
     */
    public void setHostkey(java.lang.String hostkey) {
        this.hostkey = hostkey;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof ESpaceMeetingAsSoapResponseGetDataConfInfoResponse)) return false;
        ESpaceMeetingAsSoapResponseGetDataConfInfoResponse other = (ESpaceMeetingAsSoapResponseGetDataConfInfoResponse) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = true && 
            ((this.confId==null && other.getConfId()==null) || 
             (this.confId!=null &&
              this.confId.equals(other.getConfId()))) &&
            this.confType == other.getConfType() &&
            ((this.mediaBits==null && other.getMediaBits()==null) || 
             (this.mediaBits!=null &&
              this.mediaBits.equals(other.getMediaBits()))) &&
            ((this.serverIP==null && other.getServerIP()==null) || 
             (this.serverIP!=null &&
              this.serverIP.equals(other.getServerIP()))) &&
            ((this.authKey==null && other.getAuthKey()==null) || 
             (this.authKey!=null &&
              this.authKey.equals(other.getAuthKey()))) &&
            this.timestamp == other.getTimestamp() &&
            ((this.clusterId==null && other.getClusterId()==null) || 
             (this.clusterId!=null &&
              this.clusterId.equals(other.getClusterId()))) &&
            ((this.hostkey==null && other.getHostkey()==null) || 
             (this.hostkey!=null &&
              this.hostkey.equals(other.getHostkey())));
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
        if (getConfId() != null) {
            _hashCode += getConfId().hashCode();
        }
        _hashCode += getConfType();
        if (getMediaBits() != null) {
            _hashCode += getMediaBits().hashCode();
        }
        if (getServerIP() != null) {
            _hashCode += getServerIP().hashCode();
        }
        if (getAuthKey() != null) {
            _hashCode += getAuthKey().hashCode();
        }
        _hashCode += new Long(getTimestamp()).hashCode();
        if (getClusterId() != null) {
            _hashCode += getClusterId().hashCode();
        }
        if (getHostkey() != null) {
            _hashCode += getHostkey().hashCode();
        }
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(ESpaceMeetingAsSoapResponseGetDataConfInfoResponse.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("eSpaceMeeting", "eSpaceMeeting.as.soap.response.GetDataConfInfoResponse"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("confId");
        elemField.setXmlName(new javax.xml.namespace.QName("", "confId"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("confType");
        elemField.setXmlName(new javax.xml.namespace.QName("", "confType"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "int"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("mediaBits");
        elemField.setXmlName(new javax.xml.namespace.QName("", "mediaBits"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("serverIP");
        elemField.setXmlName(new javax.xml.namespace.QName("", "serverIP"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("authKey");
        elemField.setXmlName(new javax.xml.namespace.QName("", "authKey"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("timestamp");
        elemField.setXmlName(new javax.xml.namespace.QName("", "timestamp"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "long"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("clusterId");
        elemField.setXmlName(new javax.xml.namespace.QName("", "clusterId"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("hostkey");
        elemField.setXmlName(new javax.xml.namespace.QName("", "hostkey"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(true);
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
