package com.streaming.video.dto;

import com.streaming.video.entity.Video;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.Max;
import java.time.Year;

public class VideoDTO {
    
    private Long id;
    
    @NotBlank(message = "Title is required")
    private String title;
    
    private String description;
    
    private String thumbnailUrl;
    
    private String trailerUrl;
    
    @NotNull(message = "Duration is required")
    @Min(value = 1, message = "Duration must be positive")
    private Integer duration;
    
    @NotNull(message = "Release year is required")
    private Year releaseYear;
    
    @NotNull(message = "Type is required")
    private Video.VideoType type;
    
    @NotNull(message = "Category is required")
    private Video.VideoCategory category;
    
    @Min(value = 0, message = "Rating cannot be negative")
    @Max(value = 10, message = "Rating cannot exceed 10")
    private Double rating;
    
    private String director;
    
    private String cast;

    public VideoDTO() {}

    public VideoDTO(String title, String description, String thumbnailUrl, String trailerUrl, 
                    Integer duration, Year releaseYear, Video.VideoType type, Video.VideoCategory category, 
                    Double rating, String director, String cast) {
        this.title = title;
        this.description = description;
        this.thumbnailUrl = thumbnailUrl;
        this.trailerUrl = trailerUrl;
        this.duration = duration;
        this.releaseYear = releaseYear;
        this.type = type;
        this.category = category;
        this.rating = rating;
        this.director = director;
        this.cast = cast;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getThumbnailUrl() {
        return thumbnailUrl;
    }

    public void setThumbnailUrl(String thumbnailUrl) {
        this.thumbnailUrl = thumbnailUrl;
    }

    public String getTrailerUrl() {
        return trailerUrl;
    }

    public void setTrailerUrl(String trailerUrl) {
        this.trailerUrl = trailerUrl;
    }

    public Integer getDuration() {
        return duration;
    }

    public void setDuration(Integer duration) {
        this.duration = duration;
    }

    public Year getReleaseYear() {
        return releaseYear;
    }

    public void setReleaseYear(Year releaseYear) {
        this.releaseYear = releaseYear;
    }

    public Video.VideoType getType() {
        return type;
    }

    public void setType(Video.VideoType type) {
        this.type = type;
    }

    public Video.VideoCategory getCategory() {
        return category;
    }

    public void setCategory(Video.VideoCategory category) {
        this.category = category;
    }

    public Double getRating() {
        return rating;
    }

    public void setRating(Double rating) {
        this.rating = rating;
    }

    public String getDirector() {
        return director;
    }

    public void setDirector(String director) {
        this.director = director;
    }

    public String getCast() {
        return cast;
    }

    public void setCast(String cast) {
        this.cast = cast;
    }
}
