package com.diablo3CharViewer;

import com.diablo3CharViewer.handlers.AccountHandlerApi;
import com.diablo3CharViewer.handlers.HeroHandlerApi;
import com.diablo3CharViewer.handlers.ItemHandlerApi;
import com.diablo3CharViewer.token.FetchToken;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {

        FetchToken fetchToken = new FetchToken();
        //fetchToken.requestToken();
        AccountHandlerApi accountHandlerApi = new AccountHandlerApi();
        HeroHandlerApi heroHandlerApi = new HeroHandlerApi();
        ItemHandlerApi itemHandlerApi = new ItemHandlerApi(); //dac tworzenie klas po podaniu battleTag lub item Id z podaną wartoscia dla tego pola
        CharacterViewerManager characterViewerManager = new CharacterViewerManager();

        int wybor;
        Scanner scanner = new Scanner(System.in);

        System.out.println("Witaj w archiwum bohaterow swiata Sanktuarium!\nWybierz odpowiednia opcje z menu aby przejrzec zapisy archiwum:");

       do {

            characterViewerManager.showMenu();
            wybor = scanner.nextInt();
            scanner.nextLine(); //nextInt() doesnt consume new line char when hitting enter to confirm typed data. So this command consume left end line char.

            switch (wybor) {
                case 1:
                    characterViewerManager.showProfile(accountHandlerApi, fetchToken);
                    break;
                case 2:
                    characterViewerManager.showHero(heroHandlerApi, fetchToken);
                    break;
                case 3:
                    characterViewerManager.showItem(itemHandlerApi, fetchToken);
                    break;
                case 4:
                    System.out.println("Zegnaj wedrowcze...");
                    break;
                default:
                    System.out.println("Dokonaj poprawnego wyboru:");
            }
        } while(wybor != 3);
       scanner.close(); //closable wiec zrobic try with resources
    }
}