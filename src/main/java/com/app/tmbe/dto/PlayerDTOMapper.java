package com.app.tmbe.dto;

import com.app.tmbe.dataModel.Player;
import org.springframework.stereotype.Component;

@Component
public class PlayerDTOMapper {
  public static PlayerDTO toPlayerDTO(Player player) {
    return new PlayerDTO(
        player.getId(),
        player.getIsChecked(),
        player.getFirstName(),
        player.getLastName(),
        player.getStrength(),
        player.getComment(),
        player.getPlayedTournaments());
  }
}
