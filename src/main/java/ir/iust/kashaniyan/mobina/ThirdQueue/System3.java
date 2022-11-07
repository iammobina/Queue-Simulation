package ir.iust.kashaniyan.mobina.ThirdQueue;


import ir.iust.kashaniyan.mobina.FirstQueue.StaticalCounters1;
import ir.iust.kashaniyan.mobina.FirstQueue.SystemState1;
import ir.iust.kashaniyan.mobina.SecondQueue.StaticalCounters2;
import ir.iust.kashaniyan.mobina.SecondQueue.SystemState2;
import ir.iust.kashaniyan.mobina.models.Task;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.LinkedList;
import java.util.Queue;

@Component
public class System3 {
    private double arrivalTime = 0;
    private Queue<Task> tasksQueue = new LinkedList<Task>();

    @Autowired
    SystemState1 systemState;
    @Autowired
    StaticalCounters1 staticalCounters;
    @Autowired
    SystemState2 systemState2;
    @Autowired
    StaticalCounters2 staticalCounters2;

    public double getArrivalTime() {
        return arrivalTime;
    }

    public void setArrivalTime(double arrivalTime) {
        this.arrivalTime = arrivalTime;
    }

    public Queue<Task> getTasksQueue() {
        return tasksQueue;
    }

    public void setTasksQueue(Task task) {
        this.tasksQueue.add(task);
    }

    public SystemState1 getSystemState() {
        return systemState;
    }

    public void setSystemState(SystemState1 systemState) {
        this.systemState = systemState;
    }

    public StaticalCounters1 getStaticalCounters() {
        return staticalCounters;
    }

    public void setStaticalCounters(StaticalCounters1 staticalCounters) {
        this.staticalCounters = staticalCounters;
    }
}
