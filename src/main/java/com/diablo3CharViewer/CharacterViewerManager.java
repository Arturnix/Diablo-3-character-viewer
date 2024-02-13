package com.diablo3CharViewer;

import com.diablo3CharViewer.api_handlers.HeroHandlerApi;
import com.diablo3CharViewer.api_handlers.ItemHandlerApi;
import com.diablo3CharViewer.json_mappers.AccountMapper;
import com.diablo3CharViewer.json_mappers.HeroMapper;
import com.diablo3CharViewer.token.FetchToken;

import java.util.Scanner;

public class CharacterViewerManager {

    public void showProfile(Scanner scanner, AccountMapper accountMapper, FetchToken fetchToken) {
        System.out.println("Zostan na chwile i poczytaj:\n" + accountMapper.fetchAccountToDataModel(battleTagProvider(scanner), fetchToken) + '\n');
    }

    public void showHero(Scanner scanner, HeroMapper heroMapper, FetchToken fetchToken) {
        System.out.println("Zostan na chwile i poczytaj:\n" + heroMapper.fetchHeroToDataModel(battleTagProvider(scanner), heroIdProvider(scanner), fetchToken) + '\n');
    }

    public void showItem(Scanner scanner, ItemHandlerApi itemHandlerApi, FetchToken fetchToken) {
        System.out.println("Zostan na chwile i poczytaj:\n" + itemHandlerApi.generateRequest(itemSlugAndIdProvider(scanner), fetchToken) + '\n');
    }

    private String battleTagProvider(Scanner scanner) { //zrobic funkcje anonimowe z tego?
        System.out.println("Podaj battleTag aby wyszukac profil bohatera: ");

        return dataProvider(scanner);
    }

    private String heroIdProvider(Scanner scanner) {
        System.out.println("Podaj heroId aby wyswietlic postac: ");

        return dataProvider(scanner);
    }

    private String itemSlugAndIdProvider(Scanner scanner) {
        System.out.println("Podaj itemSlugAndId aby wysweitlic informacje o przedmiocie: ");

        return dataProvider(scanner);
    }

    private String dataProvider(Scanner scanner) {
        return scanner.nextLine();
    }

    public void showMenu() {
        System.out.println("1. Przegladaj profil podajac batlleTag bohatera");
        System.out.println("2. Wyswietl postac dla wybranego profilu");
        System.out.println("3. Wyswietl informacje o przedmiocie");
        System.out.println("4. Opusc archiwum");
    }

}