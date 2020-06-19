package com.mystery.dao;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import com.mystery.beans.CityBean;
import com.mystery.beans.DatabaseManagementBean;
import com.mystery.beans.DealerBean;
import com.mystery.beans.QuestionnaireBean;
import com.mystery.beans.RegionBean;
import com.mystery.beans.StateBean;

public class DatabaseManagementDao {

	@Autowired
    JdbcTemplate template;
	
	public void setTemplate(JdbcTemplate template) {
		this.template = template;
	}
	
	DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
	Date date = new Date();

	public List<StateBean> getStateList(StateBean sBean) {
		System.out.println("SELECT * FROM mst_geo_states where state_status='active';");
		return template.query("SELECT * FROM `mst_geo_states`where state_status='active' ;",
				new RowMapper<StateBean>() {
					public StateBean mapRow(ResultSet rs, int row) throws SQLException {
						StateBean sBean = new StateBean();
						sBean.setSk_state_id(rs.getString("sk_state_id"));
						sBean.setState_name(rs.getString("state_name"));
						return sBean;
					}
				});
	}

	public List<StateBean> getInactiveStateList(StateBean sBean) {
		System.out.println("SELECT * FROM mst_geo_states where state_status='inactive';");
		return template.query("SELECT * FROM `mst_geo_states`where state_status='inactive' ;",
				new RowMapper<StateBean>() {
					public StateBean mapRow(ResultSet rs, int row) throws SQLException {
						StateBean sBean = new StateBean();
						sBean.setSk_state_id(rs.getString("sk_state_id"));
						sBean.setState_name(rs.getString("state_name"));
						return sBean;
					}
				});
	}

	public void addState(StateBean sBean) {
		System.out.println("INSERT INTO mst_geo_states(state_name,state_status) VALUES ('" + sBean.getState_name()
				+ "','active');");
		template.execute("INSERT INTO mst_geo_states(state_name,state_status) VALUES ('" + sBean.getState_name()
				+ "','active');");

	}

	public StateBean getStateDetailsById(StateBean sBean, String sid) {
		System.out.println("SELECT * FROM mst_geo_states where sk_state_id='" + sid + "' and  state_status='active';");
		return template.queryForObject(
				"SELECT * FROM `mst_geo_states`where sk_state_id='" + sid + "' and  state_status='active' ;",
				new RowMapper<StateBean>() {
					public StateBean mapRow(ResultSet rs, int row) throws SQLException {
						sBean.setSk_state_id(rs.getString("sk_state_id"));
						sBean.setState_name(rs.getString("state_name"));
						return sBean;
					}
				});
	}

	public void updatStateById(StateBean sBean, String sid) {
		System.out.println(
				"UPDATE mst_geo_states SET state_name='" + sBean.getState_name() + "' WHERE sk_state_id='" + sid + "'");
		template.execute(
				"UPDATE mst_geo_states SET state_name='" + sBean.getState_name() + "' WHERE sk_state_id='" + sid + "'");
	}

	public void deleteStateById(StateBean sBean, String sid) {
		System.out.println("UPDATE mst_geo_states SET state_status='inactive' WHERE sk_state_id='" + sid + "'");
		template.execute("UPDATE mst_geo_states SET state_status='inactive' WHERE sk_state_id='" + sid + "'");
	}

	public void restoreStateStateById(StateBean sBean, String sid) {
		System.out.println("UPDATE mst_geo_states SET state_status='active' WHERE sk_state_id='" + sid + "'");
		template.execute("UPDATE mst_geo_states SET state_status='active' WHERE sk_state_id='" + sid + "'");
	}

	public StateBean getstateexist(StateBean sBean, String name) {
		String count = "";
		String status = "";
		String sql = "SELECT count(*)as exist,ifnull(state_status,'no status') as state_status FROM mst_geo_states WHERE state_name = '"
				+ name + "' ";
		System.out.println(sql);
		List<Map<String, Object>> list = template.queryForList(sql);
		for (Map<String, Object> row : list) {
			count = row.get("exist").toString();
			status = row.get("state_status").toString();
		}
		int id_status = Integer.parseInt(count);

		if (id_status > 0) {
			System.out.println("exist");
			sBean.setMessage("stateexist");
			sBean.setStatus(status);
		} else {
			sBean.setStatus(status);
			sBean.setMessage("statenotexist");
		}
		return sBean;
	}

	public StateBean getstateexistbyid(StateBean sBean, String name, String stateid) {
		String count = "";
		String status = "";
		String sql = "SELECT count(*)as exist,ifnull(state_status,'no status') as state_status FROM mst_geo_states WHERE state_name = '"
				+ name + "' and sk_state_id!='" + stateid + "'";
		System.out.println(sql);
		List<Map<String, Object>> list = template.queryForList(sql);
		for (Map<String, Object> row : list) {
			count = row.get("exist").toString();
			status = row.get("state_status").toString();
		}
		int id_status = Integer.parseInt(count);

		if (id_status > 0) {
			System.out.println("exist");

			sBean.setMessage("stateexist");
			sBean.setStatus(status);
		} else {
			sBean.setStatus(status);
			sBean.setMessage("statenotexist");
		}
		return sBean;
	}

	public List<CityBean> getCityList(CityBean cBean) {
		System.out.println(
				"SELECT * FROM `mst_geo_city`,mst_geo_states WHERE mst_geo_states.sk_state_id=mst_geo_city.state_id and `city_status`='active' and state_status='active';");
		return template.query(
				"SELECT * FROM `mst_geo_city`,mst_geo_states WHERE mst_geo_states.sk_state_id=mst_geo_city.state_id and `city_status`='active' and state_status='active' ;",
				new RowMapper<CityBean>() {
					public CityBean mapRow(ResultSet rs, int row) throws SQLException {
						CityBean cBean = new CityBean();
						cBean.setSk_state_id(rs.getString("state_id"));
						cBean.setState_name(rs.getString("state_name"));
						cBean.setCity_name(rs.getString("city_name"));
						cBean.setSk_city_id(rs.getString("sk_city_id"));
						cBean.setTier(rs.getString("tier"));
						cBean.setMetro(rs.getString("metro"));
						return cBean;
					}
				});
	}

	public List<CityBean> getInactiveCityList(CityBean cBean) {
		System.out.println(
				"SELECT * FROM `mst_geo_city`,mst_geo_states WHERE mst_geo_states.sk_state_id=mst_geo_city.state_id and `city_status`='inactive' ;");
		return template.query(
				"SELECT * FROM `mst_geo_city`,mst_geo_states WHERE mst_geo_states.sk_state_id=mst_geo_city.state_id and `city_status`='inactive'  ;",
				new RowMapper<CityBean>() {
					public CityBean mapRow(ResultSet rs, int row) throws SQLException {
						CityBean cBean = new CityBean();
						cBean.setSk_state_id(rs.getString("state_id"));
						cBean.setState_name(rs.getString("state_name"));
						cBean.setCity_name(rs.getString("city_name"));
						cBean.setSk_city_id(rs.getString("sk_city_id"));
						cBean.setTier(rs.getString("tier"));
						cBean.setMetro(rs.getString("metro"));
						return cBean;
					}
				});
	}

	public void addCity(CityBean cBean) {
		System.out.println("INSERT INTO mst_geo_city(city_name,state_id,tier,metro,city_status) VALUES ('" + cBean.getCity_name()
				+ "','" + cBean.getSk_state_id() + "','" + cBean.getTier() + "','" + cBean.getMetro() + "','active');");
		template.execute("INSERT INTO mst_geo_city(city_name,state_id,tier,metro,city_status) VALUES ('" + cBean.getCity_name()
				+ "','" + cBean.getSk_state_id() + "','" + cBean.getTier() + "','" + cBean.getMetro() + "','active');");

	}

	public CityBean getCityDetailsById(CityBean cBean, String cid) {
		System.out.println(
				"SELECT * FROM `mst_geo_city`,mst_geo_states WHERE mst_geo_states.sk_state_id=mst_geo_city.state_id and `city_status`='active'  and sk_city_id='"
						+ cid + "';");
		return template.queryForObject(
				"SELECT * FROM `mst_geo_city`,mst_geo_states WHERE mst_geo_states.sk_state_id=mst_geo_city.state_id and `city_status`='active'  and sk_city_id='"
						+ cid + "' ;",
				new RowMapper<CityBean>() {
					public CityBean mapRow(ResultSet rs, int row) throws SQLException {
						cBean.setSk_state_id(rs.getString("sk_state_id"));
						cBean.setState_name(rs.getString("state_name"));
						cBean.setCity_name(rs.getString("city_name"));
						cBean.setSk_city_id(rs.getString("sk_city_id"));
						cBean.setTier(rs.getString("tier"));
						cBean.setMetro(rs.getString("metro"));
						return cBean;
					}
				});
	}

	public void updatCityById(CityBean cBean, String cid) {
		System.out.println("UPDATE `mst_geo_city` SET `city_name`='" + cBean.getCity_name() + "',`state_id`='"
				+ cBean.getSk_state_id() + "',tier='" + cBean.getTier() + "',metro='" + cBean.getMetro() + "',`city_status`='active' WHERE `sk_city_id`='" + cid + "'");
		template.execute("UPDATE `mst_geo_city` SET `city_name`='" + cBean.getCity_name() + "',`state_id`='"
				+ cBean.getSk_state_id() + "',tier='" + cBean.getTier() + "',metro='" + cBean.getMetro() + "',`city_status`='active' WHERE `sk_city_id`='" + cid + "'");
	}

	public void deleteCityById(CityBean cBean, String cid) {
		System.out.println("UPDATE mst_geo_city SET city_status='inactive' WHERE sk_city_id='" + cid + "'");
		template.execute("UPDATE mst_geo_city SET city_status='inactive' WHERE sk_city_id='" + cid + "'");

	}

	public void restoreCityById(CityBean cBean, String cid) {
		System.out.println("UPDATE mst_geo_city SET city_status='active' WHERE sk_city_id='" + cid + "'");
		template.execute("UPDATE mst_geo_city SET city_status='active' WHERE sk_city_id='" + cid + "'");

	}

	public CityBean getCityExistance(CityBean cBean, String state_id, String name) {
		String count = "";
		String status = "";
		String sql = "SELECT count(*)as exist,ifnull(city_status,'no status') as city_status FROM mst_geo_city WHERE state_id = '"
				+ state_id + "' and city_name='" + name + "' ";
		System.out.println(sql);
		List<Map<String, Object>> list = template.queryForList(sql);
		for (Map<String, Object> row : list) {
			count = row.get("exist").toString();
			status = row.get("city_status").toString();
		}
		int id_status = Integer.parseInt(count);

		if (id_status > 0) {
			System.out.println("exist");
			cBean.setMessage("cityexist");
			cBean.setStatus(status);
		} else {
			cBean.setStatus(status);
			cBean.setMessage("citynotexist");
		}
		return cBean;
	}

	public StateBean getcityexistbyid(CityBean cBean, String name, String cityid, String stateid) {
		String count = "";
		String status = "";
		String sql = "SELECT count(*)as exist,ifnull(city_status,'no status') as city_status FROM mst_geo_city WHERE city_name = '"
				+ name + "' and state_id='" + stateid + "' and sk_city_id!='" + cityid + "'";
		System.out.println(sql);
		List<Map<String, Object>> list = template.queryForList(sql);
		for (Map<String, Object> row : list) {
			count = row.get("exist").toString();
			status = row.get("city_status").toString();
		}
		int id_status = Integer.parseInt(count);

		if (id_status > 0) {
			System.out.println("exist");

			cBean.setMessage("cityexist");
			cBean.setStatus(status);
		} else {
			cBean.setStatus(status);
			cBean.setMessage("citynotexist");
		}
		return cBean;
	}

	/**
	 * Region services
	 * 
	 */
	/********************************* dao 
	 * @param roleId 
	 * @param region ******************************/
	public List<RegionBean> getRegionList1(RegionBean rBean) {
		System.out.println("SELECT * FROM `mst_geo_region` WHERE `region_status`='active'");
		return template.query(" SELECT * FROM `mst_geo_region` WHERE `region_status`='active';",
				new RowMapper<RegionBean>() {
					public RegionBean mapRow(ResultSet rs, int row) throws SQLException {
						RegionBean rBean = new RegionBean();
						rBean.setRegion_name(rs.getString("region_name"));
						rBean.setSk_region_id(rs.getString("sk_region_id"));
						System.out.println(
								"SELECT GROUP_CONCAT(mst_geo_city.sk_city_id) as sk_city_id,GROUP_CONCAT(mst_geo_city.city_name) as city_name FROM `mst_geo_city` WHERE `sk_city_id` in("
										+ rs.getString("city_list") + ");");
						template.query(
								"SELECT GROUP_CONCAT(mst_geo_city.sk_city_id) as sk_city_id,GROUP_CONCAT(mst_geo_city.city_name) as city_name FROM `mst_geo_city` WHERE `sk_city_id` in("
										+ rs.getString("city_list") + ");",
								new RowMapper<RegionBean>() {
									public RegionBean mapRow(ResultSet rs, int row) throws SQLException {
										rBean.setSk_city_id(rs.getString("sk_city_id"));
										rBean.setCity_name(rs.getString("city_name"));
										return rBean;
									}
								});

						return rBean;
					}
				});
	}

