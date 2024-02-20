package com.diablo3CharViewer.data_models;

public class ItemDataModel {

    private String bodyPart;
    private String id;
    private String name;
    private int requiredLevel;
    private int itemLevel;
    private int armor;
    private String minDamage;
    private String maxDamage;
    //Map<String, List<String>> attributes (key is the primary/secondary attribute, value is list of attributes)


    public ItemDataModel(String bodyPart, String id, String name) {
        this.bodyPart = bodyPart;
        this.id = id;
        this.name = name;
    }

    //osobny konstruktor dla armor osobny dla broni
    public ItemDataModel(String bodyPart, String id, String name, int requiredLevel, int itemLevel, int armor) {
        this.bodyPart = bodyPart;
        this.id = id;
        this.name = name;
        this.requiredLevel = requiredLevel;
        this.itemLevel = itemLevel;
        this.armor = armor;
    }

    public ItemDataModel(String bodyPart, String id, String name, int requiredLevel, int itemLevel, String minDamage, String maxDamage) {
        this.bodyPart = bodyPart;
        this.id = id;
        this.name = name;
        this.requiredLevel = requiredLevel;
        this.itemLevel = itemLevel;
        this.minDamage = minDamage;
        this.maxDamage = maxDamage;
    }

    @Override
    public String toString() {
        return "ItemDataModel{" +
                "bodyPart='" + bodyPart + '\'' +
                ", id='" + id + '\'' +
                ", name='" + name + '\'' +
               /* ", requiredLevel=" + requiredLevel +
                ", itemLevel=" + itemLevel +
                ", armor=" + armor +
                ", minDamage=" + minDamage +
                ", maxDamage=" + maxDamage +*/
                '}';
    }
}
