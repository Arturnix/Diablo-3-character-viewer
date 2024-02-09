package com.diablo3CharViewer.api_handlers;

import com.diablo3CharViewer.data_models.AccountDataModel;
import com.diablo3CharViewer.token.FetchToken;
import com.diablo3CharViewer.token.Token;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.util.HashMap;
import java.util.Map;

public class AccountHandlerApi {
    //zrobic jako static?
    public String generateRequest(String battleTag, FetchToken fetchToken) { //token przypisywać ze zmiennej. Pobieram token kiedy program startuje i wrzucam go do stałej.

        String apiEndpoint = BaseUrlParts.getBaseProfileApi() + battleTag.replace('#', '-');
        String localeAndToken = BaseUrlParts.getBaseLocaleAndToken() + Token.getAccess_token();

        return fetchToken.fetchAPIResourceRequest(apiEndpoint + localeAndToken);
    }

    private Map<String, Integer> sumEliteKills(JsonNode node) {

        int kills = 0;
        for(int i = 0; i < node.get("heroes").size(); i++) {
            kills += Integer.parseInt(node.get("heroes").get(i).get("kills").get("elites").asText());
        }
        Map<String, Integer> mapKills = new HashMap<String, Integer>();
        mapKills.put("elites", kills);

        return mapKills;
    }

    public AccountDataModel fetchAccountToDataModel(String battleTag, FetchToken fetchToken) {

        String accountData = generateRequest(battleTag, fetchToken);
        ObjectMapper objectMapper = new ObjectMapper();
        JsonNode node = null;

        try {
            node = objectMapper.readTree(accountData);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }

        AccountDataModel accountDataModel = new AccountDataModel(
                node.get("battleTag").asText(),
                node.get("paragonLevel").asInt(),
                node.get("guildName").asText(),
                node.get("highestHardcoreLevel").asInt(),
                sumEliteKills(node)
                );

        return accountDataModel;
    }
}