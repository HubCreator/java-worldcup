package worldcup.repository;

import worldcup.domain.Group;
import worldcup.domain.Team;

import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

public class TeamRepository {

    private static final String INVALID_TEAM_NAME = "존재하지 않는 팀입니다.";
    private static final Set<Team> teams = new HashSet<>();

    public static void update(String line) {
        String[] split = line.split(" ");
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

    public static List<Team> findAllByGroup(Group group) {
        List<Team> result = teams.stream()
                .filter(m -> m.matchGroup(group))
                .sorted()
                .collect(Collectors.toList());
        for (int index = 0; index <result.size(); index++) {
            result.get(index).setRanking(index + 1);
        }
        return result;
    }

    private static void updateTeamInformation(Team teamA, Team teamB, String[] split) {
        Group findGroup = GroupRepository.findByName(split[0]);

        teamA.addHistory(String.join(" ", Arrays.copyOfRange(split, 1, split.length)));
        teamB.addHistory(String.join(" ", Arrays.copyOfRange(split, 1, split.length)));

        teamA.setGroup(findGroup);
        teamB.setGroup(findGroup);

        teamA.setCurrentScore(split[4]);
        teamB.setCurrentScore(split[6]);

        teamA.compareAndUpdateBoth(teamB);
    }
}
