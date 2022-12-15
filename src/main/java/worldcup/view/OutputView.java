package worldcup.view;

import worldcup.domain.Group;
import worldcup.dto.output.PrintExceptionDto;
import worldcup.dto.output.PrintTotalResultDto;

import java.util.Map;

public class OutputView {
    private OutputView() {
    }

    private static class OutputViewSingletonHelper {
        private static final OutputView OUTPUT_VIEW = new OutputView();
    }

    public static OutputView getInstance() {
        return OutputViewSingletonHelper.OUTPUT_VIEW;
    }

    public void printTotalResult(PrintTotalResultDto dto) {
        Map<Group, StringBuilder> map = dto.getResult();
        StringBuilder result = new StringBuilder();
        for (Map.Entry<Group, StringBuilder> entry : map.entrySet()) {
            result.append(entry.getKey().getTitle()).append("\n");
            result.append(entry.getValue()).append("\n");
        }
        print(result.toString());
    }

    public void printException(PrintExceptionDto dto) {
        print(dto.getException().getMessage());
    }

    private void print(String message) {
        System.out.println(message);
    }
}
