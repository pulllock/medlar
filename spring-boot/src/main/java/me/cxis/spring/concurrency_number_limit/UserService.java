package me.cxis.spring.concurrency_number_limit;

/**
 * 模拟用户服务的接口
 * @author no name
 */
public interface UserService {

    /**
     * 根据用户id查询用户名
     * @param userId 用户id
     * @return 用户名称
     */
    String getUserNameById(long userId);
}
