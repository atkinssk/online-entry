package uk.org.windswept;

import com.vaadin.ui.Component;

/**
 * Created by 802998369 on 24/08/2016.
 */
public class DisplayField
{
    private final String name;
    private final String caption;
    private final DisplayFieldType type;

    public DisplayField(String name, String caption, DisplayFieldType type)
    {
        this.name = name;
        this.caption = caption;
        this.type = type;
    }

    public String getName()
    {
        return name;
    }

    public String getCaption()
    {
        return caption;
    }

    public DisplayFieldType getType()
    {
        return type;
    }

    public Component createComponent()
    {
        return type.component(name, caption);
    }
}
