package hello.hellotest.Controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class helloController {
    @GetMapping("hello")
    public String hello(Model model) {
        model.addAttribute("data", "check");
        return "hello";
    }

    @GetMapping("hello-mvc")
    public String helloMvc(@RequestParam(value = "name", required = false, defaultValue = "default") String name, Model model) {
        model.addAttribute("name", name);
        return "hello-template";
    }
}
