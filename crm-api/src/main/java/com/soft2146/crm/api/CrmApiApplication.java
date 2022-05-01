package com.soft2146.crm.api;

import cn.dev33.satoken.SaManager;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

/**
 * @author Tao_Dell
 */

@SpringBootApplication
@EnableJpaAuditing
@EnableSwagger2
@MapperScan("com.soft2146.crm.api.mapper")
public class CrmApiApplication {
    public static void main(String[] args) {
        SpringApplication.run(CrmApiApplication.class, args);
        System.out.println("启动成功：Sa-Token配置如下：" + SaManager.getConfig());
    }

}
