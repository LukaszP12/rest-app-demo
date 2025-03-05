package pl.piwowarski.itunes;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(value = "itunes-client")
public interface ItunesProxy {

    @GetMapping("/search")
    ItunesResponse makeSearchRequest(
            @RequestParam("term") String term,
            @RequestParam("limit") Integer limit
    );
}
