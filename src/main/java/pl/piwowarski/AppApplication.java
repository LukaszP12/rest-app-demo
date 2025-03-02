package pl.piwowarski;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.event.ApplicationStartedEvent;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.event.EventListener;

import java.util.List;

@SpringBootApplication
@EnableFeignClients
public class AppApplication {

    @Autowired
    private ShawnMendesProxy shawnMendesClient;

    public static void main(String[] args) {
        SpringApplication.run(AppApplication.class, args);
    }

    @EventListener(ApplicationStartedEvent.class)
    public void makeRequestToShawnMendesEndpoint() {
        ShawnMendesResponse response = shawnMendesClient.makeSearchRequest("shawnmendes", 5);
        System.out.println(response);
        List<ShawnMendesResult> results = response.results();
        ShawnMendesResult shawnMendesResult = results.get(0);
        System.out.println(shawnMendesResult);
        results.forEach(
                result ->
                        System.out.println(
                                "shawnMendesResult.artistName(): " + result.artistName() +
                                        "shawnMendesResult.trackName(): " + result.trackName()
                        )
        );
    }
}
