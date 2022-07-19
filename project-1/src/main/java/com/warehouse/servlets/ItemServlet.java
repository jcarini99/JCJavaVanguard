package com.warehouse.servlets;

import java.io.IOException;
import java.io.InputStream;
import java.sql.SQLException;
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

@WebServlet(urlPatterns = "/item/*")
public class ItemServlet extends HttpServlet {

	private static final long serialVersionUID = 6604739040895145458L;

	// Instantiate DAO
	ItemDAO dao = new MySQLItemDAOImpl();
	// Instantiate ObjectMapper
	ObjectMapper mapper = new ObjectMapper();

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		String request = req.getPathInfo();

		String[] itemArray = request.split("/");

		String result = itemArray[1]; // Check if name(string) or id(int) passed (try/catch)
		try {
			int id = Integer.parseInt(result);

			Item item = dao.findById(id);
			if (item != null) {
				// Tells Postman that file is JSON
				resp.setContentType("application/json");

				resp.getWriter().print(mapper.writeValueAsString(item));
			} else {
				resp.setStatus(404);
				resp.getWriter().print(mapper.writeValueAsString(new NotFound("No item with the provided ID found.")));
			}
		} catch (NumberFormatException e) {

			Item item = dao.findByName(result);
			if (item != null) {
				// Tells Postman that file is JSON
				resp.setContentType("application/json");

				resp.getWriter().print(mapper.writeValueAsString(item));
			} else {
				resp.setStatus(404);
				resp.getWriter().print(mapper.writeValueAsString(new NotFound("No item with the provided name found.")));
			}

		} catch (Exception e) {
			List<Item> items = dao.findAllItems();
			if (items != null) {
				// Tells Postman that file is JSON
				resp.setContentType("application/json");

				resp.getWriter().print(mapper.writeValueAsString(items));
			} else {
				resp.setStatus(404);
				resp.getWriter().print(mapper.writeValueAsString(new NotFound("No items found.")));
			}
		}
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		InputStream request = req.getInputStream();
		Item item = mapper.readValue(request, Item.class);
		// Handle duplicate entries
		item = dao.createItem(item);
		if (item != null) {
			resp.setContentType("application/json");
			resp.getWriter().print(mapper.writeValueAsString(item));
			resp.setStatus(201);
		} else {
			resp.setStatus(400);
			resp.getWriter().print(mapper.writeValueAsString(new NotFound("Error: system could not add new item.")));
		}
		
	}

	@Override
	protected void doPut(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

	}
}
