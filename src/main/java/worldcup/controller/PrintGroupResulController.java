package worldcup.controller;

import worldcup.domain.Group;
import worldcup.domain.Team;
import worldcup.dto.input.ReadGroupDto;
import worldcup.dto.output.PrintTeamsByGroupDto;
import worldcup.repository.GroupRepository;
import worldcup.repository.TeamRepository;
import worldcup.view.IOViewResolver;

import java.util.List;

public class PrintGroupResulController implements Controller {

    private final IOViewResolver ioViewResolver;

    private PrintGroupResulController(IOViewResolver ioViewResolver) {
        this.ioViewResolver = ioViewResolver;
    }

    public static PrintGroupResulController create(IOViewResolver ioViewResolver) {
        return new PrintGroupResulController(ioViewResolver);
    }

    @Override
    public void run() {
        ReadGroupDto readGroupDto = ioViewResolver.inputViewResolve(ReadGroupDto.class);
        Group findGroup = GroupRepository.findByName(readGroupDto.getInput());
        List<Team> allByGroup = TeamRepository.findAllByGroup(findGroup);
        ioViewResolver.outputViewResolve(new PrintTeamsByGroupDto(findGroup, allByGroup));
    }
}
