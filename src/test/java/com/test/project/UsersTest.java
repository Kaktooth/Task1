package com.test.project;

import static com.test.project.helper.TestDBHelper.executeDBQuery;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import com.opentable.db.postgres.embedded.FlywayPreparer;
import com.opentable.db.postgres.junit.EmbeddedPostgresRules;
import com.opentable.db.postgres.junit.PreparedDbRule;
import com.test.project.entity.UserDto;
import com.test.project.helper.TestJsonHelper;
import com.test.project.repository.UserRepository;
import com.test.project.service.UserService;
import java.util.UUID;
import javax.sql.DataSource;
import org.junit.Rule;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.http.ResponseEntity;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.ActiveProfiles;


@SpringBootTest(classes = Task1Application.class,
    webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@ActiveProfiles("test")
@DirtiesContext
class UsersTest {

  private static final String CREATE_TABLE = "db/migration/V20221103102600__add_users_table.sql";
  private static final String INSERT_USER = "db/migration/V20221103163416__insert_user_data.sql";
  private static final String DROP_TABLE = "db/migration/V20221104143048__drop_user_table.sql";
  private static final String GET_USER_BY_ID = "/users/";
  private static final String USER_ID = "2f09d8c1-a521-4bc0-b840-84a75e98a119";
  private static final String RESPONSE = "{\"name\":\"Vova\",\"surname\":\"Bezridnyi\",\"age\":20}";
  @Rule
  public PreparedDbRule db =
      EmbeddedPostgresRules.preparedDatabase(
          FlywayPreparer.forClasspathLocation("db/task1"));

  @LocalServerPort
  private int applicationPort;

  @Autowired
  private DataSource dataSource;

  @Autowired
  private UserRepository userRepository;
  private UserService userService;

  @BeforeEach
  void initialize() {
    executeDBQuery(dataSource, CREATE_TABLE);
    executeDBQuery(dataSource, INSERT_USER);
    this.userService = new UserService(userRepository);
  }

  @Test
  void testUserService_ShouldReturnUserDto() {
    UserDto user = userService.findUserById(
        UUID.fromString(USER_ID));
    assertNotNull(user);
  }

  @Test
  void getUserById_ShouldReturnUserDto() {
    ResponseEntity<String> response = TestJsonHelper.getForEntity(applicationPort,
        GET_USER_BY_ID + USER_ID);

    assertEquals(RESPONSE, response.getBody());
  }

  @AfterEach
  void deleteData() {
    executeDBQuery(dataSource, DROP_TABLE);
  }

}
