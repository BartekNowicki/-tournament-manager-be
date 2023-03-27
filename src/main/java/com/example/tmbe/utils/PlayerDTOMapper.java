package com.example.tmbe.utils;

import com.example.tmbe.dataModel.Player;
import com.example.tmbe.dto.PlayerDTO;
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
        player.getComment());
  }
}
