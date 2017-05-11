package org.vicmusa.church.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.braintreegateway.BraintreeGateway;
import com.braintreegateway.Transaction;
import com.braintreegateway.Transaction.Status;

@Controller
@RequestMapping(value = "/donate")
public class PayPalController {
	
	private static final String PAYPAL_TOKEN = "access_token$sandbox$9m4kn6nkhrr7sqgk$4517ae46972f53f4e3c6ed426e4a9e0e";
	
	private BraintreeGateway paypalconfig = new BraintreeGateway(PAYPAL_TOKEN);
	
	private Status[] TRANSACTION_SUCCESS_STATUSES = new Status[] {
	        Transaction.Status.AUTHORIZED,
	        Transaction.Status.AUTHORIZING,
	        Transaction.Status.SETTLED,
	        Transaction.Status.SETTLEMENT_CONFIRMED,
	        Transaction.Status.SETTLEMENT_PENDING,
	        Transaction.Status.SETTLING,
	        Transaction.Status.SUBMITTED_FOR_SETTLEMENT
	     };
	
	
	 @RequestMapping(value = "/to-vicm", method = RequestMethod.GET)
	 public String checkout(Model model) {
        String clientToken = paypalconfig.clientToken().generate();
        model.addAttribute("clientToken", clientToken);

        return "paypal-checkout";
    }


}
