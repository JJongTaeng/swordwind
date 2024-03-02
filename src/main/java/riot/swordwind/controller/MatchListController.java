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
import riot.swordwind.response.ApiResponse;
import riot.swordwind.response.ResponseMessage;
import riot.swordwind.service.MatchListService;

import java.util.ArrayList;

@Slf4j
@RestController
@RequestMapping("/api/match-list") // 이렇게 수정
@RequiredArgsConstructor
public class MatchListController {

    private final MatchListService matchListService;

    @GetMapping("/{gameName}/{tagLine}")
    public ResponseEntity<ApiResponse<ArrayList<String>>> getMatchList(@PathVariable String gameName, @PathVariable String tagLine) {
        log.info("[request] /api/match-list/{}/{}", gameName, tagLine);
        try {
            ArrayList<String> matchList = matchListService.findMatchIdList(gameName, tagLine);
            return ResponseEntity
                    .status(HttpStatus.OK)
                    .body(new ApiResponse<>(ResponseMessage.OK.getMessage(), matchList));
        } catch (HttpClientErrorException errorException) {
            ResponseMessage error = ResponseMessage.getMessageByStatus(errorException.getStatusCode());
            return ResponseEntity
                    .status(errorException.getStatusCode())
                    .body(new ApiResponse<>(error.getMessage(), null));
        }
    }

}
