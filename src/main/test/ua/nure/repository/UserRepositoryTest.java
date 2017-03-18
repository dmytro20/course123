package ua.nure.repository;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.test.context.junit4.SpringRunner;
import ua.nure.model.Role;
import ua.nure.model.User;

import java.util.HashSet;
import java.util.Random;
import java.util.Set;
import java.util.UUID;


@RunWith(SpringRunner.class)
@SpringBootTest
public class UserRepositoryTest {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;


    @Before
    public void setUp() throws Exception {

    }

    @Test
    public void insertUserTest(){
        User user = generateUser();
        System.out.println(user);
        userRepository.insert(user);
        System.out.println(userRepository.findById(user.getId()));
    }

    private User generateUser(){
        User user = new User();
        user.setId(UUID.randomUUID().toString());
        user.setPassword(bCryptPasswordEncoder.encode("user"));
        user.setUsername("user"+ new Random().nextInt());
        Set<Role> roles = new HashSet<>();
        roles.add(Role.USER);
        user.setRoles(roles);
        return user;
    }

}