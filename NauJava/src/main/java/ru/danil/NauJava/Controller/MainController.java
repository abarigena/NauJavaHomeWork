package ru.danil.NauJava.Controller;

import org.springframework.ui.Model;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import ru.danil.NauJava.Entities.User.User;
import ru.danil.NauJava.service.userService.UserService;

/**
 * Контроллер для обработки запросов, связанных с регистрацией пользователей.
 * Управляет отображением страницы регистрации и обработкой регистрации нового пользователя.
 */
@Controller
public class MainController {

    private final UserService userService;

    @Autowired
    public MainController(UserService userService) {
        this.userService = userService;
    }

    /**
     * Обрабатывает GET-запрос для отображения страницы регистрации.
     *
     * @return имя представления для отображения страницы регистрации
     */
    @GetMapping("/registration")
    public String getRegistration(){
        return "registration";
    }

    /**
     * Обрабатывает POST-запрос для регистрации нового пользователя.
     * Если пользователь успешно зарегистрирован, происходит перенаправление на страницу авторизации.
     * В случае ошибки (например, если пользователь уже существует), возвращает страницу регистрации с сообщением об ошибке.
     *
     * @param user  Объект пользователя, который регистрируется
     * @param model Модель для передачи данных в представление
     * @return имя представления или перенаправление в случае успешной регистрации
     */
    @PostMapping("/registration")
    public String registrerUser(User user, Model model){
        try{
            userService.addUser(user);
            return "redirect:/login";
        }
        catch(Exception e){
            model.addAttribute("message", "User exists");
            return "registration";
        }
    }
}
