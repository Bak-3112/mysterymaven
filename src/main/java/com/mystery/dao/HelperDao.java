package com.mystery.dao;

import java.util.Random;

import org.apache.commons.io.FilenameUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Random;

import org.apache.commons.io.FilenameUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import com.mystery.beans.MysteryShoppingVisitsBean;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class HelperDao {
	@Autowired
    JdbcTemplate template;
	
	private static final Logger log= LoggerFactory.getLogger(HelperDao.class);
	
	public void setTemplate(JdbcTemplate template) {
		this.template = template;
	}

	public String renameFile(String filename) {
		String data="";
		if(!filename.equals("")){
			String RANCHAR = "ABCDEFGHIJKLMNOPQRSTUVWXYZ1234567890";
	        StringBuilder rename = new StringBuilder();
	        Random rnd = new Random();
	        while (rename.length() < 4) { // length of the random string.
	            int index = (int) (rnd.nextFloat() * RANCHAR.length());
	            rename.append(RANCHAR.charAt(index));
	        }
	        String alpha = rename.toString();
	        

			String ext2 = FilenameUtils.getExtension(filename); // returns "exe"
			 
			  String renamedfilename = alpha+"."+ext2;
			log.info("rename filename name :"+renamedfilename);
			 data = renamedfilename;
			
		}
		else
		{
			 data="";

		}
		return data;
	
	
		
	}
	
public MysteryShoppingVisitsBean getScoreWeightage(MysteryShoppingVisitsBean mvBean, String year) {
		try {
		log.info("SELECT(select `weightage` from mst_score_weightage WHERE section_id=2 and year='"+year+"' ) as pe,(select `weightage` from mst_score_weightage WHERE section_id=3 and year='"+year+"' ) as ct,(select `weightage` from mst_score_weightage WHERE section_id in (4,5) AND year='"+year+"' ) as osc from mst_score_weightage WHERE year='"+year+"' and `status`='active' GROUP by year");
		return template.queryForObject("SELECT(select `weightage` from mst_score_weightage WHERE section_id=2 and year='"+year+"' ) as pe,(select `weightage` from mst_score_weightage WHERE section_id=3 and year='"+year+"' ) as ct,(select `weightage` from mst_score_weightage WHERE section_id in (4,5) AND year='"+year+"' ) as osc from mst_score_weightage WHERE year='"+year+"' and `status`='active' GROUP by year",
				new RowMapper<MysteryShoppingVisitsBean>() {
					public MysteryShoppingVisitsBean mapRow(ResultSet rs, int row) throws SQLException {
						mvBean.setPe_weightage(rs.getString("pe"));
						mvBean.setCt_weightage(rs.getString("ct"));
						mvBean.setOsc_weightage(rs.getString("osc"));
						return mvBean;
					}
				});
		
		// TODO Auto-generated method stub
		}catch (Exception e) {
			// TODO: handle exception
			log.info("catch expection in helper dao"+e);
		}
		return mvBean;
	}

}
