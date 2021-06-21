package test;

import java.rmi.NotBoundException;

import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.util.HashSet;

import models.Candidate;
import shared.Election;

public class Voting0 {

	private static String site = "e-vote.election.site";

	// test d'inscription d'un candidat sur un site 
	public static void main(String[] args) {

		try {

			Registry registry = LocateRegistry.getRegistry("127.0.0.1", 1099);

			Election election = (Election) registry.lookup(site);

			System.out.println("Connection established with the election site.");

			// récuperer la listes des candidates inscrits sur le site
			HashSet<Candidate> candidatesBefore = election.getElectionList();

			// afficher les coordonnées des candidates
			System.out.println("\nlisting candidates :");
			candidatesBefore.forEach(candidate -> {
				System.out.println("--------------------------------------");
				System.out.println("Candidate ID : " + candidate.getIdentifier());
				System.out.println("Candidate Name : " + candidate.getFullName());
				System.out.println("Candidate Province : " + candidate.getCommune());
			});
			System.out.println("--------------------------------------");

			// exemple de s'inscrire un candidat sur la liste d'un site d'election.
			election.register(new Candidate("EE2021", "Flen ben Flen", "Marrakech"));
			System.out.println("\nregistering new candidate.");

			// récuperer la listes des candidates inscrits sur le site
			HashSet<Candidate> candidatesAfter = election.getElectionList();

			// afficher les coordonnées des candidates
			System.out.println("\nlisting candidates :");
			candidatesAfter.forEach(candidate -> {
				System.out.println("--------------------------------------");
				System.out.println("Candidate ID : " + candidate.getIdentifier());
				System.out.println("Candidate Name : " + candidate.getFullName());
				System.out.println("Candidate Province : " + candidate.getCommune());
			});
			System.out.println("--------------------------------------");

		} catch (RemoteException | NotBoundException e) {
			e.printStackTrace();
		}

	}

}
