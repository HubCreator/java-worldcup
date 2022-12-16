package worldcup.repository;

import worldcup.domain.Group;
import worldcup.domain.Records;

import java.util.Arrays;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;

public class GroupRepository {
    private static final Map<Group, Records> groups = new TreeMap();
    public static final String INVALID_GROUP_NAME_EXCEPTION = "[ERROR] 존재하지 않는 조";

    public static Group saveByName(String line) {
        String[] split = line.split(" ");
        Group group = new Group(split[0]);
        if (!groups.containsKey(group)) {
            groups.put(group, new Records());
        }
        groups.get(group).add(String.join(" ", Arrays.copyOfRange(split, 1, split.length)));

        return group;
    }

    public static Set<Group> findAll() {
        return GroupRepository.groups.keySet();
    }

    public static Records getAllTeamsByGroup(Group group) {
        return GroupRepository.groups.get(group);
    }

    public static Group findByName(String groupName) {
        return groups.keySet().stream()
                .filter(group -> group.matchName(groupName))
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException(INVALID_GROUP_NAME_EXCEPTION));
    }
}
