/**
 * ESpaceMeetingAsSoapResult.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Nov 19, 2006 (02:31:34 GMT+00:00) WSDL2Java emitter.
 */

package com.bizconf.vcaasz.soap.conf;

public class ESpaceMeetingAsSoapResult  implements java.io.Serializable {
    private java.lang.String requestId;

    private int errCode;

    private java.lang.String[] args;

    private com.bizconf.vcaasz.soap.conf.ESpaceMeetingAsStringKV[] kwargs;

    public ESpaceMeetingAsSoapResult() {
    }

    public ESpaceMeetingAsSoapResult(
           java.lang.String requestId,
           int errCode,
           java.lang.String[] args,
           com.bizconf.vcaasz.soap.conf.ESpaceMeetingAsStringKV[] kwargs) {
           this.requestId = requestId;
           this.errCode = errCode;
           this.args = args;
           this.kwargs = kwargs;
    }


    /**
     * Gets the requestId value for this ESpaceMeetingAsSoapResult.
     * 
     * @return requestId
     */
    public java.lang.String getRequestId() {
        return requestId;
    }


    /**
     * Sets the requestId value for this ESpaceMeetingAsSoapResult.
     * 
     * @param requestId
     */
    public void setRequestId(java.lang.String requestId) {
        this.requestId = requestId;
    }


    /**
     * Gets the errCode value for this ESpaceMeetingAsSoapResult.
     * 
     * @return errCode
     */
    public int getErrCode() {
        return errCode;
    }


    /**
     * Sets the errCode value for this ESpaceMeetingAsSoapResult.
     * 
     * @param errCode
     */
    public void setErrCode(int errCode) {
        this.errCode = errCode;
    }


    /**
     * Gets the args value for this ESpaceMeetingAsSoapResult.
     * 
     * @return args
     */
    public java.lang.String[] getArgs() {
        return args;
    }


    /**
     * Sets the args value for this ESpaceMeetingAsSoapResult.
     * 
     * @param args
     */
    public void setArgs(java.lang.String[] args) {
        this.args = args;
    }


    /**
     * Gets the kwargs value for this ESpaceMeetingAsSoapResult.
     * 
     * @return kwargs
     */
    public com.bizconf.vcaasz.soap.conf.ESpaceMeetingAsStringKV[] getKwargs() {
        return kwargs;
    }


    /**
     * Sets the kwargs value for this ESpaceMeetingAsSoapResult.
     * 
     * @param kwargs
     */
    public void setKwargs(com.bizconf.vcaasz.soap.conf.ESpaceMeetingAsStringKV[] kwargs) {
        this.kwargs = kwargs;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof ESpaceMeetingAsSoapResult)) return false;
        ESpaceMeetingAsSoapResult other = (ESpaceMeetingAsSoapResult) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = true && 
            ((this.requestId==null && other.getRequestId()==null) || 
             (this.requestId!=null &&
              this.requestId.equals(other.getRequestId()))) &&
            this.errCode == other.getErrCode() &&
            ((this.args==null && other.getArgs()==null) || 
             (this.args!=null &&
              java.util.Arrays.equals(this.args, other.getArgs()))) &&
            ((this.kwargs==null && other.getKwargs()==null) || 
             (this.kwargs!=null &&
              java.util.Arrays.equals(this.kwargs, other.getKwargs())));
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
        if (getRequestId() != null) {
            _hashCode += getRequestId().hashCode();
        }
        _hashCode += getErrCode();
        if (getArgs() != null) {
            for (int i=0;
                 i<java.lang.reflect.Array.getLength(getArgs());
                 i++) {
                java.lang.Object obj = java.lang.reflect.Array.get(getArgs(), i);
                if (obj != null &&
                    !obj.getClass().isArray()) {
                    _hashCode += obj.hashCode();
                }
            }
        }
        if (getKwargs() != null) {
            for (int i=0;
                 i<java.lang.reflect.Array.getLength(getKwargs());
                 i++) {
                java.lang.Object obj = java.lang.reflect.Array.get(getKwargs(), i);
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
        new org.apache.axis.description.TypeDesc(ESpaceMeetingAsSoapResult.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("eSpaceMeeting", "eSpaceMeeting.as.soap.Result"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("requestId");
        elemField.setXmlName(new javax.xml.namespace.QName("", "requestId"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("errCode");
        elemField.setXmlName(new javax.xml.namespace.QName("", "errCode"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "int"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("args");
        elemField.setXmlName(new javax.xml.namespace.QName("", "args"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(false);
        elemField.setItemQName(new javax.xml.namespace.QName("", "item"));
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("kwargs");
        elemField.setXmlName(new javax.xml.namespace.QName("", "kwargs"));
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
