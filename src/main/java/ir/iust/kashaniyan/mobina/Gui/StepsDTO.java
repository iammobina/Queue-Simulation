package ir.iust.kashaniyan.mobina.Gui;

public class StepsDTO {
    private double level;
    private double clock;
    private double A;
    private double D;
    private double TotalDelay;
    private double numberServiced;
    private double Qt;
    private double timeofLastEvent;
    private double arrivalTime;
    private double numberinQ;
    private double serverStatus;
    private double Bt;

    public double getBt() {
        return Bt;
    }

    public void setBt(double bt) {
        Bt = bt;
    }

    public double getTimeofLastEvent() {
        return timeofLastEvent;
    }

    public void setTimeofLastEvent(double timeofLastEvent) {
        this.timeofLastEvent = timeofLastEvent;
    }

    public double getArrivalTime() {
        return arrivalTime;
    }

    public void setArrivalTime(double arrivalTime) {
        this.arrivalTime = arrivalTime;
    }

    public double getNumberinQ() {
        return numberinQ;
    }

    public void setNumberinQ(double numberinQ) {
        this.numberinQ = numberinQ;
    }

    public double getServerStatus() {
        return serverStatus;
    }

    public void setServerStatus(double serverStatus) {
        this.serverStatus = serverStatus;
    }

    public double getLevel() {
        return level;
    }

    public void setLevel(double level) {
        this.level = level;
    }

    public double getClock() {
        return clock;
    }

    public void setClock(double clock) {
        this.clock = clock;
    }

    public double getA() {
        return A;
    }

    public void setA(double a) {
        A = a;
    }

    public double getD() {
        return D;
    }

    public void setD(double d) {
        D = d;
    }

    public double getTotalDelay() {
        return TotalDelay;
    }

    public void setTotalDelay(double totalDelay) {
        TotalDelay = totalDelay;
    }

    public double getNumberServiced() {
        return numberServiced;
    }

    public void setNumberServiced(double numberServiced) {
        this.numberServiced = numberServiced;
    }

    public double getQt() {
        return Qt;
    }

    public void setQt(double qt) {
        Qt = qt;
    }
}
