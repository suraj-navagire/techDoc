package com.patterns.creational.factoryPattern;

public abstract class Computer {

		abstract String getRam();

		abstract String getHDD();

		abstract String getCore();

		@Override
		public String toString(){
				return "Configurations are as follows : "+
						"Ram Size : " +this.getRam()+", "+
						"Hard disk Size : "+this.getHDD()+", "+
						"Number of cores : "+this.getCore();
		}
}
