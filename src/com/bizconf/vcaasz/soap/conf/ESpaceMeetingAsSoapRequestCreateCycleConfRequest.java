/**
 * ESpaceMeetingAsSoapRequestCreateCycleConfRequest.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Nov 19, 2006 (02:31:34 GMT+00:00) WSDL2Java emitter.
 */

package com.bizconf.vcaasz.soap.conf;

public class ESpaceMeetingAsSoapRequestCreateCycleConfRequest  implements java.io.Serializable {
    private com.bizconf.vcaasz.soap.conf.ESpaceMeetingAsSoapToken token;

    private com.bizconf.vcaasz.soap.conf.ESpaceMeetingAsSoapRequester requester;

    private java.lang.String subject;

    private java.lang.String agenda;

    private java.lang.String areaId;

    private int confType;

    private int extConfType;

    private int confCreateType;

    private int cycleType;

    private java.lang.String cycleRule;

    private java.lang.String funcBits;

    private java.lang.String mediaBits;

    private java.lang.String accessCode;

    private int maxMemberAmount;

    private int maxSpokesmanAmount;

    private int minReservedAmount;

    private java.lang.String chairmanPwd;

    private java.lang.String memberPwd;

    private java.lang.String beginDate;

    private java.lang.String endDate;

    private java.lang.String beginTime;

    private int duration;

    private com.bizconf.vcaasz.soap.conf.ESpaceMeetingAsSoapScheduledUser[] users;

    private java.lang.String recordURL;

    public ESpaceMeetingAsSoapRequestCreateCycleConfRequest() {
    }

    public ESpaceMeetingAsSoapRequestCreateCycleConfRequest(
           com.bizconf.vcaasz.soap.conf.ESpaceMeetingAsSoapToken token,
           com.bizconf.vcaasz.soap.conf.ESpaceMeetingAsSoapRequester requester,
           java.lang.String subject,
           java.lang.String agenda,
           java.lang.String areaId,
           int confType,
           int extConfType,
           int confCreateType,
           int cycleType,
           java.lang.String cycleRule,
           java.lang.String funcBits,
           java.lang.String mediaBits,
           java.lang.String accessCode,
           int maxMemberAmount,
           int maxSpokesmanAmount,
           int minReservedAmount,
           java.lang.String chairmanPwd,
           java.lang.String memberPwd,
           java.lang.String beginDate,
           java.lang.String endDate,
           java.lang.String beginTime,
           int duration,
           com.bizconf.vcaasz.soap.conf.ESpaceMeetingAsSoapScheduledUser[] users,
           java.lang.String recordURL) {
           this.token = token;
           this.requester = requester;
           this.subject = subject;
           this.agenda = agenda;
           this.areaId = areaId;
           this.confType = confType;
           this.extConfType = extConfType;
           this.confCreateType = confCreateType;
           this.cycleType = cycleType;
           this.cycleRule = cycleRule;
           this.funcBits = funcBits;
           this.mediaBits = mediaBits;
           this.accessCode = accessCode;
           this.maxMemberAmount = maxMemberAmount;
           this.maxSpokesmanAmount = maxSpokesmanAmount;
           this.minReservedAmount = minReservedAmount;
           this.chairmanPwd = chairmanPwd;
           this.memberPwd = memberPwd;
           this.beginDate = beginDate;
           this.endDate = endDate;
           this.beginTime = beginTime;
           this.duration = duration;
           this.users = users;
           this.recordURL = recordURL;
    }


    /**
     * Gets the token value for this ESpaceMeetingAsSoapRequestCreateCycleConfRequest.
     * 
     * @return token
     */
    public com.bizconf.vcaasz.soap.conf.ESpaceMeetingAsSoapToken getToken() {
        return token;
    }


    /**
     * Sets the token value for this ESpaceMeetingAsSoapRequestCreateCycleConfRequest.
     * 
     * @param token
     */
    public void setToken(com.bizconf.vcaasz.soap.conf.ESpaceMeetingAsSoapToken token) {
        this.token = token;
    }


    /**
     * Gets the requester value for this ESpaceMeetingAsSoapRequestCreateCycleConfRequest.
     * 
     * @return requester
     */
    public com.bizconf.vcaasz.soap.conf.ESpaceMeetingAsSoapRequester getRequester() {
        return requester;
    }


    /**
     * Sets the requester value for this ESpaceMeetingAsSoapRequestCreateCycleConfRequest.
     * 
     * @param requester
     */
    public void setRequester(com.bizconf.vcaasz.soap.conf.ESpaceMeetingAsSoapRequester requester) {
        this.requester = requester;
    }


