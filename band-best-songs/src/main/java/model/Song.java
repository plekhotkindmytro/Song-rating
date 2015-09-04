package model;

import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.annotation.Version;
import org.springframework.data.mongodb.core.mapping.Document;

import java.math.BigInteger;
import java.util.Date;

/**
 * Created by Dmytro_Plekhotkin on 9/4/2015.
 */
@Document
public class Song {

    @Id
    private String id;
    private String videoId;
    private String title;

    @CreatedDate
    private Date created;

    @LastModifiedDate
    private Date updated;

    public String getVideoTitle() {
        return videoTitle;
    }

    public void setVideoTitle(String videoTitle) {
        this.videoTitle = videoTitle;
    }

    public long getVersion() {
        return version;
    }

    public void setVersion(long version) {
        this.version = version;
    }

    private String videoTitle;
    private BigInteger viewCount;

    @Version
    private long version;

    public static Song valueOf(String videoId, String title, String videoTitle, BigInteger viewCount) {
        Song song = new Song();
        song.videoId = videoId;
        song.title = title;
        song.viewCount = viewCount;
        return song;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public BigInteger getViewCount() {
        return viewCount;
    }

    public void setViewCount(BigInteger viewCount) {
        this.viewCount = viewCount;
    }

    public String getVideoId() {
        return videoId;
    }

    public void setVideoId(String videoId) {
        this.videoId = videoId;
    }

    public Date getUpdated() {
        return updated;
    }

    public void setUpdated(Date updated) {
        this.updated = updated;
    }

    public Date getCreated() {
        return created;
    }

    public void setCreated(Date created) {
        this.created = created;
    }

    @Override
    public String toString() {
        return "Song{" +
                "id='" + id + '\'' +
                ", videoId='" + videoId + '\'' +
                ", title='" + title + '\'' +
                ", videoTitle='" + videoTitle + '\'' +
                ", viewCount=" + viewCount +
                ", version=" + version +
                '}';
    }
}
