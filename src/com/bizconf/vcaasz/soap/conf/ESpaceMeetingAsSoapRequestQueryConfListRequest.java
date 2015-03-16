/**
 * ESpaceMeetingAsSoapRequestQueryConfListRequest.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Nov 19, 2006 (02:31:34 GMT+00:00) WSDL2Java emitter.
 */

package com.bizconf.vcaasz.soap.conf;

public class ESpaceMeetingAsSoapRequestQueryConfListRequest  implements java.io.Serializable {
    private com.bizconf.vcaasz.soap.conf.ESpaceMeetingAsSoapToken token;

    private com.bizconf.vcaasz.soap.conf.ESpaceMeetingAsSoapRequester requester;

    private java.lang.String querySubject;

    private java.lang.String beginDatetime0;

    private java.lang.String beginDatetimet;

    private int[] confStatusList;

    private boolean isInviter;

    private boolean isInvitee;

    private com.bizconf.vcaasz.soap.conf.ESpaceMeetingAsSoapQueryPage page;

    public ESpaceMeetingAsSoapRequestQueryConfListRequest() {
    }

    public ESpaceMeetingAsSoapRequestQueryConfListRequest(
           com.bizconf.vcaasz.soap.conf.ESpaceMeetingAsSoapToken token,
           com.bizconf.vcaasz.soap.conf.ESpaceMeetingAsSoapRequester requester,
           java.lang.String querySubject,
           java.lang.String beginDatetime0,
           java.lang.String beginDatetimet,
           int[] confStatusList,
           boolean isInviter,
           boolean isInvitee,
           com.bizconf.vcaasz.soap.conf.ESpaceMeetingAsSoapQueryPage page) {
           this.token = token;
           this.requester = requester;
           this.querySubject = querySubject;
           this.beginDatetime0 = beginDatetime0;
           this.beginDatetimet = beginDatetimet;
           this.confStatusList = confStatusList;
           this.isInviter = isInviter;
           this.isInvitee = isInvitee;
           this.page = page;
    }


    /**
     * Gets the token value for this ESpaceMeetingAsSoapRequestQueryConfListRequest.
     * 
     * @return token
     */
    public com.bizconf.vcaasz.soap.conf.ESpaceMeetingAsSoapToken getToken() {
        return token;
    }


    /**
     * Sets the token value for this ESpaceMeetingAsSoapRequestQueryConfListRequest.
     * 
     * @param token
     */
    public void setToken(com.bizconf.vcaasz.soap.conf.ESpaceMeetingAsSoapToken token) {
        this.token = token;
    }


    /**
     * Gets the requester value for this ESpaceMeetingAsSoapRequestQueryConfListRequest.
     * 
     * @return requester
     */
    public com.bizconf.vcaasz.soap.conf.ESpaceMeetingAsSoapRequester getRequester() {
        return requester;
    }


    /**
     * Sets the requester value for this ESpaceMeetingAsSoapRequestQueryConfListRequest.
     * 
     * @param requester
     */
    public void setRequester(com.bizconf.vcaasz.soap.conf.ESpaceMeetingAsSoapRequester requester) {
        this.requester = requester;
    }


    /**
     * Gets the querySubject value for this ESpaceMeetingAsSoapRequestQueryConfListRequest.
     * 
     * @return querySubject
     */
    public java.lang.String getQuerySubject() {
        return querySubject;
    }


    /**
     * Sets the querySubject value for this ESpaceMeetingAsSoapRequestQueryConfListRequest.
     * 
     * @param querySubject
     */
    public void setQuerySubject(java.lang.String querySubject) {
        this.querySubject = querySubject;
    }


    /**
     * Gets the beginDatetime0 value for this ESpaceMeetingAsSoapRequestQueryConfListRequest.
     * 
     * @return beginDatetime0
     */
    public java.lang.String getBeginDatetime0() {
        return beginDatetime0;
    }


    /**
     * Sets the beginDatetime0 value for this ESpaceMeetingAsSoapRequestQueryConfListRequest.
     * 
     * @param beginDatetime0
     */
    public void setBeginDatetime0(java.lang.String beginDatetime0) {
        this.beginDatetime0 = beginDatetime0;
    }


    /**
     * Gets the beginDatetimet value for this ESpaceMeetingAsSoapRequestQueryConfListRequest.
     * 
     * @return beginDatetimet
     */
    public java.lang.String getBeginDatetimet() {
        return beginDatetimet;
    }


    /**
     * Sets the beginDatetimet value for this ESpaceMeetingAsSoapRequestQueryConfListRequest.
     * 
     * @param beginDatetimet
     */
    public void setBeginDatetimet(java.lang.String beginDatetimet) {
        this.beginDatetimet = beginDatetimet;
    }


    /**
     * Gets the confStatusList value for this ESpaceMeetingAsSoapRequestQueryConfListRequest.
     * 
     * @return confStatusList
     */
    public int[] getConfStatusList() {
        return confStatusList;
    }


    /**
     * Sets the confStatusList value for this ESpaceMeetingAsSoapRequestQueryConfListRequest.
     * 
     * @param confStatusList
     */
    public void setConfStatusList(int[] confStatusList) {
        this.confStatusList = confStatusList;
    }


    /**
     * Gets the isInviter value for this ESpaceMeetingAsSoapRequestQueryConfListRequest.
     * 
     * @return isInviter
     */
    public boolean isIsInviter() {
        return isInviter;
    }


