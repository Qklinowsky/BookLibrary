import java.util.Scanner;

public class LibraryApp {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Library library = new Library();
        MemberList memberList = new MemberList();

        int choice;
        do {
            System.out.println("Witamy w naszej bibliotece! \n Co chcialbys zrobic: \n1.Rejestracja. \n2." +
                    "Wypozyczyc ksiazke. \n3.Dodac ksiazke do biblioteki. \n4.Usunac ksiazke. \n5.Zobaczyc spis ksiazek.\n6.Spis uzytkowanikow.");
            choice = scanner.nextInt();
            switch (choice) {
                case 1:
                    memberList.addMember();
                    break;

                case 2:
                    library.boorowBook();
                    break;
                case 3:
                    library.addBook();
                    break;
                case 4:
                    library.removeBook();
                    break;
                case 5:
                    library.showBooks();
                    break;
                case 6:
                    memberList.showMembers();
            }
        }
        while(choice<=6);
    }
}
