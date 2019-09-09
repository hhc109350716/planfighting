package com.neuedu.runtime;

import com.neuedu.base.BaseSprite;
import com.neuedu.base.Drawable;
import com.neuedu.base.Moveable;
import com.neuedu.constant.FrameConstant;
import com.neuedu.main.GameFram;
import com.neuedu.util.DateStore;
import com.neuedu.util.ImageMap;

import java.awt.*;
import java.awt.event.MouseEvent;

public class Start extends BaseSprite implements Moveable, Drawable {

    private Image image1;
    private Image image2;
    private int speed = FrameConstant.FRAME_SPEED;
    GameFram gameFram  = DateStore.get("gameFram");
    public Boolean start = false;

    public Start() {
        this(0,FrameConstant.FRAME_HEIGHT-ImageMap.get("bg2").getHeight(null),
                ImageMap.get("bg1"),ImageMap.get("bgbutten"));
    }

    public Start(int x, int y, Image image1,Image image2) {
        super(x, y);
        this.image1 = image1;
        this.image2 = image2;
    }

    @Override
    public void draw(Graphics g) {
        move();
        g.drawImage(image1,getX(),
                getY(),
                image1.getWidth(null),
                image1.getHeight(null),null);
        g.drawImage(image2,250,470,image2.getWidth(null) * 2,
                image2.getHeight(null) * 2,null);
    }

    @Override
    public void move() {
        setY(getY() + speed);
    }

    public void mouseReleased(MouseEvent e) {
        if (e.getX() >= 250 && e.getX() <= 250 + ImageMap.get("bgbutten").getWidth(null)&&
            e.getY() >= 470 && e.getX() <= 470 + ImageMap.get("bgbutten").getHeight(null)
        ){
            start = true;

        }
    }
}
