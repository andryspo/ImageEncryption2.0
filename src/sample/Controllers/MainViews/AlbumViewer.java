package sample.Controllers.MainViews;

import javafx.event.EventHandler;
import javafx.scene.control.ScrollPane;
import javafx.scene.image.Image;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import sample.ImageData.ImageViewData;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.ListIterator;

/**
 * Created by Andriy on 16.04.2016.
 */
public class AlbumViewer {
    private AnchorPane anchorPane;
    private ScrollPane scrollPane;
    private LinkedList<ImageViewData> datas;
    private OriginalAndOperatedImagesViews viewer;
    private AnchorPane mainPanel;
    private double dx = 0;
    private final double step = 110d;

    public AlbumViewer(AnchorPane anchorPane, ScrollPane scrollPane, LinkedList<ImageViewData> datas, OriginalAndOperatedImagesViews view, AnchorPane mainPanel) {
        this.anchorPane = anchorPane;
        this.scrollPane = scrollPane;
        this.datas = datas;
        this.viewer = view;
        this.mainPanel = mainPanel;
    }

    public void Add(final ImageViewData image) {
        if (datas.size() == 0) {
            image.setSelected(true);
            if (image.isOperated())
                viewer.getOperatedImage().setImage(image.getOperatedImage());
            viewer.getOriginalImage().setImage(image.getOriginalImage());
            ShowerImagesView.setImages(viewer.getOriginalImage(), viewer.getOperatedImage(), image, mainPanel);
        }
        datas.add(image);
        anchorPane.setPrefWidth(anchorPane.getPrefWidth() + step);
        anchorPane.getChildren().add(image.getBorderPane());
        scrollPane.setContent(anchorPane);
        dx += step;
        image.getBorderPane().setOnMousePressed(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                viewer.getOriginalImage().setImage(image.getOriginalImage());
                if (image.isOperated()) {
                    viewer.getOperatedImage().setImage(image.getOperatedImage());
                }
                ShowerImagesView.setImages(viewer.getOriginalImage(), viewer.getOperatedImage(), image, mainPanel);
                image.setSelected(true);
                for (ImageViewData tmp : datas) {
                    if (tmp.equals(image) == false) {
                        tmp.getBorderPane().setStyle("-fx-border-color : red;");
                        tmp.setSelected(false);
                    }
                }
            }
        });
        image.getBorderPane().setOnMouseEntered(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                if (!image.isSelected()) {
                    image.getBorderPane().setStyle("-fx-border-color : green;");
                }
            }
        });
        image.getBorderPane().setOnMouseExited(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                if (!image.isSelected()) {
                    image.getBorderPane().setStyle("-fx-border-color : red;");
                }
            }
        });
    }

    public void removeAll() {
        dx = 0;
        for (ImageViewData tmp : datas) {
            anchorPane.getChildren().remove(tmp.getBorderPane());
        }
        anchorPane.setPrefWidth(dx);
        datas.removeAll(datas);

    }

    public void Remove(ImageViewData image) {
        ListIterator<ImageViewData> iterator = datas.listIterator();
        while (iterator.hasNext()) {
            ImageViewData tmp = iterator.next();
            if (tmp.isSelected()) {
                dx -= step;
                anchorPane.getChildren().remove(tmp.getBorderPane());
                anchorPane.setPrefWidth(anchorPane.getPrefWidth() - step);
                iterator.remove();
                if (iterator.hasNext()) {
                    tmp = iterator.next();
                    tmp.setSelected(true);
                    if (iterator.hasPrevious())
                        iterator.previous();


                } else if (iterator.hasPrevious()) {
                    tmp = iterator.previous();
                    tmp.setSelected(true);
                    if (iterator.hasNext())
                        iterator.next();
                }
                while (iterator.hasNext()) {
                    ImageViewData move = iterator.next();
                    move.getBorderPane().setLayoutX(move.getBorderPane().getLayoutX() - step);
                }
                break;
            }
        }
        if (datas.isEmpty()) {
            if (viewer.getOperatedImage() != null) {
                viewer.getOperatedImage().setImage(null);
            }
            viewer.getOriginalImage().setImage(null);
        }
    }

    public void next() {
        Iterator<ImageViewData> iterator = datas.iterator();
        while (iterator.hasNext()) {
            ImageViewData tmp = iterator.next();
            if (tmp.isSelected()) {
                if (iterator.hasNext()) {
                    tmp.setSelected(false);
                    tmp.getBorderPane().setStyle("-fx-border-color : red;");
                    tmp = iterator.next();
                    viewer.getOriginalImage().setImage(tmp.getOriginalImage());
                    if (tmp.getOperatedImage() != null) {
                        viewer.getOperatedImage().setImage(tmp.getOperatedImage());
                    }
                    ShowerImagesView.setImages(viewer.getOriginalImage(), viewer.getOperatedImage(), tmp, mainPanel);
                    tmp.setSelected(true);
                    break;
                }
            }
        }
    }

    public void previus() {
        ListIterator<ImageViewData> iterator = datas.listIterator();
        while (iterator.hasNext())
            iterator.next();
        while (iterator.hasPrevious()) {
            ImageViewData tmp = iterator.previous();
            if (tmp.isSelected()) {
                if (iterator.hasPrevious()) {
                    tmp.setSelected(false);
                    tmp.getBorderPane().setStyle("-fx-border-color : red;");
                    tmp = iterator.previous();
                    viewer.getOriginalImage().setImage(tmp.getOriginalImage());
                    if (tmp.getOperatedImage() != null) {
                        viewer.getOperatedImage().setImage(tmp.getOperatedImage());
                    }
                    ShowerImagesView.setImages(viewer.getOriginalImage(), viewer.getOperatedImage(), tmp, mainPanel);
                    tmp.setSelected(true);
                    break;
                }
            }
        }
    }

    public double getDx() {
        return dx;
    }

    public void setRunInitDatas() {
        viewer.getOriginalImage().setLayoutX(50);
        mainPanel.getChildren().remove(viewer.getOperatedImage());
    }

    public void setRunResDatas(Image image) {
        viewer.getOperatedImage().setImage(image);
        viewer.getOperatedImage().setPreserveRatio(true);
        viewer.getOperatedImage().setFitWidth(250);
        viewer.getOperatedImage().setFitHeight(250);
        viewer.getOperatedImage().setLayoutY(210);
        viewer.getOperatedImage().setLayoutX(330);
        mainPanel.getChildren().add(viewer.getOperatedImage());
    }
}
