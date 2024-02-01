package com.diablo3CharViewer.api_handlers;

public class BaseUrlParts {

    private static final String baseBlizzardUrl = "https://eu.api.blizzard.com/d3/";
    private static final String baseProfileApi = baseBlizzardUrl + "profile/";
    private static final String baseHeroApi = "/hero/";
    private static final String baseItemApi = baseBlizzardUrl + "data/item/";
    private static final String baseLocaleAndToken = "/?locale=pl_PL&access_token="; //zawsze wystepuja razem w stringu wiec dalem do jednego stringa

    public static String getBaseProfileApi() {
        return baseProfileApi;
    }

    public static String getBaseHeroApi() {
        return baseHeroApi;
    }

    public static String getBaseItemApi() {
        return baseItemApi;
    }

    public static String getBaseLocaleAndToken() {
        return baseLocaleAndToken;
    }
}
