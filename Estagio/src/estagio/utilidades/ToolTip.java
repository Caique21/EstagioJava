/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package estagio.utilidades;

import javafx.event.EventHandler;
import javafx.scene.Node;
import javafx.scene.control.Tooltip;
import javafx.scene.input.MouseEvent;

/**
 *
 * @author carlo
 */
public abstract class ToolTip
{
    public static void bindTooltip(final Node node, final Tooltip tooltip)
    {
        node.setOnMouseMoved(new EventHandler<MouseEvent>()
        {
            @Override
            public void handle(MouseEvent event)
            {
                // +15 moves the tooltip 15 pixels below the mouse cursor;
                // if you don't change the y coordinate of the tooltip, you
                // will see constant screen flicker
                tooltip.setStyle("-fx-font-size:12");
                if(!tooltip.isShowing())
                    tooltip.show(node, event.getScreenX() + 15, event.getScreenY() + 15);
            }
        });
        
        node.setOnMouseExited(new EventHandler<MouseEvent>()
        {
            @Override
            public void handle(MouseEvent event)
            {
                tooltip.hide();
            }
        });
    }
}
