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

public class EnemyBullet extends BaseSprite implements Moveable, Drawable {

    private Image image;

    private int speed = FrameConstant.FRAME_SPEED * 5;

    public EnemyBullet() {
    }

    public EnemyBullet(int x, int y, Image image) {
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
            setY(getY() + speed);
    }
    //碰撞检测
    @Override
    public Rectangle getRectangle() {
        return new Rectangle(getX(),getY(),ImageMap.get("eb1").getWidth(null),
                ImageMap.get("eb1").getWidth(null));
    }
    public void collisionTesting(MyPlane myPlane){
        if (myPlane.getRectangle().intersects(this.getRectangle())){
            GameFram gameFram  = DateStore.get("gameFram");
            gameFram.getEnemyBulletList().remove(this);
            gameFram.gameover = true;
        }


    }

    public void borderTesting(){
        if (getY() > FrameConstant.FRAME_HEIGHT){
            GameFram gameFram  = DateStore.get("gameFram");
            gameFram.getEnemyPlanesList().remove(this);
        }
    }
}
