package ir.iust.kashaniyan.mobina.Initializer;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

@Service
public class ServicesInitializer {
    @Autowired
    DistributionsGenerator distributionsGenerator;

    private int numberOfServices = 10000;

    List<Double> fisrtQueueEnterArrivalsList = new ArrayList<>();
    List<Double> fisrtQueueServiceList = new ArrayList<>();
    List<Double> secondQueueServiceList = new ArrayList<>();
    List<Double> thirdQueueServiceList = new ArrayList<>();

    public List<Double> firstQueueEnterArrivalsTime() {
        for(int i=0;i<numberOfServices;i++){
            fisrtQueueEnterArrivalsList.add(distributionsGenerator.generateExponential(1));
        }
        return fisrtQueueEnterArrivalsList;
    }

    public List<Double> firstQueueServiceTime() {
        for(int i=0;i<numberOfServices;i++){
            fisrtQueueServiceList.add(distributionsGenerator.generateExponential(2));
        }
        return fisrtQueueServiceList;
    }

    public List<Double> generateProbability(){
        List<Double> probabilities = new ArrayList<>();
        Random random = new Random();
        for(int i=0; i<numberOfServices; i++){
            probabilities.add(random.nextDouble());
        }
        for(int i=0; i<numberOfServices; i++){
            System.out.println(probabilities.get(i));
        }
        return probabilities;
//        int perecent60count=0;
//        int perecent40count=0;
//        int perecent60 = (numberOfServices *60)/100 ;
//        int precent40=(numberOfServices *40)/100;
//        do {
//            double randy = random.nextDouble();
//            if(randy >= 0.6)
//            {
//                probabilities.add(randy);
//                perecent60count++;
//            }
//
//        }while (perecent60 != perecent60count);
//        do {
//            double randy = random.nextDouble();
//            if(randy >= 0.6)
//            {
//                probabilities.add(randy);
//                perecent40count++;
//            }
//
//        }while (precent40 != perecent40count);
    }

    public List<Double> secondQueueServiceTime() {
        for(int i=0; i<numberOfServices; i++){
            secondQueueServiceList.add(distributionsGenerator.generateExponential(4));
        }
        return secondQueueServiceList;
    }

    public List<Double> ThirdQueueServiceTime() {
        for(int i=0; i<numberOfServices; i++){
            thirdQueueServiceList.add(distributionsGenerator.generateExponential(3));
        }
        return thirdQueueServiceList;
    }
}
