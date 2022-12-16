package worldcup.domain;

import java.util.Objects;

public class Team implements Comparable<Team>{
    public static final String FORMAT = "%s, 승 : %d, 무 : %d, 패 : %d, 승점 : %d, 득실차 : %d, 득점: %d";

    private final String teamName;
    private Group group;

    private int currentScore = 0;

    private int winCount = 0; // 승
    private int drawCount = 0; // 무
    private int loseCount = 0; // 패

    private int winScore = 0; // 승점
    private int goalCount = 0; // 득점
    private int loseGoalCount = 0; // 실점

    public Team(String teamName) {
        this.teamName = teamName;
    }

    public void win() {
        winCount += 1;
        winScore += 3;
    }

    public void lose() {
        loseCount += 1;
    }

    public void draw() {
        drawCount += 1;
        winScore += 1;
    }

    public void addGoalCount(int score) {
        goalCount += score;
    }

    public void addLoseGoalCount(int score) {
        loseGoalCount += score;
    }

    public void setCurrentScore(String score) {
        currentScore = validate(score);
    }

    private int validate(String score) {
        try {
            return Integer.parseInt(score);
        } catch (NumberFormatException exception) {
            throw new IllegalArgumentException("숫자를 입력야 합니다.", exception);
        }
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Team team = (Team) o;
        return Objects.equals(teamName, team.teamName);
    }

    @Override
    public int hashCode() {
        return Objects.hash(teamName);
    }

    public void compareAndUpdateBoth(Team target) {
        lowerThan(target);
        equal(target);
        greaterThan(target);

        addGoalCount(currentScore);
        addLoseGoalCount(target.currentScore);

        target.addGoalCount(target.currentScore);
        target.addLoseGoalCount(currentScore);
    }

    private void lowerThan(Team target) {
        if (currentScore < target.currentScore) {
            lose();
            target.win();
        }
    }

    private void equal(Team target) {
        if (currentScore == target.currentScore) {
            draw();
            target.draw();
        }
    }

    private void greaterThan(Team target) {
        if (currentScore > target.currentScore) {
            win();
            target.lose();
        }
    }

    public void setGroup(Group group) {
        this.group = group;
    }

    public boolean matchGroup(Group group) {
        return this.group.equals(group);
    }

    public String getResult() {
        return String.format(FORMAT,
                teamName, winCount, drawCount, loseCount,
                winScore, goalCount - loseGoalCount, goalCount);
    }

    @Override
    public int compareTo(Team o) {
        return Integer.compare(o.winScore, this.winScore);
    }
}
