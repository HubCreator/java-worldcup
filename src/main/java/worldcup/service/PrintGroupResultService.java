package worldcup.service;

import worldcup.domain.MenuCommand;
import worldcup.view.IOViewResolver;

public class PrintGroupResultService implements Service{

    @Override
    public MenuCommand run(IOViewResolver ioViewResolver) {
        return null;
    }

    public static Service create() {
        return new PrintGroupResultService();
    }
}
