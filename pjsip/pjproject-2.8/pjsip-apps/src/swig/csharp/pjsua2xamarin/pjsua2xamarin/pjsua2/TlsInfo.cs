//------------------------------------------------------------------------------
// <auto-generated />
//
// This file was automatically generated by SWIG (http://www.swig.org).
// Version 3.0.12
//
// Do not make changes to this file unless you know what you are doing--modify
// the SWIG interface file instead.
//------------------------------------------------------------------------------

namespace pjsua2xamarin.pjsua2 {

public class TlsInfo : global::System.IDisposable {
  private global::System.Runtime.InteropServices.HandleRef swigCPtr;
  protected bool swigCMemOwn;

  internal TlsInfo(global::System.IntPtr cPtr, bool cMemoryOwn) {
    swigCMemOwn = cMemoryOwn;
    swigCPtr = new global::System.Runtime.InteropServices.HandleRef(this, cPtr);
  }

  internal static global::System.Runtime.InteropServices.HandleRef getCPtr(TlsInfo obj) {
    return (obj == null) ? new global::System.Runtime.InteropServices.HandleRef(null, global::System.IntPtr.Zero) : obj.swigCPtr;
  }

  ~TlsInfo() {
    Dispose();
  }

  public virtual void Dispose() {
    lock(this) {
      if (swigCPtr.Handle != global::System.IntPtr.Zero) {
        if (swigCMemOwn) {
          swigCMemOwn = false;
          pjsua2PINVOKE.delete_TlsInfo(swigCPtr);
        }
        swigCPtr = new global::System.Runtime.InteropServices.HandleRef(null, global::System.IntPtr.Zero);
      }
      global::System.GC.SuppressFinalize(this);
    }
  }

  public bool established {
    set {
      pjsua2PINVOKE.TlsInfo_established_set(swigCPtr, value);
    } 
    get {
      bool ret = pjsua2PINVOKE.TlsInfo_established_get(swigCPtr);
      return ret;
    } 
  }

  public uint protocol {
    set {
      pjsua2PINVOKE.TlsInfo_protocol_set(swigCPtr, value);
    } 
    get {
      uint ret = pjsua2PINVOKE.TlsInfo_protocol_get(swigCPtr);
      return ret;
    } 
  }

  public pj_ssl_cipher cipher {
    set {
      pjsua2PINVOKE.TlsInfo_cipher_set(swigCPtr, (int)value);
    } 
    get {
      pj_ssl_cipher ret = (pj_ssl_cipher)pjsua2PINVOKE.TlsInfo_cipher_get(swigCPtr);
      return ret;
    } 
  }

  public string cipherName {
    set {
      pjsua2PINVOKE.TlsInfo_cipherName_set(swigCPtr, value);
      if (pjsua2PINVOKE.SWIGPendingException.Pending) throw pjsua2PINVOKE.SWIGPendingException.Retrieve();
    } 
    get {
      string ret = pjsua2PINVOKE.TlsInfo_cipherName_get(swigCPtr);
      if (pjsua2PINVOKE.SWIGPendingException.Pending) throw pjsua2PINVOKE.SWIGPendingException.Retrieve();
      return ret;
    } 
  }

  public string localAddr {
    set {
      pjsua2PINVOKE.TlsInfo_localAddr_set(swigCPtr, value);
      if (pjsua2PINVOKE.SWIGPendingException.Pending) throw pjsua2PINVOKE.SWIGPendingException.Retrieve();
    } 
    get {
      string ret = pjsua2PINVOKE.TlsInfo_localAddr_get(swigCPtr);
      if (pjsua2PINVOKE.SWIGPendingException.Pending) throw pjsua2PINVOKE.SWIGPendingException.Retrieve();
      return ret;
    } 
  }

  public string remoteAddr {
    set {
      pjsua2PINVOKE.TlsInfo_remoteAddr_set(swigCPtr, value);
      if (pjsua2PINVOKE.SWIGPendingException.Pending) throw pjsua2PINVOKE.SWIGPendingException.Retrieve();
    } 
    get {
      string ret = pjsua2PINVOKE.TlsInfo_remoteAddr_get(swigCPtr);
      if (pjsua2PINVOKE.SWIGPendingException.Pending) throw pjsua2PINVOKE.SWIGPendingException.Retrieve();
      return ret;
    } 
  }

  public SslCertInfo localCertInfo {
    set {
      pjsua2PINVOKE.TlsInfo_localCertInfo_set(swigCPtr, SslCertInfo.getCPtr(value));
    } 
    get {
      global::System.IntPtr cPtr = pjsua2PINVOKE.TlsInfo_localCertInfo_get(swigCPtr);
      SslCertInfo ret = (cPtr == global::System.IntPtr.Zero) ? null : new SslCertInfo(cPtr, false);
      return ret;
    } 
  }

  public SslCertInfo remoteCertInfo {
    set {
      pjsua2PINVOKE.TlsInfo_remoteCertInfo_set(swigCPtr, SslCertInfo.getCPtr(value));
    } 
    get {
      global::System.IntPtr cPtr = pjsua2PINVOKE.TlsInfo_remoteCertInfo_get(swigCPtr);
      SslCertInfo ret = (cPtr == global::System.IntPtr.Zero) ? null : new SslCertInfo(cPtr, false);
      return ret;
    } 
  }

  public uint verifyStatus {
    set {
      pjsua2PINVOKE.TlsInfo_verifyStatus_set(swigCPtr, value);
    } 
    get {
      uint ret = pjsua2PINVOKE.TlsInfo_verifyStatus_get(swigCPtr);
      return ret;
    } 
  }

  public StringVector verifyMsgs {
    set {
      pjsua2PINVOKE.TlsInfo_verifyMsgs_set(swigCPtr, StringVector.getCPtr(value));
    } 
    get {
      global::System.IntPtr cPtr = pjsua2PINVOKE.TlsInfo_verifyMsgs_get(swigCPtr);
      StringVector ret = (cPtr == global::System.IntPtr.Zero) ? null : new StringVector(cPtr, false);
      return ret;
    } 
  }

  public TlsInfo() : this(pjsua2PINVOKE.new_TlsInfo(), true) {
  }

  public bool isEmpty() {
    bool ret = pjsua2PINVOKE.TlsInfo_isEmpty(swigCPtr);
    return ret;
  }

}

}
