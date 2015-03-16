/**
 * ESpaceMeetingAsSoapResponseModifyConfResponse.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Nov 19, 2006 (02:31:34 GMT+00:00) WSDL2Java emitter.
 */

package com.bizconf.vcaasz.soap.conf;

public class ESpaceMeetingAsSoapResponseModifyConfResponse  implements java.io.Serializable {
    private java.lang.String confId;

    private java.lang.String chairmanPwd;

    private java.lang.String memberPwd;

    private java.lang.String beginDatetime;

    private java.lang.String endDatetime;

    public ESpaceMeetingAsSoapResponseModifyConfResponse() {
    }

    public ESpaceMeetingAsSoapResponseModifyConfResponse(
           java.lang.String confId,
           java.lang.String chairmanPwd,
           java.lang.String memberPwd,
           java.lang.String beginDatetime,
           java.lang.String endDatetime) {
           this.confId = confId;
           this.chairmanPwd = chairmanPwd;
           this.memberPwd = memberPwd;
           this.beginDatetime = beginDatetime;
           this.endDatetime = endDatetime;
    }


    /**
     * Gets the confId value for this ESpaceMeetingAsSoapResponseModifyConfResponse.
     * 
     * @return confId
     */
    public java.lang.String getConfId() {
        return confId;
    }


    /**
     * Sets the confId value for this ESpaceMeetingAsSoapResponseModifyConfResponse.
     * 
     * @param confId
     */
    public void setConfId(java.lang.String confId) {
        this.confId = confId;
    }


    /**
     * Gets the chairmanPwd value for this ESpaceMeetingAsSoapResponseModifyConfResponse.
     * 
     * @return chairmanPwd
     */
    public java.lang.String getChairmanPwd() {
        return chairmanPwd;
    }


    /**
     * Sets the chairmanPwd value for this ESpaceMeetingAsSoapResponseModifyConfResponse.
     * 
     * @param chairmanPwd
     */
    public void setChairmanPwd(java.lang.String chairmanPwd) {
        this.chairmanPwd = chairmanPwd;
    }


    /**
     * Gets the memberPwd value for this ESpaceMeetingAsSoapResponseModifyConfResponse.
     * 
     * @return memberPwd
     */
    public java.lang.String getMemberPwd() {
        return memberPwd;
    }


    /**
     * Sets the memberPwd value for this ESpaceMeetingAsSoapResponseModifyConfResponse.
     * 
     * @param memberPwd
     */
    public void setMemberPwd(java.lang.String memberPwd) {
        this.memberPwd = memberPwd;
    }


    /**
     * Gets the beginDatetime value for this ESpaceMeetingAsSoapResponseModifyConfResponse.
     * 
     * @return beginDatetime
     */
    public java.lang.String getBeginDatetime() {
        return beginDatetime;
    }


    /**
     * Sets the beginDatetime value for this ESpaceMeetingAsSoapResponseModifyConfResponse.
     * 
     * @param beginDatetime
     */
    public void setBeginDatetime(java.lang.String beginDatetime) {
        this.beginDatetime = beginDatetime;
    }


    /**
     * Gets the endDatetime value for this ESpaceMeetingAsSoapResponseModifyConfResponse.
     * 
     * @return endDatetime
     */
    public java.lang.String getEndDatetime() {
        return endDatetime;
    }


    /**
     * Sets the endDatetime value for this ESpaceMeetingAsSoapResponseModifyConfResponse.
     * 
     * @param endDatetime
     */
    public void setEndDatetime(java.lang.String endDatetime) {
        this.endDatetime = endDatetime;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof ESpaceMeetingAsSoapResponseModifyConfResponse)) return false;
        ESpaceMeetingAsSoapResponseModifyConfResponse other = (ESpaceMeetingAsSoapResponseModifyConfResponse) obj;
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
            ((this.chairmanPwd==null && other.getChairmanPwd()==null) || 
             (this.chairmanPwd!=null &&
              this.chairmanPwd.equals(other.getChairmanPwd()))) &&
            ((this.memberPwd==null && other.getMemberPwd()==null) || 
             (this.memberPwd!=null &&
              this.memberPwd.equals(other.getMemberPwd()))) &&
            ((this.beginDatetime==null && other.getBeginDatetime()==null) || 
             (this.beginDatetime!=null &&
              this.beginDatetime.equals(other.getBeginDatetime()))) &&
            ((this.endDatetime==null && other.getEndDatetime()==null) || 
             (this.endDatetime!=null &&
              this.endDatetime.equals(other.getEndDatetime())));
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
        if (getChairmanPwd() != null) {
            _hashCode += getChairmanPwd().hashCode();
        }
        if (getMemberPwd() != null) {
            _hashCode += getMemberPwd().hashCode();
        }
        if (getBeginDatetime() != null) {
            _hashCode += getBeginDatetime().hashCode();
        }
        if (getEndDatetime() != null) {
            _hashCode += getEndDatetime().hashCode();
        }
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(ESpaceMeetingAsSoapResponseModifyConfResponse.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("eSpaceMeeting", "eSpaceMeeting.as.soap.response.ModifyConfResponse"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("confId");
        elemField.setXmlName(new javax.xml.namespace.QName("", "confId"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("chairmanPwd");
        elemField.setXmlName(new javax.xml.namespace.QName("", "chairmanPwd"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("memberPwd");
        elemField.setXmlName(new javax.xml.namespace.QName("", "memberPwd"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("beginDatetime");
        elemField.setXmlName(new javax.xml.namespace.QName("", "beginDatetime"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("endDatetime");
        elemField.setXmlName(new javax.xml.namespace.QName("", "endDatetime"));
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
