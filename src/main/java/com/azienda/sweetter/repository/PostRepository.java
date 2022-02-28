package com.azienda.sweetter.repository;

import com.azienda.sweetter.model.entity.Post;
import com.azienda.sweetter.model.entity.User;
import com.azienda.sweetter.model.entity.dto.PostDTO;
import com.azienda.sweetter.model.entity.dto.UserDTO;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.sql.Timestamp;
import java.util.List;
import java.util.Optional;

@Repository
public interface PostRepository extends CrudRepository<Post, Integer> {
    @Override
    <S extends Post> S save(S entity);

    List<Post> findAllByIsActiveTrue();

    List<Post> findAllByPostOwnerAndIsActiveTrue(User user);

    List<Post> findAllByLastModifiedDateBetweenAndIsActiveTrue(Timestamp start, Timestamp end);

    List<Post> findAllByIsActiveTrueAndTitleContainingOrTextContaining(String like, String like2);

    List<Post> findAllByIsActiveTrueAndPostOwnerAndTitleContainingOrTextLike(User user, String like, String like2);

    Optional<Post> findByIdAndIsActiveTrue(Integer id);

    @Query("select u from User u join u.likedPost l where l.id = :postId and l.isActive = true")
    List<User> findLikersByIdAndIsActiveTrue(@Param("postId") Integer postId);

    @Query("select u from User u join u.unlikedPost l where l.id = :postId and l.isActive = true")
    List<User> findUnlikersByIdAndIsActiveTrue(@Param("postId") Integer postId);

    @Override
    void deleteById(Integer postId);
}
