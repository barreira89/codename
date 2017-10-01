package com.stevebarreira.codename.model;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class TeamList {

	private static int DEFAULT_SIZE = 25;
	private Random random = new Random();
	private List<Team> teamList;
	private List<Team> selectTeamList;
	private String leadTeam;
	private Team lead;
	
	public List<Team> getTeamList() {
		return teamList;
	}

	public String getLeadTeam() {
		return leadTeam;
	}

	public TeamList() {
		this.teamList = createTeamList();
	}

	public Team getLead() {
		return lead;
	}

	public void setLead(Team lead) {
		this.lead = lead;
	}

	public Team getRandomTeam() {
		int wordListSize = teamList.size();
		int randomValue = random.nextInt(wordListSize);
		Team output = teamList.get(randomValue);
		teamList.remove(randomValue);
		return output;
	}

	private List<Team> createTeamList() {
		List<Team> teamList = new ArrayList<Team>();
		if (Math.random() > 0.5) {
			teamList.add(Team.RED);
			this.leadTeam = "Red";
			this.lead = Team.RED;
		} else {
			teamList.add(Team.BLUE);
			this.leadTeam = "Blue";
			this.lead = Team.BLUE;
		}
		for (int i = 0; i < 8; i++) {
			teamList.add(Team.RED);
		}
		for (int i = 0; i < 8; i++) {
			teamList.add(Team.BLUE);
		}
		for (int i = 0; i < 7; i++) {
			teamList.add(Team.NEUTRAL);
		}
		teamList.add(Team.ASSASSIN);
		this.selectTeamList = teamList;
		return teamList;
	}
}
