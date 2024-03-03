package riot.swordwind.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.HttpClientErrorException;
import riot.swordwind.entity.Summoner;
import riot.swordwind.response.ApiResponse;
import riot.swordwind.response.ResponseMessage;
import riot.swordwind.service.SummonerService;

@RestController
@RequestMapping("/api/summoner")
@RequiredArgsConstructor
public class SummonerSearchContainer {

    private final SummonerService summonerService;

    @GetMapping("/puuid/{puuid}")
    public ResponseEntity<ApiResponse<Summoner>> searchSummoner(@PathVariable String puuid) {
        try {
            Summoner summoner = summonerService.findByPuuidWithRiotApi(puuid);
            return ResponseEntity
                    .status(HttpStatus.OK)
                    .body(new ApiResponse<>(ResponseMessage.OK.getMessage(), summoner));
        } catch (HttpClientErrorException e) {
            ResponseMessage messageByStatus = ResponseMessage.getMessageByStatus(e.getStatusCode());
            return ResponseEntity
                    .status(e.getStatusCode())
                    .body(new ApiResponse<>(messageByStatus.getMessage(), null));
        }
    }
}
