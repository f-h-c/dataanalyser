package org.dataanalyser.controller;

import java.util.List;

import org.dataanalyser.model.CSVData;
import org.dataanalyser.service.CSVFileConsumer;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
public class HomeController {
	
	@RequestMapping("/")
  public ModelAndView index() {
    
    return new ModelAndView("/home");
  }
	
	@RequestMapping(value="/home", method=RequestMethod.POST)
  public ModelAndView sendFile(MultipartFile cvsFile, RedirectAttributes redirectAttributes) {
		List<CSVData> csvData = null;
		String errorMessage = null;
		List<String> variables = null;
		
		try {
			CSVFileConsumer consumer = new CSVFileConsumer(cvsFile.getInputStream());
			csvData = consumer.getNotRemovedData();
			variables = consumer.getVariables();
		}
		catch (Exception e) {
			e.printStackTrace();
			errorMessage = e.getMessage();
		}

    ModelAndView modelAndView = new ModelAndView("redirect:/resultado");
    redirectAttributes.addFlashAttribute("cvsData", csvData);
    redirectAttributes.addFlashAttribute("variables", variables);
    redirectAttributes.addFlashAttribute("errorMessage", errorMessage);
		
		return modelAndView;
	}
	
	@RequestMapping(value="/resultado", method=RequestMethod.GET)
  public ModelAndView result() {
    ModelAndView modelAndView = new ModelAndView("/result");
		
		return modelAndView;
	}

}
