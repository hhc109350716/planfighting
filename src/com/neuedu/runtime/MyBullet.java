package com.neuedu.runtime;

import com.neuedu.base.BaseSprite;
import com.neuedu.base.Drawable;
import com.neuedu.base.Moveable;
import com.neuedu.constant.FrameConstant;
import com.neuedu.main.GameFram;
import com.neuedu.util.DateStore;
import com.neuedu.util.ImageMap;

import java.awt.*;
import java.util.List;

public class MyBullet extends BaseSprite implements Moveable, Drawable {

    private Image image;
    private int speed = FrameConstant.FRAME_SPEED * 6;

    public MyBullet() {
        this(0,0,ImageMap.get("mpb1"));
    }

    public MyBullet(int x, int y, Image image) {
        super(x, y);
        this.image = image;
    }

    @Override
    public void draw(Graphics g) {
        g.drawImage(image,getX(),getY(),image.getWidth(null),image.getHeight(null),null);
        move();
        borderTesting();
    }

    @Override
    public void move() {
        setY(getY() - speed);
    }

    @Override
    public Rectangle getRectangle() {
        return new Rectangle(getX(),getY(),ImageMap.get("mpb1").getWidth(null),
                ImageMap.get("mpb1").getWidth(null));
    }

    public void borderTesting(){
        if (getY() < 30 - image.getHeight(null)){
            GameFram gameFram  = DateStore.get("gameFram");
            gameFram.getBulletList().remove(this);
        }
    }

    public void collisionTesting(List<EnemyPlane> enemyPlaneList){
        for (EnemyPlane enemyPlane : enemyPlaneList) {
            if (enemyPlane.getRectangle().intersects(this.getRectangle())){
                enemyPlaneList.remove(enemyPlane);
                GameFram gameFram  = DateStore.get("gameFram");
                gameFram.getBulletList().remove(this);

            }
        }
    }
    public void bulletcollisionTesting(List<EnemyBullet> enemyBulletList){
        for (EnemyBullet enemyBullet : enemyBulletList) {
            if (enemyBullet.getRectangle().intersects(this.getRectangle())){
                enemyBulletList.remove(enemyBullet);
                GameFram gameFram  = DateStore.get("gameFram");
                gameFram.getBulletList().remove(this);
            }
        }
    }
}
