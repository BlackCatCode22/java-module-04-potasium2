package zooApp;

import java.io.File;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Scanner;
import java.util.Random;

public class App {
    public static void main(String[] args) {
        String filePath = "D:\\Homework\\College - 3rd Year\\CIT-63\\java-module-04-potasium2\\arrivingAnimals.txt";
        ArrayList<Animal> animals = new ArrayList<>();
        HashMap<String, Integer> speciesCount = new HashMap<String, Integer>();
        
        processAnimals(filePath, animals, speciesCount);
        genReport(animals, speciesCount);
    }
    
    private static void processAnimals(String filePath, ArrayList<Animal> animals, HashMap<String, Integer> speciesCount) {
        String name = "Placeholder";
        String species;
        int age;
        
        // Open external file
        File file = new File(filePath);

        try (Scanner scanner = new Scanner(file)) {
            while (scanner.hasNextLine()) {
                String line = scanner.nextLine();

                String[] parts = line.split(", ");

                String ageAndSpecies = parts[0];

                String[] theParts = ageAndSpecies.split(" ");
                
                age = Integer.parseInt(theParts[0]);
                species = theParts[4].substring(0, 1).toUpperCase() + theParts[4].substring(1);
                name = genAnimalName(species);
                
                if (species.equals("Hyena"))
                	animals.add(new Hyena(name, age));
                if (species.equals("Lion"))
                	animals.add(new Lion(name, age));
                if (species.equals("Tiger"))
                	animals.add(new Tiger(name, age));
                if (species.equals("Bear"))
                	animals.add(new Bear(name, age));
                
                speciesCount.put(species, speciesCount.getOrDefault(species, 0) + 1);
                
                System.out.println("\n Number of animals is: " + animals.size());
            }
        } catch (FileNotFoundException e) {
            System.out.println("File not found: " + filePath);
            e.printStackTrace();
        }
    }
    
    private static void genReport(ArrayList<Animal> animals, HashMap<String, Integer> speciesCount) {
    	String outputFile = "D:\\Homework\\College - 3rd Year\\CIT-63\\java-module-04-potasium2\\newAnimals.txt";
    	try (BufferedWriter writer = new BufferedWriter(new FileWriter(outputFile))) {
    		for (String species : speciesCount.keySet()) {
    			writer.write("Species: " + species + ", Count: " + speciesCount.get(species) + "\n");
    			
    			for (Animal animal : animals) {
    				if (animal.getSpecies().equals(species))
    					writer.write("Name: " + animal.getName() + ", Age: " + animal.getAge() + "\n");
    			}
    			writer.write("\n");
    		}
    	} catch (IOException e) {
            e.printStackTrace();
    	}
    }
    
    private static String genAnimalName(String species) {
    	String filePath = "D:\\Homework\\College - 3rd Year\\CIT-63\\java-module-04-potasium2\\animalNames.txt";
    	File file = new File(filePath);
    	
    	List<String> names = new ArrayList<>();
    	boolean foundSpecies = false;
    	
    	try (Scanner scanner = new Scanner(file)) {
    		while (scanner.hasNextLine()) {
    			String line = scanner.nextLine();
    			
    			if (line.equals(species + " Names:")) {
    				foundSpecies = true;
    				continue;
    			}
    			if (foundSpecies) {
    				if (line.isEmpty())
    					continue;
    				
    				String[] speciesNames = line.split(", ");
    				for (String name : speciesNames) {
    					names.add(name.trim());
    				}
    			}
    		}
    	} catch (FileNotFoundException e) {
            System.out.println("File not found: " + filePath);
            e.printStackTrace();
    	}
    	
    	Random rand = new Random();
    	return names.get(rand.nextInt(names.size()));
    }
}
