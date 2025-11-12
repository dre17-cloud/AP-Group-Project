package models;

import java.io.Serializable;
import java.text.DecimalFormat;
import java.util.Date;

public class Invoice implements Serializable {
    private static final long serialVersionUID = 1L;
    private static int invoiceCounter = 1;

    private String invoiceNumber;
    private Shipment shipment;
    private double amount;
    private String status;
    private Date dateIssued;

    public Invoice(Shipment shipment) {
        this.invoiceNumber = "INV" + String.format("%04d", invoiceCounter++);
        this.shipment = shipment;
        this.amount = shipment.getCost();
        this.status = "Unpaid";
        this.dateIssued = new Date();
    }

    public void makePayment(double amountPaid) {
        DecimalFormat df = new DecimalFormat("#,###.00");
        if (amountPaid >= amount) {
            status = "Paid";
            System.out.println("✅ Full payment of $" + df.format(amountPaid) + " received.");
        } else if (amountPaid > 0) {
            status = "Partially Paid";
            System.out.println("💰 Partial payment received: $" + df.format(amountPaid));
        } else {
            System.out.println("⚠️ No payment made.");
        }
    }

    public void printInvoice() {
        DecimalFormat df = new DecimalFormat("#,###.00");
        System.out.println("\n===== INVOICE =====");
        System.out.println("Invoice #: " + invoiceNumber);
        System.out.println("Shipment #: " + shipment.getTrackingNumber());
        System.out.println("Amount: $" + df.format(amount));
        System.out.println("Status: " + status);
        System.out.println("Date: " + dateIssued);
    }

    public String getInvoiceNumber() { return invoiceNumber; }
    public String getStatus() { return status; }
    public double getAmount() { return amount; }
}
