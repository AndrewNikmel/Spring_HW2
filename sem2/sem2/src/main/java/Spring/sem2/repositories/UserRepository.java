package Spring.sem2.repositories;

import Spring.sem2.model.User;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

@Repository
public class UserRepository {

    private final JdbcTemplate jdbc;

    public UserRepository(JdbcTemplate jdbc) {
        this.jdbc = jdbc;
    }

    public List<User> findAll() {
        String sql = "SELECT * FROM userTable";
        return jdbc.query(sql, getUserRowMapper());
    }

    public User findById(int id) {
        String sql = "SELECT * FROM userTable WHERE id = ?";
        return jdbc.queryForObject(sql, getUserRowMapper(), id);
    }

    private RowMapper<User> getUserRowMapper() {
        return (resultSet, i) -> mapUser(resultSet);
    }

    private User mapUser(ResultSet resultSet) throws SQLException {
        User rowObject = new User();
        rowObject.setId(resultSet.getInt("id"));
        rowObject.setFirstName(resultSet.getString("firstName"));
        rowObject.setLastName(resultSet.getString("lastName"));
        return rowObject;
    }

    public User save(User user) {
        String sql = "INSERT INTO userTable (firstName, lastName) VALUES (?, ?)";
        jdbc.update(sql, user.getFirstName(), user.getLastName());
        return user;
    }

    public void deleteById(int id){
        String sql = "DELETE FROM userTable WHERE id=?";
        jdbc.update(sql, id);
    }

    public User update(User user) {
        String sql = "UPDATE userTable SET firstName = ?, lastName = ? WHERE id = ?";
        jdbc.update(sql, user.getFirstName(), user.getLastName(), user.getId());
        return user;
    }

}
