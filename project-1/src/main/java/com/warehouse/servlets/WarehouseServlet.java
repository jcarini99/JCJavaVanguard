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

@WebServlet(urlPatterns = "/warehouse/*")
public class WarehouseServlet extends HttpServlet {

	private static final long serialVersionUID = 8697948623649022156L;

	// Instantiate DAO
	WarehouseDAO dao = new MySQLWarehouseDAOImpl();
	// Instantiate ObjectMapper
	ObjectMapper mapper = new ObjectMapper();

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String request = req.getPathInfo();

		String[] warehouseArray = request.split("/");

		String result = warehouseArray[1]; // Check if name(string) or id(int) passed (try/catch)
		try {
			int id = Integer.parseInt(result);

			Warehouse warehouse = dao.findById(id);
			if (warehouse != null) {
				// Tells Postman that file is JSON
				resp.setContentType("application/json");

				resp.getWriter().print(mapper.writeValueAsString(warehouse));
			} else {
				resp.setStatus(404);
				resp.getWriter().print(mapper.writeValueAsString(new NotFound("No warehouse with the provided ID found.")));
			}
		} catch (NumberFormatException e) {

			Warehouse warehouse = dao.findByName(result);
			if (warehouse != null) {
				// Tells Postman that file is JSON
				resp.setContentType("application/json");

				resp.getWriter().print(mapper.writeValueAsString(warehouse));
			} else {
				resp.setStatus(404);
				resp.getWriter().print(mapper.writeValueAsString(new NotFound("No warehouse with the provided name found.")));
			}

		} catch (Exception e) {
			List<Warehouse> warehouses = dao.findAllWarehouses();
			if (warehouses != null) {
				// Tells Postman that file is JSON
				resp.setContentType("application/json");

				resp.getWriter().print(mapper.writeValueAsString(warehouses));
			} else {
				resp.setStatus(404);
				resp.getWriter().print(mapper.writeValueAsString(new NotFound("No warehouses found.")));
			}
		}
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		InputStream request = req.getInputStream();
		Warehouse warehouse = mapper.readValue(request, Warehouse.class);
		// Handle duplicate entries
		warehouse = dao.createWarehouseItem(warehouse);
		if (warehouse != null) {
			resp.setContentType("application/json");
			resp.getWriter().print(mapper.writeValueAsString(warehouse));
			resp.setStatus(201);
		} else {
			resp.setStatus(400);
			resp.getWriter().print(mapper.writeValueAsString(new NotFound("Error: system could not add new warehouse and/or item.")));
		}
	}
	
	@Override
	protected void doPut(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// TODO Auto-generated method stub
		super.doPut(req, resp);
	}
	
	@Override
	protected void doDelete(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// TODO Auto-generated method stub
		super.doDelete(req, resp);
	}
}
