/**
 * ESpaceMeetingAsSoapQueryPage.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Nov 19, 2006 (02:31:34 GMT+00:00) WSDL2Java emitter.
 */

package com.bizconf.vcaasz.soap.conf;

public class ESpaceMeetingAsSoapQueryPage  implements java.io.Serializable {
    private int currentPage;

    private int pageSize;

    private int orderBy;

    public ESpaceMeetingAsSoapQueryPage() {
    }

    public ESpaceMeetingAsSoapQueryPage(
           int currentPage,
           int pageSize,
           int orderBy) {
           this.currentPage = currentPage;
           this.pageSize = pageSize;
           this.orderBy = orderBy;
    }


    /**
     * Gets the currentPage value for this ESpaceMeetingAsSoapQueryPage.
     * 
     * @return currentPage
     */
    public int getCurrentPage() {
        return currentPage;
    }


    /**
     * Sets the currentPage value for this ESpaceMeetingAsSoapQueryPage.
     * 
     * @param currentPage
     */
    public void setCurrentPage(int currentPage) {
        this.currentPage = currentPage;
    }


    /**
     * Gets the pageSize value for this ESpaceMeetingAsSoapQueryPage.
     * 
     * @return pageSize
     */
    public int getPageSize() {
        return pageSize;
    }


    /**
     * Sets the pageSize value for this ESpaceMeetingAsSoapQueryPage.
     * 
     * @param pageSize
     */
    public void setPageSize(int pageSize) {
        this.pageSize = pageSize;
    }


    /**
     * Gets the orderBy value for this ESpaceMeetingAsSoapQueryPage.
     * 
     * @return orderBy
     */
    public int getOrderBy() {
        return orderBy;
    }


    /**
     * Sets the orderBy value for this ESpaceMeetingAsSoapQueryPage.
     * 
     * @param orderBy
     */
    public void setOrderBy(int orderBy) {
        this.orderBy = orderBy;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof ESpaceMeetingAsSoapQueryPage)) return false;
        ESpaceMeetingAsSoapQueryPage other = (ESpaceMeetingAsSoapQueryPage) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = true && 
            this.currentPage == other.getCurrentPage() &&
            this.pageSize == other.getPageSize() &&
            this.orderBy == other.getOrderBy();
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
        _hashCode += getCurrentPage();
        _hashCode += getPageSize();
        _hashCode += getOrderBy();
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(ESpaceMeetingAsSoapQueryPage.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("eSpaceMeeting", "eSpaceMeeting.as.soap.QueryPage"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("currentPage");
        elemField.setXmlName(new javax.xml.namespace.QName("", "currentPage"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "int"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("pageSize");
        elemField.setXmlName(new javax.xml.namespace.QName("", "pageSize"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "int"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("orderBy");
        elemField.setXmlName(new javax.xml.namespace.QName("", "orderBy"));
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
