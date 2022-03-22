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
import java.lang.reflect.InvocationTargetException;
import java.net.MalformedURLException;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JSplitPane;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;

import com.google.gson.JsonIOException;
import com.google.gson.JsonSyntaxException;

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

    public static void main(String[] args) throws IOException, InvocationTargetException {
    	
    	String country = "England";
    	 Service s = new Service(country);
    	    String weatherJson = s.getWeather("Warsaw");
//    	    String rate1 = s.getRateFor();
    	    Double rate2 = s.getNBPRate();
        Application.launch(args);
 
    }
    
    @Override
    public void start(Stage primaryStage) throws Exception {
    	
    	
    	
    	TextField textRate =  new TextField();
    	String country = "England";
    	Service s = new Service(country);
    	String weatherFrom = "Rome";
 	    String weatherJson = s.getWeather(weatherFrom);
 	   
 	    Double rate1 = s.getRateFor(textRate.getText());
 	    Double rate2 = s.getNBPRate();
    	
    	WebView przegl = new WebView();
        WebEngine silnikStron = przegl.getEngine();
        
        
        primaryStage.setTitle("HBox Experiment 1");

        Label labelTop = new Label("Today weather from:  " + weatherFrom);
        Label label = new Label();
        Button button = new Button("Weather");
        TextField  text = new TextField();

        
        Label labelTop2 = new Label();
        Label label2 = new Label();
       
        Label labelBlank = new Label();
        Label labelRat = new Label("Choose from: GBP, USD, PLN, EUR, CHF");
        Button button2 = new Button("Ratings!");
              
        
        Label labelTop3 = new Label("Check currency from " + country);
        Label label3 = new Label();
        Button button3 = new Button("To PLN!");
        Service service = new Service();
        
        button.setOnAction(value ->  {
					label2.setText(weatherJson);
        });
        
        button2.setOnAction(v -> {
        	try {
				label2.setText("Przelicznik wynosi: " + s.getRateFor(textRate.getText()));
			} catch (JsonIOException | JsonSyntaxException | IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
        });
        
        button3.setOnAction(v -> {
        	
        	try {
				label2.setText("Currency converter between the Polish currency and  " + country + " is: " + s.getNBPRate());
			} catch (InvocationTargetException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
        });
        
        
        HBox hboxLabel = new HBox(labelBlank, labelRat);
        hboxLabel.setSpacing(300);
        
        HBox hboxTop = new HBox(labelTop, textRate, labelTop3);
        hboxTop.setSpacing(160);

        HBox hbox = new HBox(button, button2, button3);
        hbox.setSpacing(240);
        
        HBox hbox2 = new HBox(label, label2, label3);
        hbox2.setSpacing(180);
        
        VBox vbox = new VBox(hboxLabel, hboxTop, hbox, hbox2);
        vbox.setSpacing(30);

        Scene scene = new Scene(vbox, 800, 400);
        primaryStage.setScene(scene);
        primaryStage.show();

    }
}