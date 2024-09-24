package beta.function.order.dto;

public class OrderDTO {
    private int orderCode;

    public OrderDTO() {
    }

    public OrderDTO(int orderCode) {
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
