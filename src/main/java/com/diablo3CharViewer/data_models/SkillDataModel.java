package com.diablo3CharViewer.data_models;

public class SkillDataModel {

    private enum type {
        ACTIVE,
        PASSIVE
    };
    private String slug;
    private String name;
    private int level;
    private String description;

    public SkillDataModel(String slug, String name, int level, String description) {
        this.slug = slug;
        this.name = name;
        this.level = level;
        this.description = description;
    }

    @Override
    public String toString() {
        return "SkillDataModel{" +
                "slug='" + slug + '\'' +
                ", name='" + name + '\'' +
                ", level=" + level +
                ", description='" + description + '\'' +
                '}';
    }
}