	public List<RegionBean> getRegionList(RegionBean rBean, String region, String roleId) {
		if(roleId.contentEquals("2") || roleId.contentEquals("4") || roleId.contentEquals("5")) {
			region="all";
			
		}else if(roleId.contentEquals("6") ){
			region="all";
		}else {
			region=region;
		}
		System.out.println("SELECT * FROM `mst_geo_region` WHERE `region_status`='active' and (sk_region_id='"+region+"' or '"+region+"'='all')");
		return template.query(" SELECT * FROM `mst_geo_region` WHERE `region_status`='active' and (sk_region_id='"+region+"' or '"+region+"'='all');",
				new RowMapper<RegionBean>() {
					public RegionBean mapRow(ResultSet rs, int row) throws SQLException {
						RegionBean rBean = new RegionBean();
						rBean.setRegion_name(rs.getString("region_name"));
						rBean.setSk_region_id(rs.getString("sk_region_id"));
						System.out.println(
								"SELECT GROUP_CONCAT(mst_geo_city.sk_city_id) as sk_city_id,GROUP_CONCAT(mst_geo_city.city_name) as city_name FROM `mst_geo_city` WHERE `sk_city_id` in("
										+ rs.getString("city_list") + ");");
						template.query(
								"SELECT GROUP_CONCAT(mst_geo_city.sk_city_id) as sk_city_id,GROUP_CONCAT(mst_geo_city.city_name) as city_name FROM `mst_geo_city` WHERE `sk_city_id` in("
										+ rs.getString("city_list") + ");",
								new RowMapper<RegionBean>() {
									public RegionBean mapRow(ResultSet rs, int row) throws SQLException {
										rBean.setSk_city_id(rs.getString("sk_city_id"));
										rBean.setCity_name(rs.getString("city_name"));
										return rBean;
									}
								});

						return rBean;
					}
				});
	}

	public List<RegionBean> getInactiveRegionList(RegionBean rBean) {
		System.out.println("SELECT * FROM `mst_geo_region` WHERE `region_status`='inactive';");
		return template.query(" SELECT * FROM `mst_geo_region` WHERE `region_status`='inactive';",
				new RowMapper<RegionBean>() {
					public RegionBean mapRow(ResultSet rs, int row) throws SQLException {
						RegionBean rBean = new RegionBean();
						rBean.setRegion_name(rs.getString("region_name"));
						rBean.setSk_region_id(rs.getString("sk_region_id"));
						System.out.println(
								"SELECT GROUP_CONCAT(mst_geo_city.sk_city_id) as sk_city_id,GROUP_CONCAT(mst_geo_city.city_name) as city_name FROM `mst_geo_city` WHERE `sk_city_id` in("
										+ rs.getString("city_list") + ");");
						template.query(
								"SELECT GROUP_CONCAT(mst_geo_city.sk_city_id) as sk_city_id,GROUP_CONCAT(mst_geo_city.city_name) as city_name FROM `mst_geo_city` WHERE `sk_city_id` in("
										+ rs.getString("city_list") + ");",
								new RowMapper<RegionBean>() {
									public RegionBean mapRow(ResultSet rs, int row) throws SQLException {
										rBean.setSk_city_id(rs.getString("sk_city_id"));
										rBean.setCity_name(rs.getString("city_name"));
										return rBean;
									}
								});

						return rBean;
					}
				});
	}

	public void addRegion(RegionBean rBean) {
		System.out.println("INSERT INTO mst_geo_region(region_name,city_list,region_status) VALUES ('"
				+ rBean.getRegion_name() + "','" + rBean.getSk_city_id() + "','active');");
		template.execute("INSERT INTO mst_geo_region(region_name,city_list,region_status) VALUES ('"
				+ rBean.getRegion_name() + "','" + rBean.getSk_city_id() + "','active');");

	}

	public RegionBean getRegionDetailsById(RegionBean rBean, String rid) {
		System.out.println(
				"SELECT * FROM `mst_geo_region` WHERE `region_status`='active' and sk_region_id='" + rid + "';");
		return template.queryForObject(
				"SELECT * FROM `mst_geo_region` WHERE `region_status`='active' and sk_region_id='" + rid + "';",
				new RowMapper<RegionBean>() {
					public RegionBean mapRow(ResultSet rs, int row) throws SQLException {
						rBean.setRegion_name(rs.getString("region_name"));
						rBean.setSk_region_id(rs.getString("sk_region_id"));
						System.out.println(
								"SELECT GROUP_CONCAT(mst_geo_city.sk_city_id) as sk_city_id,GROUP_CONCAT(mst_geo_city.city_name) as city_name FROM `mst_geo_city` WHERE `sk_city_id` in("
										+ rs.getString("city_list") + ");");
						template.queryForObject(
								"SELECT GROUP_CONCAT(mst_geo_city.sk_city_id) as sk_city_id,GROUP_CONCAT(mst_geo_city.city_name) as city_name FROM `mst_geo_city` WHERE `sk_city_id` in("
										+ rs.getString("city_list") + ");",
								new RowMapper<RegionBean>() {
									public RegionBean mapRow(ResultSet rs, int row) throws SQLException {
										rBean.setSk_city_id(rs.getString("sk_city_id"));
										rBean.setCity_name(rs.getString("city_name"));
										return rBean;
									}
								});

						return rBean;
					}
				});
	}

	public void updatRegionById(RegionBean rBean, String rid) {
		System.out.println("update mst_geo_region set city_list='" + rBean.getSk_city_id()  + "',region_name='"
				+ rBean.getRegion_name() + "' where sk_region_id='" + rid + "'");
		template.execute("update mst_geo_region set city_list='" + rBean.getSk_city_id()  + "',region_name='"
				+ rBean.getRegion_name() + "' where sk_region_id='" + rid + "';");
	}

	public void deleteRegionById(RegionBean rBean, String rid) {
		System.out.println("update mst_geo_region set region_status='inactive' where sk_region_id='" + rid + "'");
		template.execute("update mst_geo_region set region_status='inactive' where sk_region_id='" + rid + "';");

	}

	public void restoreRegionById(RegionBean rBean, String rid) {
		System.out.println("update mst_geo_region set region_status='active' where sk_region_id='" + rid + "'");
		template.execute("update mst_geo_region set region_status='active' where sk_region_id='" + rid + "';");

	}

	public RegionBean getResgionExistance(RegionBean rBean, String name) {
		String count = "";
		String status = "";
		String sql = "SELECT count(*)as exist,ifnull(region_status,'no status') as region_status FROM mst_geo_region WHERE region_name = '"
				+ name + "' ";
		System.out.println(sql);
		List<Map<String, Object>> list = template.queryForList(sql);
		for (Map<String, Object> row : list) {
			count = row.get("exist").toString();
			status = row.get("region_status").toString();
		}
		int id_status = Integer.parseInt(count);

		if (id_status > 0) {
			System.out.println("exist");
			rBean.setMessage("regionexist");
			rBean.setStatus(status);
		} else {
			rBean.setStatus(status);
			rBean.setMessage("regionnotexist");
		}
		return rBean;
	}

	public RegionBean getRegionExistanceByid(RegionBean rBean, String name, String regionid) {
		String count = "";
		String status = "";
		String sql = "SELECT count(*)as exist,ifnull(region_status,'no status') as region_status FROM mst_geo_region WHERE region_name = '"
				+ name + "' and sk_region_id!='" + regionid + "'";
		System.out.println(sql);
		List<Map<String, Object>> list = template.queryForList(sql);
		for (Map<String, Object> row : list) {
			count = row.get("exist").toString();
			status = row.get("region_status").toString();
		}
		int id_status = Integer.parseInt(count);

		if (id_status > 0) {
			System.out.println("exist");

			rBean.setMessage("regionexist");
			rBean.setStatus(status);
		} else {
			rBean.setStatus(status);
			rBean.setMessage("regionnotexist");
		}
		return rBean;
	}

	/* Brand start */
	public List<DatabaseManagementBean> getBrandList(DatabaseManagementBean dbBean) {
		System.out.println("SELECT * FROM mst_brand where brand_status='active';");
		return template.query("SELECT * FROM `mst_brand`where brand_status='active' ;",
				new RowMapper<DatabaseManagementBean>() {
					public DatabaseManagementBean mapRow(ResultSet rs, int row) throws SQLException {
						DatabaseManagementBean dbBean = new DatabaseManagementBean();
						dbBean.setSk_brand_id(rs.getString("sk_brand_id"));
						dbBean.setBrand_name(rs.getString("brand_name"));
						dbBean.setBrand_description(rs.getString("brand_description"));
						return dbBean;
					}
				});
	}

	public List<DatabaseManagementBean> getInactiveBrandList(DatabaseManagementBean dbBean) {
		System.out.println("SELECT * FROM mst_brand where brand_status='inactive';");
		return template.query("SELECT * FROM `mst_brand` where brand_status='inactive' ;",
				new RowMapper<DatabaseManagementBean>() {
					public DatabaseManagementBean mapRow(ResultSet rs, int row) throws SQLException {
						DatabaseManagementBean dbBean = new DatabaseManagementBean();
						dbBean.setSk_brand_id(rs.getString("sk_brand_id"));
						dbBean.setBrand_name(rs.getString("brand_name"));
						dbBean.setBrand_description(rs.getString("brand_description"));
						return dbBean;
					}
				});
	}

	public void addBrand(DatabaseManagementBean dbBean, String user_id) {
		System.out.println("INSERT INTO mst_brand(brand_name,brand_description,created_by,brand_status) VALUES ('"
				+ dbBean.getBrand_name() + "','" + dbBean.getBrand_description().replace("'", "\\'") + "','" + user_id
				+ "','active');");
		template.execute("INSERT INTO mst_brand(brand_name,brand_description,created_by,brand_status) VALUES ('"
				+ dbBean.getBrand_name() + "','" + dbBean.getBrand_description().replace("'", "\\'") + "','" + user_id
				+ "','active');");

	}

	public void updatBrandById(DatabaseManagementBean dbBean, String sid) {
		System.out.println("UPDATE mst_brand SET brand_name='" + dbBean.getBrand_name() + "',brand_description='"
				+ dbBean.getBrand_description() + "' WHERE sk_brand_id='" + sid + "'");
		template.execute("UPDATE mst_brand SET brand_name='" + dbBean.getBrand_name() + "',brand_description='"
				+ dbBean.getBrand_description() + "' WHERE sk_brand_id='" + sid + "'");
	}

	public void deleteBrandById(DatabaseManagementBean dbBean, String sid) {
		System.out.println("UPDATE mst_brand SET brand_status='inactive' WHERE sk_brand_id='" + sid + "'");
		template.execute("UPDATE mst_brand SET brand_status='inactive' WHERE sk_brand_id='" + sid + "'");
	}

	public void restoreBrandBrandById(DatabaseManagementBean dbBean, String sid) {
		System.out.println("UPDATE mst_brand SET brand_status='active' WHERE sk_brand_id='" + sid + "'");
		template.execute("UPDATE mst_brand SET brand_status='active' WHERE sk_brand_id='" + sid + "'");
	}

	public DatabaseManagementBean getBrandExistance(DatabaseManagementBean dbBean, String name) {
		String count = "";
		String status = "";
		String sql = "SELECT count(*)as exist,ifnull(brand_status,'no status') as brand_status FROM mst_brand WHERE brand_name = '"
				+ name + "' ";
		System.out.println(sql);
		List<Map<String, Object>> list = template.queryForList(sql);
		for (Map<String, Object> row : list) {
			count = row.get("exist").toString();
			status = row.get("brand_status").toString();
		}
		int id_status = Integer.parseInt(count);

		if (id_status > 0) {
			System.out.println("exist");

			dbBean.setMessage("brandexist");
			dbBean.setStatus(status);
		} else {
			dbBean.setStatus(status);
			dbBean.setMessage("brandnotexist");
		}
		return dbBean;
	}

