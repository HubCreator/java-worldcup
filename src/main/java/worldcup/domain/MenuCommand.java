package worldcup.domain;

import java.util.Arrays;

public enum MenuCommand {
    DEFAULT(0),
    PRINT_TOTAL_RESULT(1),
    PRINT_GROUP_RESULT(2),
    PRINT_WINNERS(3),
    EXIT(4);

    private final int command;

    MenuCommand(int command) {
        this.command = command;
    }

    public static MenuCommand map(String input) {
        int value = validateDigit(input);

        return Arrays.stream(values())
                .filter(m -> m.command == value)
                .findAny()
                .orElseThrow(() -> new IllegalStateException("[ERROR] 올바르지 않은 입력값입니다."));
    }

    private static int validateDigit(String input) {
        try {
            return Integer.parseInt(input);
        } catch (NumberFormatException exception) {
            throw new IllegalArgumentException("[ERROR] 숫자를 입력하셔야 합니다.", exception);
        }
    }
}

