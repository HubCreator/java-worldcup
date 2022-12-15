package worldcup;

import worldcup.controller.InitController;
import worldcup.controller.MainController;
import worldcup.domain.MenuCommand;
import worldcup.service.PrintGroupResultService;
import worldcup.service.PrintTotalResultService;
import worldcup.service.Service;
import worldcup.view.IOViewResolver;
import worldcup.view.InputView;
import worldcup.view.OutputView;

import java.util.EnumMap;
import java.util.Map;
import java.util.function.Supplier;

public class GameManager {
    private static final MenuCommand INITIAL_STATUS = MenuCommand.DEFAULT;
    private static final Map<MenuCommand, Supplier<Service>> serviceMap = new EnumMap<>(MenuCommand.class);

    static {
        new InitController().run();
        serviceMap.put(MenuCommand.PRINT_TOTAL_RESULT, PrintTotalResultService::create);
        serviceMap.put(MenuCommand.PRINT_GROUP_RESULT, PrintGroupResultService::create);
    }

    private GameManager() {

    }

    public static void run() {
        IOViewResolver ioViewResolver = new IOViewResolver(InputView.getInstance(), OutputView.getInstance());
        MainController mainController = new MainController(ioViewResolver, serviceMap);
        MenuCommand currentCommand = INITIAL_STATUS;

        while (currentCommand != MenuCommand.EXIT) {
            currentCommand = mainController.run();
        }
    }
}
