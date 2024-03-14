package com.diablo3CharViewer.data_models;

import java.util.List;
import java.util.Map;

public class ItemArmorDataModel extends ItemDataModel {

    private String armor;

    public ItemArmorDataModel(List<String> itemBodyPartSlots, String id, String name, int requiredLevel, Map<String, List<String>> attributes, String armor) {
        super(itemBodyPartSlots, id, name, requiredLevel, attributes);
        this.armor = armor;
    }

    @Override
    public String toString() {
        return "ItemArmorDataModel{" +
                "itemBodyPartSlots='" + itemBodyPartSlots + '\'' +
                ", id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", requiredLevel=" + requiredLevel +
                ", attributes='" + attributes + '\'' +
                ", armor=" + armor +
                '}';
    }
}
