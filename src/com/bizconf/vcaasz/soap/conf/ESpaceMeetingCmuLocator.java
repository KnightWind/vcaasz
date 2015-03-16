/**
 * ESpaceMeetingCmuLocator.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Nov 19, 2006 (02:31:34 GMT+00:00) WSDL2Java emitter.
 */

package com.bizconf.vcaasz.soap.conf;

public class ESpaceMeetingCmuLocator extends org.apache.axis.client.Service implements com.bizconf.vcaasz.soap.conf.ESpaceMeetingCmu {

    public ESpaceMeetingCmuLocator() {
    }


    public ESpaceMeetingCmuLocator(org.apache.axis.EngineConfiguration config) {
        super(config);
    }

    public ESpaceMeetingCmuLocator(java.lang.String wsdlLoc, javax.xml.namespace.QName sName) throws javax.xml.rpc.ServiceException {
        super(wsdlLoc, sName);
    }

    // Use to get a proxy class for ESpaceMeetingAsSoapConfManagementService
    private java.lang.String ESpaceMeetingAsSoapConfManagementService_address = "http://127.0.0.1:8996/eSpaceMeeting/ConfManagementService";

    public java.lang.String getESpaceMeetingAsSoapConfManagementServiceAddress() {
        return ESpaceMeetingAsSoapConfManagementService_address;
    }

    // The WSDD service name defaults to the port name.
    private java.lang.String ESpaceMeetingAsSoapConfManagementServiceWSDDServiceName = "eSpaceMeeting.as.soap.ConfManagementService";

    public java.lang.String getESpaceMeetingAsSoapConfManagementServiceWSDDServiceName() {
        return ESpaceMeetingAsSoapConfManagementServiceWSDDServiceName;
    }

    public void setESpaceMeetingAsSoapConfManagementServiceWSDDServiceName(java.lang.String name) {
        ESpaceMeetingAsSoapConfManagementServiceWSDDServiceName = name;
    }

    public com.bizconf.vcaasz.soap.conf.ESpaceMeetingAsSoapConfManagementService getESpaceMeetingAsSoapConfManagementService() throws javax.xml.rpc.ServiceException {
       java.net.URL endpoint;
        try {
            endpoint = new java.net.URL(ESpaceMeetingAsSoapConfManagementService_address);
        }
        catch (java.net.MalformedURLException e) {
            throw new javax.xml.rpc.ServiceException(e);
        }
        return getESpaceMeetingAsSoapConfManagementService(endpoint);
    }

    public com.bizconf.vcaasz.soap.conf.ESpaceMeetingAsSoapConfManagementService getESpaceMeetingAsSoapConfManagementService(java.net.URL portAddress) throws javax.xml.rpc.ServiceException {
        try {
            com.bizconf.vcaasz.soap.conf.ESpaceMeetingAsSoapConfManagementServicebindingStub _stub = new com.bizconf.vcaasz.soap.conf.ESpaceMeetingAsSoapConfManagementServicebindingStub(portAddress, this);
            _stub.setPortName(getESpaceMeetingAsSoapConfManagementServiceWSDDServiceName());
            return _stub;
        }
        catch (org.apache.axis.AxisFault e) {
            return null;
        }
    }

    public void setESpaceMeetingAsSoapConfManagementServiceEndpointAddress(java.lang.String address) {
        ESpaceMeetingAsSoapConfManagementService_address = address;
    }


    // Use to get a proxy class for ESpaceMeetingAsSoapBusinessService
    private java.lang.String ESpaceMeetingAsSoapBusinessService_address = "http://127.0.0.1:8996/eSpaceMeeting/BusinessService";

    public java.lang.String getESpaceMeetingAsSoapBusinessServiceAddress() {
        return ESpaceMeetingAsSoapBusinessService_address;
    }

    // The WSDD service name defaults to the port name.
    private java.lang.String ESpaceMeetingAsSoapBusinessServiceWSDDServiceName = "eSpaceMeeting.as.soap.BusinessService";

    public java.lang.String getESpaceMeetingAsSoapBusinessServiceWSDDServiceName() {
        return ESpaceMeetingAsSoapBusinessServiceWSDDServiceName;
    }

    public void setESpaceMeetingAsSoapBusinessServiceWSDDServiceName(java.lang.String name) {
        ESpaceMeetingAsSoapBusinessServiceWSDDServiceName = name;
    }