	public DatabaseManagementBean getBrandDetailsById(DatabaseManagementBean dbBean, String sid) {
		System.out.println("SELECT * FROM mst_brand where sk_brand_id='" + sid + "' and  brand_status='active';");
		return template.queryForObject(
				"SELECT * FROM `mst_brand`where sk_brand_id='" + sid + "' and  brand_status='active' ;",
				new RowMapper<DatabaseManagementBean>() {
					public DatabaseManagementBean mapRow(ResultSet rs, int row) throws SQLException {
						dbBean.setSk_brand_id(rs.getString("sk_brand_id"));
						dbBean.setBrand_name(rs.getString("brand_name"));
						dbBean.setBrand_description(rs.getString("brand_description"));
						return dbBean;
					}
				});
	}

	/* Brand end */
	/* Model Start */
	public List<DatabaseManagementBean> getModelList(DatabaseManagementBean dbBean) {
		System.out.println(
				"SELECT mst_brand.brand_name, mst_brand_model.* FROM mst_brand,mst_brand_model where mst_brand_model.brand_id=mst_brand.sk_brand_id AND mst_brand.brand_status='active' AND model_status='active';");
		return template.query(
				"SELECT mst_brand.brand_name, mst_brand_model.* FROM mst_brand,mst_brand_model where mst_brand_model.brand_id=mst_brand.sk_brand_id AND mst_brand.brand_status='active' AND model_status='active';",
				new RowMapper<DatabaseManagementBean>() {
					public DatabaseManagementBean mapRow(ResultSet rs, int row) throws SQLException {
						DatabaseManagementBean dbBean = new DatabaseManagementBean();
						dbBean.setSk_model_id(rs.getString("sk_model_id"));
						dbBean.setModel_name(rs.getString("model_name"));
						dbBean.setBrand_id(rs.getInt("brand_id"));
						dbBean.setBrand_name(rs.getString("brand_name"));
						return dbBean;
					}
				});
	}

		 
	public List<DatabaseManagementBean> getInactiveModelList(DatabaseManagementBean dbBean) {
		System.out.println(
				"SELECT mst_brand.*, mst_brand_model.* FROM mst_brand,mst_brand_model where mst_brand_model.brand_id=mst_brand.sk_brand_id AND model_status='inactive';");
		return template.query(
				"SELECT mst_brand.*, mst_brand_model.* FROM mst_brand,mst_brand_model where mst_brand_model.brand_id=mst_brand.sk_brand_id AND model_status='inactive';",
				new RowMapper<DatabaseManagementBean>() {
					public DatabaseManagementBean mapRow(ResultSet rs, int row) throws SQLException {
						// BrandBean bBean = new BrandBean();
						DatabaseManagementBean dbBean = new DatabaseManagementBean();
						dbBean.setSk_model_id(rs.getString("sk_model_id"));
						dbBean.setModel_name(rs.getString("model_name"));
						dbBean.setBrand_id(rs.getInt("brand_id"));
						dbBean.setBrand_name(rs.getString("brand_name"));
						return dbBean;
					}
				});
	}

	public void addModel(DatabaseManagementBean dbBean, String user_id) {
		System.out.println(
				"INSERT INTO mst_brand_model(brand_id,model_name,created_by,created_date,model_status) VALUES ('"
						+ dbBean.getBrand_id() + "','" + dbBean.getModel_name() + "','" + user_id + "','"
						+ dateFormat.format(date) + "','active');");
		template.execute(
				"INSERT INTO mst_brand_model(brand_id,model_name,created_by,created_date,model_status) VALUES ('"
						+ dbBean.getBrand_id() + "','" + dbBean.getModel_name() + "','" + user_id + "','"
						+ dateFormat.format(date) + "','active');");

	}

	public DatabaseManagementBean getModelDetailsById(DatabaseManagementBean dbBean, String sid) {
		System.out.println(
				"SELECT mst_brand_model.*,mst_brand.brand_name FROM mst_brand_model,mst_brand where sk_model_id='" + sid
						+ "' AND mst_brand_model.brand_id=mst_brand.sk_brand_id and model_status='active';");
		return template.queryForObject(
				"SELECT mst_brand_model.*,mst_brand.brand_name FROM mst_brand_model,mst_brand where sk_model_id='" + sid
						+ "' AND mst_brand_model.brand_id=mst_brand.sk_brand_id and model_status='active' ;",
				new RowMapper<DatabaseManagementBean>() {
					public DatabaseManagementBean mapRow(ResultSet rs, int row) throws SQLException {
						dbBean.setBrand_id(rs.getInt("brand_id"));
						dbBean.setModel_name(rs.getString("model_name"));
						dbBean.setBrand_name(rs.getString("brand_name"));
						return dbBean;
					}
				});
	}

	public void deleteModelById(DatabaseManagementBean dbBean, String sid) {
		System.out.println("UPDATE mst_brand_model SET model_status='inactive' WHERE sk_model_id='" + sid + "'");
		template.execute("UPDATE mst_brand_model SET model_status='inactive' WHERE sk_model_id='" + sid + "'");
	}

	public void restoreModelModelById(DatabaseManagementBean dbBean, String sid) {
		System.out.println("UPDATE mst_brand_model SET model_status='active' WHERE sk_model_id='" + sid + "'");
		template.execute("UPDATE mst_brand_model SET model_status='active' WHERE sk_model_id='" + sid + "'");
	}

	public void updatModelById(DatabaseManagementBean dbBean, String sid) {
		System.out.println("UPDATE mst_brand_model SET brand_id='" + dbBean.getSk_brand_id() + "',model_name='"
				+ dbBean.getModel_name() + "' WHERE sk_model_id='" + sid + "'");
		template.execute("UPDATE mst_brand_model SET brand_id='" + dbBean.getSk_brand_id() + "',model_name='"
				+ dbBean.getModel_name() + "' WHERE sk_model_id='" + sid + "'");
	}

	public DatabaseManagementBean getmodelexist(DatabaseManagementBean dbBean, String name, String brand_id) {
		String count = "";
		String status = "";
		String sql = "SELECT count(*)as exist,ifnull(model_status,'no status') as model_status FROM mst_brand_model WHERE model_name = '"
				+ name + "' and brand_id='" + brand_id + "' ";
		System.out.println(sql);
		List<Map<String, Object>> list = template.queryForList(sql);
		for (Map<String, Object> row : list) {
			count = row.get("exist").toString();
			status = row.get("model_status").toString();
		}
		int id_status = Integer.parseInt(count);

		if (id_status > 0) {
			System.out.println("exist");

			dbBean.setMessage("modelexist");
			dbBean.setStatus(status);
		} else {
			dbBean.setStatus(status);
			dbBean.setMessage("modelnotexist");
		}
		return dbBean;
	}

	public StateBean getModelExistanceById(CityBean cBean, String name, String modelid, String brandid) {
		String count = "";
		String status = "";
		String sql = "SELECT count(*)as exist,ifnull(model_status,'no status') as city_status FROM mst_brand_model WHERE city_name = '"
				+ name + "' and brand_id='" + brandid + "' and sk_model_id!='" + modelid + "'";
		System.out.println(sql);
		List<Map<String, Object>> list = template.queryForList(sql);
		for (Map<String, Object> row : list) {
			count = row.get("exist").toString();
			status = row.get("city_status").toString();
		}
		int id_status = Integer.parseInt(count);

		if (id_status > 0) {
			System.out.println("exist");

			cBean.setMessage("cityexist");
			cBean.setStatus(status);
		} else {
			cBean.setStatus(status);
			cBean.setMessage("citynotexist");
		}
		return cBean;
	}

	/* Model end */
	/* varient start */
	public void addVarient(DatabaseManagementBean dbBean, String user_id) {
		System.out.println(
				"INSERT INTO mst_brand_model_variant(brand_id,model_id,variant_name,created_by,created_date,variant_status) VALUES ('"
						+ dbBean.getBrand_id() + "','" + dbBean.getModel_id() + "','" + dbBean.getVarient_name() + "','"
						+ user_id + "','" + dateFormat.format(date) + "','active');");
		template.execute(
				"INSERT INTO mst_brand_model_variant(brand_id,model_id,variant_name,created_by,created_date,variant_status) VALUES ('"
						+ dbBean.getBrand_id() + "','" + dbBean.getModel_id() + "','" + dbBean.getVarient_name() + "','"
						+ user_id + "','" + dateFormat.format(date) + "','active');");

	}

	public List<DatabaseManagementBean> getVarientList(DatabaseManagementBean dbBean) {
		System.out.println(
				"SELECT mst_brand.brand_name,mst_brand.brand_status,mst_brand_model.*,mst_brand_model_variant.* FROM mst_brand,mst_brand_model,mst_brand_model_variant WHERE mst_brand_model_variant.brand_id=mst_brand.sk_brand_id AND mst_brand_model_variant.model_id=mst_brand_model.sk_model_id AND mst_brand.brand_status='active' AND mst_brand_model.model_status='active' AND mst_brand_model_variant.variant_status='active'");
		return template.query(
				"SELECT mst_brand.brand_name,mst_brand.brand_status,mst_brand_model.*,mst_brand_model_variant.* FROM mst_brand,mst_brand_model,mst_brand_model_variant WHERE mst_brand_model_variant.brand_id=mst_brand.sk_brand_id AND mst_brand_model_variant.model_id=mst_brand_model.sk_model_id AND mst_brand.brand_status='active' AND mst_brand_model.model_status='active' AND mst_brand_model_variant.variant_status='active';",
				new RowMapper<DatabaseManagementBean>() {
					public DatabaseManagementBean mapRow(ResultSet rs, int row) throws SQLException {
						DatabaseManagementBean dbBean = new DatabaseManagementBean();
						dbBean.setModel_id(rs.getInt("model_id"));
						dbBean.setModel_name(rs.getString("model_name"));
						dbBean.setBrand_id(rs.getInt("brand_id"));
						dbBean.setBrand_name(rs.getString("brand_name"));
						dbBean.setSk_varient_id(rs.getInt("sk_variant_id"));
						dbBean.setVarient_name(rs.getString("variant_name"));
						return dbBean;
					}
				});
	}

	public List<DatabaseManagementBean> getInactiveVarientList(DatabaseManagementBean dbBean) {
		System.out.println(
				"SELECT mst_brand.brand_name,mst_brand.brand_status,mst_brand_model.model_name,mst_brand_model.model_status,mst_brand_model_variant.* FROM mst_brand,mst_brand_model,mst_brand_model_variant WHERE mst_brand_model_variant.brand_id=mst_brand.sk_brand_id AND mst_brand_model_variant.model_id=mst_brand_model.sk_model_id AND mst_brand_model_variant.variant_status='inactive';");
		return template.query(
				"SELECT mst_brand.brand_name,mst_brand.brand_status,mst_brand_model.model_name,mst_brand_model.model_status,mst_brand_model_variant.* FROM mst_brand,mst_brand_model,mst_brand_model_variant WHERE mst_brand_model_variant.brand_id=mst_brand.sk_brand_id AND mst_brand_model_variant.model_id=mst_brand_model.sk_model_id AND mst_brand_model_variant.variant_status='inactive';",
				new RowMapper<DatabaseManagementBean>() {
					public DatabaseManagementBean mapRow(ResultSet rs, int row) throws SQLException {
						DatabaseManagementBean dbBean = new DatabaseManagementBean();
						dbBean.setModel_id(rs.getInt("model_id"));
						dbBean.setModel_name(rs.getString("model_name"));
						dbBean.setBrand_id(rs.getInt("brand_id"));
						dbBean.setBrand_name(rs.getString("brand_name"));
						dbBean.setSk_varient_id(rs.getInt("sk_variant_id"));
						dbBean.setVarient_name(rs.getString("variant_name"));
						return dbBean;
					}
				});
	}

	public void deleteVarientById(DatabaseManagementBean dbBean, String sid) {
		System.out.println(
				"UPDATE mst_brand_model_variant SET variant_status='inactive' WHERE sk_variant_id='" + sid + "'");
		template.execute(
				"UPDATE mst_brand_model_variant SET variant_status='inactive' WHERE sk_variant_id='" + sid + "'");
	}

	public void restoreVarientVarientById(DatabaseManagementBean dbBean, String sid) {
		System.out.println(
				"UPDATE mst_brand_model_variant SET variant_status='active' WHERE sk_variant_id='" + sid + "'");
		template.execute(
				"UPDATE mst_brand_model_variant SET variant_status='active' WHERE sk_variant_id='" + sid + "'");
	}

	public void updateVariantById(DatabaseManagementBean dbBean, String sid) {
		System.out.println("UPDATE mst_brand_model_variant SET brand_id='" + dbBean.getSk_brand_id() + "',model_id='"
				+ dbBean.getSk_model_id() + "',variant_name='" + dbBean.getVarient_name() + "' WHERE sk_variant_id='"
				+ sid + "'");
		template.execute("UPDATE mst_brand_model_variant SET brand_id='" + dbBean.getSk_brand_id() + "',model_id='"
				+ dbBean.getSk_model_id() + "',variant_name='" + dbBean.getVarient_name() + "' WHERE sk_variant_id='"
				+ sid + "'");
	}

