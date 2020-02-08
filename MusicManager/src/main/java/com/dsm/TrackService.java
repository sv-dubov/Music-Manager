package com.dsm;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class TrackService {

	@Autowired
	private TrackRepository trackRepos;
	
	public List<Track> listAll() {
		return trackRepos.findAll();
	}
	
	public void save(Track track) {
		trackRepos.save(track);
	}
	
	public Track get(long id) {
		return trackRepos.findById(id).get();
	}
	
	public void delete(long id) {
		trackRepos.deleteById(id);
	}
}
