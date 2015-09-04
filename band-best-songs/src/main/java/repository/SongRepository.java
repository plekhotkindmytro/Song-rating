package repository;

import model.Song;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

/**
 * Created by Dmytro_Plekhotkin on 9/4/2015.
 */
@Repository
public interface SongRepository extends MongoRepository<Song, String>{

}
