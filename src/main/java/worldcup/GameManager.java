package worldcup;

import worldcup.controller.InitController;
import worldcup.controller.MainController;
import worldcup.domain.MenuCommand;
import worldcup.view.IOViewResolver;
import worldcup.view.InputView;
import worldcup.view.OutputView;

public class GameManager {
    private static final MenuCommand INITIAL_STATUS = MenuCommand.DEFAULT;

    static {
        new InitController().run();
    }

    private GameManager() {

    }

    public static void run() {
        IOViewResolver ioViewResolver = new IOViewResolver(InputView.getInstance(), OutputView.getInstance());
        MainController mainController = new MainController(ioViewResolver);
        MenuCommand currentCommand = INITIAL_STATUS;

        while (currentCommand != MenuCommand.EXIT) {
            currentCommand = mainController.run();
        }
    }
}
