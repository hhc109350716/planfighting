package com.neuedu.runtime;

import com.neuedu.base.BaseSprite;
import com.neuedu.base.Drawable;
import com.neuedu.base.Moveable;
import com.neuedu.constant.FrameConstant;
import com.neuedu.main.GameFram;
import com.neuedu.util.DateStore;
import com.neuedu.util.ImageMap;

import java.awt.*;
import java.util.Random;

public class EnemyPlane extends BaseSprite implements Moveable, Drawable {

    private Image image;
    private int speed = FrameConstant.FRAME_SPEED * 3;
    private Random random = new Random();

    public EnemyPlane() {
        this(0,0,ImageMap.get("ep1"));
    }

    public EnemyPlane(int x, int y, Image image) {
        super(x, y);
        this.image = image;
    }


    @Override
    public void draw(Graphics g) {
        g.drawImage(image,getX(),getY(),image.getWidth(null),image.getHeight(null),null);
        move();
        fire();
        borderTesting();
    }

    @Override
    public void move() {
        setY(getY() + speed);
    }
    @Override
    public Rectangle getRectangle() {
        return new Rectangle(getX(),getY(),ImageMap.get("ep1").getWidth(null),
                ImageMap.get("ep1").getWidth(null));
    }
    public void fire(){
        GameFram gameFram  = DateStore.get("gameFram");
        if (random.nextInt(1000) > 980){
            gameFram.getEnemyBulletList().add(new EnemyBullet(getX() + ImageMap.get("ep1").getWidth(null) / 2  - 5,
                    getY() + ImageMap.get("ep1").getHeight(null) , ImageMap.get("eb1")));
        }

    }
    public void borderTesting(){
        if (getY() > FrameConstant.FRAME_HEIGHT){
            GameFram gameFram  = DateStore.get("gameFram");
            gameFram.getEnemyPlanesList().remove(this);
        }
    }
    public void collisionTesting(MyPlane myPlane) {
        if (myPlane.getRectangle().intersects(this.getRectangle())) {
            GameFram gameFram = DateStore.get("gameFram");
            gameFram.getEnemyPlanesList().remove(this);
            gameFram.gameover = true;
        }
    }
}
