package com.streaming.video.repository;

import com.streaming.video.entity.Video;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface VideoRepository extends JpaRepository<Video, Long> {
    
    List<Video> findByType(Video.VideoType type);
    
    List<Video> findByCategory(Video.VideoCategory category);
    
    List<Video> findByTypeAndCategory(Video.VideoType type, Video.VideoCategory category);
    
    @Query("SELECT v FROM Video v WHERE v.title LIKE %:title%")
    List<Video> findByTitleContaining(@Param("title") String title);
    
    @Query("SELECT v FROM Video v WHERE v.rating >= :minRating")
    List<Video> findByRatingGreaterThanEqual(@Param("minRating") Double minRating);
    
    @Query("SELECT v FROM Video v WHERE v.releaseYear = :year")
    List<Video> findByReleaseYear(@Param("year") Integer year);
}
