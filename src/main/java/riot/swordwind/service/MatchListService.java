package riot.swordwind.service;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import riot.swordwind.dto.RiotIdResponseDto;
import riot.swordwind.entity.Summoner;

import java.util.ArrayList;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class MatchListService {

    private final SummonerService summonerService;
    private final RiotApiService riotApiService;

    public ArrayList<String> findMatchIdList(String gameName, String tagLine) {
        String puuid;
        Optional<Summoner> byGameNameAndTagLine = summonerService.findByGameNameAndTagLine(gameName, tagLine);
        if (byGameNameAndTagLine.isEmpty()) {
            ResponseEntity<RiotIdResponseDto> riotIdResponse = riotApiService.requestFindRiotIdByGameNameTag(gameName, tagLine);
            RiotIdResponseDto riotDto = riotIdResponse.getBody();
            puuid = riotDto.getPuuid();
            ResponseEntity<ArrayList> matchListResponse = riotApiService.requestFindMatchList(puuid);
            return matchListResponse.getBody();
        } else {
            puuid = byGameNameAndTagLine.get().getPuuid();
            ResponseEntity<ArrayList> matchListResponse = riotApiService.requestFindMatchList(puuid);
            return matchListResponse.getBody();
        }
    }
}
