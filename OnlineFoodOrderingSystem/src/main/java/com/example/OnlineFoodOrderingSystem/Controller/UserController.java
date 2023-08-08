package com.example.OnlineFoodOrderingSystem.Controller;

import com.example.OnlineFoodOrderingSystem.Entiry.OrdersTbl;
import com.example.OnlineFoodOrderingSystem.Entiry.Product;
import com.example.OnlineFoodOrderingSystem.Repo.OrderRepo;
import com.example.OnlineFoodOrderingSystem.Repo.ProductRepo;
import com.example.OnlineFoodOrderingSystem.Utils.FileUploader;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.multipart.MultipartFile;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

@Controller
public class UserController {
    ArrayList<Integer> productId=new ArrayList<Integer>();
    @Autowired
    ProductRepo productRepo;
    @Autowired
    FileUploader uploader;
    @Autowired
    OrderRepo orderRepo;


    @GetMapping("/")
    public String homePage(Model model)
    {
        List<Product> burgerSection=productRepo.findByCategory("2");
        List<Product> pizaSection=productRepo.findByCategory("1");
        List<Product> chickenSection=productRepo.findByCategory("3");
        model.addAttribute("burgerSection",burgerSection);
        model.addAttribute("pizaSection",pizaSection);
        model.addAttribute("chickenSection",chickenSection);
        return "index";
    }

    @GetMapping("/bill/")
    public String billPage()
    {
        return "Bill";
    }
    @GetMapping("/order/")
    public String oderPage()
    {
        return "Order_Page";
    }
    @GetMapping("/record/")
    public String recordPage()
    {
        return "RecordTable";
    }

    @GetMapping("/order/product/{id}/")
    public String orderProductPage(@PathVariable int id,Model model)
    {
        Product addedProductList=productRepo.getReferenceById(id);
        model.addAttribute("addedProductList",addedProductList);
        return "AddProduct_Page";
    }
    @GetMapping("/add/to/card/{id}/")
    public String addToCardPage(@PathVariable int id,Model model)
    {
        if(productId.contains(id))
        {
            model.addAttribute("emsg","This product is already added!");
        }
        else{
            productId.add(id);
            System.out.println(productId);
        }
        return "index";
    }
    @GetMapping("/add/to/card/")
    public String addToCardPage(Model model){
        List<Product> addedProductList=productRepo.findAllById(productId);
        model.addAttribute("addedProductList",addedProductList);
        model.addAttribute("productId",productId);
        return "AddProduct_Page";
    }

    @PostMapping("/add/new/product/")
    public String addNewProduct(Model model, Product pd, MultipartFile file){
        String fileNameOld = file.getOriginalFilename();
        String extension = fileNameOld.substring(fileNameOld.indexOf(".") + 1);
        pd.setExtension(extension);

        Product product = productRepo.save(pd);
        String fileNameNew = product.getId() + "." + extension;

        uploader.uploadFile(file, fileNameNew);
        pd.setExtension(extension);
        model.addAttribute("msg", "New Product Added Successfully");
        return "Order_Page";
    }
    @PostMapping("/order/saved/")
    public String saveOrdedData(Model model, String custName, String custAddress, String custPhone,
                                String custEmail, String total,String qtys, LocalDate curDate)
    {
        try {
            orderRepo.save(new OrdersTbl(productId.toString(),custName,custAddress,custPhone,custEmail,total,qtys,curDate));
        }
        catch (Exception e)
        {
            model.addAttribute("emsg","Some thing went wrong...Please try after some time");
        }
        model.addAttribute("msg","Your ordered placed successfully...!");

        List<Product> poProductList=productRepo.findAllById(productId);

        model.addAttribute("poProductList",poProductList);
        return "Bill";
    }
}
