package com.skysea.monitor.web.controller;

import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.skysea.monitor.domain.MonitorInstance;
import com.skysea.monitor.service.MonitorInstanceService;
import com.skysea.monitor.web.entity.UIResult;

@RestController
public class MonitorInstanceController extends BaseController{

	@Autowired
	private MonitorInstanceService monitorInstanceService;
	
	@RequestMapping(value = "/monitorInstance/list")
	public UIResult list(HttpServletRequest request, HttpServletResponse response) {
		UIResult result = new UIResult();
		try{
			List<MonitorInstance> list = this.monitorInstanceService.getList(null);
			if(null != list && list.size() > 0){
				result.setResult(list);
				result.setTotalCount(list.size());
			}
			String callback = request.getParameter("callback");
			if(StringUtils.isNotBlank(callback)){
				ObjectMapper mapper = new ObjectMapper();
				super.renderJson(response, callback+"(" + mapper.writeValueAsString(result) +");" );
				return null;
			}else{
				return result;
			}
		}catch(Exception e){
			result.setMessage(e.getMessage());
			result.setFlag(false);
			e.printStackTrace();
			LOG.error(e.getMessage());
		}
		return result;
	}

	@RequestMapping(value = "/monitorInstance/add")
	public ModelAndView add() {
		ModelAndView modelView = new ModelAndView("monitorInstance/add");
		return modelView;
	}
	
	@RequestMapping(value = "/monitorInstance/save")
	public UIResult save(MonitorInstance entity,HttpServletRequest request, HttpServletResponse response) {
		UIResult result = new UIResult();
		try {
			if(null == entity){
				result.setMessage("entity is null");
				return result;
			}else{
				Date now = new Date();
				if(null != entity.getId()){
					entity.setUpdateTime(now);
					this.monitorInstanceService.update(entity);
					result.setResult("update success");	
				}else{
					MonitorInstance instance = new MonitorInstance();
					instance.setName(entity.getName());
					Integer count = this.monitorInstanceService.getCount(instance);
					if(count > 0){
						result.setMessage(entity.getName() + " have exists");
					}else{
						entity.setCreateTime(now);
						this.monitorInstanceService.insert(entity);
						result.setResult("insert success");
					}
				}
			}
			String callback = request.getParameter("callback");
			if(StringUtils.isNotBlank(callback)){
				ObjectMapper mapper = new ObjectMapper();
				super.renderJson(response, callback+"(" + mapper.writeValueAsString(result) +");" );
				return null;
			}else{
				return result;
			}
		} catch (Exception e) {
			result.setMessage(e.getMessage());
			result.setFlag(false);
			e.printStackTrace();
			LOG.error(e.getMessage());
		}
		return result;
	}
	
	@RequestMapping(value = "/monitorInstance/update")
	public ModelAndView update() {
		ModelAndView modelView = new ModelAndView("monitorInstance/update");
		return modelView;
	}
	
	@RequestMapping(value = "/monitorInstance/get/{id}")
	public UIResult get(@PathVariable Integer id,HttpServletRequest request, HttpServletResponse response) {
		UIResult result = new UIResult();
		try {
			MonitorInstance entity = new MonitorInstance();
			entity.setId(id);
			entity = this.monitorInstanceService.get(entity);
			if(null == entity){
				result.setMessage("entity is null by id " + id);
			}else{
				result.setResult(entity);
			}
			String callback = request.getParameter("callback");
			if(StringUtils.isNotBlank(callback)){
				ObjectMapper mapper = new ObjectMapper();
				super.renderJson(response, callback+"(" + mapper.writeValueAsString(result) +");" );
				return null;
			}else{
				return result;
			}
		} catch (Exception e) {
			result.setMessage(e.getMessage());
			result.setFlag(false);
			e.printStackTrace();
			LOG.error(e.getMessage());
		}
		return result;
	}
	
	@RequestMapping(value = "/monitorInstance/delete/{id}")
	public UIResult delete(@PathVariable Integer id,HttpServletRequest request, HttpServletResponse response) {
		UIResult result = new UIResult();
		try {
			MonitorInstance entity = new MonitorInstance();
			entity.setId(id);
			this.monitorInstanceService.delete(entity);
			result.setResult("delete success");
			
			String callback = request.getParameter("callback");
			if(StringUtils.isNotBlank(callback)){
				ObjectMapper mapper = new ObjectMapper();
				super.renderJson(response, callback+"(" + mapper.writeValueAsString(result) +");" );
				return null;
			}else{
				return result;
			}
			
		} catch (Exception e) {
			result.setMessage(e.getMessage());
			result.setFlag(false);
			e.printStackTrace();
			LOG.error(e.getMessage());
		}
		return result;
	}
}