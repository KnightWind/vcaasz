/**
 * ESpaceMeetingAsSoapBaseServicebindingStub.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Nov 19, 2006 (02:31:34 GMT+00:00) WSDL2Java emitter.
 */

package com.bizconf.vcaasz.soap.conf;

public class ESpaceMeetingAsSoapBaseServicebindingStub extends org.apache.axis.client.Stub implements com.bizconf.vcaasz.soap.conf.ESpaceMeetingAsSoapBaseService {
    private java.util.Vector cachedSerClasses = new java.util.Vector();
    private java.util.Vector cachedSerQNames = new java.util.Vector();
    private java.util.Vector cachedSerFactories = new java.util.Vector();
    private java.util.Vector cachedDeserFactories = new java.util.Vector();

    static org.apache.axis.description.OperationDesc [] _operations;

    static {
        _operations = new org.apache.axis.description.OperationDesc[8];
        _initOperationDesc1();
    }

    private static void _initOperationDesc1(){
        org.apache.axis.description.OperationDesc oper;
        org.apache.axis.description.ParameterDesc param;
        oper = new org.apache.axis.description.OperationDesc();
        oper.setName("getAppkey");
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("", "request"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("eSpaceMeeting", "eSpaceMeeting.as.soap.request.GetAppkeyRequest"), com.bizconf.vcaasz.soap.conf.ESpaceMeetingAsSoapRequestGetAppkeyRequest.class, false, false);
        oper.addParameter(param);
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("", "response"), org.apache.axis.description.ParameterDesc.OUT, new javax.xml.namespace.QName("eSpaceMeeting", "eSpaceMeeting.as.soap.response.GetAppkeyResponse"), com.bizconf.vcaasz.soap.conf.ESpaceMeetingAsSoapResponseGetAppkeyResponse.class, false, false);
        oper.addParameter(param);
        oper.setReturnType(new javax.xml.namespace.QName("eSpaceMeeting", "eSpaceMeeting.as.soap.Result"));
        oper.setReturnClass(com.bizconf.vcaasz.soap.conf.ESpaceMeetingAsSoapResult.class);
        oper.setReturnQName(new javax.xml.namespace.QName("", "_result"));
        oper.setStyle(org.apache.axis.constants.Style.RPC);
        oper.setUse(org.apache.axis.constants.Use.LITERAL);
        _operations[0] = oper;

        oper = new org.apache.axis.description.OperationDesc();
        oper.setName("heartbeat");
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("", "request"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("eSpaceMeeting", "eSpaceMeeting.as.soap.request.HeartbeatRequest"), com.bizconf.vcaasz.soap.conf.ESpaceMeetingAsSoapRequestHeartbeatRequest.class, false, false);
        oper.addParameter(param);
        oper.setReturnType(new javax.xml.namespace.QName("eSpaceMeeting", "eSpaceMeeting.as.soap.Result"));
        oper.setReturnClass(com.bizconf.vcaasz.soap.conf.ESpaceMeetingAsSoapResult.class);
        oper.setReturnQName(new javax.xml.namespace.QName("", "_result"));
        oper.setStyle(org.apache.axis.constants.Style.RPC);
        oper.setUse(org.apache.axis.constants.Use.LITERAL);
        _operations[1] = oper;

        oper = new org.apache.axis.description.OperationDesc();
        oper.setName("setSystemParam");
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("", "request"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("eSpaceMeeting", "eSpaceMeeting.as.soap.request.SetSystemParamRequest"), com.bizconf.vcaasz.soap.conf.ESpaceMeetingAsSoapRequestSetSystemParamRequest.class, false, false);
        oper.addParameter(param);
        oper.setReturnType(new javax.xml.namespace.QName("eSpaceMeeting", "eSpaceMeeting.as.soap.Result"));
        oper.setReturnClass(com.bizconf.vcaasz.soap.conf.ESpaceMeetingAsSoapResult.class);
        oper.setReturnQName(new javax.xml.namespace.QName("", "_result"));
        oper.setStyle(org.apache.axis.constants.Style.RPC);
        oper.setUse(org.apache.axis.constants.Use.LITERAL);
        _operations[2] = oper;

