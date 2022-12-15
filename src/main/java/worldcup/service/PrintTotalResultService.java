package worldcup.service;

import worldcup.domain.Group;
import worldcup.domain.MenuCommand;
import worldcup.dto.output.PrintTotalResultDto;
import worldcup.repository.GroupRepository;
import worldcup.view.IOViewResolver;

import java.util.Set;

public class PrintTotalResultService implements Service {

    @Override
    public MenuCommand run(IOViewResolver ioViewResolver) {
        Set<Group> allGroups = GroupRepository.findAll();
        StringBuilder result = new StringBuilder();
        for (Group group : allGroups) {
            result.append(group.getTitle()).append("\n");
            result.append(GroupRepository.getByGroup(group)).append("\n");
        }
        ioViewResolver.outputViewResolve(new PrintTotalResultDto(result));
        return MenuCommand.PRINT_TOTAL_RESULT;
    }

    public static Service create() {
        return new PrintTotalResultService();
    }
}
