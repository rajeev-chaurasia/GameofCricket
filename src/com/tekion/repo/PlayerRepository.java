package com.tekion.repo;

public interface PlayerRepository {
    Boolean ifCheckPlayerExists(int teamId , String playerName);
    void createPlayer(int teamId , String playerName);
    int getIdByTeamIdAndPlayerName(int teamId , String playerName);
}