/**
 * ESpaceMeetingAsSoapResponseQueryIPSegListResponse.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Nov 19, 2006 (02:31:34 GMT+00:00) WSDL2Java emitter.
 */

package com.bizconf.vcaasz.soap.conf;

public class ESpaceMeetingAsSoapResponseQueryIPSegListResponse  implements java.io.Serializable {
    private com.bizconf.vcaasz.soap.conf.ESpaceMeetingAsIPSegment[] ipsegList;

    public ESpaceMeetingAsSoapResponseQueryIPSegListResponse() {
    }

    public ESpaceMeetingAsSoapResponseQueryIPSegListResponse(
           com.bizconf.vcaasz.soap.conf.ESpaceMeetingAsIPSegment[] ipsegList) {
           this.ipsegList = ipsegList;
    }


    /**
     * Gets the ipsegList value for this ESpaceMeetingAsSoapResponseQueryIPSegListResponse.
     * 
     * @return ipsegList
     */
    public com.bizconf.vcaasz.soap.conf.ESpaceMeetingAsIPSegment[] getIpsegList() {
        return ipsegList;
    }


    /**
     * Sets the ipsegList value for this ESpaceMeetingAsSoapResponseQueryIPSegListResponse.
     * 
     * @param ipsegList
     */
    public void setIpsegList(com.bizconf.vcaasz.soap.conf.ESpaceMeetingAsIPSegment[] ipsegList) {
        this.ipsegList = ipsegList;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof ESpaceMeetingAsSoapResponseQueryIPSegListResponse)) return false;
        ESpaceMeetingAsSoapResponseQueryIPSegListResponse other = (ESpaceMeetingAsSoapResponseQueryIPSegListResponse) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = true && 
            ((this.ipsegList==null && other.getIpsegList()==null) || 
             (this.ipsegList!=null &&
              java.util.Arrays.equals(this.ipsegList, other.getIpsegList())));
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
        if (getIpsegList() != null) {
            for (int i=0;
                 i<java.lang.reflect.Array.getLength(getIpsegList());
                 i++) {
                java.lang.Object obj = java.lang.reflect.Array.get(getIpsegList(), i);
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
        new org.apache.axis.description.TypeDesc(ESpaceMeetingAsSoapResponseQueryIPSegListResponse.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("eSpaceMeeting", "eSpaceMeeting.as.soap.response.QueryIPSegListResponse"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("ipsegList");
        elemField.setXmlName(new javax.xml.namespace.QName("", "ipsegList"));
        elemField.setXmlType(new javax.xml.namespace.QName("eSpaceMeeting", "eSpaceMeeting.as.IPSegment"));
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
