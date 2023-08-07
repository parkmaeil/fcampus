package kr.smhrd.model;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@Entity
public class Team {
	
  @Id	
  @Column(name="TEAM_ID")
  private String id;
  
  private String name;
  
  @OneToMany(mappedBy = "team")
  private List<Member> members;
  
  public Team(String id, String name) {
	super();
	this.id = id;
	this.name = name;
  }
}
