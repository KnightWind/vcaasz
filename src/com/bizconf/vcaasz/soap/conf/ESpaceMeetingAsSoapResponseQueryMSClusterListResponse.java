/**
 * ESpaceMeetingAsSoapResponseQueryMSClusterListResponse.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Nov 19, 2006 (02:31:34 GMT+00:00) WSDL2Java emitter.
 */

package com.bizconf.vcaasz.soap.conf;

public class ESpaceMeetingAsSoapResponseQueryMSClusterListResponse  implements java.io.Serializable {
    private com.bizconf.vcaasz.soap.conf.ESpaceMeetingAsAreaMSCluster[] clusterList;

    public ESpaceMeetingAsSoapResponseQueryMSClusterListResponse() {
    }

    public ESpaceMeetingAsSoapResponseQueryMSClusterListResponse(
           com.bizconf.vcaasz.soap.conf.ESpaceMeetingAsAreaMSCluster[] clusterList) {
           this.clusterList = clusterList;
    }


    /**
     * Gets the clusterList value for this ESpaceMeetingAsSoapResponseQueryMSClusterListResponse.
     * 
     * @return clusterList
     */
    public com.bizconf.vcaasz.soap.conf.ESpaceMeetingAsAreaMSCluster[] getClusterList() {
        return clusterList;
    }


    /**
     * Sets the clusterList value for this ESpaceMeetingAsSoapResponseQueryMSClusterListResponse.
     * 
     * @param clusterList
     */
    public void setClusterList(com.bizconf.vcaasz.soap.conf.ESpaceMeetingAsAreaMSCluster[] clusterList) {
        this.clusterList = clusterList;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof ESpaceMeetingAsSoapResponseQueryMSClusterListResponse)) return false;
        ESpaceMeetingAsSoapResponseQueryMSClusterListResponse other = (ESpaceMeetingAsSoapResponseQueryMSClusterListResponse) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = true && 
            ((this.clusterList==null && other.getClusterList()==null) || 
             (this.clusterList!=null &&
              java.util.Arrays.equals(this.clusterList, other.getClusterList())));
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
        if (getClusterList() != null) {
            for (int i=0;
                 i<java.lang.reflect.Array.getLength(getClusterList());
                 i++) {
                java.lang.Object obj = java.lang.reflect.Array.get(getClusterList(), i);
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
        new org.apache.axis.description.TypeDesc(ESpaceMeetingAsSoapResponseQueryMSClusterListResponse.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("eSpaceMeeting", "eSpaceMeeting.as.soap.response.QueryMSClusterListResponse"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("clusterList");
        elemField.setXmlName(new javax.xml.namespace.QName("", "clusterList"));
        elemField.setXmlType(new javax.xml.namespace.QName("eSpaceMeeting", "eSpaceMeeting.as.AreaMSCluster"));
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
