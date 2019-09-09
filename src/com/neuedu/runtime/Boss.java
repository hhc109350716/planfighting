package com.neuedu.runtime;

import com.neuedu.base.BaseSprite;
import com.neuedu.base.Drawable;
import com.neuedu.main.GameFram;
import com.neuedu.util.DateStore;
import com.neuedu.util.ImageMap;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Boss extends BaseSprite implements  Drawable {

    private List<Image> imageList = new ArrayList<>();

    private Random random = new Random();

    GameFram gameFram  = DateStore.get("gameFram");


    public int bossHp;

    public Boss() {
        this(250,100);
    }

    public Boss(int x, int y) {
        super(x, y);
        bossHp = 2000;
        for (int i = 1; i < 10; i++) {
            imageList.add(ImageMap.get("bs"+i));
        }
    }
    private int index;
    @Override
    public void draw(Graphics g) {
        fire();
        g.drawImage(imageList.get(index++ / 6),250,100,imageList.get(0).getWidth(null),
                imageList.get(0).getHeight(null),null);


        if (index >= 54){
            index = 0;
        }
    }

    public  void fire(){
        GameFram gameFram  = DateStore.get("gameFram");
        if (random.nextInt(1000) > 996) {
            gameFram.getBossBulletList().add(new BossBullet(250 + ImageMap.get("bsb2").getWidth(null)/2 + 60,
                    100 + ImageMap.get("bsb2").getHeight(null)));
        }

    }
    @Override
    public Rectangle getRectangle() {
        return new Rectangle(getX(),getY(), ImageMap.get("bs1").getWidth(null),
                ImageMap.get("bs1").getWidth(null));
    }

    public void collisionTesting(MyPlane myPlane){
        if (myPlane.getRectangle().intersects(this.getRectangle())){
            new Thread(){
                @Override
                public void run() {
                    while (true) {
                        try {
                            Thread.sleep(5000);
                            myPlane.myHp -= 30;
                            if ( myPlane.myHp <= 0){
                                GameFram gameFram  = DateStore.get("gameFram");
                                gameFram.gameover = 2;
                            }
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }


                    }

                }
            }.start();

        }

    }


}
