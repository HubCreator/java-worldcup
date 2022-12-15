package worldcup.view;

import worldcup.dto.output.PrintExceptionDto;
import worldcup.dto.output.PrintTotalResultDto;


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
        print(dto.getResult().toString());
    }

    public void printException(PrintExceptionDto dto) {
        print(dto.getException().getMessage());
    }

    private void print(String message) {
        System.out.println(message);
    }
}