	public DatabaseManagementBean getVariantDetailsById(DatabaseManagementBean dbBean, String sid) {
		System.out.println(
				"SELECT mst_brand_model_variant.*,mst_brand_model.model_name,mst_brand.brand_name FROM mst_brand_model_variant,mst_brand_model,mst_brand where sk_variant_id='"
						+ sid
						+ "' and mst_brand_model_variant.brand_id=mst_brand.sk_brand_id AND mst_brand_model_variant.model_id=mst_brand_model.sk_model_id AND  variant_status='active';");
		return template.queryForObject(
				"SELECT mst_brand_model_variant.*,mst_brand_model.model_name,mst_brand.brand_name FROM mst_brand_model_variant,mst_brand_model,mst_brand where sk_variant_id='"
						+ sid
						+ "' and mst_brand_model_variant.brand_id=mst_brand.sk_brand_id AND mst_brand_model_variant.model_id=mst_brand_model.sk_model_id AND  variant_status='active' ;",
				new RowMapper<DatabaseManagementBean>() {
					public DatabaseManagementBean mapRow(ResultSet rs, int row) throws SQLException {
						dbBean.setBrand_id(rs.getInt("brand_id"));
						dbBean.setModel_id(rs.getInt("model_id"));
						dbBean.setVarient_name(rs.getString("variant_name"));
						dbBean.setBrand_name(rs.getString("brand_name"));
						dbBean.setModel_name(rs.getString("model_name"));
						return dbBean;
					}
				});
	}

	public DatabaseManagementBean getvariantexist(DatabaseManagementBean dbBean, String name, String model_id) {
		String count = "";
		String status = "";
		String sql = "SELECT count(*)as exist,ifnull(variant_status,'no status') as variant_status FROM mst_brand_model_variant WHERE variant_name = '"
				+ name + "'  and model_id= '" + model_id + "' ";
		System.out.println(sql);
		List<Map<String, Object>> list = template.queryForList(sql);
		for (Map<String, Object> row : list) {
			count = row.get("exist").toString();
			status = row.get("variant_status").toString();
		}
		int id_status = Integer.parseInt(count);

		if (id_status > 0) {
			System.out.println("exist");

			dbBean.setMessage("variantexist");
			dbBean.setStatus(status);
		} else {
			dbBean.setStatus(status);
			dbBean.setMessage("variantnotexist");
		}
		return dbBean;
	}

	/* variant end */

	/* dear management */

	public StateBean getDealerExistance(RegionBean rBean, String name, String brand_id) {
		String count = "";
		String status = "";
		String sql = "SELECT count(*)as exist,ifnull(dealer_status,'no status') as dealer_status FROM mst_dealer WHERE dealer_name = '"
				+ name + "' AND brand_id='" + brand_id + "'";
		System.out.println(sql);
		List<Map<String, Object>> list = template.queryForList(sql);
		for (Map<String, Object> row : list) {
			count = row.get("exist").toString();
			status = row.get("dealer_status").toString();
		}
		int id_status = Integer.parseInt(count);

		if (id_status > 0) {
			rBean.setMessage("exist");
			rBean.setStatus(status);
		} else {
			rBean.setStatus(status);
			rBean.setMessage("notexist");
		}
		return rBean;
	}

	public List<DatabaseManagementBean> getDealersByBrandId(DatabaseManagementBean dbBean, String brand_id) {
		System.out
				.println("SELECT * FROM `mst_dealer` WHERE `brand_id`='" + brand_id + "' AND dealer_status='active';");
		return template.query(
				"SELECT * FROM `mst_dealer` WHERE `brand_id`='" + brand_id + "' AND dealer_status='active' ;",
				new RowMapper<DatabaseManagementBean>() {
					public DatabaseManagementBean mapRow(ResultSet rs, int row) throws SQLException {
						DatabaseManagementBean dbBean = new DatabaseManagementBean();
						dbBean.setSk_dealer_id(rs.getString("sk_dealer_id"));
						dbBean.setDealer_name(rs.getString("dealer_name"));
						return dbBean;
					}
				});
	}
	public List<DatabaseManagementBean> getRegionsByDealerId(DatabaseManagementBean dbBean, String dealer_id) {
		System.out
				.println("SELECT mst_dealer.dealer_name,mst_geo_region.sk_region_id,mst_geo_region.region_name FROM mst_dealer,mst_geo_region where mst_dealer.region_id=mst_geo_region.sk_region_id AND mst_dealer.sk_dealer_id='"+dealer_id+"' AND mst_geo_region.region_status='active';");
		return template.query(
				"SELECT mst_dealer.dealer_name,mst_geo_region.sk_region_id,mst_geo_region.region_name FROM mst_dealer,mst_geo_region where mst_dealer.region_id=mst_geo_region.sk_region_id AND mst_dealer.sk_dealer_id='"+dealer_id+"' AND mst_geo_region.region_status='active';",
				new RowMapper<DatabaseManagementBean>() {
					public DatabaseManagementBean mapRow(ResultSet rs, int row) throws SQLException {
						DatabaseManagementBean dbBean = new DatabaseManagementBean();
						dbBean.setSk_region_id(rs.getString("sk_region_id"));
						dbBean.setRegion_name(rs.getString("region_name"));
						return dbBean;
					}
				});
	}

	public DealerBean addDealership(DealerBean dBean, String user_id) {
		System.out.println(
				"INSERT INTO mst_dealer(dealer_name,region_id,brand_id,contact_persion,email,mobile,address,lat,lan,created_by,create_date,dealer_status) VALUES ('"
						+ dBean.getDealer_name() + "','" + dBean.getRegion_id() + "','" + dBean.getBrand_id() + "','"
						+ dBean.getContact_persion() + "','" + dBean.getEmail() + "','" + dBean.getMobile() + "','"
						+ dBean.getAddress().replace("'", "\\'") + "','" + dBean.getLat() + "','" + dBean.getLan()
						+ "','" + user_id + "','" + dateFormat.format(date) + "','active')");
		template.execute(
				"INSERT INTO mst_dealer(dealer_name,region_id,brand_id,contact_persion,email,mobile,address,lat,lan,created_by,create_date,dealer_status) VALUES ('"
						+ dBean.getDealer_name() + "','" + dBean.getRegion_id() + "','" + dBean.getBrand_id() + "','"
						+ dBean.getContact_persion() + "','" + dBean.getEmail() + "','" + dBean.getMobile() + "','"
						+ dBean.getAddress().replace("'", "\\'") + "','" + dBean.getLat() + "','" + dBean.getLan()
						+ "','" + user_id + "','" + dateFormat.format(date) + "','active')");
		return dBean;

	}

	public List<DealerBean> getDealerList(DealerBean dBean) {
		System.out.println(
				"SELECT mst_dealer.*,mst_brand.brand_name FRom mst_dealer,mst_brand where mst_dealer.brand_id=mst_brand.sk_brand_id AND dealer_status='active';");
		return template.query(
				"SELECT mst_dealer.*,mst_brand.brand_name FRom mst_dealer,mst_brand where mst_dealer.brand_id=mst_brand.sk_brand_id AND dealer_status='active';",
				new RowMapper<DealerBean>() {
					public DealerBean mapRow(ResultSet rs, int row) throws SQLException {
						DealerBean dBean = new DealerBean();
						dBean.setSk_dealer_id(rs.getString("sk_dealer_id"));
						dBean.setDealer_name(rs.getString("dealer_name"));
						dBean.setContact_persion(rs.getString("contact_persion"));
						dBean.setEmail(rs.getString("email"));
						dBean.setMobile(rs.getString("mobile"));
						dBean.setAddress(rs.getString("address"));
						dBean.setBrand_id(rs.getString("brand_id"));
						dBean.setBrand_name(rs.getString("brand_name"));
						return dBean;
					}
				});
	}

	public List<DealerBean> getInactiveDealerList(DealerBean dBean) {
		System.out.println(
				"SELECT mst_dealer.*,mst_brand.brand_name FRom mst_dealer,mst_brand where mst_dealer.brand_id=mst_brand.sk_brand_id AND dealer_status='inactive';");
		return template.query(
				"SELECT mst_dealer.*,mst_brand.brand_name FRom mst_dealer,mst_brand where mst_dealer.brand_id=mst_brand.sk_brand_id AND dealer_status='inactive';",
				new RowMapper<DealerBean>() {
					public DealerBean mapRow(ResultSet rs, int row) throws SQLException {
						DealerBean dBean = new DealerBean();
						dBean.setSk_dealer_id(rs.getString("sk_dealer_id"));
						dBean.setDealer_name(rs.getString("dealer_name"));
						dBean.setContact_persion(rs.getString("contact_persion"));
						dBean.setEmail(rs.getString("email"));
						dBean.setMobile(rs.getString("mobile"));
						dBean.setAddress(rs.getString("address"));
						dBean.setBrand_id(rs.getString("brand_id"));
						dBean.setBrand_name(rs.getString("brand_name"));
						return dBean;
					}
				});
	}

	public void deleteDealerById(DealerBean dBean, String did) {
		System.out.println("update mst_dealer set dealer_status='inactive' where sk_dealer_id='" + did + "'");
		template.execute("update mst_dealer set dealer_status='inactive' where sk_dealer_id='" + did + "';");

	}

	public void restoreDealerById(DealerBean dBean, String did) {
		System.out.println("update mst_dealer set dealer_status='active' where sk_dealer_id='" + did + "'");
		template.execute("update mst_dealer set dealer_status='active' where sk_dealer_id='" + did + "';");

	}

	public DealerBean getDealerDetailsById(DealerBean dBean, String did) {
		System.out.println(
				"SELECT * FROM `mst_dealer`,mst_geo_region,mst_brand WHERE mst_dealer.region_id=mst_geo_region.sk_region_id and mst_brand.sk_brand_id=mst_dealer.brand_id and `sk_dealer_id`='"
						+ did + "' ;");
		return template.queryForObject(
				"SELECT * FROM `mst_dealer`,mst_geo_region,mst_brand WHERE mst_dealer.region_id=mst_geo_region.sk_region_id and mst_brand.sk_brand_id=mst_dealer.brand_id and `sk_dealer_id`='"
						+ did + "';",
				new RowMapper<DealerBean>() {
					public DealerBean mapRow(ResultSet rs, int row) throws SQLException {
						dBean.setSk_dealer_id(rs.getString("sk_dealer_id"));
						dBean.setDealer_name(rs.getString("dealer_name"));
						dBean.setContact_persion(rs.getString("contact_persion"));
						dBean.setEmail(rs.getString("email"));
						dBean.setMobile(rs.getString("mobile"));
						dBean.setAddress(rs.getString("address"));
						dBean.setRegion_id(rs.getString("region_id"));
						dBean.setBrand_id(rs.getString("brand_id"));
						dBean.setBrand_name(rs.getString("brand_name"));
						dBean.setRegion_name(rs.getString("region_name"));
						dBean.setLat(rs.getString("lat"));
						dBean.setLan(rs.getString("lan"));
						return dBean;
					}
				});
	}

	public void updateDealerPost(DealerBean dBean, String did, String user_id) {
		template.execute("UPDATE `mst_dealer` SET  `region_id`='" + dBean.getRegion_id() + "',`brand_id`='"
				+ dBean.getBrand_id() + "',`dealer_name`='" + dBean.getDealer_name() + "',`contact_persion`='"
				+ dBean.getContact_persion() + "',`email`='" + dBean.getEmail() + "',`mobile`='" + dBean.getMobile()
				+ "',`lat`='" + dBean.getLat() + "',`lan`='" + dBean.getLat() + "',`address`='" + dBean.getAddress()
				+ "',`created_by`='" + user_id + "',`create_date`='" + dateFormat.format(date)
				+ "' WHERE `sk_dealer_id`='" + did + "'");

	}

	public StateBean getDealerExistanceById(RegionBean rBean, String name, String did, String brand_id) {
		String count = "";
		String status = "";
		String sql = "SELECT count(*)as exist,ifnull(dealer_status,'no status') as dealer_status FROM mst_dealer WHERE dealer_name = '"
				+ name + "' and sk_dealer_id!='" + did + "' and brand_id='" + brand_id + "' ";
		System.out.println(sql);
		List<Map<String, Object>> list = template.queryForList(sql);
		for (Map<String, Object> row : list) {
			count = row.get("exist").toString();
			status = row.get("dealer_status").toString();
		}
		int id_status = Integer.parseInt(count);

		if (id_status > 0) {
			rBean.setMessage("exist");
			rBean.setStatus(status);
		} else {
			rBean.setStatus(status);
			rBean.setMessage("notexist");
		}
		return rBean;
	}
	/* dealer Management End */

