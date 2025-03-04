package pl.piwowarski.sampleshawnmendesserver;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import pl.piwowarski.itunes.ItunesResponse;

@FeignClient(value = "sample-server-shawn-mendes-client")
public interface SampleServerShawnMendesProxy {

    @GetMapping("/search")
    ItunesResponse makeSearchRequest(
        @RequestParam("term") String term,
        @RequestParam("limit") Integer limit
    );
}
