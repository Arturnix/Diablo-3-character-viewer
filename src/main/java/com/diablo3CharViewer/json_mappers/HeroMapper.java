package com.diablo3CharViewer.json_mappers;

import com.diablo3CharViewer.api_handlers.HeroHandlerApi;
import com.diablo3CharViewer.data_models.FollowerDataModel;
import com.diablo3CharViewer.data_models.HeroDataModel;
import com.diablo3CharViewer.data_models.ItemDataModel;
import com.diablo3CharViewer.data_models.SkillDataModel;
import com.diablo3CharViewer.token.FetchToken;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

public class HeroMapper extends HeroHandlerApi {

    private HeroDataModel heroTypeCreator(JsonNode node) {

        if(!node.get("hardcore").asBoolean()) {
            return regularHeroCreator(node);
        } else {
            return hardcoreHeroCreator(node);
        }
    }

    private HeroDataModel hardcoreHeroCreator(JsonNode node) {

        HeroDataModel heroDataModelHardcore = new HeroDataModel(
                node.get("id").asInt(),
                node.get("name").asText(),
                node.get("class").asText(),
                node.get("level").asInt(),
                node.get("paragonLevel").asInt(),
                node.get("hardcore").asBoolean(),
                node.get("seasonal").asBoolean(),
                node.get("dead").asBoolean(),
                fetchKills(node),
                fetchSkills(node),
                fetchItems(node),
                fetchFollowers(node),
                fetchHeroStats(node)
        );
        return heroDataModelHardcore;
    }

    private HeroDataModel regularHeroCreator(JsonNode node) {
        //System.out.println(node.get("items").elements().toString());
        HeroDataModel heroDataModel = new HeroDataModel(
                node.get("id").asInt(),
                node.get("name").asText(),
                node.get("class").asText(),
                node.get("level").asInt(),
                node.get("paragonLevel").asInt(),
                node.get("hardcore").asBoolean(),
                node.get("seasonal").asBoolean(),
                fetchKills(node),
                fetchSkills(node),
                fetchItems(node),
                fetchFollowers(node),
                fetchHeroStats(node)
        );
        return heroDataModel;
    }

    private Map<String, Integer> fetchKills(JsonNode node) {

        ObjectMapper objectMapper = new ObjectMapper();
        Map<String, Integer> mapKills = objectMapper.convertValue(node.get("kills"), Map.class);

        return mapKills;
    }

    private List<SkillDataModel> fetchSkills(JsonNode node) {

        List<SkillDataModel> skills = new ArrayList<>();
        for(int i = 0; i < node.get("skills").size(); i++) {
            SkillDataModel skillDataModel = new SkillDataModel(
                    node.get("skills").get("active").get(i).get("skill").get("slug").asText(),
                    node.get("skills").get("active").get(i).get("skill").get("name").asText(),
                    node.get("skills").get("active").get(i).get("skill").get("level").asInt(),
                    node.get("skills").get("active").get(i).get("skill").get("description").asText()
            );
            skills.add(skillDataModel);
        }

        return skills;
    }

    private List<ItemDataModel> fetchItems(JsonNode node) {

        List<ItemDataModel> items = new ArrayList<>();
        List<String> itemsKeys = new ArrayList<>();
        Iterator<String> iterator = node.get("items").fieldNames();
        iterator.forEachRemaining(itemsKeys::add);

        for (String itemsKey : itemsKeys) {
            ItemDataModel itemDataModel = new ItemDataModel(
                    node.get("items").get(itemsKey).get("id").asText(),
                    node.get("items").get(itemsKey).get("name").asText()
            );
            items.add(itemDataModel);
        }

        return items;
    }

    private List<FollowerDataModel> fetchFollowers(JsonNode node) {

        List<FollowerDataModel> followers = new ArrayList<>();
        List<String> followersKeys = new ArrayList<>();
        Iterator<String> iterator = node.get("followers").fieldNames();
        iterator.forEachRemaining(e -> followersKeys.add(e));

        for(int i = 0; i < followersKeys.size(); i++) {
            FollowerDataModel followerDataModel = new FollowerDataModel(
                    node.get("followers").get(followersKeys.get(i)).get("slug").asText(),
                    node.get("followers").get(followersKeys.get(i)).get("level").asInt()
            );
            followers.add(followerDataModel);
        }

        return followers;
    }

    private Map<String, Integer> fetchHeroStats(JsonNode node) {

        ObjectMapper objectMapper = new ObjectMapper();
        Map<String, Integer> mapHeroStats = objectMapper.convertValue(node.get("stats"), Map.class);

        return mapHeroStats;
    }

    public HeroDataModel fetchHeroToDataModel(String battleTag, String heroId, FetchToken fetchToken) {

        String accountData = generateRequest(battleTag, heroId, fetchToken);
        ObjectMapper objectMapper = new ObjectMapper();
        JsonNode node = null;

        try {
            node = objectMapper.readTree(accountData);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }

        return heroTypeCreator(node);
    }
}
