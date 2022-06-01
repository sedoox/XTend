package uml;

import java.util.Arrays;

@SuppressWarnings("all")
public class Main {
  public static void main(final String[] args) {
    throw new Error("Unresolved compilation problems:"
      + "\nThe field Customer.firstName refers to the missing type Object"
      + "\nThe field Customer.lastName refers to the missing type Object"
      + "\nThe field Customer.orders refers to the missing type Object"
      + "\nThe field Order.date refers to the missing type Object"
      + "\nThe field PhoneOrder.number refers to the missing type Object"
      + "\nThe field Order.date refers to the missing type Object"
      + "\nThe field Customer.firstName refers to the missing type Object"
      + "\nThe field Customer.lastName refers to the missing type Object"
      + "\nThe field Customer.orders refers to the missing type Object"
      + "\nThe field Order.date refers to the missing type Object"
      + "\nThe field OnlineOrder.ipAddress refers to the missing type Object"
      + "\nThe field Customer.firstName refers to the missing type Object"
      + "\nThe field Customer.lastName refers to the missing type Object"
      + "\nThe field Customer.orders refers to the missing type Object"
      + "\nThe field Order.date refers to the missing type Object"
      + "\nThe field Order.date refers to the missing type Object"
      + "\nThe field OnlineOrder.ipAddress refers to the missing type Object"
      + "\nThe field Order.date refers to the missing type Object"
      + "\nThe field PhoneOrder.number refers to the missing type Object");
  }
  
  protected static double _getDeliveryCosts(final Order order) {
    if ((order.amount > 100)) {
      return 0;
    }
    boolean _matched = false;
    if (order instanceof PhoneOrder) {
      _matched=true;
      if ((((PhoneOrder)order).amount <= 50)) {
        return 5;
      } else {
        return 4;
      }
    }
    if (!_matched) {
      if (order instanceof PostalOrder) {
        _matched=true;
        if ((((PostalOrder)order).amount <= 50)) {
          return 5.5;
        } else {
          return 3;
        }
      }
    }
    if (!_matched) {
      if (order instanceof OnlineOrder) {
        _matched=true;
        if ((((OnlineOrder)order).amount <= 50)) {
          return 4;
        } else {
          return 0;
        }
      }
    }
    return (-1);
  }
  
  protected static double _getDeliveryCosts(final PhoneOrder order) {
    if ((order.amount <= 50)) {
      return 5;
    } else {
      if ((order.amount <= 100)) {
        return 4;
      } else {
        return 0;
      }
    }
  }
  
  protected static double _getDeliveryCosts(final PostalOrder order) {
    if ((order.amount <= 50)) {
      return 5.5;
    } else {
      if ((order.amount <= 100)) {
        return 3;
      } else {
        return 0;
      }
    }
  }
  
  protected static double _getDeliveryCosts(final OnlineOrder order) {
    if ((order.amount <= 50)) {
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
