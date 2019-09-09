package com.neuedu.runtime;

import com.neuedu.base.BaseSprite;
import com.neuedu.base.Drawable;
import com.neuedu.base.Moveable;
import com.neuedu.constant.FrameConstant;
import com.neuedu.main.GameFram;
import com.neuedu.util.DateStore;
import com.neuedu.util.ImageMap;

import java.awt.*;

public class Prop2 extends BaseSprite implements Moveable, Drawable {

    private Image image;
    private int speed = FrameConstant.FRAME_SPEED * 2;
    private boolean right,left;

    public Prop2() {
        this(400,400);
    }

    public Prop2(int x, int y) {
        super(x, y);
        this.image = ImageMap.get("prop2");
        left = true;
    }
    @Override
    public void draw(Graphics g) {
        move();
        borderTesting();
        g.drawImage(image,getX(),getY(),image.getWidth(null)*2,
                image.getHeight(null)*2,null);
    }

    @Override
    public void move() {
        if (right){
            setX(getX() - speed);
            setY(getY() + speed);
        }

        if (left){
            setX(getX() + speed);
            setY(getY() + speed);
        }
    }
    public void borderTesting(){
        if (getX() < 0 ){
            left = true;
            right = false;

        }
        if (getX() > FrameConstant.FRAME_WIDTH - ImageMap.get("prop2").getWidth(null) - 50){
            right = true;
            left = false;
        }

    }

    @Override
    public Rectangle getRectangle() {
        return new Rectangle(getX(),getY(),ImageMap.get("prop2").getWidth(null),
                ImageMap.get("prop2").getWidth(null));
    }
    public void collisionTesting(MyPlane myPlane) {
        GameFram gameFram = DateStore.get("gameFram");
        if (myPlane.getRectangle().intersects(this.getRectangle())) {
            myPlane.bigbullet += 10;
            if (myPlane.bigbullet > 20){
                myPlane.bigbullet = 20;
            }
            gameFram.getProp2List().remove(this);

        }
    }
}
