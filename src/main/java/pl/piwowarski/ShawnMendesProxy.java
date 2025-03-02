package pl.piwowarski;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(value = "shawnmendes-client", url = "https://itunes.apple.com")
public interface ShawnMendesProxy {

    @RequestMapping("/search")
    ShawnMendesResponse makeSearchRequest(
            @RequestParam("term") String term,
            @RequestParam("limit") Integer limit
    );
}
