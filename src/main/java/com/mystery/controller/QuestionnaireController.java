package com.mystery.controller;
import java.io.IOException;

import java.util.ArrayList;
import java.util.Iterator;

import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.google.gson.Gson;
import com.mystery.beans.DatabaseManagementBean;
import com.mystery.beans.QuestionnaireBean;
import com.mystery.dao.DatabaseManagementDao;
import com.mystery.dao.QuestionnaireDao;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Controller
public class QuestionnaireController {

	@Autowired
	DatabaseManagementDao dbDao;

	@Autowired
	QuestionnaireDao qDao;
	@RequestMapping("questionCreate")
	public ModelAndView login(DatabaseManagementBean dbBean) {
		log.info("/questionCreate api");                  	        
		ModelAndView mv = new ModelAndView("createquestionnaire");
		List<DatabaseManagementBean> activebrandList = dbDao.getBrandList(dbBean);
		mv.addObject("activebrandList", activebrandList);
		List<DatabaseManagementBean> sectionList = dbDao.getSectionList(dbBean);
		mv.addObject("sectionList", sectionList);

		return mv;

	}
	
	
	@RequestMapping("/questionformula/{qid}")
	public ModelAndView questionformula(@PathVariable String qid, HttpServletRequest request, HttpServletResponse response,QuestionnaireBean qBean) {
		log.info("/questionformula/{qid} api");                  	        

		ModelAndView mv = new ModelAndView("questionformula");
		List<QuestionnaireBean> subsetquestionsList = dbDao.subsetquestionsList(qBean,qid);
		mv.addObject("subsetquestionsList", subsetquestionsList);
		request.setAttribute("qid", qid);
		List<List<QuestionnaireBean>> optionsList = new ArrayList<List<QuestionnaireBean>>();
			Iterator<QuestionnaireBean> iterator = subsetquestionsList.iterator();
			while (iterator.hasNext()) {
				String subid = iterator.next().getSk_subquestion_id();
				optionsList.add(dbDao.getOptionsbysubid(qBean, subid,qid));
			}

			mv.addObject("optionsList", optionsList);
		return mv;

	}
	
	@RequestMapping("/editquestionformula/{qid}")
	public ModelAndView editquestionformula(@PathVariable String qid,HttpServletRequest request, HttpServletResponse response,QuestionnaireBean qBean) {
		ModelAndView mv = new ModelAndView("editquestionformula");
		log.info("/editquestionformula/{qid} api");                  	        
		List<QuestionnaireBean> formulacount = qDao.formulacount(qBean,qid);
		mv.addObject("formulacount", formulacount);
		
		
		List<QuestionnaireBean> subsetquestionsList = dbDao.subsetquestionsList1(qBean,qid);
		mv.addObject("subsetquestionsList", subsetquestionsList);
	 
	
		request.setAttribute("qid", qid);
		List<List<QuestionnaireBean>> optionsList = new ArrayList<List<QuestionnaireBean>>();
			Iterator<QuestionnaireBean> iterator = subsetquestionsList.iterator();
			while (iterator.hasNext()) {
				String subid = iterator.next().getSk_subquestion_id();
				optionsList.add(dbDao.getOptionsbysubid(qBean, subid,qid));
			}

			mv.addObject("optionsList", optionsList);
		return mv;

	}
	
	
	