    /**
     * Gets the subject value for this ESpaceMeetingAsSoapRequestCreateCycleConfRequest.
     * 
     * @return subject
     */
    public java.lang.String getSubject() {
        return subject;
    }


    /**
     * Sets the subject value for this ESpaceMeetingAsSoapRequestCreateCycleConfRequest.
     * 
     * @param subject
     */
    public void setSubject(java.lang.String subject) {
        this.subject = subject;
    }


    /**
     * Gets the agenda value for this ESpaceMeetingAsSoapRequestCreateCycleConfRequest.
     * 
     * @return agenda
     */
    public java.lang.String getAgenda() {
        return agenda;
    }


    /**
     * Sets the agenda value for this ESpaceMeetingAsSoapRequestCreateCycleConfRequest.
     * 
     * @param agenda
     */
    public void setAgenda(java.lang.String agenda) {
        this.agenda = agenda;
    }


    /**
     * Gets the areaId value for this ESpaceMeetingAsSoapRequestCreateCycleConfRequest.
     * 
     * @return areaId
     */
    public java.lang.String getAreaId() {
        return areaId;
    }


    /**
     * Sets the areaId value for this ESpaceMeetingAsSoapRequestCreateCycleConfRequest.
     * 
     * @param areaId
     */
    public void setAreaId(java.lang.String areaId) {
        this.areaId = areaId;
    }


    /**
     * Gets the confType value for this ESpaceMeetingAsSoapRequestCreateCycleConfRequest.
     * 
     * @return confType
     */
    public int getConfType() {
        return confType;
    }


    /**
     * Sets the confType value for this ESpaceMeetingAsSoapRequestCreateCycleConfRequest.
     * 
     * @param confType
     */
    public void setConfType(int confType) {
        this.confType = confType;
    }


    /**
     * Gets the extConfType value for this ESpaceMeetingAsSoapRequestCreateCycleConfRequest.
     * 
     * @return extConfType
     */
    public int getExtConfType() {
        return extConfType;
    }


    /**
     * Sets the extConfType value for this ESpaceMeetingAsSoapRequestCreateCycleConfRequest.
     * 
     * @param extConfType
     */
    public void setExtConfType(int extConfType) {
        this.extConfType = extConfType;
    }


    /**
     * Gets the confCreateType value for this ESpaceMeetingAsSoapRequestCreateCycleConfRequest.
     * 
     * @return confCreateType
     */
    public int getConfCreateType() {
        return confCreateType;
    }


    /**
     * Sets the confCreateType value for this ESpaceMeetingAsSoapRequestCreateCycleConfRequest.
     * 
     * @param confCreateType
     */
    public void setConfCreateType(int confCreateType) {
        this.confCreateType = confCreateType;
    }


    /**
     * Gets the cycleType value for this ESpaceMeetingAsSoapRequestCreateCycleConfRequest.
     * 
     * @return cycleType
     */
    public int getCycleType() {
        return cycleType;
    }


    /**
     * Sets the cycleType value for this ESpaceMeetingAsSoapRequestCreateCycleConfRequest.
     * 
     * @param cycleType
     */
    public void setCycleType(int cycleType) {
        this.cycleType = cycleType;
    }


    /**
     * Gets the cycleRule value for this ESpaceMeetingAsSoapRequestCreateCycleConfRequest.
     * 
     * @return cycleRule
     */
    public java.lang.String getCycleRule() {
        return cycleRule;
    }


    /**
     * Sets the cycleRule value for this ESpaceMeetingAsSoapRequestCreateCycleConfRequest.
     * 
     * @param cycleRule
     */
    public void setCycleRule(java.lang.String cycleRule) {
        this.cycleRule = cycleRule;
    }


    /**
     * Gets the funcBits value for this ESpaceMeetingAsSoapRequestCreateCycleConfRequest.
     * 
     * @return funcBits
     */
    public java.lang.String getFuncBits() {
        return funcBits;
    }


    /**
     * Sets the funcBits value for this ESpaceMeetingAsSoapRequestCreateCycleConfRequest.
     * 
     * @param funcBits
     */
    public void setFuncBits(java.lang.String funcBits) {
        this.funcBits = funcBits;
    }


    /**
     * Gets the mediaBits value for this ESpaceMeetingAsSoapRequestCreateCycleConfRequest.
     * 
     * @return mediaBits
     */
    public java.lang.String getMediaBits() {
        return mediaBits;
    }


