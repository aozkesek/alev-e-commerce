package com.merge.base.dao.model;

public abstract class AbstractModel {
	
	public abstract Integer getId();

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
		
		return getId().equals(((AbstractModel)o).getId());
	}

}
