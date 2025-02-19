package villagegaulois;


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
			int etalsVide=0;
			for (int i = 0; i < etals.length; i++) {
				if (etals[i].isEtalOccupe()) {
					etals[i].afficherEtal();
				} else {
					etalsVide+=1;
				}
			}
			return "Il reste " + etalsVide + " etals non utilisees dans le marche.\n";
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
	
//	public String installerVendeur(Gaulois vendeur, String produit, int nbProduit) {
//		StringBuilder chaine = new StringBuilder();
//		chaine.append(vendeur.getNom() + " cherche un endroit pour vendre " + nbProduit + " " + produit + "\n");
		
		
//	}
	
	
}