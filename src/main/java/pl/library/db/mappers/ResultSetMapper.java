package pl.library.db.mappers;

import java.sql.ResultSet;
import java.util.List;

public interface ResultSetMapper<T> {
    List<T> map(ResultSet resultSet);
}