	@RequestMapping(value = "/createFormula/{qid}", method = RequestMethod.POST)
	public ModelAndView addQuestionnaire1(@PathVariable String qid,HttpServletRequest request, HttpServletResponse response,
			QuestionnaireBean qBean) throws IOException {
		log.info("/createFormula/{qid} api");                  	        
		ModelAndView model = null;
		 model = new ModelAndView("redirect:/questions");
		 List<QuestionnaireBean> formula = qBean.getFormula();
		 String finalresult="";
		  String finalcount="";
		 	if (null != formula && formula.size() > 0) {

				for (QuestionnaireBean formulaList : formula) {
				 finalresult=formulaList.getFormulaFinalResult();
				 finalcount=formulaList.getFormulaCount();
				 try {
		 		for (QuestionnaireBean formulaList1 : formulaList.getFormuladata()) {
		 		
		 		if(formulaList1.getSk_subquestion_id()!=null && formulaList1.getSk_answer_id()!=null) {
		 		qDao.addFormula(qBean, finalresult,finalcount,formulaList1,qid);
		 			
		 		}
		 	}
				 }catch (Exception e) {
					e.printStackTrace();
					log.info("Exception /createFormula/{qid} api"+e); 
				}
		}
	}
		
		return model;
		
		
	}
	
	
	@RequestMapping(value = "/updateFormula/{qid}", method = RequestMethod.POST)
	public ModelAndView updateFormula(@PathVariable String qid,HttpServletRequest request, HttpServletResponse response,
			QuestionnaireBean qBean) throws IOException {
		log.info("/updateFormula/{qid} api");                  	        
		ModelAndView model = null;
		 model = new ModelAndView("redirect:/questions");
		 List<QuestionnaireBean> formula = qBean.getFormula();
		
		 String finalresult="";
		  String finalcount="";
		 	if (null != formula && formula.size() > 0) {

				for (QuestionnaireBean formulaList : formula) {
				 finalresult=formulaList.getFormulaFinalResult();
				 finalcount=formulaList.getFormulaCount();
				 try {
		 		for (QuestionnaireBean formulaList1 : formulaList.getFormuladata()) {
		 		if(formulaList1.getSk_subquestion_id()!=null && formulaList1.getSk_answer_id()!=null) {
		 			qDao.getformulaid(qBean,finalcount,formulaList1,qid);
		 		if(qBean.getSk_formula_id().contentEquals("noformulaid")) {
		 		qDao.addFormula(qBean, finalresult,finalcount,formulaList1,qid);
		 		}else {
		 		qDao.updateFormula(qBean, finalresult,finalcount,formulaList1,qid,qBean.getSk_formula_id());
		 		}
		 			
		 		}
		 	}
				 }catch (Exception e) {
					e.printStackTrace();
					log.info("Exception /updateFormula/{qid} api"+e); 
				}
		}
	}
		
		return model;
		
		
	}
	
	

	@RequestMapping(value = "/createQuestionnairePost", method = RequestMethod.POST)
	public ModelAndView addQuestionnaire(HttpServletRequest request, HttpServletResponse response,
			QuestionnaireBean qBean) throws IOException {
		ModelAndView model = null;
		log.info("/createQuestionnairePost api");                  	        
		List<QuestionnaireBean> questionSelectList = qBean.getAnswerdata();
		List<QuestionnaireBean> subquestionsList = qBean.getSubquestions();
        List<QuestionnaireBean> superQuestions=qBean.getSuperquestions();
		if (qBean.getQuestion_type().equals("Main Question")) {
			model = new ModelAndView("redirect:/questions");
			
			qDao.addMainQuestion(qBean, questionSelectList);
			

		} else if (qBean.getQuestion_type().equals("Dependent Question")) {
			model = new ModelAndView("redirect:/questions");
			qDao.addDependentQuestion(qBean, questionSelectList,superQuestions);
		 
		} else if (qBean.getQuestion_type().equals("Dependent Question With Set Of SubQuestions")) {
		
			
			// for main question insert
			qDao.insertMainquestion(qBean,superQuestions);
			if (null != subquestionsList && subquestionsList.size() > 0) {

				for (QuestionnaireBean subQuestionList : subquestionsList) {
					if (!(subQuestionList.getSubQuestionAnswerType()==null)) {
					if (!subQuestionList.getSubQuestionAnswerType().equals("Select/List")) {
						String subanswertype = subQuestionList.getSubQuestionAnswerType();
						qDao.addDependentQuestionwithSubquestion(qBean, subanswertype, subQuestionList,
								qBean.getSk_question_id());
						

					} else {
						qDao.insertSubquestion(qBean, qBean.getSk_question_id(), subQuestionList);

						// qDao.insertSubquestion(qBean, qBean.getSk_question_id(),subquestionsList);
						String subanswertype = subQuestionList.getSubQuestionAnswerType();
						if (!(subQuestionList.getAnswer()==null)) {
						for (QuestionnaireBean subQuestionList1 : subQuestionList.getSubquestionanswers()) {
							if (!(subQuestionList1.getAnswer()==null)) {
							qDao.addDependentQuestionwithSubquestionSelectList(qBean, subanswertype, subQuestionList1,
									qBean.getSuper_question_id(), qBean.getSk_subquestion_id());
							}else {
								
							}
							
						}
						}else {
							
						}
					}
				}else {
					log.info("no index");
				}
					
				}
			}
			
			
			if(qBean.getFormulFlag().contentEquals("yes")) {
				model = new ModelAndView("redirect:/questionformula/"+qBean.getSk_question_id());
				}else {
				model = new ModelAndView("redirect:/questions");
				}
		} else {

			// for main with set of sub questions

			// for main question insert
			qDao.insertMainsetofsubquestionsquestion(qBean);

			if (null != subquestionsList && subquestionsList.size() > 0) {

				for (QuestionnaireBean subQuestionList : subquestionsList) {
					if (!(subQuestionList.getSubQuestionAnswerType()==null)) {
					if (!subQuestionList.getSubQuestionAnswerType().equals("Select/List") ) {
						String subanswertype = subQuestionList.getSubQuestionAnswerType();
						qDao.addDependentQuestionwithSubquestion(qBean, subanswertype, subQuestionList,
								qBean.getSk_question_id());

					} else {
						qDao.insertSubquestion(qBean, qBean.getSk_question_id(), subQuestionList);

						String subanswertype = subQuestionList.getSubQuestionAnswerType();
						for (QuestionnaireBean subQuestionList1 : subQuestionList.getSubquestionanswers()) {
							if (!(subQuestionList1.getAnswer()==null)) {
							qDao.addDependentQuestionwithSubquestionSelectList(qBean, subanswertype, subQuestionList1,
									qBean.getSuper_question_id(), qBean.getSk_subquestion_id());
							}else {
							}
						}
					}
					}else {
						log.info("no index");
					}
				}
			}
			log.info("form="+qBean.getFormulFlag());
			if(qBean.getFormulFlag().contentEquals("yes")) {
		model = new ModelAndView("redirect:/questionformula/"+qBean.getSk_question_id());
		}else {
		model = new ModelAndView("redirect:/questions");
		}

		}
		return model;

	}

