package org.leaarn_school.app.util;

import javax.swing.*;
import java.awt.*;

public class DialogUtil {
    public static void ShowInfo(Component component, String message) {
        JOptionPane.showMessageDialog(component, message, "Информация", JOptionPane.INFORMATION_MESSAGE);
    }
    public static void ShowError(Component component, String message) {
        JOptionPane.showMessageDialog(component, message, "Ошибка", JOptionPane.ERROR_MESSAGE);
    }
}
