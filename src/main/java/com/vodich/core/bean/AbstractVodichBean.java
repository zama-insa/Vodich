package com.vodich.core.bean;

import java.lang.reflect.Field;

public abstract class AbstractVodichBean {

	@Override
	public int hashCode() {
		int result = 0;
		for (Field field : this.getClass().getDeclaredFields()) {
			// private fields
			if (!field.isEnumConstant() && !field.isAccessible()) {
				try {
					field.setAccessible(true);
					Object obj = field.get(this);
					result += (obj != null ? obj.hashCode() : 0);
					field.setAccessible(false);
				} catch (IllegalArgumentException | IllegalAccessException e) {
					throw new RuntimeException("Error computing hashcode for java bean class " + this.getClass().getCanonicalName(), e);
				}
			}
		}
		return result;
	}
	
	@Override
	public boolean equals(Object obj) {
		return this.hashCode() == obj.hashCode();
	}
}
