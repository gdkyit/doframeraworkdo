package com.gdky.restful.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import com.gdky.restful.entity.Role;
import com.gdky.restful.entity.User;
@Repository
public class MainDaoImpl extends BaseJdbcDao  {

	
	public List<Role> getUserRoles(String username) {
		// TODO Auto-generated method stub
		String sql ="select c.* from user a,user_role b,role c where a.id_=b.user_id and b.role_id=c.id_ and a.login_name=?";
		return this.jdbcTemplate.query(sql, new Object[] { username },
				new RoleRowMapper());
	}



	private class RoleRowMapper implements RowMapper<Role> {
		public Role mapRow(final ResultSet rs, final int arg1) throws SQLException {
			Role role = new Role();
			role.setId_(rs.getInt("id_"));
			role.setMs(rs.getString("ms"));
			role.setRole_Name(rs.getString("role_Name"));
			return role;
		}
	}
	
	
	

	private class UserRowMapper implements RowMapper<User> {
		public User mapRow(final ResultSet rs, final int arg1) throws SQLException {
			User user = new User();
			user.setId_(rs.getInt("id_"));
			user.setLogin_Name(rs.getString("login_Name"));
			user.setDeptid(rs.getInt("deptid"));
			user.setPhone(rs.getString("phone"));
			user.setRzsj(rs.getDate("rzsj"));
			user.setPwd(rs.getString("pwd"));
			user.setUser_Name(rs.getString("user_Name"));
			user.setPhoto(rs.getString("photo"));
			return user;
		}
	}

	

	
	public User getUser(String username) {
		// TODO Auto-generated method stub
		String sql = " select * from user where login_name= ?";
		List<User> users= this.jdbcTemplate.query(sql.toString(), new Object[] {username  },
				new UserRowMapper());
		if(users.size()>0){
			return users.get(0);
		}else{
			return null;
		}
	}


		


}
