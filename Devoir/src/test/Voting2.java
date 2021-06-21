package test;

import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

import shared.Election;

public class Voting2 {

	private static String site = "e-vote.election.site";

	// test de voter donner le resultat du vote
	public static void main(String[] args) {

		try {

			Registry registry = LocateRegistry.getRegistry("127.0.0.1", 1099);

			Election election = (Election) registry.lookup(site);
			
			System.out.println("Connection established with the election site.");
			
			String results = election.getResult();
			System.out.println(results);


		} catch (RemoteException | NotBoundException e) {
			e.printStackTrace();
		}
	}
	
}
