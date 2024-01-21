package com.diablo3CharViewer;

import java.util.Scanner;

public class CharacterViewerManager {

    public void showMenu() {

        System.out.println("1. Przegladaj profil podajac batlleTag bohatera");
        System.out.println("2. Wyswietl postac dla wybranego profilu");
        System.out.println("3. Wyswietl informacje o przedmiocie");
        System.out.println("4. Opusc archiwum");
    }

    public void showProfile(GetApiAccount getApiAccount, FetchToken fetchToken) {

        System.out.println("Witaj wedrowcze! Podaj battleTag aby wyszukac profil bohatera: ");
        Scanner scanner = new Scanner(System.in);
        String battleTag = scanner.nextLine();
        scanner.reset();
        getApiAccount.setAccount(battleTag);
        getApiAccount.setRequestURL();

        System.out.println("Zostan na chwile i poczytaj:\n" + getApiAccount.generateRequest(fetchToken) + '\n');
    }

    public void showHero(GetApiHero getApiHero, FetchToken fetchToken) {
        System.out.println("Witaj wedrowcze! Podaj battleTag aby wyszukac profil bohatera: ");
        Scanner scanner = new Scanner(System.in);
        String battleTag = scanner.nextLine();
        scanner.reset();

        System.out.println("Podaj heroId aby wyswietlic postac: ");
        String heroId = scanner.nextLine();
        scanner.reset();

        getApiHero.setAccount(battleTag);
        getApiHero.setHeroId(heroId);
        getApiHero.setRequestURL();
        System.out.println("Zostan na chwile i poczytaj:\n" + getApiHero.generateRequest(fetchToken) + '\n');
    }

    public void showItem(GetApiItem getApiItem, FetchToken fetchToken) {
        System.out.println("Witaj wedrowcze! Podaj itemSlugAndId aby wysweitlic informacje o przedmiocie: ");
        Scanner scanner = new Scanner(System.in);
        String itemSlugAndId = scanner.nextLine();
        scanner.reset();

        getApiItem.setItemSlugAndId(itemSlugAndId);
        getApiItem.setRequestURL();
        System.out.println("Zostan na chwile i poczytaj:\n" + getApiItem.generateRequest(fetchToken) + '\n');
    }

}
