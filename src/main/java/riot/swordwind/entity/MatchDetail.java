package riot.swordwind.entity;

import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@NoArgsConstructor
@Table(uniqueConstraints = {
        @UniqueConstraint(columnNames = {"matchId", "puuid"})
})
public class MatchDetail {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Builder
    public MatchDetail(String matchId, String puuid, Integer teamId, String gameMode, Long gameEndTimestamp, Integer kills, Integer assists, Integer deaths, Long totalDamageDealtToChampions, Long totalDamageTaken, Long totalHeal) {
        this.matchId = matchId;
        this.puuid = puuid;
        this.teamId = teamId;
        this.gameMode = gameMode;
        this.gameEndTimestamp = gameEndTimestamp;
        this.kills = kills;
        this.assists = assists;
        this.deaths = deaths;
        this.totalDamageDealtToChampions = totalDamageDealtToChampions;
        this.totalDamageTaken = totalDamageTaken;
        this.totalHeal = totalHeal;
    }

    private String matchId;
    private String gameMode;
    private String puuid;
    private Integer teamId;
    private Long gameEndTimestamp;
    private Integer kills;
    private Integer assists;
    private Integer deaths;
    private Long totalDamageDealtToChampions;
    private Long totalDamageTaken;
    private Long totalHeal;
}
