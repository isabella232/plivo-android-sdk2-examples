package com.plivo.plivoaddressbook.dagger2;

import android.content.Context;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.plivo.plivoaddressbook.utils.ContactUtils;
import com.plivo.plivoaddressbook.utils.NotificationUtils;
import com.plivo.plivoaddressbook.utils.PreferencesUtils;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

@Module(includes = {
        AppContext.class
})
public class UtilsModule {
    @Provides
    @Singleton
    public NotificationUtils notification() {
        return new NotificationUtils();
    }

    @Provides @Singleton
    public PreferencesUtils getPreferences(Context context, Gson gson) {
        return new PreferencesUtils(context, gson);
    }

    @Provides @Singleton
    public ContactUtils contactUtils(Context context) {
        return new ContactUtils(context);
    }

    @Provides @Singleton
    public Gson getGson() {
        return new GsonBuilder().create();
    }
}
