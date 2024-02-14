package com.diablo3CharViewer.data_models;

import java.util.List;
import java.util.Map;

public class HeroDataModel {
    private int id;
    private String name;
    private String classHero;
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
                         List<ItemDataModel> items, List<FollowerDataModel> followers) {
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
    }

    public HeroDataModel(int id, String name, String classHero, int level, int paragonLevel,
                         boolean hardcore, boolean seasonal,
                         Map<String, Integer> kills, List<SkillDataModel> skills,
                         List<ItemDataModel> items, List<FollowerDataModel> followers) {
        this.id = id;
        this.name = name;
        this.classHero = classHero;
        this.level = level;
        this.paragonLevel = paragonLevel;
        this.hardcore = hardcore;
        this.seasonal = seasonal;
        this.kills = kills;
        this.skills = skills;
        this.items = items;
        this.followers = followers;
    }

    @Override
    public String toString() {

        String basicStats = "HeroDataModel{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", classHero='" + classHero + '\'' +
                ", level=" + level +
                ", paragonLevel=" + paragonLevel +
                ", hardcore=" + hardcore +
                ", seasonal=" + seasonal;

        if (!hardcore) {
            return basicStats +
                    ", kills=" + kills +
                    ", skills=" + skills +
                    ", items=" + items +
                    ", followers=" + followers +
                    '}';
        } else {
            return basicStats +
                    ", dead=" + dead +
                    ", kills=" + kills +
                    ", skills=" + skills +
                    ", items=" + items +
                    ", followers=" + followers +
                    '}';
        }
    }
}
