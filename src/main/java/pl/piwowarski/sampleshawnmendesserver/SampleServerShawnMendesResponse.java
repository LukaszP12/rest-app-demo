package pl.piwowarski.sampleshawnmendesserver;

import java.util.List;

public record SampleServerShawnMendesResponse(Integer resultCount, List<SampleServerShawnMendesResult> results) {
}
