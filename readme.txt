Elghazi Ilyass - IRISI 2 (2020/2021)

Module : Programmation concurrentielle
Prof. : Mr. Rakrak Said

# Structure du projet

Devoir
└───src
    ├───models
    │       Candidate.java
    │       Voter.java
    │
    ├───server
    │       ElectionImpl.java
    │       Vote.java
    │       VoteServer.java (main)
    │
    ├───shared
    │       Election.java
    │       SignatureHelper.java
    │
    └───test
            Voting0.java (main)
            Voting1.java (main)
            Voting2.java (main)
	
# description des fichier :

le répertoire models : continet les modéles utilisés dans l'application (candidat et électeur)

le répertoire server : 
	continet l'implémentation du code partagé avec la techno. RMI
	le fichier VoteServer continet la méhode main pour démarrer le service de RMI (Serveur de vote)
	
le répertoire shared : 
	continet les fichier partagé l'iterface election des services RMI
	et la class abstraite SignatureHelper qui va nous aider de signer les objet et génerer les clés privé/public et verifier un objet signé

le répertoire test :
	continet 3 fichier, chacun faire l'objet de tester une fonctionnalité de notre services RMI.
	Voting0 : ajouter un canidat à la listes des candidats inscrit dans le site
	Voting1 : ajouter un électeur à la liste des électeur et voter pour un candidat
	Voting2 : afficher les résultat des votes
	

# lancer les tests :

	en premier démarrer le serveur RMI (VoteServer.java) // lancé dans le loop back 127.0.0.1
	Rmq. si vous etes connécte à l'internet, la méthode lookup peut génerer des erreurs !

	démarrer Voting0 (optionnel) pour tester l'ajout d'un candidat sur un site.
	démarrer Voting1 pour voir les différent cas possible du vote
			(Vote dupliqué, vote non signé par l'electeur lui meme, vote normal)
	démarrer Voting2 pour avoir les résultat enregsitré sur le site.