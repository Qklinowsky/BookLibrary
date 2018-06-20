package pl.library.db.mappers;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import pl.library.Book;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class BookMapper implements ResultSetMapper<Book> {

    private static final Logger log = LoggerFactory.getLogger(BookMapper.class);

    @Override
    public List<Book> map(ResultSet resultSet) {
        List<Book> books = new ArrayList<>();
        try {
            while (resultSet.next()) {
                String id = resultSet.getString("BOOKS.ID");
                String author = resultSet.getString("BOOKS.AUTHOR");
                String title = resultSet.getString("BOOKS.TITLE");
                int releaseYear = resultSet.getInt("BOOKS.RELEASEYEAR");
                String description = resultSet.getString("BOOKS.DESCRIPTION");
                Book book = new Book(UUID.fromString(id), author, title, releaseYear, description);
                books.add(book);
            }
        } catch (SQLException e) {
            log.error("Failed while mapping to books list", e);
        }
        return books;
    }
}
