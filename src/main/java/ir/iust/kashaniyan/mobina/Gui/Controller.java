package ir.iust.kashaniyan.mobina.Gui;


import ir.iust.kashaniyan.mobina.Logics.Queue1;
import ir.iust.kashaniyan.mobina.Logics.Queue2;
import ir.iust.kashaniyan.mobina.Logics.Queue3;
import ir.iust.kashaniyan.mobina.models.Task;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;



@RestController
public class Controller {

    @Autowired
    Queue1 queue1;

    @Autowired
     Queue2 queue2;

    @Autowired
    Queue3 queue3;

    List<Task> tasks1 = new ArrayList<>();
    List<Task> tasks2 = new ArrayList<>();
    List<Task> tasks3 = new ArrayList<>();
    public static List<StepsDTO> stepsDTOS = new ArrayList<>();


    @GetMapping("/api/v1/QueueSimulation")
    public List<ResponseDTO> test() {
        List<ResponseDTO> responseDTOS = new ArrayList<>();
        queue1.initializeSystemParameters();
        tasks1 = queue1.run();
        ResponseDTO firstQ = queue1.responseDTO();
        responseDTOS.add(firstQ);

        queue2.initializeSystemParameters(tasks1, 0.4);
        tasks2 = queue2.run();
        double resp = measureResponseTime(tasks1, tasks2, 0.4);
        ResponseDTO secondQ = queue2.responseDTO();
        secondQ.setResponseTime(resp);
        responseDTOS.add(secondQ);

        queue3.initializeSystemParameters(tasks1, 0.6);
        tasks3 = queue3.run();
        System.out.println(tasks3.size());
        double response = measureResponseTime(tasks1, tasks2, 0.6);
        ResponseDTO responseDTO = queue3.responseDTO();
        responseDTO.setResponseTime(response);
        responseDTOS.add(responseDTO);
        return responseDTOS;

    }

    @GetMapping("/api/v1/Steps")
    public List<StepsDTO> steps() {

        for (StepsDTO st : queue1.stepsDTOS) {
            stepsDTOS.add(st);
        }
        for (StepsDTO st : queue2.stepsDTOS) {
            stepsDTOS.add(st);
        }
        for (StepsDTO st : queue3.stepsDTOS) {
            stepsDTOS.add(st);
        }
        return stepsDTOS;

    }

    public static double measureResponseTime(List<Task> tasks1, List<Task> tasks2, double probability) {
        double time = 0.0;
        for (int i = 0; i < tasks1.size(); i++) {
            if (tasks1.get(i).getProbability() < 0.6)
                time += (tasks1.get(i).getFirstQueueExitTime() - tasks1.get(i).getTimesOfArrival());
            if (tasks1.get(i).getProbability() > 0.6)
                time += (tasks2.get(i).getSecondQueueExitTime() - tasks1.get(i).getTimesOfArrival());
        }
        System.out.println("Total ResponseTime:");
        System.out.println((time) / tasks1.size());
        return (time) / tasks1.size();
    }

    @GetMapping("/api/v1/show")
    public static void showui(){
        swinggui swinggui= new swinggui();
        swinggui.swingg_ui(stepsDTOS);
    }

}

