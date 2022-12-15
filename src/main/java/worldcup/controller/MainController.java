package worldcup.controller;

import worldcup.domain.MenuCommand;
import worldcup.domain.Status;
import worldcup.dto.input.ReadInputDto;
import worldcup.dto.output.PrintExceptionDto;
import worldcup.view.IOViewResolver;

import java.util.EnumMap;
import java.util.Map;
import java.util.function.Supplier;

public class MainController {
    private final IOViewResolver ioViewResolver;
    private final Map<MenuCommand, Controller> controllerMap;
    private final Map<Status, Supplier<Status>> statusMap;

    public MainController(Map<MenuCommand, Controller> controllerMap, IOViewResolver ioViewResolver) {
        this.ioViewResolver = ioViewResolver;
        this.controllerMap = controllerMap;
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
        ReadInputDto readInputDto = ioViewResolver.inputViewResolve(ReadInputDto.class);
        MenuCommand menuCommand = MenuCommand.map(readInputDto.getInput());
        controllerMap.get(menuCommand).run();
        return null;
    }
}
