package server;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.security.SignedObject;
import java.util.ArrayList;
import java.util.HashSet;

import models.Candidate;
import models.Voter;
import shared.Election;
import shared.SignatureHelper;

public class ElectionImpl extends UnicastRemoteObject implements Election {

	private static final long serialVersionUID = 1L;

	private HashSet<Candidate> candidates;
	private HashSet<Voter> voters;

	private ArrayList<Vote> register = new ArrayList<Vote>();

	protected ElectionImpl() throws RemoteException {
		candidates = new HashSet<Candidate>();
		voters = new HashSet<Voter>();
		register = new ArrayList<Vote>();
	}

	@Override
	public boolean register(Candidate candidate) throws RemoteException {
		return this.candidates.add(candidate);
	}

	@Override
	public HashSet<Candidate> getElectionList() throws RemoteException {
		return this.getCandidates();
	}

	@Override
	public boolean vote(Voter voter, SignedObject sginedCandidate) throws RemoteException {
		
		for (Vote vote : register)
			if (vote.voter.getIdentifier().equals(voter.getIdentifier()))
				return false; // deja voté, vote refesué !

		Candidate candidate;
		try {
			
			// verifier que l'objet est bien signé par le meme voteur qu'il a envoyé.
			boolean verifier = SignatureHelper.verifierObjet(sginedCandidate, voter.getPublicKey());
			
			if (!verifier) return false; // echec !
			
			// succées
			candidate = (Candidate) SignatureHelper.getObjet(sginedCandidate);	
			// ajouter le vote
			this.register.add(new Vote(voter, candidate));
			return true;
					
			
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
		
	}

	@Override
	public boolean subscribe(Voter voter) throws RemoteException {
		for (Voter vo : voters)
			if (vo.getIdentifier().equals(voter.getIdentifier()))
				return false; // deja inscrit !

		this.voters.add(voter);
		return true;
	}

	@Override
	public String getResult() throws RemoteException {
				
		StringBuffer buffer = new StringBuffer();
		
		int votes;
		for (Candidate c : candidates) {
			votes = 0;
			for (Vote v: register) {
				if (v.candidate.getIdentifier().equals(c.getIdentifier()))
					votes++;
			}
			buffer.append("Candidate ID : " + c.getIdentifier());
			buffer.append("\nCandidate Name : " + c.getFullName());
			buffer.append("\nCandidate Province : " + c.getCommune());
			buffer.append("\nCandidate N° Votes : " + votes);
			buffer.append("\n----------------------------------\n");
		}
		
		return buffer.toString();
	}

	public HashSet<Candidate> getCandidates() {
		return candidates;
	}

	public void setCandidates(HashSet<Candidate> candidates) {
		this.candidates = candidates;
	}

	public HashSet<Voter> getVoters() {
		return voters;
	}

	public void setVoters(HashSet<Voter> voters) {
		this.voters = voters;
	}

}
