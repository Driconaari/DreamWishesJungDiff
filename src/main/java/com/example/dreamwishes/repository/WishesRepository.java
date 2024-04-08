package com.example.dreamwishes.repository;

import com.example.dreamwishes.model.Wishes;
import org.springframework.stereotype.Repository;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Repository
public class WishesRepository {

    private static final String URL = "jdbc:mysql://dreamwishesserver.mysql.database.azure.com:3306/repository?sslMode=required";

    private static final String USERNAME = "asger";
    private static final String PASSWORD = "Bob1234avb";

    public List<String> getCities() {
        List<String> cities = new ArrayList<>();
        String query = "SELECT DISTINCT city FROM repository.touristattraction";
        try (Connection connection = DriverManager.getConnection(URL, USERNAME, PASSWORD);
             Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery(query)) {
            while (resultSet.next()) {
                String city = resultSet.getString("city");
                cities.add(city);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return cities;
    }

    public List<String> getTags(int attractionId) {
        List<String> tags = new ArrayList<>();
        String query = "SELECT DISTINCT tag_name FROM repository.tags";
        try (Connection connection = DriverManager.getConnection(URL, USERNAME, PASSWORD);
             Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery(query)) {
            while (resultSet.next()) {
                String tag = resultSet.getString("tag_name");
                tags.add(tag);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return tags;
    }


    public void updateTouristAttraction(Wishes updatedAttraction) {
        String query = "UPDATE repository.touristattraction SET name = ?, description = ?, city = ?, tags = ?, location = ? WHERE id = ?";
        try (Connection connection = DriverManager.getConnection(URL, USERNAME, PASSWORD);
             PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setString(1, updatedAttraction.getName());
            preparedStatement.setString(2, updatedAttraction.getDescription());
            preparedStatement.setString(3, updatedAttraction.getCity());

            // Convert the list of tags to a single string separated by commas
            String tagsAsString = String.join(",", updatedAttraction.getTags());
            preparedStatement.setString(4, tagsAsString);

            preparedStatement.setString(5, updatedAttraction.getLocation());
            preparedStatement.setInt(6, updatedAttraction.getId());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


    public List<Wishes> findAll() {
        List<Wishes> attractions = new ArrayList<>();
        String query = "SELECT * FROM repository.touristattraction";
        try (Connection connection = DriverManager.getConnection(URL, USERNAME, PASSWORD);
             PreparedStatement preparedStatement = connection.prepareStatement(query);
             ResultSet resultSet = preparedStatement.executeQuery()) {
            while (resultSet.next()) {
                // Retrieve data from the result set and create TouristAttraction objects
                int id = resultSet.getInt("id");
                String name = resultSet.getString("name");
                String description = resultSet.getString("description");
                String tags = resultSet.getString("tags");
                String location = resultSet.getString("location");
                String city = resultSet.getString("city");

                Wishes attraction = new Wishes(id, name, description, tags, location, city);
                attractions.add(attraction);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return attractions;
    }


    public Optional<Wishes> findByName(String name) {
        String query = "SELECT * FROM repository.touristattraction WHERE name = ?";
        try (Connection connection = DriverManager.getConnection(URL, USERNAME, PASSWORD);
             PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setString(1, name);
            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                if (resultSet.next()) {
                    // Retrieve data from the result set and create a TouristAttraction object
                    int id = resultSet.getInt("id");
                    String description = resultSet.getString("description");
                    String tags = resultSet.getString("tags");
                    String location = resultSet.getString("location");
                    String city = resultSet.getString("city");

                    return Optional.of(new Wishes(id, name, description, tags, location, city));
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return Optional.empty();
    }


    public void save(Wishes attraction) {
        String query = "INSERT INTO repository.touristattraction (name, description, tags, location) VALUES (?, ?, ?, ?)";
        try (Connection connection = DriverManager.getConnection(URL, USERNAME, PASSWORD);
             PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setString(1, attraction.getName());
            preparedStatement.setString(2, attraction.getDescription());

            // Convert tags collection to a comma-separated string
            String tagsAsString = String.join(",", attraction.getTags());
            preparedStatement.setString(3, tagsAsString);

            preparedStatement.setString(4, attraction.getLocation());

            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            // Handle or log the exception appropriately
            e.printStackTrace();
        }
    }



    public void deleteAttractionByName(String name) {
        String query = "DELETE FROM repository.touristattraction WHERE name = ?";
        try (Connection connection = DriverManager.getConnection(URL, USERNAME, PASSWORD);
             PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setString(1, name);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public List<String> getAllTags() {
        List<String> tags = new ArrayList<>();
        String query = "SELECT DISTINCT tag_name FROM repository.tags";
        try (Connection connection = DriverManager.getConnection(URL, USERNAME, PASSWORD);
             PreparedStatement statement = connection.prepareStatement(query)) {
            try (ResultSet resultSet = statement.executeQuery()) {
                while (resultSet.next()) {
                    String tag = resultSet.getString("tag_name");
                    tags.add(tag);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return tags;
    }

}

