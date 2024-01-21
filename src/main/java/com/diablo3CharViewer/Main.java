package com.diablo3CharViewer;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {

        FetchToken fetchToken = new FetchToken();
        //fetchToken.requestToken();
        GetApiAccount getApiAccount = new GetApiAccount();
        GetApiHero getApiHero = new GetApiHero();
        GetApiItem getApiItem = new GetApiItem(); //dac tworzenie klas po podaniu battleTag lub item Id z podaną wartoscia dla tego pola
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
                    characterViewerManager.showProfile(getApiAccount, fetchToken);
                    break;
                case 2:
                    characterViewerManager.showHero(getApiHero, fetchToken);
                    break;
                case 3:
                    characterViewerManager.showItem(getApiItem, fetchToken);
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