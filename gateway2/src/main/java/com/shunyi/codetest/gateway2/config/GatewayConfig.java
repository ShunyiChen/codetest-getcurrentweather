package com.shunyi.codetest.gateway2.config;

import org.springframework.context.annotation.Configuration;

/**
 * @author Shunyi Chen
 * @create 2020-12-24 13:11
 */
@Configuration
public class GatewayConfig {

//    /// 路由的第二种配置
//    @Bean
//    public RouteLocator customRouteLocator(RouteLocatorBuilder routeLocatorBuilder) {
//        RouteLocatorBuilder.Builder routes = routeLocatorBuilder.routes();
//        routes.route("path_route_shunyi",
//                r -> r.path("/game").uri("http://news.baidu.com/game")).build();
//        return routes.build();
//    }
}
