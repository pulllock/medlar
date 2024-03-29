package fun.pullock.spring.doc.param;

import io.swagger.v3.oas.annotations.media.Schema;

import java.io.Serial;
import java.io.Serializable;

@Schema(description = "用户查询参数，可根据用户名，id，年龄查询")
public class UserQueryParam implements Serializable {

    @Serial
    private static final long serialVersionUID = -1860358647205128516L;

    @Schema(description = "用户名")
    private String name;

    @Schema(description = "用户ID")
    private Long id;

    @Schema(description = "年龄")
    private Integer age;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    @Override
    public String toString() {
        return "UserQueryParam{" +
                "name='" + name + '\'' +
                ", id=" + id +
                ", age=" + age +
                '}';
    }
}
