package com.example.feedbackportal;
import jakarta.persistence.GenerationType;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;

@Entity
public class Feedback {

	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String courseId;
    private int rating;
    private String comment;
    
    public Long getId() { return id ;}
    public void setId(Long id) {this.id = id; } 
    
    public String getCourseId() { return courseId;}
    public void setCourseId( String courseId) {this.courseId = courseId; }
    
    public int getRating() { return rating; }
    public void setRating ( int rating) {this.rating = rating ; }
    
    public String getComment() {return comment ;}
    public void setComment (String comment) {this.comment = comment ; }
    
}












