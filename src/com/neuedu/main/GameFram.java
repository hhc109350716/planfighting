package com.neuedu.main;

import com.neuedu.constant.FrameConstant;
import com.neuedu.runtime.*;
import com.neuedu.util.ImageMap;

import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

public class GameFram extends Frame {

    public boolean gameover = false;
    //创建背景对象
    private Background background = new Background();

    //创建我方飞机对象
    private MyPlane myPlane = new MyPlane();

    //创建我方子弹对象
    private List<MyBullet> bulletList = new CopyOnWriteArrayList<>();

    //创建敌方飞机对象
    private List<EnemyPlane> enemyPlanesList = new CopyOnWriteArrayList<>();

    //创建敌方子弹对象
    private List<EnemyBullet> enemyBulletList = new CopyOnWriteArrayList<>();

    public final List<MyBullet> getBulletList() {
        return bulletList;
    }
    public final List<EnemyPlane> getEnemyPlanesList(){
        return enemyPlanesList;
    }
    public final List<EnemyBullet> getEnemyBulletList(){ return enemyBulletList; }

    //传入画笔
    @Override
    public void paint(Graphics g) {
        if (!gameover){
            background.draw(g);
            myPlane.draw(g);
            for (MyBullet bullet : bulletList) {
                bullet.draw(g);
                bullet.collisionTesting(enemyPlanesList);
                bullet.bulletcollisionTesting(enemyBulletList);
            }
            for (EnemyPlane enemyPlane : enemyPlanesList) {
                enemyPlane.draw(g);
                enemyPlane.collisionTesting(myPlane);
            }
            for (EnemyBullet enemyBullet : enemyBulletList) {
                enemyBullet.draw(g);
                enemyBullet.collisionTesting(myPlane);
            }

            /////////////////////////////////////
//            g.setColor(Color.red);
//            g.drawString(""+ bulletList.size(),200,200);
//            g.drawString(""+ enemyPlanesList.size(),200,100);
        }

    }

    /**
     * 使用init方法初始化窗口
     */
    public void init(){
        //窗口大小
        setSize(FrameConstant.FRAME_WIDTH,FrameConstant.FRAME_HEIGHT);

        //设置居中
        setLocationRelativeTo(null);

        //打开窗口禁用输入法
        enableInputMethods(false);

        //固定窗口大小
        setResizable(false);

        //可关闭窗口
        addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                System.exit(0);
            }
        });

        //窗口可见
        setVisible(true);

        //按键监听
        addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                myPlane.keyPressed(e);
            }

            @Override
            public void keyReleased(KeyEvent e) {
                myPlane.keyReleased(e);
            }
        });
        new Thread(){
            @Override
            public void run() {
                while (true) {
                    repaint();
                    try {
                        Thread.sleep(10);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }

            }
        }.start();

        //添加敌方飞机
        enemyPlanesList.add(new EnemyPlane(100,0, ImageMap.get("ep1")));
        enemyPlanesList.add(new EnemyPlane(300,0, ImageMap.get("ep1")));
        enemyPlanesList.add(new EnemyPlane(500,0, ImageMap.get("ep1")));
        enemyPlanesList.add(new EnemyPlane(680,0, ImageMap.get("ep1")));
    }



    private Image offScreenImage = null;// 创建缓冲区

    @Override
    public void update (Graphics g) {

        if (offScreenImage ==null) {

            offScreenImage = this.createImage(FrameConstant.FRAME_WIDTH, FrameConstant.FRAME_HEIGHT);
        }
            Graphics goff = offScreenImage . getGraphics() ;//创建离线图片的实例，在图片缓冲区绘图
            paint (goff);
            g. drawImage (offScreenImage, 0, 0, null) ;//将缓冲图片绘制到窗口目标

        }
}
