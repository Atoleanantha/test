package DLL;

public class MathOperation {
    static {
        System.load("cal");
    }
    public native int add(int a,int b);
    public native int sub(int a,int b);
    public native int mult(int a, int b);
    public native double div(int a, int b);
    public static void main(String[] args){
        MathOperation ob=new MathOperation();
        System.out.println(ob.add(10, 20));
    }
}
