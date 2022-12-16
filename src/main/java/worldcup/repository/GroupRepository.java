package worldcup.repository;

import worldcup.domain.Group;

import java.util.Arrays;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;

public class GroupRepository {
    private static final Map<Group, StringBuilder> groups = new TreeMap();
    public static final String INVALID_GROUP_NAME_EXCEPTION = "[ERROR] 존재하지 않는 조";

    public static Group saveByName(String[] split) {
        Group group = new Group(split[0]);
        groups.put(group, groups.getOrDefault(group, new StringBuilder())
                .append(String.join(" ", Arrays.copyOfRange(split, 1, split.length)))
                .append("\n"));

        return group;
    }

    public static Set<Group> findAll() {
        return GroupRepository.groups.keySet();
    }

    public static StringBuilder getAllTeamsByGroup(Group group) {
        return GroupRepository.groups.get(group);
    }

    public static StringBuilder getTeamsByGroup(Group group) {
        Group findGroup = findByName(group.getName());
        return GroupRepository.groups.get(group);
    }

    public static Group findByName(String groupName) {
        return groups.keySet().stream()
                .filter(group -> group.matchName(groupName))
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException(INVALID_GROUP_NAME_EXCEPTION));
    }
}
