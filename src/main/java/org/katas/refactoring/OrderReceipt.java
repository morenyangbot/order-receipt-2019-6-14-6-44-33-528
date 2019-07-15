package org.katas.refactoring;

/**
 * OrderReceipt prints the details of order including customer name, address, description, quantity,
 * price and amount. It also calculates the sales tax @ 10% and prints as part
 * of order. It computes the total order amount (amount of individual lineItems +
 * total sales tax) and prints it.
 */
public class OrderReceipt {
    private static final double TAX_RATE = .10;

    private Order o;

    public OrderReceipt(Order o) {
        this.o = o;
    }

    private void appendReceiptHeader(StringBuilder builder) {
        builder.append("======Printing Orders======\n");
    }

    private void appendCustomerInfo(StringBuilder builder) {
        builder.append(o.getCustomerName()).append(o.getCustomerAddress()).append("\n");
    }

    private void appendOrderItems(StringBuilder output) {
        for (LineItem lineItem : o.getLineItems()) {
            output.append(lineItem.getDescription()).append('\t');
            output.append(lineItem.getPrice()).append('\t');
            output.append(lineItem.getQuantity()).append('\t');
            output.append(lineItem.totalAmount()).append('\n');
        }
    }

    private double calculateSalesTax() {
        return o.getLineItems().stream().map(LineItem::totalAmount).mapToDouble(item -> item * TAX_RATE).sum();
    }

    private double calculateTotal() {
        return o.getLineItems().stream().mapToDouble(LineItem::totalAmount).sum() + calculateSalesTax();
    }

    public String printReceipt() {
        StringBuilder output = new StringBuilder();
        appendReceiptHeader(output);
        appendCustomerInfo(output);
        appendOrderItems(output);
        // prints the state tax
        output.append("Sales Tax").append('\t').append(calculateSalesTax());
        // print total amount
        output.append("Total Amount").append('\t').append(calculateTotal());
        return output.toString();
    }
}