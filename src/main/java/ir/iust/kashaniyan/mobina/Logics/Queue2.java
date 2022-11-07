package ir.iust.kashaniyan.mobina.Logics;

import ir.iust.kashaniyan.mobina.Gui.ResponseDTO;
import ir.iust.kashaniyan.mobina.Gui.StepsDTO;
import ir.iust.kashaniyan.mobina.Initializer.ServicesInitializer;
import ir.iust.kashaniyan.mobina.SecondQueue.System2;
import ir.iust.kashaniyan.mobina.models.Task;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class Queue2 {

    @Autowired
    System2 system2;

    @Autowired
    ServicesInitializer servicesInitializer;

    private double clock;
    private double A;
    private double D;
    List<Task> tasksList = new ArrayList<>();
    public static List<StepsDTO> stepsDTOS = new ArrayList<>();

    private int tasksCounter = 0;
    private Task runningTask;
    private int numberServiced = 0;
    private double totalDelay = 0;

    Double Qt = 0.0;
    Double timeOfLastEvent = 0.0;
    List<Double> Es = new ArrayList<>();
    int arrivedcount = 1;
    private Double tmp;
    private double Wq;
    private double Bt;
    private double Lq;
    private double L ;
    private  double W ;
    private  double p ;

    public void initializeSystemParameters(List<Task> tasks, double probability) {
        tasksList = new ArrayList<>();
        tasksCounter = 0;
        numberServiced = 0;
        totalDelay = 0;
        Qt = 0.0;
        timeOfLastEvent = 0.0;
        Es = new ArrayList<>();
        arrivedcount = 1;

        List<Double> secondQueueServiceTime = servicesInitializer.secondQueueServiceTime();
        for (int i = 0; i < tasks.size(); i++) {
            Task task = new Task();
            if(tasks.get(i).getProbability() < 0.6) {
                task.setSecondQueueEnterArrivalTime(tasks.get(i).getFirstQueueExitTime());
            }else
            {
                task.setThirdQueueEnterArrivalTime(tasks.get(i).getFirstQueueExitTime());
            }
            task.setSecondQueueServiceTime(secondQueueServiceTime.get(i));
            tasksList.add(task);
        }

        system2.getSystemState().setServerStatus(0);
        system2.getSystemState().setNumberInQueue(0);
        system2.getSystemState().setTimeOfLastEvent(0);

        system2.getStaticalCounters().setNumberServiced(0);
        system2.getStaticalCounters().setTotalDelay(0);
        system2.getStaticalCounters().setAreaUnderQt(0);
        system2.getStaticalCounters().setGetAreaUnderBt(0);

        clock = 0;
        A = tasksList.get(0).getSecondQueueEnterArrivalTime();
        D =  Double.POSITIVE_INFINITY;
         print();
    }

    public List<Task> run() {
        Double initialA = A;
        Double prevClock = 0.0;

        clock = A;
        D = tasksList.get(0).getSecondQueueServiceTime() + clock;

        System.out.println("Event queue 2 Arrived Flag Up!");
        tasksCounter++;
        A = clock + tasksList.get(tasksCounter).getSecondQueueEnterArrivalTime();

        int exitcount = 0;
        while (arrivedcount != tasksList.size()) {

            prevClock = clock;

            if (A < D) {
                clock = A;
                if (D == Double.POSITIVE_INFINITY) {
                    initialA += (clock - prevClock);
                }
                System.out.println("Event queue 2 Arrived Flag Up!");
                system2.setArrivalTime(prevClock);
                arrivedcount++;
                Qt = Qt + system2.getTasksQueue().size() * (clock - prevClock);
                arrivalflag();
            } else {

                System.out.println("Event queue 2 Exited Flag Up!");
                exitcount++;
                clock = D;
                if (D == Double.POSITIVE_INFINITY) {
                    initialA += (clock - prevClock);
                }
                Qt = Qt + system2.getTasksQueue().size() * (clock - prevClock);
                exitedflag();
            }
            print();

        }

        timeOfLastEvent = clock;

        for (Task task : tasksList) {
            Es.add(task.getSecondQueueServiceTime());
        }

        timeOfLastEvent = clock;

        while (exitcount != tasksList.size()) {
            exitcount++;
            clock = D;
            if (D == Double.POSITIVE_INFINITY) {
                initialA += (clock - prevClock);
            }
            Qt = Qt + system2.getTasksQueue().size() * (clock - prevClock);
            exitedflag();
            print();

        }

         Wq = (system2.getStaticalCounters().getTotalDelay()) / system2.getStaticalCounters().getNumberServiced();
         Bt = clock - initialA;
         Lq = (Qt / timeOfLastEvent);
         p = (Bt / timeOfLastEvent);
         L = Lq + p;
         tmp = 0.0;
        for (Double e : Es) {
            tmp += e;
        }
         W = Wq + (tmp/numberServiced);
        responseDTO();

        System.out.println("total delay:" + system2.getStaticalCounters().getTotalDelay());
        System.out.println("number serviced:" + system2.getStaticalCounters().getNumberServiced());
        System.out.println("Q(t):" + Qt);
        System.out.println("B(t):" + Bt);
        System.out.println("W(q):" + Wq);
        System.out.println("L(q):" + Lq);
        System.out.println("p:" + p);
        System.out.println("L:" + L);
        System.out.println("E[s]:" + tmp/numberServiced);
        System.out.println("W:" + W);

        return tasksList;

    }
    public ResponseDTO responseDTO(){
        ResponseDTO responseDTO= new ResponseDTO();
        responseDTO.setBt(Bt);
        responseDTO.setEs(tmp/numberServiced);
        responseDTO.setL(L);
        responseDTO.setLq(Lq);
        responseDTO.setQt(Qt);
        responseDTO.setNumber_serviced(system2.getStaticalCounters().getNumberServiced());
        responseDTO.setTotal_delay(system2.getStaticalCounters().getTotalDelay());
        responseDTO.setP(p);
        responseDTO.setW(W);
        responseDTO.setWq(Wq);
        return responseDTO;
    }

    public void arrivalflag() {
        if(D == Double.POSITIVE_INFINITY){
            D = clock + tasksList.get(tasksCounter).getSecondQueueServiceTime();
        }
        else {
            tasksList.get(tasksCounter).setTimesOfArrival(clock);
            system2.setTasksQueue(tasksList.get(tasksCounter));
        }
        if(arrivedcount == tasksList.size()-1)
            return;
        tasksCounter++;
        A = clock + tasksList.get(tasksCounter).getSecondQueueEnterArrivalTime();

    }

    public void exitedflag() {
        numberServiced++;
        tasksList.get(numberServiced-1).setSecondQueueExitTime(clock);
        if(system2.getTasksQueue().size() == 0){
            system2.getStaticalCounters().setNumberServiced(numberServiced);
            D =  Double.POSITIVE_INFINITY;
            return;
        }
        runningTask = system2.getTasksQueue().poll();
        D = clock + runningTask.getSecondQueueServiceTime();
        system2.getStaticalCounters().setNumberServiced(numberServiced);
        totalDelay = totalDelay + (clock - runningTask.getTimesOfArrival());
        system2.getStaticalCounters().setTotalDelay(totalDelay);

    }

    int counter = 1;

    public List<StepsDTO> print() {
        System.out.println("level: " + counter);
        System.out.println("clock: " + clock);
        System.out.println("A: " + A);
        System.out.println("D: " + D);
        System.out.println("TotalDelay: " + system2.getStaticalCounters().getTotalDelay());
        System.out.println("numberServiced: " + numberServiced);
        System.out.println("Q(t): " + Qt);
        System.out.println();
        counter++;
        StepsDTO stepsDTO = new StepsDTO();
        stepsDTO.setA(A);
        stepsDTO.setClock(clock);
        stepsDTO.setD(D);
        stepsDTO.setQt(Qt);
        stepsDTO.setLevel(counter);
        stepsDTO.setNumberServiced(system2.getStaticalCounters().getNumberServiced());
        stepsDTO.setTotalDelay(system2.getStaticalCounters().getTotalDelay());
        stepsDTO.setArrivalTime(system2.getArrivalTime());
        stepsDTO.setBt(Bt);
        stepsDTO.setTimeofLastEvent(clock);
        stepsDTO.setServerStatus(1);
        stepsDTO.setNumberinQ(Lq);
        stepsDTOS.add(stepsDTO);
        return stepsDTOS;
    }
}