	public DatabaseManagementBean getBrandExistanceByid(DatabaseManagementBean dbBean, String name, String brand_id) {
		String count = "";
		String status = "";
		String sql = "SELECT count(*)as exist,ifnull(brand_status,'no status') as brand_status FROM mst_brand WHERE brand_name = '"
				+ name + "' and sk_brand_id!='" + brand_id + "' ";
		System.out.println(sql);
		List<Map<String, Object>> list = template.queryForList(sql);
		for (Map<String, Object> row : list) {
			count = row.get("exist").toString();
			status = row.get("brand_status").toString();
		}
		int id_status = Integer.parseInt(count);

		if (id_status > 0) {
			System.out.println("exist");

			dbBean.setMessage("brandexist");
			dbBean.setStatus(status);
		} else {
			dbBean.setStatus(status);
			dbBean.setMessage("brandnotexist");
		}
		return dbBean;
	}

	public DatabaseManagementBean getmodelexistById(DatabaseManagementBean dbBean, String name, String brand_id,
			String model_id) {
		String count = "";
		String status = "";
		String sql = "SELECT count(*)as exist,ifnull(model_status,'no status') as model_status FROM mst_brand_model WHERE model_name = '"
				+ name + "' and brand_id='" + brand_id + "' and sk_model_id!='" + model_id + "'";
		System.out.println(sql);
		List<Map<String, Object>> list = template.queryForList(sql);
		for (Map<String, Object> row : list) {
			count = row.get("exist").toString();
			status = row.get("model_status").toString();
		}
		int id_status = Integer.parseInt(count);

		if (id_status > 0) {
			System.out.println("exist");

			dbBean.setMessage("modelexist");
			dbBean.setStatus(status);
		} else {
			dbBean.setStatus(status);
			dbBean.setMessage("modelnotexist");
		}
		return dbBean;
	}

	public List<DatabaseManagementBean> getModelsByBrandId(DatabaseManagementBean dbBean, String brand_id) {
		System.out.println(
				"SELECT * FROM `mst_brand_model` WHERE `brand_id`='" + brand_id + "' AND model_status='active';");
		return template.query(
				"SELECT * FROM `mst_brand_model` WHERE `brand_id`='" + brand_id + "' AND model_status='active' ;",
				new RowMapper<DatabaseManagementBean>() {
					public DatabaseManagementBean mapRow(ResultSet rs, int row) throws SQLException {
						DatabaseManagementBean dbBean = new DatabaseManagementBean();
						dbBean.setSk_model_id(rs.getString("sk_model_id"));
						dbBean.setModel_name(rs.getString("model_name"));
						return dbBean;
					}
				});
	}

	public DatabaseManagementBean getVariantExistanceByid(DatabaseManagementBean dbBean, String name, String model_id,
			String variant_id) {
		String count = "";
		String status = "";
		String sql = "SELECT count(*)as exist,ifnull(variant_status,'no status') as variant_status FROM mst_brand_model_variant WHERE variant_name = '"
				+ name + "'  and model_id= '" + model_id + "' and sk_variant_id!='" + variant_id + "'";
		System.out.println(sql);
		List<Map<String, Object>> list = template.queryForList(sql);
		for (Map<String, Object> row : list) {
			count = row.get("exist").toString();
			status = row.get("variant_status").toString();
		}
		int id_status = Integer.parseInt(count);

		if (id_status > 0) {
			System.out.println("exist");

			dbBean.setMessage("variantexist");
			dbBean.setStatus(status);
		} else {
			dbBean.setStatus(status);
			dbBean.setMessage("variantnotexist");
		}
		return dbBean;
	}

	public DatabaseManagementBean addSection(DatabaseManagementBean dbBean, String user_id) {

		System.out.println("INSERT INTO mst_section(section_name, created_by,section_status) VALUES ('"
				+ dbBean.getSection_name() + "','" + user_id + "','active');");
		template.execute("INSERT INTO mst_section(section_name,created_by,section_status) VALUES ('"
				+ dbBean.getSection_name() + "','" + user_id + "','active');");
		return dbBean;
	}

	public List<DatabaseManagementBean> getSectionList(DatabaseManagementBean dbBean) {

		System.out.println("SELECT * FROM mst_section where section_status='active';");
		return template.query("SELECT * FROM mst_section where section_status='active' ;",
				new RowMapper<DatabaseManagementBean>() {
					public DatabaseManagementBean mapRow(ResultSet rs, int row) throws SQLException {
						DatabaseManagementBean dbBean = new DatabaseManagementBean();
						dbBean.setSk_section_id(rs.getString("sk_section_id"));
						dbBean.setSection_name(rs.getString("section_name"));
						return dbBean;
					}
				});
	}

	public List<DatabaseManagementBean> getInactiveSectionList(DatabaseManagementBean dbBean) {

		System.out.println("SELECT * FROM mst_section where section_status='inactive';");
		return template.query("SELECT * FROM mst_section where section_status='inactive' ;",
				new RowMapper<DatabaseManagementBean>() {
					public DatabaseManagementBean mapRow(ResultSet rs, int row) throws SQLException {
						DatabaseManagementBean dbBean = new DatabaseManagementBean();
						dbBean.setSk_section_id(rs.getString("sk_section_id"));
						dbBean.setSection_name(rs.getString("section_name"));
						return dbBean;
					}
				});
	}

	public DatabaseManagementBean getsectionExistance(DatabaseManagementBean dbBean, String section_name) {
		String count = "";
		String status = "";
		String sql = "SELECT count(*)as exist,ifnull(section_status,'no status') as section_status FROM mst_section WHERE section_name = '"
				+ section_name + "' ";
		System.out.println(sql);
		List<Map<String, Object>> list = template.queryForList(sql);
		for (Map<String, Object> row : list) {
			count = row.get("exist").toString();
			status = row.get("section_status").toString();
		}
		int id_status = Integer.parseInt(count);

		if (id_status > 0) {
			System.out.println("exist");
			dbBean.setMessage("sectionexist");
			dbBean.setStatus(status);
		} else {
			dbBean.setStatus(status);
			dbBean.setMessage("sectionnotexist");
		}
		return dbBean;
	}

	public DatabaseManagementBean getsectionExistanceById(DatabaseManagementBean dbBean, String section_name,
			String sk_section_id) {
		String count = "";
		String status = "";
		String sql = "SELECT count(*)as exist,ifnull(section_status,'no status') as section_status FROM mst_section WHERE section_name = '"
				+ section_name + "' and sk_section_id!='" + sk_section_id + "'";
		System.out.println(sql);
		List<Map<String, Object>> list = template.queryForList(sql);
		for (Map<String, Object> row : list) {
			count = row.get("exist").toString();
			status = row.get("section_status").toString();
		}
		int id_status = Integer.parseInt(count);

		if (id_status > 0) {
			System.out.println("exist");

			dbBean.setMessage("sectionexist");
			dbBean.setStatus(status);
		} else {
			dbBean.setStatus(status);
			dbBean.setMessage("sectionnotexist");
		}
		return dbBean;
	}

	public DatabaseManagementBean getSetionById(DatabaseManagementBean dbBean, String sectionId) {

		System.out.println("SELECT * FROM mst_section where sk_section_id='" + sectionId + "';");
		return template.queryForObject("SELECT * FROM mst_section where sk_section_id='" + sectionId + "' ;",
				new RowMapper<DatabaseManagementBean>() {
					public DatabaseManagementBean mapRow(ResultSet rs, int row) throws SQLException {
						dbBean.setSk_section_id(rs.getString("sk_section_id"));
						dbBean.setSection_name(rs.getString("section_name"));
						return dbBean;
					}
				});
	}

	public DatabaseManagementBean updateSection(DatabaseManagementBean dbBean, String userId) {
		System.out.println("UPDATE mst_section SET section_name='" + dbBean.getSection_name() + "',created_by='"
				+ userId + "' WHERE sk_section_id='" + dbBean.getSk_section_id() + "'");
		template.execute("UPDATE mst_section SET section_name='" + dbBean.getSection_name() + "',created_by='" + userId
				+ "'  WHERE sk_section_id='" + dbBean.getSk_section_id() + "'");
		return dbBean;

	}

	public DatabaseManagementBean deleteSectionById(DatabaseManagementBean dbBean, String sectionId, String user_id) {

		System.out.println("UPDATE mst_section SET section_status='inactive',created_by='" + user_id
				+ "'  WHERE sk_section_id='" + sectionId + "'");
		template.execute("UPDATE mst_section SET section_status='inactive',created_by='" + user_id
				+ "' WHERE sk_section_id='" + sectionId + "'");
		return dbBean;
	}

	public DatabaseManagementBean restoreSectionById(DatabaseManagementBean dbBean, String sectionId, String user_id) {

		System.out.println("UPDATE mst_section SET section_status='active',created_by='" + user_id
				+ "' WHERE sk_section_id='" + sectionId + "'");
		template.execute("UPDATE mst_section SET section_status='active',created_by='" + user_id
				+ "' WHERE sk_section_id='" + sectionId + "'");
		return dbBean;
	}

	public DatabaseManagementBean addSubSection(DatabaseManagementBean dbBean, String user_id) {
		System.out
				.println("INSERT INTO mst_subsection(subsection_name,section_id,created_by,subsection_status) VALUES ('"
						+ dbBean.getSubsection_name() + "','" + dbBean.getSk_section_id() + "','" + user_id
						+ "','active');");
		template.execute("INSERT INTO mst_subsection(subsection_name,section_id,created_by,subsection_status) VALUES ('"
				+ dbBean.getSubsection_name() + "','" + dbBean.getSk_section_id() + "','" + user_id + "','active');");
		return dbBean;

	}

	public List<DatabaseManagementBean> getSubsectionList(DatabaseManagementBean dbBean) {
		System.out.println(
				"SELECT mst_subsection.*, mst_section.section_name FROM mst_subsection,mst_section where mst_subsection.section_id=mst_section.sk_section_id and mst_subsection.subsection_status='active' and mst_section.section_status='active' ;");
		return template.query(
				"SELECT mst_subsection.*, mst_section.section_name FROM mst_subsection,mst_section where mst_subsection.section_id=mst_section.sk_section_id and mst_subsection.subsection_status='active' and mst_section.section_status='active' ",
				new RowMapper<DatabaseManagementBean>() {
					public DatabaseManagementBean mapRow(ResultSet rs, int row) throws SQLException {
						DatabaseManagementBean dbBean = new DatabaseManagementBean();
						dbBean.setSk_subsection_id(rs.getString("sk_subsection_id"));
						dbBean.setSubsection_name(rs.getString("subsection_name"));
						dbBean.setSk_section_id(rs.getString("section_id"));
						dbBean.setSection_name(rs.getString("section_name"));
						return dbBean;
					}
				});
	}

	public List<DatabaseManagementBean> getInactiveSubSectionList(DatabaseManagementBean dbBean) {
		System.out.println(
				"SELECT mst_subsection.*, mst_section.section_name FROM mst_subsection,mst_section where mst_subsection.section_id=mst_section.sk_section_id and mst_subsection.subsection_status='inactive';");
		return template.query(
				"SELECT mst_subsection.*, mst_section.section_name FROM mst_subsection,mst_section where mst_subsection.section_id=mst_section.sk_section_id and mst_subsection.subsection_status='inactive'",
				new RowMapper<DatabaseManagementBean>() {
					public DatabaseManagementBean mapRow(ResultSet rs, int row) throws SQLException {
						DatabaseManagementBean dbBean = new DatabaseManagementBean();
						dbBean.setSk_subsection_id(rs.getString("sk_subsection_id"));
						dbBean.setSubsection_name(rs.getString("subsection_name"));
						dbBean.setSk_section_id(rs.getString("section_id"));
						dbBean.setSection_name(rs.getString("section_name"));
						return dbBean;
					}
				});
	}

	public DatabaseManagementBean getSubsectionExistance(DatabaseManagementBean dbBean, String sk_section_id,
			String subsection_name) {
		String count = "";
		String status = "";
		String sql = "SELECT count(*)as exist,ifnull(subsection_status,'no status') as subsection_status FROM  mst_subsection WHERE section_id = '"
				+ sk_section_id + "' and subsection_name='" + subsection_name + "' ";
		System.out.println(sql);
		List<Map<String, Object>> list = template.queryForList(sql);
		for (Map<String, Object> row : list) {
			count = row.get("exist").toString();
			status = row.get("subsection_status").toString();
		}
		int id_status = Integer.parseInt(count);

		if (id_status > 0) {
			System.out.println("exist");
			dbBean.setMessage("subsectionexist");
			dbBean.setStatus(status);
		} else {
			dbBean.setStatus(status);
			dbBean.setMessage("subsectionnotexist");
		}
		return dbBean;
	}

