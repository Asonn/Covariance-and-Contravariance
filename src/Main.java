import java.util.ArrayList;
import java.util.List;

public class Main {

    public static void main(String[] args) {
        List <Bird> birds = new ArrayList <>();
        birds.add(new Bird());
        List <Reptile> reptiles = new ArrayList <>();
        reptiles.add(new Reptile());

        List <Animal> allAnimals = new ArrayList <Animal>();
        copy(birds, allAnimals);
        copy(reptiles, allAnimals);
        System.out.println(allAnimals);
    }


    public static void PolymorphismExplained() {
        List <Animal> animals = new ArrayList <>();
        // polymorphism
        // A Bird is an Type of Animal
        // And so is a Reptile
        Animal animal = new Bird();
        animals.add(animal);
        animals.add(new Reptile());
        // this is useful when we want to do an generic action from an animal like walking.
        // A Bird can have a different implementation but we don't want to check every type of animal in this case.
        // The object can decide for itself.
        for (Animal a : animals) {
            a.walk();
        }
    }

    /**
     * Arrays are a type of reifiable.
     * This means it knows what type the array was instantiated at Runtime.
     */
    public static void ArraysExplained() {
        Animal[] b = new Bird[3];
        b[0] = new Bird();
        // gives an error at runtime. Because the array is instantiated with Birds
        b[1] = new Reptile();
    }

    /**
     * Generics are a type of non-reifiable.
     * This means that the type information is discarded when compiled.
     * So we won't be able to tell what the true nature of the generic is at run time.
     */
    public static void genericsExplained() {
        // doesn't compile because after the code is compiled it won't know that it only should allow Birds.
        List <Animal> animals = new ArrayList <Bird>();
    }

    /**
     * With the help of covariant we can read from a List that use a wildcard and extends the base class.
     * Rule: List ? extends T where ? is the wildcard and T is the type of the base class.
     * However we cannot write to a covariant.
     */
    public static void covariantExplained() {
        // We can set an type to the arrayList, but it won't check if it's a reptile because we expect anything that is an Animal
        // And we can only read from it and not add so it won't be an unsafe action.
        List <? extends Animal> animals = new ArrayList <Reptile>();
        // we may read but we cannot add animals to the list.
        Animal animal = animals.get(0);
        animals.add(new Reptile());
        // The list cast to the type of Animal, because the list can contain anything that IS the type of an Animal.
        // And won't know the true nature of the generic as explained earlier.
        Reptile reptile = animals.get(0);
    }

    /**
     * With the help of contravariant we can write to a List that use a wildcard and a super to the base class.
     * Rule: List ? super T where ? is the wildcard and T is the type of the base class.
     * However we cannot read from a contravariant.
     */
    public static void contraVariantExplained() {
        List <? super Animal> animals = new ArrayList <>();
        // we may add new Animals but we cannot read from animals
        animals.add(new Reptile());
        Animal animal = animals.get(0);
        // This won't compile, because it's non-reifiable and we can add objects to the list.
        // So this action is defined as an unsafe action.
        List <? super Animal> animalsNotCompiled = new ArrayList <Reptile>();
    }

    /**
     * Covariant and contravariant in use.
     *
     * @param source  The covariant what we want to copy
     * @param destiny The contravariant where we want to write to
     */
    public static void copy(List <? extends Animal> source, List <? super Animal> destiny) {
        destiny.addAll(source);
    }
}
