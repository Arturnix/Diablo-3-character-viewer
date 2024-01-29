package com.diablo3CharViewer.api_handlers;

import com.diablo3CharViewer.token.FetchToken;
import com.diablo3CharViewer.token.Token;

public class HeroHandlerApi {
    //Hero ID: 162864678
    //zrobic jako static?
    public String generateRequest(String battleTag, String heroId, FetchToken fetchToken) { //token przypisywać ze zmiennej. Pobieram token kiedy program startuje i wrzucam go do stałej.
        return fetchToken.initializeApiServiceGET("https://eu.api.blizzard.com/d3/profile/" +
                battleTag + "/hero/" + heroId + "?locale=pl_PL&access_token=" + Token.getAccess_token());
    }
}
