package com.systemdesign.lld.financiallimitsystem.model;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

public class LimitPackage {

		private String id;
		private String name;
		private String description;

		private Map<String, List<ILimit>> listMap;

		public LimitPackage(String name, String description) {
				this.id = UUID.randomUUID().toString();
				this.name = name;
				this.description = description;
				this.listMap = new HashMap<>();
		}

		public String getId() {
				return id;
		}

		public void setId(String id) {
				this.id = id;
		}

		public String getName() {
				return name;
		}

		public void setName(String name) {
				this.name = name;
		}

		public String getDescription() {
				return description;
		}

		public void setDescription(String description) {
				this.description = description;
		}

		public Map<String, List<ILimit>> getLimitMap() {
				return listMap;
		}

		public void addLimitMap(String transaction, ILimit limit) {
				List<ILimit> limits = listMap.computeIfAbsent(transaction, v -> new ArrayList<>());
				limits.add(limit);
		}
}
