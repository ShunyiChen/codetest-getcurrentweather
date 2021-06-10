
# codetest-getcurrentweather

[CodeTest-GetCurrentWeather](https://github.com/ShunyiChen/codetest-getcurrentweather) is a weather forecast microservices running as distributed system and deployed in docker. It is composed of multiple microservices:

```scala
eureka-server1:  Microservice registration and discovery service
eureka-server2:  Microservice registration and discovery service
config-server: Central configuration service
city-service: Provide APIs for city management
weather-service: Provide APIs to get current weather data, update date per one minute,see API provider side at https://openweathermap.org/
gateway1: Microservice gateway1 and integrated with Swagger3.0
gateway2: Microservice gateway2

```

Architecture:

![image-20210611071026875](C:\Users\ESEHHUC\AppData\Roaming\Typora\typora-user-images\image-20210611071026875.png)



 Docker containers:

![image-20210611071753017](C:\Users\ESEHHUC\AppData\Roaming\Typora\typora-user-images\image-20210611071753017.png)

CICD:

![image-20210611073439823](C:\Users\ESEHHUC\AppData\Roaming\Typora\typora-user-images\image-20210611073439823.png)



Pages:

```scala
Weather : http://8.142.15.127:8888/
Jenkins: http://8.142.15.127:10240/
Swagger: http://8.142.15.127:9527/swagger-ui/index.html

```





## Quick-start

 

 常用命令：

```scala
//查询端口
netstat  -nlp|grep 8089     //8089是系统启动访问的端口， 由此可得到9578 是java运行的端口，
```

三方软件安装：
安装MYSQL:

###### vim /usr/shunyi/mysql/conf/my.cnf

```angular2html
[client]
default-character-set=utf8mb4
 
[mysql]
default-character-set=utf8mb4
 
[mysqld]
pid-file        = /var/run/mysqld/mysqld.pid
socket          = /var/run/mysqld/mysqld.sock
datadir         = /var/lib/mysql
secure-file-priv= NULL
# Disabling symbolic-links is recommended to prevent assorted security risks
symbolic-links=0
max_connections=10000
default-time_zone='+8:00'
character-set-client-handshake=FALSE
character_set_server=utf8mb4
collation-server=utf8mb4_unicode_ci
init_connect='SET NAMES utf8mb4 COLLATE utf8mb4_unicode_ci'
# Custom config should go here
!includedir /etc/mysql/conf.d/
```

###### 启动容器

```scala
docker run -p 3306:3306 --name weather-mysql -e MYSQL_ROOT_PASSWORD=cDe3@wsx \
-v /usr/shunyi/mysql/conf/my.cnf:/etc/mysql/my.cnf \
-v /usr/shunyi/mysql/logs:/var/log/mysql \
-v /usr/shunyi/mysql/data:/var/lib/mysql \
-d mysql --lower_case_table_names=1
进入容器
docker exec -it weather-mysql bash
```



安装REDIS:

```scala
从github下载最新redis.conf
docker run -p 6379:6379 --name weather-redis -v /home/redis/conf/redis.conf:/redis.conf -v /home/redis/data:/data -d redis:latest redis-server
进入容器
docker exec -it weather-redis redis-cli
```



安装Jenkins:

```sh
docker pull jenkins/jenkins

docker run -d -p 10240:8080 -p 10241:50000 -v /usr/shunyi/jenkins:/var/jenkins_home -v /etc/localtime:/etc/localtime --name weather-jenkins jenkins/jenkins

参考https://www.cnblogs.com/fuzongle/p/12834080.html
//访问控制台
http://8.142.15.127:10240/
```



安装harbor

```scala
下载工具[harbor-offline-installer-v2.2.2.tgz](https://github.com/goharbor/harbor/releases/download/v2.2.2/harbor-offline-installer-v2.2.2.tgz)

解压tar xf harbor-offline-installer-v2.2.2.tgz
cd harbor
cp harbor.yml.impl harbor.yml
vim harbor.yml
change hostname to 内外IP
打开网页访问
http://8.142.15.127/harbor/projects/2/repositories
admin/Harbor12345
参考https://www.bilibili.com/video/BV1Vv411C7gn?p=33
```



安装Nginx
```angular2html
docker logs -f weather-nginx 1>/dev/null
docker logs -f weather-nginx 2>/dev/null

docker cp weather-nginx:/var/log/nginx/* /usr/shunyi/nginx/
docker cp nginx:/etc/nginx/nginx.conf /usr/shunyi/nginx/conf/nginx.conf

docker run --name weather-nginx -d -p 8888:80 -v /usr/shunyi/nginx/html:/usr/share/nginx/html -v /usr/shunyi/nginx/conf/nginx.conf:/etc/nginx/nginx.conf  -v /usr/shunyi/nginx/logs:/var/log/nginx -d nginx:latest

For pets:

docker run --name weather-nginx2 -d -p 8889:80 -v /usr/shunyi/nginx/pets:/usr/share/nginx/html -d nginx:latest


```

构建镜像

```sh
cd /usr/shunyi/dockerfiles/eureka-server1
docker build -t eureka-server .

docker run -d -p 7001:7001 --name weather-eureka-server1 --add-host eureka7001.com:127.0.0.1 --network=host eureka-server1
docker run -d -p 7002:7002 --name weather-eureka-server2 --add-host eureka7002.com:127.0.0.1 --network=host eureka-server2
docker run -d -p 33472:33472 --name weather-city-service city-service
docker run -d -p 33473:33473 --name weather-weather-service weather-service
docker run -d -p 9527:9527 --name weather-gateway-service1 gateway-service

docker run -d -p 7001:7001 --name weather-eureka-server1 --add-host eureka7001.com:172.19.249.254 eureka-server1

查看日志
docker logs --tail=9999 weather-eureka-server1
docker logs --tail=9999 weather-eureka-server2
```



8.142.15.127
172.19.249.254





