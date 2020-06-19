package com.mystery.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;
import java.util.ResourceBundle;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;

import com.mystery.api.AESCrypt;
import com.mystery.api.Encryption;
import com.mystery.api.SendEmailUsingGMailSMTP;
import com.mystery.beans.UserBean;

public class AuthenticDao {

	@Autowired
    JdbcTemplate template;
	
	public void setTemplate(JdbcTemplate template) {
		this.template = template;
	}

	ResourceBundle resource = ResourceBundle.getBundle("resources/web");
	final String dashboardURL = resource.getString("dashboardURL");
	String emailfrom=resource.getString("emailfrom");
	String emailusername=resource.getString("emailusername");
	String emailpassword=resource.getString("emailpassword");
	
	
	
	
	
	public UserBean signin(final UserBean uBean) {
		template.execute("SET GLOBAL sql_mode=(SELECT REPLACE(@@sql_mode,'ONLY_FULL_GROUP_BY',''))");
		System.out.println("select count(email)as exist,ifnull(user_status,'no status') as user_status from mst_users where email='" + uBean.getEmail() + "' and password=MD5('"
				+ uBean.getPassword() + "') and user_status='active'");
		String count = "";
		String status="";
		String sql = "select count(email)as exist,ifnull(user_status,'no status') as user_status  from mst_users where email='" + uBean.getEmail()
				+ "' and password=MD5('" + uBean.getPassword() + "') and user_status='active'";
		List<Map<String, Object>> list = template.queryForList(sql);
		for (Map<String, Object> row : list) {
			count = row.get("exist").toString();
			status = row.get("user_status").toString();
		}
		int id_status = Integer.parseInt(count);
		if (id_status > 0 && status.equals("active") ) {
			System.out.println("exist");

			String sql1 = "select * from (SELECT * FROM mst_users,mst_users_roles WHERE email=? AND password=MD5(?) AND mst_users.user_status='active' AND mst_users.role=mst_users_roles.sk_role_id)as res1 left join mst_user_type on res1.role_for=mst_user_type.sk_user_id";
			return template.queryForObject(sql1, new Object[] { uBean.getEmail(), uBean.getPassword() },
					new RowMapper<UserBean>() {
						public UserBean mapRow(ResultSet rs, int row) throws SQLException {
							uBean.setSk_user_id(rs.getString("sk_user_id"));
							uBean.setFirst_name(rs.getString("first_name"));
							uBean.setLast_name(rs.getString("last_name"));
							uBean.setEmail(rs.getString("email"));
							uBean.setPassword(rs.getString("password"));
							uBean.setMobile(rs.getString("mobile"));
							uBean.setRegion(rs.getString("region"));
							uBean.setOutlets(rs.getString("outlets"));
							uBean.setDealers(rs.getString("dealers"));
							uBean.setSk_role_id(rs.getString("role"));
							uBean.setUser_type(rs.getString("user_type"));
							uBean.setRole_for(rs.getString("role_for"));
							uBean.setDealers(rs.getString("dealers"));
							uBean.setRegion(rs.getString("region"));
							uBean.setRole_name(rs.getString("role_name"));
							uBean.setUser_status(rs.getString("user_status"));
							uBean.setStatusMessage("Welcome To DashBoard");
							uBean.setStatusCode("1");
							return uBean;
						}
					});

		} 
		else if (id_status > 0 && status.equals("inactive")) {
			uBean.setStatusMessage("User is in inactive status");
			uBean.setStatusCode("0");
			return uBean;
		}
		
		else {
			System.out.println("error");

			uBean.setStatusMessage("Invalid Credintials");
			uBean.setStatusCode("0");
			return uBean;
		}

	}
	
	public UserBean forgotPassword(final UserBean uBean) {

	    String count = "";
	    String sql = "SELECT COUNT(*) AS exist from mst_users where email='"+uBean.getEmail()+"' and user_status='active'";
	    System.out.println("sql count"+sql);
	    List<Map<String, Object>> list = template.queryForList(sql);
	    for (Map<String, Object> row : list) {
	      count = row.get("exist").toString();
	    }
	    int id_status = Integer.parseInt(count);
	    if (id_status > 0) {
	      System.out.println("exist");
	      
	      String sql1 = "SELECT *FROM mst_users where email='"+uBean.getEmail()+"' and user_status='active'";
	      System.out.println("sql query  for email"+sql1);
	      return template.queryForObject("SELECT *FROM mst_users where email='"+uBean.getEmail()+"' and user_status='active';",new RowMapper<UserBean>() {
	            public UserBean mapRow(ResultSet rs, int row) throws SQLException {
	              uBean.setSk_user_id(rs.getString("sk_user_id"));
	              uBean.setEmail(rs.getString("email"));
	              String email=rs.getString("email");
	              System.out.println("email in adao"+email);
	              String userId=rs.getString("sk_user_id");
	              System.out.println("userId in adao"+userId);
	            //  String encrypted_userId="";
							/*
							 * try { encrypted_userId = AESCrypt.encrypt(userId);
							 * System.out.println("encrypted"+encrypted_userId); } catch (Exception e) { //
							 * TODO Auto-generated catch block e.printStackTrace(); }
							 */
	              String encrypted_userId = Encryption.encrypt(userId);
	              System.out.println("encrypted userId"+encrypted_userId);
	                            String link="<td><a href="+dashboardURL+"resetPassword/"+encrypted_userId+">click on the link to reset password</a></td>";
	                            
	                            SendEmailUsingGMailSMTP mail= new SendEmailUsingGMailSMTP();
	                           
	                            System.out.println("email from"+emailfrom);
	                            System.out.println("email username"+emailusername);
	                            System.out.println("email password"+emailpassword);
	                            System.out.println("email link"+link);
	                            mail.forgotPassword(email,link,emailfrom,emailusername,emailpassword);
								
	              uBean.setStatusMessage("Reset Password link Sent to email");
	              uBean.setStatusCode("1");
	              return uBean;
	            }
	          });

	    } 
	      else {
	    
	      uBean.setStatusMessage("Email Does Not Exist");
	      uBean.setStatusCode("0");
	      return uBean;
	    }
		}
		public UserBean resetPassword(UserBean uBean) {

			String sql="UPDATE mst_users SET password=MD5('"+ uBean.getPassword() + "') where sk_user_id='"+ uBean.getSk_user_id() + "'";
			template.execute(sql);
			//uBean.setStatusMessage(" Password Updated Successfully");
			//uBean.setStatusCode("1");
			return uBean;

		}
		
	
}
