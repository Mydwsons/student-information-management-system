package com.xzl.test;

import java.util.regex.Pattern;

public class Test {
	public static void main(String[] args) {
		String test = "A23456";
		String reg = "^(?![0-9]+$)(?![a-zA-Z]+$)[0-9A-Za-z]{6,16}$";
		boolean b = Pattern.matches(reg, test);
		System.out.println(b);
	}
}
