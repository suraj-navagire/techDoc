package com.patterns.creational.builder;

public class URLWithoutBuilderTest {
		public static void main(String[] args) {
				System.out.println("URLWithoutBuilderTest Started");

				//We are creating URL
				URLWithoutBuilder url = new URLWithoutBuilder("http","localhost","8080");

				System.out.println("URL : "+url.getProtocol()+"://"+url.getHostname()+":"+url.getPort()+"/"+url.getPathParam()+"?"+url.getPathParam());

				//updating port which should not be allowed once url formed
				url.setPort("9090");

				System.out.println("URL : "+url.getProtocol()+"://"+url.getHostname()+":"+url.getPort()+"/"+url.getPathParam()+"?"+url.getPathParam());

		}
}
