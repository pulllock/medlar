# log

- HandlerInterceptor
- HandlerInterceptorAdapter
- AbstractRequestLoggingFilter
- CommonsRequestLoggingFilter
- ServletContextRequestLoggingFilter
- OncePerRequestFilter
- ContentCachingResponseWrapper
- ContentCachingRequestWrapper
- ContentCachingRequestWrapper的问题
- 重复读取Request Body
- HttpServletResponseWrapper
- HttpServletRequestWrapper
- RequestInterceptor
- ClientHttpRequestInterceptor
- RequestBodyInterceptor
- ResponseBodyInterceptor
- RequestBodyAdviceAdapter
- ResponseBodyAdvice
- 记录日志的方式

# HandlerInterceptor

实现`HandlerInterceptor`接口，创建自定义拦截器，可以在请求的前后进行自定义处理，该接口可以实现的方法有三个：

- `preHandler()`：在实际的Handler调用前执行
- `postHandler()`：在Handler调用后执行
- `afterCompletion()`：在请求完成后并且视图已经渲染完成后执行

