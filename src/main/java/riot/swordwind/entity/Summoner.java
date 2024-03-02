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
@Table(uniqueConstraints = {
        @UniqueConstraint(columnNames = {"gameName", "tagLine"})
})
public class Summoner {

    @Id
    @Column(name = "summoner_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Builder
    public Summoner(String puuid, String gameName, String tagLine) {
        this.puuid = puuid;
        this.gameName = gameName;
        this.tagLine = tagLine;
    }

    public void removeAllSpace() {
        puuid = puuid.replaceAll("\\s", "");
        gameName = gameName.replaceAll("\\s", "");
    }

    @Column(unique = true)
    private String puuid;
    private String gameName;
    private String tagLine;
}
