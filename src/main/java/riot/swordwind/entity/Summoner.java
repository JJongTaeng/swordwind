package riot.swordwind.entity;

import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Entity
@Getter
@Setter
@ToString
public class Summoner {

    @Id
    @Column(name = "summoner_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Builder
    public Summoner(String puuid, String summonerName, String tagGameName) {
        this.puuid = puuid;
        this.summonerName = summonerName;
        this.tagGameName = tagGameName;
    }

    public void removeAllSpace() {
        puuid = puuid.replaceAll("\\s", "");
        summonerName = summonerName.replaceAll("\\s", "");
        tagGameName = tagGameName.replaceAll("\\s", "");
    }

    private String puuid;
    private String summonerName;
    private String tagGameName;
}
