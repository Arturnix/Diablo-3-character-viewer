package com.diablo3CharViewer;

public class GetApiAccount {

    private String region = "eu";
    private String locale = "pl_PL";
    private String account;

    private String requestURL;

    //mozna ustawic dla innych regionow
    public void setRegion(String region) {
        this.region = region;
    }

    public void setLocale(String locale) {
        this.locale = locale;
    }

    public void setAccount(String account) {
        this.account = account;
    }

    public void setRequestURL() {
        this.requestURL = "https://eu.api.blizzard.com/d3/profile/" +
                account + "/?locale=pl_PL&access_token=" + Token.getAccess_token();
    }

    public String getRegion() {
        return region;
    }

    public String getLocale() {
        return locale;
    }

    public String getAccount() {
        return account;
    }

    public String getRequestURL() {
        return requestURL;
    }

    //zrobic jako static?
    public String generateRequest(FetchToken fetchToken) { //token przypisywać ze zmiennej. Pobieram token kiedy program startuje i wrzucam go do stałej.

        return fetchToken.initializeApiServiceGET(requestURL);
    }
}
