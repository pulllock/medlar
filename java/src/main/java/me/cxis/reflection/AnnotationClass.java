package me.cxis.reflection;

/**
 * Created by cheng.xi on 2017-10-14 10:34.
 */
@MyAnnotation(name = "someName", value = "hello")
public class AnnotationClass {

    @MyAnnotation(name = "methodName", value = "value")
    public void something(){

    }

    public void somethingElse(@MyAnnotation(name = "parameterName", value = "xxx") String parameter) {

    }
}
