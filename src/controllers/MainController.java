package controllers;

import models.dao.DatabaseConnection;
import views.MainGUI;

import java.sql.SQLException;


public class MainController {
    public static void main(String[] args) throws SQLException {
        DatabaseConnection.initTables();
        new MainGUI();
    }
}
