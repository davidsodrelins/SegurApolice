package br.com.seguradora.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ConfigurationWeb {

    @Bean(name = "applicationName")
    public String applicationName(){
        return "Api de Apolices de Seguros";
    }

}
