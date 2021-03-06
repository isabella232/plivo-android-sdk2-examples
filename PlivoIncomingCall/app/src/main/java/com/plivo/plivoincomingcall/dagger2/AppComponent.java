package com.plivo.plivoincomingcall.dagger2;

import com.plivo.plivoincomingcall.screens.dial.DialViewModel;
import com.plivo.plivoincomingcall.screens.login.LoginViewModel;
import com.plivo.plivoincomingcall.services.PlivoFCMService;

import javax.inject.Singleton;

import dagger.Component;

@Component(modules = {
        BackendModule.class,
        ReceiverModule.class
})
@Singleton
public interface AppComponent {
    void inject(LoginViewModel loginViewModel);
    void inject(DialViewModel dialViewModel);

    void inject(PlivoFCMService service);
}
