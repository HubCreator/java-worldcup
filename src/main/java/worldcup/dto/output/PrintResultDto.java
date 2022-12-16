package worldcup.dto.output;

public class PrintResultDto {
    private final StringBuilder result;

    public PrintResultDto(StringBuilder result) {
        this.result = result;
    }

    public StringBuilder getResult() {
        return result;
    }
}
