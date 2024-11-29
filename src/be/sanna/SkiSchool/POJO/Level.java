package be.sanna.SkiSchool.POJO;

public enum Level {
	
	PETIT_SPIROU("Petit Spirou"),
	BRONZE("Bronze"),
	ARGENT("Argent"),
	OR("Or"),
	PLATINE("Platine"),
	DIAMANT("Diamant"),
	COMPETITION("Compétition"),
	HORS_PISTE("Hors-Piste"),
	UN("1"),
	DEUX_A_QUATRE("2 à 4"),
	UN_A_QUATRE("De 1 à 4");
	
	private final String description;
	
	Level(String description_){
		this.description = description_;
	}
	
	public String getDescription() {
		return description;
	}
	
	public static Level fromDescription(String description) {
	    for (Level level : Level.values()) {
	        if (level.getDescription().equalsIgnoreCase(description)) {
	            return level;
	        }
	    }
	    throw new IllegalArgumentException("Aucun niveau correspondant pour la description : " + description);
	}
}
