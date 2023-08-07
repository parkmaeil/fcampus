package kr.smhrd.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Member {  // Member(N) : Team(1)
   
   @Id
   @Column(name="member_id")
   private String id;
   
   private String username;
   
   // Member가 Team을 가르킨다(Team의 TEAM_ID를 가르킨다)
   @ManyToOne
   @JoinColumn(name="TEAM_ID")
   private Team team;

  public Member(String id, String username) {
	super();
	this.id = id;
	this.username = username;
  }
   
   
}
