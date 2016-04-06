# Spring PetClinic（宠物诊所）示例应用 [![Build Status](https://travis-ci.org/spring-projects/spring-petclinic.png?branch=master)](https://travis-ci.org/spring-projects/spring-petclinic/)

## 通过几张图理解Spring Petclinic应用
<a href="https://speakerdeck.com/michaelisvy/spring-petclinic-sample-application">英文原版讲义</a>

<a href="https://speakerdeck.com/liouxiao/spring-petclinic-sample-application">中文讲义（部分有更新）</a>

## 在本地运行petclinic应用
```
	git clone https://github.com/spring-projects/spring-petclinic.git
	cd spring-petclinic
    ./mvnw tomcat7:run
```

您可以通过右边的链接访问petclinic应用： http://localhost:9966/petclinic/

## 如果发现了bug，或者有什么改进的建议，应该怎么办
请访问我们的issue追踪系统： https://github.com/spring-projects/spring-petclinic/issues


## 数据库配置

Petclinic默认采用内存数据库(HSQLDB)，在启动时初始化数据。对于MySQL数据库，我们也提供了类似的配置，如果需要可以打开相应选项。
注意，如果数据库类型切换为MySQL，则需要更新data-access.properties文件，并在pom.xml中把关于mysql-connector-java包的注释去掉。

您可以用docker启动一个MySql数据库实例:

```
docker run -e MYSQL_ROOT_PASSWORD=petclinic -e MYSQL_DATABASE=petclinic -p 3306:3306 mysql:5.7.8
```

## 在Eclipse/STS中开发Petclinic应用

