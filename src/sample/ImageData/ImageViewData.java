package sample.ImageData;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;

/**
 * Created by Andriy on 05.02.2016.
 */

//цей клас представляє собою картинку і те в чому вона буде відображатися також ідентифікує її
public class ImageViewData {

    private static int id = 0;

    private ImageView imageView;
    private BorderPane borderPane;
    private Image originalImage;
    private Image operatedImage;

    private boolean isSelected = false;
    private boolean isOperated = false;


    private String path = null;

    public ImageViewData(Image image, BorderPane borderPane) {
        this.originalImage = image;
        this.borderPane = borderPane;
        isSelected = false;
    }

    public ImageViewData(String path, Image image, double dx) {
        originalImage = image;
        this.path = path;
        isSelected = false;
        imageView = new ImageView();

        imageView.setFitHeight(100);
        imageView.setFitWidth(100);
        imageView.setX(5);
        imageView.setY(5);
        imageView.setImage(originalImage);
        imageView.setStyle("-fx-backgtound: white;");

        borderPane = new BorderPane();
        borderPane.setPrefHeight(110);
        borderPane.setPrefWidth(110);
        borderPane.setLayoutX(dx);
        borderPane.getStyleClass().setAll("borderPane");

        borderPane.getChildren().add(imageView);

    }


    public Image getOperatedImage() {
        return operatedImage;
    }

    public void setOperatedImage(Image operatedImage) {
        this.operatedImage = operatedImage;
    }

    public boolean isSelected() {
        return isSelected;
    }

    public boolean isOperated() {
        return isOperated;
    }

    public void setOperated(boolean operated) {
        isOperated = operated;
    }

    public void setSelected(boolean selected) {
        isSelected = selected;
        if (isSelected) {
            borderPane.setStyle("-fx-border-color : blue;-fx-beckground-color : white;");
        } else borderPane.setStyle("-fx-border-color : red;-fx-beckground-color : white;");
    }

    public BorderPane getBorderPane() {
        return borderPane;
    }

    public Image getOriginalImage() {
        return originalImage;
    }

    public void setOriginalImage(Image originalImage) {
        this.originalImage = originalImage;
    }

    public static int getId() {
        return id;
    }

    public static void setId(int id) {
        ImageViewData.id = id;
    }

    public String getPath() {
        return path;
    }
}

