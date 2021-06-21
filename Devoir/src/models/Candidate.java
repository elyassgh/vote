package models;

import java.io.Serializable;

public class Candidate implements Serializable{

	private static final long serialVersionUID = 1L;
	
	private String identifier;
	
	private String fullName;
	private String commune;
	
	public Candidate() {
		
	}
	
	public Candidate( String identifier, String fullName, String commune) {
		this.identifier = identifier;
		this.fullName = fullName;
		this.commune = commune;
	}

	public String getIdentifier() {
		return identifier;
	}

	public void setIdentifier(String identifier) {
		this.identifier = identifier;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	public String getFullName() {
		return fullName;
	}

	public void setFullName(String fullName) {
		this.fullName = fullName;
	}

	public String getCommune() {
		return commune;
	}

	public void setCommune(String commune) {
		this.commune = commune;
	}
	
}
