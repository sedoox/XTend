package test;

import java.util.List;
import org.eclipse.xtext.xbase.lib.CollectionLiterals;

@SuppressWarnings("all")
public class MyVector {
  private List<Integer> components;
  
  public MyVector(final List<Integer> components) {
    this.components = components;
  }
  
  public Integer operator_doubleArrow(final int i) {
    int _size = this.components.size();
    boolean _lessThan = (i < _size);
    if (_lessThan) {
      return this.components.get(i);
    } else {
      throw new IllegalArgumentException("Out of bounds");
    }
  }
  
  public MyVector operator_plus(final MyVector other) {
    int _size = this.components.size();
    int _size_1 = other.components.size();
    boolean _equals = (_size == _size_1);
    if (_equals) {
      List<Integer> newComponents = CollectionLiterals.<Integer>newArrayList();
      for (int i = 0; (i < this.components.size()); i++) {
        Integer _doubleArrow = this.operator_doubleArrow(i);
        Integer _doubleArrow_1 = other.operator_doubleArrow(i);
        int _plus = ((_doubleArrow).intValue() + (_doubleArrow_1).intValue());
        newComponents.add(Integer.valueOf(_plus));
      }
      return new MyVector(newComponents);
    } else {
      throw new IllegalArgumentException("Dimensions are incompatible!");
    }
  }
  
  public int operator_multiply(final MyVector other) {
    int _size = this.components.size();
    int _size_1 = other.components.size();
    boolean _equals = (_size == _size_1);
    if (_equals) {
      int sum = 0;
      for (int i = 0; (i < this.components.size()); i++) {
        int _sum = sum;
        Integer _get = this.components.get(i);
        Integer _get_1 = other.components.get(i);
        int _multiply = ((_get).intValue() * (_get_1).intValue());
        sum = (_sum + _multiply);
      }
      return sum;
    } else {
      throw new IllegalArgumentException("Dimensions are incompatible!");
    }
  }
  
  public List<Integer> operator_multiply(final int other) {
    List<Integer> newComponents = CollectionLiterals.<Integer>newArrayList();
    for (int i = 0; (i < this.components.size()); i++) {
      Integer _get = this.components.get(i);
      int _multiply = (other * (_get).intValue());
      newComponents.add(Integer.valueOf(_multiply));
    }
    return newComponents;
  }
  
  public String toString() {
    String _string = this.components.toString();
    String _plus = ("<" + _string);
    return (_plus + ">");
  }
}
