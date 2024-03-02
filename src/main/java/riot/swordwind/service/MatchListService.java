package riot.swordwind.service;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import riot.swordwind.dto.RiotIdResponseDto;
import riot.swordwind.entity.Summoner;

import java.util.ArrayList;

@Service
@RequiredArgsConstructor
public class MatchListService {

    private final SummonerService summonerService;
    private final RiotApiService riotApiService;

    public ArrayList<String> findMatchIdList(String gameName, String tagLine) {
        ResponseEntity<RiotIdResponseDto> riotIdResponse = riotApiService.requestFindRiotIdByGameNameTag(gameName, tagLine);
        RiotIdResponseDto riotDto = riotIdResponse.getBody();
        Summoner summoner = Summoner.builder()
                .puuid(riotDto.getPuuid())
                .summonerName("")
                .tagGameName(riotDto.getGameName() + "#" + riotDto.getTagLine())
                .build();

        summonerService.save(summoner);

        ResponseEntity<ArrayList> matchListResponse = riotApiService.requestFindMatchList(summoner.getPuuid());
        return matchListResponse.getBody();

    }
}
