
/**
 * La classe Attribuer représente un objet qui associe une traduction, un type et une signification d'un mot.
 */
public class Attribuer {
	
	private String traduction;
	private String type;
	private String meaning;

	/**
	 * Constructeur de la classe Attribuer.
	 * @param traduction La traduction associée à l'objet.
	 * @param type Le type associé à l'objet.
	 * @param meaning La signification associée à l'objet.
	 */
	public Attribuer(String traduction, String type, String meaning) {
		this.traduction = traduction;
		this.type = type;
		this.meaning = meaning;
	}

	/**
     * Méthode pour obtenir la traduction associée à l'objet.
     * @return La traduction associée à l'objet.
     */
	public String getTraduction() {
		return this.traduction;
	}

	/**
     * Méthode pour obtenir le type associé à l'objet.
     * @return Le type associé à l'objet.
     */
	public String getType() {
		return this.type;
	}

	/**
     * Méthode pour obtenir la signification associée à l'objet.
     * @return La signification associée à l'objet.
     */
	public String getMeaning() {
		return this.meaning;
	}

	/**
     * Redéfinition de la méthode toString pour afficher les informations de l'objet.
     * @return Une représentation textuelle de l'objet.
     */
	@Override 
	public String toString() {
		return traduction + " " + type + " " + meaning;
	}  
}
