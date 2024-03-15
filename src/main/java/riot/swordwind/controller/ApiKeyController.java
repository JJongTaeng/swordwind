package riot.swordwind.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import riot.swordwind.entity.ApiKey;
import riot.swordwind.response.ApiResponse;
import riot.swordwind.response.ResponseMessage;
import riot.swordwind.service.ApiKeyService;

@RestController
@RequestMapping("/api/swordwind/api-key")
@RequiredArgsConstructor
public class ApiKeyController {

    private final ApiKeyService apiKeyService;

    @PostMapping
    public ResponseEntity<ApiResponse<String>> changeApiKey(@RequestBody ApiKey apiKeyBody) {
        ApiKey apiKey = new ApiKey(apiKeyBody.getCode());
        
        apiKeyService.changeApiKey(apiKey);

        return ResponseEntity
                .status(HttpStatus.OK)
                .body(new ApiResponse<>(ResponseMessage.OK.getMessage(), apiKey.getCode()));
    }
}
