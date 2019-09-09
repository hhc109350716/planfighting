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
    private Image image1;
    private Image image2;
    private Image image3;
    private Image image4;
    private int type;

    private MyPlane myPlane = new MyPlane();
    private int speed = FrameConstant.FRAME_SPEED * 6;
    GameFram gameFram  = DateStore.get("gameFram");


    public MyBullet(int x, int y,int type) {
        super(x, y);
        this.image1 = ImageMap.get("mpb1");
        this.image2 = ImageMap.get("mpb2");
        this.image3 = ImageMap.get("mpb3");
        this.image4 = ImageMap.get("mpb4");
        this.type = type;

    }

    @Override
    public void draw(Graphics g){
        move();
        borderTesting();
        if ( type == 1){
            g.drawImage(image1,getX(),
                    getY(),image1.getWidth(null),
                    image1.getHeight(null),null);
        }
        if ( type == 2){
            g.drawImage(image2,getX() - image2.getWidth(null) / 2  + 30,
                    getY(),image2.getWidth(null),
                    image2.getHeight(null),null);
        }
        if ( type == 3){
            g.drawImage(image3,getX() - image3.getWidth(null) / 2  + 30,
                    getY(),
                    image3.getWidth(null),image3.getHeight(null),null);
        }
        if (type == 4){
            g.drawImage(image4,getX()- image4.getWidth(null) / 2  + 30,
                    getY(),image4.getWidth(null),
                    image4.getHeight(null),null);
        }


    }

    @Override
    public void move() {
        setY(getY() - speed);
    }

    @Override
    public Rectangle getRectangle() {
        if ( type == 1){
            return new Rectangle(getX(),getY(),ImageMap.get("mpb1").getWidth(null),
                    ImageMap.get("mpb1").getWidth(null));
        }else if ( type == 2){
            return new Rectangle(getX(),getY(),ImageMap.get("mpb2").getWidth(null),
                    ImageMap.get("mpb2").getWidth(null));
        }else if ( type == 3){
            return new Rectangle(getX(),getY(),ImageMap.get("mpb3").getWidth(null),
                    ImageMap.get("mpb3").getWidth(null));
        }else{
            return new Rectangle(getX(),getY(),ImageMap.get("mpb4").getWidth(null),
                    ImageMap.get("mpb4").getWidth(null));
        }
    }

    public void borderTesting(){
        if ( type == 1){
            if (getY() < 30 - image1.getHeight(null)){
                gameFram.getBulletList().remove(this);
            }
        }
        if ( type == 2){
            if (getY() < 30 - image2.getHeight(null)){
                gameFram.getBulletList().remove(this);
            }
        }
        if ( type == 3){
            if (getY() < 30 - image3.getHeight(null)){
                gameFram.getBulletList().remove(this);
            }
        }
        if ( type == 4){
            if (getY() < 30 - image4.getHeight(null)){
                gameFram.getBulletList().remove(this);
            }
        }

    }

    public void collisionTesting(List<EnemyPlane> enemyPlaneList){
        for (EnemyPlane enemyPlane : enemyPlaneList) {
            if (enemyPlane.getRectangle().intersects(this.getRectangle())){
                if (type == 1){
                    enemyPlane.hp -= 10;
                }
                if (type == 2){
                    enemyPlane.hp -= 15;
                }
                if (type == 3){
                    enemyPlane.hp -= 20;
                }
                if (type == 4){
                    enemyPlane.hp -= 25;
                }

                if ( enemyPlane.hp <= 0){
                    enemyPlaneList.remove(enemyPlane);
                    gameFram.scout += 20;
//                    prop1.Appear = true;
                }
                gameFram.getBulletList().remove(this);
                if (type == 1){
                    gameFram.scout += 5;
                }
                if (type == 2){
                    gameFram.scout += 10;
                }
                if (type == 3){
                    gameFram.scout += 15;
                }
                if (type == 4){
                    gameFram.scout += 20;
                }

            }
        }
    }
    public void collisionTesting1(Boss boss){
        if (boss.getRectangle().intersects(this.getRectangle())){
            if (type == 1){
                boss.bossHp -= 10;
            }
            if (type == 2){
                boss.bossHp -= 15;
            }
            if (type == 3){
                boss.bossHp -= 20;
            }
            if (type == 4){
                boss.bossHp -= 25;
            }

            if (boss.bossHp<=0){
                gameFram.gameover = 3;
            }
            gameFram.getBulletList().remove(this);
            if (type == 1){
                gameFram.scout += 10;
            }
            if (type == 2){
                gameFram.scout += 15;
            }
            if (type == 3){
                gameFram.scout += 20;
            }
            if (type == 4){
                gameFram.scout += 25;
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
