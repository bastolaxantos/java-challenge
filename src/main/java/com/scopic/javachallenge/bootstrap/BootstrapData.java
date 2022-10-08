package com.scopic.javachallenge.bootstrap;

import com.scopic.javachallenge.enums.PlayerPosition;
import com.scopic.javachallenge.enums.Skill;
import com.scopic.javachallenge.models.Player;
import com.scopic.javachallenge.models.PlayerSkill;
import com.scopic.javachallenge.services.PlayerService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;

@Configuration
public class BootstrapData implements CommandLineRunner {

    private final PlayerService playerService;

    public BootstrapData(PlayerService playerService) {
        this.playerService = playerService;
    }

    @Override
    public void run(String... args) throws Exception {
        addPlayer("Santosh Bastola", PlayerPosition.DEFENDER, Skill.STRENGTH, 49, Skill.ATTACK, 80);
        addPlayer("Upendra Gautam", PlayerPosition.FORWARD, Skill.DEFENSE, 60, Skill.SPEED, 23);
        addPlayer("Prabin Shrestha", PlayerPosition.MIDFIELDER, Skill.ATTACK, 20, Skill.SPEED, 76);
        addPlayer("Kushal Thapa", PlayerPosition.FORWARD, Skill.STAMINA, 58, Skill.SPEED, 67);
        addPlayer("Abhishek Bhujel", PlayerPosition.DEFENDER, Skill.ATTACK, 47, Skill.STRENGTH, 88);
        addPlayer("Jagadish Shrestha", PlayerPosition.DEFENDER, Skill.STRENGTH, 22, Skill.DEFENSE, 92);
    }

    private void addPlayer(String name, PlayerPosition position, Skill skill1, int value1, Skill skill2, int value2) {
        Player player = new Player();
        player.setName(name);
        player.setPosition(position);

        PlayerSkill playerSkill1 = new PlayerSkill();
        playerSkill1.setSkill(skill1);
        playerSkill1.setValue(value1);
        playerSkill1.setPlayer(player);

        PlayerSkill playerSkill2 = new PlayerSkill();
        playerSkill2.setSkill(skill2);
        playerSkill2.setValue(value2);
        playerSkill2.setPlayer(player);

        player.getPlayerSkills().add(playerSkill1);
        player.getPlayerSkills().add(playerSkill2);

        playerService.save(player);
    }
}
