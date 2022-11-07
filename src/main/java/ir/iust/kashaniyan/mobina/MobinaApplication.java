package ir.iust.kashaniyan.mobina;

import com.sun.scenario.effect.impl.sw.java.JSWBlend_SRC_INPeer;
import ir.iust.kashaniyan.mobina.Gui.Controller;
import ir.iust.kashaniyan.mobina.Initializer.ServicesInitializer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.context.ConfigurableApplicationContext;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

@SpringBootApplication
@EnableSwagger2
public class MobinaApplication {

    public static void main(String[] args) {

//        SpringApplication.run(MobinaApplication.class, args);
//        }
        SpringApplicationBuilder builder = new SpringApplicationBuilder(MobinaApplication.class);

        builder.headless(false);

        ConfigurableApplicationContext context = builder.run(args);
    }
}

