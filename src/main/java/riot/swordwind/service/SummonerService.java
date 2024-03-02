package riot.swordwind.service;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import riot.swordwind.dto.RiotIdResponseDto;
import riot.swordwind.entity.Summoner;
import riot.swordwind.repository.SummonerRepository;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class SummonerService {
    private final SummonerRepository summonerRepository;
    private final RiotApiService riotApiService;

    public Optional<Summoner> findById(Long id) {
        return summonerRepository.findById(id);
    }

    public Summoner findByPuuid(String puuid) {
        Optional<Summoner> optionalSummoner = summonerRepository.findByPuuid(puuid);
        if (optionalSummoner.isEmpty()) {
            ResponseEntity<RiotIdResponseDto> response = riotApiService.requestFindRiotIdByPuuid(puuid);
            RiotIdResponseDto responseBody = response.getBody();

            return Summoner
                    .builder()
                    .tagLine(responseBody.getTagLine())
                    .gameName(responseBody.getGameName())
                    .puuid(responseBody.getPuuid())
                    .build();
        }
        return null;
    }

    public Optional<Summoner> findByGameNameAndTagLine(String gameName, String tagLine) {
        return summonerRepository.findByGameNameAndTagLine(gameName, tagLine);
    }


    public Summoner save(Summoner summoner) {
        summoner.removeAllSpace(); // String 필드 공백 문자 제거
        return summonerRepository.save(summoner);
    }

    public void deleteSummoner(Summoner summoner) {
        summonerRepository.delete(summoner);
    }

    public void deleteAll() {
        summonerRepository.deleteAll();
    }
}
