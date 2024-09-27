package beta.function.order.dto;

import beta.function.account.dto.AccountDTO;

import java.sql.Date;

public class PaymentDTO {

    private int paymentCode;
    private Date paymentDate;
    private int amount;
    private AccountDTO accountDTO;

    public PaymentDTO() {
    }

    public PaymentDTO(int paymentCode, Date paymentDate, int amount, AccountDTO accountDTO) {
        this.paymentCode = paymentCode;
        this.paymentDate = paymentDate;
        this.amount = amount;
        this.accountDTO = accountDTO;
    }

    public int getPaymentCode() {
        return paymentCode;
    }

    public void setPaymentCode(int paymentCode) {
        this.paymentCode = paymentCode;
    }

    public Date getPaymentDate() {
        return paymentDate;
    }

    public void setPaymentDate(Date paymentDate) {
        this.paymentDate = paymentDate;
    }

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }

    public AccountDTO getAccountDTO() {
        return accountDTO;
    }

    public void setAccountDTO(AccountDTO accountDTO) {
        this.accountDTO = accountDTO;
    }

    @Override
    public String toString() {
        return "PaymentDTO{" +
                "paymentCode=" + paymentCode +
                ", paymentDate=" + paymentDate +
                ", amount=" + amount +
                ", accountDTO=" + accountDTO +
                '}';
    }
}
