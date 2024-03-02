package riot.swordwind.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Getter;

@Entity
@Getter
public class MatchDetail {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String puuid;
    private String gameMode;
    private Long gameEndTimestamp;
    private Integer kills;
    private Integer assists;
    private Integer deaths;
    private Long totalDamageDealtToChampions;
    private Long totalDamageTaken;
    private Long totalHeal;
}
