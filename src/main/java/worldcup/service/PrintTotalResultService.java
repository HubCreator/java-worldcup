package worldcup.service;

import worldcup.domain.Group;
import worldcup.repository.GroupRepository;

import java.util.Set;

public class PrintTotalResultService implements Service {

    public static Service create() {
        return new PrintTotalResultService();
    }

    @Override
    public StringBuilder run() {
        Set<Group> allGroups = GroupRepository.findAll();
        StringBuilder result = new StringBuilder();
        for (Group group : allGroups) {
            result.append(group.getTitle()).append("\n");
            result.append(GroupRepository.getByGroup(group)).append("\n");
        }
        return result;
    }
}