	@RequestMapping(value = "/getSuperQuestionAnswerBystdnum")
	  public @ResponseBody Object SuperQuestionAnswerList(HttpServletRequest request, HttpServletResponse response,
	      QuestionnaireBean qBean) {
		log.info("/getSuperQuestionAnswerBystdnum api");                  	        
		String stdnum = request.getParameter("stdnum");
	    String section_id = request.getParameter("section_id");
	    String brand_id = request.getParameter("brand_id");
	    String modeOfContact = request.getParameter("modeOfContact");
	    String subsection_id = request.getParameter("subsection_id");
	    List<QuestionnaireBean> SuperQuestionAnswerList = qDao.getSuperQuestionAnswerBystdnum(qBean, stdnum, brand_id,
	        section_id, subsection_id, modeOfContact);
	    return SuperQuestionAnswerList;
	  }

	@RequestMapping("/questions")
	public ModelAndView question(HttpServletRequest request, HttpServletResponse response, QuestionnaireBean qBean)
			throws IOException {
		log.info("/questions api");                  	        
		ModelAndView model = null;
		model = new ModelAndView("viewQuestions");
		
		request.setAttribute("brand_id", qBean.getSk_brand_id());
		request.setAttribute("mode_of_contact", qBean.getMode_of_contact());
	
		List<DatabaseManagementBean> activebrandList = dbDao.getBrandList(qBean);
		model.addObject("brandList", activebrandList);
		List<QuestionnaireBean> questionnaireList = qDao.getQuestionnaireList(qBean);
		model.addObject("questionnaireList", questionnaireList);
	    return model;
	}


