package riot.swordwind.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import riot.swordwind.entity.ApiKey;
import riot.swordwind.repository.ApiKeyRepository;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ApiKeyService {

    private final ApiKeyRepository apiKeyRepository;

    public ApiKey changeApiKey(ApiKey apiKey) {
        Optional<ApiKey> optionalApiKey = apiKeyRepository.findById(1L);
        if (optionalApiKey.isEmpty()) {
            apiKeyRepository.save(apiKey);
            return apiKey;
        }

        ApiKey prevApiKey = optionalApiKey.get();
        prevApiKey.setCode(apiKey.getCode());
        apiKeyRepository.save(prevApiKey);
        return prevApiKey;
    }
}
