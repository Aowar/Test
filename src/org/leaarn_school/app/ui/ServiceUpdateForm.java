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

public class ServiceUpdateForm extends BaseForm {
    private JPanel mainPanel;
    private JTextField titleField;
    private JSpinner durationSpinner;
    private JTextField costField;
    private JTextField descField;
    private JTextField pathField;
    private JSpinner discountSpinner;
    private JButton okButton;
    private JButton cancelButton;
    private JButton deleteButton;
    private JTextField idField;
    private JTextField dateField;
    private ServiceEntity serviceEntity;


    public ServiceUpdateForm(ServiceEntity serviceEntity) {
        super(1200, 800);
        this.serviceEntity = serviceEntity;
        setContentPane(mainPanel);
        initFields();
        initButtons();
        setVisible(true);
    }

    private void initFields() {
        idField.setText(String.valueOf(serviceEntity.getId()));
        idField.setEditable(false);
        titleField.setText(serviceEntity.getTitle());
        durationSpinner.setValue(serviceEntity.getDuration());
        costField.setText(String.valueOf(serviceEntity.getCost()));
        descField.setText(serviceEntity.getDesc());
        pathField.setText(serviceEntity.getImagePath());
        dateField.setText(String.valueOf(serviceEntity.getDate()));
        discountSpinner.setValue(serviceEntity.getDiscount());
    }

    private void initButtons() {
        okButton.addActionListener(e -> {
            String title = titleField.getText();
            int duration = (int) durationSpinner.getValue();
            double cost = Double.parseDouble(costField.getText());
            String desc = descField.getText();
            String imagePath = pathField.getText();
            int discount = (int) discountSpinner.getValue();

            serviceEntity.setTitle(title);
            serviceEntity.setDuration(duration);
            serviceEntity.setCost(cost);
            serviceEntity.setDesc(desc);
            serviceEntity.setImagePath(imagePath);
            serviceEntity.setDiscount(discount);
            Date date = null;
            try {
                date = App.DATE_FORMAT.parse(dateField.getText());
            } catch (ParseException parseException) {
                parseException.printStackTrace();
                DialogUtil.ShowError(this, "Ошибка в дате");
            }
            serviceEntity.setDate(date);

            try {
                ServiceEntityManager.update(serviceEntity);
                DialogUtil.ShowInfo(this, "Услуга изменена");
            } catch (SQLException throwables) {
                throwables.printStackTrace();
                DialogUtil.ShowError(this, "Ошибка изменения услуги \n" + throwables.getMessage());
            }
            dispose();
            new ServiceTableForm();
        });

        cancelButton.addActionListener(e -> {
            dispose();
            new ServiceTableForm();
        });

        deleteButton.addActionListener(e -> {
            if (JOptionPane.showConfirmDialog(this, "Вы точно хотите удалить услугу?", "Удалить услугу", JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION) {
                try {
                    ServiceEntityManager.delete(serviceEntity);
                    DialogUtil.ShowInfo(this, "Услуга удалена");
                } catch (SQLException throwables) {
                    throwables.printStackTrace();
                    DialogUtil.ShowError(this, "Ошибка удаления услуги \n" + throwables.getMessage());
                }
            }
            dispose();
            new ServiceTableForm();
        });
    }
}
