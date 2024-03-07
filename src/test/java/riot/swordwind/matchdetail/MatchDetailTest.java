package riot.swordwind.matchdetail;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.web.client.HttpClientErrorException;
import riot.swordwind.entity.MatchDetail;
import riot.swordwind.service.MatchDetailService;

import java.util.ArrayList;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;


@SpringBootTest
public class MatchDetailTest {
    private final MatchDetailService matchDetailService;

    @Autowired
    public MatchDetailTest(MatchDetailService matchDetailService) {
        this.matchDetailService = matchDetailService;
    }

    @Test
    public void saveMatchDetail() {
        ArrayList<MatchDetail> matchDetails = matchDetailService.findByMatchId("KR_6973313082");
        assertThat(matchDetails).isNotEmpty();
    }

    @Test
    public void isWin() {
        ArrayList<MatchDetail> matchDetails = matchDetailService.findByMatchId("KR_6973313082");
        MatchDetail matchDetail = matchDetails.get(0);
        assertThat(matchDetail.getWin()).isExactlyInstanceOf(Boolean.class);
    }

    @Test
    public void saveMatchDetailFail() {
        assertThrows(HttpClientErrorException.class, () -> matchDetailService.findByMatchId("KR_6972223313082"));
    }
}
