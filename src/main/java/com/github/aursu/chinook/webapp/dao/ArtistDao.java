package com.github.aursu.chinook.webapp.dao;

import com.github.aursu.chinook.webapp.data.Artist;
import lombok.AllArgsConstructor;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@AllArgsConstructor
public class ArtistDao {
    // follow this tutorial to add  database source into Tomcat
    // https://www.digitalocean.com/community/tutorials/tomcat-datasource-jndi-example-java
    private final DataSource ds;

    public List<Artist> findAll() {
        try (Connection connection = ds.getConnection();
             PreparedStatement ps = connection.prepareStatement("select * from Artist");
             ResultSet rs = ps.executeQuery()
        ) {
            List<Artist> result = new ArrayList<>();
            while (rs.next()) {
                int artistId = rs.getInt("ArtistId");
                String name = rs.getString("Name");

                result.add(new Artist(artistId, name));
            }
            return result;
        }
        catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void add(String name) {
        try (Connection connection = ds.getConnection();
             PreparedStatement ps = connection.prepareStatement("insert into Artist (Name) values (?)")
        ) {
            ps.setString(1, name);
            ps.executeUpdate();
        }
        catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void deleteById(int id) {
        try (Connection connection = ds.getConnection();
             PreparedStatement ps = connection.prepareStatement("delete from Artist where ArtistId = ?")
        ) {
            ps.setInt(1, id);
            ps.executeUpdate();
        }
        catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public Artist findById(int id) {
        try (Connection connection = ds.getConnection();
             PreparedStatement ps = connection.prepareStatement("select * from Artist where ArtistId = ?")
        ) {
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                int artistId = rs.getInt("ArtistId");
                String name = rs.getString("Name");

                return new Artist(artistId, name);
            }
            return null;
        }
        catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void update(Artist artist) {
        try (Connection connection = ds.getConnection();
             PreparedStatement ps = connection.prepareStatement("update Artist set Name = ? where ArtistId = ?")
        ) {
            ps.setString(1, artist.getName());
            ps.setInt(2, artist.getId());

            ps.executeUpdate();
        }
        catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
