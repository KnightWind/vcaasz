/**
 * ESpaceMeetingAsSoapBaseService.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Nov 19, 2006 (02:31:34 GMT+00:00) WSDL2Java emitter.
 */

package com.bizconf.vcaasz.soap.conf;

public interface ESpaceMeetingAsSoapBaseService extends java.rmi.Remote {
    public com.bizconf.vcaasz.soap.conf.ESpaceMeetingAsSoapResult getAppkey(com.bizconf.vcaasz.soap.conf.ESpaceMeetingAsSoapRequestGetAppkeyRequest request, com.bizconf.vcaasz.soap.conf.holders.ESpaceMeetingAsSoapResponseGetAppkeyResponseHolder response) throws java.rmi.RemoteException;
    public com.bizconf.vcaasz.soap.conf.ESpaceMeetingAsSoapResult heartbeat(com.bizconf.vcaasz.soap.conf.ESpaceMeetingAsSoapRequestHeartbeatRequest request) throws java.rmi.RemoteException;
    public com.bizconf.vcaasz.soap.conf.ESpaceMeetingAsSoapResult setSystemParam(com.bizconf.vcaasz.soap.conf.ESpaceMeetingAsSoapRequestSetSystemParamRequest request) throws java.rmi.RemoteException;
    public com.bizconf.vcaasz.soap.conf.ESpaceMeetingAsSoapResult getSystemParam(com.bizconf.vcaasz.soap.conf.ESpaceMeetingAsSoapRequestGetSystemParamRequest request, com.bizconf.vcaasz.soap.conf.holders.ESpaceMeetingAsSoapResponseGetSystemParamResponseHolder response) throws java.rmi.RemoteException;
    public com.bizconf.vcaasz.soap.conf.ESpaceMeetingAsSoapResult getSystemLicense(com.bizconf.vcaasz.soap.conf.ESpaceMeetingAsSoapRequestGetSystemLicenseRequest request, com.bizconf.vcaasz.soap.conf.holders.ESpaceMeetingAsSoapResponseGetSystemLicenseResponseHolder response) throws java.rmi.RemoteException;
    public com.bizconf.vcaasz.soap.conf.ESpaceMeetingAsSoapResult getLicenseInfo(com.bizconf.vcaasz.soap.conf.ESpaceMeetingAsSoapRequestGetLicenseInfoRequest request, com.bizconf.vcaasz.soap.conf.holders.ESpaceMeetingAsSoapResponseGetLicenseInfoResponseHolder response) throws java.rmi.RemoteException;
    public com.bizconf.vcaasz.soap.conf.ESpaceMeetingAsSoapResult getESN(com.bizconf.vcaasz.soap.conf.ESpaceMeetingAsSoapRequestGetESNRequest request, com.bizconf.vcaasz.soap.conf.holders.ESpaceMeetingAsSoapResponseGetESNResponseHolder response) throws java.rmi.RemoteException;
    public com.bizconf.vcaasz.soap.conf.ESpaceMeetingAsSoapResult activeLicense(com.bizconf.vcaasz.soap.conf.ESpaceMeetingAsSoapRequestActiveLicenseRequest request) throws java.rmi.RemoteException;
}
