package riot.swordwind.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import riot.swordwind.service.SummonerService;

@Controller("/api/search/summoner")
@RequiredArgsConstructor
public class SummonerSearchContainer {

    private final SummonerService summonerService;

    @GetMapping("/{name}/{tagline}")
    public void searchSummoner() {
        
    }
}
