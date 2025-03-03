package pl.piwowarski;

import feign.FeignException;
import feign.RetryableException;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.event.ApplicationStartedEvent;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.event.EventListener;

import java.util.List;

@SpringBootApplication
@EnableFeignClients
@Log4j2
public class AppApplication {

    @Autowired
    private ShawnMendesProxy shawnMendesClient;

    public static void main(String[] args) {
        SpringApplication.run(AppApplication.class, args);
    }

    @EventListener(ApplicationStartedEvent.class)
    public void makeRequestToShawnMendesEndpoint() {
        try {
            ShawnMendesResponse response = shawnMendesClient.makeSearchRequest("shawnmendes", 5);
            List<ShawnMendesResult> results = response.results();
            results.forEach(
                    result ->
                            System.out.println(
                                    "shawnMendesResult.artistName(): " + result.artistName() +
                                            " shawnMendesResult.trackName(): " + result.trackName()
                            )
            );
        } catch (FeignException.FeignClientException feignException) {
            System.out.println("client exception: " + feignException.status());
            log.error("client exception: " + feignException.status());
        } catch (FeignException.FeignServerException feignException) {
            System.out.println("server exception: " + feignException.status());
            log.error("server exception: " + feignException.status());
        } catch (RetryableException retryableException) {
            System.out.println("retryable Exception: " + retryableException.getMessage());
            log.error("retryable Exception: " + retryableException.getMessage());
        } catch (FeignException feignException) {
            log.error("message: " + feignException.getMessage() + ", " +
                    " status: " + feignException.status());
            System.out.println(feignException.getMessage());
            System.out.println(feignException.status());
        }
    }
}
