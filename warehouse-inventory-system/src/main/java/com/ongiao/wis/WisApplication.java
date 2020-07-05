package com.ongiao.wis;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;

/**
 * Created by chenjunxing ON 2020-07-03 02:50.
 */
@SpringBootApplication(exclude = {DataSourceAutoConfiguration.class})
public class WisApplication {
    public static void main (String[] args) {
        SpringApplication.run(WisApplication.class, args);
    }
}
