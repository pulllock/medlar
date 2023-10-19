package fun.pullock.unit.test.spock.manager

import fun.pullock.unit.test.spock.AbstractUnitTest
import spock.lang.Narrative
import spock.lang.Title
import spock.lang.Unroll

@Title("CvdManager单元测试")
@Narrative("""
    CvdManager单元测试，用来测试CVD风险计算相关的的逻辑，主要包括：
    1. CVD风险计算结果的测试
""")
class CvdManagerUnitTest extends AbstractUnitTest{

    def cvdManager = new CvdManager();

    @Unroll
    def "正确的CVD等级计算，是否男性：#isMen，年龄：#age，是否糖尿病：#diabetes，是否抽烟：#smoker，血压：#sbp，胆固醇：#tchol，返回的结果：#result"() {
        expect: "输入给定的值，返回正确的风险等级"
        cvdManager.calculateCvdRisk(isMen, age, diabetes, smoker, sbp, tchol) == result

        where: "输入不同的计算数据，得到对应的正确的风险等级结果"
        isMen | age | diabetes | smoker | sbp | tchol || result
        true  | 70  | true     | false  | 119 | 3     || 14
        true  | 74  | true     | false  | 140 | 5     || 23
        true  | 40  | true     | false  | 119 | 3     || 4
    }
}
