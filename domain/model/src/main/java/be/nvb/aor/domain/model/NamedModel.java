package be.nvb.aor.domain.model;

public abstract class NamedModel {

	private String name;

	/**
	 * Gets the name of the {@link Model}.
	 * 
	 * @return the name of the {@link Model}
	 */
	public String getName() {
		return name;
	}
	
	/**
	 * Set the name of the {@link Model}.
	 * 
	 * @param name the name of the {@link Model}
	 */
	public void setName(String name) {
		this.name = name;
	}

}
