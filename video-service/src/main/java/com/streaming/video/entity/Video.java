package com.streaming.video.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.Max;
import java.time.Year;
import com.streaming.video.config.YearAttributeConverter;

@Entity
@Table(name = "videos")
public class Video {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @NotBlank(message = "Title is required")
    @Column(nullable = false)
    private String title;
    
    @Column(columnDefinition = "TEXT")
    private String description;
    
    private String thumbnailUrl;
    
    private String trailerUrl;
    
    @NotNull(message = "Duration is required")
    @Min(value = 1, message = "Duration must be positive")
    private Integer duration;
    
    @NotNull(message = "Release year is required")
    @Convert(converter = YearAttributeConverter.class)
    private Year releaseYear;
    
    @Enumerated(EnumType.STRING)
    @NotNull(message = "Type is required")
    private VideoType type;
    
    @Enumerated(EnumType.STRING)
    @NotNull(message = "Category is required")
    private VideoCategory category;
    
    @Min(value = 0, message = "Rating cannot be negative")
    @Max(value = 10, message = "Rating cannot exceed 10")
    private Double rating;
    
    private String director;
    
    @Column(name = "cast_members", columnDefinition = "TEXT")
    private String cast;
    
    public enum VideoType {
        FILM, SERIE
    }
    
    public enum VideoCategory {
        ACTION, COMEDIE, DRAME, SCIENCE_FICTION, THRILLER, HORREUR
    }

    public Video() {}

    public Video(String title, String description, String thumbnailUrl, String trailerUrl, 
                 Integer duration, Year releaseYear, VideoType type, VideoCategory category, 
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

    public VideoType getType() {
        return type;
    }

    public void setType(VideoType type) {
        this.type = type;
    }

    public VideoCategory getCategory() {
        return category;
    }

    public void setCategory(VideoCategory category) {
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
