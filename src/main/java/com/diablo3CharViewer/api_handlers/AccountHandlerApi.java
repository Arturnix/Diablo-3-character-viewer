package com.diablo3CharViewer.api_handlers;

import com.diablo3CharViewer.token.FetchToken;
import com.diablo3CharViewer.token.Token;

public class AccountHandlerApi {
    //zrobic jako static?
    public String generateRequest(String battletag, FetchToken fetchToken) { //token przypisywać ze zmiennej. Pobieram token kiedy program startuje i wrzucam go do stałej.
        return fetchToken.fetchAPIResourceRequest("https://eu.api.blizzard.com/d3/profile/" +
                battletag + "/?locale=pl_PL&access_token=" + Token.getAccess_token());
    }
}