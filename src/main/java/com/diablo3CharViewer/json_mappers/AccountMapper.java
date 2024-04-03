package com.diablo3CharViewer.json_mappers;

import com.diablo3CharViewer.api_handlers.AccountHandlerApi;
import com.diablo3CharViewer.data_models.AccountDataModel;
import com.diablo3CharViewer.data_models.HeroDataModel;
import com.diablo3CharViewer.token.FetchToken;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class AccountMapper {

    private Map<String, Integer> sumEliteKills(JsonNode node) {

        int kills = 0;
        for(int i = 0; i < node.get("heroes").size(); i++) {
            kills += Integer.parseInt(node.get("heroes").get(i).get("kills").get("elites").asText());
        }
        Map<String, Integer> mapKills = new HashMap<String, Integer>();
        mapKills.put("elites", kills);

        return mapKills;
    }

    private List<HeroDataModel> fetchHeroesList(JsonNode node) {

        List<HeroDataModel> heroes = new ArrayList<>();
        for(int i = 0; i < node.get("heroes").size(); i++) {
            HeroDataModel heroDataModel = new HeroDataModel(
                    node.get("heroes").get(i).get("id").asInt(),
                    node.get("heroes").get(i).get("name").asText(),
                    node.get("heroes").get(i).get("class").asText(),
                    node.get("heroes").get(i).get("level").asInt()
            );
            heroes.add(heroDataModel);
        }

        return heroes;
    }

    public AccountDataModel fetchAccountToDataModel(String battleTag, FetchToken fetchToken) {

        String accountData = AccountHandlerApi.generateRequest(battleTag, fetchToken);
        ObjectMapper objectMapper = new ObjectMapper();
        JsonNode node = null;

        try {
            node = objectMapper.readTree(accountData);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }

        return new AccountDataModel(
                node.get("battleTag").asText(),
                node.get("paragonLevel").asInt(),
                node.get("guildName").asText(),
                fetchHeroesList(node),
                node.get("highestHardcoreLevel").asInt(),
                sumEliteKills(node)
        );
    }

    public List<HeroDataModel> fetchHeroesList(String battleTag, FetchToken fetchToken) {

        String accountData = AccountHandlerApi.generateRequest(battleTag, fetchToken);
        ObjectMapper objectMapper = new ObjectMapper();
        JsonNode node = null;

        try {
            node = objectMapper.readTree(accountData);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }

        return fetchHeroesList(node);
    }
}
