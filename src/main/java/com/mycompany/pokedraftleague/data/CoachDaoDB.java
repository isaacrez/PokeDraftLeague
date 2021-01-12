/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.pokedraftleague.data;

import com.mycompany.pokedraftleague.models.Coach;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

/**
 *
 * @author isaacrez
 */
@Repository
public class CoachDaoDB implements CoachDao {
    
    private final JdbcTemplate jdbc;
    
    @Autowired
    public CoachDaoDB(JdbcTemplate jdbcTemplate) {
        this.jdbc = jdbcTemplate;
    }

    @Override
    public List<Coach> getAllCoaches() {
        final String GET_ALL_COACHES = "SELECT * FROM coach";
        return jdbc.query(GET_ALL_COACHES, new CoachMapper());
    }

    @Override
    public Coach getCoachById(int id) {
        try {
            final String GET_COACH_BY_ID = "SELECT * FROM coach WHERE id = ?";
            return jdbc.queryForObject(GET_COACH_BY_ID, new CoachMapper(), id);
        } catch (DataAccessException e) {
            return null;
        }
    }

    @Override
    public Coach addCoach(Coach coach) {
        final String ADD_COACH = "INSERT INTO coach (nickname, discordName, showdownName) "
                + "VALUES (?, ?, ?)";
        jdbc.update(ADD_COACH,
                coach.getNickname(),
                coach.getDiscordName(),
                coach.getShowdownName());
        
        int newId = jdbc.queryForObject("SELECT LAST_INSERT_ID()", Integer.class);
        coach.setId(newId);
        return coach;
    }

    @Override
    public void updateCoach(Coach coach) {
        final String UPDATE_COACH = "UPDATE coach "
                + "SET nickname = ?, discordName = ?, showdownName = ? "
                + "WHERE id = ?";
        jdbc.update(UPDATE_COACH,
                coach.getNickname(),
                coach.getDiscordName(),
                coach.getShowdownName(),
                coach.getId());
    }

    @Override
    public void deleteCoachById(int id) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
    public static final class CoachMapper implements RowMapper<Coach> {
        @Override
        public Coach mapRow(ResultSet rs, int index) throws SQLException {
            Coach coach = new Coach();
            coach.setId(rs.getInt("id"));
            coach.setNickname(rs.getString("nickname"));
            coach.setDiscordName(rs.getString("discordName"));
            coach.setShowdownName(rs.getString("showdownName"));
            return coach;
        }
    }
    
}
