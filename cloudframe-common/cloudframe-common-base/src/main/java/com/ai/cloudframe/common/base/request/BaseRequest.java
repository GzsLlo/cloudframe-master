package com.ai.cloudframe.common.base.request;

import java.io.Serializable;

public abstract class BaseRequest implements Serializable{

    private Long requestId;

    public Long getRequestId() {
        return requestId;
    }

    public void setRequestId(Long requestId) {
        this.requestId = requestId;
    }

    @Override
    public String toString() {
        return "BaseRequest{" +
                "requestId=" + requestId +
                '}';
    }
}
