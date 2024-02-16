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

import java.util.*;

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
        List<String> skillType = new ArrayList<>();
        Iterator<String> iterator = node.get("skills").fieldNames();
        iterator.forEachRemaining(skillType::add);

        for (String singleSkillType : skillType) {
            for(int i = 0; i < node.get("skills").get(singleSkillType).size(); i++) {
                SkillDataModel skillDataModel = new SkillDataModel(
                        singleSkillType,
                        node.get("skills").get(singleSkillType).get(i).get("skill").get("slug").asText(),
                        node.get("skills").get(singleSkillType).get(i).get("skill").get("name").asText(),
                        node.get("skills").get(singleSkillType).get(i).get("skill").get("level").asInt(),
                        node.get("skills").get(singleSkillType).get(i).get("skill").get("description").asText()
                );
                skills.add(skillDataModel);
            }
        }

        return skills;
    }

    private List<ItemDataModel> fetchItems(JsonNode node) {

        List<ItemDataModel> items = new ArrayList<>();
        List<String> itemsBodyPart = new ArrayList<>();
        Iterator<String> iterator = node.get("items").fieldNames();
        iterator.forEachRemaining(itemsBodyPart::add);

        for (String itemBodyPart : itemsBodyPart) {
            ItemDataModel itemDataModel = new ItemDataModel(
                    itemBodyPart,
                    node.get("items").get(itemBodyPart).get("id").asText(),
                    node.get("items").get(itemBodyPart).get("name").asText()
            );
            items.add(itemDataModel);
        }

        return items;
    }

    private List<FollowerDataModel> fetchFollowers(JsonNode node) {

        List<FollowerDataModel> followers = new ArrayList<>();
        List<String> followersKeys = new ArrayList<>();
        List<ItemDataModel> items = new ArrayList<>();
        Map<String, Integer> followerStats = new HashMap<String, Integer>();
        Iterator<String> iterator = node.get("followers").fieldNames();
        iterator.forEachRemaining(followersKeys::add);

        for (String followersKey : followersKeys) {
            FollowerDataModel followerDataModel = new FollowerDataModel(
                    node.get("followers").get(followersKey).get("slug").asText(),
                    node.get("followers").get(followersKey).get("level").asInt(),
                    items = fetchItems(node.get("followers").get(followersKey)),
                    followerStats = fetchHeroStats(node.get("followers").get(followersKey))
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
