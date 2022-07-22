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
import com.warehouse.models.NotFound;
import com.warehouse.models.Warehouse;

@WebServlet(urlPatterns = {"/warehouse/get", "/warehouse/deleteW/"})
public class AllWarehousesServlet extends HttpServlet {

	private static final long serialVersionUID = -5208086989843635030L;
	// Instantiate DAO
	WarehouseDAO dao = new MySQLWarehouseDAOImpl();
	// Instantiate ObjectMapper
	ObjectMapper mapper = new ObjectMapper();

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		List<Warehouse> warehouses = dao.findAllWarehouses();
		if (warehouses != null) {
			// Tells Postman that file is JSON
			resp.setContentType("application/json");

			resp.getWriter().print(mapper.writeValueAsString(warehouses));
		} else {
			resp.setStatus(404); // Sets response as not found error
			resp.getWriter().print(mapper.writeValueAsString(new NotFound("No warehouses found.")));
		}

	}
	
	@Override
	protected void doDelete(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// Read Delete Request
		InputStream request = req.getInputStream();
		Warehouse warehouse = mapper.readValue(request, Warehouse.class); // Use ObjectMapper
		dao.deleteWarehouse(warehouse);
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
