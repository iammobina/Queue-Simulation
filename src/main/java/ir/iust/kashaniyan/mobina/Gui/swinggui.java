package ir.iust.kashaniyan.mobina.Gui;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;

class swinggui extends JFrame {


    public void swingg_ui(List<StepsDTO> stepsDTO) {
        List<StepsDTO> stepsDTOS = new ArrayList<>(stepsDTO);

        JLabel clock1,clock,A,D,numberServiced,Qt,TotalDelay;
        JLabel timeofLastEvent;
        JLabel arrivalTime;
        JLabel numberinQ;
        JLabel serverStatus;
        JLabel Bt;

        MyCanvas m = new MyCanvas();
        clock = new JLabel();
        clock1 = new JLabel();
        A = new JLabel();
        D = new JLabel();
        numberServiced = new JLabel();
        Qt = new JLabel();
        TotalDelay = new JLabel();
        timeofLastEvent = new JLabel();
        arrivalTime = new JLabel();
        numberinQ = new JLabel();
        serverStatus = new JLabel();
        Bt = new JLabel();


        clock.setBounds(100, 35, 40, 40);
        clock1.setBounds(580, 80, 40, 40);
        A.setBounds(790, 65, 30, 30);
        D.setBounds(790, 100, 30, 30);
        Qt.setBounds(755, 220, 40, 40);
        TotalDelay.setBounds(660, 220, 40, 40);
        numberServiced.setBounds(560, 220, 40, 40);
        timeofLastEvent.setBounds(450, 150, 40, 40);
        arrivalTime.setBounds(358, 105, 40, 30);
        numberinQ.setBounds(280, 150, 40, 40);
        serverStatus.setBounds(190, 160, 40, 40);
        Bt.setBounds(860, 220, 40, 40);

        add(clock);
        add(clock1);
        add(A);
        add(D);
        add(Qt);
        add(TotalDelay);
        add(numberServiced);
        add(arrivalTime);
        add(numberinQ);
        add(serverStatus);
        add(timeofLastEvent);
        add(Bt);
        add(m);
        setSize(1000, 450);


        for (int i = 0; i < stepsDTOS.size(); i++) {
            long start = System.currentTimeMillis();
            long end = start + 3 * 1000;
            while (System.currentTimeMillis() < end) {
                clock.setText(String.valueOf((stepsDTOS.get(i).getClock())));
                clock1.setText(String.valueOf((stepsDTOS.get(i).getClock())));
                A.setText(String.valueOf((stepsDTOS.get(i).getA())));
                D.setText(String.valueOf((stepsDTOS.get(i).getD())));
                Qt.setText(String.valueOf((stepsDTOS.get(i).getQt())));
                TotalDelay.setText(String.valueOf((stepsDTOS.get(i).getTotalDelay())));
                numberServiced.setText(String.valueOf((stepsDTOS.get(i).getNumberServiced())));
                arrivalTime.setText(String.valueOf((stepsDTOS.get(i).getArrivalTime())));
                timeofLastEvent.setText(String.valueOf((stepsDTOS.get(i).getTimeofLastEvent())));
                numberinQ.setText(String.valueOf((stepsDTOS.get(i).getNumberinQ())));
                serverStatus.setText(String.valueOf((stepsDTOS.get(i).getServerStatus())));
                Bt.setText(String.valueOf((stepsDTOS.get(i).getBt())));

                setVisible(true);
            }
        }
        setVisible(true);


    }
}

class MyCanvas extends Canvas {

    public void paint(Graphics g) {

        Toolkit t = Toolkit.getDefaultToolkit();
        Image i = t.getImage("E:\\projects\\QueueSimulation\\src\\main\\java\\ir\\iust\\kashaniyan\\mobina\\1.png");
        g.drawImage(i, 0, 0, this);

    }
}
