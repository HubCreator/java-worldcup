package worldcup.controller;

import worldcup.domain.Group;
import worldcup.dto.output.PrintTotalResultDto;
import worldcup.view.IOViewResolver;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.EnumMap;
import java.util.Map;

public class PrintTotalResultController implements Controller {
    public static final String FILE_PATH = "src/main/resources/MatchResult.txt";
    public static final String UNREADABLE_FILE_EXCEPTION = "[ERROR] 파일을 읽을 수 없습니다.";

    private final IOViewResolver ioViewResolver;
    Map<Group, StringBuilder> result = new EnumMap<>(Group.class);

    public PrintTotalResultController(IOViewResolver ioViewResolver) {
        this.ioViewResolver = ioViewResolver;
        parse();
    }

    public void parse() {
        BufferedReader bufferedReader;
        try {
            bufferedReader = new BufferedReader(new FileReader(FILE_PATH));
            String information;

            while ((information = bufferedReader.readLine()) != null) {
                String groupName = information.substring(0, 2);
                Group key = Group.map(groupName);
                result.put(key, result.getOrDefault(key, new StringBuilder())
                        .append(information.substring(3))
                        .append("\n"));
            }
            bufferedReader.close();
        } catch (IOException exception) {
            throw new IllegalArgumentException(UNREADABLE_FILE_EXCEPTION, exception);
        }
    }

    @Override
    public void run() {
        ioViewResolver.outputViewResolve(new PrintTotalResultDto(result));
    }
}
