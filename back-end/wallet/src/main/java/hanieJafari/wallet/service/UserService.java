package hanieJafari.wallet.service;

import hanieJafari.wallet.models.Account;
import hanieJafari.wallet.models.User;
import hanieJafari.wallet.repositories.UserRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class UserService {

    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public User createUser(User user) {
        List<Account> accounts = new ArrayList<>();
        user.setAccounts(accounts);
        return userRepository.save(user);
    }

    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    public User getUserById(Long id) {
        return userRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("User not found"));
    }

    public User updateUser(Long id, User userDetails) {
        User user = getUserById(id);
        user.setNationalID(userDetails.getNationalID());
        user.setName(userDetails.getName());
        user.setLastName(userDetails.getLastName());
        user.setBirthDate(userDetails.getBirthDate());
        user.setGender(userDetails.getGender());
        user.setEmail(userDetails.getEmail());
        user.setMilitaryServiceStatement(userDetails.getMilitaryServiceStatement());
        return userRepository.save(user);
    }

    public void deleteUser(Long id) {
        userRepository.deleteById(id);
    }
}
