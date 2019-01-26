package passwordReminder;

import dao.FileSystemAccountDao;
import dao.MySqlAccountDao;

import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

public class Utils {

    public static List<Account> accounts = new CopyOnWriteArrayList<>();


    public static List<Account> addToList(Account r) {
        accounts.add(r);
        return accounts;
    }

    public static String convertToString() {
//        FileSystemAccountDao dao = new FileSystemAccountDao();
        MySqlAccountDao dao = new MySqlAccountDao();
        StringBuilder builder = new StringBuilder();
        for (Account number : dao.get()) {
            builder.append(number.toString());
        }
        return builder.toString();
    }
}
