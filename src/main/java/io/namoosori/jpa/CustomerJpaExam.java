package io.namoosori.jpa;

import io.namoosori.jpa.entity.Major;
import io.namoosori.jpa.entity.Student;
import jakarta.persistence.*;

import java.util.List;

public class CustomerJpaExam {

    public static void main(String[] args) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("customer-exam");
        EntityManager em = emf.createEntityManager();
        EntityTransaction tx = em.getTransaction();

        tx.begin();
        try{

            Major major = new Major( "Computer Science", "College of Enginnering");
            em.persist(major);

            Student student = new Student("Kim", "3");
            student.setMajor(major);
            em.persist(student);

            em.flush();
            em.clear();

            // Student 검색
            Student foundStudent = em.find(Student.class, 1);
            foundStudent.getName();
            foundStudent.getGrade();
            //
            System.out.println(foundStudent.getMajor().getName()); // 해당 쿼리에서
            // major에 대한 정보를 사용하고자 할 때(가져올 필요가 있을 때)를 구분해서 사용하는 지연로딩(LAZYLOADING)
            // FetchType.LAZY : 지연로딩 / FetchType.EAGER : 그냥 다 찾아옴.
//            System.out.println(foundStudent); // 학생 정보와 전공 정보가 온전히 다 들어감.

//            Major foundMajor = em.find(Major.class , foundStudent.getMajorId());
//            System.out.println(foundMajor);

//                Customer customer = new Customer(); // 비영속 상태(new)
//                customer.setName("Kim");
//                customer.setRegisterDate(System.currentTimeMillis());
//                em.persist(customer); // Customer 객체가 영속 상태(Managed)

            //--> 영속성 컨텍스트 안의 1차 캐시에 ID, Object 형태로 들어감.
            // 이 때 ID는 데이터가 insert 될 때 db에서 발생하기 때문에 현재 상태에서는
            // 위와 같이 관리될 수 없다.
            // persist 시점에 ID를 알아오기 위해 insert 쿼리가 진행이 됨.
//            em.flush(); // DB에 동기화, persist에 대한 insert 쿼리가 나가게 됨!
            // 아직 커밋을 하기 전이므로, 테이블에 데이터가 들어가 있지는 않다!

//            Query query = em.createQuery("SELECT c FROM Customer c", Customer.class);
//            List<Customer> customers = query.getResultList();
//            System.out.println(customers);
// 커밋을 안해도 JPQL 쿼리 실행 시에는 flush()가 자동으로 되므로, console에 출력 시에는
// 4개의 데이터가 모두 보이게 된다.
//            em.detach(customer);    // Customer 객체를 준영속 상태(Detached)로 전환
            // --> 이 때는 select문이 나가게 된다! 기존에 db에 ID0005 영속 객체를 넣었기 때문.
//            Customer foundCustomer = em.find(Customer.class, 1L);

//            System.out.println("===================== Before Commit ===================");

            tx.commit();
        } catch (Exception e){
            tx.rollback();
        } finally {
            em.close();
        }
        emf.close();
    }
}
