package com.diablo3CharViewer.json_mappers;

import com.diablo3CharViewer.api_handlers.ItemHandlerApi;
import com.diablo3CharViewer.data_models.ItemArmorDataModel;
import com.diablo3CharViewer.data_models.ItemDataModel;
import com.diablo3CharViewer.data_models.ItemWeaponDataModel;
import com.diablo3CharViewer.token.FetchToken;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class ItemMapper extends ItemHandlerApi {

    private List<String> fetchItemBodyPartSlots(JsonNode node) {

        List<String> itemBodyPartSlots = new ArrayList<>();

        for(int i = 0; i < node.get("slots").size(); i++) {
            itemBodyPartSlots.add(node.get("slots").get(i).asText());
        }

        return itemBodyPartSlots;
    }

    private Map<String, List<String>> fetchItemAttrtibutes(JsonNode node) {

        ObjectMapper objectMapper = new ObjectMapper();
        Map<String, List<String>> mapItemAttributes = objectMapper.convertValue(node.get("attributes"), Map.class);

        return mapItemAttributes;
    }

    private String getMinDamage(JsonNode node) {

        int minDamageEndIndex = node.get("damage").asText().indexOf("-");
        String minDamage = node.get("damage").asText().substring(0, minDamageEndIndex);

        return minDamage;
    }

    private String getMaxDamage(JsonNode node) {

        int maxDamageStartIndex = node.get("damage").asText().indexOf("-")+1;
        int maxDamageEndIndex = node.get("damage").asText().indexOf(" ");
        String maxDamage = node.get("damage").asText().substring(maxDamageStartIndex, maxDamageEndIndex);

        return maxDamage;
    }

    private ItemDataModel createArmorDataModel(JsonNode node) {

        ItemDataModel itemDataModel = new ItemArmorDataModel(
                fetchItemBodyPartSlots(node),
                node.get("id").asText(),
                node.get("name").asText(),
                node.get("requiredLevel").asInt(),
                fetchItemAttrtibutes(node),
                node.get("armor").asText()
        );

        return itemDataModel;
    }

    private ItemDataModel createWeaponDataModel(JsonNode node) {

        ItemDataModel itemDataModel = new ItemWeaponDataModel(
                fetchItemBodyPartSlots(node),
                node.get("id").asText(),
                node.get("name").asText(),
                node.get("requiredLevel").asInt(),
                fetchItemAttrtibutes(node),
                getMinDamage(node),
                getMaxDamage(node)
        );

        return itemDataModel;
    }

    public ItemDataModel fetchItemToDataModel(String itemSlugAndId, FetchToken fetchToken) {

        String accountData = generateRequest(itemSlugAndId, fetchToken);
        ObjectMapper objectMapper = new ObjectMapper();
        JsonNode node = null;

        try {
            node = objectMapper.readTree(accountData);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }

        if(node.has("armor")) {
            return createArmorDataModel(node);
        } else {
           return  createWeaponDataModel(node);
        }
    }
}
