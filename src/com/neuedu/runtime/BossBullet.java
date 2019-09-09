package com.neuedu.runtime;

import com.neuedu.base.BaseSprite;
import com.neuedu.base.Drawable;
import com.neuedu.base.Moveable;
import com.neuedu.constant.FrameConstant;
import com.neuedu.main.GameFram;
import com.neuedu.util.DateStore;
import com.neuedu.util.ImageMap;


import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class BossBullet extends BaseSprite implements Drawable, Moveable {

    private List<Image> imageList = new ArrayList<>();
    GameFram gameFram  = DateStore.get("gameFram");
    private int speed = FrameConstant.FRAME_SPEED * 4;

    public BossBullet() {
    }

    public BossBullet(int x, int y) {
        super(x, y);
        for (int i = 1; i < 3; i++) {
            imageList.add(ImageMap.get("bsb" + i));
        }
    }



    private int index;
    @Override
    public void draw(Graphics g) {
        borderTesting();
        move();
        g.drawImage(imageList.get(index++ / 2) , getX(),getY(),imageList.get(0).getWidth(null),
                imageList.get(0).getHeight(null),null);
        if (index >= 4){
            index = 0;
        }
    }


    public void borderTesting(){
        if (getY() > FrameConstant.FRAME_HEIGHT){
            GameFram gameFram  = DateStore.get("gameFram");
            gameFram.getEnemyPlanesList().remove(this);
        }
    }
    @Override
    public Rectangle getRectangle() {
        return new Rectangle(getX(),getY(),ImageMap.get("bsb2").getWidth(null),
                ImageMap.get("bsb2").getWidth(null));
    }
    public void collisionTesting(MyPlane myPlane){

        if (myPlane.getRectangle().intersects(this.getRectangle())){
            myPlane.myHp -= 20;
            if ( myPlane.myHp <= 0){
                gameFram.gameover = 2;
            }
            gameFram.getBossBulletList().remove(this);
        }

    }

    @Override
    public void move() {
        setY(getY()+ speed);
    }
}
