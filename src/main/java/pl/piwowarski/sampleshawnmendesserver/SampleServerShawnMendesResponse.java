package pl.piwowarski.sampleshawnmendesserver;

import java.util.List;

public record SampleServerShawnMendesResponse(String message,
                                              List<String> songsName) {
}
