package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.LinkedList;
import java.util.List;

import passwordReminder.Account;
import passwordReminder.Utils;

public class MySqlAccountDao implements AccountDao {
    private static final String DRIVER = "com.mysql.jdbc.Driver";
    private static final String URL = "jdbc:mysql://contac01.mysql.ukraine.com.ua/contac01_prilosh";
    private static final String LOGIN = "contac01_prilosh";
    private static final String PASS = "4h57futz";

    private Connection getConnection() {
        try {
            Class.forName(DRIVER);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        Connection c = null;
        try {
            c = DriverManager.getConnection(URL, LOGIN, PASS);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return c;
    }

    @Override
    public List<Account> get() {
        List<Account> out = new LinkedList<>();
        Connection c = getConnection();
        try {
            Statement st = c.createStatement();
            ResultSet rs = st.executeQuery("SELECT * FROM pili_pass");
            while (rs.next()) {
                out.add(new Account(rs.getString("adress"),
                        rs.getString("login"),
                        rs.getString("pass")));
            }
            st.close();
            c.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return out;
    }

    @Override
    public List<Account> add() {
        Connection c = getConnection();
        try {
            String log = "INSERT INTO pili_pass (adress,login,pass)" + "VALUES(?,?,?);";
            PreparedStatement ps = c.prepareStatement(log);
            ps.setString(1, Utils.accounts.get(Utils.accounts.size() - 1).getAdress());
            ps.setString(2, Utils.accounts.get(Utils.accounts.size() - 1).getLogin());
            ps.setString(3, Utils.accounts.get(Utils.accounts.size() - 1).getPass());

            ps.executeUpdate();
            ps.close();
            c.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public List<Account> delete() {
        Connection c = getConnection();
        try {
            String log = "DELETE FROM pili_pass WHERE adress = '" + Utils.accounts.get(Utils.accounts.size() - 1).getAdress() + "'";
            System.out.println(Utils.accounts.get(Utils.accounts.size() - 1).getAdress());
            PreparedStatement ps = c.prepareStatement(log);
            ps.executeUpdate();
            ps.close();
            c.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }


}
