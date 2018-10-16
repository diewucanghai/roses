package cn.stylefeng.roses.gateway;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.cloud.netflix.zuul.EnableZuulProxy;
import org.springframework.cloud.openfeign.EnableFeignClients;

/**
 * 网关服务
 *
 * @author fengshuonan
 * @Date 2017/11/10 上午11:24
 */
@SpringBootApplication(exclude = {DataSourceAutoConfiguration.class})
@EnableFeignClients(basePackages = "cn.stylefeng.roses.gateway.modular.consumer")
@EnableZuulProxy
public class RosesGatewayApplication {

    public static void main(String[] args) {
        SpringApplication.run(RosesGatewayApplication.class, args);
    }

}
