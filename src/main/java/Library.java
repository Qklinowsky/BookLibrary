import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Library {

    private final List<Book> bookList;
    private Scanner scanner;
    private MemberList members;

    public Library() {
        this.bookList = new ArrayList<Book>();
        this.scanner = new Scanner(System.in);
        this.members = new MemberList();
    }

    public List<Book> getBookList() {
        return bookList;
    }


    public Book addBook() {

        System.out.println("Podaj autora: \n");
        String author = scanner.nextLine();
        System.out.println("Podaj tytul: \n");
        String title = scanner.nextLine();


        for (Book book1 : bookList) {
            if (book1.getAuthor().equals(author) && book1.getTitle().equals(title)) {
                System.out.println("Dziekujemy, ale mamy juz taka ksiazke w naszych zbiorach. ");
                return null;
            }
        }
        System.out.println("Podaj rok wydania: ");
        int releseYear = scanner.nextInt();
        System.out.println("Podaj opis: ");
        String description = scanner.nextLine();
        Book book2 = new Book(author, title, releseYear, description);
        bookList.add(book2);
        System.out.println("Ksiazka zostala dodana.");
        return null;


    }

    public void removeBook() {
        System.out.println("Podaj tytul ksiazki, ktora chcesz usunac: ");
        String title = scanner.nextLine();
        if (bookList.size() == 0) {
            System.out.println("Nie ma zadnej ksiazki w zbiorze,");
        } else {
            for (Book book1 : bookList) {
                if (book1.getTitle().equals(title)) {
                    int i = bookList.indexOf(title);
                    bookList.remove(i);
                    System.out.println("Ksiazka " + book1.getTitle() + " zostala usunieta.");
                } else {
                    System.out.println("Nie posiadamy takiej ksiazki w naszych zbiorach.");
                }
            }
        }
    }

    public Book findBook() {
        System.out.println("Podaj tytul lub autora ksiazki ktorej poszukujesz: ");
        String input = scanner.nextLine();
        for (int i = 0; i < bookList.size(); i++) {
            String title = (bookList.get(i).getTitle());
            String author = (bookList.get(i).getAuthor());
            if (bookList.get(i).getAuthor().equals(author) || bookList.get(i).getTitle().equals(title)) {
                System.out.println(bookList.get(i).toString());
                return bookList.get(i);
            }
        }
        return null;
    }

    public void boorowBook() {
        Member member = members.findMember();
        if (member != null) {
            if (member.getMembersBook().size() < 5) {
                Book book = findBook();
                if (book.isAvaible()) {
                    member.getMembersBook().add(book);
                    bookList.remove(book);
                    System.out.println("Uzytkownik " + member.getName() + " wypozyczyl " + book.toString() + " .");
                } else {
                    System.out.println("Ksiazka nie jest dostepna");
                }
            } else {
                System.out.println("Limit ksiazek osiagniety.");
            }
        } else {
            System.out.println("Taki uzytkowanik nie istnieje, sprobuj sie zarejestrowac.");
        }
    }

    public void showBooks() {
        for (int i = 0; i < bookList.size(); i++) {
            System.out.println(bookList.get(i).toString());
        }
    }
}
