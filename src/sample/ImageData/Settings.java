package sample.ImageData;

import sample.algorythms.typesOfInput.Algorythms;
import sample.algorythms.typesOfInput.Keys;
import sample.styles.Styles;

import java.io.File;

/**
 * Created by Andriy on 18.02.2016.
 */

//клас який описуя налаштування роботи програми для кожного зображення
public class Settings {


    public static final String MODERNSTYLE = getURI("D:\\4 level part 1\\ImageEncryption 2.0\\src\\sample\\styles\\modern.css");
    public static final String BRIGHTSTYLE = getURI("D:\\4 level part 1\\ImageEncryption 2.0\\src\\sample\\styles\\bright.css");
    public static final String SIMPLETSTYLE = getURI("D:\\4 level part 1\\ImageEncryption 2.0\\src\\sample\\styles\\simple.css");


    private boolean encryptOperation = true;
    private Algorythms algorythm = Algorythms.XOR;
    private Keys typeOfInputOperation = Keys.InputFromKeyboard;

    private Styles style = Styles.MODERN;

    public boolean isEncryptOperation() {
        return encryptOperation;
    }

    public void setEncryptOperation(boolean encryptOperation) {
        this.encryptOperation = encryptOperation;
    }

    public Algorythms getAlgorythm() {
        return algorythm;
    }

    public void setAlgorythm(Algorythms algorythm) {
        this.algorythm = algorythm;
    }

    public Keys getTypeOfInputOperation() {
        return typeOfInputOperation;
    }

    public void setTypeOfInputOperation(Keys typeOfInputOperation) {
        this.typeOfInputOperation = typeOfInputOperation;
    }

    public Styles getStyle() {
        return style;
    }

    public void setStyle(Styles style) {
        this.style = style;
    }

    private static String getURI(String source) {
        File f = new File(source);
        return f.toURI().toString();
    }

    public String getCurrentStyleURI() {
        return getURIStyle(getStyle());
    }

    public String getURIStyle(Styles styles) {
        switch (styles) {
            case BRIGHT:
                return BRIGHTSTYLE;
            case MODERN:
                return MODERNSTYLE;
            case SIMPLE:
                return SIMPLETSTYLE;
        }
        return null;
    }
}

