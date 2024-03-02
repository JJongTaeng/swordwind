package riot.swordwind.matchlist;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import riot.swordwind.service.MatchListService;

import java.util.ArrayList;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
public class MatchListTest {


    private final MatchListService matchListService;

    @Autowired
    public MatchListTest(MatchListService matchListService) {
        this.matchListService = matchListService;
    }

    @Test
    @DisplayName("매치 리스트 반환")
    public void findMatchListTest() {
        ArrayList<String> matchIdList = matchListService.findMatchIdList("자조용해", "KR1");

        System.out.println("matchIdList = " + matchIdList);
        assertThat(matchIdList).isNotEmpty();
    }
}
