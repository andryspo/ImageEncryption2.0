package sample.Controllers;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.*;
import javafx.scene.layout.AnchorPane;
import javafx.stage.FileChooser;
import sample.ImageData.Settings;
import sample.Main;
import sample.algorythms.Keys.KeyCreator;
import sample.algorythms.typesOfInput.Algorythms;
import sample.algorythms.typesOfInput.Keys;

import java.io.File;

/**
 * Created by Andriy on 18.02.2016.
 */
public class SettingController {
    private static Settings setting = MainController.setting;
    private static KeyCreator keyCreator = MainController.keyCreator;
    @FXML
    private ComboBox algorythmCombobox;
    @FXML
    private RadioButton encryption_RadioButton;
    @FXML
    private RadioButton decryptionRadioButton;
    @FXML
    private AnchorPane mainPanel;
    @FXML
    private Label typeOfKeyInputLabel;
    @FXML
    private ComboBox keyComboBox;
    @FXML
    private Button searchBtn;
    @FXML
    private TextField inputDataTextfield;
    private static String keyArgument;

    @FXML
    void initialize() {
        try {

            inputDataTextfield.setText(keyArgument);

            switch (setting.getAlgorythm()) {
                case XOR:
                    algorythmCombobox.setValue(Algorythms.XOR);
                    break;
                case RC4:
                    algorythmCombobox.setValue(Algorythms.RC4);
                    break;
                case Salsa20:
                    algorythmCombobox.setValue(Algorythms.Salsa20);
                    break;
                case Rabbit:
                    algorythmCombobox.setValue(Algorythms.Rabbit);
                    break;
            }

            if (setting.isEncryptOperation() == true) {
                encryption_RadioButton.setSelected(true);
                decryptionRadioButton.setSelected(false);
            } else {
                encryption_RadioButton.setSelected(false);
                decryptionRadioButton.setSelected(true);
            }

            if (setting.getTypeOfInputOperation() == Keys.InputFromKeyboard) {
                keyComboBox.setValue(Keys.InputFromKeyboard);
                mainPanel.getChildren().remove(searchBtn);
                typeOfKeyInputLabel.setText("Enter key:");
            } else if (setting.getTypeOfInputOperation() == Keys.Generate) {
                typeOfKeyInputLabel.setText("Enter length here:");
                mainPanel.getChildren().remove(searchBtn);
                keyComboBox.setValue(Keys.Generate);
            } else {
                typeOfKeyInputLabel.setText("Enter path to file:");
                mainPanel.getChildren().add(searchBtn);
                keyComboBox.setValue(Keys.ReadFromFile);
            }
        } catch (NullPointerException e) {
            e.printStackTrace();
        }
        mainPanel.getChildren().remove(searchBtn);
        ObservableList<String> list = FXCollections.observableArrayList(Keys.InputFromKeyboard.toString(),
                Keys.Generate.toString(), Keys.ReadFromFile.toString());
        keyComboBox.setItems(list);
        list = FXCollections.observableArrayList(Algorythms.XOR.toString(), Algorythms.RC4.toString(), Algorythms.Salsa20.toString(), Algorythms.Rabbit.toString());
        algorythmCombobox.setItems(list);
        inputDataTextfield.setText(keyArgument);
    }

    public void cencel(ActionEvent actionEvent) {
        ((Node) actionEvent.getSource()).getScene().getWindow().hide();
    }

    public void saveChanges(ActionEvent actionEvent) {
        if (encryption_RadioButton.isSelected()) {
            setting.setEncryptOperation(true);
        } else {
            setting.setEncryptOperation(false);
        }

        if (keyComboBox.getValue().toString().equals(Keys.InputFromKeyboard.toString())) {
            setting.setTypeOfInputOperation(Keys.InputFromKeyboard);
            keyCreator.inputKey(inputDataTextfield.getText());
        } else if (keyComboBox.getValue().toString().equals(Keys.Generate.toString())) {
            setting.setTypeOfInputOperation(Keys.Generate);
            keyCreator.generateKey(Integer.parseInt(inputDataTextfield.getText()));
        } else if (keyComboBox.getValue().toString().equals(Keys.ReadFromFile.toString())) {
            setting.setTypeOfInputOperation(Keys.ReadFromFile);
            keyCreator.getKeyFromFile(inputDataTextfield.getText());
        }

        keyArgument = inputDataTextfield.getText();

        if (algorythmCombobox.getValue().toString().equals(Algorythms.XOR.toString()))
            setting.setAlgorythm(Algorythms.XOR);
        else if (algorythmCombobox.getValue().toString().equals(Algorythms.RC4.toString()))
            setting.setAlgorythm(Algorythms.RC4);
        else if (algorythmCombobox.getValue().toString().equals(Algorythms.Rabbit.toString()))
            setting.setAlgorythm(Algorythms.Rabbit);
        else if (algorythmCombobox.getValue().toString().equals(Algorythms.Salsa20.toString()))
            setting.setAlgorythm(Algorythms.Salsa20);
        ((Node) actionEvent.getSource()).getScene().getWindow().hide();
    }


    public void createComboBoxLayout(ActionEvent actionEvent) {

        if (Keys.InputFromKeyboard.toString().equals(keyComboBox.getValue().toString())) {
            mainPanel.getChildren().remove(searchBtn);
            typeOfKeyInputLabel.setText("Enter key:");
        } else if (Keys.Generate.toString().equals(keyComboBox.getValue().toString())) {
            typeOfKeyInputLabel.setText("Enter length here:");
            mainPanel.getChildren().remove(searchBtn);
        } else if (Keys.ReadFromFile.toString().equals(keyComboBox.getValue().toString())) {
            typeOfKeyInputLabel.setText("Enter path to file:");
            mainPanel.getChildren().add(searchBtn);
        }

    }

    public void searchFile(ActionEvent actionEvent) {
        FileChooser fileChooser = new FileChooser();
        configureFileChooser(fileChooser);
        File file = fileChooser.showOpenDialog(Main.stage);
        inputDataTextfield.setText(file.getPath());
    }

    private void configureFileChooser(final FileChooser fileChooser) {
        fileChooser.setTitle("Load password");
        fileChooser.setInitialDirectory(
                new File("D:\\")
        );
        fileChooser.getExtensionFilters().addAll(
                new FileChooser.ExtensionFilter("txt", "*.txt")
        );

    }
}
