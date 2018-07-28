package com.interview.core.web;

import org.glassfish.hk2.utilities.binding.AbstractBinder;

import com.interview.core.service.IPersonService;
import com.interview.core.service.PersonService;

public class ApplicationBinder extends AbstractBinder{

	@Override
	protected void configure() {
		  bind(PersonService.class).to(IPersonService.class);		
	}

}
