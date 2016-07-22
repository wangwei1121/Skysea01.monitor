package com.skysea.monitor.web.controller;

import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.skysea.monitor.domain.AppInstance;
import com.skysea.monitor.domain.HeartbeatLog;
import com.skysea.monitor.service.AppInstanceService;
import com.skysea.monitor.service.HeartbeatLogService;
import com.skysea.monitor.web.entity.UIResult;

@RestController
public class AppInstanceController extends BaseController{

	@Autowired
	private AppInstanceService appInstanceService;
	
	@Autowired
	private HeartbeatLogService heartbeatLogService;
	
	@RequestMapping(value = "/appInstance/list")
	public UIResult list(HttpServletRequest request, HttpServletResponse response) {
		UIResult result = new UIResult();
		try{
			List<AppInstance> list = this.appInstanceService.getList(null);
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

	@RequestMapping(value = "/appInstance/add")
	public ModelAndView add() {
		ModelAndView modelView = new ModelAndView("monitorInstance/add");
		return modelView;
	}
	
	@RequestMapping(value = "/appInstance/save")
	public UIResult save(AppInstance entity,HttpServletRequest request, HttpServletResponse response) {
		UIResult result = new UIResult();
		try {
			if(null == entity){
				result.setMessage("entity is null");
				return result;
			}else{
				Date now = new Date();
				if(null != entity.getId()){
					entity.setUpdateTime(now);
					this.appInstanceService.update(entity);
					result.setResult("update success");	
				}else{
					AppInstance instance = new AppInstance();
					instance.setName(entity.getName());
					Integer count = this.appInstanceService.getCount(instance);
					if(count > 0){
						result.setMessage(entity.getName() + " have exists");
					}else{
						entity.setCreateTime(now);
						this.appInstanceService.insert(entity);
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
	
	@RequestMapping(value = "/appInstance/update")
	public ModelAndView update() {
		ModelAndView modelView = new ModelAndView("monitorInstance/update");
		return modelView;
	}
	
	@RequestMapping(value = "/appInstance/get/{id}")
	public UIResult get(@PathVariable Integer id,HttpServletRequest request, HttpServletResponse response) {
		UIResult result = new UIResult();
		try {
			AppInstance entity = new AppInstance();
			entity.setId(id);
			entity = this.appInstanceService.get(entity);
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
	
	@RequestMapping(value = "/appInstance/delete/{id}")
	public UIResult delete(@PathVariable Integer id,HttpServletRequest request, HttpServletResponse response) {
		UIResult result = new UIResult();
		try {
			AppInstance entity = new AppInstance();
			entity.setId(id);
			this.appInstanceService.delete(entity);
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
	
	@RequestMapping(value = "/appInstance/heartbeatCharts")
	public ModelAndView heartbeatCharts() {
		ModelAndView modelView = new ModelAndView("AppInstance/heartbeatCharts");
		return modelView;
	}
	
	@RequestMapping(value = "/appInstance/getHeartbeatData/{id}")
	public UIResult getHeartbeatData(@PathVariable Integer id,@RequestParam(value="pageSize",required=false) Integer pageSize,HttpServletRequest request, HttpServletResponse response) {
		UIResult result = new UIResult();
		try {
			pageSize = null == pageSize ? 10 : pageSize;
			HeartbeatLog entity = new HeartbeatLog();
			entity.setAppInstanceId(id);
			entity.getPager().setRows(pageSize);
			List<HeartbeatLog> list = this.heartbeatLogService.getList(entity);
			result.setResult(list);
		} catch (Exception e) {
			result.setMessage(e.getMessage());
			result.setFlag(false);
			e.printStackTrace();
			LOG.error(e.getMessage());
		}
		return result;
	}
	
}