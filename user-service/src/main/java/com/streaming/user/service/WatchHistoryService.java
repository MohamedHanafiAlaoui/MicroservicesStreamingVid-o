package com.streaming.user.service;

import com.streaming.user.client.VideoServiceClient;
import com.streaming.user.dto.UserStatsDTO;
import com.streaming.user.dto.VideoDTO;
import com.streaming.user.dto.WatchHistoryDTO;
import com.streaming.user.entity.User;
import com.streaming.user.entity.WatchHistory;
import com.streaming.user.repository.UserRepository;
import com.streaming.user.repository.WatchHistoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class WatchHistoryService {
    
    @Autowired
    private WatchHistoryRepository watchHistoryRepository;
    
    @Autowired
    private UserRepository userRepository;
    
    @Autowired
    private VideoServiceClient videoServiceClient;
    
    public List<WatchHistoryDTO> getUserWatchHistory(Long userId) {
        if (!userRepository.existsById(userId)) {
            throw new RuntimeException("User not found");
        }
        
        List<WatchHistory> history = watchHistoryRepository.findByUserIdOrderByWatchedAtDesc(userId);
        return history.stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }
    
    public Optional<WatchHistoryDTO> recordWatchHistory(Long userId, Long videoId, Integer progressTime, Boolean completed) {
        if (!userRepository.existsById(userId)) {
            throw new RuntimeException("User not found");
        }
        
        try {
            VideoDTO video = videoServiceClient.getVideoById(videoId);
            if (video == null) {
                throw new RuntimeException("Video not found");
            }
        } catch (Exception e) {
            throw new RuntimeException("Video not found", e);
        }
        
        Optional<WatchHistory> existing = watchHistoryRepository.findByUserIdAndVideoId(userId, videoId);
        WatchHistory watchHistory;
        
        if (existing.isPresent()) {
            watchHistory = existing.get();
            watchHistory.setWatchedAt(LocalDateTime.now());
            watchHistory.setProgressTime(progressTime);
            watchHistory.setCompleted(completed);
        } else {
            User user = userRepository.findById(userId).get();
            watchHistory = new WatchHistory(user, videoId, LocalDateTime.now(), progressTime, completed);
        }
        
        WatchHistory saved = watchHistoryRepository.save(watchHistory);
        return Optional.of(convertToDTO(saved));
    }
    
    public Optional<WatchHistoryDTO> getWatchHistory(Long userId, Long videoId) {
        Optional<WatchHistory> history = watchHistoryRepository.findByUserIdAndVideoId(userId, videoId);
        return history.map(this::convertToDTO);
    }
    
    public UserStatsDTO getUserStats(Long userId) {
        if (!userRepository.existsById(userId)) {
            throw new RuntimeException("User not found");
        }
        
        User user = userRepository.findById(userId).get();
        Long totalVideosWatched = watchHistoryRepository.countByUserId(userId);
        Long completedVideos = watchHistoryRepository.countCompletedByUserId(userId);
        Integer totalWatchTime = watchHistoryRepository.getTotalWatchTimeByUserId(userId);
        
        Double averageCompletionRate = totalVideosWatched > 0 ? 
            (double) completedVideos / totalVideosWatched * 100 : 0.0;
        
        return new UserStatsDTO(
            userId, 
            user.getUsername(), 
            totalVideosWatched, 
            completedVideos, 
            totalWatchTime, 
            averageCompletionRate
        );
    }
    
    private WatchHistoryDTO convertToDTO(WatchHistory watchHistory) {
        WatchHistoryDTO dto = new WatchHistoryDTO();
        dto.setId(watchHistory.getId());
        dto.setUserId(watchHistory.getUser().getId());
        dto.setVideoId(watchHistory.getVideoId());
        dto.setWatchedAt(watchHistory.getWatchedAt());
        dto.setProgressTime(watchHistory.getProgressTime());
        dto.setCompleted(watchHistory.getCompleted());
        
        try {
            VideoDTO video = videoServiceClient.getVideoById(watchHistory.getVideoId());
            dto.setVideo(video);
        } catch (Exception e) {
            // Video service might be unavailable, set video to null
            dto.setVideo(null);
        }
        
        return dto;
    }
}
