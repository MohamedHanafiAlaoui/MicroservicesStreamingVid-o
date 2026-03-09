package com.streaming.user.dto;

import java.time.LocalDateTime;

public class WatchHistoryDTO {
    
    private Long id;
    private Long userId;
    private Long videoId;
    private VideoDTO video;
    private LocalDateTime watchedAt;
    private Integer progressTime;
    private Boolean completed;

    public WatchHistoryDTO() {}

    public WatchHistoryDTO(Long userId, Long videoId, LocalDateTime watchedAt, Integer progressTime, Boolean completed) {
        this.userId = userId;
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

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public Long getVideoId() {
        return videoId;
    }

    public void setVideoId(Long videoId) {
        this.videoId = videoId;
    }

    public VideoDTO getVideo() {
        return video;
    }

    public void setVideo(VideoDTO video) {
        this.video = video;
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
