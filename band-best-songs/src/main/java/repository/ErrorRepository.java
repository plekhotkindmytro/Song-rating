package repository;

import model.*;
import model.Error;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

/**
 * Created by Dmytro_Plekhotkin on 9/4/2015.
 */
@Repository
public interface ErrorRepository extends MongoRepository<Error, String> {
}
