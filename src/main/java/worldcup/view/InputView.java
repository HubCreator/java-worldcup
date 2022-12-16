package worldcup.view;

import camp.nextstep.edu.missionutils.Console;
import worldcup.dto.input.ReadGroupDto;
import worldcup.dto.input.ReadInputDto;

public class InputView {
    private InputView() {
    }

    private static class InputViewSingletonHelper {
        private static final InputView INPUT_VIEW = new InputView();
    }

    public static InputView getInstance() {
        return InputViewSingletonHelper.INPUT_VIEW;
    }

    public ReadInputDto readInput() {
        printMessage(ViewMessage.DEFAULT_MESSAGE);
        String input = getUserInput();
        return new ReadInputDto(input);
    }

    public ReadGroupDto readGroup() {
        printMessage(ViewMessage.GROUP_NAME);
        String input = getUserInput();
        return new ReadGroupDto(input);
    }

    private String getUserInput() {
        return Console.readLine();
    }

    private void printMessage(ViewMessage viewMessage) {
        System.out.println(viewMessage.message);
    }

    private enum ViewMessage {
        DEFAULT_MESSAGE("1. 경기 결과 출력\n" +
                "2. 조별 결과 출력\n" +
                "3. 국가 경기 및 순위 결과 출력\n" +
                "4. 16강 진출 국가 출력\n" +
                "5. 종료\n\n" +
                "출력할 내용을 입력하세요. (1 ~ 5)"
        ),
        GROUP_NAME("출력할 조를 입력하세요 (A ~ H)");

        private final String message;

        ViewMessage(String message) {
            this.message = message;
        }
    }

}
