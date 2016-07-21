package com.skysea.monitor.web.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

@RestController
public class HeartbeatLogController {

	@RequestMapping(value = "/heartbeatLog/list")
	public ModelAndView list() {
		ModelAndView modelView = new ModelAndView("heartbeatLog/list");
		return modelView;
	}

	@RequestMapping(value = "/heartbeatLog/add")
	public ModelAndView add() {
		ModelAndView modelView = new ModelAndView("heartbeatLog/add");
		return modelView;
	}
	
	@RequestMapping(value = "/heartbeatLog/save")
	public ModelAndView save() {
		ModelAndView modelView = new ModelAndView("heartbeatLog/save");
		return modelView;
	}
	
	@RequestMapping(value = "/heartbeatLog/update")
	public ModelAndView update() {
		ModelAndView modelView = new ModelAndView("heartbeatLog/update");
		return modelView;
	}
	
	@RequestMapping(value = "/heartbeatLog/delete")
	public ModelAndView delete() {
		ModelAndView modelView = new ModelAndView("heartbeatLog/delete");
		return modelView;
	}
}