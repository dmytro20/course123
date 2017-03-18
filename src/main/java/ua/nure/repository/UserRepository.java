package ua.nure.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import ua.nure.model.User;


public interface UserRepository extends MongoRepository<User, String> {
    User findByUsername(String username);
    User findById(String id);
}
