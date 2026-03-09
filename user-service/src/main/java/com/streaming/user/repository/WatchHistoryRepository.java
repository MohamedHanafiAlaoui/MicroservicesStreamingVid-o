package com.streaming.user.repository;

import com.streaming.user.entity.WatchHistory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface WatchHistoryRepository extends JpaRepository<WatchHistory, Long> {
    
    List<WatchHistory> findByUserIdOrderByWatchedAtDesc(Long userId);
    
    Optional<WatchHistory> findByUserIdAndVideoId(Long userId, Long videoId);
    
    @Query("SELECT COUNT(wh) FROM WatchHistory wh WHERE wh.user.id = :userId")
    Long countByUserId(@Param("userId") Long userId);
    
    @Query("SELECT COUNT(wh) FROM WatchHistory wh WHERE wh.user.id = :userId AND wh.completed = true")
    Long countCompletedByUserId(@Param("userId") Long userId);
    
    @Query("SELECT SUM(wh.progressTime) FROM WatchHistory wh WHERE wh.user.id = :userId")
    Integer getTotalWatchTimeByUserId(@Param("userId") Long userId);
}
