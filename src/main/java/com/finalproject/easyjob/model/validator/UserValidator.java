package com.finalproject.easyjob.model.validator;

import com.finalproject.easyjob.model.User;
import java.util.ArrayList;
import java.util.List;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
public class UserValidator {

  public void accept(List<User> users) {
    users.forEach(this::accept);
  }

  public void accept(User user) {
    List<String> exceptions = new ArrayList<>();
    if (user.getPassword() == null) {
      exceptions.add("password is missing");
    }
    if (user.getEmail() == null) {
      exceptions.add("email is missing");
    }
    if (!exceptions.isEmpty()) {
      String constraintMessages = String.join(". ", exceptions);
      throw new RuntimeException(constraintMessages);
    }
  }
}
