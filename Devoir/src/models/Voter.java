package models;

import java.io.Serializable;
import java.security.PublicKey;

public class Voter implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	private String identifier;
	
	private String fullName;
	
	private PublicKey publicKey;
	
	// à ne pas partagé !
	// private PrivateKey privateKey;

	public Voter() {
		
	}
	
	public Voter(String identifier, String fullName, PublicKey publicKey) throws Exception {
		this.identifier = identifier;
		this.fullName = fullName;
		this.publicKey = publicKey;
		
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

	public PublicKey getPublicKey() {
		return publicKey;
	}

	public void setPublicKey(PublicKey publicKey) {
		this.publicKey = publicKey;
	}
	
}
