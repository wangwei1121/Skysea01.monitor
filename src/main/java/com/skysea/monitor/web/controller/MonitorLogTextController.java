package com.skysea.monitor.web.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

@RestController
public class MonitorLogTextController {

	@RequestMapping(value = "/monitorLogText/list")
	public ModelAndView list() {
		ModelAndView modelView = new ModelAndView("monitorLogText/list");
		return modelView;
	}

	@RequestMapping(value = "/monitorLogText/add")
	public ModelAndView add() {
		ModelAndView modelView = new ModelAndView("monitorLogText/add");
		return modelView;
	}
	
	@RequestMapping(value = "/monitorLogText/save")
	public ModelAndView save() {
		ModelAndView modelView = new ModelAndView("monitorLogText/save");
		return modelView;
	}
	
	@RequestMapping(value = "/monitorLogText/update")
	public ModelAndView update() {
		ModelAndView modelView = new ModelAndView("monitorLogText/update");
		return modelView;
	}
	
	@RequestMapping(value = "/monitorLogText/delete")
	public ModelAndView delete() {
		ModelAndView modelView = new ModelAndView("monitorLogText/delete");
		return modelView;
	}
}