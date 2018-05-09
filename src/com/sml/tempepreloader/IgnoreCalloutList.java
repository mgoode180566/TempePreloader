/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sml.tempepreloader;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import org.apache.log4j.Logger;

/**
 *
 * @author michaelgoode
 */
public class IgnoreCalloutList extends ArrayList {

    private static final Logger log = Logger.getLogger(IgnoreCalloutList.class.getName());
    private static final IgnoreCalloutList instance = new IgnoreCalloutList();

    public static IgnoreCalloutList getInstance() {
        return instance;
    }

    public void load() {
        try {

            this.clear();

            DB db = DB.getInstance();

            Connection conn = db.getConnection();

            String sql = "select * from XITE_Ignore_Callouts";

            PreparedStatement ps = conn.prepareStatement(sql);

            ResultSet rs = ps.executeQuery();

            while (rs.next()) {

                if (rs.getBoolean("Active")) {

                    this.add(rs.getString("Callout"));

                }

            }

        } catch (SQLException ex) {

            log.debug(ex.getMessage());

        }

    }
}
