package co.edu.mintic.ciclo4.minticciclo4.repository;

import co.edu.mintic.ciclo4.minticciclo4.model.User;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

import java.util.Optional;

public interface UserRepository extends MongoRepository<User, String> {

    @Query("{email :?0}")
    Optional<User> getUserByEmail(String email);

    @Query("{email :?0, password :?1}")
    Optional<User> getUserByEmailAAndPassword(String email, String password);
}
