package com.streaming.user.controller;

import com.streaming.user.dto.WatchlistDTO;
import com.streaming.user.service.WatchlistService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/watchlist")
public class WatchlistController {
    
    @Autowired
    private WatchlistService watchlistService;
    
    @GetMapping("/user/{userId}")
    public ResponseEntity<List<WatchlistDTO>> getUserWatchlist(@PathVariable Long userId) {
        try {
            List<WatchlistDTO> watchlist = watchlistService.getUserWatchlist(userId);
            return ResponseEntity.ok(watchlist);
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }
    
    @PostMapping("/user/{userId}/video/{videoId}")
    public ResponseEntity<WatchlistDTO> addToWatchlist(@PathVariable Long userId, @PathVariable Long videoId) {
        try {
            Optional<WatchlistDTO> watchlist = watchlistService.addToWatchlist(userId, videoId);
            return watchlist.map(ResponseEntity::ok)
                    .orElse(ResponseEntity.badRequest().build());
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().build();
        }
    }
    
    @DeleteMapping("/user/{userId}/video/{videoId}")
    public ResponseEntity<Void> removeFromWatchlist(@PathVariable Long userId, @PathVariable Long videoId) {
        try {
            boolean removed = watchlistService.removeFromWatchlist(userId, videoId);
            return removed ? ResponseEntity.noContent().build() : ResponseEntity.notFound().build();
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }
    
    @GetMapping("/user/{userId}/video/{videoId}")
    public ResponseEntity<Boolean> isInWatchlist(@PathVariable Long userId, @PathVariable Long videoId) {
        try {
            boolean inWatchlist = watchlistService.isInWatchlist(userId, videoId);
            return ResponseEntity.ok(inWatchlist);
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }
}
