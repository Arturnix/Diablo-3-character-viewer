package com.diablo3CharViewer;

import com.diablo3CharViewer.api_handlers.AccountHandlerApi;
import com.diablo3CharViewer.token.FetchToken;

import java.util.Scanner;

public class CharacterViewerManager {

    public void showProfile(Scanner scanner, AccountHandlerApi accountHandlerApi, FetchToken fetchToken) {

        System.out.println("Witaj wedrowcze! Podaj battleTag aby wyszukac profil bohatera: ");
        String battleTag = scanner.nextLine();
        System.out.println("Zostan na chwile i poczytaj:\n" + accountHandlerApi.generateRequest(battleTag, fetchToken) + '\n');
    }

}
