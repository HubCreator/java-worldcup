package worldcup.domain;

import java.util.ArrayList;
import java.util.List;

public class Records {
    private final List<String> records = new ArrayList<>();

    public void add(String record) {
        records.add(record);
    }

    public String getRecords() {
        StringBuilder result = new StringBuilder();
        for (String record : records) {
            result.append(record).append("\n");
        }
        return result.toString();
    }
}