    /**
     * Sets the mediaBits value for this ESpaceMeetingAsSoapRequestCreateCycleConfRequest.
     * 
     * @param mediaBits
     */
    public void setMediaBits(java.lang.String mediaBits) {
        this.mediaBits = mediaBits;
    }


    /**
     * Gets the accessCode value for this ESpaceMeetingAsSoapRequestCreateCycleConfRequest.
     * 
     * @return accessCode
     */
    public java.lang.String getAccessCode() {
        return accessCode;
    }


    /**
     * Sets the accessCode value for this ESpaceMeetingAsSoapRequestCreateCycleConfRequest.
     * 
     * @param accessCode
     */
    public void setAccessCode(java.lang.String accessCode) {
        this.accessCode = accessCode;
    }


    /**
     * Gets the maxMemberAmount value for this ESpaceMeetingAsSoapRequestCreateCycleConfRequest.
     * 
     * @return maxMemberAmount
     */
    public int getMaxMemberAmount() {
        return maxMemberAmount;
    }


    /**
     * Sets the maxMemberAmount value for this ESpaceMeetingAsSoapRequestCreateCycleConfRequest.
     * 
     * @param maxMemberAmount
     */
    public void setMaxMemberAmount(int maxMemberAmount) {
        this.maxMemberAmount = maxMemberAmount;
    }


    /**
     * Gets the maxSpokesmanAmount value for this ESpaceMeetingAsSoapRequestCreateCycleConfRequest.
     * 
     * @return maxSpokesmanAmount
     */
    public int getMaxSpokesmanAmount() {
        return maxSpokesmanAmount;
    }


    /**
     * Sets the maxSpokesmanAmount value for this ESpaceMeetingAsSoapRequestCreateCycleConfRequest.
     * 
     * @param maxSpokesmanAmount
     */
    public void setMaxSpokesmanAmount(int maxSpokesmanAmount) {
        this.maxSpokesmanAmount = maxSpokesmanAmount;
    }


    /**
     * Gets the minReservedAmount value for this ESpaceMeetingAsSoapRequestCreateCycleConfRequest.
     * 
     * @return minReservedAmount
     */
    public int getMinReservedAmount() {
        return minReservedAmount;
    }


    /**
     * Sets the minReservedAmount value for this ESpaceMeetingAsSoapRequestCreateCycleConfRequest.
     * 
     * @param minReservedAmount
     */
    public void setMinReservedAmount(int minReservedAmount) {
        this.minReservedAmount = minReservedAmount;
    }


    /**
     * Gets the chairmanPwd value for this ESpaceMeetingAsSoapRequestCreateCycleConfRequest.
     * 
     * @return chairmanPwd
     */
    public java.lang.String getChairmanPwd() {
        return chairmanPwd;
    }


    /**
     * Sets the chairmanPwd value for this ESpaceMeetingAsSoapRequestCreateCycleConfRequest.
     * 
     * @param chairmanPwd
     */
    public void setChairmanPwd(java.lang.String chairmanPwd) {
        this.chairmanPwd = chairmanPwd;
    }


    /**
     * Gets the memberPwd value for this ESpaceMeetingAsSoapRequestCreateCycleConfRequest.
     * 
     * @return memberPwd
     */
    public java.lang.String getMemberPwd() {
        return memberPwd;
    }


    /**
     * Sets the memberPwd value for this ESpaceMeetingAsSoapRequestCreateCycleConfRequest.
     * 
     * @param memberPwd
     */
    public void setMemberPwd(java.lang.String memberPwd) {
        this.memberPwd = memberPwd;
    }


    /**
     * Gets the beginDate value for this ESpaceMeetingAsSoapRequestCreateCycleConfRequest.
     * 
     * @return beginDate
     */
    public java.lang.String getBeginDate() {
        return beginDate;
    }


    /**
     * Sets the beginDate value for this ESpaceMeetingAsSoapRequestCreateCycleConfRequest.
     * 
     * @param beginDate
     */
    public void setBeginDate(java.lang.String beginDate) {
        this.beginDate = beginDate;
    }


    /**
     * Gets the endDate value for this ESpaceMeetingAsSoapRequestCreateCycleConfRequest.
     * 
     * @return endDate
     */
    public java.lang.String getEndDate() {
        return endDate;
    }


    /**
     * Sets the endDate value for this ESpaceMeetingAsSoapRequestCreateCycleConfRequest.
     * 
     * @param endDate
     */
    public void setEndDate(java.lang.String endDate) {
        this.endDate = endDate;
    }


