package com.skysea.monitor.web.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

@RestController
public class UserInfoController {

	@RequestMapping(value = "/userInfo/list")
	public ModelAndView list() {
		ModelAndView modelView = new ModelAndView("userInfo/list");
		return modelView;
	}

	@RequestMapping(value = "/userInfo/add")
	public ModelAndView add() {
		ModelAndView modelView = new ModelAndView("userInfo/add");
		return modelView;
	}
	
	@RequestMapping(value = "/userInfo/save")
	public ModelAndView save() {
		ModelAndView modelView = new ModelAndView("userInfo/save");
		return modelView;
	}
	
	@RequestMapping(value = "/userInfo/update")
	public ModelAndView update() {
		ModelAndView modelView = new ModelAndView("userInfo/update");
		return modelView;
	}
	
	@RequestMapping(value = "/userInfo/delete")
	public ModelAndView delete() {
		ModelAndView modelView = new ModelAndView("userInfo/delete");
		return modelView;
	}
}