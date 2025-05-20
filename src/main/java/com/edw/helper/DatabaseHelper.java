package com.edw.helper;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.SQLException;

/**
 * <pre>
 *  com.edw.helper.DatabaseHelper
 * </pre>
 *
 * @author Muhammad Edwin < edwin at redhat dot com >
 * 20 May 2025 23:12
 */

public class DatabaseHelper {

    private DataSource ds = null;

    public DatabaseHelper() {
    }

    public DatabaseHelper(String customerId) {
        try {
            Context initCxt = new InitialContext();
            ds = (DataSource) initCxt.lookup(String.format("java:/%s-ds", customerId));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public Connection getConnection() throws SQLException {
        return ds.getConnection();
    }
}
