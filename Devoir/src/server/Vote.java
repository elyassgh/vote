package server;

import models.Candidate;
import models.Voter;

public class Vote {

	Voter voter;
	Candidate candidate;
	
	public Vote (Voter voter, Candidate candidate) {
		this.voter = voter;
		this.candidate = candidate;
	}
	
}
