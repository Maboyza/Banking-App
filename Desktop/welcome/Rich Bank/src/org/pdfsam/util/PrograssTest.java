/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.pdfsam.util;

import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.scene.Scene;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

/**
 *
 * @author terry
 */
public class PrograssTest extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception {
        primaryStage = new Stage();
        primaryStage.setTitle("Prograss Indicator");

        RingProgressIndicator ringprograIndicator = new RingProgressIndicator();
        ringprograIndicator.setRingWidth(200);
        ringprograIndicator.makeIndeterminate();

        StackPane pane = new StackPane(ringprograIndicator);
       

        Scene scene = new Scene(pane, 300, 250);

        primaryStage.setScene(scene);
        primaryStage.show();

         new WorkerThread(ringprograIndicator).start();
    }

    public static void main(String[] args) {
        launch(args);
    }

    class WorkerThread extends Thread {

        RingProgressIndicator ring;
        int progras = 0;

        public WorkerThread(RingProgressIndicator ring) {
            this.ring = ring;
        }

        @Override
        public void run() {
            while (true) {
                try {
                    Thread.sleep(0);
                } catch (InterruptedException ex) {
                    Logger.getLogger(PrograssTest.class.getName()).log(Level.SEVERE, null, ex);
                }
                Platform.runLater(() -> {
                    ring.setProgress(progras);
                });
                progras += 1;
                if (progras > 100) {
                    break;
                }
            }
        }
    }

}
