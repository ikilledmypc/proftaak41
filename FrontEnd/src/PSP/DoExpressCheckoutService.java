/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package PSP;

/**
 *
 * @author Mr. Jin
 */
public class DoExpressCheckoutService {
/*
	private Logger log = Logger.getLogger(this.getClass());

	public boolean doExpressCheckoutService(GetExpressCheckoutDetailsResponseDetailsType response) throws PayPalException{
		CallerServices caller = new CallerServices();

		APIProfile profile = ...;

		DoExpressCheckoutPaymentRequestType pprequest = new DoExpressCheckoutPaymentRequestType();
		pprequest.setVersion("63.0");

		DoExpressCheckoutPaymentResponseType ppresponse= new DoExpressCheckoutPaymentResponseType();

		DoExpressCheckoutPaymentRequestDetailsType paymentDetailsRequestType = new DoExpressCheckoutPaymentRequestDetailsType();
		paymentDetailsRequestType.setToken(response.getToken());

		PayerInfoType payerInfo = response.getPayerInfo();
		paymentDetailsRequestType.setPayerID(payerInfo.getPayerID());

		PaymentDetailsType paymentDetails = response.getPaymentDetails(0);
		paymentDetailsRequestType.setPaymentAction(paymentDetails.getPaymentAction());

		paymentDetailsRequestType.setPaymentDetails(response.getPaymentDetails());
		pprequest.setDoExpressCheckoutPaymentRequestDetails(paymentDetailsRequestType);

		ppresponse= (DoExpressCheckoutPaymentResponseType) caller.call("DoExpressCheckoutPayment", pprequest);
		DoExpressCheckoutPaymentResponseDetailsType type = ppresponse.getDoExpressCheckoutPaymentResponseDetails();

		if (type != null){
			PaymentInfoType paymentInfo = type.getPaymentInfo(0);
			if (paymentInfo.getPaymentStatus().equals(PaymentStatusCodeType.fromString("Completed"))){
				log.info("Payment completed.");
				return true;
			}
			else {
				log.info("Payment not completed.. (" + paymentInfo.getPaymentStatus() + ")");
				return false;
			}
		}
		else {
			log.info("Problem executing DoExpressCheckoutPayment.. Maybe you tried to process an ExpressCheckout that has already been processed.");
			return false;
		}
	}
    */
}
