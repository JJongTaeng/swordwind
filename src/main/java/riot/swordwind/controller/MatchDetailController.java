package riot.swordwind.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.HttpClientErrorException;
import riot.swordwind.entity.MatchDetail;
import riot.swordwind.response.ApiResponse;
import riot.swordwind.response.ResponseMessage;
import riot.swordwind.service.MatchDetailService;

import java.util.ArrayList;

@Slf4j
@RestController
@RequestMapping("/api/match-detail") // 이렇게 수정
@RequiredArgsConstructor
public class MatchDetailController {

    private final MatchDetailService matchDetailService;

    @GetMapping("/{matchId}")
    public ResponseEntity<ApiResponse<ArrayList<MatchDetail>>> getMatchDetail(@PathVariable String matchId) {

        try {
            ArrayList<MatchDetail> matchDetails = matchDetailService.findByMatchId(matchId);
            return ResponseEntity
                    .status(HttpStatus.OK)
                    .body(new ApiResponse<>(ResponseMessage.OK.getMessage(), matchDetails));
        } catch (HttpClientErrorException e) {
            ResponseMessage messageByStatus = ResponseMessage.getMessageByStatus(e.getStatusCode());
            return ResponseEntity
                    .status(e.getStatusCode())
                    .body(new ApiResponse<>(messageByStatus.getMessage(), null));

        }

    }
}
