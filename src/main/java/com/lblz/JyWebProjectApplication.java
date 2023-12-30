package com.lblz;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletComponentScan;

@SpringBootApplication
@ServletComponentScan //在SpringBootApplication上使用@ServletComponentScan注解后，Servlet、Filter、Listener
                        // 可以直接通过@WebServlet、@WebFilter、@WebListener注解自动注册，无需其他代码。

public class JyWebProjectApplication {

    public static void main(String[] args) {
        SpringApplication.run(JyWebProjectApplication.class, args);
    }

}
