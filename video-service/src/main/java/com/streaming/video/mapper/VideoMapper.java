package com.streaming.video.mapper;

import com.streaming.video.dto.VideoDTO;
import com.streaming.video.entity.Video;
import org.springframework.stereotype.Component;

@Component
public class VideoMapper {
    
    public VideoDTO toDTO(Video video) {
        if (video == null) {
            return null;
        }
        
        VideoDTO dto = new VideoDTO();
        dto.setId(video.getId());
        dto.setTitle(video.getTitle());
        dto.setDescription(video.getDescription());
        dto.setThumbnailUrl(video.getThumbnailUrl());
        dto.setTrailerUrl(video.getTrailerUrl());
        dto.setDuration(video.getDuration());
        dto.setReleaseYear(video.getReleaseYear());
        dto.setType(video.getType());
        dto.setCategory(video.getCategory());
        dto.setRating(video.getRating());
        dto.setDirector(video.getDirector());
        dto.setCast(video.getCast());
        
        return dto;
    }
    
    public Video toEntity(VideoDTO dto) {
        if (dto == null) {
            return null;
        }
        
        Video video = new Video();
        video.setId(dto.getId());
        video.setTitle(dto.getTitle());
        video.setDescription(dto.getDescription());
        video.setThumbnailUrl(dto.getThumbnailUrl());
        video.setTrailerUrl(dto.getTrailerUrl());
        video.setDuration(dto.getDuration());
        video.setReleaseYear(dto.getReleaseYear());
        video.setType(dto.getType());
        video.setCategory(dto.getCategory());
        video.setRating(dto.getRating());
        video.setDirector(dto.getDirector());
        video.setCast(dto.getCast());
        
        return video;
    }
}
