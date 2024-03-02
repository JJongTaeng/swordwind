package riot.swordwind.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import riot.swordwind.repository.MatchDetailRepository;

@Service
@RequiredArgsConstructor
public class MatchDetailService {
    private final MatchDetailRepository matchDetailRepository;


}
