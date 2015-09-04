package application;

import com.mongodb.MongoClient;
import com.mongodb.WriteConcern;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import org.springframework.data.mongodb.config.AbstractMongoConfiguration;
import org.springframework.data.mongodb.config.EnableMongoAuditing;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

/**
 * Created by Dmytro_Plekhotkin on 9/4/2015.
 */
@Configuration
@EnableMongoRepositories(basePackages = {"repository"})
@EnableMongoAuditing
public class MongoDbConfiguration extends AbstractMongoConfiguration {
        @Autowired
        private Environment env;

        @Override
        protected String getDatabaseName() {
            return env.getProperty("db.name");
        }

        @Override
        @Bean
        public MongoClient mongo() throws Exception {
            MongoClient client = new MongoClient(env.getProperty("db.host"));
            client.setWriteConcern(WriteConcern.SAFE);
            return client;
        }

        @Override
        protected String getMappingBasePackage() {
            return "model";
        }

        @Bean
        public MongoTemplate mongoTemplate() throws Exception {
            return new MongoTemplate(mongo(), getDatabaseName());
        }


}
