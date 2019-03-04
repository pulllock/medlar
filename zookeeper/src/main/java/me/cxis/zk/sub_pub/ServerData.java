package me.cxis.zk.sub_pub;

/**
 * 服务器基本信息
 */
public class ServerData {

    private Integer id;

    private String address;

    private String name;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "ServerData{" +
            "id=" + id +
            ", address='" + address + '\'' +
            ", name='" + name + '\'' +
            '}';
    }
}
