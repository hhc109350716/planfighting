package com.neuedu.util;

import java.awt.*;
import java.util.HashMap;
import java.util.Map;

public class ImageMap {
    private   static final Map<String, Image> map = new HashMap<>();
    static {


        //开始界面
        map.put("bg1",ImageUtil.getImage("com\\neuedu\\imgs\\begain\\bg.jpg"));
        map.put("bgbutten",ImageUtil.getImage("com\\neuedu\\imgs\\begain\\bgbutten.png"));

        //背景
        map.put("bg2",ImageUtil.getImage("com\\neuedu\\imgs\\bg\\bg1.png"));


        //我方飞机
        map.put("mp1",ImageUtil.getImage("com\\neuedu\\imgs\\plane\\mp_1.png"));
        //我方子弹
        map.put("mpb1",ImageUtil.getImage("com\\neuedu\\imgs\\bullet\\mpb_1.png"));
        map.put("mpb2",ImageUtil.getImage("com\\neuedu\\imgs\\bullet\\mpb_2.png"));
        map.put("mpb3",ImageUtil.getImage("com\\neuedu\\imgs\\bullet\\mpb_3.png"));
        map.put("mpb4",ImageUtil.getImage("com\\neuedu\\imgs\\bullet\\mpb_4.png"));
        //我方大子弹
        map.put("bmpb1",ImageUtil.getImage("com\\neuedu\\imgs\\mybigbullet\\bigbullet_1.png"));
        map.put("bmpb2",ImageUtil.getImage("com\\neuedu\\imgs\\mybigbullet\\bigbullet_2.png"));
        map.put("bmpb3",ImageUtil.getImage("com\\neuedu\\imgs\\mybigbullet\\bigbullet_3.png"));
        map.put("bmpb4",ImageUtil.getImage("com\\neuedu\\imgs\\mybigbullet\\bigbullet_4.png"));
        map.put("bmpb5",ImageUtil.getImage("com\\neuedu\\imgs\\mybigbullet\\bigbullet_5.png"));


        //敌方飞机
        map.put("ep1",ImageUtil.getImage("com\\neuedu\\imgs\\plane\\ep_1.png"));
        map.put("ep2",ImageUtil.getImage("com\\neuedu\\imgs\\plane\\ep_2.png"));
        map.put("ep3",ImageUtil.getImage("com\\neuedu\\imgs\\plane\\ep_3.png"));

        //敌方子弹
        map.put("eb1",ImageUtil.getImage("com\\neuedu\\imgs\\bullet\\eb_1.png"));
        //道具
        map.put("prop1",ImageUtil.getImage("com\\neuedu\\imgs\\prop\\prop_1.png"));
        map.put("prop2",ImageUtil.getImage("com\\neuedu\\imgs\\prop\\prop_2.png"));
        map.put("prop3",ImageUtil.getImage("com\\neuedu\\imgs\\prop\\prop_3.png"));


        //boss
        map.put("bs1",ImageUtil.getImage("com\\neuedu\\imgs\\boss\\boss_A_01.png"));
        map.put("bs2",ImageUtil.getImage("com\\neuedu\\imgs\\boss\\boss_A_02.png"));
        map.put("bs3",ImageUtil.getImage("com\\neuedu\\imgs\\boss\\boss_A_03.png"));
        map.put("bs4",ImageUtil.getImage("com\\neuedu\\imgs\\boss\\boss_A_04.png"));
        map.put("bs5",ImageUtil.getImage("com\\neuedu\\imgs\\boss\\boss_A_05.png"));
        map.put("bs6",ImageUtil.getImage("com\\neuedu\\imgs\\boss\\boss_A_06.png"));
        map.put("bs7",ImageUtil.getImage("com\\neuedu\\imgs\\boss\\boss_A_07.png"));
        map.put("bs8",ImageUtil.getImage("com\\neuedu\\imgs\\boss\\boss_A_08.png"));
        map.put("bs9",ImageUtil.getImage("com\\neuedu\\imgs\\boss\\boss_A_09.png"));

        //boss子弹
        map.put("bsb1",ImageUtil.getImage("com\\neuedu\\imgs\\bossbullet\\01.png"));
        map.put("bsb2",ImageUtil.getImage("com\\neuedu\\imgs\\bossbullet\\02.png"));

        //成功
        map.put("win",ImageUtil.getImage("com\\neuedu\\imgs\\\\lose\\win.png"));

        //失败
        map.put("lose",ImageUtil.getImage("com\\neuedu\\imgs\\\\lose\\lose.png"));






    }

  public static Image get(String key){
        return map.get(key);
  }
}
