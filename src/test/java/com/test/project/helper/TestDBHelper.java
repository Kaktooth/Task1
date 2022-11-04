package com.test.project.helper;


import javax.sql.DataSource;
import org.springframework.core.io.ClassPathResource;
import org.springframework.jdbc.datasource.init.ResourceDatabasePopulator;

public class TestDBHelper {

  private TestDBHelper(){}

  public static void executeDBQuery(DataSource dataSource, String filePath) {
    ResourceDatabasePopulator populator = new ResourceDatabasePopulator();
    populator.addScript(new ClassPathResource(filePath));
    populator.execute(dataSource);
  }
}