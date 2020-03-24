import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
import controller.*;
import view.*;

import java.io.IOException;

public class Main extends Application {

    private Stage window;
    Scene connectionForm, mainWindow;
    private BorderPane rootLayout;

    @Override
    public void start(Stage primaryStage) throws Exception{
        this.window = primaryStage;
        this.window.setTitle("GSB Wiki");

        initRootLayout();
    }

    public void initRootLayout() throws IOException {
        rootLayout = FXMLLoader.load(getClass().getResource("view/RootLayout.fxml"));

        ConnectionScene connLayout = new ConnectionScene();
        connectionForm = new Scene(connLayout, 300, 250);

        MainWindow mainLayout = new MainWindow();
        mainWindow = new Scene(mainLayout, 600, 400);

        // ActionListener
        connLayout.btnConnection.setOnAction(e -> {
            String login = connLayout.loginField.getText();
            if (login == "sam") {
                window.setScene(mainWindow);
            } else {
                System.out.println(login);
            }
        });

        window.setScene(connectionForm);
        window.setResizable(false);
        window.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}

