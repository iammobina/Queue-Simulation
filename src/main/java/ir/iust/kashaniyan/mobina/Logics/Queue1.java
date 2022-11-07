package ir.iust.kashaniyan.mobina.Logics;


import ir.iust.kashaniyan.mobina.FirstQueue.System1;
import ir.iust.kashaniyan.mobina.Gui.ResponseDTO;
import ir.iust.kashaniyan.mobina.Gui.StepsDTO;
import ir.iust.kashaniyan.mobina.Initializer.ServicesInitializer;
import ir.iust.kashaniyan.mobina.models.Task;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class Queue1 {

    @Autowired
    System1 system1;

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

    private Double tmp;
    private double Wq;
    private double Bt;
    private double Lq;
    private double L ;
    private  double W ;
    private  double p ;

    Double Qt = 0.0;
    Double timeOfLastEvent = 0.0;
    List<Double> Es = new ArrayList<>();

    public void initializeSystemParameters() {

        tasksList = new ArrayList<>();
        Es = new ArrayList<>();
        tasksCounter = 0;
        numberServiced = 0;
        totalDelay = 0;
        Qt = 0.0;
        timeOfLastEvent = 0.0;

        List<Double> firstQueueEnterArrivalsList = servicesInitializer.firstQueueEnterArrivalsTime();
        List<Double> firstQueueServiceTime = servicesInitializer.firstQueueServiceTime();
        List<Double> probabilities = servicesInitializer.generateProbability();

        for (int i = 0; i < firstQueueEnterArrivalsList.size(); i++) {
            Task task = new Task();
            task.setEnterArrivalTime(firstQueueEnterArrivalsList.get(i));
            task.setServiceTime(firstQueueServiceTime.get(i));
            task.setProbability(probabilities.get(i));
            tasksList.add(task);
        }

        system1.getSystemState().setServerStatus(0);
        system1.getSystemState().setNumberInQueue(0);
        system1.getSystemState().setTimeOfLastEvent(0);
        system1.getStaticalCounters().setNumberServiced(0);
        system1.getStaticalCounters().setTotalDelay(0);
        system1.getStaticalCounters().setAreaUnderQt(0);
        system1.getStaticalCounters().setGetAreaUnderBt(0);
        printInitials();


        clock = 0;
        A = tasksList.get(0).getEnterArrivalTime();
        D =  Double.POSITIVE_INFINITY;
         print();
    }

    int arrivedcount = 1;

    public List<Task> run() {
        double initialA = A;
        double prevClock = 0.0;

        clock = A;
        D = tasksList.get(0).getServiceTime() + clock;

        System.out.println("First Queue Event Arrived Flag Up!");
        tasksCounter++;
        A = clock + tasksList.get(tasksCounter).getEnterArrivalTime();

        int exitcount = 0;
        while (arrivedcount != tasksList.size()) {

            prevClock = clock;

            if (A < D) {
                clock = A;
                if (D == Double.POSITIVE_INFINITY) {
                    initialA += (clock - prevClock);
                }
                System.out.println("First Queue Event Arrived Flag Up!");
                arrivedcount++;
                system1.setArrivalTime(clock);
                Qt = Qt + system1.getTasksQueue().size() * (clock - prevClock);
                arrivalflag();
            } else {
                System.out.println("First Queue Event Exited Flag Up!");
                exitcount++;
                clock = D;
                system1.setArrivalTime(clock);
                if (D == Double.POSITIVE_INFINITY) {
                    initialA += (clock - prevClock);
                }
                Qt = Qt + system1.getTasksQueue().size() * (clock - prevClock);
                exitedflag();
                print();
            }

        }

        timeOfLastEvent = clock;

        for (Task task : tasksList) {
            Es.add(task.getServiceTime());
        }

        timeOfLastEvent = clock;

        while (exitcount != tasksList.size()) {
            exitcount++;
            clock = D;
            if (D == Double.POSITIVE_INFINITY) {
                initialA += (clock - prevClock);
            }
            Qt = Qt + system1.getTasksQueue().size() * (clock - prevClock);
            exitedflag();
        }

         Wq = (system1.getStaticalCounters().getTotalDelay()) / system1.getStaticalCounters().getNumberServiced();
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
        System.out.println();
        System.out.println("total delay:" + system1.getStaticalCounters().getTotalDelay());
        System.out.println("number serviced:" + system1.getStaticalCounters().getNumberServiced());
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
        responseDTO.setNumber_serviced(system1.getStaticalCounters().getNumberServiced());
        responseDTO.setTotal_delay(system1.getStaticalCounters().getTotalDelay());
        responseDTO.setP(p);
        responseDTO.setW(W);
        responseDTO.setWq(Wq);
        return responseDTO;
    }

    public void arrivalflag() {
        if(D == Double.POSITIVE_INFINITY){
            D = clock + tasksList.get(tasksCounter).getServiceTime();
        }
        else {
            tasksList.get(tasksCounter).setTimesOfArrival(clock);
            system1.setTasksQueue(tasksList.get(tasksCounter));
        }
        if(arrivedcount == tasksList.size()-1)
            return;
        tasksCounter++;
        A = clock + tasksList.get(tasksCounter).getEnterArrivalTime();

    }

    public void exitedflag() {
        numberServiced++;
        tasksList.get(numberServiced-1).setFirstQueueExitTime(clock);
        if(system1.getTasksQueue().size() == 0){
            system1.getStaticalCounters().setNumberServiced(numberServiced);
            D =  Double.POSITIVE_INFINITY;
            return;
        }
        runningTask = system1.getTasksQueue().poll();
        D = clock + runningTask.getServiceTime();
        system1.getStaticalCounters().setNumberServiced(numberServiced);
        totalDelay = totalDelay + (clock - runningTask.getTimesOfArrival());
        system1.getStaticalCounters().setTotalDelay(totalDelay);

    }

    int counter = 1;

    public List<StepsDTO> print() {
        System.out.println("level: " + counter);
        System.out.println("clock: " + clock);
        System.out.println("A:"+ A);
        System.out.println("D:"+ D);
        System.out.println("TotalDelay: " + system1.getStaticalCounters().getTotalDelay());
        System.out.println("numberServiced: " + numberServiced);
        System.out.println("Q(t):" + Qt);
        System.out.println();
        counter++;
        StepsDTO stepsDTO = new StepsDTO();
        stepsDTO.setA(A);
        stepsDTO.setClock(clock);
        stepsDTO.setD(D);
        stepsDTO.setQt(Qt);
        stepsDTO.setLevel(counter);
        stepsDTO.setNumberServiced(system1.getStaticalCounters().getNumberServiced());
        stepsDTO.setTotalDelay(system1.getStaticalCounters().getTotalDelay());
        stepsDTO.setArrivalTime(system1.getArrivalTime());
        stepsDTO.setBt(Bt);
        stepsDTO.setTimeofLastEvent(clock);
        stepsDTO.setServerStatus(1);
        stepsDTO.setNumberinQ(Lq);
        stepsDTOS.add(stepsDTO);
        return stepsDTOS;
    }

    public void printInitials() {
        for(int i=0;i<tasksList.size();i++){
            System.out.println("task"+i+":\t"+tasksList.get(i).getEnterArrivalTime()+"\t"+tasksList.get(i).getServiceTime());
        }
    }

}
