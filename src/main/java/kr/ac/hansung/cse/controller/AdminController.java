package kr.ac.hansung.cse.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import kr.ac.hansung.cse.model.Product;
import kr.ac.hansung.cse.service.ProductService;

@Controller
@RequestMapping("admin")
public class AdminController {

	@Autowired
	private ProductService productService;

	@RequestMapping
	public String adminPage() {
		return "admin";
	}

	@RequestMapping("/productInventory")
	public String getProducts(Model model) {
		List<Product> products = productService.getProducts();

		model.addAttribute("products", products);

		return "productInventory";
	}

	@RequestMapping(value = "/productInventory/addProduct", method = RequestMethod.GET)
	public String addProductGet(Model model) {
		Product product = new Product();

		model.addAttribute("product", product);

		return "addProduct";
	}

	@RequestMapping(value = "/productInventory/addProduct", method = RequestMethod.POST)
	public String addProductPost(@Valid Product product, BindingResult result) {

		if (result.hasErrors()) {
			System.out.println("==== Web Form data has some errors ====");
			List<ObjectError> errors = result.getAllErrors();

			for (ObjectError error : errors) {
				System.out.println(error.getDefaultMessage());
			}

			return "addProduct";
		}

		System.out.println(product);
		productService.addProduct(product);

		return "redirect:/admin/productInventory";
	}

	@RequestMapping(value = "/productInventory/deleteProduct/{id}", method = RequestMethod.GET)
	public String deleteProductPost(@PathVariable int id) {
		Product product = productService.getProductById(id);
		productService.deleteProduct(product);
		return "redirect:/admin/productInventory";
	}

	@RequestMapping(value = "/productInventory/updateProduct/{id}", method = RequestMethod.GET)
	public String updateProduct(@PathVariable int id, Model model) {
		
		Product product = productService.getProductById(id);
		model.addAttribute("product", product);
		return "updateProduct";
	}

	@RequestMapping(value = "/productInventory/updateProduct", method = RequestMethod.POST)
	public String updateProductPost(@Valid Product product, BindingResult result) {

		if (result.hasErrors()) {
			System.out.println("==== Web Form data has some errors ====");
			List<ObjectError> errors = result.getAllErrors();

			for (ObjectError error : errors) {
				System.out.println(error.getDefaultMessage());
			}

			return "updateProduct";
		}

		productService.updateProduct(product);
		return "redirect:/admin/productInventory";
	}
}
