package me.cxis.unit.test.spock.controller

import me.cxis.unit.test.spock.AbstractIntegrationTest
import org.springframework.http.MediaType
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders
import spock.lang.Narrative
import spock.lang.Title

import jakarta.servlet.http.HttpServletResponse

@Title("UserController集成测试")
@Narrative("""
    UserController集成测试的描述。
    可以写具体的描述信息。
""")
class UserControllerTest extends AbstractIntegrationTest {

    def "集成测试：Get user by id 运行成功"() {
        given: "查询的userId=1"
        def userId = "1";

        when: "调用接口"
        def response = mockMvc.perform(
                MockMvcRequestBuilders.get("/user/queryById")
                        .contentType(MediaType.APPLICATION_FORM_URLENCODED)
                        .param("id", userId)
        ).andReturn()

        then: "验证返回的结果"
        response.response.status == HttpServletResponse.SC_OK
    }
}
