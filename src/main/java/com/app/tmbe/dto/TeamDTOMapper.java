package com.app.tmbe.dto;

import com.app.tmbe.dataModel.Team;
import org.springframework.stereotype.Component;

@Component
public class TeamDTOMapper {
  public static TeamDTO toTeamDTO(Team team) {
    return new TeamDTO(
        team.getId(),
        team.getIsChecked(),
        team.getPlayerOneId(),
        team.getPlayerTwoId(),
        team.getStrength(),
        team.getComment(),
        team.getPlayedDoublesTournaments());
  }
}
