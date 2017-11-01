package me.cxis.jmeter;

import org.apache.jmeter.config.Arguments;
import org.apache.jmeter.protocol.java.sampler.AbstractJavaSamplerClient;
import org.apache.jmeter.protocol.java.sampler.JavaSamplerContext;
import org.apache.jmeter.samplers.SampleResult;

/**
 * Created by cheng.xi on 2017-11-01 22:04.
 */
public class TestJmeterSampler extends AbstractJavaSamplerClient {

    private static String host;
    private static int port;
    private static String param;
    private static String host_default = "http://localhost";
    private static int port_default = 8080;
    private static String param_default = "test";

    @Override
    public Arguments getDefaultParameters() {
        Arguments arguments = new Arguments();
        arguments.addArgument("host", host == null ? host_default : host);
        arguments.addArgument("port", String.valueOf(port <= 0 ? port_default : port));
        arguments.addArgument("param", param == null ? param_default : param);
        return arguments;
    }

    @Override
    public void setupTest(JavaSamplerContext context) {
        host = context.getParameter("host");
        port = context.getIntParameter("port");
        param = context.getParameter("param");
    }

    public SampleResult runTest(JavaSamplerContext javaSamplerContext) {
        SampleResult sampleResult = new SampleResult();
        sampleResult.setSampleLabel("TestJmeterSample" + Thread.currentThread().getId());
        sampleResult.sampleStart();
        String url = host + ":" + port + "/param=" + param;
        System.out.println("connect to :" + url);
        sampleResult.setResponseData(url.getBytes());
        sampleResult.setSuccessful(true);
        sampleResult.sampleEnd();
        return sampleResult;
    }
}
