package application;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.*;
import service.MetallicaService;

/**
 * Created by Dmytro_Plekhotkin on 9/4/2015.
 */
@Configuration
@ComponentScan(basePackages = {"application", "model", "repository", "service"})
@PropertySource("classpath:/application.properties")
@Import({MongoDbConfiguration.class})
public class Application {

    @Autowired
    MetallicaService metallicaService;

    public static void main(String[] args) {
        ApplicationContext applicationContext = new AnnotationConfigApplicationContext(Application.class);
        MetallicaService metallicaService = applicationContext.getBean(MetallicaService.class);
        metallicaService.gatherSongStatistics();
    }
}
