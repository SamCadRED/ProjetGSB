import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

import java.io.IOException;

public class Main extends Application {

    private Stage primaryStage;
    private BorderPane rootLayout;

    public Main() {

    }

    @Override
    public void start(Stage primaryStage) throws Exception{
        this.primaryStage = primaryStage;
        this.primaryStage.setTitle("GSB Application");

        initRootLayout();
    }

    public void initRootLayout() throws IOException {
        rootLayout = FXMLLoader.load(getClass().getResource("view/RootLayout.fxml"));

        Scene scene = new Scene(rootLayout);

        showAppHeader();
        //showConnectionForm();
        showMainWindow();

        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public void showAppHeader() throws IOException {
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(Main.class.getResource("view/AppHeader.fxml"));
        AnchorPane header = loader.load();

        rootLayout.setTop(header);
    }

    public void showConnectionForm() throws IOException {
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(Main.class.getResource("view/ConnectionForm.fxml"));
        AnchorPane connectionForm = loader.load();

        rootLayout.setCenter(connectionForm);
    }

    public void showMainWindow() throws IOException {
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(Main.class.getResource("view/MainView.fxml"));
        AnchorPane connectionForm = loader.load();

        rootLayout.setCenter(connectionForm);
    }

    public static void main(String[] args) {
        launch(args);
    }
}

