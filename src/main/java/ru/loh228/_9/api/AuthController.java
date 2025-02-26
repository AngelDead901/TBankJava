package ru.loh228._9.api;
import org.springframework.web.bind.annotation.*;
import ru.loh228._9.api.Controllers.AccountRequest;

import java.sql.Timestamp;
import java.time.LocalDate;

@RestController
@RequestMapping("/api/auth")
public class AuthController {
    static int checkReality(String email, String password, String firstName, String lastName, String middleName, int dd, int mm, int yy) {
        int yyNow = LocalDate.now().getYear();
        if(email.length() > 64 || password.length() > 64 || firstName.length() > 64 || lastName.length() > 64 || middleName.length() > 64 || (yy > yyNow - 10 || yy < yyNow - 200) || (dd < 0 || dd > 31) || (mm < 0 || mm > 12)) {
            return 1;
        }
        for(var i : UserController.users) {
            if(i.email.equals(email)) {
                return 2;
            }
        }
        return 0;
    }

    @PostMapping("/create")
    int createAccount(@RequestBody AccountRequest request) {
        int returnCode = checkReality(request.getEmail(), request.getPassword(), request.getFirstName(), request.getLastName(), "", request.getDd(), request.getMm(), request.getYy());
        if(returnCode == 0) {
            UserController.users.add(new UserController.User(request.getEmail(), request.getPassword(), request.getFirstName(), request.getLastName(), "", new Timestamp(request.getDd(), request.getMm(), request.getYy(), 12, 0, 0, 0)));
        }
        return (returnCode == 0 ? 200 : returnCode + 500);
    }

    @PostMapping("/login")
    long loginInto(@RequestBody AccountRequest request) {
        for(var i : UserController.users) {
            if(i.email.equals(request.getEmail()) && i.password.equals(request.getPassword())) {
                return i.id * 1000;
            }
        }
        return 403;
    }
}