        oper = new org.apache.axis.description.OperationDesc();
        oper.setName("getSystemParam");
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("", "request"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("eSpaceMeeting", "eSpaceMeeting.as.soap.request.GetSystemParamRequest"), com.bizconf.vcaasz.soap.conf.ESpaceMeetingAsSoapRequestGetSystemParamRequest.class, false, false);
        oper.addParameter(param);
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("", "response"), org.apache.axis.description.ParameterDesc.OUT, new javax.xml.namespace.QName("eSpaceMeeting", "eSpaceMeeting.as.soap.response.GetSystemParamResponse"), com.bizconf.vcaasz.soap.conf.ESpaceMeetingAsSoapResponseGetSystemParamResponse.class, false, false);
        oper.addParameter(param);
        oper.setReturnType(new javax.xml.namespace.QName("eSpaceMeeting", "eSpaceMeeting.as.soap.Result"));
        oper.setReturnClass(com.bizconf.vcaasz.soap.conf.ESpaceMeetingAsSoapResult.class);
        oper.setReturnQName(new javax.xml.namespace.QName("", "_result"));
        oper.setStyle(org.apache.axis.constants.Style.RPC);
        oper.setUse(org.apache.axis.constants.Use.LITERAL);
        _operations[3] = oper;

        oper = new org.apache.axis.description.OperationDesc();
        oper.setName("getSystemLicense");
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("", "request"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("eSpaceMeeting", "eSpaceMeeting.as.soap.request.GetSystemLicenseRequest"), com.bizconf.vcaasz.soap.conf.ESpaceMeetingAsSoapRequestGetSystemLicenseRequest.class, false, false);
        oper.addParameter(param);
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("", "response"), org.apache.axis.description.ParameterDesc.OUT, new javax.xml.namespace.QName("eSpaceMeeting", "eSpaceMeeting.as.soap.response.GetSystemLicenseResponse"), com.bizconf.vcaasz.soap.conf.ESpaceMeetingAsSoapResponseGetSystemLicenseResponse.class, false, false);
        oper.addParameter(param);
        oper.setReturnType(new javax.xml.namespace.QName("eSpaceMeeting", "eSpaceMeeting.as.soap.Result"));
        oper.setReturnClass(com.bizconf.vcaasz.soap.conf.ESpaceMeetingAsSoapResult.class);
        oper.setReturnQName(new javax.xml.namespace.QName("", "_result"));
        oper.setStyle(org.apache.axis.constants.Style.RPC);
        oper.setUse(org.apache.axis.constants.Use.LITERAL);
        _operations[4] = oper;

        oper = new org.apache.axis.description.OperationDesc();
        oper.setName("getLicenseInfo");
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("", "request"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("eSpaceMeeting", "eSpaceMeeting.as.soap.request.GetLicenseInfoRequest"), com.bizconf.vcaasz.soap.conf.ESpaceMeetingAsSoapRequestGetLicenseInfoRequest.class, false, false);
        oper.addParameter(param);
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("", "response"), org.apache.axis.description.ParameterDesc.OUT, new javax.xml.namespace.QName("eSpaceMeeting", "eSpaceMeeting.as.soap.response.GetLicenseInfoResponse"), com.bizconf.vcaasz.soap.conf.ESpaceMeetingAsSoapResponseGetLicenseInfoResponse.class, false, false);
        oper.addParameter(param);
        oper.setReturnType(new javax.xml.namespace.QName("eSpaceMeeting", "eSpaceMeeting.as.soap.Result"));
        oper.setReturnClass(com.bizconf.vcaasz.soap.conf.ESpaceMeetingAsSoapResult.class);
        oper.setReturnQName(new javax.xml.namespace.QName("", "_result"));
        oper.setStyle(org.apache.axis.constants.Style.RPC);
        oper.setUse(org.apache.axis.constants.Use.LITERAL);
        _operations[5] = oper;

        oper = new org.apache.axis.description.OperationDesc();
        oper.setName("getESN");
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("", "request"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("eSpaceMeeting", "eSpaceMeeting.as.soap.request.GetESNRequest"), com.bizconf.vcaasz.soap.conf.ESpaceMeetingAsSoapRequestGetESNRequest.class, false, false);
        oper.addParameter(param);
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("", "response"), org.apache.axis.description.ParameterDesc.OUT, new javax.xml.namespace.QName("eSpaceMeeting", "eSpaceMeeting.as.soap.response.GetESNResponse"), com.bizconf.vcaasz.soap.conf.ESpaceMeetingAsSoapResponseGetESNResponse.class, false, false);
        oper.addParameter(param);
        oper.setReturnType(new javax.xml.namespace.QName("eSpaceMeeting", "eSpaceMeeting.as.soap.Result"));
        oper.setReturnClass(com.bizconf.vcaasz.soap.conf.ESpaceMeetingAsSoapResult.class);
        oper.setReturnQName(new javax.xml.namespace.QName("", "_result"));
        oper.setStyle(org.apache.axis.constants.Style.RPC);
        oper.setUse(org.apache.axis.constants.Use.LITERAL);
        _operations[6] = oper;

        oper = new org.apache.axis.description.OperationDesc();
        oper.setName("activeLicense");
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("", "request"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("eSpaceMeeting", "eSpaceMeeting.as.soap.request.ActiveLicenseRequest"), com.bizconf.vcaasz.soap.conf.ESpaceMeetingAsSoapRequestActiveLicenseRequest.class, false, false);
        oper.addParameter(param);
        oper.setReturnType(new javax.xml.namespace.QName("eSpaceMeeting", "eSpaceMeeting.as.soap.Result"));
        oper.setReturnClass(com.bizconf.vcaasz.soap.conf.ESpaceMeetingAsSoapResult.class);
        oper.setReturnQName(new javax.xml.namespace.QName("", "_result"));
        oper.setStyle(org.apache.axis.constants.Style.RPC);
        oper.setUse(org.apache.axis.constants.Use.LITERAL);
        _operations[7] = oper;

    }

    public ESpaceMeetingAsSoapBaseServicebindingStub() throws org.apache.axis.AxisFault {
         this(null);
    }

    public ESpaceMeetingAsSoapBaseServicebindingStub(java.net.URL endpointURL, javax.xml.rpc.Service service) throws org.apache.axis.AxisFault {
         this(service);
         super.cachedEndpoint = endpointURL;
    }

    public ESpaceMeetingAsSoapBaseServicebindingStub(javax.xml.rpc.Service service) throws org.apache.axis.AxisFault {
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

            qName = new javax.xml.namespace.QName("eSpaceMeeting", "eSpaceMeeting.as.soap.LicenseRes");
            cachedSerQNames.add(qName);
            cls = com.bizconf.vcaasz.soap.conf.ESpaceMeetingAsSoapLicenseRes.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("eSpaceMeeting", "eSpaceMeeting.as.soap.request.ActiveLicenseRequest");
            cachedSerQNames.add(qName);
            cls = com.bizconf.vcaasz.soap.conf.ESpaceMeetingAsSoapRequestActiveLicenseRequest.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("eSpaceMeeting", "eSpaceMeeting.as.soap.request.GetAppkeyRequest");
            cachedSerQNames.add(qName);
            cls = com.bizconf.vcaasz.soap.conf.ESpaceMeetingAsSoapRequestGetAppkeyRequest.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("eSpaceMeeting", "eSpaceMeeting.as.soap.request.GetESNRequest");
            cachedSerQNames.add(qName);
            cls = com.bizconf.vcaasz.soap.conf.ESpaceMeetingAsSoapRequestGetESNRequest.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("eSpaceMeeting", "eSpaceMeeting.as.soap.request.GetLicenseInfoRequest");
            cachedSerQNames.add(qName);
            cls = com.bizconf.vcaasz.soap.conf.ESpaceMeetingAsSoapRequestGetLicenseInfoRequest.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("eSpaceMeeting", "eSpaceMeeting.as.soap.request.GetSystemLicenseRequest");
            cachedSerQNames.add(qName);
            cls = com.bizconf.vcaasz.soap.conf.ESpaceMeetingAsSoapRequestGetSystemLicenseRequest.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("eSpaceMeeting", "eSpaceMeeting.as.soap.request.GetSystemParamRequest");
            cachedSerQNames.add(qName);
            cls = com.bizconf.vcaasz.soap.conf.ESpaceMeetingAsSoapRequestGetSystemParamRequest.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("eSpaceMeeting", "eSpaceMeeting.as.soap.request.HeartbeatRequest");
            cachedSerQNames.add(qName);
            cls = com.bizconf.vcaasz.soap.conf.ESpaceMeetingAsSoapRequestHeartbeatRequest.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("eSpaceMeeting", "eSpaceMeeting.as.soap.request.SetSystemParamRequest");
            cachedSerQNames.add(qName);
            cls = com.bizconf.vcaasz.soap.conf.ESpaceMeetingAsSoapRequestSetSystemParamRequest.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("eSpaceMeeting", "eSpaceMeeting.as.soap.response.GetAppkeyResponse");
            cachedSerQNames.add(qName);
            cls = com.bizconf.vcaasz.soap.conf.ESpaceMeetingAsSoapResponseGetAppkeyResponse.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("eSpaceMeeting", "eSpaceMeeting.as.soap.response.GetESNResponse");
            cachedSerQNames.add(qName);
            cls = com.bizconf.vcaasz.soap.conf.ESpaceMeetingAsSoapResponseGetESNResponse.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("eSpaceMeeting", "eSpaceMeeting.as.soap.response.GetLicenseInfoResponse");
            cachedSerQNames.add(qName);
            cls = com.bizconf.vcaasz.soap.conf.ESpaceMeetingAsSoapResponseGetLicenseInfoResponse.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("eSpaceMeeting", "eSpaceMeeting.as.soap.response.GetSystemLicenseResponse");
            cachedSerQNames.add(qName);
            cls = com.bizconf.vcaasz.soap.conf.ESpaceMeetingAsSoapResponseGetSystemLicenseResponse.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("eSpaceMeeting", "eSpaceMeeting.as.soap.response.GetSystemParamResponse");
            cachedSerQNames.add(qName);
            cls = com.bizconf.vcaasz.soap.conf.ESpaceMeetingAsSoapResponseGetSystemParamResponse.class;
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

            qName = new javax.xml.namespace.QName("eSpaceMeeting", "eSpaceMeeting.as.StringKVList");
            cachedSerQNames.add(qName);
            cls = com.bizconf.vcaasz.soap.conf.ESpaceMeetingAsStringKV[].class;
            cachedSerClasses.add(cls);
            qName = new javax.xml.namespace.QName("eSpaceMeeting", "eSpaceMeeting.as.StringKV");
            qName2 = new javax.xml.namespace.QName("", "item");
            cachedSerFactories.add(new org.apache.axis.encoding.ser.ArraySerializerFactory(qName, qName2));
            cachedDeserFactories.add(new org.apache.axis.encoding.ser.ArrayDeserializerFactory());

            qName = new javax.xml.namespace.QName("eSpaceMeeting", "eSpaceMeeting.as.StringList");
            cachedSerQNames.add(qName);
            cls = java.lang.String[].class;
            cachedSerClasses.add(cls);
            qName = new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string");
            qName2 = new javax.xml.namespace.QName("", "item");
            cachedSerFactories.add(new org.apache.axis.encoding.ser.ArraySerializerFactory(qName, qName2));
            cachedDeserFactories.add(new org.apache.axis.encoding.ser.ArrayDeserializerFactory());

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

    public com.bizconf.vcaasz.soap.conf.ESpaceMeetingAsSoapResult getAppkey(com.bizconf.vcaasz.soap.conf.ESpaceMeetingAsSoapRequestGetAppkeyRequest request, com.bizconf.vcaasz.soap.conf.holders.ESpaceMeetingAsSoapResponseGetAppkeyResponseHolder response) throws java.rmi.RemoteException {
        if (super.cachedEndpoint == null) {
            throw new org.apache.axis.NoEndPointException();
        }
        org.apache.axis.client.Call _call = createCall();
        _call.setOperation(_operations[0]);
        _call.setUseSOAPAction(true);
        _call.setSOAPActionURI("eSpaceMeeting.as.soap.BaseService#getAppkey");
        _call.setEncodingStyle(null);
        _call.setProperty(org.apache.axis.client.Call.SEND_TYPE_ATTR, Boolean.FALSE);
        _call.setProperty(org.apache.axis.AxisEngine.PROP_DOMULTIREFS, Boolean.FALSE);
        _call.setSOAPVersion(org.apache.axis.soap.SOAPConstants.SOAP11_CONSTANTS);
        _call.setOperationName(new javax.xml.namespace.QName("eSpaceMeeting.as.soap.BaseService", "getAppkey"));

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
                response.value = (com.bizconf.vcaasz.soap.conf.ESpaceMeetingAsSoapResponseGetAppkeyResponse) _output.get(new javax.xml.namespace.QName("", "response"));
            } catch (java.lang.Exception _exception) {
                response.value = (com.bizconf.vcaasz.soap.conf.ESpaceMeetingAsSoapResponseGetAppkeyResponse) org.apache.axis.utils.JavaUtils.convert(_output.get(new javax.xml.namespace.QName("", "response")), com.bizconf.vcaasz.soap.conf.ESpaceMeetingAsSoapResponseGetAppkeyResponse.class);
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

    public com.bizconf.vcaasz.soap.conf.ESpaceMeetingAsSoapResult heartbeat(com.bizconf.vcaasz.soap.conf.ESpaceMeetingAsSoapRequestHeartbeatRequest request) throws java.rmi.RemoteException {
        if (super.cachedEndpoint == null) {
            throw new org.apache.axis.NoEndPointException();
        }
        org.apache.axis.client.Call _call = createCall();
        _call.setOperation(_operations[1]);
        _call.setUseSOAPAction(true);
        _call.setSOAPActionURI("eSpaceMeeting.as.soap.BaseService#heartbeat");
        _call.setEncodingStyle(null);
        _call.setProperty(org.apache.axis.client.Call.SEND_TYPE_ATTR, Boolean.FALSE);
        _call.setProperty(org.apache.axis.AxisEngine.PROP_DOMULTIREFS, Boolean.FALSE);
        _call.setSOAPVersion(org.apache.axis.soap.SOAPConstants.SOAP11_CONSTANTS);
        _call.setOperationName(new javax.xml.namespace.QName("eSpaceMeeting.as.soap.BaseService", "heartbeat"));

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

    public com.bizconf.vcaasz.soap.conf.ESpaceMeetingAsSoapResult setSystemParam(com.bizconf.vcaasz.soap.conf.ESpaceMeetingAsSoapRequestSetSystemParamRequest request) throws java.rmi.RemoteException {
        if (super.cachedEndpoint == null) {
            throw new org.apache.axis.NoEndPointException();
        }
        org.apache.axis.client.Call _call = createCall();
        _call.setOperation(_operations[2]);
        _call.setUseSOAPAction(true);
        _call.setSOAPActionURI("eSpaceMeeting.as.soap.BaseService#setSystemParam");
        _call.setEncodingStyle(null);
        _call.setProperty(org.apache.axis.client.Call.SEND_TYPE_ATTR, Boolean.FALSE);
        _call.setProperty(org.apache.axis.AxisEngine.PROP_DOMULTIREFS, Boolean.FALSE);
        _call.setSOAPVersion(org.apache.axis.soap.SOAPConstants.SOAP11_CONSTANTS);
        _call.setOperationName(new javax.xml.namespace.QName("eSpaceMeeting.as.soap.BaseService", "setSystemParam"));

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

    public com.bizconf.vcaasz.soap.conf.ESpaceMeetingAsSoapResult getSystemParam(com.bizconf.vcaasz.soap.conf.ESpaceMeetingAsSoapRequestGetSystemParamRequest request, com.bizconf.vcaasz.soap.conf.holders.ESpaceMeetingAsSoapResponseGetSystemParamResponseHolder response) throws java.rmi.RemoteException {
        if (super.cachedEndpoint == null) {
            throw new org.apache.axis.NoEndPointException();
        }
        org.apache.axis.client.Call _call = createCall();
        _call.setOperation(_operations[3]);
        _call.setUseSOAPAction(true);
        _call.setSOAPActionURI("eSpaceMeeting.as.soap.BaseService#getSystemParam");
        _call.setEncodingStyle(null);
        _call.setProperty(org.apache.axis.client.Call.SEND_TYPE_ATTR, Boolean.FALSE);
        _call.setProperty(org.apache.axis.AxisEngine.PROP_DOMULTIREFS, Boolean.FALSE);
        _call.setSOAPVersion(org.apache.axis.soap.SOAPConstants.SOAP11_CONSTANTS);
        _call.setOperationName(new javax.xml.namespace.QName("eSpaceMeeting.as.soap.BaseService", "getSystemParam"));

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
                response.value = (com.bizconf.vcaasz.soap.conf.ESpaceMeetingAsSoapResponseGetSystemParamResponse) _output.get(new javax.xml.namespace.QName("", "response"));
            } catch (java.lang.Exception _exception) {
                response.value = (com.bizconf.vcaasz.soap.conf.ESpaceMeetingAsSoapResponseGetSystemParamResponse) org.apache.axis.utils.JavaUtils.convert(_output.get(new javax.xml.namespace.QName("", "response")), com.bizconf.vcaasz.soap.conf.ESpaceMeetingAsSoapResponseGetSystemParamResponse.class);
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

    public com.bizconf.vcaasz.soap.conf.ESpaceMeetingAsSoapResult getSystemLicense(com.bizconf.vcaasz.soap.conf.ESpaceMeetingAsSoapRequestGetSystemLicenseRequest request, com.bizconf.vcaasz.soap.conf.holders.ESpaceMeetingAsSoapResponseGetSystemLicenseResponseHolder response) throws java.rmi.RemoteException {
        if (super.cachedEndpoint == null) {
            throw new org.apache.axis.NoEndPointException();
        }
        org.apache.axis.client.Call _call = createCall();
        _call.setOperation(_operations[4]);
        _call.setUseSOAPAction(true);
        _call.setSOAPActionURI("eSpaceMeeting.as.soap.BaseService#getSystemLicense");
        _call.setEncodingStyle(null);
        _call.setProperty(org.apache.axis.client.Call.SEND_TYPE_ATTR, Boolean.FALSE);
        _call.setProperty(org.apache.axis.AxisEngine.PROP_DOMULTIREFS, Boolean.FALSE);
        _call.setSOAPVersion(org.apache.axis.soap.SOAPConstants.SOAP11_CONSTANTS);
        _call.setOperationName(new javax.xml.namespace.QName("eSpaceMeeting.as.soap.BaseService", "getSystemLicense"));

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
                response.value = (com.bizconf.vcaasz.soap.conf.ESpaceMeetingAsSoapResponseGetSystemLicenseResponse) _output.get(new javax.xml.namespace.QName("", "response"));
            } catch (java.lang.Exception _exception) {
                response.value = (com.bizconf.vcaasz.soap.conf.ESpaceMeetingAsSoapResponseGetSystemLicenseResponse) org.apache.axis.utils.JavaUtils.convert(_output.get(new javax.xml.namespace.QName("", "response")), com.bizconf.vcaasz.soap.conf.ESpaceMeetingAsSoapResponseGetSystemLicenseResponse.class);
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

    public com.bizconf.vcaasz.soap.conf.ESpaceMeetingAsSoapResult getLicenseInfo(com.bizconf.vcaasz.soap.conf.ESpaceMeetingAsSoapRequestGetLicenseInfoRequest request, com.bizconf.vcaasz.soap.conf.holders.ESpaceMeetingAsSoapResponseGetLicenseInfoResponseHolder response) throws java.rmi.RemoteException {
        if (super.cachedEndpoint == null) {
            throw new org.apache.axis.NoEndPointException();
        }
        org.apache.axis.client.Call _call = createCall();
        _call.setOperation(_operations[5]);
        _call.setUseSOAPAction(true);
        _call.setSOAPActionURI("eSpaceMeeting.as.soap.BaseService#getLicenseInfo");
        _call.setEncodingStyle(null);
        _call.setProperty(org.apache.axis.client.Call.SEND_TYPE_ATTR, Boolean.FALSE);
        _call.setProperty(org.apache.axis.AxisEngine.PROP_DOMULTIREFS, Boolean.FALSE);
        _call.setSOAPVersion(org.apache.axis.soap.SOAPConstants.SOAP11_CONSTANTS);
        _call.setOperationName(new javax.xml.namespace.QName("eSpaceMeeting.as.soap.BaseService", "getLicenseInfo"));

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
                response.value = (com.bizconf.vcaasz.soap.conf.ESpaceMeetingAsSoapResponseGetLicenseInfoResponse) _output.get(new javax.xml.namespace.QName("", "response"));
            } catch (java.lang.Exception _exception) {
                response.value = (com.bizconf.vcaasz.soap.conf.ESpaceMeetingAsSoapResponseGetLicenseInfoResponse) org.apache.axis.utils.JavaUtils.convert(_output.get(new javax.xml.namespace.QName("", "response")), com.bizconf.vcaasz.soap.conf.ESpaceMeetingAsSoapResponseGetLicenseInfoResponse.class);
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

    public com.bizconf.vcaasz.soap.conf.ESpaceMeetingAsSoapResult getESN(com.bizconf.vcaasz.soap.conf.ESpaceMeetingAsSoapRequestGetESNRequest request, com.bizconf.vcaasz.soap.conf.holders.ESpaceMeetingAsSoapResponseGetESNResponseHolder response) throws java.rmi.RemoteException {
        if (super.cachedEndpoint == null) {
            throw new org.apache.axis.NoEndPointException();
        }
        org.apache.axis.client.Call _call = createCall();
        _call.setOperation(_operations[6]);
        _call.setUseSOAPAction(true);
        _call.setSOAPActionURI("eSpaceMeeting.as.soap.BaseService#getESN");
        _call.setEncodingStyle(null);
        _call.setProperty(org.apache.axis.client.Call.SEND_TYPE_ATTR, Boolean.FALSE);
        _call.setProperty(org.apache.axis.AxisEngine.PROP_DOMULTIREFS, Boolean.FALSE);
        _call.setSOAPVersion(org.apache.axis.soap.SOAPConstants.SOAP11_CONSTANTS);
        _call.setOperationName(new javax.xml.namespace.QName("eSpaceMeeting.as.soap.BaseService", "getESN"));

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
                response.value = (com.bizconf.vcaasz.soap.conf.ESpaceMeetingAsSoapResponseGetESNResponse) _output.get(new javax.xml.namespace.QName("", "response"));
            } catch (java.lang.Exception _exception) {
                response.value = (com.bizconf.vcaasz.soap.conf.ESpaceMeetingAsSoapResponseGetESNResponse) org.apache.axis.utils.JavaUtils.convert(_output.get(new javax.xml.namespace.QName("", "response")), com.bizconf.vcaasz.soap.conf.ESpaceMeetingAsSoapResponseGetESNResponse.class);
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

    public com.bizconf.vcaasz.soap.conf.ESpaceMeetingAsSoapResult activeLicense(com.bizconf.vcaasz.soap.conf.ESpaceMeetingAsSoapRequestActiveLicenseRequest request) throws java.rmi.RemoteException {
        if (super.cachedEndpoint == null) {
            throw new org.apache.axis.NoEndPointException();
        }
        org.apache.axis.client.Call _call = createCall();
        _call.setOperation(_operations[7]);
        _call.setUseSOAPAction(true);
        _call.setSOAPActionURI("eSpaceMeeting.as.soap.BaseService#activeLicense");
        _call.setEncodingStyle(null);
        _call.setProperty(org.apache.axis.client.Call.SEND_TYPE_ATTR, Boolean.FALSE);
        _call.setProperty(org.apache.axis.AxisEngine.PROP_DOMULTIREFS, Boolean.FALSE);
        _call.setSOAPVersion(org.apache.axis.soap.SOAPConstants.SOAP11_CONSTANTS);
        _call.setOperationName(new javax.xml.namespace.QName("eSpaceMeeting.as.soap.BaseService", "activeLicense"));

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

}
