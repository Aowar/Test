package org.leaarn_school.app.ui;

import org.leaarn_school.app.entity.ServiceEntity;
import org.leaarn_school.app.manager.ServiceEntityManager;
import org.leaarn_school.app.util.BaseForm;
import org.leaarn_school.app.util.CustomTableModel;
import org.leaarn_school.app.util.DialogUtil;

import javax.swing.*;
import javax.swing.table.TableColumn;
import java.awt.event.ItemEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.SQLException;
import java.util.Comparator;
import java.util.List;

public class ServiceTableForm extends BaseForm {
    private JTable table;
    private JPanel mainPanel;
    private JComboBox comboBox;
    private JButton sortButton;
    private JLabel amountLabel;
    private CustomTableModel<ServiceEntity> model;
    private boolean sortFlag = false;

    public ServiceTableForm() {
        super(1200, 800);
        setContentPane(mainPanel);
        initTable();
        initButtons();
        initBoxes();

        setVisible(true);
    }

    private void initTable() {
        try {
            model = new CustomTableModel<>(
                    ServiceEntity.class,
                    new String[]{"ID", "Наименование услуги", "Стоимость", "Длительность", "Описание", "Скидка", "Дата", "Путь к картинке", "Картинка"},
                    ServiceEntityManager.selectALl()
            );
        } catch (SQLException throwables) {
            throwables.printStackTrace();
            DialogUtil.ShowError(this, "Ошибка вывода таблицы\n" + throwables.getMessage());
        }
        table.setRowHeight(50);
        table.setModel(model);
        table.getTableHeader().setReorderingAllowed(false);
        TableColumn column1 = table.getColumn("ID");
        column1.setMinWidth(0);
        column1.setMaxWidth(0);
        column1.setPreferredWidth(0);

        TableColumn column2 = table.getColumn("Путь к картинке");
        column2.setMinWidth(0);
        column2.setMaxWidth(0);
        column2.setPreferredWidth(0);

        table.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                if (e.getButton() == MouseEvent.BUTTON2) {
                    int row = table.rowAtPoint(e.getPoint());
                    if (row != -1) {
                        dispose();
                        new ServiceUpdateForm(model.getRows().get(row));
                    }
                }
            }
        });
    }

    private void initBoxes() {
        comboBox.addItemListener(e -> {
            if (e.getStateChange() == ItemEvent.SELECTED) {
                try {
                    List<ServiceEntity> list = ServiceEntityManager.selectALl();
                    switch (comboBox.getSelectedIndex()) {
                        case 1 -> list.removeIf(s -> s.getDiscount() > 5);
                        case 2 -> list.removeIf(s -> s.getDiscount() <= 5 || s.getDiscount() > 15);
                        case 3 -> list.removeIf(s -> s.getDiscount() <= 15 || s.getDiscount() > 30);
                        case 4 -> list.removeIf(s -> s.getDiscount() <= 30 || s.getDiscount() > 70);
                        case 5 -> list.removeIf(s -> s.getDiscount() <= 70);
                    }
                    model.setRows(list);
                    model.fireTableDataChanged();
                } catch (SQLException throwables) {
                    throwables.printStackTrace();
                    DialogUtil.ShowError(this, "Ошибка фильтрации");
                }
            }
        });
    }

    private void initButtons() {
        sortButton.addActionListener(e -> {
            if(sortFlag) {
                model.getRows().sort(new Comparator<ServiceEntity>() {
                    @Override
                    public int compare(ServiceEntity o1, ServiceEntity o2) {
                        return Double.compare(o1.getCost(), o2.getCost());
                    }
                });
            } else {
                model.getRows().sort(new Comparator<ServiceEntity>() {
                    @Override
                    public int compare(ServiceEntity o1, ServiceEntity o2) {
                        return Double.compare(o2.getCost(), o1.getCost());
                    }
                });
            }
            model.fireTableDataChanged();
            sortFlag = !sortFlag;
        });
    }
}