package com.stevebarreira.codename.model;

import static org.junit.Assert.*;

import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;



@RunWith(SpringRunner.class)
@SpringBootTest(classes = {TeamList.class})
public class TeamListTest {
	
	@Test
	public void randomTeamList() {
		TeamList teamList = new TeamList();
		List<Team> teams = teamList.getTeamList();
		
		Map<Team, Long> teamMap = 
				teams.stream()
					.collect(Collectors.groupingBy(Function.identity(), Collectors.counting()));


		assertEquals("Team Type Assassin Should Equal 1", (Long) 1L, teamMap.get(Team.ASSASSIN));
		assertEquals("Team Type Neutral Should Equal 7", (Long) 7L, teamMap.get(Team.NEUTRAL));
		
		if(teamList.getLead() == Team.RED){
			assertEquals(teamMap.get(Team.RED).intValue(), 9);
			assertEquals(teamMap.get(Team.BLUE).intValue(), 8);
		} else {
			assertEquals(teamMap.get(Team.RED).intValue(), 8);
			assertEquals(teamMap.get(Team.BLUE).intValue(), 9);
		}
	}
	


}
