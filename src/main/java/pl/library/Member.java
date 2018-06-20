package pl.library;

import pl.library.users.UserRole;

import java.util.UUID;

public class Member {

    private final UUID ID;
    private final String password;
    private final String login;
    private final UserRole role;


    public Member(UUID ID, String password, String login, UserRole role) {
        this.ID = ID;
        this.password = password;
        this.login = login;
        this.role = role;
    }

    public Member(String password, String login, UserRole role) {
        this(UUID.randomUUID(), password, login, role);
    }

    public String getLogin() {
        return login;
    }

    public UUID getID() {
        return ID;
    }

    public String getPassword() {
        return password;
    }

    public UserRole getRole() {
        return role;
    }

    @Override
    public String toString() {
        return "pl.library.Member{" +
                "ID=" + ID +
                ", login='" + login + '\'' +
                '}';
    }


}
