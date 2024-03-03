package riot.swordwind.service;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import riot.swordwind.dto.MatchDetailResponseDto;
import riot.swordwind.dto.ParticipantDto;
import riot.swordwind.entity.MatchDetail;
import riot.swordwind.entity.Summoner;
import riot.swordwind.repository.MatchDetailRepository;

import java.util.ArrayList;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class MatchDetailService {
    private final MatchDetailRepository matchDetailRepository;
    private final RiotApiService riotApiService;
    private final SummonerService summonerService;


    public ArrayList<MatchDetail> findByMatchId(String matchId) {
        ArrayList<MatchDetail> returnList = new ArrayList<>();
        Optional<ArrayList<MatchDetail>> optionalMatchDetail = matchDetailRepository.findByMatchId(matchId);
        if (optionalMatchDetail.get().size() != 0) {
            return optionalMatchDetail.get();
        }

        ResponseEntity<MatchDetailResponseDto> responseEntity = riotApiService.requestFindMatchDetail(matchId);
        MatchDetailResponseDto matchDetailResponseDto = responseEntity.getBody();

        ArrayList<String> participants = matchDetailResponseDto.getMetadata().getParticipants();
        ArrayList<ParticipantDto> participantDtos = matchDetailResponseDto.getInfo().getParticipants();

        for (int i = 0; i < participants.size(); i++) {
            String puuid = participants.get(i);

            Summoner summoner = summonerService.findByPuuidWithRiotApi(puuid);

            ParticipantDto participantDto = participantDtos.get(i);
            MatchDetail matchDetail = MatchDetail.builder()
                    .matchId(matchId)
                    .puuid(puuid)
                    .gameName(summoner.getGameName())
                    .tagLine(summoner.getTagLine())
                    .teamId(participantDto.getTeamId())
                    .championName(participantDto.getChampionName())
                    .kills(participantDto.getKills())
                    .deaths(participantDto.getDeaths())
                    .assists(participantDto.getAssists())
                    .totalHeal(participantDto.getTotalHeal())
                    .totalDamageTaken(participantDto.getTotalDamageTaken())
                    .totalDamageDealtToChampions(participantDto.getTotalDamageDealtToChampions())
                    .gameEndTimestamp(matchDetailResponseDto.getInfo().getGameEndTimestamp())
                    .gameMode(matchDetailResponseDto.getInfo().getGameMode())
                    .build();

            MatchDetail saved = matchDetailRepository.save(matchDetail);
            returnList.add(saved);
        }

        return returnList;
    }

}
