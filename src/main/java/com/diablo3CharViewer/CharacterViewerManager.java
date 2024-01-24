package com.diablo3CharViewer;

import com.diablo3CharViewer.handlers.AccountHandlerApi;
import com.diablo3CharViewer.handlers.HeroHandlerApi;
import com.diablo3CharViewer.handlers.ItemHandlerApi;
import com.diablo3CharViewer.token.FetchToken;

import java.util.Scanner;

public class CharacterViewerManager {

    public void showMenu() {

        System.out.println("1. Przegladaj profil podajac batlleTag bohatera");
        System.out.println("2. Wyswietl postac dla wybranego profilu");
        System.out.println("3. Wyswietl informacje o przedmiocie");
        System.out.println("4. Opusc archiwum");
    }

    public void showProfile(AccountHandlerApi accountHandlerApi, FetchToken fetchToken) {

        System.out.println("Witaj wedrowcze! Podaj battleTag aby wyszukac profil bohatera: ");
        Scanner scanner = new Scanner(System.in);
        String battleTag = scanner.nextLine();
        scanner.reset();
        accountHandlerApi.setAccount(battleTag);
        accountHandlerApi.setRequestURL();

        System.out.println("Zostan na chwile i poczytaj:\n" + accountHandlerApi.generateRequest(fetchToken) + '\n');
    }

    public void showHero(HeroHandlerApi heroHandlerApi, FetchToken fetchToken) {
        System.out.println("Witaj wedrowcze! Podaj battleTag aby wyszukac profil bohatera: ");
        Scanner scanner = new Scanner(System.in);
        String battleTag = scanner.nextLine();
        scanner.reset();

        System.out.println("Podaj heroId aby wyswietlic postac: ");
        String heroId = scanner.nextLine();
        scanner.reset();

        heroHandlerApi.setAccount(battleTag);
        heroHandlerApi.setHeroId(heroId);
        heroHandlerApi.setRequestURL();
        System.out.println("Zostan na chwile i poczytaj:\n" + heroHandlerApi.generateRequest(fetchToken) + '\n');
    }

    public void showItem(ItemHandlerApi itemHandlerApi, FetchToken fetchToken) {
        System.out.println("Witaj wedrowcze! Podaj itemSlugAndId aby wysweitlic informacje o przedmiocie: ");
        Scanner scanner = new Scanner(System.in);
        String itemSlugAndId = scanner.nextLine();
        scanner.reset();

        itemHandlerApi.setItemSlugAndId(itemSlugAndId);
        itemHandlerApi.setRequestURL();
        System.out.println("Zostan na chwile i poczytaj:\n" + itemHandlerApi.generateRequest(fetchToken) + '\n');
    }

}
