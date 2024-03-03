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
    public MatchDetail(String matchId, String gameMode, String puuid, String gameName, String tagLine, Integer teamId, Long gameEndTimestamp, String championName, Integer kills, Integer assists, Integer deaths, Long totalDamageDealtToChampions, Long totalDamageTaken, Long totalHeal) {
        this.matchId = matchId;
        this.gameMode = gameMode;
        this.puuid = puuid;
        this.gameName = gameName;
        this.tagLine = tagLine;
        this.teamId = teamId;
        this.gameEndTimestamp = gameEndTimestamp;
        this.championName = championName;
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
    private String gameName;
    private String tagLine;
    private Integer teamId;
    private Long gameEndTimestamp;
    private String championName;
    private Integer kills;
    private Integer assists;
    private Integer deaths;
    private Long totalDamageDealtToChampions;
    private Long totalDamageTaken;
    private Long totalHeal;
}
