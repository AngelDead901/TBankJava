package ru.loh228._9.api.Controllers;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;


@Getter
@Setter
@AllArgsConstructor
public class AccountRequest {
    private String email, password, firstName, lastName, middleName;
    private Integer dd, mm, yy, operationType;
    private long id;
}
