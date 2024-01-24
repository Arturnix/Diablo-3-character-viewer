package com.diablo3CharViewer;

import com.diablo3CharViewer.api_handlers.AccountHandlerApi;
import com.diablo3CharViewer.api_handlers.HeroHandlerApi;
import com.diablo3CharViewer.api_handlers.ItemHandlerApi;
import com.diablo3CharViewer.token.FetchToken;

import java.util.Scanner;

public class CharacterViewerManager {

    public void showProfile(Scanner scanner, AccountHandlerApi accountHandlerApi, FetchToken fetchToken) {
        System.out.println("Zostan na chwile i poczytaj:\n" + accountHandlerApi.generateRequest(battleTagProvider(scanner), fetchToken) + '\n');
    }

    public void showHero(Scanner scanner, HeroHandlerApi heroHandlerApi, FetchToken fetchToken) {
        System.out.println("Zostan na chwile i poczytaj:\n" + heroHandlerApi.generateRequest(battleTagProvider(scanner), heroIdProvider(scanner), fetchToken) + '\n');
    }

    public void showItem(Scanner scanner, ItemHandlerApi itemHandlerApi, FetchToken fetchToken) {
        System.out.println("Zostan na chwile i poczytaj:\n" + itemHandlerApi.generateRequest(itemSlugAndIdProvider(scanner), fetchToken) + '\n');
    }

    private String battleTagProvider(Scanner scanner) { //zrobic funkcje anonimowe z tego?
        System.out.println("Podaj battleTag aby wyszukac profil bohatera: ");
        String battleTag = scanner.nextLine();

        return battleTag;
    }

    private String heroIdProvider(Scanner scanner) {
        System.out.println("Podaj heroId aby wyswietlic postac: ");
        String heroId = scanner.nextLine();

        return heroId;
    }

    private String itemSlugAndIdProvider(Scanner scanner) {
        System.out.println("Podaj itemSlugAndId aby wysweitlic informacje o przedmiocie: ");
        String itemSlugAndId = scanner.nextLine();

        return itemSlugAndId;
    }

    public void showMenu() {
        System.out.println("1. Przegladaj profil podajac batlleTag bohatera");
        System.out.println("2. Wyswietl postac dla wybranego profilu");
        System.out.println("3. Wyswietl informacje o przedmiocie");
        System.out.println("4. Opusc archiwum");
    }

}