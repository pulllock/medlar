import me.cxis.spring.groovy.Processor

class ClasspathMessageProcessor implements Processor {

    @Override
    String process(String message) {
        println('classpath 的groovy文件处理消息：'+ message)

        def  result = message;
        if (message.contains("error")) {
            result = '消息里有错误信息'
        }
        result = result + "，经过了classpath的groovy文件处理"
        return result
    }
}