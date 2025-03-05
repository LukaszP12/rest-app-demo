package pl.piwowarski.sampleshawnmendesserver;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;

@FeignClient(value = "sample-server-shawn-mendes-client")
public interface SampleShawnMendesServerProxy {

    // GET http://localhost:8080/shawn/songs
    @GetMapping("/shawn/songs")
    SampleServerShawnMendesResponse fetchAllSongs(@RequestHeader String requestId);
}
