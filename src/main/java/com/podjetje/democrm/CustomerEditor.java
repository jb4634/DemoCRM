package com.podjetje.democrm;

import com.podjetje.democrm.entity.Customer;
import com.podjetje.democrm.service.impl.CustomerServiceImpl;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.formlayout.FormLayout;
import com.vaadin.flow.component.html.Label;
import com.vaadin.flow.component.icon.VaadinIcon;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.data.binder.Binder;
import com.vaadin.flow.spring.annotation.SpringComponent;
import com.vaadin.flow.spring.annotation.UIScope;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * CustomerEditor is the form for creating, updating and removing Customers.
 */
@SpringComponent
@UIScope
public class CustomerEditor extends FormLayout {
    private final CustomerServiceImpl customerService;

    /**
     * The currently edited customer
     */
    private Customer customer;

    // Fields to edit properties in Customer entity
    TextField firstName = new TextField("First name");
    TextField lastName = new TextField("Last name");
    TextField phone = new TextField("Phone number");
    TextField email = new TextField("Email");

    // Label for showing information
    Label infoLabel = new Label();

    // Action buttons
    Button save = new Button("Save", VaadinIcon.CHECK.create());
    Button cancel = new Button("Cancel");
    Button delete = new Button("Delete", VaadinIcon.TRASH.create());
    HorizontalLayout actions = new HorizontalLayout(save, cancel, delete);

    // Binder that bindes form fields to currently edited customer
    Binder<Customer> binder = new Binder<>(Customer.class);
    private ChangeHandler changeHandler;

    @Autowired
    public CustomerEditor(CustomerServiceImpl customerService) {
        this.customerService = customerService;

        add(firstName, lastName, phone, email, actions);

        // Bind using naming convention
        binder.bindInstanceFields(this);

        // Change the theme (color) of the buttons
        save.getElement().getThemeList().add("primary"); // blue
        delete.getElement().getThemeList().add("error"); // red

        // Wire action buttons to save, delete and reset
        save.addClickListener(e -> save());
        delete.addClickListener(e -> delete());
        cancel.addClickListener(e -> editCustomer(customer));
        setVisible(false);
    }

    // Deletes the Customer and refreshes with changeHandler
    void delete() {
        customerService.deleteCustomer(customer);
        changeHandler.onChange();
    }

    // Saves the Customer and refreshes with changeHanlder
    void save() {
        customerService.saveCustomer(customer);
        changeHandler.onChange();
    }

    public interface ChangeHandler {
        void onChange();
    }


    public final void editCustomer(Customer c) {
        // Fallback if the argument is null
        if (c == null) {
            setVisible(false);
            return;
        }

        // Checks if given Customer exists in the database
        final boolean persisted = c.getId() != null;
        if (persisted) {
            // Find fresh entity for editing as currently edited customer -- update
            customer = customerService.getCustomerById(c.getId());
        }
        else {
            // Sets newly created object Customer as currently edited customer -- save
            customer = c;
        }
        cancel.setVisible(persisted);

        // Bind customer properties to similarly named fields
        binder.setBean(customer);

        setVisible(true);

        // Focus first name initially
        firstName.focus();
    }

    public void setChangeHandler(ChangeHandler h) {
        // ChangeHandler is notified when either save or delete is clicked
        changeHandler = h;
    }

}