package riot.swordwind.service;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import riot.swordwind.dto.SummonerResponseDto;

@Service
@RequiredArgsConstructor
public class SummonerSearchService {

    private final SummonerService summonerService;
    private final RiotApiService riotApiService;

    public void searchSummoner(String summonerName) {
        ResponseEntity<SummonerResponseDto> response = riotApiService.requestFindSummonerBySummonerName(summonerName);
        if (response.getStatusCode().is2xxSuccessful()) {
            SummonerResponseDto summoner = response.getBody();
//            Summoner.builder()
//                    .puuid(summoner.getPuuid())
//                    .summonerName(summoner.getName())
//                    .tagGameName(summoner.get)
//                    .build()
        }
    }
}
