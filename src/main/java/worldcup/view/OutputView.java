package worldcup.view;

import worldcup.domain.Team;
import worldcup.dto.output.PrintExceptionDto;
import worldcup.dto.output.PrintResultDto;
import worldcup.dto.output.PrintTeamResultDto;
import worldcup.dto.output.PrintTeamsByGroupDto;

import java.util.List;

public class OutputView {
    private OutputView() {
    }

    private static class OutputViewSingletonHelper {
        private static final OutputView OUTPUT_VIEW = new OutputView();
    }

    public static OutputView getInstance() {
        return OutputViewSingletonHelper.OUTPUT_VIEW;
    }

    public void printResult(PrintResultDto dto) {
        print(dto.getResult().toString());
    }

    public void printTeamsByGroup(PrintTeamsByGroupDto dto) {
        List<Team> teams = dto.getTeams();
        StringBuilder result = new StringBuilder(dto.getGroup().getName()).append("\n");
        for (int index = 0; index < teams.size(); index++) {
            result.append(String.format("%d위 %s\n", index + 1, teams.get(index).getPerformance()));
        }
        print(result.toString());
    }

    public void printTeamResult(PrintTeamResultDto dto) {
        Team team = dto.getTeam();
        String result = team.getGroup().getName() + "\n" +
                team.getPerformance() + "\n" +
                team.getHistories() + "\n" +
                team.getCanAdvance();
        print(result);
    }

    public void printException(PrintExceptionDto dto) {
        print(dto.getException().getMessage());
    }

    private void print(String message) {
        System.out.println(message);
    }
}
