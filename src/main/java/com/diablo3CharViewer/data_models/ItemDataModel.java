package com.diablo3CharViewer.data_models;

public class ItemDataModel {

    protected String bodyPart;
    protected String id;
    protected String name;
    protected int requiredLevel;
    protected int itemLevel;

    //Map<String, List<String>> attributes (key is the primary/secondary attribute, value is list of attributes)


    public ItemDataModel(String bodyPart, String id, String name) {
        this.bodyPart = bodyPart;
        this.id = id;
        this.name = name;
    }

    public ItemDataModel(String bodyPart, String id, String name, int requiredLevel, int itemLevel) {
        this.bodyPart = bodyPart;
        this.id = id;
        this.name = name;
        this.requiredLevel = requiredLevel;
        this.itemLevel = itemLevel;
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
