# JMH

spring-boot-jmh

# 注解

- `@BenchmarkMode`：用在类或方法上，取值：
  - `Throughput`：吞吐量，1秒可以执行多少次调用，单位：`ops/time`
  - `AverageTime`：平均时间，每次调用的平均耗时，单位：`time/op`
  - `SampleTime`：随机取样，输出取样结果的分布
  - `SingleShotTime`：只运行一次
  - `All`：所有的模式
- `@State`：用在类上，指定对象的作用范围，取值：
  - `Scope.Benchmark`：所有线程共享一个实例
  - `Scope.Group`：同一个线程在同一个组里共享实例
  - `Scope.Thread`：每个线程分配一个实例
- `@OutputTimeUnit`：用在类或方法上，表示统计结果的时间单位
- `@Warmup`：用在类或方法上，预热参数，参数如下：
  - `iterations`：预热的次数
  - `time`：每次预热的时间
  - `timeUnit`：时间的单位，默认为：秒
  - `batchSize`：批处理大小
- `@Measurement`：用在类或方法上，实际要测试的方法的配置参数，参数如下：
  - `iterations`：预热的次数
  - `time`：每次预热的时间
  - `timeUnit`：时间的单位，默认为：秒
  - `batchSize`：批处理大小
- `@Threads`：用在类或方法上，表示每个进程中的测试线程
- `@Fork`：用在类或方法上，表示fork的次数
- `@Param`：用在字段上，指定某项参数的多种情况
- `@Benchmark`：用在方法上，表示该方法是需要进行测试的对象
- `@Setup`：用在方法上，该方法在测试方法执行前先执行
- `@TearDown`：用在方法上，该方法在测试方法执行后执行