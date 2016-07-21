package com.skysea.monitor.web.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

@RestController
public class HeartbeatLogTextController {

	@RequestMapping(value = "/heartbeatLogText/list")
	public ModelAndView list() {
		ModelAndView modelView = new ModelAndView("heartbeatLogText/list");
		return modelView;
	}

	@RequestMapping(value = "/heartbeatLogText/add")
	public ModelAndView add() {
		ModelAndView modelView = new ModelAndView("heartbeatLogText/add");
		return modelView;
	}
	
	@RequestMapping(value = "/heartbeatLogText/save")
	public ModelAndView save() {
		ModelAndView modelView = new ModelAndView("heartbeatLogText/save");
		return modelView;
	}
	
	@RequestMapping(value = "/heartbeatLogText/update")
	public ModelAndView update() {
		ModelAndView modelView = new ModelAndView("heartbeatLogText/update");
		return modelView;
	}
	
	@RequestMapping(value = "/heartbeatLogText/delete")
	public ModelAndView delete() {
		ModelAndView modelView = new ModelAndView("heartbeatLogText/delete");
		return modelView;
	}
}