package com.app.tmbe.utils;

import com.app.tmbe.dataModel.Player;
import com.app.tmbe.dataModel.Team;

import java.util.Map;
import java.util.Set;

public interface GrouperInterface {
    public Map<Integer, Set<Player>> groupPlayers();
    public Map<Integer, Set<Team>> groupTeams();
}
