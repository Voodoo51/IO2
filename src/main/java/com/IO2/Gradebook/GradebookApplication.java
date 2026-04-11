package com.IO2.Gradebook;

import java.util.ArrayList;
import java.util.List;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.boot.jdbc.autoconfigure.DataSourceAutoConfiguration;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.jdbc.core.JdbcTemplate;
import java.sql.SQLException;
import java.sql.ResultSet;
import org.springframework.jdbc.core.RowMapper;
//@SpringBootApplication(exclude = { DataSourceAutoConfiguration.class })

@SpringBootApplication
@RestController
public class GradebookApplication {

  public final int a = 2;

  private final JdbcTemplate jdbcTemplate;

  public GradebookApplication(JdbcTemplate jdbcTemplate) {
    this.jdbcTemplate = jdbcTemplate;
  }

  public static void main(String[] args) {
    SpringApplication.run(GradebookApplication.class, args);
  }

  @GetMapping("/hello")
  public String hello(@RequestParam(value = "name", defaultValue = "World") String name) {

    List<String> data = jdbcTemplate.query("SELECT * from uzytkownik;", new RowMapper<String>(){
      public String mapRow(ResultSet rs, int rowNum)
              throws SQLException {

        String output = "";

        for(int i = 0; i < 8; i++)
        {
            output += rs.getString(i+1) + " ";
        }

        output += "<br>";
        return output;
      }
    });

    String output = "";

    for (int i = 0; i < data.size(); i++) {
      output += data.get(i);
    }

    return output;
    // return String.format("Hello %s!", name);
  }
}


