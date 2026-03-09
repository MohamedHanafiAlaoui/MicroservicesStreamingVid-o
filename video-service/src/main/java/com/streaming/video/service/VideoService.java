package com.streaming.video.service;

import com.streaming.video.dto.VideoDTO;
import com.streaming.video.entity.Video;
import com.streaming.video.mapper.VideoMapper;
import com.streaming.video.repository.VideoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class VideoService {
    
    @Autowired
    private VideoRepository videoRepository;
    
    @Autowired
    private VideoMapper videoMapper;
    
    public List<VideoDTO> getAllVideos() {
        return videoRepository.findAll().stream()
                .map(videoMapper::toDTO)
                .collect(Collectors.toList());
    }
    
    public Optional<VideoDTO> getVideoById(Long id) {
        return videoRepository.findById(id)
                .map(videoMapper::toDTO);
    }
    
    public VideoDTO createVideo(VideoDTO videoDTO) {
        Video video = videoMapper.toEntity(videoDTO);
        Video savedVideo = videoRepository.save(video);
        return videoMapper.toDTO(savedVideo);
    }
    
    public Optional<VideoDTO> updateVideo(Long id, VideoDTO videoDTO) {
        if (!videoRepository.existsById(id)) {
            return Optional.empty();
        }
        
        videoDTO.setId(id);
        Video video = videoMapper.toEntity(videoDTO);
        Video updatedVideo = videoRepository.save(video);
        return Optional.of(videoMapper.toDTO(updatedVideo));
    }
    
    public boolean deleteVideo(Long id) {
        if (!videoRepository.existsById(id)) {
            return false;
        }
        
        videoRepository.deleteById(id);
        return true;
    }
    
    public List<VideoDTO> getVideosByType(Video.VideoType type) {
        return videoRepository.findByType(type).stream()
                .map(videoMapper::toDTO)
                .collect(Collectors.toList());
    }
    
    public List<VideoDTO> getVideosByCategory(Video.VideoCategory category) {
        return videoRepository.findByCategory(category).stream()
                .map(videoMapper::toDTO)
                .collect(Collectors.toList());
    }
    
    public List<VideoDTO> getVideosByTypeAndCategory(Video.VideoType type, Video.VideoCategory category) {
        return videoRepository.findByTypeAndCategory(type, category).stream()
                .map(videoMapper::toDTO)
                .collect(Collectors.toList());
    }
    
    public List<VideoDTO> searchVideosByTitle(String title) {
        return videoRepository.findByTitleContaining(title).stream()
                .map(videoMapper::toDTO)
                .collect(Collectors.toList());
    }
    
    public List<VideoDTO> getVideosByMinRating(Double minRating) {
        return videoRepository.findByRatingGreaterThanEqual(minRating).stream()
                .map(videoMapper::toDTO)
                .collect(Collectors.toList());
    }
    
    public List<VideoDTO> getVideosByYear(Integer year) {
        return videoRepository.findByReleaseYear(year).stream()
                .map(videoMapper::toDTO)
                .collect(Collectors.toList());
    }
}
