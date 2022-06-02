package uml;

import java.time.LocalDate;
import java.util.List;
import org.eclipse.xtend2.lib.StringConcatenation;
import org.eclipse.xtext.xbase.lib.Conversions;
import org.eclipse.xtext.xbase.lib.DoubleExtensions;
import org.eclipse.xtext.xbase.lib.Functions.Function1;
import org.eclipse.xtext.xbase.lib.Functions.Function2;
import org.eclipse.xtext.xbase.lib.IntegerRange;
import org.eclipse.xtext.xbase.lib.IterableExtensions;
import org.eclipse.xtext.xbase.lib.ListExtensions;

@SuppressWarnings("all")
public class Customer {
  public String firstName;
  
  public String lastName;
  
  public List<Order> orders;
  
  public CharSequence getSummary() {
    StringConcatenation _builder = new StringConcatenation();
    _builder.append("*****************************************************");
    _builder.newLine();
    _builder.append("******* C U S T O M E R ******** S U M M A R Y ******");
    _builder.newLine();
    _builder.append("First name: ");
    _builder.append(this.firstName);
    _builder.newLineIfNotEmpty();
    _builder.append("Last name: ");
    _builder.append(this.lastName);
    _builder.newLineIfNotEmpty();
    _builder.newLine();
    _builder.append("Total order value: ");
    final Function1<Order, Double> _function = new Function1<Order, Double>() {
      public Double apply(final Order it) {
        return Double.valueOf(it.amount);
      }
    };
    final Function2<Double, Double, Double> _function_1 = new Function2<Double, Double, Double>() {
      public Double apply(final Double p1, final Double p2) {
        return Double.valueOf(DoubleExtensions.operator_plus(p1, p2));
      }
    };
    Double _reduce = IterableExtensions.<Double>reduce(ListExtensions.<Order, Double>map(this.orders, _function), _function_1);
    _builder.append(_reduce);
    _builder.append(" Avarage Order Value: ");
    final Function1<Order, Double> _function_2 = new Function1<Order, Double>() {
      public Double apply(final Order it) {
        return Double.valueOf(it.amount);
      }
    };
    final Function2<Double, Double, Double> _function_3 = new Function2<Double, Double, Double>() {
      public Double apply(final Double p1, final Double p2) {
        return Double.valueOf(DoubleExtensions.operator_plus(p1, p2));
      }
    };
    Double _reduce_1 = IterableExtensions.<Double>reduce(ListExtensions.<Order, Double>map(this.orders, _function_2), _function_3);
    int _length = ((Object[])Conversions.unwrapArray(this.orders, Object.class)).length;
    double _divide = ((_reduce_1).doubleValue() / _length);
    _builder.append(((int) _divide));
    _builder.newLineIfNotEmpty();
    _builder.append("First order: ");
    final Function1<Order, LocalDate> _function_4 = new Function1<Order, LocalDate>() {
      public LocalDate apply(final Order it) {
        return it.date;
      }
    };
    _builder.append(IterableExtensions.<Order, LocalDate>minBy(this.orders, _function_4).date);
    _builder.append(" Last order: ");
    final Function1<Order, LocalDate> _function_5 = new Function1<Order, LocalDate>() {
      public LocalDate apply(final Order it) {
        return it.date;
      }
    };
    _builder.append(IterableExtensions.<Order, LocalDate>maxBy(this.orders, _function_5).date);
    _builder.newLineIfNotEmpty();
    _builder.append("HappyCustomer Points (1 for every full 5.0): ");
    final Function1<Order, Double> _function_6 = new Function1<Order, Double>() {
      public Double apply(final Order it) {
        return Double.valueOf(it.amount);
      }
    };
    final Function2<Double, Double, Double> _function_7 = new Function2<Double, Double, Double>() {
      public Double apply(final Double p1, final Double p2) {
        return Double.valueOf(DoubleExtensions.operator_plus(p1, p2));
      }
    };
    Double _reduce_2 = IterableExtensions.<Double>reduce(ListExtensions.<Order, Double>map(this.orders, _function_6), _function_7);
    double _divide_1 = ((_reduce_2).doubleValue() / 5);
    _builder.append(((int) _divide_1));
    _builder.newLineIfNotEmpty();
    _builder.newLine();
    _builder.append("-----------------------------------------------------");
    _builder.newLine();
    _builder.append("Orders in chronological order:");
    _builder.newLine();
    _builder.newLine();
    {
      int _length_1 = ((Object[])Conversions.unwrapArray(this.orders, Object.class)).length;
      int _minus = (_length_1 - 1);
      IntegerRange _upTo = new IntegerRange(0, _minus);
      for(final Integer i : _upTo) {
        _builder.append(((i).intValue() + 1));
        _builder.append(". on ");
        final Function1<Order, LocalDate> _function_8 = new Function1<Order, LocalDate>() {
          public LocalDate apply(final Order it) {
            return it.date;
          }
        };
        _builder.append(IterableExtensions.<Order, LocalDate>sortBy(this.orders, _function_8).get((i).intValue()).date);
        _builder.append("  value: ");
        _builder.append(this.orders.get((i).intValue()).amount);
        _builder.append(" via ");
        {
          Order _get = this.orders.get((i).intValue());
          if ((_get instanceof PhoneOrder)) {
            _builder.append(" Phone ");
          } else {
            Order _get_1 = this.orders.get((i).intValue());
            if ((_get_1 instanceof PostalOrder)) {
              _builder.append(" Post ");
            } else {
              _builder.append(" Internet ");
            }
          }
        }
        _builder.newLineIfNotEmpty();
      }
    }
    _builder.append("*****************************************************");
    _builder.newLine();
    return _builder;
  }
}
