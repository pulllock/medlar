# maven插件

- maven-surefire-plugin 在maven构建项目的时候自动执行测试用例，输出txt或者xml格式的测试报告。
- maven-surefire-report-plugin 将maven-surefire-plugin生成的测试报告转换为html格式的报告。
- maven-project-info-reports-plugin 将maven-surefire-report-plugin等插件生成的html格式的报告进行汇总。
- maven-site-plugin 自动生成、部署、启动站点，站点里包含很多东西，也有maven-project-info-reports-plugin生成的报告