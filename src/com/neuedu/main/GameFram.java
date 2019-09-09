package com.neuedu.main;

import com.neuedu.constant.FrameConstant;
import com.neuedu.runtime.*;
import com.neuedu.util.ImageMap;

import java.awt.*;
import java.awt.event.*;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

public class GameFram extends Frame {

    public int gameover = 1;
    //创建开始对象
    private Start playgame = new Start();

    //创建背景对象
    private Background background = new Background();

    //创建我方飞机对象
    private MyPlane myPlane = new MyPlane();

    //创建Boss对象
    private Boss boss = new Boss();

    //创建道具对象
    private Prop1 prop1 = new Prop1();


    //创建我方子弹对象
    private List<MyBullet> bulletList = new CopyOnWriteArrayList<>();

    //创建我方大子弹对象
    private List<MyBigBullet> bigBulletList = new CopyOnWriteArrayList<>();

    //创建敌方飞机对象
    private List<EnemyPlane> enemyPlanesList = new CopyOnWriteArrayList<>();

    //创建敌方子弹对象
    private List<EnemyBullet> enemyBulletList = new CopyOnWriteArrayList<>();

    //创建boss子弹对象
    private List<BossBullet> bossBulletList = new CopyOnWriteArrayList<>();

    //创建道具集合2
    private List<Prop2> prop2List = new CopyOnWriteArrayList<>();

    //创建道具集合3
    private List<Prop3> prop3List = new CopyOnWriteArrayList<>();


    //成功图片
    private Image imagewin = ImageMap.get("win");

    //失败图片
    private Image imagelose = ImageMap.get("lose");


    //控制大招
    public int scout;

    //开始游戏
    public boolean start;


    public final List<MyBullet> getBulletList() {
        return bulletList;
    }

    public final List<EnemyPlane> getEnemyPlanesList() {
        return enemyPlanesList;
    }

    public final List<EnemyBullet> getEnemyBulletList() {
        return enemyBulletList;
    }

    public final List<MyBigBullet> getBigBulletList() {
        return bigBulletList;
    }

    public final List<BossBullet> getBossBulletList() {
        return bossBulletList;
    }

    public final List<Prop2> getProp2List() {
        return prop2List;
    }

    public final List<Prop3> getProp3List() {
        return prop3List;
    }


    //传入画笔
    @Override
    public void paint(Graphics g) {
        start = playgame.start;
        if (!start) {
            playgame.draw(g);
        } else {
            if (gameover == 1) {
                background.draw(g);


                if (myPlane.myHp < 50) {
                    prop1.draw(g);
                    prop1.collisionTesting(myPlane);

                }

                for (Prop2 prop2 : prop2List) {
                    prop2.draw(g);
                    prop2.collisionTesting(myPlane);
                }
                for (Prop3 prop3 : prop3List) {
                    prop3.draw(g);
                    prop3.collisionTesting(myPlane);
                }

                if (scout > 200 || getX() == 5000) {
                    boss.draw(g);
                    boss.collisionTesting(myPlane);
                }

                for (BossBullet bossBullet : bossBulletList) {
                    bossBullet.draw(g);
                    bossBullet.collisionTesting(myPlane);
                }

                for (EnemyPlane enemyPlane : enemyPlanesList) {
                    enemyPlane.draw(g);
                    enemyPlane.collisionTesting(myPlane);

                }
                for (MyBullet bullet : bulletList) {
                    bullet.draw(g);
                    bullet.collisionTesting(enemyPlanesList);
                    bullet.bulletcollisionTesting(enemyBulletList);
                    if (scout > 200 || getX() == 5000) {
                        bullet.collisionTesting1(boss);
                    }
                }
                for (MyBigBullet myBigBullet : bigBulletList) {
                    myBigBullet.draw(g);
                    myBigBullet.collisionTesting2(boss);
                    myBigBullet.collisionTesting(enemyPlanesList);
                    myBigBullet.collisionTesting1(enemyBulletList);
                }
                for (EnemyBullet enemyBullet : enemyBulletList) {
                    enemyBullet.draw(g);
                    enemyBullet.collisionTesting(myPlane);
                }
                myPlane.draw(g);
                g.setFont(new Font("楷体",0,36));
                g.setColor(new Color(255, 55, 146));
                g.drawString("得分:"+ scout,40,140);
                //画血量
                Toolkit toolkit = Toolkit.getDefaultToolkit();
                g.setColor(Color.red);
                /* g.setFont(new Font("楷体",Font.BOLD,20));*/
                g.drawRect(150, 200, 100, 20);
                g.fillRect(150, 200, myPlane.myHp, 20);
                g.drawString("血量:",50,220);
            } else if (gameover == 2) {
                g.drawImage(imagelose, 250, 400,
                        imagelose.getWidth(null),
                        imagelose.getHeight(null), null);

            } else if (gameover == 3) {
                g.drawImage(imagewin, 250, 400,
                        imagewin.getWidth(null),
                        imagewin.getHeight(null), null);

            }
        }
    }