	@RequestMapping(value = "/editquestion/{qid}")
	public ModelAndView questionPost(@PathVariable String qid, HttpServletRequest request, HttpServletResponse response,
			DatabaseManagementBean dbBean, QuestionnaireBean qBean) throws IOException {
		ModelAndView mv = null;
		log.info("/editquestion/{qid} api");                  	        
		mv = new ModelAndView("editQuestions");
		qBean.setSk_question_id(qid);
		try {
			qDao.getQuestionById(qBean);
		} catch (Exception e) {
			e.printStackTrace();
			log.info("Exception qDao.getQuestionById"+e);
			// TODO: handle exception
		}
		request.setAttribute("question", qBean.getQuestion());
		request.setAttribute("question_code", qBean.getQuestion_code());
		request.setAttribute("question_points", qBean.getQuestion_points());
		request.setAttribute("question_response", qBean.getQuestion_response());
		request.setAttribute("question_type", qBean.getQuestion_type());
		request.setAttribute("stdno", qBean.getStandard_number());
		request.setAttribute("brand_id", qBean.getSk_brand_id());
		request.setAttribute("brand_name", qBean.getBrand_name());
		request.setAttribute("section_id", qBean.getSk_section_id());
		request.setAttribute("section_name", qBean.getSection_name());
		request.setAttribute("subsection_id", qBean.getSk_subsection_id());
		request.setAttribute("subsection_name", qBean.getSubsection_name());
		request.setAttribute("modeofcontact", qBean.getMode_of_contact());
		request.setAttribute("question_optiontype", qBean.getQuestion_optiontype());
		request.setAttribute("answer_type", qBean.getAnswer_type());
		request.setAttribute("comment_mandatory", qBean.getComment_mandatory());
		request.setAttribute("uploadfile", qBean.getUpload_file());
		request.setAttribute("question_id", qBean.getSk_question_id());
		request.setAttribute("datecode", qBean.getDate_code());
		request.setAttribute("datepoints", qBean.getDate_points());
		request.setAttribute("dateresponse", qBean.getDate_response());
		request.setAttribute("daterouting", qBean.getDate_routing());
		request.setAttribute("timecode", qBean.getTime_code());
		request.setAttribute("timepints", qBean.getTime_points());
		request.setAttribute("timeresponse", qBean.getTime_response());
		request.setAttribute("timerouting", qBean.getTime_routing());
		request.setAttribute("formula_flag", qBean.getFormulFlag());
		request.setAttribute("year", qBean.getYear());


		request.setAttribute("super_question_answer", qBean.getSuper_question_answer());
		request.setAttribute("super_question_id", qBean.getSuper_question_id());
		
		List<QuestionnaireBean> subsectionList = qDao.getsubsectionsbyid(dbBean, qBean.getSk_section_id());
		mv.addObject("subsectionList", subsectionList);

		List<QuestionnaireBean> superquestionsubsectionList = qDao.getsuperquestionsubsectionsbyid(dbBean,
				qBean.getSuper_question_section_id());
		mv.addObject("superquestionsubsectionList", superquestionsubsectionList);

		List<QuestionnaireBean> questionselectList = dbDao.getquestionselectList(qBean, qBean.getSk_question_id(),
				qBean.getQuestion_type());
		mv.addObject("questionselectList", questionselectList);

		List<QuestionnaireBean> subquestionsList = qDao.getsubquestionsList(dbBean, qBean.getSk_question_id());
		mv.addObject("subquestionsList", subquestionsList);
		
		//to get multiple super questions
		


			try {
			String sqid=qBean.getSuper_question_id().replaceAll("##", ",");
			String soid=qBean.getSuper_question_answer().replaceAll("##", ",");
			
			
				List<QuestionnaireBean>	superquestionsList=qDao.superquestionsList(qBean,
						sqid,qid,soid);
				mv.addObject("superquestionsList", superquestionsList);


			
			for (QuestionnaireBean questionnaireBean : subquestionsList) {
				List<QuestionnaireBean> subquestionoptionsList = dbDao.getsubquestionoptionsList(qBean,
						questionnaireBean.getSuper_question_id(), questionnaireBean.getSk_subquestion_id(),
						qBean.getQuestion_type());
				mv.addObject("subquestionoptionsList", subquestionoptionsList);

			}
			
	
			}catch (Exception e) {
				e.printStackTrace();
				log.info("Exception /editquestion/{qid} api"+e);
			}
		

		List<QuestionnaireBean> dependentsubquestionsDateList = qDao.dependentsubquestionsDateList(dbBean,
				qBean.getSk_question_id());
		mv.addObject("dependentsubquestionsDateList", dependentsubquestionsDateList);

		List<QuestionnaireBean> dependentsubquestionsDateTimeList = qDao.dependentsubquestionsDateTimeList(dbBean,
				qBean.getSk_question_id());
		mv.addObject("dependentsubquestionsDateTimeList", dependentsubquestionsDateTimeList);

		List<DatabaseManagementBean> activebrandList = dbDao.getBrandList(dbBean);
		mv.addObject("activebrandList", activebrandList);
		List<DatabaseManagementBean> sectionList = dbDao.getSectionList(dbBean);
		mv.addObject("sectionList", sectionList);
		return mv;
	}

