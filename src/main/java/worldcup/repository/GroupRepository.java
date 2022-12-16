package worldcup.repository;

import worldcup.domain.Group;

import java.util.Set;
import java.util.TreeSet;

public class GroupRepository {
    private static final Set<Group> groups = new TreeSet<>();
    public static final String INVALID_GROUP_NAME_EXCEPTION = "[ERROR] 존재하지 않는 조";

    public static Group save(String line) {
        String[] split = line.split(" ");
        Group group = new Group(split[0]);
        groups.add(group);

        return group;
    }

    public static Set<Group> findAll() {
        return GroupRepository.groups;
    }

    public static Group findGroupByName(String groupName) {
        return groups.stream()
                .filter(group -> group.matchName(groupName))
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException(INVALID_GROUP_NAME_EXCEPTION));
    }
}
