package dev.aaljukic.babylon.babylon_card_holder_service.common.config;

import dev.aaljukic.babylon.babylon_card_holder_service.data.model.dao.CardHolder;
import dev.aaljukic.babylon.babylon_card_holder_service.data.model.dao.Status;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.rest.core.config.RepositoryRestConfiguration;
import org.springframework.data.rest.webmvc.config.RepositoryRestConfigurer;
import org.springframework.web.servlet.config.annotation.CorsRegistry;

@Configuration
public class SpringDataRestConfig implements RepositoryRestConfigurer {
    @Override
    public void configureRepositoryRestConfiguration(RepositoryRestConfiguration config, CorsRegistry cors) {
        config.exposeIdsFor(CardHolder.class, Status.class);
        config.disableDefaultExposure();
    }
}

