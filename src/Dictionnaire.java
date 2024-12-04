/**
 * Devoir 2 IFT1025 
 * @author Zouhair El Yani 20238383
 * @author Douae Sakkat 20253363
 */

import java.util.Scanner;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.*;


public class Dictionnaire {
	/**
	 * Rechercher dans un mot passé en paramètre la sous-chaîne de longueur maximale
	 * depuis le début qui est présente dans le dictionnaire, et renvoyer tous les mots 
	 * commençant par ce préfixe dans le dictionnaire.
	 *
	 *  
	 * @param mot une string 
	 * @param hashMap qui contient le dictionnaire
	 * @return une string contenant tous les mots commençant par ce préfixe dans le dictionnaire
	 * séparés par des virgules
	 * @see la classe Attribuer
	 */
	public static String prefix(String mot, HashMap<String, Attribuer> hashMap){
		String mots = "";
		mot = mot.substring(0, mot.length() - 1);     // On enlève la dernière lettre du mot

		if (mot.length() == 0){
			return "Le mot est introuvable";
		}

		else {
			for (Map.Entry<String, Attribuer> set : hashMap.entrySet()) {
				// on ignore tous les mots plus petits que le mot qu'on cherche
				if (set.getKey().length() < mot.length() ){ 
					continue;
				}
				else {
					// on compare le mot avec les prefixes des mots du dictionnaire
					String s = set.getKey().substring(0, mot.length());

					if (mot.compareToIgnoreCase(s) == 0){
						// concaténer le mot avec les autres mots similaires
						mots += set.getKey() + ", "; 
					} 
				}
			}

			// Si on ne trouve aucun mot similaire, on fait un appel récursif pour trouver un plus petit préfixe
			if (mots.length() == 0){
				return prefix(mot, hashMap);
			}
			return mots;
		}
	}


	/**
	 * Cette méthode prend une chaîne de caractères en entrée et retourne la même chaîne
	 * avec seulement la première lettre en majuscule.
	 *
	 * @param str la chaîne de caractères à modifier
	 * @return la chaîne de caractères avec la première lettre en majuscule
	 */
	public static String capitalizeFirstLetter(String str) {
		if (str == null || str.isEmpty()) {
			return str;
		}
		return str.substring(0, 1).toUpperCase() + str.substring(1).toLowerCase();
	}



	/**
	 * Création du dictionnaire en remplissant un hashmap initialement vide  
	 * par tous les éléments du fichier csv
	 * @return le hashmap 
	 */
	public static HashMap<String, Attribuer>  dictionnaire() {

		HashMap<String, Attribuer> dictionnaire = new HashMap<String, Attribuer>();

		try { 
			FileReader fr = new FileReader("dictionary.csv"); 
			BufferedReader reader = new BufferedReader(fr); 
			String s = reader.readLine();  

			while ( s != null ) {
				// on divise chaque ligne en 4 subdivisions  
				String[] ligne = s.split("," , 4);

				Attribuer attribut = new Attribuer(ligne[1],ligne[2],ligne[3]);
				dictionnaire.put(ligne[0], attribut);
				s = reader.readLine();
			}
			reader.close(); }

		catch (IOException ex) { System.out.println("Erreur à l’ouverture du fichier"); 
		}
		return dictionnaire;
	}


	/**
	 * La méthode principale main permet à l'utilisateur d'interagir avec le dictionnaire.
	 * Il peut rechercher un mot, imprimer l'historique, traduire un mot ou lire un fichier.
	 */

	public static void main( String[] args ) {
		List<String> historique = new LinkedList<String>();
		HashMap<String, Attribuer> dictionnaire = dictionnaire();
		Scanner scanner = new Scanner(System.in); 

		do {
			System.out.println("Tapez 1 pour rechercher un mot  \nTapez 2 pour imprimer l’historique  \nTapez 3 pour traduire un mot \nTapez 4 pour lire un fichier");
			String input = scanner.nextLine();

			if ( input.equals("1")) {

				System.out.println("Entrez le mot à rechercher");
				String input1 = capitalizeFirstLetter(scanner.nextLine());

				if ( dictionnaire.get(input1) == null) {
					System.out.println(prefix(input1, dictionnaire));	
				}
				else { 
					System.out.println(dictionnaire.get(input1));
					historique.add(input1);
				}
			}

			else if ( input.equals("2")) {

				System.out.println("Historique:");
				for (String s : historique ) { System.out.println(s); }
			}

			else if ( input.equals("3")) {
				System.out.println("Entrez le mot à traduire");

				String input3 = capitalizeFirstLetter(scanner.nextLine());

				if ( dictionnaire.get(input3) == null) {
					System.out.println(prefix(input3, dictionnaire));				 
				}
				else { 
					System.out.println(dictionnaire.get(input3).getTraduction());
				}
			}
			else if ( input.equals("4")) {

				System.out.println("Entrez un nom de fichier");
				String inputTest = scanner.nextLine();

				try { 
					FileReader fr = new FileReader(inputTest); 
					BufferedReader reader = new BufferedReader(fr); 
					String s = reader.readLine(); 
					ArrayList<Integer> nbPrefix = new ArrayList<Integer>();

					while ( s != null ) {
						String mots = prefix(s , dictionnaire); 
						String[] tab = mots.split(",");
						System.out.println(tab.length - 1);
						nbPrefix.add(tab.length - 1);
						s = reader.readLine();
					}
					reader.close();

					try{
						File myfile = new File("testPrefixResult.txt");
						FileWriter fw = new FileWriter(myfile);
						BufferedWriter writer = new BufferedWriter(fw);

						for (int element : nbPrefix ) {
							writer.write(String.valueOf(element));
							writer.newLine();
						}
						writer.close();}

					catch( IOException ex){
						System.out.println(ex.getMessage());
					}
				}

				catch (IOException ex) { System.out.println("Erreur à l’ouverture du fichier"); 
				}
			}
		}
		while (scanner.nextLine().equals(""));
	}
}

