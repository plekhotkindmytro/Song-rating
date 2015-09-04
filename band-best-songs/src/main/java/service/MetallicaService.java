package service;

import model.*;
import model.Error;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;

/**
 * Created by Dmytro_Plekhotkin on 9/4/2015.
 */
@Service
public class MetallicaService {

    private static final String BAND_NAME = "Metallica";

    @Autowired
    private SearchService searchService;

    @Autowired
    private ErrorService errorService;
    @Autowired
    private SongService songService;

    @Autowired
    private Environment environment;

    private List<String> songTitlesList;
    @PostConstruct
    private void init() throws IOException {
        String songsFilePath = environment.getProperty("metallica.songList.filePath");
        songTitlesList = Files.readAllLines(Paths.get(songsFilePath),
                Charset.defaultCharset());
        System.out.println(songTitlesList);
    }

    public void gatherSongStatistics() {
        for (String title: songTitlesList) {
            Song song = searchService.search(title, BAND_NAME);
            if(song != null) {
                System.out.println(song.toString());
                Song savedSong = songService.save(song);

                try {
                    Thread.sleep(5000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            } else {
                errorService.save(Error.valueOf(title, "Song is null"));
            }
        }

    }
}