	@RequestMapping(value = "/updatequestionpost/{qid}", method = RequestMethod.POST)
	public ModelAndView editstatePost(@PathVariable String qid, HttpServletRequest request,
			HttpServletResponse response, QuestionnaireBean qBean) throws IOException {
		log.info("/updatequestionpost/{qid} api");                  	        
		ModelAndView model = null;
		log.info("==================="+qBean.getYear_applied());

		List<QuestionnaireBean> subquestionsList = qBean.getSubquestions();
		// for main with set of sub questions
        List<QuestionnaireBean> superQuestions=qBean.getSuperquestions();
		List<QuestionnaireBean> questionSelectList = qBean.getAnswerdata();
		if (qBean.getQuestion_type().equals("Main Question")) {
            model = new ModelAndView("redirect:/questions");
			qDao.updateQuestion(qBean, qid, questionSelectList,superQuestions);
		}
		
		else if (qBean.getQuestion_type().equals("Dependent Question")) {
            model = new ModelAndView("redirect:/questions");
			qDao.updateQuestion1(qBean, qid, questionSelectList,superQuestions);
		}
		
		
		else if (qBean.getQuestion_type().equals("Dependent Question With Set Of SubQuestions")) {
			
		
			// for main question insert
			try {
			qDao.uploadDependentWithsetofsubquestions(qBean,qid,superQuestions);
			}catch(Exception e){
				e.printStackTrace();
				log.info("Exception of /updatequestionpost/{qid} api"+e);                  	        
			}
			if (null != subquestionsList && subquestionsList.size() > 0) {

				for (QuestionnaireBean subQuestionList : subquestionsList) {
				log.info("hema=="+subQuestionList.getSubQuestionAnswerType());
				if (!(subQuestionList.getSubQuestionAnswerType()==null)) {
					if (!subQuestionList.getSubQuestionAnswerType().equals("Select/List")) {
						String subanswertype = subQuestionList.getSubQuestionAnswerType();
						String subid=subQuestionList.getSk_subquestion_id();
						if(subid.contentEquals("nosubid")) {
						qDao.addDependentQuestionwithSubquestion(qBean, subanswertype, subQuestionList,qid);
						}else {
						qDao.updatemainQuestionwithSubquestion(qBean, subanswertype, subQuestionList,qid);
						}
						

					} else {
					String subid=subQuestionList.getSk_subquestion_id();
					if(subid.contentEquals("nosubid")) {
						qDao.insertSubquestion(qBean, qid, subQuestionList);
					
						String subanswertype = subQuestionList.getSubQuestionAnswerType();
						for (QuestionnaireBean subQuestionList1 : subQuestionList.getSubquestionanswers()) {  
							if (!(subQuestionList1.getAnswer()==null)) {
							qDao.addDependentQuestionwithSubquestionSelectList(qBean, subanswertype, subQuestionList1,
									qBean.getSuper_question_id(), qBean.getSk_subquestion_id());
							}else {
								
							}
						}
						}else {
					qDao.updateSubquestion(qBean, qid, subQuestionList);
					String subanswertype = subQuestionList.getSubQuestionAnswerType();
						for (QuestionnaireBean subQuestionList1 : subQuestionList.getSubquestionanswers()) {
							if (!(subQuestionList1.getAnswer()==null)) {
							if(subQuestionList1.getSk_answer_id().contentEquals("noanswerid")){
								qDao.addDependentQuestionwithSubquestionSelectList(qBean, subanswertype, subQuestionList1,
									qid, subid);
							}else {
							qDao.updatemainQuestionwithSubquestionSelectList(qBean, subanswertype, subQuestionList1,
									qid, subid);
							}
							}else {
								
							}
						}
					}
					}
				}else {
					
				}
				}
			}
			log.info("form dependent="+qBean.getFormulFlag());
			if(qBean.getFormulFlag().contentEquals("yes")) {
		model = new ModelAndView("redirect:/editquestionformula/"+qid);
		}else {
		model = new ModelAndView("redirect:/questions");
		}
		}
		
		
		
		
		
		else {
			// for main with set of sub questions

			// for main question insert
			qDao.uploadMainWithsetofsubquestions(qBean,qid);

			if (null != subquestionsList && subquestionsList.size() > 0) {

				for (QuestionnaireBean subQuestionList : subquestionsList) {
				log.info("hema=="+subQuestionList.getSubQuestionAnswerType());
				if (!(subQuestionList.getSubQuestionAnswerType()==null)) {
					if (!subQuestionList.getSubQuestionAnswerType().equals("Select/List")) {
						String subanswertype = subQuestionList.getSubQuestionAnswerType();
						String subid=subQuestionList.getSk_subquestion_id();
						
						if(subid.contentEquals("nosubid")) {
						qDao.addDependentQuestionwithSubquestion(qBean, subanswertype, subQuestionList,qid);
						}else {
						qDao.updatemainQuestionwithSubquestion(qBean, subanswertype, subQuestionList,qid);
						}
						

					} else {
					String subid=subQuestionList.getSk_subquestion_id();
					log.info("subid"+subid);
					if(subid.contentEquals("nosubid")) {
						qDao.insertSubquestion(qBean, qid, subQuestionList);
					
						String subanswertype = subQuestionList.getSubQuestionAnswerType();
						for (QuestionnaireBean subQuestionList1 : subQuestionList.getSubquestionanswers()) {  
							if (!(subQuestionList1.getAnswer()==null)) {
							qDao.addDependentQuestionwithSubquestionSelectList(qBean, subanswertype, subQuestionList1,
									qBean.getSuper_question_id(), qBean.getSk_subquestion_id());
							}else {
								
							}
						}
						}else {
					qDao.updateSubquestion(qBean, qid, subQuestionList);
					String subanswertype = subQuestionList.getSubQuestionAnswerType();
						for (QuestionnaireBean subQuestionList1 : subQuestionList.getSubquestionanswers()) {
							if (!(subQuestionList1.getAnswer()==null)) {
							if(subQuestionList1.getSk_answer_id().contentEquals("noanswerid")){
								qDao.addDependentQuestionwithSubquestionSelectList(qBean, subanswertype, subQuestionList1,
									qid, subid);
							}else {
							qDao.updatemainQuestionwithSubquestionSelectList(qBean, subanswertype, subQuestionList1,
									qid, subid);
							}
							}else {
								
							}
						}
					}
					}
				}else {
					
				}
				}
			}
			log.info("form="+qBean.getFormulFlag());
			if(qBean.getFormulFlag().contentEquals("yes")) {
		model = new ModelAndView("redirect:/editquestionformula/"+qid);
		}else {
		model = new ModelAndView("redirect:/questions");
		}

		}
		return model;
	}

