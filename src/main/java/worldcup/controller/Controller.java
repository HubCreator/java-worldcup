package worldcup.controller;

import worldcup.domain.Status;
import worldcup.dto.output.PrintExceptionDto;
import worldcup.view.IOViewResolver;

import java.util.EnumMap;
import java.util.Map;
import java.util.function.Supplier;

public class Controller {
    private final IOViewResolver ioViewResolver;
    private final Map<Status, Supplier<Status>> statusMap;

    public Controller(IOViewResolver ioViewResolver) {
        this.ioViewResolver = ioViewResolver;
        this.statusMap = new EnumMap<>(Status.class);
        initStatusMap();
    }

    private void initStatusMap() {
        statusMap.put(Status.READ_INPUT, this::readInput);
    }

    public Status run(Status status) {
        try {
            return statusMap.get(status).get();
        } catch (IllegalArgumentException exception) {
            ioViewResolver.outputViewResolve(new PrintExceptionDto(exception));
            return status;
        } catch (Exception exception) {
            return Status.EXIT;
        }
    }

    private Status readInput() {
        return null;
    }
}
