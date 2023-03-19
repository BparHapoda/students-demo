package com.example.studentsdemo.repository;

import com.example.studentsdemo.model.Student;

import javax.sql.DataSource;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.function.Function;

public class StudentsRepositoryImpl implements StudentsRepository {

    private static final String SQL_SELECT_ALL = "SELECT * FROM students ORDER BY id";
    private static final String SQL_INSERT = "INSERT INTO students (first_name,last_name,email,password) values (?,?,?,?)";
    private static final String SQL_SELECT_BY_EMAIL = "SELECT * FROM students WHERE email = ? ";

    private static final String SQL_SELECT_ALL_BY_EMAIL_LIKE = "SELECT * FROM students WHERE email LIKE ? ";

    private DataSource dataSource;

    public StudentsRepositoryImpl(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    private static final Function<ResultSet, Student> studentMapper = resultSet -> {
        try {
            return Student.builder().
                    id(resultSet.getLong("id")).
                    firstName(resultSet.getString("first_name")).
                    lastName(resultSet.getString("last_name")).
                    email(resultSet.getString("email")).
                    password(resultSet.getString("password")).
                    build();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    };

    public void save(Student student) {
        try (Connection connection = dataSource.getConnection();
             PreparedStatement statement = connection.prepareStatement(SQL_INSERT, Statement.RETURN_GENERATED_KEYS)) {
            statement.setString(1, student.getFirstName());
            statement.setString(2, student.getLastName());
            statement.setString(3, student.getEmail());
            statement.setString(4, student.getPassword());

            int affectedRows = statement.executeUpdate();
            if (affectedRows != 1) {
                throw new SQLException("We can`t save info into Data Base");
            }
            ResultSet generatedKeys = statement.getGeneratedKeys();
            if (generatedKeys.next()) {
                student.setId(generatedKeys.getLong("id"));
            } else {
                throw new SQLException("We can`t get Id from Data Base ");
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }


    }


    public Optional<Student> findByEmail(String email) {
        try (Connection connection = dataSource.getConnection();
             PreparedStatement statement = connection.prepareStatement(SQL_SELECT_BY_EMAIL);
        ) {
            statement.setString(1, email);
            try (
                    ResultSet rs = statement.executeQuery()) {
                if (rs.next()) {
                    return Optional.of(studentMapper.apply(rs));
                } else return Optional.empty();
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }

    public List<Student> searchByEmail(String email) {
        List<Student> students = new ArrayList<>();
        try (Connection connection = dataSource.getConnection();
             PreparedStatement statement = connection.prepareStatement(SQL_SELECT_ALL_BY_EMAIL_LIKE)) {
            statement.setString(1, "%" + email + "%");
            try (ResultSet rs = statement.executeQuery()) {
                while (rs.next()) {
                    students.add(studentMapper.apply(rs));
                }
            }
        } catch (
                SQLException e) {
            throw new RuntimeException(e);
        }

        return students;
    }

    public List<Student> findAll() {

        List<Student> students = new ArrayList<>();
        try (
                Connection connection = dataSource.getConnection();
                PreparedStatement statement = connection.prepareStatement(SQL_SELECT_ALL)
        ) {

            try (
                    ResultSet rs = statement.executeQuery()
            ) {
                while (rs.next()) {
                    students.add(studentMapper.apply(rs));
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return students;
    }
}
