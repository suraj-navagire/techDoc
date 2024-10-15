package com.patterns.creational.builder;

public class URLWithBuilderTest {
		public static void main(String[] args) {
				System.out.println("URLWithBuilderTest Started");

				//We are creating URL
				URLWithBuilder.Builder builder = URLWithBuilder.getBuilder();
				builder.protocol("http");
				builder.hostname("localhost");
				builder.port("8080");

				URLWithBuilder url = builder.build();

				System.out.println("URL : "+url.getProtocol()+"://"+url.getHostname()+":"+url.getPort()+"/"+url.getPathParam()+"?"+url.getPathParam());

		}
}
