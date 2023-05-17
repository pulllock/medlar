package me.cxis.unit.test.spock.controller

import me.cxis.unit.test.spock.AbstractUnitTest
import me.cxis.unit.test.spock.manager.UserManager
import me.cxis.unit.test.spock.model.UserVO
import spock.lang.Narrative
import spock.lang.Title

@Title("UserController单元测试")
@Narrative("""
    UserController单元测试的描述。
    可以写具体的描述信息。
""")
class UserControllerUnitTest extends AbstractUnitTest {

    def userController = new UserController()

    def "Get user by id 参数错误"() {

    }

    def "Get user by id 运行成功"() {
        given: "对UserManager以及queryById返回的UserVO进行Mock"
        UserVO mockUser = new UserVO(id: 1, name: "小明")
        UserManager userManager = Mock(UserManager.class)
        userController.userManager = userManager

        and: "打桩：UserManager的queryById返回Mock的UserVO"
        userManager.queryById(1) >> mockUser

        when: "UserController调用queryById方法"
        def result = userController.queryById(1)
        println(result)

        then: "验证queryById返回的结果"
        with(result) {
            id == 1
        }
    }
}
