package worldcup.controller;

import worldcup.domain.Group;
import worldcup.dto.output.PrintResultDto;
import worldcup.repository.GroupRepository;
import worldcup.view.IOViewResolver;

import java.util.Set;

public class PrintTotalResultController implements Controller {

    private final IOViewResolver ioViewResolver;

    private PrintTotalResultController(IOViewResolver ioViewResolver) {
        this.ioViewResolver = ioViewResolver;
    }

    public static Controller create(IOViewResolver ioViewResolver) {
        return new PrintTotalResultController(ioViewResolver);
    }

    @Override
    public void run() {
        Set<Group> allGroups = GroupRepository.findAll();
        StringBuilder result = new StringBuilder();
        for (Group group : allGroups) {
            result.append(group.getTitle()).append("\n");
            result.append(GroupRepository.getByGroup(group)).append("\n");
        }
        ioViewResolver.outputViewResolve(new PrintResultDto(result));
    }
}
