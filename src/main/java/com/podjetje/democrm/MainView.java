package com.podjetje.democrm;

import com.podjetje.democrm.entity.Customer;
import com.podjetje.democrm.entity.Meeting;
import com.podjetje.democrm.service.ConclusionTypeService;
import com.podjetje.democrm.service.CustomerService;
import com.podjetje.democrm.service.MeetingService;
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
public class MainView extends HorizontalLayout {
    private final CustomerService customerService;
    private final MeetingService meetingService;
    private final ConclusionTypeService conclusionTypeService;
    final Grid<Customer> customersGrid = new Grid<>(Customer.class);
    final Grid<Meeting> meetingsGrid = new Grid<>(Meeting.class);
    private final CustomerEditor customerEditor;
    private final MeetingEditor meetingEditor;
    private TextField filterFirstNameText = new TextField();
    private TextField filterLastNameText = new TextField();
    private final Button filterBtn;
    private final Button clearBtn;
    private final Button addNewBtn;
    private final Button addNewMeetingBtn;

    private final VerticalLayout customersLayout;
    private final VerticalLayout meetingsLayout;


    public MainView(CustomerService customerService, CustomerEditor customerEditor, MeetingService meetingService,
                    MeetingEditor meetingEditor, ConclusionTypeService conclusionTypeService){
        this.customerService = customerService;
        this.customerEditor = customerEditor;
        this.meetingService = meetingService;
        this.meetingEditor = meetingEditor;
        this.conclusionTypeService = conclusionTypeService;
        this.filterBtn = new Button("Filter", VaadinIcon.PLUS.create());
        this.clearBtn = new Button("Clear filter");
        this.addNewBtn = new Button("New customer", VaadinIcon.PLUS.create());
        this.addNewMeetingBtn = new Button("New Meeting", VaadinIcon.PLUS.create());

        customersLayout = new VerticalLayout();
        meetingsLayout = new VerticalLayout();

        add(customersLayout,meetingsLayout);
        showCustomersGrid();
        showMeetingsGrid();

    }

    private void showCustomersGrid(){
        // build layout
        HorizontalLayout actions = new HorizontalLayout(filterFirstNameText, filterLastNameText, filterBtn, clearBtn, addNewBtn);
        customersLayout.add(actions, customersGrid, customerEditor);
        filterFirstNameText.setPlaceholder("filter by first name...");
        filterLastNameText.setPlaceholder("filter by last name...");
        customersGrid.setColumns("id", "firstName", "lastName", "email", "phone");
        updateCustomersList();

        // Connect selected Customer to editor or hide if none is selected
        customersGrid.asSingleSelect().addValueChangeListener(e -> {
            customerEditor.editCustomer(e.getValue());
            updateMeetingsList();
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

    private void showMeetingsGrid(){
        // build layout
        meetingsLayout.add(addNewMeetingBtn, meetingsGrid, meetingEditor);
        meetingsGrid.setColumns("id", "location","date", "timeStart", "timeEnd");
        meetingsGrid.addColumn(Meeting::getCustomerName).setHeader("Customer");
        updateMeetingsList();

        // Connect selected Customer to editor or hide if none is selected
        meetingsGrid.asSingleSelect().addValueChangeListener(e -> {
            meetingEditor.editMeeting(e.getValue());
        });

        // Instantiate and edit new Customer the new button is clicked
        addNewMeetingBtn.addClickListener(e -> meetingEditor.editMeeting(new Meeting()));

        // Listen changes made by the editor, refresh data from backend
        meetingEditor.setChangeHandler(() -> {
            meetingEditor.setVisible(false);
            updateMeetingsList();
        });
    }

    private void updateMeetingsList() {
        Customer selectedCustomer = customersGrid.asSingleSelect().getValue();
        if (selectedCustomer!=null){
            meetingsGrid.setItems(meetingService.getMeetingByCustomer(selectedCustomer));
        }
        else{
            meetingsGrid.setItems();
        }
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