    public com.bizconf.vcaasz.soap.conf.ESpaceMeetingAsSoapBusinessService getESpaceMeetingAsSoapBusinessService() throws javax.xml.rpc.ServiceException {
       java.net.URL endpoint;
        try {
            endpoint = new java.net.URL(ESpaceMeetingAsSoapBusinessService_address);
        }
        catch (java.net.MalformedURLException e) {
            throw new javax.xml.rpc.ServiceException(e);
        }
        return getESpaceMeetingAsSoapBusinessService(endpoint);
    }

    public com.bizconf.vcaasz.soap.conf.ESpaceMeetingAsSoapBusinessService getESpaceMeetingAsSoapBusinessService(java.net.URL portAddress) throws javax.xml.rpc.ServiceException {
        try {
            com.bizconf.vcaasz.soap.conf.ESpaceMeetingAsSoapBusinessServicebindingStub _stub = new com.bizconf.vcaasz.soap.conf.ESpaceMeetingAsSoapBusinessServicebindingStub(portAddress, this);
            _stub.setPortName(getESpaceMeetingAsSoapBusinessServiceWSDDServiceName());
            return _stub;
        }
        catch (org.apache.axis.AxisFault e) {
            return null;
        }
    }

    public void setESpaceMeetingAsSoapBusinessServiceEndpointAddress(java.lang.String address) {
        ESpaceMeetingAsSoapBusinessService_address = address;
    }


    // Use to get a proxy class for ESpaceMeetingAsSoapBaseService
    private java.lang.String ESpaceMeetingAsSoapBaseService_address = "http://127.0.0.1:8996/eSpaceMeeting/BaseService";

    public java.lang.String getESpaceMeetingAsSoapBaseServiceAddress() {
        return ESpaceMeetingAsSoapBaseService_address;
    }

    // The WSDD service name defaults to the port name.
    private java.lang.String ESpaceMeetingAsSoapBaseServiceWSDDServiceName = "eSpaceMeeting.as.soap.BaseService";

    public java.lang.String getESpaceMeetingAsSoapBaseServiceWSDDServiceName() {
        return ESpaceMeetingAsSoapBaseServiceWSDDServiceName;
    }

    public void setESpaceMeetingAsSoapBaseServiceWSDDServiceName(java.lang.String name) {
        ESpaceMeetingAsSoapBaseServiceWSDDServiceName = name;
    }

    public com.bizconf.vcaasz.soap.conf.ESpaceMeetingAsSoapBaseService getESpaceMeetingAsSoapBaseService() throws javax.xml.rpc.ServiceException {
       java.net.URL endpoint;
        try {
            endpoint = new java.net.URL(ESpaceMeetingAsSoapBaseService_address);
        }
        catch (java.net.MalformedURLException e) {
            throw new javax.xml.rpc.ServiceException(e);
        }
        return getESpaceMeetingAsSoapBaseService(endpoint);
    }

    public com.bizconf.vcaasz.soap.conf.ESpaceMeetingAsSoapBaseService getESpaceMeetingAsSoapBaseService(java.net.URL portAddress) throws javax.xml.rpc.ServiceException {
        try {
            com.bizconf.vcaasz.soap.conf.ESpaceMeetingAsSoapBaseServicebindingStub _stub = new com.bizconf.vcaasz.soap.conf.ESpaceMeetingAsSoapBaseServicebindingStub(portAddress, this);
            _stub.setPortName(getESpaceMeetingAsSoapBaseServiceWSDDServiceName());
            return _stub;
        }
        catch (org.apache.axis.AxisFault e) {
            return null;
        }
    }

    public void setESpaceMeetingAsSoapBaseServiceEndpointAddress(java.lang.String address) {
        ESpaceMeetingAsSoapBaseService_address = address;
    }

