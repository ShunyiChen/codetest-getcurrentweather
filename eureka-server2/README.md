配置windows hosts文件 C:\Windows\System32\drivers\etc
127.0.0.1 eureka7001.com
127.0.0.1 eureka7002.com


//eureka 控制台网站
http://eureka7002.com:7002

出厂配置自我保护是开启的，更改参数：
enable-self-preservation: false
eviction-interval-timer-in-ms: 2000