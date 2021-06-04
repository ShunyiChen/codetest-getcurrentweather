package com.shunyi.codetest.gateway.filter;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang.StringUtils;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.core.Ordered;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

import java.util.Date;

/**
 * @author Shunyi Chen
 *
 * @create 2020-12-25 11:07
 */
@Component
@Slf4j
public class CustomGlobalFilter implements GlobalFilter, Ordered{

    @Override
    public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {
        log.info("**************come in CustomGlobalFilter: "+new Date());
        return chain.filter(exchange);
    }

    @Override
    public int getOrder() {
        return 0;
    }
}
