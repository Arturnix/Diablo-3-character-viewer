package com.diablo3CharViewer;

public class GetApiItem {
    private String region = "eu";
    private String locale = "pl_PL";
    private String itemSlugAndId; //corrupted-ashbringer-Unique_Sword_2H_104_x1

    private String requestURL;

    //mozna ustawic dla innych regionow
    public void setRegion(String region) {
        this.region = region;
    }

    public void setLocale(String locale) { //locale PL daje polskie opisy
        this.locale = locale;
    }

    public void setItemSlugAndId(String itemSlugAndId) {
        this.itemSlugAndId = itemSlugAndId;
    }

    public void setRequestURL() {
        this.requestURL = "https://eu.api.blizzard.com/d3/data/item/" +
                itemSlugAndId + "?locale=pl_PL&access_token=" + Token.getAccess_token();
    }

    public String getRegion() {
        return region;
    }

    public String getLocale() {
        return locale;
    }

    public String getItemSlugAndId() {
        return itemSlugAndId;
    }

    public String getRequestURL() {
        return requestURL;
    }

    //zrobic jako static?
    public String generateRequest(FetchToken fetchToken) { //token przypisywać ze zmiennej. Pobieram token kiedy program startuje i wrzucam go do stałej.

        return fetchToken.initializeApiServiceGET(requestURL);
    }
}
