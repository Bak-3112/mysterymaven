package com.mystery.dao;

import java.net.InetAddress;
import java.net.UnknownHostException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.ResourceBundle;

import org.apache.commons.io.FilenameUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;
import org.springframework.web.multipart.MultipartFile;

import com.google.gson.Gson;
import com.mystery.api.AESCrypt;
import com.mystery.api.Encryption;
import com.mystery.api.SendEmailUsingGMailSMTP;
import com.mystery.beans.DatabaseManagementBean;
import com.mystery.beans.MysteryShoppingVisitsBean;
import com.mystery.beans.QuestionnaireBean;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class MysteryShoppingVisitsDao {

	@Autowired
	JdbcTemplate template;
	@Autowired
	HelperDao hDao;

	public void setTemplate(JdbcTemplate template) {
		this.template = template;
	}

	DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
	Date date = new Date();
	ResourceBundle resource = ResourceBundle.getBundle("resources/web");
	String url = resource.getString("dashboardURL");
	String emailfrom = resource.getString("emailfrom");
	String emailusername = resource.getString("emailusername");
	String emailpassword = resource.getString("emailpassword");

	public List<MysteryShoppingVisitsBean> getOutletsByBrandId(MysteryShoppingVisitsBean mvBean, String brand_id) {
		log.info("getOutletsByBrandId method" + new Gson().toJson(mvBean));
		log.info("getOutletsByBrandId method :"
				+ "SELECT mst_dealer.sk_dealer_id,mst_dealer.dealer_name, mst_dealer_outlet.*  FROM mst_dealer, `mst_dealer_outlet` WHERE mst_dealer.sk_dealer_id=mst_dealer_outlet.dealer_id and mst_dealer_outlet.brand_id='"
				+ brand_id + "' AND  mst_dealer.dealer_status='active' AND mst_dealer_outlet.outlet_status='active';");
		return template.query(
				"SELECT mst_dealer.sk_dealer_id,mst_dealer.dealer_name, mst_dealer_outlet.*  FROM mst_dealer, `mst_dealer_outlet` WHERE mst_dealer.sk_dealer_id=mst_dealer_outlet.dealer_id and mst_dealer_outlet.brand_id='"
						+ brand_id
						+ "' AND  mst_dealer.dealer_status='active' AND mst_dealer_outlet.outlet_status='active';;",
				new RowMapper<MysteryShoppingVisitsBean>() {
					@Override
					public MysteryShoppingVisitsBean mapRow(ResultSet rs, int row) throws SQLException {
						MysteryShoppingVisitsBean mvBean = new MysteryShoppingVisitsBean();
						mvBean.setOutlet_id(rs.getString("sk_outlet_id"));
						mvBean.setOutlet_name(rs.getString("outlet_name"));
						log.info("outlet name" + rs.getString("outlet_name"));
						mvBean.setOutlet_location_id(rs.getString("outlet_id"));
						mvBean.setDealer_id(rs.getString("sk_dealer_id"));
						mvBean.setDealer_name(rs.getString("dealer_name"));
						log.info("getOutletsByBrandId method return" + new Gson().toJson(mvBean));
						return mvBean;
					}
				});

	}

	public List<MysteryShoppingVisitsBean> getModelVariantById(MysteryShoppingVisitsBean mvBean, String brand_id) {
		log.info("getModelVariantById method" + new Gson().toJson(mvBean));
		log.info("getModelVariantById method :"
				+ "edit SELECT mst_brand.sk_brand_id,mst_brand.brand_name,mst_brand_model.sk_model_id,mst_brand_model.model_name,mst_brand_model_variant.sk_variant_id,mst_brand_model_variant.variant_name from  mst_brand  LEFT JOIN mst_brand_model on mst_brand_model.brand_id=mst_brand.sk_brand_id LEFT JOIN mst_brand_model_variant on mst_brand_model_variant.brand_id=mst_brand.sk_brand_id   and mst_brand_model_variant.model_id=mst_brand_model.sk_model_id  AND mst_brand.brand_status='active' and mst_brand_model.model_status='active' AND mst_brand_model_variant.variant_status='active' WHERE mst_brand.sk_brand_id='"
				+ brand_id + "';");
		return template.query(
				"SELECT mst_brand.sk_brand_id,mst_brand.brand_name,mst_brand_model.sk_model_id,mst_brand_model.model_name,mst_brand_model_variant.sk_variant_id,mst_brand_model_variant.variant_name from  mst_brand  LEFT JOIN mst_brand_model on mst_brand_model.brand_id=mst_brand.sk_brand_id LEFT JOIN mst_brand_model_variant on mst_brand_model_variant.brand_id=mst_brand.sk_brand_id   and mst_brand_model_variant.model_id=mst_brand_model.sk_model_id  AND mst_brand.brand_status='active' and mst_brand_model.model_status='active' AND mst_brand_model_variant.variant_status='active' WHERE mst_brand.sk_brand_id='"
						+ brand_id + "';",
				new RowMapper<MysteryShoppingVisitsBean>() {
					@Override
					public MysteryShoppingVisitsBean mapRow(ResultSet rs, int row) throws SQLException {
						MysteryShoppingVisitsBean mvBean = new MysteryShoppingVisitsBean();
						mvBean.setBrand_id(rs.getString("sk_brand_id"));
						mvBean.setBrand_name(rs.getString("brand_name"));
						mvBean.setBrand_model_id(rs.getString("sk_model_id"));
						mvBean.setModel_name(rs.getString("model_name"));
						mvBean.setVariant_id(rs.getString("sk_variant_id"));
						mvBean.setVariant_name(rs.getString("variant_name"));
						log.info("getModelVariantById method return" + new Gson().toJson(mvBean));
						return mvBean;
					}
				});

	}

	public MysteryShoppingVisitsBean getDealerByoutletId(String outlet_id, MysteryShoppingVisitsBean mvBean) {
		log.info("getDealerByoutletId method" + new Gson().toJson(mvBean));
		log.info("getDealerByoutletId method :"
				+ "SELECT mst_dealer.sk_dealer_id,mst_dealer.dealer_name ,mst_dealer_outlet.outlet_name FROM mst_dealer,mst_dealer_outlet WHERE mst_dealer.sk_dealer_id = mst_dealer_outlet.dealer_id and mst_dealer_outlet.sk_outlet_id='"
				+ outlet_id + "';");
		return template.queryForObject(
				"SELECT mst_dealer.sk_dealer_id,mst_dealer.dealer_name ,mst_dealer_outlet.outlet_name FROM mst_dealer,mst_dealer_outlet WHERE mst_dealer.sk_dealer_id = mst_dealer_outlet.dealer_id and mst_dealer_outlet.sk_outlet_id='"
						+ outlet_id + "';",
				new RowMapper<MysteryShoppingVisitsBean>() {
					@Override
					public MysteryShoppingVisitsBean mapRow(ResultSet rs, int row) throws SQLException {
						mvBean.setDealer_id(rs.getString("sk_dealer_id"));
						mvBean.setDealer_name(rs.getString("dealer_name"));
						mvBean.setOutlet_name(rs.getString("outlet_name"));
						log.info("getDealerByoutletId method return" + new Gson().toJson(mvBean));
						return mvBean;
					}
				});

	}

	public MysteryShoppingVisitsBean addscheduleMysteryShopper(MysteryShoppingVisitsBean mvBean, String user_id,
			String dealer_id) {
		log.info("addscheduleMysteryShopper method" + new Gson().toJson(mvBean));
		log.info("addscheduleMysteryShopper method Inserting :"
				+ "INSERT INTO  mst_shopper_details(name, shoppers_car_owned_brand, shoppers_car_owned,shoppers_car_yop,email,contact_number,age,gender,quarter,year,mode_of_contact,visit_date,brand_id,outlet_id,dealer_id,brand_model_variant_id,type,created_by,status) VALUES ('"
				+ mvBean.getName() + "','" + mvBean.getShoppers_car_owned_brand() + "','"
				+ mvBean.getShoppers_car_owned() + "','" + mvBean.getShoppers_car_yop() + "','" + mvBean.getEmail()
				+ "','" + mvBean.getContact_number() + "','" + mvBean.getAge() + "','" + mvBean.getGender() + "','"
				+ mvBean.getQuarter() + "','" + mvBean.getYear() + "','" + mvBean.getMode_of_contact() + "','"
				+ mvBean.getVisit_date() + "','" + mvBean.getBrand_id() + "','" + mvBean.getOutlet_id() + "','"
				+ mvBean.getDealer_id() + "','" + mvBean.getVariant_id() + "','" + mvBean.getType() + "','" + user_id
				+ "','active')");
		template.execute(
				"INSERT INTO  mst_shopper_details(name,shoppers_car_owned_brand,shoppers_car_owned,shoppers_car_yop,email,contact_number,age,gender,quarter,year,mode_of_contact,visit_date,brand_id,outlet_id,dealer_id,brand_model_variant_id,type,created_by,status) VALUES ('"
						+ mvBean.getName() + "', '" + mvBean.getShoppers_car_owned_brand() + "','"
						+ mvBean.getShoppers_car_owned() + "','" + mvBean.getShoppers_car_yop() + "','"
						+ mvBean.getEmail() + "','" + mvBean.getContact_number() + "','" + mvBean.getAge() + "','"
						+ mvBean.getGender() + "','" + mvBean.getQuarter() + "','" + mvBean.getYear() + "','"
						+ mvBean.getMode_of_contact() + "','" + mvBean.getVisit_date() + "','" + mvBean.getBrand_id()
						+ "','" + mvBean.getOutlet_id() + "', '" + mvBean.getDealer_id() + "','"
						+ mvBean.getVariant_id() + "','" + mvBean.getType() + "','" + user_id + "','active')");

		log.info("addscheduleMysteryShopper method getting max value"
				+ "SELECT max(sk_shopper_id) as shopper_id FROM mst_shopper_details");
		return template.queryForObject("SELECT max(sk_shopper_id) as shopper_id FROM mst_shopper_details",
				new RowMapper<MysteryShoppingVisitsBean>() {
					@Override
					public MysteryShoppingVisitsBean mapRow(ResultSet rs, int row) throws SQLException {
						mvBean.setSk_shopper_id(rs.getString("shopper_id"));
						log.info("addscheduleMysteryShopper method return" + new Gson().toJson(mvBean));
						return mvBean;
					}
				});

	}

	public MysteryShoppingVisitsBean getscheduleMysteryShopperById(String encrypted_shopper_id,
			MysteryShoppingVisitsBean mvBean) {
		log.info("getscheduleMysteryShopperById method" + new Gson().toJson(mvBean));
		String decrypted_shopperId = Encryption.decrypt(encrypted_shopper_id);
		/*
		 * try {
		 * 
		 * String shopper_id = encrypted_shopper_id; String decrypt_shopperId =
		 * shopper_id.replace("symbol", "/"); decrypted_shopperId =
		 * AESCrypt.decrypt(decrypt_shopperId); } catch (Exception e) { log.info("bug" +
		 * e.getMessage()); }
		 */

		log.info("========decrypted_shopperId==========" + decrypted_shopperId);
		log.info("========encrypted_shopper_id==========" + encrypted_shopper_id);
		log.info("getscheduleMysteryShopperById method :"
				+ "SELECT mst_shopper_details.*, mst_brand.brand_name,mst_brand_model.model_name,mst_dealer.dealer_name,mst_dealer_outlet.outlet_name,mst_dealer_outlet.address,mst_geo_city.city_name FROM mst_shopper_details,mst_brand,mst_brand_model,mst_dealer,mst_dealer_outlet ,mst_geo_city, mst_brand_model_variant WHERE mst_shopper_details.brand_id=mst_brand.sk_brand_id AND mst_shopper_details.dealer_id=mst_dealer.sk_dealer_id AND mst_brand_model_variant.sk_variant_id=mst_shopper_details.brand_model_variant_id AND mst_brand_model.sk_model_id=mst_brand_model_variant.model_id AND mst_shopper_details.outlet_id=mst_dealer_outlet.sk_outlet_id AND mst_geo_city.sk_city_id=mst_dealer_outlet.city AND mst_brand.brand_status='active' AND mst_brand_model.model_status='active' AND mst_dealer_outlet.outlet_status='active' AND mst_dealer.dealer_status='active' and mst_geo_city.city_status='active' AND mst_shopper_details.status='active' and mst_shopper_details.sk_shopper_id='"
				+ decrypted_shopperId + "';");
		return template.queryForObject(
				"SELECT mst_shopper_details.*, mst_brand.brand_name,mst_brand_model.model_name,mst_dealer.dealer_name,mst_dealer_outlet.outlet_name,mst_dealer_outlet.address,mst_geo_city.city_name FROM mst_shopper_details,mst_brand,mst_brand_model,mst_dealer,mst_dealer_outlet ,mst_geo_city, mst_brand_model_variant WHERE mst_shopper_details.brand_id=mst_brand.sk_brand_id AND mst_shopper_details.dealer_id=mst_dealer.sk_dealer_id AND mst_brand_model_variant.sk_variant_id=mst_shopper_details.brand_model_variant_id AND mst_brand_model.sk_model_id=mst_brand_model_variant.model_id AND mst_shopper_details.outlet_id=mst_dealer_outlet.sk_outlet_id AND mst_geo_city.sk_city_id=mst_dealer_outlet.city AND mst_brand.brand_status='active' AND mst_brand_model.model_status='active' AND mst_dealer_outlet.outlet_status='active' AND mst_dealer.dealer_status='active' and mst_geo_city.city_status='active' AND mst_shopper_details.status='active' and mst_shopper_details.sk_shopper_id='"
						+ decrypted_shopperId + "';",
				new RowMapper<MysteryShoppingVisitsBean>() {
					@Override
					public MysteryShoppingVisitsBean mapRow(ResultSet rs, int row) throws SQLException {
						mvBean.setSk_shopper_id(rs.getString("sk_shopper_id"));
						mvBean.setName(rs.getString("name"));
						mvBean.setShoppers_car_owned(rs.getString("shoppers_car_owned"));
						mvBean.setShoppers_car_owned_brand(rs.getString("shoppers_car_owned_brand"));
						mvBean.setShoppers_car_yop(rs.getString("shoppers_car_yop"));
						mvBean.setEmail(rs.getString("email"));
						mvBean.setContact_number(rs.getString("contact_number"));
						mvBean.setAge(rs.getString("age"));
						mvBean.setGender(rs.getString("gender"));
						mvBean.setQuarter(rs.getString("quarter"));
						mvBean.setYear(rs.getString("year"));
						mvBean.setMode_of_contact(rs.getString("mode_of_contact"));
						mvBean.setVisit_date(rs.getString("visit_date"));
						mvBean.setBrand_id(rs.getString("brand_id"));
						mvBean.setOutlet_id(rs.getString("outlet_id"));
						mvBean.setDealer_id(rs.getString("dealer_id"));
						mvBean.setVariant_id(rs.getString("brand_model_variant_id"));
						mvBean.setType(rs.getString("type"));
						mvBean.setStatus(rs.getString("status"));
						mvBean.setBrand_name(rs.getString("brand_name"));
						mvBean.setModel_name(rs.getString("model_name"));
						mvBean.setDealer_name(rs.getString("dealer_name"));
						mvBean.setOutlet_name(rs.getString("outlet_name"));
						mvBean.setAddress(rs.getString("address"));
						mvBean.setCity_name(rs.getString("city_name"));
						String email = rs.getString("email");
						log.info("encrypted shopper id" + encrypted_shopper_id);

						try {
							SendEmailUsingGMailSMTP mail = new SendEmailUsingGMailSMTP();

							if (mvBean.getType().equals("EY Employee")) {
								mail.Eymail(email, url, encrypted_shopper_id, emailfrom, emailusername, emailpassword,
										mvBean);
							} else if (mvBean.getType().equals("Friends and family")) {
								log.info("in else friends and family");
								mail.FandFemail(email, url, encrypted_shopper_id, emailfrom, emailusername,
										emailpassword, mvBean);
							}
						} catch (Exception e) {
							e.printStackTrace();
							log.info("Exception of getscheduleMysteryShopperById method" + e);
						}
						log.info("getscheduleMysteryShopperById method return" + new Gson().toJson(mvBean));
						return mvBean;
					}
				});
	}

	public List<MysteryShoppingVisitsBean> getMysScheduledList(MysteryShoppingVisitsBean mvBean) {
		log.info("getMysScheduledList method" + new Gson().toJson(mvBean));
		log.info("getMysScheduledList method :"
				+ "SELECT mst_dealer.dealer_name,mst_dealer_outlet.outlet_name, mst_brand.brand_name, mst_shopper_details.* FROM mst_dealer,mst_dealer_outlet,mst_brand,mst_shopper_details where mst_shopper_details.dealer_id=mst_dealer.sk_dealer_id AND mst_shopper_details.outlet_id=mst_dealer_outlet.sk_outlet_id AND mst_shopper_details.brand_id=mst_brand.sk_brand_id and mst_brand.brand_status='active' AND mst_dealer.dealer_status='active' AND mst_dealer_outlet.outlet_status='active' AND mst_shopper_details.status='active' and mst_shopper_details.visit_status='scheduled'");
		return template.query(
				"SELECT mst_dealer.dealer_name,mst_dealer_outlet.outlet_name, mst_brand.brand_name, mst_shopper_details.* FROM mst_dealer,mst_dealer_outlet,mst_brand,mst_shopper_details where mst_shopper_details.dealer_id=mst_dealer.sk_dealer_id AND mst_shopper_details.outlet_id=mst_dealer_outlet.sk_outlet_id AND mst_shopper_details.brand_id=mst_brand.sk_brand_id and mst_brand.brand_status='active' AND mst_dealer.dealer_status='active' AND mst_dealer_outlet.outlet_status='active' AND mst_shopper_details.status='active' and mst_shopper_details.visit_status='scheduled';",
				new RowMapper<MysteryShoppingVisitsBean>() {
					@Override
					public MysteryShoppingVisitsBean mapRow(ResultSet rs, int row) throws SQLException {
						MysteryShoppingVisitsBean mvBean = new MysteryShoppingVisitsBean();
						mvBean.setSk_shopper_id(rs.getString("sk_shopper_id"));
						mvBean.setDealer_name(rs.getString("dealer_name"));
						mvBean.setBrand_name(rs.getString("brand_name"));
						mvBean.setOutlet_name(rs.getString("outlet_name"));
						mvBean.setVisit_date(rs.getString("visit_date"));
						mvBean.setName(rs.getString("name"));
						mvBean.setEmail(rs.getString("email"));
						mvBean.setContact_number(rs.getString("contact_number"));
						mvBean.setShoppers_car_owned(rs.getString("shoppers_car_owned"));
						mvBean.setShoppers_car_yop(rs.getString("shoppers_car_yop"));
						mvBean.setAge(rs.getString("age"));
						mvBean.setGender(rs.getString("gender"));
						mvBean.setYear(rs.getString("year"));
						mvBean.setQuarter(rs.getString("quarter"));
						mvBean.setMode_of_contact(rs.getString("mode_of_contact"));
						log.info("getMysScheduledList method return" + new Gson().toJson(mvBean));
						return mvBean;
					}
				});
	}

	public MysteryShoppingVisitsBean getShopperDetailsById(MysteryShoppingVisitsBean mvBean, String sk_shopper_id) {
		log.info("getShopperDetailsById method" + new Gson().toJson(mvBean));
		log.info("getShopperDetailsById method :"
				+ "SELECT mst_shopper_details.*, mst_brand.brand_name,mst_brand_model.model_name,mst_dealer.dealer_name,mst_dealer_outlet.outlet_name,mst_brand_model_variant.variant_name ,mst_dealer_outlet.address,mst_geo_city.city_name  FROM mst_shopper_details,mst_brand,mst_brand_model,mst_dealer,mst_dealer_outlet ,mst_geo_city, mst_brand_model_variant WHERE mst_shopper_details.brand_id=mst_brand.sk_brand_id AND mst_shopper_details.dealer_id=mst_dealer.sk_dealer_id AND mst_brand_model_variant.sk_variant_id=mst_shopper_details.brand_model_variant_id AND mst_brand_model.sk_model_id=mst_brand_model_variant.model_id AND mst_shopper_details.outlet_id=mst_dealer_outlet.sk_outlet_id AND mst_geo_city.sk_city_id=mst_dealer_outlet.city AND mst_brand.brand_status='active' and mst_brand_model.model_status='active' AND mst_dealer.dealer_status='active' AND mst_dealer_outlet.outlet_status='active' AND mst_brand_model_variant.variant_status='active' AND mst_geo_city.city_status='active' AND mst_shopper_details.status='active' AND mst_shopper_details.sk_shopper_id='"
				+ sk_shopper_id + "' ");
		return template.queryForObject(
				"SELECT mst_shopper_details.*, mst_brand.brand_name,mst_brand_model.model_name,mst_dealer.dealer_name,mst_dealer_outlet.outlet_name,mst_brand_model_variant.variant_name ,mst_dealer_outlet.address,mst_geo_city.city_name  FROM mst_shopper_details,mst_brand,mst_brand_model,mst_dealer,mst_dealer_outlet ,mst_geo_city, mst_brand_model_variant WHERE mst_shopper_details.brand_id=mst_brand.sk_brand_id AND mst_shopper_details.dealer_id=mst_dealer.sk_dealer_id AND mst_brand_model_variant.sk_variant_id=mst_shopper_details.brand_model_variant_id AND mst_brand_model.sk_model_id=mst_brand_model_variant.model_id AND mst_shopper_details.outlet_id=mst_dealer_outlet.sk_outlet_id AND mst_geo_city.sk_city_id=mst_dealer_outlet.city AND mst_brand.brand_status='active' and mst_brand_model.model_status='active' AND mst_dealer.dealer_status='active' AND mst_dealer_outlet.outlet_status='active' AND mst_brand_model_variant.variant_status='active' AND mst_geo_city.city_status='active' AND mst_shopper_details.status='active' AND mst_shopper_details.sk_shopper_id='"
						+ sk_shopper_id + "' ",
				new RowMapper<MysteryShoppingVisitsBean>() {
					@Override
					public MysteryShoppingVisitsBean mapRow(ResultSet rs, int row) throws SQLException {
						mvBean.setSk_shopper_id(rs.getString("sk_shopper_id"));
						mvBean.setName(rs.getString("name"));
						mvBean.setShoppers_car_owned(rs.getString("shoppers_car_owned"));
						mvBean.setShoppers_car_owned_brand(rs.getString("shoppers_car_owned_brand"));
						mvBean.setShoppers_car_yop(rs.getString("shoppers_car_yop"));
						mvBean.setEmail(rs.getString("email"));
						mvBean.setContact_number(rs.getString("contact_number"));
						mvBean.setAge(rs.getString("age"));
						mvBean.setGender(rs.getString("gender"));
						mvBean.setQuarter(rs.getString("quarter"));
						mvBean.setYear(rs.getString("year"));
						mvBean.setMode_of_contact(rs.getString("mode_of_contact"));
						mvBean.setVisit_date(rs.getString("visit_date"));
						mvBean.setBrand_id(rs.getString("brand_id"));
						mvBean.setOutlet_id(rs.getString("outlet_id"));
						mvBean.setDealer_id(rs.getString("dealer_id"));
						mvBean.setVariant_id(rs.getString("brand_model_variant_id"));
						mvBean.setType(rs.getString("type"));
						mvBean.setStatus(rs.getString("status"));
						mvBean.setBrand_name(rs.getString("brand_name"));
						mvBean.setModel_name(rs.getString("model_name"));
						mvBean.setDealer_name(rs.getString("dealer_name"));
						mvBean.setOutlet_name(rs.getString("outlet_name"));
						mvBean.setVariant_name(rs.getString("variant_name"));
						log.info("getShopperDetailsById method return" + new Gson().toJson(mvBean));
						return mvBean;
					}
				});
	}

	public void updatescheduleMysteryShopper(MysteryShoppingVisitsBean mvBean, String user_id, String dealer_id) {
		log.info("updatescheduleMysteryShopper method" + new Gson().toJson(mvBean));

		log.info("updatescheduleMysteryShopper method :" + "UPDATE mst_shopper_details SET name='" + mvBean.getName()
				+ "',shoppers_car_owned_brand='" + mvBean.getShoppers_car_owned_brand() + "',shoppers_car_owned='"
				+ mvBean.getShoppers_car_owned() + "',shoppers_car_yop='" + mvBean.getShoppers_car_yop() + "',email='"
				+ mvBean.getEmail() + "', contact_number='" + mvBean.getContact_number() + "',age='" + mvBean.getAge()
				+ "',gender='" + mvBean.getGender() + "',quarter='" + mvBean.getQuarter() + "',year='"
				+ mvBean.getYear() + "',mode_of_contact='" + mvBean.getMode_of_contact() + "',visit_date='"
				+ mvBean.getVisit_date() + "',brand_id='" + mvBean.getBrand_id() + "',outlet_id='"
				+ mvBean.getOutlet_id() + "',dealer_id='" + dealer_id + "',brand_model_variant_id='"
				+ mvBean.getVariant_id() + "',type='" + mvBean.getType() + "',created_by='" + user_id
				+ "' where sk_shopper_id='" + mvBean.getSk_shopper_id() + "'");
		template.execute("UPDATE mst_shopper_details SET name='" + mvBean.getName() + "',shoppers_car_owned_brand='"
				+ mvBean.getShoppers_car_owned_brand() + "',shoppers_car_owned='" + mvBean.getShoppers_car_owned()
				+ "',shoppers_car_yop='" + mvBean.getShoppers_car_yop() + "',email='" + mvBean.getEmail()
				+ "', contact_number='" + mvBean.getContact_number() + "',age='" + mvBean.getAge() + "',gender='"
				+ mvBean.getGender() + "',quarter='" + mvBean.getQuarter() + "',year='" + mvBean.getYear()
				+ "',mode_of_contact='" + mvBean.getMode_of_contact() + "',visit_date='" + mvBean.getVisit_date()
				+ "',brand_id='" + mvBean.getBrand_id() + "',outlet_id='" + mvBean.getOutlet_id() + "',dealer_id='"
				+ dealer_id + "',	brand_model_variant_id='" + mvBean.getVariant_id() + "',type='" + mvBean.getType()
				+ "',created_by='" + user_id + "' where sk_shopper_id='" + mvBean.getSk_shopper_id() + "'");
	}

	public void deleteMysteryShopper(String shopperId, MysteryShoppingVisitsBean mvBean) {
		log.info("deleteMysteryShopper method" + new Gson().toJson(mvBean));
		log.info("deleteMysteryShopper method :"
				+ "UPDATE mst_shopper_details SET status='inactive' where sk_shopper_id='" + shopperId + "'");
		template.execute("UPDATE mst_shopper_details SET status='inactive' where sk_shopper_id='" + shopperId + "'");

	}

	public void addShopperDetails(MysteryShoppingVisitsBean mBean, String user_id) {
		log.info("addShopperDetails method" + new Gson().toJson(mBean));
		log.info("addShopperDetails method :" + "UPDATE `mst_shopper_details` SET `start_time`='"
				+ mBean.getStart_date() + "',`end_time`='" + mBean.getEnd_date() + "',`met_sc`='" + mBean.getMet_sc()
				+ "',`sc_name`='" + mBean.getSc_name() + "',`sc_designation`='" + mBean.getSc_designation()
				+ "',`sc_description`='" + mBean.getSc_description() + "',`shopper_link_name`='"
				+ mBean.getShopper_link_name() + "',`shopper_link_age`='" + mBean.getShopper_link_age()
				+ "',`shopper_link_email`='" + mBean.getShopper_link_email() + "',`shopper_link_contact`='"
				+ mBean.getShopper_link_contact() + "',`shopper_link_gender`='" + mBean.getShopper_link_gender()
				+ "',`shoppers_link_shoppersCarBrand`='" + mBean.getShoppers_link_shoppersCarBrand()
				+ "',`shoppers_link_shoppersCarModel`='" + mBean.getShoppers_link_shoppersCarModel()
				+ "',`shoppers_link_shoppersCarYop`='" + mBean.getShoppers_link_shoppersCarYop()
				+ "',`Shopper_link_productMet`='" + mBean.getShopper_link_productMet()
				+ "',`Shopper_link_productNameProvided`='" + mBean.getShopper_link_productNameProvided()
				+ "',`Shopper_link_productName`='" + mBean.getShopper_link_productName()
				+ "',`Shopper_link_productDesc`='" + mBean.getShopper_link_productDesc() + "',shopper_link_visit_date='"
				+ mBean.getShopper_link_visit_date() + "',`created_by`='" + user_id + "'  WHERE sk_shopper_id='"
				+ mBean.getSk_shopper_id() + "'");

		template.execute("UPDATE `mst_shopper_details` SET `start_time`='" + mBean.getStart_date() + "',`end_time`='"
				+ mBean.getEnd_date() + "',`met_sc`='" + mBean.getMet_sc() + "',`sc_name`='" + mBean.getSc_name()
				+ "',`sc_designation`='" + mBean.getSc_designation() + "',`sc_description`='"
				+ mBean.getSc_description() + "',`shopper_link_name`='" + mBean.getShopper_link_name()
				+ "',`shopper_link_age`='" + mBean.getShopper_link_age() + "',`shopper_link_email`='"
				+ mBean.getShopper_link_email() + "',`shopper_link_contact`='" + mBean.getShopper_link_contact()
				+ "',`shopper_link_gender`='" + mBean.getShopper_link_gender() + "',`shoppers_link_shoppersCarBrand`='"
				+ mBean.getShoppers_link_shoppersCarBrand() + "',`shoppers_link_shoppersCarModel`='"
				+ mBean.getShoppers_link_shoppersCarModel() + "',`shoppers_link_shoppersCarYop`='"
				+ mBean.getShoppers_link_shoppersCarYop() + "',`Shopper_link_productMet`='"
				+ mBean.getShopper_link_productMet() + "',`Shopper_link_productNameProvided`='"
				+ mBean.getShopper_link_productNameProvided() + "',`Shopper_link_productName`='"
				+ mBean.getShopper_link_productName() + "',`Shopper_link_productDesc`='"
				+ mBean.getShopper_link_productDesc() + "',shopper_link_visit_date='"
				+ mBean.getShopper_link_visit_date() + "',`created_by`='" + user_id + "'  WHERE sk_shopper_id='"
				+ mBean.getSk_shopper_id() + "'");
		saveMst_anwerData(mBean);

	}

	public List<MysteryShoppingVisitsBean> getMysShopperDetails(MysteryShoppingVisitsBean mBean) {

		log.info("getMysShopperDetails method" + new Gson().toJson(mBean));
		String sql = "SELECT COUNT(shopper_id) as exist from mst_msm_result WHERE shopper_id='"
				+ mBean.getSk_shopper_id() + "'";
		log.info("getMysShopperDetails method" + sql);
		String count = "";
		String query = "";
		List<Map<String, Object>> list = template.queryForList(sql);
		for (Map<String, Object> row : list) {
			count = row.get("exist").toString();
		}
		int id_status = Integer.parseInt(count);
		if (id_status > 0) {
			query = "SELECT mst_msm_result.*,mst_brand.brand_name,mst_brand_model.model_name,mst_dealer_outlet.outlet_name,mst_dealer.dealer_name,mst_geo_states.state_name,mst_geo_city.city_name,mst_dealer_outlet.address,mst_dealer_outlet.outlet_id as location_outlet_id,mst_dealer_outlet.pincode FROM  mst_msm_result, mst_brand,mst_brand_model,mst_dealer_outlet,mst_dealer,mst_geo_states,mst_geo_city,mst_brand_model_variant WHERE mst_msm_result.visit_brand_id=mst_brand.sk_brand_id and mst_dealer_outlet.sk_outlet_id= mst_msm_result.outlet_id and mst_dealer.sk_dealer_id= mst_msm_result.dealership_id and mst_brand_model_variant.sk_variant_id= mst_msm_result.brand_varient_id and mst_brand.brand_status='active' and mst_brand_model.model_status='active' and mst_dealer_outlet.outlet_status='active' and mst_dealer.dealer_status='active' and mst_dealer_outlet.state=mst_geo_states.sk_state_id and mst_dealer_outlet.city=mst_geo_city.sk_city_id and mst_brand_model_variant.model_id=mst_brand_model.sk_model_id AND shopper_id='"
					+ mBean.getSk_shopper_id() + "'";
			log.info("getMysShopperDetails method status>0 :" + query);
			return template.query(query, new RowMapper<MysteryShoppingVisitsBean>() {
				@Override
				public MysteryShoppingVisitsBean mapRow(ResultSet rs, int row) throws SQLException {
					MysteryShoppingVisitsBean mBean = new MysteryShoppingVisitsBean();
					mBean.setSk_shopper_id(rs.getString("shopper_id"));
					mBean.setName(rs.getString("shopper_link_name"));
					mBean.setAge(rs.getString("shopper_link_age"));
					mBean.setEmail(rs.getString("shopper_link_email"));
					mBean.setContact_number(rs.getString("shopper_link_contact"));
					mBean.setShoppers_car_yop(rs.getString("shoppers_link_shoppersCarYop"));
					mBean.setShoppers_car_owned(rs.getString("shoppers_link_shoppersCarBrand"));
					mBean.setShoppers_link_shoppersCarModel(rs.getString("shoppers_link_shoppersCarModel"));
					mBean.setGender(rs.getString("shopper_link_gender"));
					mBean.setQuarter(rs.getString("quarter"));
					mBean.setYear(rs.getString("year"));
					mBean.setMode_of_contact(rs.getString("mode_of_contact"));
					mBean.setVisit_date(rs.getString("shopper_link_visit_date"));
					mBean.setBrand_id(rs.getString("visit_brand_id"));
					mBean.setBrand_name(rs.getString("visit_brand_name"));
					mBean.setStatus(rs.getString("visit_status"));
					mBean.setStart_date(rs.getString("start_time"));
					mBean.setEnd_date(rs.getString("end_time"));
					// mBean.setMet_sc(rs.getString("met_sc"));
					// mBean.setSc_name(rs.getString("sc_name"));
					// mBean.setSc_designation(rs.getString("sc_designation"));
					// mBean.setSc_description(rs.getString("sc_description"));
					// mBean.setType(mBean.getBrand_name() + " " + rs.getString("type") + " " +
					// rs.getString("model_name"));
					mBean.setOutlet_name(rs.getString("outlet_name"));
					mBean.setDealer_name(rs.getString("dealer_name"));
					mBean.setState_name(rs.getString("state_name"));
					mBean.setCity_name(rs.getString("city_name"));
					mBean.setOutlet_id(rs.getString("location_outlet_id"));
					mBean.setPincode(rs.getString("pincode"));
					mBean.setStart_time(rs.getString("start_time"));
					mBean.setEnd_time(rs.getString("end_time"));
					mBean.setShopper_link_productMet(rs.getString("Shopper_link_productMet"));
					mBean.setShopper_link_productNameProvided(rs.getString("Shopper_link_productNameProvided"));
					mBean.setShopper_link_productName(rs.getString("Shopper_link_productName"));
					mBean.setShopper_link_productDesc(rs.getString("Shopper_link_productDesc"));
					log.info("getMysShopperDetails method return" + new Gson().toJson(mBean));
					return mBean;
				}
			});

		} else {
			query = "SELECT mst_shopper_details.*,mst_brand.brand_name,mst_brand_model.model_name,mst_dealer_outlet.outlet_name,mst_dealer.dealer_name,mst_geo_states.state_name,mst_geo_city.city_name,mst_dealer_outlet.address,mst_dealer_outlet.outlet_id as location_outlet_id,mst_dealer_outlet.pincode FROM `mst_shopper_details`, mst_brand,mst_brand_model,mst_dealer_outlet,mst_dealer,mst_geo_states,mst_geo_city,mst_brand_model_variant WHERE mst_shopper_details.brand_id=mst_brand.sk_brand_id and mst_dealer_outlet.sk_outlet_id=mst_shopper_details.outlet_id and mst_dealer.sk_dealer_id=mst_shopper_details.dealer_id and mst_brand_model_variant.sk_variant_id=mst_shopper_details.brand_model_variant_id and mst_brand.brand_status='active' and mst_brand_model.model_status='active' and mst_dealer_outlet.outlet_status='active' and mst_dealer.dealer_status='active' and mst_dealer_outlet.state=mst_geo_states.sk_state_id and mst_dealer_outlet.city=mst_geo_city.sk_city_id and mst_brand_model_variant.model_id=mst_brand_model.sk_model_id AND sk_shopper_id='"
					+ mBean.getSk_shopper_id() + "'";
			log.info("getMysShopperDetails method status!>0 else part :" + query);
			return template.query(query, new RowMapper<MysteryShoppingVisitsBean>() {

				@Override
				public MysteryShoppingVisitsBean mapRow(ResultSet rs, int row) throws SQLException {
					MysteryShoppingVisitsBean mBean = new MysteryShoppingVisitsBean();
					mBean.setSk_shopper_id(rs.getString("sk_shopper_id"));
					mBean.setName(rs.getString("name"));
					mBean.setAge(rs.getString("age"));
					mBean.setEmail(rs.getString("email"));
					mBean.setContact_number(rs.getString("contact_number"));

					if (rs.getString("shoppers_link_shoppersCarBrand") != null) {
						mBean.setShoppers_car_owned(rs.getString("shoppers_link_shoppersCarBrand"));
					} else {
						mBean.setShoppers_car_owned(rs.getString("shoppers_car_owned_brand"));
					}
					if (rs.getString("shoppers_link_shoppersCarModel") != null) {
						mBean.setShoppers_link_shoppersCarModel(rs.getString("shoppers_link_shoppersCarModel"));
					} else {
						mBean.setShoppers_link_shoppersCarModel(rs.getString("shoppers_car_owned"));
					}
					if (rs.getString("shoppers_link_shoppersCarYop") != null) {
						mBean.setShoppers_car_yop(rs.getString("shoppers_link_shoppersCarYop"));
					} else {
						mBean.setShoppers_car_yop(rs.getString("shoppers_car_yop"));
					}

					mBean.setGender(rs.getString("gender"));
					mBean.setQuarter(rs.getString("quarter"));
					mBean.setYear(rs.getString("year"));
					mBean.setMode_of_contact(rs.getString("mode_of_contact"));
					mBean.setVisit_date(rs.getString("visit_date"));
					mBean.setBrand_id(rs.getString("brand_id"));
					mBean.setBrand_name(rs.getString("brand_name"));
					mBean.setStatus(rs.getString("visit_status"));
					mBean.setStart_date(rs.getString("start_time"));
					mBean.setEnd_date(rs.getString("end_time"));
					mBean.setMet_sc(rs.getString("met_sc"));
					mBean.setSc_name(rs.getString("sc_name"));
					mBean.setSc_designation(rs.getString("sc_designation"));
					mBean.setSc_description(rs.getString("sc_description"));
					mBean.setType(
							mBean.getBrand_name() + " " + rs.getString("type") + " " + rs.getString("model_name"));
					mBean.setOutlet_name(rs.getString("outlet_name"));
					mBean.setDealer_name(rs.getString("dealer_name"));
					mBean.setState_name(rs.getString("state_name"));
					mBean.setCity_name(rs.getString("city_name"));
					mBean.setOutlet_id(rs.getString("location_outlet_id"));
					mBean.setPincode(rs.getString("pincode"));
					mBean.setStart_time(rs.getString("start_time"));
					mBean.setEnd_time(rs.getString("end_time"));
					log.info("getMysShopperDetails method return" + new Gson().toJson(mBean));
					return mBean;
				}
			});

		}

	}

	public List<MysteryShoppingVisitsBean> getMysteryShoppersUniqueList(MysteryShoppingVisitsBean mBean,
			final String status) {
		log.info("getMysteryShoppersUniqueList method" + new Gson().toJson(mBean));
		log.info("getMysteryShoppersUniqueList method :"
				+ "select count(contact_number) as mcount,mst_dealer_outlet.outlet_name,mst_shopper_details.* from mst_dealer_outlet, mst_shopper_details WHERE mst_shopper_details.outlet_id=mst_dealer_outlet.sk_outlet_id AND visit_status='scheduled' group by contact_number ;");
		return template.query(
				"select count(contact_number) as mcount,mst_dealer_outlet.outlet_name,mst_shopper_details.* from mst_dealer_outlet, mst_shopper_details WHERE mst_shopper_details.outlet_id=mst_dealer_outlet.sk_outlet_id AND visit_status='scheduled' group by contact_number ;",
				new RowMapper<MysteryShoppingVisitsBean>() {
					public MysteryShoppingVisitsBean mapRow(ResultSet rs, int row) throws SQLException {
						MysteryShoppingVisitsBean mvBean = new MysteryShoppingVisitsBean();
						mvBean.setSk_shopper_id(rs.getString("sk_shopper_id"));
						mvBean.setName(rs.getString("name"));
						mvBean.setAge(rs.getString("age"));
						mvBean.setEmail(rs.getString("email"));
						mvBean.setContact_number(rs.getString("contact_number"));
						mvBean.setGender(rs.getString("gender"));
						mvBean.setQuarter(rs.getString("quarter"));
						mvBean.setYear(rs.getString("year"));
						mvBean.setMode_of_contact(rs.getString("mode_of_contact"));
						mvBean.setVisit_date(rs.getString("visit_date"));
						mvBean.setBrand_id(rs.getString("brand_id"));
						mvBean.setType(rs.getString("type"));
						mvBean.setOutlet_id(rs.getString("outlet_id"));
						mvBean.setOutlet_name(rs.getString("outlet_name"));
						mvBean.setCount(rs.getString("mcount"));
						log.info("getMysteryShoppersUniqueList method return" + new Gson().toJson(mvBean));
						return mvBean;
					}
				});
	}

	public List<MysteryShoppingVisitsBean> getShopperVisitedDetailsById(MysteryShoppingVisitsBean mBean,
			final String mobile) {
		log.info("getShopperVisitedDetailsById method" + new Gson().toJson(mBean));
		log.info("getShopperVisitedDetailsById method :"
				+ "SELECT mst_dealer_outlet.outlet_id,mst_dealer_outlet.outlet_name,mst_dealer.dealer_name,mst_shopper_details.* FROM mst_dealer_outlet,mst_shopper_details,mst_dealer where mst_shopper_details.outlet_id=mst_dealer_outlet.sk_outlet_id AND mst_shopper_details.dealer_id=mst_dealer.sk_dealer_id And mst_shopper_details.contact_number='"
				+ mobile + "';");
		return template.query(
				"SELECT mst_dealer_outlet.outlet_id,mst_dealer_outlet.outlet_name,mst_dealer.dealer_name,mst_shopper_details.* FROM mst_dealer_outlet,mst_shopper_details,mst_dealer where mst_shopper_details.outlet_id=mst_dealer_outlet.sk_outlet_id AND mst_shopper_details.dealer_id=mst_dealer.sk_dealer_id And mst_shopper_details.contact_number='"
						+ mobile + "';",
				new RowMapper<MysteryShoppingVisitsBean>() {
					public MysteryShoppingVisitsBean mapRow(ResultSet rs, int row) throws SQLException {
						MysteryShoppingVisitsBean mvBean = new MysteryShoppingVisitsBean();
						mvBean.setSk_shopper_id(rs.getString("sk_shopper_id"));
						mvBean.setContact_number(rs.getString("contact_number"));
						mvBean.setQuarter(rs.getString("quarter"));
						mvBean.setYear(rs.getString("year"));
						mvBean.setMode_of_contact(rs.getString("mode_of_contact"));
						mvBean.setVisit_date(rs.getString("visit_date"));
						mvBean.setOutlet_id(rs.getString("outlet_id"));
						mvBean.setOutlet_name(rs.getString("outlet_name"));
						mvBean.setDealer_name(rs.getString("dealer_name"));
						log.info("getShopperVisitedDetailsById method return" + new Gson().toJson(mvBean));
						return mvBean;
					}
				});
	}

	public List<MysteryShoppingVisitsBean> getMysVisitedList(MysteryShoppingVisitsBean mvBean) {
		log.info("getMysVisitedList method" + new Gson().toJson(mvBean));
		log.info("getMysVisitedList method :"
				+ "SELECT mst_dealer.dealer_name,mst_dealer_outlet.outlet_name,mst_brand.brand_name, mst_shopper_details.* FROM mst_dealer,mst_dealer_outlet,mst_brand,mst_shopper_details where mst_shopper_details.dealer_id=mst_dealer.sk_dealer_id AND mst_shopper_details.outlet_id=mst_dealer_outlet.sk_outlet_id  AND mst_shopper_details.brand_id=mst_brand.sk_brand_id AND mst_brand.brand_status='active' AND mst_dealer.dealer_status='active' AND mst_dealer_outlet.outlet_status='active' AND mst_shopper_details.status='active' and mst_shopper_details.visit_status='visited';");
		return template.query(
				"SELECT mst_dealer.dealer_name,mst_dealer_outlet.outlet_name,mst_brand.brand_name, mst_shopper_details.* FROM mst_dealer,mst_dealer_outlet,mst_brand,mst_shopper_details where mst_shopper_details.dealer_id=mst_dealer.sk_dealer_id AND mst_shopper_details.outlet_id=mst_dealer_outlet.sk_outlet_id  AND mst_shopper_details.brand_id=mst_brand.sk_brand_id AND mst_brand.brand_status='active' AND mst_dealer.dealer_status='active' AND mst_dealer_outlet.outlet_status='active' AND mst_shopper_details.status='active' and mst_shopper_details.visit_status='visited';",
				new RowMapper<MysteryShoppingVisitsBean>() {
					@Override
					public MysteryShoppingVisitsBean mapRow(ResultSet rs, int row) throws SQLException {
						MysteryShoppingVisitsBean mvBean = new MysteryShoppingVisitsBean();

						mvBean.setSk_shopper_id(rs.getString("sk_shopper_id"));
						mvBean.setDealer_name(rs.getString("dealer_name"));
						mvBean.setOutlet_name(rs.getString("outlet_name"));
						mvBean.setBrand_name(rs.getString("brand_name"));
						mvBean.setVisit_date(rs.getString("visit_date"));
						mvBean.setName(rs.getString("name"));
						mvBean.setEmail(rs.getString("email"));
						mvBean.setContact_number(rs.getString("contact_number"));
						mvBean.setShoppers_car_owned(rs.getString("shoppers_car_owned"));
						mvBean.setShoppers_car_yop(rs.getString("shoppers_car_yop"));
						mvBean.setAge(rs.getString("age"));
						mvBean.setGender(rs.getString("gender"));
						mvBean.setYear(rs.getString("year"));
						mvBean.setQuarter(rs.getString("quarter"));
						mvBean.setMode_of_contact(rs.getString("mode_of_contact"));
						String sk_shopper_id = rs.getString("sk_shopper_id");
						MysteryShoppingVisitsBean mvBean1 = getShopperIdQuestionCount(mvBean, sk_shopper_id);
						mvBean.setShopper_question_count(mvBean1.getShopper_question_count());
						log.info("getMysVisitedList method return" + new Gson().toJson(mvBean));
						return mvBean;
					}
				});

	}

	public MysteryShoppingVisitsBean getShopperIdQuestionCount(MysteryShoppingVisitsBean mvBean, String sk_shopper_id) {
		log.info("getShopperIdQuestionCount method" + new Gson().toJson(mvBean));
		log.info("getShopperIdQuestionCount method :"
				+ "select COUNT( DISTINCT question_id) as question_count from mys_txn_answers where mys_txn_answers.shopper_id='"
				+ sk_shopper_id + "' AND mys_txn_answers.status='active'");
		return template.queryForObject(
				"select COUNT( DISTINCT question_id) as question_count from mys_txn_answers where mys_txn_answers.shopper_id='"
						+ sk_shopper_id + "' AND mys_txn_answers.status='active';",
				new RowMapper<MysteryShoppingVisitsBean>() {

					public MysteryShoppingVisitsBean mapRow(ResultSet rs, int row) throws SQLException {
						mvBean.setShopper_question_count(rs.getString("question_count"));
						log.info("getShopperIdQuestionCount method return" + new Gson().toJson(mvBean));
						return mvBean;
					}
				});

	}

	public void updateToVisit(MysteryShoppingVisitsBean mvBean, String visit_status, String sk_shopper_id,
			String user_id) {
		log.info("updateToVisit method" + new Gson().toJson(mvBean));
		log.info("updateToVisit method :" + "UPDATE mst_shopper_details SET visit_status='" + visit_status
				+ "',created_by='" + user_id + "' where sk_shopper_id='" + sk_shopper_id + "'");
		template.execute("UPDATE mst_shopper_details SET visit_status='" + visit_status + "',created_by='" + user_id
				+ "'  where sk_shopper_id='" + sk_shopper_id + "'");

	}

	public void updatetoSchedule(MysteryShoppingVisitsBean mvBean, String visit_status, String sk_shopper_id,
			String user_id) {
		log.info("updatetoSchedule method" + new Gson().toJson(mvBean));
		log.info("updatetoSchedule method :" + "UPDATE mst_shopper_details SET visit_status='" + visit_status
				+ "',created_by='" + user_id + "' where sk_shopper_id='" + sk_shopper_id + "'");
		template.execute("UPDATE mst_shopper_details SET visit_status='" + visit_status + "',created_by='" + user_id
				+ "'  where sk_shopper_id='" + sk_shopper_id + "'");

	}

	public void updatetoComplete(MysteryShoppingVisitsBean mvBean, String visit_status, String sk_shopper_id,
			String user_id) {
		log.info("updatetoComplete method" + new Gson().toJson(mvBean));
		log.info("updatetoComplete method :" + "UPDATE mst_shopper_details SET visit_status='" + visit_status
				+ "',created_by='" + user_id + "' where sk_shopper_id='" + sk_shopper_id + "'");
		template.execute("UPDATE mst_shopper_details SET visit_status='" + visit_status + "',created_by='" + user_id
				+ "'  where sk_shopper_id='" + sk_shopper_id + "'");

	}

	public MysteryShoppingVisitsBean getMysShopperLinkDetailsById(MysteryShoppingVisitsBean mvBean,
			String sk_shopper_id) {

		log.info("getMysShopperLinkDetailsById method" + new Gson().toJson(mvBean));
		log.info("getMysShopperLinkDetailsById method :"
				+ "SELECT mst_shopper_details.*, mst_brand.brand_name,mst_brand_model.model_name,mst_dealer.dealer_name,mst_brand_model_variant.variant_name,mst_dealer_outlet.outlet_name,mst_dealer_outlet.outlet_id as outlet_location_id,mst_dealer_outlet.pincode,mst_dealer_outlet.address,mst_geo_city.city_name,mst_geo_states.state_name FROM mst_shopper_details,mst_brand,mst_brand_model,mst_dealer,mst_dealer_outlet ,mst_geo_city,mst_geo_states, mst_brand_model_variant WHERE mst_shopper_details.brand_id=mst_brand.sk_brand_id AND mst_shopper_details.dealer_id=mst_dealer.sk_dealer_id AND mst_brand_model_variant.sk_variant_id=mst_shopper_details.brand_model_variant_id AND mst_brand_model.sk_model_id=mst_brand_model_variant.model_id AND mst_shopper_details.outlet_id=mst_dealer_outlet.sk_outlet_id AND mst_geo_city.sk_city_id=mst_dealer_outlet.city AND mst_geo_states.sk_state_id=mst_dealer_outlet.state AND mst_brand.brand_status='active' AND mst_brand_model.model_status='active'AND mst_dealer.dealer_status='active'and mst_dealer_outlet.outlet_status='active' AND mst_brand_model_variant.variant_status='active' AND mst_geo_city.city_status='active' AND mst_geo_states.state_status='active' AND  mst_shopper_details.status='active' AND mst_shopper_details.sk_shopper_id='"
				+ sk_shopper_id + "'");
		return template.queryForObject(
				"SELECT mst_shopper_details.*, mst_brand.brand_name,mst_brand_model.model_name,mst_dealer.dealer_name,mst_brand_model_variant.variant_name,mst_dealer_outlet.outlet_name,mst_dealer_outlet.outlet_id as outlet_location_id,mst_dealer_outlet.pincode,mst_dealer_outlet.address,mst_geo_city.city_name,mst_geo_states.state_name FROM mst_shopper_details,mst_brand,mst_brand_model,mst_dealer,mst_dealer_outlet ,mst_geo_city,mst_geo_states, mst_brand_model_variant WHERE mst_shopper_details.brand_id=mst_brand.sk_brand_id AND mst_shopper_details.dealer_id=mst_dealer.sk_dealer_id AND mst_brand_model_variant.sk_variant_id=mst_shopper_details.brand_model_variant_id AND mst_brand_model.sk_model_id=mst_brand_model_variant.model_id AND mst_shopper_details.outlet_id=mst_dealer_outlet.sk_outlet_id AND mst_geo_city.sk_city_id=mst_dealer_outlet.city AND mst_geo_states.sk_state_id=mst_dealer_outlet.state AND mst_brand.brand_status='active' AND mst_brand_model.model_status='active'AND mst_dealer.dealer_status='active'and mst_dealer_outlet.outlet_status='active' AND mst_brand_model_variant.variant_status='active' AND mst_geo_city.city_status='active' AND mst_geo_states.state_status='active' AND  mst_shopper_details.status='active' AND mst_shopper_details.sk_shopper_id='"
						+ sk_shopper_id + "'",
				new RowMapper<MysteryShoppingVisitsBean>() {
					@Override
					public MysteryShoppingVisitsBean mapRow(ResultSet rs, int row) throws SQLException {
						mvBean.setSk_shopper_id(rs.getString("sk_shopper_id"));
						mvBean.setName(rs.getString("name"));
						mvBean.setShoppers_car_owned(rs.getString("shoppers_car_owned"));
						mvBean.setShoppers_car_owned_brand(rs.getString("shoppers_car_owned_brand"));
						mvBean.setShoppers_car_yop(rs.getString("shoppers_car_yop"));
						mvBean.setEmail(rs.getString("email"));
						mvBean.setContact_number(rs.getString("contact_number"));
						mvBean.setAge(rs.getString("age"));
						mvBean.setGender(rs.getString("gender"));
						mvBean.setQuarter(rs.getString("quarter"));
						mvBean.setYear(rs.getString("year"));
						mvBean.setMode_of_contact(rs.getString("mode_of_contact"));
						mvBean.setVisit_date(rs.getString("visit_date"));
						mvBean.setBrand_id(rs.getString("brand_id"));
						mvBean.setOutlet_id(rs.getString("outlet_id"));
						mvBean.setDealer_id(rs.getString("dealer_id"));
						mvBean.setVariant_id(rs.getString("brand_model_variant_id"));
						mvBean.setBrand_name(rs.getString("brand_name"));
						mvBean.setModel_name(rs.getString("model_name"));
						mvBean.setDealer_name(rs.getString("dealer_name"));
						mvBean.setOutlet_name(rs.getString("outlet_name"));
						mvBean.setVariant_name(rs.getString("variant_name"));
						mvBean.setOutlet_location_id(rs.getString("outlet_location_id"));
						mvBean.setPincode(rs.getString("pincode"));
						mvBean.setState_name(rs.getString("state_name"));
						mvBean.setCity_name(rs.getString("city_name"));
						mvBean.setStart_time(rs.getString("start_time"));
						mvBean.setEnd_time(rs.getString("end_time"));
						mvBean.setMet_sc(rs.getString("met_sc"));
						mvBean.setSc_name(rs.getString("sc_name"));
						mvBean.setSc_designation(rs.getString("sc_designation"));
						mvBean.setSc_description(rs.getString("sc_description"));
						mvBean.setShopper_link_name(rs.getString("shopper_link_name"));
						mvBean.setShopper_link_age(rs.getString("shopper_link_age"));
						mvBean.setShopper_link_email(rs.getString("shopper_link_email"));
						mvBean.setShopper_link_contact(rs.getString("shopper_link_contact"));
						mvBean.setShopper_link_gender(rs.getString("shopper_link_gender"));
						mvBean.setShoppers_link_shoppersCarBrand(rs.getString("shoppers_link_shoppersCarBrand"));
						mvBean.setShoppers_link_shoppersCarModel(rs.getString("shoppers_link_shoppersCarModel"));
						mvBean.setShoppers_link_shoppersCarYop(rs.getString("shoppers_link_shoppersCarYop"));
						mvBean.setShopper_link_productMet(rs.getString("Shopper_link_productMet"));
						mvBean.setShopper_link_productNameProvided(rs.getString("Shopper_link_productNameProvided"));
						mvBean.setShopper_link_productName(rs.getString("Shopper_link_productName"));
						mvBean.setShopper_link_productDesc(rs.getString("Shopper_link_productDesc"));
						mvBean.setShopper_link_visit_date(rs.getString("shopper_link_visit_date"));
						mvBean.setType(rs.getString("type"));
						mvBean.setStatus(rs.getString("status"));
						mvBean.setVisit_status(rs.getString("visit_status"));
						log.info("getMysShopperLinkDetailsById method return" + new Gson().toJson(mvBean));
						return mvBean;
					}
				});
	}

	public void updateMysteryShopperLinkDetails(MysteryShoppingVisitsBean mvBean, String user_id, String dealer_id) {
		log.info("updateMysteryShopperLinkDetails method" + new Gson().toJson(mvBean));
		log.info("updateMysteryShopperLinkDetails method :" + "UPDATE mst_shopper_details SET name='" + mvBean.getName()
				+ "',shoppers_car_owned_brand='" + mvBean.getShoppers_car_owned_brand() + "',shoppers_car_owned='"
				+ mvBean.getShoppers_car_owned() + "',shoppers_car_yop='" + mvBean.getShoppers_car_yop() + "',email='"
				+ mvBean.getEmail() + "', contact_number='" + mvBean.getContact_number() + "',age='" + mvBean.getAge()
				+ "',gender='" + mvBean.getGender() + "',quarter='" + mvBean.getQuarter() + "',year='"
				+ mvBean.getYear() + "',mode_of_contact='" + mvBean.getMode_of_contact() + "',visit_date='"
				+ mvBean.getVisit_date() + "',brand_id='" + mvBean.getBrand_id() + "',outlet_id='"
				+ mvBean.getOutlet_id() + "',dealer_id='" + dealer_id + "',brand_model_variant_id='"
				+ mvBean.getVariant_id() + "',start_time='" + mvBean.getStart_time() + "',end_time='"
				+ mvBean.getEnd_time() + "',met_sc='" + mvBean.getMet_sc() + "',sc_name='" + mvBean.getSc_name()
				+ "',sc_designation='" + mvBean.getSc_designation() + "',sc_description='" + mvBean.getSc_description()
				+ "',shopper_link_name='" + mvBean.getShopper_link_name() + "',shopper_link_age='"
				+ mvBean.getShopper_link_age() + "',shopper_link_email='" + mvBean.getShopper_link_email()
				+ "',shopper_link_contact='" + mvBean.getShopper_link_contact() + "',shopper_link_gender='"
				+ mvBean.getShopper_link_gender() + "',shoppers_link_shoppersCarBrand='"
				+ mvBean.getShoppers_link_shoppersCarBrand() + "',shoppers_link_shoppersCarModel='"
				+ mvBean.getShoppers_link_shoppersCarModel() + "',shoppers_link_shoppersCarYop='"
				+ mvBean.getShoppers_link_shoppersCarYop() + "',Shopper_link_productMet='"
				+ mvBean.getShopper_link_productMet() + "',Shopper_link_productNameProvided='"
				+ mvBean.getShopper_link_productNameProvided() + "',Shopper_link_productName='"
				+ mvBean.getShopper_link_productName() + "',Shopper_link_productDesc='"
				+ mvBean.getShopper_link_productDesc() + "',shopper_link_visit_date='"
				+ mvBean.getShopper_link_visit_date() + "', type='" + mvBean.getType() + "',created_by='" + user_id
				+ "' where sk_shopper_id='" + mvBean.getSk_shopper_id() + "'");
		template.execute("UPDATE mst_shopper_details SET name='" + mvBean.getName() + "',shoppers_car_owned_brand='"
				+ mvBean.getShoppers_car_owned_brand() + "',shoppers_car_owned='" + mvBean.getShoppers_car_owned()
				+ "',shoppers_car_yop='" + mvBean.getShoppers_car_yop() + "',email='" + mvBean.getEmail()
				+ "', contact_number='" + mvBean.getContact_number() + "',age='" + mvBean.getAge() + "',gender='"
				+ mvBean.getGender() + "',quarter='" + mvBean.getQuarter() + "',year='" + mvBean.getYear()
				+ "',mode_of_contact='" + mvBean.getMode_of_contact() + "',visit_date='" + mvBean.getVisit_date()
				+ "',brand_id='" + mvBean.getBrand_id() + "',outlet_id='" + mvBean.getOutlet_id() + "',dealer_id='"
				+ dealer_id + "',brand_model_variant_id='" + mvBean.getVariant_id() + "',start_time='"
				+ mvBean.getStart_time() + "',end_time='" + mvBean.getEnd_time() + "',met_sc='" + mvBean.getMet_sc()
				+ "',sc_name='" + mvBean.getSc_name() + "',sc_designation='" + mvBean.getSc_designation()
				+ "',sc_description='" + mvBean.getSc_description() + "',shopper_link_name='"
				+ mvBean.getShopper_link_name() + "',shopper_link_age='" + mvBean.getShopper_link_age()
				+ "',shopper_link_email='" + mvBean.getShopper_link_email() + "',shopper_link_contact='"
				+ mvBean.getShopper_link_contact() + "',shopper_link_gender='" + mvBean.getShopper_link_gender()
				+ "',shoppers_link_shoppersCarBrand='" + mvBean.getShoppers_link_shoppersCarBrand()
				+ "',shoppers_link_shoppersCarModel='" + mvBean.getShoppers_link_shoppersCarModel()
				+ "',shoppers_link_shoppersCarYop='" + mvBean.getShoppers_link_shoppersCarYop()
				+ "',Shopper_link_productMet='" + mvBean.getShopper_link_productMet()
				+ "',Shopper_link_productNameProvided='" + mvBean.getShopper_link_productNameProvided()
				+ "',Shopper_link_productName='" + mvBean.getShopper_link_productName() + "',Shopper_link_productDesc='"
				+ mvBean.getShopper_link_productDesc() + "',shopper_link_visit_date='"
				+ mvBean.getShopper_link_visit_date() + "', type='" + mvBean.getType() + "',created_by='" + user_id
				+ "' where sk_shopper_id='" + mvBean.getSk_shopper_id() + "'");
		String brand_id = mvBean.getBrand_id();
		String outlet_id = mvBean.getOutlet_id();
		String dealer_id1 = mvBean.getDealer_id();
		String variant_id = mvBean.getVariant_id();
		MysteryShoppingVisitsBean brandDetails = getBrandNameById(mvBean, brand_id);
		String brand_name = brandDetails.getBrand_name();

		MysteryShoppingVisitsBean outletDetails = getOutletDetailsById(mvBean, outlet_id);
		MysteryShoppingVisitsBean dealerDetails = getDealerDetailsById(mvBean, dealer_id1);
		MysteryShoppingVisitsBean variantDetails = getVariantDetailsById(mvBean, variant_id);
		String model_id = variantDetails.getModel_id();
		MysteryShoppingVisitsBean modelDetails = getModelDetailsById(mvBean, model_id);
		InetAddress ip = null;
		try {
			ip = InetAddress.getLocalHost();
		} catch (UnknownHostException e) {
			e.printStackTrace();
		}

		String count = "";
		String status = "";
		String sql = "SELECT COUNT(*) AS exist,ifnull(status,'no status') as user_status from mst_msm_result where shopper_id='"
				+ mvBean.getSk_shopper_id() + "' AND status='active'";
		log.info("updateMysteryShopperLinkDetails method count :" + sql);
		List<Map<String, Object>> list = template.queryForList(sql);
		for (Map<String, Object> row : list) {
			count = row.get("exist").toString();
			status = row.get("user_status").toString();
		}
		log.info("status" + count);
		if (count.equals("1")) {
			log.info("updateMysteryShopperLinkDetails method count==1 :"
					+ "UPDATE  mst_msm_result SET mode_of_contact='" + mvBean.getMode_of_contact() + "',quarter='"
					+ mvBean.getQuarter() + "', year='" + mvBean.getYear() + "',visit_brand_id='" + brand_id
					+ "',visit_brand_name='" + brand_name + "',visit_brand_model_id='" + modelDetails.getModel_id()
					+ "',visit_brand_model_name='" + modelDetails.getModel_name() + "',brand_varient_id='"
					+ variantDetails.getVariant_id() + "',brand_varient_name='" + variantDetails.getVariant_name()
					+ "',dealership_id='" + dealerDetails.getDealer_id() + "',dealership_name='"
					+ dealerDetails.getDealer_name() + "',outlet_id='" + outletDetails.getOutlet_id()
					+ "',outlet_name='" + outletDetails.getOutlet_name() + "',location_id='"
					+ outletDetails.getOutlet_location_id() + "',outlet_address='" + outletDetails.getAddress()
					+ "',outlet_pincode='" + outletDetails.getPincode() + "',outlet_region_id='"
					+ outletDetails.getRegion_id() + "',outlet_region_name='" + outletDetails.getRegion_name()
					+ "',outlet_city_id='" + outletDetails.getCity_id() + "',outlet_city_name='"
					+ outletDetails.getCity_name() + "',city_tier='" + outletDetails.getTier() + "',city_metro='"
					+ outletDetails.getMetro() + "',salesperson_name='" + mvBean.getSc_name() + "',salesperson_desc='"
					+ mvBean.getSc_description() + "',scheduled_date='" + mvBean.getVisit_date()
					+ "',shopper_link_name='" + mvBean.getShopper_link_name() + "',shopper_link_age='"
					+ mvBean.getShopper_link_age() + "',shopper_link_email='" + mvBean.getShopper_link_email()
					+ "',shopper_link_contact='" + mvBean.getShopper_link_contact() + "',shopper_link_gender='"
					+ mvBean.getShopper_link_gender() + "',shoppers_link_shoppersCarBrand='"
					+ mvBean.getShoppers_link_shoppersCarBrand() + "',shoppers_link_shoppersCarModel='"
					+ mvBean.getShoppers_link_shoppersCarModel() + "',shoppers_link_shoppersCarYop='"
					+ mvBean.getShoppers_car_yop() + "',Shopper_link_productMet='" + mvBean.getShopper_link_productMet()
					+ "',Shopper_link_productNameProvided='" + mvBean.getShopper_link_productNameProvided()
					+ "',Shopper_link_productDesc='" + mvBean.getShopper_link_productDesc()
					+ "',shopper_link_visit_date='" + mvBean.getShopper_link_visit_date() + "',start_time='"
					+ mvBean.getStart_time() + "',end_time='" + mvBean.getEnd_time() + "',visit_status='"
					+ mvBean.getVisit_status() + "',created_by='" + ip + "',created_on='" + dateFormat.format(date)
					+ "',status='active' where shopper_id='" + mvBean.getSk_shopper_id() + "'");
			template.execute("UPDATE  mst_msm_result SET mode_of_contact='" + mvBean.getMode_of_contact()
					+ "',quarter='" + mvBean.getQuarter() + "', year='" + mvBean.getYear() + "',visit_brand_id='"
					+ brand_id + "',visit_brand_name='" + brand_name + "',visit_brand_model_id='"
					+ modelDetails.getModel_id() + "',visit_brand_model_name='" + modelDetails.getModel_name()
					+ "',brand_varient_id='" + variantDetails.getVariant_id() + "',brand_varient_name='"
					+ variantDetails.getVariant_name() + "',dealership_id='" + dealerDetails.getDealer_id()
					+ "',dealership_name='" + dealerDetails.getDealer_name() + "',outlet_id='"
					+ outletDetails.getOutlet_id() + "',outlet_name='" + outletDetails.getOutlet_name()
					+ "',location_id='" + outletDetails.getOutlet_location_id() + "',outlet_address='"
					+ outletDetails.getAddress() + "',outlet_pincode='" + outletDetails.getPincode()
					+ "',outlet_region_id='" + outletDetails.getRegion_id() + "',outlet_region_name='"
					+ outletDetails.getRegion_name() + "',outlet_city_id='" + outletDetails.getCity_id()
					+ "',outlet_city_name='" + outletDetails.getCity_name() + "',city_tier='" + outletDetails.getTier()
					+ "',city_metro='" + outletDetails.getMetro() + "',salesperson_name='" + mvBean.getSc_name()
					+ "',salesperson_desc='" + mvBean.getSc_description() + "',scheduled_date='"
					+ mvBean.getVisit_date() + "',shopper_link_name='" + mvBean.getShopper_link_name()
					+ "',shopper_link_age='" + mvBean.getShopper_link_age() + "',shopper_link_email='"
					+ mvBean.getShopper_link_email() + "',shopper_link_contact='" + mvBean.getShopper_link_contact()
					+ "',shopper_link_gender='" + mvBean.getShopper_link_gender() + "',shoppers_link_shoppersCarBrand='"
					+ mvBean.getShoppers_link_shoppersCarBrand() + "',shoppers_link_shoppersCarModel='"
					+ mvBean.getShoppers_link_shoppersCarModel() + "',shoppers_link_shoppersCarYop='"
					+ mvBean.getShoppers_car_yop() + "',Shopper_link_productMet='" + mvBean.getShopper_link_productMet()
					+ "',Shopper_link_productNameProvided='" + mvBean.getShopper_link_productNameProvided()
					+ "',Shopper_link_productDesc='" + mvBean.getShopper_link_productDesc()
					+ "',shopper_link_visit_date='" + mvBean.getShopper_link_visit_date() + "',start_time='"
					+ mvBean.getStart_time() + "',end_time='" + mvBean.getEnd_time() + "',visit_status='"
					+ mvBean.getVisit_status() + "',created_by='" + ip + "',created_on='" + dateFormat.format(date)
					+ "',status='active' where shopper_id='" + mvBean.getSk_shopper_id() + "'");
		} else {
			log.info("updateMysteryShopperLinkDetails method count!=1 :"
					+ "INSERT INTO `mst_msm_result`(`shopper_id`, `mode_of_contact`, `quarter`, `year`, `visit_brand_id`, `visit_brand_name`, `visit_brand_model_id`, `visit_brand_model_name`, `brand_varient_id`, `brand_varient_name`, `dealership_id`, `dealership_name`, `outlet_id`, `outlet_name`, `location_id`, `outlet_address`, `outlet_pincode`, `outlet_region_id`, `outlet_region_name`, `outlet_city_id`, `outlet_city_name`, `city_tier`, `city_metro`, `salesperson_name`, `salesperson_desc`, `scheduled_date`, `shopper_link_name`, `shopper_link_age`, `shopper_link_email`, `shopper_link_contact`, `shopper_link_gender`, `shoppers_link_shoppersCarBrand`, `shoppers_link_shoppersCarModel`, `shoppers_link_shoppersCarYop`, `Shopper_link_productMet`, `Shopper_link_productNameProvided`, `Shopper_link_productName`, `Shopper_link_productDesc`, `shopper_link_visit_date`, `start_time`, `end_time`, `visit_status`, `created_by`, `created_on`, `status`) VALUES ('"
					+ mvBean.getSk_shopper_id() + "','" + mvBean.getMode_of_contact() + "','" + mvBean.getQuarter()
					+ "','" + mvBean.getYear() + "','" + brand_id + "','" + brand_name + "','"
					+ modelDetails.getModel_id() + "','" + modelDetails.getModel_name() + "','"
					+ variantDetails.getVariant_id() + "','" + variantDetails.getVariant_name() + "','"
					+ dealerDetails.getDealer_id() + "','" + dealerDetails.getDealer_name() + "','"
					+ outletDetails.getOutlet_id() + "','" + outletDetails.getOutlet_name() + "','"
					+ outletDetails.getOutlet_location_id() + "','" + outletDetails.getAddress() + "','"
					+ outletDetails.getPincode() + "','" + outletDetails.getRegion_id() + "','"
					+ outletDetails.getRegion_name() + "','" + outletDetails.getCity_id() + "','"
					+ outletDetails.getCity_name() + "','" + outletDetails.getTier() + "','" + outletDetails.getMetro()
					+ "','" + mvBean.getSc_name() + "','" + mvBean.getSc_description() + "','" + mvBean.getVisit_date()
					+ "','" + mvBean.getShopper_link_name() + "','" + mvBean.getShopper_link_age() + "','"
					+ mvBean.getShopper_link_email() + "','" + mvBean.getShopper_link_contact() + "','"
					+ mvBean.getShopper_link_gender() + "','" + mvBean.getShoppers_link_shoppersCarBrand() + "','"
					+ mvBean.getShoppers_link_shoppersCarModel() + "','" + mvBean.getShoppers_car_yop() + "','"
					+ mvBean.getShopper_link_productMet() + "','" + mvBean.getShopper_link_productNameProvided() + "','"
					+ mvBean.getShopper_link_productName() + "','" + mvBean.getShopper_link_productDesc() + "','"
					+ mvBean.getShopper_link_visit_date() + "','" + mvBean.getStart_time() + "','"
					+ mvBean.getEnd_time() + "','" + mvBean.getVisit_status() + "','" + ip + "','"
					+ dateFormat.format(date) + "','active')");
			template.execute(
					"INSERT INTO `mst_msm_result`(`shopper_id`, `mode_of_contact`, `quarter`, `year`, `visit_brand_id`, `visit_brand_name`, `visit_brand_model_id`, `visit_brand_model_name`, `brand_varient_id`, `brand_varient_name`, `dealership_id`, `dealership_name`, `outlet_id`, `outlet_name`, `location_id`, `outlet_address`, `outlet_pincode`, `outlet_region_id`, `outlet_region_name`, `outlet_city_id`, `outlet_city_name`, `city_tier`, `city_metro`, `salesperson_name`, `salesperson_desc`, `scheduled_date`, `shopper_link_name`, `shopper_link_age`, `shopper_link_email`, `shopper_link_contact`, `shopper_link_gender`, `shoppers_link_shoppersCarBrand`, `shoppers_link_shoppersCarModel`, `shoppers_link_shoppersCarYop`, `Shopper_link_productMet`, `Shopper_link_productNameProvided`, `Shopper_link_productName`, `Shopper_link_productDesc`, `shopper_link_visit_date`, `start_time`, `end_time`, `visit_status`, `created_by`, `created_on`, `status`) VALUES ('"
							+ mvBean.getSk_shopper_id() + "','" + mvBean.getMode_of_contact() + "','"
							+ mvBean.getQuarter() + "','" + mvBean.getYear() + "','" + brand_id + "','" + brand_name
							+ "','" + modelDetails.getModel_id() + "','" + modelDetails.getModel_name() + "','"
							+ variantDetails.getVariant_id() + "','" + variantDetails.getVariant_name() + "','"
							+ dealerDetails.getDealer_id() + "','" + dealerDetails.getDealer_name() + "','"
							+ outletDetails.getOutlet_id() + "','" + outletDetails.getOutlet_name() + "','"
							+ outletDetails.getOutlet_location_id() + "','" + outletDetails.getAddress() + "','"
							+ outletDetails.getPincode() + "','" + outletDetails.getRegion_id() + "','"
							+ outletDetails.getRegion_name() + "','" + outletDetails.getCity_id() + "','"
							+ outletDetails.getCity_name() + "','" + outletDetails.getTier() + "','"
							+ outletDetails.getMetro() + "','" + mvBean.getSc_name() + "','"
							+ mvBean.getSc_description() + "','" + mvBean.getVisit_date() + "','"
							+ mvBean.getShopper_link_name() + "','" + mvBean.getShopper_link_age() + "','"
							+ mvBean.getShopper_link_email() + "','" + mvBean.getShopper_link_contact() + "','"
							+ mvBean.getShopper_link_gender() + "','" + mvBean.getShoppers_link_shoppersCarBrand()
							+ "','" + mvBean.getShoppers_link_shoppersCarModel() + "','" + mvBean.getShoppers_car_yop()
							+ "','" + mvBean.getShopper_link_productMet() + "','"
							+ mvBean.getShopper_link_productNameProvided() + "','"
							+ mvBean.getShopper_link_productName() + "','" + mvBean.getShopper_link_productDesc()
							+ "','" + mvBean.getShopper_link_visit_date() + "','" + mvBean.getStart_time() + "','"
							+ mvBean.getEnd_time() + "','" + mvBean.getVisit_status() + "','" + ip + "','"
							+ dateFormat.format(date) + "','active')");
		}
	}

	public MysteryShoppingVisitsBean getBrandNameById(MysteryShoppingVisitsBean mvBean, String brand_id) {
		log.info("getBrandNameById method" + new Gson().toJson(mvBean));
		log.info("getBrandNameById method :" + "SELECT * FROM mst_brand where sk_brand_id='" + brand_id
				+ "' and  brand_status='active';");
		return template.queryForObject(
				"SELECT * FROM `mst_brand`where sk_brand_id='" + brand_id + "' and  brand_status='active' ;",
				new RowMapper<MysteryShoppingVisitsBean>() {
					public MysteryShoppingVisitsBean mapRow(ResultSet rs, int row) throws SQLException {
						mvBean.setBrand_id(rs.getString("sk_brand_id"));
						mvBean.setBrand_name(rs.getString("brand_name"));
						log.info("getBrandNameById method return" + new Gson().toJson(mvBean));

						return mvBean;
					}
				});
	}

	public MysteryShoppingVisitsBean getOutletDetailsById(MysteryShoppingVisitsBean mvBean, String outlet_id) {
		log.info("getOutletDetailsById method" + new Gson().toJson(mvBean));
		log.info("getOutletDetailsById method :"
				+ "SELECT mst_dealer_outlet.*,  mst_geo_region.region_name, mst_geo_city.city_name,mst_geo_city.tier,mst_geo_city.metro FROM mst_dealer_outlet,mst_geo_region, mst_geo_city  WHERE mst_dealer_outlet.region_id=mst_geo_region.sk_region_id AND mst_dealer_outlet.city=mst_geo_city.sk_city_id AND mst_geo_region.region_status='active'AND mst_geo_city.city_status='active' AND mst_dealer_outlet.outlet_status='active'AND mst_dealer_outlet.sk_outlet_id='"
				+ outlet_id + "';");
		return template.queryForObject(
				"SELECT mst_dealer_outlet.*,  mst_geo_region.region_name, mst_geo_city.city_name,mst_geo_city.tier,mst_geo_city.metro FROM mst_dealer_outlet,mst_geo_region, mst_geo_city  WHERE mst_dealer_outlet.region_id=mst_geo_region.sk_region_id AND mst_dealer_outlet.city=mst_geo_city.sk_city_id AND mst_geo_region.region_status='active'AND mst_geo_city.city_status='active' AND mst_dealer_outlet.outlet_status='active'AND mst_dealer_outlet.sk_outlet_id='"
						+ outlet_id + "';",
				new RowMapper<MysteryShoppingVisitsBean>() {
					public MysteryShoppingVisitsBean mapRow(ResultSet rs, int row) throws SQLException {
						mvBean.setOutlet_id(rs.getString("sk_outlet_id"));
						mvBean.setOutlet_name(rs.getString("outlet_name"));
						mvBean.setOutlet_location_id(rs.getString("outlet_id"));
						mvBean.setBrand_id(rs.getString("brand_id"));
						mvBean.setRegion_id(rs.getString("region_id"));
						mvBean.setRegion_name(rs.getString("region_name"));
						mvBean.setDealer_id(rs.getString("dealer_id"));
						mvBean.setState_id(rs.getString("state"));
						mvBean.setCity_id(rs.getString("city"));
						mvBean.setCity_name(rs.getString("city_name"));
						mvBean.setTier(rs.getString("tier"));
						mvBean.setMetro(rs.getString("metro"));
						mvBean.setContact_person(rs.getString("contact_person"));
						mvBean.setAddress(rs.getString("address"));
						mvBean.setPincode(rs.getString("pincode"));
						log.info("getOutletDetailsById method return" + new Gson().toJson(mvBean));
						return mvBean;
					}
				});
	}

	public MysteryShoppingVisitsBean getDealerDetailsById(MysteryShoppingVisitsBean mvBean, String dealer_id1) {
		log.info("getDealerDetailsById method" + new Gson().toJson(mvBean));
		log.info("getDealerDetailsById method :" + "SELECT * FROM `mst_dealer` WHERE `sk_dealer_id`='" + dealer_id1
				+ "' AND dealer_status='active';");
		return template.queryForObject(
				"SELECT * FROM `mst_dealer` WHERE `sk_dealer_id`='" + dealer_id1 + "' AND dealer_status='active';",
				new RowMapper<MysteryShoppingVisitsBean>() {
					public MysteryShoppingVisitsBean mapRow(ResultSet rs, int row) throws SQLException {
						mvBean.setDealer_id(rs.getString("sk_dealer_id"));
						mvBean.setDealer_name(rs.getString("dealer_name"));

						log.info("getDealerDetailsById method return" + new Gson().toJson(mvBean));
						return mvBean;
					}
				});
	}

	public MysteryShoppingVisitsBean getVariantDetailsById(MysteryShoppingVisitsBean mvBean, String variant_id) {
		log.info("getVariantDetailsById method" + new Gson().toJson(mvBean));
		log.info("getVariantDetailsById method :" + "SELECT * FROM `mst_brand_model_variant` WHERE sk_variant_id='"
				+ variant_id + "' AND variant_status='active';");
		return template.queryForObject("SELECT * FROM `mst_brand_model_variant` WHERE sk_variant_id='" + variant_id
				+ "' AND variant_status='active' ;", new RowMapper<MysteryShoppingVisitsBean>() {
					public MysteryShoppingVisitsBean mapRow(ResultSet rs, int row) throws SQLException {
						mvBean.setVariant_id(rs.getString("sk_variant_id"));
						mvBean.setBrand_id(rs.getString("brand_id"));
						mvBean.setModel_id(rs.getString("model_id"));
						mvBean.setVariant_name(rs.getString("variant_name"));
						log.info("getVariantDetailsById method return" + new Gson().toJson(mvBean));
						return mvBean;
					}
				});
	}

	public MysteryShoppingVisitsBean getModelDetailsById(MysteryShoppingVisitsBean mvBean, String model_id) {
		log.info("getModelDetailsById method" + new Gson().toJson(mvBean));
		log.info("getModelDetailsById method :" + "SELECT * FROM `mst_brand_model` WHERE `sk_model_id`='" + model_id
				+ "' AND model_status='active';");
		return template.queryForObject(
				"SELECT * FROM `mst_brand_model` WHERE `sk_model_id`='" + model_id + "' AND model_status='active' ;",
				new RowMapper<MysteryShoppingVisitsBean>() {
					public MysteryShoppingVisitsBean mapRow(ResultSet rs, int row) throws SQLException {
						mvBean.setModel_id(rs.getString("sk_model_id"));
						mvBean.setBrand_id(rs.getString("brand_id"));
						mvBean.setModel_name(rs.getString("model_name"));

						log.info("getModelDetailsById method return" + new Gson().toJson(mvBean));
						return mvBean;
					}
				});
	}

	public List<MysteryShoppingVisitsBean> allquestionsList(MysteryShoppingVisitsBean mBean, String brand_id,
			String modeofcontact, String limit, String decrypted_shooper_id, String year_question_applied) {
		log.info("allquestionsList method" + new Gson().toJson(mBean));
		String sql = "";
		if (modeofcontact.contentEquals("Online Sales Channel")) {
			sql = "SELECT mys_txn_answers.mandatory_optional_status,mys_txn_answers.main_ques_comment,mys_txn_answers.date,mys_txn_answers.dateandtime,mys_txn_answers.paragraph,mst_questionare.* ,mst_section.section_name,mst_subsection.subsection_name,GROUP_CONCAT(DISTINCT mst_questionare_subquestion.sk_subquestion_id) as subquestionid,GROUP_CONCAT(DISTINCT mst_questionare_subquestion.sk_subquestion_id ,'-', mst_questionare_subquestion.subquestion SEPARATOR '#')as subquestion,GROUP_CONCAT(DISTINCT mst_questionare_subquestion.subanswer_type) as subanswertype,GROUP_CONCAT(DISTINCT mst_questionare_selectoption.subquestion_id,'-', mst_questionare_selectoption.sk_answer_id) as optionid,GROUP_CONCAT(distinct mst_questionare_selectoption.subquestion_id,'-', mst_questionare_selectoption.options SEPARATOR '#') as options,GROUP_CONCAT(DISTINCT mst_questionare_selectoption.option_comment SEPARATOR '#') as optioncomment FROM `mst_questionare` LEFT JOIN mst_section ON mst_section.sk_section_id=mst_questionare.section_id LEFT JOIN mst_subsection on mst_subsection.sk_subsection_id=mst_questionare.subsection_id LEFT JOIN mst_questionare_subquestion on mst_questionare_subquestion.question_id=mst_questionare.sk_question_id LEFT JOIN mst_questionare_selectoption on mst_questionare_selectoption.subquestion_id=mst_questionare_subquestion.sk_subquestion_id and  mst_questionare_selectoption.question_id=mst_questionare.sk_question_id left JOIN mys_txn_answers on mys_txn_answers.question_id=mst_questionare.sk_question_id and mys_txn_answers.shopper_id='"
					+ mBean.getSk_shopper_id() + "'  WHERE `brand_id`='" + brand_id + "' and   mode_of_contact='"
					+ modeofcontact + "' and  year_applied='" + year_question_applied
					+ "' and question_status='active'   GROUP BY sk_question_id  ORDER by mst_questionare.standard_number_sequence  limit "
					+ limit + ", 1;";
		} else {
			sql = "SELECT mys_txn_answers.mandatory_optional_status,mys_txn_answers.main_ques_comment,mys_txn_answers.date,mys_txn_answers.dateandtime,mys_txn_answers.paragraph,mst_questionare.* ,mst_section.section_name,mst_subsection.subsection_name,GROUP_CONCAT(DISTINCT mst_questionare_subquestion.sk_subquestion_id) as subquestionid,GROUP_CONCAT(DISTINCT mst_questionare_subquestion.sk_subquestion_id ,'-', mst_questionare_subquestion.subquestion SEPARATOR '#')as subquestion,GROUP_CONCAT(DISTINCT mst_questionare_subquestion.subanswer_type) as subanswertype,GROUP_CONCAT(DISTINCT mst_questionare_selectoption.subquestion_id,'-', mst_questionare_selectoption.sk_answer_id) as optionid,GROUP_CONCAT(distinct mst_questionare_selectoption.subquestion_id,'-', mst_questionare_selectoption.options SEPARATOR '#') as options,GROUP_CONCAT(DISTINCT mst_questionare_selectoption.option_comment SEPARATOR '#') as optioncomment FROM `mst_questionare` LEFT JOIN mst_section ON mst_section.sk_section_id=mst_questionare.section_id LEFT JOIN mst_subsection on mst_subsection.sk_subsection_id=mst_questionare.subsection_id LEFT JOIN mst_questionare_subquestion on mst_questionare_subquestion.question_id=mst_questionare.sk_question_id LEFT JOIN mst_questionare_selectoption on mst_questionare_selectoption.subquestion_id=mst_questionare_subquestion.sk_subquestion_id and  mst_questionare_selectoption.question_id=mst_questionare.sk_question_id  left JOIN mys_txn_answers on mys_txn_answers.question_id=mst_questionare.sk_question_id and mys_txn_answers.shopper_id='"
					+ mBean.getSk_shopper_id() + "' WHERE `brand_id`='" + brand_id + "' and (mode_of_contact ='"
					+ modeofcontact + "' or mode_of_contact= 'all') and year_applied='" + year_question_applied
					+ "'  and question_status='active'  GROUP BY sk_question_id  ORDER by mst_questionare.standard_number_sequence  limit "
					+ limit + ",1;";
		}
		log.info("allquestionsList method :" + sql);
		return template.query(sql, new RowMapper<MysteryShoppingVisitsBean>() {
			public MysteryShoppingVisitsBean mapRow(ResultSet rs, int row) throws SQLException {
				MysteryShoppingVisitsBean mvBean = new MysteryShoppingVisitsBean();
				mvBean.setQuestion_id(rs.getString("sk_question_id"));

				mvBean.setStandard_number(rs.getString("standard_number"));
				mvBean.setStandard_number_sequence(rs.getString("standard_number_sequence"));
				mvBean.setQuestion_code(rs.getString("question_code"));
				mvBean.setFormula_flag(rs.getString("formula_flag"));
				mvBean.setQuestion_points(rs.getString("question_points"));
				mvBean.setQuestion_optiontype(rs.getString("question_optiontype"));
				mvBean.setMandatory_optional_status(rs.getString("mandatory_optional_status"));
				mvBean.setSection_id(rs.getString("section_id"));
				mvBean.setSection_name(rs.getString("section_name"));
				mvBean.setSubsection_id(rs.getString("subsection_id"));
				mvBean.setSubsection_name(rs.getString("subsection_name"));

				mvBean.setMainquestiontype(rs.getString("question_type"));
				mvBean.setMainquestion(rs.getString("question"));
				mvBean.setMainanswertype(rs.getString("answer_type"));
				mvBean.setMainquestionComment(rs.getString("comment_mandatory"));

				mvBean.setSubquestionid(rs.getString("subquestionid"));

				mvBean.setSubquestion(rs.getString("subquestion"));
				mvBean.setSubanswertype(rs.getString("subanswertype"));

				mvBean.setOptionid(rs.getString("optionid"));
				mvBean.setOptions(rs.getString("options"));
				mvBean.setOptioncomment(rs.getString("optioncomment"));
				mvBean.setSuper_question_id(rs.getString("super_question_id"));
				mvBean.setSuper_question_answer(rs.getString("super_question_answer"));
				mvBean.setSk_shopper_id(decrypted_shooper_id);
				mvBean.setSelected_main_comment(rs.getString("main_ques_comment"));
				mvBean.setSelected_date(rs.getString("date"));
				mvBean.setSelected_time(rs.getString("dateandtime"));
				mvBean.setSelected_pagaragraph(rs.getString("paragraph"));
				List<MysteryShoppingVisitsBean> sectionsList = getSubsections(mBean, rs.getString("section_id"));
				mvBean.setSectionsList(sectionsList);

				List<MysteryShoppingVisitsBean> optionslist = getoptionsquestions(mBean, rs.getString("sk_question_id"),
						mvBean.getSk_shopper_id());
				mvBean.setOptionslist(optionslist);
				try {
					log.info("allquestionsList method concating :"
							+ "SELECT GROUP_CONCAT(mys_txn_answers.select_option_id ) as select_option_id,GROUP_CONCAT(mys_txn_answers.question_id,'-',mys_txn_answers.select_option_id) as question_id FROM `mys_txn_answers` WHERE `question_id` in("
							+ rs.getString("super_question_id").replaceAll("##", ",") + ") and shopper_id='"
							+ mvBean.getSk_shopper_id() + "' and status='active';");
					return template.queryForObject(
							"SELECT GROUP_CONCAT(mys_txn_answers.select_option_id ) as select_option_id,GROUP_CONCAT(mys_txn_answers.question_id,'-',mys_txn_answers.select_option_id) as question_id FROM `mys_txn_answers` WHERE `question_id`in("
									+ rs.getString("super_question_id").replaceAll("##", ",") + ") and shopper_id='"
									+ mvBean.getSk_shopper_id() + "' and status='active'",
							new RowMapper<MysteryShoppingVisitsBean>() {
								public MysteryShoppingVisitsBean mapRow(ResultSet rs, int row) throws SQLException {
									mvBean.setSuper_question_selected_id(rs.getString("select_option_id"));
									mvBean.setSuper_selected_qid(rs.getString("question_id"));
									log.info("allquestionsList method return" + new Gson().toJson(mvBean));
									return mvBean;
								}
							});
				} catch (Exception e) {
					e.printStackTrace();
					log.info("Exception allquestionsList method return" + e);
				}

				try {
					log.info("allquestionsList method  fetching all txn_answers :"
							+ "SELECT * FROM `mys_txn_answers` WHERE `question_id`='" + rs.getString("sk_question_id")
							+ "'  and shopper_id='" + mBean.getSk_shopper_id() + "' and status='active';");
					return template.queryForObject(
							"SELECT * FROM `mys_txn_answers` WHERE `question_id`='" + rs.getString("sk_question_id")
									+ "'  and shopper_id='" + mBean.getSk_shopper_id() + "' and status='active'",
							new RowMapper<MysteryShoppingVisitsBean>() {
								public MysteryShoppingVisitsBean mapRow(ResultSet rs, int row) throws SQLException {
									mvBean.setSelected_option_id(rs.getString("select_option_id"));
									log.info(rs.getString("main_ques_comment"));
									mvBean.setSelected_main_comment(rs.getString("main_ques_comment"));
									mvBean.setSelected_option_comment(rs.getString("select_option_comment"));
									mvBean.setSelected_date(rs.getString("date"));
									mvBean.setSelected_time(rs.getString("dateandtime"));
									mvBean.setSelected_pagaragraph(rs.getString("paragraph"));
									log.info("allquestionsList method return" + new Gson().toJson(mvBean));
									return mvBean;
								}
							});
				} catch (Exception e) {
					e.printStackTrace();
					log.info("Exception allquestionsList method return" + e);
				}

				log.info("allquestionsList method return" + new Gson().toJson(mvBean));
				return mvBean;

			}
		});

	}

	protected List<MysteryShoppingVisitsBean> getSubsections(MysteryShoppingVisitsBean mBean, String section_id) {
		log.info("getSubsections method" + new Gson().toJson(mBean));
		log.info("getSubsections method :" + "SELECT * FROM `mst_subsection` WHERE `section_id`='" + section_id
				+ "' and `subsection_status`='active';");
		return template.query("SELECT * FROM `mst_subsection` WHERE `section_id`='" + section_id
				+ "' and `subsection_status`='active'", new RowMapper<MysteryShoppingVisitsBean>() {
					public MysteryShoppingVisitsBean mapRow(ResultSet rs, int row) throws SQLException {
						MysteryShoppingVisitsBean mBean = new MysteryShoppingVisitsBean();
						mBean.setSubsection_id(rs.getString("sk_subsection_id"));
						mBean.setSubsection_name(rs.getString("subsection_name"));
						log.info("getSubsections method return" + new Gson().toJson(mBean));
						return mBean;
					}
				});
	}

	public MysteryShoppingVisitsBean getShoopersDetails(MysteryShoppingVisitsBean mBean) {
		log.info("getShoopersDetails method" + new Gson().toJson(mBean));
		log.info("getShoopersDetails method :" + "SELECT * FROM `mst_shopper_details` WHERE `sk_shopper_id`='"
				+ mBean.getSk_shopper_id() + "';");
		return template.queryForObject(
				"SELECT * FROM `mst_shopper_details` WHERE `sk_shopper_id`='" + mBean.getSk_shopper_id() + "'",
				new RowMapper<MysteryShoppingVisitsBean>() {
					public MysteryShoppingVisitsBean mapRow(ResultSet rs, int row) throws SQLException {
						mBean.setBrand_id(rs.getString("brand_id"));
						mBean.setMode_of_contact(rs.getString("mode_of_contact"));
						mBean.setName(rs.getString("name"));
						mBean.setYear(rs.getString("year"));
						log.info("getShoopersDetails method return" + new Gson().toJson(mBean));
						return mBean;
					}
				});
	}

	public List<MysteryShoppingVisitsBean> getsubquestions(MysteryShoppingVisitsBean mBean, String qid,
			String decrypted_shooper_id) {
		log.info("getsubquestions method" + new Gson().toJson(mBean));
		log.info("getShoopersDetails method :" + "SELECT * FROM `mst_questionare_subquestion` WHERE `question_id`='"
				+ qid + "';");
		return template.query("SELECT * FROM `mst_questionare_subquestion` WHERE `question_id`='" + qid + "'",
				new RowMapper<MysteryShoppingVisitsBean>() {
					public MysteryShoppingVisitsBean mapRow(ResultSet rs, int row) throws SQLException {
						MysteryShoppingVisitsBean mBean = new MysteryShoppingVisitsBean();
						mBean.setSubquestionid(rs.getString("sk_subquestion_id"));
						mBean.setQuestion_id(rs.getString("question_id"));
						mBean.setSubquestion(rs.getString("subquestion"));
						mBean.setAnswer_type(rs.getString("subanswer_type"));
						mBean.setSk_shopper_id(decrypted_shooper_id);
						List<MysteryShoppingVisitsBean> optionslist = getoptionsquestions(mBean, qid,
								rs.getString("sk_subquestion_id"), mBean.getSk_shopper_id());
						mBean.setOptionslist(optionslist);

						try {
							log.info("getShoopersDetails method fetching all txn_answers :"
									+ "SELECT * FROM `mys_txn_answers` WHERE `question_id`='" + qid
									+ "' and `subquestion_id`='" + rs.getString("sk_subquestion_id")
									+ "' and shopper_id='" + mBean.getSk_shopper_id() + "' and status='active';");
							return template.queryForObject("SELECT * FROM `mys_txn_answers` WHERE `question_id`='" + qid
									+ "'and `subquestion_id`='" + rs.getString("sk_subquestion_id")
									+ "'  and shopper_id='" + mBean.getSk_shopper_id() + "' and status='active'",
									new RowMapper<MysteryShoppingVisitsBean>() {
										public MysteryShoppingVisitsBean mapRow(ResultSet rs, int row)
												throws SQLException {

											mBean.setSelected_option_id(rs.getString("select_option_id"));
											mBean.setSelected_main_comment(rs.getString("main_ques_comment"));
											mBean.setSelected_option_comment(rs.getString("select_option_comment"));
											if (rs.getString("select_option_comment").isEmpty()) {
												mBean.setSelected_option_comment(null);
											}
											log.info("SDFHSDJH" + rs.getString("select_option_comment"));
											mBean.setSelected_date(rs.getString("date"));
											mBean.setSelected_time(rs.getString("dateandtime"));
											mBean.setSelected_pagaragraph(rs.getString("paragraph"));
											return mBean;
										}
									});
						} catch (Exception e) {
							e.printStackTrace();
						}
						log.info("getsubquestions method return" + new Gson().toJson(mBean));
						return mBean;
					}
				});
	}

	public List<MysteryShoppingVisitsBean> getoptionsquestions(MysteryShoppingVisitsBean mBean, String qid,
			String subqid, String shopper_id) {

		log.info("getoptionsquestions method" + new Gson().toJson(mBean));
		log.info("getoptionsquestions method :" + "SELECT * FROM `mst_questionare_selectoption` WHERE `question_id`='"
				+ qid + "' and `subquestion_id`='" + subqid + "';");
		return template.query("SELECT * FROM `mst_questionare_selectoption` WHERE `question_id`='" + qid
				+ "' and `subquestion_id`='" + subqid + "'", new RowMapper<MysteryShoppingVisitsBean>() {
					public MysteryShoppingVisitsBean mapRow(ResultSet rs, int row) throws SQLException {
						MysteryShoppingVisitsBean mBean = new MysteryShoppingVisitsBean();
						mBean.setOptionid(rs.getString("sk_answer_id"));
						mBean.setOptions(rs.getString("options"));
						mBean.setOptioncomment(rs.getString("option_comment"));
						mBean.setSk_shopper_id(shopper_id);
						try {
							log.info("getoptionsquestions method fetching all txn_answers :"
									+ "SELECT * FROM `mys_txn_answers` WHERE `question_id`='" + qid
									+ "' and `subquestion_id`='" + subqid + "' and shopper_id='"
									+ mBean.getSk_shopper_id() + "' and status='active';");
							return template.queryForObject(
									"SELECT * FROM `mys_txn_answers` WHERE `question_id`='" + qid
											+ "'and `subquestion_id`='" + subqid + "'  and shopper_id='"
											+ mBean.getSk_shopper_id() + "' and status='active'",
									new RowMapper<MysteryShoppingVisitsBean>() {
										public MysteryShoppingVisitsBean mapRow(ResultSet rs, int row)
												throws SQLException {
											mBean.setSelected_option_id(rs.getString("select_option_id"));
											mBean.setSelected_main_comment(rs.getString("main_ques_comment"));
											mBean.setSelected_option_comment(rs.getString("select_option_comment"));
											mBean.setSelected_date(rs.getString("date"));
											mBean.setSelected_time(rs.getString("dateandtime"));
											mBean.setSelected_pagaragraph(rs.getString("paragraph"));
											return mBean;
										}
									});
						} catch (Exception e) {
							e.printStackTrace();
						}
						log.info("getoptionsquestions method return" + new Gson().toJson(mBean));
						return mBean;
					}
				});

	}

	private List<MysteryShoppingVisitsBean> getoptionsquestions(MysteryShoppingVisitsBean mBean, String qid,
			String shopper_id) {
		log.info("getoptionsquestions method" + new Gson().toJson(mBean));
		log.info("getoptionsquestions method :" + "SELECT * FROM `mst_questionare_selectoption` WHERE `question_id`='"
				+ qid + "' and `subquestion_id` is null and options_status='active';");
		return template.query(
				"SELECT * FROM `mst_questionare_selectoption` WHERE `question_id`='" + qid
						+ "' and `subquestion_id` is null and options_status='active'",
				new RowMapper<MysteryShoppingVisitsBean>() {
					public MysteryShoppingVisitsBean mapRow(ResultSet rs, int row) throws SQLException {
						MysteryShoppingVisitsBean mBean = new MysteryShoppingVisitsBean();
						mBean.setOptionid(rs.getString("sk_answer_id"));
						mBean.setOptions(rs.getString("options"));
						mBean.setOptioncomment(rs.getString("option_comment"));
						mBean.setSk_shopper_id(shopper_id);
						try {
							log.info("getoptionsquestions method fetching all txn_answer :"
									+ "SELECT * FROM `mys_txn_answers` WHERE `question_id`='" + qid
									+ "'  and shopper_id='" + mBean.getSk_shopper_id() + "' and status='active';");
							template.queryForObject("SELECT * FROM `mys_txn_answers` WHERE `question_id`='" + qid
									+ "'  and shopper_id='" + mBean.getSk_shopper_id() + "' and status='active'",
									new RowMapper<MysteryShoppingVisitsBean>() {
										public MysteryShoppingVisitsBean mapRow(ResultSet rs, int row)
												throws SQLException {
											mBean.setSelected_option_id(rs.getString("select_option_id"));
											mBean.setSelected_main_comment(rs.getString("main_ques_comment"));
											// log.info(mBean.getSelected_main_comment());
											mBean.setSelected_option_comment(rs.getString("select_option_comment"));
											mBean.setSelected_date(rs.getString("date"));
											mBean.setSelected_time(rs.getString("dateandtime"));
											mBean.setSelected_pagaragraph(rs.getString("paragraph"));
											return mBean;
										}
									});
						} catch (Exception e) {
							e.printStackTrace();
							log.info("Exception of getoptionsquestions method" + e);
						}
						log.info("getoptionsquestions method return" + new Gson().toJson(mBean));
						return mBean;
					}
				});
	}

	public MysteryShoppingVisitsBean questionscount(MysteryShoppingVisitsBean mBean, String sid, String bid,
			String modeOfContact, String year_applied) {
		log.info("questionscount method " + new Gson().toJson(mBean));
		String sql = "";

		if (modeOfContact.contentEquals("Online Sales Channel")) {
			sql = "SELECT count(*) as qcount, mst_questionare.* from mst_questionare  WHERE `brand_id`='" + bid
					+ "'  and mode_of_contact='Online Sales Channel' and year_applied='" + year_applied
					+ "'and  mst_questionare.question_status='active'";
		} else {
			sql = "SELECT count(*) as qcount, mst_questionare.* from mst_questionare  WHERE `brand_id`='" + bid
					+ "' and  (mode_of_contact ='" + modeOfContact + "' or mode_of_contact= 'all') and year_applied='"
					+ year_applied + "' and  mst_questionare.question_status='active'  ";
		}
		log.info("questionscount method :" + sql);
		return template.queryForObject(sql, new RowMapper<MysteryShoppingVisitsBean>() {
			public MysteryShoppingVisitsBean mapRow(ResultSet rs, int row) throws SQLException {
				mBean.setCount(rs.getString("qcount"));
				log.info("questionscount method return" + new Gson().toJson(mBean));
				return mBean;
			}

		});
	}

	public void addMysDocuments(MysteryShoppingVisitsBean mvBean, List<MysteryShoppingVisitsBean> documentType,
			List<MultipartFile> files, String sk_shopper_id, String shopperFolderName) {
		log.info("addMysDocuments method" + new Gson().toJson(mvBean));
		for (int i = 0; i < documentType.size(); i++) {
			String document = documentType.get(i).getDocuments();
			// String comment = documentType.get(i).getComment();
			String comment = "";
			if (documentType.get(i).getComment() == null) {
				comment = "";
			} else {
				comment = documentType.get(i).getComment();
			}
			String file = "";
			try {
				file = files.get(i).getOriginalFilename();
			}

			catch (Exception e) {
				e.printStackTrace();
				file = "";
			}

			log.info("addMysDocuments method :"
					+ "INSERT INTO mst_uploaded_documents(shopper_visit_id,document_type, document_name,document_reason,shopper_folder_name) VALUES ('"
					+ sk_shopper_id + "','" + document + "','" + file + "','" + comment + "','" + shopperFolderName
					+ "')");
			template.execute(
					"INSERT INTO mst_uploaded_documents(shopper_visit_id, document_type, document_name,document_reason,shopper_folder_name) VALUES ('"
							+ sk_shopper_id + "','" + document + "','" + file + "','" + comment + "','"
							+ shopperFolderName + "')");

		}

	}

	public MysteryShoppingVisitsBean getShopperDocumentCount(MysteryShoppingVisitsBean mvBean, String sk_shopper_id) {
		log.info("getShopperDocumentCount method" + new Gson().toJson(mvBean));
		log.info("getShopperDocumentCount method :"
				+ "select count(*) as shopper_count from mst_uploaded_documents where shopper_visit_id='"
				+ sk_shopper_id + "'");
		return template
				.queryForObject("select count(*) as shopper_count from mst_uploaded_documents where shopper_visit_id='"
						+ sk_shopper_id + "';", new RowMapper<MysteryShoppingVisitsBean>() {

							public MysteryShoppingVisitsBean mapRow(ResultSet rs, int row) throws SQLException {
								mvBean.setShopper_document_count(rs.getString("shopper_count"));
								log.info("getShopperDocumentCount method return" + new Gson().toJson(mvBean));
								return mvBean;
							}
						});

	}

	public List<MysteryShoppingVisitsBean> getMysCompletedList(MysteryShoppingVisitsBean mvBean) {
		log.info("getMysCompletedList method" + new Gson().toJson(mvBean));
		log.info("getMysCompletedList method :"
				+ "SELECT mst_dealer.dealer_name,mst_dealer_outlet.outlet_name,mst_brand.brand_name, mst_shopper_details.* FROM mst_dealer,mst_dealer_outlet,mst_brand,mst_shopper_details where mst_shopper_details.dealer_id=mst_dealer.sk_dealer_id AND mst_shopper_details.outlet_id=mst_dealer_outlet.sk_outlet_id  AND mst_shopper_details.brand_id=mst_brand.sk_brand_id AND mst_brand.brand_status='active' AND mst_dealer.dealer_status='active' AND mst_dealer_outlet.outlet_status='active' AND mst_shopper_details.status='active' and mst_shopper_details.visit_status='completed' and( mst_shopper_details.visit_status='completed' OR mst_shopper_details.visit_status='reviewed');");
		return template.query(
				"SELECT mst_dealer.dealer_name,mst_dealer_outlet.outlet_name,mst_brand.brand_name, mst_shopper_details.* FROM mst_dealer,mst_dealer_outlet,mst_brand,mst_shopper_details where mst_shopper_details.dealer_id=mst_dealer.sk_dealer_id AND mst_shopper_details.outlet_id=mst_dealer_outlet.sk_outlet_id  AND mst_shopper_details.brand_id=mst_brand.sk_brand_id AND mst_brand.brand_status='active' AND mst_dealer.dealer_status='active' AND mst_dealer_outlet.outlet_status='active' AND mst_shopper_details.status='active'and ( mst_shopper_details.visit_status='completed' OR mst_shopper_details.visit_status='reviewed');",
				new RowMapper<MysteryShoppingVisitsBean>() {
					@Override
					public MysteryShoppingVisitsBean mapRow(ResultSet rs, int row) throws SQLException {
						MysteryShoppingVisitsBean mvBean = new MysteryShoppingVisitsBean();
						mvBean.setSk_shopper_id(rs.getString("sk_shopper_id"));
						String sk_shopper_id = rs.getString("sk_shopper_id");
						mvBean.setDealer_name(rs.getString("dealer_name"));
						mvBean.setOutlet_name(rs.getString("outlet_name"));
						mvBean.setBrand_name(rs.getString("brand_name"));
						mvBean.setVisit_date(rs.getString("visit_date"));
						mvBean.setName(rs.getString("name"));
						mvBean.setEmail(rs.getString("email"));
						mvBean.setContact_number(rs.getString("contact_number"));
						mvBean.setShoppers_car_owned(rs.getString("shoppers_car_owned"));
						mvBean.setShoppers_car_yop(rs.getString("shoppers_car_yop"));
						mvBean.setAge(rs.getString("age"));
						mvBean.setGender(rs.getString("gender"));
						mvBean.setYear(rs.getString("year"));
						mvBean.setQuarter(rs.getString("quarter"));
						mvBean.setMode_of_contact(rs.getString("mode_of_contact"));
						mvBean.setSc_name(rs.getString("sc_name"));
						String year = rs.getString("year");
						log.info("year in completed" + year);
						getShopperDocumentCount(mvBean, sk_shopper_id);

						mvBean.setShopper_document_count(mvBean.getShopper_document_count());

						if (rs.getString("mode_of_contact").equals("Online Sales Channel")) {
							getOutletLevelScoreForonlineSales(mvBean, sk_shopper_id);

							// mvBean.setOutlet_level_score((mvBean.getOutlet_level_score()));
							mvBean.setOutlet_level_score(String.valueOf(
									Math.round(Double.parseDouble(mvBean.getOutlet_level_score()) * 100.00) / 100.00));
						} else {
							getOutletLevelScoreForNonOsc(mvBean, sk_shopper_id, year);
							// mvBean.setOutlet_level_score(mvBean.getOutlet_level_score());
							mvBean.setOutlet_level_score(String.valueOf(
									Math.round(Double.parseDouble(mvBean.getOutlet_level_score()) * 100.00) / 100.00));

						}
						try {
							getPEScore(mvBean, sk_shopper_id);
							mvBean.setProcess_excellence_score(String.valueOf(
									Math.round(Double.parseDouble(mvBean.getProcess_excellence_score()) * 100.00)
											/ 100.00));

						} catch (Exception e) {
							e.printStackTrace();
						}
						try {
							getCTScore(mvBean, sk_shopper_id);
							mvBean.setCustomer_treatment_score(String.valueOf(
									Math.round(Double.parseDouble(mvBean.getCustomer_treatment_score()) * 100.00)
											/ 100.00));

						} catch (Exception e) {
							e.printStackTrace();
						}

						// getPEScore(mvBean,sk_shopper_id);
						// mvBean.setProcess_excellence_score(mvBean.getProcess_excellence_score());
						// mvBean.setProcess_excellence_score(
						// String.valueOf(Math.round(Double.parseDouble(mvBean.getProcess_excellence_score())
						// * 100.00) / 100.00));
						// getCTScore(mvBean,sk_shopper_id);
						// mvBean.setCustomer_treatment_score(mvBean.getCustomer_treatment_score());
						// mvBean.setCustomer_treatment_score(
						// String.valueOf(Math.round(Double.parseDouble(mvBean.getCustomer_treatment_score())
						// * 100.00) / 100.00));

						log.info("getMysCompletedList method return" + new Gson().toJson(mvBean));
						return mvBean;
					}
				});

	}

	public List<MysteryShoppingVisitsBean> getShopperDocumentDetails(MysteryShoppingVisitsBean mvBean,
			String sk_shopper_id) {
		log.info("getShopperDocumentDetails method" + new Gson().toJson(mvBean));
		log.info("getShopperDocumentDetails method :" + "SELECT *FROM mst_uploaded_documents where  shopper_visit_id='"
				+ sk_shopper_id + "'");
		return template.query("SELECT *FROM mst_uploaded_documents where  shopper_visit_id='" + sk_shopper_id + "'",
				new RowMapper<MysteryShoppingVisitsBean>() {
					@Override
					public MysteryShoppingVisitsBean mapRow(ResultSet rs, int row) throws SQLException {
						MysteryShoppingVisitsBean mvBean = new MysteryShoppingVisitsBean();
						mvBean.setSk_document_id(rs.getString("sk_document_id"));
						mvBean.setSk_shopper_id(rs.getString("shopper_visit_id"));
						mvBean.setDocument_type(rs.getString("document_type"));
						log.info("document reason" + rs.getString("document_reason"));
						if (rs.getString("document_reason").equals("")) {
							mvBean.setDocument_name(rs.getString("document_name"));
							String ext1 = FilenameUtils.getExtension(rs.getString("document_name")); // returns "txt"
							log.info("extension" + ext1);
							if (ext1.equals("pdf")) {
								mvBean.setExtension("pdf");
								log.info("extension in pdf" + mvBean.getExtension());
							} else {
								mvBean.setExtension("jpgpdf");
							}

							mvBean.setComment("");

						} else {
							mvBean.setDocument_name("");
							mvBean.setComment(rs.getString("document_reason"));
						}
						mvBean.setShopper_folder_name(rs.getString("shopper_folder_name"));
						log.info("getShopperDocumentDetails method return" + new Gson().toJson(mvBean));
						return mvBean;
					}
				});
	}

	public void addMainQuestionAnswer(MysteryShoppingVisitsBean mBean) {

		log.info("addMainQuestionAnswer method" + new Gson().toJson(mBean));
		if (mBean.getAnswer_type().contentEquals("Select/List")) {

			mBean.setOption_points(mBean.getOption_points());
			log.info("options points");
			try {
				getoptionsName(mBean);
			} catch (Exception e) {
				e.printStackTrace();
			}
		} else {
			log.info("question points");
			mBean.setOption_points(mBean.getQuestion_points());
		}
		String main_question_comment = "";
		String option_question_comment = "";
		String paragraph = "";
		String options = "";
		try {
			if (mBean.getMain_ques_comment().contentEquals("null") || mBean.getMain_ques_comment().isEmpty()) {
				main_question_comment = mBean.getMain_ques_comment();
			} else {
				main_question_comment = mBean.getMain_ques_comment().replace("'", "\\'");
			}
		} catch (Exception e) {
			main_question_comment = mBean.getMain_ques_comment();
		}
		try {
			if (mBean.getOption_comment().contentEquals("null") || mBean.getOption_comment().isEmpty()
					|| mBean.getOption_comment() == "") {
				option_question_comment = "";
			} else {
				option_question_comment = mBean.getOption_comment().replace("'", "\\'").replace(",", "  ");
				;
			}
		} catch (Exception e) {
			option_question_comment = "";
		}
		try {
			if (mBean.getAnswerParagraph().contentEquals("null") || mBean.getAnswerParagraph().isEmpty()) {
				paragraph = mBean.getAnswerParagraph();
			} else {
				paragraph = mBean.getAnswerParagraph().replace("'", "\\'");
			}
		} catch (Exception e) {
			paragraph = mBean.getAnswerParagraph();
		}
		try {
			if (mBean.getOptions().contentEquals("null") || mBean.getOptions().isEmpty()) {
				options = mBean.getOptions();
			} else {
				options = mBean.getOptions().replace("'", "\\'");
			}
		} catch (Exception e) {
			options = mBean.getOptions();
		}

		log.info("addMainQuestionAnswer method :"
				+ "INSERT INTO `mys_txn_answers`(`shopper_id`, `question_id`, `question_code`, standard_number,`standard_number_sequence`, `question_type`, `question_text`, `answer_type`, `select_option_id`, `select_option_text`, `paragraph`, `date`, `dateandtime`, `formula_flag`, `question_points`, `scored_points`, `main_ques_comment`, `select_option_comment`) VALUES ('"
				+ mBean.getSk_shopper_id() + "','" + mBean.getQuestion_id() + "','" + mBean.getQuestion_code() + "','"
				+ mBean.getStandard_number() + "','" + mBean.getStandard_number_sequence() + "','"
				+ mBean.getQuestion_type() + "','" + mBean.getQuestions().replace("'", "\\'") + "','"
				+ mBean.getAnswer_type() + "','" + mBean.getOptionid() + "','" + options + "','" + paragraph + "','"
				+ mBean.getAnswerDate() + "','" + mBean.getAnswerTime() + "','" + mBean.getFormula_flag() + "','"
				+ mBean.getQuestion_points() + "','" + mBean.getOption_points() + "','" + main_question_comment + "','"
				+ option_question_comment + "')");
		template.execute(
				"INSERT INTO `mys_txn_answers`(`shopper_id`, `question_id`, `question_code`,standard_number, `standard_number_sequence`, `question_type`, `question_text`, `answer_type`, `select_option_id`, `select_option_text`, `paragraph`, `date`, `dateandtime`, `formula_flag`, `question_points`, `scored_points`, `main_ques_comment`, `select_option_comment`) VALUES ('"
						+ mBean.getSk_shopper_id() + "','" + mBean.getQuestion_id() + "','" + mBean.getQuestion_code()
						+ "','" + mBean.getStandard_number() + "','" + mBean.getStandard_number_sequence() + "','"
						+ mBean.getQuestion_type() + "','" + mBean.getQuestions().replace("'", "\\'") + "','"
						+ mBean.getAnswer_type() + "','" + mBean.getOptionid() + "','" + options + "','" + paragraph
						+ "','" + mBean.getAnswerDate() + "','" + mBean.getAnswerTime() + "','"
						+ mBean.getFormula_flag() + "','" + mBean.getQuestion_points() + "','"
						+ mBean.getOption_points() + "','" + main_question_comment + "','" + option_question_comment
						+ "')");
	}

	private MysteryShoppingVisitsBean getoptionsName(MysteryShoppingVisitsBean mBean) {
		String sql = "SELECT  COUNT(*),mst_questionare_selectoption.* FROM `mst_questionare_selectoption` WHERE `sk_answer_id`='"
				+ mBean.getOptionid() + "' and `options_status`='active' ";
		log.info("getoptionsName method" + new Gson().toJson(mBean));
		log.info("getoptionsName method :" + sql);
		return template.queryForObject(sql, new RowMapper<MysteryShoppingVisitsBean>() {
			public MysteryShoppingVisitsBean mapRow(ResultSet rs, int row) throws SQLException {
				mBean.setOptions(rs.getString("options"));
				mBean.setOption_points(rs.getString("option_points"));
				log.info("getoptionsName method return" + new Gson().toJson(mBean));
				return mBean;
			}

		});
	}

	public void addMainSubQuestionAnswer(MysteryShoppingVisitsBean mBean, List<MysteryShoppingVisitsBean> answerdata) {
		for (MysteryShoppingVisitsBean mysteryShoppingVisitsBean : answerdata) {
			if (mysteryShoppingVisitsBean.getSubquestionid() != null) {
				log.info("addMainSubQuestionAnswer method" + new Gson().toJson(mBean));

				if (mBean.getAnswer_type().contentEquals("Select/List")) {

					mBean.setOption_points(mBean.getOption_points());
					log.info("options points");
					try {
						getoptionsName(mBean, mysteryShoppingVisitsBean);
					} catch (Exception e) {
						e.printStackTrace();
					}
				} else {
					log.info("question points");
					mBean.setOption_points(mBean.getQuestion_points());
				}

			}

		}
	}

	private MysteryShoppingVisitsBean getoptionsName(MysteryShoppingVisitsBean mBean,
			MysteryShoppingVisitsBean mysteryShoppingVisitsBean) {
		String sql = "SELECT   COUNT(*),mst_questionare_selectoption.* FROM `mst_questionare_selectoption` WHERE `sk_answer_id`='"
				+ mysteryShoppingVisitsBean.getOptionid() + "' and `options_status`='active' ";
		log.info("getoptionsName method" + new Gson().toJson(mBean));
		log.info("getoptionsName method :" + sql);
		return template.queryForObject(sql, new RowMapper<MysteryShoppingVisitsBean>() {
			public MysteryShoppingVisitsBean mapRow(ResultSet rs, int row) throws SQLException {
				mBean.setOptions(rs.getString("options"));
				mBean.setOption_points(rs.getString("option_points"));
				log.info("getoptionsName method return" + new Gson().toJson(mBean));
				return mBean;
			}

		});
	}

	public MysteryShoppingVisitsBean saveMst_anwerData(MysteryShoppingVisitsBean mBean) {
		log.info("saveMst_anwerData method" + new Gson().toJson(mBean));
		log.info("in dao save mst answer  data");
		String sql = "SELECT COUNT(shopper_id) as exist from mst_msm_result WHERE shopper_id='"
				+ mBean.getSk_shopper_id() + "'";
		String count = "";
		log.info("saveMst_anwerData method :" + sql);
		List<Map<String, Object>> list = template.queryForList(sql);
		for (Map<String, Object> row : list) {
			count = row.get("exist").toString();
		}
		int id_status = Integer.parseInt(count);

		if (id_status > 0) {
			template.execute("delete from mst_msm_result where shopper_id='" + mBean.getSk_shopper_id() + "'");
			String sql1 = "SELECT  mst_shopper_details.*, mst_brand.sk_brand_id,mst_dealer_outlet.outlet_id as oid, mst_brand.brand_name,mst_brand_model_variant.sk_variant_id,mst_brand_model_variant.variant_name,mst_brand_model.sk_model_id,mst_brand_model.model_name,mst_dealer.sk_dealer_id,mst_dealer.dealer_name,mst_dealer_outlet.*,mst_geo_region.region_name,mst_geo_city.city_name,mst_geo_city.sk_city_id,mst_geo_city.tier,mst_geo_city.metro FROM mst_shopper_details, mst_brand,mst_brand_model_variant,mst_brand_model,mst_dealer,mst_dealer_outlet,mst_geo_region,mst_geo_city WHERE mst_shopper_details.dealer_id=mst_dealer.sk_dealer_id AND mst_shopper_details.outlet_id=mst_dealer_outlet.sk_outlet_id AND  mst_shopper_details.brand_id=mst_brand.sk_brand_id AND mst_brand_model_variant.model_id=mst_brand_model.sk_model_id AND mst_shopper_details.brand_model_variant_id=mst_brand_model_variant.sk_variant_id AND mst_dealer_outlet.region_id=mst_geo_region.sk_region_id AND mst_dealer_outlet.city=mst_geo_city.sk_city_id AND mst_shopper_details.sk_shopper_id='"
					+ mBean.getSk_shopper_id()
					+ "' AND mst_shopper_details.status='active' AND mst_brand.brand_status='active' AND mst_brand_model_variant.variant_status='active' AND mst_brand_model.model_status='active' And mst_dealer.dealer_status='active' AND mst_dealer_outlet.outlet_status='active' AND mst_geo_region.region_status='active' AND mst_geo_city.city_status='active'";
			log.info("saveMst_anwerData method if id_status>0 :"
					+ "SELECT  mst_shopper_details.*, mst_brand.sk_brand_id,mst_dealer_outlet.outlet_id as oid, mst_brand.brand_name,mst_brand_model_variant.sk_variant_id,mst_brand_model_variant.variant_name,mst_brand_model.sk_model_id,mst_brand_model.model_name,mst_dealer.sk_dealer_id,mst_dealer.dealer_name,mst_dealer_outlet.*,mst_geo_region.region_name,mst_geo_city.city_name,mst_geo_city.sk_city_id,mst_geo_city.tier,mst_geo_city.metro FROM mst_shopper_details, mst_brand,mst_brand_model_variant,mst_brand_model,mst_dealer,mst_dealer_outlet,mst_geo_region,mst_geo_city WHERE mst_shopper_details.dealer_id=mst_dealer.sk_dealer_id AND mst_shopper_details.outlet_id=mst_dealer_outlet.sk_outlet_id AND  mst_shopper_details.brand_id=mst_brand.sk_brand_id AND mst_brand_model_variant.model_id=mst_brand_model.sk_model_id AND mst_shopper_details.brand_model_variant_id=mst_brand_model_variant.sk_variant_id AND mst_dealer_outlet.region_id=mst_geo_region.sk_region_id AND mst_dealer_outlet.city=mst_geo_city.sk_city_id AND mst_shopper_details.sk_shopper_id='"
					+ mBean.getSk_shopper_id()
					+ "' AND mst_shopper_details.status='active' AND mst_brand.brand_status='active' AND mst_brand_model_variant.variant_status='active' AND mst_brand_model.model_status='active' And mst_dealer.dealer_status='active' AND mst_dealer_outlet.outlet_status='active' AND mst_geo_region.region_status='active' AND mst_geo_city.city_status='active'");

			return template.queryForObject(sql1, new RowMapper<MysteryShoppingVisitsBean>() {
				public MysteryShoppingVisitsBean mapRow(ResultSet rs, int row) throws SQLException {

					InetAddress ip = null;
					try {
						ip = InetAddress.getLocalHost();
					} catch (UnknownHostException e) {
						e.printStackTrace();
					}
					String metro = "";
					if (rs.getString("metro").equals(0) || rs.getString("metro").equals("No")) {
						metro = "Non-Metro";
					} else {
						metro = "Metro";
					}
					log.info("saveMst_anwerData method inserting to DB :"
							+ "INSERT INTO `mst_msm_result`(`shopper_id`, `mode_of_contact`, `quarter`, `year`, `visit_brand_id`, `visit_brand_name`, `visit_brand_model_id`, `visit_brand_model_name`, `brand_varient_id`, `brand_varient_name`, `dealership_id`, `dealership_name`, `outlet_id`, `outlet_name`, `location_id`, `outlet_address`, `outlet_pincode`, `outlet_region_id`, `outlet_region_name`, `outlet_city_id`, `outlet_city_name`, `city_tier`, `city_metro`, `salesperson_name`, `salesperson_desc`, `scheduled_date`, `shopper_link_name`, `shopper_link_age`, `shopper_link_email`, `shopper_link_contact`, `shopper_link_gender`, `shoppers_link_shoppersCarBrand`, `shoppers_link_shoppersCarModel`, `shoppers_link_shoppersCarYop`, `Shopper_link_productMet`, `Shopper_link_productNameProvided`, `Shopper_link_productName`, `Shopper_link_productDesc`, `shopper_link_visit_date`, `start_time`, `end_time`, `visit_status`, `created_by`, `created_on`, `status`) VALUES ('"
							+ rs.getString("sk_shopper_id") + "','" + rs.getString("mode_of_contact") + "','"
							+ rs.getString("quarter") + "','" + rs.getString("year") + "','" + rs.getString("brand_id")
							+ "','" + rs.getString("brand_name") + "','" + rs.getString("sk_model_id") + "','"
							+ rs.getString("model_name") + "','" + rs.getString("brand_model_variant_id") + "','"
							+ rs.getString("variant_name") + "','" + rs.getString("sk_dealer_id") + "','"
							+ rs.getString("dealer_name") + "','" + rs.getString("sk_outlet_id") + "','"
							+ rs.getString("outlet_name") + "','" + rs.getString("oid") + "','"
							+ rs.getString("address") + "','" + rs.getString("pincode") + "','"
							+ rs.getString("region_id") + "','" + rs.getString("region_name") + "','"
							+ rs.getString("sk_city_id") + "','" + rs.getString("city_name") + "','"
							+ rs.getString("tier") + "','" + metro + "','" + rs.getString("sc_name") + "','"
							+ rs.getString("sc_description") + "','" + rs.getString("visit_date") + "','"
							+ rs.getString("shopper_link_name") + "','" + rs.getString("shopper_link_age") + "','"
							+ rs.getString("shopper_link_email") + "','" + rs.getString("shopper_link_contact") + "','"
							+ rs.getString("shopper_link_gender") + "','" + rs.getString("shoppers_car_owned_brand")
							+ "','" + rs.getString("shoppers_link_shoppersCarModel") + "','"
							+ rs.getString("shoppers_link_shoppersCarYop") + "','" + mBean.getShopper_link_productMet()
							+ "','" + mBean.getShopper_link_productNameProvided() + "','"
							+ mBean.getShopper_link_productName() + "','" + mBean.getShopper_link_productDesc() + "','"
							+ rs.getString("shopper_link_visit_date") + "','" + rs.getString("start_time") + "','"
							+ rs.getString("end_time") + "','scheduled','" + ip + "','" + dateFormat.format(date)
							+ "','active')");
					template.execute(
							"INSERT INTO `mst_msm_result`(`shopper_id`, `mode_of_contact`, `quarter`, `year`, `visit_brand_id`, `visit_brand_name`, `visit_brand_model_id`, `visit_brand_model_name`, `brand_varient_id`, `brand_varient_name`, `dealership_id`, `dealership_name`, `outlet_id`, `outlet_name`, `location_id`, `outlet_address`, `outlet_pincode`, `outlet_region_id`, `outlet_region_name`, `outlet_city_id`, `outlet_city_name`, `city_tier`, `city_metro`, `salesperson_name`, `salesperson_desc`, `scheduled_date`, `shopper_link_name`, `shopper_link_age`, `shopper_link_email`, `shopper_link_contact`, `shopper_link_gender`, `shoppers_link_shoppersCarBrand`, `shoppers_link_shoppersCarModel`, `shoppers_link_shoppersCarYop`, `Shopper_link_productMet`, `Shopper_link_productNameProvided`, `Shopper_link_productName`, `Shopper_link_productDesc`, `shopper_link_visit_date`, `start_time`, `end_time`, `visit_status`, `created_by`, `created_on`, `status`) VALUES ('"
									+ rs.getString("sk_shopper_id") + "','" + rs.getString("mode_of_contact") + "','"
									+ rs.getString("quarter") + "','" + rs.getString("year") + "','"
									+ rs.getString("brand_id") + "','" + rs.getString("brand_name") + "','"
									+ rs.getString("sk_model_id") + "','" + rs.getString("model_name") + "','"
									+ rs.getString("brand_model_variant_id") + "','" + rs.getString("variant_name")
									+ "','" + rs.getString("sk_dealer_id") + "','" + rs.getString("dealer_name") + "','"
									+ rs.getString("sk_outlet_id") + "','" + rs.getString("outlet_name") + "','"
									+ rs.getString("oid") + "','" + rs.getString("address") + "','"
									+ rs.getString("pincode") + "','" + rs.getString("region_id") + "','"
									+ rs.getString("region_name") + "','" + rs.getString("sk_city_id") + "','"
									+ rs.getString("city_name") + "','" + rs.getString("tier") + "','" + metro + "','"
									+ rs.getString("sc_name") + "','" + rs.getString("sc_description") + "','"
									+ rs.getString("visit_date") + "','" + rs.getString("shopper_link_name") + "','"
									+ rs.getString("shopper_link_age") + "','" + rs.getString("shopper_link_email")
									+ "','" + rs.getString("shopper_link_contact") + "','"
									+ rs.getString("shopper_link_gender") + "','"
									+ rs.getString("shoppers_car_owned_brand") + "','"
									+ rs.getString("shoppers_link_shoppersCarModel") + "','"
									+ rs.getString("shoppers_link_shoppersCarYop") + "','"
									+ mBean.getShopper_link_productMet() + "','"
									+ mBean.getShopper_link_productNameProvided() + "','"
									+ mBean.getShopper_link_productName() + "','" + mBean.getShopper_link_productDesc()
									+ "','" + rs.getString("shopper_link_visit_date") + "','"
									+ rs.getString("start_time") + "','" + rs.getString("end_time") + "','scheduled','"
									+ ip + "','" + dateFormat.format(date) + "','active')");

					template.execute("UPDATE `mst_shopper_details` SET `start_time`='" + mBean.getStart_time()
							+ "',`end_time`='" + mBean.getEnd_time() + "',`met_sc`='" + mBean.getMet_sc()
							+ "',`sc_name`='" + mBean.getSc_name() + "',`sc_designation`='" + mBean.getSc_designation()
							+ "',`sc_description`='" + mBean.getSc_description() + "',`shopper_link_name`='"
							+ mBean.getShopper_link_name() + "',`shopper_link_age`='" + mBean.getShopper_link_age()
							+ "',`shopper_link_email`='" + mBean.getShopper_link_email() + "',`shopper_link_contact`='"
							+ mBean.getShopper_link_contact() + "',`shopper_link_gender`='"
							+ mBean.getShopper_link_gender() + "',`shoppers_link_shoppersCarBrand`='"
							+ mBean.getShoppers_link_shoppersCarBrand() + "',`shoppers_link_shoppersCarModel`='"
							+ mBean.getShoppers_link_shoppersCarModel() + "',`shoppers_link_shoppersCarYop`='"
							+ mBean.getShoppers_link_shoppersCarYop() + "',`Shopper_link_productMet`='"
							+ mBean.getShopper_link_productMet() + "',`Shopper_link_productNameProvided`='"
							+ mBean.getShopper_link_productNameProvided() + "',`Shopper_link_productName`='"
							+ mBean.getShopper_link_productName() + "',`Shopper_link_productDesc`='"
							+ mBean.getShopper_link_productDesc() + "',`shopper_link_visit_date`='"
							+ mBean.getShopper_link_visit_date() + "',`created_by`='" + ip + "',`date_time`='"
							+ dateFormat.format(date) + "'  WHERE sk_shopper_id='" + mBean.getSk_shopper_id()
							+ "' AND visit_status='active'");
					log.info("saveMst_anwerData method return" + new Gson().toJson(mBean));
					return mBean;
				}

			});

		} else {
			String sql1 = "SELECT  mst_shopper_details.*, mst_brand.sk_brand_id,mst_dealer_outlet.outlet_id as oid, mst_brand.brand_name,mst_brand_model_variant.sk_variant_id,mst_brand_model_variant.variant_name,mst_brand_model.sk_model_id,mst_brand_model.model_name,mst_dealer.sk_dealer_id,mst_dealer.dealer_name,mst_dealer_outlet.*,mst_geo_region.region_name,mst_geo_city.city_name,mst_geo_city.sk_city_id,mst_geo_city.tier,mst_geo_city.metro FROM mst_shopper_details, mst_brand,mst_brand_model_variant,mst_brand_model,mst_dealer,mst_dealer_outlet,mst_geo_region,mst_geo_city WHERE mst_shopper_details.dealer_id=mst_dealer.sk_dealer_id AND mst_shopper_details.outlet_id=mst_dealer_outlet.sk_outlet_id AND  mst_shopper_details.brand_id=mst_brand.sk_brand_id AND mst_brand_model_variant.model_id=mst_brand_model.sk_model_id AND mst_shopper_details.brand_model_variant_id=mst_brand_model_variant.sk_variant_id AND mst_dealer_outlet.region_id=mst_geo_region.sk_region_id AND mst_dealer_outlet.city=mst_geo_city.sk_city_id AND mst_shopper_details.sk_shopper_id='"
					+ mBean.getSk_shopper_id()
					+ "' AND mst_shopper_details.status='active' AND mst_brand.brand_status='active' AND mst_brand_model_variant.variant_status='active' AND mst_brand_model.model_status='active' And mst_dealer.dealer_status='active' AND mst_dealer_outlet.outlet_status='active' AND mst_geo_region.region_status='active' AND mst_geo_city.city_status='active'";
			log.info("saveMst_anwerData method else part:"
					+ "SELECT  mst_shopper_details.*, mst_brand.sk_brand_id,mst_dealer_outlet.outlet_id as oid, mst_brand.brand_name,mst_brand_model_variant.sk_variant_id,mst_brand_model_variant.variant_name,mst_brand_model.sk_model_id,mst_brand_model.model_name,mst_dealer.sk_dealer_id,mst_dealer.dealer_name,mst_dealer_outlet.*,mst_geo_region.region_name,mst_geo_city.city_name,mst_geo_city.sk_city_id,mst_geo_city.tier,mst_geo_city.metro FROM mst_shopper_details, mst_brand,mst_brand_model_variant,mst_brand_model,mst_dealer,mst_dealer_outlet,mst_geo_region,mst_geo_city WHERE mst_shopper_details.dealer_id=mst_dealer.sk_dealer_id AND mst_shopper_details.outlet_id=mst_dealer_outlet.sk_outlet_id AND  mst_shopper_details.brand_id=mst_brand.sk_brand_id AND mst_brand_model_variant.model_id=mst_brand_model.sk_model_id AND mst_shopper_details.brand_model_variant_id=mst_brand_model_variant.sk_variant_id AND mst_dealer_outlet.region_id=mst_geo_region.sk_region_id AND mst_dealer_outlet.city=mst_geo_city.sk_city_id AND mst_shopper_details.sk_shopper_id='"
					+ mBean.getSk_shopper_id()
					+ "' AND mst_shopper_details.status='active' AND mst_brand.brand_status='active' AND mst_brand_model_variant.variant_status='active' AND mst_brand_model.model_status='active' And mst_dealer.dealer_status='active' AND mst_dealer_outlet.outlet_status='active' AND mst_geo_region.region_status='active' AND mst_geo_city.city_status='active'");

			return template.queryForObject(sql1, new RowMapper<MysteryShoppingVisitsBean>() {
				public MysteryShoppingVisitsBean mapRow(ResultSet rs, int row) throws SQLException {

					InetAddress ip = null;
					try {
						ip = InetAddress.getLocalHost();
					} catch (UnknownHostException e) {
						e.printStackTrace();
					}
					log.info("shooper name" + rs.getString("shopper_link_name"));
					String metro = "";
					if (rs.getString("metro").equals(0) || rs.getString("metro").equals("No")) {
						metro = "Non-Metro";
					} else {
						metro = "Metro";
					}
					log.info("saveMst_anwerData method Insert to DB:"
							+ "INSERT INTO `mst_msm_result`(`shopper_id`, `mode_of_contact`, `quarter`, `year`, `visit_brand_id`, `visit_brand_name`, `visit_brand_model_id`, `visit_brand_model_name`, `brand_varient_id`, `brand_varient_name`, `dealership_id`, `dealership_name`, `outlet_id`, `outlet_name`, `location_id`, `outlet_address`, `outlet_pincode`, `outlet_region_id`, `outlet_region_name`, `outlet_city_id`, `outlet_city_name`, `city_tier`, `city_metro`, `salesperson_name`, `salesperson_desc`, `scheduled_date`, `shopper_link_name`, `shopper_link_age`, `shopper_link_email`, `shopper_link_contact`, `shopper_link_gender`, `shoppers_link_shoppersCarBrand`, `shoppers_link_shoppersCarModel`, `shoppers_link_shoppersCarYop`, `Shopper_link_productMet`, `Shopper_link_productNameProvided`, `Shopper_link_productName`, `Shopper_link_productDesc`, `shopper_link_visit_date`, `start_time`, `end_time`, `visit_status`, `created_by`, `created_on`, `status`) VALUES ('"
							+ rs.getString("sk_shopper_id") + "','" + rs.getString("mode_of_contact") + "','"
							+ rs.getString("quarter") + "','" + rs.getString("year") + "','" + rs.getString("brand_id")
							+ "','" + rs.getString("brand_name") + "','" + rs.getString("sk_model_id") + "','"
							+ rs.getString("model_name") + "','" + rs.getString("brand_model_variant_id") + "','"
							+ rs.getString("variant_name") + "','" + rs.getString("sk_dealer_id") + "','"
							+ rs.getString("dealer_name") + "','" + rs.getString("sk_outlet_id") + "','"
							+ rs.getString("outlet_name") + "','" + rs.getString("oid") + "','"
							+ rs.getString("address") + "','" + rs.getString("pincode") + "','"
							+ rs.getString("region_id") + "','" + rs.getString("region_name") + "','"
							+ rs.getString("sk_city_id") + "','" + rs.getString("city_name") + "','"
							+ rs.getString("tier") + "','" + metro + "','" + rs.getString("sc_name") + "','"
							+ rs.getString("sc_description") + "','" + rs.getString("visit_date") + "','"
							+ rs.getString("shopper_link_name") + "','" + rs.getString("age") + "','"
							+ rs.getString("email") + "','" + rs.getString("contact_number") + "','"
							+ rs.getString("gender") + "','" + rs.getString("shoppers_car_owned_brand") + "','"
							+ rs.getString("shoppers_car_owned") + "','" + rs.getString("shoppers_car_yop") + "','"
							+ mBean.getShopper_link_productMet() + "','" + mBean.getShopper_link_productNameProvided()
							+ "','" + mBean.getShopper_link_productName() + "','" + mBean.getShopper_link_productDesc()
							+ "','" + rs.getString("shopper_link_visit_date") + "','" + rs.getString("start_time")
							+ "','" + rs.getString("end_time") + "','scheduled','" + ip + "','"
							+ dateFormat.format(date) + "','active')");
					template.execute(
							"INSERT INTO `mst_msm_result`(`shopper_id`, `mode_of_contact`, `quarter`, `year`, `visit_brand_id`, `visit_brand_name`, `visit_brand_model_id`, `visit_brand_model_name`, `brand_varient_id`, `brand_varient_name`, `dealership_id`, `dealership_name`, `outlet_id`, `outlet_name`, `location_id`, `outlet_address`, `outlet_pincode`, `outlet_region_id`, `outlet_region_name`, `outlet_city_id`, `outlet_city_name`, `city_tier`, `city_metro`, `salesperson_name`, `salesperson_desc`, `scheduled_date`, `shopper_link_name`, `shopper_link_age`, `shopper_link_email`, `shopper_link_contact`, `shopper_link_gender`, `shoppers_link_shoppersCarBrand`, `shoppers_link_shoppersCarModel`, `shoppers_link_shoppersCarYop`, `Shopper_link_productMet`, `Shopper_link_productNameProvided`, `Shopper_link_productName`, `Shopper_link_productDesc`, `shopper_link_visit_date`, `start_time`, `end_time`, `visit_status`, `created_by`, `created_on`, `status`) VALUES ('"
									+ rs.getString("sk_shopper_id") + "','" + rs.getString("mode_of_contact") + "','"
									+ rs.getString("quarter") + "','" + rs.getString("year") + "','"
									+ rs.getString("brand_id") + "','" + rs.getString("brand_name") + "','"
									+ rs.getString("sk_model_id") + "','" + rs.getString("model_name") + "','"
									+ rs.getString("brand_model_variant_id") + "','" + rs.getString("variant_name")
									+ "','" + rs.getString("sk_dealer_id") + "','" + rs.getString("dealer_name") + "','"
									+ rs.getString("sk_outlet_id") + "','" + rs.getString("outlet_name") + "','"
									+ rs.getString("oid") + "','" + rs.getString("address") + "','"
									+ rs.getString("pincode") + "','" + rs.getString("region_id") + "','"
									+ rs.getString("region_name") + "','" + rs.getString("sk_city_id") + "','"
									+ rs.getString("city_name") + "','" + rs.getString("tier") + "','" + metro + "','"
									+ rs.getString("sc_name") + "','" + rs.getString("sc_description") + "','"
									+ rs.getString("visit_date") + "','" + rs.getString("shopper_link_name") + "','"
									+ rs.getString("age") + "','" + rs.getString("email") + "','"
									+ rs.getString("contact_number") + "','" + rs.getString("gender") + "','"
									+ rs.getString("shoppers_car_owned_brand") + "','"
									+ rs.getString("shoppers_car_owned") + "','" + rs.getString("shoppers_car_yop")
									+ "','" + mBean.getShopper_link_productMet() + "','"
									+ mBean.getShopper_link_productNameProvided() + "','"
									+ mBean.getShopper_link_productName() + "','" + mBean.getShopper_link_productDesc()
									+ "','" + rs.getString("shopper_link_visit_date") + "','"
									+ rs.getString("start_time") + "','" + rs.getString("end_time") + "','scheduled','"
									+ ip + "','" + dateFormat.format(date) + "','active')");
					// template.execute("UPDATE `mst_shopper_details` SET
					// `start_time`='"+rs.getString("start_time")+"',`end_time`='"+rs.getString("end_time")+"',`met_sc`='"+rs.getString("met_sc")+"',`sc_name`='"+rs.getString("sc_name")+"',`sc_designation`='"+rs.getString("sc_designation")+"',`sc_description`='"+rs.getString("sc_description")+"',`shopper_link_name`='"+rs.getString("shopper_link_name")+"',`shopper_link_age`='"+rs.getString("")+"',`shopper_link_email`='"++"',`shopper_link_contact`='"++"',`shopper_link_gender`='"++"',`shoppers_link_shoppersCarBrand`='"++"',`shoppers_link_shoppersCarModel`='"++"',`shoppers_link_shoppersCarYop`='"++"',`Shopper_link_productMet`='"++"',`Shopper_link_productNameProvided`='"++"',`Shopper_link_productName`='"++"',`Shopper_link_productDesc`='"++"',`shopper_link_visit_date`='"++"',`created_by`=[value-38],`date_time`=[value-39]
					// WHERE sk_shopper_id='"++"' AND visit_status='active'");
					log.info("saveMst_anwerData method return" + new Gson().toJson(mBean));
					return mBean;
				}

			});

		}

	}

	protected String getTierAndMetroNameByOutletId(String city_id) {
		log.info("getTierAndMetroNameByOutletId method");
		log.info("getTierAndMetroNameByOutletId method :" + "SELECT tier,metro FROM mst_geo_city WHERE sk_city_id='"
				+ city_id + "'");
		return template.queryForObject("SELECT tier,metro FROM mst_geo_city WHERE sk_city_id='" + city_id + "'",
				new RowMapper<String>() {
					public String mapRow(ResultSet rs, int row) throws SQLException {
						String data = "";
						if (rs.getString("metro").equals(0) || rs.getString("metro").equals("No")) {
							data = rs.getString("tier") + "@" + "Non-Metro";
						} else {
							data = rs.getString("tier") + "@" + "Metro";
						}

						return data;

					}

				});
	}

	public MysteryShoppingVisitsBean checkMainquestionexist(MysteryShoppingVisitsBean mBean) {
		log.info("checkMainquestionexist method" + new Gson().toJson(mBean));
		log.info("checkMainquestionexist method :" + "SELECT * FROM `mys_txn_answers` WHERE `shopper_id`='"
				+ mBean.getSk_shopper_id() + "' and `question_id`='" + mBean.getQuestion_id()
				+ "' and status='active'");
		return template.queryForObject(
				"SELECT * FROM `mys_txn_answers` WHERE `shopper_id`='" + mBean.getSk_shopper_id()
						+ "' and `question_id`='" + mBean.getQuestion_id() + "' and status='active'  ",
				new RowMapper<MysteryShoppingVisitsBean>() {
					public MysteryShoppingVisitsBean mapRow(ResultSet rs, int row) throws SQLException {
						mBean.setSk_answer_id(rs.getString("sk_answer_id"));
						log.info("checkMainquestionexist method return" + new Gson().toJson(mBean));
						return mBean;
					}

				});

	}

	public void updateMainQuestion(MysteryShoppingVisitsBean mBean) {
		if (mBean.getAnswer_type().contentEquals("Select/List")) {
			log.info("updateMainQuestion method" + new Gson().toJson(mBean));
			mBean.setOption_points(mBean.getOption_points());
			log.info("options points");
			try {
				getoptionsName(mBean);
			} catch (Exception e) {
				e.printStackTrace();
			}
		} else {
			log.info("question points");
			mBean.setOption_points(mBean.getQuestion_points());
		}
		String main_question_comment = "";
		String option_question_comment = "";
		String paragraph = "";
		String options = "";
		try {
			if (mBean.getMain_ques_comment().contentEquals("null") || mBean.getMain_ques_comment().isEmpty()) {
				main_question_comment = mBean.getMain_ques_comment();
			} else {
				main_question_comment = mBean.getMain_ques_comment().replace("'", "\\'");
			}

		} catch (Exception e) {
			main_question_comment = mBean.getMain_ques_comment();
		}
		try {
			if (mBean.getOption_comment().contentEquals("null") || mBean.getOption_comment().isEmpty()) {
				// option_question_comment="";
			} else {
				option_question_comment = mBean.getOption_comment().replace("'", "\\'");
			}
		} catch (Exception e) {

		}
		try {
			if (mBean.getAnswerParagraph().contentEquals("null") || mBean.getAnswerParagraph().isEmpty()) {
				// paragraph=mBean.getAnswerParagraph();
			} else {
				paragraph = mBean.getAnswerParagraph().replace("'", "\\'");
			}
		} catch (Exception e) {

		}
		try {
			if (mBean.getOptions().contentEquals("null") || mBean.getOptions().isEmpty()) {
				options = mBean.getOptions();
			} else {
				options = mBean.getOptions().replace("'", "\\'");
			}
		} catch (Exception e) {
			options = mBean.getOptions();
		}

		if (mBean.getQuestion_optiontype().contentEquals("no")) {
			mBean.setQuestion_optiontype("not answered");
		} else {
			mBean.setQuestion_optiontype("answered");
		}

		String sql = "SELECT COUNT(*) as exist,ifnull(sk_answer_id,'noid') as sk_answer_id  FROM `mys_txn_answers` WHERE `shopper_id`='"
				+ mBean.getSk_shopper_id() + "' and `question_id`='" + mBean.getQuestion_id() + "' and status='active'";
		log.info("updateMainQuestion method :" + sql);
		String count = "";
		String sk_answer_id = "";
		List<Map<String, Object>> list = template.queryForList(sql);
		for (Map<String, Object> row : list) {
			count = row.get("exist").toString();
			sk_answer_id = row.get("sk_answer_id").toString();
		}
		int id_status = Integer.parseInt(count);

		if (id_status > 0) {
			log.info("updating");
			log.info("updateMainQuestion method if id_status > 0 :"
					+ "UPDATE `mys_txn_answers` SET  `select_option_id`='" + mBean.getOptionid()
					+ "',`select_option_text`='" + options + "',`paragraph`='" + paragraph + "',`date`='"
					+ mBean.getAnswerDate() + "',`dateandtime`='" + mBean.getAnswerTime() + "', question_points='"
					+ mBean.getQuestion_points() + "',`scored_points`='" + mBean.getOption_points()
					+ "', `main_ques_comment`='" + main_question_comment + "',`select_option_comment`='"
					+ option_question_comment + "',answer_status='updated',mandatory_optional_status='"
					+ mBean.getQuestion_optiontype() + "'  WHERE  `sk_answer_id`='" + sk_answer_id
					+ "' and  `question_id`='" + mBean.getQuestion_id() + "' and `shopper_id`='"
					+ mBean.getSk_shopper_id() + "' and status='active'");
			template.execute("UPDATE `mys_txn_answers` SET  `select_option_id`='" + mBean.getOptionid()
					+ "',`select_option_text`='" + options + "',`paragraph`='" + paragraph + "',`date`='"
					+ mBean.getAnswerDate() + "',`dateandtime`='" + mBean.getAnswerTime() + "', question_points='"
					+ mBean.getQuestion_points() + "', `scored_points`='" + mBean.getOption_points()
					+ "', `main_ques_comment`='" + main_question_comment + "',`select_option_comment`='"
					+ option_question_comment + "',answer_status='updated' ,mandatory_optional_status='"
					+ mBean.getQuestion_optiontype() + "' WHERE  `sk_answer_id`='" + sk_answer_id
					+ "' and  `question_id`='" + mBean.getQuestion_id() + "' and `shopper_id`='"
					+ mBean.getSk_shopper_id() + "' and status='active'");

		} else {
			log.info("inserting");
			log.info("updateMainQuestion method else part :"
					+ "INSERT INTO `mys_txn_answers`(`shopper_id`, `question_id`,section_id,section_name,subsection_id,subsection_name, `question_code`, standard_number,`standard_number_sequence`, `question_type`, `question_text`, `answer_type`, `select_option_id`, `select_option_text`, `paragraph`, `date`, `dateandtime`, `formula_flag`, `question_points`, `scored_points`, `main_ques_comment`, `select_option_comment`,mandatory_optional_status) VALUES ('"
					+ mBean.getSk_shopper_id() + "','" + mBean.getQuestion_id() + "','" + mBean.getSection_id() + "','"
					+ mBean.getSection_name() + "','" + mBean.getSubsection_id() + "','" + mBean.getSubsection_name()
					+ "','" + mBean.getQuestion_code() + "','" + mBean.getStandard_number() + "','"
					+ mBean.getStandard_number_sequence() + "','" + mBean.getQuestion_type() + "','"
					+ mBean.getQuestions().replace("'", "\\'") + "','" + mBean.getAnswer_type() + "','"
					+ mBean.getOptionid() + "','" + options + "','" + paragraph + "','" + mBean.getAnswerDate() + "','"
					+ mBean.getAnswerTime() + "','" + mBean.getFormula_flag() + "','" + mBean.getQuestion_points()
					+ "','" + mBean.getOption_points() + "','" + main_question_comment + "','" + option_question_comment
					+ "','" + mBean.getQuestion_optiontype() + "')");
			template.execute(
					"INSERT INTO `mys_txn_answers`(`shopper_id`, `question_id`,section_id,section_name,subsection_id,subsection_name, `question_code`,standard_number, `standard_number_sequence`, `question_type`, `question_text`, `answer_type`, `select_option_id`, `select_option_text`, `paragraph`, `date`, `dateandtime`, `formula_flag`, `question_points`, `scored_points`, `main_ques_comment`, `select_option_comment`,mandatory_optional_status) VALUES ('"
							+ mBean.getSk_shopper_id() + "','" + mBean.getQuestion_id() + "','" + mBean.getSection_id()
							+ "','" + mBean.getSection_name() + "','" + mBean.getSubsection_id() + "','"
							+ mBean.getSubsection_name() + "','" + mBean.getQuestion_code() + "','"
							+ mBean.getStandard_number() + "','" + mBean.getStandard_number_sequence() + "','"
							+ mBean.getQuestion_type() + "','" + mBean.getQuestions().replace("'", "\\'") + "','"
							+ mBean.getAnswer_type() + "','" + mBean.getOptionid() + "','" + options + "','" + paragraph
							+ "','" + mBean.getAnswerDate() + "','" + mBean.getAnswerTime() + "','"
							+ mBean.getFormula_flag() + "','" + mBean.getQuestion_points() + "','"
							+ mBean.getOption_points() + "','" + main_question_comment + "','" + option_question_comment
							+ "','" + mBean.getQuestion_optiontype() + "')");

		}

	}

	public MysteryShoppingVisitsBean checkMainSubquestionexist(MysteryShoppingVisitsBean mBean,
			List<MysteryShoppingVisitsBean> answerdata) {
		for (MysteryShoppingVisitsBean mysteryShoppingVisitsBean : answerdata) {
			log.info("checkMainSubquestionexist method" + new Gson().toJson(mBean));
			if (mysteryShoppingVisitsBean.getSubquestionid() != null) {
				if (mBean.getAnswer_type().contentEquals("Select/List")) {

					mBean.setOption_points(mBean.getOption_points());
					log.info("options points");
					try {
						getoptionsName(mBean, mysteryShoppingVisitsBean);
					} catch (Exception e) {
						e.printStackTrace();
					}
				} else {
					log.info("question points");
					mBean.setOption_points(mBean.getQuestion_points());
				}

				return template.queryForObject("checkMainSubquestionexist method :"
						+ "SELECT * FROM `mys_txn_answers` WHERE `shopper_id`='" + mBean.getSk_shopper_id()
						+ "' and `question_id`='" + mBean.getQuestion_id() + "' and subquestion_id='"
						+ mysteryShoppingVisitsBean.getSubquestionid() + "' and status='active' ",
						new RowMapper<MysteryShoppingVisitsBean>() {
							public MysteryShoppingVisitsBean mapRow(ResultSet rs, int row) throws SQLException {
								mBean.setSk_answer_id(rs.getString("sk_answer_id"));
								return mBean;
							}

						});

			}
		}
		log.info("checkMainSubquestionexist method return" + new Gson().toJson(mBean));
		return mBean;
	}

	public MysteryShoppingVisitsBean updateMainSubQuestion(MysteryShoppingVisitsBean mBean,
			List<MysteryShoppingVisitsBean> answerdata) {

		log.info("updateMainSubQuestion method" + new Gson().toJson(mBean));
		String main_question_comment = "";
		String option_question_comment = "";
		String paragraph = "";
		String options = "";
		try {
			if (mBean.getMain_ques_comment().contentEquals("null") || mBean.getMain_ques_comment().isEmpty()) {
				main_question_comment = mBean.getMain_ques_comment();
			} else {
				main_question_comment = mBean.getMain_ques_comment().replace("'", "\\'");
			}

		} catch (Exception e) {
			main_question_comment = mBean.getMain_ques_comment();
		}

		for (MysteryShoppingVisitsBean mysteryShoppingVisitsBean : answerdata) {
			if (mysteryShoppingVisitsBean.getSubquestionid() != null) {
				if (mysteryShoppingVisitsBean.getAnswer_type().contentEquals("Select/List")) {

					mBean.setOption_points(mBean.getOption_points());
					log.info("options points");
					try {
						getoptionsName(mBean, mysteryShoppingVisitsBean);
						log.info("sjlhfdlsdflk" + mBean.getOptions());
					} catch (Exception e) {
						e.printStackTrace();
					}

					try {
						getMaxOptionPoints(mBean, mysteryShoppingVisitsBean);
						log.info("max points==" + mBean.getMax_option_point());
					} catch (Exception e) {
						e.printStackTrace();
					}

				} else if (mysteryShoppingVisitsBean.getAnswer_type().contentEquals("Date & Time")) {

					try {
						getMaxdatetimePoints(mBean, mysteryShoppingVisitsBean);
						log.info("max points==" + mBean.getMax_option_point());
						mBean.setOption_points(mBean.getMax_option_point());
						mBean.setOptions("");
						mBean.setOptionid("");

					} catch (Exception e) {
						e.printStackTrace();
					}

				} else if (mysteryShoppingVisitsBean.getAnswer_type().contentEquals("Date")) {
					try {
						getMaxdatePoints(mBean, mysteryShoppingVisitsBean);
						log.info("max points==" + mBean.getMax_option_point());
						mBean.setOption_points(mBean.getMax_option_point());
						mBean.setOptions("");
						mBean.setOptionid("");
					} catch (Exception e) {
						e.printStackTrace();
					}

				} else {

					log.info("question points");
					mBean.setOption_points("0");// front end doesnot allow to enter points for para.. so its 0
					mBean.setMax_option_point("0");// front end doesnot allow to enter points for para.. so its 0
					mBean.setOptions("");
					mBean.setOptionid("");
				}
				try {
					if (mysteryShoppingVisitsBean.getOption_comment().contentEquals("null")
							|| mysteryShoppingVisitsBean.getOption_comment().isEmpty()) {
						option_question_comment = mysteryShoppingVisitsBean.getOption_comment();
					} else {
						option_question_comment = mysteryShoppingVisitsBean.getOption_comment().replace("'", "\\'");
					}
				} catch (Exception e) {
					option_question_comment = mysteryShoppingVisitsBean.getOption_comment();
				}

				try {
					if (mysteryShoppingVisitsBean.getAnswerParagraph().contentEquals("null")
							|| mysteryShoppingVisitsBean.getAnswerParagraph().isEmpty()) {
						paragraph = mysteryShoppingVisitsBean.getAnswerParagraph();
					} else {
						paragraph = mysteryShoppingVisitsBean.getAnswerParagraph().replace("'", "\\'");
					}
				} catch (Exception e) {
					paragraph = mysteryShoppingVisitsBean.getAnswerParagraph();
				}
				if (mBean.getOptions().contentEquals("null") || mBean.getOptions().isEmpty()) {
					options = mBean.getOptions();
				} else {
					options = mBean.getOptions().replace("'", "\\'");
				}

				if (mBean.getQuestion_optiontype().contentEquals("no")) {
					mBean.setQuestion_optiontype("not answered");
				} else {
					mBean.setQuestion_optiontype("answered");
				}

				String sql = "SELECT COUNT(*) as exist,ifnull(sk_answer_id,'noid') as sk_answer  from mys_txn_answers WHERE shopper_id='"
						+ mBean.getSk_shopper_id() + "' and question_id='" + mBean.getQuestion_id()
						+ "' and subquestion_id ='" + mysteryShoppingVisitsBean.getSubquestionid()
						+ "' and status='active'";
				log.info("updateMainSubQuestion method :" + sql);
				String count = "";
				String id = "";
				List<Map<String, Object>> list = template.queryForList(sql);
				for (Map<String, Object> row : list) {
					count = row.get("exist").toString();
					id = row.get("sk_answer").toString();
				}
				int id_status = Integer.parseInt(count);

				if (id_status > 0) {
					log.info("updateMainSubQuestion method id_status > 0 :"
							+ "UPDATE `mys_txn_answers` SET  `select_option_id`='"
							+ mysteryShoppingVisitsBean.getOptionid() + "',`select_option_text`='" + options
							+ "',`paragraph`='" + paragraph + "',`date`='" + mysteryShoppingVisitsBean.getAnswerDate()
							+ "',`dateandtime`='" + mysteryShoppingVisitsBean.getAnswerTime() + "', `scored_points`='"
							+ mBean.getOption_points() + "', `main_ques_comment`='" + main_question_comment
							+ "',`select_option_comment`='" + option_question_comment
							+ "',answer_status='updated' ,mandatory_optional_status='" + mBean.getQuestion_optiontype()
							+ "' WHERE  `sk_answer_id`='" + id + "' and  `question_id`='" + mBean.getQuestion_id()
							+ "' and `shopper_id`='" + mBean.getSk_shopper_id() + "' and subquestion_id ='"
							+ mysteryShoppingVisitsBean.getSubquestionid() + "' and status='active'");
					template.execute("UPDATE `mys_txn_answers` SET  `select_option_id`='"
							+ mysteryShoppingVisitsBean.getOptionid() + "',`select_option_text`='" + options
							+ "',`paragraph`='" + paragraph + "',`date`='" + mysteryShoppingVisitsBean.getAnswerDate()
							+ "',`dateandtime`='" + mysteryShoppingVisitsBean.getAnswerTime() + "', `scored_points`='"
							+ mBean.getOption_points() + "', `main_ques_comment`='" + main_question_comment
							+ "',`select_option_comment`='" + option_question_comment
							+ "',answer_status='updated' ,mandatory_optional_status='" + mBean.getQuestion_optiontype()
							+ "' WHERE  `sk_answer_id`='" + id + "' and  `question_id`='" + mBean.getQuestion_id()
							+ "' and `shopper_id`='" + mBean.getSk_shopper_id() + "' and subquestion_id ='"
							+ mysteryShoppingVisitsBean.getSubquestionid() + "'and status='active'");

				} else {
					log.info("updateMainSubQuestion method else part  :"
							+ "INSERT INTO `mys_txn_answers`(`shopper_id`, `question_id`,section_id,section_name,subsection_id,subsection_name, `question_code`,standard_number, `standard_number_sequence`, `question_type`, `question_text`, `subquestion_id`, `sub_question_text`, `answer_type`, `select_option_id`, `select_option_text`, `paragraph`, `date`, `dateandtime`, `formula_flag`, `question_points`, `scored_points`, `main_ques_comment`, `select_option_comment`,mandatory_optional_status) VALUES ('"
							+ mBean.getSk_shopper_id() + "','" + mBean.getQuestion_id() + "','" + mBean.getSection_id()
							+ "','" + mBean.getSection_name() + "','" + mBean.getSubsection_id() + "','"
							+ mBean.getSubsection_name() + "','" + mBean.getQuestion_code() + "','"
							+ mBean.getStandard_number() + "','" + mBean.getStandard_number_sequence() + "','"
							+ mBean.getQuestion_type() + "','" + mBean.getQuestions().replace("'", "\\'") + "','"
							+ mysteryShoppingVisitsBean.getSubquestionid() + "','"
							+ mysteryShoppingVisitsBean.getSubquestion().replace("'", "\\'") + "','"
							+ mysteryShoppingVisitsBean.getAnswer_type() + "','"
							+ mysteryShoppingVisitsBean.getOptionid() + "','" + options + "','" + paragraph + "','"
							+ mysteryShoppingVisitsBean.getAnswerDate() + "','"
							+ mysteryShoppingVisitsBean.getAnswerTime() + "','" + mBean.getFormula_flag() + "','"
							+ mBean.getMax_option_point() + "','" + mBean.getOption_points() + "','"
							+ main_question_comment + "','" + option_question_comment + "','"
							+ mBean.getQuestion_optiontype() + "')");
					template.execute(
							"INSERT INTO `mys_txn_answers`(`shopper_id`, `question_id`,section_id,section_name,subsection_id,subsection_name, `question_code`,standard_number, `standard_number_sequence`, `question_type`, `question_text`, `subquestion_id`, `sub_question_text`, `answer_type`, `select_option_id`, `select_option_text`, `paragraph`, `date`, `dateandtime`, `formula_flag`, `question_points`, `scored_points`, `main_ques_comment`, `select_option_comment`,mandatory_optional_status) VALUES ('"
									+ mBean.getSk_shopper_id() + "','" + mBean.getQuestion_id() + "','"
									+ mBean.getSection_id() + "','" + mBean.getSection_name() + "','"
									+ mBean.getSubsection_id() + "','" + mBean.getSubsection_name() + "','"
									+ mBean.getQuestion_code() + "','" + mBean.getStandard_number() + "','"
									+ mBean.getStandard_number_sequence() + "','" + mBean.getQuestion_type() + "','"
									+ mBean.getQuestions().replace("'", "\\'") + "','"
									+ mysteryShoppingVisitsBean.getSubquestionid() + "','"
									+ mysteryShoppingVisitsBean.getSubquestion().replace("'", "\\'") + "','"
									+ mysteryShoppingVisitsBean.getAnswer_type() + "','"
									+ mysteryShoppingVisitsBean.getOptionid() + "','" + options + "','" + paragraph
									+ "','" + mysteryShoppingVisitsBean.getAnswerDate() + "','"
									+ mysteryShoppingVisitsBean.getAnswerTime() + "','" + mBean.getFormula_flag()
									+ "','" + mBean.getMax_option_point() + "','" + mBean.getOption_points() + "','"
									+ main_question_comment + "','" + option_question_comment + "','"
									+ mBean.getQuestion_optiontype() + "')");

				}

			} else {
				log.info("sdhlsd");
			}
		}
		log.info("updateMainSubQuestion method return" + new Gson().toJson(mBean));
		return mBean;

	}

	private MysteryShoppingVisitsBean getMaxdatePoints(MysteryShoppingVisitsBean mBean,
			MysteryShoppingVisitsBean mysteryShoppingVisitsBean) {
		String sql = "SELECT sum(mst_questionare_subquestion.subdate_points) as sumpoint FROM mst_questionare_subquestion WHERE `sk_subquestion_id`='"
				+ mysteryShoppingVisitsBean.getSubquestionid()
				+ "' and mst_questionare_subquestion.subquestion_status='active' ";
		log.info("getMaxdatePoints method" + new Gson().toJson(mBean));
		log.info("getMaxdatePoints method :" + sql);
		return template.queryForObject(sql, new RowMapper<MysteryShoppingVisitsBean>() {
			public MysteryShoppingVisitsBean mapRow(ResultSet rs, int row) throws SQLException {
				mBean.setMax_option_point(rs.getString("sumpoint"));
				log.info("getMaxdatePoints method return" + new Gson().toJson(mBean));
				return mBean;
			}

		});
	}

	private MysteryShoppingVisitsBean getMaxdatetimePoints(MysteryShoppingVisitsBean mBean,
			MysteryShoppingVisitsBean mysteryShoppingVisitsBean) {
		String sql = "SELECT sum(mst_questionare_subquestion.subdate_points+mst_questionare_subquestion.subtime_points) as sumpoint FROM mst_questionare_subquestion WHERE `sk_subquestion_id`='"
				+ mysteryShoppingVisitsBean.getSubquestionid()
				+ "' and mst_questionare_subquestion.subquestion_status='active' ";
		log.info("getMaxdatetimePoints method" + new Gson().toJson(mBean));
		log.info("getMaxdatetimePoints method :" + sql);
		return template.queryForObject(sql, new RowMapper<MysteryShoppingVisitsBean>() {
			public MysteryShoppingVisitsBean mapRow(ResultSet rs, int row) throws SQLException {
				mBean.setMax_option_point(rs.getString("sumpoint"));
				log.info("getMaxdatetimePoints method return" + new Gson().toJson(mBean));
				return mBean;
			}

		});
	}

	private MysteryShoppingVisitsBean getMaxOptionPoints(MysteryShoppingVisitsBean mBean,
			MysteryShoppingVisitsBean mysteryShoppingVisitsBean) {
		String sql = "SELECT ifnull(max(`option_points`),'0') as option_points FROM `mst_questionare_selectoption` WHERE `subquestion_id`='"
				+ mysteryShoppingVisitsBean.getSubquestionid() + "' and `options_status`='active' ";
		log.info("getMaxOptionPoints method" + new Gson().toJson(mBean));
		log.info("getMaxOptionPoints method :" + sql);
		return template.queryForObject(sql, new RowMapper<MysteryShoppingVisitsBean>() {
			public MysteryShoppingVisitsBean mapRow(ResultSet rs, int row) throws SQLException {
				mBean.setMax_option_point(rs.getString("option_points"));
				log.info("getMaxOptionPoints method return" + new Gson().toJson(mBean));
				return mBean;
			}

		});
	}

	public MysteryShoppingVisitsBean addFormularesult(MysteryShoppingVisitsBean mBean, String result,
			String formula_id) {
		String sql = "SELECT COUNT(*) as exist,ifnull(sk_answer,'noid') as sk_answer  from mys_txn_formulaanswer WHERE shopper_id='"
				+ mBean.getSk_shopper_id() + "' and question_id='" + mBean.getQuestion_id() + "' and status='active'";
		log.info("addFormularesult method" + new Gson().toJson(mBean));
		log.info("addFormularesult method :" + sql);
		String count = "";
		String id = "";
		List<Map<String, Object>> list = template.queryForList(sql);
		for (Map<String, Object> row : list) {
			count = row.get("exist").toString();
			id = row.get("sk_answer").toString();
		}
		int id_status = Integer.parseInt(count);

		if (id_status > 0) {
			log.info("addFormularesult method id_status > 0 :"
					+ "update mys_txn_formulaanswer set `question_formula_id`='" + formula_id + "', `points`='" + result
					+ "' where question_id='" + mBean.getQuestion_id() + "' and sk_answer='" + id
					+ "' and status='active'");
			template.execute("update mys_txn_formulaanswer set `question_formula_id`='" + formula_id + "', `points`='"
					+ result + "'  where question_id='" + mBean.getQuestion_id() + "' and sk_answer='" + id
					+ "' and status='active' ");
		} else {
			log.info("addFormularesult method else part :"
					+ "INSERT INTO `mys_txn_formulaanswer`( `standard_num`, `shopper_id`, `question_id`, `question_formula_flag`, `question_formula_id`, `points`) VALUES ('"
					+ mBean.getStandard_number() + "','" + mBean.getSk_shopper_id() + "','" + mBean.getQuestion_id()
					+ "','" + mBean.getFormula_flag() + "','" + formula_id + "','" + result + "')");
			template.execute(
					"INSERT INTO `mys_txn_formulaanswer`( `standard_num`, `shopper_id`, `question_id`, `question_formula_flag`, `question_formula_id`, `points`) VALUES ('"
							+ mBean.getStandard_number() + "','" + mBean.getSk_shopper_id() + "','"
							+ mBean.getQuestion_id() + "','" + mBean.getFormula_flag() + "','" + formula_id + "','"
							+ result + "')");
		}

		log.info("addFormularesult method return" + new Gson().toJson(mBean));
		return mBean;

	}

	public List<MysteryShoppingVisitsBean> getformulacount(MysteryShoppingVisitsBean mBean, String sid) {
		log.info("getformulacount method" + new Gson().toJson(mBean));
		log.info("formula in dao");
		log.info("getformulacount method First :"
				+ " first==SELECT GROUP_CONCAT(DISTINCT CONCAT('\\'', mst_formula_map.formula_id, '\\'') ) as formula_id from mst_formula_map WHERE question_id='"
				+ mBean.getQuestion_id() + "'");
		return template.query(
				"SELECT GROUP_CONCAT(DISTINCT CONCAT('\\'', mst_formula_map.formula_id, '\\'') ) as formula_id from mst_formula_map WHERE question_id='"
						+ mBean.getQuestion_id() + "' ",

				new RowMapper<MysteryShoppingVisitsBean>() {
					public MysteryShoppingVisitsBean mapRow(ResultSet rs, int row) throws SQLException {

						String formula_id = rs.getString("formula_id");
						mBean.setSk_shopper_id(sid);
						try {

							log.info("getformulacount method Second :"
									+ "second ==SELECT (CASE WHEN res1.res1_count = res2.res2_count THEN res2.result ELSE '0' END  ) result,res2.`sk_formula_map_id`,res2.formula_id FROM((SELECT count(*)as res1_count ,mst_formula_map.* FROM `mst_formula_map` WHERE `question_id`='"
									+ mBean.getQuestion_id()
									+ "'  GROUP by `formula_id`) res1 JOIN (select count(*)as res2_count, mst_formula_map.* from mst_formula_map,mys_txn_answers WHERE mst_formula_map.question_id='"
									+ mBean.getQuestion_id()
									+ "' and mys_txn_answers.select_option_id=mst_formula_map.option_id and formula_id IN ("
									+ formula_id + ") AND shopper_id='" + mBean.getSk_shopper_id()
									+ "' AND mys_txn_answers.status='active'  GROUP by mst_formula_map.formula_id )res2) ORDER BY result DESC LIMIT 1");
							return template.queryForObject(
									"SELECT (CASE WHEN res1.res1_count = res2.res2_count THEN res2.result ELSE '0' END  ) result,res2.`sk_formula_map_id`,res2.formula_id FROM((SELECT count(*)as res1_count ,mst_formula_map.* FROM `mst_formula_map` WHERE `question_id`='"
											+ mBean.getQuestion_id()
											+ "'  GROUP by `formula_id`) res1 JOIN (select count(*)as res2_count, mst_formula_map.* from mst_formula_map,mys_txn_answers WHERE mst_formula_map.question_id='"
											+ mBean.getQuestion_id()
											+ "' and mys_txn_answers.select_option_id=mst_formula_map.option_id and formula_id IN ("
											+ formula_id + ") AND shopper_id='" + mBean.getSk_shopper_id()
											+ "' AND mys_txn_answers.status='active' GROUP by mst_formula_map.formula_id )res2) ORDER BY result DESC LIMIT 1",
									new RowMapper<MysteryShoppingVisitsBean>() {
										public MysteryShoppingVisitsBean mapRow(ResultSet rs, int row)
												throws SQLException {

											mBean.setResult(rs.getString("result"));
											if (rs.getString("result").contentEquals("0")) {
												mBean.setSk_formula_map_id(null);
											} else {
												mBean.setSk_formula_map_id(rs.getString("sk_formula_map_id"));
											}
											return mBean;
										}

									});
						} catch (Exception e) {
							mBean.setResult("0");
							mBean.setSk_formula_map_id(null);
						}
						log.info("getformulacount method return" + new Gson().toJson(mBean));
						return mBean;

					}

				});

	}

	public MysteryShoppingVisitsBean getMaxScorePoints(MysteryShoppingVisitsBean mBean, String shopper_id) {
		log.info("getMaxScorePoints method" + new Gson().toJson(mBean));
		log.info("getMaxScorePoints method :" + "DELETE FROM `mys_score` WHERE `shopper_id`='" + shopper_id + "';");
		template.execute("DELETE FROM `mys_score` WHERE `shopper_id`='" + shopper_id + "';");
		try {
			log.info("getMaxScorePoints method selecting max points :"
					+ "SELECT *,(max_points + IFNULL(f1_max_points,0)) as maximum_points,(max_scored_points + IFNULL(f1_scored_points,0)) as maximum_scored_points FROM(select * FROM(SELECT *,SUM(question_points) as max_points,SUM(scored_points) as max_scored_points FROM mys_txn_answers WHERE shopper_id='"
					+ shopper_id
					+ "' and formula_flag=0 and status='active' GROUP BY section_id,subsection_id) as res1 LEFT JOIN (SELECT mys_txn_formulaanswer.shopper_id as f1_shopper_id,mys_txn_formulaanswer.question_formula_flag,SUM(mys_txn_formulaanswer.points) as f1_scored_points,SUM(mst_questionare.question_points) as f1_max_points,mst_questionare.section_id as f1_section_id,mst_questionare.subsection_id as f1_subsection_id FROM mys_txn_formulaanswer LEFT JOIN mst_questionare ON mys_txn_formulaanswer.question_id=mst_questionare.sk_question_id  WHERE shopper_id='"
					+ shopper_id
					+ "' and question_formula_flag=1 and mys_txn_formulaanswer.status='active' GROUP BY section_id,subsection_id) as res2 ON res1.section_id=res2.f1_section_id AND res1.subsection_id=res2.f1_subsection_id) as res3  GROUP BY section_id,subsection_id ORDER BY section_id ASC");
			return template.queryForObject(
					"SELECT *,(max_points + IFNULL(f1_max_points,0)) as maximum_points,(max_scored_points + IFNULL(f1_scored_points,0)) as maximum_scored_points FROM(select * FROM(SELECT *,SUM(question_points) as max_points,SUM(scored_points) as max_scored_points FROM mys_txn_answers WHERE shopper_id='"
							+ shopper_id
							+ "' and formula_flag=0 and status='active'  GROUP BY section_id,subsection_id) as res1 LEFT JOIN (SELECT mys_txn_formulaanswer.shopper_id as f1_shopper_id,mys_txn_formulaanswer.question_formula_flag,SUM(mys_txn_formulaanswer.points) as f1_scored_points,SUM(mst_questionare.question_points) as f1_max_points,mst_questionare.section_id as f1_section_id,mst_questionare.subsection_id as f1_subsection_id FROM mys_txn_formulaanswer LEFT JOIN mst_questionare ON mys_txn_formulaanswer.question_id=mst_questionare.sk_question_id  WHERE shopper_id='"
							+ shopper_id
							+ "' and question_formula_flag=1 and mys_txn_formulaanswer.status='active'  GROUP BY section_id,subsection_id) as res2 ON res1.section_id=res2.f1_section_id AND res1.subsection_id=res2.f1_subsection_id) as res3  GROUP BY section_id,subsection_id ORDER BY section_id ASC ",
					new RowMapper<MysteryShoppingVisitsBean>() {
						public MysteryShoppingVisitsBean mapRow(ResultSet rs1, int row) throws SQLException {
							// log.info("question_points2=="+rs.getString("questionpoint1")+"===scored_points2="+rs.getString("scoredpoint1"));
							// log.info("question_points1=="+mBean.getQuestion_points()+"===scored_points1="+mBean.getScored_points());
							double maximum_points = Double.parseDouble(rs1.getString("maximum_points"));
							double maximum_scored_points = Double.parseDouble(rs1.getString("maximum_scored_points"));
							double percentage;
							if (maximum_scored_points != 0 && maximum_points != 0) {
								percentage = ((maximum_scored_points / maximum_points) * 100);
							} else {
								percentage = 0;
							}
							log.info("getMaxScorePoints method selecting month :"
									+ "SELECT *,month(scheduled_date)as months FROM `mst_msm_result` WHERE shopper_id='"
									+ shopper_id + "'");
							template.query(
									"SELECT *,month(scheduled_date)as months FROM `mst_msm_result` WHERE shopper_id='"
											+ shopper_id + "'",
									new RowMapper<MysteryShoppingVisitsBean>() {

										@Override
										public MysteryShoppingVisitsBean mapRow(ResultSet rs2, int rowNum)
												throws SQLException {

											String osc_flag = "";
											if (rs2.getString("mode_of_contact")
													.contentEquals("Online Sales Channel")) {
												osc_flag = "1";
											} else {
												osc_flag = "0";
											}
											InetAddress ip = null;
											try {
												ip = InetAddress.getLocalHost();
											} catch (UnknownHostException e) {

												e.printStackTrace();
											}

											log.info("getMaxScorePoints method Inserting :"
													+ "INSERT INTO `mys_score`(`shopper_id`, `osc_flag`, `outlet_id`, `Outlet_name`, `dealer_id`, `dealer_name`, `region_id`, `region_name`, `quarter`, `year`,month, `section_id`, `section_name`, `subsection_id`, `subsection_name`, `max_points`, `scored_points`, `percentage`, `rank`, `Created_by`, `created_on`, `status`) VALUES ('"
													+ shopper_id + "','" + osc_flag + "','" + rs2.getString("outlet_id")
													+ "','" + rs2.getString("outlet_name") + "','"
													+ rs2.getString("dealership_id") + "','"
													+ rs2.getString("dealership_name") + "','"
													+ rs2.getString("outlet_region_id") + "','"
													+ rs2.getString("outlet_region_name") + "','"
													+ rs2.getString("quarter") + "','" + rs2.getString("year") + "','"
													+ rs2.getString("months") + "','" + rs1.getString("section_id")
													+ "','" + rs1.getString("section_name") + "','"
													+ rs1.getString("subsection_id") + "','"
													+ rs1.getString("subsection_name") + "','" + maximum_points + "','"
													+ maximum_scored_points + "',ROUND('" + percentage + "',2),'rank','"
													+ ip + "','" + dateFormat.format(date) + "','active'");
											template.execute(
													"INSERT INTO `mys_score`(`shopper_id`, `osc_flag`, `outlet_id`, `Outlet_name`, `dealer_id`, `dealer_name`, `region_id`, `region_name`, `quarter`, `year`,month, `section_id`, `section_name`, `subsection_id`, `subsection_name`, `max_points`, `scored_points`, `percentage`, `rank`, `Created_by`, `created_on`, `status`) VALUES ('"
															+ shopper_id + "','" + osc_flag + "','"
															+ rs2.getString("outlet_id") + "','"
															+ rs2.getString("outlet_name") + "','"
															+ rs2.getString("dealership_id") + "','"
															+ rs2.getString("dealership_name") + "','"
															+ rs2.getString("outlet_region_id") + "','"
															+ rs2.getString("outlet_region_name") + "','"
															+ rs2.getString("quarter") + "','" + rs2.getString("year")
															+ "','" + rs2.getString("months") + "','"
															+ rs1.getString("section_id") + "','"
															+ rs1.getString("section_name") + "','"
															+ rs1.getString("subsection_id") + "','"
															+ rs1.getString("subsection_name") + "','" + maximum_points
															+ "','" + maximum_scored_points + "',ROUND('" + percentage
															+ "',2),'rank','" + ip + "','" + dateFormat.format(date)
															+ "','active')");

											return mBean;
										}

									});

							return mBean;
						}

					});
		} catch (Exception e) {
			e.printStackTrace();
			log.info("Exception getMaxScorePoints method" + e);
		}
		MysteryShoppingVisitsBean mvBean1 = new MysteryShoppingVisitsBean();
		mvBean1 = getShopperYear(mvBean1, shopper_id);
		String year = mvBean1.getYear();
		log.info("yearin shopper year" + year);
		addOutletScore(mBean, shopper_id, year);
		getshopperdetails(mBean, shopper_id);
		addDealerScore(mBean, shopper_id, mBean.getYear(), mBean.getMonth(), mBean.getDealer_id(), mBean.getBrand_id());
		log.info("getMaxScorePoints method return" + new Gson().toJson(mBean));
		return mBean;

	}

	/*
	 * public List<MysteryShoppingVisitsBean>
	 * getMaxScorePoints(MysteryShoppingVisitsBean mBean, String shopper_id) {
	 * String
	 * sql="SELECT *,SUM(question_points) as questionpoints,SUM(scored_points) as scoredpoints FROM mys_txn_answers WHERE shopper_id="
	 * +shopper_id+" GROUP BY section_id,subsection_id order by section_id DESC";
	 * log.info(sql); return template.
	 * query("SELECT *,SUM(question_points) as questionpoints,SUM(scored_points) as scoredpoints FROM mys_txn_answers WHERE shopper_id="
	 * +shopper_id+" GROUP BY section_id,subsection_id order by section_id DESC",
	 * new RowMapper<MysteryShoppingVisitsBean>() { public MysteryShoppingVisitsBean
	 * mapRow(ResultSet rs1, int row) throws SQLException {
	 * MysteryShoppingVisitsBean mBean =new MysteryShoppingVisitsBean();
	 * 
	 * mBean.setQuestion_points(rs1.getString("questionpoints"));
	 * mBean.setScored_points(rs1.getString("scoredpoints"));
	 * //log.info(rs1.getString("questionpoints")+"====="+rs1.getString(
	 * "scoredpoints")); //log.
	 * info("SELECT mys_txn_formulaanswer.shopper_id,mys_txn_formulaanswer.question_id,SUM(mys_txn_formulaanswer.points) as scored_points,mst_questionare.section_id,mst_questionare.subsection_id,SUM(mst_questionare.question_points) as question_points FROM mys_txn_formulaanswer LEFT JOIN mst_questionare ON mys_txn_formulaanswer.question_id=mst_questionare.sk_question_id WHERE mys_txn_formulaanswer.shopper_id=1 GROUP BY section_id,subsection_id"
	 * ); log.
	 * info("SELECT COUNT(mys_txn_formulaanswer.shopper_id),mys_txn_formulaanswer.shopper_id,mys_txn_formulaanswer.question_id,ifnull(SUM(mys_txn_formulaanswer.points),'0') as scoredpoint1,mst_questionare.section_id,mst_questionare.subsection_id,ifnull(SUM(mst_questionare.question_points),'0') as questionpoint1 FROM mys_txn_formulaanswer LEFT JOIN mst_questionare ON mys_txn_formulaanswer.question_id=mst_questionare.sk_question_id WHERE mys_txn_formulaanswer.shopper_id="
	 * +shopper_id+" and  mst_questionare.section_id='"+rs1.getString("section_id")
	 * +"' and mst_questionare.subsection_id='"+rs1.getString("section_id")+"' ");
	 * return template.
	 * queryForObject("SELECT COUNT(mys_txn_formulaanswer.shopper_id),mys_txn_formulaanswer.shopper_id,mys_txn_formulaanswer.question_id,ifnull(SUM(mys_txn_formulaanswer.points),'0') as scoredpoint1,mst_questionare.section_id,mst_questionare.subsection_id,ifnull(SUM(mst_questionare.question_points),'0') as questionpoint1 FROM mys_txn_formulaanswer LEFT JOIN mst_questionare ON mys_txn_formulaanswer.question_id=mst_questionare.sk_question_id WHERE mys_txn_formulaanswer.shopper_id="
	 * +shopper_id+" and  mst_questionare.section_id='"+rs1.getString("section_id")
	 * +"' and mst_questionare.subsection_id='"+rs1.getString("section_id")+"' ",
	 * new RowMapper<MysteryShoppingVisitsBean>() { public MysteryShoppingVisitsBean
	 * mapRow(ResultSet rs, int row) throws SQLException {
	 * //log.info("question_points2=="+rs.getString("questionpoint1")+
	 * "===scored_points2="+rs.getString("scoredpoint1"));
	 * //log.info("question_points1=="+mBean.getQuestion_points()+
	 * "===scored_points1="+mBean.getScored_points()); double
	 * questionPoits=Double.parseDouble(mBean.getQuestion_points())+
	 * Double.parseDouble(rs.getString("questionpoint1")); double
	 * scorePoints=Double.parseDouble(mBean.getScored_points())+Double.parseDouble(
	 * rs.getString("scoredpoint1")); double percentage; if(scorePoints!=0 &&
	 * questionPoits!=0){ percentage=((scorePoints/questionPoits)*100); }else{
	 * percentage=0; }
	 * 
	 * template.query("SELECT * FROM `mst_msm_result` WHERE shopper_id='"+shopper_id
	 * +"'", new RowMapper<MysteryShoppingVisitsBean>(){
	 * 
	 * @Override public MysteryShoppingVisitsBean mapRow(ResultSet rs2, int rowNum)
	 * throws SQLException { // TODO Auto-generated method stub String osc_flag="";
	 * if(rs2.getString("mode_of_contact")=="Online Sales Channel"){ osc_flag="1";
	 * }else{ osc_flag="0"; } template.
	 * execute("INSERT INTO `mys_score`(`shopper_id`, `osc_flag`, `outlet_id`, `Outlet_name`, `dealer_id`, `dealer_name`, `region_id`, `region_name`, `quarter`, `year`, `section_id`, `section_name`, `subsection_id`, `subsection_name`, `max_points`, `scored_points`, `percentage`, `rank`, `Created_by`, `created_on`, `status`) VALUES ('"
	 * +shopper_id+"','"+osc_flag+"','"+rs2.getString("outlet_id")+"','"+rs2.
	 * getString("outlet_name")+"','"+rs2.getString("dealership_id")+"','"+rs2.
	 * getString("dealership_name")+"','"+rs2.getString("outlet_region_id")+"','"+
	 * rs2.getString("outlet_region_name")+"','"+rs2.getString("quarter")+"','"+rs2.
	 * getString("year")+"','"+rs1.getString("section_id")+"','"+rs1.getString(
	 * "section_name")+"','"+rs1.getString("subsection_id")+"','"+rs1.getString(
	 * "subsection_name")+"','"+questionPoits+"','"+scorePoints+"','"+percentage+
	 * "','rank','createdby','createdon','active')"); return mBean; }
	 * 
	 * });
	 * 
	 * return mBean; }
	 * 
	 * 
	 * });
	 * 
	 * } }); }
	 */

	private MysteryShoppingVisitsBean getShopperYear(MysteryShoppingVisitsBean mvBean1, String shopper_id) {
		String sql1 = "select year from mst_shopper_details where sk_shopper_id='" + shopper_id + "'";
		log.info("getShopperYear method" + new Gson().toJson(mvBean1));
		log.info("getShopperYear method :" + sql1);
		return template.queryForObject(sql1, new RowMapper<MysteryShoppingVisitsBean>() {
			public MysteryShoppingVisitsBean mapRow(ResultSet rs, int row) throws SQLException {
				mvBean1.setYear(rs.getString("year"));
				log.info("getShopperYear method return" + new Gson().toJson(mvBean1));
				return mvBean1;
			}
		});

	}

	private MysteryShoppingVisitsBean getshopperdetails(MysteryShoppingVisitsBean mBean, String shopper_id) {
		String sql1 = "select *,month(mst_shopper_details.visit_date)as months from mst_shopper_details where sk_shopper_id='"
				+ shopper_id + "'";
		log.info("getshopperdetails method" + new Gson().toJson(mBean));
		log.info("getshopperdetails method :" + sql1);
		return template.queryForObject(sql1, new RowMapper<MysteryShoppingVisitsBean>() {
			public MysteryShoppingVisitsBean mapRow(ResultSet rs, int row) throws SQLException {
				mBean.setYear(rs.getString("year"));
				mBean.setMonth(rs.getString("months"));
				mBean.setDealer_id(rs.getString("dealer_id"));
				mBean.setBrand_id(rs.getString("brand_id"));
				log.info("getshopperdetails method return" + new Gson().toJson(mBean));
				return mBean;
			}
		});

	}

	private MysteryShoppingVisitsBean addDealerScore(MysteryShoppingVisitsBean mBean, String shopper_id, String year,
			String month, String dealer_id, String brand_id) {
		log.info("addDealerScore method" + new Gson().toJson(mBean));
		MysteryShoppingVisitsBean mvBean1 = new MysteryShoppingVisitsBean();
		mvBean1 = hDao.getScoreWeightage(mvBean1, year);
		String pe_weightage = mvBean1.getPe_weightage();
		String ct_weightage = mvBean1.getCt_weightage();
		String osc_weightage = mvBean1.getOsc_weightage();

		log.info("addDealerScore method :"
				+ "SELECT COUNT(*) as exist from mys_txn_dealer_score WHERE mys_txn_dealer_score.year='" + year
				+ "' and mys_txn_dealer_score.dealer_id='" + dealer_id + "' AND mys_txn_dealer_score.brand_id='"
				+ brand_id + "' AND mys_txn_dealer_score.month='" + month + "';");
		String sql = "SELECT COUNT(*) as exist from mys_txn_dealer_score WHERE mys_txn_dealer_score.year='" + year
				+ "' and mys_txn_dealer_score.dealer_id='" + dealer_id + "' AND mys_txn_dealer_score.brand_id='"
				+ brand_id + "' AND mys_txn_dealer_score.month='" + month + "';";
		String count = "";

		mBean.setMonth(month);

		List<Map<String, Object>> list = template.queryForList(sql);
		for (Map<String, Object> row : list) {
			count = row.get("exist").toString();

		}
		int id_status = Integer.parseInt(count);
		if (id_status > 0) {
			log.info("exist");

			String sql1 = "SELECT  osc_sc_pt, osc_mx_pt,scored_pt,max_pt,res2.dealer_id,res2.shopper_id ,month,year,brand_id,quater,(osc_sc_pt+scored_pt)as sc,(osc_mx_pt+max_pt) as mx,round((((osc_sc_pt+scored_pt)/(osc_mx_pt+max_pt))*100),2)as monthly_scored_per from (SELECT ifnull(round(sum(scored_points)*"
					+ osc_weightage + ",2),0) as osc_sc_pt,ifnull(round(sum(max_points)*" + osc_weightage
					+ ",2),0) as osc_mx_pt FROM mys_score WHERE year='" + year + "' and dealer_id='" + dealer_id
					+ "'  and month='" + month
					+ "' and osc_flag='1' )res1 JOIN (SELECT ifnull(round(sum(scored_point),2),0) as scored_pt, ifnull(round(sum(maximum_point),2),0)as max_pt,dealer_id,shopper_id,month,year,brand_id,quater FROM mys_txn_outlet_score WHERE year='"
					+ year + "' and dealer_id='" + dealer_id + "' and brand_id='" + brand_id + "' and month='" + month
					+ "' and osc_flag='0')as res2";
			// String sql1="SELECT round(ifnull(osc_sc_pt,0),2) as osc_sc_pt,
			// round(ifnull(osc_mx_pt,0),2) as osc_mx_pt,ifnull(scored_pt,0) as
			// scored_pt,ifnull(max_pt,0) as max_pt,res2.dealer_id,res2.shopper_id
			// ,month,year,brand_id,quater,round((ifnull(osc_sc_pt,0)+ifnull(scored_pt,0)),2)as
			// sc,(ifnull(osc_mx_pt,0)+ifnull(max_pt,0)) as
			// mx,round((((ifnull(osc_sc_pt,0)+ifnull(scored_pt,0))/(ifnull(osc_mx_pt,0)+ifnull(max_pt,0)))*100),2)as
			// monthly_scored_per from (SELECT sum(scored_points)*0.1 as
			// osc_sc_pt,sum(max_points)*0.1 as osc_mx_pt FROM mys_score WHERE
			// year='"+year+"' and dealer_id='"+dealer_id+"' and month='"+month+"' and
			// osc_flag='1')res1 JOIN (SELECT sum(scored_point) as
			// scored_pt,round(sum(maximum_point),2) as
			// max_pt,dealer_id,shopper_id,month,year,brand_id,quater FROM
			// mys_txn_outlet_score WHERE year='"+year+"' and dealer_id='"+dealer_id+"' and
			// brand_id='"+brand_id+"' and month='"+month+"')as res2";
			log.info("addDealerScore method if id_status > 0 :" + sql1);
			return template.queryForObject(sql1, new RowMapper<MysteryShoppingVisitsBean>() {
				public MysteryShoppingVisitsBean mapRow(ResultSet rs, int row) throws SQLException {
					String year = rs.getString("year");
					String month = mBean.getMonth();
					int mon = Integer.parseInt(month) - 1;
					String prevmonth = String.valueOf(mon);
					String dealer_id = rs.getString("dealer_id");
					String quater = rs.getString("quater");
					String scored_point = rs.getString("sc");
					String maximum_point = rs.getString("mx");
					String monthly_scored_per = rs.getString("monthly_scored_per");
					String brand_id = rs.getString("brand_id");
					String sql4 = "select ytd_score_percentage,ytd_score_percentage1,(ytd_score_percentage-ytd_score_percentage1)as movement from (SELECT ifnull(round((SUM(mys_txn_dealer_score.scored_point_721)/ SUM(mys_txn_dealer_score.maximum_point_721))*100,2),0) AS ytd_score_percentage FROM mys_txn_dealer_score WHERE brand_id='"
							+ brand_id + "' AND year='" + year + "' AND dealer_id='" + dealer_id + "' AND month<='"
							+ month
							+ "'   and mys_txn_dealer_score.dealer_score_status='active')res1 JOIN (SELECT ifnull(round((SUM(mys_txn_dealer_score.scored_point_721)/ SUM(mys_txn_dealer_score.maximum_point_721))*100,2),0) AS ytd_score_percentage1 FROM mys_txn_dealer_score WHERE brand_id='"
							+ brand_id + "' AND year='" + year + "' AND dealer_id='" + dealer_id + "' AND month<='"
							+ prevmonth + "'   and mys_txn_dealer_score.dealer_score_status='active')res2";

					log.info("addDealerScore method selecting ytd_score_percentage :" + sql4);
					return template.queryForObject(sql4, new RowMapper<MysteryShoppingVisitsBean>() {
						public MysteryShoppingVisitsBean mapRow(ResultSet rs, int row) throws SQLException {
							log.info("addDealerScore method updating query :"
									+ "UPDATE `mys_txn_dealer_score` SET  `scored_point_721`='" + scored_point
									+ "',`maximum_point_721`='" + maximum_point + "',`monthly_score_percentage`='"
									+ monthly_scored_per + "',`ytd_score_percentage`='"
									+ rs.getString("ytd_score_percentage") + "',`movement`='" + rs.getString("movement")
									+ "' WHERE mys_txn_dealer_score.year='" + year
									+ "' and mys_txn_dealer_score.dealer_id='" + dealer_id
									+ "' AND mys_txn_dealer_score.brand_id='" + brand_id
									+ "' AND mys_txn_dealer_score.month='" + month + "'");
							template.execute("UPDATE `mys_txn_dealer_score` SET  `scored_point_721`='" + scored_point
									+ "',`maximum_point_721`='" + maximum_point + "',`monthly_score_percentage`='"
									+ monthly_scored_per + "',`ytd_score_percentage`='"
									+ rs.getString("ytd_score_percentage") + "',`movement`='" + rs.getString("movement")
									+ "' WHERE mys_txn_dealer_score.year='" + year
									+ "' and mys_txn_dealer_score.dealer_id='" + dealer_id
									+ "' AND mys_txn_dealer_score.brand_id='" + brand_id
									+ "' AND mys_txn_dealer_score.month='" + month + "'");
							log.info("addDealerScore method return" + new Gson().toJson(mBean));

							return mBean;
						}

					});

				}

			});
		} else {
			log.info("not exist");
			String sql2 = "SELECT  osc_sc_pt, osc_mx_pt,scored_pt,max_pt,res2.dealer_id,res2.shopper_id ,month,year,brand_id,quater,(osc_sc_pt+scored_pt)as sc,(osc_mx_pt+max_pt) as mx,round((((osc_sc_pt+scored_pt)/(osc_mx_pt+max_pt))*100),2)as monthly_scored_per from (SELECT ifnull(round(sum(scored_points)*"
					+ osc_weightage + ",2),0) as osc_sc_pt,ifnull(round(sum(max_points)*" + osc_weightage
					+ ",2),0) as osc_mx_pt FROM mys_score WHERE year='" + year + "' and dealer_id='" + dealer_id
					+ "'  and month='" + month
					+ "' and osc_flag='1' )res1 JOIN (SELECT ifnull(round(sum(scored_point),2),0) as scored_pt, ifnull(round(sum(maximum_point),2),0)as max_pt,dealer_id,shopper_id,month,year,brand_id,quater FROM mys_txn_outlet_score WHERE year='"
					+ year + "' and dealer_id='" + dealer_id + "' and brand_id='" + brand_id + "' and month='" + month
					+ "' and osc_flag='0')as res2";
			// String sql2="SELECT round(ifnull(osc_sc_pt,0),2) as osc_sc_pt,
			// round(ifnull(osc_mx_pt,0),2) as osc_mx_pt,ifnull(scored_pt,0) as
			// scored_pt,ifnull(max_pt,0) as max_pt,res2.dealer_id,res2.shopper_id
			// ,month,year,brand_id,quater,round((ifnull(osc_sc_pt,0)+ifnull(scored_pt,0)),2)as
			// sc,(ifnull(osc_mx_pt,0)+ifnull(max_pt,0)) as
			// mx,round((((ifnull(osc_sc_pt,0)+ifnull(scored_pt,0))/(ifnull(osc_mx_pt,0)+ifnull(max_pt,0)))*100),2)as
			// monthly_scored_per from (SELECT sum(scored_points)*0.1 as
			// osc_sc_pt,sum(max_points)*0.1 as osc_mx_pt FROM mys_score WHERE
			// year='"+year+"' and dealer_id='"+dealer_id+"' and month='"+month+"' and
			// osc_flag='1')res1 JOIN (SELECT sum(scored_point) as
			// scored_pt,round(sum(maximum_point),2)as
			// max_pt,dealer_id,shopper_id,month,year,brand_id,quater FROM
			// mys_txn_outlet_score WHERE year='"+year+"' and dealer_id='"+dealer_id+"' and
			// brand_id='"+brand_id+"' and month='"+month+"')as res2";
			log.info("addDealerScore method else part :"+sql2);
			return template.queryForObject(sql2, new RowMapper<MysteryShoppingVisitsBean>() {
				public MysteryShoppingVisitsBean mapRow(ResultSet rs, int row) throws SQLException {
					String year = rs.getString("year");
					String month = mBean.getMonth();
					int mon = Integer.parseInt(month) - 1;
					String prevmonth = String.valueOf(mon);
					String dealer_id = rs.getString("dealer_id");
					String quater = rs.getString("quater");
					String scored_point = rs.getString("sc");
					String maximum_point = rs.getString("mx");
					String monthly_scored_per = rs.getString("monthly_scored_per");
					String brand_id = rs.getString("brand_id");
					template.execute(
							"INSERT INTO `mys_txn_dealer_score`(`year`, `month`, `quater`, `dealer_id`, `brand_id`, `scored_point_721`, `maximum_point_721`, `monthly_score_percentage`) VALUES "
									+ "('" + year + "','" + month + "','" + quater + "','" + dealer_id + "','"
									+ brand_id + "','" + scored_point + "','" + maximum_point + "','"
									+ monthly_scored_per + "')");
					log.info("addDealerScore method Inserting query :"+
							"INSERT INTO `mys_txn_dealer_score`(`year`, `month`, `quater`, `dealer_id`, `brand_id`, `scored_point_721`, `maximum_point_721`, `monthly_score_percentage`) VALUES "
									+ "('" + year + "','" + month + "','" + quater + "','" + dealer_id + "','"
									+ brand_id + "','" + scored_point + "','" + maximum_point + "','"
									+ monthly_scored_per + "')");

					String sql3 = "select ytd_score_percentage,ytd_score_percentage1,(ytd_score_percentage-ytd_score_percentage1)as movement from (SELECT ifnull(round((SUM(mys_txn_dealer_score.scored_point_721)/ SUM(mys_txn_dealer_score.maximum_point_721))*100,2),0) AS ytd_score_percentage FROM mys_txn_dealer_score WHERE brand_id='"
							+ brand_id + "' AND year='" + year + "' AND dealer_id='" + dealer_id + "' AND month<='"
							+ month
							+ "'   and mys_txn_dealer_score.dealer_score_status='active')res1 JOIN (SELECT ifnull(round((SUM(mys_txn_dealer_score.scored_point_721)/ SUM(mys_txn_dealer_score.maximum_point_721))*100,2),0) AS ytd_score_percentage1 FROM mys_txn_dealer_score WHERE brand_id='"
							+ brand_id + "' AND year='" + year + "' AND dealer_id='" + dealer_id + "' AND month<='"
							+ prevmonth + "'   and mys_txn_dealer_score.dealer_score_status='active')res2";

					log.info("addDealerScore method td_score_percentage query :"+sql3);
					return template.queryForObject(sql3, new RowMapper<MysteryShoppingVisitsBean>() {
						public MysteryShoppingVisitsBean mapRow(ResultSet rs1, int row) throws SQLException {
							log.info(rs1.getString("ytd_score_percentage"));
							template.execute("update mys_txn_dealer_score set ytd_score_percentage='"
									+ rs1.getString("ytd_score_percentage") + "',movement='" + rs1.getString("movement")
									+ "' where mys_txn_dealer_score.year='" + year
									+ "' and mys_txn_dealer_score.dealer_id='" + dealer_id
									+ "' AND mys_txn_dealer_score.brand_id='" + brand_id
									+ "' AND mys_txn_dealer_score.month='" + month + "'");
							log.info("addDealerScore method update else part query :"+"update mys_txn_dealer_score set ytd_score_percentage='"
									+ rs1.getString("ytd_score_percentage") + "',movement='" + rs1.getString("movement")
									+ "' where mys_txn_dealer_score.year='" + year
									+ "' and mys_txn_dealer_score.dealer_id='" + dealer_id
									+ "' AND mys_txn_dealer_score.brand_id='" + brand_id
									+ "' AND mys_txn_dealer_score.month='" + month + "'");
							log.info("addDealerScore method return" + new Gson().toJson(mBean));
							return mBean;
						}

					});

				}

			});
		}
	}

	public List<MysteryShoppingVisitsBean> getmysQuestionstatus(MysteryShoppingVisitsBean mvBean,
			String sk_shopper_id) {
		log.info("getmysQuestionstatus method" + new Gson().toJson(mvBean));
		log.info("getmysQuestionstatus method :"+
				"SELECT sum(`scored_points`) as scored_points, standard_number, section_name,subsection_name , question_id,question_text, answer_status,question_type,formula_flag  FROM mys_txn_answers  WHERE shopper_id='"
						+ sk_shopper_id + "'AND status='active' group by mys_txn_answers.question_id");
		return template.query(
				"SELECT sum(`scored_points`) as scored_points,standard_number, section_name,subsection_name , question_id,question_text, answer_status,question_type,formula_flag  FROM mys_txn_answers  WHERE shopper_id='"
						+ sk_shopper_id + "'AND status='active' group by mys_txn_answers.question_id;",
				new RowMapper<MysteryShoppingVisitsBean>() {
					@Override
					public MysteryShoppingVisitsBean mapRow(ResultSet rs, int row) throws SQLException {
						MysteryShoppingVisitsBean mvBean = new MysteryShoppingVisitsBean();
						mvBean.setSk_shopper_id(sk_shopper_id);
						mvBean.setSection_name(rs.getString("section_name"));
						mvBean.setSubsection_name(rs.getString("subsection_name"));
						mvBean.setQuestion_id(rs.getString("question_id"));
						mvBean.setStandard_number(rs.getString("standard_number"));
						mvBean.setQuestion_text(rs.getString("question_text"));
						mvBean.setAnswer_status(rs.getString("answer_status"));
						mvBean.setScored_points(rs.getString("scored_points"));
						mvBean.setFormula_flag(rs.getString("formula_flag"));
						String question_id = rs.getString("question_id");
						MysteryShoppingVisitsBean mvBean1 = new MysteryShoppingVisitsBean();

						if (rs.getString("formula_flag").equals("1")) {
							mvBean1 = getPoints(mvBean, sk_shopper_id, question_id);
						}
						mvBean.setPoints(mvBean1.getPoints());
						return mvBean;
					}
				});
	}

	public MysteryShoppingVisitsBean getPoints(MysteryShoppingVisitsBean mvBean, String sk_shopper_id,
			String question_id) {
		log.info("getPoints method" + new Gson().toJson(mvBean));
		log.info("getPoints method :"+"SELECT * FROM `mys_txn_formulaanswer` WHERE shopper_id='" + sk_shopper_id + "' AND question_id='"
				+ question_id + "' AND status='active'");
		return template
				.queryForObject(
						"SELECT * FROM `mys_txn_formulaanswer` WHERE shopper_id='" + sk_shopper_id
								+ "' AND question_id='" + question_id + "' AND status='active' ",
						new RowMapper<MysteryShoppingVisitsBean>() {
							@Override
							public MysteryShoppingVisitsBean mapRow(ResultSet rs, int row) throws SQLException {

								mvBean.setSk_answer_id(rs.getString("sk_answer"));
								mvBean.setStandard_number(rs.getString("standard_num"));
								mvBean.setSk_shopper_id(rs.getString("shopper_id"));
								mvBean.setQuestion_id(rs.getString("question_id"));
								mvBean.setFormula_flag(rs.getString("question_formula_flag"));
								mvBean.setPoints(rs.getString("points"));
								log.info("getPoints method return" + new Gson().toJson(mvBean));
								return mvBean;
							}
						});

	}

	public MysteryShoppingVisitsBean getShopperIdByOid(MysteryShoppingVisitsBean mvBean, String oid, String brand,
			String mode) {
		log.info("getShopperIdByOid method" + new Gson().toJson(mvBean));
		try {
			log.info("no osc current month=" + mvBean.getMonth());
			log.info("getShopperIdByOid method :"+"select * from mst_shopper_details where outlet_id='" + oid + "' and year='" + mvBean.getYear()
					+ "' and month(visit_date)='" + mvBean.getMonth() + "' and brand_id='" + brand
					+ "' and mode_of_contact = '" + mode
					+ "' and mst_shopper_details.mode_of_contact!='Online Sales Channel' and visit_status='published';");
			return template.queryForObject("select * from mst_shopper_details where outlet_id='" + oid + "' and year='"
					+ mvBean.getYear() + "' and month(visit_date)='" + mvBean.getMonth() + "' and brand_id='" + brand
					+ "' and mode_of_contact = '" + mode
					+ "'  and mst_shopper_details.mode_of_contact!='Online Sales Channel' and visit_status='published';",
					new RowMapper<MysteryShoppingVisitsBean>() {
						public MysteryShoppingVisitsBean mapRow(ResultSet rs, int row) throws SQLException {
							mvBean.setSk_shopper_id(rs.getString("sk_shopper_id"));
							log.info("non OSC shopper ID in dao=====" + rs.getString("sk_shopper_id"));
							return mvBean;
						}
					});
		} catch (Exception e) {
			e.printStackTrace();
			mvBean.setSk_shopper_id("0");
			log.info("in dao for shopper id" + e);
		}
		log.info("getShopperIdByOid method return" + new Gson().toJson(mvBean));
		return mvBean;
	}

	public MysteryShoppingVisitsBean getShopperIdByOid1(MysteryShoppingVisitsBean mvBean, String did, String brand,
			String mode) {
		log.info("getShopperIdByOid1 method" + new Gson().toJson(mvBean));
		try {
			log.info("no osc current month=" + mvBean.getMonth());
			log.info("getShopperIdByOid1 method :"+"select * from mst_shopper_details where dealer_id='" + did + "' and year='" + mvBean.getYear()
					+ "' and month(visit_date)='" + mvBean.getMonth() + "' and brand_id='" + brand
					+ "' and mode_of_contact = '" + mode
					+ "' and mst_shopper_details.mode_of_contact='Online Sales Channel' and visit_status='published';");
			return template.queryForObject("select * from mst_shopper_details where dealer_id='" + did + "' and year='"
					+ mvBean.getYear() + "' and month(visit_date)='" + mvBean.getMonth() + "' and brand_id='" + brand
					+ "' and mode_of_contact = '" + mode
					+ "' and mst_shopper_details.mode_of_contact='Online Sales Channel' and visit_status='published';",
					new RowMapper<MysteryShoppingVisitsBean>() {
						public MysteryShoppingVisitsBean mapRow(ResultSet rs, int row) throws SQLException {
							mvBean.setOsc_shopper_id(rs.getString("sk_shopper_id"));
							log.info("OSC shopper ID in dao=====" + rs.getString("sk_shopper_id"));
							return mvBean;
						}
					});
		} catch (Exception e) {
			mvBean.setOsc_shopper_id("0");
			e.printStackTrace();
			log.info("in dao for shopper id" + e);
		}
		log.info("getShopperIdByOid1 method return" + new Gson().toJson(mvBean));
		return mvBean;
	}

	public void updatevisitstatus(MysteryShoppingVisitsBean mBean, String sid) {
		log.info("updatevisitstatus method" + new Gson().toJson(mBean));
		log.info("updatevisitstatus method :"+"UPDATE `mst_shopper_details` SET  `visit_status`='completed'  WHERE `sk_shopper_id`='" + sid + "'");
		template.execute(
				"UPDATE `mst_shopper_details` SET  `visit_status`='completed'  WHERE `sk_shopper_id`='" + sid + "'");

	}

	public MysteryShoppingVisitsBean checkshopperlinkstatus(MysteryShoppingVisitsBean mvBean,
			String decrypted_shooper_id) {
		log.info("checkshopperlinkstatus method" + new Gson().toJson(mvBean));
		log.info("checkshopperlinkstatus method :"+"SELECT * FROM mst_shopper_details  WHERE sk_shopper_id='" + decrypted_shooper_id
				+ "'AND visit_status!='scheduled'");
		return template.queryForObject("SELECT * FROM mst_shopper_details  WHERE sk_shopper_id='" + decrypted_shooper_id
				+ "'AND visit_status!='scheduled';", new RowMapper<MysteryShoppingVisitsBean>() {
					@Override
					public MysteryShoppingVisitsBean mapRow(ResultSet rs, int row) throws SQLException {
						log.info("checkshopperlinkstatus method return" + new Gson().toJson(mvBean));
						return mvBean;

					}
				});
	}

	/*
	 * public List<MysteryShoppingVisitsBean>
	 * reviewQuestionDetailsByQuestionId(MysteryShoppingVisitsBean mvBean, String
	 * sk_shopper_id,String question_id) { System.out.
	 * println("select DISTINCT(question_text),mys_txn_answers.*,GROUP_CONCAT(subquestion_id SEPARATOR '#') as sub_question_id,GROUP_CONCAT(sub_question_text  SEPARATOR '#') as subquestion_text,GROUP_CONCAT(select_option_id SEPARATOR '#') as selectoption_id,GROUP_CONCAT(select_option_text SEPARATOR '#') as old_answer FROM mys_txn_answers where mys_txn_answers.shopper_id='"
	 * +sk_shopper_id+"' AND question_id='"+question_id+"' "); return template.
	 * query("select DISTINCT(question_text),mys_txn_answers.*,GROUP_CONCAT(subquestion_id SEPARATOR '#') as sub_question_id,GROUP_CONCAT(sub_question_text  SEPARATOR '#') as subquestion_text,GROUP_CONCAT(select_option_id SEPARATOR '#') as selectoption_id,GROUP_CONCAT(select_option_text SEPARATOR '#') as old_answer FROM mys_txn_answers where mys_txn_answers.shopper_id='"
	 * +sk_shopper_id+"' AND question_id='"+question_id+"'", new
	 * RowMapper<MysteryShoppingVisitsBean>() {
	 * 
	 * @Override public MysteryShoppingVisitsBean mapRow(ResultSet rs, int row)
	 * throws SQLException { MysteryShoppingVisitsBean mvBean = new
	 * MysteryShoppingVisitsBean();
	 * mvBean.setSk_answer_id(rs.getString("sk_answer_id"));
	 * mvBean.setSk_shopper_id(rs.getString("shopper_id"));
	 * mvBean.setQuestion_id(rs.getString("question_id"));
	 * mvBean.setSection_id(rs.getString("section_id"));
	 * mvBean.setSection_name(rs.getString("section_name"));
	 * mvBean.setSubsection_id(rs.getString("subsection_id"));
	 * mvBean.setSubsection_name(rs.getString("subsection_name"));
	 * mvBean.setQuestion_code(rs.getString("question_code"));
	 * mvBean.setStandard_number(rs.getString("standard_number"));
	 * mvBean.setStandard_number_sequence(rs.getString("standard_number_sequence"));
	 * mvBean.setQuestion_type(rs.getString("question_type"));
	 * mvBean.setQuestion_text(rs.getString("question_text"));
	 * mvBean.setSubquestionid(rs.getString("sub_question_id"));
	 * mvBean.setSubquestion(rs.getString("subquestion_text"));
	 * mvBean.setAnswer_type(rs.getString("answer_type"));
	 * mvBean.setSelected_option_id(rs.getString("selectoption_id"));
	 * mvBean.setSelected_option_text(rs.getString("old_answer"));
	 * log.info("select option text="+rs.getString("old_answer"));
	 * 
	 * mvBean.setSelected_pagaragraph(rs.getString("paragraph"));
	 * mvBean.setSelected_date(rs.getString("date"));
	 * mvBean.setSelected_time(rs.getString("dateandtime"));
	 * mvBean.setFormula_flag(rs.getString("formula_flag"));
	 * mvBean.setQuestion_points(rs.getString("question_points"));
	 * mvBean.setScored_points(rs.getString("scored_points"));
	 * mvBean.setMain_ques_comment(rs.getString("main_ques_comment"));
	 * mvBean.setSelected_option_comment(rs.getString("select_option_comment"));
	 * mvBean.setAnswer_status(rs.getString("answer_status"));
	 * mvBean.setCreated_by(rs.getString("created_by"));
	 * mvBean.setStatus(rs.getString("status")); String
	 * subquestion_id=rs.getString("sub_question_id"); String
	 * question_type=rs.getString("question_type");
	 * log.info("question type"+question_type);
	 * log.info("sub question id text"+subquestion_id);
	 * List<MysteryShoppingVisitsBean>optionlistByquestionId=
	 * getOptionlistsByQuestionId(mvBean,question_id);
	 * mvBean.setOptionslist(optionlistByquestionId); String[] subqids =
	 * subquestion_id.split("#"); MysteryShoppingVisitsBean options= new
	 * MysteryShoppingVisitsBean(); String options_text=""; String optionid="";
	 * String optionpoints=""; for (int i = 0; i < subqids.length; i++) {
	 * 
	 * options=getSubQuestionOptionListByQuestionId(mvBean,question_id,subqids[i]);
	 * options_text+=options.getOptions()+"#"; optionid+=options.getOptionid()+"#";
	 * optionpoints+=options.getOption_points()+"#";
	 * 
	 * } mvBean.setOptions(options_text); mvBean.setOptionid(optionid);
	 * mvBean.setOption_points(optionpoints); log.info("option"+options_text);
	 * log.info("option id"+optionid); log.info("option points"+optionpoints);
	 * 
	 * //List<MysteryShoppingVisitsBean>SubQuestionOptionListByQuestionId=
	 * getSubQuestionOptionListByQuestionId(mvBean,question_id,subquestion_id);
	 * //mvBean.setSubquestionOptionList(SubQuestionOptionListByQuestionId);
	 * 
	 * return mvBean; } });
	 * 
	 * 
	 * 
	 * }
	 */

	public List<MysteryShoppingVisitsBean> reviewQuestionDetailsByQuestionId(MysteryShoppingVisitsBean mvBean,
			String sk_shopper_id, String question_id) {
		log.info("reviewQuestionDetailsByQuestionId method" + new Gson().toJson(mvBean));
		log.info("reviewQuestionDetailsByQuestionId method :"+"select *FROM mys_txn_answers where mys_txn_answers.shopper_id='" + sk_shopper_id
				+ "' AND question_id='" + question_id + "' and status='active'");
		return template.query(
				"select *FROM mys_txn_answers where mys_txn_answers.shopper_id='" + sk_shopper_id
						+ "' AND question_id='" + question_id + "'and status='active'",
				new RowMapper<MysteryShoppingVisitsBean>() {
					@Override
					public MysteryShoppingVisitsBean mapRow(ResultSet rs, int row) throws SQLException {
						MysteryShoppingVisitsBean mvBean = new MysteryShoppingVisitsBean();
						mvBean.setSk_answer_id(rs.getString("sk_answer_id"));
						mvBean.setSk_shopper_id(rs.getString("shopper_id"));
						mvBean.setQuestion_id(rs.getString("question_id"));
						mvBean.setSection_id(rs.getString("section_id"));
						mvBean.setSection_name(rs.getString("section_name"));
						mvBean.setSubsection_id(rs.getString("subsection_id"));
						mvBean.setSubsection_name(rs.getString("subsection_name"));
						mvBean.setQuestion_code(rs.getString("question_code"));
						mvBean.setStandard_number(rs.getString("standard_number"));
						mvBean.setStandard_number_sequence(rs.getString("standard_number_sequence"));
						mvBean.setQuestion_type(rs.getString("question_type"));
						mvBean.setQuestion_text(rs.getString("question_text"));
						mvBean.setSubquestionid(rs.getString("subquestion_id"));
						mvBean.setSubquestion(rs.getString("sub_question_text"));
						mvBean.setAnswer_type(rs.getString("answer_type"));
						mvBean.setSelected_option_id(rs.getString("select_option_id"));
						mvBean.setSelected_option_text(rs.getString("select_option_text"));
						mvBean.setSelected_pagaragraph(rs.getString("paragraph"));
						mvBean.setSelected_date(rs.getString("date"));
						mvBean.setSelected_time(rs.getString("dateandtime"));
						mvBean.setFormula_flag(rs.getString("formula_flag"));
						mvBean.setQuestion_points(rs.getString("question_points"));
						mvBean.setScored_points(rs.getString("scored_points"));
						mvBean.setMain_ques_comment(rs.getString("main_ques_comment"));
						mvBean.setSelected_option_comment(rs.getString("select_option_comment"));
						mvBean.setAnswer_status(rs.getString("answer_status"));
						// mvBean.setSub_question_text(rs.getString("sub_questions"));
						mvBean.setCreated_by(rs.getString("created_by"));
						mvBean.setStatus(rs.getString("status"));
						String subquestion_id = rs.getString("subquestion_id");
						String question_type = rs.getString("question_type");
						log.info("select option comment" + rs.getString("select_option_comment"));
						log.info("question type" + question_type);
						log.info("subquestion id" + subquestion_id);
						List<MysteryShoppingVisitsBean> optionlistByquestionId = getOptionlistsByQuestionId(mvBean,
								question_id);
						mvBean.setOptionslist(optionlistByquestionId);
						List<MysteryShoppingVisitsBean> SubQuestionOptionListByQuestionId = getSubQuestionOptionListByQuestionId(
								mvBean, question_id, subquestion_id);
						mvBean.setSubquestionOptionList(SubQuestionOptionListByQuestionId);
						log.info("reviewQuestionDetailsByQuestionId method return" + new Gson().toJson(mvBean));
						return mvBean;
					}
				});

	}

	public List<MysteryShoppingVisitsBean> getOptionlistsByQuestionId(MysteryShoppingVisitsBean mvBean,
			String question_id) {
		log.info("getOptionlistsByQuestionId method" + new Gson().toJson(mvBean));
		log.info("getOptionlistsByQuestionId method :"+"SELECT *FROM mst_questionare_selectoption WHERE question_id='" + question_id
				+ "' and options_status='active'");
		return template.query("SELECT *FROM mst_questionare_selectoption WHERE question_id='" + question_id
				+ "' and options_status='active'", new RowMapper<MysteryShoppingVisitsBean>() {
					@Override
					public MysteryShoppingVisitsBean mapRow(ResultSet rs, int row) throws SQLException {
						MysteryShoppingVisitsBean mvBean = new MysteryShoppingVisitsBean();
						mvBean.setSk_answer_id(rs.getString("sk_answer_id"));
						mvBean.setOptions(rs.getString("options"));
						log.info("getOptionlistsByQuestionId method return" + new Gson().toJson(mvBean));
						return mvBean;
					}
				});
	}

	public List<MysteryShoppingVisitsBean> getSubQuestionOptionListByQuestionId(MysteryShoppingVisitsBean mvBean,
			String question_id, String subQUestionId) {
		log.info("getSubQuestionOptionListByQuestionId method" + new Gson().toJson(mvBean));
		log.info("getSubQuestionOptionListByQuestionId method :"+"SELECT *FROM mst_questionare_selectoption WHERE question_id='" + question_id
				+ "' and subquestion_id='" + subQUestionId + "' and options_status='active'");
		return template.query("SELECT *FROM mst_questionare_selectoption WHERE question_id='" + question_id
				+ "' and subquestion_id='" + subQUestionId + "' and options_status='active'",
				new RowMapper<MysteryShoppingVisitsBean>() {

					@Override
					public MysteryShoppingVisitsBean mapRow(ResultSet rs, int row) throws SQLException {
						MysteryShoppingVisitsBean mvBean = new MysteryShoppingVisitsBean();
						mvBean.setOptionId(rs.getString("sk_answer_id"));
						mvBean.setQuestion_id(rs.getString("question_id"));
						mvBean.setSubquestionid(rs.getString("subquestion_id"));
						mvBean.setOptions(rs.getString("options"));
						mvBean.setOption_points(rs.getString("option_points"));
						mvBean.setOption_comment(rs.getString("option_comment"));
						log.info("getSubQuestionOptionListByQuestionId method return" + new Gson().toJson(mvBean));
						return mvBean;
					}
				});
	}

	public MysteryShoppingVisitsBean getQuestionsById(MysteryShoppingVisitsBean mvBean, String sk_shopper_id,
			String question_id) {
		log.info("getQuestionsById method" + new Gson().toJson(mvBean));
		log.info("getQuestionsById method :"+"SELECT mys_txn_answers.* FROM mys_txn_answers WHERE shopper_id='" + sk_shopper_id
				+ "' AND question_id='" + question_id + "' and status='active' GROUP BY question_id");
		return template.queryForObject(
				"SELECT mys_txn_answers.* FROM mys_txn_answers WHERE shopper_id='" + sk_shopper_id
						+ "' AND question_id='" + question_id + "' and  status='active' GROUP BY question_id",
				new RowMapper<MysteryShoppingVisitsBean>() {
					@Override
					public MysteryShoppingVisitsBean mapRow(ResultSet rs, int row) throws SQLException {

						mvBean.setSk_answer_id(rs.getString("sk_answer_id"));
						mvBean.setSk_shopper_id(rs.getString("shopper_id"));
						mvBean.setQuestion_id(rs.getString("question_id"));
						mvBean.setStandard_number(rs.getString("standard_number"));
						mvBean.setStandard_number_sequence(rs.getString("standard_number_sequence"));
						mvBean.setQuestion_type(rs.getString("question_type"));
						mvBean.setQuestion_text(rs.getString("question_text"));
						log.info("this is question text" + rs.getString("question_text"));
						log.info("getQuestionsById method return" + new Gson().toJson(mvBean));
						return mvBean;
					}
				});
	}

	public List<MysteryShoppingVisitsBean> getDealerPerformananceDetails(MysteryShoppingVisitsBean mvBean) {
		log.info("getDealerPerformananceDetails method" + new Gson().toJson(mvBean));
		String sql = "SELECT *,MonthName(visit_date) as month,mst_dealer_outlet.address as addres,mst_brand.brand_name FROM mst_dealer,mst_shopper_details,mst_dealer_outlet,mst_brand WHERE mst_dealer_outlet.sk_outlet_id=mst_shopper_details.outlet_id AND mst_dealer_outlet.dealer_id=mst_dealer.sk_dealer_id AND mst_shopper_details.brand_id=mst_brand.sk_brand_id AND mst_shopper_details.sk_shopper_id='"
				+ mvBean.getSk_shopper_id() + "'";
		log.info("getDealerPerformananceDetails method :"+sql);
		log.info("Ho in dwaler perforance===========================");
		return template.query(sql, new RowMapper<MysteryShoppingVisitsBean>() {
			public MysteryShoppingVisitsBean mapRow(ResultSet rs, int row) throws SQLException {
				MysteryShoppingVisitsBean mvBean = new MysteryShoppingVisitsBean();
				mvBean.setBrand_name(rs.getString("brand_name"));
				mvBean.setOutlet_name(rs.getString("outlet_name"));
				mvBean.setOutlet_id(rs.getString("outlet_id"));
				mvBean.setAddress(rs.getString("addres"));
				mvBean.setMode_of_contact(rs.getString("mode_of_contact"));
				mvBean.setDealer_name(rs.getString("dealer_name"));
				mvBean.setDealer_id(rs.getString("sk_dealer_id"));
				mvBean.setSk_shopper_id(rs.getString("sk_shopper_id"));
				mvBean.setMonth(rs.getString("month"));
				mvBean.setYear(rs.getString("year"));
				mvBean.setQuarter(rs.getString("quarter"));
				mvBean.setVisit_date(rs.getString("visit_date"));
				log.info("getDealerPerformananceDetails method return" + new Gson().toJson(mvBean));
				return mvBean;
			}
		});
	}

	public void updateAnswerById(MysteryShoppingVisitsBean mBean, String user_id) {
		if (mBean.getAnswer_type().contentEquals("Select/List")) {
			log.info("updateAnswerById method" + new Gson().toJson(mBean));
			mBean.setOption_points(mBean.getOption_points());
			// log.info("get option comment"+mBean.getOption_comment());
			log.info("option ids" + mBean.getOptionid());
			try {
				getoptionsName(mBean);
			} catch (Exception e) {
				// TODO: handle exception
				log.info("Exception of updateAnswerById method" + e);
			}
		} else {
			log.info("question points");
			mBean.setOption_points(mBean.getQuestion_points());
		}
		String main_question_comment = "";
		String option_question_comment = "";
		String paragraph = "";
		String options = "";

		try {
			if (mBean.getMain_ques_comment().contentEquals("null") || mBean.getMain_ques_comment().isEmpty()) {
				main_question_comment = mBean.getMain_ques_comment();
			} else {
				main_question_comment = mBean.getMain_ques_comment().replace("'", "\\'");
			}

		} catch (Exception e) {
			main_question_comment = mBean.getMain_ques_comment();
		}
		try {
			if (mBean.getOption_comment().contentEquals("null") || mBean.getOption_comment().isEmpty()
					|| mBean.getOption_comment() == "") {
				option_question_comment = "";
			} else {
				option_question_comment = mBean.getOption_comment().replace("'", "\\'").replace(",", "");
				;
			}
		} catch (Exception e) {
			option_question_comment = "";
		}
		try {
			if (mBean.getAnswerParagraph().contentEquals("null") || mBean.getAnswerParagraph().isEmpty()) {
				paragraph = mBean.getAnswerParagraph();
			} else {
				paragraph = mBean.getAnswerParagraph().replace("'", "\\'");
			}
		} catch (Exception e) {

		}
		try {
			if (mBean.getOptions().contentEquals("null") || mBean.getOptions().isEmpty()) {
				options = mBean.getOptions();
			} else {
				options = mBean.getOptions().replace("'", "\\'");
			}
		} catch (Exception e) {
			options = mBean.getOptions();
		}

		String old_main_question_text = "";
		String old_select_option_text = "";
		String old_main_question_comment = "";
		String old_select_option_comment = "";
		String old_paragraph = "";

		old_main_question_text = mBean.getQuestion_text().replace("'", "\\'");

		try {
			if (mBean.getOld_selectionoption_text().contentEquals("null")
					|| mBean.getOld_selectionoption_text().isEmpty()) {
				old_select_option_text = mBean.getOld_selectionoption_text();
			} else {
				old_select_option_text = mBean.getOld_selectionoption_text().replace("'", "\\'");
			}

		} catch (Exception e) {
			old_select_option_text = mBean.getOld_selectionoption_text();
		}

		try {
			if (mBean.getOld_mainquestion_comment().contentEquals("null")
					|| mBean.getOld_mainquestion_comment().isEmpty()) {
				old_main_question_comment = mBean.getOld_mainquestion_comment();
			} else {
				old_main_question_comment = mBean.getOld_mainquestion_comment().replace("'", "\\'");
			}

		} catch (Exception e) {
			old_main_question_comment = mBean.getOld_mainquestion_comment();
		}

		try {
			if (mBean.getOldselectOptioncomment().contentEquals("null")
					|| mBean.getOldselectOptioncomment().isEmpty()) {
				old_select_option_comment = mBean.getOldselectOptioncomment();
			} else {
				old_select_option_comment = mBean.getOldselectOptioncomment().replace("'", "\\'");
			}

		} catch (Exception e) {
			old_select_option_comment = mBean.getOldselectOptioncomment();
		}

		try {
			if (mBean.getOld_paragraph().contentEquals("null") || mBean.getOld_paragraph().isEmpty()) {
				old_paragraph = mBean.getOld_paragraph();
			} else {
				old_paragraph = mBean.getOld_paragraph().replace("'", "\\'");
			}
		} catch (Exception e) {

		}
		log.info("answer date" + mBean.getAnswerDate());

		String sql = "SELECT COUNT(*) as exist,ifnull(sk_answer_id,'noid') as sk_answer_id  FROM `mys_txn_answers` WHERE `shopper_id`='"
				+ mBean.getSk_shopper_id() + "' and `question_id`='" + mBean.getQuestion_id() + "' AND status='active'";
		log.info("updateAnswerById method :" + sql);
		String count = "";
		String sk_answer_id = "";
		List<Map<String, Object>> list = template.queryForList(sql);
		for (Map<String, Object> row : list) {
			count = row.get("exist").toString();
			sk_answer_id = row.get("sk_answer_id").toString();
		}
		int id_status = Integer.parseInt(count);

		if (id_status > 0) {
			log.info("updating");
			log.info("updateAnswerById method if id_status > 0 updating query:"+"UPDATE `mys_txn_answers` SET  `select_option_id`='" + mBean.getOptionid()
					+ "',`select_option_text`='" + options + "',`paragraph`='" + paragraph + "',`date`='"
					+ mBean.getAnswerDate() + "',`dateandtime`='" + mBean.getAnswerTime() + "', `scored_points`='"
					+ mBean.getOption_points() + "', `main_ques_comment`='" + main_question_comment
					+ "',`select_option_comment`='" + option_question_comment
					+ "',answer_status='admin_update',created_by='" + user_id + "' WHERE  `sk_answer_id`='"
					+ sk_answer_id + "' and  `question_id`='" + mBean.getQuestion_id() + "' and `shopper_id`='"
					+ mBean.getSk_shopper_id() + "' and status='active'");
			template.execute("UPDATE `mys_txn_answers` SET  `select_option_id`='" + mBean.getOptionid()
					+ "',`select_option_text`='" + options + "',`paragraph`='" + paragraph + "',`date`='"
					+ mBean.getAnswerDate() + "',`dateandtime`='" + mBean.getAnswerTime() + "', `scored_points`='"
					+ mBean.getOption_points() + "', `main_ques_comment`='" + main_question_comment
					+ "',`select_option_comment`='" + option_question_comment
					+ "',answer_status='admin_update',created_by='" + user_id + "'   WHERE  `sk_answer_id`='"
					+ sk_answer_id + "' and  `question_id`='" + mBean.getQuestion_id() + "' and `shopper_id`='"
					+ mBean.getSk_shopper_id() + "' and status='active'");

			log.info("updateAnswerById method if id_status > 0 Inserting query :"+
					"INSERT INTO mys_review_old_answers(shopper_id, question_id,question_text,selectoption_id, selectionoption_text, paragraph, date, time, scored_points, mainquestion_comment, selectoption_comment, Created_by, Created_on) VALUES ('"
							+ mBean.getSk_shopper_id() + "','" + mBean.getQuestion_id() + "','" + old_main_question_text
							+ "','" + mBean.getOld_selectoption_id() + "','" + old_select_option_text + "','"
							+ old_paragraph + "','" + mBean.getOld_date() + "','" + mBean.getOld_time() + "','"
							+ mBean.getScored_points() + "','" + old_main_question_comment + "','"
							+ old_select_option_comment + "','" + user_id + "','" + dateFormat.format(date) + "')");
			template.execute(
					"INSERT INTO mys_review_old_answers(shopper_id, question_id,question_text,selectoption_id, selectionoption_text, paragraph, date, time, scored_points, mainquestion_comment, selectoption_comment, Created_by, Created_on) VALUES ('"
							+ mBean.getSk_shopper_id() + "','" + mBean.getQuestion_id() + "','" + old_main_question_text
							+ "','" + mBean.getOld_selectoption_id() + "','" + old_select_option_text + "','"
							+ old_paragraph + "','" + mBean.getOld_date() + "','" + mBean.getOld_time() + "','"
							+ mBean.getScored_points() + "','" + old_main_question_comment + "','"
							+ old_select_option_comment + "','" + user_id + "','" + dateFormat.format(date) + "')");
			String question_id = mBean.getQuestion_id();
			String shopperId = mBean.getSk_shopper_id();
			String question_type = mBean.getQuestion_type();
			log.info("question type" + question_type);

			List<MysteryShoppingVisitsBean> superquestionDetails = getsuperquestionDetails(mBean,
					question_id); /* this method is used to check each question is having super question or not */
			String super_question_answer = "";
			String sk_question_id = "";
			for (MysteryShoppingVisitsBean questionDetails : superquestionDetails) { /* super question for loop ends */
				super_question_answer = questionDetails.getSuper_question_answer();
				sk_question_id = questionDetails.getSk_question_id();
				log.info("sk question id" + sk_question_id);
				String[] values = super_question_answer.split(",");

				for (int i = 0; i < values.length; i++) { /*
															 * comma separtaed or super question answer Details for loop
															 * starts
															 */
					log.info("super question answer" + super_question_answer);
					log.info("sk question id" + sk_question_id);
					String new_select_option_id = mBean.getOptionid();
					log.info("select option id" + new_select_option_id);
					String old_select_option_id = mBean.getOld_selectoption_id();
					log.info("old select option id" + old_select_option_id);

					if (!new_select_option_id.equals(old_select_option_id)) { /* main if condition starts */
						log.info("new select");

						if (old_select_option_id.equals(values[i]))/* here question inactive starts */
						{

							log.info("updateAnswerById method old_select_option_id query :"+"UPDATE `mys_txn_answers` SET  `status`='inactive'  WHERE  `question_id`='"
											+ sk_question_id + "'  and `shopper_id`='" + mBean.getSk_shopper_id()
											+ "'");
							template.execute("UPDATE `mys_txn_answers` SET  `status`='inactive'  WHERE  `question_id`='"
									+ sk_question_id + "'  and `shopper_id`='" + mBean.getSk_shopper_id() + "'");

							/* insertion of old answers from mys_txn_answers to mys_review_table starts */

							MysteryShoppingVisitsBean mvBean3 = getOldAnswer(mBean, sk_question_id,
									mBean.getSk_shopper_id()); /*
																 * To get all the old answer details from mys_txn_answer
																 * for the particular shopper id
																 */
							String old_select_option_id1 = mvBean3.getOldselectedoptionid();
							if (mvBean3.getQuestion_type().equals("Main Question")
									|| mvBean3.getQuestion_type().equals("Dependent Question")) {
								log.info("updateAnswerById method Insert query :"+
										"INSERT INTO mys_review_old_answers(shopper_id, question_id,question_text,selectoption_id, selectionoption_text, paragraph, date, time, scored_points, mainquestion_comment, selectoption_comment, Created_by, Created_on) VALUES ('"
												+ mvBean3.getSk_shopper_id() + "','" + mvBean3.getQuestion_id() + "','"
												+ mvBean3.getOld_question_text() + "','"
												+ mvBean3.getOldselectedoptionid() + "','"
												+ mvBean3.getOld_selectionoption_text() + "','"
												+ mvBean3.getOld_paragraph() + "','" + mvBean3.getOld_date() + "','"
												+ mvBean3.getOld_time() + "','" + mvBean3.getOld_scored_points() + "','"
												+ mvBean3.getOld_mainquestion_comment() + "','"
												+ mvBean3.getOld_selectoption_comment() + "','" + user_id + "','"
												+ dateFormat.format(date) + "')");
								template.execute(
										"INSERT INTO mys_review_old_answers(shopper_id, question_id,question_text,selectoption_id, selectionoption_text, paragraph, date, time, scored_points, mainquestion_comment, selectoption_comment, Created_by, Created_on) VALUES ('"
												+ mvBean3.getSk_shopper_id() + "','" + mvBean3.getQuestion_id() + "','"
												+ mvBean3.getOld_question_text() + "','"
												+ mvBean3.getOldselectedoptionid() + "','"
												+ mvBean3.getOld_selectionoption_text() + "','"
												+ mvBean3.getOld_paragraph() + "','" + mvBean3.getOld_date() + "','"
												+ mvBean3.getOld_time() + "','" + mvBean3.getOld_scored_points() + "','"
												+ mvBean3.getOld_mainquestion_comment() + "','"
												+ mvBean3.getOld_selectoption_comment() + "','" + user_id + "','"
												+ dateFormat.format(date) + "')");
								if (mvBean3.getFormula_flag()
										.equals("1")) { /* to do shopper id inactive in formula table */
									log.info("updateAnswerById method mvBean3.getFormula_flag==1 :"+"update mys_txn_formulaanswer set status='inactive' where shopper_id='"
											+ mBean.getSk_shopper_id() + "' and question_id='" + sk_question_id + "'");
									template.execute(
											"update mys_txn_formulaanswer set status='inactive' where shopper_id='"
													+ mBean.getSk_shopper_id() + "' and question_id='" + sk_question_id
													+ "'");

								}

							}

							else {

								MysteryShoppingVisitsBean mvBean4 = getCountOfSubQuestion(mBean, sk_question_id,
										mBean.getSk_shopper_id()); /*
																	 * if question type is main question with set of
																	 * subquestions or dependent question with set of
																	 * sub questions then bringing the subquestion count
																	 * from mst subquestionarrie table
																	 */
								String subquestion_count = mvBean4.getSubquestion_count();
								log.info("subquestion_count" + subquestion_count);
								List<MysteryShoppingVisitsBean> SubquestionDetails = getoldAnswerForSubQuestion(mBean,
										sk_question_id, mBean.getSk_shopper_id(),
										subquestion_count);/*
															 * based on the count we are bringing last count answer
															 * details from mys_txn_answer
															 */
								for (MysteryShoppingVisitsBean details : SubquestionDetails) {
									log.info("updateAnswerById method Insert :"+
											"INSERT INTO mys_review_old_answers(shopper_id, question_id,question_text,subquestion_id,subquestion_text,selectoption_id, selectionoption_text, paragraph, date, time, scored_points,mainquestion_commentselectoption_comment, Created_by, Created_on) VALUES ('"
													+ details.getSk_shopper_id() + "','" + details.getQuestion_id()
													+ "','" + details.getOld_question_text() + "','"
													+ details.getOld_subquestion_id() + "','"
													+ details.getOld_subquestion_text() + "','"
													+ details.getOldselectedoptionid() + "','"
													+ details.getOld_selectionoption_text() + "','"
													+ details.getOld_paragraph() + "','" + details.getOld_date() + "','"
													+ details.getOld_time() + "','" + details.getOld_scored_points()
													+ "','" + details.getOld_mainquestion_comment() + "','"
													+ details.getOld_selectoption_comment() + "','" + user_id + "','"
													+ dateFormat.format(date) + "')");
									template.execute(
											"INSERT INTO mys_review_old_answers(shopper_id, question_id,question_text,subquestion_id,subquestion_text,selectoption_id, selectionoption_text, paragraph, date, time, scored_points,mainquestion_comment,selectoption_comment, Created_by, Created_on) VALUES ('"
													+ details.getSk_shopper_id() + "','" + details.getQuestion_id()
													+ "','" + details.getOld_question_text() + "','"
													+ details.getOld_subquestion_id() + "','"
													+ details.getOld_subquestion_text() + "','"
													+ details.getOldselectedoptionid() + "','"
													+ details.getOld_selectionoption_text() + "','"
													+ details.getOld_paragraph() + "','" + details.getOld_date() + "','"
													+ details.getOld_time() + "','" + details.getOld_scored_points()
													+ "','" + details.getOld_mainquestion_comment() + "','"
													+ details.getOld_selectoption_comment() + "','" + user_id + "','"
													+ dateFormat.format(date) + "')");
								}
								if (mvBean3.getFormula_flag().equals("1")) {
									log.info("updateAnswerById method mvBean3.getFormula_flag==1 :"+"update mys_txn_formulaanswer set status='inactive' where shopper_id='"
											+ mBean.getSk_shopper_id() + "' and question_id='" + sk_question_id + "'");
									template.execute(
											"update mys_txn_formulaanswer set status='inactive' where shopper_id='"
													+ mBean.getSk_shopper_id() + "' and question_id='" + sk_question_id
													+ "'");

								}

							}
							/* insertion of old answers from mys_txn_answers to mys_review_table Ends */

							// MysteryShoppingVisitsBean
							// mvBean2=getOldSelectOptionById(mBean,sk_question_id,mBean.getSk_shopper_id());
							// String old_select_option_id1=mvBean2.getSelected_option_id();
							log.info("old select option id1 inactive" + old_select_option_id1);

							List<MysteryShoppingVisitsBean> superquestionDetails1 = getsuperquestionDetails(mBean,
									sk_question_id); /*
														 * this method is to check the quesion which you are making
														 * inactive is having super question answer or not
														 */
							String super_question_answer1 = "";
							String sk_question_id1 = "";
							for (MysteryShoppingVisitsBean questionDetails1 : superquestionDetails1) { /*
																										 * super
																										 * question
																										 * details
																										 * starts
																										 */
								super_question_answer1 = questionDetails1.getSuper_question_answer();
								log.info("super question answer1 in inactive" + super_question_answer1);
								sk_question_id1 = questionDetails1.getSk_question_id();
								log.info("sk_question_id1 in inactive " + sk_question_id1);
								String[] values1 = super_question_answer1.split(",");
								log.info("values length in thire level" + values1.length);
								for (int j = 0; j < values1.length; j++) { /* question inactive starts */
									log.info("in for loop inacitve" + old_select_option_id1);
									try {

										if (old_select_option_id1
												.equals(values1[j])) { /* third level question inactive */
											log.info("in if loop inacitve");

											log.info("updateAnswerById method old_select_option_id1 query "+
													"UPDATE `mys_txn_answers` SET  `status`='inactive'  WHERE  `question_id`='"
															+ sk_question_id1 + "'  and `shopper_id`='"
															+ mBean.getSk_shopper_id() + "'");
											template.execute(
													"UPDATE `mys_txn_answers` SET  `status`='inactive'  WHERE  `question_id`='"
															+ sk_question_id1 + "'  and `shopper_id`='"
															+ mBean.getSk_shopper_id() + "'");
											MysteryShoppingVisitsBean mvBean5 = getOldAnswer(mBean, sk_question_id1,
													mBean.getSk_shopper_id()); /*
																				 * To get all the old answer details
																				 * from mys_txn_answer for the
																				 * particular shopper id
																				 */
											if (mvBean5.getQuestion_type().equals("Main Question")
													|| mvBean5.getQuestion_type().equals("Dependent Question")) {
												log.info("updateAnswerById method Insert :"+
														"INSERT INTO mys_review_old_answers(shopper_id, question_id,question_text,selectoption_id, selectionoption_text, paragraph, date, time, scored_points, mainquestion_comment, selectoption_comment, Created_by, Created_on) VALUES ('"
																+ mvBean5.getSk_shopper_id() + "','"
																+ mvBean5.getQuestion_id() + "','"
																+ mvBean5.getOld_question_text() + "','"
																+ mvBean5.getOldselectedoptionid() + "','"
																+ mvBean5.getOld_selectionoption_text() + "','"
																+ mvBean5.getOld_paragraph() + "','"
																+ mvBean5.getOld_date() + "','" + mvBean5.getOld_time()
																+ "','" + mvBean5.getOld_scored_points() + "','"
																+ mvBean5.getOld_mainquestion_comment() + "','"
																+ mvBean5.getOld_selectoption_comment() + "','"
																+ user_id + "','" + dateFormat.format(date) + "')");
												template.execute(
														"INSERT INTO mys_review_old_answers(shopper_id, question_id,question_text,selectoption_id, selectionoption_text, paragraph, date, time, scored_points, mainquestion_comment, selectoption_comment, Created_by, Created_on) VALUES ('"
																+ mvBean5.getSk_shopper_id() + "','"
																+ mvBean5.getQuestion_id() + "','"
																+ mvBean5.getOld_question_text() + "','"
																+ mvBean5.getOldselectedoptionid() + "','"
																+ mvBean5.getOld_selectionoption_text() + "','"
																+ mvBean5.getOld_paragraph() + "','"
																+ mvBean5.getOld_date() + "','" + mvBean5.getOld_time()
																+ "','" + mvBean5.getOld_scored_points() + "','"
																+ mvBean5.getOld_mainquestion_comment() + "','"
																+ mvBean5.getOld_selectoption_comment() + "','"
																+ user_id + "','" + dateFormat.format(date) + "')");
												if (mvBean5.getFormula_flag()
														.equals("1")) { /* to do shopper id inactive in formula table */
													log.info("updateAnswerById method mvBean5.Formula_flag==1"+
															"update mys_txn_formulaanswer set status='inactive' where shopper_id='"
																	+ mBean.getSk_shopper_id() + "' and question_id='"
																	+ sk_question_id1 + "'");
													template.execute(
															"update mys_txn_formulaanswer set status='inactive' where shopper_id='"
																	+ mBean.getSk_shopper_id() + "' and question_id='"
																	+ sk_question_id1 + "'");

												}
											} else {

												MysteryShoppingVisitsBean mvBean6 = getCountOfSubQuestion(mBean,
														sk_question_id1,
														mBean.getSk_shopper_id()); /*
																					 * if question type is main question
																					 * with set of subquestions or
																					 * dependent question with set of
																					 * sub questions then bringing the
																					 * subquestion count from mst
																					 * subquestionarrie table
																					 */
												String subquestion_count = mvBean6.getSubquestion_count();
												log.info("subquestion_count" + subquestion_count);
												List<MysteryShoppingVisitsBean> SubquestionDetails = getoldAnswerForSubQuestion(
														mBean, sk_question_id1, mBean.getSk_shopper_id(),
														subquestion_count);/*
																			 * based on the count we are bringing last
																			 * count answer details from mys_txn_answer
																			 */
												for (MysteryShoppingVisitsBean details : SubquestionDetails) {
													log.info("updateAnswerById method Inserting :"+
															"INSERT INTO mys_review_old_answers(shopper_id, question_id,question_text,subquestion_id,subquestion_text,selectoption_id, selectionoption_text, paragraph, date, time, scored_points,mainquestion_commentselectoption_comment, Created_by, Created_on) VALUES ('"
																	+ details.getSk_shopper_id() + "','"
																	+ details.getQuestion_id() + "','"
																	+ details.getOld_question_text() + "','"
																	+ details.getOld_subquestion_id() + "','"
																	+ details.getOld_subquestion_text() + "','"
																	+ details.getOldselectedoptionid() + "','"
																	+ details.getOld_selectionoption_text() + "','"
																	+ details.getOld_paragraph() + "','"
																	+ details.getOld_date() + "','"
																	+ details.getOld_time() + "','"
																	+ details.getOld_scored_points() + "','"
																	+ details.getOld_mainquestion_comment() + "','"
																	+ details.getOld_selectoption_comment() + "','"
																	+ user_id + "','" + dateFormat.format(date) + "')");
													template.execute(
															"INSERT INTO mys_review_old_answers(shopper_id, question_id,question_text,subquestion_id,subquestion_text,selectoption_id, selectionoption_text, paragraph, date, time, scored_points,mainquestion_comment,selectoption_comment, Created_by, Created_on) VALUES ('"
																	+ details.getSk_shopper_id() + "','"
																	+ details.getQuestion_id() + "','"
																	+ details.getOld_question_text() + "','"
																	+ details.getOld_subquestion_id() + "','"
																	+ details.getOld_subquestion_text() + "','"
																	+ details.getOldselectedoptionid() + "','"
																	+ details.getOld_selectionoption_text() + "','"
																	+ details.getOld_paragraph() + "','"
																	+ details.getOld_date() + "','"
																	+ details.getOld_time() + "','"
																	+ details.getOld_scored_points() + "','"
																	+ details.getOld_mainquestion_comment() + "','"
																	+ details.getOld_selectoption_comment() + "','"
																	+ user_id + "','" + dateFormat.format(date) + "')");
												}
												if (mvBean5.getFormula_flag()
														.equals("1")) { /* to do shopper id inactive in formula table */
													log.info("updateAnswerById method mvBean5.Formula_flag==1 :"+
															"update mys_txn_formulaanswer set status='inactive' where shopper_id='"
																	+ mBean.getSk_shopper_id() + "' and question_id='"
																	+ sk_question_id1 + "'");
													template.execute(
															"update mys_txn_formulaanswer set status='inactive' where shopper_id='"
																	+ mBean.getSk_shopper_id() + "' and question_id='"
																	+ sk_question_id1 + "'");

												}
											}

										}
									} catch (Exception e) {
										e.printStackTrace();
									}

								} /* question inactive ends */
							} /* super question details Ends */

						} /* here question inactive Ends */

						if (new_select_option_id.equals(values[i])) { /* here question insertion Starts */
							log.info("another if");
							MysteryShoppingVisitsBean mBean1 = new MysteryShoppingVisitsBean();
							mBean1 = getDependentQuestionDetails(mBean, sk_question_id); /* To get question details */
							if (mBean1.getQuestion_type().equals("Dependent Question")) {
								log.info("updateAnswerById method mBean1.getQuestion_type()==Dependent Question :"+
										"insert into mys_txn_answers(shopper_id,question_id,section_id,section_name,subsection_id,subsection_name,question_code,standard_number,standard_number_sequence,question_type,question_text,answer_type,question_points, formula_flag,answer_status)values('"
												+ shopperId + "','" + mBean1.getSk_question_id() + "','"
												+ mBean1.getSection_id() + "','" + mBean1.getSection_name() + "','"
												+ mBean1.getSubsection_id() + "','" + mBean1.getSubsection_name()
												+ "','" + mBean1.getQuestion_code() + "','"
												+ mBean1.getStandard_number() + "','"
												+ mBean1.getStandard_number_sequence() + "','"
												+ mBean1.getQuestion_type() + "','" + mBean1.getQuestion_text() + "','"
												+ mBean1.getAnswer_type() + "','" + mBean1.getQuestion_points() + "','"
												+ mBean1.getFormula_flag() + "','not answered')");
								template.execute(
										"insert into mys_txn_answers(shopper_id,question_id,section_id,section_name,subsection_id,subsection_name,question_code,standard_number,standard_number_sequence,question_type,question_text,answer_type,question_points,formula_flag,answer_status)values('"
												+ shopperId + "','" + mBean1.getSk_question_id() + "','"
												+ mBean1.getSection_id() + "','" + mBean1.getSection_name() + "','"
												+ mBean1.getSubsection_id() + "','" + mBean1.getSubsection_name()
												+ "','" + mBean1.getQuestion_code() + "','"
												+ mBean1.getStandard_number() + "','"
												+ mBean1.getStandard_number_sequence() + "','"
												+ mBean1.getQuestion_type() + "','" + mBean1.getQuestion_text() + "','"
												+ mBean1.getAnswer_type() + "','" + mBean1.getQuestion_points() + "','"
												+ mBean1.getFormula_flag() + "','not answered')");

							}
							if (mBean1.getQuestion_type().equals("Dependent Question With Set Of SubQuestions")) {
								List<MysteryShoppingVisitsBean> subquestionDetails1 = getSubquestiondetails(mBean1,
										mBean1.getSk_question_id());/*
																	 * to get the subquestion details of dependent
																	 * question with set of subquestion or Main question
																	 * with set of subquestion
																	 */
								try {
									for (MysteryShoppingVisitsBean subquestionDetails : subquestionDetails1) {
										log.info("subquestion for loop");
										log.info("subquestion id" + subquestionDetails.getSubquestionid());
										log.info("subquestion text" + subquestionDetails.getSub_question_text());
										log.info(
												"insert into mys_txn_answers(shopper_id,question_id,section_id,section_name,subsection_id,subsection_name,question_code,standard_number,standard_number_sequence,question_type,question_text,subquestion_id,sub_question_text,answer_type,question_points,formula_flag,answer_status)values('"
														+ shopperId + "','" + mBean1.getSk_question_id() + "','"
														+ mBean1.getSection_id() + "','" + mBean1.getSection_name()
														+ "','" + mBean1.getSubsection_id() + "','"
														+ mBean1.getSubsection_name() + "','"
														+ mBean1.getQuestion_code() + "','"
														+ mBean1.getStandard_number() + "','"
														+ mBean1.getStandard_number_sequence() + "','"
														+ mBean1.getQuestion_type() + "','" + mBean1.getQuestion_text()
														+ "','" + subquestionDetails.getSubquestionid() + "','"
														+ subquestionDetails.getSub_question_text() + "','"
														+ subquestionDetails.getSubanswertype() + "','"
														+ mBean1.getQuestion_points() + "','" + mBean1.getFormula_flag()
														+ "','not answered')");
										template.execute(
												"insert into mys_txn_answers(shopper_id,question_id,section_id,section_name,subsection_id,subsection_name,question_code,standard_number,standard_number_sequence,question_type,question_text,subquestion_id,sub_question_text,answer_type,question_points,formula_flag,answer_status)values('"
														+ shopperId + "','" + mBean1.getSk_question_id() + "','"
														+ mBean1.getSection_id() + "','" + mBean1.getSection_name()
														+ "','" + mBean1.getSubsection_id() + "','"
														+ mBean1.getSubsection_name() + "','"
														+ mBean1.getQuestion_code() + "','"
														+ mBean1.getStandard_number() + "','"
														+ mBean1.getStandard_number_sequence() + "','"
														+ mBean1.getQuestion_type() + "','" + mBean1.getQuestion_text()
														+ "','" + subquestionDetails.getSubquestionid() + "','"
														+ subquestionDetails.getSub_question_text() + "','"
														+ subquestionDetails.getSubanswertype() + "','"
														+ mBean1.getQuestion_points() + "','" + mBean1.getFormula_flag()
														+ "','not answered')");
									}
								} catch (Exception e) {
									// TODO: handle exception
								}

							}

						} /* here question insertion Ends */
					} /* main if condition Ends */
				} /* comma separtaed or super question answer Details for loop starts */

			} /* super question for loop ends */

		}
	}

	public MysteryShoppingVisitsBean getOldSelectOptionById(MysteryShoppingVisitsBean mvBean, String sk_question_id,
			String shopper_id) {
		log.info("getOldSelectOptionById method" + new Gson().toJson(mvBean));
		log.info("getOldSelectOptionById method :"+"SELECT *FROM mys_txn_answers WHERE shopper_id='" + shopper_id + "' AND question_id='" + sk_question_id
				+ "' AND status='inactive' ORDER by sk_answer_id DESC  LIMIT 1");
		return template.queryForObject(
				"SELECT *FROM mys_txn_answers WHERE shopper_id='" + shopper_id + "' AND question_id='" + sk_question_id
						+ "' AND status='inactive' ORDER by sk_answer_id DESC  LIMIT 1 ",
				new RowMapper<MysteryShoppingVisitsBean>() {
					@Override
					public MysteryShoppingVisitsBean mapRow(ResultSet rs, int row) throws SQLException {
						mvBean.setSk_answer_id(rs.getString("sk_answer_id"));
						mvBean.setStandard_number(rs.getString("standard_number"));
						mvBean.setSelected_option_id(rs.getString("select_option_id"));
						mvBean.setSelected_option_text(rs.getString("select_option_text"));
						log.info("getOldSelectOptionById method return" + new Gson().toJson(mvBean));
						return mvBean;
					}
				});
	}

	public MysteryShoppingVisitsBean getCountOfSubQuestion(MysteryShoppingVisitsBean mvBean, String sk_question_id,
			String shopper_id) {
		log.info("getCountOfSubQuestion method" + new Gson().toJson(mvBean));
		log.info("getCountOfSubQuestion method :"+"SELECT count(*) as subquesion_count FROM mst_questionare_subquestion WHERE question_id='"
				+ sk_question_id + "' AND subquestion_status='active'");
		return template.queryForObject(
				"SELECT count(*) as subquesion_count FROM mst_questionare_subquestion WHERE question_id='"
						+ sk_question_id + "' AND subquestion_status='active' ",
				new RowMapper<MysteryShoppingVisitsBean>() {
					@Override
					public MysteryShoppingVisitsBean mapRow(ResultSet rs, int row) throws SQLException {
						mvBean.setSubquestion_count(rs.getString("subquesion_count"));
						
						log.info("getCountOfSubQuestion method return" + new Gson().toJson(mvBean));

						return mvBean;
					}
				});
	}

	public MysteryShoppingVisitsBean getOldAnswer(MysteryShoppingVisitsBean mvBean, String sk_question_id,
			String shopper_id) {
		log.info("getOldAnswer method" + new Gson().toJson(mvBean));
		log.info("getOldAnswer method :"+"SELECT *FROM mys_txn_answers WHERE shopper_id='" + shopper_id + "' AND question_id='" + sk_question_id
				+ "' AND status='inactive' ORDER by sk_answer_id DESC  LIMIT 1");
		return template.queryForObject(
				"SELECT *FROM mys_txn_answers WHERE shopper_id='" + shopper_id + "' AND question_id='" + sk_question_id
						+ "' AND status='inactive' ORDER by sk_answer_id DESC  LIMIT 1 ",
				new RowMapper<MysteryShoppingVisitsBean>() {
					@Override
					public MysteryShoppingVisitsBean mapRow(ResultSet rs, int row) throws SQLException {
						mvBean.setSk_shopper_id(rs.getString("shopper_id"));
						mvBean.setQuestion_id(rs.getString("question_id"));
						mvBean.setOld_question_text(rs.getString("question_text"));
						mvBean.setQuestion_type(rs.getString("question_type"));
						mvBean.setOld_subquestion_id(rs.getString("subquestion_id"));
						mvBean.setOld_subquestion_text(rs.getString("sub_question_text"));
						mvBean.setOldselectedoptionid(rs.getString("select_option_id"));
						mvBean.setOld_selectionoption_text(rs.getString("select_option_text"));
						mvBean.setOld_paragraph(rs.getString("paragraph"));
						mvBean.setOld_date(rs.getString("date"));
						mvBean.setOld_time(rs.getString("dateandtime"));
						mvBean.setOld_scored_points(rs.getString("scored_points"));
						mvBean.setOld_mainquestion_comment(rs.getString("main_ques_comment"));
						mvBean.setOld_selectoption_comment(rs.getString("select_option_comment"));
						mvBean.setFormula_flag(rs.getString("formula_flag"));
						mvBean.setCreated_by(rs.getString("Created_by"));
						log.info("getOldAnswer method return" + new Gson().toJson(mvBean));
						return mvBean;
					}
				});
	}

	public List<MysteryShoppingVisitsBean> getoldAnswerForSubQuestion(MysteryShoppingVisitsBean mvBean,
			String sk_question_id, String shopper_id, String subquestion_count) {
		log.info("getoldAnswerForSubQuestion method" + new Gson().toJson(mvBean));
		log.info("getoldAnswerForSubQuestion method :"+"SELECT *FROM mys_txn_answers WHERE shopper_id='" + shopper_id + "' AND question_id='" + sk_question_id
				+ "' AND status='inactive' ORDER by sk_answer_id DESC  LIMIT " + subquestion_count + "");
		return template.query(
				"SELECT *FROM mys_txn_answers WHERE shopper_id='" + shopper_id + "' AND question_id='" + sk_question_id
						+ "' AND status='inactive' ORDER by sk_answer_id DESC  LIMIT " + subquestion_count + " ",
				new RowMapper<MysteryShoppingVisitsBean>() {
					@Override
					public MysteryShoppingVisitsBean mapRow(ResultSet rs, int row) throws SQLException {
						MysteryShoppingVisitsBean mvBean = new MysteryShoppingVisitsBean();
						mvBean.setSk_shopper_id(rs.getString("shopper_id"));
						mvBean.setQuestion_id(rs.getString("question_id"));
						mvBean.setOld_question_text(rs.getString("question_text"));
						mvBean.setOld_subquestion_id(rs.getString("subquestion_id"));
						mvBean.setOld_subquestion_text(rs.getString("sub_question_text"));
						mvBean.setOldselectedoptionid(rs.getString("select_option_id"));
						mvBean.setOld_selectionoption_text(rs.getString("select_option_text"));
						mvBean.setOld_paragraph(rs.getString("paragraph"));
						mvBean.setOld_date(rs.getString("date"));
						mvBean.setOld_time(rs.getString("dateandtime"));
						mvBean.setOld_scored_points(rs.getString("scored_points"));
						mvBean.setOld_mainquestion_comment(rs.getString("main_ques_comment"));
						mvBean.setOld_selectoption_comment(rs.getString("select_option_comment"));
						mvBean.setCreated_by(rs.getString("Created_by"));
						log.info("getoldAnswerForSubQuestion method return" + new Gson().toJson(mvBean));
						return mvBean;
					}
				});
	}

	public MysteryShoppingVisitsBean updateAnswerById(MysteryShoppingVisitsBean mBean,
			List<MysteryShoppingVisitsBean> answerdata, String user_id) {
		log.info("updateAnswerById method" + new Gson().toJson(mBean));
		String main_question_comment = "";
		String option_question_comment = "";
		String paragraph = "";
		String options = "";
		String old_main_question_text = "";
		String old_subquestion_text = "";
		String old_select_option_text = "";
		String old_select_option_comment = "";
		String old_paragraph = "";

		try {
			if (mBean.getMain_ques_comment().contentEquals("null") || mBean.getMain_ques_comment().isEmpty()) {
				main_question_comment = mBean.getMain_ques_comment();
			} else {
				main_question_comment = mBean.getMain_ques_comment().replace("'", "\\'");
			}

		} catch (Exception e) {
			main_question_comment = mBean.getMain_ques_comment();
		}

		for (MysteryShoppingVisitsBean mysteryShoppingVisitsBean : answerdata) {
			if (mysteryShoppingVisitsBean.getSubquestionid() != null) {

				if (mysteryShoppingVisitsBean.getAnswer_type().contentEquals("Select/List")) {
					mBean.setOption_points(mBean.getOption_points());
					String optionId = mysteryShoppingVisitsBean.getOptionid();
					String[] values = optionId.split("#");

					try {
						String ids = "";
						for (int i = 0; i < values.length; i++) {
							ids = values[0];
						}

						getoptionsNameByoptionalId(mBean, ids);

					} catch (Exception e) {
						e.printStackTrace();
					}
					try {
						getMaxOptionPoints(mBean, mysteryShoppingVisitsBean);
						log.info("max points==" + mBean.getMax_option_point());
					} catch (Exception e) {
						e.printStackTrace();
					}

				}

				else if (mysteryShoppingVisitsBean.getAnswer_type().contentEquals("Date & Time")) {
					try {
						// getMaxdatetimePoints(mBean,mysteryShoppingVisitsBean);
						log.info("max points==" + mBean.getMax_option_point());
						mBean.setOption_points(mBean.getMax_option_point());
						mBean.setOptions("");
						mBean.setOptionid("");
					} catch (Exception e) {
						e.printStackTrace();
					}

				} else if (mysteryShoppingVisitsBean.getAnswer_type().contentEquals("Date")) {
					try {
						// getMaxdatePoints(mBean,mysteryShoppingVisitsBean);
						log.info("max points==" + mBean.getMax_option_point());
						mBean.setOption_points(mBean.getMax_option_point());
						mBean.setOptions("");
						mBean.setOptionid("");
					} catch (Exception e) {
						e.printStackTrace();
					}

				} else {
					mBean.setOption_points("0");
					mBean.setMax_option_point("0");
					mBean.setOptions("");
					mBean.setOptionid("");

				}
				try {
					if (mysteryShoppingVisitsBean.getOption_comment().contentEquals("null")
							|| mysteryShoppingVisitsBean.getOption_comment().isEmpty()) {
						option_question_comment = mysteryShoppingVisitsBean.getOption_comment();
					} else {
						option_question_comment = mysteryShoppingVisitsBean.getOption_comment().replace("'", "\\'");
					}
				} catch (Exception e) {
					option_question_comment = mysteryShoppingVisitsBean.getOption_comment();
				}
				try {
					if (mysteryShoppingVisitsBean.getAnswerParagraph().contentEquals("null")
							|| mysteryShoppingVisitsBean.getAnswerParagraph().isEmpty()) {
						paragraph = mysteryShoppingVisitsBean.getAnswerParagraph();

					} else {
						paragraph = mysteryShoppingVisitsBean.getAnswerParagraph().replace("'", "\\'");

					}
				} catch (Exception e) {
					paragraph = mysteryShoppingVisitsBean.getAnswerParagraph();

				}

				if (mBean.getOptions().contentEquals("null") || mBean.getOptions().isEmpty()) {
					options = mBean.getOptions();

				} else {
					options = mBean.getOptions().replace("'", "\\'");
				}

				try {
					if (mysteryShoppingVisitsBean.getOld_question_text().contentEquals("null")
							|| mysteryShoppingVisitsBean.getOld_question_text().isEmpty()) {
						old_main_question_text = mysteryShoppingVisitsBean.getOld_question_text();
					} else {
						old_main_question_text = mysteryShoppingVisitsBean.getOld_question_text().replace("'", "\\'");
					}
				} catch (Exception e) {
					old_main_question_text = mysteryShoppingVisitsBean.getOld_question_text();
				}

				try {
					if (mysteryShoppingVisitsBean.getSubquestion().contentEquals("null")
							|| mysteryShoppingVisitsBean.getSubquestion().isEmpty()) {
						old_subquestion_text = mysteryShoppingVisitsBean.getSubquestion();
					} else {
						old_subquestion_text = mysteryShoppingVisitsBean.getSubquestion().replace("'", "\\'");
					}
				} catch (Exception e) {
					old_subquestion_text = mysteryShoppingVisitsBean.getSubquestion();
				}

				try {
					if (mysteryShoppingVisitsBean.getOld_selectionoption_text().contentEquals("null")
							|| mysteryShoppingVisitsBean.getOld_selectionoption_text().isEmpty()) {
						old_select_option_text = mysteryShoppingVisitsBean.getOld_selectionoption_text();
					} else {
						old_select_option_text = mysteryShoppingVisitsBean.getOld_selectionoption_text().replace("'",
								"\\'");
					}
				} catch (Exception e) {
					old_select_option_text = mysteryShoppingVisitsBean.getOld_selectionoption_text();
				}

				try {
					if (mysteryShoppingVisitsBean.getOldselectOptioncomment().contentEquals("null")
							|| mysteryShoppingVisitsBean.getOldselectOptioncomment().isEmpty()) {
						old_select_option_comment = mysteryShoppingVisitsBean.getOldselectOptioncomment();
					} else {
						old_select_option_comment = mysteryShoppingVisitsBean.getOldselectOptioncomment().replace("'",
								"\\'");
					}
				} catch (Exception e) {
					old_select_option_comment = mysteryShoppingVisitsBean.getOldselectOptioncomment();
				}

				try {
					if (mysteryShoppingVisitsBean.getOld_paragraph().contentEquals("null")
							|| mysteryShoppingVisitsBean.getOld_paragraph().isEmpty()) {
						old_paragraph = mysteryShoppingVisitsBean.getOld_paragraph();
					} else {
						old_paragraph = mysteryShoppingVisitsBean.getOld_paragraph().replace("'", "\\'");
					}
				} catch (Exception e) {
					old_paragraph = mysteryShoppingVisitsBean.getOld_paragraph();
				}

				String sql = "SELECT COUNT(*) as exist,ifnull(sk_answer_id,'noid') as sk_answer  from mys_txn_answers WHERE shopper_id='"
						+ mBean.getSk_shopper_id() + "' and question_id='" + mBean.getQuestion_id()
						+ "' and subquestion_id ='" + mysteryShoppingVisitsBean.getSubquestionid()
						+ "' AND status='active'";
				log.info("updateAnswerById method :" + sql);
				String count = "";
				String id = "";
				List<Map<String, Object>> list = template.queryForList(sql);
				for (Map<String, Object> row : list) {
					count = row.get("exist").toString();
					id = row.get("sk_answer").toString();
				}
				int id_status = Integer.parseInt(count);
				if (id_status > 0) {
					log.info("updateAnswerById method if id_status > 0 :"+"UPDATE `mys_txn_answers` SET  `select_option_id`='" + mBean.getOptionid()
							+ "',`select_option_text`='" + options + "',`paragraph`='" + paragraph + "',`date`='"
							+ mysteryShoppingVisitsBean.getAnswerDate() + "',`dateandtime`='"
							+ mysteryShoppingVisitsBean.getAnswerTime() + "', `scored_points`='"
							+ mBean.getOption_points() + "', `main_ques_comment`='" + main_question_comment
							+ "',`select_option_comment`='" + option_question_comment
							+ "',answer_status='admin_update',created_by='" + user_id + "'  WHERE  `sk_answer_id`='"
							+ id + "' and  `question_id`='" + mBean.getQuestion_id() + "' and `shopper_id`='"
							+ mBean.getSk_shopper_id() + "' and subquestion_id ='"
							+ mysteryShoppingVisitsBean.getSubquestionid() + "' and status='active'");
					template.execute("UPDATE `mys_txn_answers` SET  `select_option_id`='" + mBean.getOptionid()
							+ "',`select_option_text`='" + options + "',`paragraph`='" + paragraph + "',`date`='"
							+ mysteryShoppingVisitsBean.getAnswerDate() + "',`dateandtime`='"
							+ mysteryShoppingVisitsBean.getAnswerTime() + "', `scored_points`='"
							+ mBean.getOption_points() + "', `main_ques_comment`='" + main_question_comment
							+ "',`select_option_comment`='" + option_question_comment
							+ "',answer_status='admin_update',created_by='" + user_id + "'  WHERE  `sk_answer_id`='"
							+ id + "' and  `question_id`='" + mBean.getQuestion_id() + "' and `shopper_id`='"
							+ mBean.getSk_shopper_id() + "' and subquestion_id ='"
							+ mysteryShoppingVisitsBean.getSubquestionid() + "' and status='active'");

					log.info("updateAnswerById method Insert:"+
							"INSERT INTO mys_review_old_answers(shopper_id, question_id,question_text,subquestion_id,subquestion_text,selectoption_id, selectionoption_text, paragraph, date, time, scored_points,selectoption_comment, Created_by, Created_on) VALUES ('"
									+ mBean.getSk_shopper_id() + "','" + mBean.getQuestion_id() + "','"
									+ old_main_question_text + "','" + mysteryShoppingVisitsBean.getSubquestionid()
									+ "','" + old_subquestion_text + "','"
									+ mysteryShoppingVisitsBean.getOld_selectoption_id() + "','"
									+ old_select_option_text + "','" + old_paragraph + "','"
									+ mysteryShoppingVisitsBean.getOld_date() + "','"
									+ mysteryShoppingVisitsBean.getOld_time() + "','"
									+ mysteryShoppingVisitsBean.getOld_scored_points() + "','"
									+ old_select_option_comment + "','" + user_id + "','" + dateFormat.format(date)
									+ "')");
					template.execute(
							"INSERT INTO mys_review_old_answers(shopper_id, question_id,question_text,subquestion_id,subquestion_text,selectoption_id, selectionoption_text, paragraph, date, time, scored_points,selectoption_comment, Created_by, Created_on) VALUES ('"
									+ mBean.getSk_shopper_id() + "','" + mBean.getQuestion_id() + "','"
									+ old_main_question_text + "','" + mysteryShoppingVisitsBean.getSubquestionid()
									+ "','" + old_subquestion_text + "','"
									+ mysteryShoppingVisitsBean.getOld_selectoption_id() + "','"
									+ old_select_option_text + "','" + old_paragraph + "','"
									+ mysteryShoppingVisitsBean.getOld_date() + "','"
									+ mysteryShoppingVisitsBean.getOld_time() + "','"
									+ mysteryShoppingVisitsBean.getOld_scored_points() + "','"
									+ old_select_option_comment + "','" + user_id + "','" + dateFormat.format(date)
									+ "')");
				}

			}

		}
		log.info("updateAnswerById method return" + new Gson().toJson(mBean));
		return mBean;

	}

	private MysteryShoppingVisitsBean getoptionsNameByoptionalId(MysteryShoppingVisitsBean mBean, String ids) {
		String sql = "SELECT  COUNT(*),mst_questionare_selectoption.* FROM `mst_questionare_selectoption` WHERE `sk_answer_id`='"
				+ ids + "' and `options_status`='active' ";
		log.info("getoptionsNameByoptionalId method" + new Gson().toJson(mBean));
		log.info("getoptionsNameByoptionalId method :"+sql);
		return template.queryForObject(sql, new RowMapper<MysteryShoppingVisitsBean>() {
			public MysteryShoppingVisitsBean mapRow(ResultSet rs, int row) throws SQLException {
				mBean.setOptionid(rs.getString("sk_answer_id"));
				mBean.setOptions(rs.getString("options"));
				mBean.setOption_points(rs.getString("option_points"));
				// log.info("option in dao"+rs.getString("options"));

				log.info("getoptionsNameByoptionalId method return" + new Gson().toJson(mBean));
				return mBean;
			}

		});
	}

	public List<MysteryShoppingVisitsBean> getsuperquestionDetails(MysteryShoppingVisitsBean mvBean,
			String question_id) {
		log.info("getsuperquestionDetails method" + new Gson().toJson(mvBean));
		log.info("getsuperquestionDetails method :"+
				"SELECT * FROM (SELECT sk_question_id,super_question_id,super_question_answer,REPLACE(super_question_id, '#', ',') as sp from mst_questionare where mst_questionare.question_status='active' ) as res1 where FIND_IN_SET('"
						+ question_id + "',sp)");
		return template.query(
				"SELECT * FROM (SELECT sk_question_id,super_question_id,super_question_answer,REPLACE(super_question_id, '#', ',') as sp from mst_questionare where  mst_questionare.question_status='active') as res1 where FIND_IN_SET('"
						+ question_id + "',sp)",
				new RowMapper<MysteryShoppingVisitsBean>() {
					@Override
					public MysteryShoppingVisitsBean mapRow(ResultSet rs, int row) throws SQLException {
						MysteryShoppingVisitsBean mvBean = new MysteryShoppingVisitsBean();
						mvBean.setSk_question_id(rs.getString("sk_question_id"));
						mvBean.setSuper_quesiton_id(rs.getString("super_question_id"));
						mvBean.setSuper_question_answer(rs.getString("super_question_answer"));
						mvBean.setSp(rs.getString("sp"));
						log.info("getsuperquestionDetails method return" + new Gson().toJson(mvBean));
						return mvBean;
					}
				});
	}

	public List<MysteryShoppingVisitsBean> getScoreTabTableData(MysteryShoppingVisitsBean mvBean,
			String sk_shopper_id) {
		log.info("getScoreTabTableData method" + new Gson().toJson(mvBean));
		log.info("getScoreTabTableData method :"+
				"SELECT *,IFNULL(percentage1,0) as percentage,(select round((sum(scored_points)/sum(max_points))*100,2) as overallScore FROM mys_score WHERE shopper_id='"
						+ sk_shopper_id
						+ "') as overallScore FROM(SELECT sk_section_id,section_name FROM mst_section WHERE section_status='active') as res1 LEFT JOIN (SELECT section_id,ROUND(IFNULL((SUM(scored_points)/SUM(max_points) *100),0),2) as percentage1 FROM mys_score LEFT join mst_section ON mys_score.section_id=mst_section.sk_section_id WHERE shopper_id='"
						+ sk_shopper_id + "' GROUP BY section_id) as res2 ON res1.sk_section_id=res2.section_id");
		return template.query(
				"SELECT *,IFNULL(percentage1,0) as percentage,(select round((sum(scored_points)/sum(max_points))*100,2) as overallScore FROM mys_score WHERE shopper_id='"
						+ sk_shopper_id
						+ "') as overallScore FROM(SELECT sk_section_id,section_name FROM mst_section WHERE section_status='active') as res1 LEFT JOIN (SELECT section_id,ROUND(IFNULL((SUM(scored_points)/SUM(max_points) *100),0),2) as percentage1 FROM mys_score LEFT join mst_section ON mys_score.section_id=mst_section.sk_section_id WHERE shopper_id='"
						+ sk_shopper_id + "' GROUP BY section_id) as res2 ON res1.sk_section_id=res2.section_id",
				new RowMapper<MysteryShoppingVisitsBean>() {
					@Override
					public MysteryShoppingVisitsBean mapRow(ResultSet rs, int row) throws SQLException {
						MysteryShoppingVisitsBean mvBean = new MysteryShoppingVisitsBean();
						mvBean.setSc_name(rs.getString("section_name"));
						mvBean.setSection_id(rs.getString("section_id"));
						mvBean.setPercentage(rs.getString("percentage"));
						String section_id = rs.getString("section_id");
						mvBean.setOverAllScore(rs.getString("overallScore"));
						List<MysteryShoppingVisitsBean> eachTableData = GetSubSectionScoreByMysteryId(mvBean,
								section_id, sk_shopper_id);
						mvBean.setEachTableData(eachTableData);
						log.info("getScoreTabTableData method return" + new Gson().toJson(mvBean));
						return mvBean;
					}
				});
	}

	public List<MysteryShoppingVisitsBean> GetSubSectionScoreByMysteryId(MysteryShoppingVisitsBean mvBean,
			String section_id, String sk_shopper_id) {
		log.info("GetSubSectionScoreByMysteryId method" + new Gson().toJson(mvBean));
		log.info("GetSubSectionScoreByMysteryId method :"+
				"SELECT * FROM mys_score where shopper_id='" + sk_shopper_id + "' and section_id='" + section_id + "'");
		return template.query(
				"SELECT * FROM mys_score where shopper_id='" + sk_shopper_id + "' and section_id='" + section_id + "'",
				new RowMapper<MysteryShoppingVisitsBean>() {
					@Override
					public MysteryShoppingVisitsBean mapRow(ResultSet rs, int row) throws SQLException {
						MysteryShoppingVisitsBean mvBean = new MysteryShoppingVisitsBean();
						mvBean.setSection_id(rs.getString("section_id"));
						mvBean.setSc_name(rs.getString("section_name"));
						mvBean.setSubsection_name(rs.getString("subsection_name"));
						mvBean.setSubsection_id(rs.getString("subsection_id"));
						mvBean.setPercentage(rs.getString("percentage"));
						log.info("GetSubSectionScoreByMysteryId method return" + new Gson().toJson(mvBean));
						return mvBean;
					}
				});

	}

	public MysteryShoppingVisitsBean getDependentQuestionDetails(MysteryShoppingVisitsBean mvBean,
			String sk_question_id) {
		log.info("getDependentQuestionDetails method" + new Gson().toJson(mvBean));
		log.info("getDependentQuestionDetails method :"+
				"select mst_section.sk_section_id,mst_section.section_name,mst_subsection.sk_subsection_id,mst_subsection.subsection_name , mst_questionare.* FROM mst_section,mst_subsection, mst_questionare WHERE mst_questionare.section_id=mst_section.sk_section_id AND mst_questionare.subsection_id=mst_subsection.sk_subsection_id AND  mst_questionare.sk_question_id='"
						+ sk_question_id + "' AND question_status='active'");
		return template.queryForObject(
				"select mst_section.sk_section_id,mst_section.section_name,mst_subsection.sk_subsection_id,mst_subsection.subsection_name , mst_questionare.* FROM mst_section,mst_subsection, mst_questionare WHERE mst_questionare.section_id=mst_section.sk_section_id AND mst_questionare.subsection_id=mst_subsection.sk_subsection_id AND  mst_questionare.sk_question_id='"
						+ sk_question_id + "' AND question_status='active'",
				new RowMapper<MysteryShoppingVisitsBean>() {
					@Override
					public MysteryShoppingVisitsBean mapRow(ResultSet rs, int row) throws SQLException {
						mvBean.setSection_id(rs.getString("section_id"));
						mvBean.setSection_name(rs.getString("section_name"));
						mvBean.setSubsection_id(rs.getString("subsection_id"));
						mvBean.setSubsection_name(rs.getString("subsection_name"));
						mvBean.setSk_question_id(rs.getString("sk_question_id"));
						mvBean.setStandard_number(rs.getString("standard_number"));
						mvBean.setStandard_number_sequence(rs.getString("standard_number_sequence"));
						mvBean.setQuestion_type(rs.getString("question_type"));
						mvBean.setQuestion_optiontype(rs.getString("question_optiontype"));
						mvBean.setQuestion_points(rs.getString("question_points"));
						mvBean.setQuestion_code(rs.getString("question_code"));
						mvBean.setQuestion_text(rs.getString("question"));
						mvBean.setAnswer_type(rs.getString("answer_type"));
						mvBean.setSuper_quesiton_id(rs.getString("super_question_id"));
						mvBean.setSuper_question_answer(rs.getString("super_question_answer"));
						mvBean.setFormula_flag(rs.getString("formula_flag"));
						/*
						 * String questionId=rs.getString("sk_question_id"); String
						 * question_type=rs.getString("question_type");
						 */
						/*
						 * if(question_type.equals("Dependent Question With Set Of SubQuestions")) {
						 * log.info("dependent question SubQuestions details in if");
						 * List<MysteryShoppingVisitsBean>subquestionDetails=getSubquestiondetails(
						 * mvBean, questionId); mvBean.setSubquestionDetails(subquestionDetails); }
						 */
						log.info("getDependentQuestionDetails method return" + new Gson().toJson(mvBean));
						return mvBean;
					}
				});
	}

	public List<MysteryShoppingVisitsBean> getSubquestiondetails(MysteryShoppingVisitsBean mvBean, String questionId) {
		log.info("getSubquestiondetails method" + new Gson().toJson(mvBean));
		log.info("getSubquestiondetails method :"+"SELECT * FROM `mst_questionare_subquestion` WHERE question_id='" + questionId
				+ "' AND subquestion_status='active'");
		return template.query("SELECT * FROM `mst_questionare_subquestion` WHERE question_id='" + questionId
				+ "' AND subquestion_status='active' ", new RowMapper<MysteryShoppingVisitsBean>() {
					@Override
					public MysteryShoppingVisitsBean mapRow(ResultSet rs, int row) throws SQLException {
						MysteryShoppingVisitsBean mvBean = new MysteryShoppingVisitsBean();
						mvBean.setSubquestionid(rs.getString("sk_subquestion_id"));
						mvBean.setQuestion_id(rs.getString("question_id"));
						mvBean.setQuestion_type(rs.getString("question_type"));
						mvBean.setSub_question_text(rs.getString("subquestion"));
						mvBean.setSubanswertype(rs.getString("subanswer_type"));
						log.info("getSubquestiondetails method return" + new Gson().toJson(mvBean));
						return mvBean;
					}
				});

	}

	public MysteryShoppingVisitsBean addOutletScore(MysteryShoppingVisitsBean mBean, String shopper_id, String year) {
		log.info("addOutletScore method" + new Gson().toJson(mBean));
		// String year="2019";
		MysteryShoppingVisitsBean mvBean1 = new MysteryShoppingVisitsBean();
		mvBean1 = hDao.getScoreWeightage(mvBean1, year);
		String pe_weightage = mvBean1.getPe_weightage();
		String ct_weightage = mvBean1.getCt_weightage();
		String osc_weightage = mvBean1.getOsc_weightage();
		log.info("pe_weightage add outletscore" + pe_weightage);
		log.info("ct_weightage add outletscore" + ct_weightage);
		log.info("osc_weightage add outletscore" + osc_weightage);

		log.info("addOutletScore method :"+
				"SELECT COUNT(shopper_id) as exist,mst_shopper_details.mode_of_contact  from mys_txn_outlet_score LEFT JOIN  mst_shopper_details on mst_shopper_details.sk_shopper_id=mys_txn_outlet_score.shopper_id WHERE mst_shopper_details.sk_shopper_id='"
						+ shopper_id + "'");
		String sql = "SELECT COUNT(shopper_id) as exist,mst_shopper_details.mode_of_contact  from mys_txn_outlet_score LEFT JOIN  mst_shopper_details on mst_shopper_details.sk_shopper_id=mys_txn_outlet_score.shopper_id WHERE mst_shopper_details.sk_shopper_id='"
				+ shopper_id + "'";
		String count = "";
		String mode = "";
		List<Map<String, Object>> list = template.queryForList(sql);
		for (Map<String, Object> row : list) {
			count = row.get("exist").toString();
			mode = row.get("mode_of_contact").toString();
		}
		log.info(mode);
		int id_status = Integer.parseInt(count);
		if (id_status > 0) {
			log.info("exist");
			String sql1 = "";
			if (mode.contentEquals("Online Sales Channel")) {
				sql1 = "SELECT res1.*,  max_points,  scored_points,round(((scored_points/max_points)*100),2) as osc_outlet_score FROM (SELECT (SELECT month(mst_shopper_details.visit_date) as month FROM mst_shopper_details WHERE mst_shopper_details.sk_shopper_id='"
						+ shopper_id
						+ "') as month,  (SELECT IFNULL(sum(scored_points),0)  from mys_score WHERE  shopper_id='"
						+ shopper_id
						+ "'   and status='active' )as scored_points,(SELECT IFNULL(sum(max_points),0) from mys_score WHERE  shopper_id='"
						+ shopper_id
						+ "'   and status='active'  )as max_points, mys_score.shopper_id,mys_score.osc_flag,mys_score.outlet_id,mys_score.dealer_id,mys_score.quarter,mys_score.year from mys_score WHERE  shopper_id='"
						+ shopper_id + "'   and status='active'  GROUP BY shopper_id  )res1";

				log.info("addOutletScore method : if (id_status > 0)"+sql1);
				return template.queryForObject(sql1, new RowMapper<MysteryShoppingVisitsBean>() {
					public MysteryShoppingVisitsBean mapRow(ResultSet rs, int row) throws SQLException {
						String shopper_id = rs.getString("shopper_id");
						String year = rs.getString("year");
						String month = rs.getString("month");
						String outlet_id = rs.getString("outlet_id");
						String dealer_id = rs.getString("dealer_id");
						String osc_flag = rs.getString("osc_flag");
						String quater = rs.getString("quarter");
						String scored_point = rs.getString("scored_points");
						String maximum_point = rs.getString("max_points");
						String outlet_score = rs.getString("osc_outlet_score");
						getbrandid(mBean, shopper_id);
						String brand_id = mBean.getBrand_id();
						String sql4 = "SELECT round((SUM(mys_txn_outlet_score.scored_point)/ SUM(mys_txn_outlet_score.maximum_point))*100 ,2) AS ytd_score_percentage FROM mys_txn_outlet_score WHERE brand_id='"
								+ brand_id + "' AND year='" + rs.getString("year") + "' AND dealer_id='"
								+ rs.getString("dealer_id") + "' AND month<='" + rs.getString("month")
								+ "' AND osc_flag='1' and mys_txn_outlet_score.outlet_score_status='active'";

						log.info("addOutletScore method selecting round :"+sql4);
						return template.queryForObject(sql4, new RowMapper<MysteryShoppingVisitsBean>() {
							public MysteryShoppingVisitsBean mapRow(ResultSet rs1, int row) throws SQLException {

								template.execute("update mys_txn_outlet_score set scored_point='" + scored_point
										+ "', maximum_point='" + maximum_point + "',monthly_score_percentage='"
										+ outlet_score + "',ytd_score_percentage='"
										+ rs1.getString("ytd_score_percentage") + "' where shopper_id='" + shopper_id
										+ "'");
								log.info("addOutletScore method return" + new Gson().toJson(mBean));
								return mBean;
							}

						});

					}

				});
			} else {

				sql1 = "SELECT res1.*, round((sec2_sp+sec3_sp)/(sec2_mp+sec3_mp)*100,2) as outlet_score,(sec2_sp+sec3_sp)as scoredpoint, (sec2_mp+sec3_mp)as maximumpoint FROM (SELECT (SELECT month(mst_shopper_details.visit_date) as month FROM mst_shopper_details WHERE mst_shopper_details.sk_shopper_id='"
						+ shopper_id + "')month,  (SELECT SUM(scored_points)*" + pe_weightage
						+ " from mys_score WHERE  shopper_id='" + shopper_id
						+ "'   and status='active' and mys_score.section_id='2' )as sec2_sp,(SELECT SUM(scored_points)*"
						+ ct_weightage + "from mys_score WHERE  shopper_id='" + shopper_id
						+ "'  and status='active' and mys_score.section_id='3' )as sec3_sp,(SELECT SUM(max_points)*"
						+ pe_weightage + " from mys_score WHERE  shopper_id='" + shopper_id
						+ "'   and status='active' and mys_score.section_id='2' )as sec2_mp,(SELECT SUM(max_points)*"
						+ ct_weightage + " from mys_score WHERE  shopper_id='" + shopper_id
						+ "'  and status='active' and mys_score.section_id='3' )as sec3_mp, mys_score.shopper_id,mys_score.osc_flag,mys_score.outlet_id,mys_score.dealer_id,mys_score.quarter,mys_score.year from mys_score WHERE  shopper_id='"
						+ shopper_id
						+ "'   and status='active' and mys_score.section_id='2' GROUP BY shopper_id  )res1";

				log.info("addOutletScore method else part :"+sql1);
				return template.queryForObject(sql1, new RowMapper<MysteryShoppingVisitsBean>() {
					public MysteryShoppingVisitsBean mapRow(ResultSet rs, int row) throws SQLException {
						String shopper_id = rs.getString("shopper_id");
						String year = rs.getString("year");
						String month = rs.getString("month");
						String outlet_id = rs.getString("outlet_id");
						String dealer_id = rs.getString("dealer_id");
						String osc_flag = rs.getString("osc_flag");
						String quater = rs.getString("quarter");
						String scored_point = rs.getString("scoredpoint");
						String maximum_point = rs.getString("maximumpoint");
						String outlet_score = rs.getString("outlet_score");
						getbrandid(mBean, shopper_id);
						String brand_id = mBean.getBrand_id();
						String sql4 = "SELECT round((SUM(mys_txn_outlet_score.scored_point)/ SUM(mys_txn_outlet_score.maximum_point))*100 ,2) AS ytd_score_percentage FROM mys_txn_outlet_score WHERE brand_id='"
								+ brand_id + "' AND year='" + rs.getString("year") + "' AND outlet_id='"
								+ rs.getString("outlet_id") + "' AND month<='" + rs.getString("month")
								+ "' AND osc_flag='" + rs.getString("osc_flag")
								+ "' and mys_txn_outlet_score.outlet_score_status='active'";

						log.info("addOutletScore method selecting round :"+sql4);
						return template.queryForObject(sql4, new RowMapper<MysteryShoppingVisitsBean>() {
							public MysteryShoppingVisitsBean mapRow(ResultSet rs1, int row) throws SQLException {

								template.execute("update mys_txn_outlet_score set scored_point='" + scored_point
										+ "', maximum_point='" + maximum_point + "',monthly_score_percentage='"
										+ outlet_score + "',ytd_score_percentage='"
										+ rs1.getString("ytd_score_percentage") + "' where shopper_id='" + shopper_id
										+ "'");
								log.info("addOutletScore method return" + new Gson().toJson(mBean));
								return mBean;
							}

						});

					}

				});
			}
		} else {
			log.info("not exist tryhrty");
			String sql3 = "";
			String sql2 = "";
			if (mode.contentEquals("Online Sales Channel")) {

				sql3 = "SELECT res1.*,  max_points,  scored_points,round(((scored_points/max_points)*100),2) as osc_outlet_score FROM (SELECT (SELECT month(mst_shopper_details.visit_date) as month FROM mst_shopper_details WHERE mst_shopper_details.sk_shopper_id='"
						+ shopper_id
						+ "') as month,  (SELECT IFNULL(sum(scored_points),0)  from mys_score WHERE  shopper_id='"
						+ shopper_id
						+ "'   and status='active' )as scored_points,(SELECT IFNULL(sum(max_points),0) from mys_score WHERE  shopper_id='"
						+ shopper_id
						+ "'   and status='active'  )as max_points, mys_score.shopper_id,mys_score.osc_flag,mys_score.outlet_id,mys_score.dealer_id,mys_score.quarter,mys_score.year from mys_score WHERE  shopper_id='"
						+ shopper_id + "'   and status='active'  GROUP BY shopper_id  )res1";

				log.info("addOutletScore method if mode==Online Sales Channel :"+sql3);
				return template.queryForObject(sql3, new RowMapper<MysteryShoppingVisitsBean>() {
					public MysteryShoppingVisitsBean mapRow(ResultSet rs, int row) throws SQLException {
						String shopper_id = rs.getString("shopper_id");
						String year = rs.getString("year");
						String month = rs.getString("month");
						String outlet_id = rs.getString("outlet_id");
						String dealer_id = rs.getString("dealer_id");
						String osc_flag = rs.getString("osc_flag");
						String quater = rs.getString("quarter");
						String scored_point = rs.getString("scored_points");
						String maximum_point = rs.getString("max_points");
						String outlet_score = rs.getString("osc_outlet_score");
						getbrandid(mBean, shopper_id);
						String brand_id = mBean.getBrand_id();
						template.execute(
								"INSERT INTO `mys_txn_outlet_score`(`shopper_id`, `year`, `month`, `outlet_id`, `dealer_id`, `osc_flag`, `brand_id`, `quater`, `scored_point`, `maximum_point`, `monthly_score_percentage`) VALUES "
										+ "('" + shopper_id + "','" + year + "','" + month + "','" + outlet_id + "','"
										+ dealer_id + "','" + osc_flag + "','" + brand_id + "','" + quater + "','"
										+ scored_point + "','" + maximum_point + "','" + outlet_score + "')");
						log.info("addOutletScore method inserting :"+
								"INSERT INTO `mys_txn_outlet_score`(`shopper_id`, `year`, `month`, `outlet_id`, `dealer_id`, `osc_flag`, `brand_id`, `quater`, `scored_point`, `maximum_point`, `monthly_score_percentage`) VALUES "
										+ "('" + shopper_id + "','" + year + "','" + month + "','" + outlet_id + "','"
										+ dealer_id + "','" + osc_flag + "','" + brand_id + "','" + quater + "','"
										+ scored_point + "','" + maximum_point + "','" + outlet_score + "')");

						String sql3 = "SELECT round((SUM(mys_txn_outlet_score.scored_point)/ SUM(mys_txn_outlet_score.maximum_point))*100 ,2) AS ytd_score_percentage FROM mys_txn_outlet_score WHERE brand_id='"
								+ brand_id + "' AND year='" + rs.getString("year") + "' AND dealer_id='"
								+ rs.getString("dealer_id") + "' AND month<='" + rs.getString("month")
								+ "' AND osc_flag='1' and mys_txn_outlet_score.outlet_score_status='active'";

						log.info("addOutletScore method selecting round off :"+sql3);
						return template.queryForObject(sql3, new RowMapper<MysteryShoppingVisitsBean>() {
							public MysteryShoppingVisitsBean mapRow(ResultSet rs1, int row) throws SQLException {
								log.info(rs1.getString("ytd_score_percentage"));
								template.execute("update mys_txn_outlet_score set ytd_score_percentage='"
										+ rs1.getString("ytd_score_percentage") + "' where shopper_id='" + shopper_id
										+ "'");
								log.info("update mys_txn_outlet_score set ytd_score_percentage='"
										+ rs1.getString("ytd_score_percentage") + "' where shopper_id='" + shopper_id
										+ "'");
								log.info("addOutletScore method return" + new Gson().toJson(mBean));
								return mBean;
							}

						});

					}

				});

			} else {
				sql2 = "SELECT res1.*, round((sec2_sp+sec3_sp)/(sec2_mp+sec3_mp)*100,2) as outlet_score,(sec2_sp+sec3_sp)as scoredpoint, (sec2_mp+sec3_mp)as maximumpoint FROM (SELECT (SELECT month(mst_shopper_details.visit_date) as month FROM mst_shopper_details WHERE mst_shopper_details.sk_shopper_id='"
						+ shopper_id + "')month,  (SELECT SUM(scored_points)*" + pe_weightage
						+ " from mys_score WHERE  shopper_id='" + shopper_id
						+ "'   and status='active' and mys_score.section_id='2' )as sec2_sp,(SELECT SUM(scored_points)*"
						+ ct_weightage + " from mys_score WHERE  shopper_id='" + shopper_id
						+ "'  and status='active' and mys_score.section_id='3' )as sec3_sp,(SELECT SUM(max_points)*"
						+ pe_weightage + " from mys_score WHERE  shopper_id='" + shopper_id
						+ "'   and status='active' and mys_score.section_id='2' )as sec2_mp,(SELECT SUM(max_points)*"
						+ ct_weightage + " from mys_score WHERE  shopper_id='" + shopper_id
						+ "'  and status='active' and mys_score.section_id='3' )as sec3_mp, mys_score.shopper_id,mys_score.osc_flag,mys_score.outlet_id,mys_score.dealer_id,mys_score.quarter,mys_score.year from mys_score WHERE  shopper_id='"
						+ shopper_id
						+ "'   and status='active' and mys_score.section_id='2' GROUP BY shopper_id  )res1";

				log.info("addOutletScore method sql2 :"+sql2);
				return template.queryForObject(sql2, new RowMapper<MysteryShoppingVisitsBean>() {
					public MysteryShoppingVisitsBean mapRow(ResultSet rs, int row) throws SQLException {
						String shopper_id = rs.getString("shopper_id");
						String year = rs.getString("year");
						String month = rs.getString("month");
						String outlet_id = rs.getString("outlet_id");
						String dealer_id = rs.getString("dealer_id");
						String osc_flag = rs.getString("osc_flag");
						String quater = rs.getString("quarter");
						String scored_point = rs.getString("scoredpoint");
						String maximum_point = rs.getString("maximumpoint");
						String outlet_score = rs.getString("outlet_score");
						getbrandid(mBean, shopper_id);
						String brand_id = mBean.getBrand_id();
						template.execute(
								"INSERT INTO `mys_txn_outlet_score`(`shopper_id`, `year`, `month`, `outlet_id`, `dealer_id`, `osc_flag`, `brand_id`, `quater`, `scored_point`, `maximum_point`, `monthly_score_percentage`) VALUES "
										+ "('" + shopper_id + "','" + year + "','" + month + "','" + outlet_id + "','"
										+ dealer_id + "','" + osc_flag + "','" + brand_id + "','" + quater + "','"
										+ scored_point + "','" + maximum_point + "','" + outlet_score + "')");
						log.info("addOutletScore method inserting"+
								"INSERT INTO `mys_txn_outlet_score`(`shopper_id`, `year`, `month`, `outlet_id`, `dealer_id`, `osc_flag`, `brand_id`, `quater`, `scored_point`, `maximum_point`, `monthly_score_percentage`) VALUES "
										+ "('" + shopper_id + "','" + year + "','" + month + "','" + outlet_id + "','"
										+ dealer_id + "','" + osc_flag + "','" + brand_id + "','" + quater + "','"
										+ scored_point + "','" + maximum_point + "','" + outlet_score + "')");

						String sql3 = "SELECT round((SUM(mys_txn_outlet_score.scored_point)/ SUM(mys_txn_outlet_score.maximum_point))*100 ,2) AS ytd_score_percentage FROM mys_txn_outlet_score WHERE brand_id='"
								+ brand_id + "' AND year='" + rs.getString("year") + "' AND outlet_id='"
								+ rs.getString("outlet_id") + "' AND month<='" + rs.getString("month")
								+ "' AND osc_flag='" + rs.getString("osc_flag")
								+ "' and mys_txn_outlet_score.outlet_score_status='active'";

						log.info("addOutletScore method selecting round off :"+sql3);
						return template.queryForObject(sql3, new RowMapper<MysteryShoppingVisitsBean>() {
							public MysteryShoppingVisitsBean mapRow(ResultSet rs1, int row) throws SQLException {
								log.info(rs1.getString("ytd_score_percentage"));
								template.execute("update mys_txn_outlet_score set ytd_score_percentage='"
										+ rs1.getString("ytd_score_percentage") + "' where shopper_id='" + shopper_id
										+ "'");
								log.info("update mys_txn_outlet_score set ytd_score_percentage='"
										+ rs1.getString("ytd_score_percentage") + "' where shopper_id='" + shopper_id
										+ "'");
								log.info("addOutletScore method return" + new Gson().toJson(mBean));
								return mBean;
							}

						});

					}

				});
			}
		}

	}

	private MysteryShoppingVisitsBean getbrandid(MysteryShoppingVisitsBean mBean, String shopper_id) {
		String sql1 = "SELECT * FROM `mst_shopper_details` WHERE `sk_shopper_id`='" + shopper_id + "' ";
		log.info("getbrandid method" + new Gson().toJson(mBean));
		log.info("getbrandid method :"+sql1);
		return template.queryForObject(sql1, new RowMapper<MysteryShoppingVisitsBean>() {
			public MysteryShoppingVisitsBean mapRow(ResultSet rs, int row) throws SQLException {
				mBean.setBrand_id(rs.getString("brand_id"));
				log.info("getbrandid method return" + new Gson().toJson(mBean));
				return mBean;
			}

		});

	}

	public List<MysteryShoppingVisitsBean> getYear(MysteryShoppingVisitsBean mvBean, String brand_id) {
		log.info("getYear method" + new Gson().toJson(mvBean));
		log.info("getYear method :"+"SELECT DISTINCT  year  FROM `mst_shopper_details` WHERE `brand_id`='" + brand_id
				+ "' AND visit_status='Published'");
		return template.query("SELECT DISTINCT year  FROM `mst_shopper_details` WHERE `brand_id`='" + brand_id
				+ "' AND visit_status='Published'", new RowMapper<MysteryShoppingVisitsBean>() {
					@Override
					public MysteryShoppingVisitsBean mapRow(ResultSet rs, int row) throws SQLException {
						MysteryShoppingVisitsBean mvBean = new MysteryShoppingVisitsBean();
						mvBean.setYear(rs.getString("year"));
						log.info("getYear method return" + new Gson().toJson(mvBean));
						return mvBean;
					}
				});

	}

	public List<MysteryShoppingVisitsBean> getMysPublishedDetails(MysteryShoppingVisitsBean mvBean) {
		log.info("getYegetMysPublishedDetailsar method" + new Gson().toJson(mvBean));
		log.info("getYegetMysPublishedDetailsar method :"+
				"SELECT mst_dealer.dealer_name,mst_dealer_outlet.outlet_name,mst_brand.brand_name, mst_shopper_details.* FROM mst_dealer,mst_dealer_outlet,mst_brand,mst_shopper_details where mst_shopper_details.dealer_id=mst_dealer.sk_dealer_id AND mst_shopper_details.outlet_id=mst_dealer_outlet.sk_outlet_id  AND mst_shopper_details.brand_id=mst_brand.sk_brand_id AND mst_brand.brand_status='active' AND mst_dealer.dealer_status='active' AND mst_dealer_outlet.outlet_status='active' AND mst_shopper_details.status='active' and mst_shopper_details.brand_id='"
						+ mvBean.getBrand_id() + "' AND mst_shopper_details.year='" + mvBean.getYear()
						+ "' AND mode_of_contact='" + mvBean.getMode_of_contact() + "' AND month(visit_date)='"
						+ mvBean.getMonth() + "' AND mst_shopper_details.visit_status='Published';");
		return template.query(
				"SELECT mst_dealer.dealer_name,mst_dealer_outlet.outlet_name,mst_brand.brand_name, mst_shopper_details.* FROM mst_dealer,mst_dealer_outlet,mst_brand,mst_shopper_details where mst_shopper_details.dealer_id=mst_dealer.sk_dealer_id AND mst_shopper_details.outlet_id=mst_dealer_outlet.sk_outlet_id  AND mst_shopper_details.brand_id=mst_brand.sk_brand_id AND mst_brand.brand_status='active' AND mst_dealer.dealer_status='active' AND mst_dealer_outlet.outlet_status='active' AND mst_shopper_details.status='active' and mst_shopper_details.brand_id='"
						+ mvBean.getBrand_id() + "' AND mst_shopper_details.year='" + mvBean.getYear()
						+ "' AND mode_of_contact='" + mvBean.getMode_of_contact() + "' AND month(visit_date)='"
						+ mvBean.getMonth() + "' AND mst_shopper_details.visit_status='Published';",
				new RowMapper<MysteryShoppingVisitsBean>() {
					@Override
					public MysteryShoppingVisitsBean mapRow(ResultSet rs, int row) throws SQLException {
						MysteryShoppingVisitsBean mvBean = new MysteryShoppingVisitsBean();
						mvBean.setSk_shopper_id(rs.getString("sk_shopper_id"));
						String sk_shopper_id = rs.getString("sk_shopper_id");
						mvBean.setDealer_name(rs.getString("dealer_name"));
						mvBean.setOutlet_name(rs.getString("outlet_name"));
						mvBean.setBrand_name(rs.getString("brand_name"));
						mvBean.setVisit_date(rs.getString("visit_date"));
						mvBean.setName(rs.getString("name"));
						mvBean.setEmail(rs.getString("email"));
						mvBean.setContact_number(rs.getString("contact_number"));
						mvBean.setShoppers_car_owned(rs.getString("shoppers_car_owned"));
						mvBean.setShoppers_car_yop(rs.getString("shoppers_car_yop"));
						mvBean.setAge(rs.getString("age"));
						mvBean.setGender(rs.getString("gender"));
						mvBean.setYear(rs.getString("year"));
						mvBean.setQuarter(rs.getString("quarter"));
						mvBean.setMode_of_contact(rs.getString("mode_of_contact"));
						mvBean.setSc_name(rs.getString("sc_name"));
						String year = rs.getString("year");
						if (rs.getString("mode_of_contact").equals("Online Sales Channel")) {
							try {
								getOutletLevelScore(mvBean, sk_shopper_id);
								mvBean.setOutlet_level_score(String
										.valueOf(Math.round(Double.parseDouble(mvBean.getOutlet_level_score()) * 100.00)
												/ 100.00));

							} catch (Exception e) {
								e.printStackTrace();
							}
						} else {
							try {
								getOutletLevelScoreForNonOsc(mvBean, sk_shopper_id, year);
								mvBean.setOutlet_level_score(String
										.valueOf(Math.round(Double.parseDouble(mvBean.getOutlet_level_score()) * 100.00)
												/ 100.00));

							} catch (Exception e) {
								e.printStackTrace();
							}
						}

						try {
							getPEScore(mvBean, sk_shopper_id);
							// mvBean.setProcess_excellence_score(mvBean.getProcess_excellence_score());
							mvBean.setProcess_excellence_score(String.valueOf(
									Math.round(Double.parseDouble(mvBean.getProcess_excellence_score()) * 100.00)
											/ 100.00));

						} catch (Exception e) {
							e.printStackTrace();
						}

						try {
							getCTScore(mvBean, sk_shopper_id);
							mvBean.setCustomer_treatment_score(String.valueOf(
									Math.round(Double.parseDouble(mvBean.getCustomer_treatment_score()) * 100.00)
											/ 100.00));
						} catch (Exception e) {
							e.printStackTrace();
						}
						// getPEScore(mvBean,sk_shopper_id);
						// mvBean.setProcess_excellence_score(mvBean.getProcess_excellence_score());
						// getCTScore(mvBean,sk_shopper_id);
						// mvBean.setCustomer_treatment_score(mvBean.getCustomer_treatment_score());
						log.info("getYegetMysPublishedDetailsar method return" + new Gson().toJson(mvBean));
						return mvBean;
					}
				});

	}

	public MysteryShoppingVisitsBean getOutletLevelScore(MysteryShoppingVisitsBean mvBean, String sk_shopper_id) {
		log.info("getOutletLevelScore method" + new Gson().toJson(mvBean));
		log.info("getOutletLevelScore method"+
				"select monthly_score_percentage   from mys_txn_outlet_score where shopper_id='" + sk_shopper_id + "'");
		return template.queryForObject(
				"select monthly_score_percentage   from mys_txn_outlet_score where shopper_id='" + sk_shopper_id + "';",
				new RowMapper<MysteryShoppingVisitsBean>() {

					public MysteryShoppingVisitsBean mapRow(ResultSet rs, int row) throws SQLException {
						mvBean.setOutlet_level_score(rs.getString("monthly_score_percentage"));
						log.info("getOutletLevelScore method return" + new Gson().toJson(mvBean));
						return mvBean;
					}
				});

	}

	public MysteryShoppingVisitsBean getOutletLevelScoreForNonOsc(MysteryShoppingVisitsBean mvBean,
			String sk_shopper_id, String year) {
		log.info("getOutletLevelScoreForNonOsc method" + new Gson().toJson(mvBean));
		log.info("outlet score for non osc in dao");
		MysteryShoppingVisitsBean mvBean1 = new MysteryShoppingVisitsBean();
		mvBean1 = hDao.getScoreWeightage(mvBean1, year);
		String pe_weightage = mvBean1.getPe_weightage();
		String ct_weightage = mvBean1.getCt_weightage();
		String osc_weightage = mvBean1.getOsc_weightage();
		log.info("pe weightage" + pe_weightage);
		log.info("ct weightage" + ct_weightage);
		log.info("osc weightage" + osc_weightage);

		log.info("getOutletLevelScoreForNonOsc method :"+
				"SELECT IFNULL(sum(scored_points)*" + pe_weightage + ",0) as one_scored_points, IFNULL(sum(max_points)*"
						+ pe_weightage + ",0) as one_max_points,(SELECT IFNULL(sum(scored_points)*" + ct_weightage
						+ ",0) as two_scored_points from mys_score where section_id='3' and mys_score.shopper_id='"
						+ sk_shopper_id + "') as two_scored_points,(SELECT  IFNULL(sum(max_points)*" + ct_weightage
						+ ",0)  from mys_score where section_id='3' and mys_score.shopper_id='" + sk_shopper_id
						+ "')as two_max_points from mys_score where section_id='2' and mys_score.shopper_id='"
						+ sk_shopper_id + "'");
		return template.queryForObject(
				"SELECT IFNULL(sum(scored_points)*" + pe_weightage + ",0) as one_scored_points, IFNULL(sum(max_points)*"
						+ pe_weightage + ",0) as one_max_points,(SELECT IFNULL(sum(scored_points)*" + ct_weightage
						+ ",0) as two_scored_points from mys_score where section_id='3' and mys_score.shopper_id='"
						+ sk_shopper_id + "') as two_scored_points,(SELECT  IFNULL(sum(max_points)*" + ct_weightage
						+ ",0)  from mys_score where section_id='3' and mys_score.shopper_id='" + sk_shopper_id
						+ "')as two_max_points from mys_score where section_id='2' and mys_score.shopper_id='"
						+ sk_shopper_id + "'",
				new RowMapper<MysteryShoppingVisitsBean>() {

					public MysteryShoppingVisitsBean mapRow(ResultSet rs, int row) throws SQLException {
						double one = Double.parseDouble(rs.getString("one_scored_points"))
								+ Double.parseDouble(rs.getString("two_scored_points"));
						double two = Double.parseDouble(rs.getString("one_max_points"))
								+ Double.parseDouble(rs.getString("two_max_points"));
						log.info(Double.parseDouble(rs.getString("one_scored_points")) + "one Score"
								+ Double.parseDouble(rs.getString("two_scored_points")));
						double percentage = ((double) one) / ((double) two) * 100;
						if (two == 0) {

							mvBean.setOutlet_level_score("0");
						} else {

							mvBean.setOutlet_level_score(String.valueOf(percentage));
						}
						log.info("getOutletLevelScoreForNonOsc method return" + new Gson().toJson(mvBean));
						return mvBean;
					}
				});

	}

	public MysteryShoppingVisitsBean getPEScore(MysteryShoppingVisitsBean mvBean, String sk_shopper_id) {
		log.info("getPEScore method" + new Gson().toJson(mvBean));
		log.info("getPEScore method :"+"select(SUM(scored_points)/sum(max_points))*100 as PE_SCORE from mys_score where shopper_id='"
				+ sk_shopper_id + "' and section_id='2'");
		return template.queryForObject(
				"select(SUM(scored_points)/sum(max_points))*100 as PE_SCORE from mys_score where shopper_id='"
						+ sk_shopper_id + "' and section_id='2';", // here section id 2 is used, because 2 is used for
																	// process excellence
				new RowMapper<MysteryShoppingVisitsBean>() {

					public MysteryShoppingVisitsBean mapRow(ResultSet rs, int row) throws SQLException {
						mvBean.setProcess_excellence_score(rs.getString("PE_SCORE"));
						log.info("getPEScore method return" + new Gson().toJson(mvBean));
						return mvBean;
					}
				});

	}

	public MysteryShoppingVisitsBean getCTScore(MysteryShoppingVisitsBean mvBean, String sk_shopper_id) {
		log.info("getCTScore method" + new Gson().toJson(mvBean));
		log.info("getCTScore method :"+"select(SUM(scored_points)/sum(max_points))*100 as CT_SCORE from mys_score where shopper_id='"
				+ sk_shopper_id + "' and section_id='3'");
		return template.queryForObject(
				"select(SUM(scored_points)/sum(max_points))*100 as CT_SCORE from mys_score where shopper_id='"
						+ sk_shopper_id + "' and section_id='3';", // here section id 3 is used, because 3 is used for
																	// Customer Treatment
				new RowMapper<MysteryShoppingVisitsBean>() {

					public MysteryShoppingVisitsBean mapRow(ResultSet rs, int row) throws SQLException {
						mvBean.setCustomer_treatment_score(rs.getString("CT_SCORE"));
						log.info("getCTScore method return" + new Gson().toJson(mvBean));
						return mvBean;
					}
				});

	}

	public MysteryShoppingVisitsBean getOutletLevelScoreForonlineSales(MysteryShoppingVisitsBean mvBean,
			String sk_shopper_id) {
		log.info("getOutletLevelScoreForonlineSales method" + new Gson().toJson(mvBean));
		log.info("getOutletLevelScoreForonlineSales method :"+
				"SELECT IFNULL(sum(max_points),0) as max_points, IFNULL(sum(scored_points),0) as scored_points from mys_score where shopper_id='"
						+ sk_shopper_id + "';");
		return template.queryForObject(
				"SELECT IFNULL(sum(max_points),0) as max_points, IFNULL(sum(scored_points),0) as scored_points from mys_score where shopper_id='"
						+ sk_shopper_id + "'",
				new RowMapper<MysteryShoppingVisitsBean>() {

					public MysteryShoppingVisitsBean mapRow(ResultSet rs, int row) throws SQLException {
						double percentage = ((double) Integer.parseInt(rs.getString("scored_points"))
								/ (double) Integer.parseInt(rs.getString("max_Points"))) * 100;
						if (rs.getString("max_Points").equals("0")) {
							log.info("dAFVS");
							mvBean.setOutlet_level_score("0");
						} else {
							log.info("sad");
							mvBean.setOutlet_level_score(String.valueOf(percentage));
						}
						log.info("getOutletLevelScoreForonlineSales method return" + new Gson().toJson(mvBean));
						return mvBean;
					}
				});

	}

	public void submitReview(MysteryShoppingVisitsBean mBean, String visit_status, String sk_shopper_id,
			String user_id) {
		log.info("submitReview method" + new Gson().toJson(mBean));
		log.info("submitReview method :"+"UPDATE mst_shopper_details SET visit_status='" + visit_status + "',created_by='" + user_id
				+ "' where sk_shopper_id='" + sk_shopper_id + "'");
		template.execute("UPDATE mst_shopper_details SET visit_status='" + visit_status + "',created_by='" + user_id
				+ "'  where sk_shopper_id='" + sk_shopper_id + "'");

	}

	public void submitReview2(MysteryShoppingVisitsBean mBean, String visit_status, String sk_shopper_id,
			String user_id) {
		log.info("submitReview method" + new Gson().toJson(mBean));
		log.info("submitReview method :"+"UPDATE mst_shopper_details SET visit_status='" + visit_status + "',created_by='" + user_id
				+ "' where sk_shopper_id='" + sk_shopper_id + "'");
		template.execute("UPDATE mst_shopper_details SET visit_status='" + visit_status + "',created_by='" + user_id
				+ "'  where sk_shopper_id='" + sk_shopper_id + "'");

	}

	public MysteryShoppingVisitsBean getMaxScorePointsForReview(MysteryShoppingVisitsBean mBean, String shopper_id) {
		log.info("getMaxScorePointsForReview method" + new Gson().toJson(mBean));
		template.execute("DELETE FROM `mys_score` WHERE `shopper_id`='" + shopper_id + "';");
		try {
			log.info("getMaxScorePointsForReview method :"+
					"SELECT *,(max_points + IFNULL(f1_max_points,0)) as maximum_points,(max_scored_points + IFNULL(f1_scored_points,0)) as maximum_scored_points FROM(select * FROM(SELECT *,SUM(question_points) as max_points,SUM(scored_points) as max_scored_points FROM mys_txn_answers WHERE shopper_id='"
							+ shopper_id
							+ "' and formula_flag=0 and mys_txn_answers.status='active' GROUP BY section_id,subsection_id) as res1 LEFT JOIN (SELECT mys_txn_formulaanswer.shopper_id as f1_shopper_id,mys_txn_formulaanswer.question_formula_flag,SUM(mys_txn_formulaanswer.points) as f1_scored_points,SUM(mst_questionare.question_points) as f1_max_points,mst_questionare.section_id as f1_section_id,mst_questionare.subsection_id as f1_subsection_id FROM mys_txn_formulaanswer LEFT JOIN mst_questionare ON mys_txn_formulaanswer.question_id=mst_questionare.sk_question_id  WHERE shopper_id='"
							+ shopper_id
							+ "' and question_formula_flag=1 AND mys_txn_formulaanswer.status='active' GROUP BY section_id,subsection_id) as res2 ON res1.section_id=res2.f1_section_id AND res1.subsection_id=res2.f1_subsection_id) as res3  GROUP BY section_id,subsection_id ORDER BY section_id ASC");
			return template.queryForObject(
					"SELECT *,(max_points + IFNULL(f1_max_points,0)) as maximum_points,(max_scored_points + IFNULL(f1_scored_points,0)) as maximum_scored_points FROM(select * FROM(SELECT *,SUM(question_points) as max_points,SUM(scored_points) as max_scored_points FROM mys_txn_answers WHERE shopper_id='"
							+ shopper_id
							+ "' and formula_flag=0 and mys_txn_answers.status='active' GROUP BY section_id,subsection_id) as res1 LEFT JOIN (SELECT mys_txn_formulaanswer.shopper_id as f1_shopper_id,mys_txn_formulaanswer.question_formula_flag,SUM(mys_txn_formulaanswer.points) as f1_scored_points,SUM(mst_questionare.question_points) as f1_max_points,mst_questionare.section_id as f1_section_id,mst_questionare.subsection_id as f1_subsection_id FROM mys_txn_formulaanswer LEFT JOIN mst_questionare ON mys_txn_formulaanswer.question_id=mst_questionare.sk_question_id  WHERE shopper_id='"
							+ shopper_id
							+ "' and question_formula_flag=1 AND mys_txn_formulaanswer.status='active' GROUP BY section_id,subsection_id) as res2 ON res1.section_id=res2.f1_section_id AND res1.subsection_id=res2.f1_subsection_id) as res3  GROUP BY section_id,subsection_id ORDER BY section_id ASC ",
					new RowMapper<MysteryShoppingVisitsBean>() {
						public MysteryShoppingVisitsBean mapRow(ResultSet rs1, int row) throws SQLException {
							// log.info("question_points2=="+rs.getString("questionpoint1")+"===scored_points2="+rs.getString("scoredpoint1"));
							// log.info("question_points1=="+mBean.getQuestion_points()+"===scored_points1="+mBean.getScored_points());
							double maximum_points = Double.parseDouble(rs1.getString("maximum_points"));
							double maximum_scored_points = Double.parseDouble(rs1.getString("maximum_scored_points"));
							double percentage;
							if (maximum_scored_points != 0 && maximum_points != 0) {
								percentage = ((maximum_scored_points / maximum_points) * 100);
							} else {
								percentage = 0;
							}
							log.info("getMaxScorePointsForReview method selcting month :"+"SELECT *,month(scheduled_date)as months FROM `mst_msm_result` WHERE shopper_id='"
									+ shopper_id + "'");
							template.query(
									"SELECT *,month(scheduled_date)as months FROM `mst_msm_result` WHERE shopper_id='"
											+ shopper_id + "'",
									new RowMapper<MysteryShoppingVisitsBean>() {

										@Override
										public MysteryShoppingVisitsBean mapRow(ResultSet rs2, int rowNum)
												throws SQLException {
											// TODO Auto-generated method stub
											String osc_flag = "";
											if (rs2.getString("mode_of_contact")
													.contentEquals("Online Sales Channel")) {
												osc_flag = "1";
											} else {
												osc_flag = "0";
											}
											log.info("getMaxScorePointsForReview method Inserting query :"+
													"INSERT INTO `mys_score`(`shopper_id`, `osc_flag`, `outlet_id`, `Outlet_name`, `dealer_id`, `dealer_name`, `region_id`, `region_name`, `quarter`, `year`,month, `section_id`, `section_name`, `subsection_id`, `subsection_name`, `max_points`, `scored_points`, `percentage`, `rank`, `Created_by`, `created_on`, `status`) VALUES ('"
															+ shopper_id + "','" + osc_flag + "','"
															+ rs2.getString("outlet_id") + "','"
															+ rs2.getString("outlet_name") + "','"
															+ rs2.getString("dealership_id") + "','"
															+ rs2.getString("dealership_name") + "','"
															+ rs2.getString("outlet_region_id") + "','"
															+ rs2.getString("outlet_region_name") + "','"
															+ rs2.getString("quarter") + "','" + rs2.getString("year")
															+ "','" + rs2.getString("months") + "','"
															+ rs1.getString("section_id") + "','"
															+ rs1.getString("section_name") + "','"
															+ rs1.getString("subsection_id") + "','"
															+ rs1.getString("subsection_name") + "','" + maximum_points
															+ "','" + maximum_scored_points + "',ROUND('" + percentage
															+ "',2),'rank','createdby','createdon','active'");
											template.execute(
													"INSERT INTO `mys_score`(`shopper_id`, `osc_flag`, `outlet_id`, `Outlet_name`, `dealer_id`, `dealer_name`, `region_id`, `region_name`, `quarter`, `year`,month, `section_id`, `section_name`, `subsection_id`, `subsection_name`, `max_points`, `scored_points`, `percentage`, `rank`, `Created_by`, `created_on`, `status`) VALUES ('"
															+ shopper_id + "','" + osc_flag + "','"
															+ rs2.getString("outlet_id") + "','"
															+ rs2.getString("outlet_name") + "','"
															+ rs2.getString("dealership_id") + "','"
															+ rs2.getString("dealership_name") + "','"
															+ rs2.getString("outlet_region_id") + "','"
															+ rs2.getString("outlet_region_name") + "','"
															+ rs2.getString("quarter") + "','" + rs2.getString("year")
															+ "','" + rs2.getString("months") + "','"
															+ rs1.getString("section_id") + "','"
															+ rs1.getString("section_name") + "','"
															+ rs1.getString("subsection_id") + "','"
															+ rs1.getString("subsection_name") + "','" + maximum_points
															+ "','" + maximum_scored_points + "',ROUND('" + percentage
															+ "',2),'rank','createdby','createdon','active')");

											return mBean;
										}

									});
							log.info("getMaxScorePointsForReview method return" + new Gson().toJson(mBean));
							return mBean;
						}

					});
		} catch (Exception e) {
			e.printStackTrace();
			log.info("getMaxScorePointsForReview method return" + e);
		}
		log.info("getMaxScorePointsForReview method return" + new Gson().toJson(mBean));
		return mBean;

	}

	public void updateToPublish(MysteryShoppingVisitsBean mvBean, String visit_status, String sk_shopper_id,
			String user_id) {
		log.info("updateToPublish method" + new Gson().toJson(mvBean));
		log.info("updateToPublish method :"+"UPDATE mst_shopper_details SET visit_status='" + visit_status + "',created_by='" + user_id
				+ "' where sk_shopper_id='" + sk_shopper_id + "'");
		template.execute("UPDATE mst_shopper_details SET visit_status='" + visit_status + "',created_by='" + user_id
				+ "'  where sk_shopper_id='" + sk_shopper_id + "'");

	}

	public void updateToPublishMsm(MysteryShoppingVisitsBean mBean, String visit_status, String sk_shopper_id,
			String user_id) {
		log.info("updateToPublishMsm method" + new Gson().toJson(mBean));
		InetAddress ip = null;
		try {
			ip = InetAddress.getLocalHost();
		} catch (UnknownHostException e) {
			e.printStackTrace();
		}
		log.info("updateToPublishMsm method :"+"UPDATE mst_msm_result SET visit_status='" + visit_status + "',created_by='" + ip
				+ "' where shopper_id='" + sk_shopper_id + "'");
		template.execute("UPDATE mst_msm_result SET visit_status='" + visit_status + "',created_by='" + ip
				+ "'  where shopper_id='" + sk_shopper_id + "'");

	}

	public MysteryShoppingVisitsBean checkShopperAnswerStatus(MysteryShoppingVisitsBean mBean, String sk_shopper_id) {
		log.info("checkShopperAnswerStatus method" + new Gson().toJson(mBean));
		log.info("checkShopperAnswerStatus method :"+"SELECT count(*) as not_answered_status_count  FROM `mys_txn_answers` WHERE `shopper_id`='"
				+ sk_shopper_id + "' AND answer_status='not answered' and status='active'");
		return template.queryForObject(
				"SELECT count(*) as not_answered_status_count  FROM `mys_txn_answers` WHERE `shopper_id`='"
						+ sk_shopper_id + "' AND answer_status='not answered' and status='active';",
				new RowMapper<MysteryShoppingVisitsBean>() {

					public MysteryShoppingVisitsBean mapRow(ResultSet rs, int row) throws SQLException {
						mBean.setNot_answered_status_count(rs.getString("not_answered_status_count"));
						log.info("checkShopperAnswerStatus method return" + new Gson().toJson(mBean));
						return mBean;
					}
				});

	}

	/*
	 * public void updateAnswerById(MysteryShoppingVisitsBean mBean, String userId)
	 * { // TODO Auto-generated method stub
	 * if(!mBean.getAnswer_type().contentEquals("Select/List")) {
	 * mBean.setOption_points(mBean.getQuestion_points()); }else {
	 * mBean=getoptionsName(mBean); if(!mBean.equals(null)) {
	 * log.info(mBean.getOptions()); log.info(mBean.getOption_points()); }
	 * 
	 * } log.info("1="+mBean.getMain_ques_comment());
	 * log.info("2="+mBean.getOption_comment());
	 * log.info("3="+mBean.getAnswerParagraph()); log.info("4"+mBean.getOptions());
	 * log.info("5="+mBean.getOld_selectionoption_text());
	 * log.info("6="+mBean.getOld_mainquestion_comment());
	 * log.info("7="+mBean.getOldselectOptioncomment());
	 * log.info("8="+mBean.getOld_paragraph());
	 * 
	 * }
	 */

}
