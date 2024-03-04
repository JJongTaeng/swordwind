package riot.swordwind.summoners;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;
import riot.swordwind.entity.Summoner;
import riot.swordwind.service.SummonerService;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
@Transactional
public class SummonerServiceTest {
    private final SummonerService summonerService;

    @Autowired
    public SummonerServiceTest(SummonerService summonerService) {
        this.summonerService = summonerService;
    }

    @BeforeEach
    public void reset() {
        summonerService.deleteAll();
    }

    @Test
    @DisplayName("저장한 엔티티와 조회한 엔티티의 이름이 동일")
    public void saveSummonerTest() {
        Summoner summoner = Summoner.builder()
                .puuid("1111")
                .gameName("그랜드 플래티넘")
                .tagLine("KR2")
                .build();

        Summoner savedSummoner = summonerService.save(summoner);
        assertThat(savedSummoner.getGameName()).isEqualTo("그랜드플래티넘");
    }

    @Test
    @DisplayName("서머너 업데이트 gameName변경")
    public void updateSummonerTest() {
        Summoner summoner = Summoner.builder()
                .puuid("2222")
                .gameName("그랜드 플래티넘")
                .tagLine("KR23")
                .build();

        Summoner savedSummoner = summonerService.save(summoner);
        savedSummoner.setGameName("똥개인척하는범");

        Summoner changedSummoner = summonerService.save(savedSummoner);

        assertThat(changedSummoner.getGameName()).isEqualTo("똥개인척하는범");
    }

}
