package com.warehouse.servlets;

import java.io.IOException;
import java.io.InputStream;
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

@WebServlet(urlPatterns = { "/item/add/", "/item/get/*", "/item/delete/", "/item/update/" })
public class ItemServlet extends HttpServlet {

	private static final long serialVersionUID = 6604739040895145458L;

	// Instantiate DAO
	ItemDAO dao = new MySQLItemDAOImpl();
	// Instantiate ObjectMapper
	ObjectMapper mapper = new ObjectMapper();

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// Read Get Request	
		String request = req.getPathInfo(); 	
		String[] itemArray = request.split("/"); // Take everything after /item/add/		
		String result = itemArray[1]; // Separate the useful part of array
		
		try { // Check if name(string) or id(int) passed (try/catch)
			int id = Integer.parseInt(result);

			Item item = dao.findById(id);
			if (item != null) {
				
				resp.setContentType("application/json"); // Tells Postman that file is JSON
				resp.getWriter().print(mapper.writeValueAsString(item)); // Use ObjectMapper
			} else {
				resp.setStatus(404); // Sets response as not found error
				resp.getWriter().print(mapper.writeValueAsString(new NotFound("No item with the provided ID found."))); // Use ObjectMapper
			}
		} catch (NumberFormatException e) {

			Item item = dao.findByName(result);
			if (item != null) {				
				resp.setContentType("application/json"); // Tells Postman that file is JSON
				resp.getWriter().print(mapper.writeValueAsString(item)); // Use ObjectMapper
			} else {
				resp.setStatus(404); // Sets response as not found error
				resp.getWriter()
						.print(mapper.writeValueAsString(new NotFound("No item with the provided name found."))); // Use ObjectMapper
			}
		}
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// Read Post Request
		InputStream request = req.getInputStream();
		Item item = mapper.readValue(request, Item.class);
		// Handle duplicate entries
		item = dao.createItem(item);
		if (item != null) {
			resp.setContentType("application/json"); // Tells Postman that file is JSON
			resp.getWriter().print(mapper.writeValueAsString(item)); // Use ObjectMapper
			resp.setStatus(201); // Sets response as successful creation of object
		} else {
			resp.setStatus(400); // Sets response as invalid request
			resp.getWriter().print(mapper.writeValueAsString(new NotFound("Error: system could not add new item. (Check for duplicate entries)"))); // Use ObjectMapper
		}

	}

	@Override
	protected void doPut(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// Read Put Request
		InputStream request = req.getInputStream();
		Item item = mapper.readValue(request, Item.class);
		// Handle duplicate entries
		dao.updateItemName(item);
		if (item != null) {
			resp.setContentType("application/json"); // Tells Postman that file is JSON
			resp.getWriter().print(mapper.writeValueAsString(item)); // Use ObjectMapper
			resp.setStatus(200); // Sets response as successful
		} else {
			resp.setStatus(400); // Sets response as invalid request
			resp.getWriter()
					.print(mapper.writeValueAsString(new NotFound("Error: system could not change item name."))); // Use ObjectMapper
		}
	}

	@Override
	protected void doDelete(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// Read Delete Request
		InputStream request = req.getInputStream();
		Item item = mapper.readValue(request, Item.class); // Use ObjectMapper
		// Handle duplicate entries
		dao.deleteItem(item);
		if (item != null) {
			resp.setStatus(200); // Sets response as successful
		} else {
			resp.setStatus(400); // Sets response as invalid request
			resp.getWriter().print(mapper.writeValueAsString(new NotFound("Error: system could not delete item."))); // Use ObjectMapper
		}
	}
}
