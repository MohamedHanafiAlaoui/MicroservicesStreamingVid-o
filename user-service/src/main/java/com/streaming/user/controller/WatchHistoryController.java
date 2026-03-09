package com.streaming.user.controller;

import com.streaming.user.dto.UserStatsDTO;
import com.streaming.user.dto.WatchHistoryDTO;
import com.streaming.user.service.WatchHistoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping("/api/history")
public class WatchHistoryController {
    
    @Autowired
    private WatchHistoryService watchHistoryService;
    
    @GetMapping("/user/{userId}")
    public ResponseEntity<List<WatchHistoryDTO>> getUserWatchHistory(@PathVariable Long userId) {
        try {
            List<WatchHistoryDTO> history = watchHistoryService.getUserWatchHistory(userId);
            return ResponseEntity.ok(history);
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }
    
    @PostMapping("/user/{userId}/video/{videoId}")
    public ResponseEntity<WatchHistoryDTO> recordWatchHistory(
            @PathVariable Long userId, 
            @PathVariable Long videoId,
            @RequestBody Map<String, Object> requestBody) {
        
        try {
            Integer progressTime = (Integer) requestBody.getOrDefault("progressTime", 0);
            Boolean completed = (Boolean) requestBody.getOrDefault("completed", false);
            
            Optional<WatchHistoryDTO> history = watchHistoryService.recordWatchHistory(userId, videoId, progressTime, completed);
            return history.map(ResponseEntity::ok)
                    .orElse(ResponseEntity.badRequest().build());
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().build();
        }
    }
    
    @GetMapping("/user/{userId}/video/{videoId}")
    public ResponseEntity<WatchHistoryDTO> getWatchHistory(@PathVariable Long userId, @PathVariable Long videoId) {
        try {
            Optional<WatchHistoryDTO> history = watchHistoryService.getWatchHistory(userId, videoId);
            return history.map(ResponseEntity::ok)
                    .orElse(ResponseEntity.notFound().build());
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }
    
    @GetMapping("/user/{userId}/stats")
    public ResponseEntity<UserStatsDTO> getUserStats(@PathVariable Long userId) {
        try {
            UserStatsDTO stats = watchHistoryService.getUserStats(userId);
            return ResponseEntity.ok(stats);
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }
}
