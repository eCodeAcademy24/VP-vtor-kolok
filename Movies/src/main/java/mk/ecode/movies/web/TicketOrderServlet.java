package mk.ecode.movies.web;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import mk.ecode.movies.model.TicketOrder;
import mk.ecode.movies.service.TicketOrderService;
import org.thymeleaf.context.WebContext;
import org.thymeleaf.spring6.SpringTemplateEngine;
import org.thymeleaf.web.IWebExchange;
import org.thymeleaf.web.servlet.JakartaServletWebApplication;

import java.io.IOException;

@WebServlet(name = "ticket-order", urlPatterns = "/ticketOrder")
public class TicketOrderServlet extends HttpServlet {

    private final TicketOrderService ticketOrderService;
    private final SpringTemplateEngine springTemplateEngine;

    public TicketOrderServlet(TicketOrderService ticketOrderService, SpringTemplateEngine springTemplateEngine) {
        this.ticketOrderService = ticketOrderService;
        this.springTemplateEngine = springTemplateEngine;
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String movieTitle = req.getParameter("movieTitle");
        String numTickets = req.getParameter("numTickets");
        String address = req.getLocalAddr();

        TicketOrder ticketOrder = ticketOrderService.placeOrder(movieTitle, "Test", address, Integer.parseInt(numTickets));

        IWebExchange webExchange = JakartaServletWebApplication
                .buildApplication(getServletContext())
                .buildExchange(req, resp);

        WebContext webContext = new WebContext(webExchange);
        webContext.setVariable("ticketOrder", ticketOrder);

        springTemplateEngine.process(
                "orderConfirmation.html",
                webContext,
                resp.getWriter()
        );
    }
}
