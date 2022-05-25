package test;

import java.util.ArrayList;
import java.util.List;
import org.eclipse.xtext.xbase.lib.CollectionLiterals;
import org.eclipse.xtext.xbase.lib.InputOutput;

@SuppressWarnings("all")
public class MyVectorTestClass {
  public static void main(final String[] args) {
    ArrayList<Integer> _newArrayList = CollectionLiterals.<Integer>newArrayList(Integer.valueOf(1), Integer.valueOf(2), Integer.valueOf(3));
    MyVector a = new MyVector(_newArrayList);
    ArrayList<Integer> _newArrayList_1 = CollectionLiterals.<Integer>newArrayList(Integer.valueOf(6), Integer.valueOf(5), Integer.valueOf(4));
    MyVector b = new MyVector(_newArrayList_1);
    int c = 10;
    List<Integer> _multiply = a.operator_multiply(c);
    InputOutput.<List<Integer>>println(_multiply);
  }
}
