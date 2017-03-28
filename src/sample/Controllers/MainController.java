package sample.Controllers;

import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ScrollPane;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.stage.FileChooser;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;
import sample.Controllers.MainViews.AlbumViewer;
import sample.Controllers.MainViews.OriginalAndOperatedImagesViews;
import sample.Controllers.MainViews.ShowerImagesView;
import sample.ImageData.ImageViewData;
import sample.ImageData.Settings;
import sample.Main;
import sample.MenuComands.*;
import sample.algorythms.Keys.KeyCreator;

import javax.swing.*;
import java.io.File;
import java.io.IOException;
import java.util.LinkedList;
import java.util.List;

public class MainController {
    @FXML
    public AnchorPane mainPanel;
    @FXML
    private AnchorPane imagesPanel;
    @FXML
    private ScrollPane mainImagesPanel;
    @FXML
    private ImageView previusImg;
    private ImageView resultImageView = new ImageView();
    ;
    private OriginalAndOperatedImagesViews imagesViewer = null;
    private AlbumViewer viewer;


    //тут будуть зберігатися всі картинки що відображуються на AnchorPane
    private LinkedList<ImageViewData> imageViewDatas;
    public static Settings setting;
    public static KeyCreator keyCreator;

    @FXML
    public void initialize() {

        imagesPanel.setStyle("-fx-background-color: white");
        mainImagesPanel.setStyle("-fx-background-color: #2f4b8f");
        setting = new Settings();
        keyCreator = new KeyCreator();
        previusImg.setFitHeight(250);
        previusImg.setFitWidth(250);
        resultImageView.setEffect(null);
        imageViewDatas = new LinkedList<ImageViewData>();
        imagesViewer = new OriginalAndOperatedImagesViews(previusImg, resultImageView);
        viewer = new AlbumViewer(imagesPanel, mainImagesPanel, imageViewDatas, imagesViewer, mainPanel);
        mainPanel.getStylesheets().removeAll(mainPanel.getStylesheets());
        mainPanel.getStylesheets().add(setting.getCurrentStyleURI());

    }

    public void add(ActionEvent actionEvent) {
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Open Image");
        configureFileChooser(fileChooser);
        List<File> fileList = fileChooser.showOpenMultipleDialog(Main.stage);
        if (fileList != null)
            //цикл в якому додаємо картинки в програму
            for (File file : fileList) {
                final ImageViewData imageViewData = new ImageViewData(file.getPath(), new Image(file.toURI().toString()), viewer.getDx());
                viewer.Add(imageViewData);
            }
    }

    public void scrollRight(Event event) {
        viewer.next();
    }

    public void scrollLeft(ActionEvent actionEvent) {
        viewer.previus();
    }

    public void removePictures(ActionEvent actionEvent) {
        if (imageViewDatas != null && !imageViewDatas.isEmpty()) {
            viewer.Remove(getSelectedimage());
            ShowerImagesView.setImages(previusImg, resultImageView, getSelectedimage(), mainPanel);
        }
    }

    private void configureFileChooser(
            final FileChooser fileChooser) {
        fileChooser.setTitle("View Pictures");
        fileChooser.setInitialDirectory(
                new File("D:\\")
        );
        fileChooser.getExtensionFilters().addAll(
                new FileChooser.ExtensionFilter("All Images", "*.*"),
                new FileChooser.ExtensionFilter("JPG", "*.jpg"),
                new FileChooser.ExtensionFilter("PNG", "*.png"),
                new FileChooser.ExtensionFilter("GIF", "*.gif")
        );
    }

    public void encrypt(ActionEvent actionEvent) throws IOException {
        if (imageViewDatas != null && !imageViewDatas.isEmpty()) {
            if (keyCreator.getKey() == null || keyCreator.getKey().length == 0) {
                JOptionPane.showMessageDialog(null, "Please, enter key!");
                return;
            }
            ImageViewData currentImageViewData = getSelectedimage();
            viewer.setRunInitDatas();
            new Encryptor(setting, keyCreator, currentImageViewData).execute();
            assert currentImageViewData != null;
            viewer.setRunResDatas(currentImageViewData.getOperatedImage());
        }
    }


    public void setSetting(ActionEvent actionEvent) throws IOException {
        new SettingCommand(mainPanel).execute();
    }


