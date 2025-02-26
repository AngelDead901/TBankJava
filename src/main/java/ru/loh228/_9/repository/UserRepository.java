package ru.loh228._9.repository;

import org.apache.catalina.User;
import org.jooq.DSLContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import ru.tbank.test239.Tables;

@Repository
public class UserRepository {

    @Autowired
    private DSLContext dslContext;

    private void createUser(int id, String firstName, String lastName, String password) {
        dslContext.selectFrom(Tables.USERS);
    }
}
