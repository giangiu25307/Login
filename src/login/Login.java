/*
*Tutorial su Login in JavaFx.
 */
package login;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Random;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.Stage;

/**
 *
 * @author giangiu
 */
public class Login extends Application {

    public int variabile = 0;
    public int punti = 0;
    public int punto = 0;

    @Override
    public void start(Stage primaryStage) {
        String[][] trad = new String[25][2];
        Random rand = new Random();

        try {

            FileReader f = new FileReader("data/traduzioni.txt");
            BufferedReader bf = new BufferedReader(f);

            int x = 0;
            while (bf.ready()) {
                String s = bf.readLine();
                String[] ss = s.split(",");
                if (ss.length != trad[x].length) {
                    System.out.println("Errore");
                    continue;
                }
                trad[x][0] = ss[0];
                trad[x][1] = ss[1];
                x++;
            }

        } catch (FileNotFoundException ex) {
            System.out.println("Errore: " + ex.getMessage());
        } catch (IOException ex) {
            System.out.println("Errore: " + ex.getMessage());
        }

        primaryStage.setTitle("Esercizi di traduzione");

        GridPane grid = new GridPane();
        grid.setAlignment(Pos.CENTER);
        grid.setHgap(15);
        grid.setVgap(15);
        grid.setPadding(new Insets(25, 25, 25, 25));
        Text scenetitle = new Text("Traduci in italiano");
        scenetitle.setFont(Font.font("Tahoma", FontWeight.NORMAL, 20));
        grid.add(scenetitle, 0, 0, 2, 1);
        variabile = rand.nextInt(trad.length);
        Label Parola = new Label(trad[variabile][0]);
        grid.add(Parola, 0, 1);

        TextField tr = new TextField();
        grid.add(tr, 1, 1);

        Button btn = new Button("Controlla");
        HBox hbBtn = new HBox(10);
        hbBtn.setAlignment(Pos.BOTTOM_RIGHT);
        hbBtn.getChildren().add(btn);
        grid.add(hbBtn, 1, 7);
        final Text actiontarget = new Text();
        grid.add(actiontarget, 1, 9);
        btn.setOnAction((ActionEvent e) -> {
            if (tr.getText().equals(trad[variabile][1])) {
                actiontarget.setFill(Color.GREEN);
                actiontarget.setText("Esatto!");
                punti++;
                variabile = rand.nextInt(trad.length);
                Parola.setText(trad[variabile][0]);
                tr.setText("");
            } else {
                actiontarget.setFill(Color.RED);
                actiontarget.setText("Errato!");
                punto++;
                variabile = rand.nextInt(trad.length);
                Parola.setText(trad[variabile][0]);
                tr.setText("");
            }
        });
        Button butn = new Button("Risultato");
        HBox hbButn = new HBox(10);
        hbButn.setAlignment(Pos.BOTTOM_RIGHT);
        hbButn.getChildren().add(butn);
        grid.add(hbButn, 0, 7);
        butn.setOnAction((ActionEvent e) -> {
            tr.setDisable(true);
            btn.setDisable(true);
            Label p = new Label("Risposte esatte: " + punti);
            grid.add(p, 1, 3);
            Label parola = new Label("Risposte errate: " + punto);
            grid.add(parola, 1, 4);
            Label g = new Label("Vuoi rigiocare?");
            grid.add(g, 0, 9);
                        Button bt = new Button("No");
            HBox hbBt = new HBox(10);
            hbBt.setAlignment(Pos.BOTTOM_RIGHT);
            hbBt.getChildren().add(bt);
            grid.add(hbBt, 2, 9);
            butn.setDisable(true);
            bt.setOnAction((ActionEvent i) -> {
                primaryStage.close();
            });
            Button btu = new Button("Si");
            HBox hbBtu = new HBox(10);
            hbBtu.setAlignment(Pos.BOTTOM_RIGHT);
            hbBtu.getChildren().add(btu);
            grid.add(hbBtu, 1, 9);
            butn.setDisable(true);
            btu.setOnAction((ActionEvent i) -> {
                tr.setDisable(false);
            btn.setDisable(false);
            butn.setDisable(false);
            punti=0;
            punto=0;
            parola.setText("");
            p.setText("");
            g.setVisible(false);
            btu.setVisible(false);
            bt.setVisible(false);
            });
        });

        Scene scene = new Scene(grid, 550, 350);

        primaryStage.setScene(scene);

        primaryStage.show();
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);

    }
}
