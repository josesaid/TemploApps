package mx.com.development.aspectj;

import mx.com.development.aspectj.service.MessageService;
import mx.com.development.aspectj.service.UserService;
import mx.com.development.aspectj.repository.UserRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class AspectjApplicationTests {
    @Autowired
    UserService userService;

    @Autowired
    MessageService messageService;

    @Autowired
    UserRepository userRepository;

    @Test
    void testUserService() {
        userService.createUser("create new user Said Olano", 41);
        userService.deleteUser("Said Olano");
    }

    @Test
    void testMessageService() {
        messageService.sendMessage("send message from user Said Olano");
        messageService.receiveMessage("receive message from user Said Olano");
    }

    @Test
    void testUserRepository() {
        userRepository.createUser("Said Olano", 41);
        userRepository.deleteUser("Said Olano");
    }
}
