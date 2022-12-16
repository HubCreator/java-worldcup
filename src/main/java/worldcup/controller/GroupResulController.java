package worldcup.controller;

import worldcup.domain.Group;
import worldcup.domain.Team;
import worldcup.dto.input.ReadGroupDto;
import worldcup.dto.output.PrintGroupResultDto;
import worldcup.repository.GroupRepository;
import worldcup.repository.TeamRepository;
import worldcup.view.IOViewResolver;

import java.util.List;

public class GroupResulController implements Controller {

    private final IOViewResolver ioViewResolver;

    private GroupResulController(IOViewResolver ioViewResolver) {
        this.ioViewResolver = ioViewResolver;
    }

    public static GroupResulController create(IOViewResolver ioViewResolver) {
        return new GroupResulController(ioViewResolver);
    }

    @Override
    public void run() {
        ReadGroupDto readGroupDto = ioViewResolver.inputViewResolve(ReadGroupDto.class);
        Group findGroup = GroupRepository.findGroupByName(readGroupDto.getInput());
        List<Team> allByGroup = TeamRepository.findAllByGroup(findGroup);
        ioViewResolver.outputViewResolve(new PrintGroupResultDto(findGroup, allByGroup));
    }
}
