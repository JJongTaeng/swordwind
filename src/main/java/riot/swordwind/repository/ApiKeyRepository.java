package riot.swordwind.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import riot.swordwind.entity.ApiKey;

public interface ApiKeyRepository extends JpaRepository<ApiKey, Long> {

}
