package pl.library.db;

import org.h2.tools.Server;
import org.slf4j.LoggerFactory;
import pl.library.Book;
import pl.library.Member;
import pl.library.db.mappers.BookMapper;
import pl.library.db.mappers.MemberMapper;
import pl.library.db.mappers.ResultSetMapper;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;
import java.util.Scanner;
import java.util.UUID;


public class LibraryDao {

    private static final org.slf4j.Logger log = LoggerFactory.getLogger(LibraryDao.class);
    private Server tcpServer;
    private String url;
    private Server webServer;
    private Connection con;
    private Scanner scannner;

    public LibraryDao() {
        scannner = new Scanner(System.in);
    }


    public void initialize() {
        String paramsString = "-baseDir /tmp/h2-test -tcpPort 8081 -tcpAllowOthers";
        String[] dbParams = paramsString.split(" ");
        try {
            tcpServer = Server.createTcpServer(dbParams).start();
            webServer = Server.createWebServer("-webAllowOthers", "-webPort", "8080").start();
            url = String.format("jdbc:h2:%s/test", tcpServer.getURL());
            System.out.println(tcpServer.getStatus());
            System.out.println(webServer.getStatus());
            con = DriverManager.getConnection(url, "sa", "");
            InputStream sqlFile = LibraryDao.class.getResourceAsStream("/skrypt.sql");
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(sqlFile));
            StringBuilder stringBuilder = new StringBuilder();
            String line;
            while ((line = bufferedReader.readLine()) != null) {
                stringBuilder.append(line).append("\n");
            }
            String[] queries = stringBuilder.toString().split(";");
            for (String query : queries) {
                Statement st = con.createStatement();
                st.executeUpdate(query);
                st.close();
            }


        } catch (Exception e) {
            log.error("Failed to initialize data base ", e);
        }

    }

    public void close() {
        try {
            if (con != null) {
                con.close();
            }
        } catch (SQLException e) {
            log.error("Cannot close connection. ", e);
        }
        if (tcpServer != null) {
            tcpServer.stop();
        }
        if (webServer != null) {
            webServer.stop();
        }

    }

    public void addBook(Book book) {
        try {
            Statement statement = con.createStatement();
            statement.executeUpdate(String.format("insert into BOOKS values('%s','%s','%s', '%s','%s')", book.getID(),
                    book.getAuthor(), book.getTitle(), book.getReleseYear(), book.getDescritpion()));
            statement.close();
        } catch (SQLException e) {
            log.error("Failed to add book.", e);
        }
    }

    public void addMember(Member member) {
        try {
            Statement statement = con.createStatement();
            statement.executeUpdate(String.format("insert into MEMBERS values ('%s', '%s', '%s', '%s')",
                    member.getID(),
                    member.getPassword(),
                    member.getLogin(),
                    member.getRole()));
            statement.close();
        } catch (SQLException e) {
            log.error("Failed to add a member", e);
        }

    }

    public List<Book> findBook(String searchedAuthor, String searchedTitle) {
        return executeQuery(String.format("SELECT * FROM BOOKS WHERE TITLE LIKE '%%%s%%' OR AUTHOR LIKE '%%%s%%'",
                searchedTitle, searchedAuthor), new BookMapper());
    }

    public void removeBook(String searchPhrase) {
        try {
            Statement statment = con.createStatement();
            List<Book> bookToRemove = this.findBook(searchPhrase, searchPhrase);
            Book book = null;
            if (bookToRemove.size() > 1) {
                for (int i = 0; i < bookToRemove.size(); i++) {
                    System.out.println(i + " " + bookToRemove.get(i).toString());
                }
                System.out.println("Pick book to remove: ");
                int nextInt = scannner.nextInt();
                book = bookToRemove.get(nextInt);
                String id = book.getID().toString();
            } else if (bookToRemove.size() == 1) {
                book = bookToRemove.get(0);
            } else {
                System.out.println("This book doesnt exist.");
                return;
            }
            statment.executeUpdate(String.format("DELETE FROM BOOKS WHERE ID = '%s'", book.getID()));
            System.out.println("pl.library.Book has been removed");


        } catch (SQLException e) {
            log.error("Failed to remove book.", e);
        }
    }

    public List<Book> showBooks() {
        return executeQuery(String.format("SELECT * FROM BOOKS"), new BookMapper());
    }

    public List<Member> findMember(String searchPharse) {
        return executeQuery(String.format("SELECT * FROM MEMBERS WHERE NAME LIKE '%s'", searchPharse), new MemberMapper());
    }

    public Member findMember(String login, String password) {
        List<Member> members = executeQuery(String.format("SELECT * FROM MEMBERS WHERE NAME ='%s' AND PASSWORD ='%s'", login, password), new MemberMapper());
        if(members.size() > 1) {
            throw new IllegalStateException("Found more that 1 member for login = " + login + " and password = " + password);
        }
        if(members.isEmpty()) {
            return null;
        }
        return members.get(0);

    }

    public void borrowBook(UUID memberID, UUID bookID) {
        try {
            Statement statement = con.createStatement();
            statement.execute(String.format("INSERT INTO BORROWED_BOOKS VALUES ('%s', '%s')", memberID, bookID));
        } catch (SQLException e) {
            log.error("Failed to borrow book", e);
        }
    }


    public List<Book> getMemberBooks(UUID memberID) {
        return executeQuery(String.format("SELECT  BORROWED_BOOKS.*, BOOKS.*   FROM BORROWED_BOOKS\n" +
                "INNER JOIN BOOKS ON BORROWED_BOOKS.BOOKID=BOOKS.ID\n" +
                "where BORROWED_BOOKS.MEMBERID = '%s'", memberID), new BookMapper());
    }

    public List<Member> showMembers() {
        return executeQuery(String.format("SELECT * FROM MEMBERS"), new MemberMapper());
    }

    public boolean isAvaible(UUID bookID) {
        boolean execute = false;
        try {
            Statement statement = con.createStatement();
            execute = statement.execute(String.format("SELECT * FROM BORROWED_BOOKS WHERE BOOKID = '%s' ", bookID));

        } catch (SQLException e) {
            log.error("Failed to check availability.", e);
        }
        return execute;

    }

    public void returnBook(String bookID) {
        try {
            Statement statement = con.createStatement();
            statement.executeUpdate(String.format("DELETE FROM BORROWED_BOOKS WHERE BOOKID = '%s' ", bookID));
        } catch (SQLException e) {
            log.error("Failed to return book", e);
        }
    }

    private <T> List<T> executeQuery(String query, ResultSetMapper<T> mapper) {
        try (Statement statement = con.createStatement()) {
            ResultSet resultSet = statement.executeQuery(query);
            return mapper.map(resultSet);
        } catch (SQLException e) {
            throw new IllegalStateException("Query " + query + "execution failed", e);
        }
    }
}






