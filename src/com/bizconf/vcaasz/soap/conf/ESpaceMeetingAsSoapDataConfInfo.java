/**
 * ESpaceMeetingAsSoapDataConfInfo.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Nov 19, 2006 (02:31:34 GMT+00:00) WSDL2Java emitter.
 */

package com.bizconf.vcaasz.soap.conf;

public class ESpaceMeetingAsSoapDataConfInfo  implements java.io.Serializable {
    private java.lang.String confId;

    private int confType;

    private java.lang.String attendeeNum;

    private java.lang.String attendeeType;

    private java.lang.String serverIP;

    private java.lang.String encryptToken;

    private java.lang.String timeStamp;

    private java.lang.String clusterId;

    private java.lang.String hostkey;

    private com.bizconf.vcaasz.soap.conf.ESpaceMeetingAsStringKV[] kvList;

    public ESpaceMeetingAsSoapDataConfInfo() {
    }

    public ESpaceMeetingAsSoapDataConfInfo(
           java.lang.String confId,
           int confType,
           java.lang.String attendeeNum,
           java.lang.String attendeeType,
           java.lang.String serverIP,
           java.lang.String encryptToken,
           java.lang.String timeStamp,
           java.lang.String clusterId,
           java.lang.String hostkey,
           com.bizconf.vcaasz.soap.conf.ESpaceMeetingAsStringKV[] kvList) {
           this.confId = confId;
           this.confType = confType;
           this.attendeeNum = attendeeNum;
           this.attendeeType = attendeeType;
           this.serverIP = serverIP;
           this.encryptToken = encryptToken;
           this.timeStamp = timeStamp;
           this.clusterId = clusterId;
           this.hostkey = hostkey;
           this.kvList = kvList;
    }


    /**
     * Gets the confId value for this ESpaceMeetingAsSoapDataConfInfo.
     * 
     * @return confId
     */
    public java.lang.String getConfId() {
        return confId;
    }


    /**
     * Sets the confId value for this ESpaceMeetingAsSoapDataConfInfo.
     * 
     * @param confId
     */
    public void setConfId(java.lang.String confId) {
        this.confId = confId;
    }


    /**
     * Gets the confType value for this ESpaceMeetingAsSoapDataConfInfo.
     * 
     * @return confType
     */
    public int getConfType() {
        return confType;
    }


    /**
     * Sets the confType value for this ESpaceMeetingAsSoapDataConfInfo.
     * 
     * @param confType
     */
    public void setConfType(int confType) {
        this.confType = confType;
    }


    /**
     * Gets the attendeeNum value for this ESpaceMeetingAsSoapDataConfInfo.
     * 
     * @return attendeeNum
     */
    public java.lang.String getAttendeeNum() {
        return attendeeNum;
    }


    /**
     * Sets the attendeeNum value for this ESpaceMeetingAsSoapDataConfInfo.
     * 
     * @param attendeeNum
     */
    public void setAttendeeNum(java.lang.String attendeeNum) {
        this.attendeeNum = attendeeNum;
    }


    /**
     * Gets the attendeeType value for this ESpaceMeetingAsSoapDataConfInfo.
     * 
     * @return attendeeType
     */
    public java.lang.String getAttendeeType() {
        return attendeeType;
    }


    /**
     * Sets the attendeeType value for this ESpaceMeetingAsSoapDataConfInfo.
     * 
     * @param attendeeType
     */
    public void setAttendeeType(java.lang.String attendeeType) {
        this.attendeeType = attendeeType;
    }


    /**
     * Gets the serverIP value for this ESpaceMeetingAsSoapDataConfInfo.
     * 
     * @return serverIP
     */
    public java.lang.String getServerIP() {
        return serverIP;
    }


    /**
     * Sets the serverIP value for this ESpaceMeetingAsSoapDataConfInfo.
     * 
     * @param serverIP
     */
    public void setServerIP(java.lang.String serverIP) {
        this.serverIP = serverIP;
    }


    /**
     * Gets the encryptToken value for this ESpaceMeetingAsSoapDataConfInfo.
     * 
     * @return encryptToken
     */
    public java.lang.String getEncryptToken() {
        return encryptToken;
    }


    /**
     * Sets the encryptToken value for this ESpaceMeetingAsSoapDataConfInfo.
     * 
     * @param encryptToken
     */
    public void setEncryptToken(java.lang.String encryptToken) {
        this.encryptToken = encryptToken;
    }


    /**
     * Gets the timeStamp value for this ESpaceMeetingAsSoapDataConfInfo.
     * 
     * @return timeStamp
     */
    public java.lang.String getTimeStamp() {
        return timeStamp;
    }


    /**
     * Sets the timeStamp value for this ESpaceMeetingAsSoapDataConfInfo.
     * 
     * @param timeStamp
     */
    public void setTimeStamp(java.lang.String timeStamp) {
        this.timeStamp = timeStamp;
    }


