package uml;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
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
        PostalCode _postalCode = new PostalCode();
        final Procedure1<PostalCode> _function_1 = new Procedure1<PostalCode>() {
          public void apply(final PostalCode it) {
            it.date = LocalDate.parse("2021-04-23");
            it.amount = 400.25;
            it.zipCode = "55887";
          }
        };
        PostalCode _doubleArrow_1 = ObjectExtensions.<PostalCode>operator_doubleArrow(_postalCode, _function_1);
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
        PostalCode _postalCode = new PostalCode();
        final Procedure1<PostalCode> _function = new Procedure1<PostalCode>() {
          public void apply(final PostalCode it) {
            it.date = LocalDate.parse("2017-02-04");
            it.amount = 987.05;
            it.zipCode = "55822";
          }
        };
        PostalCode _doubleArrow = ObjectExtensions.<PostalCode>operator_doubleArrow(_postalCode, _function);
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
  }
}
