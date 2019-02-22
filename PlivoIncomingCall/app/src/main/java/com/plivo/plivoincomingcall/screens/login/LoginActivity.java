package com.plivo.plivoincomingcall.screens.login;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.widget.AutoCompleteTextView;
import android.widget.EditText;

import com.plivo.plivoincomingcall.BaseActivity;
import com.plivo.plivoincomingcall.R;
import com.plivo.plivoincomingcall.dagger2.DaggerViewComponent;
import com.plivo.plivoincomingcall.dagger2.ViewContext;
import com.plivo.plivoincomingcall.screens.dial.DialActivity;
import com.plivo.plivoincomingcall.utils.AlarmUtils;
import com.plivo.plivoincomingcall.utils.AlertUtils;
import com.plivo.plivoincomingcall.utils.Constants;

import javax.inject.Inject;

import androidx.lifecycle.ViewModelProviders;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.OnEditorAction;

/**
 * A login screen that offers login via email/password.
 */
public class LoginActivity extends BaseActivity {
    private static final String TAG = LoginActivity.class.getSimpleName();

    @Inject
    AlertUtils alertUtils;

    @Inject
    AlarmUtils alarmUtils;

    @BindView(R.id.username)
    AutoCompleteTextView usernameView;

    @BindView(R.id.password)
    EditText passwordView;

    @BindView(R.id.login_form)
    View progressView;

    @BindView(R.id.login_progress)
    View progressBar;

    private LoginViewModel viewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        DaggerViewComponent.builder().viewContext(new ViewContext(this)).build().inject(this);
        viewModel = ViewModelProviders.
                of(this).get(LoginViewModel.class);

        viewModel.loginObserver().observe(this, success -> {
            if (success) {
                alarmUtils.setRepeatingAlarmNow();
                showProgressBar(false);
                dialScreen();
            } else {
                alertUtils.showToast("Logout");
                showLoginForm();
            }
        });

        if (viewModel.isLoggedIn()) {
            dialScreen();
        } else {
            showLoginForm();
        }
    }

    private void showLoginForm() {
        setContentView(R.layout.activity_login);
        ButterKnife.bind(this);
    }


    @OnClick(R.id.email_sign_in_button)
    public void onClickSignIn() {
        login();
    }

    @OnEditorAction(R.id.password)
    public boolean onEditorAction(int id, KeyEvent keyEvent) {
        if (id == EditorInfo.IME_ACTION_DONE || id == EditorInfo.IME_NULL) {
            login();
            return true;
        }
        return false;
    }

    /**
     * Attempts to sign in or register the account specified by the login form.
     * If there are form errors (invalid email, missing fields, etc.), the
     * errors are presented and no actual login attempt is made.
     */
    private void login() {

        // Reset errors.
        usernameView.setError(null);
        passwordView.setError(null);

        // Store values at the time of the login attempt.
        String email = usernameView.getText().toString();
        String password = passwordView.getText().toString();

        boolean cancel = false;
        View focusView = null;

        // Check for a valid password, if the user entered one.
        if (!TextUtils.isEmpty(password) && !isPasswordValid(password)) {
            passwordView.setError(getString(R.string.error_invalid_password));
            focusView = passwordView;
            cancel = true;
        }

        // Check for a valid email address.
        if (TextUtils.isEmpty(email)) {
            usernameView.setError(getString(R.string.error_field_required));
            focusView = usernameView;
            cancel = true;
        } else if (!isUsernameValid(email)) {
            usernameView.setError(getString(R.string.error_invalid_email));
            focusView = usernameView;
            cancel = true;
        }

        if (cancel) {
            // There was an error; don't attempt login and focus the first
            // form field with an error.
            focusView.requestFocus();
        } else {
            // Show a progress spinner, and kick off a background task to
            // perform the user login attempt.
            progressBar.setVisibility(View.VISIBLE);
            viewModel.login(usernameView.getText().toString(), passwordView.getText().toString());
        }
    }

    private void dialScreen() {
        startActivity(new Intent(this, DialActivity.class)
                .addFlags(Intent.FLAG_ACTIVITY_NEW_TASK|Intent.FLAG_ACTIVITY_CLEAR_TOP)
                .putExtra(Constants.EXTRA_INCOMING_CALL, getIntent().getBooleanExtra(Constants.EXTRA_INCOMING_CALL, false)));
        finish();
    }

    private void showProgressBar(boolean show) {
        if (progressBar == null) return;

        progressBar.setVisibility(show ? View.VISIBLE : View.INVISIBLE);
    }

    private boolean isUsernameValid(String username) {
        //TODO: Replace this with your own logic
        return !username.contains("@");
    }

    private boolean isPasswordValid(String password) {
        //TODO: Replace this with your own logic
        return password.length() > 4;
    }
}

