package pl.piwowarski.sampleshawnmendesserver;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;

@FeignClient(value = "sample-server-shawn-mendes-client")
public interface SampleShawnMendesServerProxy {

    // GET http://localhost:8080/shawn/songs
    @GetMapping("/shawn/songs")
    SampleServerShawnMendesResponse fetchAllSongs();
}
