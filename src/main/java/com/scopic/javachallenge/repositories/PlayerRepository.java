// /////////////////////////////////////////////////////////////////////////////
// PLEASE DO NOT RENAME OR REMOVE ANY OF THE CODE BELOW.
// YOU CAN ADD YOUR CODE TO THIS FILE TO EXTEND THE FEATURES TO USE THEM IN YOUR WORK.
// /////////////////////////////////////////////////////////////////////////////

package com.scopic.javachallenge.repositories;

import com.scopic.javachallenge.enums.PlayerPosition;
import com.scopic.javachallenge.enums.Skill;
import com.scopic.javachallenge.models.Player;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;


public interface PlayerRepository extends JpaRepository<Player, Long> {
    @Query("SELECT p FROM Player p " +
        "INNER JOIN p.playerSkills s " +
        "WHERE p.position = :position " +
        "AND s.skill = :mainSkill " +
        "ORDER BY s.value DESC")
    List<Player> findSkilledPlayerByPositionAndSkill(PlayerPosition position, Skill mainSkill, Pageable pageable);

    @Query("SELECT p FROM Player p " +
        "INNER JOIN p.playerSkills s " +
        "WHERE p.position = :position " +
        "AND p.id NOT IN (:alreadySelectedIds) " +
        "ORDER BY s.value DESC")
    List<Player> findSkilledPlayerByPosition(PlayerPosition position, List<Long> alreadySelectedIds, Pageable pageable);
}
