package com.skysea.monitor.web.controller;

import java.io.OutputStreamWriter;
import java.net.URLEncoder;
import java.util.ArrayList;
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
import com.skysea.monitor.domain.MonitorServer;
import com.skysea.monitor.service.MonitorServerService;
import com.skysea.monitor.web.entity.UIResult;

@RestController
public class MonitorServerController extends BaseController{

	
	@Autowired
	private MonitorServerService MonitorServerService;

	@RequestMapping(value = "/monitorServer/list")
	public UIResult list(HttpServletRequest request, HttpServletResponse response) {
		UIResult result = new UIResult();
		try{
			List<MonitorServer> list = this.MonitorServerService.getList(null);
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

	@RequestMapping(value = "/monitorServer/add")
	public ModelAndView add() {
		ModelAndView modelView = new ModelAndView("MonitorServer/add");
		return modelView;
	}
	
	@RequestMapping(value = "/monitorServer/save")
	public UIResult save(MonitorServer entity,HttpServletRequest request, HttpServletResponse response) {
		UIResult result = new UIResult();
		try {
			if(null == entity || null == entity.getIp() || null == entity.getPort() || null == entity.getName()){
				result.setMessage("entity is null");
				return result;
			}else{
				Date now = new Date();
				if(null != entity.getId()){
					entity.setUpdateTime(now);
					this.MonitorServerService.update(entity);
					result.setResult("update success");	
				}else{
					MonitorServer MonitorServer = new MonitorServer();
					MonitorServer.setName(entity.getName());
					Integer count = this.MonitorServerService.getCount(MonitorServer);
					if(count > 0){
						result.setMessage(entity.getName() + " have exists");
					}else{
						entity.setCreateTime(now);
						this.MonitorServerService.insert(entity);
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
	
	@RequestMapping(value = "/monitorServer/get/{id}")
	public UIResult get(@PathVariable Integer id,HttpServletRequest request, HttpServletResponse response) {
		UIResult result = new UIResult();
		try {
			MonitorServer entity = new MonitorServer();
			entity.setId(id);
			entity = this.MonitorServerService.get(entity);
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
	
	@RequestMapping(value = "/monitorServer/delete/{id}")
	public UIResult delete(@PathVariable Integer id,HttpServletRequest request, HttpServletResponse response) {
		UIResult result = new UIResult();
		try {
			MonitorServer entity = new MonitorServer();
			entity.setId(id);
			this.MonitorServerService.delete(entity);
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
	
	@RequestMapping(value = "/monitorServer/download")
	public void download(HttpServletRequest request,HttpServletResponse response) {
		try {
			String fileName = "cvs下载";
			String userAgent = request.getHeader("USER-AGENT");
            if(StringUtils.contains(userAgent, "Mozilla")){//火狐浏览器
            	fileName = new String(fileName.getBytes(), "ISO8859-1");
            }else{
            	fileName = URLEncoder.encode(fileName,"UTF8");//其他浏览器
            }
            
            List<String[]> dataList = new ArrayList<String[]>();
            for(int i=0;i<100;i++){
            	dataList.add(new String[]{"张三" + i,"sex " + i, "地址 " + i});
            }
            StringBuilder builder = new StringBuilder();
            for(String[] data:dataList){
            	builder.append(StringUtils.join(data,",")).append("\r\n");
            }
            
			response.setHeader("Content-Disposition", "attachment;filename=" + fileName + ".csv");
			response.setContentType("application/csv;charset=UTF-8");
			response.setCharacterEncoding("UTF-8");
			response.setBufferSize(1024);
			OutputStreamWriter osw = new OutputStreamWriter(response.getOutputStream(), "UTF-8");
			osw.write(new String(new byte[] {(byte)0xEF,(byte)0xBB,(byte)0xBF}));
			osw.write(builder.toString());
			osw.flush();
		} catch (Exception e) {
			LOG.error(e.getMessage(),e);
		}
	}

}