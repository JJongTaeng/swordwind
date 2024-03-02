package riot.swordwind.riotapi;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.HttpClientErrorException;
import riot.swordwind.dto.MatchDetailResponseDto;
import riot.swordwind.dto.SummonerResponseDto;
import riot.swordwind.service.RiotApiService;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

@SpringBootTest
public class RiotAPITest {
    private final RiotApiService riotAPIService;

    @Autowired
    public RiotAPITest(RiotApiService riotAPIService) {
        this.riotAPIService = riotAPIService;
    }

    @Test
    @DisplayName("Riot Summoner API 200")
    public void findSummonerNameSuccess() {
        ResponseEntity<SummonerResponseDto> response = riotAPIService.requestFindSummonerBySummonerName("자조용해");

        System.out.println("response = " + response);
        HttpStatusCode statusCode = response.getStatusCode();
        assertThat(statusCode).isEqualTo(HttpStatusCode.valueOf(200));
    }

    @Test
    @DisplayName("error Riot Summoner API")
    public void findSummonerNameNotFound() {
        assertThrows(HttpClientErrorException.class, () -> riotAPIService.requestFindSummonerBySummonerName("자조용해2"));
    }

    @Test
    @DisplayName("matchlist api success")
    public void findMatchListIdSuccess() {
        String puuid = "kP1g5l3oLaxdAjpuyIB7hTdLff-6THnUcs4Zs2deyfLsWM1bmEoLya_oN7chnS8cH8BeG9zwHaArLw";

        ResponseEntity response = riotAPIService.requestFindMatchList(puuid);
        assertThat(response.getStatusCode()).isEqualTo(HttpStatusCode.valueOf(200));
    }

    @Test
    @DisplayName("matchlist api fail")
    public void findMatchListIdFail() {
        String puuid = "kP1g5l3oLaxdAjpuyIB7hTdLff-6THnUcs4Zs2deyfLsWM1bmEoLya_oN7chn123123123123S8cH8BeG9zwHaArLw";

        assertThrows(HttpClientErrorException.class, () -> riotAPIService.requestFindMatchList(puuid));
    }

    @Test
    @DisplayName("match detail api success")
    public void findMatchDetailSuccess() {
        String matchId = "KR_6972814243";
        ResponseEntity<MatchDetailResponseDto> response = riotAPIService.requestFindMatchDetail(matchId);
        MatchDetailResponseDto body = response.getBody();

        // detail 정보
        assertThat(body.getInfo().getParticipants()).isNotEmpty();
        assertThat(body.getInfo().getParticipants()).isNotEmpty();

        // metadata
        assertThat(body.getMetadata().getMatchId()).isNotNull();
        assertThat(body.getMetadata().getDataVersion()).isNotNull();
        assertThat(body.getMetadata().getDataVersion()).isNotNull();

        assertThat(response.getStatusCode()).isEqualTo(HttpStatusCode.valueOf(200));
    }

    @Test
    @DisplayName("match detail api fail")
    public void findMatchDetailFail() {
        String matchId = "INVALID_MATCH_ID";
        assertThrows(HttpClientErrorException.class, () -> riotAPIService.requestFindMatchDetail(matchId));
    }
}
