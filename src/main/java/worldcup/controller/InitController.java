package worldcup.controller;

import worldcup.repository.GroupRepository;
import worldcup.repository.RecordsRepository;
import worldcup.repository.TeamRepository;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class InitController {
    public static final String FILE_PATH = "src/main/resources/MatchResult.txt";
    public static final String UNREADABLE_FILE_EXCEPTION = "파일을 읽을 수 없습니다.";

    private static void parseInformation(Scanner scanner) {
        String line = scanner.nextLine();

        GroupRepository.save(line);
        RecordsRepository.save(line);
        TeamRepository.update(line);
        TeamRepository.updateRanking();
    }

    public static void run() {
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
