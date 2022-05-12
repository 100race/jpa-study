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
            //[code]

            //jpql
            List<Member> result = em.createQuery("select m from Member as m", Member.class).getResultList();

            for (Member member : result) {
                System.out.println("member = " + member.getName());
            }

            /* update
            Member findMember = em.find(Member.class, 1L);
            findMember.setName("HelloJPA");
            select문과의 차이 :  em.persist 저장 안해줘도 된다 */


            /* delete
            em.remove();
            */

            /* select
            Member findMember = em.find(Member.class, 1L);
            System.out.println("findMember.id = " + findMember.getId());
            System.out.println("findMember.name = " + findMember.getName());
            */

            /* insert
            Member member = new Member();
            member.setId(2L);
            member.setName("HelloB");

            em.persist(member);*/

            //[code]

            tx.commit();
        } catch  (Exception e){
            tx.rollback();
        } finally {
            em.close();
        }

        emf.close();

    }
}
