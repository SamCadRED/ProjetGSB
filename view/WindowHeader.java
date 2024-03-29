package view;

import javafx.scene.control.Hyperlink;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;

public class WindowHeader extends AnchorPane {
    Label sceneTitle = new Label();
    public Hyperlink link = new Hyperlink();

    public WindowHeader(String title, String linkText) {
        super();
        sceneTitle.setText(title);
        sceneTitle.setLayoutX(10);
        sceneTitle.setLayoutY(10);
        sceneTitle.setId("title");
        if (title == "") {sceneTitle.setVisible(false);}

        link.setText(linkText);
        link.setLayoutX(10);
        link.setLayoutY(40);
        link.setStyle("-fx-border-width: 0px;");
        if(linkText == "") {link.setVisible(false);}

        this.getChildren().addAll(sceneTitle, link);
    }

}
