package worldcup;

import worldcup.controller.Controller;
import worldcup.domain.Status;
import worldcup.view.IOViewResolver;
import worldcup.view.InputView;
import worldcup.view.OutputView;

public class GameManager {
    private static final Status INITIAL_STATUS = Status.READ_INPUT;

    private GameManager() {

    }

    public static void run() {
        IOViewResolver ioViewResolver = new IOViewResolver(InputView.getInstance(), OutputView.getInstance());
        Controller controller = new Controller(ioViewResolver);
        Status currentStatus = INITIAL_STATUS;
        while (currentStatus != Status.EXIT) {
            currentStatus = controller.run(currentStatus);
        }
    }
}
