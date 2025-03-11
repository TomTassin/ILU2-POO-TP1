package villagegaulois;


import java.util.Iterator;

import personnages.Chef;
import personnages.Gaulois;

public class Village {
	private String nom;
	private Chef chef;
	private Gaulois[] villageois;
	private int nbVillageois = 0;
	private Marche marche;

	public Village(String nom, int nbVillageoisMaximum, int nbEtal) {
		this.nom = nom;
		villageois = new Gaulois[nbVillageoisMaximum];
		marche = new Marche(nbEtal);
	}
	
	
	public class Marche {
		private Etal[] etals;
		
		public Marche(int nbEtal) {
			etals=new Etal[nbEtal];
			for (int i = 0; i < nbEtal; i++) {
				etals[i]= new Etal();
			}
		}
		
		public void  utiliserEtal(int indiceEtal, Gaulois vendeur,String produit, int nbProduit) {
			etals[indiceEtal].occuperEtal(vendeur, produit, nbProduit);
		}
		
		public int trouverEtalLibre() {
			for (int i = 0; i < etals.length; i++) {
				if (!etals[i].isEtalOccupe()) {
					return i;
				}	
			}
			return -1;
		}
		
		public Etal[] trouverEtals(String produit) {
			int nbEtalsVendantProduit=0;
			for (int i = 0; i < etals.length; i++) {
				if (etals[i].contientProduit(produit)==true) {
					nbEtalsVendantProduit+=1;
				} 
			}
			Etal[] etalsVendantProduit= new Etal[nbEtalsVendantProduit];
			for (int i = 0, j=0; i < etals.length; i++) {
				if (etals[i].contientProduit(produit)==true) {
					etalsVendantProduit[j]=etals[i];
					j++;
				}
			}
			return etalsVendantProduit;
		}
		
		public Etal trouverVendeur(Gaulois gaulois) {
			for (int i = 0; i < etals.length; i++) {
				if (etals[i].getVendeur()==gaulois) {
					return etals[i];
				}
			}
			return null;
			
		}
		
		public String afficherMarche() {
			StringBuilder chaine = new StringBuilder();
			int etalsVide=0;
			for (int i = 0; i < etals.length; i++) {
				if (etals[i].isEtalOccupe()) {
					chaine.append(etals[i].afficherEtal());
				} else {
					etalsVide+=1;
				}
			}
			chaine.append("Il reste " + etalsVide + " etals non utilisees dans le marche.\n");
			return chaine.toString();
		}
	}
	
	
	
	public String getNom() {
		return nom;
	}

	public void setChef(Chef chef) {
		this.chef = chef;
	}

	public void ajouterHabitant(Gaulois gaulois) {
		if (nbVillageois < villageois.length) {
			villageois[nbVillageois] = gaulois;
			nbVillageois++;
		}
	}

	public Gaulois trouverHabitant(String nomGaulois) {
		if (nomGaulois.equals(chef.getNom())) {
			return chef;
		}
		for (int i = 0; i < nbVillageois; i++) {
			Gaulois gaulois = villageois[i];
			if (gaulois.getNom().equals(nomGaulois)) {
				return gaulois;
			}
		}
		return null;
	}

	public String afficherVillageois() {
		StringBuilder chaine = new StringBuilder();
		if (nbVillageois < 1) {
			chaine.append("Il n'y a encore aucun habitant au village du chef "
					+ chef.getNom() + ".\n");
		} else {
			chaine.append("Au village du chef " + chef.getNom()
					+ " vivent les légendaires gaulois :\n");
			for (int i = 0; i < nbVillageois; i++) {
				chaine.append("- " + villageois[i].getNom() + "\n");
			}
		}
		return chaine.toString();
	}
	
	public String installerVendeur(Gaulois vendeur, String produit, int nbProduit) {
		StringBuilder chaine = new StringBuilder();
		chaine.append(vendeur.getNom() + " cherche un endroit pour vendre " + nbProduit + " " + produit + "\n");
		marche.utiliserEtal(marche.trouverEtalLibre(), vendeur, produit, nbProduit);
		chaine.append("Le vendeur " + vendeur.getNom() + " vend des " + produit + " à l'étal n°" + marche.trouverEtalLibre() + ".\n");
		return chaine.toString();
	}
	
	public String rechercherVendeursProduit(String produit) {
		StringBuilder chaine = new StringBuilder();
		Etal[] etalVendeurProduit;
		etalVendeurProduit = marche.trouverEtals(produit);
		if (etalVendeurProduit.length == 0) {
			chaine.append("Il n'y a pas de vendeur qui propose des " + produit + " au marche.\n");
		} else {
			chaine.append("Les vendeurs qui proposent des " + produit + " sont :\n");
			for (int i = 0; i < etalVendeurProduit.length; i++) {
				chaine.append("- " + etalVendeurProduit[i].getVendeur().getNom() + "\n");
			}
		}
		return chaine.toString();
	}
	
	public Etal rechercherEtal(Gaulois gaulois) {
		return marche.trouverVendeur(gaulois);
	}
	
	public String partirVendeur(Gaulois gaulois) {
		StringBuilder chaine = new StringBuilder();
		if (marche.trouverVendeur(gaulois) != null) {
			chaine.append((marche.trouverVendeur(gaulois)).libererEtal());
		}
		return chaine.toString();
	}
	
	public String afficherMarche() {
		StringBuilder chaine = new StringBuilder();
		chaine.append("Le marche du village \"" + nom + "\" possede plusieurs etals :\n");
		chaine.append(marche.afficherMarche());
		return chaine.toString();
	}
}