### 先决条件
在您的系统中需要安装以下组件:
* Maven 3 (http://www.sonatype.com/books/mvnref-book/reference/installation.html)
* git命令行工具 (https://help.github.com/articles/set-up-git)
* Eclipse及m2e插件 (在STS (http://www.springsource.org/sts) 中默认附带安装了m2e插件)

注意: m2e插件装好之后，在Eclipse的 “帮助” -&gt; “关于”对话框中会出现一个 m2 的图标。
请依据右边链接中描述的方法安装m2e插件: http://eclipse.org/m2e/download/


### 步骤:

1) 在命令行中
```
git clone https://github.com/spring-projects/spring-petclinic.git
```
2) 在Eclipse中
```
File -> Import -> Maven -> Existing Maven project
```


## 要查找一些特定的东西?

<table>
  <tr>
    <th width="300px">Java配置</th><th width="300px"></th>
  </tr>
  <tr>
    <td>Java Config分支</td>
    <td>
      Petclinic默认使用基于XML的配置方式。如果您更喜欢使用Java Config（基于注解），这里有个Java Config分支 <a href="https://github.com/spring-projects/spring-petclinic/tree/javaconfig">链接</a>。Antoine Rey贡献了这个分支，在此向他致谢。    
    </td>
  </tr>
  <tr>
    <th width="300px">在'Web'层中</th><th width="300px">文件</th>
  </tr>
  <tr>
    <td>Spring MVC - XML集成</td>
    <td><a href="/src/main/resources/spring/mvc-view-config.xml">mvc-view-config.xml</a></td>
  </tr>
  <tr>
    <td>Spring MVC - ContentNegotiatingViewResolver</td>
    <td><a href="/src/main/resources/spring/mvc-view-config.xml">mvc-view-config.xml</a></td>
  </tr>
  <tr>
    <td>JSP自定义标签</td>
    <td>
      <a href="/src/main/webapp/WEB-INF/tags">WEB-INF/tags</a>
      <a href="/src/main/webapp/WEB-INF/jsp/owners/createOrUpdateOwnerForm.jsp">createOrUpdateOwnerForm.jsp</a>
    </td>
  </tr>
  <tr>
    <td>Bower</td>
    <td>
      <a href="/pom.xml">bower-install maven profile 在 pom.xml 中声明</a> <br />
      <a href="/bower.json">JavaScript库在bower.json中定义</a> <br />
      <a href="/.bowerrc">Bower采用JSON格式的配置文件</a> <br />
      <a href="/src/main/resources/spring/mvc-core-config.xml#L30">在Spring配置中的资源映射</a>
    </td>
  </tr>
  <tr>
    <td>Dandelion-datatables</td>
    <td>
      <a href="/src/main/webapp/WEB-INF/jsp/owners/ownersList.jsp">ownersList.jsp</a> 
      <a href="/src/main/webapp/WEB-INF/jsp/vets/vetList.jsp">vetList.jsp</a> 
      <a href="/src/main/webapp/WEB-INF/web.xml">web.xml</a> 
      <a href="/src/main/resources/dandelion/datatables/datatables.properties">datatables.properties</a> 
   </td>
  </tr>
  <tr>
    <td>Thymeleaf分支</td>
    <td>
      <a href="http://www.thymeleaf.org/petclinic.html">参见此处</a></td>
  </tr>
  <tr>
    <td>采用GemFire和Spring Data GemFire代替ehcache (向Bijoy Choudhury致谢)</td>
    <td>
      <a href="https://github.com/bijoych/spring-petclinic-gemfire">参见此处</a></td>
  </tr>
</table>

<table>
  <tr>
    <th width="300px">'Service'和'Repository'层</th><th width="300px">文件</th>
  </tr>
  <tr>
    <td>事务（Transactions）</td>
    <td>
      <a href="/src/main/resources/spring/business-config.xml">business-config.xml</a>
       <a href="/src/main/java/org/springframework/samples/petclinic/service/ClinicServiceImpl.java">ClinicServiceImpl.java</a>
    </td>
  </tr>
  <tr>
    <td>缓存（Cache）</td>
      <td>
      <a href="/src/main/resources/spring/tools-config.xml">tools-config.xml</a>
       <a href="/src/main/java/org/springframework/samples/petclinic/service/ClinicServiceImpl.java">ClinicServiceImpl.java</a>
    </td>
  </tr>
  <tr>
    <td>Bean Profiles</td>
      <td>
      <a href="/src/main/resources/spring/business-config.xml">business-config.xml</a>
       <a href="/src/test/java/org/springframework/samples/petclinic/service/ClinicServiceJdbcTests.java">ClinicServiceJdbcTests.java</a>
       <a href="/src/main/webapp/WEB-INF/web.xml">web.xml</a>
    </td>
  </tr>
  <tr>
    <td>JdbcTemplate</td>
    <td>
      <a href="/src/main/resources/spring/business-config.xml">business-config.xml</a>
      <a href="/src/main/java/org/springframework/samples/petclinic/repository/jdbc">jdbc目录</a></td>
  </tr>
  <tr>
    <td>JPA</td>
    <td>
      <a href="/src/main/resources/spring/business-config.xml">business-config.xml</a>
      <a href="/src/main/java/org/springframework/samples/petclinic/repository/jpa">jpa目录</a></td>
  </tr>
  <tr>
    <td>Spring Data JPA</td>
    <td>
      <a href="/src/main/resources/spring/business-config.xml">business-config.xml</a>
      <a href="/src/main/java/org/springframework/samples/petclinic/repository/springdatajpa">springdatajpa目录</a></td>
  </tr>
</table>

<table>
  <tr>
    <th width="300px">其它</th><th width="300px">文件</th>
  </tr>
  <tr>
    <td>Gradle 分支</td>
    <td>
      <a href="https://github.com/whimet/spring-petclinic">参见此处</a></td>
  </tr>
</table>


## 与其它开源项目的交互

在开发Spring Petclinic应用的过程中，最有意思的部分之一是我们能有机会和许多其它开源项目直接联系。我们发现了一些bug/改进的建议，涉及到了Spring、Spring Data、Bean Validation甚至Eclipse项目！ 在多数情况下，这些问题都在几天之内就被修复／实现了。
以下是有关列表:

<table>
  <tr>
    <th width="300px">名称</th>
    <th width="300px"> 事项 </th>
  </tr>

  <tr>
    <td>Spring JDBC: simplify usage of NamedParameterJdbcTemplate</td>
    <td> <a href="https://jira.springsource.org/browse/SPR-10256"> SPR-10256</a> 及 <a href="https://jira.springsource.org/browse/SPR-10257"> SPR-10257</a> </td>
  </tr>
  <tr>
    <td>Bean Validation / Hibernate Validator: simplify Maven dependencies and backward compatibility</td>
    <td>
      <a href="https://hibernate.atlassian.net/browse/HV-790"> HV-790</a> 及 <a href="https://hibernate.atlassian.net/browse/HV-792"> HV-792</a>
      </td>
  </tr>
  <tr>
    <td>Spring Data: provide more flexibility when working with JPQL queries</td>
    <td>
      <a href="https://jira.springsource.org/browse/DATAJPA-292"> DATAJPA-292</a>
      </td>
  </tr>  
  <tr>
    <td>Eclipse: validation bug when working with .tag/.tagx files (has only been fixed for Eclipse 4.3 (Kepler)). <a href="https://github.com/spring-projects/spring-petclinic/issues/14">See here for more details.</a></td>
    <td>
      <a href="https://issuetracker.springsource.com/browse/STS-3294"> STS-3294</a>
    </td>
  </tr>    
</table>


# 贡献

我们建议使用[issue tracker](https://github.com/spring-projects/spring-petclinic/issues)来报告bug、提交新功能请求及合并代码请求。

对于合并代码的请求，请参考[editor config](https://github.com/spring-projects/spring-petclinic/blob/master/.editorconfig)中的编辑器设置。想要了解更多关于信息、或者要下载相关插件，请移步至<http://editorconfig.org>。




