package worldcup.controller;

import worldcup.view.IOViewResolver;

public class PrintGroupResultController implements Controller {
    private final IOViewResolver ioViewResolver;

    public PrintGroupResultController(IOViewResolver ioViewResolver) {
        this.ioViewResolver = ioViewResolver;
    }

    @Override
    public void run() {

    }
}
