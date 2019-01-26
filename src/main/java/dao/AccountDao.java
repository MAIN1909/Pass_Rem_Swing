package dao;

import java.util.List;

import passwordReminder.Account;

public interface AccountDao {
    List<Account> get();

    List<Account> add();

    List<Account> delete();
}
