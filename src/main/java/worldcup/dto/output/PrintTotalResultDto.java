package worldcup.dto.output;

public class PrintTotalResultDto {
    private final StringBuilder result;

    public PrintTotalResultDto(StringBuilder result) {
        this.result = result;
    }

    public StringBuilder getResult() {
        return result;
    }
}
