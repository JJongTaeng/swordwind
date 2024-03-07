package riot.swordwind.service;

import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClient;
import riot.swordwind.dto.MatchDetailResponseDto;
import riot.swordwind.dto.RiotIdResponseDto;
import riot.swordwind.entity.ApiKey;
import riot.swordwind.repository.ApiKeyRepository;

import java.util.ArrayList;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class RiotApiService {
    private final ApiKeyRepository apiKeyRepository;

    @Value("${api.auth.key}")
    private String API_KEY;

    @PostConstruct
    private void init() {
        Optional<ApiKey> optionalApiKey = apiKeyRepository.findById(1L);

        if (optionalApiKey.isEmpty()) {
            ApiKey apiKey = new ApiKey(API_KEY);
            apiKeyRepository.save(apiKey);
            return;
        }

        ApiKey apiKey = optionalApiKey.get();
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

    public ResponseEntity<RiotIdResponseDto> requestFindRiotIdByPuuid(String puuid) {
        RestClient customClient = RestClient.builder()
                .defaultHeader("X-Riot-Token", API_KEY)
                .build();

        ResponseEntity<RiotIdResponseDto> res = customClient.get()
                .uri("https://asia.api.riotgames.com/riot/account/v1/accounts/by-puuid/{puuid}", puuid)
                .accept(MediaType.APPLICATION_JSON)
                .retrieve()
                .toEntity(RiotIdResponseDto.class);

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
