package ru.itis.javalab.smokes;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import ru.itis.javalab.controllers.FreeTimeController;
import ru.itis.javalab.controllers.UserAddController;
import ru.itis.javalab.controllers.UserEventAddController;
import ru.itis.javalab.controllers.UsersEventsAddController;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.notNullValue;
import static org.hamcrest.Matchers.is;

@SpringBootTest
public class SmokeTest {
    @Autowired
    private FreeTimeController freeTimeController;
    @Autowired
    private UserAddController userAddController;
    @Autowired
    private UserEventAddController userEventAddController;
    @Autowired
    private UsersEventsAddController usersEventsAddController;

    @Test
    void contextLoadsFreeTimeController(){
        assertThat(freeTimeController, is(notNullValue()));
    }
    @Test
    void contextLoadsUserAddController(){
        assertThat(userAddController, is(notNullValue()));
    }
    @Test
    void contextLoadsUserEventAddController(){
        assertThat(userEventAddController, is(notNullValue()));
    }
    @Test
    void contextLoadsUsersEventsAddController(){
        assertThat(usersEventsAddController, is(notNullValue()));
    }
}
