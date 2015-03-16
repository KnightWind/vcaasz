/**
 * ESpaceMeetingAsSoapBusinessServicebindingStub.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Nov 19, 2006 (02:31:34 GMT+00:00) WSDL2Java emitter.
 */

package com.bizconf.vcaasz.soap.conf;

public class ESpaceMeetingAsSoapBusinessServicebindingStub extends org.apache.axis.client.Stub implements com.bizconf.vcaasz.soap.conf.ESpaceMeetingAsSoapBusinessService {
    private java.util.Vector cachedSerClasses = new java.util.Vector();
    private java.util.Vector cachedSerQNames = new java.util.Vector();
    private java.util.Vector cachedSerFactories = new java.util.Vector();
    private java.util.Vector cachedDeserFactories = new java.util.Vector();

    static org.apache.axis.description.OperationDesc [] _operations;

    static {
        _operations = new org.apache.axis.description.OperationDesc[26];
        _initOperationDesc1();
        _initOperationDesc2();
        _initOperationDesc3();
    }

    private static void _initOperationDesc1(){
        org.apache.axis.description.OperationDesc oper;
        org.apache.axis.description.ParameterDesc param;
        oper = new org.apache.axis.description.OperationDesc();
        oper.setName("addEnterprise");
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("", "request"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("eSpaceMeeting", "eSpaceMeeting.as.soap.request.AddEnterpriseRequest"), com.bizconf.vcaasz.soap.conf.ESpaceMeetingAsSoapRequestAddEnterpriseRequest.class, false, false);
        oper.addParameter(param);
        oper.setReturnType(new javax.xml.namespace.QName("eSpaceMeeting", "eSpaceMeeting.as.soap.Result"));
        oper.setReturnClass(com.bizconf.vcaasz.soap.conf.ESpaceMeetingAsSoapResult.class);
        oper.setReturnQName(new javax.xml.namespace.QName("", "_result"));
        oper.setStyle(org.apache.axis.constants.Style.RPC);
        oper.setUse(org.apache.axis.constants.Use.LITERAL);
        _operations[0] = oper;

        oper = new org.apache.axis.description.OperationDesc();
        oper.setName("updateEnterprise");
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("", "request"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("eSpaceMeeting", "eSpaceMeeting.as.soap.request.UpdateEnterpriseRequest"), com.bizconf.vcaasz.soap.conf.ESpaceMeetingAsSoapRequestUpdateEnterpriseRequest.class, false, false);
        oper.addParameter(param);
        oper.setReturnType(new javax.xml.namespace.QName("eSpaceMeeting", "eSpaceMeeting.as.soap.Result"));
        oper.setReturnClass(com.bizconf.vcaasz.soap.conf.ESpaceMeetingAsSoapResult.class);
        oper.setReturnQName(new javax.xml.namespace.QName("", "_result"));
        oper.setStyle(org.apache.axis.constants.Style.RPC);
        oper.setUse(org.apache.axis.constants.Use.LITERAL);
        _operations[1] = oper;

        oper = new org.apache.axis.description.OperationDesc();
        oper.setName("removeEnterprise");
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("", "request"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("eSpaceMeeting", "eSpaceMeeting.as.soap.request.RemoveEnterpriseRequest"), com.bizconf.vcaasz.soap.conf.ESpaceMeetingAsSoapRequestRemoveEnterpriseRequest.class, false, false);
        oper.addParameter(param);
        oper.setReturnType(new javax.xml.namespace.QName("eSpaceMeeting", "eSpaceMeeting.as.soap.Result"));
        oper.setReturnClass(com.bizconf.vcaasz.soap.conf.ESpaceMeetingAsSoapResult.class);
        oper.setReturnQName(new javax.xml.namespace.QName("", "_result"));
        oper.setStyle(org.apache.axis.constants.Style.RPC);
        oper.setUse(org.apache.axis.constants.Use.LITERAL);
        _operations[2] = oper;

        oper = new org.apache.axis.description.OperationDesc();
        oper.setName("queryEnterprise");
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("", "request"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("eSpaceMeeting", "eSpaceMeeting.as.soap.request.QueryEnterpriseRequest"), com.bizconf.vcaasz.soap.conf.ESpaceMeetingAsSoapRequestQueryEnterpriseRequest.class, false, false);
        oper.addParameter(param);
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("", "response"), org.apache.axis.description.ParameterDesc.OUT, new javax.xml.namespace.QName("eSpaceMeeting", "eSpaceMeeting.as.soap.response.QueryEnterpriseResponse"), com.bizconf.vcaasz.soap.conf.ESpaceMeetingAsSoapResponseQueryEnterpriseResponse.class, false, false);
        oper.addParameter(param);
        oper.setReturnType(new javax.xml.namespace.QName("eSpaceMeeting", "eSpaceMeeting.as.soap.Result"));
        oper.setReturnClass(com.bizconf.vcaasz.soap.conf.ESpaceMeetingAsSoapResult.class);
        oper.setReturnQName(new javax.xml.namespace.QName("", "_result"));
        oper.setStyle(org.apache.axis.constants.Style.RPC);
        oper.setUse(org.apache.axis.constants.Use.LITERAL);
        _operations[3] = oper;

