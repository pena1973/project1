package myRider.listeners;

import myRider.View;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class PopupClass extends MouseAdapter
{
    private View view;

    public PopupClass(View view)
    {
        this.view = view;
    }

    public void mousePressed(MouseEvent event)
    {

    }

    public void mouseReleased(MouseEvent event)
    {
        if (event.isPopupTrigger())
        {
             view.openMenu(event);
        }
    }

    @Override
    public void mouseClicked(MouseEvent event)
    {

    }
}
