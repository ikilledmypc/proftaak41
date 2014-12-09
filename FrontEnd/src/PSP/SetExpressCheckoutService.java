/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package PSP;

import java.util.logging.Logger;

/**
 *
 * @author Mr. Jin
 */
public class SetExpressCheckoutService{
/*
	private static Logger log = Logger.getLogger(SetExpressCheckoutService.class);

	public static void main(String[] args){
		SetExpressCheckoutService setExpressCheckoutService = new SetExpressCheckoutService();

		//the parameters for the service
		Long userId = 5l;
		String amount = "25";
		String returnURL = "http://localhost:8080/integratingstuff-paypal/return_after_payment.xhtml";
		String cancelURL = "http://localhost:8080/integratingstuff-paypal/cancel_payment.xhtml";
		PaymentActionCodeType paymentAction = PaymentActionCodeType.Sale;
		CurrencyCodeType currencyCode = CurrencyCodeType.EUR;

		try {
			//calling the service, setting up the checkoutpage
			String token = setExpressCheckoutService.setExpressCheckout(userId, amount, currencyCode, returnURL,cancelURL,paymentAction);
			log.info("Url to redirect to: https://www.sandbox.paypal.com/webscr?cmd=_express-checkout&useraction=commit&token=" + token);
		} catch (PayPalException e) {
			log.error(e);
		}
	}

	public String setExpressCheckout(Long userId, String paymentAmount,
			CurrencyCodeType currencyCodeType, String returnURL, String cancelURL,
				PaymentActionCodeType paymentAction) throws PayPalException{
		CallerServices caller = new CallerServices();

		//construct and set the profile, these are the credentials we establish as "the shop" with Paypal
		APIProfile profile = ProfileFactory.createSignatureAPIProfile();
		profile.setAPIUsername("sdk-three_api1.sdk.com");
		profile.setAPIPassword("QFZCWN5HZM8VBG7Q");
		profile.setSignature("AVGidzoSQiGWu.lGj3z15HLczXaaAcK6imHawrjefqgclVwBe8imgCHZ");
		profile.setEnvironment("sandbox");
		caller.setAPIProfile(profile);

		//construct the request
		SetExpressCheckoutRequestType pprequest = new SetExpressCheckoutRequestType();
		pprequest.setVersion("63.0");

		//construct the details for the request
		SetExpressCheckoutRequestDetailsType details = new SetExpressCheckoutRequestDetailsType();

		PaymentDetailsType paymentDetails = new PaymentDetailsType();
		paymentDetails.setOrderDescription("Integrating Stuff Test Order");
		paymentDetails.setInvoiceID("INVOICE-" + Math.random());
		BasicAmountType orderTotal = new BasicAmountType(paymentAmount);
		orderTotal.setCurrencyID(currencyCodeType);
		paymentDetails.setOrderTotal(orderTotal);
		paymentDetails.setPaymentAction(paymentAction);
		details.setPaymentDetails(new PaymentDetailsType[]{paymentDetails});

		details.setReturnURL(returnURL);
		details.setCancelURL(cancelURL);
		details.setCustom(userId.toString());

		//set the details on the request
		pprequest.setSetExpressCheckoutRequestDetails(details);

		//call the actual webservice, passing the constructed request
		SetExpressCheckoutResponseType ppresponse = (SetExpressCheckoutResponseType) caller.call("SetExpressCheckout", pprequest);

		//get the token from the response
		return ppresponse.getToken();
	}
    */
}