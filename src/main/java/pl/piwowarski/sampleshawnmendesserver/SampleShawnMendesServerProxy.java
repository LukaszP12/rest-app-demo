package pl.piwowarski.sampleshawnmendesserver;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(value = "sample-server-shawn-mendes-client")
public interface SampleShawnMendesServerProxy {

    // GET http://localhost:8080/shawn/songs
    @GetMapping("/shawn/songs")
    SampleServerShawnMendesResponse fetchAllSongs(@RequestHeader String requestId);

    @PostMapping("/shawn/songs")
    SampleServerShawnMendesResponse addSong(@RequestBody SampleShawnMendesRequest request);

    @DeleteMapping("/shawn/songs/{songId}")
    void deleteByPathVariableId(@PathVariable int songId);

    @DeleteMapping("/shawn/songs")
    void deleteByIdUsingQueryParam(@RequestParam(name = "id") int songId);

}
