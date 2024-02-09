package com.diablo3CharViewer.data_models;

import com.fasterxml.jackson.databind.JsonNode;

import java.util.Map;

public class AccountDataModel {

    private String  battleTag;
    private int paragonLevel;
    private String guildName;
    //private List<Hero> heroes;
    private int highestHardcoreLevel;
    private Map<String, Integer> kills;


    public AccountDataModel(String battleTag, int paragonLevel, String guildName, int highestHardcoreLevel, Map kills) {
        this.battleTag = battleTag;
        this.paragonLevel = paragonLevel;
        this.guildName = guildName;
        this.highestHardcoreLevel = highestHardcoreLevel;
        this.kills = kills;
    }

    @Override
    public String toString() {
        return "AccountDataModel{" +
                "battleTag='" + battleTag + '\'' +
                ", paragonLevel=" + paragonLevel +
                ", guildName='" + guildName + '\'' +
                ", highestHardcoreLevel=" + highestHardcoreLevel +
                ", kills=" + kills +
                '}';
    }

    public String getBattleTag() {
        return battleTag;
    }

    public void setBattleTag(String battleTag) {
        this.battleTag = battleTag;
    }

    public int getParagonLevel() {
        return paragonLevel;
    }

    public void setParagonLevel(int paragonLevel) {
        this.paragonLevel = paragonLevel;
    }

    public String getGuildName() {
        return guildName;
    }

    public void setGuildName(String guildName) {
        this.guildName = guildName;
    }

    public int getHighestHardcoreLevel() {
        return highestHardcoreLevel;
    }

    public void setHighestHardcoreLevel(int highestHardcoreLevel) {
        this.highestHardcoreLevel = highestHardcoreLevel;
    }

    public Map<String, Integer> getKills() {
        return kills;
    }

    public void setKills(Map<String, Integer> kills) {
        this.kills = kills;
    }
}
