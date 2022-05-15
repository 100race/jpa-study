package hellojpa;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import java.util.List;

public class JpaMain {
    public static void main(String[] args) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("hello"); //로딩시점에 하나만 만든다

        EntityManager em = emf.createEntityManager(); //행동 단위마다 생성.커넥션 하나라고 생각

        EntityTransaction tx = em.getTransaction(); //jpa는 트랜잭션을 중요하게 생각
        tx.begin();

        try{
            //비영속
            Member member = new Member();
            member.setId(100L);
            member.setName("HelloJPA");

            //영속 - 1차 캐시에 저장됨
            em.persist(member);

            //1차 캐시에서 조회
           Member findMember = em.find(Member.class,100L);

            System.out.println("memberId = "+findMember.getId());
            System.out.println("memberName = "+findMember.getName());

            tx.commit(); //커밋시점에 db에 저장됨
        } catch  (Exception e){
            tx.rollback();
        } finally {
            em.close();
        }

        emf.close();

    }
}
