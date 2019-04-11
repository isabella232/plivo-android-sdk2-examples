package com.plivo.plivosimplequickstart;

import android.util.Log;

import com.plivo.endpoint.Endpoint;
import com.plivo.endpoint.EventListener;
import com.plivo.endpoint.Incoming;
import com.plivo.endpoint.Outgoing;

import java.util.HashMap;

public class PlivoBackEnd implements EventListener {

    private static final String TAG = PlivoBackEnd.class.getSimpleName();

    enum STATE { IDLE, RINGING, ANSWERED, HANGUP, REJECTED, INVALID }

    private Endpoint endpoint;

    private BackendListener listener;

    static PlivoBackEnd newInstance() {
        return new PlivoBackEnd();
    }

    public void init(boolean log) {
        endpoint = Endpoint.newInstance(log, this);
    }

    public void setListener(BackendListener listener) {
        this.listener = listener;
    }

    public void login(String newToken) {
        endpoint.login(Utils.USERNAME, Utils.PASSWORD, newToken);
    }

    public void logout() {
        endpoint.logout();
    }

    public void relayIncomingPushData(HashMap<String, String> incomingData) {
        if (incomingData != null && !incomingData.isEmpty()) {
            endpoint.relayVoipPushNotification(incomingData);
        }
    }

    public Outgoing getOutgoing() {
        return endpoint.createOutgoingCall();
    }


    // Plivo SDK callbacks
    @Override
    public void onLogin() {
        Log.d(TAG, "onLogin success");
        if (listener != null) listener.onLogin(true);
    }

    @Override
    public void onLogout() {
        Log.d(TAG, "onLogout success");
        if (listener != null) listener.onLogout();
    }

    @Override
    public void onLoginFailed() {
        Log.e(TAG, "onLoginFailed");
        if (listener != null) listener.onLogin(false);
    }

    @Override
    public void onIncomingDigitNotification(String s) {
        if (listener != null) listener.onIncomingDigit(s);
    }

    @Override
    public void onIncomingCall(Incoming incoming) {
        Log.d(TAG, "onIncomingCall Ringing");
        if (listener != null) listener.onIncomingCall(incoming, STATE.RINGING);
    }

    @Override
    public void onIncomingCallHangup(Incoming incoming) {
        Log.d(TAG, "onIncomingCallHangup");
        if (listener != null) listener.onIncomingCall(incoming, STATE.HANGUP);
    }

    @Override
    public void onIncomingCallRejected(Incoming incoming) {
        Log.d(TAG, "onIncomingCallRejected");
        if (listener != null) listener.onIncomingCall(incoming, STATE.REJECTED);
    }

    @Override
    public void onOutgoingCall(Outgoing outgoing) {
        Log.d(TAG, "onOutgoingCall Ringing");
        if (listener != null) listener.onOutgoingCall(outgoing, STATE.RINGING);
    }

    @Override
    public void onOutgoingCallAnswered(Outgoing outgoing) {
        Log.d(TAG, "onOutgoingCall Answered");
        if (listener != null) listener.onOutgoingCall(outgoing, STATE.ANSWERED);
    }

    @Override
    public void onOutgoingCallRejected(Outgoing outgoing) {
        Log.d(TAG, "onOutgoingCall Rejected");
        if (listener != null) listener.onOutgoingCall(outgoing, STATE.REJECTED);
    }

    @Override
    public void onOutgoingCallHangup(Outgoing outgoing) {
        Log.d(TAG, "onOutgoingCall Hangup");
        if (listener != null) listener.onOutgoingCall(outgoing, STATE.HANGUP);
    }

    @Override
    public void onOutgoingCallInvalid(Outgoing outgoing) {
        Log.d(TAG, "onOutgoingCall Invalid");
        if (listener != null) listener.onOutgoingCall(outgoing, STATE.INVALID);
    }


    // Your own custom listener
    public interface BackendListener {
        void onLogin(boolean success);
        void onLogout();
        void onIncomingCall(Incoming data, STATE callState);
        void onOutgoingCall(Outgoing data, STATE callState);
        void onIncomingDigit(String digit);
    }
}