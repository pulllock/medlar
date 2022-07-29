# maven命令

## mvn test

`mvn test`用来执行单元测试

- `-Dtest=XxxTest`：可以指定要执行的测试用例
- `-DfailIfNoTests=false`：可以在没有任何测试用例时也不报错

# maven插件汇总

## 核心插件

- `maven-clean-plugin`：在构建之后进行清理
- `maven-compiler-plugin`：编译Java源码
- `maven-deploy-plugin`：将构建好的artifact部署到远程仓库
- `maven-failsafe-plugin`：用来运行集成测试
- `maven-install-plugin`：将构建好的artifact安装到本地仓库
- `maven-resources-plugin`：将资源拷贝到输出目录中
- `maven-site-plugin`：为当前项目生成站点
- `maven-surefire-plugin`：在maven构建项目的时候自动执行测试用例，输出txt或者xml格式的测试报告
- `maven-verifier-plugin`：用于集成测试，验证某些条件是否存在

## 打包插件

- `maven-ear-plugin`：生成EAR
- `maven-ejb-plugin`：构建RJB
- `maven-jar-plugin`：构建JAR
- `maven-rar-plugin`：构建RAR
- `maven-war-plugin`：构建WAR
- `maven-acr-plugin`：构建JavaEE应用客户端
- `maven-shade-plugin`：构建包含依赖的JAR
- `maven-source-plugin`：构建包含源码的JAR
- `maven-jlink-plugin`：构建Java运行时镜像
- `maven-jmod-plugin`：构建Java的JMod文件

## 报告插件

- `maven-changelog-plugin`：生成SCM变更列表报告
- `maven-changes-plugin`：根据问题或者变化文档来生成报告
- `maven-checkstyle-plugin`：生成Checkstyle报告，自动检查编码规范
- `maven-doap-plugin`：根据POM生成项目描述
- `maven-docck-plugin`：文档检查插件
- `maven-javadoc-plugin`：生成Javadoc
- `maven-jeps-plugin`：运行JDK的JDeps工具
- `maven-jxr-plugin`：生成文档和源码的关系，以web页面的形式将Java源代码展现
- `maven-linkcheck-plugin`：生成Linkcheck报告
- `maven-pmd-plugin`：生成PMD报告，Java源码分析工具
- `maven-project-info-reports-plugin`：生成项目报告
- `maven-surefire-report-plugin`：根据单测结果生成单测报告

## maven-surefire-plugin插件介绍

`maven-surefire-plugin`插件，执行单测，默认情况下会自动执行测试源码路径`src/test/java`下符合以下命名模式的测试类：
- `**/Test*.java`：任何子目录下所有命名以Test开头的Java类
- `**/*Test.java`：任何子目录下所有命名以Test结尾的Java类
- `**/*TestCase.java`：任何子目录下所有命名以TestCase结尾的Java类

默认情况下会在项目的target/surefire-reports目录下生成报告，报告有两种形式：简单文本格式和与Junit兼容的xml格式。

                    

# build插件和reporting插件的区别

- build插件在`build`阶段执行，应该配置在`<build></build>`节点中
- reporting插件在站点生成的时候用，应该配置在`<reporting></reporting>`节点中，由于reporting插件的结果是生成的站点的一部分，所以这种插件应该是需要国际化和本地化的。

