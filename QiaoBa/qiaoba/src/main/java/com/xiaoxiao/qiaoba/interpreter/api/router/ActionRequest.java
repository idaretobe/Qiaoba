package com.xiaoxiao.qiaoba.interpreter.api.router;

import android.os.Parcel;
import android.os.Parcelable;

import com.xiaoxiao.qiaoba.interpreter.utils.RouterUtils;
import com.xiaoxiao.qiaoba.interpreter.utils.UUIDUtils;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by wangfei on 2017/8/6.
 */

public class ActionRequest implements Parcelable{

    private String uuid;
    private String router;
    private String originDomain;
    private String jsonData;
    private Map<String, String> paramData = new HashMap<>();

    public ActionRequest(String router, String originDomain) {
        this.uuid = UUIDUtils.generateUUID();
        this.router = router;
        this.originDomain = originDomain;
        this.paramData.putAll(RouterUtils.parseQueryString(router));
        this.jsonData = RouterUtils.generateJsonData(this.paramData);
    }

    public ActionRequest(String router, String originDomain, String jsonData, Map<String, String> paramData) {
        this.router = router;
        this.originDomain = originDomain;
        this.jsonData = jsonData;
        this.paramData.putAll(paramData);
        this.uuid = UUIDUtils.generateUUID();
    }

    protected ActionRequest(Parcel in) {
        uuid = in.readString();
        router = in.readString();
        originDomain = in.readString();
        jsonData = in.readString();
    }

    public static final Creator<ActionRequest> CREATOR = new Creator<ActionRequest>() {
        @Override
        public ActionRequest createFromParcel(Parcel in) {
            return new ActionRequest(in);
        }

        @Override
        public ActionRequest[] newArray(int size) {
            return new ActionRequest[size];
        }
    };

    public String getUuid() {
        return uuid;
    }

    public String getRouter() {
        return router;
    }

    public String getOriginDomain() {
        return originDomain;
    }

    public String getJsonData() {
        return jsonData;
    }

    public Map<String, String> getParamData() {
        return paramData;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(uuid);
        dest.writeString(router);
        dest.writeString(originDomain);
        dest.writeString(jsonData);
    }

    public static class Builder{
        private String originDomain;
        private String router;
        public Builder originDomain(String originDomain){
            this.originDomain = originDomain;
            return this;
        }
        public Builder router(String router){
            this.router = router;
            return this;
        }
        public ActionRequest build(){
            return new ActionRequest(this.router, this.originDomain);
        }
    }
}