        oper = new org.apache.axis.description.OperationDesc();
        oper.setName("queryEnterpriseList");
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("", "request"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("eSpaceMeeting", "eSpaceMeeting.as.soap.request.QueryEnterpriseListRequest"), com.bizconf.vcaasz.soap.conf.ESpaceMeetingAsSoapRequestQueryEnterpriseListRequest.class, false, false);
        oper.addParameter(param);
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("", "response"), org.apache.axis.description.ParameterDesc.OUT, new javax.xml.namespace.QName("eSpaceMeeting", "eSpaceMeeting.as.soap.response.QueryEnterpriseListResponse"), com.bizconf.vcaasz.soap.conf.ESpaceMeetingAsSoapResponseQueryEnterpriseListResponse.class, false, false);
        oper.addParameter(param);
        oper.setReturnType(new javax.xml.namespace.QName("eSpaceMeeting", "eSpaceMeeting.as.soap.Result"));
        oper.setReturnClass(com.bizconf.vcaasz.soap.conf.ESpaceMeetingAsSoapResult.class);
        oper.setReturnQName(new javax.xml.namespace.QName("", "_result"));
        oper.setStyle(org.apache.axis.constants.Style.RPC);
        oper.setUse(org.apache.axis.constants.Use.LITERAL);
        _operations[4] = oper;

        oper = new org.apache.axis.description.OperationDesc();
        oper.setName("addArea");
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("", "request"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("eSpaceMeeting", "eSpaceMeeting.as.soap.request.AddAreaRequest"), com.bizconf.vcaasz.soap.conf.ESpaceMeetingAsSoapRequestAddAreaRequest.class, false, false);
        oper.addParameter(param);
        oper.setReturnType(new javax.xml.namespace.QName("eSpaceMeeting", "eSpaceMeeting.as.soap.Result"));
        oper.setReturnClass(com.bizconf.vcaasz.soap.conf.ESpaceMeetingAsSoapResult.class);
        oper.setReturnQName(new javax.xml.namespace.QName("", "_result"));
        oper.setStyle(org.apache.axis.constants.Style.RPC);
        oper.setUse(org.apache.axis.constants.Use.LITERAL);
        _operations[5] = oper;

        oper = new org.apache.axis.description.OperationDesc();
        oper.setName("updateArea");
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("", "request"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("eSpaceMeeting", "eSpaceMeeting.as.soap.request.AddAreaRequest"), com.bizconf.vcaasz.soap.conf.ESpaceMeetingAsSoapRequestAddAreaRequest.class, false, false);
        oper.addParameter(param);
        oper.setReturnType(new javax.xml.namespace.QName("eSpaceMeeting", "eSpaceMeeting.as.soap.Result"));
        oper.setReturnClass(com.bizconf.vcaasz.soap.conf.ESpaceMeetingAsSoapResult.class);
        oper.setReturnQName(new javax.xml.namespace.QName("", "_result"));
        oper.setStyle(org.apache.axis.constants.Style.RPC);
        oper.setUse(org.apache.axis.constants.Use.LITERAL);
        _operations[6] = oper;

        oper = new org.apache.axis.description.OperationDesc();
        oper.setName("removeArea");
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("", "request"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("eSpaceMeeting", "eSpaceMeeting.as.soap.request.RemoveAreaRequest"), com.bizconf.vcaasz.soap.conf.ESpaceMeetingAsSoapRequestRemoveAreaRequest.class, false, false);
        oper.addParameter(param);
        oper.setReturnType(new javax.xml.namespace.QName("eSpaceMeeting", "eSpaceMeeting.as.soap.Result"));
        oper.setReturnClass(com.bizconf.vcaasz.soap.conf.ESpaceMeetingAsSoapResult.class);
        oper.setReturnQName(new javax.xml.namespace.QName("", "_result"));
        oper.setStyle(org.apache.axis.constants.Style.RPC);
        oper.setUse(org.apache.axis.constants.Use.LITERAL);
        _operations[7] = oper;

        oper = new org.apache.axis.description.OperationDesc();
        oper.setName("queryAreaList");
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("", "request"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("eSpaceMeeting", "eSpaceMeeting.as.soap.request.QueryAreaListRequest"), com.bizconf.vcaasz.soap.conf.ESpaceMeetingAsSoapRequestQueryAreaListRequest.class, false, false);
        oper.addParameter(param);
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("", "response"), org.apache.axis.description.ParameterDesc.OUT, new javax.xml.namespace.QName("eSpaceMeeting", "eSpaceMeeting.as.soap.response.QueryAreaListResponse"), com.bizconf.vcaasz.soap.conf.ESpaceMeetingAsSoapResponseQueryAreaListResponse.class, false, false);
        oper.addParameter(param);
        oper.setReturnType(new javax.xml.namespace.QName("eSpaceMeeting", "eSpaceMeeting.as.soap.Result"));
        oper.setReturnClass(com.bizconf.vcaasz.soap.conf.ESpaceMeetingAsSoapResult.class);
        oper.setReturnQName(new javax.xml.namespace.QName("", "_result"));
        oper.setStyle(org.apache.axis.constants.Style.RPC);
        oper.setUse(org.apache.axis.constants.Use.LITERAL);
        _operations[8] = oper;

        oper = new org.apache.axis.description.OperationDesc();
        oper.setName("queryAreaByIP");
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("", "request"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("eSpaceMeeting", "eSpaceMeeting.as.soap.request.QueryAreaByIPRequest"), com.bizconf.vcaasz.soap.conf.ESpaceMeetingAsSoapRequestQueryAreaByIPRequest.class, false, false);
        oper.addParameter(param);
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("", "response"), org.apache.axis.description.ParameterDesc.OUT, new javax.xml.namespace.QName("eSpaceMeeting", "eSpaceMeeting.as.soap.response.QueryAreaByIPResponse"), com.bizconf.vcaasz.soap.conf.ESpaceMeetingAsSoapResponseQueryAreaByIPResponse.class, false, false);
        oper.addParameter(param);
        oper.setReturnType(new javax.xml.namespace.QName("eSpaceMeeting", "eSpaceMeeting.as.soap.Result"));
        oper.setReturnClass(com.bizconf.vcaasz.soap.conf.ESpaceMeetingAsSoapResult.class);
        oper.setReturnQName(new javax.xml.namespace.QName("", "_result"));
        oper.setStyle(org.apache.axis.constants.Style.RPC);
        oper.setUse(org.apache.axis.constants.Use.LITERAL);
        _operations[9] = oper;

    }

    private static void _initOperationDesc2(){
        org.apache.axis.description.OperationDesc oper;
        org.apache.axis.description.ParameterDesc param;
        oper = new org.apache.axis.description.OperationDesc();
        oper.setName("addEnterpriseAreaMap");
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("", "request"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("eSpaceMeeting", "eSpaceMeeting.as.soap.request.AddEnterpriseAreaMapRequest"), com.bizconf.vcaasz.soap.conf.ESpaceMeetingAsSoapRequestAddEnterpriseAreaMapRequest.class, false, false);
        oper.addParameter(param);
        oper.setReturnType(new javax.xml.namespace.QName("eSpaceMeeting", "eSpaceMeeting.as.soap.Result"));
        oper.setReturnClass(com.bizconf.vcaasz.soap.conf.ESpaceMeetingAsSoapResult.class);
        oper.setReturnQName(new javax.xml.namespace.QName("", "_result"));
        oper.setStyle(org.apache.axis.constants.Style.RPC);
        oper.setUse(org.apache.axis.constants.Use.LITERAL);
        _operations[10] = oper;

        oper = new org.apache.axis.description.OperationDesc();
        oper.setName("updateEnterpriseAreaMap");
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("", "request"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("eSpaceMeeting", "eSpaceMeeting.as.soap.request.AddEnterpriseAreaMapRequest"), com.bizconf.vcaasz.soap.conf.ESpaceMeetingAsSoapRequestAddEnterpriseAreaMapRequest.class, false, false);
        oper.addParameter(param);
        oper.setReturnType(new javax.xml.namespace.QName("eSpaceMeeting", "eSpaceMeeting.as.soap.Result"));
        oper.setReturnClass(com.bizconf.vcaasz.soap.conf.ESpaceMeetingAsSoapResult.class);
        oper.setReturnQName(new javax.xml.namespace.QName("", "_result"));
        oper.setStyle(org.apache.axis.constants.Style.RPC);
        oper.setUse(org.apache.axis.constants.Use.LITERAL);
        _operations[11] = oper;

        oper = new org.apache.axis.description.OperationDesc();
        oper.setName("removeEnterpriseAreaMap");
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("", "request"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("eSpaceMeeting", "eSpaceMeeting.as.soap.request.RemoveEnterpriseAreaMapRequest"), com.bizconf.vcaasz.soap.conf.ESpaceMeetingAsSoapRequestRemoveEnterpriseAreaMapRequest.class, false, false);
        oper.addParameter(param);
        oper.setReturnType(new javax.xml.namespace.QName("eSpaceMeeting", "eSpaceMeeting.as.soap.Result"));
        oper.setReturnClass(com.bizconf.vcaasz.soap.conf.ESpaceMeetingAsSoapResult.class);
        oper.setReturnQName(new javax.xml.namespace.QName("", "_result"));
        oper.setStyle(org.apache.axis.constants.Style.RPC);
        oper.setUse(org.apache.axis.constants.Use.LITERAL);
        _operations[12] = oper;

        oper = new org.apache.axis.description.OperationDesc();
        oper.setName("queryEnterpriseAreaMapList");
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("", "request"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("eSpaceMeeting", "eSpaceMeeting.as.soap.request.QueryEnterpriseAreaMapListRequest"), com.bizconf.vcaasz.soap.conf.ESpaceMeetingAsSoapRequestQueryEnterpriseAreaMapListRequest.class, false, false);
        oper.addParameter(param);
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("", "response"), org.apache.axis.description.ParameterDesc.OUT, new javax.xml.namespace.QName("eSpaceMeeting", "eSpaceMeeting.as.soap.response.QueryEnterpriseAreaMapListResponse"), com.bizconf.vcaasz.soap.conf.ESpaceMeetingAsSoapResponseQueryEnterpriseAreaMapListResponse.class, false, false);
        oper.addParameter(param);
        oper.setReturnType(new javax.xml.namespace.QName("eSpaceMeeting", "eSpaceMeeting.as.soap.Result"));
        oper.setReturnClass(com.bizconf.vcaasz.soap.conf.ESpaceMeetingAsSoapResult.class);
        oper.setReturnQName(new javax.xml.namespace.QName("", "_result"));
        oper.setStyle(org.apache.axis.constants.Style.RPC);
        oper.setUse(org.apache.axis.constants.Use.LITERAL);
        _operations[13] = oper;

        oper = new org.apache.axis.description.OperationDesc();
        oper.setName("addMSCluster");
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("", "request"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("eSpaceMeeting", "eSpaceMeeting.as.soap.request.AddMSClusterRequest"), com.bizconf.vcaasz.soap.conf.ESpaceMeetingAsSoapRequestAddMSClusterRequest.class, false, false);
        oper.addParameter(param);
        oper.setReturnType(new javax.xml.namespace.QName("eSpaceMeeting", "eSpaceMeeting.as.soap.Result"));
        oper.setReturnClass(com.bizconf.vcaasz.soap.conf.ESpaceMeetingAsSoapResult.class);
        oper.setReturnQName(new javax.xml.namespace.QName("", "_result"));
        oper.setStyle(org.apache.axis.constants.Style.RPC);
        oper.setUse(org.apache.axis.constants.Use.LITERAL);
        _operations[14] = oper;

        oper = new org.apache.axis.description.OperationDesc();
        oper.setName("updateMSCluster");
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("", "request"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("eSpaceMeeting", "eSpaceMeeting.as.soap.request.AddMSClusterRequest"), com.bizconf.vcaasz.soap.conf.ESpaceMeetingAsSoapRequestAddMSClusterRequest.class, false, false);
        oper.addParameter(param);
        oper.setReturnType(new javax.xml.namespace.QName("eSpaceMeeting", "eSpaceMeeting.as.soap.Result"));
        oper.setReturnClass(com.bizconf.vcaasz.soap.conf.ESpaceMeetingAsSoapResult.class);
        oper.setReturnQName(new javax.xml.namespace.QName("", "_result"));
        oper.setStyle(org.apache.axis.constants.Style.RPC);
        oper.setUse(org.apache.axis.constants.Use.LITERAL);
        _operations[15] = oper;

        oper = new org.apache.axis.description.OperationDesc();
        oper.setName("removeMSCluster");
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("", "request"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("eSpaceMeeting", "eSpaceMeeting.as.soap.request.RemoveMSClusterRequest"), com.bizconf.vcaasz.soap.conf.ESpaceMeetingAsSoapRequestRemoveMSClusterRequest.class, false, false);
        oper.addParameter(param);
        oper.setReturnType(new javax.xml.namespace.QName("eSpaceMeeting", "eSpaceMeeting.as.soap.Result"));
        oper.setReturnClass(com.bizconf.vcaasz.soap.conf.ESpaceMeetingAsSoapResult.class);
        oper.setReturnQName(new javax.xml.namespace.QName("", "_result"));
        oper.setStyle(org.apache.axis.constants.Style.RPC);
        oper.setUse(org.apache.axis.constants.Use.LITERAL);
        _operations[16] = oper;

        oper = new org.apache.axis.description.OperationDesc();
        oper.setName("queryMSClusterList");
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("", "request"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("eSpaceMeeting", "eSpaceMeeting.as.soap.request.QueryMSClusterListRequest"), com.bizconf.vcaasz.soap.conf.ESpaceMeetingAsSoapRequestQueryMSClusterListRequest.class, false, false);
        oper.addParameter(param);
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("", "response"), org.apache.axis.description.ParameterDesc.OUT, new javax.xml.namespace.QName("eSpaceMeeting", "eSpaceMeeting.as.soap.response.QueryMSClusterListResponse"), com.bizconf.vcaasz.soap.conf.ESpaceMeetingAsSoapResponseQueryMSClusterListResponse.class, false, false);
        oper.addParameter(param);
        oper.setReturnType(new javax.xml.namespace.QName("eSpaceMeeting", "eSpaceMeeting.as.soap.Result"));
        oper.setReturnClass(com.bizconf.vcaasz.soap.conf.ESpaceMeetingAsSoapResult.class);
        oper.setReturnQName(new javax.xml.namespace.QName("", "_result"));
        oper.setStyle(org.apache.axis.constants.Style.RPC);
        oper.setUse(org.apache.axis.constants.Use.LITERAL);
        _operations[17] = oper;

        oper = new org.apache.axis.description.OperationDesc();
        oper.setName("addMRS");
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("", "request"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("eSpaceMeeting", "eSpaceMeeting.as.soap.request.AddMRSRequest"), com.bizconf.vcaasz.soap.conf.ESpaceMeetingAsSoapRequestAddMRSRequest.class, false, false);
        oper.addParameter(param);
        oper.setReturnType(new javax.xml.namespace.QName("eSpaceMeeting", "eSpaceMeeting.as.soap.Result"));
        oper.setReturnClass(com.bizconf.vcaasz.soap.conf.ESpaceMeetingAsSoapResult.class);
        oper.setReturnQName(new javax.xml.namespace.QName("", "_result"));
        oper.setStyle(org.apache.axis.constants.Style.RPC);
        oper.setUse(org.apache.axis.constants.Use.LITERAL);
        _operations[18] = oper;

        oper = new org.apache.axis.description.OperationDesc();
        oper.setName("updateMRS");
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("", "request"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("eSpaceMeeting", "eSpaceMeeting.as.soap.request.AddMRSRequest"), com.bizconf.vcaasz.soap.conf.ESpaceMeetingAsSoapRequestAddMRSRequest.class, false, false);
        oper.addParameter(param);
        oper.setReturnType(new javax.xml.namespace.QName("eSpaceMeeting", "eSpaceMeeting.as.soap.Result"));
        oper.setReturnClass(com.bizconf.vcaasz.soap.conf.ESpaceMeetingAsSoapResult.class);
        oper.setReturnQName(new javax.xml.namespace.QName("", "_result"));
        oper.setStyle(org.apache.axis.constants.Style.RPC);
        oper.setUse(org.apache.axis.constants.Use.LITERAL);
        _operations[19] = oper;

    }

    private static void _initOperationDesc3(){
        org.apache.axis.description.OperationDesc oper;
        org.apache.axis.description.ParameterDesc param;
        oper = new org.apache.axis.description.OperationDesc();
        oper.setName("removeMRS");
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("", "request"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("eSpaceMeeting", "eSpaceMeeting.as.soap.request.RemoveMRSRequest"), com.bizconf.vcaasz.soap.conf.ESpaceMeetingAsSoapRequestRemoveMRSRequest.class, false, false);
        oper.addParameter(param);
        oper.setReturnType(new javax.xml.namespace.QName("eSpaceMeeting", "eSpaceMeeting.as.soap.Result"));
        oper.setReturnClass(com.bizconf.vcaasz.soap.conf.ESpaceMeetingAsSoapResult.class);
        oper.setReturnQName(new javax.xml.namespace.QName("", "_result"));
        oper.setStyle(org.apache.axis.constants.Style.RPC);
        oper.setUse(org.apache.axis.constants.Use.LITERAL);
        _operations[20] = oper;

        oper = new org.apache.axis.description.OperationDesc();
        oper.setName("queryMRSList");
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("", "request"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("eSpaceMeeting", "eSpaceMeeting.as.soap.request.QueryMRSListRequest"), com.bizconf.vcaasz.soap.conf.ESpaceMeetingAsSoapRequestQueryMRSListRequest.class, false, false);
        oper.addParameter(param);
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("", "response"), org.apache.axis.description.ParameterDesc.OUT, new javax.xml.namespace.QName("eSpaceMeeting", "eSpaceMeeting.as.soap.response.QueryMRSListResponse"), com.bizconf.vcaasz.soap.conf.ESpaceMeetingAsSoapResponseQueryMRSListResponse.class, false, false);
        oper.addParameter(param);
        oper.setReturnType(new javax.xml.namespace.QName("eSpaceMeeting", "eSpaceMeeting.as.soap.Result"));
        oper.setReturnClass(com.bizconf.vcaasz.soap.conf.ESpaceMeetingAsSoapResult.class);
        oper.setReturnQName(new javax.xml.namespace.QName("", "_result"));
        oper.setStyle(org.apache.axis.constants.Style.RPC);
        oper.setUse(org.apache.axis.constants.Use.LITERAL);
        _operations[21] = oper;

        oper = new org.apache.axis.description.OperationDesc();
        oper.setName("addIPSeg");
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("", "request"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("eSpaceMeeting", "eSpaceMeeting.as.soap.request.AddIPSegRequest"), com.bizconf.vcaasz.soap.conf.ESpaceMeetingAsSoapRequestAddIPSegRequest.class, false, false);
        oper.addParameter(param);
        oper.setReturnType(new javax.xml.namespace.QName("eSpaceMeeting", "eSpaceMeeting.as.soap.Result"));
        oper.setReturnClass(com.bizconf.vcaasz.soap.conf.ESpaceMeetingAsSoapResult.class);
        oper.setReturnQName(new javax.xml.namespace.QName("", "_result"));
        oper.setStyle(org.apache.axis.constants.Style.RPC);
        oper.setUse(org.apache.axis.constants.Use.LITERAL);
        _operations[22] = oper;

        oper = new org.apache.axis.description.OperationDesc();
        oper.setName("updateIPSeg");
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("", "request"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("eSpaceMeeting", "eSpaceMeeting.as.soap.request.AddIPSegRequest"), com.bizconf.vcaasz.soap.conf.ESpaceMeetingAsSoapRequestAddIPSegRequest.class, false, false);
        oper.addParameter(param);
        oper.setReturnType(new javax.xml.namespace.QName("eSpaceMeeting", "eSpaceMeeting.as.soap.Result"));
        oper.setReturnClass(com.bizconf.vcaasz.soap.conf.ESpaceMeetingAsSoapResult.class);
        oper.setReturnQName(new javax.xml.namespace.QName("", "_result"));
        oper.setStyle(org.apache.axis.constants.Style.RPC);
        oper.setUse(org.apache.axis.constants.Use.LITERAL);
        _operations[23] = oper;

        oper = new org.apache.axis.description.OperationDesc();
        oper.setName("removeIPSeg");
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("", "request"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("eSpaceMeeting", "eSpaceMeeting.as.soap.request.RemoveIPSegRequest"), com.bizconf.vcaasz.soap.conf.ESpaceMeetingAsSoapRequestRemoveIPSegRequest.class, false, false);
        oper.addParameter(param);
        oper.setReturnType(new javax.xml.namespace.QName("eSpaceMeeting", "eSpaceMeeting.as.soap.Result"));
        oper.setReturnClass(com.bizconf.vcaasz.soap.conf.ESpaceMeetingAsSoapResult.class);
        oper.setReturnQName(new javax.xml.namespace.QName("", "_result"));
        oper.setStyle(org.apache.axis.constants.Style.RPC);
        oper.setUse(org.apache.axis.constants.Use.LITERAL);
        _operations[24] = oper;

        oper = new org.apache.axis.description.OperationDesc();
        oper.setName("queryIPSegList");
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("", "request"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("eSpaceMeeting", "eSpaceMeeting.as.soap.request.QueryIPSegListRequest"), com.bizconf.vcaasz.soap.conf.ESpaceMeetingAsSoapRequestQueryIPSegListRequest.class, false, false);
        oper.addParameter(param);
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("", "response"), org.apache.axis.description.ParameterDesc.OUT, new javax.xml.namespace.QName("eSpaceMeeting", "eSpaceMeeting.as.soap.response.QueryIPSegListResponse"), com.bizconf.vcaasz.soap.conf.ESpaceMeetingAsSoapResponseQueryIPSegListResponse.class, false, false);
        oper.addParameter(param);
        oper.setReturnType(new javax.xml.namespace.QName("eSpaceMeeting", "eSpaceMeeting.as.soap.Result"));
        oper.setReturnClass(com.bizconf.vcaasz.soap.conf.ESpaceMeetingAsSoapResult.class);
        oper.setReturnQName(new javax.xml.namespace.QName("", "_result"));
        oper.setStyle(org.apache.axis.constants.Style.RPC);
        oper.setUse(org.apache.axis.constants.Use.LITERAL);
        _operations[25] = oper;

    }

    public ESpaceMeetingAsSoapBusinessServicebindingStub() throws org.apache.axis.AxisFault {
         this(null);
    }

    public ESpaceMeetingAsSoapBusinessServicebindingStub(java.net.URL endpointURL, javax.xml.rpc.Service service) throws org.apache.axis.AxisFault {
         this(service);
         super.cachedEndpoint = endpointURL;
    }

    public ESpaceMeetingAsSoapBusinessServicebindingStub(javax.xml.rpc.Service service) throws org.apache.axis.AxisFault {
        if (service == null) {
            super.service = new org.apache.axis.client.Service();
        } else {
            super.service = service;
        }
        ((org.apache.axis.client.Service)super.service).setTypeMappingVersion("1.2");
            java.lang.Class cls;
            javax.xml.namespace.QName qName;
            javax.xml.namespace.QName qName2;
            java.lang.Class beansf = org.apache.axis.encoding.ser.BeanSerializerFactory.class;
            java.lang.Class beandf = org.apache.axis.encoding.ser.BeanDeserializerFactory.class;
            java.lang.Class enumsf = org.apache.axis.encoding.ser.EnumSerializerFactory.class;
            java.lang.Class enumdf = org.apache.axis.encoding.ser.EnumDeserializerFactory.class;
            java.lang.Class arraysf = org.apache.axis.encoding.ser.ArraySerializerFactory.class;
            java.lang.Class arraydf = org.apache.axis.encoding.ser.ArrayDeserializerFactory.class;
            java.lang.Class simplesf = org.apache.axis.encoding.ser.SimpleSerializerFactory.class;
            java.lang.Class simpledf = org.apache.axis.encoding.ser.SimpleDeserializerFactory.class;
            java.lang.Class simplelistsf = org.apache.axis.encoding.ser.SimpleListSerializerFactory.class;
            java.lang.Class simplelistdf = org.apache.axis.encoding.ser.SimpleListDeserializerFactory.class;
            qName = new javax.xml.namespace.QName("eSpaceMeeting", "eSpaceMeeting.as.Area");
            cachedSerQNames.add(qName);
            cls = com.bizconf.vcaasz.soap.conf.ESpaceMeetingAsArea.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("eSpaceMeeting", "eSpaceMeeting.as.AreaList");
            cachedSerQNames.add(qName);
            cls = com.bizconf.vcaasz.soap.conf.ESpaceMeetingAsArea[].class;
            cachedSerClasses.add(cls);
            qName = new javax.xml.namespace.QName("eSpaceMeeting", "eSpaceMeeting.as.Area");
            qName2 = new javax.xml.namespace.QName("", "item");
            cachedSerFactories.add(new org.apache.axis.encoding.ser.ArraySerializerFactory(qName, qName2));
            cachedDeserFactories.add(new org.apache.axis.encoding.ser.ArrayDeserializerFactory());

            qName = new javax.xml.namespace.QName("eSpaceMeeting", "eSpaceMeeting.as.AreaMRS");
            cachedSerQNames.add(qName);
            cls = com.bizconf.vcaasz.soap.conf.ESpaceMeetingAsAreaMRS.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("eSpaceMeeting", "eSpaceMeeting.as.AreaMSCluster");
            cachedSerQNames.add(qName);
            cls = com.bizconf.vcaasz.soap.conf.ESpaceMeetingAsAreaMSCluster.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("eSpaceMeeting", "eSpaceMeeting.as.Enterprise");
            cachedSerQNames.add(qName);
            cls = com.bizconf.vcaasz.soap.conf.ESpaceMeetingAsEnterprise.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("eSpaceMeeting", "eSpaceMeeting.as.EnterpriseAreaMap");
            cachedSerQNames.add(qName);
            cls = com.bizconf.vcaasz.soap.conf.ESpaceMeetingAsEnterpriseAreaMap.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("eSpaceMeeting", "eSpaceMeeting.as.EnterpriseAreaMapList");
            cachedSerQNames.add(qName);
            cls = com.bizconf.vcaasz.soap.conf.ESpaceMeetingAsEnterpriseAreaMap[].class;
            cachedSerClasses.add(cls);
            qName = new javax.xml.namespace.QName("eSpaceMeeting", "eSpaceMeeting.as.EnterpriseAreaMap");
            qName2 = new javax.xml.namespace.QName("", "item");
            cachedSerFactories.add(new org.apache.axis.encoding.ser.ArraySerializerFactory(qName, qName2));
            cachedDeserFactories.add(new org.apache.axis.encoding.ser.ArrayDeserializerFactory());

            qName = new javax.xml.namespace.QName("eSpaceMeeting", "eSpaceMeeting.as.EnterpriseList");
            cachedSerQNames.add(qName);
            cls = com.bizconf.vcaasz.soap.conf.ESpaceMeetingAsEnterprise[].class;
            cachedSerClasses.add(cls);
            qName = new javax.xml.namespace.QName("eSpaceMeeting", "eSpaceMeeting.as.Enterprise");
            qName2 = new javax.xml.namespace.QName("", "item");
            cachedSerFactories.add(new org.apache.axis.encoding.ser.ArraySerializerFactory(qName, qName2));
            cachedDeserFactories.add(new org.apache.axis.encoding.ser.ArrayDeserializerFactory());

            qName = new javax.xml.namespace.QName("eSpaceMeeting", "eSpaceMeeting.as.IPSegment");
            cachedSerQNames.add(qName);
            cls = com.bizconf.vcaasz.soap.conf.ESpaceMeetingAsIPSegment.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("eSpaceMeeting", "eSpaceMeeting.as.IPSegmentList");
            cachedSerQNames.add(qName);
            cls = com.bizconf.vcaasz.soap.conf.ESpaceMeetingAsIPSegment[].class;
            cachedSerClasses.add(cls);
            qName = new javax.xml.namespace.QName("eSpaceMeeting", "eSpaceMeeting.as.IPSegment");
            qName2 = new javax.xml.namespace.QName("", "item");
            cachedSerFactories.add(new org.apache.axis.encoding.ser.ArraySerializerFactory(qName, qName2));
            cachedDeserFactories.add(new org.apache.axis.encoding.ser.ArrayDeserializerFactory());

            qName = new javax.xml.namespace.QName("eSpaceMeeting", "eSpaceMeeting.as.MRSList");
            cachedSerQNames.add(qName);
            cls = com.bizconf.vcaasz.soap.conf.ESpaceMeetingAsAreaMRS[].class;
            cachedSerClasses.add(cls);
            qName = new javax.xml.namespace.QName("eSpaceMeeting", "eSpaceMeeting.as.AreaMRS");
            qName2 = new javax.xml.namespace.QName("", "item");
            cachedSerFactories.add(new org.apache.axis.encoding.ser.ArraySerializerFactory(qName, qName2));
            cachedDeserFactories.add(new org.apache.axis.encoding.ser.ArrayDeserializerFactory());

            qName = new javax.xml.namespace.QName("eSpaceMeeting", "eSpaceMeeting.as.MSClusterList");
            cachedSerQNames.add(qName);
            cls = com.bizconf.vcaasz.soap.conf.ESpaceMeetingAsAreaMSCluster[].class;
            cachedSerClasses.add(cls);
            qName = new javax.xml.namespace.QName("eSpaceMeeting", "eSpaceMeeting.as.AreaMSCluster");
            qName2 = new javax.xml.namespace.QName("", "item");
            cachedSerFactories.add(new org.apache.axis.encoding.ser.ArraySerializerFactory(qName, qName2));
            cachedDeserFactories.add(new org.apache.axis.encoding.ser.ArrayDeserializerFactory());

            qName = new javax.xml.namespace.QName("eSpaceMeeting", "eSpaceMeeting.as.soap.ArgList");
            cachedSerQNames.add(qName);
            cls = java.lang.String[].class;
            cachedSerClasses.add(cls);
            qName = new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string");
            qName2 = new javax.xml.namespace.QName("", "item");
            cachedSerFactories.add(new org.apache.axis.encoding.ser.ArraySerializerFactory(qName, qName2));
            cachedDeserFactories.add(new org.apache.axis.encoding.ser.ArrayDeserializerFactory());

            qName = new javax.xml.namespace.QName("eSpaceMeeting", "eSpaceMeeting.as.soap.KWArgList");
            cachedSerQNames.add(qName);
            cls = com.bizconf.vcaasz.soap.conf.ESpaceMeetingAsStringKV[].class;
            cachedSerClasses.add(cls);
            qName = new javax.xml.namespace.QName("eSpaceMeeting", "eSpaceMeeting.as.StringKV");
            qName2 = new javax.xml.namespace.QName("", "item");
            cachedSerFactories.add(new org.apache.axis.encoding.ser.ArraySerializerFactory(qName, qName2));
            cachedDeserFactories.add(new org.apache.axis.encoding.ser.ArrayDeserializerFactory());

            qName = new javax.xml.namespace.QName("eSpaceMeeting", "eSpaceMeeting.as.soap.request.AddAreaRequest");
            cachedSerQNames.add(qName);
            cls = com.bizconf.vcaasz.soap.conf.ESpaceMeetingAsSoapRequestAddAreaRequest.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("eSpaceMeeting", "eSpaceMeeting.as.soap.request.AddEnterpriseAreaMapRequest");
            cachedSerQNames.add(qName);
            cls = com.bizconf.vcaasz.soap.conf.ESpaceMeetingAsSoapRequestAddEnterpriseAreaMapRequest.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("eSpaceMeeting", "eSpaceMeeting.as.soap.request.AddEnterpriseRequest");
            cachedSerQNames.add(qName);
            cls = com.bizconf.vcaasz.soap.conf.ESpaceMeetingAsSoapRequestAddEnterpriseRequest.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("eSpaceMeeting", "eSpaceMeeting.as.soap.request.AddIPSegRequest");
            cachedSerQNames.add(qName);
            cls = com.bizconf.vcaasz.soap.conf.ESpaceMeetingAsSoapRequestAddIPSegRequest.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("eSpaceMeeting", "eSpaceMeeting.as.soap.request.AddMRSRequest");
            cachedSerQNames.add(qName);
            cls = com.bizconf.vcaasz.soap.conf.ESpaceMeetingAsSoapRequestAddMRSRequest.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("eSpaceMeeting", "eSpaceMeeting.as.soap.request.AddMSClusterRequest");
            cachedSerQNames.add(qName);
            cls = com.bizconf.vcaasz.soap.conf.ESpaceMeetingAsSoapRequestAddMSClusterRequest.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("eSpaceMeeting", "eSpaceMeeting.as.soap.request.QueryAreaByIPRequest");
            cachedSerQNames.add(qName);
            cls = com.bizconf.vcaasz.soap.conf.ESpaceMeetingAsSoapRequestQueryAreaByIPRequest.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("eSpaceMeeting", "eSpaceMeeting.as.soap.request.QueryAreaListRequest");
            cachedSerQNames.add(qName);
            cls = com.bizconf.vcaasz.soap.conf.ESpaceMeetingAsSoapRequestQueryAreaListRequest.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("eSpaceMeeting", "eSpaceMeeting.as.soap.request.QueryEnterpriseAreaMapListRequest");
            cachedSerQNames.add(qName);
            cls = com.bizconf.vcaasz.soap.conf.ESpaceMeetingAsSoapRequestQueryEnterpriseAreaMapListRequest.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("eSpaceMeeting", "eSpaceMeeting.as.soap.request.QueryEnterpriseListRequest");
            cachedSerQNames.add(qName);
            cls = com.bizconf.vcaasz.soap.conf.ESpaceMeetingAsSoapRequestQueryEnterpriseListRequest.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("eSpaceMeeting", "eSpaceMeeting.as.soap.request.QueryEnterpriseRequest");
            cachedSerQNames.add(qName);
            cls = com.bizconf.vcaasz.soap.conf.ESpaceMeetingAsSoapRequestQueryEnterpriseRequest.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("eSpaceMeeting", "eSpaceMeeting.as.soap.request.QueryIPSegListRequest");
            cachedSerQNames.add(qName);
            cls = com.bizconf.vcaasz.soap.conf.ESpaceMeetingAsSoapRequestQueryIPSegListRequest.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("eSpaceMeeting", "eSpaceMeeting.as.soap.request.QueryMRSListRequest");
            cachedSerQNames.add(qName);
            cls = com.bizconf.vcaasz.soap.conf.ESpaceMeetingAsSoapRequestQueryMRSListRequest.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("eSpaceMeeting", "eSpaceMeeting.as.soap.request.QueryMSClusterListRequest");
            cachedSerQNames.add(qName);
            cls = com.bizconf.vcaasz.soap.conf.ESpaceMeetingAsSoapRequestQueryMSClusterListRequest.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("eSpaceMeeting", "eSpaceMeeting.as.soap.request.RemoveAreaRequest");
            cachedSerQNames.add(qName);
            cls = com.bizconf.vcaasz.soap.conf.ESpaceMeetingAsSoapRequestRemoveAreaRequest.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("eSpaceMeeting", "eSpaceMeeting.as.soap.request.RemoveEnterpriseAreaMapRequest");
            cachedSerQNames.add(qName);
            cls = com.bizconf.vcaasz.soap.conf.ESpaceMeetingAsSoapRequestRemoveEnterpriseAreaMapRequest.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("eSpaceMeeting", "eSpaceMeeting.as.soap.request.RemoveEnterpriseRequest");
            cachedSerQNames.add(qName);
            cls = com.bizconf.vcaasz.soap.conf.ESpaceMeetingAsSoapRequestRemoveEnterpriseRequest.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("eSpaceMeeting", "eSpaceMeeting.as.soap.request.RemoveIPSegRequest");
            cachedSerQNames.add(qName);
            cls = com.bizconf.vcaasz.soap.conf.ESpaceMeetingAsSoapRequestRemoveIPSegRequest.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("eSpaceMeeting", "eSpaceMeeting.as.soap.request.RemoveMRSRequest");
            cachedSerQNames.add(qName);
            cls = com.bizconf.vcaasz.soap.conf.ESpaceMeetingAsSoapRequestRemoveMRSRequest.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("eSpaceMeeting", "eSpaceMeeting.as.soap.request.RemoveMSClusterRequest");
            cachedSerQNames.add(qName);
            cls = com.bizconf.vcaasz.soap.conf.ESpaceMeetingAsSoapRequestRemoveMSClusterRequest.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("eSpaceMeeting", "eSpaceMeeting.as.soap.request.UpdateEnterpriseRequest");
            cachedSerQNames.add(qName);
            cls = com.bizconf.vcaasz.soap.conf.ESpaceMeetingAsSoapRequestUpdateEnterpriseRequest.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("eSpaceMeeting", "eSpaceMeeting.as.soap.response.QueryAreaByIPResponse");
            cachedSerQNames.add(qName);
            cls = com.bizconf.vcaasz.soap.conf.ESpaceMeetingAsSoapResponseQueryAreaByIPResponse.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("eSpaceMeeting", "eSpaceMeeting.as.soap.response.QueryAreaListResponse");
            cachedSerQNames.add(qName);
            cls = com.bizconf.vcaasz.soap.conf.ESpaceMeetingAsSoapResponseQueryAreaListResponse.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("eSpaceMeeting", "eSpaceMeeting.as.soap.response.QueryEnterpriseAreaMapListResponse");
            cachedSerQNames.add(qName);
            cls = com.bizconf.vcaasz.soap.conf.ESpaceMeetingAsSoapResponseQueryEnterpriseAreaMapListResponse.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("eSpaceMeeting", "eSpaceMeeting.as.soap.response.QueryEnterpriseListResponse");
            cachedSerQNames.add(qName);
            cls = com.bizconf.vcaasz.soap.conf.ESpaceMeetingAsSoapResponseQueryEnterpriseListResponse.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("eSpaceMeeting", "eSpaceMeeting.as.soap.response.QueryEnterpriseResponse");
            cachedSerQNames.add(qName);
            cls = com.bizconf.vcaasz.soap.conf.ESpaceMeetingAsSoapResponseQueryEnterpriseResponse.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("eSpaceMeeting", "eSpaceMeeting.as.soap.response.QueryIPSegListResponse");
            cachedSerQNames.add(qName);
            cls = com.bizconf.vcaasz.soap.conf.ESpaceMeetingAsSoapResponseQueryIPSegListResponse.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("eSpaceMeeting", "eSpaceMeeting.as.soap.response.QueryMRSListResponse");
            cachedSerQNames.add(qName);
            cls = com.bizconf.vcaasz.soap.conf.ESpaceMeetingAsSoapResponseQueryMRSListResponse.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("eSpaceMeeting", "eSpaceMeeting.as.soap.response.QueryMSClusterListResponse");
            cachedSerQNames.add(qName);
            cls = com.bizconf.vcaasz.soap.conf.ESpaceMeetingAsSoapResponseQueryMSClusterListResponse.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("eSpaceMeeting", "eSpaceMeeting.as.soap.Result");
            cachedSerQNames.add(qName);
            cls = com.bizconf.vcaasz.soap.conf.ESpaceMeetingAsSoapResult.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("eSpaceMeeting", "eSpaceMeeting.as.soap.Token");
            cachedSerQNames.add(qName);
            cls = com.bizconf.vcaasz.soap.conf.ESpaceMeetingAsSoapToken.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("eSpaceMeeting", "eSpaceMeeting.as.StringKV");
            cachedSerQNames.add(qName);
            cls = com.bizconf.vcaasz.soap.conf.ESpaceMeetingAsStringKV.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

    }

    protected org.apache.axis.client.Call createCall() throws java.rmi.RemoteException {
        try {
            org.apache.axis.client.Call _call = super._createCall();
            if (super.maintainSessionSet) {
                _call.setMaintainSession(super.maintainSession);
            }
            if (super.cachedUsername != null) {
                _call.setUsername(super.cachedUsername);
            }
            if (super.cachedPassword != null) {
                _call.setPassword(super.cachedPassword);
            }
            if (super.cachedEndpoint != null) {
                _call.setTargetEndpointAddress(super.cachedEndpoint);
            }
            if (super.cachedTimeout != null) {
                _call.setTimeout(super.cachedTimeout);
            }
            if (super.cachedPortName != null) {
                _call.setPortName(super.cachedPortName);
            }
            java.util.Enumeration keys = super.cachedProperties.keys();
            while (keys.hasMoreElements()) {
                java.lang.String key = (java.lang.String) keys.nextElement();
                _call.setProperty(key, super.cachedProperties.get(key));
            }
            // All the type mapping information is registered
            // when the first call is made.
            // The type mapping information is actually registered in
            // the TypeMappingRegistry of the service, which
            // is the reason why registration is only needed for the first call.
            synchronized (this) {
                if (firstCall()) {
                    // must set encoding style before registering serializers
                    _call.setEncodingStyle(null);
                    for (int i = 0; i < cachedSerFactories.size(); ++i) {
                        java.lang.Class cls = (java.lang.Class) cachedSerClasses.get(i);
                        javax.xml.namespace.QName qName =
                                (javax.xml.namespace.QName) cachedSerQNames.get(i);
                        java.lang.Object x = cachedSerFactories.get(i);
                        if (x instanceof Class) {
                            java.lang.Class sf = (java.lang.Class)
                                 cachedSerFactories.get(i);
                            java.lang.Class df = (java.lang.Class)
                                 cachedDeserFactories.get(i);
                            _call.registerTypeMapping(cls, qName, sf, df, false);
                        }
                        else if (x instanceof javax.xml.rpc.encoding.SerializerFactory) {
                            org.apache.axis.encoding.SerializerFactory sf = (org.apache.axis.encoding.SerializerFactory)
                                 cachedSerFactories.get(i);
                            org.apache.axis.encoding.DeserializerFactory df = (org.apache.axis.encoding.DeserializerFactory)
                                 cachedDeserFactories.get(i);
                            _call.registerTypeMapping(cls, qName, sf, df, false);
                        }
                    }
                }
            }
            return _call;
        }
        catch (java.lang.Exception _t) {
            throw new org.apache.axis.AxisFault("Failure trying to get the Call object", _t);
        }
    }

    public com.bizconf.vcaasz.soap.conf.ESpaceMeetingAsSoapResult addEnterprise(com.bizconf.vcaasz.soap.conf.ESpaceMeetingAsSoapRequestAddEnterpriseRequest request) throws java.rmi.RemoteException {
        if (super.cachedEndpoint == null) {
            throw new org.apache.axis.NoEndPointException();
        }
        org.apache.axis.client.Call _call = createCall();
        _call.setOperation(_operations[0]);
        _call.setUseSOAPAction(true);
        _call.setSOAPActionURI("eSpaceMeeting.as.soap.BusinessService#addEnterprise");
        _call.setEncodingStyle(null);
        _call.setProperty(org.apache.axis.client.Call.SEND_TYPE_ATTR, Boolean.FALSE);
        _call.setProperty(org.apache.axis.AxisEngine.PROP_DOMULTIREFS, Boolean.FALSE);
        _call.setSOAPVersion(org.apache.axis.soap.SOAPConstants.SOAP11_CONSTANTS);
        _call.setOperationName(new javax.xml.namespace.QName("eSpaceMeeting.as.soap.BusinessService", "addEnterprise"));

        setRequestHeaders(_call);
        setAttachments(_call);
 try {        java.lang.Object _resp = _call.invoke(new java.lang.Object[] {request});

        if (_resp instanceof java.rmi.RemoteException) {
            throw (java.rmi.RemoteException)_resp;
        }
        else {
            extractAttachments(_call);
            try {
                return (com.bizconf.vcaasz.soap.conf.ESpaceMeetingAsSoapResult) _resp;
            } catch (java.lang.Exception _exception) {
                return (com.bizconf.vcaasz.soap.conf.ESpaceMeetingAsSoapResult) org.apache.axis.utils.JavaUtils.convert(_resp, com.bizconf.vcaasz.soap.conf.ESpaceMeetingAsSoapResult.class);
            }
        }
  } catch (org.apache.axis.AxisFault axisFaultException) {
  throw axisFaultException;
}
    }

    public com.bizconf.vcaasz.soap.conf.ESpaceMeetingAsSoapResult updateEnterprise(com.bizconf.vcaasz.soap.conf.ESpaceMeetingAsSoapRequestUpdateEnterpriseRequest request) throws java.rmi.RemoteException {
        if (super.cachedEndpoint == null) {
            throw new org.apache.axis.NoEndPointException();
        }
        org.apache.axis.client.Call _call = createCall();
        _call.setOperation(_operations[1]);
        _call.setUseSOAPAction(true);
        _call.setSOAPActionURI("eSpaceMeeting.as.soap.BusinessService#updateEnterprise");
        _call.setEncodingStyle(null);
        _call.setProperty(org.apache.axis.client.Call.SEND_TYPE_ATTR, Boolean.FALSE);
        _call.setProperty(org.apache.axis.AxisEngine.PROP_DOMULTIREFS, Boolean.FALSE);
        _call.setSOAPVersion(org.apache.axis.soap.SOAPConstants.SOAP11_CONSTANTS);
        _call.setOperationName(new javax.xml.namespace.QName("eSpaceMeeting.as.soap.BusinessService", "updateEnterprise"));

        setRequestHeaders(_call);
        setAttachments(_call);
 try {        java.lang.Object _resp = _call.invoke(new java.lang.Object[] {request});

        if (_resp instanceof java.rmi.RemoteException) {
            throw (java.rmi.RemoteException)_resp;
        }
        else {
            extractAttachments(_call);
            try {
                return (com.bizconf.vcaasz.soap.conf.ESpaceMeetingAsSoapResult) _resp;
            } catch (java.lang.Exception _exception) {
                return (com.bizconf.vcaasz.soap.conf.ESpaceMeetingAsSoapResult) org.apache.axis.utils.JavaUtils.convert(_resp, com.bizconf.vcaasz.soap.conf.ESpaceMeetingAsSoapResult.class);
            }
        }
  } catch (org.apache.axis.AxisFault axisFaultException) {
  throw axisFaultException;
}
    }

    public com.bizconf.vcaasz.soap.conf.ESpaceMeetingAsSoapResult removeEnterprise(com.bizconf.vcaasz.soap.conf.ESpaceMeetingAsSoapRequestRemoveEnterpriseRequest request) throws java.rmi.RemoteException {
        if (super.cachedEndpoint == null) {
            throw new org.apache.axis.NoEndPointException();
        }
        org.apache.axis.client.Call _call = createCall();
        _call.setOperation(_operations[2]);
        _call.setUseSOAPAction(true);
        _call.setSOAPActionURI("eSpaceMeeting.as.soap.BusinessService#removeEnterprise");
        _call.setEncodingStyle(null);
        _call.setProperty(org.apache.axis.client.Call.SEND_TYPE_ATTR, Boolean.FALSE);
        _call.setProperty(org.apache.axis.AxisEngine.PROP_DOMULTIREFS, Boolean.FALSE);
        _call.setSOAPVersion(org.apache.axis.soap.SOAPConstants.SOAP11_CONSTANTS);
        _call.setOperationName(new javax.xml.namespace.QName("eSpaceMeeting.as.soap.BusinessService", "removeEnterprise"));

        setRequestHeaders(_call);
        setAttachments(_call);
 try {        java.lang.Object _resp = _call.invoke(new java.lang.Object[] {request});

        if (_resp instanceof java.rmi.RemoteException) {
            throw (java.rmi.RemoteException)_resp;
        }
        else {
            extractAttachments(_call);
            try {
                return (com.bizconf.vcaasz.soap.conf.ESpaceMeetingAsSoapResult) _resp;
            } catch (java.lang.Exception _exception) {
                return (com.bizconf.vcaasz.soap.conf.ESpaceMeetingAsSoapResult) org.apache.axis.utils.JavaUtils.convert(_resp, com.bizconf.vcaasz.soap.conf.ESpaceMeetingAsSoapResult.class);
            }
        }
  } catch (org.apache.axis.AxisFault axisFaultException) {
  throw axisFaultException;
}
    }

    public com.bizconf.vcaasz.soap.conf.ESpaceMeetingAsSoapResult queryEnterprise(com.bizconf.vcaasz.soap.conf.ESpaceMeetingAsSoapRequestQueryEnterpriseRequest request, com.bizconf.vcaasz.soap.conf.holders.ESpaceMeetingAsSoapResponseQueryEnterpriseResponseHolder response) throws java.rmi.RemoteException {
        if (super.cachedEndpoint == null) {
            throw new org.apache.axis.NoEndPointException();
        }
        org.apache.axis.client.Call _call = createCall();
        _call.setOperation(_operations[3]);
        _call.setUseSOAPAction(true);
        _call.setSOAPActionURI("eSpaceMeeting.as.soap.BusinessService#queryEnterprise");
        _call.setEncodingStyle(null);
        _call.setProperty(org.apache.axis.client.Call.SEND_TYPE_ATTR, Boolean.FALSE);
        _call.setProperty(org.apache.axis.AxisEngine.PROP_DOMULTIREFS, Boolean.FALSE);
        _call.setSOAPVersion(org.apache.axis.soap.SOAPConstants.SOAP11_CONSTANTS);
        _call.setOperationName(new javax.xml.namespace.QName("eSpaceMeeting.as.soap.BusinessService", "queryEnterprise"));

        setRequestHeaders(_call);
        setAttachments(_call);
 try {        java.lang.Object _resp = _call.invoke(new java.lang.Object[] {request});

        if (_resp instanceof java.rmi.RemoteException) {
            throw (java.rmi.RemoteException)_resp;
        }
        else {
            extractAttachments(_call);
            java.util.Map _output;
            _output = _call.getOutputParams();
            try {
                response.value = (com.bizconf.vcaasz.soap.conf.ESpaceMeetingAsSoapResponseQueryEnterpriseResponse) _output.get(new javax.xml.namespace.QName("", "response"));
            } catch (java.lang.Exception _exception) {
                response.value = (com.bizconf.vcaasz.soap.conf.ESpaceMeetingAsSoapResponseQueryEnterpriseResponse) org.apache.axis.utils.JavaUtils.convert(_output.get(new javax.xml.namespace.QName("", "response")), com.bizconf.vcaasz.soap.conf.ESpaceMeetingAsSoapResponseQueryEnterpriseResponse.class);
            }
            try {
                return (com.bizconf.vcaasz.soap.conf.ESpaceMeetingAsSoapResult) _resp;
            } catch (java.lang.Exception _exception) {
                return (com.bizconf.vcaasz.soap.conf.ESpaceMeetingAsSoapResult) org.apache.axis.utils.JavaUtils.convert(_resp, com.bizconf.vcaasz.soap.conf.ESpaceMeetingAsSoapResult.class);
            }
        }
  } catch (org.apache.axis.AxisFault axisFaultException) {
  throw axisFaultException;
}
    }

    public com.bizconf.vcaasz.soap.conf.ESpaceMeetingAsSoapResult queryEnterpriseList(com.bizconf.vcaasz.soap.conf.ESpaceMeetingAsSoapRequestQueryEnterpriseListRequest request, com.bizconf.vcaasz.soap.conf.holders.ESpaceMeetingAsSoapResponseQueryEnterpriseListResponseHolder response) throws java.rmi.RemoteException {
        if (super.cachedEndpoint == null) {
            throw new org.apache.axis.NoEndPointException();
        }
        org.apache.axis.client.Call _call = createCall();
        _call.setOperation(_operations[4]);
        _call.setUseSOAPAction(true);
        _call.setSOAPActionURI("eSpaceMeeting.as.soap.BusinessService#queryEnterpriseList");
        _call.setEncodingStyle(null);
        _call.setProperty(org.apache.axis.client.Call.SEND_TYPE_ATTR, Boolean.FALSE);
        _call.setProperty(org.apache.axis.AxisEngine.PROP_DOMULTIREFS, Boolean.FALSE);
        _call.setSOAPVersion(org.apache.axis.soap.SOAPConstants.SOAP11_CONSTANTS);
        _call.setOperationName(new javax.xml.namespace.QName("eSpaceMeeting.as.soap.BusinessService", "queryEnterpriseList"));

        setRequestHeaders(_call);
        setAttachments(_call);
 try {        java.lang.Object _resp = _call.invoke(new java.lang.Object[] {request});

        if (_resp instanceof java.rmi.RemoteException) {
            throw (java.rmi.RemoteException)_resp;
        }
        else {
            extractAttachments(_call);
            java.util.Map _output;
            _output = _call.getOutputParams();
            try {
                response.value = (com.bizconf.vcaasz.soap.conf.ESpaceMeetingAsSoapResponseQueryEnterpriseListResponse) _output.get(new javax.xml.namespace.QName("", "response"));
            } catch (java.lang.Exception _exception) {
                response.value = (com.bizconf.vcaasz.soap.conf.ESpaceMeetingAsSoapResponseQueryEnterpriseListResponse) org.apache.axis.utils.JavaUtils.convert(_output.get(new javax.xml.namespace.QName("", "response")), com.bizconf.vcaasz.soap.conf.ESpaceMeetingAsSoapResponseQueryEnterpriseListResponse.class);
            }
            try {
                return (com.bizconf.vcaasz.soap.conf.ESpaceMeetingAsSoapResult) _resp;
            } catch (java.lang.Exception _exception) {
                return (com.bizconf.vcaasz.soap.conf.ESpaceMeetingAsSoapResult) org.apache.axis.utils.JavaUtils.convert(_resp, com.bizconf.vcaasz.soap.conf.ESpaceMeetingAsSoapResult.class);
            }
        }
  } catch (org.apache.axis.AxisFault axisFaultException) {
  throw axisFaultException;
}
    }

    public com.bizconf.vcaasz.soap.conf.ESpaceMeetingAsSoapResult addArea(com.bizconf.vcaasz.soap.conf.ESpaceMeetingAsSoapRequestAddAreaRequest request) throws java.rmi.RemoteException {
        if (super.cachedEndpoint == null) {
            throw new org.apache.axis.NoEndPointException();
        }
        org.apache.axis.client.Call _call = createCall();
        _call.setOperation(_operations[5]);
        _call.setUseSOAPAction(true);
        _call.setSOAPActionURI("eSpaceMeeting.as.soap.BusinessService#addArea");
        _call.setEncodingStyle(null);
        _call.setProperty(org.apache.axis.client.Call.SEND_TYPE_ATTR, Boolean.FALSE);
        _call.setProperty(org.apache.axis.AxisEngine.PROP_DOMULTIREFS, Boolean.FALSE);
        _call.setSOAPVersion(org.apache.axis.soap.SOAPConstants.SOAP11_CONSTANTS);
        _call.setOperationName(new javax.xml.namespace.QName("eSpaceMeeting.as.soap.BusinessService", "addArea"));

        setRequestHeaders(_call);
        setAttachments(_call);
 try {        java.lang.Object _resp = _call.invoke(new java.lang.Object[] {request});

        if (_resp instanceof java.rmi.RemoteException) {
            throw (java.rmi.RemoteException)_resp;
        }
        else {
            extractAttachments(_call);
            try {
                return (com.bizconf.vcaasz.soap.conf.ESpaceMeetingAsSoapResult) _resp;
            } catch (java.lang.Exception _exception) {
                return (com.bizconf.vcaasz.soap.conf.ESpaceMeetingAsSoapResult) org.apache.axis.utils.JavaUtils.convert(_resp, com.bizconf.vcaasz.soap.conf.ESpaceMeetingAsSoapResult.class);
            }
        }
  } catch (org.apache.axis.AxisFault axisFaultException) {
  throw axisFaultException;
}
    }

    public com.bizconf.vcaasz.soap.conf.ESpaceMeetingAsSoapResult updateArea(com.bizconf.vcaasz.soap.conf.ESpaceMeetingAsSoapRequestAddAreaRequest request) throws java.rmi.RemoteException {
        if (super.cachedEndpoint == null) {
            throw new org.apache.axis.NoEndPointException();
        }
        org.apache.axis.client.Call _call = createCall();
        _call.setOperation(_operations[6]);
        _call.setUseSOAPAction(true);
        _call.setSOAPActionURI("eSpaceMeeting.as.soap.BusinessService#updateArea");
        _call.setEncodingStyle(null);
        _call.setProperty(org.apache.axis.client.Call.SEND_TYPE_ATTR, Boolean.FALSE);
        _call.setProperty(org.apache.axis.AxisEngine.PROP_DOMULTIREFS, Boolean.FALSE);
        _call.setSOAPVersion(org.apache.axis.soap.SOAPConstants.SOAP11_CONSTANTS);
        _call.setOperationName(new javax.xml.namespace.QName("eSpaceMeeting.as.soap.BusinessService", "updateArea"));

        setRequestHeaders(_call);
        setAttachments(_call);
 try {        java.lang.Object _resp = _call.invoke(new java.lang.Object[] {request});

        if (_resp instanceof java.rmi.RemoteException) {
            throw (java.rmi.RemoteException)_resp;
        }
        else {
            extractAttachments(_call);
            try {
                return (com.bizconf.vcaasz.soap.conf.ESpaceMeetingAsSoapResult) _resp;
            } catch (java.lang.Exception _exception) {
                return (com.bizconf.vcaasz.soap.conf.ESpaceMeetingAsSoapResult) org.apache.axis.utils.JavaUtils.convert(_resp, com.bizconf.vcaasz.soap.conf.ESpaceMeetingAsSoapResult.class);
            }
        }
  } catch (org.apache.axis.AxisFault axisFaultException) {
  throw axisFaultException;
}
    }

    public com.bizconf.vcaasz.soap.conf.ESpaceMeetingAsSoapResult removeArea(com.bizconf.vcaasz.soap.conf.ESpaceMeetingAsSoapRequestRemoveAreaRequest request) throws java.rmi.RemoteException {
        if (super.cachedEndpoint == null) {
            throw new org.apache.axis.NoEndPointException();
        }
        org.apache.axis.client.Call _call = createCall();
        _call.setOperation(_operations[7]);
        _call.setUseSOAPAction(true);
        _call.setSOAPActionURI("eSpaceMeeting.as.soap.BusinessService#removeArea");
        _call.setEncodingStyle(null);
        _call.setProperty(org.apache.axis.client.Call.SEND_TYPE_ATTR, Boolean.FALSE);
        _call.setProperty(org.apache.axis.AxisEngine.PROP_DOMULTIREFS, Boolean.FALSE);
        _call.setSOAPVersion(org.apache.axis.soap.SOAPConstants.SOAP11_CONSTANTS);
        _call.setOperationName(new javax.xml.namespace.QName("eSpaceMeeting.as.soap.BusinessService", "removeArea"));

        setRequestHeaders(_call);
        setAttachments(_call);
 try {        java.lang.Object _resp = _call.invoke(new java.lang.Object[] {request});

        if (_resp instanceof java.rmi.RemoteException) {
            throw (java.rmi.RemoteException)_resp;
        }
        else {
            extractAttachments(_call);
            try {
                return (com.bizconf.vcaasz.soap.conf.ESpaceMeetingAsSoapResult) _resp;
            } catch (java.lang.Exception _exception) {
                return (com.bizconf.vcaasz.soap.conf.ESpaceMeetingAsSoapResult) org.apache.axis.utils.JavaUtils.convert(_resp, com.bizconf.vcaasz.soap.conf.ESpaceMeetingAsSoapResult.class);
            }
        }
  } catch (org.apache.axis.AxisFault axisFaultException) {
  throw axisFaultException;
}
    }

    public com.bizconf.vcaasz.soap.conf.ESpaceMeetingAsSoapResult queryAreaList(com.bizconf.vcaasz.soap.conf.ESpaceMeetingAsSoapRequestQueryAreaListRequest request, com.bizconf.vcaasz.soap.conf.holders.ESpaceMeetingAsSoapResponseQueryAreaListResponseHolder response) throws java.rmi.RemoteException {
        if (super.cachedEndpoint == null) {
            throw new org.apache.axis.NoEndPointException();
        }
        org.apache.axis.client.Call _call = createCall();
        _call.setOperation(_operations[8]);
        _call.setUseSOAPAction(true);
        _call.setSOAPActionURI("eSpaceMeeting.as.soap.BusinessService#queryAreaList");
        _call.setEncodingStyle(null);
        _call.setProperty(org.apache.axis.client.Call.SEND_TYPE_ATTR, Boolean.FALSE);
        _call.setProperty(org.apache.axis.AxisEngine.PROP_DOMULTIREFS, Boolean.FALSE);
        _call.setSOAPVersion(org.apache.axis.soap.SOAPConstants.SOAP11_CONSTANTS);
        _call.setOperationName(new javax.xml.namespace.QName("eSpaceMeeting.as.soap.BusinessService", "queryAreaList"));

        setRequestHeaders(_call);
        setAttachments(_call);
 try {        java.lang.Object _resp = _call.invoke(new java.lang.Object[] {request});

        if (_resp instanceof java.rmi.RemoteException) {
            throw (java.rmi.RemoteException)_resp;
        }
        else {
            extractAttachments(_call);
            java.util.Map _output;
            _output = _call.getOutputParams();
            try {
                response.value = (com.bizconf.vcaasz.soap.conf.ESpaceMeetingAsSoapResponseQueryAreaListResponse) _output.get(new javax.xml.namespace.QName("", "response"));
            } catch (java.lang.Exception _exception) {
                response.value = (com.bizconf.vcaasz.soap.conf.ESpaceMeetingAsSoapResponseQueryAreaListResponse) org.apache.axis.utils.JavaUtils.convert(_output.get(new javax.xml.namespace.QName("", "response")), com.bizconf.vcaasz.soap.conf.ESpaceMeetingAsSoapResponseQueryAreaListResponse.class);
            }
            try {
                return (com.bizconf.vcaasz.soap.conf.ESpaceMeetingAsSoapResult) _resp;
            } catch (java.lang.Exception _exception) {
                return (com.bizconf.vcaasz.soap.conf.ESpaceMeetingAsSoapResult) org.apache.axis.utils.JavaUtils.convert(_resp, com.bizconf.vcaasz.soap.conf.ESpaceMeetingAsSoapResult.class);
            }
        }
  } catch (org.apache.axis.AxisFault axisFaultException) {
  throw axisFaultException;
}
    }

    public com.bizconf.vcaasz.soap.conf.ESpaceMeetingAsSoapResult queryAreaByIP(com.bizconf.vcaasz.soap.conf.ESpaceMeetingAsSoapRequestQueryAreaByIPRequest request, com.bizconf.vcaasz.soap.conf.holders.ESpaceMeetingAsSoapResponseQueryAreaByIPResponseHolder response) throws java.rmi.RemoteException {
        if (super.cachedEndpoint == null) {
            throw new org.apache.axis.NoEndPointException();
        }
        org.apache.axis.client.Call _call = createCall();
        _call.setOperation(_operations[9]);
        _call.setUseSOAPAction(true);
        _call.setSOAPActionURI("eSpaceMeeting.as.soap.BusinessService#queryAreaByIP");
        _call.setEncodingStyle(null);
        _call.setProperty(org.apache.axis.client.Call.SEND_TYPE_ATTR, Boolean.FALSE);
        _call.setProperty(org.apache.axis.AxisEngine.PROP_DOMULTIREFS, Boolean.FALSE);
        _call.setSOAPVersion(org.apache.axis.soap.SOAPConstants.SOAP11_CONSTANTS);
        _call.setOperationName(new javax.xml.namespace.QName("eSpaceMeeting.as.soap.BusinessService", "queryAreaByIP"));

        setRequestHeaders(_call);
        setAttachments(_call);
 try {        java.lang.Object _resp = _call.invoke(new java.lang.Object[] {request});

        if (_resp instanceof java.rmi.RemoteException) {
            throw (java.rmi.RemoteException)_resp;
        }
        else {
            extractAttachments(_call);
            java.util.Map _output;
            _output = _call.getOutputParams();
            try {
                response.value = (com.bizconf.vcaasz.soap.conf.ESpaceMeetingAsSoapResponseQueryAreaByIPResponse) _output.get(new javax.xml.namespace.QName("", "response"));
            } catch (java.lang.Exception _exception) {
                response.value = (com.bizconf.vcaasz.soap.conf.ESpaceMeetingAsSoapResponseQueryAreaByIPResponse) org.apache.axis.utils.JavaUtils.convert(_output.get(new javax.xml.namespace.QName("", "response")), com.bizconf.vcaasz.soap.conf.ESpaceMeetingAsSoapResponseQueryAreaByIPResponse.class);
            }
            try {
                return (com.bizconf.vcaasz.soap.conf.ESpaceMeetingAsSoapResult) _resp;
            } catch (java.lang.Exception _exception) {
                return (com.bizconf.vcaasz.soap.conf.ESpaceMeetingAsSoapResult) org.apache.axis.utils.JavaUtils.convert(_resp, com.bizconf.vcaasz.soap.conf.ESpaceMeetingAsSoapResult.class);
            }
        }
  } catch (org.apache.axis.AxisFault axisFaultException) {
  throw axisFaultException;
}
    }

    public com.bizconf.vcaasz.soap.conf.ESpaceMeetingAsSoapResult addEnterpriseAreaMap(com.bizconf.vcaasz.soap.conf.ESpaceMeetingAsSoapRequestAddEnterpriseAreaMapRequest request) throws java.rmi.RemoteException {
        if (super.cachedEndpoint == null) {
            throw new org.apache.axis.NoEndPointException();
        }
        org.apache.axis.client.Call _call = createCall();
        _call.setOperation(_operations[10]);
        _call.setUseSOAPAction(true);
        _call.setSOAPActionURI("eSpaceMeeting.as.soap.BusinessService#addEnterpriseAreaMap");
        _call.setEncodingStyle(null);
        _call.setProperty(org.apache.axis.client.Call.SEND_TYPE_ATTR, Boolean.FALSE);
        _call.setProperty(org.apache.axis.AxisEngine.PROP_DOMULTIREFS, Boolean.FALSE);
        _call.setSOAPVersion(org.apache.axis.soap.SOAPConstants.SOAP11_CONSTANTS);
        _call.setOperationName(new javax.xml.namespace.QName("eSpaceMeeting.as.soap.BusinessService", "addEnterpriseAreaMap"));

        setRequestHeaders(_call);
        setAttachments(_call);
 try {        java.lang.Object _resp = _call.invoke(new java.lang.Object[] {request});

        if (_resp instanceof java.rmi.RemoteException) {
            throw (java.rmi.RemoteException)_resp;
        }
        else {
            extractAttachments(_call);
            try {
                return (com.bizconf.vcaasz.soap.conf.ESpaceMeetingAsSoapResult) _resp;
            } catch (java.lang.Exception _exception) {
                return (com.bizconf.vcaasz.soap.conf.ESpaceMeetingAsSoapResult) org.apache.axis.utils.JavaUtils.convert(_resp, com.bizconf.vcaasz.soap.conf.ESpaceMeetingAsSoapResult.class);
            }
        }
  } catch (org.apache.axis.AxisFault axisFaultException) {
  throw axisFaultException;
}
    }

    public com.bizconf.vcaasz.soap.conf.ESpaceMeetingAsSoapResult updateEnterpriseAreaMap(com.bizconf.vcaasz.soap.conf.ESpaceMeetingAsSoapRequestAddEnterpriseAreaMapRequest request) throws java.rmi.RemoteException {
        if (super.cachedEndpoint == null) {
            throw new org.apache.axis.NoEndPointException();
        }
        org.apache.axis.client.Call _call = createCall();
        _call.setOperation(_operations[11]);
        _call.setUseSOAPAction(true);
        _call.setSOAPActionURI("eSpaceMeeting.as.soap.BusinessService#updateEnterpriseAreaMap");
        _call.setEncodingStyle(null);
        _call.setProperty(org.apache.axis.client.Call.SEND_TYPE_ATTR, Boolean.FALSE);
        _call.setProperty(org.apache.axis.AxisEngine.PROP_DOMULTIREFS, Boolean.FALSE);
        _call.setSOAPVersion(org.apache.axis.soap.SOAPConstants.SOAP11_CONSTANTS);
        _call.setOperationName(new javax.xml.namespace.QName("eSpaceMeeting.as.soap.BusinessService", "updateEnterpriseAreaMap"));

        setRequestHeaders(_call);
        setAttachments(_call);
 try {        java.lang.Object _resp = _call.invoke(new java.lang.Object[] {request});

        if (_resp instanceof java.rmi.RemoteException) {
            throw (java.rmi.RemoteException)_resp;
        }
        else {
            extractAttachments(_call);
            try {
                return (com.bizconf.vcaasz.soap.conf.ESpaceMeetingAsSoapResult) _resp;
            } catch (java.lang.Exception _exception) {
                return (com.bizconf.vcaasz.soap.conf.ESpaceMeetingAsSoapResult) org.apache.axis.utils.JavaUtils.convert(_resp, com.bizconf.vcaasz.soap.conf.ESpaceMeetingAsSoapResult.class);
            }
        }
  } catch (org.apache.axis.AxisFault axisFaultException) {
  throw axisFaultException;
}
    }

    public com.bizconf.vcaasz.soap.conf.ESpaceMeetingAsSoapResult removeEnterpriseAreaMap(com.bizconf.vcaasz.soap.conf.ESpaceMeetingAsSoapRequestRemoveEnterpriseAreaMapRequest request) throws java.rmi.RemoteException {
        if (super.cachedEndpoint == null) {
            throw new org.apache.axis.NoEndPointException();
        }
        org.apache.axis.client.Call _call = createCall();
        _call.setOperation(_operations[12]);
        _call.setUseSOAPAction(true);
        _call.setSOAPActionURI("eSpaceMeeting.as.soap.BusinessService#removeEnterpriseAreaMap");
        _call.setEncodingStyle(null);
        _call.setProperty(org.apache.axis.client.Call.SEND_TYPE_ATTR, Boolean.FALSE);
        _call.setProperty(org.apache.axis.AxisEngine.PROP_DOMULTIREFS, Boolean.FALSE);
        _call.setSOAPVersion(org.apache.axis.soap.SOAPConstants.SOAP11_CONSTANTS);
        _call.setOperationName(new javax.xml.namespace.QName("eSpaceMeeting.as.soap.BusinessService", "removeEnterpriseAreaMap"));

        setRequestHeaders(_call);
        setAttachments(_call);
 try {        java.lang.Object _resp = _call.invoke(new java.lang.Object[] {request});

        if (_resp instanceof java.rmi.RemoteException) {
            throw (java.rmi.RemoteException)_resp;
        }
        else {
            extractAttachments(_call);
            try {
                return (com.bizconf.vcaasz.soap.conf.ESpaceMeetingAsSoapResult) _resp;
            } catch (java.lang.Exception _exception) {
                return (com.bizconf.vcaasz.soap.conf.ESpaceMeetingAsSoapResult) org.apache.axis.utils.JavaUtils.convert(_resp, com.bizconf.vcaasz.soap.conf.ESpaceMeetingAsSoapResult.class);
            }
        }
  } catch (org.apache.axis.AxisFault axisFaultException) {
  throw axisFaultException;
}
    }

    public com.bizconf.vcaasz.soap.conf.ESpaceMeetingAsSoapResult queryEnterpriseAreaMapList(com.bizconf.vcaasz.soap.conf.ESpaceMeetingAsSoapRequestQueryEnterpriseAreaMapListRequest request, com.bizconf.vcaasz.soap.conf.holders.ESpaceMeetingAsSoapResponseQueryEnterpriseAreaMapListResponseHolder response) throws java.rmi.RemoteException {
        if (super.cachedEndpoint == null) {
            throw new org.apache.axis.NoEndPointException();
        }
        org.apache.axis.client.Call _call = createCall();
        _call.setOperation(_operations[13]);
        _call.setUseSOAPAction(true);
        _call.setSOAPActionURI("eSpaceMeeting.as.soap.BusinessService#queryEnterpriseAreaMapList");
        _call.setEncodingStyle(null);
        _call.setProperty(org.apache.axis.client.Call.SEND_TYPE_ATTR, Boolean.FALSE);
        _call.setProperty(org.apache.axis.AxisEngine.PROP_DOMULTIREFS, Boolean.FALSE);
        _call.setSOAPVersion(org.apache.axis.soap.SOAPConstants.SOAP11_CONSTANTS);
        _call.setOperationName(new javax.xml.namespace.QName("eSpaceMeeting.as.soap.BusinessService", "queryEnterpriseAreaMapList"));

        setRequestHeaders(_call);
        setAttachments(_call);
 try {        java.lang.Object _resp = _call.invoke(new java.lang.Object[] {request});

        if (_resp instanceof java.rmi.RemoteException) {
            throw (java.rmi.RemoteException)_resp;
        }
        else {
            extractAttachments(_call);
            java.util.Map _output;
            _output = _call.getOutputParams();
            try {
                response.value = (com.bizconf.vcaasz.soap.conf.ESpaceMeetingAsSoapResponseQueryEnterpriseAreaMapListResponse) _output.get(new javax.xml.namespace.QName("", "response"));
            } catch (java.lang.Exception _exception) {
                response.value = (com.bizconf.vcaasz.soap.conf.ESpaceMeetingAsSoapResponseQueryEnterpriseAreaMapListResponse) org.apache.axis.utils.JavaUtils.convert(_output.get(new javax.xml.namespace.QName("", "response")), com.bizconf.vcaasz.soap.conf.ESpaceMeetingAsSoapResponseQueryEnterpriseAreaMapListResponse.class);
            }
            try {
                return (com.bizconf.vcaasz.soap.conf.ESpaceMeetingAsSoapResult) _resp;
            } catch (java.lang.Exception _exception) {
                return (com.bizconf.vcaasz.soap.conf.ESpaceMeetingAsSoapResult) org.apache.axis.utils.JavaUtils.convert(_resp, com.bizconf.vcaasz.soap.conf.ESpaceMeetingAsSoapResult.class);
            }
        }
  } catch (org.apache.axis.AxisFault axisFaultException) {
  throw axisFaultException;
}
    }

    public com.bizconf.vcaasz.soap.conf.ESpaceMeetingAsSoapResult addMSCluster(com.bizconf.vcaasz.soap.conf.ESpaceMeetingAsSoapRequestAddMSClusterRequest request) throws java.rmi.RemoteException {
        if (super.cachedEndpoint == null) {
            throw new org.apache.axis.NoEndPointException();
        }
        org.apache.axis.client.Call _call = createCall();
        _call.setOperation(_operations[14]);
        _call.setUseSOAPAction(true);
        _call.setSOAPActionURI("eSpaceMeeting.as.soap.BusinessService#addMSCluster");
        _call.setEncodingStyle(null);
        _call.setProperty(org.apache.axis.client.Call.SEND_TYPE_ATTR, Boolean.FALSE);
        _call.setProperty(org.apache.axis.AxisEngine.PROP_DOMULTIREFS, Boolean.FALSE);
        _call.setSOAPVersion(org.apache.axis.soap.SOAPConstants.SOAP11_CONSTANTS);
        _call.setOperationName(new javax.xml.namespace.QName("eSpaceMeeting.as.soap.BusinessService", "addMSCluster"));

        setRequestHeaders(_call);
        setAttachments(_call);
 try {        java.lang.Object _resp = _call.invoke(new java.lang.Object[] {request});

        if (_resp instanceof java.rmi.RemoteException) {
            throw (java.rmi.RemoteException)_resp;
        }
        else {
            extractAttachments(_call);
            try {
                return (com.bizconf.vcaasz.soap.conf.ESpaceMeetingAsSoapResult) _resp;
            } catch (java.lang.Exception _exception) {
                return (com.bizconf.vcaasz.soap.conf.ESpaceMeetingAsSoapResult) org.apache.axis.utils.JavaUtils.convert(_resp, com.bizconf.vcaasz.soap.conf.ESpaceMeetingAsSoapResult.class);
            }
        }
  } catch (org.apache.axis.AxisFault axisFaultException) {
  throw axisFaultException;
}
    }

    public com.bizconf.vcaasz.soap.conf.ESpaceMeetingAsSoapResult updateMSCluster(com.bizconf.vcaasz.soap.conf.ESpaceMeetingAsSoapRequestAddMSClusterRequest request) throws java.rmi.RemoteException {
        if (super.cachedEndpoint == null) {
            throw new org.apache.axis.NoEndPointException();
        }
        org.apache.axis.client.Call _call = createCall();
        _call.setOperation(_operations[15]);
        _call.setUseSOAPAction(true);
        _call.setSOAPActionURI("eSpaceMeeting.as.soap.BusinessService#updateMSCluster");
        _call.setEncodingStyle(null);
        _call.setProperty(org.apache.axis.client.Call.SEND_TYPE_ATTR, Boolean.FALSE);
        _call.setProperty(org.apache.axis.AxisEngine.PROP_DOMULTIREFS, Boolean.FALSE);
        _call.setSOAPVersion(org.apache.axis.soap.SOAPConstants.SOAP11_CONSTANTS);
        _call.setOperationName(new javax.xml.namespace.QName("eSpaceMeeting.as.soap.BusinessService", "updateMSCluster"));

        setRequestHeaders(_call);
        setAttachments(_call);
 try {        java.lang.Object _resp = _call.invoke(new java.lang.Object[] {request});

        if (_resp instanceof java.rmi.RemoteException) {
            throw (java.rmi.RemoteException)_resp;
        }
        else {
            extractAttachments(_call);
            try {
                return (com.bizconf.vcaasz.soap.conf.ESpaceMeetingAsSoapResult) _resp;
            } catch (java.lang.Exception _exception) {
                return (com.bizconf.vcaasz.soap.conf.ESpaceMeetingAsSoapResult) org.apache.axis.utils.JavaUtils.convert(_resp, com.bizconf.vcaasz.soap.conf.ESpaceMeetingAsSoapResult.class);
            }
        }
  } catch (org.apache.axis.AxisFault axisFaultException) {
  throw axisFaultException;
}
    }

    public com.bizconf.vcaasz.soap.conf.ESpaceMeetingAsSoapResult removeMSCluster(com.bizconf.vcaasz.soap.conf.ESpaceMeetingAsSoapRequestRemoveMSClusterRequest request) throws java.rmi.RemoteException {
        if (super.cachedEndpoint == null) {
            throw new org.apache.axis.NoEndPointException();
        }
        org.apache.axis.client.Call _call = createCall();
        _call.setOperation(_operations[16]);
        _call.setUseSOAPAction(true);
        _call.setSOAPActionURI("eSpaceMeeting.as.soap.BusinessService#removeMSCluster");
        _call.setEncodingStyle(null);
        _call.setProperty(org.apache.axis.client.Call.SEND_TYPE_ATTR, Boolean.FALSE);
        _call.setProperty(org.apache.axis.AxisEngine.PROP_DOMULTIREFS, Boolean.FALSE);
        _call.setSOAPVersion(org.apache.axis.soap.SOAPConstants.SOAP11_CONSTANTS);
        _call.setOperationName(new javax.xml.namespace.QName("eSpaceMeeting.as.soap.BusinessService", "removeMSCluster"));

        setRequestHeaders(_call);
        setAttachments(_call);
 try {        java.lang.Object _resp = _call.invoke(new java.lang.Object[] {request});

        if (_resp instanceof java.rmi.RemoteException) {
            throw (java.rmi.RemoteException)_resp;
        }
        else {
            extractAttachments(_call);
            try {
                return (com.bizconf.vcaasz.soap.conf.ESpaceMeetingAsSoapResult) _resp;
            } catch (java.lang.Exception _exception) {
                return (com.bizconf.vcaasz.soap.conf.ESpaceMeetingAsSoapResult) org.apache.axis.utils.JavaUtils.convert(_resp, com.bizconf.vcaasz.soap.conf.ESpaceMeetingAsSoapResult.class);
            }
        }
  } catch (org.apache.axis.AxisFault axisFaultException) {
  throw axisFaultException;
}
    }

    public com.bizconf.vcaasz.soap.conf.ESpaceMeetingAsSoapResult queryMSClusterList(com.bizconf.vcaasz.soap.conf.ESpaceMeetingAsSoapRequestQueryMSClusterListRequest request, com.bizconf.vcaasz.soap.conf.holders.ESpaceMeetingAsSoapResponseQueryMSClusterListResponseHolder response) throws java.rmi.RemoteException {
        if (super.cachedEndpoint == null) {
            throw new org.apache.axis.NoEndPointException();
        }
        org.apache.axis.client.Call _call = createCall();
        _call.setOperation(_operations[17]);
        _call.setUseSOAPAction(true);
        _call.setSOAPActionURI("eSpaceMeeting.as.soap.BusinessService#queryMSClusterList");
        _call.setEncodingStyle(null);
        _call.setProperty(org.apache.axis.client.Call.SEND_TYPE_ATTR, Boolean.FALSE);
        _call.setProperty(org.apache.axis.AxisEngine.PROP_DOMULTIREFS, Boolean.FALSE);
        _call.setSOAPVersion(org.apache.axis.soap.SOAPConstants.SOAP11_CONSTANTS);
        _call.setOperationName(new javax.xml.namespace.QName("eSpaceMeeting.as.soap.BusinessService", "queryMSClusterList"));

        setRequestHeaders(_call);
        setAttachments(_call);
 try {        java.lang.Object _resp = _call.invoke(new java.lang.Object[] {request});

        if (_resp instanceof java.rmi.RemoteException) {
            throw (java.rmi.RemoteException)_resp;
        }
        else {
            extractAttachments(_call);
            java.util.Map _output;
            _output = _call.getOutputParams();
            try {
                response.value = (com.bizconf.vcaasz.soap.conf.ESpaceMeetingAsSoapResponseQueryMSClusterListResponse) _output.get(new javax.xml.namespace.QName("", "response"));
            } catch (java.lang.Exception _exception) {
                response.value = (com.bizconf.vcaasz.soap.conf.ESpaceMeetingAsSoapResponseQueryMSClusterListResponse) org.apache.axis.utils.JavaUtils.convert(_output.get(new javax.xml.namespace.QName("", "response")), com.bizconf.vcaasz.soap.conf.ESpaceMeetingAsSoapResponseQueryMSClusterListResponse.class);
            }
            try {
                return (com.bizconf.vcaasz.soap.conf.ESpaceMeetingAsSoapResult) _resp;
            } catch (java.lang.Exception _exception) {
                return (com.bizconf.vcaasz.soap.conf.ESpaceMeetingAsSoapResult) org.apache.axis.utils.JavaUtils.convert(_resp, com.bizconf.vcaasz.soap.conf.ESpaceMeetingAsSoapResult.class);
            }
        }
  } catch (org.apache.axis.AxisFault axisFaultException) {
  throw axisFaultException;
}
    }

    public com.bizconf.vcaasz.soap.conf.ESpaceMeetingAsSoapResult addMRS(com.bizconf.vcaasz.soap.conf.ESpaceMeetingAsSoapRequestAddMRSRequest request) throws java.rmi.RemoteException {
        if (super.cachedEndpoint == null) {
            throw new org.apache.axis.NoEndPointException();
        }
        org.apache.axis.client.Call _call = createCall();
        _call.setOperation(_operations[18]);
        _call.setUseSOAPAction(true);
        _call.setSOAPActionURI("eSpaceMeeting.as.soap.BusinessService#addMRS");
        _call.setEncodingStyle(null);
        _call.setProperty(org.apache.axis.client.Call.SEND_TYPE_ATTR, Boolean.FALSE);
        _call.setProperty(org.apache.axis.AxisEngine.PROP_DOMULTIREFS, Boolean.FALSE);
        _call.setSOAPVersion(org.apache.axis.soap.SOAPConstants.SOAP11_CONSTANTS);
        _call.setOperationName(new javax.xml.namespace.QName("eSpaceMeeting.as.soap.BusinessService", "addMRS"));

        setRequestHeaders(_call);
        setAttachments(_call);
 try {        java.lang.Object _resp = _call.invoke(new java.lang.Object[] {request});

        if (_resp instanceof java.rmi.RemoteException) {
            throw (java.rmi.RemoteException)_resp;
        }
        else {
            extractAttachments(_call);
            try {
                return (com.bizconf.vcaasz.soap.conf.ESpaceMeetingAsSoapResult) _resp;
            } catch (java.lang.Exception _exception) {
                return (com.bizconf.vcaasz.soap.conf.ESpaceMeetingAsSoapResult) org.apache.axis.utils.JavaUtils.convert(_resp, com.bizconf.vcaasz.soap.conf.ESpaceMeetingAsSoapResult.class);
            }
        }
  } catch (org.apache.axis.AxisFault axisFaultException) {
  throw axisFaultException;
}
    }

    public com.bizconf.vcaasz.soap.conf.ESpaceMeetingAsSoapResult updateMRS(com.bizconf.vcaasz.soap.conf.ESpaceMeetingAsSoapRequestAddMRSRequest request) throws java.rmi.RemoteException {
        if (super.cachedEndpoint == null) {
            throw new org.apache.axis.NoEndPointException();
        }
        org.apache.axis.client.Call _call = createCall();
        _call.setOperation(_operations[19]);
        _call.setUseSOAPAction(true);
        _call.setSOAPActionURI("eSpaceMeeting.as.soap.BusinessService#updateMRS");
        _call.setEncodingStyle(null);
        _call.setProperty(org.apache.axis.client.Call.SEND_TYPE_ATTR, Boolean.FALSE);
        _call.setProperty(org.apache.axis.AxisEngine.PROP_DOMULTIREFS, Boolean.FALSE);
        _call.setSOAPVersion(org.apache.axis.soap.SOAPConstants.SOAP11_CONSTANTS);
        _call.setOperationName(new javax.xml.namespace.QName("eSpaceMeeting.as.soap.BusinessService", "updateMRS"));

        setRequestHeaders(_call);
        setAttachments(_call);
 try {        java.lang.Object _resp = _call.invoke(new java.lang.Object[] {request});

        if (_resp instanceof java.rmi.RemoteException) {
            throw (java.rmi.RemoteException)_resp;
        }
        else {
            extractAttachments(_call);
            try {
                return (com.bizconf.vcaasz.soap.conf.ESpaceMeetingAsSoapResult) _resp;
            } catch (java.lang.Exception _exception) {
                return (com.bizconf.vcaasz.soap.conf.ESpaceMeetingAsSoapResult) org.apache.axis.utils.JavaUtils.convert(_resp, com.bizconf.vcaasz.soap.conf.ESpaceMeetingAsSoapResult.class);
            }
        }
  } catch (org.apache.axis.AxisFault axisFaultException) {
  throw axisFaultException;
}
    }

    public com.bizconf.vcaasz.soap.conf.ESpaceMeetingAsSoapResult removeMRS(com.bizconf.vcaasz.soap.conf.ESpaceMeetingAsSoapRequestRemoveMRSRequest request) throws java.rmi.RemoteException {
        if (super.cachedEndpoint == null) {
            throw new org.apache.axis.NoEndPointException();
        }
        org.apache.axis.client.Call _call = createCall();
        _call.setOperation(_operations[20]);
        _call.setUseSOAPAction(true);
        _call.setSOAPActionURI("eSpaceMeeting.as.soap.BusinessService#removeMRS");
        _call.setEncodingStyle(null);
        _call.setProperty(org.apache.axis.client.Call.SEND_TYPE_ATTR, Boolean.FALSE);
        _call.setProperty(org.apache.axis.AxisEngine.PROP_DOMULTIREFS, Boolean.FALSE);
        _call.setSOAPVersion(org.apache.axis.soap.SOAPConstants.SOAP11_CONSTANTS);
        _call.setOperationName(new javax.xml.namespace.QName("eSpaceMeeting.as.soap.BusinessService", "removeMRS"));

        setRequestHeaders(_call);
        setAttachments(_call);
 try {        java.lang.Object _resp = _call.invoke(new java.lang.Object[] {request});

        if (_resp instanceof java.rmi.RemoteException) {
            throw (java.rmi.RemoteException)_resp;
        }
        else {
            extractAttachments(_call);
            try {
                return (com.bizconf.vcaasz.soap.conf.ESpaceMeetingAsSoapResult) _resp;
            } catch (java.lang.Exception _exception) {
                return (com.bizconf.vcaasz.soap.conf.ESpaceMeetingAsSoapResult) org.apache.axis.utils.JavaUtils.convert(_resp, com.bizconf.vcaasz.soap.conf.ESpaceMeetingAsSoapResult.class);
            }
        }
  } catch (org.apache.axis.AxisFault axisFaultException) {
  throw axisFaultException;
}
    }

    public com.bizconf.vcaasz.soap.conf.ESpaceMeetingAsSoapResult queryMRSList(com.bizconf.vcaasz.soap.conf.ESpaceMeetingAsSoapRequestQueryMRSListRequest request, com.bizconf.vcaasz.soap.conf.holders.ESpaceMeetingAsSoapResponseQueryMRSListResponseHolder response) throws java.rmi.RemoteException {
        if (super.cachedEndpoint == null) {
            throw new org.apache.axis.NoEndPointException();
        }
        org.apache.axis.client.Call _call = createCall();
        _call.setOperation(_operations[21]);
        _call.setUseSOAPAction(true);
        _call.setSOAPActionURI("eSpaceMeeting.as.soap.BusinessService#queryMRSList");
        _call.setEncodingStyle(null);
        _call.setProperty(org.apache.axis.client.Call.SEND_TYPE_ATTR, Boolean.FALSE);
        _call.setProperty(org.apache.axis.AxisEngine.PROP_DOMULTIREFS, Boolean.FALSE);
        _call.setSOAPVersion(org.apache.axis.soap.SOAPConstants.SOAP11_CONSTANTS);
        _call.setOperationName(new javax.xml.namespace.QName("eSpaceMeeting.as.soap.BusinessService", "queryMRSList"));

        setRequestHeaders(_call);
        setAttachments(_call);
 try {        java.lang.Object _resp = _call.invoke(new java.lang.Object[] {request});

        if (_resp instanceof java.rmi.RemoteException) {
            throw (java.rmi.RemoteException)_resp;
        }
        else {
            extractAttachments(_call);
            java.util.Map _output;
            _output = _call.getOutputParams();
            try {
                response.value = (com.bizconf.vcaasz.soap.conf.ESpaceMeetingAsSoapResponseQueryMRSListResponse) _output.get(new javax.xml.namespace.QName("", "response"));
            } catch (java.lang.Exception _exception) {
                response.value = (com.bizconf.vcaasz.soap.conf.ESpaceMeetingAsSoapResponseQueryMRSListResponse) org.apache.axis.utils.JavaUtils.convert(_output.get(new javax.xml.namespace.QName("", "response")), com.bizconf.vcaasz.soap.conf.ESpaceMeetingAsSoapResponseQueryMRSListResponse.class);
            }
            try {
                return (com.bizconf.vcaasz.soap.conf.ESpaceMeetingAsSoapResult) _resp;
            } catch (java.lang.Exception _exception) {
                return (com.bizconf.vcaasz.soap.conf.ESpaceMeetingAsSoapResult) org.apache.axis.utils.JavaUtils.convert(_resp, com.bizconf.vcaasz.soap.conf.ESpaceMeetingAsSoapResult.class);
            }
        }
  } catch (org.apache.axis.AxisFault axisFaultException) {
  throw axisFaultException;
}
    }

    public com.bizconf.vcaasz.soap.conf.ESpaceMeetingAsSoapResult addIPSeg(com.bizconf.vcaasz.soap.conf.ESpaceMeetingAsSoapRequestAddIPSegRequest request) throws java.rmi.RemoteException {
        if (super.cachedEndpoint == null) {
            throw new org.apache.axis.NoEndPointException();
        }
        org.apache.axis.client.Call _call = createCall();
        _call.setOperation(_operations[22]);
        _call.setUseSOAPAction(true);
        _call.setSOAPActionURI("eSpaceMeeting.as.soap.BusinessService#addIPSeg");
        _call.setEncodingStyle(null);
        _call.setProperty(org.apache.axis.client.Call.SEND_TYPE_ATTR, Boolean.FALSE);
        _call.setProperty(org.apache.axis.AxisEngine.PROP_DOMULTIREFS, Boolean.FALSE);
        _call.setSOAPVersion(org.apache.axis.soap.SOAPConstants.SOAP11_CONSTANTS);
        _call.setOperationName(new javax.xml.namespace.QName("eSpaceMeeting.as.soap.BusinessService", "addIPSeg"));

        setRequestHeaders(_call);
        setAttachments(_call);
 try {        java.lang.Object _resp = _call.invoke(new java.lang.Object[] {request});

        if (_resp instanceof java.rmi.RemoteException) {
            throw (java.rmi.RemoteException)_resp;
        }
        else {
            extractAttachments(_call);
            try {
                return (com.bizconf.vcaasz.soap.conf.ESpaceMeetingAsSoapResult) _resp;
            } catch (java.lang.Exception _exception) {
                return (com.bizconf.vcaasz.soap.conf.ESpaceMeetingAsSoapResult) org.apache.axis.utils.JavaUtils.convert(_resp, com.bizconf.vcaasz.soap.conf.ESpaceMeetingAsSoapResult.class);
            }
        }
  } catch (org.apache.axis.AxisFault axisFaultException) {
  throw axisFaultException;
}
    }

    public com.bizconf.vcaasz.soap.conf.ESpaceMeetingAsSoapResult updateIPSeg(com.bizconf.vcaasz.soap.conf.ESpaceMeetingAsSoapRequestAddIPSegRequest request) throws java.rmi.RemoteException {
        if (super.cachedEndpoint == null) {
            throw new org.apache.axis.NoEndPointException();
        }
        org.apache.axis.client.Call _call = createCall();
        _call.setOperation(_operations[23]);
        _call.setUseSOAPAction(true);
        _call.setSOAPActionURI("eSpaceMeeting.as.soap.BusinessService#updateIPSeg");
        _call.setEncodingStyle(null);
        _call.setProperty(org.apache.axis.client.Call.SEND_TYPE_ATTR, Boolean.FALSE);
        _call.setProperty(org.apache.axis.AxisEngine.PROP_DOMULTIREFS, Boolean.FALSE);
        _call.setSOAPVersion(org.apache.axis.soap.SOAPConstants.SOAP11_CONSTANTS);
        _call.setOperationName(new javax.xml.namespace.QName("eSpaceMeeting.as.soap.BusinessService", "updateIPSeg"));

        setRequestHeaders(_call);
        setAttachments(_call);
 try {        java.lang.Object _resp = _call.invoke(new java.lang.Object[] {request});

        if (_resp instanceof java.rmi.RemoteException) {
            throw (java.rmi.RemoteException)_resp;
        }
        else {
            extractAttachments(_call);
            try {
                return (com.bizconf.vcaasz.soap.conf.ESpaceMeetingAsSoapResult) _resp;
            } catch (java.lang.Exception _exception) {
                return (com.bizconf.vcaasz.soap.conf.ESpaceMeetingAsSoapResult) org.apache.axis.utils.JavaUtils.convert(_resp, com.bizconf.vcaasz.soap.conf.ESpaceMeetingAsSoapResult.class);
            }
        }
  } catch (org.apache.axis.AxisFault axisFaultException) {
  throw axisFaultException;
}
    }

    public com.bizconf.vcaasz.soap.conf.ESpaceMeetingAsSoapResult removeIPSeg(com.bizconf.vcaasz.soap.conf.ESpaceMeetingAsSoapRequestRemoveIPSegRequest request) throws java.rmi.RemoteException {
        if (super.cachedEndpoint == null) {
            throw new org.apache.axis.NoEndPointException();
        }
        org.apache.axis.client.Call _call = createCall();
        _call.setOperation(_operations[24]);
        _call.setUseSOAPAction(true);
        _call.setSOAPActionURI("eSpaceMeeting.as.soap.BusinessService#removeIPSeg");
        _call.setEncodingStyle(null);
        _call.setProperty(org.apache.axis.client.Call.SEND_TYPE_ATTR, Boolean.FALSE);
        _call.setProperty(org.apache.axis.AxisEngine.PROP_DOMULTIREFS, Boolean.FALSE);
        _call.setSOAPVersion(org.apache.axis.soap.SOAPConstants.SOAP11_CONSTANTS);
        _call.setOperationName(new javax.xml.namespace.QName("eSpaceMeeting.as.soap.BusinessService", "removeIPSeg"));

        setRequestHeaders(_call);
        setAttachments(_call);
 try {        java.lang.Object _resp = _call.invoke(new java.lang.Object[] {request});

        if (_resp instanceof java.rmi.RemoteException) {
            throw (java.rmi.RemoteException)_resp;
        }
        else {
            extractAttachments(_call);
            try {
                return (com.bizconf.vcaasz.soap.conf.ESpaceMeetingAsSoapResult) _resp;
            } catch (java.lang.Exception _exception) {
                return (com.bizconf.vcaasz.soap.conf.ESpaceMeetingAsSoapResult) org.apache.axis.utils.JavaUtils.convert(_resp, com.bizconf.vcaasz.soap.conf.ESpaceMeetingAsSoapResult.class);
            }
        }
  } catch (org.apache.axis.AxisFault axisFaultException) {
  throw axisFaultException;
}
    }

    public com.bizconf.vcaasz.soap.conf.ESpaceMeetingAsSoapResult queryIPSegList(com.bizconf.vcaasz.soap.conf.ESpaceMeetingAsSoapRequestQueryIPSegListRequest request, com.bizconf.vcaasz.soap.conf.holders.ESpaceMeetingAsSoapResponseQueryIPSegListResponseHolder response) throws java.rmi.RemoteException {
        if (super.cachedEndpoint == null) {
            throw new org.apache.axis.NoEndPointException();
        }
        org.apache.axis.client.Call _call = createCall();
        _call.setOperation(_operations[25]);
        _call.setUseSOAPAction(true);
        _call.setSOAPActionURI("eSpaceMeeting.as.soap.BusinessService#queryIPSegList");
        _call.setEncodingStyle(null);
        _call.setProperty(org.apache.axis.client.Call.SEND_TYPE_ATTR, Boolean.FALSE);
        _call.setProperty(org.apache.axis.AxisEngine.PROP_DOMULTIREFS, Boolean.FALSE);
        _call.setSOAPVersion(org.apache.axis.soap.SOAPConstants.SOAP11_CONSTANTS);
        _call.setOperationName(new javax.xml.namespace.QName("eSpaceMeeting.as.soap.BusinessService", "queryIPSegList"));

        setRequestHeaders(_call);
        setAttachments(_call);
 try {        java.lang.Object _resp = _call.invoke(new java.lang.Object[] {request});

        if (_resp instanceof java.rmi.RemoteException) {
            throw (java.rmi.RemoteException)_resp;
        }
        else {
            extractAttachments(_call);
            java.util.Map _output;
            _output = _call.getOutputParams();
            try {
                response.value = (com.bizconf.vcaasz.soap.conf.ESpaceMeetingAsSoapResponseQueryIPSegListResponse) _output.get(new javax.xml.namespace.QName("", "response"));
            } catch (java.lang.Exception _exception) {
                response.value = (com.bizconf.vcaasz.soap.conf.ESpaceMeetingAsSoapResponseQueryIPSegListResponse) org.apache.axis.utils.JavaUtils.convert(_output.get(new javax.xml.namespace.QName("", "response")), com.bizconf.vcaasz.soap.conf.ESpaceMeetingAsSoapResponseQueryIPSegListResponse.class);
            }
            try {
                return (com.bizconf.vcaasz.soap.conf.ESpaceMeetingAsSoapResult) _resp;
            } catch (java.lang.Exception _exception) {
                return (com.bizconf.vcaasz.soap.conf.ESpaceMeetingAsSoapResult) org.apache.axis.utils.JavaUtils.convert(_resp, com.bizconf.vcaasz.soap.conf.ESpaceMeetingAsSoapResult.class);
            }
        }
  } catch (org.apache.axis.AxisFault axisFaultException) {
  throw axisFaultException;
}
    }

}
