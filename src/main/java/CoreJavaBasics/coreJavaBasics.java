package CoreJavaBasics;


public class coreJavaBasics {
    public static void main(String[] args) {
        //Java Program Principles
        System.out.println("Java Basics");
        System.out.println("");
        System.out.println("Classes, Objects");
        System.out.println("For example: in real life, a car is an object.The car has attributes,such as weight and color, and methods, such as drive and brake.");
        System.out.println("A Class is like an object constructor, or a blueprint for creating objects");
        System.out.println("");

        //Strings, Integer
        System.out.println("The wrapper class in Java provides the mechanism to convert primitive into object and object into primitive.");
        System.out.println("An int is a primitive data type that is capable of storing 32-bit signed two's complement integer." +
                "\tAn Integer is a wrapper class for the int data type that gives us more flexibility in converting, storing, and manipulating int data.");
        System.out.println("string is basically an object that represents sequence of char values. An array of characters works same as Java string." +
                "\n" +
                "Java String class provides a lot of methods to perform operations on strings such as compare(), concat(), equals(), split(), length(), replace(), compareTo(), intern(), substring() etc.\n" +
                "\n" +
                "The java.lang.String class implements Serializable, Comparable and CharSequence interfaces.");
        System.out.println("");

        //Interface
        System.out.println("An interface in Java is a blueprint of a class. It has static constants and abstract methods." +
                "The interface in Java is a mechanism to achieve abstraction. There can be only abstract methods in the Java interface, not method body. It is used to achieve abstraction and multiple inheritance in Java.");

        System.out.println("It is used to achieve total abstraction.\n" +
                "Since java does not support multiple inheritances in the case of class, by using an interface it can achieve multiple inheritances.\n" +
                "Any class can extend only 1 class, but can any class implement an infinite number of interfaces");

        System.out.println("Class IMPLEMENTS Interface");
        System.out.println("Class EXTENDS Class");
        System.out.println("Interface EXTENDS Interface");
        System.out.println("");

        //Usage of inheritance in Java
        System.out.println("Inheritance in Java is a mechanism in which one object acquires all the properties and behaviors of a parent object.");
        System.out.println("The idea behind inheritance in Java is that you can create new classes that are built upon existing classes. " +
                "When you inherit from an existing class, you can reuse methods and fields of the parent class.");
        System.out.println("Inheritance represents the IS-A relationship which is also known as a parent-child relationship.");
        System.out.println("Why use inheritance in java:" +
                "For Method Overriding (so runtime polymorphism can be achieved).\n" +
                "For Code Reusability.");
        System.out.println("");
        //Arrays
        System.out.println("Java array is an object which contains elements of a similar data type." +
                "Additionally, The elements of an array are stored in a contiguous memory location." +
                "array is an object of a dynamically generated class. " +
                "Java array inherits the Object class, and implements the Serializable as well as Cloneable interfaces." +
                "We can store primitive values or objects in an array in Java.");

        int a[] = new int[5]; //we have object a which accommodate 5 values into. always starts from zero (index)
        a[0]=1;
        a[1]=2;
        a[2]=3;
        a[3]=4;
        a[4]=5;
        for (int i = 0; i < a.length; i++) {
            System.out.println(a[i]);
        }
    }

    //Single Dimensional and Multi Dimensional arrays

    //Multi Dimensional
    public Object[][] multiDim() {
        return new Object[][] {
                {},{}
        };
    }

    //Single Dimensional
    public Object[] singleDim() {
        return new Object[] {
                "","",""
        };
    }
}
