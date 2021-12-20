**TD Generics**

Q1 :

    1. L'erreur levée : java: incompatible types: java.lang.Double cannot be converted to java.lang.Integer
       
Q2 : 
    
    1. Integer(int)' is deprecated and marked for removal 

Q3 : 
    
    1. Non Box<Integer> n'est pas un sous-typede Box<Object> 
       On ne peut pas écrire : Box<Object> box = new Box<Integer>(42);
       Par contre on peut écrire Box<Object> box = new Box<>(42) 

Q4:

    1. incompatible types: Box<java.lang.Integer> cannot be converted to Box<java.lang.Object>
       
Q5: 

    1. 'Integer(int)' is deprecated and marked for removal

Q6: 

    1. Ligne 12 : box.setElement(new Integer(43));
       L'erreur : java: incompatible types: java.lang.Integer cannot be converted to capture#1 of ?

Q7: 

    1. On ne pourra plus faire appel à la méthode compareTo

Q8:

    1. java: type argument java.lang.Number is not within bounds of type-variable T

Q9: 

    1.List<Integer> liste = new ArrayList<>();
      List<Box> liste2 = new ArrayList<>();
      Collections.copy(liste, liste2);

Q10:
public class Student implements Comparable<Student> {
    
    String name;
    int age;

    public Student(String name, int age) {
       this.name = name;
       this.age = age;
    }

    public static Comparator<Student> AGE = new Comparator<Student>() {
            @Override
            public int compare(Student o1, Student o2) {
                return o1.age - o2.age;
            }
        };

    List<Student> studentList = new LinkedList<>();
    Collections.sort(studentList, Student.Comparators.AGE);