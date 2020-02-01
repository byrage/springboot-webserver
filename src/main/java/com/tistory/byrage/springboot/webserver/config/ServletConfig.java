package com.tistory.byrage.springboot.webserver.config;

import org.springframework.boot.web.servlet.ServletComponentScan;
import org.springframework.context.annotation.Configuration;

@ServletComponentScan(basePackages = "com.tistory.byrage.springboot.webserver")
@Configuration
public class ServletConfig {

}