package com.problems;

import java.util.Stack;

/**
 * Decimal to binary ->
 * Algorithm :-
 * 1. ans = 0;
 * 2. power = 1;
 * 3. Iterate till given number is not 0
 * 		4. find mod using 2 and multiply it with power
 * 		5. add result of step 4 with ans
 * 		6. multiply power with 10
 * 		7. update given number as number / 2
 *
 *
 *
 * Binary to Decimal ->
 * Algorithm :-
 * 1. ans = 0;
 * 2. power = 1;
 * 3. Iterate till given number is not 0
 * 		4. find mod using 10 and multiply it with power
 * 		5. add result of step 4 with ans
 * 		6. multiply power with 2
 * 		7. update given number as number / 10
 */
public class BinaryNumber {

		public static void main(String[] args) {
				System.out.println("BinaryNumber Started");

				int number = 57;
				String result = getBinaryUsingString(number);
				System.out.println("Binary number of : "+number+" using string is "+result);

				int intResult = getBinaryUsingInt(number);

				System.out.println("Binary number of : "+number+" using int is "+intResult);

				int decimalResult = getDecimalUsingInt(intResult);

				System.out.println("Decimal number of : "+intResult+" using int is "+decimalResult);

				System.out.println("BinaryNumber ended");
		}
		private static String getBinaryUsingString(int a){

				String ans = new String();

				while(a!=0){
						ans = a%2 + ans;
						a = a/2;
				}

				return ans;
		}

		private static int getBinaryUsingInt(int a){

				int ans = 0;

				int power = 1;

				while(a!=0){
						int remainder = a%2;
						a = a/2;

						remainder = remainder * power;
						ans = ans+remainder;

						power = power*10;
				}

				return ans;
		}

		private static int getDecimalUsingInt(int a){

				int answer = 0;

				int power = 1;

				while(a!=0){
						int remainder = a%10;
						a = a/10;

						remainder = power * remainder;

						answer = answer + remainder;

						power = power * 2;
				}

				return answer;
		}

}
