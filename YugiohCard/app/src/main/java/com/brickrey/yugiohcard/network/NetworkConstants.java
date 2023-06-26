package com.brickrey.yugiohcard.network;

public class NetworkConstants {

    static final int WS_SOCKET_TIMEOUT = 15000;

    // Endpoints
    public static final String WS_END_POINT_URL = "https://db.ygoprodeck.com/api/v7/";
    public static final String IMAGE_END_POINT_URL = "https://images.ygoprodeck.com/images/cards/";

    // Operations
    public static final String OP_CARD = "cardinfo.php";
    public static final String OP_ARCHETYPES = "archetypes.php";
}
