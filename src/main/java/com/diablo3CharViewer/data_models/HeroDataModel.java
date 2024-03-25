package com.diablo3CharViewer.data_models;

import java.util.List;
import java.util.Map;

public class HeroDataModel {
    private final int id;
    private final String name;
    private final String classHero;
    private int level;
    private int paragonLevel;
    private boolean hardcore;
    private boolean seasonal;
    private boolean dead;
    private Map<String, Integer> kills;
    private List<SkillDataModel> skills;
    private List<ItemDataModel> items;
    private List<FollowerDataModel> followers;
    private Map<String, Integer> stats;

    public HeroDataModel(int id, String name, String classHero) {
        this.id = id;
        this.name = name;
        this.classHero = classHero;
    }

    public HeroDataModel(int id, String name, String classHero, int level, int paragonLevel,
                         boolean hardcore, boolean seasonal, boolean dead,
                         Map<String, Integer> kills, List<SkillDataModel> skills,
                         List<ItemDataModel> items, List<FollowerDataModel> followers, Map<String, Integer> stats) {
        this.id = id;
        this.name = name;
        this.classHero = classHero;
        this.level = level;
        this.paragonLevel = paragonLevel;
        this.hardcore = hardcore;
        this.seasonal = seasonal;
        this.dead = dead;
        this.kills = kills;
        this.skills = skills;
        this.items = items;
        this.followers = followers;
        this.stats = stats;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return this.name;
    }

    public String getClassHero() {
        return this.classHero;
    }

    public Map<String, Integer> getKills() {
        return this.kills;
    }

    public List<SkillDataModel> getSkills() {
        return this.skills;
    }

    public List<ItemDataModel> getItems() {
        return this.items;
    }

    public List<FollowerDataModel> getFollowers() {
        return this.followers;
    }

    public Map<String, Integer> getStats() {
        return this.stats;
    }

    @Override
    public String toString() {

        return "HeroDataModel{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", classHero='" + classHero + '\'' +
                ", level=" + level +
                ", paragonLevel=" + paragonLevel +
                ", hardcore=" + hardcore +
                ", seasonal=" + seasonal +
                ", dead=" + dead +
                ", kills=" + kills +
                ", skills=" + skills +
                ", items=" + items +
                ", followers=" + followers +
                ", stats=" + stats +
                '}';
    }
}
