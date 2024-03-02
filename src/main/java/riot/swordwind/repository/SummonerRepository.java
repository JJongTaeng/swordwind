package riot.swordwind.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import riot.swordwind.entity.Summoner;

import java.util.ArrayList;

public interface SummonerRepository extends JpaRepository<Summoner, Long> {
    ArrayList<Summoner> findBySummonerName(String summonerName);

    ArrayList<Summoner> findByTagGameName(String tagGameName);
}