	@RequestMapping(value = "/getStandardNumberExistance")
	public @ResponseBody Object getStandardNumberExistance(HttpServletRequest request, HttpServletResponse response,
			QuestionnaireBean qBean) {
		log.info("/getStandardNumberExistance api");                  	        
		String section_id = request.getParameter("section_id");
		String brand_id = request.getParameter("brand_id");
		String modeOfContact = request.getParameter("modeOfContact");
		String subsection_id = request.getParameter("subsection_id");
		String standardNumber = request.getParameter("standardNumber");
		String year_applied = request.getParameter("year_applied");
		log.info("year applied"+year_applied);
		QuestionnaireBean StandardNumberexistingstatus = qDao.getStandardNumberExistance(qBean, section_id, brand_id,
				modeOfContact, subsection_id, standardNumber,year_applied);
		return StandardNumberexistingstatus;
	}

	@RequestMapping(value = "/getStandardNumberByIds")
	public @ResponseBody Object getStandardNumberByIds(HttpServletRequest request, HttpServletResponse response,
			QuestionnaireBean qBean) {
		log.info("/getStandardNumberByIds api");                  	        
		String section_id = request.getParameter("section_id");
		String brand_id = request.getParameter("brand_id");
		String modeOfContact = request.getParameter("modeOfContact");
		String subsection_id = request.getParameter("subsection_id");
		List<QuestionnaireBean> StandardNumberList = qDao.getStandardNumberByIds(qBean, section_id, brand_id,
				modeOfContact, subsection_id);
		return StandardNumberList;
	}

	@RequestMapping("deleteanwerOptions/{aid}/{qid}")
	public ModelAndView deleteanwerOptions(@PathVariable String aid, @PathVariable String qid,
			HttpServletRequest request, HttpServletResponse response, QuestionnaireBean qBean) throws IOException {
		log.info("/deleteanwerOptions/{aid}/{qid} api");                  	        
		ModelAndView model = null;
		model = new ModelAndView("redirect:/editquestion/" + qid);
		qDao.deleteanwerOptions(qBean, aid);
		return model;
	}

	@RequestMapping("deletequestion/{qid}")
	public ModelAndView deletequestion(@PathVariable String qid, HttpServletRequest request,
			HttpServletResponse response, QuestionnaireBean qBean) throws IOException {
		log.info("/deletequestion/{qid} api");                  	        
		ModelAndView model = null;
		model = new ModelAndView("redirect:/questions");
		qDao.deletequestion(qBean, qid);
		return model;
	}

	@RequestMapping(value = "/getSubsectionsById")
	public @ResponseBody Object getsectionExistance(HttpServletRequest request, HttpServletResponse response,
			DatabaseManagementBean dbBean) {
		log.info("/getSubsectionsById api");                  	        
		String section_id = request.getParameter("section_id");
		List<DatabaseManagementBean> subSection_nameList = dbDao.getSubSections(dbBean, section_id);
		return subSection_nameList;
	}

	
	
	
	
