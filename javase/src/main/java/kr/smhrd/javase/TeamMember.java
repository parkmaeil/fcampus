package kr.smhrd.javase;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import kr.smhrd.model.*;

public class TeamMember {

	public static void main(String[] args) {
		
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("YourPersistenceUnitName");
	    EntityManager em = emf.createEntityManager();
		
	    em.getTransaction().begin();
	  
	  /*
	    Team team1=new Team("team1","팀1");
	    em.persist(team1);
	    
		Member member1=new Member("member1", "회원1");
		member1.setTeam(team1);
		em.persist(member1);
		
		Member member2=new Member("member2", "회원2");
		member2.setTeam(team1);
		em.persist(member2);
		*/
	    /*
	    Member member=em.find(Member.class, "member1");
	    Team team=member.getTeam();
	    System.out.println(team.getName());
	    */
	    
	    String jpql="select m from Member m join m.team t where t.name=:teamName";
	    List<Member> result=em.createQuery(jpql, Member.class).setParameter("teamName", "팀1").getResultList();
	   
	    for(Member m : result) {
	    	System.out.println(m.getUsername());
	    }
	    
		em.getTransaction().commit();
	   
	    em.close();
	    emf.close();
	}

}
