package com.diablo3CharViewer.data_models;

import java.util.List;
import java.util.Map;

public class ItemWeaponDataModel extends ItemDataModel {

    private final String minDamage;
    private final String maxDamage;

    public ItemWeaponDataModel(List<String> itemBodyPartSlots, String id, String name, int requiredLevel, Map<String, List<String>> attributes, String minDamage, String maxDamage) {
        super(itemBodyPartSlots, id, name, requiredLevel, attributes);
        this.minDamage = minDamage;
        this.maxDamage = maxDamage;
    }

    @Override
    public String toString() {
        return "ItemWeaponDataModel{" +
                "itemBodyPartSlots='" + itemBodyPartSlots + '\'' +
                ", id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", requiredLevel=" + requiredLevel +
                ", attributes='" + attributes + '\'' +
                ", minDamage='" + minDamage + '\'' +
                ", maxDamage='" + maxDamage + '\'' +
                '}';
    }
}
