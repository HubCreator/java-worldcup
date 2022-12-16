package worldcup.view;

import worldcup.dto.output.PrintExceptionDto;
import worldcup.dto.output.PrintResultDto;

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

    public void printException(PrintExceptionDto dto) {
        print(dto.getException().getMessage());
    }

    private void print(String message) {
        System.out.println(message);
    }
}
