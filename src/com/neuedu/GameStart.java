package com.neuedu;

import com.neuedu.main.GameFram;
import com.neuedu.util.DateStore;

public class GameStart {
    public static void main(String[] args) {
        GameFram gameFram = new GameFram();
        DateStore.put("gameFram",gameFram);
        gameFram.init();
    }
}
