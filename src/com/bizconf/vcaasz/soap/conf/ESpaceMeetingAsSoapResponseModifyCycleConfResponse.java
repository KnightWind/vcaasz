/**
 * ESpaceMeetingAsSoapResponseModifyCycleConfResponse.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Nov 19, 2006 (02:31:34 GMT+00:00) WSDL2Java emitter.
 */

package com.bizconf.vcaasz.soap.conf;

public class ESpaceMeetingAsSoapResponseModifyCycleConfResponse  implements java.io.Serializable {
    private java.lang.String cycleId;

    private java.lang.String chairmanPwd;

    private java.lang.String memberPwd;

    private java.lang.String[] unModifiedDate;

    private java.lang.String beginDate;

    private java.lang.String endDate;

    private java.lang.String beginTime;

    public ESpaceMeetingAsSoapResponseModifyCycleConfResponse() {
    }

    public ESpaceMeetingAsSoapResponseModifyCycleConfResponse(
           java.lang.String cycleId,
           java.lang.String chairmanPwd,
           java.lang.String memberPwd,
           java.lang.String[] unModifiedDate,
           java.lang.String beginDate,
           java.lang.String endDate,
           java.lang.String beginTime) {
           this.cycleId = cycleId;
           this.chairmanPwd = chairmanPwd;
           this.memberPwd = memberPwd;
           this.unModifiedDate = unModifiedDate;
           this.beginDate = beginDate;
           this.endDate = endDate;
           this.beginTime = beginTime;
    }


    /**
     * Gets the cycleId value for this ESpaceMeetingAsSoapResponseModifyCycleConfResponse.
     * 
     * @return cycleId
     */
    public java.lang.String getCycleId() {
        return cycleId;
    }


    /**
     * Sets the cycleId value for this ESpaceMeetingAsSoapResponseModifyCycleConfResponse.
     * 
     * @param cycleId
     */
    public void setCycleId(java.lang.String cycleId) {
        this.cycleId = cycleId;
    }


    /**
     * Gets the chairmanPwd value for this ESpaceMeetingAsSoapResponseModifyCycleConfResponse.
     * 
     * @return chairmanPwd
     */
    public java.lang.String getChairmanPwd() {
        return chairmanPwd;
    }


    /**
     * Sets the chairmanPwd value for this ESpaceMeetingAsSoapResponseModifyCycleConfResponse.
     * 
     * @param chairmanPwd
     */
    public void setChairmanPwd(java.lang.String chairmanPwd) {
        this.chairmanPwd = chairmanPwd;
    }


    /**
     * Gets the memberPwd value for this ESpaceMeetingAsSoapResponseModifyCycleConfResponse.
     * 
     * @return memberPwd
     */
    public java.lang.String getMemberPwd() {
        return memberPwd;
    }


    /**
     * Sets the memberPwd value for this ESpaceMeetingAsSoapResponseModifyCycleConfResponse.
     * 
     * @param memberPwd
     */
    public void setMemberPwd(java.lang.String memberPwd) {
        this.memberPwd = memberPwd;
    }


    /**
     * Gets the unModifiedDate value for this ESpaceMeetingAsSoapResponseModifyCycleConfResponse.
     * 
     * @return unModifiedDate
     */
    public java.lang.String[] getUnModifiedDate() {
        return unModifiedDate;
    }


    /**
     * Sets the unModifiedDate value for this ESpaceMeetingAsSoapResponseModifyCycleConfResponse.
     * 
     * @param unModifiedDate
     */
    public void setUnModifiedDate(java.lang.String[] unModifiedDate) {
        this.unModifiedDate = unModifiedDate;
    }


    /**
     * Gets the beginDate value for this ESpaceMeetingAsSoapResponseModifyCycleConfResponse.
     * 
     * @return beginDate
     */
    public java.lang.String getBeginDate() {
        return beginDate;
    }


    /**
     * Sets the beginDate value for this ESpaceMeetingAsSoapResponseModifyCycleConfResponse.
     * 
     * @param beginDate
     */
    public void setBeginDate(java.lang.String beginDate) {
        this.beginDate = beginDate;
    }


