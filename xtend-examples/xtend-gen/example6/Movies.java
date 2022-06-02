/**
 * Copyright (c) 2012 itemis AG (http://www.itemis.eu) and others.
 * This program and the accompanying materials are made available under the
 * terms of the Eclipse Public License 2.0 which is available at
 * http://www.eclipse.org/legal/epl-2.0.
 * 
 * SPDX-License-Identifier: EPL-2.0
 * 
 * Author - Sven Efftinge
 */
package example6;

import com.google.common.io.CharStreams;
import java.io.FileReader;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import org.eclipse.xtend2.lib.StringConcatenation;
import org.eclipse.xtext.xbase.lib.Conversions;
import org.eclipse.xtext.xbase.lib.Exceptions;
import org.eclipse.xtext.xbase.lib.Functions.Function0;
import org.eclipse.xtext.xbase.lib.Functions.Function1;
import org.eclipse.xtext.xbase.lib.Functions.Function2;
import org.eclipse.xtext.xbase.lib.InputOutput;
import org.eclipse.xtext.xbase.lib.IntegerRange;
import org.eclipse.xtext.xbase.lib.IterableExtensions;
import org.eclipse.xtext.xbase.lib.IteratorExtensions;
import org.eclipse.xtext.xbase.lib.ListExtensions;
import org.junit.Assert;
import org.junit.Test;

@SuppressWarnings("all")
public class Movies {
  /**
   * @return the total number of action movies
   */
  @Test
  public void numberOfActionMovies() {
    final Function1<Movie, Boolean> _function = (Movie it) -> {
      return Boolean.valueOf(it.getCategories().contains("Action"));
    };
    Assert.assertEquals(828, IterableExtensions.size(IterableExtensions.<Movie>filter(Movies.movies, _function)));
  }
  
  /**
   * @return the year the best rated movie of 80ies (1980-1989) was released.
   */
  @Test
  public void yearOfBestMovieFrom80ies() {
    final Function1<Movie, Boolean> _function = (Movie it) -> {
      return Boolean.valueOf(new IntegerRange(1980, 1989).contains(it.getYear()));
    };
    final Function1<Movie, Double> _function_1 = (Movie it) -> {
      return Double.valueOf(it.getRating());
    };
    Assert.assertEquals(1989, IterableExtensions.<Movie, Double>maxBy(IterableExtensions.<Movie>filter(Movies.movies, _function), _function_1).getYear());
  }
  
  /**
   * @return the sum of the number of votes of the two top rated movies.
   */
  @Test
  public void sumOfVotesOfTop2() {
    final Function1<Movie, Double> _function = (Movie it) -> {
      double _rating = it.getRating();
      return Double.valueOf((-_rating));
    };
    final Function1<Movie, Long> _function_1 = (Movie it) -> {
      return Long.valueOf(it.getNumberOfVotes());
    };
    final Function2<Long, Long, Long> _function_2 = (Long a, Long b) -> {
      return Long.valueOf(((a).longValue() + (b).longValue()));
    };
    final long movies = (long) IterableExtensions.<Long>reduce(IterableExtensions.<Movie, Long>map(IterableExtensions.<Movie>take(IterableExtensions.<Movie, Double>sortBy(Movies.movies, _function), 2), _function_1), _function_2);
    Assert.assertEquals(47_229, movies);
  }
  
  private static final List<Movie> movies = new Function0<List<Movie>>() {
    @Override
    public List<Movie> apply() {
      try {
        final Function1<String, Movie> _function = (String line) -> {
          final Iterator<String> segments = ((List<String>)Conversions.doWrapArray(line.split("  "))).iterator();
          String _next = segments.next();
          int _parseInt = Integer.parseInt(segments.next());
          double _parseDouble = Double.parseDouble(segments.next());
          long _parseLong = Long.parseLong(segments.next());
          Set<String> _set = IteratorExtensions.<String>toSet(segments);
          return new Movie(_next, _parseInt, _parseDouble, _parseLong, _set);
        };
        List<Movie> _map = ListExtensions.<String, Movie>map(CharStreams.readLines(new FileReader("data.csv")), _function);
        return _map;
      } catch (Throwable _e) {
        throw Exceptions.sneakyThrow(_e);
      }
    }
  }.apply();
  
  private static final int oldestMovieYear = IterableExtensions.<Movie, Integer>minBy(Movies.movies, ((Function1<Movie, Integer>) (Movie it) -> {
    return Integer.valueOf(it.getYear());
  })).getYear();
  
  private static final List<String> listMovieTitle = ListExtensions.<Movie, String>map(Movies.movies, ((Function1<Movie, String>) (Movie a) -> {
    return a.getTitle();
  }));
  
  private static final int theMovies = ((Object[])Conversions.unwrapArray(IterableExtensions.<Movie>filter(Movies.movies, ((Function1<Movie, Boolean>) (Movie a) -> {
    return Boolean.valueOf(a.getTitle().startsWith("The"));
  })), Object.class)).length;
  
  private static final int countMysteryMouvies = ((Object[])Conversions.unwrapArray(IterableExtensions.<Movie>filter(Movies.movies, ((Function1<Movie, Boolean>) (Movie a) -> {
    return Boolean.valueOf(a.getCategories().contains("Mystery"));
  })), Object.class)).length;
  
