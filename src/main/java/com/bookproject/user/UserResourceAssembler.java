package com.bookproject.user;

import com.bookproject.book.Book;
import com.bookproject.book.BookResource;
import org.springframework.hateoas.mvc.ResourceAssemblerSupport;

public class UserResourceAssembler extends ResourceAssemblerSupport<User, UserResource> {

    public UserResourceAssembler(Class<UserController> userController, Class<UserResource> userResource) {
        super(userController, userResource);
    }

    @Override
    protected UserResource instantiateResource(User user){
        return new UserResource(user);
    }

    @Override
    public UserResource toResource(User user) {
        return createResourceWithId(user.getId(), user);
    }
}
