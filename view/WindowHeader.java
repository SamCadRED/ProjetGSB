package view;

import javafx.scene.control.Hyperlink;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.scene.text.Font;

public class WindowHeader extends AnchorPane {
    Label sceneTitle = new Label();
    Hyperlink link = new Hyperlink();

    public WindowHeader(String title, String linkText) {
        super();

        sceneTitle.setText(title);
        sceneTitle.setLayoutX(10);
        sceneTitle.setLayoutY(10);
        sceneTitle.setFont(new Font("Arial",20));

        link.setText(linkText);
        link.setVisited(true);
        link.setLayoutX(10);
        link.setLayoutY(30);

        if (linkText != "") {
            link.setVisible(true);
        } else {
            link.setVisible(false);
        }

        this.getChildren().addAll(sceneTitle, link);
    }

}
