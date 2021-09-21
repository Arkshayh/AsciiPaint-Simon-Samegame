package td02;

class A {
    A() {
        System.out.println("constructor of A");
    }
}

class B extends A {
    B () {
        super();
        System.out.println("constructor of B");
    }
}
class C extends B {
    C () {
        super();
        System.out.println("constructor of C");

    }
}
class TestConstructor {
    public static void main(String[] args) {
         new C();
         Object test;
    }
}
