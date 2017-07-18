package com.gdky.restful.entity;

import java.io.Serializable;

public class Role implements Serializable {

	/** serialVersionUID. */
	private static final long serialVersionUID = 1L;

	/** Id. */
	private Integer id;

	/** 角色名称. */
	private String roleName;

	/** 描述. */
	private String description;

	private int level;

	/**
	 * Constructor.
	 */
	public Role() {
	}

	/**
	 * Set the ID_.
	 * 
	 * @param id
	 *            ID_
	 */
	public void setId_(Integer id) {
		this.id = id;
	}

	/**
	 * Get the Id.
	 * 
	 * @return Id
	 */
	public Integer getId() {
		return this.id;
	}

	/**
	 * Set the 角色名称.
	 * 
	 * @param roleName
	 *            角色名称
	 */
	public void setRoleName(String roleName) {
		this.roleName = roleName;
	}

	/**
	 * Get the 角色名称.
	 * 
	 * @return 角色名称
	 */
	public String getRoleName() {
		return this.roleName;
	}
	
	
	
	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public int getLevel() {
		return level;
	}

	public void setLevel(int level) {
		this.level = level;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		return result;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}
		if (obj == null) {
			return false;
		}
		if (getClass() != obj.getClass()) {
			return false;
		}
		Role other = (Role) obj;
		if (id == null) {
			if (other.id != null) {
				return false;
			}
		} else if (!id.equals(other.id)) {
			return false;
		}
		return true;
	}
}
