/**
 * ESpaceMeetingAsIPSegment.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Nov 19, 2006 (02:31:34 GMT+00:00) WSDL2Java emitter.
 */

package com.bizconf.vcaasz.soap.conf;

public class ESpaceMeetingAsIPSegment  implements java.io.Serializable {
    private java.lang.String ipSegmentId;

    private java.lang.String areaId;

    private java.lang.String enterpriseId;

    private java.lang.String name;

    private java.lang.String beginIp;

    private java.lang.String endIp;

    public ESpaceMeetingAsIPSegment() {
    }

    public ESpaceMeetingAsIPSegment(
           java.lang.String ipSegmentId,
           java.lang.String areaId,
           java.lang.String enterpriseId,
           java.lang.String name,
           java.lang.String beginIp,
           java.lang.String endIp) {
           this.ipSegmentId = ipSegmentId;
           this.areaId = areaId;
           this.enterpriseId = enterpriseId;
           this.name = name;
           this.beginIp = beginIp;
           this.endIp = endIp;
    }


    /**
     * Gets the ipSegmentId value for this ESpaceMeetingAsIPSegment.
     * 
     * @return ipSegmentId
     */
    public java.lang.String getIpSegmentId() {
        return ipSegmentId;
    }


    /**
     * Sets the ipSegmentId value for this ESpaceMeetingAsIPSegment.
     * 
     * @param ipSegmentId
     */
    public void setIpSegmentId(java.lang.String ipSegmentId) {
        this.ipSegmentId = ipSegmentId;
    }


    /**
     * Gets the areaId value for this ESpaceMeetingAsIPSegment.
     * 
     * @return areaId
     */
    public java.lang.String getAreaId() {
        return areaId;
    }


    /**
     * Sets the areaId value for this ESpaceMeetingAsIPSegment.
     * 
     * @param areaId
     */
    public void setAreaId(java.lang.String areaId) {
        this.areaId = areaId;
    }


    /**
     * Gets the enterpriseId value for this ESpaceMeetingAsIPSegment.
     * 
     * @return enterpriseId
     */
    public java.lang.String getEnterpriseId() {
        return enterpriseId;
    }


    /**
     * Sets the enterpriseId value for this ESpaceMeetingAsIPSegment.
     * 
     * @param enterpriseId
     */
    public void setEnterpriseId(java.lang.String enterpriseId) {
        this.enterpriseId = enterpriseId;
    }


    /**
     * Gets the name value for this ESpaceMeetingAsIPSegment.
     * 
     * @return name
     */
    public java.lang.String getName() {
        return name;
    }


    /**
     * Sets the name value for this ESpaceMeetingAsIPSegment.
     * 
     * @param name
     */
    public void setName(java.lang.String name) {
        this.name = name;
    }


    /**
     * Gets the beginIp value for this ESpaceMeetingAsIPSegment.
     * 
     * @return beginIp
     */
    public java.lang.String getBeginIp() {
        return beginIp;
    }


    /**
     * Sets the beginIp value for this ESpaceMeetingAsIPSegment.
     * 
     * @param beginIp
     */
    public void setBeginIp(java.lang.String beginIp) {
        this.beginIp = beginIp;
    }


    /**
     * Gets the endIp value for this ESpaceMeetingAsIPSegment.
     * 
     * @return endIp
     */
    public java.lang.String getEndIp() {
        return endIp;
    }


    /**
     * Sets the endIp value for this ESpaceMeetingAsIPSegment.
     * 
     * @param endIp
     */
    public void setEndIp(java.lang.String endIp) {
        this.endIp = endIp;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof ESpaceMeetingAsIPSegment)) return false;
        ESpaceMeetingAsIPSegment other = (ESpaceMeetingAsIPSegment) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = true && 
            ((this.ipSegmentId==null && other.getIpSegmentId()==null) || 
             (this.ipSegmentId!=null &&
              this.ipSegmentId.equals(other.getIpSegmentId()))) &&
            ((this.areaId==null && other.getAreaId()==null) || 
             (this.areaId!=null &&
              this.areaId.equals(other.getAreaId()))) &&
            ((this.enterpriseId==null && other.getEnterpriseId()==null) || 
             (this.enterpriseId!=null &&
              this.enterpriseId.equals(other.getEnterpriseId()))) &&
            ((this.name==null && other.getName()==null) || 
             (this.name!=null &&
              this.name.equals(other.getName()))) &&
            ((this.beginIp==null && other.getBeginIp()==null) || 
             (this.beginIp!=null &&
              this.beginIp.equals(other.getBeginIp()))) &&
            ((this.endIp==null && other.getEndIp()==null) || 
             (this.endIp!=null &&
              this.endIp.equals(other.getEndIp())));
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
        if (getIpSegmentId() != null) {
            _hashCode += getIpSegmentId().hashCode();
        }
        if (getAreaId() != null) {
            _hashCode += getAreaId().hashCode();
        }
        if (getEnterpriseId() != null) {
            _hashCode += getEnterpriseId().hashCode();
        }
        if (getName() != null) {
            _hashCode += getName().hashCode();
        }
        if (getBeginIp() != null) {
            _hashCode += getBeginIp().hashCode();
        }
        if (getEndIp() != null) {
            _hashCode += getEndIp().hashCode();
        }
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(ESpaceMeetingAsIPSegment.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("eSpaceMeeting", "eSpaceMeeting.as.IPSegment"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("ipSegmentId");
        elemField.setXmlName(new javax.xml.namespace.QName("", "ipSegmentId"));
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
        elemField.setFieldName("beginIp");
        elemField.setXmlName(new javax.xml.namespace.QName("", "beginIp"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("endIp");
        elemField.setXmlName(new javax.xml.namespace.QName("", "endIp"));
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
