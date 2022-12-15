package worldcup;

import worldcup.controller.Controller;
import worldcup.controller.MainController;
import worldcup.controller.PrintGroupResultController;
import worldcup.controller.PrintTotalResultController;
import worldcup.domain.MenuCommand;
import worldcup.domain.Status;
import worldcup.view.IOViewResolver;
import worldcup.view.InputView;
import worldcup.view.OutputView;

import java.util.EnumMap;
import java.util.Map;

public class GameManager {
    private static final Status INITIAL_STATUS = Status.READ_INPUT;
    private static final Map<MenuCommand, Controller> controllerMap = new EnumMap<>(MenuCommand.class);

    private GameManager() {

    }

    public static void run() {
        IOViewResolver ioViewResolver = new IOViewResolver(InputView.getInstance(), OutputView.getInstance());
        MainController mainController = new MainController(controllerMap, ioViewResolver);
        initControllerMap(ioViewResolver);
        Status currentStatus = INITIAL_STATUS;

        while (currentStatus != Status.EXIT) {
            currentStatus = mainController.run(currentStatus);
        }
    }

    private static void initControllerMap(IOViewResolver ioViewResolver) {
        controllerMap.put(MenuCommand.PRINT_TOTAL_RESULT, new PrintTotalResultController(ioViewResolver));
        controllerMap.put(MenuCommand.PRINT_GROUP_RESULT, new PrintGroupResultController(ioViewResolver));
    }
}
