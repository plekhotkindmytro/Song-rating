package service;

import model.Song;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import repository.SongRepository;

import java.util.List;

/**
 * Created by Dmytro_Plekhotkin on 9/4/2015.
 */
@Service
public class SongService {
    @Autowired
    private SongRepository songRepository;

    public List<Song> findAll() {
        return songRepository.findAll();
    }

    public Song findById(String id) {
        return songRepository.findOne(id);
    }

    public Song save(Song song) {
        return  songRepository.save(song);
    }
}
