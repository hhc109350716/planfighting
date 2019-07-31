package com.neuedu.util;

import java.awt.*;
import java.util.HashMap;
import java.util.Map;

public class ImageMap {
    private   static final Map<String, Image> map = new HashMap<>();
    static {
        //背景
        map.put("bg1",ImageUtil.getImage("com\\neuedu\\imgs\\bg\\bg1.png"));

        //我方飞机
        map.put("mp1",ImageUtil.getImage("com\\neuedu\\imgs\\plane\\mp_1.png"));
        //我方子弹
        map.put("mpb1",ImageUtil.getImage("com\\neuedu\\imgs\\bullet\\mpb_1.png"));

        //敌方飞机
        map.put("ep1",ImageUtil.getImage("com\\neuedu\\imgs\\plane\\ep_1.png"));
        //敌方子弹
        map.put("eb1",ImageUtil.getImage("com\\neuedu\\imgs\\bullet\\eb_1.png"));
    }

  public static Image get(String key){
        return map.get(key);
  }
}
