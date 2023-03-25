package edu.uet.signlanguage.repository;

import edu.uet.signlanguage.entity.Sentence;
import edu.uet.signlanguage.entity.User;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface SentenceRepository extends JpaRepository<Sentence, Integer> {
    Optional<Sentence> findById(Integer integer);
    //Optional<Sentence> findByUser(User user);

    List<Sentence> findByUser(User user);

    @Query("SELECT s FROM Sentence s WHERE s.user = ?1 AND s.favor = ?2")
    List<Sentence> findByUserAndFavor(User user, boolean favor);

    Optional<Sentence> findByFavor(boolean bool);
//    @PersistenceContext
//    EntityManager entityManager = (EntityManager) Persistence.createEntityManagerFactory("my-persistence-unit");
//
//    public default List<Sentence> searchAllSentenceByUser(int id, boolean favor){
//        // Khởi tạo CriteriaBuilder
//        CriteriaBuilder cb = entityManager.getCriteriaBuilder();
//        // Tạo CriteriaQuery cho User
//        CriteriaQuery<Sentence> query = cb.createQuery(Sentence.class);
//        Root<Sentence> userRoot = query.from(Sentence.class);
//        // Tạo các điều kiện tìm kiếm
//        Predicate idPredicate = cb.equal(userRoot.get("id"), id);
//        //Predicate agePredicate = cb.greaterThan(userRoot.get("age"), age);
//        Predicate favourPredicate = cb.equal(userRoot.get("favour"), favor);
//        // Kết hợp các điều kiện tìm kiếm bằng AND
//        Predicate finalPredicate = cb.and(idPredicate, favourPredicate);
//        // Thêm điều kiện tìm kiếm vào CriteriaQuery
//        query.where(finalPredicate);
//        // Thực hiện truy vấn và trả về danh sách các user thỏa mãn
//        return entityManager.createQuery(query).getResultList();
//    }
}
