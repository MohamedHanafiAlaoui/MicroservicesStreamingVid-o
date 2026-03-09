package com.streaming.user.service;

import com.streaming.user.client.VideoServiceClient;
import com.streaming.user.dto.VideoDTO;
import com.streaming.user.dto.WatchlistDTO;
import com.streaming.user.entity.User;
import com.streaming.user.entity.Watchlist;
import com.streaming.user.repository.UserRepository;
import com.streaming.user.repository.WatchlistRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class WatchlistService {
    
    @Autowired
    private WatchlistRepository watchlistRepository;
    
    @Autowired
    private UserRepository userRepository;
    
    @Autowired
    private VideoServiceClient videoServiceClient;
    
    public List<WatchlistDTO> getUserWatchlist(Long userId) {
        if (!userRepository.existsById(userId)) {
            throw new RuntimeException("User not found");
        }
        
        List<Watchlist> watchlist = watchlistRepository.findByUserId(userId);
        return watchlist.stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }
    
    public Optional<WatchlistDTO> addToWatchlist(Long userId, Long videoId) {
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
        
        Optional<Watchlist> existing = watchlistRepository.findByUserIdAndVideoId(userId, videoId);
        if (existing.isPresent()) {
            throw new RuntimeException("Video already in watchlist");
        }
        
        User user = userRepository.findById(userId).get();
        Watchlist watchlist = new Watchlist(user, videoId, LocalDateTime.now());
        Watchlist saved = watchlistRepository.save(watchlist);
        return Optional.of(convertToDTO(saved));
    }
    
    public boolean removeFromWatchlist(Long userId, Long videoId) {
        if (!userRepository.existsById(userId)) {
            throw new RuntimeException("User not found");
        }
        
        Optional<Watchlist> existing = watchlistRepository.findByUserIdAndVideoId(userId, videoId);
        if (!existing.isPresent()) {
            return false;
        }
        
        watchlistRepository.deleteByUserIdAndVideoId(userId, videoId);
        return true;
    }
    
    public boolean isInWatchlist(Long userId, Long videoId) {
        return watchlistRepository.findByUserIdAndVideoId(userId, videoId).isPresent();
    }
    
    private WatchlistDTO convertToDTO(Watchlist watchlist) {
        WatchlistDTO dto = new WatchlistDTO();
        dto.setId(watchlist.getId());
        dto.setUserId(watchlist.getUser().getId());
        dto.setVideoId(watchlist.getVideoId());
        dto.setAddedAt(watchlist.getAddedAt());
        
        try {
            VideoDTO video = videoServiceClient.getVideoById(watchlist.getVideoId());
            dto.setVideo(video);
        } catch (Exception e) {
            // Video service might be unavailable, set video to null
            dto.setVideo(null);
        }
        
        return dto;
    }
}
