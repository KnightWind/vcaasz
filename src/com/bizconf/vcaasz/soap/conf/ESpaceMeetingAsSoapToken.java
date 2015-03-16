/**
 * ESpaceMeetingAsSoapToken.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Nov 19, 2006 (02:31:34 GMT+00:00) WSDL2Java emitter.
 */

package com.bizconf.vcaasz.soap.conf;

public class ESpaceMeetingAsSoapToken  implements java.io.Serializable {
    private java.lang.String requestId;

    private java.lang.String timezone;

    private long timestamp;

    private java.lang.String appkey;

    private java.lang.String[] args;

    private com.bizconf.vcaasz.soap.conf.ESpaceMeetingAsStringKV[] kwargs;

    public ESpaceMeetingAsSoapToken() {
    }

    public ESpaceMeetingAsSoapToken(
           java.lang.String requestId,
           java.lang.String timezone,
           long timestamp,
           java.lang.String appkey,
           java.lang.String[] args,
           com.bizconf.vcaasz.soap.conf.ESpaceMeetingAsStringKV[] kwargs) {
           this.requestId = requestId;
           this.timezone = timezone;
           this.timestamp = timestamp;
           this.appkey = appkey;
           this.args = args;
           this.kwargs = kwargs;
    }


    /**
     * Gets the requestId value for this ESpaceMeetingAsSoapToken.
     * 
     * @return requestId
     */
    public java.lang.String getRequestId() {
        return requestId;
    }


    /**
     * Sets the requestId value for this ESpaceMeetingAsSoapToken.
     * 
     * @param requestId
     */
    public void setRequestId(java.lang.String requestId) {
        this.requestId = requestId;
    }


    /**
     * Gets the timezone value for this ESpaceMeetingAsSoapToken.
     * 
     * @return timezone
     */
    public java.lang.String getTimezone() {
        return timezone;
    }


    /**
     * Sets the timezone value for this ESpaceMeetingAsSoapToken.
     * 
     * @param timezone
     */
    public void setTimezone(java.lang.String timezone) {
        this.timezone = timezone;
    }


    /**
     * Gets the timestamp value for this ESpaceMeetingAsSoapToken.
     * 
     * @return timestamp
     */
    public long getTimestamp() {
        return timestamp;
    }


    /**
     * Sets the timestamp value for this ESpaceMeetingAsSoapToken.
     * 
     * @param timestamp
     */
    public void setTimestamp(long timestamp) {
        this.timestamp = timestamp;
    }


    /**
     * Gets the appkey value for this ESpaceMeetingAsSoapToken.
     * 
     * @return appkey
     */
    public java.lang.String getAppkey() {
        return appkey;
    }


    /**
     * Sets the appkey value for this ESpaceMeetingAsSoapToken.
     * 
     * @param appkey
     */
    public void setAppkey(java.lang.String appkey) {
        this.appkey = appkey;
    }


    /**
     * Gets the args value for this ESpaceMeetingAsSoapToken.
     * 
     * @return args
     */
    public java.lang.String[] getArgs() {
        return args;
    }


    /**
     * Sets the args value for this ESpaceMeetingAsSoapToken.
     * 
     * @param args
     */
    public void setArgs(java.lang.String[] args) {
        this.args = args;
    }


    /**
     * Gets the kwargs value for this ESpaceMeetingAsSoapToken.
     * 
     * @return kwargs
     */
    public com.bizconf.vcaasz.soap.conf.ESpaceMeetingAsStringKV[] getKwargs() {
        return kwargs;
    }


    /**
     * Sets the kwargs value for this ESpaceMeetingAsSoapToken.
     * 
     * @param kwargs
     */
    public void setKwargs(com.bizconf.vcaasz.soap.conf.ESpaceMeetingAsStringKV[] kwargs) {
        this.kwargs = kwargs;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof ESpaceMeetingAsSoapToken)) return false;
        ESpaceMeetingAsSoapToken other = (ESpaceMeetingAsSoapToken) obj;
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
            ((this.timezone==null && other.getTimezone()==null) || 
             (this.timezone!=null &&
              this.timezone.equals(other.getTimezone()))) &&
            this.timestamp == other.getTimestamp() &&
            ((this.appkey==null && other.getAppkey()==null) || 
             (this.appkey!=null &&
              this.appkey.equals(other.getAppkey()))) &&
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
        if (getTimezone() != null) {
            _hashCode += getTimezone().hashCode();
        }
        _hashCode += new Long(getTimestamp()).hashCode();
        if (getAppkey() != null) {
            _hashCode += getAppkey().hashCode();
        }
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
        new org.apache.axis.description.TypeDesc(ESpaceMeetingAsSoapToken.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("eSpaceMeeting", "eSpaceMeeting.as.soap.Token"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("requestId");
        elemField.setXmlName(new javax.xml.namespace.QName("", "requestId"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("timezone");
        elemField.setXmlName(new javax.xml.namespace.QName("", "timezone"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("timestamp");
        elemField.setXmlName(new javax.xml.namespace.QName("", "timestamp"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "long"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("appkey");
        elemField.setXmlName(new javax.xml.namespace.QName("", "appkey"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(true);
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
