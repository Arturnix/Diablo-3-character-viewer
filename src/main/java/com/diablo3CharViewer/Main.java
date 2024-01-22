package com.diablo3CharViewer;

import com.diablo3CharViewer.token.FetchToken;

public class Main {
    public static void main(String[] args) {

        FetchToken fetchToken = new FetchToken();
        //System.out.println(fetchToken.requestToken().getAccess_token());
        //System.out.println(fetchToken.initializeApiServiceGET("https://eu.api.blizzard.com/d3/data/act?locale=pl_Pl&access_token=EUggOP7SjK01twhg2Ikk9YS3mSZB2xq2wh"));
    }
}