    /**
     * Gets the beginTime value for this ESpaceMeetingAsSoapRequestCreateCycleConfRequest.
     * 
     * @return beginTime
     */
    public java.lang.String getBeginTime() {
        return beginTime;
    }


    /**
     * Sets the beginTime value for this ESpaceMeetingAsSoapRequestCreateCycleConfRequest.
     * 
     * @param beginTime
     */
    public void setBeginTime(java.lang.String beginTime) {
        this.beginTime = beginTime;
    }


    /**
     * Gets the duration value for this ESpaceMeetingAsSoapRequestCreateCycleConfRequest.
     * 
     * @return duration
     */
    public int getDuration() {
        return duration;
    }


    /**
     * Sets the duration value for this ESpaceMeetingAsSoapRequestCreateCycleConfRequest.
     * 
     * @param duration
     */
    public void setDuration(int duration) {
        this.duration = duration;
    }


    /**
     * Gets the users value for this ESpaceMeetingAsSoapRequestCreateCycleConfRequest.
     * 
     * @return users
     */
    public com.bizconf.vcaasz.soap.conf.ESpaceMeetingAsSoapScheduledUser[] getUsers() {
        return users;
    }


    /**
     * Sets the users value for this ESpaceMeetingAsSoapRequestCreateCycleConfRequest.
     * 
     * @param users
     */
    public void setUsers(com.bizconf.vcaasz.soap.conf.ESpaceMeetingAsSoapScheduledUser[] users) {
        this.users = users;
    }


    /**
     * Gets the recordURL value for this ESpaceMeetingAsSoapRequestCreateCycleConfRequest.
     * 
     * @return recordURL
     */
    public java.lang.String getRecordURL() {
        return recordURL;
    }


