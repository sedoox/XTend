package uml;

import org.eclipse.xtext.xbase.lib.DoubleExtensions;
import org.eclipse.xtext.xbase.lib.IntegerExtensions;

public class Main {
  public static void main(final Object/* type is 'null' */ args) {
    throw new Error("Unresolved compilation problems:"
      + "\nThe method firstName(java.lang.String) is undefined"
      + "\nThe method lastName(java.lang.String) is undefined"
      + "\nThe method orders(Object) is undefined"
      + "\nArrayList cannot be resolved."
      + "\nThe method or field Arrays is undefined"
      + "\nThe method date(Object) is undefined"
      + "\nThe method or field LocalDate is undefined"
      + "\nThe method amount(double) is undefined"
      + "\nThe method number(java.lang.String) is undefined"
      + "\nThe method date(Object) is undefined"
      + "\nThe method or field LocalDate is undefined"
      + "\nThe method amount(double) is undefined"
      + "\nThe method zipCode(java.lang.String) is undefined"
      + "\nThe method firstName(java.lang.String) is undefined"
      + "\nThe method lastName(java.lang.String) is undefined"
      + "\nThe method orders(Object) is undefined"
      + "\nArrayList cannot be resolved."
      + "\nThe method or field Arrays is undefined"
      + "\nThe method date(Object) is undefined"
      + "\nThe method or field LocalDate is undefined"
      + "\nThe method amount(double) is undefined"
      + "\nThe method ipAddress(java.lang.String) is undefined"
      + "\nThe method firstName(java.lang.String) is undefined"
      + "\nThe method lastName(java.lang.String) is undefined"
      + "\nThe method orders(Object) is undefined"
      + "\nArrayList cannot be resolved."
      + "\nThe method or field Arrays is undefined"
      + "\nThe method date(Object) is undefined"
      + "\nThe method or field LocalDate is undefined"
      + "\nThe method amount(double) is undefined"
      + "\nThe method zipCode(java.lang.String) is undefined"
      + "\nThe method date(Object) is undefined"
      + "\nThe method or field LocalDate is undefined"
      + "\nThe method amount(double) is undefined"
      + "\nThe method ipAddress(java.lang.String) is undefined"
      + "\nThe method date(Object) is undefined"
      + "\nThe method or field LocalDate is undefined"
      + "\nThe method amount(double) is undefined"
      + "\nThe method number(java.lang.String) is undefined"
      + "\nThe method operator_doubleArrow(T, Procedure1<? super T>) from the type ObjectExtensions refers to the missing type Object"
      + "\nThe method operator_doubleArrow(T, Procedure1<? super T>) from the type ObjectExtensions refers to the missing type Object"
      + "\nThe method operator_doubleArrow(T, Procedure1<? super T>) from the type ObjectExtensions refers to the missing type Object"
      + "\nThe method operator_doubleArrow(T, Procedure1<? super T>) from the type ObjectExtensions refers to the missing type Object"
      + "\nThe method operator_doubleArrow(T, Procedure1<? super T>) from the type ObjectExtensions refers to the missing type Object"
      + "\nThe method operator_doubleArrow(T, Procedure1<? super T>) from the type ObjectExtensions refers to the missing type Object"
      + "\nThe method operator_doubleArrow(T, Procedure1<? super T>) from the type ObjectExtensions refers to the missing type Object"
      + "\nThe method operator_doubleArrow(T, Procedure1<? super T>) from the type ObjectExtensions refers to the missing type Object"
      + "\nThe method operator_doubleArrow(T, Procedure1<? super T>) from the type ObjectExtensions refers to the missing type Object"
      + "\nasList cannot be resolved"
      + "\nparse cannot be resolved"
      + "\nparse cannot be resolved"
      + "\nasList cannot be resolved"
      + "\nparse cannot be resolved"
      + "\nasList cannot be resolved"
      + "\nparse cannot be resolved"
      + "\nparse cannot be resolved"
      + "\nparse cannot be resolved");
  }
  
  protected static double _getDeliveryCosts(final Order order) {
    boolean _greaterThan = DoubleExtensions.operator_greaterThan(order.amount, 100);
    if (_greaterThan) {
      return 0;
    }
    boolean _matched = false;
    if (order instanceof PhoneOrder) {
      _matched=true;
      boolean _lessEqualsThan = DoubleExtensions.operator_lessEqualsThan(((PhoneOrder)order).amount, 50);
      if (_lessEqualsThan) {
        return 5;
      } else {
        return 4;
      }
    }
    if (!_matched) {
      if (order instanceof PostalOrder) {
        _matched=true;
        boolean _lessEqualsThan = DoubleExtensions.operator_lessEqualsThan(((PostalOrder)order).amount, 50);
        if (_lessEqualsThan) {
          return 5.5;
        } else {
          return 3;
        }
      }
    }
    if (!_matched) {
      if (order instanceof OnlineOrder) {
        _matched=true;
        boolean _lessEqualsThan = DoubleExtensions.operator_lessEqualsThan(((OnlineOrder)order).amount, 50);
        if (_lessEqualsThan) {
          return 4;
        } else {
          return 0;
        }
      }
    }
    return IntegerExtensions.operator_minus(1);
  }
  
  protected static double _getDeliveryCosts(final PhoneOrder order) {
    boolean _lessEqualsThan = DoubleExtensions.operator_lessEqualsThan(order.amount, 50);
    if (_lessEqualsThan) {
      return 5;
    } else {
      boolean _lessEqualsThan_1 = DoubleExtensions.operator_lessEqualsThan(order.amount, 100);
      if (_lessEqualsThan_1) {
        return 4;
      } else {
        return 0;
      }
    }
  }
  
  protected static double _getDeliveryCosts(final PostalOrder order) {
    boolean _lessEqualsThan = DoubleExtensions.operator_lessEqualsThan(order.amount, 50);
    if (_lessEqualsThan) {
      return 5.5;
    } else {
      boolean _lessEqualsThan_1 = DoubleExtensions.operator_lessEqualsThan(order.amount, 100);
      if (_lessEqualsThan_1) {
        return 3;
      } else {
        return 0;
      }
    }
  }
  
  protected static double _getDeliveryCosts(final OnlineOrder order) {
    boolean _lessEqualsThan = DoubleExtensions.operator_lessEqualsThan(order.amount, 50);
    if (_lessEqualsThan) {
      return 4;
    } else {
      return 0;
    }
  }
  
  public static double getDeliveryCosts(final Order order) {
    if (order instanceof OnlineOrder) {
      return _getDeliveryCosts((OnlineOrder)order);
    } else if (order instanceof PhoneOrder) {
      return _getDeliveryCosts((PhoneOrder)order);
    } else if (order instanceof PostalOrder) {
      return _getDeliveryCosts((PostalOrder)order);
    } else if (order != null) {
      return _getDeliveryCosts(order);
    } else {
      throw new IllegalArgumentException("Unhandled parameter types: " +
        Arrays.<Object>asList(order).toString());
    }
  }
}
