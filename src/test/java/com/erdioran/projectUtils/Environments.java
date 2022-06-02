package com.erdioran.projectUtils;

public class Environments {

    public static final String mainUrl = "https://pro-api.coinmarketcap.com";
    public static final String version = "/v1";
    public static final String url = mainUrl + version;

    public static final String X_CMC_PRO_API_KEY = "X-CMC_PRO_API_KEY";
    public static final String key_value = "get marketcap panel";

    //TEST USER INFO
    public static final String username = "";  //This method is not valid for CoinMarketCap services.
    public static final String password = "";  //This method is not valid for CoinMarketCap services.

    // AUTHENTICATION
    public static final String authenticate = "/authenticate"; //This method is not valid for CoinMarketCap services.
    public static final String accessToken = "accessToken"; //This method is not valid for CoinMarketCap services.


    // cryptocurrency
    public static final String cryptocurrency = "/cryptocurrency";
    public static final String airdrops = "/airdrops";
    public static final String airdrop = "/airdrop";
    public static final String upcoming = "UPCOMING";
    public static final String airdropId = "";

}
