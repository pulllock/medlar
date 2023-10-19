package fun.pullock.cloud.open.feign.server.api.query;

import java.io.Serializable;
import java.time.LocalDateTime;

public class UserQuery implements Serializable {

    private static final long serialVersionUID = -4873931229449186532L;

    private Long userId;

    private LocalDateTime createTime;

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public LocalDateTime getCreateTime() {
        return createTime;
    }

    public void setCreateTime(LocalDateTime createTime) {
        this.createTime = createTime;
    }

    @Override
    public String toString() {
        return "UserQuery{" +
                "userId=" + userId +
                ", birthday=" + createTime +
                '}';
    }
}
