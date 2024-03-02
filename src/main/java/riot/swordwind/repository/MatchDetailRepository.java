package riot.swordwind.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import riot.swordwind.entity.MatchDetail;

import java.util.ArrayList;
import java.util.Optional;

public interface MatchDetailRepository extends JpaRepository<MatchDetail, Long> {
    ArrayList<MatchDetail> findByMatchIdIn(ArrayList<String> matchIdList);

    Optional<ArrayList<MatchDetail>> findByMatchId(String matchId);

}
