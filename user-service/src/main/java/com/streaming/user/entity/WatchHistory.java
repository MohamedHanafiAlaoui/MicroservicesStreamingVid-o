package com.streaming.user.entity;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "watch_history")
public class WatchHistory {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false)
    private User user;
    
    @Column(name = "video_id", nullable = false)
    private Long videoId;
    
    @Column(name = "watched_at", nullable = false)
    private LocalDateTime watchedAt;
    
    @Column(name = "progress_time")
    private Integer progressTime;
    
    @Column(nullable = false)
    private Boolean completed = false;

    public WatchHistory() {}

    public WatchHistory(User user, Long videoId, LocalDateTime watchedAt, Integer progressTime, Boolean completed) {
        this.user = user;
        this.videoId = videoId;
        this.watchedAt = watchedAt;
        this.progressTime = progressTime;
        this.completed = completed;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Long getVideoId() {
        return videoId;
    }

    public void setVideoId(Long videoId) {
        this.videoId = videoId;
    }

    public LocalDateTime getWatchedAt() {
        return watchedAt;
    }

    public void setWatchedAt(LocalDateTime watchedAt) {
        this.watchedAt = watchedAt;
    }

    public Integer getProgressTime() {
        return progressTime;
    }

    public void setProgressTime(Integer progressTime) {
        this.progressTime = progressTime;
    }

    public Boolean getCompleted() {
        return completed;
    }

    public void setCompleted(Boolean completed) {
        this.completed = completed;
    }
}
