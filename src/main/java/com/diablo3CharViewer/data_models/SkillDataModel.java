package com.diablo3CharViewer.data_models;

public class SkillDataModel {

    private String type;
    private String slug;
    private String name;
    private int level;
    private String description;

    public SkillDataModel(String type, String slug, String name, int level, String description) {
        this.type = type;
        this.slug = slug;
        this.name = name;
        this.level = level;
        this.description = description;
    }

    @Override
    public String toString() {
        return "SkillDataModel{" +
                "type='" + type + '\'' +
                ", slug='" + slug + '\'' +
                ", name='" + name + '\'' +
                ", level=" + level +
                ", description='" + description + '\'' +
                '}';
    }
}
