package riot.swordwind.dto;

import lombok.Getter;
import lombok.ToString;

import java.util.ArrayList;

@Getter
@ToString
public class MetadataDto {

    private Integer dataVersion;
    private String matchId;
    private ArrayList<String> participants;
}
