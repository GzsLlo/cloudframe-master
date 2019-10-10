## cloudframe 开发手册


### 项目结构说明


```
├─cloudframe-master----------------------------父项目
│  │
│  ├─cloudframe-common--------------------------公共配置信息等
│  │  │
│  │  ├─cloudframe-common-base------------------项目公共基础类
│  │  │
│  │  ├─cloudframe-common-config-----------------公共配置信息
│  │
│  ├─cloudframe-eureka-----------------------服务注册中心
│  │
│  ├─cloudframe-gateway-------------------------服务网关
│  │
│  ├─cloudframe-generrator--------------------------代码生成器
│  │
│  ├─cloudframe-provider
│  │  │
│  │  ├─cloudframe-provider-sys------------------系统管理(用户/认证中心)
│  │  │
│  │  ├─cloudframe-provider-device------------------设备中心(例子)
│  │  │
│  │  ├─cloudframe-provider-dtrans------------------分布式事务测试例子
│  │  │
│  │  ├─cloudframe-provider-****------------------***中心
│  │
│  ├─cloudframe-provider-api--------------------------服务调用API接口
│  │
│  ├─cloudframe-web--------------------------web/app等rest服务输出端

```


### 开发说明

#### 一 系统api定义


```
1. 系统开发涉及到多个服务间相互调用,服务间调用接口规范需在项目cloudframe-provider-api 中定义,通过package区分各个服务中心;
2. 服务间接口通过Feign调用,需定义好接口url、依赖服务端服务名、以及降级fallback函数;
3. 服务间实例传递需在对应模块(如sys) model下建立pojo传递,不允许通过entity(除属性和entity完全一致);
4. 返回到浏览器(app)端实例需通过定义vo实例返回(除非接口返回的属性和dto一致可通过dto返回)
```


#### 二 服务provider端开发

```
1. 服务中心provider端如果没有已有项目,需在cloudframe-provider下新建module,并配置application.yml;
2. 同时在cloudframe-generater下新建对应中心代码生成器类,配置对应中心连接数据库等;
3. 配置完cloudframe-generater后,可以通过cloudframe-generater 生成对应中心需开发模块基础代码;
4. 生成代码后续修改Controller类 extends BaseController implements *FeignServiceApi(上面定义的接口);
5. 编写业务代码程序;
6. 服务端开发/测试环境需开放swagger调试;
7. 服务中心间不允许跨库使用,中心间业务关联需通过接口调用实现;
```



#### 三 服务consumer端开发

```
1. 编写业务Controller 或 Service , 依赖*FeignServiceApi 接口;
2. 通过调用 *FeignServiceApi 实例方法调用远程服务;
3. 服务端开发/测试环境需开放swagger调试;
4. 服务间调用中provider端很多场景下也为consumer端,也需通过Feign接口实现调用;
```

#### 四 服务启动顺序(这里没有列数据库/redis/配置中心/限流服务器/分布式事务管理器等)

```
1. 启动cloudframe-eureka服务;
2. 启动cloudframe-provider服务;
3. 启动cloudframe-web等消费服务
```

#### 五 分布式事务开发

```
待补充...
```