    /**
     * Sets the isInviter value for this ESpaceMeetingAsSoapRequestQueryConfListRequest.
     * 
     * @param isInviter
     */
    public void setIsInviter(boolean isInviter) {
        this.isInviter = isInviter;
    }


    /**
     * Gets the isInvitee value for this ESpaceMeetingAsSoapRequestQueryConfListRequest.
     * 
     * @return isInvitee
     */
    public boolean isIsInvitee() {
        return isInvitee;
    }


    /**
     * Sets the isInvitee value for this ESpaceMeetingAsSoapRequestQueryConfListRequest.
     * 
     * @param isInvitee
     */
    public void setIsInvitee(boolean isInvitee) {
        this.isInvitee = isInvitee;
    }


    /**
     * Gets the page value for this ESpaceMeetingAsSoapRequestQueryConfListRequest.
     * 
     * @return page
     */
    public com.bizconf.vcaasz.soap.conf.ESpaceMeetingAsSoapQueryPage getPage() {
        return page;
    }


    /**
     * Sets the page value for this ESpaceMeetingAsSoapRequestQueryConfListRequest.
     * 
     * @param page
     */
    public void setPage(com.bizconf.vcaasz.soap.conf.ESpaceMeetingAsSoapQueryPage page) {
        this.page = page;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof ESpaceMeetingAsSoapRequestQueryConfListRequest)) return false;
        ESpaceMeetingAsSoapRequestQueryConfListRequest other = (ESpaceMeetingAsSoapRequestQueryConfListRequest) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = true && 
            ((this.token==null && other.getToken()==null) || 
             (this.token!=null &&
              this.token.equals(other.getToken()))) &&
            ((this.requester==null && other.getRequester()==null) || 
             (this.requester!=null &&
              this.requester.equals(other.getRequester()))) &&
            ((this.querySubject==null && other.getQuerySubject()==null) || 
             (this.querySubject!=null &&
              this.querySubject.equals(other.getQuerySubject()))) &&
            ((this.beginDatetime0==null && other.getBeginDatetime0()==null) || 
             (this.beginDatetime0!=null &&
              this.beginDatetime0.equals(other.getBeginDatetime0()))) &&
            ((this.beginDatetimet==null && other.getBeginDatetimet()==null) || 
             (this.beginDatetimet!=null &&
              this.beginDatetimet.equals(other.getBeginDatetimet()))) &&
            ((this.confStatusList==null && other.getConfStatusList()==null) || 
             (this.confStatusList!=null &&
              java.util.Arrays.equals(this.confStatusList, other.getConfStatusList()))) &&
            this.isInviter == other.isIsInviter() &&
            this.isInvitee == other.isIsInvitee() &&
            ((this.page==null && other.getPage()==null) || 
             (this.page!=null &&
              this.page.equals(other.getPage())));
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
        if (getToken() != null) {
            _hashCode += getToken().hashCode();
        }
        if (getRequester() != null) {
            _hashCode += getRequester().hashCode();
        }
        if (getQuerySubject() != null) {
            _hashCode += getQuerySubject().hashCode();
        }
        if (getBeginDatetime0() != null) {
            _hashCode += getBeginDatetime0().hashCode();
        }
        if (getBeginDatetimet() != null) {
            _hashCode += getBeginDatetimet().hashCode();
        }
        if (getConfStatusList() != null) {
            for (int i=0;
                 i<java.lang.reflect.Array.getLength(getConfStatusList());
                 i++) {
                java.lang.Object obj = java.lang.reflect.Array.get(getConfStatusList(), i);
                if (obj != null &&
                    !obj.getClass().isArray()) {
                    _hashCode += obj.hashCode();
                }
            }
        }
        _hashCode += (isIsInviter() ? Boolean.TRUE : Boolean.FALSE).hashCode();
        _hashCode += (isIsInvitee() ? Boolean.TRUE : Boolean.FALSE).hashCode();
        if (getPage() != null) {
            _hashCode += getPage().hashCode();
        }
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(ESpaceMeetingAsSoapRequestQueryConfListRequest.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("eSpaceMeeting", "eSpaceMeeting.as.soap.request.QueryConfListRequest"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("token");
        elemField.setXmlName(new javax.xml.namespace.QName("", "token"));
        elemField.setXmlType(new javax.xml.namespace.QName("eSpaceMeeting", "eSpaceMeeting.as.soap.Token"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("requester");
        elemField.setXmlName(new javax.xml.namespace.QName("", "requester"));
        elemField.setXmlType(new javax.xml.namespace.QName("eSpaceMeeting", "eSpaceMeeting.as.soap.Requester"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("querySubject");
        elemField.setXmlName(new javax.xml.namespace.QName("", "querySubject"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("beginDatetime0");
        elemField.setXmlName(new javax.xml.namespace.QName("", "beginDatetime0"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("beginDatetimet");
        elemField.setXmlName(new javax.xml.namespace.QName("", "beginDatetimet"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("confStatusList");
        elemField.setXmlName(new javax.xml.namespace.QName("", "confStatusList"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "int"));
        elemField.setNillable(false);
        elemField.setItemQName(new javax.xml.namespace.QName("", "item"));
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("isInviter");
        elemField.setXmlName(new javax.xml.namespace.QName("", "isInviter"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "boolean"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("isInvitee");
        elemField.setXmlName(new javax.xml.namespace.QName("", "isInvitee"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "boolean"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("page");
        elemField.setXmlName(new javax.xml.namespace.QName("", "page"));
        elemField.setXmlType(new javax.xml.namespace.QName("eSpaceMeeting", "eSpaceMeeting.as.soap.QueryPage"));
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
