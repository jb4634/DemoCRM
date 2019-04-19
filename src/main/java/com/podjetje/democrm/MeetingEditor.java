package com.podjetje.democrm;

import com.podjetje.democrm.entity.Customer;
import com.podjetje.democrm.entity.Meeting;
import com.podjetje.democrm.service.ConclusionTypeService;
import com.podjetje.democrm.service.CustomerService;
import com.podjetje.democrm.service.impl.ConclusionServiceImpl;
import com.podjetje.democrm.service.impl.MeetingServiceImpl;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.combobox.ComboBox;
import com.vaadin.flow.component.datepicker.DatePicker;
import com.vaadin.flow.component.formlayout.FormLayout;
import com.vaadin.flow.component.html.Label;
import com.vaadin.flow.component.icon.VaadinIcon;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.component.timepicker.TimePicker;
import com.vaadin.flow.data.binder.Binder;
import com.vaadin.flow.spring.annotation.SpringComponent;
import com.vaadin.flow.spring.annotation.UIScope;
import org.springframework.beans.factory.annotation.Autowired;

import java.time.LocalTime;

@SpringComponent
@UIScope
public class MeetingEditor extends FormLayout {
    private final MeetingServiceImpl meetingService;
    private final ConclusionServiceImpl conclusionService;
    private final ConclusionTypeService conclusionTypeService;
    private final CustomerService customerService;

    /**
     * The currently edited meeting
     */
    private Meeting meeting;

    /* Fields to edit properties in Meeting entity */
    TextField location = new TextField("Location");
    ComboBox<Customer> customerComboBox = new ComboBox<>();
    DatePicker datePicker = new DatePicker("Date");
    TimePicker startTimePicker = new TimePicker("Begining");
    TimePicker endTimePicker = new TimePicker("End");

    // Label for showing information
    Label infoLabel = new Label();

    // Button to show the form for creating a new Meeting
    Button save = new Button("Save", VaadinIcon.CHECK.create());

    // Binder that bindes form fields to currently edited meeting
    Binder<Meeting> binder = new Binder<>(Meeting.class);
    private ChangeHandler changeHandler;

    @Autowired
    public MeetingEditor(MeetingServiceImpl meetingService, ConclusionServiceImpl conclusionService,
                         ConclusionTypeService conclusionTypeService, CustomerService customerService) {

        this.meetingService = meetingService;
        this.conclusionService = conclusionService;
        this.conclusionTypeService = conclusionTypeService;
        this.customerService = customerService;

        // Add fields to edit Meeting properties to the layout
        add(customerComboBox, location, datePicker, startTimePicker, endTimePicker, save);

        // Bind form fields to matching Meeting properties
        binder.forField(location).bind(Meeting::getLocation,Meeting::setLocation);
        binder.forField(datePicker).bind(Meeting::getDate,Meeting::setDate);
        binder.forField(startTimePicker).bind(Meeting::getTimeStart,Meeting::setTimeStart);
        binder.forField(endTimePicker).bind(Meeting::getTimeEnd,Meeting::setTimeEnd);
        binder.forField(customerComboBox).bind(Meeting::getCustomer,Meeting::setCustomer);
        customerComboBox.setLabel("Customer");
        customerComboBox.setItemLabelGenerator(Customer::getFullName); // Present Customer as firstName+" "+lastName
        customerComboBox.setItems(customerService.getAllCustomers()); // Fill ComboBox with data from backend
        customerComboBox.addFocusListener(e -> updateCustomersList()); // Refresh with data from backend on focus

        save.getElement().getThemeList().add("primary"); // blue

        // Wire button to save action
        save.addClickListener(e -> save());

        // Hide form when initialized
        setVisible(false);
    }

    // Refresh Customers in ComboBox with data from backend
    private void updateCustomersList() {
        customerComboBox.setItems(customerService.getAllCustomers());
    }


    // Save Customer to backend and clear the fields
    void save() {
        try {
            // Save field values as currently edited meeting
            binder.writeBean(meeting);

            // Save meeting to backend
            meetingService.saveMeeting(meeting);

            changeHandler.onChange();

            // Clear fields
            meeting = new Meeting();
            meeting.setTimeEnd(LocalTime.now());    //  TimePicker value cannot be set to null
            meeting.setTimeStart(LocalTime.now());  //  Set to current time
            binder.readBean(meeting);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public interface ChangeHandler {
        void onChange();
    }

    // Updates current or saves new Meeting to backend
    public final void editMeeting(Meeting m) {
        // Fallback if the argument is null
        if (m == null) {
            setVisible(false);
            return;
        }
        // Checks if given Customer exists in the database
        final boolean persisted = m.getId() != null;
        if (persisted) {
            // Find fresh entity for editing as currently edited customer -- update
            meeting = meetingService.getMeetingById(m.getId());

            // TimePicker value cannot be set to null
            // If time in database is null --> change to current time
            if(meeting.getTimeStart()==null)
                meeting.setTimeStart(LocalTime.now());
            if(meeting.getTimeEnd()==null)
                meeting.setTimeEnd(LocalTime.now());
            binder.readBean(meeting);
        }
        else {
            // Sets newly created object Customer as currently edited customer -- save
            meeting = m;
        }
        setVisible(true);
    }

    public void setChangeHandler(ChangeHandler h) {
        // ChangeHandler is notified when either save or delete is clicked
        changeHandler = h;
    }
}
