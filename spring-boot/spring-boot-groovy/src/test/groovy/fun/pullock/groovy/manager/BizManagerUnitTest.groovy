package fun.pullock.groovy.manager

import fun.pullock.groovy.AbstractUnitTest
import fun.pullock.groovy.dao.BizDao
import fun.pullock.groovy.engine.AbstractResultCalculator
import fun.pullock.groovy.engine.GCLEngineManager
import fun.pullock.groovy.engine.ScriptContext
import spock.lang.Narrative
import spock.lang.Subject
import spock.lang.Title

@Title("BizManager单元测试")
@Narrative("""
    BizManager单元测试。
""")
class BizManagerUnitTest extends AbstractUnitTest {

    def "计算正确并且能成功保存的execute方法"() {
        given: "准备一个BizManager，一个被模拟的GCLEngineManager，一个被模拟的BizDao"
        @Subject
        def bizManager = new BizManager()
        def gclEngineManager = Mock(GCLEngineManager)
        def bizDao = Mock(BizDao)
        bizManager.gclEngineManager = gclEngineManager
        bizManager.bizDao = bizDao

        and: "被模拟的GCLEngineManager的getCalculator方法输入任意的脚本名字，返回一个模拟的Calculator"
        def calculator = Mock(AbstractResultCalculator)
        gclEngineManager.getCalculator(_) >> calculator

        and: "模拟的Calculator输入任意的上下文执行计算后返回正确的结果"
        def calculateResult = "{\"age\": 1}"
        calculator.calculate(_) >> calculateResult

        when: "execute方法被调用，输入指定的上下文和任意的脚本名称"
        def context = new ScriptContext(bizResultId:  1 as Long, param:  _)
        def executeResult = bizManager.execute(context, _ as String)

        then: "返回正确的计算结果，并且成功保存到数据库中"
        executeResult == calculateResult

        and: "BizDao调用保存方法成功，并且入参类型正确"
        1 * bizDao.save(_ as Long, _ as String)
    }

    // @Unroll
    def "计算失败并且能成功保存的execute方法"() {
        given: "准备一个BizManager，一个被模拟的GCLEngineManager，一个被模拟的BizDao"
        @Subject
        def bizManager = new BizManager()
        def gclEngineManager = Mock(GCLEngineManager)
        def bizDao = Mock(BizDao)
        bizManager.gclEngineManager = gclEngineManager
        bizManager.bizDao = bizDao

        and: "被模拟的GCLEngineManager的getCalculator方法输入任意的脚本名字，返回一个模拟的Calculator"
        def calculator = Mock(AbstractResultCalculator)
        gclEngineManager.getCalculator(_) >> calculator

        and: "模拟的Calculator输入任意的上下文执行计算后返回失败的结果"
        calculator.calculate(_) >> calculateResult

        when: "execute方法被调用，输入指定的上下文和任意的脚本名称"
        def context = new ScriptContext(bizResultId:  1 as Long, param:  _)
        def executeResult = bizManager.execute(context, _ as String)

        then: "返回失败的计算结果，并且成功保存到数据库中"
        executeResult == result

        and: "BizDao调用保存方法成功，并且入参类型正确"
        1 * bizDao.save({bizResultId -> bizResultId == 1}, _ as String)

        where: "不同计算错误的结果和对应的最终结果数据："
        calculateResult || result
        null            || "{}"
        ""              || "{}"
        "null"          || "{}"
    }
}
