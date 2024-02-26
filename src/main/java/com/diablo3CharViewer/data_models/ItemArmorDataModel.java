package com.diablo3CharViewer.data_models;

public class ItemArmorDataModel extends ItemDataModel {

    private int armor;

    public ItemArmorDataModel(String bodyPart, String id, String name, int requiredLevel, int itemLevel, int armor) {
        super(bodyPart, id, name, requiredLevel, itemLevel);
        this.armor = armor;
    }

    @Override
    public String toString() {
        return "ItemArmorDataModel{" +
                "bodyPart='" + bodyPart + '\'' +
                ", id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", requiredLevel=" + requiredLevel +
                ", itemLevel=" + itemLevel +
                ", armor=" + armor +
                '}';
    }
}
