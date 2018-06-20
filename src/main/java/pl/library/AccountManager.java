package pl.library;

import pl.library.db.LibraryDao;
import pl.library.users.UserRole;

import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

public class AccountManager {
    public static final Member ANONYMOUS_USER = new Member("dummy", "dummy", UserRole.ANONYMOUS);
    private LibraryDao libdao;
    private Member currentlyLoggedInUser;

    public AccountManager(LibraryDao libdao) {
        this.libdao = libdao;
        currentlyLoggedInUser = ANONYMOUS_USER;
    }

    public void login() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Type your login: ");
        String login = scanner.nextLine();
        System.out.println("Type your password: ");
        String password = scanner.nextLine();

        Member member = libdao.findMember(login, password);
        if (member == null) {
            System.out.println("Wrong login or password, try again");
            return;
        }
        this.currentlyLoggedInUser = member;
    }

    public void logout() {
        currentlyLoggedInUser = ANONYMOUS_USER;
    }

    public void registerUser() {
        Scanner scanner = new Scanner(System.in);
        boolean newLogin = false;
        String chosenLogin = "";
        List<Member> members = libdao.showMembers();
        while (!newLogin) {
            System.out.println("Type your login: ");
            String currentChosenLogin = scanner.nextLine();
            List<Member> alreadyExistingMembers = members.stream()
                    .filter(member -> member.getLogin().equals(currentChosenLogin))
                    .collect(Collectors.toList());
            if (!alreadyExistingMembers.isEmpty()) {
                System.out.println("User with this login already exists.");
            } else {
                newLogin = true;
                chosenLogin = currentChosenLogin;
            }
        }
        System.out.println("Type your password: ");
        String password = scanner.nextLine();
        libdao.addMember(new Member(password, chosenLogin, UserRole.USER));
        System.out.println("Registry completed successfully");
    }

    public Member getCurrentlyLoggedInUser() {
        return currentlyLoggedInUser;
    }
}