	@RequestMapping("updateQuestionOrder")
	public ModelAndView updateQuestionOrder(QuestionnaireBean qBean,DatabaseManagementBean dbBean,HttpServletRequest request, HttpServletResponse response) {
		log.info("/updateQuestionOrder api");                  	        
		ModelAndView mv = new ModelAndView("questionnairesequence");
		request.setAttribute("brand_id", qBean.getSk_brand_id());
		request.setAttribute("mode_of_contact", qBean.getMode_of_contact());
	
		  List<QuestionnaireBean> questionnaireList = qDao.getQuestionnaireList(qBean);
		  mv.addObject("questionnaireList", questionnaireList);
		  List<DatabaseManagementBean> activebrandList = dbDao.getBrandList(dbBean);
		  mv.addObject("activebrandList", activebrandList);
		  return mv;

	}
	
	
	@RequestMapping("/updateStandardSequenceNumber")
	public @ResponseBody Object updateStandardSequenceNumber(HttpServletRequest request, HttpServletResponse response,
			QuestionnaireBean qBean) {
		log.info("/updateStandardSequenceNumber api");                  	        
		String standard_number_sequence = request.getParameter("standardNumberSequence");
		String questionId = request.getParameter("questionId");
		qBean.setStandard_number_sequence(standard_number_sequence);
		qBean.setSk_question_id(questionId);
		QuestionnaireBean standard_sequence_number = qDao.updateStandardSequenceNumber(qBean);
		return standard_sequence_number;
	
	}
	
	
	
	
	
	
	@RequestMapping(value = "/editquestion/{qid}/{qtype}")
	public ModelAndView questionPost1(@PathVariable String qid,@PathVariable String qtype, HttpServletRequest request, HttpServletResponse response,
			DatabaseManagementBean dbBean, QuestionnaireBean qBean) throws IOException {
		ModelAndView mv = null;
		log.info("/editquestion/{qid}/{qtype} api");                  	        
		mv = new ModelAndView("editmainsubQuestions");
		qBean.setSk_question_id(qid);
		log.info("----------------");

		try {
			qDao.getQuestionById(qBean);
		} catch (Exception e) {
			e.printStackTrace();
			log.info("Exception qDao.getQuestionById"+e);
		}
		request.setAttribute("question", qBean.getQuestion());
		request.setAttribute("question_code", qBean.getQuestion_code());
		request.setAttribute("question_points", qBean.getQuestion_points());
		request.setAttribute("question_response", qBean.getQuestion_response());
		request.setAttribute("question_type", qBean.getQuestion_type());
		request.setAttribute("stdno", qBean.getStandard_number());
		request.setAttribute("brand_id", qBean.getSk_brand_id());
		request.setAttribute("brand_name", qBean.getBrand_name());
		request.setAttribute("section_id", qBean.getSk_section_id());
		request.setAttribute("section_name", qBean.getSection_name());
		request.setAttribute("subsection_id", qBean.getSk_subsection_id());
		request.setAttribute("subsection_name", qBean.getSubsection_name());
		request.setAttribute("modeofcontact", qBean.getMode_of_contact());
		request.setAttribute("question_optiontype", qBean.getQuestion_optiontype());
		request.setAttribute("answer_type", qBean.getAnswer_type());
		request.setAttribute("comment_mandatory", qBean.getComment_mandatory());
		request.setAttribute("uploadfile", qBean.getUpload_file());
		request.setAttribute("question_id", qBean.getSk_question_id());
		request.setAttribute("datecode", qBean.getDate_code());
		request.setAttribute("datepoints", qBean.getDate_points());
		request.setAttribute("dateresponse", qBean.getDate_response());
		request.setAttribute("daterouting", qBean.getDate_routing());
		request.setAttribute("timecode", qBean.getTime_code());
		request.setAttribute("timepints", qBean.getTime_points());
		request.setAttribute("timeresponse", qBean.getTime_response());
		request.setAttribute("timerouting", qBean.getTime_routing());
		request.setAttribute("qid", qid);

		request.setAttribute("super_question_answer", qBean.getSuper_question_answer());
		request.setAttribute("super_question_id", qBean.getSuper_question_id());
		request.setAttribute("super_section_id", qBean.getSuper_question_section_id());
		request.setAttribute("super_section_name", qBean.getSuper_question_section_name());
		request.setAttribute("super_subsection_id", qBean.getSuper_question_subsection_id());
		request.setAttribute("super_subsection_name", qBean.getSuper_question_subsection_name());
		request.setAttribute("super_standard_number", qBean.getSuper_question_standard_number());
		request.setAttribute("year", qBean.getYear());

		List<QuestionnaireBean> subquestionsList = qDao.getsubquestionsList(dbBean, qBean.getSk_question_id());
		mv.addObject("subquestionsList", subquestionsList);
		
		List<List<QuestionnaireBean>> optionsList = new ArrayList<List<QuestionnaireBean>>();
			Iterator<QuestionnaireBean> iterator = subquestionsList.iterator();
			while (iterator.hasNext()) {
				String subid = iterator.next().getSk_subquestion_id();
				optionsList.add(dbDao.getsubquestionoptionsList(qBean, qid,subid,qtype));
			}


			mv.addObject("optionsList", optionsList);

		return mv;
	}
	
	
	@RequestMapping(value = "/editdependentquestion/{qid}/{qtype}")
	public ModelAndView editdependentquestion(@PathVariable String qid,@PathVariable String qtype, HttpServletRequest request, HttpServletResponse response,
			DatabaseManagementBean dbBean, QuestionnaireBean qBean) throws IOException {
		ModelAndView mv = null;
		log.info("/editdependentquestion/{qid}/{qtype} api");                  	        
		mv = new ModelAndView("editdependentsubQuestions");
		qBean.setSk_question_id(qid);
		try {
			qDao.getQuestionById(qBean);
		} catch (Exception e) {
			e.printStackTrace();
			log.info("Exception qDao.getQuestionById"+e);
		}
		request.setAttribute("question", qBean.getQuestion());
		request.setAttribute("question_code", qBean.getQuestion_code());
		request.setAttribute("question_points", qBean.getQuestion_points());
		request.setAttribute("question_response", qBean.getQuestion_response());
		request.setAttribute("question_type", qBean.getQuestion_type());
		request.setAttribute("stdno", qBean.getStandard_number());
		request.setAttribute("brand_id", qBean.getSk_brand_id());
		request.setAttribute("brand_name", qBean.getBrand_name());
		request.setAttribute("section_id", qBean.getSk_section_id());
		request.setAttribute("section_name", qBean.getSection_name());
		request.setAttribute("subsection_id", qBean.getSk_subsection_id());
		request.setAttribute("subsection_name", qBean.getSubsection_name());
		request.setAttribute("modeofcontact", qBean.getMode_of_contact());
		request.setAttribute("question_optiontype", qBean.getQuestion_optiontype());
		request.setAttribute("answer_type", qBean.getAnswer_type());
		request.setAttribute("comment_mandatory", qBean.getComment_mandatory());
		request.setAttribute("uploadfile", qBean.getUpload_file());
		request.setAttribute("question_id", qBean.getSk_question_id());
		request.setAttribute("datecode", qBean.getDate_code());
		request.setAttribute("datepoints", qBean.getDate_points());
		request.setAttribute("dateresponse", qBean.getDate_response());
		request.setAttribute("daterouting", qBean.getDate_routing());
		request.setAttribute("timecode", qBean.getTime_code());
		request.setAttribute("timepints", qBean.getTime_points());
		request.setAttribute("timeresponse", qBean.getTime_response());
		request.setAttribute("timerouting", qBean.getTime_routing());
		request.setAttribute("qid", qid);

		request.setAttribute("super_question_answer", qBean.getSuper_question_answer());
		request.setAttribute("super_question_id", qBean.getSuper_question_id());
		request.setAttribute("super_section_id", qBean.getSuper_question_section_id());
		request.setAttribute("super_section_name", qBean.getSuper_question_section_name());
		request.setAttribute("super_subsection_id", qBean.getSuper_question_subsection_id());
		request.setAttribute("super_subsection_name", qBean.getSuper_question_subsection_name());
		request.setAttribute("super_standard_number", qBean.getSuper_question_standard_number());
		request.setAttribute("year", qBean.getYear());

		List<QuestionnaireBean> subquestionsList = qDao.getsubquestionsList(dbBean, qBean.getSk_question_id());
		mv.addObject("subquestionsList", subquestionsList);
		
		List<List<QuestionnaireBean>> optionsList = new ArrayList<List<QuestionnaireBean>>();
			Iterator<QuestionnaireBean> iterator = subquestionsList.iterator();
			while (iterator.hasNext()) {
				String subid = iterator.next().getSk_subquestion_id();
				optionsList.add(dbDao.getsubquestionoptionsList(qBean, qid,subid,qtype));
			}


			mv.addObject("optionsList", optionsList);
			
			
			
			
			

			try {
		
			String sqid=qBean.getSuper_question_id().replaceAll("##", ",");
			String soid=qBean.getSuper_question_answer().replaceAll("##", ",");
			
			
				List<QuestionnaireBean>	superquestionsList=qDao.superquestionsList(qBean,
						sqid,qid,soid);
				mv.addObject("superquestionsList", superquestionsList);
			}catch (Exception e) {
				e.printStackTrace();
				log.info("Exception /editdependentquestion/{qid}/{qtype} api"+e);  
			}
			
			for (QuestionnaireBean questionnaireBean : subquestionsList) {
				List<QuestionnaireBean> subquestionoptionsList = dbDao.getsubquestionoptionsList(qBean,
						questionnaireBean.getSuper_question_id(), questionnaireBean.getSk_subquestion_id(),
						qBean.getQuestion_type());
				mv.addObject("subquestionoptionsList", subquestionoptionsList);

			}
			
		
			
			

			List<QuestionnaireBean> dependentsubquestionsDateList = qDao.dependentsubquestionsDateList(dbBean,
					qBean.getSk_question_id());
			mv.addObject("dependentsubquestionsDateList", dependentsubquestionsDateList);

			List<QuestionnaireBean> dependentsubquestionsDateTimeList = qDao.dependentsubquestionsDateTimeList(dbBean,
					qBean.getSk_question_id());
			mv.addObject("dependentsubquestionsDateTimeList", dependentsubquestionsDateTimeList);

			List<DatabaseManagementBean> activebrandList = dbDao.getBrandList(dbBean);
			mv.addObject("activebrandList", activebrandList);
			List<DatabaseManagementBean> sectionList = dbDao.getSectionList(dbBean);
			mv.addObject("sectionList", sectionList);
			

		return mv;
	}
	
}
