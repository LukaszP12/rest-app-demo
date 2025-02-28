package pl.piwowarski;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;

@FeignClient("shawnmendes-client")
public interface ShawnMendesProxy {

    @RequestMapping("/search")
    String search();
}
