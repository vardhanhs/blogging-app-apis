package com.blogging_apis.blog.repositories;

import com.blogging_apis.blog.entities.Category;
import com.blogging_apis.blog.entities.Post;
import com.blogging_apis.blog.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PostRepo extends JpaRepository<Post, Integer> {
        List<Post> findByUser(User user);
        List<Post> findByCategory(Category category);

        List<Post> findByTitleContaining(String title);

}
