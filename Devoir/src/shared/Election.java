package shared;

import java.rmi.Remote;

import java.rmi.RemoteException;
import java.security.SignedObject;
import java.util.HashSet;

import models.Candidate;
import models.Voter;

public interface Election extends Remote {
	
	public boolean register(Candidate candidate) throws RemoteException;
	
	public boolean subscribe(Voter voter) throws RemoteException;

	public HashSet<Candidate> getElectionList() throws RemoteException;
	
	public boolean vote(Voter voter, SignedObject signedCandidate) throws RemoteException;
	
	public String getResult() throws RemoteException;

}
