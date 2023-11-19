public class TestJNI{
  static{
    System.loadLibrary("cal");
  }
  private native int add(int n1,int n2);
 
 
  public static void main(String[] args)
  {
    TestJNI obj = new TestJNI();
    System.out.println("Addition is = "+obj.add(10,20));
  }
}
