
package page;

import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;
import lombok.val;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$$;

public class DashboardPage {
    private SelenideElement heading = $("[data-test-id=dashboard]");
    private ElementsCollection cards = $$(".list__item");
    private SelenideElement transferButton1 = $("[data-test-id = '92df3f1c-a033-48e6-8390-206f6b1f56c0'] [class=button__text]");
    private SelenideElement transferButton2 = $("[data-test-id= '0f3f5c2a-249e-4c3d-8287-09f7a039391d'] [class=button__text]");
    private final String balanceStart = "баланс: ";
    private final String balanceFinish = " р.";

    public DashboardPage() {
        heading.shouldBe(visible);
    }

    private int extractBalance(String text) {
        var start = text.indexOf(balanceStart);
        var finish = text.indexOf(balanceFinish);
        var value = text.substring(start + balanceStart.length(), finish);
        return Integer.parseInt(value);
    }

    public int getCardBalance(String cardNumber) {
        return extractBalance(cards.find(text(cardNumber.substring(15))).getText());
    }

    public MoneyTransferPage depositToFirstCard() {
        transferButton1.click();
        return new MoneyTransferPage();
    }

    public MoneyTransferPage depositToSecondCard() {
        transferButton2.click();
        return new MoneyTransferPage();
    }
}
