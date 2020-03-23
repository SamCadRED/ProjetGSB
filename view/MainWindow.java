package view;

import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.layout.AnchorPane;

public class MainWindow extends AnchorPane {
    Label sceneTitle = new Label("Fenêtre Principale");

    public MainWindow() {
        super();
        setComposition();
        this.getChildren().addAll(sceneTitle);
    }

    private void  setComposition() {
        sceneTitle.setLayoutX(78);
        sceneTitle.setLayoutY(51);
    }
}
