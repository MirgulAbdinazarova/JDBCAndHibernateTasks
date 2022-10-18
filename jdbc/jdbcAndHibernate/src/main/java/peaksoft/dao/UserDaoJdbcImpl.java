package peaksoft.dao;

import peaksoft.model.User;
import peaksoft.util.Util;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UserDaoJdbcImpl implements UserDao {

    public UserDaoJdbcImpl() {

    }

    public void createUsersTable() {
      String sql = "create table  if not exists users(id serial primary key," +
              "name varchar(50) not null," +
              "lastName varchar(50) not null ," +
              "age smallint);";
      try(Connection connection = Util.getConnection();
          Statement statement = connection.createStatement()) {
          statement.executeUpdate(sql);
      } catch (SQLException e) {
          System.out.println(e.getMessage());
      }
    }

    public void dropUsersTable() {
        String sql = " drop table if exists users;";
        try(Connection connection = Util.getConnection();
            Statement statement = connection.createStatement()) {
            statement.executeUpdate(sql);
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    public void saveUser(String name, String lastName, byte age) {
     String save = "insert into users (name,lastName,age) values (?,?,?);";

     try (Connection connection = Util.getConnection();
          PreparedStatement preparedStatement = connection.prepareStatement(save)){
          preparedStatement.setString(1,name);
          preparedStatement.setString(2,lastName);
          preparedStatement.setInt(3,age);
          preparedStatement.executeUpdate();
         System.out.println(name+" базага кошулду");
     } catch (SQLException e) {
         System.out.println(e.getMessage());
     }
    }

    public void removeUserById(long id) {
        String remove = "delete  from users where id=?;";

        try (Connection connection = Util.getConnection();
        PreparedStatement preparedStatement = connection.prepareStatement(remove)){
            preparedStatement.setLong(1,id);
            preparedStatement.executeUpdate();

        }catch (SQLException e){
            System.out.println(e.getMessage());
        }

    }

    public List<User> getAllUsers() throws SQLException {
        User user = new User();
        String query = "select * from users";

        try(Connection connection = Util.getConnection();
             Statement statement = connection.createStatement()) {
            ResultSet resultSet = statement.executeQuery(query);
             List<User>userList = new ArrayList<>();
            while (resultSet.next()){
                userList.add(new User(resultSet.getString("name"),
                                      resultSet.getString("lastName"),
                                      resultSet.getByte("age") ));
            }
                return userList;
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            throw new SQLException();
        }


    }

    public void cleanUsersTable() {
        String query = "truncate table users ;";

        try (Connection connection = Util.getConnection();
             Statement statement = connection.createStatement()){
            statement.execute(query);
            System.out.println("Table cleared");
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }

    }
}