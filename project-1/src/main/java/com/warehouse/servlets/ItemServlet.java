package com.warehouse.servlets;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.warehouse.daos.ItemDAO;
import com.warehouse.daos.MySQLItemDAOImpl;
import com.warehouse.models.Item;

@WebServlet(urlPatterns="/item/*")
public class ItemServlet extends HttpServlet{

	private static final long serialVersionUID = 6604739040895145458L;

	//Instantiate DAO
	ItemDAO dao = new MySQLItemDAOImpl();
	//Instantiate ObjectMapper
	ObjectMapper mapper = new ObjectMapper();
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		String request = req.getPathInfo();
		
		String[] itemArray = request.split("/");
		
		String result = itemArray[1]; //Check if name(string) or id(int) passed (try/catch)
		//
		int id = Integer.parseInt(result);
		
		Item item = dao.findById(id);
		//Tells Postman that file is JSON
		resp.setContentType("application/json");
		
		resp.getWriter().print(mapper.writeValueAsString(item));
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
	}
	
	@Override
	protected void doPut(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
	}
}
