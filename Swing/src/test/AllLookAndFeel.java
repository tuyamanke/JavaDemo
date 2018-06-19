package test;

import javax.swing.*;

/**
 * Created by wen on 2018/6/19
 */
public class AllLookAndFeel {
    public static void main(String[] args) {
        System.out.println("当前系统可用的所有Look And Feel：");
        for (UIManager.LookAndFeelInfo info : UIManager.getInstalledLookAndFeels()){
            System.out.println(info.getName() + "--->" + info);
        }
    }
}
