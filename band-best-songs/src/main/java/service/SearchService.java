package service;

import com.google.api.client.http.HttpRequest;
import com.google.api.client.http.HttpRequestInitializer;
import com.google.api.client.http.HttpTransport;
import com.google.api.client.http.javanet.NetHttpTransport;
import com.google.api.client.json.JsonFactory;
import com.google.api.client.json.jackson2.JacksonFactory;
import com.google.api.services.youtube.YouTube;
import com.google.api.services.youtube.model.*;
import model.Song;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.io.IOException;
import java.math.BigInteger;
import java.util.List;

/**
 * Created by Dmytro_Plekhotkin on 9/4/2015.
 */
@Service
public class SearchService {

    private static final String PROPERTIES_FILENAME = "application.properties";
    private static final long NUMBER_OF_VIDEOS_RETURNED = 1;

    /**
     * Define a global instance of the HTTP transport.
     */
    public static final HttpTransport HTTP_TRANSPORT = new NetHttpTransport();

    /**
     * Define a global instance of the JSON factory.
     */
    public static final JsonFactory JSON_FACTORY = new JacksonFactory();

    @Autowired
    private Environment environment;

    private YouTube youtube;
    private String apiKey;

    @PostConstruct
    public void init() {
        apiKey = environment.getProperty("youtube.apikey");
        youtube = new YouTube.Builder(HTTP_TRANSPORT, JSON_FACTORY, new HttpRequestInitializer() {
            public void initialize(HttpRequest httpRequest) throws IOException {

            }
        }).setApplicationName("song-rating").build();
    }

    public Song search(String songTitle, String band) {
        Song song = null;
        final String queryTerm = band + " " + songTitle;
        try {
            YouTube.Search.List search = newSearch(queryTerm);

            SearchListResponse searchResponse = search.execute();
            List<SearchResult> searchResultList = searchResponse.getItems();
            if (searchResultList != null && searchResultList.size() > 0) {
                SearchResult result = searchResultList.get(0);
                String videoId= result.getId().getVideoId();
                String videoTitle = result.getSnippet().getTitle();
                Video video = getVideo(videoId);
                if(video != null) {
                    BigInteger viewCount = video.getStatistics().getViewCount();
                    song = Song.valueOf(videoId, songTitle, videoTitle, viewCount);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return song;
    }

    private Video getVideo(String videoId) throws IOException {
        YouTube.Videos.List search = youtube.videos().list("statistics");
        search.setId(videoId);
        search.setKey(apiKey);
        VideoListResponse response = search.execute();
        List<Video> videoList = response.getItems();
        Video video = null;
        if (videoList != null && videoList.size() > 0) {
            video = videoList.get(0);
        }
        return video;
    }

    private  YouTube.Search.List newSearch(String queryTerm) throws IOException {
        YouTube.Search.List search = youtube.search().list("id,snippet");
        search.setKey(apiKey);
        search.setQ(queryTerm);

        // Restrict the search results to only include videos. See:
        // https://developers.google.com/youtube/v3/docs/search/list#type
        search.setType("video");
        search.setOrder("viewCount");
        search.setVideoDuration("medium");

        // To increase efficiency, only retrieve the fields that the
        // application uses.
        search.setFields("items(id/videoId,snippet/title)");
        search.setMaxResults(NUMBER_OF_VIDEOS_RETURNED);

        return search;
    }
}
