package me.cxis.zk.sub_pub;

/**
 * 配置信息
 */
public class ServerConfig {

    private String url;

    private String userName;

    private String password;

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public String toString() {
        return "ServerConfig{" +
            "url='" + url + '\'' +
            ", userName='" + userName + '\'' +
            ", password='" + password + '\'' +
            '}';
    }
}
