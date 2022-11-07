package ir.iust.kashaniyan.mobina.FirstQueue;

import ir.iust.kashaniyan.mobina.models.Task;
import org.springframework.stereotype.Component;

import java.util.LinkedList;
import java.util.Queue;

@Component
public class SystemState1 {
    private int serverStatus;
    private int numberInQueue;
    private Queue<Task> tasksQueue = new LinkedList<>();
    private double timeOfLastEvent;

    public int getServerStatus() {
        return serverStatus;
    }

    public void setServerStatus(int serverStatus) {
        this.serverStatus = serverStatus;
    }

    public int getNumberInQueue() {
        return numberInQueue;
    }

    public void setNumberInQueue(int numberInQueue) {
        this.numberInQueue = numberInQueue;
    }

    public double getTimeOfLastEvent() {
        return timeOfLastEvent;
    }

    public void setTimeOfLastEvent(double timeOfLastEvent) {
        this.timeOfLastEvent = timeOfLastEvent;
    }

    public Queue<Task> getTasksQueue() {
        return tasksQueue;
    }

    public void setTasksQueue(Queue<Task> tasksQueue) {
        this.tasksQueue = tasksQueue;
    }
}
