package worldcup.controller;

import worldcup.domain.MenuCommand;
import worldcup.dto.input.ReadInputDto;
import worldcup.dto.output.PrintExceptionDto;
import worldcup.view.IOViewResolver;

import java.util.EnumMap;
import java.util.Map;
import java.util.function.Supplier;

public class MainController {
    private final IOViewResolver ioViewResolver;
    private final Map<MenuCommand, Supplier<Controller>> controllerMap;

    public MainController(IOViewResolver ioViewResolver) {
        this.ioViewResolver = ioViewResolver;
        this.controllerMap = new EnumMap<>(MenuCommand.class);
        initControllerMap();
    }

    private void initControllerMap() {
        controllerMap.put(MenuCommand.PRINT_TOTAL_RESULT, () -> PrintTotalResultController.create(ioViewResolver));
        controllerMap.put(MenuCommand.PRINT_GROUP_RESULT, () -> PrintGroupResulController.create(ioViewResolver));
    }

    public MenuCommand run() {
        try {
            ReadInputDto readInputDto = ioViewResolver.inputViewResolve(ReadInputDto.class);
            MenuCommand menuCommand = MenuCommand.map(readInputDto.getInput());
            Controller controller = controllerMap.get(menuCommand).get();
            controller.run();
            return MenuCommand.DEFAULT;
        } catch (IllegalArgumentException exception) {
            ioViewResolver.outputViewResolve(new PrintExceptionDto(exception));
            return MenuCommand.DEFAULT;
        } catch (Exception exception) {
            return MenuCommand.EXIT;
        }
    }
}
