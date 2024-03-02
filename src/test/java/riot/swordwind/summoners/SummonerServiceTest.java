package riot.swordwind.summoners;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;
import riot.swordwind.entity.Summoner;
import riot.swordwind.service.SummonerService;

import java.util.Optional;

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
                .puuid("101010")
                .summonerName("그랜드 플래티넘")
                .tagGameName("똥개인척 하는 범#KR2")
                .build();

        Summoner savedSummoner = summonerService.save(summoner);
        assertThat(savedSummoner.getTagGameName()).isEqualTo("똥개인척하는범#KR2");
    }

    @Test
    @DisplayName("서머너 삭제")
    public void deleteSummonerTest() {
        Summoner summoner = Summoner.builder()
                .puuid("101010")
                .summonerName("그랜드 플래티넘")
                .tagGameName("똥개인척 하는 범#KR2")
                .build();

        Summoner savedSummoner = summonerService.save(summoner);
        summonerService.deleteSummoner(savedSummoner);
        Optional<Summoner> foundSummoner = summonerService.findById(1L);

        assertThat(foundSummoner).isEmpty();
    }

    @Test
    @DisplayName("서머너 업데이트 gameName변경")
    public void updateSummonerTest() {
        Summoner summoner = Summoner.builder()
                .puuid("101010")
                .summonerName("그랜드 플래티넘")
                .tagGameName("똥개인척 하는 범#KR2")
                .build();
        Summoner savedSummoner = summonerService.save(summoner);
        savedSummoner.setTagGameName("똥개인척하는범#KRKR");

        Summoner changedSummoner = summonerService.save(savedSummoner);

        assertThat(changedSummoner.getTagGameName()).isEqualTo("똥개인척하는범#KRKR");
    }

}
