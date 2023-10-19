package fun.pullock.redis.service

import fun.pullock.redis.AbstractIntegrationSpec
import spock.lang.Narrative
import spock.lang.Title

import jakarta.annotation.Resource

@Title("RankService集成测试")
@Narrative("""
    RankService集成测试。
""")
class RankServiceIntegrationSpec extends AbstractIntegrationSpec {

    @Resource
    RankService rankService

    def "给userId=1的用户添加10个好友" () {
        when:
        def result = rankService.addFriend()

        then:
        result
    }

    def "随机给用户添加步数" () {
        expect:
        def random = new Random()
        for (userId in 1..11) {
            rankService.addSteps(userId, random.nextInt(3000))
        }
    }

    def "将步数初始化添加进排行榜中" () {
        expect:
        rankService.rank()
    }

    def "使用pipeline方式将步数初始化添加进排行榜中" () {
        expect:
        rankService.rankPipeline()
    }

    def "获取排名前5的数据" () {
        expect:
        def result = rankService.getTopRankList(5)
        println(result)
    }

    def "获取排名前20的数据" () {
        expect:
        def result = rankService.getTopRankList(20)
        println(result)
    }
}
