package ir.iust.kashaniyan.mobina.Initializer;

import org.springframework.stereotype.Service;

import java.util.Random;

@Service
public class DistributionsGenerator {
    Random random = new Random();

    public double generateExponential(double lambda) {
        if (lambda == 0.0)
            return 0.0;
        double randomize;
        randomize = random.nextDouble();
        return -1/ (lambda)*Math.log(randomize) ;
    }

}
