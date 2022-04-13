package org.leaarn_school.app.ui;

import org.leaarn_school.app.App;
import org.leaarn_school.app.entity.ServiceEntity;
import org.leaarn_school.app.manager.ServiceEntityManager;
import org.leaarn_school.app.util.BaseForm;
import org.leaarn_school.app.util.DialogUtil;

import javax.swing.*;
import java.sql.SQLException;
import java.text.ParseException;
import java.util.Date;

import static org.leaarn_school.app.App.DATE_FORMAT;

public class ServiceCreateForm extends BaseForm {
    private JPanel mainPanel;
    private JTextField titleField;
    private JSpinner durationSpinner;
    private JTextField costField;
    private JTextField descField;
    private JTextField pathField;
    private JSpinner discountSpinner;
    private JButton okButton;
    private JButton cancelButton;
    private JTextField dateField;
    private ServiceEntity serviceEntity;


    public ServiceCreateForm() {
        super(1200, 800);
        setContentPane(mainPanel);
        initButtons();
        setVisible(true);
    }

    private void initButtons() {
        okButton.addActionListener(e -> {
            String title = titleField.getText();
            int duration = (int) durationSpinner.getValue();
            double cost = Double.parseDouble(costField.getText());
            String desc = descField.getText();
            String imagePath = pathField.getText();
            Date date = null;
            try {
                date = DATE_FORMAT.parse(dateField.getText());
            } catch (ParseException parseException) {
                parseException.printStackTrace();
                DialogUtil.ShowError(this, "Ошибка обработки даты");
            }
            int discount = (int) discountSpinner.getValue();

            serviceEntity = new ServiceEntity(title, cost, duration, desc, discount, date, imagePath);

            try {
                ServiceEntityManager.insert(serviceEntity);
                DialogUtil.ShowInfo(this, "Услуга создана");
            } catch (SQLException throwables) {
                throwables.printStackTrace();
                DialogUtil.ShowError(this, "Ошибка создания услуги \n" + throwables.getMessage());
            }
            dispose();
            new ServiceTableForm();
        });

        cancelButton.addActionListener(e -> {
            dispose();
            new ServiceTableForm();
        });
    }
}
