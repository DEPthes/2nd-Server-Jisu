package hello.hellospring.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {
    // 컨트롤러가 정적 파일보다 우선순위가 높으므로
    // localhost:8080에서 index.html이 아닌 home.html출력
    @GetMapping("/")
    public String home() {
        return "home";
    }
}