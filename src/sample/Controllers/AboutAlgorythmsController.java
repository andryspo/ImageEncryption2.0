package sample.Controllers;

import javafx.event.ActionEvent;

import java.awt.*;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;

/**
 * Created by Andriy on 21.05.2016.
 */
public class AboutAlgorythmsController {

    private final String XORLINK = "https://uk.wikipedia.org/wiki/%D0%92%D0%B8%D0%BA%D0%BB%D1%8E%D1%87%D0%BD%D0%B0_%D0%B4%D0%B8%D0%B7%27%D1%8E%D0%BD%D0%BA%D1%86%D1%96%D1%8F";
    private final String RC4LINK = "https://uk.wikipedia.org/wiki/RC4";
    private final String SALSA20LINK = "https://en.wikipedia.org/wiki/Salsa20";
    private final String RABBITLINK = "https://ru.wikipedia.org/wiki/Rabbit";

    public void xorPress(ActionEvent actionEvent) throws IOException, URISyntaxException {
        if (Desktop.isDesktopSupported()) {
            Desktop.getDesktop().browse(new URI(XORLINK));
        }
    }

    public void salsa20Press(ActionEvent actionEvent) throws URISyntaxException, IOException {
        if (Desktop.isDesktopSupported()) {
            Desktop.getDesktop().browse(new URI(SALSA20LINK));
        }
    }

    public void RC4Press(ActionEvent actionEvent) throws URISyntaxException, IOException {
        if (Desktop.isDesktopSupported()) {
            Desktop.getDesktop().browse(new URI(RC4LINK));
        }
    }

    public void RabbitPress(ActionEvent actionEvent) throws URISyntaxException, IOException {
        if (Desktop.isDesktopSupported()) {
            Desktop.getDesktop().browse(new URI(RABBITLINK));
        }
    }

}
