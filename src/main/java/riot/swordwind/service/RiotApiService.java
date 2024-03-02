package riot.swordwind.service;

import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClient;
import riot.swordwind.dto.RiotMatchDetailResponseDto;
import riot.swordwind.dto.RiotSummonerResponseDto;
import riot.swordwind.entity.ApiKey;
import riot.swordwind.repository.ApiKeyRepository;

import java.util.ArrayList;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class RiotApiService {
    private final ApiKeyRepository apiKeyRepository;
    private RestClient restClient = RestClient.create();
    private String API_KEY;

    /*
        https://kr.api.riotgames.com/lol/summoner/v4/summoners/by-name/${summonerName}
        https://asia.api.riotgames.com/lol/match/v5/matches/by-puuid/${puuid}/ids?start=${start}&count=${count}
        https://asia.api.riotgames.com/lol/match/v5/matches/${matchId}
     */


    @PostConstruct
    private void init() {
        Optional<ApiKey> optionalApiKey = apiKeyRepository.findById(1L);

        ApiKey apiKey = optionalApiKey.orElse(new ApiKey(1L, ""));
        API_KEY = apiKey.getCode();
    }

    public ResponseEntity<RiotSummonerResponseDto> findSummonerByRiotAPI(String name) {
        RestClient customClient = RestClient.builder()
                .defaultHeader("X-Riot-Token", API_KEY)
                .build();

        ResponseEntity<RiotSummonerResponseDto> res = customClient.get()
                .uri("https://kr.api.riotgames.com/lol/summoner/v4/summoners/by-name/{name}", name)
                .accept(MediaType.APPLICATION_JSON)
                .retrieve()
                .toEntity(RiotSummonerResponseDto.class);

        return res;
    }

    public ResponseEntity<ArrayList> findMatchIdList(String puuid) {
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

    public ResponseEntity<RiotMatchDetailResponseDto> findMatchDetail(String matchId) {
        RestClient customClient = RestClient.builder()
                .defaultHeader("X-Riot-Token", API_KEY)
                .build();

        ResponseEntity<RiotMatchDetailResponseDto> res = customClient.get()
                .uri("https://asia.api.riotgames.com/lol/match/v5/matches/{matchId}", matchId)
                .accept(MediaType.APPLICATION_JSON)
                .retrieve()
                .toEntity(RiotMatchDetailResponseDto.class);

        return res;
    }

}
