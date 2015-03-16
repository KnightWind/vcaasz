/**
 * ESpaceMeetingAsAreaMSCluster.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Nov 19, 2006 (02:31:34 GMT+00:00) WSDL2Java emitter.
 */

package com.bizconf.vcaasz.soap.conf;

public class ESpaceMeetingAsAreaMSCluster  implements java.io.Serializable {
    private java.lang.String clusterId;

    private java.lang.String areaId;

    private java.lang.String name;

    private java.lang.String msList;

    private java.lang.String extMsList;

    private int clusterType;

    private int isDefault;

    public ESpaceMeetingAsAreaMSCluster() {
    }

    public ESpaceMeetingAsAreaMSCluster(
           java.lang.String clusterId,
           java.lang.String areaId,
           java.lang.String name,
           java.lang.String msList,
           java.lang.String extMsList,
           int clusterType,
           int isDefault) {
           this.clusterId = clusterId;
           this.areaId = areaId;
           this.name = name;
           this.msList = msList;
           this.extMsList = extMsList;
           this.clusterType = clusterType;
           this.isDefault = isDefault;
    }


    /**
     * Gets the clusterId value for this ESpaceMeetingAsAreaMSCluster.
     * 
     * @return clusterId
     */
    public java.lang.String getClusterId() {
        return clusterId;
    }


    /**
     * Sets the clusterId value for this ESpaceMeetingAsAreaMSCluster.
     * 
     * @param clusterId
     */
    public void setClusterId(java.lang.String clusterId) {
        this.clusterId = clusterId;
    }


    /**
     * Gets the areaId value for this ESpaceMeetingAsAreaMSCluster.
     * 
     * @return areaId
     */
    public java.lang.String getAreaId() {
        return areaId;
    }


    /**
     * Sets the areaId value for this ESpaceMeetingAsAreaMSCluster.
     * 
     * @param areaId
     */
    public void setAreaId(java.lang.String areaId) {
        this.areaId = areaId;
    }


    /**
     * Gets the name value for this ESpaceMeetingAsAreaMSCluster.
     * 
     * @return name
     */
    public java.lang.String getName() {
        return name;
    }


    /**
     * Sets the name value for this ESpaceMeetingAsAreaMSCluster.
     * 
     * @param name
     */
    public void setName(java.lang.String name) {
        this.name = name;
    }


    /**
     * Gets the msList value for this ESpaceMeetingAsAreaMSCluster.
     * 
     * @return msList
     */
    public java.lang.String getMsList() {
        return msList;
    }


    /**
     * Sets the msList value for this ESpaceMeetingAsAreaMSCluster.
     * 
     * @param msList
     */
    public void setMsList(java.lang.String msList) {
        this.msList = msList;
    }


    /**
     * Gets the extMsList value for this ESpaceMeetingAsAreaMSCluster.
     * 
     * @return extMsList
     */
    public java.lang.String getExtMsList() {
        return extMsList;
    }


    /**
     * Sets the extMsList value for this ESpaceMeetingAsAreaMSCluster.
     * 
     * @param extMsList
     */
    public void setExtMsList(java.lang.String extMsList) {
        this.extMsList = extMsList;
    }


    /**
     * Gets the clusterType value for this ESpaceMeetingAsAreaMSCluster.
     * 
     * @return clusterType
     */
    public int getClusterType() {
        return clusterType;
    }


    /**
     * Sets the clusterType value for this ESpaceMeetingAsAreaMSCluster.
     * 
     * @param clusterType
     */
    public void setClusterType(int clusterType) {
        this.clusterType = clusterType;
    }


    /**
     * Gets the isDefault value for this ESpaceMeetingAsAreaMSCluster.
     * 
     * @return isDefault
     */
    public int getIsDefault() {
        return isDefault;
    }


    /**
     * Sets the isDefault value for this ESpaceMeetingAsAreaMSCluster.
     * 
     * @param isDefault
     */
    public void setIsDefault(int isDefault) {
        this.isDefault = isDefault;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof ESpaceMeetingAsAreaMSCluster)) return false;
        ESpaceMeetingAsAreaMSCluster other = (ESpaceMeetingAsAreaMSCluster) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = true && 
            ((this.clusterId==null && other.getClusterId()==null) || 
             (this.clusterId!=null &&
              this.clusterId.equals(other.getClusterId()))) &&
            ((this.areaId==null && other.getAreaId()==null) || 
             (this.areaId!=null &&
              this.areaId.equals(other.getAreaId()))) &&
            ((this.name==null && other.getName()==null) || 
             (this.name!=null &&
              this.name.equals(other.getName()))) &&
            ((this.msList==null && other.getMsList()==null) || 
             (this.msList!=null &&
              this.msList.equals(other.getMsList()))) &&
            ((this.extMsList==null && other.getExtMsList()==null) || 
             (this.extMsList!=null &&
              this.extMsList.equals(other.getExtMsList()))) &&
            this.clusterType == other.getClusterType() &&
            this.isDefault == other.getIsDefault();
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
        if (getClusterId() != null) {
            _hashCode += getClusterId().hashCode();
        }
        if (getAreaId() != null) {
            _hashCode += getAreaId().hashCode();
        }
        if (getName() != null) {
            _hashCode += getName().hashCode();
        }
        if (getMsList() != null) {
            _hashCode += getMsList().hashCode();
        }
        if (getExtMsList() != null) {
            _hashCode += getExtMsList().hashCode();
        }
        _hashCode += getClusterType();
        _hashCode += getIsDefault();
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(ESpaceMeetingAsAreaMSCluster.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("eSpaceMeeting", "eSpaceMeeting.as.AreaMSCluster"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("clusterId");
        elemField.setXmlName(new javax.xml.namespace.QName("", "clusterId"));
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
        elemField.setFieldName("name");
        elemField.setXmlName(new javax.xml.namespace.QName("", "name"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("msList");
        elemField.setXmlName(new javax.xml.namespace.QName("", "msList"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("extMsList");
        elemField.setXmlName(new javax.xml.namespace.QName("", "extMsList"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("clusterType");
        elemField.setXmlName(new javax.xml.namespace.QName("", "clusterType"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "int"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("isDefault");
        elemField.setXmlName(new javax.xml.namespace.QName("", "isDefault"));
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
