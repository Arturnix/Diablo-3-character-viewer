package com.diablo3CharViewer;

import com.diablo3CharViewer.api_handlers.AccountHandlerApi;
import com.diablo3CharViewer.api_handlers.HeroHandlerApi;
import com.diablo3CharViewer.api_handlers.ItemHandlerApi;
import com.diablo3CharViewer.data_models.AccountDataModel;
import com.diablo3CharViewer.json_mappers.AccountMapper;
import com.diablo3CharViewer.json_mappers.HeroMapper;
import com.diablo3CharViewer.json_mappers.ItemMapper;
import com.diablo3CharViewer.token.FetchToken;
import com.diablo3CharViewer.token.Token;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {

        FetchToken fetchToken = new FetchToken();
        AccountMapper accountMapper = new AccountMapper();
        HeroMapper heroMapper = new HeroMapper();
        ItemMapper itemMapper = new ItemMapper();
        //ItemHandlerApi itemHandlerApi = new ItemHandlerApi(); //dac tworzenie klas po podaniu battleTag lub item Id z podaną wartoscia dla tego pola
        CharacterViewerManager characterViewerManager = new CharacterViewerManager();
        Scanner scanner = new Scanner(System.in);
        String wybor;

        System.out.println("Witaj w archiwum bohaterow swiata Sanktuarium!\nWybierz odpowiednia opcje z menu aby przejrzec zapisy archiwum:");

        do {
            characterViewerManager.showMenu();
            wybor = scanner.nextLine();
            //scanner.nextLine(); //nextInt() doesnt consume new line char when hitting enter to confirm typed data. So this command consume left end line char.

            switch (wybor) {
                case "1":
                    characterViewerManager.showProfile(scanner, accountMapper, fetchToken);
                    break;
                case "2":
                    characterViewerManager.showHero(scanner, heroMapper, fetchToken);
                    break;
                case "3":
                    characterViewerManager.showItem(scanner, itemMapper, fetchToken);
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