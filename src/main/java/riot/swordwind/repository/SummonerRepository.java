package riot.swordwind.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import riot.swordwind.entity.Summoner;

import java.util.Optional;

public interface SummonerRepository extends JpaRepository<Summoner, Long> {
    Optional<Summoner> findByPuuid(String puuid);

    Optional<Summoner> findByGameNameAndTagLine(String gameName, String tagLine);
}
