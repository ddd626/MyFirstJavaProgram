package com.company;

import  javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.Stack;

public class Calculator{
    //设置参数
    String str1 = "";
    boolean k_1 = true;           //判断是否能输入符号
    boolean k_2 = false, k_4 = true;          //判断是否能输入小数点
    boolean k_3 = true;           //判断是否可以清空

    //new
    JFrame frame = new JFrame("calculator");
    JTextField result = new JTextField(str1, 20);
    JButton clear = new JButton("clear");
    JButton button0 = new JButton("0");
    JButton button1 = new JButton("1");
    JButton button2 = new JButton("2");
    JButton button3 = new JButton("3");
    JButton button4 = new JButton("4");
    JButton button5 = new JButton("5");
    JButton button6 = new JButton("6");
    JButton button7 = new JButton("7");
    JButton button8 = new JButton("8");
    JButton button9 = new JButton("9");
    JButton plus = new JButton("+");
    JButton sub = new JButton("-");
    JButton multiply = new JButton("*");
    JButton divide = new JButton("/");
    JButton dot = new JButton(".");
    JButton equal = new JButton("=");

    public Calculator() {
        // 设置文本框为右对齐，使输入和结果都靠右显示
        result.setHorizontalAlignment(JTextField.RIGHT);

        //设置表盘
        JPanel panel1 = new JPanel();
        panel1.setLayout(new GridLayout(4, 4, 5, 5));
        panel1.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));
        panel1.add(button7);
        panel1.add(button8);
        panel1.add(button9);
        panel1.add(divide);
        panel1.add(button4);
        panel1.add(button5);
        panel1.add(button6);
        panel1.add(multiply);
        panel1.add(button1);
        panel1.add(button2);
        panel1.add(button3);
        panel1.add(sub);
        panel1.add(button0);
        panel1.add(dot);
        panel1.add(equal);
        panel1.add(plus);

        JPanel panel2 = new JPanel();
        panel2.setLayout(new BorderLayout());
        panel2.add(result, BorderLayout.WEST);
        panel2.add(clear, BorderLayout.EAST);


        //设置框架
        frame.setLocation(300,200);
        frame.setResizable(false);
        //frame.setSize(350, 200);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.getContentPane().setLayout(new BorderLayout());
        frame.getContentPane().add(panel2, BorderLayout.NORTH);
        frame.getContentPane().add(panel1, BorderLayout.CENTER);
        frame.pack();
        frame.setVisible(true);

            //设置数字内部监听类
            class num_Listener implements ActionListener {
                public void actionPerformed(ActionEvent e) {
                    String s = ((JButton) e.getSource()).getText();
                    if(k_3){
                        str1 = s;
                        k_3 = false;
                    }
                    else{
                        str1 = str1 + s;
                    }
                    result.setText(str1);
                    k_1 = true;
                    k_2 = true;
                }
            }

            //设置符号监听类
            class sign_Listener implements ActionListener {
                public void actionPerformed(ActionEvent e) {
                    if(k_1) {
                        String s = ((JButton) e.getSource()).getText();
                        if(k_3){
                            str1 = s;
                            k_3 = false;
                        }
                        else{
                            str1 = str1 + s;
                        }
                        result.setText(str1);
                        k_1 = false;
                        k_2 = false;
                        k_4 = true;
                    }
                }
            }

            //设置小数点监听类
            class dot_Listener implements ActionListener {
                public void actionPerformed(ActionEvent e) {
                    if(k_2 && k_4) {
                        String s = ((JButton) e.getSource()).getText();
                        str1 = str1 + s;
                        result.setText(str1);
                        k_1 = false;
                        k_2 = false;
                        k_4 = false;
                    }
                }
            }

            //设置清除监听类
            class clear_Listener implements ActionListener {
                public void actionPerformed(ActionEvent e) {
                    String str1 = "0";
                    k_1 = true;
                    k_2 = false;
                    k_3 = true;
                    k_4 = true;
                    result.setText(str1);
                }
            }

            //设置等于监听类
            class equal_Listener implements ActionListener {
                public void actionPerformed(ActionEvent e) {
                    String s = String.valueOf(cal(str1));
                    result.setText(s);
                    k_1 = true;
                    k_2 = false;
                    k_3 = true;
                    k_4 = true;
                }
            }

        //初始化监听键
        num_Listener num_listener = new num_Listener();
        sign_Listener sign_listener = new sign_Listener();
        dot_Listener dot_listener = new dot_Listener();
        equal_Listener equal_listener = new equal_Listener();
        clear_Listener clear_listener = new clear_Listener();

        //注册监听键
        button0.addActionListener(num_listener);
        button1.addActionListener(num_listener);
        button2.addActionListener(num_listener);
        button3.addActionListener(num_listener);
        button4.addActionListener(num_listener);
        button5.addActionListener(num_listener);
        button6.addActionListener(num_listener);
        button7.addActionListener(num_listener);
        button8.addActionListener(num_listener);
        button9.addActionListener(num_listener);
        plus.addActionListener(sign_listener);
        divide.addActionListener(sign_listener);
        multiply.addActionListener(sign_listener);
        sub.addActionListener(sign_listener);
        dot.addActionListener(dot_listener);
        clear.addActionListener(clear_listener);
        equal.addActionListener(equal_listener);
        }

        //计算器
        public static double cal(String str){
        double[] num = new double[20];
        Stack<Character> st = new Stack<>();
        int begin = 0, end , index = 0;
        double flag = 1;

        for (int i = 0; i < str.length(); i++) {
            char s = str.charAt(i);
            if (s == '-') {
                if (i == 0 || str.charAt(i - 1) == '*' || str.charAt(i - 1) == '/') {
                    flag = -1;
                    begin = i + 1;
                    continue;
                }
            }

            if (i == str.length() - 1) {
                if (s == '+' || s == '-' || s == '*' || s == '/' || s == '.') {
                    System.out.println("false");
                    return 0;
                } else {
                    num[index] = flag * Double.parseDouble(str.substring(begin));
                    while (!st.empty()) {
                        num[index - 1] = compute(num[index - 1], num[index], st.pop());
                        index--;
                    }
                }
            }

            if (s == '+' || s == '-' || s == '*' || s == '/' ) {
                end = i;
                num[index] = flag * Double.parseDouble(str.substring(begin, end));
                flag = 1;
                begin = i + 1;
                while (!st.empty()){
                    if (priority(s) <= priority(st.peek())) {
                        num[index - 1] = compute(num[index - 1], num[index], st.pop());
                        index--;
                    }
                    else {
                        break;
                    }
                }
                index++;
                st.push(s);
            }
        }
        return(num[0]);
    }

    //计算单元
    public static double compute(double num1,double num2,char s){
        switch(s){
            case '-':
                return num1 - num2;
            case '+':
                return num1 + num2;
            case '*':
                return num1 * num2;
            case '/':
                return num1 / num2;
            default:
                return 0;
        }
    }

    //优先级
    public static int priority(char s){
        switch(s){
            case '-':
            case '+':
                return 0;

            case '*':
            case '/':
                return 1;

            default:
                return 0;
        }
    }


    public static void main(String[] args) {
        Calculator calculator = new Calculator();
    }
}





