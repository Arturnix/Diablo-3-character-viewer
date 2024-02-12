package com.diablo3CharViewer.data_models;

public class HeroDataModel {
    private int id;
    private String name;
    private String classHero;

    public HeroDataModel(int id, String name, String classHero) {
        this.id = id;
        this.name = name;
        this.classHero = classHero;
    }

    @Override
    public String toString() {
        return "HeroDataModel{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", classHero='" + classHero + '\'' +
                '}';
    }
}