    public void init() {

        //设置好窗口尺寸
        setSize(FrameConstant.FRAME_WIDTH, FrameConstant.FRAME_HEIGHT);

        setTitle("全民飞机大战");

        //设置窗口居中
        setLocationRelativeTo(null);

        //关闭输入法
        enableInputMethods(false);

        //不可改变窗口大小
        setResizable(false);

        //添加窗口监听
        addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                System.exit(0);
            }
        });

        //添加键盘监听
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
        //添加键盘监听
        addMouseListener(new MouseAdapter(){

            @Override
            public void mouseReleased(MouseEvent e) {
                playgame.mouseReleased(e);
            }
        });

        new Thread() {

            @Override
            public void run() {
                while (true) {
                    repaint();
                    try {
                        Thread.sleep(7);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        }.start();

        //添加子弹道具
        enemyPlanesList.add(new EnemyPlane(200, 30, 1));
        enemyPlanesList.add(new EnemyPlane(100, 60, 1));
        enemyPlanesList.add(new EnemyPlane(500, -110, 1));
        //添加子弹道具
        prop2List.add(new Prop2(460, -150));
        enemyPlanesList.add(new EnemyPlane(400, -230, 1));
        enemyPlanesList.add(new EnemyPlane(350, -350, 1));
        //添加子弹道具
        prop3List.add(new Prop3(50, -400));

        enemyPlanesList.add(new EnemyPlane(240, -440, 1));
        enemyPlanesList.add(new EnemyPlane(670, -568, 1));
        enemyPlanesList.add(new EnemyPlane(560, -690, 1));
        enemyPlanesList.add(new EnemyPlane(720, -820, 1));
        //添加子弹道具
        prop2List.add(new Prop2(460, -900));
        enemyPlanesList.add(new EnemyPlane(220, -929, 1));
        enemyPlanesList.add(new EnemyPlane(450, -1044, 1));
        enemyPlanesList.add(new EnemyPlane(600, -1165, 1));
        enemyPlanesList.add(new EnemyPlane(358, -1349, 1));
        //添加子弹道具
        prop3List.add(new Prop3(50, -1400));
        enemyPlanesList.add(new EnemyPlane(60, -1599, 1));
        enemyPlanesList.add(new EnemyPlane(500, -1700, 1));

        //添加子弹道具
        prop3List.add(new Prop3(50, -1620));

        enemyPlanesList.add(new EnemyPlane(550, -1790, 1));
        enemyPlanesList.add(new EnemyPlane(220, -1900, 1));

        enemyPlanesList.add(new EnemyPlane(400, -1900, 2));
        enemyPlanesList.add(new EnemyPlane(110, -2689, 2));
        //添加子弹道具
        prop3List.add(new Prop3(50, -2800));

        enemyPlanesList.add(new EnemyPlane(670, -2993, 2));
        enemyPlanesList.add(new EnemyPlane(30, -3366, 2));
        //添加道具
        prop2List.add(new Prop2(460, -3400));

        enemyPlanesList.add(new EnemyPlane(660, -3580, 2));
        enemyPlanesList.add(new EnemyPlane(220, -3900, 2));

        //添加子弹道具
        prop3List.add(new Prop3(50, -3800));

        enemyPlanesList.add(new EnemyPlane(400, -3900, 3));
        enemyPlanesList.add(new EnemyPlane(110, -4689, 3));
        //添加道具
        prop2List.add(new Prop2(460, -3400));

        enemyPlanesList.add(new EnemyPlane(670, -4993, 3));
        enemyPlanesList.add(new EnemyPlane(30, -5366, 3));
        enemyPlanesList.add(new EnemyPlane(660, -6580, 3));
        enemyPlanesList.add(new EnemyPlane(220, -7900, 3));
        setVisible(true);
    }


    /**
     * 双缓冲
     *
     * @param g
     */
    private Image offScreenImage = null;// 创建缓冲区

    @Override
    public void update(Graphics g) {

        if (offScreenImage == null) {

            offScreenImage = this.createImage(FrameConstant.FRAME_WIDTH, FrameConstant.FRAME_HEIGHT);
        }
        Graphics goff = offScreenImage.getGraphics();//创建离线图片的实例，在图片缓冲区绘图
        paint(goff);
        g.drawImage(offScreenImage, 0, 0, null);//将缓冲图片绘制到窗口目标
    }
}