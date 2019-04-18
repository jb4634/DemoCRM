package com.podjetje.democrm;

import com.podjetje.democrm.entity.Customer;
import com.podjetje.democrm.service.CustomerService;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.icon.VaadinIcon;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.router.Route;


/**
 * The main view contains a button and a click listener.
 */
@Route("")
public class MainView extends VerticalLayout {
    private final CustomerService customerService;
    final Grid<Customer> customersGrid = new Grid<>(Customer.class);
    private final CustomerEditor customerEditor;
    private TextField filterFirstNameText = new TextField();
    private TextField filterLastNameText = new TextField();
    private final Button filterBtn;
    private final Button clearBtn;
    private final Button addNewBtn;


    public MainView(CustomerService customerService, CustomerEditor customerEditor){
        this.customerService = customerService;
        this.customerEditor = customerEditor;
        this.filterBtn = new Button("Filter", VaadinIcon.PLUS.create());
        this.clearBtn = new Button("Clear filter");
        this.addNewBtn = new Button("New customer", VaadinIcon.PLUS.create());
        showCustomersGrid();

        newCustomerForm();

    }

    private void showCustomersGrid(){
        // build layout
        HorizontalLayout actions = new HorizontalLayout(filterFirstNameText, filterLastNameText, filterBtn, clearBtn, addNewBtn);
        add(actions, customersGrid, customerEditor);
        filterFirstNameText.setPlaceholder("filter by first name...");
        filterLastNameText.setPlaceholder("filter by last name...");
        customersGrid.setColumns("id", "firstName", "lastName", "email", "phone");
        updateCustomersList();

        // Connect selected Customer to editor or hide if none is selected
        customersGrid.asSingleSelect().addValueChangeListener(e -> {
            customerEditor.editCustomer(e.getValue());
        });

        // Instantiate and edit new Customer the new button is clicked
        addNewBtn.addClickListener(e -> customerEditor.editCustomer(new Customer()));

        // Listen changes made by the editor, refresh data from backend
        customerEditor.setChangeHandler(() -> {
            customerEditor.setVisible(false);
            updateCustomersList();
        });
        filterBtn.addClickListener(e -> updateCustomersList());
        clearBtn.addClickListener(e -> clearFilter());

    }

    private void newCustomerForm(){

    }

    private void updateCustomersList() {
        customersGrid.setItems(customerService.getCustomersByName(filterFirstNameText.getValue(),filterLastNameText.getValue()));
    }

    private void clearFilter(){
        filterFirstNameText.setValue("");
        filterLastNameText.setValue("");
        updateCustomersList();
    }
}