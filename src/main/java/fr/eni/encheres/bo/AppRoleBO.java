package fr.eni.encheres.bo;

public class AppRoleBO {
	
	private Long roleId;
	private String roleName;
	
	/**
	 * @return the roleId
	 */
	public Long getRoleId() {
		return roleId;
	}
	/**
	 * @param roleId the roleId to set
	 */
	public void setRoleId(Long roleId) {
		this.roleId = roleId;
	}
	/**
	 * @return the roleName
	 */
	public String getRoleName() {
		return roleName;
	}
	/**
	 * @param roleName the roleName to set
	 */
	public void setRoleName(String roleName) {
		this.roleName = roleName;
	}
	
	/**
	 * 
	 */
	public AppRoleBO() {
	}
	/**
	 * @param roleId
	 * @param roleName
	 */
	public AppRoleBO(Long roleId, String roleName) {
		super(); 
		this.roleId = roleId;
		this.roleName = roleName;
	}
	
	
	
}
