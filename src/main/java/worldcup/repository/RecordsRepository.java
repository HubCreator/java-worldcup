package worldcup.repository;

import worldcup.domain.Group;
import worldcup.domain.Records;

import java.util.Arrays;
import java.util.Map;
import java.util.TreeMap;

public class RecordsRepository {
    private static final Map<Group, Records> records = new TreeMap<>();

    public static void save(String line) {
        String[] split = line.split(" ", 2);
        Group group = new Group(split[0]);
        if (!records.containsKey(group)) {
            records.put(group, new Records());
        }
        records.get(group).add(String.join(" ", Arrays.copyOfRange(split, 1, split.length)));
    }

    public static Records getRecordsByGroup(Group group) {
        return records.get(group);
    }
}
