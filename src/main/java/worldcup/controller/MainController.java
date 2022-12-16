package worldcup.controller;

import worldcup.domain.MenuCommand;
import worldcup.dto.input.ReadInputDto;
import worldcup.dto.output.PrintResultDto;
import worldcup.dto.output.PrintExceptionDto;
import worldcup.service.Service;
import worldcup.view.IOViewResolver;
import worldcup.view.exception.NotFoundViewException;

import java.util.Map;
import java.util.function.Supplier;

public class MainController {
    private final IOViewResolver ioViewResolver;
    private final Map<MenuCommand, Supplier<Service>> serviceMap;

    public MainController(IOViewResolver ioViewResolver, Map<MenuCommand, Supplier<Service>> serviceMap) {
        this.ioViewResolver = ioViewResolver;
        this.serviceMap = serviceMap;
    }

    public MenuCommand run() {
        try {
            ReadInputDto readInputDto = ioViewResolver.inputViewResolve(ReadInputDto.class);
            MenuCommand menuCommand = MenuCommand.map(readInputDto.getInput());
            Service service = serviceMap.get(menuCommand).get();
            ioViewResolver.outputViewResolve(new PrintResultDto(service.run()));
            return MenuCommand.DEFAULT;
        } catch (IllegalArgumentException exception) {
            ioViewResolver.outputViewResolve(new PrintExceptionDto(exception));
            return MenuCommand.DEFAULT;
        } catch (Exception exception) {
            return MenuCommand.EXIT;
        }
    }

    public <T> T inputViewResolve(final Class<T> type) {
        try {
            return type.cast(serviceMap.get(type).get());
        } catch (NullPointerException exception) {
            throw new NotFoundViewException();
        }
    }
}
