package worldcup.controller;

import worldcup.domain.Group;
import worldcup.dto.output.PrintResultDto;
import worldcup.repository.GroupRepository;
import worldcup.view.IOViewResolver;

import java.util.Set;

public class TotalResultController implements Controller {

    private final IOViewResolver ioViewResolver;

    private TotalResultController(IOViewResolver ioViewResolver) {
        this.ioViewResolver = ioViewResolver;
    }

    public static Controller create(IOViewResolver ioViewResolver) {
        return new TotalResultController(ioViewResolver);
    }

    @Override
    public void run() {
        Set<Group> allGroups = GroupRepository.findAllGroup();
        StringBuilder result = new StringBuilder();
        for (Group group : allGroups) {
            result.append(group.getName()).append("\n");
            result.append(GroupRepository.getAllRecordsByGroup(group).getRecords()).append("\n");
        }
        ioViewResolver.outputViewResolve(new PrintResultDto(result));
    }
}