    public void decrypt(ActionEvent actionEvent) throws IOException {
        if (imageViewDatas != null && !imageViewDatas.isEmpty()) {
            ImageViewData currentImageViewData = getSelectedimage();
            viewer.setRunInitDatas();
            new Decryptor(setting, keyCreator, currentImageViewData).execute();
            viewer.setRunResDatas(currentImageViewData.getOperatedImage());
        }
    }

    public void saveSelected(ActionEvent actionEvent) throws IOException {
        if (imageViewDatas != null && !imageViewDatas.isEmpty())
            new SelectedSaver(getSelectedimage(), imagesPanel, keyCreator, setting.getAlgorythm()).execute();

    }

    public ImageViewData getSelectedimage() {
        for (ImageViewData imageViewData : imageViewDatas) {
            if (imageViewData.isSelected())
                return imageViewData;
        }
        return null;
    }

    public void showAllResult(ActionEvent actionEvent) throws IOException {
        new AllResultShower(setting, imageViewDatas, mainPanel, keyCreator).execute();
    }

    public void currentRsult(ActionEvent actionEvent) throws IOException {
        if (imageViewDatas != null && !imageViewDatas.isEmpty())
            new CurrentResultShower(setting, getSelectedimage(), mainPanel, keyCreator).execute();
    }

    public void addImage(ActionEvent actionEvent) {
        add(null);
    }

    public void deleteSelectedImage(ActionEvent actionEvent) {
        removePictures(null);
    }

    public void closeProgram(ActionEvent actionEvent) {
        Main.stage.close();
    }

    public void deleteAllImages(ActionEvent actionEvent) {
        previusImg.setImage(null);
        resultImageView.setImage(null);
        viewer.removeAll();
    }

    public void saveAllOperated(ActionEvent actionEvent) throws IOException {

        new AllResultSaver(imageViewDatas, imagesPanel, keyCreator, setting.getAlgorythm()).execute();
    }

    public void operateCurrent(ActionEvent actionEvent) throws IOException {
        if (imageViewDatas != null && !imageViewDatas.isEmpty())
            if (setting.isEncryptOperation()) {
                encrypt(null);
            } else {
                decrypt(null);
            }
    }

    public void operateAll(ActionEvent actionEvent) throws IOException {
        if (imageViewDatas != null && !imageViewDatas.isEmpty())
            if (setting.isEncryptOperation()) {
                getSelectedimage().setSelected(false);
                for (ImageViewData image : imageViewDatas) {
                    image.setSelected(true);
                    encrypt(null);
                    image.setSelected(false);
                }
            }
        imageViewDatas.getLast().setSelected(true);
    }

    public void encryptCurrent(ActionEvent actionEvent) throws IOException {
        if (imageViewDatas != null && !imageViewDatas.isEmpty())
            encrypt(null);
    }

    public void decryptCurrent(ActionEvent actionEvent) throws IOException {
        if (imageViewDatas != null && !imageViewDatas.isEmpty())
            decrypt(null);
    }

    public void about(ActionEvent actionEvent) throws IOException {
        new AboutCommand(mainPanel).execute();
    }

    public void help(ActionEvent actionEvent) throws IOException {
        new HelpCommand(mainPanel).execute();
    }

    public void swap(ActionEvent actionEvent) throws IOException {
        if (imageViewDatas != null && !imageViewDatas.isEmpty())
            new SwapCommand(getSelectedimage(), previusImg, resultImageView).execute();
    }

    public void changeTheme(ActionEvent actionEvent) throws IOException {

        Stage stage = new Stage();
        FXMLLoader loader = new FXMLLoader();
        Parent root = loader.load(getClass().getResource("../FXMLfiles/theme.fxml"));
        stage.setTitle("Result:");
        stage.setResizable(false);
        stage.setScene(new Scene(root));
        stage.initModality(Modality.WINDOW_MODAL);
        stage.initOwner(mainPanel.getScene().getWindow());
        stage.setResizable(true);
        stage.show();
        stage.setOnCloseRequest(new EventHandler<WindowEvent>() {
            @Override
            public void handle(WindowEvent event) {
                mainPanel.getStylesheets().removeAll(mainPanel.getStylesheets());
                mainPanel.getStylesheets().add(setting.getCurrentStyleURI());
            }
        });
    }
}