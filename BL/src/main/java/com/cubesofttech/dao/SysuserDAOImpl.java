package com.cubesofttech.dao;

import java.util.List;
import java.util.Map;

import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.hibernate.transform.AliasToEntityMapResultTransformer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import com.cubesofttech.model.Sysuser;

@Repository
public class SysuserDAOImpl implements SysuserDAO{
	@Autowired
		private SessionFactory sessionFactory;
	
	@Override
	public void save(Sysuser sysuser) throws Exception {
		Session session = this.sessionFactory.getCurrentSession();
	    session.save(sysuser);
	    session.flush();
	}
	
	@Override
	public List<Map<String, Object>> sequense() throws Exception {
		Session session = this.sessionFactory.getCurrentSession();
		List<Map<String, Object>> sysuser_id = null;
		try {
			String sql = " SELECT sys_user_id, CONCAT(sys_user_id) FROM sys_user  ORDER BY sys_user_id;  ";
			SQLQuery query = session.createSQLQuery(sql);
			query.setResultTransformer(AliasToEntityMapResultTransformer.INSTANCE);
			sysuser_id = query.list();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return sysuser_id;
	}
	
	@Override
	public List<Sysuser> findAll() throws Exception {
		Session session = this.sessionFactory.getCurrentSession();
		List<Sysuser> sysuserList = null;
		try {
			String sql = "SELECT * FROM sys_user" ;  
			SQLQuery query = session.createSQLQuery(sql);
			query.setResultTransformer(AliasToEntityMapResultTransformer.INSTANCE);
			sysuserList = query.list();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return sysuserList;
	}
	
	@Override
	public List<Map<String, Object>> findAllList() throws Exception {
		Session session = this.sessionFactory.getCurrentSession();
		List<Map<String, Object>> sysuserList = null;
		try {
			String sql = "SELECT * FROM sys_user";
			SQLQuery query = session.createSQLQuery(sql);
			query.setResultTransformer(AliasToEntityMapResultTransformer.INSTANCE);
			sysuserList = query.list();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return sysuserList;
	}
	
	@Override
	public List<Sysuser> findBySysuserId(String sysuserId) throws Exception {
		Session session = this.sessionFactory.getCurrentSession();
	    List<Sysuser> list = null;
	    try {
	        
	        Criteria cr = session.createCriteria(Sysuser.class);
	        cr.add(Restrictions.eq("sysuserId", sysuserId));
	        
	        list = cr.list();
	
	    } catch (Exception e) {
	    	e.printStackTrace();
	    	return null;
	
	    } finally {
	
	    }
	    return list;
	}
	
	@Override
	public Sysuser findById(String sys_user_id) throws Exception {
		Session session = this.sessionFactory.getCurrentSession();
	    Sysuser sysuser = null;
	
	    try {
	        sysuser = (Sysuser) session.get(Sysuser.class, sys_user_id);
	    } catch (Exception e) {
	        e.printStackTrace();
	    }finally{
	        //session.close();
	    }        
	    return sysuser;
	}
	
	@Override
	public void update(Sysuser sysuser) throws Exception {
		 Session session = this.sessionFactory.getCurrentSession();
	     session.clear();
	     session.update(sysuser);
	     session.flush();
	}
	
	@Override
	public void delete(Sysuser sysuser) throws Exception {
		Session session = this.sessionFactory.getCurrentSession();
	    session.delete(sysuser);
	    session.flush();
	}
	
	@Override
	public List<Sysuser> search(String sysuserId) throws Exception {
		Session session = this.sessionFactory.getCurrentSession();
	    List<Sysuser> list = null;
	    try {
	        
	        Criteria cr = session.createCriteria(Sysuser.class);
	        cr.add(Restrictions.eq("sysuserId", sysuserId));
	        
	        list = cr.list();
	
	    } catch (Exception e) {
	    	e.printStackTrace();
	    	return null;
	
	    } finally {
	
	    }
	    return list;
		}
	
	@Override
	public List<Map<String, Object>> findById2(String id) throws Exception {
		Session session = this.sessionFactory.getCurrentSession();
		List id1 = null;
		try {
			String sql = " SELECT * FROM sys_user WHERE sys_user_id = :id ";
			SQLQuery query = session.createSQLQuery(sql);
			query.setParameter("id", id);
			query.setResultTransformer(AliasToEntityMapResultTransformer.INSTANCE);
			id1 = query.list();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return id1;
	
	}
	
	public List<Map<String, Object>> sysUserEnable(String user) {
	
		Session session = this.sessionFactory.getCurrentSession();
		List<Map<String, Object>> UserActive = null;
	
		try {
			String sql = "SELECT sys_user_id,name_th,user_id,sys_role_id FROM sys_user WHERE is_active = '1' AND sys_user_id LIKE '%"+user+"%'";
			SQLQuery query = session.createSQLQuery(sql);
			query.setResultTransformer(AliasToEntityMapResultTransformer.INSTANCE);
			UserActive = query.list();
		} catch (HibernateException e) {
			e.printStackTrace();
		}
		return UserActive;
	}

	@Override
	public List<Sysuser> listOfUser_Role(String role) throws Exception {
		Session session = this.sessionFactory.getCurrentSession();
		List<Sysuser> User_Role = null;
	
		try {
			String sql = "SELECT sys_user.* from sys_user \r\n"
					+ "JOIN sys_role ON sys_user.sys_role_id = sys_role.name \r\n"
					+ "WHERE sys_role.name = '"+role+"'\r\n"
					+ "ORDER BY sys_user.sys_user_id ASC;";
			SQLQuery query = session.createSQLQuery(sql);
			query.setResultTransformer(AliasToEntityMapResultTransformer.INSTANCE);
			User_Role = query.list();
		} catch (HibernateException e) {
			e.printStackTrace();
		}
		return User_Role;
	}

	@Override
	public List<Map<String, Object>> listOfNotEqualSysrole(String role) throws Exception {
		Session session = this.sessionFactory.getCurrentSession();
		List<Map<String, Object>> User_Role = null;
	
		try {
			String sql = "SELECT * from sys_user\r\n"
					+ "WHERE sys_role_id is NULL || sys_role_id != '"+role+"'\r\n"
					+ "ORDER BY sys_user_id ASC;";
			SQLQuery query = session.createSQLQuery(sql);
			query.setResultTransformer(AliasToEntityMapResultTransformer.INSTANCE);
			User_Role = query.list();
		} catch (HibernateException e) {
			e.printStackTrace();
		}
		return User_Role;
	}

	@Override
	public List<Sysuser> findBySysuserID(String loginUser) {
		Session session = this.sessionFactory.getCurrentSession();
		List<Sysuser> sysuserList = null;
	
		try {
			String sql = "SELECT * FROM `sys_user` where sys_user_id = '"+loginUser+"'";
			SQLQuery query = session.createSQLQuery(sql);
			query.setResultTransformer(AliasToEntityMapResultTransformer.INSTANCE);
			sysuserList = query.list();
		} catch (HibernateException e) {
			e.printStackTrace();
		}
		return sysuserList;
	}

	@Override
	public List<Map<String, Object>> CheckCurrent(String current_m , String loginUser) {
		Session session = this.sessionFactory.getCurrentSession();
		List<Map<String, Object>> sysuserList = null;
	
		try {
			String sql = "SELECT * FROM `sys_user`where sys_user_id = '"+loginUser+"' and password = '"+current_m+"' ";
			SQLQuery query = session.createSQLQuery(sql);
			query.setResultTransformer(AliasToEntityMapResultTransformer.INSTANCE);
			sysuserList = query.list();
		} catch (HibernateException e) {
			e.printStackTrace();
		}
		return sysuserList;
	}
	
}