    /**
     * Gets the clusterId value for this ESpaceMeetingAsSoapDataConfInfo.
     * 
     * @return clusterId
     */
    public java.lang.String getClusterId() {
        return clusterId;
    }


    /**
     * Sets the clusterId value for this ESpaceMeetingAsSoapDataConfInfo.
     * 
     * @param clusterId
     */
    public void setClusterId(java.lang.String clusterId) {
        this.clusterId = clusterId;
    }


    /**
     * Gets the hostkey value for this ESpaceMeetingAsSoapDataConfInfo.
     * 
     * @return hostkey
     */
    public java.lang.String getHostkey() {
        return hostkey;
    }


    /**
     * Sets the hostkey value for this ESpaceMeetingAsSoapDataConfInfo.
     * 
     * @param hostkey
     */
    public void setHostkey(java.lang.String hostkey) {
        this.hostkey = hostkey;
    }


    /**
     * Gets the kvList value for this ESpaceMeetingAsSoapDataConfInfo.
     * 
     * @return kvList
     */
    public com.bizconf.vcaasz.soap.conf.ESpaceMeetingAsStringKV[] getKvList() {
        return kvList;
    }


    /**
     * Sets the kvList value for this ESpaceMeetingAsSoapDataConfInfo.
     * 
     * @param kvList
     */
    public void setKvList(com.bizconf.vcaasz.soap.conf.ESpaceMeetingAsStringKV[] kvList) {
        this.kvList = kvList;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof ESpaceMeetingAsSoapDataConfInfo)) return false;
        ESpaceMeetingAsSoapDataConfInfo other = (ESpaceMeetingAsSoapDataConfInfo) obj;
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
            ((this.attendeeNum==null && other.getAttendeeNum()==null) || 
             (this.attendeeNum!=null &&
              this.attendeeNum.equals(other.getAttendeeNum()))) &&
            ((this.attendeeType==null && other.getAttendeeType()==null) || 
             (this.attendeeType!=null &&
              this.attendeeType.equals(other.getAttendeeType()))) &&
            ((this.serverIP==null && other.getServerIP()==null) || 
             (this.serverIP!=null &&
              this.serverIP.equals(other.getServerIP()))) &&
            ((this.encryptToken==null && other.getEncryptToken()==null) || 
             (this.encryptToken!=null &&
              this.encryptToken.equals(other.getEncryptToken()))) &&
            ((this.timeStamp==null && other.getTimeStamp()==null) || 
             (this.timeStamp!=null &&
              this.timeStamp.equals(other.getTimeStamp()))) &&
            ((this.clusterId==null && other.getClusterId()==null) || 
             (this.clusterId!=null &&
              this.clusterId.equals(other.getClusterId()))) &&
            ((this.hostkey==null && other.getHostkey()==null) || 
             (this.hostkey!=null &&
              this.hostkey.equals(other.getHostkey()))) &&
            ((this.kvList==null && other.getKvList()==null) || 
             (this.kvList!=null &&
              java.util.Arrays.equals(this.kvList, other.getKvList())));
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
        if (getAttendeeNum() != null) {
            _hashCode += getAttendeeNum().hashCode();
        }
        if (getAttendeeType() != null) {
            _hashCode += getAttendeeType().hashCode();
        }
        if (getServerIP() != null) {
            _hashCode += getServerIP().hashCode();
        }
        if (getEncryptToken() != null) {
            _hashCode += getEncryptToken().hashCode();
        }
        if (getTimeStamp() != null) {
            _hashCode += getTimeStamp().hashCode();
        }
        if (getClusterId() != null) {
            _hashCode += getClusterId().hashCode();
        }
        if (getHostkey() != null) {
            _hashCode += getHostkey().hashCode();
        }
        if (getKvList() != null) {
            for (int i=0;
                 i<java.lang.reflect.Array.getLength(getKvList());
                 i++) {
                java.lang.Object obj = java.lang.reflect.Array.get(getKvList(), i);
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
        new org.apache.axis.description.TypeDesc(ESpaceMeetingAsSoapDataConfInfo.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("eSpaceMeeting", "eSpaceMeeting.as.soap.DataConfInfo"));
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
        elemField.setFieldName("attendeeNum");
        elemField.setXmlName(new javax.xml.namespace.QName("", "attendeeNum"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("attendeeType");
        elemField.setXmlName(new javax.xml.namespace.QName("", "attendeeType"));
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
        elemField.setFieldName("encryptToken");
        elemField.setXmlName(new javax.xml.namespace.QName("", "encryptToken"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("timeStamp");
        elemField.setXmlName(new javax.xml.namespace.QName("", "timeStamp"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(true);
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
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("kvList");
        elemField.setXmlName(new javax.xml.namespace.QName("", "kvList"));
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
