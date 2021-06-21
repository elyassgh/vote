package test;

import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.security.KeyPair;
import java.security.SignedObject;
import java.util.HashSet;
import java.util.Random;

import models.Candidate;
import models.Voter;
import shared.Election;
import shared.SignatureHelper;

public class Voting1 {

	private static String site = "e-vote.election.site";

	// test de voter pour un candidat
	public static void main(String[] args) {

		try {

			Registry registry = LocateRegistry.getRegistry("127.0.0.1", 1099);

			Election election = (Election) registry.lookup(site);

			System.out.println("Connection established with the election site.");

			// récuperer la listes des candidates inscrits sur le site
			HashSet<Candidate> candidates = election.getElectionList();

			// créer les paire de clés (public et privée)
			KeyPair paireCles = SignatureHelper.genererKeys();
			
			// créer un voteur (avec son clé public)
			Voter voter = new Voter("P339854", "Elghazi Ilyass", paireCles.getPublic());

			System.out.println("\nvoter created.");
			
			// inscrire le voteur sur la list des voteur sur le site.
			boolean sub = election.subscribe(voter);
			if (!sub) System.out.println("Voter already subscribed");

			// voter pour le premier candidat de la liste des candidates
			System.out.println("\nvoting for the first candidate on the list");
			
			// recupérer le premier candidate dans la list des candidates (exemple)
			Candidate candidate = (Candidate) candidates.toArray()[0];
			System.out.println("Candidat choisi : " + candidate.getFullName());
			
			// signer le candidate choisi par l'utilisateur instant.
			SignedObject signedCandidate =  SignatureHelper.signerObjet(candidate, paireCles.getPrivate());
			
			boolean vote1 = election.vote(voter, signedCandidate);
			System.out.println(vote1);

			Thread.sleep(1000);

			// test de voter pour le meme candidat par le meme voteur
			
			System.out.println("\nvoting for the first candidate on the list (second time!)");
			System.out.println("Candidat choisi (2eme fois) : " + candidate.getFullName());
			boolean vote11 = election.vote(voter, signedCandidate);
			System.out.println(vote11);
			
			Random rnd = new Random();
			
			Thread.sleep(1000);
			
			// un autre electeur vote pour un candidat aleatoire
			KeyPair paireCles2 = SignatureHelper.genererKeys();
			Voter voter2 = new Voter("P12345", "Flen2", paireCles2.getPublic());
			Candidate candidate2 = (Candidate) candidates.toArray()[rnd.nextInt(candidates.size())];
			SignedObject signedCandidate2 =  SignatureHelper.signerObjet(candidate2, paireCles2.getPrivate());
			boolean vote2 = election.vote(voter2, signedCandidate2);
			System.out.println(vote2);

			Thread.sleep(1000);
			
			// un autre electeur vote pour un candidat aleatoire
			KeyPair paireCles3 = SignatureHelper.genererKeys();
			Voter voter3 = new Voter("P23456", "Flen3", paireCles3.getPublic());
			Candidate candidate3 = (Candidate) candidates.toArray()[rnd.nextInt(candidates.size())];
			SignedObject signedCandidate3 =  SignatureHelper.signerObjet(candidate3, paireCles3.getPrivate());
			boolean vote3 = election.vote(voter3, signedCandidate3);
			System.out.println(vote3);
			
			Thread.sleep(1000);
			
			// un autre electeur vote pour un candidat aleatoire
			KeyPair paireCles4 = SignatureHelper.genererKeys();
			Voter voter4 = new Voter("P34567", "Flen4", paireCles4.getPublic());
			Candidate candidate4 = (Candidate) candidates.toArray()[rnd.nextInt(candidates.size())];
			SignedObject signedCandidate4 =  SignatureHelper.signerObjet(candidate4, paireCles4.getPrivate());
			boolean vote4 = election.vote(voter4, signedCandidate4);
			System.out.println(vote4);

			Thread.sleep(1000);
			
			// un autre electeur vote pour un candidat aleatoire
			// cette fois on signe le candidate avec un private key d'un autre voter
			KeyPair paireCles5 = SignatureHelper.genererKeys();
			Voter voter5 = new Voter("P45678", "Flen5", paireCles5.getPublic());
			Candidate candidate5 = (Candidate) candidates.toArray()[rnd.nextInt(candidates.size())];
			// utilisation de la clé privé de l'électeur 4 au lieu du 5
			// SignedObject signedCandidate5 =  SignatureHelper.signerObjet(candidate5, paireCles5.getPrivate());
			SignedObject signedCandidate5 =  SignatureHelper.signerObjet(candidate5, paireCles4.getPrivate());
			boolean vote5 = election.vote(voter5, signedCandidate5);
			System.out.println(vote5);
						

		} catch (Exception e) {
			e.printStackTrace();
		}

	}

}
