package com.test.project.helper;

import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

@Component
public class TestJsonHelper {

  private static TestRestTemplate restTemplate;

  public TestJsonHelper(TestRestTemplate testRestTemplate) {
    this.restTemplate = testRestTemplate;
  }

  public static ResponseEntity<String> getForEntity(int port, String url) {
    return restTemplate.getForEntity(getUri(port, url), String.class);
  }

  private static String getUri(int port, String url){
    return "http://localhost:" + port + url;
  }
}