package com.diablo3CharViewer;

import java.util.Scanner;

public class CharacterViewerManager {

    public void showProfile(GetApiAccount getApiAccount, FetchToken fetchToken) {

        System.out.println("Witaj wedrowcze! Podaj battleTag aby wyszukac profil bohatera: ");
        Scanner scanner = new Scanner(System.in);
        String battleTag = scanner.nextLine();
        scanner.reset();
        getApiAccount.setAccount(battleTag);
        getApiAccount.setRequestURL();

        System.out.println("Zostan na chwile i poczytaj:\n" + getApiAccount.generateRequest(fetchToken) + '\n');
    }

}
