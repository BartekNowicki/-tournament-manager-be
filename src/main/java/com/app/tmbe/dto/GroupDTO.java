package com.app.tmbe.dto;

import com.app.tmbe.dataModel.GroupInDoubles;
import com.app.tmbe.dataModel.GroupInSingles;
import com.app.tmbe.dataModel.Player;
import com.app.tmbe.dataModel.SinglesTournament;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

@AllArgsConstructor
@Getter
public class GroupDTO {
  private long id;
  private String type;
  private Set<Long> members;

  public static GroupDTO badGroupDTO(String message) {
    return new GroupDTO(0, "", null);
  }

  public static GroupDTO fromGroupInSingles(GroupInSingles group) {
    return new GroupDTO(
        group.getId(),
        "groupInSingles",
        group.getMembers().stream().map(player -> player.getId()).collect(Collectors.toSet()));
  }

  public static GroupDTO fromGroupInDoubles(GroupInDoubles group) {
    return new GroupDTO(
        group.getId(),
        "groupInDoubles",
        group.getMembers().stream().map(team -> team.getId()).collect(Collectors.toSet()));
  }
}
