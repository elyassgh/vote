package server;

import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

import models.Candidate;

public class VoteServer {

	public static void main(String[] args) {

		try {
			ElectionImpl election = new ElectionImpl();
			
			election.register(new Candidate("P56568", "Saad Eddine Othmani", "Ouarzazate"));
			election.register(new Candidate("D13923","Aziz akhnouch", "Agadir"));
			
			Registry registry = LocateRegistry.createRegistry(1099);
			registry.bind("e-vote.election.site", election);
			System.out.println("Election server is on.");

		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
}
