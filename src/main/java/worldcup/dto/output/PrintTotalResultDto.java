package worldcup.dto.output;

import worldcup.domain.Group;

import java.util.Map;

public class PrintTotalResultDto {

    private final Map<Group, StringBuilder> result;

    public PrintTotalResultDto(Map<Group, StringBuilder> result) {
        this.result = result;
    }

    public Map<Group, StringBuilder> getResult() {
        return result;
    }
}