    /**
     * Gets the endDate value for this ESpaceMeetingAsSoapResponseModifyCycleConfResponse.
     * 
     * @return endDate
     */
    public java.lang.String getEndDate() {
        return endDate;
    }


    /**
     * Sets the endDate value for this ESpaceMeetingAsSoapResponseModifyCycleConfResponse.
     * 
     * @param endDate
     */
    public void setEndDate(java.lang.String endDate) {
        this.endDate = endDate;
    }


    /**
     * Gets the beginTime value for this ESpaceMeetingAsSoapResponseModifyCycleConfResponse.
     * 
     * @return beginTime
     */
    public java.lang.String getBeginTime() {
        return beginTime;
    }


    /**
     * Sets the beginTime value for this ESpaceMeetingAsSoapResponseModifyCycleConfResponse.
     * 
     * @param beginTime
     */
    public void setBeginTime(java.lang.String beginTime) {
        this.beginTime = beginTime;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof ESpaceMeetingAsSoapResponseModifyCycleConfResponse)) return false;
        ESpaceMeetingAsSoapResponseModifyCycleConfResponse other = (ESpaceMeetingAsSoapResponseModifyCycleConfResponse) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = true && 
            ((this.cycleId==null && other.getCycleId()==null) || 
             (this.cycleId!=null &&
              this.cycleId.equals(other.getCycleId()))) &&
            ((this.chairmanPwd==null && other.getChairmanPwd()==null) || 
             (this.chairmanPwd!=null &&
              this.chairmanPwd.equals(other.getChairmanPwd()))) &&
            ((this.memberPwd==null && other.getMemberPwd()==null) || 
             (this.memberPwd!=null &&
              this.memberPwd.equals(other.getMemberPwd()))) &&
            ((this.unModifiedDate==null && other.getUnModifiedDate()==null) || 
             (this.unModifiedDate!=null &&
              java.util.Arrays.equals(this.unModifiedDate, other.getUnModifiedDate()))) &&
            ((this.beginDate==null && other.getBeginDate()==null) || 
             (this.beginDate!=null &&
              this.beginDate.equals(other.getBeginDate()))) &&
            ((this.endDate==null && other.getEndDate()==null) || 
             (this.endDate!=null &&
              this.endDate.equals(other.getEndDate()))) &&
            ((this.beginTime==null && other.getBeginTime()==null) || 
             (this.beginTime!=null &&
              this.beginTime.equals(other.getBeginTime())));
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
        if (getCycleId() != null) {
            _hashCode += getCycleId().hashCode();
        }
        if (getChairmanPwd() != null) {
            _hashCode += getChairmanPwd().hashCode();
        }
        if (getMemberPwd() != null) {
            _hashCode += getMemberPwd().hashCode();
        }
        if (getUnModifiedDate() != null) {
            for (int i=0;
                 i<java.lang.reflect.Array.getLength(getUnModifiedDate());
                 i++) {
                java.lang.Object obj = java.lang.reflect.Array.get(getUnModifiedDate(), i);
                if (obj != null &&
                    !obj.getClass().isArray()) {
                    _hashCode += obj.hashCode();
                }
            }
        }
        if (getBeginDate() != null) {
            _hashCode += getBeginDate().hashCode();
        }
        if (getEndDate() != null) {
            _hashCode += getEndDate().hashCode();
        }
        if (getBeginTime() != null) {
            _hashCode += getBeginTime().hashCode();
        }
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(ESpaceMeetingAsSoapResponseModifyCycleConfResponse.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("eSpaceMeeting", "eSpaceMeeting.as.soap.response.ModifyCycleConfResponse"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("cycleId");
        elemField.setXmlName(new javax.xml.namespace.QName("", "cycleId"));
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
        elemField.setFieldName("unModifiedDate");
        elemField.setXmlName(new javax.xml.namespace.QName("", "unModifiedDate"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(false);
        elemField.setItemQName(new javax.xml.namespace.QName("", "item"));
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("beginDate");
        elemField.setXmlName(new javax.xml.namespace.QName("", "beginDate"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("endDate");
        elemField.setXmlName(new javax.xml.namespace.QName("", "endDate"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("beginTime");
        elemField.setXmlName(new javax.xml.namespace.QName("", "beginTime"));
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
