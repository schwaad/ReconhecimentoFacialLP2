package com.reconhecimento_facial_lp2.javafx;

import com.reconhecimento_facial_lp2.javafx.MainApplication;
import com.reconhecimento_facial_lp2.springboot.ReconhecimentoFacialLp2Application;
import org.springframework.boot.SpringApplication;

public class App {
    public static void main(String[] args) {
        Thread springThread = new Thread(() -> {
            SpringApplication.run(ReconhecimentoFacialLp2Application.class, args);
        });

        springThread.setDaemon(true);
        springThread.start();

        MainApplication.main(args);
    }
}