package sample.Controllers;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.ComboBox;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;
import sample.ImageData.Settings;
import sample.MenuComands.ThemeCommand;
import sample.styles.Styles;

/**
 * Created by Andriy on 23.05.2016.
 */
public class ThemeController {
    @FXML
    private ComboBox comboBoxThemes;
    private Settings settings = ThemeCommand.getSettings();

    @FXML
    private void initialize() {

        ObservableList<Styles> list = FXCollections.observableArrayList(Styles.MODERN, Styles.BRIGHT, Styles.SIMPLE);
        comboBoxThemes.setItems(list);
        comboBoxThemes.setValue(settings.getStyle());

    }

    public void apply(ActionEvent actionEvent) {

    }

    public void save(ActionEvent actionEvent) {
        settings.setStyle((Styles) comboBoxThemes.getValue());
        Stage stage = (Stage) comboBoxThemes.getScene().getWindow();
        stage.getOnCloseRequest().handle(new WindowEvent(stage, WindowEvent.WINDOW_CLOSE_REQUEST));
        stage.close();
    }

    public void cancel(ActionEvent actionEvent) {

        ((Node) actionEvent.getSource()).getScene().getWindow().hide();
    }
}
