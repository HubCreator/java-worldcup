package worldcup.controller;

import worldcup.view.IOViewResolver;

public class PrintGroupResulController implements Controller {

    private final IOViewResolver ioViewResolver;

    private PrintGroupResulController(IOViewResolver ioViewResolver) {
        this.ioViewResolver = ioViewResolver;
    }

    public static PrintGroupResulController create(IOViewResolver ioViewResolver) {
        return new PrintGroupResulController(ioViewResolver);
    }

    @Override
    public void run() {
    }
}
