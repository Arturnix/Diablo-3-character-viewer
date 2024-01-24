package com.diablo3CharViewer.api_handlers;

import com.diablo3CharViewer.token.FetchToken;
import com.diablo3CharViewer.token.Token;

public class ItemHandlerApi {
    //corrupted-ashbringer-Unique_Sword_2H_104_x1
    public String generateRequest(String itemSlugAndId, FetchToken fetchToken) { //token przypisywać ze zmiennej. Pobieram token kiedy program startuje i wrzucam go do stałej.
        return fetchToken.fetchAPIResourceRequest("https://eu.api.blizzard.com/d3/data/item/" +
                itemSlugAndId + "?locale=pl_PL&access_token=" + Token.getAccess_token());
    }
}
