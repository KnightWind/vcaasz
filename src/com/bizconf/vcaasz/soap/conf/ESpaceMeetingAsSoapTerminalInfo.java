/**
 * ESpaceMeetingAsSoapTerminalInfo.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Nov 19, 2006 (02:31:34 GMT+00:00) WSDL2Java emitter.
 */

package com.bizconf.vcaasz.soap.conf;

public class ESpaceMeetingAsSoapTerminalInfo  implements java.io.Serializable {
    private java.lang.String confId;

    private java.lang.String userUri;

    private int terminalType;

    private java.lang.String terminalIp;

    private java.lang.String areaId;

    private com.bizconf.vcaasz.soap.conf.ESpaceMeetingAsStringKV[] kvList;

    public ESpaceMeetingAsSoapTerminalInfo() {
    }

    public ESpaceMeetingAsSoapTerminalInfo(
           java.lang.String confId,
           java.lang.String userUri,
           int terminalType,
           java.lang.String terminalIp,
           java.lang.String areaId,
           com.bizconf.vcaasz.soap.conf.ESpaceMeetingAsStringKV[] kvList) {
           this.confId = confId;
           this.userUri = userUri;
           this.terminalType = terminalType;
           this.terminalIp = terminalIp;
           this.areaId = areaId;
           this.kvList = kvList;
    }


    /**
     * Gets the confId value for this ESpaceMeetingAsSoapTerminalInfo.
     * 
     * @return confId
     */
    public java.lang.String getConfId() {
        return confId;
    }


    /**
     * Sets the confId value for this ESpaceMeetingAsSoapTerminalInfo.
     * 
     * @param confId
     */
    public void setConfId(java.lang.String confId) {
        this.confId = confId;
    }


    /**
     * Gets the userUri value for this ESpaceMeetingAsSoapTerminalInfo.
     * 
     * @return userUri
     */
    public java.lang.String getUserUri() {
        return userUri;
    }


    /**
     * Sets the userUri value for this ESpaceMeetingAsSoapTerminalInfo.
     * 
     * @param userUri
     */
    public void setUserUri(java.lang.String userUri) {
        this.userUri = userUri;
    }


    /**
     * Gets the terminalType value for this ESpaceMeetingAsSoapTerminalInfo.
     * 
     * @return terminalType
     */
    public int getTerminalType() {
        return terminalType;
    }


    /**
     * Sets the terminalType value for this ESpaceMeetingAsSoapTerminalInfo.
     * 
     * @param terminalType
     */
    public void setTerminalType(int terminalType) {
        this.terminalType = terminalType;
    }


    /**
     * Gets the terminalIp value for this ESpaceMeetingAsSoapTerminalInfo.
     * 
     * @return terminalIp
     */
    public java.lang.String getTerminalIp() {
        return terminalIp;
    }


    /**
     * Sets the terminalIp value for this ESpaceMeetingAsSoapTerminalInfo.
     * 
     * @param terminalIp
     */
    public void setTerminalIp(java.lang.String terminalIp) {
        this.terminalIp = terminalIp;
    }


    /**
     * Gets the areaId value for this ESpaceMeetingAsSoapTerminalInfo.
     * 
     * @return areaId
     */
    public java.lang.String getAreaId() {
        return areaId;
    }


    /**
     * Sets the areaId value for this ESpaceMeetingAsSoapTerminalInfo.
     * 
     * @param areaId
     */
    public void setAreaId(java.lang.String areaId) {
        this.areaId = areaId;
    }


    /**
     * Gets the kvList value for this ESpaceMeetingAsSoapTerminalInfo.
     * 
     * @return kvList
     */
    public com.bizconf.vcaasz.soap.conf.ESpaceMeetingAsStringKV[] getKvList() {
        return kvList;
    }


    /**
     * Sets the kvList value for this ESpaceMeetingAsSoapTerminalInfo.
     * 
     * @param kvList
     */
    public void setKvList(com.bizconf.vcaasz.soap.conf.ESpaceMeetingAsStringKV[] kvList) {
        this.kvList = kvList;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof ESpaceMeetingAsSoapTerminalInfo)) return false;
        ESpaceMeetingAsSoapTerminalInfo other = (ESpaceMeetingAsSoapTerminalInfo) obj;
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
            ((this.userUri==null && other.getUserUri()==null) || 
             (this.userUri!=null &&
              this.userUri.equals(other.getUserUri()))) &&
            this.terminalType == other.getTerminalType() &&
            ((this.terminalIp==null && other.getTerminalIp()==null) || 
             (this.terminalIp!=null &&
              this.terminalIp.equals(other.getTerminalIp()))) &&
            ((this.areaId==null && other.getAreaId()==null) || 
             (this.areaId!=null &&
              this.areaId.equals(other.getAreaId()))) &&
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
        if (getUserUri() != null) {
            _hashCode += getUserUri().hashCode();
        }
        _hashCode += getTerminalType();
        if (getTerminalIp() != null) {
            _hashCode += getTerminalIp().hashCode();
        }
        if (getAreaId() != null) {
            _hashCode += getAreaId().hashCode();
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
        new org.apache.axis.description.TypeDesc(ESpaceMeetingAsSoapTerminalInfo.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("eSpaceMeeting", "eSpaceMeeting.as.soap.TerminalInfo"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("confId");
        elemField.setXmlName(new javax.xml.namespace.QName("", "confId"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("userUri");
        elemField.setXmlName(new javax.xml.namespace.QName("", "userUri"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("terminalType");
        elemField.setXmlName(new javax.xml.namespace.QName("", "terminalType"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "int"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("terminalIp");
        elemField.setXmlName(new javax.xml.namespace.QName("", "terminalIp"));
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
