package com.diablo3CharViewer.data_models;

import java.util.List;
import java.util.Map;

public class ItemDataModel {
    protected String bodyPart;
    protected String id;
    protected String name;
    protected int requiredLevel;
    protected List<String> itemBodyPartSlots;

    protected Map<String, List<String>> attributes; //(key is the primary/secondary attribute, value is list of attributes)

    public ItemDataModel(String bodyPart, String id, String name) {
        this.bodyPart = bodyPart;
        this.id = id;
        this.name = name;
    }

    public ItemDataModel(List<String> itemBodyPartSlots, String id, String name, int requiredLevel, Map<String, List<String>> attributes) {
        this.itemBodyPartSlots = itemBodyPartSlots;
        this.id = id;
        this.name = name;
        this.requiredLevel = requiredLevel;
        this.attributes = attributes;
    }

    public String getId() {
        return this.id;
    }

    public String getName() {
        return this.name;
    }

    public List<String> getItemBodyPartSlots() {
        return this.itemBodyPartSlots;
    }


    @Override
    public String toString() {
        return "ItemDataModel{" +
                "bodyPart='" + bodyPart + '\'' +
                ", id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", requiredLevel='" + requiredLevel + '\'' +
                ", attributes='" + attributes + '\'' +
                '}';
    }
}
