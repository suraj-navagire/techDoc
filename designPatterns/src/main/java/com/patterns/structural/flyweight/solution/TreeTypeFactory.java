package com.patterns.structural.flyweight.solution;

import java.util.HashMap;
import java.util.Map;

/**
 * This is factory class which will return flyweight object from cache if present else it will create new object and store it in cache.
 */
public class TreeTypeFactory {

		private static Map<String, TreeType> treeTypeCache = new HashMap<>();

		public static TreeType getTreeType(String name, String color){
				String key = name + "."+ color;
				TreeType treeType = treeTypeCache.computeIfAbsent(key, val -> new TreeType(name, color));

				return treeType;
		}
}