    /**
     * Sets the recordURL value for this ESpaceMeetingAsSoapRequestCreateCycleConfRequest.
     * 
     * @param recordURL
     */
    public void setRecordURL(java.lang.String recordURL) {
        this.recordURL = recordURL;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof ESpaceMeetingAsSoapRequestCreateCycleConfRequest)) return false;
        ESpaceMeetingAsSoapRequestCreateCycleConfRequest other = (ESpaceMeetingAsSoapRequestCreateCycleConfRequest) obj;
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
            ((this.subject==null && other.getSubject()==null) || 
             (this.subject!=null &&
              this.subject.equals(other.getSubject()))) &&
            ((this.agenda==null && other.getAgenda()==null) || 
             (this.agenda!=null &&
              this.agenda.equals(other.getAgenda()))) &&
            ((this.areaId==null && other.getAreaId()==null) || 
             (this.areaId!=null &&
              this.areaId.equals(other.getAreaId()))) &&
            this.confType == other.getConfType() &&
            this.extConfType == other.getExtConfType() &&
            this.confCreateType == other.getConfCreateType() &&
            this.cycleType == other.getCycleType() &&
            ((this.cycleRule==null && other.getCycleRule()==null) || 
             (this.cycleRule!=null &&
              this.cycleRule.equals(other.getCycleRule()))) &&
            ((this.funcBits==null && other.getFuncBits()==null) || 
             (this.funcBits!=null &&
              this.funcBits.equals(other.getFuncBits()))) &&
            ((this.mediaBits==null && other.getMediaBits()==null) || 
             (this.mediaBits!=null &&
              this.mediaBits.equals(other.getMediaBits()))) &&
            ((this.accessCode==null && other.getAccessCode()==null) || 
             (this.accessCode!=null &&
              this.accessCode.equals(other.getAccessCode()))) &&
            this.maxMemberAmount == other.getMaxMemberAmount() &&
            this.maxSpokesmanAmount == other.getMaxSpokesmanAmount() &&
            this.minReservedAmount == other.getMinReservedAmount() &&
            ((this.chairmanPwd==null && other.getChairmanPwd()==null) || 
             (this.chairmanPwd!=null &&
              this.chairmanPwd.equals(other.getChairmanPwd()))) &&
            ((this.memberPwd==null && other.getMemberPwd()==null) || 
             (this.memberPwd!=null &&
              this.memberPwd.equals(other.getMemberPwd()))) &&
            ((this.beginDate==null && other.getBeginDate()==null) || 
             (this.beginDate!=null &&
              this.beginDate.equals(other.getBeginDate()))) &&
            ((this.endDate==null && other.getEndDate()==null) || 
             (this.endDate!=null &&
              this.endDate.equals(other.getEndDate()))) &&
            ((this.beginTime==null && other.getBeginTime()==null) || 
             (this.beginTime!=null &&
              this.beginTime.equals(other.getBeginTime()))) &&
            this.duration == other.getDuration() &&
            ((this.users==null && other.getUsers()==null) || 
             (this.users!=null &&
              java.util.Arrays.equals(this.users, other.getUsers()))) &&
            ((this.recordURL==null && other.getRecordURL()==null) || 
             (this.recordURL!=null &&
              this.recordURL.equals(other.getRecordURL())));
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
        if (getSubject() != null) {
            _hashCode += getSubject().hashCode();
        }
        if (getAgenda() != null) {
            _hashCode += getAgenda().hashCode();
        }
        if (getAreaId() != null) {
            _hashCode += getAreaId().hashCode();
        }
        _hashCode += getConfType();
        _hashCode += getExtConfType();
        _hashCode += getConfCreateType();
        _hashCode += getCycleType();
        if (getCycleRule() != null) {
            _hashCode += getCycleRule().hashCode();
        }
        if (getFuncBits() != null) {
            _hashCode += getFuncBits().hashCode();
        }
        if (getMediaBits() != null) {
            _hashCode += getMediaBits().hashCode();
        }
        if (getAccessCode() != null) {
            _hashCode += getAccessCode().hashCode();
        }
        _hashCode += getMaxMemberAmount();
        _hashCode += getMaxSpokesmanAmount();
        _hashCode += getMinReservedAmount();
        if (getChairmanPwd() != null) {
            _hashCode += getChairmanPwd().hashCode();
        }
        if (getMemberPwd() != null) {
            _hashCode += getMemberPwd().hashCode();
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
        _hashCode += getDuration();
        if (getUsers() != null) {
            for (int i=0;
                 i<java.lang.reflect.Array.getLength(getUsers());
                 i++) {
                java.lang.Object obj = java.lang.reflect.Array.get(getUsers(), i);
                if (obj != null &&
                    !obj.getClass().isArray()) {
                    _hashCode += obj.hashCode();
                }
            }
        }
        if (getRecordURL() != null) {
            _hashCode += getRecordURL().hashCode();
        }
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(ESpaceMeetingAsSoapRequestCreateCycleConfRequest.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("eSpaceMeeting", "eSpaceMeeting.as.soap.request.CreateCycleConfRequest"));
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
        elemField.setFieldName("subject");
        elemField.setXmlName(new javax.xml.namespace.QName("", "subject"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("agenda");
        elemField.setXmlName(new javax.xml.namespace.QName("", "agenda"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("areaId");
        elemField.setXmlName(new javax.xml.namespace.QName("", "areaId"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("confType");
        elemField.setXmlName(new javax.xml.namespace.QName("", "confType"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "int"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("extConfType");
        elemField.setXmlName(new javax.xml.namespace.QName("", "extConfType"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "int"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("confCreateType");
        elemField.setXmlName(new javax.xml.namespace.QName("", "confCreateType"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "int"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("cycleType");
        elemField.setXmlName(new javax.xml.namespace.QName("", "cycleType"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "int"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("cycleRule");
        elemField.setXmlName(new javax.xml.namespace.QName("", "cycleRule"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("funcBits");
        elemField.setXmlName(new javax.xml.namespace.QName("", "funcBits"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("mediaBits");
        elemField.setXmlName(new javax.xml.namespace.QName("", "mediaBits"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("accessCode");
        elemField.setXmlName(new javax.xml.namespace.QName("", "accessCode"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("maxMemberAmount");
        elemField.setXmlName(new javax.xml.namespace.QName("", "maxMemberAmount"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "int"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("maxSpokesmanAmount");
        elemField.setXmlName(new javax.xml.namespace.QName("", "maxSpokesmanAmount"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "int"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("minReservedAmount");
        elemField.setXmlName(new javax.xml.namespace.QName("", "minReservedAmount"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "int"));
        elemField.setNillable(false);
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
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("duration");
        elemField.setXmlName(new javax.xml.namespace.QName("", "duration"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "int"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("users");
        elemField.setXmlName(new javax.xml.namespace.QName("", "users"));
        elemField.setXmlType(new javax.xml.namespace.QName("eSpaceMeeting", "eSpaceMeeting.as.soap.ScheduledUser"));
        elemField.setNillable(false);
        elemField.setItemQName(new javax.xml.namespace.QName("", "item"));
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("recordURL");
        elemField.setXmlName(new javax.xml.namespace.QName("", "recordURL"));
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
