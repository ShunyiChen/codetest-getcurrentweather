
# codetest-getcurrentweather

[CodeTest-GetCurrentWeather](https://github.com/ShunyiChen/codetest-getcurrentweather) is a weather forecast demonstration system, It is based on microservices and running as a distributed system, build on Jenkins pipeline and deployed in docker. It is composed of multiple microservices:

```sh
gateway-service1: Microservice gateway-service1 and integration with Swagger3.0
gateway-service2: Microservice gateway-service2 and integration with Swagger3.0
eureka-server1:  Microservice registration and discovery service1 
eureka-server2:  Microservice registration and discovery service2
config-server: Central configuration service
city-service: Provide APIs for city management
weather-service: Provide APIs to get current weather data,scheduled update as of per one minute,for more detailed about API,please see API provider at https://openweathermap.org/
```

Third-party softwares:

```sh
Redis: Used to cache the weather forcase data for one minute.
Nginx: Used to do loading balancing.
Mysql: Used to store base data.
Jenkins: Used to build the CICD pipeline.
Harbor: Used to build a private docker image repository.
```



Architecture:
![image-20210611071026875](https://github.com/ShunyiChen/codetest-getcurrentweather/blob/main/docs/Architecture.png)

Docker containers:
![image-20210611071753017](https://github.com/ShunyiChen/codetest-getcurrentweather/blob/main/docs/DockerContainers.png)

CICD:
![image-20210611073439823](https://github.com/ShunyiChen/codetest-getcurrentweather/blob/main/docs/Jenkins.png)

Eureka:
![image-20210611073439822](https://github.com/ShunyiChen/codetest-getcurrentweather/blob/main/docs/Eureka.png)

Swagger:
![image-20210611073439824](https://github.com/ShunyiChen/codetest-getcurrentweather/blob/main/docs/Swagger.png)

Demo:

![image-20210611073439825](https://github.com/ShunyiChen/codetest-getcurrentweather/blob/main/docs/Demo.png)

Pages:

```scala
Demo:http://8.142.15.127:8888/
//pets: http://8.142.15.127:8889/
Jenkins:http://8.142.15.127:10240/
Swagger:http://8.142.15.127:9527/swagger-ui/index.html
Eureka:http://8.142.15.127:7001/ and http://8.142.15.127:7002/
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

nginx.conf:

```sh

user  nginx;
worker_processes  auto;

error_log  /var/log/nginx/error.log notice;
pid        /var/run/nginx.pid;


events {
    worker_connections  1024;
}


http {
    include       /etc/nginx/mime.types;
    default_type  application/octet-stream;

    log_format  main  '$remote_addr - $remote_user [$time_local] "$request" '
                      '$status $body_bytes_sent "$http_referer" '
                      '"$http_user_agent" "$http_x_forwarded_for"';

    access_log  /var/log/nginx/access.log  main;

    sendfile        on;
    #tcp_nopush     on;

    keepalive_timeout  65;

    #gzip  on;

    upstream mydata {
	    server 172.19.249.254:9527;
        server 172.19.249.254:9528;
    }


    server {
        listen       80;
        server_name  8.142.15.127;

        #charset koi8-r;

        #access_log  logs/host.access.log  main;

         root /usr/share/nginx/html;
         location = / {
#root /usr/share/nginx/html;  
 index index.html;
}
#静态文件交给nginx处理
location ~ .*\.(htm|html|gif|jpg|jpeg|png|bmp|swf|ioc|rar|zip|txt|flv|mid|doc|ppt|pdf|xls|mp3|wma)$
{    
   expires 30d;
}
location ~ .*\.(js|css)?$
{
   expires 1h;
}

location / {
   proxy_set_header   X-Real-IP        $remote_addr;
   # 请求头中Host信息
   proxy_set_header   Host             $host;
   # 代理路由信息
   proxy_set_header   X-Forwarded-For  $proxy_add_x_forwarded_for;
   # 真实的用户访问协议
   proxy_set_header   X-Forwarded-Proto $scheme;
   proxy_pass  http://mydata/;
}


        #location / {
         # root /usr/share/nginx/html;
         # index  index.html index.htm;

         # proxy_pass http://mydata/;
         # proxy_redirect default;
        
        # }

        #error_page  404              /404.html;

        # redirect server error pages to the static page /50x.html
        #
        error_page   500 502 503 504  /50x.html;
        location = /50x.html {
            root   html;
        }

        # proxy the PHP scripts to Apache listening on 127.0.0.1:80
        #
        #location ~ \.php$ {
        #    proxy_pass   http://127.0.0.1;
        #}

        # pass the PHP scripts to FastCGI server listening on 127.0.0.1:9000
        #
        #location ~ \.php$ {
        #    root           html;
        #    fastcgi_pass   127.0.0.1:9000;
        #    fastcgi_index  index.php;
        #    fastcgi_param  SCRIPT_FILENAME  /scripts$fastcgi_script_name;
        #    include        fastcgi_params;
        #}

        # deny access to .htaccess files, if Apache's document root
        # concurs with nginx's one
        #
        #location ~ /\.ht {
        #    deny  all;
        #}
    }

    include /etc/nginx/conf.d/*.conf;
}
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
docker logs --tail=9999 weather-weather-service
```

Docker Logs:

```scala
/var/lib/docker/containers
清空log
echo "" > xxx.log
```

云服务器:

```scala
8.142.15.127
172.19.249.254
```





