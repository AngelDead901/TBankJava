package ru.loh228._9.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.Map;

@RestController
@RequestMapping("/api/payment/")
public class PaymentController {
    Map<Integer, Double> map = Map.of(
            1, 12.0,
            2, 239.10,
            3, 100932.0
    );

    @PostMapping("login/login={login}?{password}")
    public void login(@PathVariable String login, @PathVariable String password) {
        System.out.println(login + " " + password);
    }

    /*@GetMapping("get")
    public String getSomething(@RequestBody PaymentSearchController request) {
        return new PaymentSearchResponse(
                request.getId(),
                request.getSum()
        ).toString();

    }*/

    @GetMapping("date")
    public LocalDateTime getPaymentDate(){
        return LocalDateTime.now();
    }
}
