package test;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;
import java.awt.event.InputEvent;

/**
 * Created by wen on 2018/6/19
 */
public class SwingComponent {
    JFrame jFrame = new JFrame("测试");

    //定义一个按钮，并为之指定图标
    Icon okIcon = new ImageIcon("icon/ok.png");
    JButton okJButton = new JButton("确认", okIcon);

    //定义一个单选按钮，初始处于选中状态
    JRadioButton maleJRadioButton = new JRadioButton("男", true);
    //定义一个单选按钮，初始处于未选中状态
    JRadioButton femaleJRadioButton = new JRadioButton("女", false);
    //定义一个ButtonGroup，用于将上面两个JRadioButton组合在一起
    ButtonGroup buttonGroup = new ButtonGroup();

    //定义一个复选框，初始处于未选中状态
    JCheckBox marriedJCheckBox = new JCheckBox("是否已婚？", false);

    String[] colors = new String[]{"红色", "绿色", "蓝色"};
    //定义一个下拉选择框
    JComboBox<String> colorChooserJComboBox = new JComboBox<>(colors);
    //定义一个列表选择框
    JList<String> colorList = new JList<>(colors);

    //定义一个8行 20列的多行文本域
    JTextArea jTextArea = new JTextArea(8, 20);

    //定义一个40列的单行文本域
    JTextField nameJTextField = new JTextField(40);

    JMenuBar jMenuBar = new JMenuBar();
    JMenu fileJMenu = new JMenu("文件");
    JMenu editJMenu = new JMenu("编辑");

    //创建【新建】菜单项，并为之指定图标
    Icon newIcon = new ImageIcon("icon/new.png");
    JMenuItem newItem = new JMenuItem("新建", newIcon);
    //创建【保存】菜单项，并为之指定图标
    Icon saveIcon = new ImageIcon("icon/save.png");
    JMenuItem saveItem = new JMenuItem("保存", saveIcon);
    //创建【退出】菜单项，并为之指定图标
    Icon exitIcon = new ImageIcon("icon/exit.png");
    JMenuItem exitItem = new JMenuItem("退出", exitIcon);
    //创建【复制】菜单项，并为之指定图标
    Icon copyIcon = new ImageIcon("icon/copy.png");
    JMenuItem copyItem = new JMenuItem("复制", copyIcon);
    //创建【粘贴】菜单项，并为之指定图标
    Icon pasteIcon = new ImageIcon("icon/paste.png");
    JMenuItem pasteItem = new JMenuItem("粘贴", pasteIcon);
    //创建【注释】菜单项
    JMenuItem commentItem = new JMenuItem("注释");
    //创建【取消注释】菜单项
    JMenuItem cancelItem = new JMenuItem("取消注释");
    JCheckBoxMenuItem autoWrap = new JCheckBoxMenuItem("自动换行");
    JMenu formatJMenu = new JMenu("格式");

    //定义一个右键菜单，用于设置程序风格
    JPopupMenu jPopupMenu = new JPopupMenu();

    //用于组合3个风格菜单项的ButtonGroup
    ButtonGroup flavorButtonGroup = new ButtonGroup();
    //创建5个单选按钮，用于设定程序的外观风格
    JRadioButtonMenuItem metalJRadioButtonMenuItem = new JRadioButtonMenuItem("Metal风格", true);
    JRadioButtonMenuItem nimbusJRadioButtonMenuItem = new JRadioButtonMenuItem("Nimbus风格");
    JRadioButtonMenuItem windowsJRadioButtonMenuItem = new JRadioButtonMenuItem("Windows风格");
    JRadioButtonMenuItem windowsClassicJRadioButtonMenuItem = new JRadioButtonMenuItem("Windows经典风格");
    JRadioButtonMenuItem motifButtonMenuItem = new JRadioButtonMenuItem("Motif风格");

