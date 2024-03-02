package riot.swordwind.service;

import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClient;
import riot.swordwind.dto.MatchDetailResponseDto;
import riot.swordwind.dto.RiotIdResponseDto;
import riot.swordwind.dto.SummonerResponseDto;
import riot.swordwind.entity.ApiKey;
import riot.swordwind.repository.ApiKeyRepository;

import java.util.ArrayList;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class RiotApiService {
    private final ApiKeyRepository apiKeyRepository;
    private String API_KEY;

    @PostConstruct
    private void init() {
        Optional<ApiKey> optionalApiKey = apiKeyRepository.findById(1L);

        ApiKey apiKey = optionalApiKey.orElse(new ApiKey(1L, "RGAPI-5c2dffde-cca4-4a0d-9d3c-de5804a6299c"));
        API_KEY = apiKey.getCode();
    }

    public ResponseEntity<RiotIdResponseDto> requestFindRiotIdByGameNameTag(String gameName, String tagLine) {
        RestClient customClient = RestClient.builder()
                .defaultHeader("X-Riot-Token", API_KEY)
                .build();

        ResponseEntity<RiotIdResponseDto> res = customClient.get()
                .uri("https://asia.api.riotgames.com/riot/account/v1/accounts/by-riot-id/{gameName}/{tagLine}", gameName, tagLine)
                .accept(MediaType.APPLICATION_JSON)
                .retrieve()
                .toEntity(RiotIdResponseDto.class);

        return res;
    }

    public ResponseEntity<SummonerResponseDto> requestFindSummonerBySummonerName(String name) {
        RestClient customClient = RestClient.builder()
                .defaultHeader("X-Riot-Token", API_KEY)
                .build();

        ResponseEntity<SummonerResponseDto> res = customClient.get()
                .uri("https://kr.api.riotgames.com/lol/summoner/v4/summoners/by-name/{name}", name)
                .accept(MediaType.APPLICATION_JSON)
                .retrieve()
                .toEntity(SummonerResponseDto.class);

        return res;
    }

    public ResponseEntity<ArrayList> requestFindMatchList(String puuid) {
        RestClient customClient = RestClient.builder()
                .defaultHeader("X-Riot-Token", API_KEY)
                .build();


        ResponseEntity<ArrayList> res = customClient.get()
                .uri("https://asia.api.riotgames.com/lol/match/v5/matches/by-puuid/{puuid}/ids", puuid)
                .accept(MediaType.APPLICATION_JSON)
                .retrieve()
                .toEntity(ArrayList.class);

        return res;
    }

    public ResponseEntity<MatchDetailResponseDto> requestFindMatchDetail(String matchId) {
        RestClient customClient = RestClient.builder()
                .defaultHeader("X-Riot-Token", API_KEY)
                .build();

        ResponseEntity<MatchDetailResponseDto> res = customClient.get()
                .uri("https://asia.api.riotgames.com/lol/match/v5/matches/{matchId}", matchId)
                .accept(MediaType.APPLICATION_JSON)
                .retrieve()
                .toEntity(MatchDetailResponseDto.class);

        return res;
    }

}
