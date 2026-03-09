package com.streaming.video.config;

import com.streaming.video.entity.Video;
import com.streaming.video.repository.VideoRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;

import java.time.Year;
import java.util.Arrays;
import java.util.List;

@Configuration
@RequiredArgsConstructor
@Slf4j
public class VideoDataSeeder {

    private final VideoRepository videoRepository;

    @Bean
    @Order(1)
    public CommandLineRunner seedVideoData() {
        return args -> {
            if (videoRepository.count() == 0) {
                log.info("Seeding video data...");
                
                List<Video> videos = Arrays.asList(
                    new Video(
                        "Inception",
                        "A skilled thief is offered a chance to have his criminal record erased if he can successfully perform an inception.",
                        "https://example.com/inception.jpg",
                        "https://example.com/inception-trailer.mp4",
                        148,
                        Year.of(2010),
                        Video.VideoType.FILM,
                        Video.VideoCategory.SCIENCE_FICTION,
                        8.8,
                        "Christopher Nolan",
                        "Leonardo DiCaprio, Marion Cotillard, Tom Hardy"
                    ),
                    new Video(
                        "The Dark Knight",
                        "When the menace known as the Joker wreaks havoc and chaos on the people of Gotham, Batman must accept one of the greatest psychological and physical tests.",
                        "https://example.com/dark-knight.jpg",
                        "https://example.com/dark-knight-trailer.mp4",
                        152,
                        Year.of(2008),
                        Video.VideoType.FILM,
                        Video.VideoCategory.ACTION,
                        9.0,
                        "Christopher Nolan",
                        "Christian Bale, Heath Ledger, Aaron Eckhart"
                    ),
                    new Video(
                        "Stranger Things",
                        "When a young boy disappears, his mother, a police chief and his friends must confront terrifying supernatural forces in order to get him back.",
                        "https://example.com/stranger-things.jpg",
                        "https://example.com/stranger-things-trailer.mp4",
                        50,
                        Year.of(2016),
                        Video.VideoType.SERIE,
                        Video.VideoCategory.SCIENCE_FICTION,
                        8.7,
                        "Duffer Brothers",
                        "Millie Bobby Brown, Finn Wolfhard, David Harbour"
                    ),
                    new Video(
                        "The Shawshank Redemption",
                        "Two imprisoned men bond over a number of years, finding solace and eventual redemption through acts of common decency.",
                        "https://example.com/shawshank.jpg",
                        "https://example.com/shawshank-trailer.mp4",
                        142,
                        Year.of(1994),
                        Video.VideoType.FILM,
                        Video.VideoCategory.DRAME,
                        9.3,
                        "Frank Darabont",
                        "Tim Robbins, Morgan Freeman, Bob Gunton"
                    ),
                    new Video(
                        "Breaking Bad",
                        "A high school chemistry teacher diagnosed with inoperable lung cancer turns to manufacturing and selling methamphetamine.",
                        "https://example.com/breaking-bad.jpg",
                        "https://example.com/breaking-bad-trailer.mp4",
                        47,
                        Year.of(2008),
                        Video.VideoType.SERIE,
                        Video.VideoCategory.THRILLER,
                        9.5,
                        "Vince Gilligan",
                        "Bryan Cranston, Aaron Paul, Anna Gunn"
                    ),
                    new Video(
                        "The Conjuring",
                        "Paranormal investigators work to help a family terrorized by a dark presence in their farmhouse.",
                        "https://example.com/conjuring.jpg",
                        "https://example.com/conjuring-trailer.mp4",
                        112,
                        Year.of(2013),
                        Video.VideoType.FILM,
                        Video.VideoCategory.HORREUR,
                        7.5,
                        "James Wan",
                        "Patrick Wilson, Vera Farmiga, Ron Livingston"
                    ),
                    new Video(
                        "The Office",
                        "A mockumentary sitcom that depicts the everyday work lives of office employees in the Scranton, Pennsylvania branch of the fictional Dunder Mifflin Paper Company.",
                        "https://example.com/office.jpg",
                        "https://example.com/office-trailer.mp4",
                        22,
                        Year.of(2005),
                        Video.VideoType.SERIE,
                        Video.VideoCategory.COMEDIE,
                        8.9,
                        "Greg Daniels",
                        "Steve Carell, Rainn Wilson, John Krasinski"
                    ),
                    new Video(
                        "Mad Max: Fury Road",
                        "In a post-apocalyptic wasteland, a woman rebels against a tyrannical ruler in search for her homeland with the aid of a group of female prisoners, a psychotic worshiper, and a drifter named Max.",
                        "https://example.com/mad-max.jpg",
                        "https://example.com/mad-max-trailer.mp4",
                        120,
                        Year.of(2015),
                        Video.VideoType.FILM,
                        Video.VideoCategory.ACTION,
                        8.1,
                        "George Miller",
                        "Tom Hardy, Charlize Theron, Nicholas Hoult"
                    ),
                    new Video(
                        "Interstellar",
                        "A team of explorers travel through a wormhole in space in an attempt to ensure humanity's survival.",
                        "https://example.com/interstellar.jpg",
                        "https://example.com/interstellar-trailer.mp4",
                        169,
                        Year.of(2014),
                        Video.VideoType.FILM,
                        Video.VideoCategory.SCIENCE_FICTION,
                        8.6,
                        "Christopher Nolan",
                        "Matthew McConaughey, Anne Hathaway, Jessica Chastain"
                    ),
                    new Video(
                        "Friends",
                        "Follows the personal and professional lives of six twenty to thirty-something-year-old friends living in Manhattan.",
                        "https://example.com/friends.jpg",
                        "https://example.com/friends-trailer.mp4",
                        22,
                        Year.of(1994),
                        Video.VideoType.SERIE,
                        Video.VideoCategory.COMEDIE,
                        8.9,
                        "David Crane, Marta Kauffman",
                        "Jennifer Aniston, Courteney Cox, Lisa Kudrow"
                    )
                );

                videoRepository.saveAll(videos);
                log.info("Successfully seeded {} videos", videos.size());
            } else {
                log.info("Video data already exists. Skipping seeding.");
            }
        };
    }
}
