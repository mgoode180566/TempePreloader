/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sml.tempepreloader;

import java.sql.Connection;
import java.sql.DriverManager;

/**
 *
 * @author michaelgoode
 */
public class DB {

    private static final DB instance = new DB();

    public static DB getInstance() {
        return instance;
    }

    public DB() {

    }

    public Connection getConnection() {

        Connection con = null;

        String RL = "jdbc:sqlserver://192.168.0.65:1489;DatabaseName=lookup";

        String user = "lookup_admin";

        String password = "sbit";

        try {

            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");

            con = DriverManager.getConnection(RL, user, password);

        } catch (Exception e) {
        }
        return con;


    }
}
