package worldcup.dto.output;

import worldcup.domain.Group;
import worldcup.domain.Team;

import java.util.List;

public class PrintGroupResultDto {
    private final List<Team> teams;
    private final Group group;

    public PrintGroupResultDto(Group findGroup, List<Team> allByGroup) {
        this.group = findGroup;
        this.teams = allByGroup;
    }

    public List<Team> getTeams() {
        return teams;
    }

    public Group getGroup() {
        return group;
    }
}
