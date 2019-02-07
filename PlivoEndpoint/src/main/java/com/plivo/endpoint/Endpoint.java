package com.plivo.endpoint;

import android.text.TextUtils;

import com.plivo.endpoint.backend.plivo;

import java.util.Map;
import java.util.concurrent.TimeUnit;


public class Endpoint {
	private static final int MIN_REG_TIMEOUT = (int) TimeUnit.MINUTES.toSeconds(2);
	private static final int MAX_REG_TIMEOUT = (int) TimeUnit.HOURS.toSeconds(24);

	/**
	 * Listener for PJSIP event.
	 */
	private BackendListener backendListener;

	/**
	 * Event listener that need to be implemented by user.
	 */
	private EventListener eventListener;

	/**
	 * isInitialized flag.
	 */
	private boolean isInitialized;

	/**
	 * Current isActive outgoing call.
	 */
	private Outgoing curOutgoing;

	/**
	 * Registration Timeout in seconds - value to be specified between 120 & 86400 only
	 */
	private int regTimeout = (int) TimeUnit.MINUTES.toSeconds(10);
	private boolean isRegistered;

	/**
	 * Creates endpoint object
	 * @param debug - true to turn ON Plivo SDK debug logs, false otherwise
	 * @param eventListener - Login, Call events Callback listener
	 */
	public Endpoint(boolean debug, EventListener eventListener) {
		Log.enable(debug);
		this.eventListener = eventListener;
		isInitialized = initLib(this.eventListener);
	}

	/**
	 * Create Plivo endpoint instance
	 * @param debug true if we want to set debug flag.
	 * @param eventListener event listener object.
	 * @return
	 */
	public static Endpoint newInstance(boolean debug, EventListener eventListener) {
		Endpoint endpoint = new Endpoint(debug, eventListener);
		Log.D("newInstance " + debug + "eventListener: " + eventListener);
		return endpoint.isInitialized ? endpoint : null;
	}

	/**
	 * Login to plivo cloud
	 * @param username Username of the endpoint
	 * @param password Password of the endpoint
	 * @return
	 */
	public boolean login(String username, String password) {
		if (plivo.LoginSip(username, password, this.regTimeout, Global.DOMAIN) != 0) {
			Log.E("Login attempt failed. Check your username and password");
			return false;
		}
		logDebug("Login attempt success");

		return true;
	}

	/**
	 * Logout
	 * @return
	 */
	public boolean logout() {
		if(isRegistered()) {
			if (plivo.Logout() != 0) {
				Log.E("Logout failed");
				return false;
			}
		}
		logDebug("Logout success");
		return true;
	}

	/**
	 * Create outgoing call instance.
	 * @return
	 */
	public Outgoing createOutgoingCall() {
		logDebug("createOutgoingCall");
		if (!isRegistered()) {
			Log.E("Endpoint not registered");
			return null;
		}

		Outgoing out = new Outgoing(this);
		this.curOutgoing = out;
		Log.I("outgoing object created");
		return out;
	}

	/**
	 * Check if a digit is valid dtmf digit
	 * @param digit Digit to be checked.
	 * @return
	 */
	public boolean checkDtmfDigit(String digit) {
		return Utils.VALID_DTMF.contains(digit);
	}

	protected Outgoing getOutgoing() {

		return this.curOutgoing;
	}

	// kept to not break the backward compatibility
	protected void setRegistered(boolean status) {
		this.isRegistered = status;
	}

	// kept to not break the backward compatibility
	public boolean getRegistered(){
		return this.isRegistered;
	}

	public boolean isRegistered() {
		int r = plivo.isRegistered();
		Log.D("isRegistered: " + r);
		return r > 0 || isRegistered;
	}

	public void setRegTimeout(int regTimeout) {
		if (regTimeout == this.regTimeout) return;

		if (regTimeout < MIN_REG_TIMEOUT || regTimeout > MAX_REG_TIMEOUT) {
			Log.E("Allowed values of regTimeout are between 120 and 86400 seconds only");
			return;
		}

		this.regTimeout = regTimeout;

		if (isRegistered()) {
			plivo.setRegTimeout(regTimeout);
		}
	}

	// reatining this to not break the backward compatibility
	private void logDebug(String str) {
		Log.D("[endpoint]" + str);
	}

	private boolean initLib(EventListener eventListener) {
		loadJNI();

		if (backendListener == null) {
			backendListener = new BackendListener(Global.DEBUG, this, eventListener);
		}
		plivo.setCallbackObject(backendListener);

		logDebug("Starting module..");

		int rc = plivo.plivoStart();

		if (rc != 0) {
			Log.E("plivolib failed. rc = " + rc + ". Failed to initialize Plivo Endpoint object.");
			return false;
		}

		logDebug("plivolib started.....");
		return true;
	}

	private void loadJNI() {
		try {
			System.loadLibrary("pjplivo");
			logDebug("libpjplivo loaded");
		} catch (UnsatisfiedLinkError ule) {
			ule.printStackTrace();
			Log.E("errload loading libpjplivo:" + ule.toString());
		}
	}

	// Creating application in keep alive
	public void keepAlive(){
		logDebug("keepAlive");
		plivo.keepAlive();
	}
	//reset the endpoint when the network has change
	public void resetEndpoint(){
		logDebug("resetEndpoint");
		plivo.resetEndpoint();
	}

	//Register Deivce token with Plivo.
	//You can get the registration token (means that your device has successfully registered) from FCM or GCM
	public void registerToken(String deviceToken) {
		logDebug("registerToken: " + deviceToken);
		if(TextUtils.isEmpty(deviceToken)) {
			Log.E("Invalid Token");
			return;
		}

		plivo.registerToken(deviceToken);
	}

	//Push_headers is the Map object forwarded by the GCM or FCM push notification service.
	public void relayVoipPushNotification(Map<String, String> push_headers) {
		logDebug("relayVoipPushNotification: " + push_headers);
		if (push_headers == null || push_headers.isEmpty()) return;

		String push_str = Utils.mapToString(push_headers);
		if (TextUtils.isEmpty(push_str)) {
			Log.E("Invalid Notification");
			return;
		}

		plivo.relayVoipPushNotification(push_str);
	}
}
