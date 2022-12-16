package worldcup.dto.output;

import worldcup.domain.Team;

public class PrintTeamResultDto {
    private final Team team;

    public PrintTeamResultDto(Team team) {
        this.team = team;
    }

    public Team getTeam() {
        return team;
    }
}
