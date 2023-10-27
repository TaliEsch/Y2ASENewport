package com.example.wastemanagement.web;

import com.example.wastemanagement.domain.Comment;
import com.example.wastemanagement.recaptcha.RecaptchaResponse;
import com.example.wastemanagement.service.*;
import com.example.wastemanagement.service.Dto.BlogDto;
import com.example.wastemanagement.web.Form.BlogForm;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.HandlerExceptionResolver;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@EnableWebSecurity // Enables protection required for the form (e.g. CSRF)
@Controller
@RequestMapping("blog") // This controller will handle all requests to /blog/**
public class BlogController {

    private final RecaptchaOpinionService recaptchaOpinionService;
    private final CategoryService categoryService;
    private final BlogService blogService;
    @Autowired
    private final CommentService commentService;
    private static final Logger log = LoggerFactory.getLogger(BlogController.class);

    public BlogController(CategoryService categoryService, BlogService blogService, RecaptchaOpinionService recaptchaOpinionService,
                          CommentService commentService) {
        this.commentService = commentService;
        this.categoryService = categoryService;
        this.blogService = blogService;
        this.recaptchaOpinionService = recaptchaOpinionService;
    }

    // addForm will enable users to enter the info to be posted as a blog
    @GetMapping("addForm")
    public ModelAndView addBlog(Model model) {
        var categories = categoryService.getCategories();
        model.addAttribute("categories", categories);
        // Creates a new blogForm for the users inputs to be assigned to
        model.addAttribute("blogAddForm", new BlogForm());
        // Returns both the html doc and the form denoted above
        var modelView = new ModelAndView("blog/addForm", model.asMap());
        return modelView;
    }

    // Simply a string return as the user is just being redirected to the home page, no data is required
    @PostMapping("addForm")
//    @ExceptionHandler(FileUploadExceptionAdvice.class)
    public String addBlog(@Valid @ModelAttribute("blogAddForm") BlogForm blogFormToAdd, BindingResult bindingResult, Model model) throws IOException {
        // TODO: need to move the logic to the service layer

        GlobalValidation global = new GlobalValidation();
        log.info(">>>>>>>>>>>>>>>>>>>>>>>>>" + blogFormToAdd.getItemDescription());
        List<FieldError> banned = global.Validation(blogFormToAdd.getItemDescription().toString(), "itemDescription", "blogAddForm");
        banned.addAll(global.Validation(blogFormToAdd.getBlogTitle().toString(), "blogTitle", "blogAddForm"));
        //if statements bypass global validation if empty, if they aren't empty then they still validated

        if (!blogFormToAdd.getUsername().equals("")) {
            banned.addAll(global.Validation(blogFormToAdd.getUsername().toString(), "username", "blogAddForm"));
        } else {
            blogFormToAdd.setUsername(null);
        }

        if (!blogFormToAdd.getItemName().equals("")) {
            banned.addAll(global.Validation(blogFormToAdd.getItemName().toString(), "itemName", "blogAddForm"));
        } else {
            blogFormToAdd.setItemName(null);
        }

        if (!blogFormToAdd.getBlogContact().equals("")) {
            banned.addAll(global.Validation(blogFormToAdd.getBlogContact().toString(), "blogContact", "blogAddForm"));
        } else {
            blogFormToAdd.setBlogContact(null);
        }

//if statement here turns category "null" (string) to null
        if (blogFormToAdd.getItemCategory().equals("null")) {
            blogFormToAdd.setItemCategory(null);
        }
        log.info(">>>>>>>>>>>>>>>>>>>>>>>>>>>>>" + banned.toString());


        for (int i = 0; i < banned.size(); i++) {
            bindingResult.addError(banned.get(i));

        }
        if (bindingResult.hasErrors()) {
            System.out.println("LOG: User Error");
            bindingResult.getAllErrors().forEach(System.out::println);
        }

        // Calls the blog service to begin the process of adding the entered info to the database
        // the errors are returned to the form page if any are found
        if (!bindingResult.hasErrors()) {
            blogService.addBlog(blogFormToAdd);
            var listOfBlogs = blogService.getAllBlogs();

            model.addAttribute("listOfBlogs", listOfBlogs);

            return "redirect:/blog";
        }

        var categories = categoryService.getCategories();
        model.addAttribute("categories", categories);

        return "blog/addForm";
    }

    @GetMapping("")
    public ModelAndView displayBlogs(Model model) throws IOException {
        var listOfBlogs = blogService.getAllBlogs();

        model.addAttribute("listOfBlogs", listOfBlogs);
        return new ModelAndView("blog/blogs", model.asMap());
    }

    @GetMapping("{id}")
    public ModelAndView singleBlog(@PathVariable("id") int id, Model model) {
        var blog = blogService.getBlogByID(id);
        List<Comment> comments = new ArrayList<>();
        // Checks if that specific(ID) blog is existing
        if (!blog.equals(null)) {
            return getModelAndView(id, model, blog, comments, new Comment());
        }
        // If the blog is not found, the user given an error page
        return new ModelAndView("error/blogNotExist", model.asMap());
    }

    @PostMapping("{id}")
    public ModelAndView singleBlog(@PathVariable("id") int id, Model model, @Valid Comment comment, BindingResult bindingResult) {
        var blog = blogService.getBlogByID(id);
        List<Comment> comments = new ArrayList<>();
        // Checks if that specific(ID) blog is existing
        if (bindingResult.hasErrors()) {
            //Gets all the comments for that specific blog
            return getModelAndView(id, model, blog, comments, comment);
        }

        // save the comment
        commentService.addComment(comment, id);
        return getModelAndView(id, model, blog, comments, new Comment());
    }

    private ModelAndView getModelAndView(int id, Model model, BlogDto blogDto, List<Comment> comments, Comment comment) {
        // Gets all the comments for that specific blog
        comments.addAll(commentService.getCommentsByBlogId(id));
        model.addAttribute("comments", comments);
        model.addAttribute("blog", blogDto);
        model.addAttribute("comment", comment);
        return new ModelAndView("blog/individualBlog", model.asMap());
    }

    // Deleting blog by Id
    @PostMapping("{id}/delete")
    public RedirectView deleteBlog(@PathVariable("id") int id, RedirectView redirectView) {
        blogService.deleteBlog(id);
        return new RedirectView("/blog");
    }

    // path variable getting the integer id from the url
    // RedirectView is used to redirect the user
    // Deleting comment by Id
    @PostMapping("{blogId}/comment/{Id}/delete")
    public RedirectView deleteComment(@PathVariable("blogId") int blogId, @PathVariable("Id") int Id, RedirectView redirectView) {
        commentService.deleteComment(Id);
        return new RedirectView("/blog/" + blogId);
    }
}

