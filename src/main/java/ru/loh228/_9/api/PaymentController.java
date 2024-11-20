package ru.loh228._9.api;

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

    @PostMapping("add/{id}/{value}")
    public void setPaymentSum(@PathVariable int id, @PathVariable double value) {
        map.put(id, value);
    }

    @GetMapping("sum/{id}")
    public String getPaymentSum(@PathVariable int id) {
        try {
            return map.get(id).toString();
        }
        catch (Exception e) {
            return "Payment not found";
        }
    }

    @GetMapping("date")
    public LocalDateTime getPaymentDate(){
        return LocalDateTime.now();
    }
}
