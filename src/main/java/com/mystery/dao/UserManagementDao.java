package com.mystery.dao;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;
/*import org.apache.jasper.tagplugins.jstl.core.Catch;*/
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;

import com.google.gson.Gson;
import com.mystery.beans.CityBean;
import com.mystery.beans.CountryBean;
import com.mystery.beans.StateBean;
import com.mystery.beans.TestBean;
import com.mystery.beans.UserBean;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class UserManagementDao {

	@Autowired
    JdbcTemplate template;
	
	public void setTemplate(JdbcTemplate template) {
		this.template = template;
	}
	public List<UserBean> getRoles(UserBean uBean) {
		log.info("getRoles method"+new Gson().toJson(uBean));
		log.info("getRoles method :"+"SELECT * FROM mst_users_roles where role_status='active';");
		return template.query("SELECT * FROM mst_users_roles where role_status='active';", new RowMapper<UserBean>() {
			public UserBean mapRow(ResultSet rs, int row) throws SQLException {
				UserBean uBean = new UserBean();
				uBean.setSk_role_id(rs.getString("sk_role_id"));
				uBean.setRole_name(rs.getString("role_name"));
				log.info("getRoles method return"+new Gson().toJson(uBean));
				return uBean;
			}
		});
	}
	
	
	public UserBean saveUser(UserBean uBean) {
		log.info("saveUser method"+new Gson().toJson(uBean));
		String region_id="";
		String dealer_id="";
		  if(uBean.getRegion_id()==null)
		  {
			  region_id="";
		  }
		  else
		  {
			  region_id=uBean.getRegion_id();
		  }
		  
		  if(uBean.getDealer_id()==null)
		  {
			  dealer_id=null;
		  }
		  else
		  {
			  dealer_id=uBean.getDealer_id();
		  }
			String sql="INSERT INTO mst_users(user_type_id,brand_id,region,dealers,first_name,last_name,email,password,mobile,role,user_status)values( '"+ uBean.getUser_type_id() +"','"+ uBean.getBrand_id() +"','"+region_id+"',"+dealer_id+",'"+ uBean.getFirst_name() + "','" + uBean.getLast_name()+ "','"+ uBean.getEmail() + "',MD5('" + uBean.getPassword()+"'),'" + uBean.getMobile() + "','"+uBean.getSk_role_id()+"','active')";
	        log.info("saveUser method :"+sql);
			template.execute(sql);
			uBean.setStatusMessage("Inserted Successfully");
			uBean.setStatusCode("1");
			log.info("saveUser method return"+new Gson().toJson(uBean));
		return uBean;
	}

	public List<UserBean> getUsers(UserBean uBean) {
		log.info("getUsers method"+new Gson().toJson(uBean));
		log.info("getUsers method :"+"select mst_users.*,mst_users_roles.role_name FROM mst_users,mst_users_roles WHERE mst_users.role=mst_users_roles.sk_role_id and mst_users.user_status='active';");
		return template.query("select mst_users.*,mst_users_roles.role_name FROM mst_users,mst_users_roles WHERE mst_users.role=mst_users_roles.sk_role_id and mst_users.user_status='active';", new RowMapper<UserBean>() {
			public UserBean mapRow(ResultSet rs, int row) throws SQLException {
				UserBean uBean = new UserBean();
				uBean.setSk_user_id(rs.getString("sk_user_id"));
				uBean.setFirst_name(rs.getString("first_name"));
				uBean.setLast_name(rs.getString("last_name"));
				uBean.setEmail(rs.getString("email"));
				uBean.setMobile(rs.getString("mobile"));
				uBean.setSk_role_id(rs.getString("role"));
				uBean.setRole_name(rs.getString("role_name"));
				log.info("getUsers method return"+new Gson().toJson(uBean));
				return uBean;
			}
		});
	}
	
	public List<UserBean> getInactiveUsers(UserBean uBean) {
		log.info("getInactiveUsers method"+new Gson().toJson(uBean));
		log.info("getInactiveUsers method :"+"select mst_users.*,mst_users_roles.role_name FROM mst_users,mst_users_roles WHERE mst_users.role=mst_users_roles.sk_role_id and mst_users.user_status='inactive';");
		return template.query("select mst_users.*,mst_users_roles.role_name FROM mst_users,mst_users_roles WHERE mst_users.role=mst_users_roles.sk_role_id and mst_users.user_status='inactive';", new RowMapper<UserBean>() {
			public UserBean mapRow(ResultSet rs, int row) throws SQLException {
				UserBean uBean = new UserBean();
				uBean.setSk_user_id(rs.getString("sk_user_id"));
				uBean.setFirst_name(rs.getString("first_name"));
				uBean.setLast_name(rs.getString("last_name"));
				uBean.setEmail(rs.getString("email"));
				uBean.setMobile(rs.getString("mobile"));
				uBean.setSk_role_id(rs.getString("role"));
				uBean.setRole_name(rs.getString("role_name"));
				log.info("getInactiveUsers method return"+new Gson().toJson(uBean));
				return uBean;
			}
		});
	}
	
	
	public UserBean getUsersById(UserBean uBean,String userId) {
		log.info("getUsersById method"+new Gson().toJson(uBean));
		log.info("getUsersById method :"+"select * from (select * from (select mst_users.*,mst_user_type.user_type,mst_brand.brand_name, mst_users_roles.role_name FROM mst_users,mst_user_type,mst_brand ,mst_users_roles WHERE mst_users.user_type_id=mst_user_type.sk_user_id AND mst_users.brand_id=mst_brand.sk_brand_id AND  mst_users.role=mst_users_roles.sk_role_id and mst_user_type.user_status='active' AND mst_brand.brand_status='active' AND mst_users.sk_user_id='"+userId+"') as res1 left join (SELECT mst_geo_region.sk_region_id,mst_geo_region.region_name  from mst_geo_region WHERE  mst_geo_region.region_status='active') as res2 on res1.region=res2.sk_region_id) as res3 left join (SELECT mst_dealer.sk_dealer_id,  mst_dealer.dealer_name from mst_dealer WHERE  mst_dealer.dealer_status='active') as res4 on res3.dealers=res4.sk_dealer_id");
		return template.queryForObject("select * from (select * from (select mst_users.*,mst_user_type.user_type,mst_brand.brand_name, mst_users_roles.role_name FROM mst_users,mst_user_type,mst_brand ,mst_users_roles WHERE mst_users.user_type_id=mst_user_type.sk_user_id AND mst_users.brand_id=mst_brand.sk_brand_id AND  mst_users.role=mst_users_roles.sk_role_id and mst_user_type.user_status='active' AND mst_brand.brand_status='active' AND mst_users.sk_user_id='"+userId+"') as res1 left join (SELECT mst_geo_region.sk_region_id,mst_geo_region.region_name  from mst_geo_region WHERE  mst_geo_region.region_status='active') as res2 on res1.region=res2.sk_region_id) as res3 left join (SELECT mst_dealer.sk_dealer_id,  mst_dealer.dealer_name from mst_dealer WHERE  mst_dealer.dealer_status='active') as res4 on res3.dealers=res4.sk_dealer_id;", new RowMapper<UserBean>() {
			public UserBean mapRow(ResultSet rs, int row) throws SQLException {
				uBean.setSk_user_id(rs.getString("sk_user_id"));
				uBean.setUser_type_id(rs.getString("user_type_id"));
				uBean.setUser_type(rs.getString("user_type"));
				uBean.setBrand_id(rs.getString("brand_id"));
				uBean.setBrand_name(rs.getString("brand_name"));
				uBean.setRegion_id(rs.getString("region"));
				uBean.setRegion_name(rs.getString("region_name"));
				uBean.setDealer_id(rs.getString("dealers"));
				uBean.setDealer_name(rs.getString("dealer_name"));
				uBean.setSk_role_id(rs.getString("role"));
				uBean.setRole_name(rs.getString("role_name"));
				uBean.setFirst_name(rs.getString("first_name"));
				uBean.setLast_name(rs.getString("last_name"));
				uBean.setEmail(rs.getString("email"));
				uBean.setPassword(rs.getString("password"));
				uBean.setMobile(rs.getString("mobile"));
				uBean.setUser_status(rs.getString("user_status"));
				log.info("getUsersById method return"+new Gson().toJson(uBean));
				return uBean;
			}
		});
	}
	
	
public UserBean updateUser(UserBean uBean) {
	log.info("updateUser method"+new Gson().toJson(uBean));
	String region_id="";
	String dealer_id="";
	  if(uBean.getRegion_id()==null)
	  {
		  region_id="";
	  }
	  else
	  {
		  region_id=uBean.getRegion_id();
	  }
	  
	  if(uBean.getDealer_id()==null)
	  {
		  dealer_id=null;
	  }
	  else
	  {
		  dealer_id=uBean.getDealer_id();
	  }
		String sql1="UPDATE  mst_users SET  brand_id='"+uBean.getBrand_id()+"',user_type_id='"+uBean.getUser_type_id()+"', region='"+region_id+"',dealers="+dealer_id+", first_name='"+ uBean.getFirst_name()+"',last_name='"+uBean.getLast_name()+"',email='"+uBean.getEmail()+"', mobile='"+uBean.getMobile()+"',role='"+uBean.getSk_role_id()+"' where sk_user_id='"+uBean.getSk_user_id()+"'";
			
			log.info("updateUser method :"+sql1);
			template.execute(sql1);
			uBean.setStatusMessage("Updated Successfully");
			uBean.setStatusCode("1");
			log.info("updateUser method return"+new Gson().toJson(uBean));
			return uBean;

	}


public UserBean deleteUser(UserBean uBean,String userId) {
	log.info("deleteUser method "+new Gson().toJson(uBean));
	String sql="UPDATE  mst_users SET user_status='inactive' where sk_user_id='"+userId+"'";
		log.info("deleteUser method :"+sql);
		template.execute(sql);
		uBean.setStatusMessage("Deleted Successfully");
		uBean.setStatusCode("1");
		log.info("deleteUser method return"+new Gson().toJson(uBean));
		return uBean;

}

public UserBean restoreUser(UserBean uBean,String userId) {
	log.info("restoreUser method"+new Gson().toJson(uBean));
	String sql="UPDATE  mst_users SET user_status='active' where sk_user_id='"+userId+"'";
		log.info("restoreUser method :"+sql);
		template.execute(sql);
		uBean.setStatusMessage("Deleted Successfully");
		uBean.setStatusCode("1");
		log.info("restoreUser method return"+new Gson().toJson(uBean));
		return uBean;
}


public UserBean getUserMailExistance(UserBean uBean, String email) {
	// TODO Auto-generated method stub
	log.info("getUserMailExistance method"+new Gson().toJson(uBean));	
	String count = "";
		String status = "";
		String sql = "SELECT COUNT(*) AS exist,ifnull(user_status,'no status') as user_status from mst_users where email='"+email+"' and user_status='active'";
		log.info("getUserMailExistance method :"+sql);
		List<Map<String, Object>> list = template.queryForList(sql);
		for (Map<String, Object> row : list) {
			count = row.get("exist").toString();
			status = row.get("user_status").toString();
		}
	//	int id_status = Integer.parseInt(count);

		
			uBean.setMessage("emailexist");
			uBean.setStatus(status);
			log.info("getUserMailExistance method return"+new Gson().toJson(uBean));	
		return uBean;
	
		
}


public UserBean getUserMailExistanceByuserid(UserBean uBean, String email, String userid) {
	log.info("getUserMailExistanceByuserid method"+new Gson().toJson(uBean));	
	String count = "";
	String status="";
		String sql = "SELECT COUNT(*) AS exist,ifnull(user_status,'no status') as user_status from mst_users where email='"+email+"' and sk_user_id!='"+userid+"' and user_status='active'";
		log.info("getUserMailExistanceByuserid method :"+sql);
		List<Map<String, Object>> list = template.queryForList(sql);
		for (Map<String, Object> row : list) {
			count = row.get("exist").toString();
			status= row.get("user_status").toString();
		}
		//int id_status = Integer.parseInt(count);
        
		log.info("exist"); 
		uBean.setMessage("emailexist");
		uBean.setStatus(status);
		log.info("getUserMailExistanceByuserid method return"+new Gson().toJson(uBean));	
		return uBean;
	
		
}


public List<UserBean> getUserType(UserBean uBean) {
	log.info("getUserType method"+new Gson().toJson(uBean));	
	log.info("getUserType method :"+"SELECT * FROM mst_user_type where user_status='active' ;");
	return template.query("SELECT * FROM mst_user_type where user_status='active';", new RowMapper<UserBean>() {
		public UserBean mapRow(ResultSet rs, int row) throws SQLException {
			UserBean uBean = new UserBean();
			uBean.setUser_type_id(rs.getString("sk_user_id"));
			uBean.setUser_type(rs.getString("user_type"));
			uBean.setUser_status(rs.getString("user_status"));
			log.info("getUserType method return"+new Gson().toJson(uBean));	
			return uBean;
		}
	});
}


public List<UserBean> getRegionList(UserBean uBean) {
	log.info("getRegionList method"+new Gson().toJson(uBean));	
	log.info("getRegionList method :"+"SELECT * FROM mst_geo_region where region_status='active' ;");
	return template.query("SELECT * FROM mst_geo_region where region_status='active';", new RowMapper<UserBean>() {
		public UserBean mapRow(ResultSet rs, int row) throws SQLException {
			UserBean uBean = new UserBean();
			uBean.setRegion_id(rs.getString("sk_region_id"));
			uBean.setRegion_name(rs.getString("region_name"));
			uBean.setCity_list(rs.getString("city_list"));
			uBean.setRegion_status(rs.getString("region_status"));
			log.info("getRegionList method return"+new Gson().toJson(uBean));	
			return uBean;
		}
	});
}


public List<UserBean> GetDealerByRegionId(UserBean uBean, String brand_id, String region_id) {
	log.info("GetDealerByRegionId method"+new Gson().toJson(uBean));	
	log.info("GetDealerByRegionId method :"+"SELECT  region_id, brand_id, dealer_id FROM  mst_dealer_outlet WHERE brand_id='"+brand_id+"' AND `region_id`='"+region_id+"' AND outlet_status='active' GROUP by dealer_id");
	return template.query("SELECT  region_id, brand_id, dealer_id FROM  mst_dealer_outlet WHERE brand_id='"+brand_id+"' AND `region_id`='"+region_id+"' AND outlet_status='active' GROUP by dealer_id;", new RowMapper<UserBean>() {
		public UserBean mapRow(ResultSet rs, int row) throws SQLException {
			UserBean uBean = new UserBean();
			uBean.setRegion_id(rs.getString("region_id"));
			uBean.setBrand_id(rs.getString("brand_id"));
			uBean.setDealer_id(rs.getString("dealer_id"));
			log.info("GetDealerByRegionId method return"+new Gson().toJson(uBean));	
			return uBean;
		}
	});
	
}


public UserBean getDealerName(UserBean uBean, String dealer_id) {
	log.info("getDealerName method"+new Gson().toJson(uBean));	
	log.info("getDealerName method :"+"Select *from mst_dealer where sk_dealer_id='"+dealer_id+"' and dealer_status='active'");
	return template.queryForObject("Select * from mst_dealer where sk_dealer_id='"+dealer_id+"' and dealer_status='active';", new RowMapper<UserBean>() {
		public UserBean mapRow(ResultSet rs, int row) throws SQLException {
			UserBean uBean = new UserBean();
			uBean.setDealer_id(rs.getString("sk_dealer_id"));
			uBean.setDealer_name(rs.getString("dealer_name"));
			log.info("getDealerName method return"+new Gson().toJson(uBean));	
			return uBean;
		}
	});
}


public UserBean saveUserType(UserBean uBean) {
	log.info("saveUserType method"+new Gson().toJson(uBean));	
	String sql="INSERT INTO mst_user_type(user_type)values('"+ uBean.getUser_type()+"')";
	log.info("saveUserType method :"+sql);
	template.execute(sql);
	log.info("saveUserType method return"+new Gson().toJson(uBean));	
	return uBean;
}

public UserBean checkUserTypeExist(UserBean uBean, String user_type) {
	// TODO Auto-generated method stub
	log.info("checkUserTypeExist method"+new Gson().toJson(uBean));		
	String count = "";
		String status = "";
		String sql = "SELECT COUNT(*) AS exist,ifnull(user_status,'no status') as user_status from mst_user_type where user_type='"+user_type+"'and user_status='active'";
		log.info("checkUserTypeExist method :"+sql);
		List<Map<String, Object>> list = template.queryForList(sql);
		for (Map<String, Object> row : list) {
			count = row.get("exist").toString();
			status = row.get("user_status").toString();
		}
	//	int id_status = Integer.parseInt(count);
			uBean.setMessage("usertypeexist");
			uBean.setStatus(status);
			log.info("checkUserTypeExist method return"+new Gson().toJson(uBean));		
		return uBean;
	
		
}

public List<CountryBean> getCountryList(CountryBean coBean) {
	String sql = "Select * from mst_geo_country";
	return template.query(sql, new RowMapper<CountryBean>() {
		@Override
		public CountryBean mapRow(ResultSet rs, int rowNum) throws SQLException {
			CountryBean coBean = new CountryBean();
			coBean.setCountry_name(rs.getString("country_name"));
			coBean.setSk_country_id(rs.getString("sk_country_id"));
			return coBean;
		}
	});
}

public List<StateBean> getState(StateBean sBean, String country_id) {
	log.info("getState method");
	String sql = "Select * from mst_geo_states where country_id='" + country_id + "'";
	log.info(sql);
	return template.query(sql, new RowMapper<StateBean>() {
		@Override
		public StateBean mapRow(ResultSet rs, int rowNum) throws SQLException {
			StateBean sBean = new StateBean();
			sBean.setSk_state_id(rs.getString("sk_state_id"));
			sBean.setState_name(rs.getString("state_name"));
			return sBean;
		}
	});
}

public List<CityBean> getCity(CityBean cBean, String state_id) {
	log.info("getCity method");
	String sql = "Select * from mst_geo_city where state_id='" + state_id + "'";
	log.info(sql);
	return template.query(sql, new RowMapper<CityBean>() {
		@Override
		public CityBean mapRow(ResultSet rs, int rowNum) throws SQLException {
			CityBean cBean = new CityBean();
			cBean.setSk_state_id(rs.getString("state_id"));
			String city_id = cBean.setCity_id(rs.getString("sk_city_id"));
			cBean.setCity_name(rs.getString("city_name"));
			log.info("cBean=" + cBean);
			return cBean;
		}
	});
}

public void addtest(TestBean tBean) {
	log.info("addtest method");
	log.info("INSERT INTO testcrud_table(name,email,country,state,city,address) VALUES ('" + tBean.getName() + "','"
			+ tBean.getEmail() + "','" + tBean.getSk_country_id() + "','" + tBean.getSk_state_id() + "','"
			+ tBean.getSk_city_id() + "','" + tBean.getAddress() + "')");
	template.execute("INSERT INTO testcrud_table(name,email,country,state,city,address) VALUES ('" + tBean.getName()
			+ "','" + tBean.getEmail() + "','" + tBean.getSk_country_id() + "','" + tBean.getSk_state_id() + "','"
			+ tBean.getSk_city_id() + "','" + tBean.getAddress() + "')");
}

public List<TestBean> gettList(TestBean tBean) {
	log.info("gettList method");
	String sql = "Select * from testcrud_table";
	return template.query(sql, new RowMapper<TestBean>() {

		@Override
		public TestBean mapRow(ResultSet rs, int rowNum) throws SQLException {
			TestBean tBean = new TestBean();
			tBean.setId(rs.getString("id"));
			log.info("id===================" + rs.getString("id"));
			tBean.setName(rs.getString("name"));
			tBean.setEmail(rs.getString("email"));
			tBean.setAddress(rs.getString("address"));
			return tBean;
		}
	});
}

public TestBean getCrudDetails(TestBean tBean, String id) {
	log.info("getCrudDetails");
	String sql = "Select * from testcrud_table where 	id ='" + id + "'";

	return template.queryForObject(sql, new RowMapper<TestBean>() {
		@Override
		public TestBean mapRow(ResultSet rs, int rowNum) throws SQLException {
			// TestBean tBean=new TestBean();
			tBean.setId(rs.getString("id"));
			System.out.println("-------------" + rs.getString("id"));
			tBean.setName(rs.getString("name"));
			tBean.setEmail(rs.getString("email"));
			tBean.setAddress(rs.getString("address"));
			return tBean;
		}
	});

}

public void updatecrudbyid(TestBean tBean, String id) {
	log.info("updatecrudbyid method");
	log.info("UPDATE testcrud_table SET name='" + tBean.getName() + "',email='" + tBean.getEmail() + "', address='"
			+ tBean.getAddress() + "' WHERE id='" + id + "'");
	template.execute("UPDATE testcrud_table SET name='" + tBean.getName() + "',email='" + tBean.getEmail()
			+ "',address='" + tBean.getAddress() + "' WHERE id='" + id + "'");
}

public void deletecrudbyid(TestBean tBean, String id) {
	log.info("deletecrudbyid method");
	log.info("DELETE FROM testcrud_table WHERE id='"+id+"'");
	template.execute("DELETE FROM testcrud_table WHERE id='"+id+"'");		
}
}
