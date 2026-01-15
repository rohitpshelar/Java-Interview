package com.rs.Stream;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;


//Intermediate Operations:

//map: The map method is used to returns a stream consisting of the results of applying the given function to the elements of this stream.
	//List number = Arrays.asList(2,3,4,5);
	//List square = number.stream().map(x->x*x).collect(Collectors.toList());
//filter: The filter method is used to select elements as per the Predicate passed as argument.
	//List names = Arrays.asList("Reflection","Collection","Stream");
	//List result = names.stream().filter(s->s.startsWith("S")).collect(Collectors.toList());
//sorted: The sorted method is used to sort the stream.
	//List names = Arrays.asList("Reflection","Collection","Stream");
	//List result = names.stream().sorted().collect(Collectors.toList());

//Terminal Operations:

//collect: The collect method is used to return the result of the intermediate operations performed on the stream.
	//List number = Arrays.asList(2,3,4,5,3);
	//Set square = number.stream().map(x->x*x).collect(Collectors.toSet());
//forEach: The forEach method is used to iterate through every element of the stream.
	//List number = Arrays.asList(2,3,4,5);
	//number.stream().map(x->x*x).forEach(y->System.out.println(y));
//reduce: The reduce method is used to reduce the elements of a stream to a single value.
	//The reduce method takes a BinaryOperator as a parameter.
	//List number = Arrays.asList(2,3,4,5);
	//int even = number.stream().filter(x->x%2==0).reduce(0,(ans,i)-> ans+i);

public class JavaStreamTest {
	public static void main(String[] args) {
		
		System.out.println("\n--Sum of All number in list--");
		System.out.println(IntStream.range(1, 10).sum());

		System.out.println("\n--Count of List Data--");
		System.out.println(Stream.of("Rohit","Shelar","AA").count());
		
		System.out.println("\n--Sort and Print First data--");
		Stream.of("Rohit","Shelar","AA").sorted().findFirst().ifPresent(System.out::println);
		
		System.out.println("\n--Print Word Stars with 'S'--");
		String[] names = {"Rohit","Shelar","AA"};
		Arrays.stream(names).filter(e->e.startsWith("S")).forEach(System.out::println);
		 
		System.out.println("\n--Average of numbers--");
		Arrays.stream(new int[] {2,1,6,3}).map(e->e*e).forEach(System.out::print);
        System.out.println("\n--Average of squares--");
        Arrays.stream(new int[] {2,1,6,3}).map(e->e*e).average().ifPresent(System.out::print);
        System.out.println(" string");
        System.out.println(Arrays.stream(new int[]{2, 1, 6, 3})
                .map(e -> e * e)
                .mapToObj(String::valueOf)
                .collect(Collectors.joining(",")));

		System.out.println("\n--Camel Case--");
		List<String> pnames= Arrays.asList("Rohit","Shelar","AA","anna");
		pnames.stream().map(String::toLowerCase).filter(e->e.startsWith("a")).map(x -> x.substring(0, 1).toUpperCase() + x.substring(1).toLowerCase()).forEach(e -> System.out.print(e+","));
		
		System.out.println("\n--List Reverse Types--");
			//types of reverse List
        System.out.println("\n--Orig--");
			List<Integer> list = new ArrayList<Integer>(Arrays.asList(1, 3, 3, 7, 5));
        list.forEach(System.out::print); // 1 3 3 7 5

        int size = list.size();

        System.out.println("\n--Using Iterator--");
        ListIterator<Integer> it = list.listIterator(size);
			Stream.generate(it::previous).limit(size)
			    .forEach(System.out::print); // 5 7 3 3 1

        System.out.println("\n--Using map--");
        IntStream.range(0, list.size()).mapToObj(i -> list.get(list.size() - 1 - i)).forEach(System.out::print);

        System.out.println("\n--Using Comparator.reverseOrder--");
        list.stream().sorted(Comparator.reverseOrder()).forEach(System.out::print);

        System.out.println("\n--Using Collectors.collectingAndThen--");
        list.stream().collect(Collectors.collectingAndThen(Collectors.toList(), l -> {
                    Collections.reverse(l);
                    return l;
                })).forEach(System.out::print);

        System.out.println("\n--Using Collections.reverse--");
        Collections.reverse(list);                  // reverse in-place
        list.forEach(System.out::print);

    }
	
}