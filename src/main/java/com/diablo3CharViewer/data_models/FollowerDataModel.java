package com.diablo3CharViewer.data_models;

import java.util.List;
import java.util.Map;

public class FollowerDataModel {

    private String name;
    private int level;
    private List<ItemDataModel> items;
    private Map<String, Integer> stats;

    public FollowerDataModel(String name, int level) {
        this.name = name;
        this.level = level;
    }

    public FollowerDataModel(String name, int level, List<ItemDataModel> items, Map<String, Integer> stats) {
        this.name = name;
        this.level = level;
        this.items = items;
        this.stats = stats;
    }

    @Override
    public String toString() {
        return "FollowerDataModel{" +
                "name='" + name + '\'' +
                ", level=" + level +
                ", items=" + items +
                ", stats=" + stats +
                '}';
    }
}
