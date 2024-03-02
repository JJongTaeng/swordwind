package riot.swordwind.dto;

import lombok.Getter;
import lombok.ToString;

@Getter
@ToString
public class ParticipantDto {
    private String championName;
    private Long totalDamageDealtToChampions;
    private Long totalDamageTaken;
    private Long totalHeal;
    private Integer kills;
    private Integer deaths;
    private Integer assists;
    private Integer teamId;
}
