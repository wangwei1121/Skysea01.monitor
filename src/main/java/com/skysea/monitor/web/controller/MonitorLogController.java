package com.skysea.monitor.web.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

@RestController
public class MonitorLogController {

	@RequestMapping(value = "/monitorLog/list")
	public ModelAndView list() {
		ModelAndView modelView = new ModelAndView("monitorLog/list");
		return modelView;
	}

	@RequestMapping(value = "/monitorLog/add")
	public ModelAndView add() {
		ModelAndView modelView = new ModelAndView("monitorLog/add");
		return modelView;
	}
	
	@RequestMapping(value = "/monitorLog/save")
	public ModelAndView save() {
		ModelAndView modelView = new ModelAndView("monitorLog/save");
		return modelView;
	}
	
	@RequestMapping(value = "/monitorLog/update")
	public ModelAndView update() {
		ModelAndView modelView = new ModelAndView("monitorLog/update");
		return modelView;
	}
	
	@RequestMapping(value = "/monitorLog/delete")
	public ModelAndView delete() {
		ModelAndView modelView = new ModelAndView("monitorLog/delete");
		return modelView;
	}
}