package ru.loh228._9.api;

import org.springframework.web.bind.annotation.*;
import ru.loh228._9.api.Controllers.AccountRequest;

import java.sql.Timestamp;
import java.time.LocalDate;
import java.util.ArrayList;

@RestController
@RequestMapping("api/user/")
public class UserController {
    static public ArrayList<User> users = new ArrayList<>();

    public static class User{
        public long id;
        public String email, password, firstName, lastName, middleName;
        public Timestamp dateOfBirth;
        
        public User(String email, String password, String firstName, String lastName, String middleName, Timestamp dateOfBirth) {
            this.id = users.size() + 1;
            this.email = email;
            this.password = password;
            this.firstName = firstName;
            this.lastName = lastName;
            this.middleName = middleName;
            this.dateOfBirth = dateOfBirth;
        }
    }

    @PostMapping("/update")
    void updateAccount(@RequestBody AccountRequest request) {
        if(AuthController.checkReality("", request.getPassword(), "", "", "", 1, 1, LocalDate.now().getYear() - 50) == 0) {
            if (request.getOperationType() == 4) { //Email
                if (UserController.users.get((int) request.getId() - 1).email.equals(request.getEmail()) && UserController.users.get((int) request.getId() - 1).password.equals(request.getPassword())) {
                    UserController.users.get((int) request.getId() - 1).email = request.getEmail();
                }
            } else if (request.getOperationType() == 0) { //pass
                if (UserController.users.get((int) request.getId() - 1).password.equals(request.getPassword())) {
                    UserController.users.get((int) request.getId() - 1).password = request.getPassword();
                }
            } else if (request.getOperationType() == 1) { //fN
                UserController.users.get((int) request.getId() - 1).firstName = request.getFirstName();
            } else if (request.getOperationType() == 2) { //lN
                UserController.users.get((int) request.getId() - 1).lastName = request.getLastName();
            } else if (request.getOperationType() == 3) { //mN
                UserController.users.get((int) request.getId() - 1).middleName = request.getMiddleName();
            }
        }
    }

    @PostMapping("/updateBD")
    void updateAccount(@RequestBody int dd, @RequestBody int mm, @RequestBody int yy, @RequestBody long id) {
        if(AuthController.checkReality("", "", "", "", "", dd, mm, yy) == 0){

        }
    }

    @PostMapping("delete")
    void deleteAccount(@RequestBody AccountRequest request) {
        if(UserController.users.get((int) request.getId() - 1).password.equals(request.getPassword())) {
            for(int i = (int) request.getId(); i < UserController.users.size(); i++) {
                UserController.users.get(i).id--;
            }
        }
        UserController.users.remove((int) request.getId() - 1);
    }
}
