# spring-cloud-open-feign-eureka-async

异步调用feign接口，传递请求头信息

# 问题

同步调用feign接口，使用自定义的RequestInterceptor，将ServletRequestAttributes中的Header手动添加到feign请求头中，这样可以实现请求头的传递。但是使用异步方式调用feign接口时，这种传递方式就没有用了。

# 解决方案

异步调用feign接口，传递请求头可以有三种方案：

- 在开始异步调用的时候，将主线程的ServletRequestAttributes传递到异步线程中。该方法存在缺陷，当ServletRequestAttributes被传递到异步线程后，主线程立刻结束掉，此时Request会被容器回收，导致异步线程中从Request中获取数据时会抛异常。
- 使用Request的startAsync()和complete()方式并将主线程的ServletRequestAttributes传递到异步线程中，在开始异步调用的时候，主线程首先执行：`AsyncContext asyncContext = request.startAsync()`，将主线程的ServletRequestAttributes传递到异步线程中，在异步线程完成后调用：`asyncContext.complete()`。
- 在主线程中将请求头参数自定义保存，并将保存的请求头参数传递到异步线程中，在异步线程中拿到保存的请求头参数并设置到feign请求中