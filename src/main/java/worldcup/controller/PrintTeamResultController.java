package worldcup.controller;

import worldcup.domain.Team;
import worldcup.dto.input.ReadTeamDto;
import worldcup.dto.output.PrintTeamResultDto;
import worldcup.repository.TeamRepository;
import worldcup.view.IOViewResolver;

public class PrintTeamResultController implements Controller {

    private final IOViewResolver ioViewResolver;

    public PrintTeamResultController(IOViewResolver ioViewResolver) {
        this.ioViewResolver = ioViewResolver;
    }

    public static PrintTeamResultController create(IOViewResolver ioViewResolver) {
        return new PrintTeamResultController(ioViewResolver);
    }

    @Override
    public void run() {
        ReadTeamDto readTeamDto = ioViewResolver.inputViewResolve(ReadTeamDto.class);
        Team findTeam = TeamRepository.findByName(new Team(readTeamDto.getInput()));
        ioViewResolver.outputViewResolve(new PrintTeamResultDto(findTeam));
    }
}