    //--------------------- 用于执行初始化的init方法 ---------------------
    public void init(){

        //创建一个装载了文本框、按钮的JPanel
        JPanel bottom = new JPanel();
        bottom.add(nameJTextField);
        bottom.add(okJButton);
        jFrame.add(bottom, BorderLayout.SOUTH);

        //创建一个装载了下拉选择框、三个JCheckBox的JPanel
        JPanel checkJPanel = new JPanel();
        checkJPanel.add(colorChooserJComboBox);
        buttonGroup.add(maleJRadioButton);
        buttonGroup.add(femaleJRadioButton);
        checkJPanel.add(maleJRadioButton);
        checkJPanel.add(femaleJRadioButton);

        //创建一个垂直排列组件的Box，盛装多行文本域JPanel
        Box topLeft = Box.createVerticalBox();
        //使用JScrollPane作为普通组件的JViewPort
        JScrollPane jTextAreaJScrollPane = new JScrollPane(jTextArea);
        topLeft.add(jTextAreaJScrollPane);
        topLeft.add(checkJPanel);
        //创建一个水平排列组件的Box，盛装topLeft、colorList
        Box top = Box.createHorizontalBox();
        top.add(topLeft);
        top.add(colorList);
        //将top Box容器添加到窗口的中间
        jFrame.add(top);
        //--------------------- 下面开始组合菜单，并为菜单添加监听器 ---------------------
        //为newItem设置快捷键，设置快捷键时要使用大写字母
        newItem.setAccelerator(KeyStroke.getKeyStroke('N', InputEvent.CTRL_MASK));
        newItem.addActionListener(e -> jTextArea.append("用户单击了【新建】菜单\n"));

        //为fileJMenu菜单添加菜单项
        fileJMenu.add(newItem);
        fileJMenu.add(saveItem);
        fileJMenu.add(exitItem);

        //为edit菜单添加菜单项
        editJMenu.add(autoWrap);
        //使用addSeparator()方法添加菜单分割线
        editJMenu.addSeparator();
        editJMenu.add(copyItem);
        editJMenu.add(pasteItem);
        //为format菜单添加菜单项
        formatJMenu.add(commentItem);
        formatJMenu.add(cancelItem);
        //使用添加new JMenuItem("-")的方式不能添加菜单分隔符
        editJMenu.add(new JMenuItem("-"));
        //将formatJMenu菜单那组合到edit菜单中，从而形成二级菜单
        editJMenu.add(formatJMenu);

        //将fileJMenu、editJMenu菜单添加到jMenuBar菜单条中
        jMenuBar.add(fileJMenu);
        jMenuBar.add(editJMenu);

        //为jFrame窗口设置菜单条
        jFrame.setJMenuBar(jMenuBar);

        //为componentItem组件添加提示信息
        commentItem.setToolTipText("将程序代码注释起来！");

        //--------------------- 下面开始组合右键菜单，并安装右键菜单 ---------------------
        flavorButtonGroup.add(metalJRadioButtonMenuItem);
        flavorButtonGroup.add(nimbusJRadioButtonMenuItem);
        flavorButtonGroup.add(windowsJRadioButtonMenuItem);
        flavorButtonGroup.add(windowsClassicJRadioButtonMenuItem);
        flavorButtonGroup.add(motifButtonMenuItem);
        jPopupMenu.add(metalJRadioButtonMenuItem);
        jPopupMenu.add(nimbusJRadioButtonMenuItem);
        jPopupMenu.add(windowsJRadioButtonMenuItem);
        jPopupMenu.add(windowsClassicJRadioButtonMenuItem);
        jPopupMenu.add(motifButtonMenuItem);

        //为5个风格菜单创建事件监听器
        ActionListener flavorListener = e -> {
            try {
                switch (e.getActionCommand()){
                    case "Metal风格":
                        changeFlavor(1);
                        break;
                    case "Nimbus风格":
                        changeFlavor(2);
                        break;
                    case "Windows风格":
                        changeFlavor(3);
                        break;
                    case "Windows经典风格":
                        changeFlavor(4);
                        break;
                    case "Motif风格":
                        changeFlavor(5);
                        break;
                }
            } catch (Exception e1) {
                e1.printStackTrace();
            }
        };
        //为5个风格菜单项添加事件监听器
        metalJRadioButtonMenuItem.addActionListener(flavorListener);
        nimbusJRadioButtonMenuItem.addActionListener(flavorListener);
        windowsJRadioButtonMenuItem.addActionListener(flavorListener);
        windowsClassicJRadioButtonMenuItem.addActionListener(flavorListener);
        motifButtonMenuItem.addActionListener(flavorListener);

        //调用该方法即可设置右键菜单，无需使用事件机制
        jTextArea.setComponentPopupMenu(jPopupMenu);
        jFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        jFrame.pack();
        jFrame.setVisible(true);

    }

    //定义一个方法，用于改变界面风格
    private void changeFlavor(int flavor) throws Exception {
         switch (flavor){
                //设置Metal风格
             case 1:
                 UIManager.setLookAndFeel("javax.swing.plaf.metal.MetalLookAndFeel");
                 break;
                 //设置Nimbus风格
             case 2:
                 UIManager.setLookAndFeel("javax.swing.plaf.nimbus.NimbusLookAndFeel");
                 break;
                 //设置Windows风格
             case 3:
                 UIManager.setLookAndFeel("com.sun.java.swing.plaf.windows.WindowsLookAndFeel");
                 break;
                 //设置Windows经典风格
             case 4:
                 UIManager.setLookAndFeel("com.sun.java.swing.plaf.windows.WindowsClassicLookAndFeel");
                 break;
                 //设置Motif风格
             case 5:
                 UIManager.setLookAndFeel("com.sun.java.swing.plaf.motif.MotifLookAndFeel");
         }
         //更新窗口内顶级容器以及内部所有组件的UI
        SwingUtilities.updateComponentTreeUI(jFrame.getContentPane());
         //更新jMenuBar菜单条以及内部所有组件的UI
        SwingUtilities.updateComponentTreeUI(jMenuBar);
        //更新pop右键菜单条以及内部所有组件的UI
        SwingUtilities.updateComponentTreeUI(jPopupMenu);
    }

    public static void main(String[] args) {
        //设置Swing窗口使用Java风格
        //JFrame.setDefaultLookAndFeelDecorated(true);
        new SwingComponent().init();
    }


}
