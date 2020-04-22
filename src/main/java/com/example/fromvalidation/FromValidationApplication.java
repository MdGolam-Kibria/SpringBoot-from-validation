package com.example.fromvalidation;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import javax.validation.Valid;
import javax.validation.constraints.*;

@SpringBootApplication
@Controller
public class FromValidationApplication {
    @GetMapping("/")
    public String showForm(Person person) {
        return "register";
    }

    @PostMapping("/")
    public String register(@Valid Person person, Errors errors, Model model) {
        if (errors.hasErrors()) {
            return "register";
        } else {
            model.addAttribute("message", "Registration successfully...");
            return "register";
        }

    }

    public static void main(String[] args) {
        SpringApplication.run(FromValidationApplication.class, args);
    }

}

@Data
@AllArgsConstructor
@NoArgsConstructor
class Person {

    @NotNull
    @Size(min = 2, max = 20, message = "length shoud be in between 2 to 10")
    private String name;

    @NotEmpty(message = "Email field should not be empty")
    @Email(regexp = "^(.+)@(.+)$", message = "Invalid email pattern")
    private String email;

    @Pattern(regexp = "^(?:\\+?88)?01[135-9]\\d{8}$", message = "invalid mobile number.")
    @Size(max = 11, message = "digits should be 11")
    private String mobile;
}