/**
 * ESpaceMeetingAsSoapResponseQueryConfStat1Response.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Nov 19, 2006 (02:31:34 GMT+00:00) WSDL2Java emitter.
 */

package com.bizconf.vcaasz.soap.conf;

public class ESpaceMeetingAsSoapResponseQueryConfStat1Response  implements java.io.Serializable {
    private com.bizconf.vcaasz.soap.conf.ESpaceMeetingAsSoapConfStat1[] confStat1List;

    private int totalCount;

    public ESpaceMeetingAsSoapResponseQueryConfStat1Response() {
    }

    public ESpaceMeetingAsSoapResponseQueryConfStat1Response(
           com.bizconf.vcaasz.soap.conf.ESpaceMeetingAsSoapConfStat1[] confStat1List,
           int totalCount) {
           this.confStat1List = confStat1List;
           this.totalCount = totalCount;
    }


    /**
     * Gets the confStat1List value for this ESpaceMeetingAsSoapResponseQueryConfStat1Response.
     * 
     * @return confStat1List
     */
    public com.bizconf.vcaasz.soap.conf.ESpaceMeetingAsSoapConfStat1[] getConfStat1List() {
        return confStat1List;
    }


    /**
     * Sets the confStat1List value for this ESpaceMeetingAsSoapResponseQueryConfStat1Response.
     * 
     * @param confStat1List
     */
    public void setConfStat1List(com.bizconf.vcaasz.soap.conf.ESpaceMeetingAsSoapConfStat1[] confStat1List) {
        this.confStat1List = confStat1List;
    }


    /**
     * Gets the totalCount value for this ESpaceMeetingAsSoapResponseQueryConfStat1Response.
     * 
     * @return totalCount
     */
    public int getTotalCount() {
        return totalCount;
    }


    /**
     * Sets the totalCount value for this ESpaceMeetingAsSoapResponseQueryConfStat1Response.
     * 
     * @param totalCount
     */
    public void setTotalCount(int totalCount) {
        this.totalCount = totalCount;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof ESpaceMeetingAsSoapResponseQueryConfStat1Response)) return false;
        ESpaceMeetingAsSoapResponseQueryConfStat1Response other = (ESpaceMeetingAsSoapResponseQueryConfStat1Response) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = true && 
            ((this.confStat1List==null && other.getConfStat1List()==null) || 
             (this.confStat1List!=null &&
              java.util.Arrays.equals(this.confStat1List, other.getConfStat1List()))) &&
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
        if (getConfStat1List() != null) {
            for (int i=0;
                 i<java.lang.reflect.Array.getLength(getConfStat1List());
                 i++) {
                java.lang.Object obj = java.lang.reflect.Array.get(getConfStat1List(), i);
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
        new org.apache.axis.description.TypeDesc(ESpaceMeetingAsSoapResponseQueryConfStat1Response.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("eSpaceMeeting", "eSpaceMeeting.as.soap.response.QueryConfStat1Response"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("confStat1List");
        elemField.setXmlName(new javax.xml.namespace.QName("", "confStat1List"));
        elemField.setXmlType(new javax.xml.namespace.QName("eSpaceMeeting", "eSpaceMeeting.as.soap.ConfStat1"));
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
