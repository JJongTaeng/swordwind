package riot.swordwind.dto;

import lombok.Getter;
import lombok.ToString;

@Getter
@ToString
public class MatchDetailResponseDto {

    private MetadataDto metadata;
    private InfoDto info;
}