  private static final Iterable<Movie> moviesWithMostCategories = IterableExtensions.<Movie>filter(Movies.movies, ((Function1<Movie, Boolean>) (Movie a) -> {
    int _length = ((Object[])Conversions.unwrapArray(a.getCategories(), Object.class)).length;
    final Function1<Movie, Integer> _function = (Movie it) -> {
      return Integer.valueOf(((Object[])Conversions.unwrapArray(it.getCategories(), Object.class)).length);
    };
    List<Movie> _sortBy = IterableExtensions.<Movie, Integer>sortBy(Movies.movies, _function);
    int _length_1 = ((Object[])Conversions.unwrapArray(Movies.movies, Object.class)).length;
    int _minus = (_length_1 - 1);
    int _length_2 = ((Object[])Conversions.unwrapArray(_sortBy.get(_minus).getCategories(), Object.class)).length;
    return Boolean.valueOf((_length == _length_2));
  }));
  
  private static final Set<Map.Entry<Integer, List<Movie>>> yearWithLessMovies = IterableExtensions.<Integer, Movie>groupBy(Movies.movies, ((Function1<Movie, Integer>) (Movie it) -> {
    return Integer.valueOf(it.getYear());
  })).entrySet();
  
  private static final int year1 = IterableExtensions.<Movie, Integer>minBy(Movies.movies, ((Function1<Movie, Integer>) (Movie it) -> {
    return Integer.valueOf(it.getYear());
  })).getYear();
  
  private static final int year2 = IterableExtensions.<Movie, Integer>maxBy(Movies.movies, ((Function1<Movie, Integer>) (Movie it) -> {
    return Integer.valueOf(it.getYear());
  })).getYear();
  
  private static final Iterable<Integer> abc = IterableExtensions.<Integer>filter(new IntegerRange(Movies.year1, Movies.year2), ((Function1<Integer, Boolean>) (Integer a) -> {
    final Function1<Movie, Integer> _function = (Movie it) -> {
      return Integer.valueOf(it.getYear());
    };
    boolean _contains = ListExtensions.<Movie, Integer>map(Movies.movies, _function).contains(a);
    return Boolean.valueOf((!_contains));
  }));
  
  public static void main(final String[] args) {
    InputOutput.<CharSequence>println(Movies.getNBottles(Integer.valueOf(20)));
  }
  
  public static CharSequence printWithTemplate(final List<Movie> movies) {
    StringConcatenation _builder = new StringConcatenation();
    {
      boolean _hasElements = false;
      for(final Movie movie : movies) {
        if (!_hasElements) {
          _hasElements = true;
          _builder.append("Filme:");
        } else {
          _builder.appendImmediate("\n----------------------", "");
        }
        _builder.newLineIfNotEmpty();
        _builder.append("Titel:               ");
        String _title = movie.getTitle();
        _builder.append(_title);
        _builder.newLineIfNotEmpty();
        _builder.append("Rating:              ");
        double _rating = movie.getRating();
        _builder.append(_rating);
        _builder.newLineIfNotEmpty();
        _builder.append("Erscheinungsjahr:    ");
        int _year = movie.getYear();
        _builder.append(_year);
        _builder.newLineIfNotEmpty();
        _builder.newLine();
        {
          double _rating_1 = movie.getRating();
          boolean _greaterEqualsThan = (_rating_1 >= 7.0);
          if (_greaterEqualsThan) {
            _builder.append("Ganz klare Empfehlung!");
            _builder.newLineIfNotEmpty();
          } else {
            double _rating_2 = movie.getRating();
            boolean _greaterEqualsThan_1 = (_rating_2 >= 5.0);
            if (_greaterEqualsThan_1) {
              _builder.append("Mittelmäßige Kritiken!");
            }
          }
        }
        _builder.newLineIfNotEmpty();
        _builder.newLine();
        _builder.append("Kategorien:          ");
        {
          Set<String> _categories = movie.getCategories();
          boolean _hasElements_1 = false;
          for(final String category : _categories) {
            if (!_hasElements_1) {
              _hasElements_1 = true;
            } else {
              _builder.appendImmediate(", ", "");
            }
            _builder.append(category);
          }
        }
        _builder.newLineIfNotEmpty();
      }
    }
    return _builder;
  }
  
  public static CharSequence getNBottles(final Integer n) {
    StringConcatenation _builder = new StringConcatenation();
    {
      IntegerRange _upTo = new IntegerRange((n).intValue(), 1);
      boolean _hasElements = false;
      for(final Integer i : _upTo) {
        if (!_hasElements) {
          _hasElements = true;
        } else {
          _builder.appendImmediate("", "");
        }
        _builder.newLineIfNotEmpty();
        _builder.append(i);
        _builder.append(" bottles of beer on the wall, ");
        _builder.append(i);
        _builder.append(" bottles of beer.");
        _builder.newLineIfNotEmpty();
        _builder.append("Take one down and pass it around, ");
        _builder.append(((i).intValue() - 1));
        _builder.append(" bottles of beer on the wall.");
      }
    }
    _builder.newLineIfNotEmpty();
    return _builder;
  }
}