	public DatabaseManagementBean getsubSetionById(DatabaseManagementBean dbBean, String sk_subsection_id) {
		System.out.println(
				"SELECT mst_subsection.*, mst_section.section_name FROM mst_subsection,mst_section where mst_subsection.section_id=mst_section.sk_section_id and  mst_subsection.sk_subsection_id='"
						+ sk_subsection_id + "';;");
		return template.queryForObject(
				"SELECT mst_subsection.*, mst_section.section_name FROM mst_subsection,mst_section where mst_subsection.section_id=mst_section.sk_section_id and  mst_subsection.sk_subsection_id='"
						+ sk_subsection_id + "' ;",
				new RowMapper<DatabaseManagementBean>() {
					public DatabaseManagementBean mapRow(ResultSet rs, int row) throws SQLException {
						dbBean.setSk_subsection_id(rs.getString("sk_subsection_id"));
						dbBean.setSubsection_name(rs.getString("subsection_name"));
						dbBean.setSk_section_id(rs.getString("section_id"));
						dbBean.setSection_name(rs.getString("section_name"));
						return dbBean;
					}
				});

	}

	public DatabaseManagementBean getSubSectionExistanceById(DatabaseManagementBean dbBean, String subSectionName,
			String sectionId, String subsectionId) {
		String count = "";
		String status = "";
		String sql = "SELECT count(*)as exist,ifnull(subsection_status,'no status') as subsection_status FROM mst_subsection WHERE subsection_name = '"
				+ subSectionName + "' and section_id='" + sectionId + "' and sk_subsection_id!='" + subsectionId + "'";
		System.out.println(sql);
		List<Map<String, Object>> list = template.queryForList(sql);
		for (Map<String, Object> row : list) {
			count = row.get("exist").toString();
			status = row.get("subsection_status").toString();
		}
		int id_status = Integer.parseInt(count);

		if (id_status > 0) {
			System.out.println("exist");

			dbBean.setMessage("subsectionexist");
			dbBean.setStatus(status);
		} else {
			dbBean.setStatus(status);
			dbBean.setMessage("subsectionnotexist");
		}
		return dbBean;
	}

	public DatabaseManagementBean updatesubSection(DatabaseManagementBean dbBean, String user_id) {

		System.out.println("UPDATE mst_subsection SET subsection_name='" + dbBean.getSubsection_name()
				+ "',section_id='" + dbBean.getSk_section_id() + "',created_by='" + user_id
				+ "'  WHERE sk_subsection_id='" + dbBean.getSk_subsection_id() + "'");
		template.execute("UPDATE mst_subsection SET subsection_name='" + dbBean.getSubsection_name() + "',section_id='"
				+ dbBean.getSk_section_id() + "',created_by='" + user_id + "'  WHERE sk_subsection_id='"
				+ dbBean.getSk_subsection_id() + "'");
		return dbBean;

	}

	public DatabaseManagementBean deleteSubSectionById(DatabaseManagementBean dbBean, String sk_subsection_id,
			String user_id) {

		System.out.println("UPDATE mst_subsection SET subsection_status='inactive',created_by='" + user_id
				+ "' WHERE sk_subsection_id='" + sk_subsection_id + "'");
		template.execute("UPDATE mst_subsection SET subsection_status='inactive',created_by='" + user_id
				+ "' WHERE sk_subsection_id='" + sk_subsection_id + "'");
		return dbBean;

	}

	public DatabaseManagementBean restoreSubSectionById(DatabaseManagementBean dbBean, String sk_subsection_id,
			String user_id) {

		System.out.println("UPDATE mst_subsection SET subsection_status='active',created_by='" + user_id
				+ "' WHERE sk_subsection_id='" + sk_subsection_id + "'");
		template.execute("UPDATE mst_subsection SET subsection_status='active',created_by='" + user_id
				+ "' WHERE sk_subsection_id='" + sk_subsection_id + "'");
		return dbBean;

	}

	/*** Outlet Start *********/
	public void addOutlet(DatabaseManagementBean dbBean, String userid) {
		System.out.println(
				"INSERT INTO mst_dealer_outlet(`dealer_id`,`outlet_name`, `outlet_id`, `outlet_size`, `outlet_type`, `brand_id`, `region_id`, `state`, `city`, `sub_region`, `lat`, `lang`, `contact_person`, `email`, `mobile`, `password`, `address`, `pincode`,`created_by`,`create_date` , `outlet_status`) VALUES ('"
						+ dbBean.getSk_dealer_id() + "','" + dbBean.getOutlet_name() + "','" + dbBean.getOutlet_id()
						+ "','" + 1 + "','" + dbBean.getOutlet_type() + "','" + dbBean.getSk_brand_id() + "','"
						+ dbBean.getSk_region_id() + "','" + dbBean.getSk_state_id() + "','" + dbBean.getSk_city_id()
						+ "','1','" + dbBean.getLat() + "','" + dbBean.getLang() + "','" + dbBean.getContact_person()
						+ "','" + dbBean.getEmail() + "','" + dbBean.getMobile() + "',' 123','"
						+ dbBean.getAddress().replace("'", "\\'") + "'," + dbBean.getPincode() + "','" + userid + "','"
						+ dateFormat.format(date) + "','active');");
		template.execute(
				"INSERT INTO mst_dealer_outlet(`dealer_id`,`outlet_name`, `outlet_id`, `outlet_size`, `outlet_type`, `brand_id`, `region_id`, `state`, `city`, `sub_region`, `lat`, `lang`, `contact_person`, `email`, `mobile`, `password`, `address`, `pincode`,`created_by`, `create_date`, `outlet_status`) VALUES ('"
						+ dbBean.getSk_dealer_id() + "','" + dbBean.getOutlet_name() + "','" + dbBean.getOutlet_id()
						+ "','" + 1 + "','" + dbBean.getOutlet_type() + "','" + dbBean.getSk_brand_id() + "','"
						+ dbBean.getSk_region_id() + "','" + dbBean.getSk_state_id() + "','" + dbBean.getSk_city_id()
						+ "','1','" + dbBean.getLat() + "','" + dbBean.getLang() + "','" + dbBean.getContact_person()
						+ "','" + dbBean.getEmail() + "','" + dbBean.getMobile() + "','123','"
						+ dbBean.getAddress().replace("'", "\\'") + "','" + dbBean.getPincode() + "','" + userid + "','"
						+ dateFormat.format(date) + "','active');");
	}

	public List<StateBean> getStatesByRegionId(StateBean sBean, String region_id) {
		System.out.println(
				"SELECT * FROM `mst_geo_region` WHERE `sk_region_id`='" + region_id + "' AND region_status='active';");
		return template.query(
				"SELECT * FROM `mst_geo_region` WHERE `sk_region_id`='" + region_id + "' AND region_status='active';",
				new RowMapper<StateBean>() {
					public StateBean mapRow(ResultSet rs, int row) throws SQLException {
						StateBean sBean = new StateBean();
						sBean.setSk_state_id(rs.getString("states_list"));

						System.out.println("SELECT * FROM `mst_geo_states` WHERE `sk_state_id` in("
								+ rs.getString("states_list") + ");");
						template.query("SELECT * FROM `mst_geo_states` WHERE `sk_state_id` in("
								+ rs.getString("states_list") + ");", new RowMapper<StateBean>() {
									public StateBean mapRow(ResultSet rs, int row) throws SQLException {
										sBean.setSk_state_id(rs.getString("sk_state_id"));
										sBean.setState_name(rs.getString("state_name"));
										return sBean;
									}
								});
						return sBean;

					}
				});
	}

	public List<CityBean> getCitiesByStateId(CityBean dbBean, String state_id) {
		System.out
				.println("SELECT * FROM `mst_geo_city` WHERE `state_id`='" + state_id + "' AND city_status='active';");
		return template.query(
				"SELECT * FROM `mst_geo_city` WHERE `state_id`='" + state_id + "' AND city_status='active' ;",
				new RowMapper<CityBean>() {
					public CityBean mapRow(ResultSet rs, int row) throws SQLException {
						CityBean cBean = new CityBean();
						cBean.setSk_city_id(rs.getString("sk_city_id"));
						cBean.setCity_name(rs.getString("city_name"));
						return cBean;
					}
				});
	}

	public List<DatabaseManagementBean> getOutletList(DatabaseManagementBean dbBean) {
		System.out.println(
				"SELECT mst_dealer_outlet.*,mst_brand.brand_name,mst_dealer.dealer_name FROM mst_dealer_outlet,mst_brand,mst_dealer where mst_dealer.sk_dealer_id=mst_dealer_outlet.dealer_id AND mst_dealer_outlet.brand_id=mst_brand.sk_brand_id AND outlet_status='active' AND mst_dealer.dealer_status='active';");
		return template.query(
				"SELECT mst_dealer_outlet.*,mst_brand.brand_name,mst_dealer.dealer_name FROM mst_dealer_outlet,mst_brand,mst_dealer where mst_dealer.sk_dealer_id=mst_dealer_outlet.dealer_id AND mst_dealer_outlet.brand_id=mst_brand.sk_brand_id AND outlet_status='active' AND mst_dealer.dealer_status='active';",
				new RowMapper<DatabaseManagementBean>() {
					public DatabaseManagementBean mapRow(ResultSet rs, int row) throws SQLException {
						DatabaseManagementBean dbBean = new DatabaseManagementBean();
						dbBean.setOutlet_id(rs.getString("sk_outlet_id"));
						System.out.println(rs.getString("sk_outlet_id"));
						dbBean.setOutlet_name(rs.getString("outlet_name"));
						// dbBean.setContact_person(rs.getString("outlet_id"));
						dbBean.setContact_person(rs.getString("contact_person"));
						dbBean.setSk_brand_id(rs.getString("brand_id"));
						dbBean.setBrand_name(rs.getString("brand_name"));
						dbBean.setOutlet_type(rs.getString("outlet_type"));
						dbBean.setMobile(rs.getString("mobile"));
						dbBean.setEmail(rs.getString("email"));
						dbBean.setDealer_name(rs.getString("dealer_name"));
						dbBean.setOutlet_size(rs.getString("outlet_size"));
						return dbBean;
					}
				});
	}

	public List<DatabaseManagementBean> getinactiveOutletList(DatabaseManagementBean dbBean) {
		System.out.println(
				"SELECT mst_dealer_outlet.*,mst_brand.brand_name,mst_dealer.dealer_name FROM mst_dealer_outlet,mst_brand,mst_dealer where mst_dealer.sk_dealer_id=mst_dealer_outlet.dealer_id AND mst_dealer_outlet.brand_id=mst_brand.sk_brand_id AND outlet_status='inactive';");
		return template.query(
				"SELECT mst_dealer_outlet.*,mst_brand.brand_name,mst_dealer.dealer_name FROM mst_dealer_outlet,mst_brand,mst_dealer where mst_dealer.sk_dealer_id=mst_dealer_outlet.dealer_id AND mst_dealer_outlet.brand_id=mst_brand.sk_brand_id AND outlet_status='inactive';",
				new RowMapper<DatabaseManagementBean>() {
					public DatabaseManagementBean mapRow(ResultSet rs, int row) throws SQLException {
						DatabaseManagementBean dbBean = new DatabaseManagementBean();
						dbBean.setOutlet_id(rs.getString("sk_outlet_id"));
						System.out.println(rs.getString("sk_outlet_id"));
						dbBean.setOutlet_name(rs.getString("outlet_name"));
						// dbBean.setContact_person(rs.getString("outlet_id"));
						dbBean.setContact_person(rs.getString("contact_person"));
						dbBean.setSk_brand_id(rs.getString("brand_id"));
						dbBean.setBrand_name(rs.getString("brand_name"));
						dbBean.setOutlet_type(rs.getString("outlet_type"));
						dbBean.setMobile(rs.getString("mobile"));
						dbBean.setEmail(rs.getString("email"));
						dbBean.setDealer_name(rs.getString("dealer_name"));
						dbBean.setOutlet_size(rs.getString("outlet_size"));
						return dbBean;
					}
				});
	}

	public void deleteOutletById(DatabaseManagementBean dbBean, String oid) {
		System.out.println("UPDATE mst_dealer_outlet SET outlet_status='inactive' WHERE sk_outlet_id='" + oid + "'");
		template.execute("UPDATE mst_dealer_outlet SET outlet_status='inactive' WHERE sk_outlet_id='" + oid + "'");
	}

	public void restoreOutletById(DatabaseManagementBean dbBean, String oid) {
		System.out.println("update mst_dealer_outlet set outlet_status='active' where sk_outlet_id='" + oid + "'");
		template.execute("update mst_dealer_outlet set outlet_status='active' where sk_outlet_id='" + oid + "';");

	}

