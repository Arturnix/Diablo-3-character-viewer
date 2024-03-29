package com.diablo3CharViewer;

import com.diablo3CharViewer.api_handlers.HeroHandlerApi;
import com.diablo3CharViewer.api_handlers.ItemHandlerApi;
import com.diablo3CharViewer.data_models.HeroDataModel;
import com.diablo3CharViewer.json_mappers.AccountMapper;
import com.diablo3CharViewer.json_mappers.HeroMapper;
import com.diablo3CharViewer.json_mappers.ItemMapper;
import com.diablo3CharViewer.token.FetchToken;

import java.util.List;
import java.util.Scanner;

public class CharacterViewerManager {

    public String profileDataInfoProvider(Scanner scanner, AccountMapper accountMapper, FetchToken fetchToken) {

        String battleTag = battleTagProvider(scanner);
        if (isBattleTagCorrect(battleTag)) {
            return "Zostan na chwile i poczytaj:\n" + accountMapper.fetchAccountToDataModel(battleTag, fetchToken) + '\n';
        } else {
            return "Niepoprawny format battleTag! Spróbuj ponownie.";
        }
    }

    public String heroDataInfoProvider(Scanner scanner, AccountMapper accountMapper, HeroMapper heroMapper, FetchToken fetchToken) {

        String battleTag = battleTagProvider(scanner);
        if (isBattleTagCorrect(battleTag)) {
            List<HeroDataModel> heroesOnProvidedAccount = accountMapper.fetchHeroesList(battleTag, fetchToken);
            if(heroesOnProvidedAccount.isEmpty()) {
                System.out.println("Brak bohaterow dla podanego konta");
            } else {
                showHeroesListForSpecificAccount(heroesOnProvidedAccount);
            }
            String heroId = heroIdProvider(scanner);
            if (isHeroIDCorrect(heroId)) {
                return "Zostan na chwile i poczytaj:\n" + heroMapper.fetchHeroToDataModel(battleTag, heroId, fetchToken) + '\n';
            } else {
                return "Niepoprawny format heroId - tylko cyfry! Spróbuj ponownie.";
            }
        } else {
            return "Niepoprawny format battleTag! Spróbuj ponownie.";
        }
    }

    public String itemDataInfoProvider(Scanner scanner, ItemMapper itemMapper, FetchToken fetchToken) {
        return "Zostan na chwile i poczytaj:\n" + itemMapper.fetchItemToDataModel(itemSlugAndIdProvider(scanner), fetchToken) + '\n';
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
            return false;
        } else {
            return true;
        }
    }

    private boolean isHeroIDCorrect(String heroIdToCheck) {

        if(!heroIdToCheck.matches("\\d+")) {
            return false;
        } else {
            return true;
        }
    }

    private void showHeroesListForSpecificAccount(List<HeroDataModel> heroesOnProvidedAccount) {

        for (int i = 0; i < heroesOnProvidedAccount.size(); i++) {
            StringBuilder str = new StringBuilder();
            str.append("Hero id: ").append(heroesOnProvidedAccount.get(i).getId());
            str.append(", name: ").append(heroesOnProvidedAccount.get(i).getName());
            str.append(", class: ").append(heroesOnProvidedAccount.get(i).getClassHero());
            str.append(", level: ").append(heroesOnProvidedAccount.get(i).getLevel());
            System.out.println(str.toString() + '\n');
        }
    }

    public void showMenu() {
        System.out.println("1. Przegladaj profil podajac batlleTag bohatera");
        System.out.println("2. Wyswietl postac dla wybranego profilu");
        System.out.println("3. Wyswietl informacje o przedmiocie");
        System.out.println("4. Opusc archiwum");
    }
}