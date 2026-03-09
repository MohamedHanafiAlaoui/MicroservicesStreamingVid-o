package com.streaming.user.dto;

public class UserStatsDTO {
    
    private Long userId;
    private String username;
    private Long totalVideosWatched;
    private Long completedVideos;
    private Integer totalWatchTime;
    private Double averageCompletionRate;

    public UserStatsDTO() {}

    public UserStatsDTO(Long userId, String username, Long totalVideosWatched, Long completedVideos, Integer totalWatchTime, Double averageCompletionRate) {
        this.userId = userId;
        this.username = username;
        this.totalVideosWatched = totalVideosWatched;
        this.completedVideos = completedVideos;
        this.totalWatchTime = totalWatchTime;
        this.averageCompletionRate = averageCompletionRate;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public Long getTotalVideosWatched() {
        return totalVideosWatched;
    }

    public void setTotalVideosWatched(Long totalVideosWatched) {
        this.totalVideosWatched = totalVideosWatched;
    }

    public Long getCompletedVideos() {
        return completedVideos;
    }

    public void setCompletedVideos(Long completedVideos) {
        this.completedVideos = completedVideos;
    }

    public Integer getTotalWatchTime() {
        return totalWatchTime;
    }

    public void setTotalWatchTime(Integer totalWatchTime) {
        this.totalWatchTime = totalWatchTime;
    }

    public Double getAverageCompletionRate() {
        return averageCompletionRate;
    }

    public void setAverageCompletionRate(Double averageCompletionRate) {
        this.averageCompletionRate = averageCompletionRate;
    }
}
