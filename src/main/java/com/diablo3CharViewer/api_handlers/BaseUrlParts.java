package com.diablo3CharViewer.api_handlers;

public class BaseUrlParts {

    private static final String BASE_BLIZZARD_URL = "https://eu.api.blizzard.com/d3/";
    private static final String BASE_PROFILE_API = BASE_BLIZZARD_URL + "profile/";
    private static final String BASE_HERO_API = "/hero/";
    private static final String BASE_ITEM_API = BASE_BLIZZARD_URL + "data/item/";
    private static final String BASE_LOCALE_AND_TOKEN = "/?locale=pl_PL&access_token="; //zawsze wystepuja razem w stringu wiec dalem do jednego stringa

    public static String getBaseProfileApi() {
        return BASE_PROFILE_API;
    }

    public static String getBaseHeroApi() {
        return BASE_HERO_API;
    }

    public static String getBaseItemApi() {
        return BASE_ITEM_API;
    }

    public static String getBaseLocaleAndToken() {
        return BASE_LOCALE_AND_TOKEN;
    }
}
