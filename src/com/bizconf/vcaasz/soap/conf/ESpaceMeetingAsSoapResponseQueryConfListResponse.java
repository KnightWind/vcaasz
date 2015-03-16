/**
 * ESpaceMeetingAsSoapResponseQueryConfListResponse.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Nov 19, 2006 (02:31:34 GMT+00:00) WSDL2Java emitter.
 */

package com.bizconf.vcaasz.soap.conf;

public class ESpaceMeetingAsSoapResponseQueryConfListResponse  implements java.io.Serializable {
    private com.bizconf.vcaasz.soap.conf.ESpaceMeetingAsSoapConfInfo[] confInfos;

    private int totalCount;

    public ESpaceMeetingAsSoapResponseQueryConfListResponse() {
    }

    public ESpaceMeetingAsSoapResponseQueryConfListResponse(
           com.bizconf.vcaasz.soap.conf.ESpaceMeetingAsSoapConfInfo[] confInfos,
           int totalCount) {
           this.confInfos = confInfos;
           this.totalCount = totalCount;
    }


    /**
     * Gets the confInfos value for this ESpaceMeetingAsSoapResponseQueryConfListResponse.
     * 
     * @return confInfos
     */
    public com.bizconf.vcaasz.soap.conf.ESpaceMeetingAsSoapConfInfo[] getConfInfos() {
        return confInfos;
    }


    /**
     * Sets the confInfos value for this ESpaceMeetingAsSoapResponseQueryConfListResponse.
     * 
     * @param confInfos
     */
    public void setConfInfos(com.bizconf.vcaasz.soap.conf.ESpaceMeetingAsSoapConfInfo[] confInfos) {
        this.confInfos = confInfos;
    }


    /**
     * Gets the totalCount value for this ESpaceMeetingAsSoapResponseQueryConfListResponse.
     * 
     * @return totalCount
     */
    public int getTotalCount() {
        return totalCount;
    }


    /**
     * Sets the totalCount value for this ESpaceMeetingAsSoapResponseQueryConfListResponse.
     * 
     * @param totalCount
     */
    public void setTotalCount(int totalCount) {
        this.totalCount = totalCount;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof ESpaceMeetingAsSoapResponseQueryConfListResponse)) return false;
        ESpaceMeetingAsSoapResponseQueryConfListResponse other = (ESpaceMeetingAsSoapResponseQueryConfListResponse) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = true && 
            ((this.confInfos==null && other.getConfInfos()==null) || 
             (this.confInfos!=null &&
              java.util.Arrays.equals(this.confInfos, other.getConfInfos()))) &&
            this.totalCount == other.getTotalCount();
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
        if (getConfInfos() != null) {
            for (int i=0;
                 i<java.lang.reflect.Array.getLength(getConfInfos());
                 i++) {
                java.lang.Object obj = java.lang.reflect.Array.get(getConfInfos(), i);
                if (obj != null &&
                    !obj.getClass().isArray()) {
                    _hashCode += obj.hashCode();
                }
            }
        }
        _hashCode += getTotalCount();
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(ESpaceMeetingAsSoapResponseQueryConfListResponse.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("eSpaceMeeting", "eSpaceMeeting.as.soap.response.QueryConfListResponse"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("confInfos");
        elemField.setXmlName(new javax.xml.namespace.QName("", "confInfos"));
        elemField.setXmlType(new javax.xml.namespace.QName("eSpaceMeeting", "eSpaceMeeting.as.soap.ConfInfo"));
        elemField.setNillable(false);
        elemField.setItemQName(new javax.xml.namespace.QName("", "item"));
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("totalCount");
        elemField.setXmlName(new javax.xml.namespace.QName("", "totalCount"));
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
