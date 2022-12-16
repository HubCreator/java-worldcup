package worldcup.view;

import worldcup.dto.input.ReadGroupDto;
import worldcup.dto.input.ReadInputDto;
import worldcup.dto.input.ReadTeamDto;
import worldcup.dto.output.PrintResultDto;
import worldcup.dto.output.PrintExceptionDto;
import worldcup.dto.output.PrintTeamResultDto;
import worldcup.dto.output.PrintTeamsByGroupDto;
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
        outputViewMap.put(PrintResultDto.class, dto -> outputView.printResult((PrintResultDto) dto));
        outputViewMap.put(PrintTeamsByGroupDto.class, dto -> outputView.printTeamsByGroup((PrintTeamsByGroupDto) dto));
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
