package com.phase2.homeService.util;

import com.phase2.homeService.service.implementations.CustomerServiceImple;
import com.phase2.homeService.service.implementations.ProfessionalServiceImple;
import com.phase2.homeService.service.implementations.ServicesServiceImple;
import com.phase2.homeService.entities.Services;
import com.phase2.homeService.exception.*;
import org.springframework.stereotype.Component;

import java.io.*;
import java.util.HashSet;
import java.util.InputMismatchException;
import java.util.Scanner;
import java.util.Set;
import java.util.regex.Pattern;

import static com.phase2.homeService.util.AllPurpose.scanner;

@Component
public class Utility {
    private String firstName, lastName, nationalCode, password, cityName;

    private final CustomerServiceImple customerService;
    private final ProfessionalServiceImple professionalService;
    private final ServicesServiceImple servicesService;

    public Utility(CustomerServiceImple customerService, ProfessionalServiceImple professionalService, ServicesServiceImple serviceService) {
        this.customerService = customerService;
        this.professionalService = professionalService;
        this.servicesService = serviceService;
    }

    public String setFirstName() {
        while (true) {
            System.out.print("Enter first name: ");
            scanner.nextLine();
            try {
                firstName = scanner.nextLine();
                checkName(firstName);
                return firstName;
            } catch (InvalidNameException except) {
                System.out.println(except.getMessage());
            }
        }
    }

    public void checkName(String name) {
        if (name.length() < 3)
            throw new InvalidNameException("name should be more than 2 character!");
        for (Character ch : name.toCharArray()) {
            if (Character.isDigit(ch))
                throw new InvalidNameException("name can not have number!");
        }
        for (Character ch : name.toCharArray()) {
            if (!Character.isAlphabetic(ch))
                throw new InvalidNameException("name can't have Sign(!,@,#,%,...)");
        }
    }

    public String setLastName() {
        while (true) {
            System.out.print("Enter last name:");
            try {
                lastName = scanner.nextLine();
                checkName(lastName);
                return lastName;
            } catch (InvalidNameException except) {
                System.out.println(except.getMessage());
            }
        }
    }

    public String setEmail() {
        while (true) {
            String email = regexAdder("^[^@\\s]+@[^@\\s]+\\.[^@\\s]+$", "Email", "Example: alirezaVk@gmail.com");
            if (customerService.findByEmail(email) != null || professionalService.findByEmail(email) != null) {
                System.out.println("Email address already exists!!!");
            } else return email;
        }
    }

    public String setPassword() {
        while (true) {
            System.out.print("Enter your password:");
            try {
                password = scanner.nextLine();
                validatePassword(password);
                return password;
            } catch (InvalidPasswordException except) {
                System.out.println(except.getMessage());
            }
        }
    }

    public void validatePassword(String password) {
        if (password.length() < 3)
            throw new InvalidPasswordException("password should be more than 2 ");
        char[] passwordArray = password.toCharArray();
        char[] signArray = new char[]{'!', '@', '#', '$', '%', '^', '&', '*', '(', ')', '-', '+', '=', '.', ',', '>', '<', '?', '/', '|', ':', ';'};
        int lowerCase = 0, upperCase = 0, sign = 0, digit = 0;
        for (char c : passwordArray)
            if (Character.isUpperCase(c))
                ++upperCase;
        for (char c : passwordArray)
            if (Character.isLowerCase(c))
                ++lowerCase;
        for (char c : passwordArray)
            if (Character.isDigit(c))
                ++digit;
        for (char c : signArray)
            for (char value : passwordArray)
                if (c == value)
                    ++sign;
        if ((lowerCase == 0) || (upperCase == 0) || (sign == 0) || (digit == 0))
            throw new InvalidPasswordException("Password should have lowerCase + upperCase + sign + digit!");
    }

    public String setCityName() {
        while (true) {
            System.out.print("Enter city name :");
            try {
                cityName = scanner.nextLine();
                checkName(cityName);
                return cityName;
            } catch (InvalidNameException except) {
                System.out.println(except.getMessage());
            }
        }
    }

    public String setNationalCode() {
        while (true) {
            System.out.print("Enter national code:");
            try {
                nationalCode = scanner.nextLine();
                nationalCodeChecker(nationalCode);
                return nationalCode;
            } catch (InvalidNationalCodeException except) {
                System.out.println(except.getMessage());
            }
        }
    }

    public void nationalCodeChecker(String nationalCode) {
        if (nationalCode.length() > 10)
            throw new InvalidNationalCodeException("national code can't more than ten number!");
        if (nationalCode.equals(""))
            throw new InvalidNationalCodeException("dont enter space!");
        for (Character ch : nationalCode.toCharArray()) {
            if (!Character.isDigit(ch))
                throw new InvalidNationalCodeException("national code should be just number!");
        }
    }

    public String regexAdder(String regex, String tag, String additionalInfo) {
        while (true) {
            System.out.print(tag + "(" + additionalInfo + "): ");
            Scanner scanner = new Scanner(System.in);
            String input = scanner.nextLine();
            if (Pattern.compile(regex).matcher(input).matches()) {
                return input;
            } else {
                System.out.println("Wrong Email Format!!!");
            }
        }
    }

    public byte[] setImage(byte[] image) {
        try {
            ByteArrayInputStream fileInputStream = new ByteArrayInputStream(image);
            fileInputStream.read(image);
            fileInputStream.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return image;
    }

    public Set<Services> setService() {
        Set<Services> services = new HashSet<>();
        while (true) {
            System.out.print("Select your services from the list below :");
            showAllServicesWithCategoryId();
            try {
                Integer id = scanner.nextInt();
                Services service = servicesService.getById(id);
                services.add(service);
                System.out.println("Want to add another service? y/n");
                String choice = scanner.nextLine();
                if (choice == "n")
                    return services;
            } catch (InvalidNameException except) {
                System.out.println(except.getMessage());
            }
        }
    }

    public void showAllServicesWithCategoryId() {
        System.out.println(servicesService.findAll().toString());

    }

    public Integer setId() {
        while (true) {
            System.out.print("Enter id : ");
            try {
                Integer id = scanner.nextInt();
                scanner.nextLine();
                return id;
            } catch (InputMismatchException except) {
                scanner.nextLine();
                System.out.println("You just have to enter the number");
            }
        }
    }
}
