package me.cxis.spring.service;

public interface HystrixService {

    String hello();

    String hystrixThreadPool();

    String hystrixSemaphore();

    String helloAnnotation();

    String hystrixAnnotationThreadPool();

    String hystrixAnnotationSemaphore();

}