    /**
     * For the given interface, get the stub implementation.
     * If this service has no port for the given interface,
     * then ServiceException is thrown.
     */
    public java.rmi.Remote getPort(Class serviceEndpointInterface) throws javax.xml.rpc.ServiceException {
        try {
            if (com.bizconf.vcaasz.soap.conf.ESpaceMeetingAsSoapConfManagementService.class.isAssignableFrom(serviceEndpointInterface)) {
                com.bizconf.vcaasz.soap.conf.ESpaceMeetingAsSoapConfManagementServicebindingStub _stub = new com.bizconf.vcaasz.soap.conf.ESpaceMeetingAsSoapConfManagementServicebindingStub(new java.net.URL(ESpaceMeetingAsSoapConfManagementService_address), this);
                _stub.setPortName(getESpaceMeetingAsSoapConfManagementServiceWSDDServiceName());
                return _stub;
            }
            if (com.bizconf.vcaasz.soap.conf.ESpaceMeetingAsSoapBusinessService.class.isAssignableFrom(serviceEndpointInterface)) {
                com.bizconf.vcaasz.soap.conf.ESpaceMeetingAsSoapBusinessServicebindingStub _stub = new com.bizconf.vcaasz.soap.conf.ESpaceMeetingAsSoapBusinessServicebindingStub(new java.net.URL(ESpaceMeetingAsSoapBusinessService_address), this);
                _stub.setPortName(getESpaceMeetingAsSoapBusinessServiceWSDDServiceName());
                return _stub;
            }
            if (com.bizconf.vcaasz.soap.conf.ESpaceMeetingAsSoapBaseService.class.isAssignableFrom(serviceEndpointInterface)) {
                com.bizconf.vcaasz.soap.conf.ESpaceMeetingAsSoapBaseServicebindingStub _stub = new com.bizconf.vcaasz.soap.conf.ESpaceMeetingAsSoapBaseServicebindingStub(new java.net.URL(ESpaceMeetingAsSoapBaseService_address), this);
                _stub.setPortName(getESpaceMeetingAsSoapBaseServiceWSDDServiceName());
                return _stub;
            }
        }
        catch (java.lang.Throwable t) {
            throw new javax.xml.rpc.ServiceException(t);
        }
        throw new javax.xml.rpc.ServiceException("There is no stub implementation for the interface:  " + (serviceEndpointInterface == null ? "null" : serviceEndpointInterface.getName()));
    }

    /**
     * For the given interface, get the stub implementation.
     * If this service has no port for the given interface,
     * then ServiceException is thrown.
     */
    public java.rmi.Remote getPort(javax.xml.namespace.QName portName, Class serviceEndpointInterface) throws javax.xml.rpc.ServiceException {
        if (portName == null) {
            return getPort(serviceEndpointInterface);
        }
        java.lang.String inputPortName = portName.getLocalPart();
        if ("eSpaceMeeting.as.soap.ConfManagementService".equals(inputPortName)) {
            return getESpaceMeetingAsSoapConfManagementService();
        }
        else if ("eSpaceMeeting.as.soap.BusinessService".equals(inputPortName)) {
            return getESpaceMeetingAsSoapBusinessService();
        }
        else if ("eSpaceMeeting.as.soap.BaseService".equals(inputPortName)) {
            return getESpaceMeetingAsSoapBaseService();
        }
        else  {
            java.rmi.Remote _stub = getPort(serviceEndpointInterface);
            ((org.apache.axis.client.Stub) _stub).setPortName(portName);
            return _stub;
        }
    }

    public javax.xml.namespace.QName getServiceName() {
        return new javax.xml.namespace.QName("eSpaceMeeting", "eSpaceMeetingCmu");
    }

    private java.util.HashSet ports = null;

    public java.util.Iterator getPorts() {
        if (ports == null) {
            ports = new java.util.HashSet();
            ports.add(new javax.xml.namespace.QName("eSpaceMeeting", "eSpaceMeeting.as.soap.ConfManagementService"));
            ports.add(new javax.xml.namespace.QName("eSpaceMeeting", "eSpaceMeeting.as.soap.BusinessService"));
            ports.add(new javax.xml.namespace.QName("eSpaceMeeting", "eSpaceMeeting.as.soap.BaseService"));
        }
        return ports.iterator();
    }

    /**
    * Set the endpoint address for the specified port name.
    */
    public void setEndpointAddress(java.lang.String portName, java.lang.String address) throws javax.xml.rpc.ServiceException {
        
if ("ESpaceMeetingAsSoapConfManagementService".equals(portName)) {
            setESpaceMeetingAsSoapConfManagementServiceEndpointAddress(address);
        }
        else 
if ("ESpaceMeetingAsSoapBusinessService".equals(portName)) {
            setESpaceMeetingAsSoapBusinessServiceEndpointAddress(address);
        }
        else 
if ("ESpaceMeetingAsSoapBaseService".equals(portName)) {
            setESpaceMeetingAsSoapBaseServiceEndpointAddress(address);
        }
        else 
{ // Unknown Port Name
            throw new javax.xml.rpc.ServiceException(" Cannot set Endpoint Address for Unknown Port" + portName);
        }
    }

    /**
    * Set the endpoint address for the specified port name.
    */
    public void setEndpointAddress(javax.xml.namespace.QName portName, java.lang.String address) throws javax.xml.rpc.ServiceException {
        setEndpointAddress(portName.getLocalPart(), address);
    }

}
