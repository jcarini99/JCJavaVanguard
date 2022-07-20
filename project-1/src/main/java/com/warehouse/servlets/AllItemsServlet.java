package com.warehouse.servlets;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.warehouse.daos.ItemDAO;
import com.warehouse.daos.MySQLItemDAOImpl;
import com.warehouse.models.Item;
import com.warehouse.models.NotFound;

@WebServlet(urlPatterns = "/item/get")
public class AllItemsServlet extends HttpServlet {

	private static final long serialVersionUID = 5553344532863879747L;
	// Instantiate DAO
	ItemDAO dao = new MySQLItemDAOImpl();
	// Instantiate ObjectMapper
	ObjectMapper mapper = new ObjectMapper();

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		List<Item> items = dao.findAllItems();
		if (items != null) {
			// Tells Postman that file is JSON
			resp.setContentType("application/json");

			resp.getWriter().print(mapper.writeValueAsString(items));
		} else {
			resp.setStatus(404); // Sets response as not found error
			resp.getWriter().print(mapper.writeValueAsString(new NotFound("No items found.")));
		}

	}
}
