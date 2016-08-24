package uk.org.windswept;

import com.vaadin.annotations.Theme;
import com.vaadin.annotations.VaadinServletConfiguration;
import com.vaadin.annotations.Widgetset;
import com.vaadin.server.VaadinRequest;
import com.vaadin.server.VaadinServlet;
import com.vaadin.ui.Button;
import com.vaadin.ui.Component;
import com.vaadin.ui.DateField;
import com.vaadin.ui.Label;
import com.vaadin.ui.Layout;
import com.vaadin.ui.Notification;
import com.vaadin.ui.TabSheet;
import com.vaadin.ui.TextField;
import com.vaadin.ui.UI;
import com.vaadin.ui.VerticalLayout;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.annotation.WebServlet;
import java.util.Date;
import java.util.List;

import static com.google.gwt.thirdparty.guava.common.collect.Lists.newArrayList;

/**
 * This UI is the application entry point. A UI may either represent a browser window 
 * (or tab) or some part of a html page where a Vaadin application is embedded.
 * <p>
 * The UI is initialized using {@link #init(VaadinRequest)}. This method is intended to be 
 * overridden to add component to the user interface and initialize non-component functionality.
 */
@Theme("mytheme")
@Widgetset("uk.org.windswept.MyAppWidgetset")
public class OnlineEntryUI extends UI {

    private static final Logger LOGGER = LoggerFactory.getLogger(OnlineEntryUI.class);

    private Date startDate = new Date();

    private Layout buildLayout(List<DisplayField> fields)
    {
        final VerticalLayout layout = new VerticalLayout();
        for (DisplayField field : fields)
        {
            Component component = field.createComponent();
            layout.addComponent(component);
        }
        layout.setMargin(true);
        layout.setSpacing(true);
        return layout;
    }


    @Override
    protected void init(VaadinRequest vaadinRequest) {

        final VerticalLayout layout = new VerticalLayout();
        final TabSheet tabsheet = new TabSheet();

        tabsheet.addTab(buildLayout(boatDetailsFields()), "Boat Details");
        tabsheet.addTab(buildLayout(personFields()), "Helm/Crew");

        layout.addComponent(tabsheet);

        Button button = new Button("Submit", e -> {
            LOGGER.info("Details updated");
            Notification.show("Details updated");
        });

        layout.addComponent(button);

        setContent(layout);
    }

    private List<DisplayField> boatDetailsFields()
    {
        return newArrayList
        (
                new DisplayField("boatname","Boat Name", DisplayFieldType.TEXT),
                new DisplayField("sailnumber","Sail Number", DisplayFieldType.TEXT),
                new DisplayField("type","Type", DisplayFieldType.TEXT),
                new DisplayField("handicap","Handicap", DisplayFieldType.NUMBER)
        );
    }

    private List<DisplayField> personFields()
    {
        return newArrayList
        (
                new DisplayField("name","Name", DisplayFieldType.TEXT),
                new DisplayField("address","Address", DisplayFieldType.TEXT_AREA),
                new DisplayField("email","Email", DisplayFieldType.EMAIL),
                new DisplayField("dob","Date of Birth", DisplayFieldType.DATE)
        );
    }

    @WebServlet(urlPatterns = "/*", name = "OnlineEntryUIServlet", asyncSupported = true)
    @VaadinServletConfiguration(ui = OnlineEntryUI.class, productionMode = false)
    public static class OnlineEntryUIServlet extends VaadinServlet {
    }
}
