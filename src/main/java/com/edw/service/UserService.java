package com.edw.service;

import com.edw.helper.DatabaseHelper;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

/**
 * <pre>
 *  com.edw.service.UserService
 * </pre>
 *
 * @author Muhammad Edwin < edwin at redhat dot com >
 * 20 May 2025 23:16
 */

public class UserService {

    private DatabaseHelper databaseHelper;

    public UserService() {
    }

    public List<Map> select(String customerId, Integer start, Integer limit) {

        List<Map> list = new ArrayList<>();

        databaseHelper = new DatabaseHelper(customerId);
        String sql = "select * from tb_testing order by id limit ?, ?";

        try (Connection conn = databaseHelper.getConnection();
             PreparedStatement preparedStmt = conn.prepareStatement(sql)) {

            preparedStmt.setInt(1, start);
            preparedStmt.setInt(2, limit);

            try (ResultSet resultSet = preparedStmt.executeQuery()) {
                while (resultSet.next()) {
                    Integer id = resultSet.getInt(1);
                    String username = resultSet.getString(2);

                    list.add(new HashMap() {{
                        put("id", id);
                        put("username", username);
                    }});
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return list;
    }

}
