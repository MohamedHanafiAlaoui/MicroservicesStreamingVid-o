package com.streaming.video.controller;

import com.streaming.video.dto.VideoDTO;
import com.streaming.video.entity.Video;
import com.streaming.video.service.VideoService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/videos")
@Tag(name = "Video Management", description = "APIs for managing video content")
public class VideoController {
    
    @Autowired
    private VideoService videoService;
    
    @GetMapping
    @Operation(summary = "Get all videos", description = "Retrieve a list of all available videos")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Successfully retrieved all videos",
            content = @Content(mediaType = "application/json",
            schema = @Schema(implementation = VideoDTO.class)))
    })
    public ResponseEntity<List<VideoDTO>> getAllVideos() {
        List<VideoDTO> videos = videoService.getAllVideos();
        return ResponseEntity.ok(videos);
    }
    
    @GetMapping("/{id}")
    @Operation(summary = "Get video by ID", description = "Retrieve a specific video by its ID")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Video found",
            content = @Content(mediaType = "application/json",
            schema = @Schema(implementation = VideoDTO.class))),
        @ApiResponse(responseCode = "404", description = "Video not found")
    })
    public ResponseEntity<VideoDTO> getVideoById(
            @Parameter(description = "ID of the video to be retrieved") @PathVariable Long id) {
        Optional<VideoDTO> video = videoService.getVideoById(id);
        return video.map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }
    
    @PostMapping
    @Operation(summary = "Create a new video", description = "Add a new video to the catalog")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "201", description = "Video created successfully",
            content = @Content(mediaType = "application/json",
            schema = @Schema(implementation = VideoDTO.class))),
        @ApiResponse(responseCode = "400", description = "Invalid input")
    })
    public ResponseEntity<VideoDTO> createVideo(
            @Parameter(description = "Video object to be created") @Valid @RequestBody VideoDTO videoDTO) {
        VideoDTO createdVideo = videoService.createVideo(videoDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdVideo);
    }
    
    @PutMapping("/{id}")
    @Operation(summary = "Update a video", description = "Update an existing video's information")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Video updated successfully",
            content = @Content(mediaType = "application/json",
            schema = @Schema(implementation = VideoDTO.class))),
        @ApiResponse(responseCode = "404", description = "Video not found"),
        @ApiResponse(responseCode = "400", description = "Invalid input")
    })
    public ResponseEntity<VideoDTO> updateVideo(
            @Parameter(description = "ID of the video to be updated") @PathVariable Long id,
            @Parameter(description = "Updated video object") @Valid @RequestBody VideoDTO videoDTO) {
        Optional<VideoDTO> updatedVideo = videoService.updateVideo(id, videoDTO);
        return updatedVideo.map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }
    
    @DeleteMapping("/{id}")
    @Operation(summary = "Delete a video", description = "Remove a video from the catalog")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "204", description = "Video deleted successfully"),
        @ApiResponse(responseCode = "404", description = "Video not found")
    })
    public ResponseEntity<Void> deleteVideo(
            @Parameter(description = "ID of the video to be deleted") @PathVariable Long id) {
        boolean deleted = videoService.deleteVideo(id);
        return deleted ? ResponseEntity.noContent().build() : ResponseEntity.notFound().build();
    }
    
    @GetMapping("/type/{type}")
    public ResponseEntity<List<VideoDTO>> getVideosByType(@PathVariable Video.VideoType type) {
        List<VideoDTO> videos = videoService.getVideosByType(type);
        return ResponseEntity.ok(videos);
    }
    
    @GetMapping("/category/{category}")
    public ResponseEntity<List<VideoDTO>> getVideosByCategory(@PathVariable Video.VideoCategory category) {
        List<VideoDTO> videos = videoService.getVideosByCategory(category);
        return ResponseEntity.ok(videos);
    }
    
    @GetMapping("/search")
    public ResponseEntity<List<VideoDTO>> searchVideos(
            @RequestParam(required = false) Video.VideoType type,
            @RequestParam(required = false) Video.VideoCategory category,
            @RequestParam(required = false) String title,
            @RequestParam(required = false) Double minRating,
            @RequestParam(required = false) Integer year) {
        
        List<VideoDTO> videos;
        
        if (type != null && category != null) {
            videos = videoService.getVideosByTypeAndCategory(type, category);
        } else if (type != null) {
            videos = videoService.getVideosByType(type);
        } else if (category != null) {
            videos = videoService.getVideosByCategory(category);
        } else if (title != null) {
            videos = videoService.searchVideosByTitle(title);
        } else if (minRating != null) {
            videos = videoService.getVideosByMinRating(minRating);
        } else if (year != null) {
            videos = videoService.getVideosByYear(year);
        } else {
            videos = videoService.getAllVideos();
        }
        
        return ResponseEntity.ok(videos);
    }
}
