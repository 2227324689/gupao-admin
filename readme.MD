[![license](https://img.shields.io/hexpm/l/plug.svg?style=flat-square)](https://github.com/2227324689/ToBeBetter/blob/master/LICENSE) [![issues](https://img.shields.io/bitbucket/issues-raw/2227324689/ToBeBetter.svg?style=flat-square)](https://github.com/2227324689/gpmall/issues) [![constract us](https://img.shields.io/badge/contract%20us-%E5%AE%98%E7%BD%91-brightgreen.svg?style=flat-square)](https://www.gupaoedu.com) [![origin](https://img.shields.io/badge/origin-%E5%92%95%E6%B3%A1%E5%AD%A6%E9%99%A2-yellowgreen.svg?style=flat-square)](https://www.gupaoedu.com) [![blog](https://img.shields.io/badge/blog-%E5%8D%9A%E5%AE%A2-orange.svg?style=flat-square)](https://istio.tech) [![author](https://img.shields.io/badge/author-Mic-blue.svg?style=flat-square)](#) [![Gpmall](https://img.shields.io/badge/linked-gpmall-red.svg?style=flat-square)](#) ![hits](http://hits.dwyl.io/gpmall/gpmall.svg?style=flat-square) [![community](https://img.shields.io/badge/community-%E6%8A%80%E6%9C%AF%E7%A4%BE%E5%8C%BA-lightgrey.svg?style=flat-square)](https://gper.club)


# 项目介绍

基于Layui 做的一个后台管理系统的脚手架。

后台技术采用Spring Boot + Mybatis

权限采用的是Shiro

数据库采用mysql

# 模块介绍

![image-20201013232326975](https://gitee.com/mic112/gupao-admin/raw/main/readme.assets/image-20201013232326975.png)



# 运行向导

* 运行dbscript数据库脚本

* 修改application.properties数据库连接字符串

  ```properties
  spring.datasource.driverClassName=com.mysql.cj.jdbc.Driver
  spring.datasource.url=jdbc:mysql://localhost:3306/gupao-live?useUnicode=true&characterEncoding=utf8&useOldAliasMetadataBehavior=true&zeroDateTimeBehavior=convertToNull&allowMultiQueries=true&serverTimezone=UTC&serverTimezone=Asia/Shanghai
  spring.datasource.username=root
  spring.datasource.password=root
  spring.datasource.hikari.minimum-idle=5
  spring.datasource.hikari.maximum-pool-size=30
  spring.datasource.hikari.idle-timeout=600000
  spring.datasource.hikari.max-lifetime=1800000
  spring.datasource.hikari.connection-timeout=500
  spring.datasource.hikari.login-timeout=500
  spring.datasource.hikari.validation-timeout=1000
  spring.datasource.hikari.initialization-fail-timeout=1000
  ```

* 运行 GupaoeduLiveApplication

# 界面截图



![image-20201013231613775](https://gitee.com/mic112/gupao-admin/raw/main/readme.assets/image-20201013231613775.png)



![image-20201013231621443](https://gitee.com/mic112/gupao-admin/raw/main/readme.assets/image-20201013231621443.png)



![image-20201013231627638](https://gitee.com/mic112/gupao-admin/raw/main/readme.assets/image-20201013231627638.png)



![image-20201013231633138](https://gitee.com/mic112/gupao-admin/raw/main/readme.assets/image-20201013231633138.png)
