package worldcup.controller;

import worldcup.repository.GroupRepository;
import worldcup.repository.TeamRepository;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class InitController implements Controller {
    public static final String FILE_PATH = "src/main/resources/MatchResult.txt";
    public static final String UNREADABLE_FILE_EXCEPTION = "파일을 읽을 수 없습니다.";

    private void parseInformation(Scanner scanner) {
        String line = scanner.nextLine();

        GroupRepository.saveGroupAndRecords(line);
        TeamRepository.update(line);
    }

    @Override
    public void run() {
        try {
            Scanner scanner = new Scanner(new File(FILE_PATH));
            while (scanner.hasNext()) {
                parseInformation(scanner);
            }
        } catch (FileNotFoundException exception) {
            throw new IllegalArgumentException(UNREADABLE_FILE_EXCEPTION, exception);
        }
    }
}
