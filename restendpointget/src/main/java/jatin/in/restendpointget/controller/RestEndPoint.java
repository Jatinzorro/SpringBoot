package jatin.in.restendpointget.controller;
import java.util.HashMap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
@RestController
public class RestEndPoint {
	@Value("${default.course.name}")
	private String cname;
	
	@Value("${default.course.chapterCount}")
	private int chapterCounting;
	
	//herarchical Properties
	@Autowired
	private CourseConfiguration courseConfiguration;
	
	@RequestMapping("/gethierarchical")
	public HashMap<String,Object> getConfigAnnonateProperties() {
		
		HashMap<String,Object> map=new HashMap<String,Object>();
		map.put("name",courseConfiguration.getName());
		map.put("chapterCount",courseConfiguration.getChapterCount());
		map.put("rating",courseConfiguration.getRating());
		map.put("author",courseConfiguration.getAuthor());
		
		return map;
	}
	
	
	@RequestMapping("/defaultCourse")
	public Course getDefaultCourse(@RequestParam(value="name",defaultValue = "Spring Boot",required = false)String name,
                              @RequestParam(value="chapterCount",defaultValue = "2",required = false)int chapterCount
                              
			) {
		return new Course(cname,chapterCounting);
	}
	
    @RequestMapping("/course")
    public Course getEndPoint(@RequestParam(value="name",defaultValue = "Spring Boot",required = false)String name,
                              @RequestParam(value="chapterCount",defaultValue = "2",required = false)int chapterCount
                              ){
        return new Course(name,chapterCount);
    }
    
    @RequestMapping(method=RequestMethod.POST,value="/register/course")
    public String saveCourse(@RequestBody Course course) {
    	return "Your course named " + course.getName()+" with "+course.getChapterCount()+" chapters saved Successfully.";
    	
    }
    
}
