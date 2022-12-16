package worldcup.controller;

import worldcup.domain.Team;
import worldcup.dto.input.ReadTeamDto;
import worldcup.dto.output.PrintTeamResultDto;
import worldcup.repository.TeamRepository;
import worldcup.view.IOViewResolver;

public class TeamResultController implements Controller {

    private final IOViewResolver ioViewResolver;

    public TeamResultController(IOViewResolver ioViewResolver) {
        this.ioViewResolver = ioViewResolver;
    }

    public static TeamResultController create(IOViewResolver ioViewResolver) {
        return new TeamResultController(ioViewResolver);
    }

    @Override
    public void run() {
        ReadTeamDto readTeamDto = ioViewResolver.inputViewResolve(ReadTeamDto.class);
        Team findTeam = TeamRepository.findByName(new Team(readTeamDto.getInput()));
        ioViewResolver.outputViewResolve(new PrintTeamResultDto(findTeam));
    }
}
