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
import pl.piwowarski.itunes.ItunesProxy;
import pl.piwowarski.sampleshawnmendesserver.SampleServerShawnMendesResponse;
import pl.piwowarski.sampleshawnmendesserver.SampleShawnMendesServerProxy;

@SpringBootApplication
@EnableFeignClients
@Log4j2
public class AppApplication {

    @Autowired
    private ItunesProxy itunesClient;

    @Autowired
    private SampleShawnMendesServerProxy sampleShawnMendesServerClient;

    public static void main(String[] args) {
        SpringApplication.run(AppApplication.class, args);
    }

    @EventListener(ApplicationStartedEvent.class)
    public void makeRequestToShawnMendesEndpoint() {
        try {
//            ItunesResponse response = itunesClient.makeSearchRequest("shawnmendes", 5);
            SampleServerShawnMendesResponse response = sampleShawnMendesServerClient.fetchAllSongs("id1");
            log.info(response);
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
