/* ----------------------------------------------------------------------------
 * This file was automatically generated by SWIG (http://www.swig.org).
 * Version 3.0.12
 *
 * Do not make changes to this file unless you know what you are doing--modify
 * the SWIG interface file instead.
 * ----------------------------------------------------------------------------- */

package com.plivo.endpoint.backend;

public class PlivoAppCallback {
  private transient long swigCPtr;
  protected transient boolean swigCMemOwn;

  protected PlivoAppCallback(long cPtr, boolean cMemoryOwn) {
    swigCMemOwn = cMemoryOwn;
    swigCPtr = cPtr;
  }

  protected static long getCPtr(PlivoAppCallback obj) {
    return (obj == null) ? 0 : obj.swigCPtr;
  }

  protected void finalize() {
    delete();
  }

  public synchronized void delete() {
    if (swigCPtr != 0) {
      if (swigCMemOwn) {
        swigCMemOwn = false;
        plivoJNI.delete_PlivoAppCallback(swigCPtr);
      }
      swigCPtr = 0;
    }
  }

  protected void swigDirectorDisconnect() {
    swigCMemOwn = false;
    delete();
  }

  public void swigReleaseOwnership() {
    swigCMemOwn = false;
    plivoJNI.PlivoAppCallback_change_ownership(this, swigCPtr, false);
  }

  public void swigTakeOwnership() {
    swigCMemOwn = true;
    plivoJNI.PlivoAppCallback_change_ownership(this, swigCPtr, true);
  }

  public void onStarted(String msg) {
    if (getClass() == PlivoAppCallback.class) plivoJNI.PlivoAppCallback_onStarted(swigCPtr, this, msg); else plivoJNI.PlivoAppCallback_onStartedSwigExplicitPlivoAppCallback(swigCPtr, this, msg);
  }

  public void onStopped(int restart) {
    if (getClass() == PlivoAppCallback.class) plivoJNI.PlivoAppCallback_onStopped(swigCPtr, this, restart); else plivoJNI.PlivoAppCallback_onStoppedSwigExplicitPlivoAppCallback(swigCPtr, this, restart);
  }

  public void onLogin() {
    if (getClass() == PlivoAppCallback.class) plivoJNI.PlivoAppCallback_onLogin(swigCPtr, this); else plivoJNI.PlivoAppCallback_onLoginSwigExplicitPlivoAppCallback(swigCPtr, this);
  }

  public void onLogout() {
    if (getClass() == PlivoAppCallback.class) plivoJNI.PlivoAppCallback_onLogout(swigCPtr, this); else plivoJNI.PlivoAppCallback_onLogoutSwigExplicitPlivoAppCallback(swigCPtr, this);
  }

  public void onLoginFailed() {
    if (getClass() == PlivoAppCallback.class) plivoJNI.PlivoAppCallback_onLoginFailed(swigCPtr, this); else plivoJNI.PlivoAppCallback_onLoginFailedSwigExplicitPlivoAppCallback(swigCPtr, this);
  }

  public void onDebugMessage(String msg) {
    if (getClass() == PlivoAppCallback.class) plivoJNI.PlivoAppCallback_onDebugMessage(swigCPtr, this, msg); else plivoJNI.PlivoAppCallback_onDebugMessageSwigExplicitPlivoAppCallback(swigCPtr, this, msg);
  }

  public void onIncomingCall(int callId, String sipCallId, String fromContact, String toContact, String header) {
    if (getClass() == PlivoAppCallback.class) plivoJNI.PlivoAppCallback_onIncomingCall(swigCPtr, this, callId, sipCallId, fromContact, toContact, header); else plivoJNI.PlivoAppCallback_onIncomingCallSwigExplicitPlivoAppCallback(swigCPtr, this, callId, sipCallId, fromContact, toContact, header);
  }

  public void onIncomingCallHangup(int callId, String sipCallId) {
    if (getClass() == PlivoAppCallback.class) plivoJNI.PlivoAppCallback_onIncomingCallHangup(swigCPtr, this, callId, sipCallId); else plivoJNI.PlivoAppCallback_onIncomingCallHangupSwigExplicitPlivoAppCallback(swigCPtr, this, callId, sipCallId);
  }

  public void onIncomingCallRejected(int callId, String sipCallId) {
    if (getClass() == PlivoAppCallback.class) plivoJNI.PlivoAppCallback_onIncomingCallRejected(swigCPtr, this, callId, sipCallId); else plivoJNI.PlivoAppCallback_onIncomingCallRejectedSwigExplicitPlivoAppCallback(swigCPtr, this, callId, sipCallId);
  }

  public void onOutgoingCall(int callId, String sipCallId) {
    if (getClass() == PlivoAppCallback.class) plivoJNI.PlivoAppCallback_onOutgoingCall(swigCPtr, this, callId, sipCallId); else plivoJNI.PlivoAppCallback_onOutgoingCallSwigExplicitPlivoAppCallback(swigCPtr, this, callId, sipCallId);
  }

  public void onOutgoingCallRinging(int callId, String sipCallId) {
    if (getClass() == PlivoAppCallback.class) plivoJNI.PlivoAppCallback_onOutgoingCallRinging(swigCPtr, this, callId, sipCallId); else plivoJNI.PlivoAppCallback_onOutgoingCallRingingSwigExplicitPlivoAppCallback(swigCPtr, this, callId, sipCallId);
  }

  public void onOutgoingCallRejected(int callId, String sipCallId) {
    if (getClass() == PlivoAppCallback.class) plivoJNI.PlivoAppCallback_onOutgoingCallRejected(swigCPtr, this, callId, sipCallId); else plivoJNI.PlivoAppCallback_onOutgoingCallRejectedSwigExplicitPlivoAppCallback(swigCPtr, this, callId, sipCallId);
  }

  public void onOutgoingCallAnswered(int callId, String sipCallId) {
    if (getClass() == PlivoAppCallback.class) plivoJNI.PlivoAppCallback_onOutgoingCallAnswered(swigCPtr, this, callId, sipCallId); else plivoJNI.PlivoAppCallback_onOutgoingCallAnsweredSwigExplicitPlivoAppCallback(swigCPtr, this, callId, sipCallId);
  }

  public void onOutgoingCallHangup(int callId, String sipCallId) {
    if (getClass() == PlivoAppCallback.class) plivoJNI.PlivoAppCallback_onOutgoingCallHangup(swigCPtr, this, callId, sipCallId); else plivoJNI.PlivoAppCallback_onOutgoingCallHangupSwigExplicitPlivoAppCallback(swigCPtr, this, callId, sipCallId);
  }

  public void onOutgoingCallInvalid(int callId, String sipCallId) {
    if (getClass() == PlivoAppCallback.class) plivoJNI.PlivoAppCallback_onOutgoingCallInvalid(swigCPtr, this, callId, sipCallId); else plivoJNI.PlivoAppCallback_onOutgoingCallInvalidSwigExplicitPlivoAppCallback(swigCPtr, this, callId, sipCallId);
  }

  public void onIncomingDigitNotification(int digit) {
    if (getClass() == PlivoAppCallback.class) plivoJNI.PlivoAppCallback_onIncomingDigitNotification(swigCPtr, this, digit); else plivoJNI.PlivoAppCallback_onIncomingDigitNotificationSwigExplicitPlivoAppCallback(swigCPtr, this, digit);
  }

  public PlivoAppCallback() {
    this(plivoJNI.new_PlivoAppCallback(), true);
    plivoJNI.PlivoAppCallback_director_connect(this, swigCPtr, swigCMemOwn, true);
  }

}