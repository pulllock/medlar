package me.cxis.cloud.open.feign.server.api.query;

import java.io.Serializable;

public class UserQuery implements Serializable {

    private static final long serialVersionUID = -4873931229449186532L;

    private Long userId;

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    @Override
    public String toString() {
        return "UserQuery{" +
                "userId=" + userId +
                '}';
    }
}
