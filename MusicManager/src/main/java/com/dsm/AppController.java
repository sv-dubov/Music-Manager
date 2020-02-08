package com.dsm;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class AppController {

	@Autowired
	private TrackService service; 
	
	@RequestMapping("/")
	public String viewHomePage(Model model) {
		List<Track> trackList = service.listAll();
		model.addAttribute("trackList", trackList);
		return "index";
	}
	
	@RequestMapping("/new")
	public String showNewTrackPage(Model model) {
		Track track = new Track();
		model.addAttribute("track", track);
		return "new_track";
	}
	
	@RequestMapping(value = "/save", method = RequestMethod.POST)
	public String saveTrack(@ModelAttribute("track") Track track) {
		service.save(track);
		
		return "redirect:/";
	}
	
	@RequestMapping("/edit/{id}")
	public ModelAndView showEditTrackPage(@PathVariable(name = "id") int id) {
		ModelAndView mav = new ModelAndView("edit_track");
		Track track = service.get(id);
		mav.addObject("track", track);
		return mav;
	}
	
	@RequestMapping("/delete/{id}")
	public String deleteTrack(@PathVariable(name = "id") int id) {
		service.delete(id);
		return "redirect:/";		
	}
}
