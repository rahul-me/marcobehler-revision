package com.rccode.context;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.rccode.ApplicationLauncher;
import org.h2.jdbcx.JdbcDataSource;
import org.springframework.context.annotation.*;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

import javax.sql.DataSource;

@PropertySource("classpath:/application${spring.profiles.active}.properties")
@ComponentScan(basePackageClasses = ApplicationLauncher.class)
@EnableWebMvc
@Configuration
public class ApplicationConfiguration {

    @Bean
    public ObjectMapper objectMapper() {
        return new ObjectMapper();
    }

    @Bean
    public DataSource dataSource() {
        JdbcDataSource ds = new JdbcDataSource();
        ds.setURL("jdbc:h2:~/myFirstH2Database;INIT=RUNSCRIPT FROM 'classpath:schema.sql'");
        ds.setUser("sa");
        ds.setPassword("sa");
        return ds;
    }

    @Bean
    public JdbcTemplate jdbcTemplate(DataSource dataSource) {
        return new JdbcTemplate(dataSource);
    }

}
