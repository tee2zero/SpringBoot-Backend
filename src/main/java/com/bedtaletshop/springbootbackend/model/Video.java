package com.bedtaletshop.springbootbackend.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

// Test Code not createtable

//@Entity
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class Video implements Comparable<Video> {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    Long videoId;

    String title;
    Integer userId; // id of User who uploaded video
    String user;
    Integer lengthOfVideo;
    Integer viewCount = 0;
    String description;
    Date videoPostedDate;
    Integer likeCount = 0;
    Integer dislikeCount = 0;
    String category;
    //List<Tag> videoTags;

    // Video ID will be create to other table but it will not id to table of comments
    @ElementCollection
    @CollectionTable(name = "video_comments", joinColumns = @JoinColumn(name = "video_id"))
    @Column(name = "comment")
    List<Comments> comments;

    public void addCommentToList(Comments comment) {
        this.comments.add(comment);
    }

    public void incrementDislikeCount() {
        this.dislikeCount++;
    }

    public void decrementDislikeCount() {
        this.dislikeCount--;
    }

    public void incrementLikeCount() {
        this.likeCount++;
    }

    public void decrementLikeCount() {
        this.likeCount--;
    }

    public void incrementViewCount() {
        this.viewCount++;
    }


    @Override
    public int compareTo(Video o) {
        if (this.getVideoId() - o.getVideoId() == 0) {
            return 0;
        } else if (this.getVideoId() - o.getVideoId() > 0) {
            return 1;
        } else {
            return -1;
        }
    }

    @Override
    public String toString() {
        return "Video{" +
                "videoId=" + videoId +
//                ", title='" + title + '\'' +
//                ", userId=" + userId +
//                ", lengthOfVideo=" + lengthOfVideo +
                ", viewCount=" + viewCount +
//                ", description='" + description + '\'' +
//                ", videoPostedDate=" + videoPostedDate +
//                ", likeCount=" + likeCount +
//                ", dislikeCount=" + dislikeCount +
//                ", category='" + category + '\'' +
//                ", comments=" + comments +
                '}';
    }
}