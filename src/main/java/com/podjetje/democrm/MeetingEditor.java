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

    /* Fields to edit properties in Customer entity */
    TextField location = new TextField("Location");
    ComboBox<Customer> customerComboBox = new ComboBox<>();
    DatePicker datePicker = new DatePicker("Date");
    TimePicker startTimePicker = new TimePicker("Begining");
    TimePicker endTimePicker = new TimePicker("End");
    Label infoLabel = new Label();

    /* Action buttons */
    Button save = new Button("Save", VaadinIcon.CHECK.create());

    Binder<Meeting> binder = new Binder<>(Meeting.class);
    private ChangeHandler changeHandler;

    @Autowired
    public MeetingEditor(MeetingServiceImpl meetingService, ConclusionServiceImpl conclusionService,
                         ConclusionTypeService conclusionTypeService, CustomerService customerService) {

        this.meetingService = meetingService;
        this.conclusionService = conclusionService;
        this.conclusionTypeService = conclusionTypeService;
        this.customerService = customerService;

        add(customerComboBox, location, datePicker, startTimePicker, endTimePicker, save);

        // bind using naming convention
        binder.forField(location).bind(Meeting::getLocation,Meeting::setLocation);
        binder.forField(datePicker).bind(Meeting::getDate,Meeting::setDate);
        binder.forField(startTimePicker).bind(Meeting::getTimeStart,Meeting::setTimeStart);
        binder.forField(endTimePicker).bind(Meeting::getTimeEnd,Meeting::setTimeEnd);
        binder.forField(customerComboBox).bind(Meeting::getCustomer,Meeting::setCustomer);
        customerComboBox.setLabel("Customer");
        customerComboBox.setItemLabelGenerator(Customer::getFullName);
        customerComboBox.setItems(customerService.getAllCustomers());

        customerComboBox.addFocusListener(e -> updateCustomersList());

        save.getElement().getThemeList().add("primary");

        // wire action buttons to save, delete and reset
        save.addClickListener(e -> save());
        setVisible(false);
    }

    private void updateCustomersList() {
        customerComboBox.setItems(customerService.getAllCustomers());
    }


    void save() {
        try {
            binder.writeBean(meeting);
            meetingService.saveMeeting(meeting);
            changeHandler.onChange();
            meeting = new Meeting();
            meeting.setTimeEnd(LocalTime.now());
            meeting.setTimeStart(LocalTime.now());
            binder.readBean(meeting);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public interface ChangeHandler {
        void onChange();
    }

    public final void editMeeting(Meeting m) {
        if (m == null) {
            setVisible(false);
            return;
        }
        final boolean persisted = m.getId() != null;
        if (persisted) {
            // Find fresh entity for editing
            meeting = meetingService.getMeetingById(m.getId());
            if(meeting.getTimeStart()==null)
                meeting.setTimeStart(LocalTime.now());
            if(meeting.getTimeEnd()==null)
                meeting.setTimeEnd(LocalTime.now());
            binder.readBean(meeting);
        }
        else {
            meeting = m;
        }

        // Bind customer properties to similarly named fields
        // Could also use annotation or "manual binding" or programmatically
        // moving values from fields to entities before saving


        setVisible(true);
    }

    public void setChangeHandler(ChangeHandler h) {
        // ChangeHandler is notified when either save or delete
        // is clicked
        changeHandler = h;
    }
}
