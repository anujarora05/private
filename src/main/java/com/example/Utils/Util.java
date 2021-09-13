package com.example.Utils;

import java.util.function.Function;

import org.apache.commons.lang3.tuple.Pair;



public class Util {

	public static <T,R,E extends Exception> Function<T,R> mapOrWrapException(ThrowingFunction<T,R,E> throwingFunction){
		return t -> {
			try {
				return throwingFunction.apply(t);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				throw new RuntimeException(e);
			}
		};
		
	}
	
	public static <T,R,E extends Exception> Function<T,Either> either(ThrowingFunction<T,R,E> throwingFunction){
		return t -> {
			try {
				return Either.Right(throwingFunction.apply(t));
			} catch (Exception e) {
				// TODO Auto-generated catch block
				return Either.Left(Pair.of(t, e));
			}
		};
		
	}
}
