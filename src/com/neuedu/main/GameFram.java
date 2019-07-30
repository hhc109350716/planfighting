package com.neuedu.main;

import com.neuedu.constant.FrameConstant;
import com.neuedu.runtime.Background;

import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class GameFram extends Frame {
    private Background background = new Background();
    @Override
    public void paint(Graphics g) {
    background.draw(g);
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

        //可关闭窗口
        addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                System.exit(0);
            }
        });

        //窗口可见
        setVisible(true);

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
