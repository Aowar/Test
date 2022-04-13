package org.leaarn_school.app.util;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.io.IOException;

public class BaseForm extends JFrame {
    private static String APP_TITLE = "Test";
    private static Image APP_ICON = null;

    static {
        try {
            APP_ICON = ImageIO.read(BaseForm.class.getClassLoader().getResource("school_logo.png"));
        } catch (IOException e) {
            e.printStackTrace();
            DialogUtil.ShowError(null, "Ошибка вывода иконки");
        }
    }

    public BaseForm(int width, int height) {
        setMinimumSize(new Dimension(width, height));
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocation(Toolkit.getDefaultToolkit().getScreenSize().width/2 - width/2, Toolkit.getDefaultToolkit().getScreenSize().height/2 - height/2);
        setIconImage(APP_ICON);
        setTitle(APP_TITLE);
    }
}
