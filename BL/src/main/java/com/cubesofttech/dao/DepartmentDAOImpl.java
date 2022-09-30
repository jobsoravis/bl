package com.cubesofttech.dao;

import java.util.List;
import java.util.Map;

import org.hibernate.Criteria;
import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.hibernate.transform.AliasToEntityMapResultTransformer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.cubesofttech.model.Department;
@Repository
public class DepartmentDAOImpl implements DepartmentDAO {

	@Autowired
	private SessionFactory sessionFactory;

	@Override
	public void save(Department department) throws Exception {
		// TODO Auto-generated method stub
		Session session = this.sessionFactory.getCurrentSession();
		session.save(department);
		session.flush();
	}

	@Override
	public List<Map<String, Object>> sequense() throws Exception {
		// TODO Auto-generated method stub
		Session session = this.sessionFactory.getCurrentSession();
		List<Map<String, Object>> department_id = null;
		try {
			String sql = "SELECT department_id, CONCAT(department_id) FROM department_id ORDER BY department_id ASC";
			SQLQuery query = session.createSQLQuery(sql);
			query.setResultTransformer(AliasToEntityMapResultTransformer.INSTANCE);
			department_id = query.list();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return department_id;

	}

	@Override
	public List<Department> findAll() throws Exception {
		// TODO Auto-generated method stub
		Session session = this.sessionFactory.getCurrentSession();
		List<Department> departmentList = null;
		try {
			departmentList = session.createCriteria(Department.class).list();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return departmentList;
	}

	@Override
	public List<Map<String, Object>> findAllList() throws Exception {
		// TODO Auto-generated method stub
		Session session = this.sessionFactory.getCurrentSession();
		List<Map<String, Object>> departmentList = null;
		try {
			String sql = "SELECT * FROM department";
			SQLQuery query = session.createSQLQuery(sql);
			query.setResultTransformer(AliasToEntityMapResultTransformer.INSTANCE);
			departmentList = query.list();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return departmentList;
	}

	@Override
	public List<Department> findByDepartmentId(String departmentId) throws Exception {
		// TODO Auto-generated method stub
		Session session = this.sessionFactory.getCurrentSession();
		List<Department> list = null;
		try {
			Criteria cr = session.createCriteria(Department.class);
			cr.add(Restrictions.eq("departmentId", departmentId));

			list = cr.list();
		} catch (Exception e) {
			e.printStackTrace();
			return null;

		} finally {

		}
		return list;
	}

	@Override
	public Department findById(String id) throws Exception {
		// TODO Auto-generated method stub
		Session session = this.sessionFactory.getCurrentSession();
		Department department = null;
		try {
			department =(Department) session.get(Department.class, id);
		} catch (Exception e) {
			e.printStackTrace();
			return null;

		} finally {

		}
		return department;
	}

	@Override
	public void update(Department department) throws Exception {
		// TODO Auto-generated method stub
		Session session = this.sessionFactory.getCurrentSession();
        session.clear();
        session.update(department);
        session.flush();
	}

	@Override
	public void delete(Department department) throws Exception {
		Session session = this.sessionFactory.getCurrentSession();
        session.delete(department);
        session.flush();
	}

	@Override
	public void saveOrUpdate(Department department) throws Exception {
		Session session = this.sessionFactory.getCurrentSession();
        session.clear();
        session.saveOrUpdate(department);
        session.flush();
	}

	@Override
	public List<Department> search(String departmentId) throws Exception {
		// TODO Auto-generated method stub
		Session session = this.sessionFactory.getCurrentSession();
		List<Department> list = null;
		try {
			Criteria cr = session.createCriteria(Department.class);
			cr.add(Restrictions.eqOrIsNull("departmentId", departmentId));
			
			list = cr.list();
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		} finally {
			
		}
		return list;
	}

	@Override
	public List<Map<String, Object>> sequense2() throws Exception {
		// TODO Auto-generated method stub
		Session session = this.sessionFactory.getCurrentSession();
		List<Map<String, Object>> department_id = null;
		try {
			String sql = " SELECT department_id, CONCAT(department_id), description FROM department  ORDER BY department_id ASC  ";
			SQLQuery query = session.createSQLQuery(sql);
			query.setResultTransformer(AliasToEntityMapResultTransformer.INSTANCE);
			department_id = query.list();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return department_id;
	}

	@Override
	public List<Map<String, Object>> fullNameDepartment() throws Exception {
		// TODO Auto-generated method stub
		Session session = this.sessionFactory.getCurrentSession();
		List<Map<String, Object>> department_id = null;
		try {
			String sql = " SELECT department_id, name, CONCAT(department_id) FROM department  ORDER BY department_id ASC  ";
			SQLQuery query = session.createSQLQuery(sql);
			query.setResultTransformer(AliasToEntityMapResultTransformer.INSTANCE);
			department_id = query.list();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return department_id;
	}

}
