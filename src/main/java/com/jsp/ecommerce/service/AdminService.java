package com.jsp.ecommerce.service;

import java.io.IOException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.ui.ModelMap;
import org.springframework.web.multipart.MultipartFile;

import com.jsp.ecommerce.dao.ProductDaoImpl;
import com.jsp.ecommerce.model.Product;

@Service
public class AdminService {

	@Autowired
	ProductDaoImpl productDaoImpl;

	public String addProduct(Product product, MultipartFile pic, ModelMap map) throws IOException {
		byte[] picture = new byte[pic.getInputStream().available()];
		pic.getInputStream().read(picture);
		product.setPicture(picture);
		productDaoImpl.save(product);

		map.put("pass", "Product Added Success");
		return "AdminHome";
	}

	public String fetchProducts(ModelMap map) {
		List<Product> products = productDaoImpl.fetchAll();
		if (products.isEmpty()) {
			map.put("fail", "No Products Found");
			return "AdminHome";
		} else {
			map.put("products", products);
			return "AdminViewProduct";
		}
	}

	public String changeStatus(int id, ModelMap map) {
		Product product=productDaoImpl.findById(id);
		if(product.isDisplay())
			product.setDisplay(false);
		else
			product.setDisplay(true);
		
		productDaoImpl.save(product);
		
		map.put("pass", "Status Update Success");
		return fetchProducts(map);
	}

	public String deleteProduct(int id, ModelMap map) {
		Product product=productDaoImpl.findById(id);
		productDaoImpl.delete(product);
		
		map.put("pass", "Product Deleted Success");
		return fetchProducts(map);
	}

	public String editProduct(int id, ModelMap map) {
		Product product=productDaoImpl.findById(id);
		map.put("product", product);
		return "EditProduct.html";
	}

	public String updateProduct(Product product, MultipartFile pic, ModelMap map) throws IOException {
		byte[] picture = new byte[pic.getInputStream().available()];
		pic.getInputStream().read(picture);
		
		if(picture.length==0)
			product.setPicture(productDaoImpl.findById(product.getId()).getPicture());
		else
		product.setPicture(picture);
		
		productDaoImpl.save(product);

		map.put("pass", "Product Updated Success");
		return fetchProducts(map);
	}

}
