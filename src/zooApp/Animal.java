package zooApp;
public class Animal {
    private String animalName;
    private String species;
    private int age;

    public Animal(String name, String aSpecies, int anAge) {
        System.out.println("\n A new Animal object was created.\n");
        animalName = name;
        species = aSpecies;
        age = anAge;
    }

    public Animal() {
        System.out.println("\n A new Animal object was created.\n");
    }

    // Getters and Setters
    public String getName() {
        return animalName;
    }

    public void setName(String name) {
        this.animalName = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getSpecies() {
        return species;
    }

    public void setSpecies(String species) {
        this.species = species;
    }
}