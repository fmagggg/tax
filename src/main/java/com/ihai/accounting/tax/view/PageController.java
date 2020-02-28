package com.ihai.accounting.tax.view;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class PageController {

    @RequestMapping("")
    public String home(Model model) {
        return "home";
    }

    @RequestMapping("sheet-filter")
    public String sheetFilter(Model model) {
        return "sheet-filter";
    }

    @RequestMapping("demo")
    public String demo(Model model) {
        return "feiyan";
    }

    @RequestMapping("result.html")
    public String result(Model model) {
        return "result";
    }
}
