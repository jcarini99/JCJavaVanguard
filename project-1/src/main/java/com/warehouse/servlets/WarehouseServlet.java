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
import com.warehouse.daos.MySQLWarehouseDAOImpl;
import com.warehouse.daos.WarehouseDAO;
import com.warehouse.models.Item;
import com.warehouse.models.NotFound;
import com.warehouse.models.Warehouse;

@WebServlet(urlPatterns = { "/warehouse/add/", "/warehouse/get/*", "/warehouse/deleteWI/", "/warehouse/update/" })
public class WarehouseServlet extends HttpServlet {

	private static final long serialVersionUID = 8697948623649022156L;
	private static final int maxCapacity = 10000; // Arbitrary max capacity of 10000 storage units (all items have equal
													// unit size)
	// Instantiate DAO
	WarehouseDAO dao = new MySQLWarehouseDAOImpl();
	// Instantiate ObjectMapper
	ObjectMapper mapper = new ObjectMapper();

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// Read Get Request
		String request = req.getPathInfo();
		String[] warehouseArray = request.split("/"); // Take everything after /warehouse/add/
		String result = warehouseArray[1]; // Separate the useful part of array

		// Check if name(string) or id(int) passed (try/catch)
		try {
			int id = Integer.parseInt(result);

			List<Warehouse> warehouses = dao.findById(id);
			if (warehouses != null) {
				resp.setContentType("application/json"); // Tells Postman that file is JSON
				resp.getWriter().print(mapper.writeValueAsString(warehouses)); // Use ObjectMapper
			} else {
				resp.setStatus(404); // Sets response as not found error
				resp.getWriter()
						.print(mapper.writeValueAsString(new NotFound("No warehouse with the provided ID found."))); // Use
																														// ObjectMapper
			}
		} catch (NumberFormatException e) {

			List<Warehouse> warehouses = dao.findByName(result);
			if (warehouses != null) {
				resp.setContentType("application/json"); // Tells Postman that file is JSON
				resp.getWriter().print(mapper.writeValueAsString(warehouses)); // Use ObjectMapper
			} else {
				resp.setStatus(404); // Sets response as not found error
				resp.getWriter()
						.print(mapper.writeValueAsString(new NotFound("No warehouse with the provided name found."))); // Use
																														// ObjectMapper
			}
		}
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// Read Post Request
		InputStream request = req.getInputStream();
		Warehouse warehouse = mapper.readValue(request, Warehouse.class);
		// Check current storage capacity of warehouse by warehouse_id
		int currentCapacity = dao.findWarehouseCapacity(warehouse.getWarehouseId());
		// Calculate impending capacity after Post request
		int nextCapacity = currentCapacity + warehouse.getItemQuantity();
		// Compare impending capacity to max capacity
		if (nextCapacity <= maxCapacity) {
			warehouse = dao.createWarehouseItem(warehouse);
			if (warehouse != null) {
				resp.setContentType("application/json"); // Tells system to read as JSON
				resp.getWriter().print(mapper.writeValueAsString(warehouse)); // Use ObjectMapper
				resp.setStatus(201); // Sets response as successful creation of object
			} else {
				resp.setStatus(400); // Sets response as invalid request
				resp.getWriter().print(mapper
						.writeValueAsString(new NotFound("Error: system could not add new warehouse and/or item."))); // Use
																														// ObjectMapper
			}
		} else {
			resp.setStatus(400); // Sets response as invalid request
			resp.getWriter().print(
					mapper.writeValueAsString(new NotFound("Error: Warehouse capacity of 10,000 units reached."))); // Use
																													// ObjectMapper
		}
	}

	@Override
	protected void doPut(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// Read Put Request
		InputStream request = req.getInputStream();
		Warehouse warehouse = mapper.readValue(request, Warehouse.class); // Use ObjectMapper
		dao.updateWarehouseName(warehouse);
		// Check current storage capacity of warehouse by warehouse_id
		int currentCapacity = dao.findWarehouseCapacity(warehouse.getWarehouseId());
		// Compare current capacity to max capacity
		if (currentCapacity < maxCapacity) {
			dao.updateItemQuantity(warehouse);
		} else {
			resp.setStatus(400); // Sets response as invalid request
			resp.getWriter().print(
					mapper.writeValueAsString(new NotFound("Error: Warehouse capacity of 10,000 units reached."))); // Use
																													// ObjectMapper
		}
		if (warehouse != null) {
			resp.setContentType("application/json"); // Tells Postman that file is JSON
			resp.getWriter().print(mapper.writeValueAsString(warehouse)); // Use ObjectMapper
			resp.setStatus(200); // Sets response as successful
		}

	}

	@Override
	protected void doDelete(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// Read Delete Request
		InputStream request = req.getInputStream();
		Warehouse warehouse = mapper.readValue(request, Warehouse.class); // Use ObjectMapper
		dao.deleteWarehouseItem(warehouse);
		try {
			resp.setStatus(200); // Sets response as successful
		} catch (Exception e) {
			resp.setStatus(400); // Sets response as invalid request
			resp.getWriter()
					.print(mapper.writeValueAsString(new NotFound("Error: system could not delete warehouse."))); // Use
																													// ObjectMapper
		}

	}
}
