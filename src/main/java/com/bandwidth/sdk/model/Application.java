package com.bandwidth.sdk.model;

import com.bandwidth.sdk.BandwidthRestClient;
import org.json.simple.JSONObject;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/**
 * @author vpotapenko
 */
public class Application {

    private final BandwidthRestClient client;

    private String id;
    private String name;
    private String incomingCallUrl;
    private String incomingSmsUrl;
    private boolean autoAnswer;

    private String incomingCallFallbackUrl;
    private Long incomingCallUrlCallbackTimeout;
    private Long incomingSmsUrlCallbackTimeout;
    private String callbackHttpMethod;

    public static Application from(BandwidthRestClient client, JSONObject jsonObject) {
        Application application = new Application(client);

        updateProperties(jsonObject, application);

        return application;
    }

    private static void updateProperties(JSONObject jsonObject, Application application) {
        application.id = (String) jsonObject.get("id");
        application.name = (String) jsonObject.get("name");
        application.incomingCallUrl = (String) jsonObject.get("incomingCallUrl");
        application.incomingSmsUrl = (String) jsonObject.get("incomingSmsUrl");
        application.autoAnswer = (Boolean) jsonObject.get("autoAnswer");
        application.incomingCallUrlCallbackTimeout = (Long) jsonObject.get("incomingCallUrlCallbackTimeout");
        application.incomingSmsUrlCallbackTimeout = (Long) jsonObject.get("incomingSmsUrlCallbackTimeout");
        application.callbackHttpMethod = (String) jsonObject.get("callbackHttpMethod");
        application.incomingCallFallbackUrl = (String) jsonObject.get("incomingCallFallbackUrl");
    }

    private Application(BandwidthRestClient client) {
        this.client = client;
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getIncomingCallUrl() {
        return incomingCallUrl;
    }

    public String getIncomingSmsUrl() {
        return incomingSmsUrl;
    }

    public boolean isAutoAnswer() {
        return autoAnswer;
    }

    public void setName(String name) {
        if (name == null) throw new IllegalArgumentException();

        this.name = name;
    }

    public void setIncomingCallUrl(String incomingCallUrl) {
        this.incomingCallUrl = incomingCallUrl;
    }

    public void setIncomingSmsUrl(String incomingSmsUrl) {
        this.incomingSmsUrl = incomingSmsUrl;
    }

    public void setAutoAnswer(boolean autoAnswer) {
        this.autoAnswer = autoAnswer;
    }

    public void setIncomingCallFallbackUrl(String incomingCallFallbackUrl) {
        this.incomingCallFallbackUrl = incomingCallFallbackUrl;
    }

    public void setIncomingCallUrlCallbackTimeout(Long incomingCallUrlCallbackTimeout) {
        this.incomingCallUrlCallbackTimeout = incomingCallUrlCallbackTimeout;
    }

    public void setIncomingSmsUrlCallbackTimeout(Long incomingSmsUrlCallbackTimeout) {
        this.incomingSmsUrlCallbackTimeout = incomingSmsUrlCallbackTimeout;
    }

    public void setCallbackHttpMethod(String callbackHttpMethod) {
        this.callbackHttpMethod = callbackHttpMethod;
    }

    /**
     * Get application properties from server again.
     *
     * @throws IOException
     */
    public void revert() throws IOException {
        if (id == null) return;

        JSONObject jsonObject = client.requestApplicationById(getId());
        updateProperties(jsonObject, this);
    }

    /**
     * Commit changes to server.
     *
     * @throws IOException
     */
    public void commit() throws IOException {
        if (id == null) return;

        Map<String, Object> params = toMap();
        params.remove("id");

        client.updateApplication(getId(), params);
    }

    /**
     * Delete application from server.
     *
     * @throws IOException
     */
    public void delete() throws IOException {
        if (id == null) return;

        client.deleteApplication(getId());
        id = null;
    }

    @Override
    public String toString() {
        return "Application{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", incomingCallUrl='" + incomingCallUrl + '\'' +
                ", incomingSmsUrl='" + incomingSmsUrl + '\'' +
                ", autoAnswer=" + autoAnswer +
                ", incomingCallUrlCallbackTimeout='" + incomingCallUrlCallbackTimeout + '\'' +
                ", incomingSmsUrlCallbackTimeout='" + incomingSmsUrlCallbackTimeout + '\'' +
                ", callbackHttpMethod='" + callbackHttpMethod + '\'' +
                ", incomingCallFallbackUrl='" + incomingCallFallbackUrl + '\'' +
                '}';
    }

    private Map<String, Object> toMap() {
        Map<String, Object> map = new HashMap<String, Object>();

        map.put("id", id);
        map.put("name", name);
        if (incomingCallUrl != null) map.put("incomingCallUrl", incomingCallUrl);
        if (incomingSmsUrl != null) map.put("incomingSmsUrl", incomingSmsUrl);
        if (callbackHttpMethod != null) map.put("callbackHttpMethod", callbackHttpMethod);

        if (incomingCallUrlCallbackTimeout != null) map.put("incomingCallUrlCallbackTimeout", String.valueOf(incomingCallUrlCallbackTimeout));
        if (incomingSmsUrlCallbackTimeout != null) map.put("incomingSmsUrlCallbackTimeout", String.valueOf(incomingSmsUrlCallbackTimeout));
        map.put("autoAnswer", String.valueOf(autoAnswer));

        return map;
    }
}