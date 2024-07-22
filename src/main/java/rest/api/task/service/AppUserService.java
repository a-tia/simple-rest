package rest.api.task.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import rest.api.task.model.AppUser;
import rest.api.task.repository.AppUserRepository;

@Service
@RequiredArgsConstructor // for default constructor creation
public class AppUserService {

    private final AppUserRepository userRepository;

    public void addUser(AppUser appUser) {
        try {
            userRepository.save(appUser);
        } catch (Exception e) {
            throw new RuntimeException("Failed to add user.", e);
        }
    }

    public AppUser getUserById(long id) {
        return userRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("User not found."));  // If user is not found throw exception.
    }

    @Transactional
    public void updateUserGender(long id, String gender) {
        try {
            userRepository.updateUserGender(id,gender);
        } catch (Exception e) {
            throw new RuntimeException("Failed to update user.", e);
        }
    }

    @Transactional
    public void updateUserAge(long id, Integer age) {
        try {
            userRepository.updateUserAge(id, age);
        } catch (Exception e) {
            throw new RuntimeException("Failed to update user.", e);
        }
    }

    public void deleteUser(long id) {
        try {
            userRepository.deleteById(id);
        } catch (Exception e) {
            throw new RuntimeException("Failed to delete user.", e);
        }
    }
}
