/**
 *
 *  @author Dajcz Dariusz S21522
 *
 */

package zad2;


import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.MalformedURLException;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JSplitPane;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.embed.swing.JFXPanel;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.web.WebEngine;
import javafx.scene.web.WebView;
import javafx.stage.Stage;

public class Main extends Application {

    public static void main(String[] args) throws MalformedURLException {
    	 Service s = new Service("Poland");
    	    String weatherJson = s.getWeather("Warsaw");
    	    Double rate1 = s.getRateFor("USD");
    	    Double rate2 = s.getNBPRate();
        Application.launch(args);
 
    }
    
    @Override
    public void start(Stage primaryStage) throws Exception {
    	
    	Service s = new Service("Poland");
 	    String weatherJson = s.getWeather("Warsaw");
 	    Double rate1 = s.getRateFor("USD");
 	    Double rate2 = s.getNBPRate();
    	
    	WebView przegl = new WebView();
        WebEngine silnikStron = przegl.getEngine();
        
        
        primaryStage.setTitle("HBox Experiment 1");

        Label label = new Label();
        Button button = new Button("Weather");
        TextField  text = new TextField();
//        TextArea text =  new TextArea();
        
        Label label2 = new Label();
        Button button2 = new Button("Ctiy!");
        
        Label label3 = new Label();
        Button button3 = new Button("Redirect!");
        Service service = new Service();
        
        button.setOnAction(value ->  {
				try {
					label.setText(s.GetTodaysWeatherInformation());
				} catch (MalformedURLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
        });
        
        button2.setOnAction(v -> {
        	label2.setText("A dollar is worth " + rate1 + "zloty");
        });
        
        button3.setOnAction(v -> {
        	
        	silnikStron.load("https://pl.wikipedia.org/wiki/Wikipedia:Strona_g%C5%82%C3%B3wna");
        });
        


        HBox hbox = new HBox(button, button2, button3);
        hbox.setSpacing(120);
        
        HBox hbox2 = new HBox(label, label2, label3);
        hbox2.setSpacing(80);
        
        VBox vbox = new VBox(hbox, hbox2);
        vbox.setSpacing(30);

        Scene scene = new Scene(vbox, 800, 400);
        primaryStage.setScene(scene);
        primaryStage.show();

    }
}