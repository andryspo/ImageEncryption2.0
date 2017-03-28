package sample.Controllers;

import javafx.embed.swing.SwingFXUtils;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.TextField;
import javafx.stage.FileChooser;
import sample.ImageData.ImageViewData;
import sample.Main;
import sample.MenuComands.SelectedSaver;
import sample.algorythms.Keys.KeyCreator;
import sample.algorythms.typesOfInput.Algorythms;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.io.*;

/**
 * Created by Andriy on 17.05.2016.
 */
public class SaveSelectedController {
    @FXML
    private TextField TextFieldSavePicture;
    @FXML
    private TextField TextFieldSaveKey;
    @FXML
    private TextField TextFieldSaveInfo;

    private File pictureFile;
    private File keyFile;
    private File infoFile;
    private KeyCreator key = SelectedSaver.getKeyCreator();
    private Algorythms algorythm = SelectedSaver.getAlg();
    private ImageViewData image = SelectedSaver.getImage();

    public void save(ActionEvent actionEvent) {
        pictureFile = new File(TextFieldSavePicture.getText());
        keyFile = new File(TextFieldSaveKey.getText());
        infoFile = new File(TextFieldSaveInfo.getText());

        if (image.isOperated() == false) {
            JOptionPane.showMessageDialog(null, "The image has been not operated yet");
            return;
        }
        if (pictureFile != null) {
            try {
                image.getOperatedImage().cancel();
                ImageIO.write(SwingFXUtils.fromFXImage(image.getOperatedImage(), null), "png", pictureFile);

            } catch (IOException ex) {
                System.out.println(ex.getMessage());
            }
        }

        try {
            FileOutputStream out = new FileOutputStream(keyFile);
            out.write(key.getKey());
            out.close();
            PrintWriter writer = new PrintWriter(infoFile, "UTF-8");
            writer.println("Algorythm: " + algorythm.toString());
            writer.println("Picture: " + pictureFile.getAbsolutePath());
            writer.println("Key: " + infoFile.getAbsolutePath());
            writer.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        ((Node) actionEvent.getSource()).getScene().getWindow().hide();
    }

    public void cancel(ActionEvent actionEvent) {
        ((Node) actionEvent.getSource()).getScene().getWindow().hide();
    }

    public void searchInfo(ActionEvent actionEvent) {
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Save Info");
        fileChooser.getExtensionFilters().addAll(
                new FileChooser.ExtensionFilter("txt", "*.txt")
        );
        File file = fileChooser.showSaveDialog(Main.stage);
        if (file != null) {
            TextFieldSaveInfo.setText(file.getAbsolutePath());
        }
    }

    public void searchKey(ActionEvent actionEvent) {
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Save Key");
        fileChooser.getExtensionFilters().addAll(
                new FileChooser.ExtensionFilter("txt", "*.txt")
        );
        File file = fileChooser.showSaveDialog(Main.stage);
        if (file != null) {
            TextFieldSaveKey.setText(file.getAbsolutePath());
        }
    }

    public void searchPicture(ActionEvent actionEvent) {
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Save Image");
        fileChooser.getExtensionFilters().addAll(
                new FileChooser.ExtensionFilter("All Images", "*.*"),
                new FileChooser.ExtensionFilter("JPG", "*.jpg"),
                new FileChooser.ExtensionFilter("PNG", "*.png"),
                new FileChooser.ExtensionFilter("GIF", "*.gif")
        );
        File file = fileChooser.showSaveDialog(Main.stage);
        if (file != null) {
            TextFieldSavePicture.setText(file.getAbsolutePath());
        }

    }
}
