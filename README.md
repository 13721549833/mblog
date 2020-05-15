### Mblog 开源Java博客系统, 支持多用户, 支持切换主题

[![Author](https://camo.githubusercontent.com/77dffebf6828889c085a242dd075610fe67a15be/68747470733a2f2f696d672e736869656c64732e696f2f62616467652f617574686f722d6c616e64792d677265656e2e7376673f7374796c653d666c61742d737175617265)](http://mtons.com/) [![JDK](https://camo.githubusercontent.com/b95fa5e9099d9c1cf7d2ab2a91bf67eb8554505f/68747470733a2f2f696d672e736869656c64732e696f2f62616467652f6a646b2d312e382d677265656e2e7376673f7374796c653d666c61742d737175617265)](https://github.com/langhsu/mblog/blob/master/README.md#) [![Release](https://camo.githubusercontent.com/32fe27591fb8563f7219c1d3f9c2c383bd56df87/68747470733a2f2f696d672e736869656c64732e696f2f6769746875622f72656c656173652f6c616e676873752f6d626c6f672e7376673f7374796c653d666c61742d737175617265)](https://github.com/langhsu/mblog) [![license](https://camo.githubusercontent.com/7d35c51174027143ea80bc45103eaee514b7f8cb/68747470733a2f2f696d672e736869656c64732e696f2f62616467652f6c6963656e73652d47504c2d2d332e302d677265656e2e737667)](https://github.com/langhsu/mblog/blob/master/LICENSE) [![Docker](https://camo.githubusercontent.com/e47182c54af8404ffab9ab1dab1350cfef34787d/68747470733a2f2f696d672e736869656c64732e696f2f646f636b65722f6175746f6d617465642f6c616e676873752f6d626c6f672e7376673f7374796c653d666c61742d737175617265)](https://hub.docker.com/r/langhsu/mblog) [![QQ群](https://camo.githubusercontent.com/17292a5990b88cccb068379597f0813ea69ff411/68747470733a2f2f696d672e736869656c64732e696f2f62616467652f636861742d4d746f6e732d677265656e2e737667)](https://jq.qq.com/?_wv=1027&k=521CRdF)

### 技术选型：

- JDK8
- MySQL
- Spring-boot
- Mybatis-plus
- Shiro
- Lombok
- Freemarker
- Bootstrap
- SeaJs
- Rabbit-mq
- Elasticsearch

### 启动：

- main方法运行

```
配置：src/main/resources/application-mysql.yml (数据库账号密码)、新建db_mblog的数据库
运行：src/main/java/com/mtons/mblog/BootApplication
访问：http://localhost:8080/
后台：http://localhost:8080/admin
账号：默认管理员账号为 admin/12345

TIPS: 
如遇到启动失败/切换环境变量后启动失败的,请先maven clean后再尝试启动
IDE得装lombok插件
```

- 文档: [说明文档](https://langhsu.github.io/mblog/#/)
- 官网: [官网地址](http://www.mtons.com/)
- QQ交流群：378433412

### 版本更新内容：

```
1. 集成mybatis-plus，将jpa转成mp
2. 集成redis，添加自定义缓存注解
3. 集成elasticsearch搜索引擎，实现内容搜索功能（因为数据访问层换成mp，导致原有搜索功能不能使用，所以换成elasticsearch实现内容搜索）
4. 添加相册功能，后台可上传图片到相册（目前只支持单图片上传）
5. 集成rabbitmq，用于邮件异步发送及es与数据库之间的内容同步，后续会将评论，点赞，收藏等功能替换成rabbitmq来完成
```

### 图片演示

[![输入图片说明](https://camo.githubusercontent.com/acf4d2be46ff90b66b1ebf9784cb5fb86a1668df/68747470733a2f2f696d616765732e67697465652e636f6d2f75706c6f6164732f696d616765732f323031392f303431342f3137353131365f34343965643837375f313735383834392e6a706567)](https://camo.githubusercontent.com/acf4d2be46ff90b66b1ebf9784cb5fb86a1668df/68747470733a2f2f696d616765732e67697465652e636f6d2f75706c6f6164732f696d616765732f323031392f303431342f3137353131365f34343965643837375f313735383834392e6a706567) [![输入图片说明](https://camo.githubusercontent.com/d221101b185fa062b8789e515ecb665c268d44db/68747470733a2f2f696d616765732e67697465652e636f6d2f75706c6f6164732f696d616765732f323031392f303431342f3137353335335f36313835653466315f313735383834392e6a706567)](https://camo.githubusercontent.com/d221101b185fa062b8789e515ecb665c268d44db/68747470733a2f2f696d616765732e67697465652e636f6d2f75706c6f6164732f696d616765732f323031392f303431342f3137353335335f36313835653466315f313735383834392e6a706567)

![0dd45835f0f7c1866a7c43af2f03c7b0](http://image.snowsea.com.cn/static/0dd45835f0f7c1866a7c43af2f03c7b0.jpg)