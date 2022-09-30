package com.cubesofttech.dao;

import java.util.List;
import java.util.Map;
import com.cubesofttech.model.Sysuser;

public interface SysuserDAO {
    public void save(Sysuser sys_user) throws Exception;
    
    public List<Map<String, Object>> sequense() throws Exception;
    
    public List<Sysuser> findAll() throws Exception;
    public List<Map<String, Object>> findAllList() throws Exception;
    public List<Sysuser> findBySysuserId(String sysuserId) throws Exception;
    
    public Sysuser findById(String sys_user_id) throws Exception;
    public void update(Sysuser sysuser) throws Exception;
    
    public void delete(Sysuser sysuser) throws Exception;

	public List<Sysuser> search(String sysuserId) throws Exception;

	public List<Map<String, Object>> findById2(String id) throws Exception;

	public List<Map<String, Object>> sysUserEnable(String user);
	
	public List<Sysuser> listOfUser_Role(String role) throws Exception;
	
	public List<Map<String, Object>> listOfNotEqualSysrole(String role) throws Exception;

	public List<Sysuser> findBySysuserID(String loginUser);

	public List<Map<String, Object>> CheckCurrent(String current_m, String loginUser);

}
