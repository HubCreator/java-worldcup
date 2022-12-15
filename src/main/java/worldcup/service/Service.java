package worldcup.service;

import worldcup.domain.MenuCommand;
import worldcup.view.IOViewResolver;

public interface Service {
    MenuCommand run(IOViewResolver ioViewResolver);
}
