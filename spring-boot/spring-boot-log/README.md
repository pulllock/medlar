# 记录日志的方式

- Interceptor
- Filter

# HandlerInterceptor

`HandlerInterceptor`是`spring-webmvc`项目中的，通常拦截来自浏览器等客户端的请求，实现`HandlerInterceptor`接口，创建自定义拦截器，可以在请求的前后进行自定义处理，该接口可以实现的方法有三个：

- `preHandler()`：在实际的Handler调用前执行
- `postHandler()`：在Handler调用后执行
- `afterCompletion()`：在请求完成后并且视图已经渲染完成后执行

可以在`preHandler()`方法中进行请求信息的处理，但是不能对请求体进行处理，请求体是InputStream，只能读一次，不能重复读，如果在`preHandler()`方法中读取了请求体，则后续会报错。同样可以在`postHandler()`方法中对响应信息进行处理，但是不能够处理响应体，因为此时已经无法读取到响应体的OutputStream，该OutputStream已经被消费过了。所以实现`HandlerInterceptor`来进行请求和响应日志的记录是不太合适的。

# HandlerInterceptorAdapter

使用`HandlerInterceptorAdapter`和使用`HandlerInterceptor`的方式是一样的，存在的问题也是一样的。

# OncePerRequestFilter

继承`OncePerRequestFilter`来实现日志记录逻辑，在`Filter`中可以将原来的Request和Response进行缓存包装，这样就可以重复读取Stream。使用`ContentCachingRequestWrapper`对Request进行包装，使用`ContentCachingResponseWrapper`对Response进行包装，这样而可以保证在`filterChain.doFilter()`方法之后依然可以从缓存的Request中获取到请求体信息，并且在从缓存的Response中获取响应体之后，还可以调用`copyBodyToResponse()`方法将缓存的响应体写回到原来的Response中去。

使用`ContentCachingRequestWrapper`来缓存Request时，不能在`filterChain.doFilter()`方法之前就读取请求体，因为此时Request中的`InputStream`还没有被读取，也就还没有被放到缓存的`ContentCachingRequestWrapper`中去，此时读取请求体是空的，如果想要在`filterChain.doFilter()`方法之前就读取请求体，需要自定义实现一个缓存请求体的包装类。

# AbstractRequestLoggingFilter

`AbstractRequestLoggingFilter`是`OncePerRequestFilter`的子类，实现此类可以在Request前后进行日志的打印，并且是Spring内置的日志打印实现逻辑，该实现只能进行请求日志的记录，不能进行响应日志的记录。另外`beforeRequest()`方法是在`filterChain.doFilter()`之前调用，并且使用的是`ContentCachingRequestWrapper`进行包装，该方法中是不能获取到请求体的，问题以及解决方法和`OncePerRequestFilter`是一样的。

# CommonsRequestLoggingFilter

`CommonsRequestLoggingFilter`是`AbstractRequestLoggingFilter`的子类，如果需要使用该类进行日志打印，只需要配置为Bean，并且在配置文件将log的level设置为debug即可。

# ServletContextRequestLoggingFilter

`ServletContextRequestLoggingFilter`是`AbstractRequestLoggingFilter`的子类，该类是使用ServletContext来记录日志，使用也是只需要将该类配置为Bean即可。

# ContentCachingResponseWrapper

`ContentCachingResponseWrapper`主要是用来缓存包装`HttpServletResponse`，让`HttpServletResponse`中的OutputStream可以重复读取，如果想要在一个Response返回给客户端之前进行处理，则需要先使用`ContentCachingResponseWrapper`对原来的`HttpServletResponse`进行包装，然后读取响应的流进行处理，处理完后再调用`ContentCachingResponseWrapper`的`copyBodyToResponse()`方法将缓存的响应体写回到原来的Response中去。

# ContentCachingRequestWrapper

`ContentCachingRequestWrapper`主要是用来缓存包装`HttpServletRequest`，让`HttpServletRequest`中的InputStream在被使用后缓存到`ContentCachingRequestWrapper`中，这样后续就可以继续从`ContentCachingRequestWrapper`中读取请求体信息。

# ClientHttpRequestInterceptor

`ClientHttpRequestInterceptor`是`spring-web`项目中的，通常和`RestTemplate`配合使用，用来拦截`RestTemplate`的请求，比如使用`RestTemplate`作为客户端向其他的服务进行请求，需要在每个请求的Header中添加相同的信息，就可以使用`ClientHttpRequestInterceptor`来进行实现。

# RequestInterceptor

`RequestInterceptor`是`feign-core`中的，通常用来拦截`Feign`请求，在`Feign`请求发送前可以对请求进行拦截设置。

# RequestBodyAdvice

实现`RequestBodyAdvice`接口可以在请求体被读取前后进行处理，只对注解了`@RequestBody`的方法或者有`HttpEntity`参数的方法有效，可以在请求体被转换成对象之前操作请求体，也可以在被转换为对象但是在传给Controller前操作。

# RequestBodyAdviceAdapter

`RequestBodyAdviceAdapter`是`RequestBodyAdvice`的抽象实现，使用和`RequestBodyAdvice`一样。

# ResponseBodyAdvice

`ResponseBodyAdvice`接口的实现了只对注解了`@ResponseBody`或者返回值是`ResponseEntity`的方法有效，可以在Controller返回了结果但是在`HttpMessageConverter`进行类型转换前进行拦截。

`ResponseBodyAdvice`实现类如果要生效，可以使用如下方式：

- 在实现类上添加注解：`@ControllerAdvice`
- 注册到`RequestMappingHandlerAdapter`中
- 注册到`ExceptionHandlerExceptionResolver`中