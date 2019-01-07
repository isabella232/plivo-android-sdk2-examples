package com.plivo.plivoaddressbook.screens.dial;

import android.app.Application;
import android.util.Log;

import com.plivo.plivoaddressbook.App;
import com.plivo.plivoaddressbook.BaseViewModel;
import com.plivo.plivoaddressbook.layer.plivo.PlivoBackend;
import com.plivo.plivoaddressbook.model.Call;
import com.plivo.plivoaddressbook.model.User;
import com.plivo.plivoaddressbook.utils.PreferencesUtils;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import androidx.annotation.NonNull;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

public class DialViewModel extends BaseViewModel {

    private static final String TAG = DialViewModel.class.getSimpleName();

    @Inject
    PlivoBackend backend;

    @Inject
    PreferencesUtils preferencesUtils;

    private MutableLiveData<Object> logoutSuccessObserver = new MutableLiveData<>();

    private MutableLiveData<Call> callStackObserver = new MutableLiveData<>();

    public DialViewModel(@NonNull Application application) {
        super(application);
        ((App) application).getAppComponent().inject(this);
    }

    LiveData<Object> logoutObserver() {
        return logoutSuccessObserver;
    }

    void logout() {
        if (!isLoggedIn()) {
            preferencesUtils.setLogin(false);
            logoutSuccessObserver.postValue(null);
        } else {
            getBackgroundTask().submit(() -> {
                backend.logout(() -> {
                    preferencesUtils.setLogin(false);
                    backend.clearCallStack();
                    logoutSuccessObserver.postValue(null);
                });
            });
        }
    }

    LiveData<Call> callStackObserver() {
        backend.setCallStackListener(call -> callStackObserver.postValue(call));
        return callStackObserver;
    }

    public LiveData<String> incomingDTMFObserver() {
        MutableLiveData<String> dtmf = new MutableLiveData<>();
        backend.setIncomingDTMFListener(digit -> dtmf.postValue(digit));
        return dtmf;
    }

    public void call(Call to) {
        if (to == null || to.getContact() == null) return;

        getBackgroundTask().submit(() -> {
            backend.outCall(to.getContact().getPhoneNumber());
        });
    }

    public void hangup() {
        getBackgroundTask().submit(() -> backend.hangUp());
    }

    public void reject() {
        getBackgroundTask().submit(() -> backend.reject());
    }

    public void answer() {
        getBackgroundTask().submit(() -> backend.answer());
    }

    public void mute() {
        getBackgroundTask().submit(() -> backend.mute());
    }

    public void unmute() {
        getBackgroundTask().submit(() -> backend.unMute());
    }

    public void hold() {
        getBackgroundTask().submit(() -> backend.hold());
    }

    public void unHold() {
        getBackgroundTask().submit(() -> backend.unHold());
    }

    public void sendDTMF(String digit) {
        getBackgroundTask().submit(() -> backend.sendDigit(digit));
    }

    boolean isLoggedIn() {
        return backend.isLoggedIn();
    }

    public User getLoggedInUser() {
        return preferencesUtils.getUser();
    }

    public Call getCurrentCall() { return backend.getCurrentCall(); }

    public List<Call> getAvailableCalls() {
        return backend.getAvailableCalls();
    }

    public List<Call> getOtherCalls() {
        ArrayList otherCalls = new ArrayList();

        if (getCurrentCall() != null) {
            for (Call c : getAvailableCalls()) {
                if (c.getId().equalsIgnoreCase(getCurrentCall().getId())) {
                    continue;
                }
                otherCalls.add(c);
            }
        }
        return otherCalls;
    }

    public void setCall(Call call) {
        backend.setCurrentCall(call);
    }

    void triggerStackChange() {
        callStackObserver.postValue(backend.getCurrentCall());
    }

}
