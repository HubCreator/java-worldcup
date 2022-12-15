package worldcup.repository;

import worldcup.domain.Team;

import java.util.HashSet;
import java.util.Set;

public class TeamRepository {

    private static final String INVALID_TEAM_NAME = "존재하지 않는 팀입니다.";
    private static final Set<Team> teams = new HashSet<>();

    public static void update(String[] split) {
        Team teamA = findByName(new Team(split[1]));
        Team teamB = findByName(new Team(split[3]));

        updateTeamInformation(teamA, teamB, split);
        save(teamA);
        save(teamB);
    }

    private static void save(Team team) {
        teams.remove(team);
        teams.add(team);
    }

    public static Team findByName(Team team) {
        return teams.stream()
                .filter(m -> m.equals(team))
                .findFirst()
                .orElse(team);
    }

    private static void updateTeamInformation(Team teamA, Team teamB, String[] split) {
        teamA.setCurrentScore(split[4]);
        teamB.setCurrentScore(split[6]);

        teamA.compareAndUpdateBoth(teamB);
    }
}
