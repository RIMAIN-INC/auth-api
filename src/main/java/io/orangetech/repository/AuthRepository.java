package io.orangetech.repository;

import io.orangetech.entity.User;
import io.quarkus.hibernate.orm.panache.PanacheRepository;
import jakarta.enterprise.context.ApplicationScoped;

import java.util.Optional;

@ApplicationScoped
public class AuthRepository implements PanacheRepository<User> {

    public Optional<User> findByEmail(String email){
        return find("email", email).firstResultOptional();
    }


}
