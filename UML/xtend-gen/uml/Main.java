package uml;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import org.eclipse.xtext.xbase.lib.InputOutput;
import org.eclipse.xtext.xbase.lib.ObjectExtensions;
import org.eclipse.xtext.xbase.lib.Procedures.Procedure1;

@SuppressWarnings("all")
public class Main {
  public static void main(final String[] args) {
    Customer _customer = new Customer();
    final Procedure1<Customer> _function = new Procedure1<Customer>() {
      public void apply(final Customer it) {
        it.firstName = "Jesse";
        it.lastName = "James";
        PhoneOrder _phoneOrder = new PhoneOrder();
        final Procedure1<PhoneOrder> _function = new Procedure1<PhoneOrder>() {
          public void apply(final PhoneOrder it) {
            it.date = LocalDate.parse("2021-04-23");
            it.amount = 400.25;
            it.number = "06445465";
          }
        };
        PhoneOrder _doubleArrow = ObjectExtensions.<PhoneOrder>operator_doubleArrow(_phoneOrder, _function);
        PostalOrder _postalOrder = new PostalOrder();
        final Procedure1<PostalOrder> _function_1 = new Procedure1<PostalOrder>() {
          public void apply(final PostalOrder it) {
            it.date = LocalDate.parse("2021-04-23");
            it.amount = 400.25;
            it.zipCode = "55887";
          }
        };
        PostalOrder _doubleArrow_1 = ObjectExtensions.<PostalOrder>operator_doubleArrow(_postalOrder, _function_1);
        List<Order> _asList = Arrays.<Order>asList(_doubleArrow, _doubleArrow_1);
        ArrayList<Order> _arrayList = new ArrayList<Order>(_asList);
        it.orders = _arrayList;
      }
    };
    final Customer person1 = ObjectExtensions.<Customer>operator_doubleArrow(_customer, _function);
    Customer _customer_1 = new Customer();
    final Procedure1<Customer> _function_1 = new Procedure1<Customer>() {
      public void apply(final Customer it) {
        it.firstName = "Ford";
        it.lastName = "Henry";
        OnlineOrder _onlineOrder = new OnlineOrder();
        final Procedure1<OnlineOrder> _function = new Procedure1<OnlineOrder>() {
          public void apply(final OnlineOrder it) {
            it.date = LocalDate.parse("1998-03-13");
            it.amount = 63.52;
            it.ipAddress = "205.204.22.22";
          }
        };
        OnlineOrder _doubleArrow = ObjectExtensions.<OnlineOrder>operator_doubleArrow(_onlineOrder, _function);
        List<Order> _asList = Arrays.<Order>asList(_doubleArrow);
        ArrayList<Order> _arrayList = new ArrayList<Order>(_asList);
        it.orders = _arrayList;
      }
    };
    final Customer person2 = ObjectExtensions.<Customer>operator_doubleArrow(_customer_1, _function_1);
    Customer _customer_2 = new Customer();
    final Procedure1<Customer> _function_2 = new Procedure1<Customer>() {
      public void apply(final Customer it) {
        it.firstName = "William";
        it.lastName = "Wallace";
        PostalOrder _postalOrder = new PostalOrder();
        final Procedure1<PostalOrder> _function = new Procedure1<PostalOrder>() {
          public void apply(final PostalOrder it) {
            it.date = LocalDate.parse("2017-02-04");
            it.amount = 987.05;
            it.zipCode = "55822";
          }
        };
        PostalOrder _doubleArrow = ObjectExtensions.<PostalOrder>operator_doubleArrow(_postalOrder, _function);
        OnlineOrder _onlineOrder = new OnlineOrder();
        final Procedure1<OnlineOrder> _function_1 = new Procedure1<OnlineOrder>() {
          public void apply(final OnlineOrder it) {
            it.date = LocalDate.parse("2020-02-05");
            it.amount = 632.78;
            it.ipAddress = "205.204.22.47";
          }
        };
        OnlineOrder _doubleArrow_1 = ObjectExtensions.<OnlineOrder>operator_doubleArrow(_onlineOrder, _function_1);
        PhoneOrder _phoneOrder = new PhoneOrder();
        final Procedure1<PhoneOrder> _function_2 = new Procedure1<PhoneOrder>() {
          public void apply(final PhoneOrder it) {
            it.date = LocalDate.parse("2020-12-07");
            it.amount = 125.25;
            it.number = "65411645";
          }
        };
        PhoneOrder _doubleArrow_2 = ObjectExtensions.<PhoneOrder>operator_doubleArrow(_phoneOrder, _function_2);
        List<Order> _asList = Arrays.<Order>asList(_doubleArrow, _doubleArrow_1, _doubleArrow_2);
        ArrayList<Order> _arrayList = new ArrayList<Order>(_asList);
        it.orders = _arrayList;
      }
    };
    final Customer person3 = ObjectExtensions.<Customer>operator_doubleArrow(_customer_2, _function_2);
    InputOutput.<CharSequence>println(person3.getSummary());
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
