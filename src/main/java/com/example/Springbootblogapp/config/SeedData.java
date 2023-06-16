package com.example.Springbootblogapp.config;

import com.example.Springbootblogapp.models.Account;
import com.example.Springbootblogapp.models.Authority;
import com.example.Springbootblogapp.models.Post;
import com.example.Springbootblogapp.repositories.AuthorityRepository;
import com.example.Springbootblogapp.services.AccountService;
import com.example.Springbootblogapp.services.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Component
public class SeedData implements CommandLineRunner {

    @Autowired
    private PostService postService;

    @Autowired
    private AuthorityRepository authorityRepository;

    @Autowired
    private AccountService accountService;

    @Override
    public void run(String... args) throws Exception {
        List<Post> posts = postService.getAll();

        if(posts.size() == 0){

            Authority user = new Authority();
            user.setName("ROLE_USER");
            authorityRepository.save(user);

            Authority admin = new Authority();
            admin.setName("ROLE_ADMIN");
            authorityRepository.save(admin);

            Account account1 = new Account();
            Account account2 = new Account();

            account1.setFirstName("User1");
            account1.setLastName("User1Lastname");
            account1.setEmail("u.user1Lastname@email.com");
            account1.setPassword("password");
            Set<Authority> authorities1 = new HashSet<>();
            authorityRepository.findById("ROLE_USER").ifPresent(authorities1::add);
            account1.setAuthorities(authorities1);

            account2.setFirstName("admin");
            account2.setLastName("admin");
            account2.setEmail("a.admin@email.com");
            account2.setPassword("password");
            Set<Authority> authorities2 = new HashSet<>();
            authorityRepository.findById("ROLE_USER").ifPresent(authorities2::add);
            authorityRepository.findById("ROLE_ADMIN").ifPresent(authorities2::add);
            account2.setAuthorities(authorities2);

            // persisting
            accountService.saveAccount(account1);
            accountService.saveAccount(account2);


            Post firstPost = new Post();
            firstPost.setTitle("Why hiring Mika Valjakka is profitable for Company");
            firstPost.setBody("""
            Hiring Mika Valjakka is a profitable choice for any company seeking a talented individual with a strong background in the basics of 
            Java, Spring Boot, and Angular. 
            With a deep understanding of these technologies, 
            Mika brings a solid foundation to tackle complex development challenges. 
            His passion for learning new technologies ensures he stays up to date with the latest advancements, 
            allowing him to adapt quickly and deliver innovative solutions. 
            By harnessing Mika's expertise, companies can capitalize on his knowledge and drive to excel, 
            fostering a culture of continuous learning and technological advancement""");
            firstPost.setAccount(account1);

            Post secondPost = new Post();
            secondPost.setTitle("Title Of Second post");
            secondPost.setBody("This is the Body of the Second Post");
            secondPost.setAccount(account2);

            postService.save(firstPost);
            postService.save(secondPost);
        }
    }
}
