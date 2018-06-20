package pl.library;

import pl.library.db.LibraryDao;
import pl.library.users.Feature;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class LibraryApp {

    public interface LibAction {
        void perform(AccountManager accountManager, Library library, MemberList members);
    }

    enum Actions {
        EXIT("Exit", (am, library, members) -> {}, null),
        LOGIN("Login", (am, library, members) -> am.login(), Feature.LOGIN),
        REGISTER("Register", (am, library, members) -> am.registerUser(), Feature.REGISTER),
        LOGOUT("Logout", (am, library, members) -> am.logout(), Feature.LOGOUT),
        BORROW_BOOK("Borrow book", (am, library, members) -> library.boorowBook(), Feature.BORROW),
        RETURN_BOOK("Return book", (am, library, members) -> library.returnBook(), Feature.RETURN_BOOK),
        ADD_BOOK("Add book", (am, library, members) -> library.addBook(), Feature.ADD_BOOK),
        REMOVE_BOOK("Remove book", (am, library, members) -> library.removeBook(), Feature.REMOVE_BOOK),
        SHOW_BOOK("Show books", (am, library, members) -> library.showBooks(), Feature.BROWSE),
        SHOW_MEMBERS("Show members", (am, library, members) -> members.showMembers(), Feature.BROWSE),
        FIND_BOOK("Find book", (am, library, members) -> library.findBook(), Feature.BROWSE);


        private final String text;
        private final LibAction actionToPerform;
        private Feature featureRequired;

        Actions(String text, LibAction actionToPerform, Feature featureRequired) {
            this.text = text;
            this.actionToPerform = actionToPerform;
            this.featureRequired = featureRequired;
        }

        public static Actions[] getActionsAvailableForUser(Member user) {
            List<Actions> userActions = new ArrayList<>();
            for (Actions action : Actions.values()) {
                if(user.getRole().getFeatures().contains(action.featureRequired)) {
                    userActions.add(action);
                }
            }
            return userActions.toArray(new Actions[] {});
        }
    }


    public static void main(String[] args) {
        LibraryDao libraryDao = new LibraryDao();
        libraryDao.initialize();
        AccountManager accountManager = new AccountManager(libraryDao);

        Scanner scanner = new Scanner(System.in);
        MemberList memberList = new MemberList(libraryDao);
        Library library = new Library(memberList, libraryDao);

        Actions lastAction = null;
        do {
            Actions[] actions = Actions.getActionsAvailableForUser(accountManager.getCurrentlyLoggedInUser());
            for (int i = 0; i < actions.length; i++) {
                System.out.println(i + " - " + actions[i].text);
            }
            int choice;
            choice = Integer.parseInt(scanner.nextLine());
            lastAction = actions[choice];
            lastAction.actionToPerform.perform(accountManager, library, memberList);
        } while (lastAction != Actions.EXIT);
        libraryDao.close();
        System.out.println("Application closed.");
    }
}