package com.stevebarreira.codename.model

import spock.lang.Specification
import spock.lang.Unroll

class TeamListSpec extends Specification {
	
	@Unroll
	def 'teamList - createRandomTeamList'(){
		setup:
		TeamList teamList = new TeamList()
		
		when:
		List<TeamList> teamListArray = teamList.getTeamList()
			
		then:
		teamListArray.size() == 25
		teamListArray.count(Team.ASSASSIN) == 1
		teamListArray.count(Team.NEUTRAL) == 7
		if(teamList.getLead() == Team.BLUE)
		switch (teamList.leadTeam) {
			case Team.BLUE:
			teamListArray.count(Team.BLUE) == 9
			teamListArray.count(Team.RED) == 8
			break
			case Team.RED:
			teamListArray.count(Team.BLUE) == 8
			teamListArray.count(Team.RED) == 9
			break
		}
	}
	

}
