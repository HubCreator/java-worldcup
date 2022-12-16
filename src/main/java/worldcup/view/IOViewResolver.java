package worldcup.view;

import worldcup.dto.input.ReadGroupDto;
import worldcup.dto.input.ReadInputDto;
import worldcup.dto.input.ReadTeamDto;
import worldcup.dto.output.PrintTotalResultDto;
import worldcup.dto.output.PrintExceptionDto;
import worldcup.dto.output.PrintTeamResultDto;
import worldcup.dto.output.PrintGroupResultDto;
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
        inputViewMap.put(ReadGroupDto.class, inputView::readGroup);
        inputViewMap.put(ReadTeamDto.class, inputView::readTeam);
    }

    private void initOutputViewMappings(OutputView outputView) {
        outputViewMap.put(PrintTotalResultDto.class, dto -> outputView.printResult((PrintTotalResultDto) dto));
        outputViewMap.put(PrintGroupResultDto.class, dto -> outputView.printTeamsByGroup((PrintGroupResultDto) dto));
        outputViewMap.put(PrintTeamResultDto.class, dto -> outputView.printTeamResult((PrintTeamResultDto) dto));
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
