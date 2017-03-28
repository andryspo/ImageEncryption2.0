package sample.Controllers;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import sample.MenuComands.AboutCommand;

/**
 * Created by Andriy on 22.05.2016.
 */
public class AboutMenuController {
    @FXML
    private Label aboutLabel;

    @FXML
    private void initialize() {
        aboutLabel.setText(AboutCommand.aboutText);
    }
}
