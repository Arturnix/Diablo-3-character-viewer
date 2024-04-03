package com.diablo3CharViewer.data_models;

import com.fasterxml.jackson.databind.JsonNode;

import java.util.List;
import java.util.Map;

public class AccountDataModel {

    private String battleTag;
    private int paragonLevel;
    private String guildName;
    private List<HeroDataModel> heroes;
    private int highestHardcoreLevel;
    private Map<String, Integer> kills;


    public AccountDataModel(String battleTag, int paragonLevel, String guildName, List<HeroDataModel> heroes, int highestHardcoreLevel, Map<String, Integer> kills) {
        this.battleTag = battleTag;
        this.paragonLevel = paragonLevel;
        this.guildName = guildName;
        this.heroes = heroes;
        this.highestHardcoreLevel = highestHardcoreLevel;
        this.kills = kills;
    }

    public String getBattleTag() {
        return this.battleTag;
    }

    public void setBattleTag(String battleTag) {
        this.battleTag = battleTag;
    }

    public int getParagonLevel() {
        return this.paragonLevel;
    }

    public String getGuildName() {
        return this.guildName;
    }

    public List<HeroDataModel> getHeroes() {
        return this.heroes;
    }

    public int getHighestHardcoreLevel() {
        return this.highestHardcoreLevel;
    }

    public Map<String, Integer> getKills() {
        return this.kills;
    }

    @Override
    public String toString() {
        return "AccountDataModel{" +
                "battleTag='" + battleTag + '\'' +
                ", paragonLevel=" + paragonLevel +
                ", guildName='" + guildName + '\'' +
                ", heroes=" + heroes +
                ", highestHardcoreLevel=" + highestHardcoreLevel +
                ", kills=" + kills +
                '}';
    }
}
