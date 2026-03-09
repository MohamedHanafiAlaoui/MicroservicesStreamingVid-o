package com.streaming.user.dto;

import java.time.LocalDateTime;

public class WatchlistDTO {
    
    private Long id;
    private Long userId;
    private Long videoId;
    private VideoDTO video;
    private LocalDateTime addedAt;

    public WatchlistDTO() {}

    public WatchlistDTO(Long userId, Long videoId, LocalDateTime addedAt) {
        this.userId = userId;
        this.videoId = videoId;
        this.addedAt = addedAt;
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

    public LocalDateTime getAddedAt() {
        return addedAt;
    }

    public void setAddedAt(LocalDateTime addedAt) {
        this.addedAt = addedAt;
    }
}
