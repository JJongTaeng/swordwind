package riot.swordwind.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import riot.swordwind.entity.Participant;
import riot.swordwind.repository.ParticipantRepository;

@Service
@RequiredArgsConstructor
public class ParticipantService {
    private final ParticipantRepository participantRepository;

    public Participant save(Participant participant) {
        return participantRepository.save(participant);
    }

    public Participant findByPuuid(String puuid) {
        return participantRepository.findByPuuid(puuid);
    }
}
