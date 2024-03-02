package riot.swordwind.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import riot.swordwind.entity.Summoner;
import riot.swordwind.repository.SummonerRepository;

import java.util.ArrayList;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class SummonerService {
    private final SummonerRepository summonerRepository;

    public ArrayList<Summoner> findBySummonerName(String summonerName) {
        ArrayList<Summoner> summoners = summonerRepository.findBySummonerName(summonerName);
        return summoners;
    }

    public ArrayList<Summoner> findByTagGameName(String gameName) {
        ArrayList<Summoner> summoners = summonerRepository.findByTagGameName(gameName);
        return summoners;
    }


    public Optional<Summoner> findById(Long id) {
        return summonerRepository.findById(id);
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
