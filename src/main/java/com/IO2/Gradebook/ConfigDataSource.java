/*
package com.IO2.Gradebook;

import javax.sql.DataSource;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ConfigDataSource {
  @Bean
  public static DataSource source() {
    DataSourceBuilder<?> dSB = DataSourceBuilder.create();
    dSB.driverClassName("com.postgres.jdbc.Driver");

    dSB.url("jdbc:postgres://localhost:5432/userdetails");
    dSB.username("voodoo");
    dSB.password("123");

    return dSB.build();
  }
}
*/
