package Spring.sem2.service;

import Spring.sem2.model.User;
import Spring.sem2.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {
    private final UserRepository userRepository;
    @Autowired
    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public List<User> findAll() {
        return userRepository.findAll();
    }

    public User saveUser(User user) {
        return userRepository.save(user);
    }

    public void deleteById(int id) {
        userRepository.deleteById(id);
    }

    public User getOne(int id) {
        return userRepository.findById(id);
    }

    public User updateUser(User user) {
        return userRepository.update(user);
    }
}