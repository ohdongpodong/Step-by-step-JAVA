package Chapter05_Object_Oriented_Programming1;

public class ReferenceParmEx2 {
    public static void main(String[] args) {
        int[] x = {10};
        System.out.println("main() : x = " + x[0]);

        change(x);
        System.out.println("After change(x)");
        System.out.println("main() : x = " + x[0]);
    }

    static void change(int[] x){
        x[0] = 1000;
        System.out.println("change() : x = " + x[0]);
    }

}