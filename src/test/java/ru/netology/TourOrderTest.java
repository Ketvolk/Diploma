package ru.netology;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import ru.netology.data.DataHelper;
import ru.netology.data.SQLHelper;
import ru.netology.page.OrderPage;
import ru.netology.page.PaymentPage;

import static com.codeborne.selenide.Selenide.*;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class TourOrderTest {


    @BeforeEach
    void setup() {
        open("http://localhost:8080");
        SQLHelper.clearDB();
    }


    //By payment
    @Test
    void ShouldPayIfCardIsActive() {
        OrderPage orderPage = new OrderPage();
        orderPage.pay();
        var paymentPage = orderPage.pay();
        paymentPage.fillCardNumber(DataHelper.getValidActiveCard());
        paymentPage.fillMonth(DataHelper.generateValidMonth());
        paymentPage.fillYear(DataHelper.generateValidYear());
        paymentPage.fillHolder(DataHelper.generateValidHolderInEn());
        paymentPage.fillCVC(DataHelper.generateValidCVCCode());
        paymentPage.continueClick();
        paymentPage.notificationIfSuccess();
    }

    @Test
    void ShouldNotPayIfCardIsInactive() {
        OrderPage orderPage = new OrderPage();
        orderPage.pay();
        var paymentPage = orderPage.pay();
        paymentPage.fillCardNumber(DataHelper.getValidInactiveCard());
        paymentPage.fillMonth(DataHelper.generateValidMonth());
        paymentPage.fillYear(DataHelper.generateValidYear());
        paymentPage.fillHolder(DataHelper.generateValidHolderInEn());
        paymentPage.fillCVC(DataHelper.generateValidCVCCode());
        paymentPage.continueClick();
        paymentPage.notificationIfFailure();
    }

    @Test
    void ShouldMessageIfCardNumberIsNullByPayment() {
        OrderPage orderPage = new OrderPage();
        orderPage.pay();
        var paymentPage = orderPage.pay();
        paymentPage.fillMonth(DataHelper.generateValidMonth());
        paymentPage.fillYear(DataHelper.generateValidYear());
        paymentPage.fillHolder(DataHelper.generateValidHolderInEn());
        paymentPage.fillCVC(DataHelper.generateValidCVCCode());
        paymentPage.continueClick();
        paymentPage.messageIfNull();
    }

    @Test
    void ShouldMessageIfMonthIsNullByPayment() {
        OrderPage orderPage = new OrderPage();
        orderPage.pay();
        var paymentPage = orderPage.pay();
        paymentPage.fillCardNumber(DataHelper.getValidActiveCard());
        paymentPage.fillYear(DataHelper.generateValidYear());
        paymentPage.fillHolder(DataHelper.generateValidHolderInEn());
        paymentPage.fillCVC(DataHelper.generateValidCVCCode());
        paymentPage.continueClick();
        paymentPage.messageIfNull();
    }

    @Test
    void ShouldMessageIfYearIsNullByPayment() {
        OrderPage orderPage = new OrderPage();
        orderPage.pay();
        var paymentPage = orderPage.pay();
        paymentPage.fillCardNumber(DataHelper.getValidActiveCard());
        paymentPage.fillMonth(DataHelper.generateValidMonth());
        paymentPage.fillHolder(DataHelper.generateValidHolderInEn());
        paymentPage.fillCVC(DataHelper.generateValidCVCCode());
        paymentPage.continueClick();
        paymentPage.messageIfNull();
    }

    @Test
    void ShouldMessageIfHolderIsNullByPayment() {
        OrderPage orderPage = new OrderPage();
        orderPage.pay();
        var paymentPage = orderPage.pay();
        paymentPage.fillCardNumber(DataHelper.getValidActiveCard());
        paymentPage.fillMonth(DataHelper.generateValidMonth());
        paymentPage.fillYear(DataHelper.generateValidYear());
        paymentPage.fillCVC(DataHelper.generateValidCVCCode());
        paymentPage.continueClick();
        paymentPage.messageIfNull();
    }

    @Test
    void ShouldMessageIfCVCIsNullByPayment() {
        OrderPage orderPage = new OrderPage();
        orderPage.pay();
        var paymentPage = orderPage.pay();
        paymentPage.fillCardNumber(DataHelper.getValidActiveCard());
        paymentPage.fillMonth(DataHelper.generateValidMonth());
        paymentPage.fillYear(DataHelper.generateValidYear());
        paymentPage.fillHolder(DataHelper.generateValidHolderInEn());
        paymentPage.continueClick();
        paymentPage.messageIfNull();
    }

    @Test
    void ShouldMessageIfCardNumberIsShortByPayment() {
        OrderPage orderPage = new OrderPage();
        orderPage.pay();
        var paymentPage = orderPage.pay();
        paymentPage.fillCardNumber(DataHelper.getShortCardNumber());
        paymentPage.fillMonth(DataHelper.generateValidMonth());
        paymentPage.fillYear(DataHelper.generateValidYear());
        paymentPage.fillHolder(DataHelper.generateValidHolderInEn());
        paymentPage.fillCVC(DataHelper.generateValidCVCCode());
        paymentPage.continueClick();
        paymentPage.messageIfIncorrect();
    }

    @Test
    void ShouldMessageIfCardNumberIsLongByPayment() {
        OrderPage orderPage = new OrderPage();
        orderPage.pay();
        var paymentPage = orderPage.pay();
        paymentPage.fillCardNumber(DataHelper.getLongCardNumber());
        paymentPage.fillMonth(DataHelper.generateValidMonth());
        paymentPage.fillYear(DataHelper.generateValidYear());
        paymentPage.fillHolder(DataHelper.generateValidHolderInEn());
        paymentPage.fillCVC(DataHelper.generateValidCVCCode());
        paymentPage.continueClick();
        paymentPage.notificationIfSuccess();
    }

    @Test
    void ShouldMessageIfCardNumberIsInvalidByPayment() {
        OrderPage orderPage = new OrderPage();
        orderPage.pay();
        var paymentPage = orderPage.pay();
        paymentPage.fillCardNumber(DataHelper.getInvalidCardNumber());
        paymentPage.fillMonth(DataHelper.generateValidMonth());
        paymentPage.fillYear(DataHelper.generateValidYear());
        paymentPage.fillHolder(DataHelper.generateValidHolderInEn());
        paymentPage.fillCVC(DataHelper.generateValidCVCCode());
        paymentPage.continueClick();
        paymentPage.notificationIfFailure();
    }

    @Test
    void ShouldMessageIfMonthIsZeroByPayment() {
        OrderPage orderPage = new OrderPage();
        orderPage.pay();
        var paymentPage = orderPage.pay();
        paymentPage.fillCardNumber(DataHelper.getInvalidCardNumber());
        paymentPage.fillMonth("00");
        paymentPage.fillYear(DataHelper.generateValidYear());
        paymentPage.fillHolder(DataHelper.generateValidHolderInEn());
        paymentPage.fillCVC(DataHelper.generateValidCVCCode());
        paymentPage.continueClick();
        paymentPage.messageIfPeriodIsIncorrect();
    }

    @Test
    void ShouldMessageIfMonthIsIncorrectByPayment() {
        OrderPage orderPage = new OrderPage();
        orderPage.pay();
        var paymentPage = orderPage.pay();
        paymentPage.fillCardNumber(DataHelper.getInvalidCardNumber());
        paymentPage.fillMonth(DataHelper.generateInvalidMonth());
        paymentPage.fillYear(DataHelper.generateValidYear());
        paymentPage.fillHolder(DataHelper.generateValidHolderInEn());
        paymentPage.fillCVC(DataHelper.generateValidCVCCode());
        paymentPage.continueClick();
        paymentPage.messageIfPeriodIsIncorrect();
    }

    @Test
    void ShouldMessageIfYearIsExpiredByPayment() {
        OrderPage orderPage = new OrderPage();
        orderPage.pay();
        var paymentPage = orderPage.pay();
        paymentPage.fillCardNumber(DataHelper.getInvalidCardNumber());
        paymentPage.fillMonth(DataHelper.generateValidMonth());
        paymentPage.fillYear(DataHelper.generateExpiredYear());
        paymentPage.fillHolder(DataHelper.generateValidHolderInEn());
        paymentPage.fillCVC(DataHelper.generateValidCVCCode());
        paymentPage.continueClick();
        paymentPage.messageIfExpired();
    }

    @Test
    void ShouldMessageIfYearIsInvalidByPayment() {
        OrderPage orderPage = new OrderPage();
        orderPage.pay();
        var paymentPage = orderPage.pay();
        paymentPage.fillCardNumber(DataHelper.getInvalidCardNumber());
        paymentPage.fillMonth(DataHelper.generateValidMonth());
        paymentPage.fillYear(DataHelper.generateInvalidYear());
        paymentPage.fillHolder(DataHelper.generateValidHolderInEn());
        paymentPage.fillCVC(DataHelper.generateValidCVCCode());
        paymentPage.continueClick();
        paymentPage.messageIfPeriodIsIncorrect();
    }


    @Test
    void ShouldPayIfHolderInRussian() {
        OrderPage orderPage = new OrderPage();
        orderPage.pay();
        var paymentPage = orderPage.pay();
        paymentPage.fillCardNumber(DataHelper.getValidActiveCard());
        paymentPage.fillMonth(DataHelper.generateValidMonth());
        paymentPage.fillYear(DataHelper.generateValidYear());
        paymentPage.fillHolder(DataHelper.generateValidHolderInRu());
        paymentPage.fillCVC(DataHelper.generateValidCVCCode());
        paymentPage.continueClick();
        paymentPage.notificationIfSuccess();
    }

    @Test
    void ShouldMessageIfHolderInNumbersByPayment() {
        OrderPage orderPage = new OrderPage();
        orderPage.pay();
        var paymentPage = orderPage.pay();
        paymentPage.fillCardNumber(DataHelper.getValidActiveCard());
        paymentPage.fillMonth(DataHelper.generateValidMonth());
        paymentPage.fillYear(DataHelper.generateValidYear());
        paymentPage.fillHolder(DataHelper.generateNumbers(7));
        paymentPage.fillCVC(DataHelper.generateValidCVCCode());
        paymentPage.continueClick();
        paymentPage.messageIfIncorrect();
    }

    @Test
    void ShouldMessageIfHolderInSymbolsByPayment() {
        OrderPage orderPage = new OrderPage();
        orderPage.pay();
        var paymentPage = orderPage.pay();
        paymentPage.fillCardNumber(DataHelper.getValidActiveCard());
        paymentPage.fillMonth(DataHelper.generateValidMonth());
        paymentPage.fillYear(DataHelper.generateValidYear());
        paymentPage.fillHolder(DataHelper.generateSymbol());
        paymentPage.fillCVC(DataHelper.generateValidCVCCode());
        paymentPage.continueClick();
        paymentPage.messageIfIncorrect();
    }

    @Test
    void ShouldCutIfHolderIsLongByPayment() {
        OrderPage orderPage = new OrderPage();
        orderPage.pay();
        var paymentPage = orderPage.pay();
        String holder = DataHelper.generateStringInEn(21);
        paymentPage.fillCardNumber(DataHelper.getValidActiveCard());
        paymentPage.fillMonth(DataHelper.generateValidMonth());
        paymentPage.fillYear(DataHelper.generateValidYear());
        paymentPage.fillHolder(holder);
        paymentPage.fillCVC(DataHelper.generateValidCVCCode());
        paymentPage.continueClick();
        paymentPage.notificationIfSuccess();
        assertEquals(holder.substring(0, 20), paymentPage.getFieldValue(3));
    }

    @Test
    void ShouldMessageIfCVCByLettersRuByPayment() {
        OrderPage orderPage = new OrderPage();
        orderPage.pay();
        var paymentPage = orderPage.pay();
        paymentPage.fillCardNumber(DataHelper.getValidActiveCard());
        paymentPage.fillMonth(DataHelper.generateValidMonth());
        paymentPage.fillYear(DataHelper.generateValidYear());
        paymentPage.fillHolder(DataHelper.generateValidHolderInEn());
        paymentPage.fillCVC(DataHelper.generateStringInRu(3));
        paymentPage.continueClick();
        paymentPage.messageIfNull();
        assertEquals("", paymentPage.getFieldValue(4));
    }

    @Test
    void ShouldMessageIfCVCByLettersEnByPayment() {
        OrderPage orderPage = new OrderPage();
        orderPage.pay();
        var paymentPage = orderPage.pay();
        paymentPage.fillCardNumber(DataHelper.getValidActiveCard());
        paymentPage.fillMonth(DataHelper.generateValidMonth());
        paymentPage.fillYear(DataHelper.generateValidYear());
        paymentPage.fillHolder(DataHelper.generateValidHolderInEn());
        paymentPage.fillCVC(DataHelper.generateStringInEn(3));
        paymentPage.continueClick();
        paymentPage.messageIfNull();
        assertEquals("", paymentPage.getFieldValue(4));
    }

    @Test
    void ShouldMessageIfCVCBySymbolsByPayment() {
        OrderPage orderPage = new OrderPage();
        orderPage.pay();
        var paymentPage = orderPage.pay();
        paymentPage.fillCardNumber(DataHelper.getValidActiveCard());
        paymentPage.fillMonth(DataHelper.generateValidMonth());
        paymentPage.fillYear(DataHelper.generateValidYear());
        paymentPage.fillHolder(DataHelper.generateValidHolderInEn());
        paymentPage.fillCVC(DataHelper.generateSymbol());
        paymentPage.continueClick();
        paymentPage.messageIfNull();
        assertEquals("", paymentPage.getFieldValue(4));
    }

    @Test
    void ShouldCutIfCVCIsLongByPayment() {
        OrderPage orderPage = new OrderPage();
        orderPage.pay();
        var paymentPage = orderPage.pay();
        String cvc = DataHelper.generateNumbers(4);
        paymentPage.fillCardNumber(DataHelper.getValidActiveCard());
        paymentPage.fillMonth(DataHelper.generateValidMonth());
        paymentPage.fillYear(DataHelper.generateValidYear());
        paymentPage.fillHolder(DataHelper.generateValidHolderInEn());
        paymentPage.fillCVC(cvc);
        paymentPage.continueClick();
        paymentPage.notificationIfSuccess();
        assertEquals(cvc.substring(0, 3), paymentPage.getFieldValue(4));
    }

//By credit

    @Test
    void ShouldCreditIfCardIsActive() {
        OrderPage orderPage = new OrderPage();
        orderPage.payByCredit();
        var creditPage = orderPage.payByCredit();
        creditPage.fillCardNumber(DataHelper.getValidActiveCard());
        creditPage.fillMonth(DataHelper.generateValidMonth());
        creditPage.fillYear(DataHelper.generateValidYear());
        creditPage.fillHolder(DataHelper.generateValidHolderInEn());
        creditPage.fillCVC(DataHelper.generateValidCVCCode());
        creditPage.continueClick();
        creditPage.notificationIfSuccess();
    }

    @Test
    void ShouldNotCreditIfCardIsInactive() {
        OrderPage orderPage = new OrderPage();
        orderPage.payByCredit();
        var creditPage = orderPage.payByCredit();
        creditPage.fillCardNumber(DataHelper.getValidInactiveCard());
        creditPage.fillMonth(DataHelper.generateValidMonth());
        creditPage.fillYear(DataHelper.generateValidYear());
        creditPage.fillHolder(DataHelper.generateValidHolderInEn());
        creditPage.fillCVC(DataHelper.generateValidCVCCode());
        creditPage.continueClick();
        creditPage.notificationIfFailure();
    }

    @Test
    void ShouldMessageIfCardNumberIsNullByCredit() {
        OrderPage orderPage = new OrderPage();
        orderPage.payByCredit();
        var creditPage = orderPage.payByCredit();
        creditPage.fillMonth(DataHelper.generateValidMonth());
        creditPage.fillYear(DataHelper.generateValidYear());
        creditPage.fillHolder(DataHelper.generateValidHolderInEn());
        creditPage.fillCVC(DataHelper.generateValidCVCCode());
        creditPage.continueClick();
        creditPage.messageIfNull();
    }

    @Test
    void ShouldMessageIfMonthIsNullByCredit() {
        OrderPage orderPage = new OrderPage();
        orderPage.payByCredit();
        var creditPage = orderPage.payByCredit();
        creditPage.fillCardNumber(DataHelper.getValidActiveCard());
        creditPage.fillYear(DataHelper.generateValidYear());
        creditPage.fillHolder(DataHelper.generateValidHolderInEn());
        creditPage.fillCVC(DataHelper.generateValidCVCCode());
        creditPage.continueClick();
        creditPage.messageIfNull();
    }

    @Test
    void ShouldMessageIfYearIsNullByCredit() {
        OrderPage orderPage = new OrderPage();
        orderPage.payByCredit();
        var creditPage = orderPage.payByCredit();
        creditPage.fillCardNumber(DataHelper.getValidActiveCard());
        creditPage.fillMonth(DataHelper.generateValidMonth());
        creditPage.fillHolder(DataHelper.generateValidHolderInEn());
        creditPage.fillCVC(DataHelper.generateValidCVCCode());
        creditPage.continueClick();
        creditPage.messageIfNull();
    }

    @Test
    void ShouldMessageIfHolderIsNullByCredit() {
        OrderPage orderPage = new OrderPage();
        orderPage.payByCredit();
        var creditPage = orderPage.payByCredit();
        creditPage.fillCardNumber(DataHelper.getValidActiveCard());
        creditPage.fillMonth(DataHelper.generateValidMonth());
        creditPage.fillYear(DataHelper.generateValidYear());
        creditPage.fillCVC(DataHelper.generateValidCVCCode());
        creditPage.continueClick();
        creditPage.messageIfNull();
    }

    @Test
    void ShouldMessageIfCVCIsNullByCredit() {
        OrderPage orderPage = new OrderPage();
        orderPage.payByCredit();
        var creditPage = orderPage.payByCredit();
        creditPage.fillCardNumber(DataHelper.getValidActiveCard());
        creditPage.fillMonth(DataHelper.generateValidMonth());
        creditPage.fillYear(DataHelper.generateValidYear());
        creditPage.fillHolder(DataHelper.generateValidHolderInEn());
        creditPage.continueClick();
        creditPage.messageIfNull();
    }

    @Test
    void ShouldMessageIfCardNumberIsShortByCredit() {
        OrderPage orderPage = new OrderPage();
        orderPage.payByCredit();
        var creditPage = orderPage.payByCredit();
        creditPage.fillCardNumber(DataHelper.getShortCardNumber());
        creditPage.fillMonth(DataHelper.generateValidMonth());
        creditPage.fillYear(DataHelper.generateValidYear());
        creditPage.fillHolder(DataHelper.generateValidHolderInEn());
        creditPage.fillCVC(DataHelper.generateValidCVCCode());
        creditPage.continueClick();
        creditPage.messageIfIncorrect();
    }

    @Test
    void ShouldMessageIfCardNumberIsLongByCredit() {
        OrderPage orderPage = new OrderPage();
        orderPage.payByCredit();
        var creditPage = orderPage.payByCredit();
        creditPage.fillCardNumber(DataHelper.getLongCardNumber());
        creditPage.fillMonth(DataHelper.generateValidMonth());
        creditPage.fillYear(DataHelper.generateValidYear());
        creditPage.fillHolder(DataHelper.generateValidHolderInEn());
        creditPage.fillCVC(DataHelper.generateValidCVCCode());
        creditPage.continueClick();
        creditPage.notificationIfSuccess();
    }

    @Test
    void ShouldMessageIfCardNumberIsInvalidByCredit() {
        OrderPage orderPage = new OrderPage();
        orderPage.payByCredit();
        var creditPage = orderPage.payByCredit();
        creditPage.fillCardNumber(DataHelper.getInvalidCardNumber());
        creditPage.fillMonth(DataHelper.generateValidMonth());
        creditPage.fillYear(DataHelper.generateValidYear());
        creditPage.fillHolder(DataHelper.generateValidHolderInEn());
        creditPage.fillCVC(DataHelper.generateValidCVCCode());
        creditPage.continueClick();
        creditPage.notificationIfFailure();
    }

    @Test
    void ShouldMessageIfMonthIsZeroByCredit() {
        OrderPage orderPage = new OrderPage();
        orderPage.payByCredit();
        var creditPage = orderPage.payByCredit();
        creditPage.fillCardNumber(DataHelper.getInvalidCardNumber());
        creditPage.fillMonth("00");
        creditPage.fillYear(DataHelper.generateValidYear());
        creditPage.fillHolder(DataHelper.generateValidHolderInEn());
        creditPage.fillCVC(DataHelper.generateValidCVCCode());
        creditPage.continueClick();
        creditPage.messageIfPeriodIsIncorrect();
    }

    @Test
    void ShouldMessageIfMonthIsIncorrectByCredit() {
        OrderPage orderPage = new OrderPage();
        orderPage.payByCredit();
        var creditPage = orderPage.payByCredit();
        creditPage.fillCardNumber(DataHelper.getInvalidCardNumber());
        creditPage.fillMonth(DataHelper.generateInvalidMonth());
        creditPage.fillYear(DataHelper.generateValidYear());
        creditPage.fillHolder(DataHelper.generateValidHolderInEn());
        creditPage.fillCVC(DataHelper.generateValidCVCCode());
        creditPage.continueClick();
        creditPage.messageIfPeriodIsIncorrect();
    }

    @Test
    void ShouldMessageIfYearIsExpiredByCredit() {
        OrderPage orderPage = new OrderPage();
        orderPage.payByCredit();
        var creditPage = orderPage.payByCredit();
        creditPage.fillCardNumber(DataHelper.getInvalidCardNumber());
        creditPage.fillMonth(DataHelper.generateValidMonth());
        creditPage.fillYear(DataHelper.generateExpiredYear());
        creditPage.fillHolder(DataHelper.generateValidHolderInEn());
        creditPage.fillCVC(DataHelper.generateValidCVCCode());
        creditPage.continueClick();
        creditPage.messageIfExpired();
    }

    @Test
    void ShouldMessageIfYearIsInvalidByCredit() {
        OrderPage orderPage = new OrderPage();
        orderPage.payByCredit();
        var creditPage = orderPage.payByCredit();
        creditPage.fillCardNumber(DataHelper.getInvalidCardNumber());
        creditPage.fillMonth(DataHelper.generateValidMonth());
        creditPage.fillYear(DataHelper.generateInvalidYear());
        creditPage.fillHolder(DataHelper.generateValidHolderInEn());
        creditPage.fillCVC(DataHelper.generateValidCVCCode());
        creditPage.continueClick();
        creditPage.messageIfPeriodIsIncorrect();
    }


    @Test
    void ShouldPayIfHolderInRussianByCredit() {
        OrderPage orderPage = new OrderPage();
        orderPage.payByCredit();
        var creditPage = orderPage.payByCredit();
        creditPage.fillCardNumber(DataHelper.getValidActiveCard());
        creditPage.fillMonth(DataHelper.generateValidMonth());
        creditPage.fillYear(DataHelper.generateValidYear());
        creditPage.fillHolder(DataHelper.generateValidHolderInRu());
        creditPage.fillCVC(DataHelper.generateValidCVCCode());
        creditPage.continueClick();
        creditPage.notificationIfSuccess();
    }

    @Test
    void ShouldMessageIfHolderInNumbersByCredit() {
        OrderPage orderPage = new OrderPage();
        orderPage.payByCredit();
        var creditPage = orderPage.payByCredit();
        creditPage.fillCardNumber(DataHelper.getValidActiveCard());
        creditPage.fillMonth(DataHelper.generateValidMonth());
        creditPage.fillYear(DataHelper.generateValidYear());
        creditPage.fillHolder(DataHelper.generateNumbers(7));
        creditPage.fillCVC(DataHelper.generateValidCVCCode());
        creditPage.continueClick();
        creditPage.messageIfIncorrect();
    }

    @Test
    void ShouldMessageIfHolderInSymbolsByCredit() {
        OrderPage orderPage = new OrderPage();
        orderPage.payByCredit();
        var creditPage = orderPage.payByCredit();
        creditPage.fillCardNumber(DataHelper.getValidActiveCard());
        creditPage.fillMonth(DataHelper.generateValidMonth());
        creditPage.fillYear(DataHelper.generateValidYear());
        creditPage.fillHolder(DataHelper.generateSymbol());
        creditPage.fillCVC(DataHelper.generateValidCVCCode());
        creditPage.continueClick();
        creditPage.messageIfIncorrect();
    }

    @Test
    void ShouldCutIfHolderIsLongByCredit() {
        OrderPage orderPage = new OrderPage();
        orderPage.payByCredit();
        var creditPage = orderPage.payByCredit();
        String holder = DataHelper.generateStringInEn(21);
        creditPage.fillCardNumber(DataHelper.getValidActiveCard());
        creditPage.fillMonth(DataHelper.generateValidMonth());
        creditPage.fillYear(DataHelper.generateValidYear());
        creditPage.fillHolder(holder);
        creditPage.fillCVC(DataHelper.generateValidCVCCode());
        creditPage.continueClick();
        creditPage.notificationIfSuccess();
        assertEquals(holder.substring(0, 20), creditPage.getFieldValue(3));
    }

    @Test
    void ShouldMessageIfCVCByLettersRuByCredit() {
        OrderPage orderPage = new OrderPage();
        orderPage.payByCredit();
        var creditPage = orderPage.payByCredit();
        creditPage.fillCardNumber(DataHelper.getValidActiveCard());
        creditPage.fillMonth(DataHelper.generateValidMonth());
        creditPage.fillYear(DataHelper.generateValidYear());
        creditPage.fillHolder(DataHelper.generateValidHolderInEn());
        creditPage.fillCVC(DataHelper.generateStringInRu(3));
        creditPage.continueClick();
        creditPage.messageIfNull();
        assertEquals("", creditPage.getFieldValue(4));
    }

    @Test
    void ShouldMessageIfCVCByLettersEnByCredit() {
        OrderPage orderPage = new OrderPage();
        orderPage.payByCredit();
        var creditPage = orderPage.payByCredit();
        creditPage.fillCardNumber(DataHelper.getValidActiveCard());
        creditPage.fillMonth(DataHelper.generateValidMonth());
        creditPage.fillYear(DataHelper.generateValidYear());
        creditPage.fillHolder(DataHelper.generateValidHolderInEn());
        creditPage.fillCVC(DataHelper.generateStringInEn(3));
        creditPage.continueClick();
        creditPage.messageIfNull();
        assertEquals("", creditPage.getFieldValue(4));
    }

    @Test
    void ShouldMessageIfCVCBySymbolsByCredit() {
        OrderPage orderPage = new OrderPage();
        orderPage.payByCredit();
        var creditPage = orderPage.payByCredit();
        creditPage.fillCardNumber(DataHelper.getValidActiveCard());
        creditPage.fillMonth(DataHelper.generateValidMonth());
        creditPage.fillYear(DataHelper.generateValidYear());
        creditPage.fillHolder(DataHelper.generateValidHolderInEn());
        creditPage.fillCVC(DataHelper.generateSymbol());
        creditPage.continueClick();
        creditPage.messageIfNull();
        assertEquals("", creditPage.getFieldValue(4));
    }

    @Test
    void ShouldCutIfCVCIsLongByCredit() {
        OrderPage orderPage = new OrderPage();
        orderPage.payByCredit();
        var creditPage = orderPage.payByCredit();
        String cvc = DataHelper.generateNumbers(4);
        creditPage.fillCardNumber(DataHelper.getValidActiveCard());
        creditPage.fillMonth(DataHelper.generateValidMonth());
        creditPage.fillYear(DataHelper.generateValidYear());
        creditPage.fillHolder(DataHelper.generateValidHolderInEn());
        creditPage.fillCVC(cvc);
        creditPage.continueClick();
        creditPage.notificationIfSuccess();
        assertEquals(cvc.substring(0, 3), creditPage.getFieldValue(4));
    }


}
