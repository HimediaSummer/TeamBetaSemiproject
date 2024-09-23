package beta.function.order.dto;

public class orderDTO {
    private int orderCode;

    public orderDTO() {
    }

    public orderDTO(int orderCode) {
        this.orderCode = orderCode;
    }

    public int getOrderCode() {
        return orderCode;
    }

    public void setOrderCode(int orderCode) {
        this.orderCode = orderCode;
    }

    @Override
    public String toString() {
        return "orderDTO{" +
                "orderCode=" + orderCode +
                '}';
    }
}
