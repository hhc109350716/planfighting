package com.neuedu.runtime;

import com.neuedu.base.BaseSprite;
import com.neuedu.base.Drawable;
import com.neuedu.base.Moveable;
import com.neuedu.constant.FrameConstant;
import com.neuedu.main.GameFram;
import com.neuedu.util.DateStore;
import com.neuedu.util.ImageMap;

import java.awt.*;
import java.awt.event.KeyEvent;

public class MyPlane extends BaseSprite implements Moveable, Drawable {

    private Image image;
    private int speed = FrameConstant.FRAME_SPEED * 5;
    private boolean up,right,down,left;
    private boolean fire;
    private boolean bigfire;
    public int bigbullet;
    public int myHp;
    public int type = 1;

    public MyPlane() {
        this((FrameConstant.FRAME_WIDTH - ImageMap.get("mp1").getWidth(null))/2,
                FrameConstant.FRAME_HEIGHT - ImageMap.get("mp1").getHeight(null),
                ImageMap.get("mp1"));
    }

    public MyPlane(int x, int y, Image image) {
        super(x, y);
        this.image = image;
        myHp = 100;
        bigbullet = 10;
    }

    @Override
    public void draw(Graphics g) {
        move();

        borderTesting();
        g.drawImage(image,getX(),getY(),image.getWidth(null),image.getHeight(null),null);

    }

    /**
     * 判断开关是否闭合
     * 创建一个子弹对象gameFrame里的子弹集合里
     */
    public void fire(){
        if (fire) {
          GameFram gameFram = DateStore.get("gameFram");
          gameFram.getBulletList().add(new MyBullet(
                  getX() + (image.getWidth(null) / 2) - (ImageMap.get("mpb1").getWidth(null) / 2),
                   getY() - ImageMap.get("mpb1").getWidth(null) - 30,type));

        }
        if (bigfire){
            GameFram gameFram = DateStore.get("gameFram");
            gameFram.getBigBulletList().add(new MyBigBullet(
                    getX() + (image.getWidth(null) / 2) -  (ImageMap.get("bmpb5").getWidth(null) / 2 - 65),
                    getY() - ImageMap.get("bmpb5").getWidth(null)));
        }
    }
    @Override
    public void move() {
        if (up) {
            setY(getY() - speed);
        }
        if (right) {
            setX(getX() + speed);
        }
        if (down) {
            setY(getY() + speed);
        }
        if (left) {
            setX(getX() - speed);
        }
    }

    @Override
    public Rectangle getRectangle() {
        return new Rectangle(getX(),getY(),ImageMap.get("mp1").getWidth(null),
                ImageMap.get("mp1").getWidth(null));
    }

    public void borderTesting(){
        if (getX() < 0 ){
                setX(0);
        }
        if (getX() > FrameConstant.FRAME_WIDTH - ImageMap.get("mp1").getWidth(null)){
            setX(FrameConstant.FRAME_WIDTH - ImageMap.get("mp1").getWidth(null));
        }
        if (getY() < 30 ){
            setY(30);
        }
        if (getY() > FrameConstant.FRAME_HEIGHT -  ImageMap.get("mp1").getHeight(null)){
            setY(FrameConstant.FRAME_HEIGHT -  ImageMap.get("mp1").getHeight(null));
        }
    }
    public void keyPressed(KeyEvent e) {
        if (e.getKeyCode() == KeyEvent.VK_W){
            up = true;
        }
        if (e.getKeyCode() == KeyEvent.VK_D){
            right = true;
        }
        if (e.getKeyCode() == KeyEvent.VK_S){
            down = true;
        }
        if (e.getKeyCode() == KeyEvent.VK_A){
            left = true;
        }
        if (e.getKeyCode() == KeyEvent.VK_J){
            fire = true;
        }
        if (e.getKeyCode() == KeyEvent.VK_O){
            if (bigbullet > 0){
                bigfire = true;
                bigbullet--;
            }else{
                bigfire = false;
            }


        }

    }
    public void keyReleased(KeyEvent e) {
        if (e.getKeyCode() == KeyEvent.VK_W){
            up = false;
        }
        if (e.getKeyCode() == KeyEvent.VK_D){
            right = false;
        }
        if (e.getKeyCode() == KeyEvent.VK_S){
            down = false;
        }
        if (e.getKeyCode() == KeyEvent.VK_A){
            left = false;
        }
        if (e.getKeyCode() == KeyEvent.VK_J){
            fire();
            fire = false;
        }
        if (e.getKeyCode() == KeyEvent.VK_O){
            fire();
            bigfire = false;
        }
    }
}
