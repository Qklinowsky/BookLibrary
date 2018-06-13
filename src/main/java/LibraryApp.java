import org.h2.tools.Server;

import java.util.HashMap;
import java.util.Scanner;
import java.util.concurrent.Callable;

public class LibraryApp {

    public interface LibAction {
        void perform(Library library, MemberList members);
    }

    enum Actions {
        EXIT("Exit", (library, members) -> {
        }),
        REGISTER("Register", (library, members) -> members.addMember()),
        BORROW_BOOK("Borrow book", (library, members) -> library.boorowBook()),
        RETURN_BOOK("Return book", (library, members) -> library.returnBook()),
        ADD_BOOK("Add book", (library, members) -> library.addBook()),
        REMOVE_BOOK("Remove book", (library, members) -> library.removeBook()),
        SHOW_BOOK("Show books", (library, members) -> library.showBooks()),
        SHOW_MEMBERS("Remove book", (library, members) -> library.showMembers()),
        FIND_BOOK("Find book", (library, members) -> library.findBook());


        private final String text;
        private final LibAction actionToPerform;

        Actions(String text, LibAction actionToPerform) {
            this.text = text;
            this.actionToPerform = actionToPerform;
        }
    }


    public static void main(String[] args) {
        LibraryDao libraryDao = new LibraryDao();
        libraryDao.initialize();
        Scanner scanner = new Scanner(System.in);
        MemberList memberList = new MemberList(libraryDao);
        Library library = new Library(memberList, libraryDao);

        Actions lastAction = null;
        do {
            Actions[] actions = Actions.values();
            for (int i = 0; i < actions.length; i++) {
                System.out.println(i + " - " + actions[i].text);
            }
            int choice;
            choice = Integer.parseInt(scanner.nextLine());
            lastAction = actions[choice];
            lastAction.actionToPerform.perform(library, memberList);
        } while (lastAction != Actions.EXIT);
    }
}