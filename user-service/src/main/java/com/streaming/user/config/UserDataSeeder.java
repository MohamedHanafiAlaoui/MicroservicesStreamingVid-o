package com.streaming.user.config;

import com.streaming.user.entity.User;
import com.streaming.user.entity.WatchHistory;
import com.streaming.user.entity.Watchlist;
import com.streaming.user.repository.UserRepository;
import com.streaming.user.repository.WatchHistoryRepository;
import com.streaming.user.repository.WatchlistRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;

@Configuration
@RequiredArgsConstructor
@Slf4j
public class UserDataSeeder {

    private final UserRepository userRepository;
    private final WatchlistRepository watchlistRepository;
    private final WatchHistoryRepository watchHistoryRepository;

    @Bean
    @Order(1)
    public CommandLineRunner seedUserData() {
        return args -> {
            if (userRepository.count() == 0) {
                log.info("Seeding user data...");
                
                List<User> users = Arrays.asList(
                    new User("john_doe", "john.doe@example.com", "password123"),
                    new User("jane_smith", "jane.smith@example.com", "password123"),
                    new User("mike_wilson", "mike.wilson@example.com", "password123"),
                    new User("sarah_jones", "sarah.jones@example.com", "password123"),
                    new User("alex_brown", "alex.brown@example.com", "password123")
                );

                userRepository.saveAll(users);
                log.info("Successfully seeded {} users", users.size());

                seedWatchlistAndHistory(users);
            } else {
                log.info("User data already exists. Skipping seeding.");
            }
        };
    }

    private void seedWatchlistAndHistory(List<User> users) {
        log.info("Seeding watchlist and watch history data...");

        User john = users.get(0);
        User jane = users.get(1);
        User mike = users.get(2);

        List<Watchlist> watchlists = Arrays.asList(
            new Watchlist(john, 1L, LocalDateTime.now().minusDays(5)),
            new Watchlist(john, 3L, LocalDateTime.now().minusDays(3)),
            new Watchlist(jane, 2L, LocalDateTime.now().minusDays(7)),
            new Watchlist(jane, 5L, LocalDateTime.now().minusDays(2)),
            new Watchlist(mike, 1L, LocalDateTime.now().minusDays(1)),
            new Watchlist(mike, 8L, LocalDateTime.now().minusDays(4))
        );

        watchlistRepository.saveAll(watchlists);

        List<WatchHistory> watchHistories = Arrays.asList(
            new WatchHistory(john, 1L, LocalDateTime.now().minusDays(10), 148, true),
            new WatchHistory(john, 4L, LocalDateTime.now().minusDays(8), 142, true),
            new WatchHistory(jane, 2L, LocalDateTime.now().minusDays(15), 152, true),
            new WatchHistory(jane, 6L, LocalDateTime.now().minusDays(12), 112, false),
            new WatchHistory(mike, 3L, LocalDateTime.now().minusDays(20), 300, false),
            new WatchHistory(mike, 7L, LocalDateTime.now().minusDays(18), 22, true)
        );

        watchHistoryRepository.saveAll(watchHistories);

        log.info("Successfully seeded {} watchlist items and {} watch history entries", 
                watchlists.size(), watchHistories.size());
    }
}