	public DatabaseManagementBean getOutletDetailsById(DatabaseManagementBean dbBean, String oid) {
		System.out.println(
				"SELECT mst_dealer_outlet.*,mst_brand.brand_name,mst_dealer.dealer_name,mst_geo_region.region_name,mst_geo_states.state_name,mst_geo_city.city_name FROM mst_dealer_outlet,mst_brand,mst_dealer,mst_geo_region,mst_geo_states,mst_geo_city where mst_dealer.sk_dealer_id=mst_dealer_outlet.dealer_id AND mst_dealer_outlet.brand_id=mst_brand.sk_brand_id AND mst_geo_region.sk_region_id=mst_dealer_outlet.region_id AND mst_geo_states.sk_state_id=mst_dealer_outlet.state AND mst_geo_city.sk_city_id=mst_dealer_outlet.city AND `sk_outlet_id`='"
						+ oid + "' AND outlet_status='active';");
		return template.queryForObject(
				"SELECT mst_dealer_outlet.*,mst_brand.brand_name,mst_dealer.dealer_name,mst_geo_region.region_name,mst_geo_states.state_name,mst_geo_city.city_name FROM mst_dealer_outlet,mst_brand,mst_dealer,mst_geo_region,mst_geo_states,mst_geo_city where mst_dealer.sk_dealer_id=mst_dealer_outlet.dealer_id AND mst_dealer_outlet.brand_id=mst_brand.sk_brand_id AND mst_geo_region.sk_region_id=mst_dealer_outlet.region_id AND mst_geo_states.sk_state_id=mst_dealer_outlet.state AND mst_geo_city.sk_city_id=mst_dealer_outlet.city AND `sk_outlet_id`='"
						+ oid + "' AND outlet_status='active';",
				new RowMapper<DatabaseManagementBean>() {
					public DatabaseManagementBean mapRow(ResultSet rs, int row) throws SQLException {
						dbBean.setSk_outlet_id(rs.getInt("sk_outlet_id"));
						dbBean.setOutlet_id(rs.getString("outlet_id"));
						dbBean.setSk_dealer_id(rs.getString("dealer_id"));
						dbBean.setDealer_name(rs.getString("dealer_name"));
						dbBean.setContact_person(rs.getString("contact_person"));
						dbBean.setEmail(rs.getString("email"));
						dbBean.setMobile(rs.getString("mobile"));
						dbBean.setAddress(rs.getString("address"));
						dbBean.setSk_region_id(rs.getString("region_id"));
						dbBean.setSk_brand_id(rs.getString("brand_id"));
						dbBean.setBrand_name(rs.getString("brand_name"));
						dbBean.setSk_city_id(rs.getString("city"));
						System.out.println(rs.getString("city"));
						dbBean.setSk_state_id(rs.getString("state"));
						dbBean.setState_name(rs.getString("state_name"));
						dbBean.setCity(rs.getString("city_name"));
						dbBean.setRegion_name(rs.getString("region_name"));
						dbBean.setOutlet_name(rs.getString("outlet_name"));
						dbBean.setLat(rs.getString("lat"));
						dbBean.setLang(rs.getString("lang"));
						dbBean.setPincode(rs.getString("pincode"));
						dbBean.setOutlet_type(rs.getString("outlet_type"));
						return dbBean;
					}
				});
	}

	public void updateOutletPost(DatabaseManagementBean dbBean, String oid, String user_id) {
		System.out.println("UPDATE `mst_dealer_outlet` SET  `region_id`='" + dbBean.getSk_region_id() + "',`state`='"
				+ dbBean.getSk_state_id() + "',`city`='" + dbBean.getSk_city_id() + "',`brand_id`='"
				+ dbBean.getSk_brand_id() + "',`dealer_id`='" + dbBean.getSk_dealer_id() + "',`outlet_name`='"
				+ dbBean.getOutlet_name() + "',`outlet_type`='" + dbBean.getOutlet_type() + "',`outlet_id`='"
				+ dbBean.getOutlet_id() + "',`contact_person`='" + dbBean.getContact_person() + "',`email`='"
				+ dbBean.getEmail() + "',`mobile`='" + dbBean.getMobile() + "',`lat`='" + dbBean.getLat() + "',`lang`='"
				+ dbBean.getLang() + "',`pincode`='" + dbBean.getPincode() + "',`address`='"
				+ dbBean.getAddress().replace("'", "\\'") + "',`created_by`='" + user_id + "',`create_date`='"
				+ dateFormat.format(date) + "' WHERE `sk_outlet_id`='" + oid + "'");
		template.execute("UPDATE `mst_dealer_outlet` SET  `region_id`='" + dbBean.getSk_region_id() + "',`state`='"
				+ dbBean.getSk_state_id() + "',`city`='" + dbBean.getSk_city_id() + "',`brand_id`='"
				+ dbBean.getSk_brand_id() + "',`dealer_id`='" + dbBean.getSk_dealer_id() + "',`outlet_name`='"
				+ dbBean.getOutlet_name() + "',`outlet_type`='" + dbBean.getOutlet_type() + "',`outlet_id`='"
				+ dbBean.getOutlet_id() + "',`contact_person`='" + dbBean.getContact_person() + "',`email`='"
				+ dbBean.getEmail() + "',`mobile`='" + dbBean.getMobile() + "',`lat`='" + dbBean.getLat() + "',`lang`='"
				+ dbBean.getLang() + "',`pincode`='" + dbBean.getPincode() + "',`address`='"
				+ dbBean.getAddress().replace("'", "\\'") + "',`created_by`='" + user_id + "',`create_date`='"
				+ dateFormat.format(date) + "' WHERE `sk_outlet_id`='" + oid + "'");

	}

	public DatabaseManagementBean getoutletexist(DatabaseManagementBean dbBean, String name, String dealer_id) {
		String count = "";
		String status = "";
		String sql = "SELECT count(*)as exist,ifnull(outlet_status,'no status') as outlet_status FROM mst_dealer_outlet WHERE outlet_name = '"
				+ name + "'  and dealer_id= '" + dealer_id + "' ";
		System.out.println(sql);
		List<Map<String, Object>> list = template.queryForList(sql);
		for (Map<String, Object> row : list) {
			count = row.get("exist").toString();
			status = row.get("outlet_status").toString();
		}
		int id_status = Integer.parseInt(count);

		if (id_status > 0) {
			System.out.println("exist");

			dbBean.setMessage("outletexist");
			dbBean.setStatus(status);
		} else {
			dbBean.setStatus(status);
			dbBean.setMessage("outletnotexist");
		}
		return dbBean;
	}
	public DatabaseManagementBean getoutletIdexist(DatabaseManagementBean dbBean, String name) {
		String count = "";
		String status = "";
		String sql = "SELECT count(*)as exist,ifnull(outlet_status,'no status') as outlet_status FROM mst_dealer_outlet WHERE outlet_id = '"
				+ name + "'";
		System.out.println(sql);
		List<Map<String, Object>> list = template.queryForList(sql);
		for (Map<String, Object> row : list) {
			count = row.get("exist").toString();
			status = row.get("outlet_status").toString();
		}
		int id_status = Integer.parseInt(count);

		if (id_status > 0) {
			System.out.println("exist");

			dbBean.setMessage("outletexist");
			dbBean.setStatus(status);
		} else {
			dbBean.setStatus(status);
			dbBean.setMessage("outletnotexist");
		}
		return dbBean;
	}

	public List<DatabaseManagementBean> getSubSections(DatabaseManagementBean dbBean, String section_id) {
		System.out.println("SELECT * FROM `mst_subsection` WHERE `section_id`='" + section_id + "' AND mst_subsection.subsection_status='active';");
		return template.query("SELECT * FROM `mst_subsection` WHERE `section_id`='" + section_id + "' AND mst_subsection.subsection_status='active';",
				new RowMapper<DatabaseManagementBean>() {
					public DatabaseManagementBean mapRow(ResultSet rs, int row) throws SQLException {
						DatabaseManagementBean dbBean = new DatabaseManagementBean();
						dbBean.setSk_subsection_id(rs.getString("sk_subsection_id"));
						dbBean.setSubsection_name(rs.getString("subsection_name"));
						return dbBean;
					}
				});

	}

	public List<QuestionnaireBean> getdatetimeList(QuestionnaireBean qBean, String sk_question_id) {
		System.out.println("SELECT * FROM `mystry_dateanswers` WHERE `question_id`='" + sk_question_id + "';");
		return template.query("SELECT * FROM `mystry_dateanswers` WHERE `question_id`='" + sk_question_id + "' ;",
				new RowMapper<QuestionnaireBean>() {
					public QuestionnaireBean mapRow(ResultSet rs, int row) throws SQLException {
						QuestionnaireBean qBean = new QuestionnaireBean();
						qBean.setDate_code(rs.getString("date_code"));
						qBean.setDate_points(rs.getString("date_points"));
						qBean.setDate_response(rs.getString("date_response"));
						qBean.setDate_routing(rs.getString("date_routing"));
						qBean.setTime_code(rs.getString("time_code"));
						qBean.setTime_points(rs.getString("time_points"));
						qBean.setTime_response(rs.getString("time_response"));
						qBean.setTime_routing(rs.getString("time_routing"));
						return qBean;
					}
				});

	}

	public List<QuestionnaireBean> getquestionselectList(QuestionnaireBean qBean, String sk_question_id,
			String question_type) {
		System.out.println("SELECT * FROM `mst_questionare_selectoption`,mst_questionare WHERE `question_id`='"
				+ sk_question_id + "' and mst_questionare_selectoption.question_type='" + question_type
				+ "' and mst_questionare_selectoption.question_id = mst_questionare.sk_question_id and mst_questionare.question_status='active' and mst_questionare_selectoption.options_status='active';");
		return template.query("SELECT * FROM `mst_questionare_selectoption`,mst_questionare WHERE `question_id`='"
				+ sk_question_id + "' and mst_questionare_selectoption.question_type='" + question_type
				+ "' and mst_questionare_selectoption.question_id = mst_questionare.sk_question_id and mst_questionare.question_status='active' and mst_questionare_selectoption.options_status='active';",
				new RowMapper<QuestionnaireBean>() {
					public QuestionnaireBean mapRow(ResultSet rs, int row) throws SQLException {
						QuestionnaireBean qBean = new QuestionnaireBean();
						qBean.setAnswer(rs.getString("options"));
						qBean.setAnswer_points(rs.getString("option_points"));
						qBean.setAnswer_code(rs.getString("option_code"));
						qBean.setAnswer_response(rs.getString("option_response"));
						qBean.setAnswer_optiontype(rs.getString("option_optiontype"));
						qBean.setAnswer_comment(rs.getString("option_comment"));
						qBean.setSk_answer_id(rs.getString("sk_answer_id"));
						qBean.setRouting_type(rs.getString("option_routing_type"));
						return qBean;
					}
				});

	}

	public List<QuestionnaireBean> getsubquestionoptionsList(QuestionnaireBean qBean, String sk_question_id,
			String sub_question_id, String question_type) {
		System.out.println(" mainwith set of subquestion==  SELECT * FROM `mst_questionare_selectoption`,mst_questionare WHERE `question_id`='"
				+ sk_question_id + "' and subquestion_id='" + sub_question_id
				+ "' and mst_questionare_selectoption.question_type='" + question_type
				+ "' and mst_questionare_selectoption.question_id = mst_questionare.sk_question_id and mst_questionare.question_status='active' and mst_questionare_selectoption.options_status='active';");
		return template.query("SELECT * FROM `mst_questionare_selectoption`,mst_questionare WHERE `question_id`='"
				+ sk_question_id + "' and subquestion_id='" + sub_question_id
				+ "' and mst_questionare_selectoption.question_type='" + question_type
				+ "' and mst_questionare_selectoption.question_id = mst_questionare.sk_question_id and mst_questionare.question_status='active' and mst_questionare_selectoption.options_status='active';",
				new RowMapper<QuestionnaireBean>() {
					public QuestionnaireBean mapRow(ResultSet rs, int row) throws SQLException {
						QuestionnaireBean qBean = new QuestionnaireBean();
						qBean.setAnswer(rs.getString("options"));
						qBean.setAnswer_points(rs.getString("option_points"));
						qBean.setAnswer_code(rs.getString("option_code"));
						qBean.setAnswer_response(rs.getString("option_response"));
						qBean.setAnswer_optiontype(rs.getString("option_optiontype"));
						qBean.setAnswer_comment(rs.getString("option_comment"));
						qBean.setSk_answer_id(rs.getString("sk_answer_id"));
						qBean.setRouting_type(rs.getString("option_routing_type"));
					
						return qBean;
					}
				});

	}

