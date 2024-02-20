package com.diablo3CharViewer.data_models;

public class ItemWeaponDataModel extends ItemDataModel {

    private String minDamage;
    private String maxDamage;

    public ItemWeaponDataModel(String bodyPart, String id, String name, int requiredLevel, int itemLevel, String minDamage, String maxDamage) {
        super(bodyPart, id, name, requiredLevel, itemLevel);
        this.minDamage = minDamage;
        this.maxDamage = maxDamage;
    }

    @Override
    public String toString() {
        return "ItemWeaponDataModel{" +
                "bodyPart='" + bodyPart + '\'' +
                ", id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", requiredLevel=" + requiredLevel +
                ", itemLevel=" + itemLevel +
                ", minDamage='" + minDamage + '\'' +
                ", maxDamage='" + maxDamage + '\'' +
                '}';
    }
}
