package com.isaac.blogpost;

import com.isaac.blogpost.dto.request.CreatePostRequest;
import com.isaac.blogpost.dto.response.PostResponse;
import com.isaac.blogpost.entity.Post;
import com.isaac.blogpost.entity.User;
import com.isaac.blogpost.exception.HttpException;
import com.isaac.blogpost.repository.PostRepository;
import com.isaac.blogpost.service.impl.PostServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

class PostServiceImplTest {

    @Mock
    private PostRepository postRepository;

    @InjectMocks
    private PostServiceImpl postService;

    private User user;
    private Post post;
    private CreatePostRequest createPostRequest;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.initMocks(this);

        user = new User();
        user.setId(1L);
        user.setName("Test User");

        post = new Post();
        post.setId(1L);
        post.setTitle("Test Title");
        post.setContent("Test Content");
        post.setUser(user);

        createPostRequest = new CreatePostRequest("Test Title", "Test Content");

    }

    @Test
    void createPostSuccessfully() {
        when(postRepository.save(any(Post.class))).thenReturn(post);

        PostResponse postResponse = postService.createPost(createPostRequest, user);

        assertEquals(post.getId(), postResponse.id());
        assertEquals(post.getTitle(), postResponse.title());
        assertEquals(post.getContent(), postResponse.content());
        assertEquals(post.getUser().getName(), postResponse.author());
    }

    @Test
    void getAllPostsSuccessfully() {
        when(postRepository.findAll()).thenReturn(Arrays.asList(post));

        List<PostResponse> postResponses = postService.getAllPosts();

        assertFalse(postResponses.isEmpty());
        assertEquals(1, postResponses.size());
        assertEquals(post.getId(), postResponses.get(0).id());
    }

    @Test
    void getPostsByUserSuccessfully() {
        when(postRepository.findByUser(user)).thenReturn(Arrays.asList(post));

        List<PostResponse> postResponses = postService.getPostsByUser(user);

        assertFalse(postResponses.isEmpty());
        assertEquals(1, postResponses.size());
        assertEquals(post.getId(), postResponses.get(0).id());
    }

    @Test
    void getPostsByTitleSuccessfully() {
        when(postRepository.findByTitleContainingIgnoreCase(anyString())).thenReturn(Arrays.asList(post));

        List<PostResponse> postResponses = postService.getPostsByTitle("Test");

        assertFalse(postResponses.isEmpty());
        assertEquals(1, postResponses.size());
        assertEquals(post.getId(), postResponses.get(0).id());
    }

    @Test
    void updatePostSuccessfully() {
        when(postRepository.findById(anyLong())).thenReturn(Optional.of(post));
        when(postRepository.save(any(Post.class))).thenReturn(post);

        PostResponse postResponse = postService.updatePost(1L, createPostRequest, user);

        assertEquals(post.getId(), postResponse.id());
        assertEquals(post.getTitle(), postResponse.title());
        assertEquals(post.getContent(), postResponse.content());
        assertEquals(post.getUser().getName(), postResponse.author());
    }

    @Test
    void deletePostSuccessfully() {
        when(postRepository.findById(anyLong())).thenReturn(Optional.of(post));

        assertDoesNotThrow(() -> postService.deletePost(1L, user));
    }

    @Test
    void updatePostThrowsExceptionWhenPostNotFound() {
        when(postRepository.findById(anyLong())).thenReturn(Optional.empty());

        assertThrows(HttpException.class, () -> postService.updatePost(1L, createPostRequest, user));
    }

    @Test
    void deletePostThrowsExceptionWhenPostNotFound() {
        when(postRepository.findById(anyLong())).thenReturn(Optional.empty());

        assertThrows(HttpException.class, () -> postService.deletePost(1L, user));
    }

    @Test
    void updatePostThrowsExceptionWhenUserNotAuthorized() {
        User anotherUser = new User();
        anotherUser.setId(2L);
        anotherUser.setName("Another User");

        when(postRepository.findById(anyLong())).thenReturn(Optional.of(post));

        assertThrows(HttpException.class, () -> postService.updatePost(1L, createPostRequest, anotherUser));
    }

    @Test
    void deletePostThrowsExceptionWhenUserNotAuthorized() {
        User anotherUser = new User();
        anotherUser.setId(2L);
        anotherUser.setName("Another User");

        when(postRepository.findById(anyLong())).thenReturn(Optional.of(post));

        assertThrows(HttpException.class, () -> postService.deletePost(1L, anotherUser));
    }
}