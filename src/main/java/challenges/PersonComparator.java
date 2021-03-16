package challenges;

import java.util.Comparator;

public class PersonComparator implements Comparator<Person> {

    @Override
    public int compare(Person o1, Person o2) {
        if ( o1.getAge() == o2.getAge()) {
            return o2.getName().compareTo(o1.getName());
        }
        return o1.getAge() - o2.getAge();
    }
}
