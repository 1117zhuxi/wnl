import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import javax.swing.*;

class awt{
    JFrame jf = new JFrame();
    Container con = jf.getContentPane();
    JPanel p_mid = new JPanel();
    JPanel pTime = new JPanel();
    JPanel pTime2 = new JPanel();
    JComboBox<String> box1 = new JComboBox<String>();
    JComboBox<String> box2 = new JComboBox<String>();
    JComboBox<String> box3 = new JComboBox<String>();
    String year, month, day;                            //记录年、月、日
    Calendar ca = Calendar.getInstance();
    public awt() {
        //基本设置
        jf.setVisible(true);
        jf.setLocation(300, 300);
        jf.setDefaultCloseOperation(jf.EXIT_ON_CLOSE);
        jf.setLayout(new BorderLayout());
        jf.setResizable(false);
        p_mid.setLayout(new GridLayout(7, 7, 1, 1));
        //调用方法
        setTime();
        week();
        Day();
        //自适应窗口大小
        jf.pack();
    }

    //下拉列表框监听器
    public class action1 implements ActionListener {
        JComboBox<String> bool;
        @Override
        public void actionPerformed(ActionEvent e) {
            bool = (JComboBox<String>)e.getSource();
            year = (String)bool.getSelectedItem();
            combox();
        }
        public void combox() {
            if(!year.equals("--年份--")) {
                String str[] = new String[13];
                str[0] = "--月份--";
                for(int i=1; i<=12; i++) {
                    str[i] = Integer.toString(i);
                }
                box2.setModel(new DefaultComboBoxModel<String>(str));
            }
        }
    }
    class action2 implements ActionListener{
        JComboBox<String> bool;
        String str[];
        @Override
        public void actionPerformed(ActionEvent e) {
            bool = (JComboBox<String>)e.getSource();
            month = (String)bool.getSelectedItem();
            combox();
        }
        public void combox() {
            if(!month.equals("--月份--") && (month.matches("[13578]") || month.equals("10") || month.equals("12"))) {
                str = new String[32];
                str[0] = "--日期--";
                for(int i=1; i<=31; i++) {
                    str[i] = Integer.toString(i);
                }
                box3.setModel(new DefaultComboBoxModel<String>(str));
            }else if(!month.equals("--月份--") && (month.matches("[469]") || month.equals("11"))){
                str = new String[31];
                str[0] = "--日期--";
                for(int i=1; i<=30; i++) {
                    str[i] = Integer.toString(i);
                }
                box3.setModel(new DefaultComboBoxModel<String>(str));
            }else if(!month.equals("--月份--") && month.equals("2")) {
                //判断为闰年还是平年
                if(Integer.parseInt(year)%4==0 && Integer.parseInt(year)%100!=0) {
                    //进入此循环，则为闰年，2月份有29天
                    str = new String[30];
                    str[0] = "--日期--";
                    for(int i=1; i<=29; i++) {
                        str[i] = Integer.toString(i);
                    }
                    box3.setModel(new DefaultComboBoxModel<String>(str));
                }else {
                    //进入此循环，则为平年，2月份有28天
                    str = new String[29];
                    str[0] = "--日期--";
                    for(int i=1; i<=28; i++) {
                        str[i] = Integer.toString(i);
                    }
                    box3.setModel(new DefaultComboBoxModel<String>(str));
                }
            }
        }
    }
    class action3 implements ActionListener{
        JComboBox<String> bool;
        @Override
        public void actionPerformed(ActionEvent e) {
            bool = (JComboBox<String>)e.getSource();
            day = (String)bool.getSelectedItem();
        }

    }
    class action4 extends JComponent implements ActionListener{
        @Override
        public void actionPerformed(ActionEvent e) {
            //实现方法
            if(!year.equals("--年份--") && !month.equals("--月份--") && !day.equals("--日期--")) {
                //移除组件
                con.remove(p_mid);
                p_mid.removeAll();
                //实现方法
                Week_new();
                Day_new();
            }
        }
        //新的星期组件
        public void Week_new() {
            JButton lab1 = new JButton("一");
            JButton lab2 = new JButton("二");
            JButton lab3 = new JButton("三");
            JButton lab4 = new JButton("四");
            JButton lab5 = new JButton("五");
            JButton lab6 = new JButton("六");
            JButton lab7 = new JButton("七");
            lab1.setFont(new Font("微软雅黑", Font.BOLD, 15));
            lab2.setFont(new Font("微软雅黑", Font.BOLD, 15));
            lab3.setFont(new Font("微软雅黑", Font.BOLD, 15));
            lab4.setFont(new Font("微软雅黑", Font.BOLD, 15));
            lab5.setFont(new Font("微软雅黑", Font.BOLD, 15));
            lab6.setFont(new Font("微软雅黑", Font.BOLD, 15));
            lab7.setFont(new Font("微软雅黑", Font.BOLD, 15));
            p_mid.add(lab1);
            p_mid.add(lab2);
            p_mid.add(lab3);
            p_mid.add(lab4);
            p_mid.add(lab5);
            p_mid.add(lab6);
            p_mid.add(lab7);
        }
        //新的天数组件
        public void Day_new() {
            int temp=1;
            ca.set(Integer.parseInt(year), Integer.parseInt(month)-1, Integer.parseInt(day));
            int Month_max = ca.getActualMaximum(Calendar.DATE);
            int day = ca.get(Calendar.DATE);
            ca.set(Calendar.DATE, 1);
            int Week = ca.get(Calendar.DAY_OF_WEEK)-1;
            if(Week == 0) {
                Week = 7;
            }
            for(int i=1; i<=42; i++) {
                if(i>=Week && i<Week+Month_max) {
                    if(temp == day) {
                        p_mid.add(new Button("#"+temp));
                        temp++;
                        continue;
                    }
                    p_mid.add(new Button(Integer.toString(temp)));
                    temp++;
                    continue;
                }
                p_mid.add(new Button());
            }
            con.add(p_mid, BorderLayout.CENTER);
            con.validate();
        }
    }
    //------//
    public void setTime() {
        //声明变量
        JLabel labTime = new JLabel();
        JButton button = new JButton("查询");
        box1.addActionListener(new action1());
        box2.addActionListener(new action2());
        box3.addActionListener(new action3());
        button.addActionListener(new action4());
        Date d = new Date();
        SimpleDateFormat sdf = new SimpleDateFormat("YYYY-MM-dd");
        String time = sdf.format(d);
        labTime.setText("北京时间:"+time);
        labTime.setFont(new Font("微软雅黑", Font.BOLD, 15));
        box1.addItem("--年份--");
        box1.addItem("2016");
        box1.addItem("2017");
        box1.addItem("2018");
        box1.addItem("2019");
        box2.addItem("--月份--");
        box3.addItem("--日期--");
        pTime.setLayout(new FlowLayout(FlowLayout.LEFT,20,5));
        pTime2.setLayout(new FlowLayout(FlowLayout.CENTER,1,0));
        pTime.add(labTime);
        pTime2.add(box1);
        pTime2.add(box2);
        pTime2.add(box3);
        pTime2.add(button);
        pTime.add(pTime2);
        con.add(pTime, BorderLayout.NORTH);
    }
    public void week() {
        JButton lab1 = new JButton("一");
        JButton lab2 = new JButton("二");
        JButton lab3 = new JButton("三");
        JButton lab4 = new JButton("四");
        JButton lab5 = new JButton("五");
        JButton lab6 = new JButton("六");
        JButton lab7 = new JButton("七");
        lab1.setFont(new Font("微软雅黑", Font.BOLD, 15));
        lab2.setFont(new Font("微软雅黑", Font.BOLD, 15));
        lab3.setFont(new Font("微软雅黑", Font.BOLD, 15));
        lab4.setFont(new Font("微软雅黑", Font.BOLD, 15));
        lab5.setFont(new Font("微软雅黑", Font.BOLD, 15));
        lab6.setFont(new Font("微软雅黑", Font.BOLD, 15));
        lab7.setFont(new Font("微软雅黑", Font.BOLD, 15));
        p_mid.add(lab1);
        p_mid.add(lab2);
        p_mid.add(lab3);
        p_mid.add(lab4);
        p_mid.add(lab5);
        p_mid.add(lab6);
        p_mid.add(lab7);
    }
    public void Day() {
        int temp=1;
        int Month_max = ca.getActualMaximum(Calendar.DATE);    //当前月有几天
        int day = ca.get(Calendar.DATE);                       //当前日期
        ca.set(Calendar.DATE, 1);
        int Week = ca.get(Calendar.DAY_OF_WEEK)-1;             //星期几
        if(Week == 0) {
            Week = 7;
        }
        for(int i=1; i<=42; i++) {
            Button b = new Button();
            if(i>=Week && i<Week+Month_max) {
                if(temp == day) {
                    p_mid.add(new Button("#"+temp));
                    temp++;
                    continue;
                }
                p_mid.add(new Button(Integer.toString(temp)));
                temp++;
                continue;
            }
            p_mid.add(new Button());
        }
        con.add(p_mid, BorderLayout.CENTER);
    }
}

