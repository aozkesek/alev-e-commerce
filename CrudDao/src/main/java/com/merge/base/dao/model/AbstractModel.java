package com.merge.base.dao.model;

public abstract class AbstractModel {
	
	public static String INVALID = "InvalidModelException";
	
	public abstract Integer getId();
	public abstract Integer getVersion();
	public abstract boolean isValid();
	
	@Override
	public int hashCode() {
		return getId() != null ? getId().hashCode() : 0;
	}
	
	@Override
	public boolean equals(Object o) {
		if (this == o)
			return true;
		
		if (!(o instanceof AbstractModel))
			return false;

		if (getId() == null)
			return false;
		
		AbstractModel co = (AbstractModel)o;
		if (!getId().equals(co.getId()))
			return false;
		
		//even if ID is equal, we also make sure that two's version is equal, only than we can say both are equal each other.
		if (getVersion() != null)
			if (co.getVersion() != null) {
				if (!getVersion().equals(co.getVersion()))
					return false;
			}
			else
				return false;
		
		return true;
	}

}
