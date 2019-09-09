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
    private Image image1;
    private Image image2;
    private Image image3;
    private int speed = FrameConstant.FRAME_SPEED*2;
    private Random random = new Random();
    private int type;
    private boolean right;
    public int hp;



    public EnemyPlane() {
        this(0,0,1);
    }

    public EnemyPlane(int x, int y,int type) {
        super(x, y);
        this.type = type;
        this.image1 = ImageMap.get("ep1");
        this.image2 = ImageMap.get("ep2");
        this.image3 = ImageMap.get("ep3");
        setHp();

    }
    public void setHp(){
        if (type == 1){
            hp = 50;
        }
        if (type == 2){
            hp = 100;
        }
        if (type == 3){
            hp = 150;
        }
    }
    @Override
    public void draw(Graphics g) {
        move();
        if (getY() > 30) {
            fire();
        }

        borderTesting();
        if (type == 1){
            g.drawImage(image1,getX(),getY(),image1.getWidth(null),image1.getHeight(null),null);
        }
        if (type == 2){
            g.drawImage(image2,getX(),getY(),image2.getWidth(null),image2.getHeight(null),null);
        }
        if (type == 3){
            g.drawImage(image3,getX(),getY(),image3.getWidth(null),image3.getHeight(null),null);
        }


    }

    @Override
    public void move() {
        if (type == 1){
            setY(getY() + speed);
        }else if (type == 2){
           if (right){
               setX(getX() + speed);
           }else{
               setX(getX() - speed);
           }
            setY(getY() + speed);
        }else if (type == 3){
            if (right){
                setX(getX() + speed);
            }else{
                setX(getX() - speed);
            }
            setY(getY() + speed);
        }

    }
    @Override
    public Rectangle getRectangle() {
        return new Rectangle(getX(),getY(),ImageMap.get("ep1").getWidth(null),
                ImageMap.get("ep1").getWidth(null));
    }
    public void fire(){
        GameFram gameFram  = DateStore.get("gameFram");
        if (random.nextInt(1000) > 990){
            gameFram.getEnemyBulletList().add(new EnemyBullet(getX() + ImageMap.get("ep1").getWidth(null) / 2  - 5,
                    getY() + ImageMap.get("ep1").getHeight(null) , ImageMap.get("eb1")));
        }

    }
    public void borderTesting(){
        if (type == 1){
            if (getY() > FrameConstant.FRAME_HEIGHT){
                GameFram gameFram  = DateStore.get("gameFram");
                gameFram.getEnemyPlanesList().remove(this);
            }
        }else if (type == 2){
            if (getY() > FrameConstant.FRAME_HEIGHT){
                GameFram gameFram  = DateStore.get("gameFram");
                gameFram.getEnemyPlanesList().remove(this);
            }
            if (getX() >= FrameConstant.FRAME_WIDTH - image2.getWidth(null)){
                right = false;
            }else if (getX() < 0){
                    right = true;
            }
        }else if (type == 3){
            if (getY() > FrameConstant.FRAME_HEIGHT){
                GameFram gameFram  = DateStore.get("gameFram");
                gameFram.getEnemyPlanesList().remove(this);
            }
            if (getX() >= FrameConstant.FRAME_WIDTH - image2.getWidth(null)){
                right = false;
            }else if (getX() < 0){
                right = true;
            }
        }


    }
    public void collisionTesting(MyPlane myPlane) {
        if (myPlane.getRectangle().intersects(this.getRectangle())) {
            GameFram gameFram = DateStore.get("gameFram");
            myPlane.myHp -=10;
            gameFram.scout += 20;
            if (myPlane.myHp <= 0){
                gameFram.gameover = 2;
            }
            gameFram.getEnemyPlanesList().remove(this);

        }
    }
}
