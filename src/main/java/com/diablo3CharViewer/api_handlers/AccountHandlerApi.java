package com.diablo3CharViewer.api_handlers;

import com.diablo3CharViewer.token.FetchToken;
import com.diablo3CharViewer.token.Token;

public class AccountHandlerApi {
    //zrobic jako static?
    public String generateRequest(String battleTag, FetchToken fetchToken) { //token przypisywać ze zmiennej. Pobieram token kiedy program startuje i wrzucam go do stałej.

        String apiEndpoint = BaseUrlParts.getBaseProfileApi() + battleTag.replace('#', '-');
        String localeAndToken = BaseUrlParts.getBaseLocaleAndToken() + Token.getAccess_token();

        return fetchToken.fetchAPIResourceRequest(apiEndpoint + localeAndToken);
    }
}