package worldcup.domain;

import java.util.Arrays;
import java.util.Optional;

public enum Group {
    TEAM_A("A조"),
    TEAM_B("B조"),
    TEAM_C("C조"),
    TEAM_D("D조"),
    TEAM_E("E조"),
    TEAM_F("F조"),
    TEAM_G("G조"),
    TEAM_H("H조");

    private final String title;

    Group(String title) {
        this.title = title;
    }


    public static Group map(String groupName) {
        Optional<Group> groupOptional = Arrays.stream(values())
                .filter(m -> m.title.equals(groupName))
                .findAny();

        return groupOptional.orElseThrow(() -> new IllegalArgumentException("[ERROR] 해당하는 조가 없습니다."));
    }

    public String getTitle() {
        return title;
    }
}
