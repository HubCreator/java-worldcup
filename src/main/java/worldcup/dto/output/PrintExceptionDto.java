package worldcup.dto.output;

public class PrintExceptionDto {
    private final IllegalArgumentException exception;

    public PrintExceptionDto(IllegalArgumentException exception) {
        this.exception = exception;
    }

    public IllegalArgumentException getException() {
        return exception;
    }
}
