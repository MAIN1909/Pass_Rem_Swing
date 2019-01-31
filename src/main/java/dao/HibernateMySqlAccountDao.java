package dao;

import Hibernate.HibernateUtil;
import org.hibernate.Session;
import passwordReminder.Account;
import passwordReminder.Utils;

import java.util.List;

public class HibernateMySqlAccountDao implements AccountDao {
    @Override
    public List<Account> get() {
        Session s = HibernateUtil.getSessionFactory().openSession();
        List<Account> out = s.createCriteria(Account.class).list();
        s.close();
        return out;
    }

    @Override
    public List<Account> add() {
        Session s = HibernateUtil.getSessionFactory().openSession();
        s.beginTransaction();
        s.save(Utils.accounts.get(Utils.accounts.size() - 1));
        s.getTransaction().commit();
        s.close();
        return null;
    }

    @Override
    public List<Account> delete() {
        Session s = HibernateUtil.getSessionFactory().openSession();
        s.createQuery("delete Account where adress = '" + Utils.accounts.get(Utils.accounts.size() - 1).getAdress() + "'").executeUpdate();
        s.close();
        return null;
    }
}
