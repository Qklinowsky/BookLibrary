package pl.library;

import pl.library.db.LibraryDao;

import java.util.List;
import java.util.Scanner;
import java.util.UUID;

public class Library {

    private Scanner scanner;
    private MemberList members;
    private LibraryDao libDao;

    public Library(MemberList memberList, LibraryDao libDao) {
        this.scanner = new Scanner(System.in);
        this.members = memberList;
        this.libDao = libDao;

    }


    public void addBook() {

        System.out.println("Author of book: \n");
        String author = scanner.nextLine();
        System.out.println("Title of book: \n");
        String title = scanner.nextLine();

        List<Book> books = libDao.findBook(author, title);
        if (!books.isEmpty()) {
            System.out.println("There are already books by this author/title");
            return;
        }
        System.out.println("Release year of book: ");
        int releseYear = Integer.parseInt(scanner.nextLine());
        System.out.println("Description: : ");
        String description = scanner.nextLine();
        Book book2 = new Book(author, title, releseYear, description);
        libDao.addBook(book2);
        System.out.println("pl.library.Book has been added");

    }

    public void removeBook() {
        System.out.println("Whats book's title that u are to remove: ");
        String title = scanner.nextLine();
        libDao.removeBook(title);
    }


    public Book findBook() {
        System.out.println("Whats title or author book that you are looking for: ");
        String input = scanner.nextLine();
        List<Book> book = libDao.findBook(input, input);
        if (book.isEmpty()) {
            return null;
        } else if (book.size() > 1) {
            for (int i = 0; i < book.size(); i++) {
                System.out.println(i + " " + book.get(i));
            }
            System.out.println("Pick book that u are looking for: ");
            int nextInt = Integer.parseInt(scanner.nextLine());
            Book book1 = book.get(nextInt);
            System.out.println(book1.toString());
            return book1;
        } else {
            return book.get(0);
        }
    }

    public void boorowBook() {
        Member member = members.findMember();
        Book book = this.findBook();
        if (member != null) {
            List<Book> memberBooks = libDao.getMemberBooks(member.getID());
            if (memberBooks.size() < 5) {
                if (libDao.isAvaible(book.getID())) {
                    libDao.borrowBook(member.getID(), book.getID());
                    memberBooks.add(book);
                    System.out.println("pl.library.Member " + member.getLogin() + " borrowed " + book.toString() + " .");
                } else {
                    System.out.println("pl.library.Book is unanavailbe");
                }
            } else {
                System.out.println("Reached limit of books.");
            }
        } else {
            System.out.println("pl.library.Member with this name doesn't exist, try to register.");
        }
    }

    public void showBooks() {
        List<Book> books = libDao.showBooks();
        for (Book book : books) {
            System.out.println(book.toString());
        }
    }

    public Member findMember() {
        System.out.println("What's your full name: ");
        String name = scanner.nextLine();
        List<Member> member = libDao.findMember(name);
        Member member2 = null;
        if (member.isEmpty()) {
            return null;
        } else if (member.size() > 1) {
            for (int i = 0; i < member.size(); i++) {
                System.out.println(i + " " + member.get(i).toString());
            }
            System.out.println("Pick your name with correct id: ");
            int nextInt = Integer.parseInt(scanner.nextLine());
            member2 = member.get(nextInt);
            System.out.println(member2.toString());

        } else {
            member2 = member.get(0);
            System.out.println(member2.toString());
        }
        return member2;
    }


    public boolean isAvaible() {
        UUID id = findBook().getID();
        boolean avaible = libDao.isAvaible(id);
        if(avaible == true){
            System.out.println("pl.library.Book is avaible");
        }else{
            System.out.println("pl.library.Book is unaviable");
        }
        return avaible;
    }
    public void returnBook(){
        Member member = this.findMember();
        List<Book> memberBooks = libDao.getMemberBooks(member.getID());
        for (int i = 0; i < memberBooks.size(); i++) {
            System.out.println(i + " " +memberBooks.get(i));
        }
        System.out.println("Pick book that you want to return");
        int nextInt = Integer.parseInt(scanner.nextLine());
        Book book = memberBooks.get(nextInt);
        libDao.returnBook(book.getID().toString());
        System.out.println("pl.library.Book has been returned");
    }


}
