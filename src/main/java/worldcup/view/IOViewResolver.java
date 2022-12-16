package worldcup.view;

import worldcup.dto.input.ReadInputDto;
import worldcup.dto.output.PrintResultDto;
import worldcup.dto.output.PrintExceptionDto;
import worldcup.view.exception.NotFoundViewException;

import java.util.HashMap;
import java.util.Map;
import java.util.function.Consumer;
import java.util.function.Supplier;

public class IOViewResolver {
    private final Map<Class<?>, Supplier<Object>> inputViewMap = new HashMap<>();
    private final Map<Class<?>, Consumer<Object>> outputViewMap = new HashMap<>();

    public IOViewResolver(InputView inputView, OutputView outputView) {
        initInputViewMappings(inputView);
        initOutputViewMappings(outputView);
    }

    private void initInputViewMappings(InputView inputView) {
        inputViewMap.put(ReadInputDto.class, inputView::readInput);
    }

    private void initOutputViewMappings(OutputView outputView) {
//        outputViewMap.put(PrintTotalResultDto.class, dto -> outputView.printTotalResult((PrintTotalResultDto) dto));
        outputViewMap.put(PrintResultDto.class, dto -> outputView.printResult((PrintResultDto) dto));
        outputViewMap.put(PrintExceptionDto.class, dto -> outputView.printException((PrintExceptionDto) dto));
    }

    public <T> T inputViewResolve(final Class<T> type) {
        try {
            return type.cast(inputViewMap.get(type).get());
        } catch (NullPointerException e) {
            throw new NotFoundViewException();
        }
    }

    public void outputViewResolve(final Object dto) {
        try {
            outputViewMap.get(dto.getClass()).accept(dto);
        } catch (NullPointerException e) {
            throw new NotFoundViewException();
        }
    }
}
