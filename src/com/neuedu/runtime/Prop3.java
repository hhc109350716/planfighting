package com.neuedu.runtime;

import com.neuedu.base.BaseSprite;
import com.neuedu.base.Drawable;
import com.neuedu.base.Moveable;
import com.neuedu.constant.FrameConstant;
import com.neuedu.main.GameFram;
import com.neuedu.util.DateStore;
import com.neuedu.util.ImageMap;

import java.awt.*;

public class Prop3 extends BaseSprite implements Moveable, Drawable {

    private Image image1;
    private boolean left,right;
    private int speed = FrameConstant.FRAME_SPEED * 2;
    GameFram gameFram = DateStore.get("gameFram");

    public Prop3() {
        this(0 ,0);
    }

    public Prop3(int x, int y) {
        super(x, y);
        this.image1 = ImageMap.get("prop3");
        left = true;
    }

    @Override
    public void draw(Graphics g) {
        move();
        borderTesting();
        g.drawImage(image1,getX(),getY(),image1.getWidth(null),image1.getHeight(null),null);

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
        if (getX() > FrameConstant.FRAME_WIDTH - ImageMap.get("prop3").getWidth(null) - 50){
            right = true;
            left = false;
        }

    }
    @Override
    public Rectangle getRectangle() {
        return new Rectangle(getX(),getY(),ImageMap.get("prop3").getWidth(null),
                ImageMap.get("prop3").getWidth(null));
    }
    public void collisionTesting(MyPlane myPlane) {

        if (myPlane.getRectangle().intersects(this.getRectangle())) {
            myPlane.type = myPlane.type + 1 ;
            if ( myPlane.type > 4){
                myPlane.type = 4;
            }

            gameFram.getProp3List().remove(this);

        }
    }
}
