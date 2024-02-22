package com.diablo3CharViewer;

import com.diablo3CharViewer.api_handlers.AccountHandlerApi;
import com.diablo3CharViewer.api_handlers.HeroHandlerApi;
import com.diablo3CharViewer.api_handlers.ItemHandlerApi;
import com.diablo3CharViewer.data_models.AccountDataModel;
import com.diablo3CharViewer.json_mappers.AccountMapper;
import com.diablo3CharViewer.json_mappers.HeroMapper;
import com.diablo3CharViewer.token.FetchToken;
import com.diablo3CharViewer.token.Token;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {

        FetchToken fetchToken = new FetchToken();
        AccountMapper accountMapper = new AccountMapper();
        HeroMapper heroMapper = new HeroMapper();
        ItemHandlerApi itemHandlerApi = new ItemHandlerApi(); //dac tworzenie klas po podaniu battleTag lub item Id z podaną wartoscia dla tego pola
        CharacterViewerManager characterViewerManager = new CharacterViewerManager();
        Scanner scanner = new Scanner(System.in);

        System.out.println("Witaj w archiwum bohaterow swiata Sanktuarium!\nWybierz odpowiednia opcje z menu aby przejrzec zapisy archiwum:");
        characterViewerManager.operateMenu(scanner, accountMapper, heroMapper, itemHandlerApi, fetchToken);
    }
}