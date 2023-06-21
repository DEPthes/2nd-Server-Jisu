package hellojpa;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

public class JpaMain {
    public static void main(String[] arg) {
        // JPA 구동 방식 이미지를 따라 Persistence -> EntityManagerFactory 생성
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("hello");

        EntityManager em = emf.createEntityManager();

        EntityTransaction tx = em.getTransaction();   // 트랜잭션 얻어오기
        tx.begin();                                   // 트랜잭션 시작

        // 예외처리
        try {
            Member findMember = em.find(Member.class, 1L);
            findMember.setName("HelloJPA");

            // em.persist(findMember); -> 저장하지 않아도 됨

            tx.commit();                              // 트랜잭션 커밋
        } catch(Exception e) {
            tx.rollback();                              // 트랜잭션 롤백
        } finally {
            em.close();
        }

        // 애플리케이션이 완전히 종료되면 EntityMangerFactory close
        emf.close();
    }
}
