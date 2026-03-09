package com.streaming.seeder;

import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

@Configuration
@Profile({"dev", "test", "default"})
@ConditionalOnProperty(name = "app.seeder.enabled", havingValue = "true", matchIfMissing = true)
public class DataSeederConfiguration {
    
}
