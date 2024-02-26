package com.diablo3CharViewer;

import com.diablo3CharViewer.api_handlers.HeroHandlerApi;
import com.diablo3CharViewer.api_handlers.ItemHandlerApi;
import com.diablo3CharViewer.json_mappers.AccountMapper;
import com.diablo3CharViewer.json_mappers.HeroMapper;
import com.diablo3CharViewer.token.FetchToken;

import java.util.Scanner;

public class CharacterViewerManager {

    private void showProfile(Scanner scanner, AccountMapper accountMapper, FetchToken fetchToken) {

        String battleTag = battleTagProvider(scanner);
        if(isBattleTagCorrect(battleTag)) {
            System.out.println("Zostan na chwile i poczytaj:\n" + accountMapper.fetchAccountToDataModel(battleTag, fetchToken) + '\n');
        }
    }

    private void showHero(Scanner scanner, HeroMapper heroMapper, FetchToken fetchToken) {

        String battleTag = battleTagProvider(scanner);
        if (isBattleTagCorrect(battleTag)) {
            String heroId = heroIdProvider(scanner);
            if (isHeroIDCorrect(heroId)) {
                System.out.println("Zostan na chwile i poczytaj:\n" + heroMapper.fetchHeroToDataModel(battleTag, heroId, fetchToken) + '\n');
            }
        }
    }

    private void showItem(Scanner scanner, ItemHandlerApi itemHandlerApi, FetchToken fetchToken) {
        System.out.println("Zostan na chwile i poczytaj:\n" + itemHandlerApi.generateRequest(itemSlugAndIdProvider(scanner), fetchToken) + '\n');
    }

    private String battleTagProvider(Scanner scanner) {
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

    private boolean isBattleTagCorrect(String battleTagToCheck) {

        if (!battleTagToCheck.matches("\\w+#+\\d+") && !battleTagToCheck.matches("\\w+-+\\d+")) {
            System.out.println("Niepoprawny format battleTag! Spróbuj ponownie.");
            return false;
        } else {
            return true;
        }
    }

    private boolean isHeroIDCorrect(String heroIdToCheck) {

        if(!heroIdToCheck.matches("\\d+")) {
            System.out.println("Niepoprawny format heroId - tylko cyfry! Spróbuj ponownie.");
            return false;
        } else {
            return true;
        }
    }

    private void showMenu() {
        System.out.println("1. Przegladaj profil podajac batlleTag bohatera");
        System.out.println("2. Wyswietl postac dla wybranego profilu");
        System.out.println("3. Wyswietl informacje o przedmiocie");
        System.out.println("4. Opusc archiwum");
    }

    public void operateMenu(Scanner scanner, AccountMapper accountMapper, HeroMapper heroMapper,
                            ItemHandlerApi itemHandlerApi, FetchToken fetchToken) {
        String wybor;

        do {
            showMenu();
            wybor = scanner.nextLine();
            //scanner.nextLine(); //nextInt() doesnt consume new line char when hitting enter to confirm typed data. So this command consume left end line char.

            switch (wybor) {
                case "1":
                    showProfile(scanner, accountMapper, fetchToken);
                    break;
                case "2":
                    showHero(scanner, heroMapper, fetchToken);
                    break;
                case "3":
                    showItem(scanner, itemHandlerApi, fetchToken);
                    break;
                case "4":
                    System.out.println("Zegnaj wedrowcze...");
                    break;
                default:
                    System.out.println("Dokonaj poprawnego wyboru:");
            }
        } while(!wybor.equals("4"));
    }
}