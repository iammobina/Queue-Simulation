package ir.iust.kashaniyan.mobina.models;

import org.springframework.stereotype.Component;

@Component
public class Task {
    public double enterArrivalTime;
    public double serviceTime;
    public double timesOfArrival;

    public double firstQueueExitTime = 0;

    public double secondQueueEnterArrivalTime = 0;
    public double secondQueueServiceTime = 0;
    public double secondQueueExitTime = 0;

    public double thirdQueueEnterArrivalTime = 0;
    public double thirdQueueServiceTime = 0;
    public double thirdQueueExitTime = 0;

    public double probability = -1;

    public double getEnterArrivalTime() {
        return enterArrivalTime;
    }

    public void setEnterArrivalTime(double enterArrivalTime) {
        this.enterArrivalTime = enterArrivalTime;
    }

    public double getServiceTime() {
        return serviceTime;
    }

    public void setServiceTime(double serviceTime) {
        this.serviceTime = serviceTime;
    }

    public double getTimesOfArrival() {
        return timesOfArrival;
    }

    public void setTimesOfArrival(double timesOfArrival) {
        this.timesOfArrival = timesOfArrival;
    }

    public double getFirstQueueExitTime() {
        return firstQueueExitTime;
    }

    public void setFirstQueueExitTime(double firstQueueExitTime) {
        this.firstQueueExitTime = firstQueueExitTime;
    }

    public double getSecondQueueEnterArrivalTime() {
        return secondQueueEnterArrivalTime;
    }

    public void setSecondQueueEnterArrivalTime(double secondQueueEnterArrivalTime) {
        this.secondQueueEnterArrivalTime = secondQueueEnterArrivalTime;
    }

    public double getSecondQueueServiceTime() {
        return secondQueueServiceTime;
    }

    public void setSecondQueueServiceTime(double secondQueueServiceTime) {
        this.secondQueueServiceTime = secondQueueServiceTime;
    }

    public double getSecondQueueExitTime() {
        return secondQueueExitTime;
    }

    public void setSecondQueueExitTime(double secondQueueExitTime) {
        this.secondQueueExitTime = secondQueueExitTime;
    }

    public double getThirdQueueEnterArrivalTime() {
        return thirdQueueEnterArrivalTime;
    }

    public void setThirdQueueEnterArrivalTime(double thirdQueueEnterArrivalTime) {
        this.thirdQueueEnterArrivalTime = thirdQueueEnterArrivalTime;
    }

    public double getThirdQueueServiceTime() {
        return thirdQueueServiceTime;
    }

    public void setThirdQueueServiceTime(double thirdQueueServiceTime) {
        this.thirdQueueServiceTime = thirdQueueServiceTime;
    }

    public double getThirdQueueExitTime() {
        return thirdQueueExitTime;
    }

    public void setThirdQueueExitTime(double thirdQueueExitTime) {
        this.thirdQueueExitTime = thirdQueueExitTime;
    }

    public double getProbability() {
        return probability;
    }

    public void setProbability(double probability) {
        this.probability = probability;
    }
}
