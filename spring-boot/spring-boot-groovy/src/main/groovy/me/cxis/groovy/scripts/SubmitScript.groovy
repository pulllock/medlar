package me.cxis.groovy.scripts

import groovy.json.JsonOutput
import groovy.json.JsonSlurper
import groovy.transform.EqualsAndHashCode
import me.cxis.groovy.engine.AbstractResultCalculator
import me.cxis.groovy.engine.ScriptContext

class SubmitScript extends AbstractResultCalculator {

    @Override
    String calculate(ScriptContext context) {
        if (!context) {
            return null
        }

        if (!context.bizResultId || !context.param) {
            return null
        }

        def jsonSlurper = new JsonSlurper()

        def paramMap = jsonSlurper.parseText(context.param)

        assert paramMap instanceof Map

        int age = paramMap.get("age") as int

        def result = new BizCalResult()
        result.age = age

        if (age > 20) {
            result.code = 'One'
            result.num = 1
        } else if (age > 10){
            result.code = 'Two'
            result.num = 2
        } else {
            result = null
        }

        return JsonOutput.toJson(result)
    }

    @EqualsAndHashCode
    class BizCalResult {
        String code
        int num
        int age
    }
}
