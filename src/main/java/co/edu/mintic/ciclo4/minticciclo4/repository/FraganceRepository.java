package co.edu.mintic.ciclo4.minticciclo4.repository;

import co.edu.mintic.ciclo4.minticciclo4.model.Fragance;
import co.edu.mintic.ciclo4.minticciclo4.model.Order;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

import java.util.Optional;

public interface FraganceRepository extends MongoRepository<Fragance, String> {

    @Query("{reference : { $eq: ?0 }}")
    Optional<Fragance> getFraganceByReference(String reference);
}
