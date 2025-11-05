package model;

import java.text.DecimalFormat;
import java.util.Date;

public class Invoice {
    private static int invoiceCounter = 1; // auto-counter for invoice numbers

    private String invoiceNumber;
    private Shipment shipment;
    private double totalAmount;
    private String paymentStatus; // Paid, Partially Paid, Unpaid
    private Date issueDate;

    // Constructor
    public Invoice(Shipment shipment) {
        this.invoiceNumber = generateInvoiceNumber();
        this.shipment = shipment;
        this.totalAmount = shipment.getCost();
        this.paymentStatus = "Unpaid";
        this.issueDate = new Date();
    }

    // Generate invoice number automatically (e.g., INV0001, INV0002)
    private String generateInvoiceNumber() {
        String num = "INV" + String.format("%04d", invoiceCounter);
        invoiceCounter++; // increment for next invoice
        return num;
    }

    // Make a payment
    public void makePayment(double amountPaid) {
        DecimalFormat df = new DecimalFormat("#,###.00");

        if (amountPaid >= totalAmount) {
            paymentStatus = "Paid";
            System.out.println("Full payment of $" + df.format(amountPaid) + " received. Invoice marked as PAID.");
        } else if (amountPaid > 0) {
            paymentStatus = "Partially Paid";
            double remaining = totalAmount - amountPaid;
            System.out.println("Partial payment of $" + df.format(amountPaid) + " received. Remaining balance: $" + df.format(remaining));
        } else {
            System.out.println("No payment made. Invoice remains UNPAID.");
        }
    }

    // Display invoice details
    public void printInvoice() {
        DecimalFormat df = new DecimalFormat("#,###.00");

        System.out.println("========== SMARTSHIP INVOICE ==========");
        System.out.println("Invoice #: " + invoiceNumber);
        System.out.println("Date: " + issueDate);
        System.out.println("Shipment #: " + shipment.getTrackingNumber());
        System.out.println("Destination: " + shipment.getZone() + " (Zone)");
        System.out.println("Total Amount: $" + df.format(totalAmount));
        System.out.println("Status: " + paymentStatus);
        System.out.println("=======================================");
    }

    // Getters
    public String getInvoiceNumber() { return invoiceNumber; }
    public double getTotalAmount() { return totalAmount; }
    public String getPaymentStatus() { return paymentStatus; }
    public Date getIssueDate() { return issueDate; }
}
