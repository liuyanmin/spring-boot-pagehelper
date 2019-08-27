# Spring Boot 集成 MyBatis, 分页插件 PageHelper, 集成 Swagger API文档插件

- [Spring Boot 2.0.1.RELEASE](https://github.com/spring-projects/spring-boot)
- [mybatis-spring-boot-starter](https://github.com/mybatis/spring-boot-starter)
- [mapper-spring-boot-starter](https://github.com/abel533/mapper-boot-starter)
- [pagehelper-spring-boot-starter](https://github.com/pagehelper/pagehelper-spring-boot)


## 项目依赖
```xml
<!--mybatis-->
<dependency>
    <groupId>org.mybatis.spring.boot</groupId>
    <artifactId>mybatis-spring-boot-starter</artifactId>
    <version>1.3.1</version>
</dependency>
<!--mapper-->
<dependency>
    <groupId>tk.mybatis</groupId>
    <artifactId>mapper-spring-boot-starter</artifactId>
    <version>2.1.5</version>
</dependency>
<!--pagehelper-->
<dependency>
    <groupId>com.github.pagehelper</groupId>
    <artifactId>pagehelper-spring-boot-starter</artifactId>
    <version>1.2.5</version>
</dependency>
```
## 集成 MyBatis Generator
通过 Maven 插件集成的，所以运行插件使用下面的命令：
>mvn mybatis-generator:generate

pom.xml中配置 Mybatis Geneator:
```xml
<build>
    <plugins>
      <plugin>
        <groupId>org.mybatis.generator</groupId>
        <artifactId>mybatis-generator-maven-plugin</artifactId>
        <version>1.3.2</version>
        <configuration>
          <configurationFile>${basedir}/src/main/resources/mybatis-generator/generatorConfig.xml</configurationFile>
          <overwrite>true</overwrite>
          <verbose>true</verbose>
        </configuration>
        <dependencies>
          <dependency>
            <groupId>mysql</groupId>
            <artifactId>mysql-connector-java</artifactId>
            <version>5.1.46</version>
          </dependency>
          <dependency>
            <groupId>tk.mybatis</groupId>
            <artifactId>mapper-generator</artifactId>
            <version>1.0.0</version>
          </dependency>
        </dependencies>
      </plugin>
    </plugins>
</build>
```

## application.yml 配置
```yaml
server:
  port: 8083
pagehelper:
  helper-dialect: mysql
  reasonable: true
  support-methods-arguments: true
  params: count=countSql
spring:
  datasource:
    url: jdbc:mysql://localhost:3306/test
    username: root
    password: root
    driver-class-name: com.mysql.jdbc.Driver
```

## pagehelper 参数详解
>https://pagehelper.github.io/docs/howtouse/

## spring boot + mybatis generator + pagehelper 集成的基础项目 
>https://github.com/liuyanmin/spring-boot-pagehelper

## 集成 swagger
pom.xml 增加依赖:
```xml
<dependency>
  <groupId>io.springfox</groupId>
  <artifactId>springfox-swagger2</artifactId>
  <version>2.9.2</version>
</dependency>
<dependency>
  <groupId>io.springfox</groupId>
  <artifactId>springfox-swagger-ui</artifactId>
  <version>2.9.2</version>
</dependency>
```
增加 application.yml 配置:
```yaml
swagger:
  enable: true
```
增加开启swagger配置类，SwaggerConfig.java
```java
/**
 * 文档范文地址: http://localhost:8084/swagger-ui.html
 * Created by liuyanmin on 2019/8/27.
 */
@Configuration
@EnableSwagger2
@ConditionalOnExpression("${swagger.enable:true}")
public class SwaggerConfig {

    @Bean
    public Docket adminDocket() {

        return new Docket(DocumentationType.SWAGGER_2)
                .groupName("admin")
                .apiInfo(apiInfo())
                .globalOperationParameters(parameters())
                .select()
                .apis(RequestHandlerSelectors.basePackage("com.lym.demo.controller"))
                .paths(PathSelectors.any())
                .build();
    }

    private ApiInfo apiInfo() {
        return new ApiInfoBuilder()
                .title("后台API接口文档")
                .description("后台API接口文档")
                .version("1.0")
                .build();
    }

    /**
    * 配置请求参数 
    */
    private List<Parameter> parameters() {
        // 在请求头(header)中添加 userId 和 token 参数
        ParameterBuilder parameterBuilder = new ParameterBuilder();
        List<Parameter> parameters = new ArrayList<>();
        parameterBuilder.name("userId").description("用户ID")
                .modelRef(new ModelRef("string")).parameterType("header").required(false).build();

        ParameterBuilder parameterBuilder2 = new ParameterBuilder();
        parameterBuilder2.name("token").description("Token")
                .modelRef(new ModelRef("string")).parameterType("header").required(false).build();

        parameters.add(parameterBuilder.build());
        parameters.add(parameterBuilder2.build());

        return parameters;
    }

}
```
## API 接口访问地址: [http://localhost:8084/swagger-ui.html](http://localhost:8084/swagger-ui.html)
