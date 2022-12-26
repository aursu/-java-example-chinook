package com.github.aursu.chinook.chinook.dao;

import com.github.aursu.chinook.chinook.data.Artist;
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
             ResultSet rs = ps.executeQuery();
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
}
