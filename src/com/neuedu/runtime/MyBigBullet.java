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

public class MyBigBullet extends BaseSprite implements Moveable, Drawable {

    private List<Image> imageList = new ArrayList<>();

    private int speed = FrameConstant.FRAME_SPEED * 3;
    GameFram gameFram  = DateStore.get("gameFram");
    public Prop1 prop1 = new Prop1();

    private int index;


    public MyBigBullet() {
        this(0,0);
    }

    public MyBigBullet(int x, int y) {
        super(x, y);
        for (int i = 2; i < 7; i++) {
            imageList.add(ImageMap.get("bmpb" + i));
        }

    }


    @Override
    public void draw(Graphics g) {
        move();
        borderTesting();
        g.drawImage(imageList.get(index++ / 18),getX() - ImageMap.get("bmpb5").getWidth(null) / 2 + 50,
                getY() + ImageMap.get("bmpb5").getHeight(null) / 2,
                imageList.get(0).getWidth(null),
                imageList.get(0).getHeight(null),null);
        if (index >= 90){
            index = 0;
        }
    }

    @Override
    public void move() {
        setY(getY() - speed);
    }
    public void borderTesting(){
        if (getY() < 30  - ImageMap.get("bmpb5").getHeight(null)){
            gameFram.getBigBulletList().remove(this);
        }
    }
    @Override
    public Rectangle getRectangle() {
        return new Rectangle(getX(),getY(),ImageMap.get("bmpb5").getWidth(null),
                ImageMap.get("bmpb5").getWidth(null));
    }

    public void collisionTesting(List<EnemyPlane> enemyPlaneList){
        for (EnemyPlane enemyPlane : enemyPlaneList) {
            if (enemyPlane.getRectangle().intersects(this.getRectangle())){
                enemyPlaneList.remove(enemyPlane);
//                    prop1.Appear = true;
                    gameFram.scout += 20;

            }
        }
    }
    public void collisionTesting1(List<EnemyBullet> enemyBulletList){
        for (EnemyBullet enemyBullet : enemyBulletList) {
            if (enemyBullet.getRectangle().intersects(this.getRectangle())){
               enemyBulletList.remove(enemyBullet);

            }
        }
    }
    public void collisionTesting2(Boss boss){
        if (boss.getRectangle().intersects(this.getRectangle())){
            boss.bossHp -= 50;
            if (boss.bossHp <= 0){
                gameFram.gameover = 3;
            }
            gameFram.getBigBulletList().remove(this);
            gameFram.scout += 30;
        }
    }

}
