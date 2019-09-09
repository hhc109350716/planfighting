package com.neuedu.runtime;

import com.neuedu.base.BaseSprite;
import com.neuedu.base.Drawable;
import com.neuedu.base.Moveable;
import com.neuedu.constant.FrameConstant;
import com.neuedu.util.ImageMap;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class Prop1 extends BaseSprite implements Moveable, Drawable {

    private List<Image> imageList = new ArrayList<>();
    private int speed = FrameConstant.FRAME_SPEED * 2;
    private boolean up,right,down,left;
//    public boolean Appear;

    public Prop1() {
        this(400,400);
    }

    public Prop1(int x, int y) {
        super(x, y);
        imageList.add(ImageMap.get("prop1"));
        up = true;
//        imageList.add(ImageMap.get("prop2"));
//        imageList.add(ImageMap.get("prop3"));
//        Appear = false;
    }
//    int index;
    @Override
    public void draw(Graphics g) {
//        if (Appear){
            move();
            borderTesting();
            g.drawImage(imageList.get(0),getX(),getY(),imageList.get(0).getWidth(null)*2,
                    imageList.get(0).getHeight(null)*2,null);
//        }
//            if (index >= 2){
//                index = 0;
//            }
    }

    @Override
    public void move() {
        if (up){
            setX(getX() + speed);
            setY(getY() + speed);
        }
        if (right){
            setX(getX() - speed);
            setY(getY() + speed);
        }
        if (down){
            setX(getX() - speed);
            setY(getY() - speed);
        }
        if (left){
            setX(getX() + speed);
            setY(getY() - speed);
        }
    }
    public void borderTesting(){
        if (getX() < 0 ){
            left = true;
            down = false;
            right = false;

        }
        if (getX() > FrameConstant.FRAME_WIDTH - ImageMap.get("prop1").getWidth(null) - 50){
           right = true;
           up = false;
           left = false;
        }
        if (getY() < 30 ){
            up = true;
            left = false;
        }
        if (getY() > FrameConstant.FRAME_HEIGHT -  ImageMap.get("prop1").getHeight(null)){
            down = true;
            right = false;
        }
    }

    @Override
    public Rectangle getRectangle() {
        return new Rectangle(getX(),getY(),ImageMap.get("prop1").getWidth(null),
                ImageMap.get("prop1").getWidth(null));
    }
    public void collisionTesting(MyPlane myPlane) {
        if (myPlane.getRectangle().intersects(this.getRectangle())) {
            myPlane.myHp += 50;
            if (myPlane.myHp > 100){
                myPlane.myHp = 100;
            }
            imageList.remove(this);

        }
    }
}
