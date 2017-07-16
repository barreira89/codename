package com.stevebarreira.codename.model;
import java.util.concurrent.CountDownLatch

import spock.lang.Specification;
import spock.lang.Unroll

class TeamListSpec extends Specification {
	
	@Unroll
	def "Hello World"(){
		given:
			def teamList = new TeamList()
			def teamListArray = teamList.getTeamList();
		
		expect:
			teamListArray.size() == 25
			teamListArray.count(Team.ASSASSIN) == 1;
			teamListArray.count(Team.NEUTRAL) == 7;
			if(teamList.getLead() == Team.BLUE)
				teamListArray.count(Team.BLUE) == 9;
	}
	

}
