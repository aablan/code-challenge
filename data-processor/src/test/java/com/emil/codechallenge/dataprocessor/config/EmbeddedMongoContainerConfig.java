package com.emil.codechallenge.dataprocessor.config;


import com.emil.codechallenge.dataprocessor.repository.TemperatureDataRepositoryMongo;
import com.emil.codechallenge.dataprocessor.service.persister.temperature.TemperatureDataPersister;
import com.emil.codechallenge.dataprocessor.utils.MongoContainerWrapper;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import org.jetbrains.annotations.NotNull;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.context.event.ContextClosedEvent;
import org.springframework.context.event.EventListener;
import org.springframework.data.mongodb.config.AbstractMongoClientConfiguration;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

@Profile("embedded_mongo")
@Configuration
@EnableMongoRepositories(
    basePackageClasses = {
        TemperatureDataRepositoryMongo.class
    }
)
@ComponentScan(
    basePackageClasses = {
        TemperatureDataPersister.class
    }
)
public class EmbeddedMongoContainerConfig extends AbstractMongoClientConfiguration {


  @Bean
  public MongoContainerWrapper mongoContainer() {
    return new MongoContainerWrapper();
  }

  @NotNull
  @Override
  public MongoClient mongoClient() {
    return MongoClients
        .create(
            "mongodb://" +
                mongoContainer().get().getContainerIpAddress() +
                ":" +
                mongoContainer().getMongoDefaultPort()
        );
  }

  @Override
  protected String getDatabaseName() {
    return "processed_data_test_db";
  }

  @EventListener
  public void handleContextEnd(ContextClosedEvent ctxEnd) {
    mongoContainer().get().stop();
  }
}
