/*******************************************************************************
 * Copyright (c) 2012 itemis AG (http://www.itemis.eu) and others.
 * This program and the accompanying materials are made available under the
 * terms of the Eclipse Public License 2.0 which is available at
 * http://www.eclipse.org/legal/epl-2.0.
 *
 * SPDX-License-Identifier: EPL-2.0
 * 
 * Author - Sven Efftinge
 *******************************************************************************/
package example6

import java.io.FileReader
import java.util.Set
import org.eclipse.xtend.lib.annotations.Data
import org.junit.Test

import static org.junit.Assert.*

import static extension com.google.common.io.CharStreams.*
import java.util.List

class Movies {
	
	/**
	 * @return the total number of action movies
	 */ 
	@Test def void numberOfActionMovies() {
		assertEquals(828, movies.filter[categories.contains('Action')].size)
	}
	
	/**
	 * @return the year the best rated movie of 80ies (1980-1989) was released.
	 */
	@Test def void yearOfBestMovieFrom80ies() {
		assertEquals(1989, movies.filter[(1980..1989).contains(year)].maxBy[rating].year)
	}
	
	/**
	 * @return the sum of the number of votes of the two top rated movies.
	 */
	@Test def void sumOfVotesOfTop2() {
		val long movies = movies.sortBy[-rating].take(2).map[numberOfVotes].reduce[a, b| a + b]
		assertEquals(47_229, movies)
	}
	
	static val movies = new FileReader('data.csv').readLines.map[ line |
		val segments = line.split('  ').iterator
		return new Movie(
			segments.next, 
			Integer.parseInt(segments.next), 
			Double.parseDouble(segments.next), 
			Long.parseLong(segments.next), 
			segments.toSet
		)
	]
	
	static val oldestMovieYear = movies.minBy[year].year
	static val listMovieTitle = movies.map[ a | a.title ]
	static val theMovies = movies.filter[ a | a.title.startsWith("The")].length
	static val countMysteryMouvies = movies.filter[ a | a.categories.contains("Mystery")].length
	static val moviesWithMostCategories = movies.filter[ a | a.categories.length == movies.sortBy[categories.length].get(movies.length-1).categories.length ]
	static val yearWithLessMovies = movies.groupBy[year].entrySet
	static val year1 = movies.minBy[year].year
	static val year2 = movies.maxBy[year].year
	static val abc = (year1..year2).filter[ a | !movies.map[year].contains(a)]
	
	def static void main(String[] args) {
		println(getNBottles(20))
	}
	
	def static printWithTemplate(List<Movie> movies) {
		'''??FOR movie : movies BEFORE "Filme:" SEPARATOR "
----------------------"??
		   Titel:               ??movie.title??
		   Rating:              ??movie.rating??
		   Erscheinungsjahr:    ??movie.year??
		   
		   ??IF movie.rating >= 7.0??Ganz klare Empfehlung!
		   ??ELSEIF movie.rating >= 5.0??Mittelm????ige Kritiken!??ENDIF??
		   
		   Kategorien:          ??FOR category : movie.categories SEPARATOR ", "????category????ENDFOR??
		   ??ENDFOR??
		   '''
	}
	
	def static getNBottles(Integer n) {
		'''??FOR i : (n..1) SEPARATOR ""??
		??i?? bottles of beer on the wall, ??i?? bottles of beer.
		Take one down and pass it around, ??i-1?? bottles of beer on the wall.??ENDFOR??
		'''
	}
}

@Data class Movie {
	String title
	int year
	double rating
	long numberOfVotes
	Set<String> categories 
}