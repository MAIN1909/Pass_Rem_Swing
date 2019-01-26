package dao;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

import passwordReminder.Account;
import passwordReminder.Utils;

public class FileSystemAccountDao implements AccountDao {

    @Override
    public List<Account> get() {
        List<Account> out = new CopyOnWriteArrayList<>();
        String s;
        try {
            BufferedReader brAdress = new BufferedReader(new FileReader("E:\\NetBeansProjects\\Pass_Rem_Swing\\src\\Resources\\Adress.txt"));
            BufferedReader brLogin = new BufferedReader(new FileReader("E:\\NetBeansProjects\\Pass_Rem_Swing\\src\\Resources\\Login.txt"));
            BufferedReader brPass = new BufferedReader(new FileReader("E:\\NetBeansProjects\\Pass_Rem_Swing\\src\\Resources\\Pass.txt"));
            while (null != (s = brAdress.readLine())) {
                out.add(new Account(s, brLogin.readLine(), brPass.readLine()));
            }
            brAdress.close();
            brLogin.close();
            brPass.close();
        } catch (IOException ex) {
            ex.printStackTrace();
        }
        return out;
    }

    @Override
    public List<Account> add() {
        File adress = new File("E:\\NetBeansProjects\\Pass_Rem_Swing\\src\\Resources\\Adress.txt");
        File login = new File("E:\\NetBeansProjects\\Pass_Rem_Swing\\src\\Resources\\Login.txt");
        File pass = new File("E:\\NetBeansProjects\\Pass_Rem_Swing\\src\\Resources\\Pass.txt");
        try (PrintWriter pwAdress = new PrintWriter(adress);
             PrintWriter pwLogin = new PrintWriter(login);
             PrintWriter pwPass = new PrintWriter(pass)) {
            for (int i = 0; i < Utils.accounts.size(); i++) {
                pwAdress.println(Utils.accounts.get(i).getAdress());
                pwLogin.println(Utils.accounts.get(i).getLogin());
                pwPass.println(Utils.accounts.get(i).getPass());
            }
//            pwAdress.close();
//            pwLogin.close();
//            pwPass.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public List<Account> delete() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }


}
