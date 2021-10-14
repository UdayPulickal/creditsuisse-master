package com.credisuisstest;

import org.hsqldb.jdbcDriver;

import java.sql.*;


public class Dbaccess
{
public Connection getConnection()
        {
        String url = "jdbc:hsqldb:file:db-data/mydatabase";
        String username = "SA";
        String password = "";
        Connection con = null;
        try
        {
        Class.forName("org.hsqldb.jdbc.JDBCDriver");
        }
        catch (ClassNotFoundException e1)
        {
        e1.printStackTrace();
        }
        try
        {
        con = DriverManager.getConnection(url, username, password);
                DriverManager.registerDriver(new jdbcDriver());
        }
        catch (Exception e)
        {
        e.printStackTrace();
        }
        return con;
        }
        }
