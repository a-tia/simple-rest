package rest.api.task.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import rest.api.task.model.AppUser;
import rest.api.task.service.AppUserService;

@RestController
@RequiredArgsConstructor // for default constructor creation
public class AppUserController {

    private final AppUserService userService;

    @PostMapping("/users")
    public void addUser(@RequestBody AppUser appUser) {
        userService.addUser(appUser);
    }

    @GetMapping("/users/{id}")
    public ResponseEntity<AppUser> getUser(@PathVariable long id) {
        return ResponseEntity.ok(userService.getUserById(id));
    }

    @PutMapping("/users/update/gender")
    public void updateUserGender(@RequestParam long id, @RequestParam String gender) {
        userService.updateUserGender(id, gender);
    }

    @PutMapping("/users/update/age")
    public void updateUserAge(@RequestParam long id, @RequestParam Integer age) {
        userService.updateUserAge(id, age);
    }

    @DeleteMapping("/users")
    public void deleteUser(@RequestParam long id){
        userService.deleteUser(id);
    }

}