关于Maven的插件以及分类可以参考官网：[https://maven.apache.org/plugins/index.html](https://maven.apache.org/plugins/index.html)


# 禁用父POM中定义的插件

## 能修改父POM的情况

如果能修改父POM，可以将父POM中的插件放到`<pluginManagement></pluginManagement>`中

## 配置插件的skip参数来禁用插件

很多插件都会有`skip`参数可以进行配置，如果有的话我们就可以直接配置该参数，来禁用该插件的执行：

```xml
<plugin>
    <artifactId>some-maven-plugin</artifactId>
    <configuration>
        <skip>true</skip>
    </configuration>
</plugin>
```

## 配置phase参数来禁用插件

还可以通过配置`phase`来对插件进行禁用，可以将`phase`配置为一个不存在的阶段或者直接将`phase`置为空。

配置一个不存在的阶段：

```xml
<plugin>
    <artifactId>some-maven-plugin</artifactId>
    <executions>
        <execution>
            <id>some-id</id>
            <phase>any-value-that-is-not-a-phase</phase>
        </execution>
    </executions>
</plugin>
```

将阶段置为空：

```xml
<plugin>
    <artifactId>some-maven-plugin</artifactId>
    <executions>
        <execution>
            <id>some-id</id>
            <phase/>
        </execution>
    </executions>
</plugin>
```

# super-pom说明

```xml
<?xml version="1.0" encoding="UTF-8"?>

<!-- 此文件是super pom，从maven-model-builder-3.8.5.jar文件的org.apache.maven.model包中复制过来的 -->

<project>
    <modelVersion>4.0.0</modelVersion>

    <repositories>
        <!-- 默认的中央仓库 -->
        <repository>
            <id>central</id>
            <name>Central Repository</name>
            <url>https://repo.maven.apache.org/maven2</url>
            <layout>default</layout>
            <!-- 中央仓库默认不开启快照版本 -->
            <snapshots>
                <enabled>false</enabled>
            </snapshots>
        </repository>
    </repositories>

    <pluginRepositories>
        <!-- 默认的插件仓库 -->
        <pluginRepository>
            <id>central</id>
            <name>Central Repository</name>
            <url>https://repo.maven.apache.org/maven2</url>
            <layout>default</layout>
            <!-- 中央仓库默认不开启快照版本 -->
            <snapshots>
                <enabled>false</enabled>
            </snapshots>
            <releases>
                <updatePolicy>never</updatePolicy>
            </releases>
        </pluginRepository>
    </pluginRepositories>

    <build>
        <!-- 项目的主输出目录 -->
        <directory>${project.basedir}/target</directory>
        <!--项目的主代码输出目录 -->
        <outputDirectory>${project.build.directory}/classes</outputDirectory>
        <!-- 最终构件的名称格式 -->
        <finalName>${project.artifactId}-${project.version}</finalName>
        <!-- 测试代码的输出目录 -->
        <testOutputDirectory>${project.build.directory}/test-classes</testOutputDirectory>
        <!-- 源码目录 -->
        <sourceDirectory>${project.basedir}/src/main/java</sourceDirectory>
        <!-- 脚本源码目录 -->
        <scriptSourceDirectory>${project.basedir}/src/main/scripts</scriptSourceDirectory>
        <!-- 测试源码目录 -->
        <testSourceDirectory>${project.basedir}/src/test/java</testSourceDirectory>
        <resources>
            <resource>
                <!-- 资源目录 -->
                <directory>${project.basedir}/src/main/resources</directory>
            </resource>
        </resources>
        <testResources>
            <testResource>
                <!--测试资源目录 -->
                <directory>${project.basedir}/src/test/resources</directory>
            </testResource>
        </testResources>
        <pluginManagement>
            <!-- NOTE: These plugins will be removed from future versions of the super POM -->
            <!-- They are kept for the moment as they are very unlikely to conflict with lifecycle mappings (MNG-4453) -->
            <!-- 核心插件 -->
            <plugins>
                <plugin>
                    <artifactId>maven-antrun-plugin</artifactId>
                    <version>1.3</version>
                </plugin>
                <plugin>
                    <artifactId>maven-assembly-plugin</artifactId>
                    <version>2.2-beta-5</version>
                </plugin>
                <plugin>
                    <artifactId>maven-dependency-plugin</artifactId>
                    <version>2.8</version>
                </plugin>
                <plugin>
                    <artifactId>maven-release-plugin</artifactId>
                    <version>2.5.3</version>
                </plugin>
            </plugins>
        </pluginManagement>
    </build>

    <reporting>
        <outputDirectory>${project.build.directory}/site</outputDirectory>
    </reporting>

    <profiles>
        <!-- NOTE: The release profile will be removed from future versions of the super POM -->
        <profile>
            <id>release-profile</id>

            <!-- profile激活的条件：有performRelease属性且值为true的时候激活 -->
            <activation>
                <property>
                    <name>performRelease</name>
                    <value>true</value>
                </property>
            </activation>

            <build>
                <plugins>
                    <plugin>
                        <inherited>true</inherited>
                        <artifactId>maven-source-plugin</artifactId>
                        <executions>
                            <!-- 为项目生成-source.jar文件 -->
                            <execution>
                                <id>attach-sources</id>
                                <goals>
                                    <goal>jar-no-fork</goal>
                                </goals>
                            </execution>
                        </executions>
                    </plugin>
                    <plugin>
                        <inherited>true</inherited>
                        <artifactId>maven-javadoc-plugin</artifactId>
                        <executions>
                            <!-- 为项目生成-javadoc.jar文件 -->
                            <execution>
                                <id>attach-javadocs</id>
                                <goals>
                                    <goal>jar</goal>
                                </goals>
                            </execution>
                        </executions>
                    </plugin>
                    <plugin>
                        <inherited>true</inherited>
                        <artifactId>maven-deploy-plugin</artifactId>
                        <configuration>
                            <!-- 在部署的时候更新仓库中的元数据，告诉仓库该版本是最新的发布版本 -->
                            <updateReleaseInfo>true</updateReleaseInfo>
                        </configuration>
                    </plugin>
                </plugins>
            </build>
        </profile>
    </profiles>

</project>
```

# settings.xml文件说明

```xml
<?xml version="1.0" encoding="UTF-8"?>
<settings xmlns="http://maven.apache.org/SETTINGS/1.2.0"
          xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
          xsi:schemaLocation="http://maven.apache.org/SETTINGS/1.2.0 https://maven.apache.org/xsd/settings-1.2.0.xsd">
  <!-- 本地仓库路径，默认：${user.home}/.m2/repository -->
  <localRepository>/path/to/local/repo</localRepository>

  <!-- 是否需要和用户交互获得输入，默认: true -->
  <interactiveMode>true</interactiveMode>

  <!-- 离线模式运行，默认: false -->
  <offline>false</offline>

  <!--是否需要用plugin-registry.xml文件来管理插件版本，默认为：false -->
  <usePluginRegistry>false</usePluginRegistry>

  <!-- pluginGroup列表 -->
  <pluginGroups>
    <!-- 使用某个插件时如果没有提供groupId，maven使用pluginGroup配置的groupId -->
    <pluginGroup>com.your.plugins</pluginGroup>
  </pluginGroups>

  <!-- 代理列表 -->
  <proxies>
    <!-- 代理 -->
    <proxy>
      <!-- 代理的唯一ID -->
      <id>optional</id>
      <!-- 为true表示激活 -->
      <active>true</active>
      <!-- 协议 -->
      <protocol>http</protocol>
      <!-- 账号 -->
      <username>proxyuser</username>
      <!-- 密码 -->
      <password>proxypass</password>
      <!-- 主机名 -->
      <host>proxy.host.net</host>
      <!-- 端口号 -->
      <port>80</port>
      <!-- 不被代理的主机名列表 -->
      <nonProxyHosts>local.net|some.host.com</nonProxyHosts>
    </proxy>
  </proxies>

  <!-- servers 配置仓库认证信息 -->
  <servers>
    <!-- server -->
    <server>
      <!-- 唯一ID，该ID必须与POM中需要认证的repository元素的ID一致 -->
      <id>deploymentRepo</id>
      <!-- 用户名 -->
      <username>repouser</username>
      <!-- 密码 -->
      <password>repopwd</password>
      <!-- 私钥 -->
      <privateKey>/path/to/private/key</privateKey>
      <!-- 私钥密码 -->
      <passphrase>optional; leave empty if not used.</passphrase>
      <!-- 文件被创建时的权限，对应了unix系统的权限 -->
      <filePermissions>755</filePermissions>
      <!-- 目录被创建时的权限 -->
      <directoryPermissions>755</directoryPermissions>
      <!-- 传输层额外的配置项 -->
      <configuration>

      </configuration>
    </server>
  </servers>

  <!-- 镜像列表 -->
  <mirrors>
    <!-- 镜像 -->
    <mirror>
      <!-- 镜像唯一ID -->
      <id>mirrorId</id>
      <!--
        被镜像的服务器的id：
        central 表示该配置为中央仓库镜像，任何对于中央仓库的请求都会转到该镜像
        * 匹配所有的远程仓库，任何对于远程仓库的请求都会被转到该镜像
        external:* 匹配所有不在本机上的远程仓库，使用localhost的除外，使用file://协议的除外
        repo1,repo2,... 匹配指定的远程仓库，使用都搞分割，所有对于这些指定的远程仓库的请求都会被转到该镜像
        *,!repo1 匹配除了repo1外的所有远程仓库，对除了repo1外的所有远程仓库的请求都会被转到该镜像
      -->
      <mirrorOf>repositoryId</mirrorOf>
      <!-- 镜像的名字 -->
      <name>Human Readable Name for this Mirror.</name>
      <!-- 镜像的url -->
      <url>http://my.repository.com/repo/path</url>
    </mirror>

    <!--
      maven 3.8.1版本之后使用如下配置禁用了http链接，默认情况下必须使用https仓库地址。如果要使用http的仓库，
      则需要将blocked设置为false
    -->
    <mirror>
      <id>maven-default-http-blocker</id>
      <mirrorOf>external:http:*</mirrorOf>
      <name>Pseudo repository to mirror external repositories initially using HTTP.</name>
      <url>http://0.0.0.0/</url>
      <blocked>true</blocked>
    </mirror>
  </mirrors>

  <!-- profile列表 -->
  <profiles>
    <!-- profile -->
    <profile>
      <!-- 唯一ID -->
      <id>jdk-1.4</id>
      <!-- 激活的条件 -->
      <activation>
        <!-- 默认是否激活 -->
        <activeByDefault>false</activeByDefault>
        <!-- 激活的条件：匹配的jdk被检测到时激活 -->
        <jdk>1.4</jdk>
        <!-- 激活的条件：匹配的操作系统被检测到时激活 -->
        <os>
          <!-- 操作系统名字 -->
          <name>os name</name>
          <!-- 操作系统所属家族 -->
          <family>os family</family>
          <!-- 操作系统体系结构 -->
          <arch>x86</arch>
          <!-- 操作系统版本 -->
          <version>5.2.1</version>
        </os>
        <!-- 激活的条件：检测到某一个属性时激活 -->
        <property>
          <!-- 属性名称 -->
          <name>name</name>
          <!-- 属性的值 -->
          <value>value</value>
        </property>
        <!-- 激活的条件：检测文件存在或不存在来激活 -->
        <file>
          <!-- 指定的文件存在 -->
          <exists>file</exists>
          <!-- 指定的文件不存在 -->
          <missing>file</missing>
        </file>
      </activation>
      <!-- 扩展属性列表 -->
      <properties>
        <my.property>property</my.property>
      </properties>
      <!-- 仓库列表 -->
      <repositories>
        <!-- 仓库 -->
        <repository>
          <!-- 唯一ID -->
          <id>jboss</id>
          <!-- 仓库名字 -->
          <name>Repository for JBoss</name>
          <!-- 仓库地址 -->
          <url>http://repository.jboss.com/maven2/</url>
          <!-- 布局，默认 -->
          <layout>default</layout>
          <!-- release版本 -->
          <releases>
            <!-- 启用release版本下载 -->
            <enabled>true</enabled>
            <!--
              从远程仓库检查更新的频率：
              daily 每天检查一次（默认）
              never 从不检查更新
              always 每次构建检查更新
              interval:X 每个X分钟检查一次更新
             -->
            <updatePolicy>daily</updatePolicy>
            <!--
              检查文件校验和的策略：
              warn 执行构建时输出警告信息（默认）
              fail 遇到校验和失败时构建失败
              ignore 忽略校验和失败错误
            -->
            <checksumPolicy>fail</checksumPolicy>
          </releases>
          <!-- 快照版本 -->
          <snapshots>
            <!-- 启用快照版本下载 -->
            <enabled>true</enabled>
            <!--
              从远程仓库检查更新的频率：
              daily 每天检查一次（默认）
              never 从不检查更新
              always 每次构建检查更新
              interval:X 每个X分钟检查一次更新
             -->
            <updatePolicy>daily</updatePolicy>
            <!--
              检查文件校验和的策略：
              warn 执行构建时输出警告信息（默认）
              fail 遇到校验和失败时构建失败
              ignore 忽略校验和失败错误
            -->
            <checksumPolicy>fail</checksumPolicy>
          </snapshots>
        </repository>
      </repositories>
      <!-- 插件仓库列表 -->
      <pluginRepositories>
        <pluginRepository>
          <!-- 唯一ID -->
          <id>plugin-repository-id</id>
          <!-- 插件仓库名字 -->
          <name>plugin repository name</name>
          <!-- 插件仓库url -->
          <url>plugin repository url</url>
          <!-- 插件仓库布局 -->
          <layout>default</layout>
          <!-- release版本 -->
          <releases>
            <!-- 启用release版本下载 -->
            <enabled>true</enabled>
            <!--
              从远程仓库检查更新的频率：
              daily 每天检查一次（默认）
              never 从不检查更新
              always 每次构建检查更新
              interval:X 每个X分钟检查一次更新
             -->
            <updatePolicy>daily</updatePolicy>
            <!--
              检查文件校验和的策略：
              warn 执行构建时输出警告信息（默认）
              fail 遇到校验和失败时构建失败
              ignore 忽略校验和失败错误
            -->
            <checksumPolicy>fail</checksumPolicy>
          </releases>
          <!-- 快照版本 -->
          <snapshots>
            <!-- 启用快照版本下载 -->
            <enabled>true</enabled>
            <!--
              从远程仓库检查更新的频率：
              daily 每天检查一次（默认）
              never 从不检查更新
              always 每次构建检查更新
              interval:X 每个X分钟检查一次更新
             -->
            <updatePolicy>daily</updatePolicy>
            <!--
              检查文件校验和的策略：
              warn 执行构建时输出警告信息（默认）
              fail 遇到校验和失败时构建失败
              ignore 忽略校验和失败错误
            -->
            <checksumPolicy>fail</checksumPolicy>
          </snapshots>
        </pluginRepository>
      </pluginRepositories>
    </profile>
  </profiles>

  <!-- 激活的profile列表 -->
  <activeProfiles>
    <activeProfile>alwaysActiveProfile</activeProfile>
    <activeProfile>anotherAlwaysActiveProfile</activeProfile>
  </activeProfiles>

</settings>
```

# pom文件说明

```
parent元素声明父模块，可继承的POM元素有：
groupId：               项目的组id
version：               项目的版本
description：           项目的描述信息
organization：          项目的组织信息
inceptionYear：         项目的创始年份
url：                   项目的地址
developers：            项目的开发者信息
contributors：          项目的贡献者信息
distributionManagement：项目的部署配置
issueManagement：       项目的缺陷跟踪系统信息
ciManagement：          项目的持续集成系统信息
scm：                   项目的版本控制系统信息
mailingLists：          项目的邮件列表信息
properties：            自定义的maven属性
dependencies：          项目的依赖配置
dependencyManagement：  项目的依赖管理配置
repositories：          项目的仓库配置
build：                 包括项目的源码目录配置、输出目录配置、插件配置、插件管理配置等
reporting：             包括项目的报告输出目录配置、报告插件配置
```

# Maven生命周期

maven有三套相互独立的生命周期：

- clean：清理
- default：构建项目
- site：建立项目站点

具体的生命周期列表可以参考官方文档：https://maven.apache.org/guides/introduction/introduction-to-the-lifecycle.html#Lifecycle_Reference

## clean生命周期

clean包含三个阶段：

- pre-clean：执行清理前需要完成的工作
- clean：清理
- post-clean：执行清理后要完成的工作

## default生命周期

- validate：验证项目是否正确，并且所有必要信息都可用
- initialize：初始化构建状态，比如设置属性或者创建目录
- generate-sources：生成源码
- process-sources：处理源码
- generate-resources：生成资源
- process-resources：处理资源，将资源复制到目标目录下，准备打包
- compile：编译项目的源码，编译`src/main/java`目录下的Java文件到项目输出的classpath目录中
- precess-classes：对编译生成的class文件进行处理，比如对字节码进行增强
- generate-test-sources：生成测试源码
- process-test-sources：处理测试源码
- generate-test-resources：生成测试资源
- process-test-resources：处理测试资源，将资源复制到目标目录中
- test-compile：编译项目的测试源码，编译`src/test/java`目录下的Java文件到项目输出的测试classpath目录中
- process-test-classes：对编译生成的测试class文件进行处理，比如对字节码进行增强
- test：使用单元测试框架运行测试
- prepare-package：实际打包前执行操作
- package：将编译好的代码打包
- pre-integration-test：执行集成测试前需要的操作
- integration-test：集成测试
- post-integration-test：集成测试后需要的操作
- verify：运行检查
- install：将打好的包安装到Maven本地仓库
- deploy：将打好的包发布到远程仓库

## site生命周期

- pre-site：生成项目站点前的操作
- site：生成项目站点
- post-site：生成项目站点后的操作
- site-deploy：生成的站点文档部署到服务器