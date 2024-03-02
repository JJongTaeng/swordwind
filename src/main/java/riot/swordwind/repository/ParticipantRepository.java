package riot.swordwind.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import riot.swordwind.entity.Participant;

public interface ParticipantRepository extends JpaRepository<Participant, Long> {

    Participant findByPuuid(String puuid);
}

