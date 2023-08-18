package com.rdstation;

import java.util.*;
import java.util.stream.Collectors;

public class CustomerSuccessBalancing {

    private final List<CustomerSuccess> customerSuccess;
    private final List<Customer> customers;
    private final List<Integer> customerSuccessAway;

    public CustomerSuccessBalancing(List<CustomerSuccess> customerSuccess,
                                    List<Customer> customers,
                                    List<Integer> customerSuccessAway) {
        this.customerSuccess = customerSuccess;
        this.customers = customers;
        this.customerSuccessAway = customerSuccessAway;
    }


    public int run() {
        List<CustomerSuccess> customerSuccessOrdered = customerSuccess.stream().sorted(Comparator.comparing(CustomerSuccess::getScore)).
                collect(Collectors.toList());
        List<Customer> customersOrdered = customers.stream().sorted(Comparator.comparing(Customer::getScore)).
                collect(Collectors.toList());


        int idCsMostCostumers = 0;
        int totalCustomersCsMostCustomers = 0;
        int totalCustomersChecked = 0;

        for (CustomerSuccess customerSuccess : customerSuccessOrdered) {
            if (!customerSuccessAway.contains(customerSuccess.getId())) {

                int totalCustomer = 0;
                for (int j = totalCustomersChecked; j < customersOrdered.size() &&
                        checkScore(customersOrdered.get(j), customerSuccess); j++) {
                    totalCustomersChecked++;
                    totalCustomer++;
                }


                if (totalCustomer > totalCustomersCsMostCustomers) {
                    totalCustomersCsMostCustomers = totalCustomer;
                    idCsMostCostumers = customerSuccess.getId();
                } else if (totalCustomer == totalCustomersCsMostCustomers) {
                    idCsMostCostumers = 0;
                }
            }
        }

        return idCsMostCostumers;

    }

    boolean checkScore(Customer customer, CustomerSuccess customerSuccess){
        return customer.getScore() <= customerSuccess.getScore();
    }
}
