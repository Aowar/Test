package org.leaarn_school.app.manager;

import org.leaarn_school.app.App;
import org.leaarn_school.app.entity.ServiceEntity;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ServiceEntityManager {
    public static List<ServiceEntity> selectALl() throws SQLException {
        Connection c = App.getConnection();
        String sql = "SELECT * FROM Service";
        Statement s = c.createStatement();
        ResultSet rs = s.executeQuery(sql);
        List<ServiceEntity> list = new ArrayList<>();

        while(rs.next()) {
            list.add(new ServiceEntity(
                    rs.getInt("ID"),
                    rs.getString("Title"),
                    rs.getDouble("Cost"),
                    rs.getInt("Duration"),
                    rs.getString("Description"),
                    rs.getInt("Discount"),
                    rs.getDate("date"),
                    rs.getString("MainImagePath")
            ));
        }
        return list;
    }

    public static void insert(ServiceEntity serviceEntity) throws SQLException {
        Connection c = App.getConnection();
        String sql = "INSERT INTO Service(Title, Cost, Duration, Description, Discount, date, MainImagePath) VALUES(?,?,?,?,?,?,?)";
        PreparedStatement ps = c.prepareStatement(sql, PreparedStatement.RETURN_GENERATED_KEYS);
        ps.setString(1, serviceEntity.getTitle());
        ps.setDouble(2, serviceEntity.getCost());
        ps.setInt(3, serviceEntity.getDuration());
        ps.setString(4, serviceEntity.getDesc());
        ps.setInt(5, serviceEntity.getDiscount());
        ps.setTimestamp(6, new Timestamp(serviceEntity.getDate().getTime()));
        ps.setString(7, serviceEntity.getImagePath());
        ps.executeUpdate();

        ResultSet keys = ps.getGeneratedKeys();
        if (keys.next()) {
            serviceEntity.setId(keys.getInt(1));
            return;
        }
        throw new SQLException("Entity not added");
    }

    public static void update(ServiceEntity serviceEntity) throws SQLException {
        Connection c = App.getConnection();
        String sql = "UPDATE Service SET Title=?, Cost=?, Duration=?, Description=?, Discount=?, date=?, MainImagePath=? WHERE ID=?";
        PreparedStatement ps = c.prepareStatement(sql);
        ps.setString(1, serviceEntity.getTitle());
        ps.setDouble(2, serviceEntity.getCost());
        ps.setInt(3, serviceEntity.getDuration());
        ps.setString(4, serviceEntity.getDesc());
        ps.setInt(5, serviceEntity.getDiscount());
        ps.setTimestamp(6, new Timestamp(serviceEntity.getDate().getTime()));
        ps.setString(7, serviceEntity.getImagePath());
        ps.setInt(8, serviceEntity.getId());
        ps.executeUpdate();
    }

    public static void delete(ServiceEntity serviceEntity) throws SQLException {
        Connection c = App.getConnection();
        String sql = "DELETE FROM Service WHERE ID=?";
        PreparedStatement ps = c.prepareStatement(sql);
        ps.setInt(1, serviceEntity.getId());
        ps.executeUpdate();
    }
}