	public List<QuestionnaireBean> subsetquestionsList(QuestionnaireBean qBean, String qid) {
		System.out.println("SELECT mst_questionare_subquestion.*,mst_formula_map.*,mst_questionare_selectoption.* FROM `mst_questionare_subquestion` LEFT JOIN mst_formula_map on mst_formula_map.subquestion_id=mst_questionare_subquestion.sk_subquestion_id LEFT JOIN mst_questionare_selectoption on mst_formula_map.subquestion_id=mst_questionare_selectoption.subquestion_id and mst_questionare_subquestion.question_id=mst_formula_map.question_id and mst_formula_map.question_id=mst_questionare_subquestion.question_id and mst_formula_map.option_id=mst_questionare_selectoption.sk_answer_id WHERE mst_questionare_subquestion.`question_id`='"+qid+"' AND `subquestion_status`='active' ;");
		return template.query("SELECT mst_questionare_subquestion.*,mst_formula_map.*,mst_questionare_selectoption.* FROM `mst_questionare_subquestion` LEFT JOIN mst_formula_map on mst_formula_map.subquestion_id=mst_questionare_subquestion.sk_subquestion_id LEFT JOIN mst_questionare_selectoption on mst_formula_map.subquestion_id=mst_questionare_selectoption.subquestion_id and mst_questionare_subquestion.question_id=mst_formula_map.question_id and mst_formula_map.question_id=mst_questionare_subquestion.question_id and mst_formula_map.option_id=mst_questionare_selectoption.sk_answer_id WHERE mst_questionare_subquestion.`question_id`='"+qid+"' AND `subquestion_status`='active';",
				new RowMapper<QuestionnaireBean>() {
					public QuestionnaireBean mapRow(ResultSet rs, int row) throws SQLException {
						QuestionnaireBean qBean = new QuestionnaireBean();
						qBean.setSk_subquestion_id(rs.getString("sk_subquestion_id"));
						qBean.setSubQuestion(rs.getString("subQuestion"));
						qBean.setSk_answer_id(rs.getString("sk_answer_id"));
						qBean.setAnswer(rs.getString("options"));
						qBean.setSelectedSubquestion_id(rs.getString("subquestion_id"));
						qBean.setSk_formula_id(rs.getString("sk_formula_map_id"));
						qBean.setSelectedanswerid(rs.getString("option_id"));
						qBean.setFormulaCount(rs.getString("formula_id"));
					
		return qBean;
						
					}
				});

	}

	public List<QuestionnaireBean> getOptionsbysubid(QuestionnaireBean qBean, String subid, String qid) {
			
						System.out.println("SELECT * FROM `mst_questionare_selectoption` WHERE `question_id`='"+qid+"' and `subquestion_id`='"+subid+"' AND `options_status`='active';");
		 return template.query("SELECT * FROM `mst_questionare_selectoption` WHERE `question_id`='"+qid+"' and `subquestion_id`='"+subid+"' AND `options_status`='active';",
				new RowMapper<QuestionnaireBean>() {
					public QuestionnaireBean mapRow(ResultSet rs, int row) throws SQLException {
					QuestionnaireBean qBean = new QuestionnaireBean();
						qBean.setAnswer(rs.getString("options"));
						qBean.setSk_answer_id(rs.getString("sk_answer_id"));
						return qBean;
						
					}
		});
	}

	public List<QuestionnaireBean> subsetquestionsList1(QuestionnaireBean qBean, String qid) {
		System.out.println("SELECT * FROM `mst_questionare_subquestion` WHERE `question_id`='"+qid+"' AND `subquestion_status`='active' ;");
		return template.query("SELECT * FROM `mst_questionare_subquestion` WHERE mst_questionare_subquestion.`question_id`='"+qid+"' AND `subquestion_status`='active';",
				new RowMapper<QuestionnaireBean>() {
					public QuestionnaireBean mapRow(ResultSet rs, int row) throws SQLException {
						QuestionnaireBean qBean = new QuestionnaireBean();
						qBean.setSk_subquestion_id(rs.getString("sk_subquestion_id"));
						qBean.setSubQuestion(rs.getString("subQuestion"));
						
						System.out.println("SELECT GROUP_CONCAT(mst_formula_map.formula_id) as formula_id,mst_formula_map.* FROM `mst_formula_map` WHERE `question_id`='"+qid+"' and subquestion_id='"+rs.getString("sk_subquestion_id")+"';");
						  template.query("SELECT * FROM `mst_formula_map` WHERE `question_id`='"+qid+"' and subquestion_id='"+rs.getString("sk_subquestion_id")+"';",
								new RowMapper<QuestionnaireBean>() {
									public QuestionnaireBean mapRow(ResultSet rs, int row) throws SQLException {
										qBean.setSk_formula_id(rs.getString("sk_formula_map_id"));
										qBean.setFormulaCount(rs.getString("formula_id"));
										
										
										
									
						return qBean;
										
									}
								});
					
		return qBean;
						
					}
				});

	}
	public DatabaseManagementBean addWeightage(DatabaseManagementBean dbBean,String user_id) {
		System.out.println("INSERT INTO mst_score_weightage(year,section_id weightage, created_by,status) VALUES ('"
				+dbBean.getYear()+ "','"+dbBean.getSection_id()+"','"+dbBean.getWeightage()+"','" + user_id + "','active');");
		template.execute("INSERT INTO mst_score_weightage(year,section_id, weightage, created_by,status) VALUES ('"+dbBean.getYear()+"','"+dbBean.getSection_id()+"','"+dbBean.getWeightage()+"','" +user_id +"','active');");
		return dbBean;
		
	}

	public DatabaseManagementBean getWeighageExistance(DatabaseManagementBean dbBean, String year,String section_id) {
		String count = "";
		String status = "";
		String sql = "SELECT count(*)as exist,ifnull(status,'no status') as weightage_status FROM mst_score_weightage WHERE year = '"
				+ year + "' and section_id='"+section_id+"' and status='active'";
		System.out.println(sql);
		List<Map<String, Object>> list = template.queryForList(sql);
		for (Map<String, Object> row : list) {
			count = row.get("exist").toString();
			status = row.get("weightage_status").toString();
		}
		int id_status = Integer.parseInt(count);

		if (id_status > 0) {
			System.out.println("exist");
			dbBean.setMessage("sectionexistforthatyear");
			dbBean.setStatus(status);
		} else {
			dbBean.setStatus(status);
			dbBean.setMessage("sectionnotexistforthatyear");
		}
		return dbBean;
	}
	
	
	


	public List<DatabaseManagementBean> getActiveWeightageList(DatabaseManagementBean dbBean) {
		System.out.println("SELECT * FROM `mst_score_weightage` WHERE `status`='active'");
		return template.query(" SELECT * FROM `mst_score_weightage` WHERE `status`='active';",
				new RowMapper<DatabaseManagementBean>() {
					public DatabaseManagementBean mapRow(ResultSet rs, int row) throws SQLException {
						DatabaseManagementBean dbBean = new DatabaseManagementBean();
						dbBean.setSk_id(rs.getString("sk_id"));
						dbBean.setYear(rs.getString("year"));
						dbBean.setWeightage(rs.getString("weightage"));
						System.out.println(
								"SELECT GROUP_CONCAT(mst_section.sk_section_id) as sk_section_id,GROUP_CONCAT(mst_section.section_name) as section_name  FROM `mst_section` WHERE `sk_section_id` in("
										+ rs.getString("section_id")+");");
						template.query(
								"SELECT GROUP_CONCAT(mst_section.sk_section_id) as sk_section_id,GROUP_CONCAT(mst_section.section_name) as section_name FROM `mst_section` WHERE `sk_section_id` in("
										+ rs.getString("section_id")+");",
								new RowMapper<DatabaseManagementBean>() {
									public DatabaseManagementBean mapRow(ResultSet rs, int row) throws SQLException {
										dbBean.setSk_section_id(rs.getString("sk_section_id"));
										dbBean.setSection_name(rs.getString("section_name"));
										return dbBean;
									}
								});

						return dbBean;
					}
				});
	}
	
	
	public List<DatabaseManagementBean> getInactiveWeightageList(DatabaseManagementBean dbBean) {
		System.out.println("SELECT * FROM `mst_score_weightage` WHERE `status`='inactive'");
		return template.query(" SELECT * FROM `mst_score_weightage` WHERE `status`='inactive';",
				new RowMapper<DatabaseManagementBean>() {
					public DatabaseManagementBean mapRow(ResultSet rs, int row) throws SQLException {
						DatabaseManagementBean dbBean = new DatabaseManagementBean();
						dbBean.setSk_id(rs.getString("sk_id"));
						dbBean.setYear(rs.getString("year"));
						dbBean.setWeightage(rs.getString("weightage"));
						System.out.println(
								"SELECT GROUP_CONCAT(mst_section.sk_section_id) as sk_section_id,GROUP_CONCAT(mst_section.section_name) as section_name  FROM `mst_section` WHERE `sk_section_id` in("
										+ rs.getString("section_id") + ");");
						template.query(
								"SELECT GROUP_CONCAT(mst_section.sk_section_id) as sk_section_id,GROUP_CONCAT(mst_section.section_name) as section_name FROM `mst_section` WHERE `sk_section_id` in("
										+ rs.getString("section_id") + ");",
								new RowMapper<DatabaseManagementBean>() {
									public DatabaseManagementBean mapRow(ResultSet rs, int row) throws SQLException {
										dbBean.setSk_section_id(rs.getString("sk_section_id"));
										dbBean.setSection_name(rs.getString("section_name"));
										return dbBean;
									}
								});

						return dbBean;
					}
				});
	}
	
	

	public DatabaseManagementBean getWeightageDetailsById(DatabaseManagementBean dbBean, String sk_id) {
		System.out.println(
				"SELECT * FROM `mst_score_weightage` WHERE `status`='active' and sk_id='" + sk_id + "';");
		return template.queryForObject(
				"SELECT * FROM `mst_score_weightage` WHERE `status`='active' and sk_id='" + sk_id + "';",
				new RowMapper<DatabaseManagementBean>() {
					public DatabaseManagementBean mapRow(ResultSet rs, int row) throws SQLException {
						dbBean.setSk_id(rs.getString("sk_id"));
						dbBean.setYear(rs.getString("year"));
						dbBean.setWeightage(rs.getString("weightage"));
						System.out.println(
								"SELECT GROUP_CONCAT(mst_section.sk_section_id) as sk_section_id,GROUP_CONCAT(mst_section.section_name) as section_name  FROM `mst_section` WHERE `sk_section_id` in("
										+ rs.getString("section_id") + ");");
						template.queryForObject(
								"SELECT GROUP_CONCAT(mst_section.sk_section_id) as sk_section_id,GROUP_CONCAT(mst_section.section_name) as section_name FROM `mst_section` WHERE `sk_section_id` in("
										+ rs.getString("section_id") + ");",
								new RowMapper<DatabaseManagementBean>() {
									public DatabaseManagementBean mapRow(ResultSet rs, int row) throws SQLException {
										dbBean.setSk_section_id(rs.getString("sk_section_id"));
										dbBean.setSection_name(rs.getString("section_name"));
										return dbBean;
									}
								});

						return dbBean;
					}
				});
	}
	
	
	
	
	
	public DatabaseManagementBean deleteWeightage(DatabaseManagementBean dbBean, String sk_id, String user_id) {

		System.out.println("UPDATE mst_score_weightage SET status='inactive',created_by='" + user_id+ "'  WHERE sk_id='" + sk_id + "'");
		template.execute("UPDATE mst_score_weightage SET status='inactive',created_by='" + user_id+ "' WHERE sk_id='" + sk_id + "'");
		return dbBean;
	}
	
	public DatabaseManagementBean restoreWeightage(DatabaseManagementBean dbBean, String sk_id, String user_id) {

		System.out.println("UPDATE mst_score_weightage SET status='active',created_by='" + user_id+ "'  WHERE sk_id='" + sk_id + "'");
		template.execute("UPDATE mst_score_weightage SET status='active',created_by='" + user_id+ "' WHERE sk_id='" + sk_id + "'");
		return dbBean;
	}

	public DatabaseManagementBean updateWeightage(DatabaseManagementBean dbBean, String user_id) {
		
		System.out.println("UPDATE mst_score_weightage SET year='"+dbBean.getYear()+"', section_id='"+dbBean.getSection_id()+"',weightage='"+dbBean.getWeightage()+"',created_by='" + user_id+ "'  WHERE sk_id='" +dbBean.getSk_id()+ "'");
		template.execute("UPDATE mst_score_weightage SET year='"+dbBean.getYear()+"', section_id='"+dbBean.getSection_id()+"',weightage='"+dbBean.getWeightage()+"',created_by='" + user_id+"'  WHERE sk_id='" +dbBean.getSk_id()+"'");
		return dbBean;
	}

	
}
