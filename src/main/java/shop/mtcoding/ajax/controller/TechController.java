package shop.mtcoding.ajax.controller;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.ResponseBody;
import shop.mtcoding.ajax.dto.TechResponse;
import shop.mtcoding.ajax.model.Category;
import shop.mtcoding.ajax.model.CategoryRepository;
import shop.mtcoding.ajax.model.Tech;
import shop.mtcoding.ajax.model.TechRepository;
import org.springframework.web.bind.annotation.RequestParam;


@Controller
public class TechController {
    @Autowired
    private TechRepository techRepository;
    @Autowired
    private CategoryRepository categoryRepository;

    @GetMapping("/v1/test/tech")
    public @ResponseBody TechResponse.MainDTO techV1() {
        List<Category> categoryList = categoryRepository.findAll();
        List<Tech> techList = techRepository.findByCategoryId(1); // 이시점에선 카테고리는 조회안함(lazy로딩)
        System.out.println("=================================");
        TechResponse.MainDTO mainDTO = new TechResponse.MainDTO(categoryList, techList);
        return mainDTO; // messageconverter 발동(dto가 아니라 entity면 게터 호출되는 시점에서 lazy로딩) - json 변환
    }
    
    @GetMapping("/v2/test/tech")
    public @ResponseBody List<Tech> techV2() {
        List<Tech> techList = techRepository.findByCategoryId(1);
        return techList;
    }
    
    // @GetMapping("/tech")
    // public String tech(Model model) {
    //     List<Category> categoryList = categoryRepository.findAll();
    //     List<Tech> techList = techRepository.findByCategoryId(1);
    //     model.addAttribute("categoryList", categoryList);
    //     model.addAttribute("techList", techList);
    //     return "main";
    // }
    

    // 그냥 빈 껍데기 디자인만 준다 (데이터 없음)
    @GetMapping("/tech")
    public String tech() {
        return "main";
    }

    @GetMapping("/api/category") // 실무에서 뷰가 아니라 데이터(json)를 반환하는건 보통 api를 붙힘
    public @ResponseBody List<Category> categoryApi() {
        return categoryRepository.findAll();
    }
    
    @GetMapping("/api/tech")
    public @ResponseBody List<Tech> techApi(@RequestParam(defaultValue = "1") Integer categoryId) {
        return techRepository.findByCategoryId(categoryId);
    }
}