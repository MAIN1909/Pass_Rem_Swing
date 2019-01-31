
import Hibernate.HibernateUtil;
import swing.AuthWindow;


public class Main {
    public static void main(String[] args) {
        new AuthWindow().setVisible(true);
//        new passwordReminder.PasswordReminder();
        new HibernateUtil();

    }
}
